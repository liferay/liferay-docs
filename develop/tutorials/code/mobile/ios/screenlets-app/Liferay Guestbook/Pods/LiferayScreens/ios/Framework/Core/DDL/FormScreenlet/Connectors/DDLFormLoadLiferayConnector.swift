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
import LRMobileSDK

open class DDLFormLoadLiferayConnector: ServerConnector {

	open let structureId: Int64

	open var resultRecord: DDLRecord?
	open var resultUserId: Int64?

	// MARK: Initializers

	public init(structureId: Int64) {
		self.structureId = structureId

		super.init()
	}

}

open class Liferay62DDLFormLoadConnector: DDLFormLoadLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let service = LRDDMStructureService_v62(session: session)

		do {
			let structureDataDictionary = try service?.getStructureWithStructureId(structureId)

			if let xsd = structureDataDictionary?["xsd"]! as? String {
				if let userIdValue = (structureDataDictionary?["userId"] as AnyObject).int64Value {
					resultUserId = userIdValue
				}

				resultRecord = DDLRecord(
					xsd: xsd,
					locale: Locale(identifier: NSLocale.currentLocaleString))
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not load ddl form with this structureId.")
				resultRecord = nil
				resultUserId = nil
			}
		}
		catch let error as NSError {
			lastError = error
			resultRecord = nil
			resultUserId = nil
		}
	}

}

open class Liferay70DDLFormLoadConnector: DDLFormLoadLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let service = LRDDMStructureService_v7(session: session)

		do {
			let structureDataDictionary = try service?.getStructureWithStructureId(structureId)

			if let json = structureDataDictionary?["definition"]! as? String {
				if let userIdValue = (structureDataDictionary?["userId"] as AnyObject).int64Value {
					resultUserId = userIdValue
				}

				resultRecord = DDLRecord(
					json: json,
					locale: Locale(identifier: NSLocale.currentLocaleString))
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not load ddl form with this structureId.")
				resultRecord = nil
				resultUserId = nil
			}
		}
		catch let error as NSError {
			lastError = error
			resultRecord = nil
			resultUserId = nil
		}
	}

}
