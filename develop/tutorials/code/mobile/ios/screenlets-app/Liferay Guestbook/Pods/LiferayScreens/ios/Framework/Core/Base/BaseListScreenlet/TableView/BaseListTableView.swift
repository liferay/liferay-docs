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

@objc(BaseListTableView)
open class BaseListTableView: BaseListView, UITableViewDataSource, UITableViewDelegate {

	@IBOutlet open var tableView: UITableView?

	internal var refreshControlView: UIRefreshControl?

	internal var refreshClosure: (() -> Bool)? {
		didSet {
			updateRefreshControl()
		}
	}

	override open var progressMessages: [String:ProgressMessages] {
		return [
			BaseListScreenlet.LoadInitialPageAction: [
				.working: LocalizedString("core", key: "base-list-loading-message", obj: self),
				.failure: LocalizedString("core", key: "base-list-loading-error", obj: self)
			]
		]
	}

	// MARK: BaseListView

	open override func onCreated() {
		super.onCreated()

		tableView?.delegate = self
		tableView?.dataSource = self

		doRegisterCellNibs()
	}

	override open func onChangedRows(_ oldRows: [String : [AnyObject?]]) {
		super.onChangedRows(oldRows)

		if oldRows[BaseListView.DefaultSection]!.isEmpty {
			insertFreshRows()
			return
		}

		let moreRowsThanExpected = (rows[BaseListView.DefaultSection]!.count >
					oldRows[BaseListView.DefaultSection]!.count)

		let lessRowsThanExpected = (rows[BaseListView.DefaultSection]!.count <
					oldRows[BaseListView.DefaultSection]!.count)

		if  moreRowsThanExpected {
			turnStreamModeOn()
			onAddedRows(oldRows)
		}
		else if lessRowsThanExpected {
			//Only executed in fluent mode so there is only one section
			deleteRows(from: rows[BaseListView.DefaultSection]!.count,
						to: oldRows[BaseListView.DefaultSection]!.count, section: 0)
		}

		if let visibleRows = tableView!.indexPathsForVisibleRows {
			updateVisibleRows(visibleRows)
		}
		else {
			tableView!.reloadData()
		}
	}

	override open func onAddedRows(_ oldRows: [String : [AnyObject?]]) {
		if moreRows {
			showProgressFooter()
		}
		else {
			hideProgressFooter()
		}

		tableView?.reloadData()
	}

	override open func onClearRows(_ oldRows: [String : [AnyObject?]]) {
		clearAllRows(oldRows)
	}

	override open func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
		if let currentRefreshControl = refreshControlView {
			dispatch_delayed(0.3) {
				currentRefreshControl.endRefreshing()
			}
		}
	}

	// MARK: UITableViewDataSource

	open func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return rowsForSectionIndex(section).count
	}

	open func numberOfSections(in tableView: UITableView) -> Int {
		return sections.count
	}

	open func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
		return sections[section]
	}

	open func tableView(_ tableView: UITableView,
	                      cellForRowAt
		indexPath: IndexPath)
		-> UITableViewCell {
			let rowsForSection = rowsForSectionIndex(indexPath.section)

			let object: AnyObject? = rowsForSection[indexPath.row]
			let cell = doDequeueReusableCell(row: indexPath.row, object: object)

			if let object = object {
				doFillLoadedCell(row: indexPath.row, cell: cell, object: object)
			}
			else {
				doFillInProgressCell(row: indexPath.row, cell: cell)

				let streamMode = (screenlet as! BaseListScreenlet).streamMode

				if !streamMode {
					fetchPageForRow?(indexPath.row)
				}
			}

			return cell
	}

	open func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

		let rowsForSection = rowsForSectionIndex(indexPath.section)

		if let row: AnyObject = rowsForSection[indexPath.row] {
			onSelectedRowClosure?(row)
		}

		tableView.deselectRow(at: indexPath, animated: false)
	}

	open func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell,
	                      forRowAt indexPath: IndexPath) {

		let streamMode = (screenlet as! BaseListScreenlet).streamMode

		if streamMode && !loadingRows && moreRows {

			let isLastSection = (indexPath.section == sections.count - 1)
			let isLastRowForSection = (indexPath.row == rowsForSectionIndex(indexPath.section).count - 1)

			if isLastSection && isLastRowForSection {
				loadingRows = true
				let lastRow = rows.values.reduce(0) { $0 + $1.count }
				fetchPageForRow?(lastRow + 1)
			}
		}
	}

	open func doDequeueReusableCell(row: Int, object: AnyObject?) -> UITableViewCell {
		let cellId = doGetCellId(row: row, object: object)

		guard let result = tableView!.dequeueReusableCell(withIdentifier: cellId) else {
			return doCreateCell(cellId)
		}

		return result
	}

	open func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
	}

	open func doFillInProgressCell(row: Int, cell: UITableViewCell) {
	}

	open func doRegisterCellNibs() {
	}

	open func doGetCellId(row: Int, object: AnyObject?) -> String {
		return "defaultCellId"
	}

	open func doCreateCell(_ cellId: String) -> UITableViewCell {
		return UITableViewCell(style: .default, reuseIdentifier: cellId)
	}

	open func createLoadingMoreView() -> UIView? {
		let progressView = UIView(frame: CGRect(x: 0, y: 0, width: frame.width, height: 30))

		let indicatorView = UIActivityIndicatorView(activityIndicatorStyle: .gray)
		indicatorView.center = CGPoint(x: frame.width/2, y: indicatorView.center.y)
		indicatorView.startAnimating()

		progressView.addSubview(indicatorView)

		return progressView
	}

	open override func changeEditable(_ editable: Bool) {
		tableView?.subviews.forEach { $0.isUserInteractionEnabled = editable }
	}

	// MARK: Internal methods

	internal func updateRefreshControl() {
		if refreshClosure != nil {
			if refreshControlView == nil {
				refreshControlView = UIRefreshControl()
				tableView?.addSubview(refreshControlView!)

				refreshControlView!.addTarget(self,
				                              action: #selector(BaseListTableView.refreshControlBeginRefresh(_:)),
				                              for: .valueChanged)
			}
		}
		else if let currentControl = refreshControlView {
			currentControl.endRefreshing()
			currentControl.removeFromSuperview()
			refreshControlView = nil
		}
	}

	internal func refreshControlBeginRefresh(_ sender: AnyObject?) {
		dispatch_delayed(0.3) {
			self.moreRows = true
			self.hideProgressFooter()
			_ = self.refreshClosure?()
		}
	}

	internal func insertFreshRows() {
		tableView?.reloadData()
	}

	internal func insertRows(from: Int, to: Int, section: Int) {
		let indexPaths = (from..<to).map {
			IndexPath(row: $0, section: section)
		}
		tableView!.beginUpdates()
		tableView!.insertRows(at: indexPaths, with:.top)
		tableView!.endUpdates()
	}

	internal func clearAllRows(_ currentRows: [String : [AnyObject?]]) {
		tableView?.reloadData()
	}

	internal func deleteRows(from: Int, to: Int, section: Int) {
		let indexPaths = (from..<to).map {
			IndexPath(row: $0, section: section)
		}
		tableView!.beginUpdates()
		tableView!.deleteRows(at: indexPaths, with: .fade)
		tableView!.endUpdates()
	}

	internal func updateVisibleRows(_ visibleRows: [IndexPath]) {
		if visibleRows.isEmpty {
			tableView!.reloadData()
		}
		else {
			tableView!.reloadRows(at: visibleRows, with:.none)
		}
	}

	internal func showProgressFooter() {
		tableView?.tableFooterView = createLoadingMoreView()
	}

	internal func hideProgressFooter() {

		tableView?.tableFooterView = nil
	}

	internal func turnStreamModeOn() {
		moreRows = true
		(screenlet as? BaseListScreenlet)?.streamMode = true
	}
}
