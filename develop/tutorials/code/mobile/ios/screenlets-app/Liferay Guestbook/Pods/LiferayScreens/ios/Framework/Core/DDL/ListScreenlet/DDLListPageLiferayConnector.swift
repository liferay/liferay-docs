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

open class DDLListPageLiferayConnector: PaginationLiferayConnector {

	open var userId: Int64?
	open var recordSetId: Int64?

	internal let viewModel: DDLListViewModel

	// MARK: Initializers

	public init(viewModel: DDLListViewModel, startRow: Int, endRow: Int, computeRowCount: Bool) {
		self.viewModel = viewModel

		super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if recordSetId == nil {
				return ValidationError("ddllist-screenlet", "undefined-recordset")
			}

			if viewModel.labelFields.isEmpty {
				return ValidationError("ddllist-screenlet", "undefined-fields")
			}
		}

		return error
	}

}

open class Liferay62DDLListPageConnector: DDLListPageLiferayConnector {

	// MARK: PaginationLiferayConnector

	override open func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

		let service = LRScreensddlrecordService_v62(session: session)

		do {
			if userId == nil {
				try service?.getDdlRecords(withDdlRecordSetId: recordSetId!,
					locale: NSLocale.currentLocaleString,
					start: Int32(startRow),
					end: Int32(endRow),
					obc: obc)
			}
			else {
				try service?.getDdlRecords(withDdlRecordSetId: recordSetId!,
					userId: userId!,
					locale: NSLocale.currentLocaleString,
					start: Int32(startRow),
					end: Int32(endRow),
					obc: obc)
			}
		}
		catch _ as NSError {
		}
	}

	override open func doAddRowCountServiceCall(session: LRBatchSession) {
		let service = LRScreensddlrecordService_v62(session: session)

		do {
			if userId == nil {
				try service?.getDdlRecordsCount(withDdlRecordSetId: recordSetId!)
			}
			else {
				try service?.getDdlRecordsCount(withDdlRecordSetId: recordSetId!,
					userId: userId!)
			}
		}
		catch _ as NSError {
		}
	}

}

open class Liferay70DDLListPageConnector: DDLListPageLiferayConnector {

	// MARK: PaginationLiferayConnector

	override open func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

		let service = LRScreensddlrecordService_v70(session: session)

		do {
			if userId == nil {
				try service?.getDdlRecords(withDdlRecordSetId: recordSetId!,
					locale: NSLocale.currentLocaleString,
					start: Int32(startRow),
					end: Int32(endRow),
					obc: obc)
			}
			else {
				try service?.getDdlRecords(withDdlRecordSetId: recordSetId!,
					userId: userId!,
					locale: NSLocale.currentLocaleString,
					start: Int32(startRow),
					end: Int32(endRow),
					obc: obc)
			}
		}
		catch _ as NSError {
		}
	}

	override open func doAddRowCountServiceCall(session: LRBatchSession) {
		let service = LRScreensddlrecordService_v70(session: session)

		do {
			if userId == nil {
				try service?.getDdlRecordsCount(withDdlRecordSetId: recordSetId!)
			}
			else {
				try service?.getDdlRecordsCount(withDdlRecordSetId: recordSetId!,
					userId: userId!)
			}
		}
		catch _ as NSError {
		}
	}

}
