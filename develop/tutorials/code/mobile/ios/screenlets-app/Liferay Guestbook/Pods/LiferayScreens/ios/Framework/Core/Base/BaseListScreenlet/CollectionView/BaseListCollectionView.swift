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

@objc(BaseListCollectionView)
open class BaseListCollectionView: BaseListView, UICollectionViewDataSource, UICollectionViewDelegate {

	let defaultCellId = "liferay-screns-loadingMoreCellId"

	// MARK: Outlets

	@IBOutlet open var collectionView: UICollectionView?

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

	override open func onCreated() {
		super.onCreated()

		collectionView?.delegate = self
		collectionView?.dataSource = self

		doConfigureCollectionView(collectionView!)
		doRegisterCellNibs()
		doRegisterLoadMoreCell()
	}

	override open func onShow() {
		super.onShow()

		// Force layout
		layoutIfNeeded()
		collectionView?.collectionViewLayout = doCreateLayout()
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

		let visibleRows = collectionView!.indexPathsForVisibleItems
		updateVisibleRows(visibleRows)
	}

	override open func onAddedRows(_ oldRows: [String : [AnyObject?]]) {
		collectionView?.reloadData()
	}

	open override func onClearRows(_ oldRows: [String : [AnyObject?]]) {
		clearAllRows(oldRows)
	}

	override open func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
		if let currentRefreshControl = refreshControlView {
			dispatch_delayed(0.3) {
				currentRefreshControl.endRefreshing()
			}
		}
	}

	// MARK: UICollectionViewDataSource

	open func collectionView(
			_ collectionView: UICollectionView,
			numberOfItemsInSection section: Int)
			-> Int {

		let streamMode = (screenlet as! BaseListScreenlet).streamMode

		if streamMode && moreRows {
			return rowsForSectionIndex(section).count + 1
		}

		return rowsForSectionIndex(section).count
	}

	open func numberOfSections(in collectionView: UICollectionView) -> Int {
		return sections.count
	}

	open func collectionView(_ collectionView: UICollectionView,
			cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

		let rowsForSection = rowsForSectionIndex(indexPath.section)

		let isProgressViewCell = indexPath.row == rowsForSectionIndex(indexPath.section).count

		if isProgressViewCell {
			let cell = collectionView.dequeueReusableCell(
				withReuseIdentifier: doGetLoadMoreCellId(),
				for: indexPath)

			doFillLoadMoreCell(cell)

			return cell
		}

		let object: AnyObject? = rowsForSection[indexPath.row]
		let cell = doDequeueReusableCell(indexPath, object: object)

		if let object = object {
			doFillLoadedCell(indexPath: indexPath, cell: cell, object: object)
		}
		else {
			doFillInProgressCell(indexPath: indexPath, cell: cell)

			let streamMode = (screenlet as! BaseListScreenlet).streamMode

			if !streamMode {
				fetchPageForRow?(indexPath.row)
			}
		}

		return cell
	}

	open func collectionView(
			_ collectionView: UICollectionView,
			didSelectItemAt indexPath: IndexPath) {

		let rowsForSection = rowsForSectionIndex(indexPath.section)

		if let row: AnyObject = rowsForSection[indexPath.row] {
			onSelectedRowClosure?(row)
		}

		collectionView.deselectItem(at: indexPath, animated: false)
	}

	open func collectionView(
			_ collectionView: UICollectionView,
			willDisplay cell: UICollectionViewCell,
			forItemAt indexPath: IndexPath) {

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

	open func collectionView(
			_ collectionView: UICollectionView,
			layout collectionViewLayout: UICollectionViewLayout,
			sizeForItemAtIndexPath indexPath: IndexPath) -> CGSize {

		// This method is only called when layout is a instance of UICollectionViewFlowLayout
		let layout = collectionViewLayout as! UICollectionViewFlowLayout

		if indexPath.row == rowsForSectionIndex(indexPath.section).count {
			return doGetLoadMoreViewSize(layout)
		}

		return layout.itemSize
	}

	// MARK: Public methods

	open dynamic func doConfigureCollectionView(_ collectionView: UICollectionView) {
		collectionView.backgroundColor = .white
	}

	open dynamic func doCreateLayout() -> UICollectionViewLayout {
		let layout = UICollectionViewFlowLayout()
		layout.itemSize = CGSize(width: 100, height: 100)
		layout.sectionInset = UIEdgeInsets.zero
		return layout
	}

	open dynamic func doDequeueReusableCell(
			_ indexPath: IndexPath,
			object: AnyObject?) -> UICollectionViewCell {

		let cellId = doGetCellId(indexPath: indexPath, object: object)

		return collectionView!.dequeueReusableCell(withReuseIdentifier: cellId, for: indexPath)
	}

	open dynamic func doFillLoadedCell(
			indexPath: IndexPath,
			cell: UICollectionViewCell, object: AnyObject) {

	}

	open dynamic func doFillInProgressCell(indexPath: IndexPath, cell: UICollectionViewCell) {
	}

	open dynamic func doRegisterCellNibs() {
	}

	open dynamic func doRegisterLoadMoreCell() {
		collectionView?.register(
				UICollectionViewCell.self,
				forCellWithReuseIdentifier: defaultCellId)
	}

	open dynamic func doGetCellId(indexPath: IndexPath, object: AnyObject?) -> String {
		return defaultCellId//"defaultCellId"
	}

	open dynamic func doGetLoadMoreCellId() -> String {
		return defaultCellId
	}

	open dynamic func doCreateCell(_ cellId: String) -> UICollectionViewCell {
		return UICollectionViewCell()
	}

	open dynamic func doFillLoadMoreCell(_ cell: UICollectionViewCell) {
		if cell.contentView.viewWithTag(99) == nil {
			let loadingMoreView = UIActivityIndicatorView(activityIndicatorStyle: .gray)
			loadingMoreView.center = CGPoint(
				x: cell.contentView.bounds.width/2,
				y: cell.contentView.bounds.height/2)
			loadingMoreView.tag = 99

			loadingMoreView.startAnimating()

			cell.contentView.addSubview(loadingMoreView)
		}
	}

	open dynamic func doGetLoadMoreViewSize(_ layout: UICollectionViewFlowLayout) -> CGSize {
		if layout.scrollDirection == .vertical {
			return CGSize(width: layout.itemSize.width, height: 30)
		}

		return CGSize(width: 30, height: layout.itemSize.height)
	}

	open dynamic func updateRefreshControl() {
		if refreshClosure != nil {
			if refreshControlView == nil {
				refreshControlView = UIRefreshControl()
				collectionView?.addSubview(refreshControlView!)
				collectionView?.alwaysBounceVertical = true
				refreshControlView!.addTarget(
						self,
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

	// MARK: Internal methods

	internal func refreshControlBeginRefresh(_ sender: AnyObject?) {
		dispatch_delayed(0.3) {
			if !self.loadingRows {
				self.moreRows = true
				_ = self.refreshClosure?()
			}
			else {
				self.refreshControlView?.endRefreshing()
			}
		}
	}

	internal func insertFreshRows() {
		collectionView?.reloadData()
	}

	internal func insertRows(from: Int, to: Int, section: Int) {
		let indexPaths = (from..<to).map {
			IndexPath(row: $0, section: section)
		}
		collectionView!.insertItems(at: indexPaths)
	}

	internal func clearAllRows(_ currentRows: [String : [AnyObject?]]) {
		collectionView?.reloadData()
	}

	internal func deleteRows(from: Int, to: Int, section: Int) {
		let indexPaths = (from..<to).map {
			IndexPath(row: $0, section: section)
		}
		collectionView?.deleteItems(at: indexPaths)
	}

	internal func updateVisibleRows(_ visibleRows: [IndexPath]) {
		if visibleRows.isEmpty {
			collectionView!.reloadData()
		}
		else {
			collectionView!.reloadItems(at: visibleRows)
		}
	}

	internal func turnStreamModeOn() {
		moreRows = true
		(screenlet as? BaseListScreenlet)?.streamMode = true
	}
}
