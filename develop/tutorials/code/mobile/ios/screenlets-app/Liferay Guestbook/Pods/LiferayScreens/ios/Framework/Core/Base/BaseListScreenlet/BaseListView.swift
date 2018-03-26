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

@objc(BaseListView)
open class BaseListView: BaseScreenletView {

	open static let DefaultSection = ""

	open var rowCount: Int {
		return _rowCount
	}

	open var rows: [String : [AnyObject?]] {
		return _rows
	}

	open var sections: [String] {
		return _sections
	}

	open var onSelectedRowClosure: ((AnyObject) -> Void)?
	open var fetchPageForRow: ((Int) -> Void)?

	fileprivate var _rows = [String: [AnyObject?]]()
	fileprivate var _sections = [String]()
	fileprivate var _rowCount = 0

	//True when a request for more rows is being processed
	open var loadingRows = false

	//True when there are more rows in the server
	open var moreRows = true

	// MARK: Public methods

	open func setRows(_ allRows: [String : [AnyObject?]], newRows: [String : [AnyObject]], rowCount: Int,
	                    sections: [String]) {

		let streamMode = (screenlet as! BaseListScreenlet).streamMode

		loadingRows = false

		moreRows = hasMoreRows(newRows)

		_rowCount = rowCount

		let oldRows = _rows
		_rows = allRows

		_sections = sections

		if streamMode {
			onAddedRows(oldRows)
		}
		else {
			onChangedRows(oldRows)
		}
	}

	open dynamic func clearRows() {
		let oldRows = _rows
		_rows = [String: [AnyObject?]]()
		_rows[BaseListView.DefaultSection] = [AnyObject?]()
		_rowCount = 0
		_sections = [BaseListView.DefaultSection]

		onClearRows(oldRows)
	}

	open func onChangedRows(_ oldRows: [String : [AnyObject?]]) {

	}

	open func onAddedRows(_ oldRows: [String : [AnyObject?]]) {

	}

	open func onClearRows(_ oldRows: [String : [AnyObject?]]) {

	}

	open dynamic func deleteRow(_ section: String, row: Int) {
		_ = _rows[section]?.remove(at: row)
	}

	open dynamic func addRow(_ section: String, element: AnyObject) {
		_rows[section]?.append(element)
	}

	open dynamic func updateRow(_ section: String, row: Int, element: AnyObject) {
		_rows[section]?[row] = element
	}

	open func rowsForSectionIndex(_ index: Int) -> [AnyObject?] {
		let key = sections[index]

		return rows[key]!
	}

	// MARK: Internal methods

	internal func hasMoreRows(_ newRows: [String : [AnyObject]]) -> Bool {
		if newRows.isEmpty {
			return false
		}

		let newRowsCount = newRows.values.reduce(0) { $0 + $1.count }
		let isFirstPage = self.isFirstPage()

		//If we are receiving less elements than the page size there are no more rows in the server
		if isFirstPage && newRowsCount < (screenlet as? BaseListScreenlet)?.firstPageSize {
			return false
		}
		else if !isFirstPage && newRowsCount < (screenlet as? BaseListScreenlet)?.pageSize {
			return false
		}

		return true
	}

	internal func isFirstPage() -> Bool {
		let hasAnyRows = rows.values.stoppableReduce(0) { (value, element, hasToStop) in
			if value > 0 {
				hasToStop = true
			}

			return value + element.count
			}

		return (hasAnyRows == 0)
	}
}
