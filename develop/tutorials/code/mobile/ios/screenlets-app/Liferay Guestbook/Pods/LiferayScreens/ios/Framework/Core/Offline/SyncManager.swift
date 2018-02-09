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

@objc public enum SyncConflictResolution: Int {

	case useRemote
	case useLocal
	case discard
	case ignore

}

@objc public protocol SyncManagerDelegate {

	@objc optional func syncManager(_ manager: SyncManager,
		itemsCount: UInt)

	@objc optional func syncManager(_ manager: SyncManager,
		onItemSyncScreenlet screenlet: String,
		startKey: String,
		attributes: [String:AnyObject])

	@objc optional func syncManager(_ manager: SyncManager,
		onItemSyncScreenlet screenlet: String,
		completedKey: String,
		attributes: [String:AnyObject])

	@objc optional func syncManager(_ manager: SyncManager,
		onItemSyncScreenlet screenlet: String,
		failedKey: String,
		attributes: [String:AnyObject],
		error: NSError)

	@objc optional func syncManager(_ manager: SyncManager,
		onItemSyncScreenlet screenlet: String,
		conflictedKey: String,
		remoteValue: AnyObject,
		localValue: AnyObject,
		resolve: (SyncConflictResolution) -> Void)

}

public typealias OfflineSynchronizer = (String, [String:AnyObject]) -> (@escaping Signal) -> Void

@objc open class SyncManager: NSObject {

	open weak var delegate: SyncManagerDelegate?

	open let cacheManager: CacheManager

	fileprivate let syncQueue: OperationQueue
	fileprivate var synchronizers: [String:OfflineSynchronizer] = [:]

	public init(cacheManager: CacheManager) {
		self.cacheManager = cacheManager

		self.syncQueue = OperationQueue()
		self.syncQueue.maxConcurrentOperationCount = 1

		super.init()

		synchronizers[ScreenletName(UserPortraitScreenlet.self)] =  userPortraitSynchronizer
		synchronizers[ScreenletName(DDLFormScreenlet.self)] =  formSynchronizer
		synchronizers[ScreenletName(ImageGalleryScreenlet.self)] = imageGallerySynchronizer
		synchronizers["CommentsScreenlet"] = commentsSynchronizer
		synchronizers["RatingsScreenlet"] = ratingsSynchronizer
	}

	open func addSynchronizer(
			_ screenletClass: AnyClass,
			synchronizer: @escaping OfflineSynchronizer) {
		addSynchronizerWithName(ScreenletName(screenletClass), synchronizer: synchronizer)
	}

	open func addSynchronizerWithName(
			_ screenletClassName: String,
			synchronizer: @escaping OfflineSynchronizer) {
		synchronizers[screenletClassName] = synchronizer
	}

	open func clear() {
		self.cacheManager.removeAll()
	}

	open func startSync() {
		cacheManager.countPendingToSync { totalCount in
			self.delegate?.syncManager?(self, itemsCount: totalCount)

			if totalCount > 0 {
				self.cacheManager.pendingToSync({ (screenlet, key, attributes) -> Bool in
					self.delegate?.syncManager?(self,
						onItemSyncScreenlet: screenlet,
						startKey: key,
						attributes: attributes)

					self.enqueueSyncForScreenlet(screenlet, key, attributes)

					return true
				})
			}
		}
	}

	open func prepareInteractorForSync(
			_ interactor: ServerConnectorInteractor,
			key: String,
			attributes: [String:AnyObject],
			signal: @escaping Signal,
			screenletClassName: String) {

		// this strategy saves the send date after the connector
		interactor.cacheStrategy = .cacheFirst

		interactor.onSuccess = {
			self.delegate?.syncManager?(self,
				onItemSyncScreenlet: screenletClassName,
				completedKey: key,
				attributes: attributes)

			signal()
		}

		interactor.onFailure = { (err: NSError) in
			self.delegate?.syncManager?(self,
				onItemSyncScreenlet: screenletClassName,
				failedKey: key,
				attributes: attributes,
				error: err)

			// TODO retry?
			signal()
		}
	}

	fileprivate func enqueueSyncForScreenlet(
			_ screenletName: String,
			_ key: String,
			_ attributes: [String:AnyObject]) {

		if let syncBuilder = synchronizers[screenletName] {
			let synchronizer = syncBuilder(key, attributes)
			syncQueue.addOperation(to_sync(synchronizer))
		}
	}

}
