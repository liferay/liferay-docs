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

open class AssetListPageLiferayConnector: PaginationLiferayConnector {

	open var groupId: Int64?
	open var classNameId: Int64?
	open var portletItemName: String?
	open var customEntryQuery: [String:AnyObject]?

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if groupId == nil {
				return ValidationError("assetlist-screenlet", "undefined-group")
			}

			if classNameId ?? 0 == 0 && portletItemName ?? "" == "" {
				return ValidationError("assetlist-screenlet", "undefined-classname")
			}
		}

		return error
	}

	override open func doRun(session: LRSession) {
		if portletItemName != nil {
			if startRow == 0 {
				// since the service doesn't support pagination, we ask for
				// rows from the top to the endRow (whole single page)

				do {
					let entries = try doGetEntries(session, rowCount: Int32(endRow))
					if let serverPageContent = entries {
						resultPageContent = serverPageContent
						resultRowCount = serverPageContent.count
						lastError = nil
					}
					else {
						lastError = NSError.errorWithCause(.invalidServerResponse,
						                                   message: "No entries found.")
						resultPageContent = nil
					}
				}
				catch let error as NSError {
					lastError = error
					resultPageContent = nil
				}
			}
			else {
				// return empty content for pages different from the first one
				resultPageContent = []
				resultRowCount = 0
			}
		}
		else {
			super.doRun(session: session)
		}
	}

	// MARK: PaginationLiferayConnector

	override open func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

		var entryQuery = configureEntryQuery()

		entryQuery["start"] = startRow as AnyObject?
		entryQuery["end"] = endRow as AnyObject?

		let entryQueryWrapper = LRJSONObjectWrapper(jsonObject: entryQuery)

		doGetPageRows(session: session, entryQuery: entryQueryWrapper!, obc: obc)
	}

	override open func doAddRowCountServiceCall(session: LRBatchSession) {
		let entryQuery = configureEntryQuery()
		let entryQueryWrapper = LRJSONObjectWrapper(jsonObject: entryQuery)

		doGetRowCount(session: session, entryQuery: entryQueryWrapper!)
	}

	// MARK: Public methods

	open func doGetEntries(_ session: LRSession, rowCount: Int32) throws -> [[String:AnyObject]]? {
		return nil
	}

	open func doGetPageRows(session: LRBatchSession, entryQuery: LRJSONObjectWrapper, obc: LRJSONObjectWrapper?) {
	}

	open func doGetRowCount(session: LRBatchSession, entryQuery: LRJSONObjectWrapper) {
	}

	open func configureEntryQuery() -> [String:AnyObject] {
		var entryQuery = (customEntryQuery != nil)
			? customEntryQuery!
			: [String: AnyObject]()

		let defaultValues = [
			"classNameIds": NSNumber(value: classNameId! as Int64),
			"groupIds": NSNumber(value: groupId! as Int64),
			"visible": true
		]

		let finalValues = self.handleUserVisibleFlag(defaultValues)

		// swiftlint:disable for_where
		for (k, v) in finalValues {
			if entryQuery[k] == nil {
				entryQuery[k] = v
			}
		}
		// swiftlint:enable for_where

		return entryQuery
	}

	// MARK: Private methods

	/// AssetListScreenlet only list Asset with visible attribute set to true. But User objects have it by
	/// default in false. So this method update this attribute of entryQuery values to list
	/// all users.
	///
	/// - parameter values: initial entryQuery values.
	///
	/// - returns: final values for entryQuery.
	fileprivate func handleUserVisibleFlag(_ values: [String : AnyObject]) -> [String : AnyObject] {
		if classNameId == AssetClasses.getClassNameId(AssetClassNameKey_User) {
			var newValues = values

			newValues["visible"] = false as AnyObject?

			return newValues
		}
		return values
	}
}

open class Liferay62AssetListPageConnector: AssetListPageLiferayConnector {

	// MARK: AssetListPageLiferayConnector

	override open func doGetEntries(_ session: LRSession, rowCount: Int32) throws -> [[String:AnyObject]]? {
		let service = LRScreensassetentryService_v62(session: session)

		return try service?.getAssetEntries(withCompanyId: LiferayServerContext.companyId,
			groupId: groupId!,
			portletItemName: portletItemName!,
			locale: NSLocale.currentLocaleString,
			max: rowCount) as? [[String:AnyObject]]
	}

	override open func doGetPageRows(session: LRBatchSession, entryQuery: LRJSONObjectWrapper, obc: LRJSONObjectWrapper?) {
		do {
			//TODO add obc to screens plugin
			let service = LRScreensassetentryService_v62(session: session)
			try service?.getAssetEntries(withAssetEntryQuery: entryQuery,
				locale: NSLocale.currentLocaleString)
		}
		catch _ as NSError {
		}
	}

	override open func doGetRowCount(session: LRBatchSession, entryQuery: LRJSONObjectWrapper) {
		do {
			let service = LRAssetEntryService_v62(session: session)
			try service?.getEntriesCount(withEntryQuery: entryQuery)
		}
		catch _ as NSError {
		}
	}

}

open class Liferay70AssetListPageConnector: AssetListPageLiferayConnector {

	// MARK: AssetListPageLiferayConnector

	override open func doGetEntries(_ session: LRSession, rowCount: Int32) throws -> [[String:AnyObject]]? {
		let service = LRScreensassetentryService_v70(session: session)

		return try service?.getAssetEntries(withCompanyId: LiferayServerContext.companyId,
			groupId: groupId!,
			portletItemName: portletItemName!,
			locale: NSLocale.currentLocaleString,
			max: rowCount) as? [[String:AnyObject]]
	}

	override open func doGetPageRows(
			session: LRBatchSession,
			entryQuery: LRJSONObjectWrapper,
			obc: LRJSONObjectWrapper?) {

		do {
			//TODO add obc to plugin
			let service = LRScreensassetentryService_v70(session: session)
			try service?.getAssetEntries(withAssetEntryQuery: entryQuery,
				locale: NSLocale.currentLocaleString)
		}
		catch _ as NSError {
		}
	}

	override open func doGetRowCount(session: LRBatchSession, entryQuery: LRJSONObjectWrapper) {
		do {
			let service = LRAssetEntryService_v7(session: session)
			try service?.getEntriesCount(withEntryQuery: entryQuery)
		}
		catch _ as NSError {
		}
	}

}
