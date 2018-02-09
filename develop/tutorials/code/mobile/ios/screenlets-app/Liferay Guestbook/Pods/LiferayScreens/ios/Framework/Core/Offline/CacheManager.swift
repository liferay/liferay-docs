/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
import Foundation
import YapDatabase
import YapDatabase.YapDatabaseView
import YapDatabase.YapDatabaseFilteredView

public enum CacheStrategyType: String {
	case remoteOnly = "remote-only"
	case remoteFirst = "remote-first"
	case cacheOnly = "cache-only"
	case cacheFirst = "cache-first"
}

@objc open class CacheManager: NSObject {

	fileprivate static let tableSchemaDatabase = "lr_cache_"

	open let database: YapDatabase
	open let readConnection: YapDatabaseConnection
	open let writeConnection: YapDatabaseConnection

	public init(database: YapDatabase) {
		self.database = database
		readConnection = database.newConnection()
		writeConnection = database.newConnection()

		super.init()

		registerPendingToSyncView(nil)
	}

	public convenience init(name: String) {
		let dbPath = CacheManager.databasePath(name)

		self.init(database: YapDatabase(path: dbPath))
	}

	public convenience init(session: LRSession, userId: Int64) {
		self.init(name: "\(session.serverName!)-\(userId)")
	}

	open func getString(collection: String, key: String, result: @escaping (String?) -> Void) {
		var value: AnyObject?
		readConnection.asyncRead({ transaction in
				value = transaction.object(forKey: key, inCollection: collection) as AnyObject?
			}, completionBlock: {
				result((value as? NSObject)?.description)
			})
	}

	open func getLocalFileURL(collection: String, key: String, result: @escaping (URL?) -> Void) {
		var value: AnyObject?

		readConnection.asyncRead({ transaction in
				value = transaction.object(forKey: key, inCollection: collection) as AnyObject?
			},
			completionBlock: {
				guard let localFileURL = value as? URL else {
					result(nil)
					return
				}

				if isCacheFilePath(localFileURL.path) {
					// use current cache path: may be different than the stored
					let filename = localFileURL.lastPathComponent
					let cacheDir = NSSearchPathForDirectoriesInDomains(.cachesDirectory, .allDomainsMask, true)[0]

					let newLocalURL = URL(fileURLWithPath: "\(cacheDir)/\(filename)")

					if (newLocalURL as NSURL).checkResourceIsReachableAndReturnError(nil) {
						result(newLocalURL)
					}
					else {
						result(nil)
					}
				}
				else {
					result(localFileURL)
				}
			})
	}

	open func getImage(collection: String, key: String, result: @escaping (UIImage?) -> Void) {
		var value: AnyObject?

		readConnection.asyncRead({ transaction in
				value = transaction.object(forKey: key, inCollection: collection) as AnyObject?
			}, completionBlock: {
				if let image = value as? UIImage {
					result(image)
				}
				else if let data = value as? Data {
					result(UIImage(data: data))
				}
				else {
					result(nil)
			}
		})
	}

	open func getAny(collection: String, key: String, result: @escaping (AnyObject?) -> Void) {
		var value: AnyObject?

		readConnection.asyncRead ({ transaction in
			value = transaction.object(forKey: key, inCollection: collection) as AnyObject?
			}, completionBlock: {
				result(value)
		})
	}

	open func getAnyWithAttributes(
			collection: String,
			key: String,
			result: @escaping (AnyObject?, [String:AnyObject]?) -> Void) {

		getSomeWithAttributes(
				collection: collection,
				keys: [key]) { objects, attributes in

			result(objects[0], attributes[0])
		}
	}

	open func getSomeWithAttributes(
			collection: String,
			keys: [String],
			result: @escaping ([AnyObject?], [[String:AnyObject]?]) -> Void) {

		var objects = [AnyObject?]()
		var attributes = [[String: AnyObject]?]()

		readConnection.asyncRead ({ transaction in
			let keyCount = keys.count

			objects = [AnyObject?](repeating: nil, count: keyCount)
			attributes = [[String: AnyObject]?](repeating: nil, count: keyCount)

			for (i, k) in keys.enumerated() {
				objects[i] = transaction.object(forKey: k, inCollection: collection) as AnyObject?

				let metadata = transaction.metadata(forKey: k, inCollection: collection) as? CacheMetadata
				attributes[i] = metadata?.attributes
			}

		}, completionBlock: {
			result(objects, attributes)
		})
	}

	open func getSome(collection: String, keys: [String], result: @escaping ([AnyObject?]) -> Void) {
		var values = [AnyObject?]()

		readConnection.asyncRead ({ transaction in
			for k in keys {
				values.append(transaction.object(forKey: k,
					inCollection: collection) as AnyObject?)
			}

		}, completionBlock: {
			result(values)
		})
	}

	open func getMetadata(collection: String, key: String, result: @escaping (CacheMetadata?) -> Void) {
		var value: AnyObject?

		readConnection.asyncRead ({ transaction in
			value = transaction.metadata(forKey: key, inCollection: collection) as AnyObject?
		}, completionBlock: {
			result(value as? CacheMetadata)
		})
	}

	open func setClean(
			collection: String,
			key: String,
			value: NSCoding,
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		// The item becomes clean (the opposite of dirty,
		// that is: synchronized): updated 'sent' & 'received' dates

		set(collection: collection,
			keys: [key],
			values: [value],
			synchronized: Date(),
			attributes: attributes,
			onCompletion: onCompletion)
	}

	open func setClean(
			collection: String,
			key: String,
			localFileURL: URL,
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		guard localFileURL.isFileURL else {
			onCompletion?()
			return
		}
		guard (localFileURL as NSURL).checkResourceIsReachableAndReturnError(nil) else {
			onCompletion?()
			return
		}

		set(collection: collection,
			keys: [key],
			values: [localFileURL as NSCoding],
			synchronized: Date(),
			attributes: attributes,
			onCompletion: onCompletion)
	}

	open func setClean(
			collection: String,
			keys: [String],
			values: [NSCoding],
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		set(collection: collection,
			keys: keys,
			values: values,
			synchronized: Date(),
			attributes: attributes,
			onCompletion: onCompletion)
	}

	open func setDirty(
			collection: String,
			key: String,
			value: NSCoding,
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		// The item becomes dirty: fresh received date but nil sent date
		set(collection: collection,
			keys: [key],
			values: [value],
			synchronized: nil,
			attributes: attributes,
			onCompletion: onCompletion)
	}

	fileprivate func set(
			collection: String,
			keys: [String],
			values: [NSCoding],
			synchronized: Date?,
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		assert(keys.count == values.count,
			"Keys and values must have same number of elements")

		writeConnection.asyncReadWrite ({ transaction in
			let metadata = CacheMetadata(
				synchronized: synchronized,
				attributes: attributes)

			for (i, k) in keys.enumerated() {
				transaction.setObject(values[i],
					forKey: k,
					inCollection: collection,
					withMetadata: metadata)
			}
		}, completionBlock: {
			onCompletion?()
		})
	}

	open func setClean(
			collection: String,
			key: String,
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		setMetadata(collection: collection,
			key: key,
			synchronized: Date(),
			attributes: attributes,
			onCompletion: onCompletion)
	}

	fileprivate func setMetadata(
			collection: String,
			key: String,
			synchronized: Date?,
			attributes: [String:AnyObject],
			onCompletion: (() -> Void)? = nil) {

		writeConnection.asyncReadWrite ({ transaction in
			if transaction.hasObject(forKey: key, inCollection: collection) {
				let newMetadata = CacheMetadata(
					synchronized: synchronized,
					attributes: attributes)

				transaction.replaceMetadata(newMetadata,
					forKey: key,
					inCollection: collection)
			}
		}, completionBlock: {
			onCompletion?()
		})
	}

	open func remove(
			collection: String,
			key: String,
			onCompletion: (() -> Void)? = nil) {

		writeConnection.asyncReadWrite ({ transaction in
			transaction.removeObject(forKey: key, inCollection: collection)
		}, completionBlock: {
			onCompletion?()
		})
	}

	open func remove(collection: String, onCompletion: (() -> Void)? = nil) {
		writeConnection.asyncReadWrite ({ transaction in
			transaction.removeAllObjects(inCollection: collection)
		}, completionBlock: {
			onCompletion?()
		})
	}

	open func removeAll(_ onCompletion: (() -> Void)? = nil) {
		writeConnection.asyncReadWrite ({ transaction in
			transaction.removeAllObjectsInAllCollections()
		}, completionBlock: {
			onCompletion?()
		})
	}

	open func countPendingToSync(_ result: @escaping (UInt) -> Void) {
		var value: UInt = 0

		pendingToSyncTransaction ({ transaction in
			value = transaction?.numberOfItemsInAllGroups() ?? 0
		}, onCompletion: {
			result(value)
		})
	}

	open func pendingToSync(
			_ result: @escaping (String, String, [String:AnyObject]) -> Bool,
			onCompletion: (() -> Void)? = nil) {

		pendingToSyncTransaction ({ transaction in
			let groups = transaction?.allGroups() ?? [String]()
			for group in groups {
				transaction?.enumerateKeysAndMetadata(inGroup: group, with: [],
				using: { (collection: String, key: String, metadata: Any, _, stop: UnsafeMutablePointer<ObjCBool>) in

					dispatch_main(true) {
						let cacheMetadata = metadata as! CacheMetadata
						if result(collection, key, cacheMetadata.attributes) {
							stop.pointee = false
						}
						else {
							stop.pointee = true
						}
					}
				})
			}
		}, onCompletion: {
			onCompletion?()
		})
	}

	//MARK "protected" methods

	open class func databasePath(_ name: String) -> String {
		let cacheFolderPath = NSSearchPathForDirectoriesInDomains(.cachesDirectory, .userDomainMask, true)[0]
		let path = (cacheFolderPath as NSString).appendingPathComponent(tableSchemaDatabase)

		let filename = name.toSafeFilename()
		let dbPath = "\(path)_\(filename)"

		CacheManager.fixWrongDatabaseFilename(filename, path: path)

		return dbPath
	}

	open func registerPendingToSyncView(_ result: ((Bool) -> Void)?) {
		let grouping = YapDatabaseViewGrouping.withKeyBlock { (_, collection, _) in
			return collection
		}

		let sorting = YapDatabaseViewSorting.withKeyBlock { (_, _, _, key1, _, key2) in
			//TODO sort by added date
			return key1.compare(key2)
		}

		let filtering = YapDatabaseViewFiltering.withMetadataBlock({ (_, _, _, _, metadata) in
			let cacheMetadata = metadata as? CacheMetadata
			return cacheMetadata?.synchronized == nil
		})

		let parentView = YapDatabaseView(grouping: grouping, sorting: sorting)

		database.asyncRegister(parentView,
			withName: "allEntries",
			connection: writeConnection,
			completionQueue: nil) { success in
				if success {
					let filterView = YapDatabaseFilteredView(
						parentViewName: "allEntries",
						filtering: filtering)

					self.database.asyncRegister(filterView,
						withName: "pendingToSync",
						connection: self.writeConnection,
						completionQueue: nil) { success in
							result?(success)
					}
				}
				else {
					result?(false)
				}
		}
	}

	// MARK: Private methods

	fileprivate func pendingToSyncTransaction(
			_ result: @escaping (YapDatabaseViewTransaction?) -> Void,
			onCompletion: @escaping () -> Void) {

		if database.registeredExtension("pendingToSync") != nil {
			readConnection.asyncRead ({ transaction in
				result(transaction.ext("pendingToSync") as? YapDatabaseViewTransaction)
			}, completionBlock: {
				onCompletion()
			})
		}
		else {
			registerPendingToSyncView { success in
				if success {
					self.readConnection.asyncRead ({ transaction in
						result(transaction.ext("pendingToSync") as? YapDatabaseViewTransaction)
					}, completionBlock: {
						onCompletion()
					})
				}
				else {
					result(nil)
					onCompletion()
				}
			}
		}
	}

	fileprivate class func fixWrongDatabaseFilename(_ filename: String, path: String) {
		// Typo in file name in Screens 1.2
		let rightDbPath = "\(path)_\(filename)"
		let wrongDbPath = "\(path)_\(filename))"

		// Use the right filename but rename wrong name first
		if FileManager.default.fileExists(atPath: wrongDbPath) {
			do {
				try FileManager.default.moveItem(atPath: wrongDbPath, toPath: rightDbPath)
			}
			catch {
			}
			}
	}

}
