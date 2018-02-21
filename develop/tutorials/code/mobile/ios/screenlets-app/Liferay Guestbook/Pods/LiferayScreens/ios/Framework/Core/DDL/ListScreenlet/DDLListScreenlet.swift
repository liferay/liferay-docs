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

/// The DDLListScreenletDelegate protocol defines some methods that you use to manage the
/// DDLListScreenlet events. All of them are optional.
@objc(DDLListScreenletDelegate)
public protocol DDLListScreenletDelegate: BaseScreenletDelegate {

	/// Called when a page of contents is received. Note that this method may be called 
	/// more than once; one call for each page received.
	///
	/// - Parameters:
	///   - screenlet: DDL list screenlet instance.
	///   - records: List of records.
	@objc optional func screenlet(_ screenlet: DDLListScreenlet,
			onDDLListResponseRecords records: [DDLRecord])

	/// Called when an error occurs in the process. 
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: DDL list screenlet instance.
	///   - error: Error in DDL list.
	@objc optional func screenlet(_ screenlet: DDLListScreenlet,
			onDDLListError error: NSError)

	/// Called when an item in the list is selected.
	///
	/// - Parameters:
	///   - screenlet: DDL list screenlet instance.
	///   - record: DDL record selected.
	@objc optional func screenlet(_ screenlet: DDLListScreenlet,
			onDDLSelectedRecord record: DDLRecord)

}

/// The DDL List Screenlet enables the following features:
/// * Shows a scrollable collection of DDL records.
/// * Implements fluent pagination with configurable page size.
/// * Allows filtering of records by creator.
/// * Supports i18n in record values.
@objc(DDLListScreenlet)
open class DDLListScreenlet: BaseListScreenlet {

	// MARK: Inspectables

	/// The ID of the user to filter records on. Records aren’t filtered if the userId is 0. 
	/// The default value is 0.
	@IBInspectable open var userId: Int64 = 0

	/// The ID of the DDL being called.
	@IBInspectable open var recordSetId: Int64 = 0

	/// The comma-separated names of the DDL fields to show.
	@IBInspectable open var labelFields: String? {
		didSet {
			(screenletView as? DDLListViewModel)?.labelFields = parseFields(labelFields)
		}
	}

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var ddlListDelegate: DDLListScreenletDelegate? {
		return delegate as? DDLListScreenletDelegate
	}

	open var viewModel: DDLListViewModel {
		return screenletView as! DDLListViewModel
	}

	// MARK: BaseListScreenlet

	override open func onCreated() {
		super.onCreated()

		viewModel.labelFields = parseFields(self.labelFields)
	}

	override open func createPageLoadInteractor(
			page: Int,
			computeRowCount: Bool)
			-> BaseListPageLoadInteractor {

		let interactor = DDLListPageLoadInteractor(
				screenlet: self,
				page: page,
				computeRowCount: computeRowCount,
				userId: self.userId,
				recordSetId: self.recordSetId)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		return interactor
	}

	override open func onLoadPageError(page: Int, error: NSError) {
		super.onLoadPageError(page: page, error: error)

		ddlListDelegate?.screenlet?(self, onDDLListError: error)
	}

	override open func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
		super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

		ddlListDelegate?.screenlet?(self,
				onDDLListResponseRecords: rows as! [DDLRecord])
	}

	override open func onSelectedRow(_ row: AnyObject) {
		ddlListDelegate?.screenlet?(self,
				onDDLSelectedRecord: row as! DDLRecord)
	}

	// MARK: Private methods

	fileprivate func parseFields(_ fields: String?) -> [String] {
		var result: [String] = []

		if let fieldsValue = fields {
			let dirtyFields = (fieldsValue as NSString).components(separatedBy: ",")
			result = dirtyFields.map {
				$0.trimmingCharacters(in: .whitespaces)
			}
			result = result.filter { return $0 != "" }
		}

		return result
	}

}
