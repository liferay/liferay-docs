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
import UIKit

class DDLFormSubmitFormInteractor: ServerWriteConnectorInteractor {

	let groupId: Int64

	let recordSetId: Int64

	let userId: Int64?

	let record: DDLRecord

	var resultRecordId: Int64?

	var resultAttributes: NSDictionary?

	fileprivate var lastCacheKeyUsed: String?

	// MARK: Class functions

	class func cacheKey(_ recordId: Int64?) -> String {
		if let recordId = recordId {
			return "recordId-\(recordId)"
		}
		else {
			return "draft-\(Date().timeIntervalSince1970)"
		}
	}

	// MARK: Initializers

	init(screenlet: BaseScreenlet?, record: DDLRecord) {
		let formScreenlet = screenlet as! DDLFormScreenlet

		self.groupId = (formScreenlet.groupId != 0)
			? formScreenlet.groupId
			: LiferayServerContext.groupId

		self.userId = (formScreenlet.userId != 0)
			? formScreenlet.userId
			: SessionContext.currentContext?.user.userId

		self.recordSetId = formScreenlet.recordSetId
		self.record = record

		super.init(screenlet: formScreenlet)
	}

	init(cacheKey: String, record: DDLRecord) {
		self.groupId = record.attributes["groupId"]?.int64Value
			?? LiferayServerContext.groupId

		self.userId = record.attributes["userId"]?.int64Value
			?? SessionContext.currentContext?.user.userId

		self.recordSetId = record.attributes["recordSetId"]!.int64Value
		self.record = record
		self.lastCacheKeyUsed = cacheKey

		super.init(screenlet: nil)
	}

	// MARK: ServerConnectorInteractor

	override func createConnector() -> DDLFormSubmitLiferayConnector {

		let connector: DDLFormSubmitLiferayConnector

		if let screenlet = self.screenlet as? DDLFormScreenlet {
			connector = LiferayServerContext.connectorFactory.createDDLFormSubmitConnector(
					values: record.values,
					viewModel: screenlet.viewModel)

			connector.autoscrollOnValidation = screenlet.autoscrollOnValidation
		}
		else {
			connector = LiferayServerContext.connectorFactory.createDDLFormSubmitConnector(
				values: record.values,
				viewModel: nil)
		}

		connector.groupId = groupId
		connector.userId = userId
		connector.recordId = record.recordId
		connector.recordSetId = recordSetId

		return connector
	}

	override func completedConnector(_ c: ServerConnector) {
		if let loadCon = c as? DDLFormSubmitLiferayConnector {
				self.resultRecordId = loadCon.resultRecordId
				self.resultAttributes = loadCon.resultAttributes

			if let modifiedDate = loadCon.resultAttributes?["modifiedDate"] as? NSNumber {
				record.attributes["modifiedDate"] = modifiedDate
			}
		}
	}

	// MARK: Cache methods

	override func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		let submitOp = c as! DDLFormSubmitLiferayConnector

		let cacheFunction = (cacheStrategy == .cacheFirst || c.lastError != nil)
			? cacheManager.setDirty
			: cacheManager.setClean

		lastCacheKeyUsed = lastCacheKeyUsed
			?? DDLFormSubmitFormInteractor.cacheKey(submitOp.recordId)

		cacheFunction(
			ScreenletName(DDLFormScreenlet.self),
			lastCacheKeyUsed!,
			record.values as NSCoding,
			cacheAttributes(),
			nil)
	}

	// MARK: Interactor

	override func callOnSuccess() {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		if cacheStrategy == .cacheFirst {
			precondition(
				lastCacheKeyUsed != nil,
				"CacheKey is expected on CacheFirst strategy")

			if let resultRecordId = resultRecordId {
				// create new cache entry and delete the draft one
				if lastCacheKeyUsed!.hasPrefix("draft-")
						&& record.recordId == nil {
					cacheManager.remove(
						collection: ScreenletName(DDLFormScreenlet.self),
						key: lastCacheKeyUsed!)
				}

				cacheManager.setClean(
					collection: ScreenletName(DDLFormScreenlet.self),
					key: DDLFormSubmitFormInteractor.cacheKey(resultRecordId),
					attributes: cacheAttributes())
			}
			else {
				// update current cache entry with date sent
				cacheManager.setClean(
					collection: ScreenletName(DDLFormScreenlet.self),
					key: lastCacheKeyUsed
						?? DDLFormSubmitFormInteractor.cacheKey(record.recordId),
					attributes: cacheAttributes())
			}
		}

		super.callOnSuccess()
	}

	// MARK: Private methods

	fileprivate func cacheAttributes() -> [String:AnyObject] {
		let attrs = ["record": record]

		if record.recordId == nil {
			record.attributes["groupId"] = NSNumber(value: self.groupId)
			record.attributes["recordSetId"] = NSNumber(value: self.recordSetId)
			if let userId = self.userId {
				record.attributes["userId"] = NSNumber(value: userId)
			}
		}

		return attrs
	}

}
