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

/// The WebContentListScreenletDelegate protocol defines some methods that you use to manage the
/// WebContentListScreenlet events. All of them are optional.
@objc(WebContentListScreenletDelegate)
public protocol WebContentListScreenletDelegate: BaseScreenletDelegate {

	/// Called when a page of contents is received.
	/// Note that this method may be called more than once: one call for each page received.
	///
	/// - Parameters:
	///   - screenlet: Web content list screenlet instance.
	///   - contents: List of web contents.
	@objc optional func screenlet(_ screenlet: WebContentListScreenlet,
			onWebContentListResponse contents: [WebContent])

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Web content list screenlet instance.
	///   - error: Error while retrieving web content list.
	@objc optional func screenlet(_ screenlet: WebContentListScreenlet,
			onWebContentListError error: NSError)

	/// Called when an item in the list is selected.
	///
	/// - Parameters:
	///   - screenlet: Web content list screenlet instance.
	///   - content: Selected web content.
	@objc optional func screenlet(_ screenlet: WebContentListScreenlet,
			onWebContentSelected content: WebContent)

}

/// Web Content List Screenlet can show lists of [web content]
/// (https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/creating-web-content) from a Liferay instance. It can 
/// show both basic and [structured web content]
/// (https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/designing-uniform-content). The Screenlet also 
/// implements fluent pagination with configurable page size, and supports i18n in asset values.
@objc(WebContentListScreenlet)
open class WebContentListScreenlet: BaseListScreenlet {

	// MARK: Inspectables

	/// The ID of the site (group) where the web content exists. If set to 0, the groupId 
	/// specified in LiferayServerContext is used. The default value is 0.
	@IBInspectable open var groupId: Int64 = 0

	/// The ID of the web content folder. If set to 0, the root folder is used.
	/// The default value is 0.
	@IBInspectable open var folderId: Int64 = 0

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var webContentListDelegate: WebContentListScreenletDelegate? {
		return delegate as? WebContentListScreenletDelegate
	}

	// MARK: BaseListScreenlet

	override open func createPageLoadInteractor(
			page: Int,
			computeRowCount: Bool)
			-> BaseListPageLoadInteractor {

		let interactor = WebContentListPageLoadInteractor(
			screenlet: self,
			page: page,
			computeRowCount: computeRowCount,
			groupId: self.groupId,
			folderId: self.folderId)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		return interactor
	}

	override open func onLoadPageError(page: Int, error: NSError) {
		super.onLoadPageError(page: page, error: error)

		webContentListDelegate?.screenlet?(self, onWebContentListError: error)
	}

	override open func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
		super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

		let webContentEntries = rows as! [WebContent]

		webContentListDelegate?.screenlet?(self, onWebContentListResponse: webContentEntries)
	}

	override open func onSelectedRow(_ row: AnyObject) {
		webContentListDelegate?.screenlet?(self, onWebContentSelected: row as! WebContent)
	}

}
