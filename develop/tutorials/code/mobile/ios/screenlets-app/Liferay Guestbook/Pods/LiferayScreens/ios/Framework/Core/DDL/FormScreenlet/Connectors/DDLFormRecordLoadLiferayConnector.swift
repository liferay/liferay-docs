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

open class DDLFormRecordLoadLiferayConnector: ServerConnector {

	open let recordId: Int64

	open var resultRecordData: [String:AnyObject]?
	open var resultRecordAttributes: [String:AnyObject]?
	open var resultRecordId: Int64?

	// MARK: Initializers

	public init(recordId: Int64) {
		self.recordId = recordId

		super.init()
	}

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		do {
			let recordDic = try getRecord(session,
					recordId: recordId,
					locale: NSLocale.currentLocaleString)

			if let resultData = recordDic["modelValues" as NSObject] as? [String:AnyObject],
					let resultAttributes = recordDic["modelAttributes" as NSObject] as? [String:AnyObject] {
				resultRecordData = resultData
				resultRecordId = recordId
				resultRecordAttributes = resultAttributes
			}
			else if let resultData = recordDic as? [String:AnyObject] {
				// backwards compat: plugins v1.1.0 and previous (pre LPS-58800)
				resultRecordData = resultData
				resultRecordId = recordId
				resultRecordAttributes = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not load record with this recordId.")
				resultRecordData = nil
				resultRecordId = nil
				resultRecordAttributes = nil
			}
		}
		catch let error as NSError {
			lastError = error
			resultRecordData = nil
			resultRecordAttributes = nil
			resultRecordId = nil
		}
	}

	// MARK: Public methods

	open func getRecord(_ session: LRSession, recordId: Int64, locale: String) throws -> [NSObject: AnyObject] {
		return [:]
	}

}

open class Liferay62DDLFormRecordLoadConnector: DDLFormRecordLoadLiferayConnector {

	// MARK: DDLFormRecordLoadLiferayConnector

	override open func getRecord(_ session: LRSession, recordId: Int64, locale: String) throws -> [NSObject: AnyObject] {
		let service = LRScreensddlrecordService_v62(session: session)

		return try service?.getDdlRecord(withDdlRecordId: recordId,
		locale: NSLocale.currentLocaleString) as! [NSObject : AnyObject]
	}

}

open class Liferay70DDLFormRecordLoadConnector: DDLFormRecordLoadLiferayConnector {

	// MARK: DDLFormRecordLoadLiferayConnector

	override open func getRecord(_ session: LRSession, recordId: Int64, locale: String) throws ->  [NSObject : AnyObject] {
		let service = LRScreensddlrecordService_v70(session: session)

		return try service!.getDdlRecord(withDdlRecordId: recordId,
			locale: NSLocale.currentLocaleString) as [NSObject: AnyObject]
	}

}
