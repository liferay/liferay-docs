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

extension SyncManager {

	func formSynchronizer(
			key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {

		let recordSynchronizer = { (signal: @escaping Signal) -> Void in
			let record = attributes["record"] as! DDLRecord

			if record.recordId != nil {
				// update
				self.checkConflictAndSendLocalRecord(
					record: record,
					key: key,
					attributes: attributes,
					signal: signal)
			}
			else {
				// add
				self.sendLocalRecord(
					record: record,
					key: key,
					attributes: attributes,
					signal: signal)
			}
		}

		let documentSynchronizer = { (signal: @escaping Signal) -> Void in
			// Do nothing. 
			// When the record is sync-ed the documents will be sync-ed too
			// Notify as this entry is finished
			dispatch_main {
				self.delegate?.syncManager?(self,
					onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
					completedKey: key, attributes: attributes)
				signal()
			}
		}

		return key.hasPrefix("document-") ? documentSynchronizer : recordSynchronizer
	}

	private func checkConflictAndSendLocalRecord(
			record localRecord: DDLRecord,
			key: String,
			attributes: [String:AnyObject],
			signal: @escaping Signal) {

		precondition(localRecord.recordId != nil, "RecordId must be defined")

		// updating record: check consistency first
		loadRecord(localRecord.recordId!) { remoteRecord in

			if remoteRecord == nil {
				self.delegate?.syncManager?(self,
					onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
					failedKey: key,
					attributes: attributes,
					error: NSError.errorWithCause(.notAvailable,
							message: "Synchronizer for check remote record not available."))
				signal()
			}
			else if let localModifiedDate = localRecord.attributes["modifiedDate"]?.longLongValue,
					let remoteModifiedDate = remoteRecord!.attributes["modifiedDate"]?.longLongValue {

				if remoteModifiedDate <= localModifiedDate {
					self.sendLocalRecord(
						record: localRecord,
						key: key,
						attributes: attributes,
						signal: signal)
				}
				else {
					self.resolveConflict(
						remoteRecord: remoteRecord!,
						localRecord: localRecord,
						key: key,
						attributes: attributes,
						signal: signal)
				}
			}
			else {
				self.delegate?.syncManager?(self,
					onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
					failedKey: key,
					attributes: attributes,
					error: NSError.errorWithCause(.invalidServerResponse,
							message: "Synchronizer for send local record not available."))
				signal()
			}
		}
	}

	private func resolveConflict(
			remoteRecord: DDLRecord,
			localRecord: DDLRecord,
			key: String,
			attributes: [String:AnyObject],
			signal: @escaping Signal) {

		self.delegate?.syncManager?(self,
				onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
				conflictedKey: key,
				remoteValue: remoteRecord,
				localValue: localRecord) { resolution in

			switch resolution {
			case .useLocal:
				// send local to server
				self.sendLocalRecord(
					record: localRecord,
					key: key,
					attributes: attributes,
					signal: signal)

			case .useRemote:
				// overwrite cache with remote record
				var newAttrs = attributes
				newAttrs["record"] = remoteRecord

				self.cacheManager.setClean(
					collection: ScreenletName(DDLFormScreenlet.self),
					key: DDLFormSubmitFormInteractor.cacheKey(localRecord.recordId),
					value: remoteRecord.values as NSCoding,
					attributes: newAttrs)

			case .discard:
				// clear cache
				self.cacheManager.remove(
					collection: ScreenletName(DDLFormScreenlet.self),
					key: key)

			case .ignore:
				// notify but keep cache
				self.delegate?.syncManager?(self,
					onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
					failedKey: key,
					attributes: attributes,
					error: NSError.errorWithCause(.abortedDueToPreconditions,
							message: "Ignoring conflicts between records."))
				signal()
			}
		}
	}

	private func loadRecord(_ recordId: Int64, result: @escaping (DDLRecord?) -> Void) {
		let c = LiferayServerContext.connectorFactory.createDDLFormRecordLoadConnector(recordId)

		c.validateAndEnqueue {
			if let c = $0 as? DDLFormRecordLoadLiferayConnector,
					let recordData = c.resultRecordData,
					let recordAttributes = c.resultRecordAttributes {

				let remoteRecord = DDLRecord(
					data: recordData,
					attributes: recordAttributes)

				result(remoteRecord)
			}
			else {
				result(nil)
			}
		}
	}

	private func sendLocalRecord(
			record localRecord: DDLRecord,
			key: String,
			attributes: [String:AnyObject],
			signal: @escaping Signal) {

		let cachedDocument = localRecord.fieldsBy(type: DDMFieldDocument.self)
			.map {
				$0 as! DDMFieldDocument
			}.filter {
				return $0.cachedKey != nil
			}.first

		if let cachedDocument = cachedDocument {
			sendLocalDocument(document: cachedDocument,
				record: localRecord,
				recordKey: key,
				recordAttributes: attributes,
				signal: signal)
			return
		}

		let interactor = DDLFormSubmitFormInteractor(
			cacheKey: key,
			record: localRecord)

		self.prepareInteractorForSync(interactor,
			key: key,
			attributes: attributes,
			signal: signal,
			screenletClassName: ScreenletName(DDLFormScreenlet.self))

		interactor.cacheStrategy = .remoteFirst

		if !interactor.start() {
			dispatch_main {
				self.delegate?.syncManager?(self,
					onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
					failedKey: key,
					attributes: attributes,
					error: NSError.errorWithCause(.validationFailed,
							message: "Validation failed during send local record."))

				signal()
			}
		}
	}

	private func sendLocalDocument(
			document: DDMFieldDocument,
			record: DDLRecord,
			recordKey: String,
			recordAttributes: [String:AnyObject],
			signal: @escaping Signal) {

		precondition(
			document.cachedKey != nil,
			"Cached key is missing on local document")

		self.cacheManager.getAnyWithAttributes(
				collection: ScreenletName(DDLFormScreenlet.self),
				key: document.cachedKey!) { object, attributes in

			if let filePrefix = attributes?["filePrefix"] as? String,
					let folderId = attributes?["folderId"]?.longLongValue,
					let repositoryId = attributes?["repositoryId"]?.longLongValue,
					let groupId = attributes?["groupId"]?.longLongValue {

				document.currentValue = object

				let interactor = DDLFormUploadDocumentInteractor(
					filePrefix: filePrefix,
					repositoryId: repositoryId,
					groupId: groupId,
					folderId: folderId,
					document: document)

				interactor.cacheStrategy = .cacheFirst

				interactor.onSuccess = {
					document.uploadStatus = .uploaded(interactor.resultResponse!)

					// go on with record recursively
					self.sendLocalRecord(
						record: record,
						key: recordKey,
						attributes: recordAttributes,
						signal: signal)
				}

				interactor.onFailure = { err in
					self.delegate?.syncManager?(self,
						onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
						failedKey: recordKey,
						attributes: recordAttributes,
						error: err)

					// TODO retry?
					signal()
				}

				if !interactor.start() {
					dispatch_main {
						self.delegate?.syncManager?(self,
							onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
							failedKey: recordKey,
							attributes: recordAttributes,
							error: NSError.errorWithCause(.validationFailed,
									message: "Validation failed during send local document."))
						signal()
					}
				}
			}
			else {
				dispatch_main {
					self.delegate?.syncManager?(self,
						onItemSyncScreenlet: ScreenletName(DDLFormScreenlet.self),
						failedKey: recordKey,
						attributes: recordAttributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "An error ocurred when sending local document."))

					signal()
				}
			}
		}
	}

}
