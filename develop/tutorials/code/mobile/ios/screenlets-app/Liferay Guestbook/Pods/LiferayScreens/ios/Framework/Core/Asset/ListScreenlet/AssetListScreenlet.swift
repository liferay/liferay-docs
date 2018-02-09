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

/// The AssetListScreenletDelegate protocol defines some methods that you use to manage the
/// AssetListScreenlet events. All of them are optional.
@objc(AssetListScreenletDelegate)
public protocol AssetListScreenletDelegate: BaseScreenletDelegate {

	/// Called when a page of assets is received. Note that this method may be called 
	/// more than once; one call for each page received.
	///
	/// - Parameters:
	///   - screenlet: Asset list screenlet instance.
	///   - assets: List of assets.
	@objc optional func screenlet(_ screenlet: AssetListScreenlet,
			onAssetListResponse assets: [Asset])

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Asset list screenlet instance.
	///   - error: Error loading asset list.
	@objc optional func screenlet(_ screenlet: AssetListScreenlet,
			onAssetListError error: NSError)

	/// Called when an item in the list is selected.
	///
	/// - Parameters:
	///   - screenlet: Asset list screenlet instance.
	///   - asset: Selected asset.
	@objc optional func screenlet(_ screenlet: AssetListScreenlet,
			onAssetSelected asset: Asset)

}

/// The Asset List Screenlet can be used to show lists of 
/// [assets](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/asset-framework) from a Liferay instance.
/// For example, you can use the Screenlet to show a scrollable collection of assets. 
/// It also implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
/// with configurable page size.
@objc(AssetListScreenlet)
open class AssetListScreenlet: BaseListScreenlet {

	// MARK: Inspectables

	/// The ID of the site (group) where the asset is stored. If set to 0, the groupId specified in
	/// LiferayServerContext is used. The default value is 0.
	@IBInspectable open var groupId: Int64 = 0

	/// The ID of the asset’s class name. Use values from the AssetClassNameId class or the Liferay 
	/// Instance’s classname_ database table.
	@IBInspectable open var classNameId: Int64 = 0

	/// The name of the [configuration template]
	/// (https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/configuration-templates) 
	/// you used in the Asset Publisher.
	@IBInspectable open var portletItemName: String?

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var assetListDelegate: AssetListScreenletDelegate? {
		return delegate as? AssetListScreenletDelegate
	}

	open var customEntryQuery: [String:AnyObject]?

	// MARK: BaseListScreenlet

	override open func createPageLoadInteractor(
			page: Int,
			computeRowCount: Bool)
			-> BaseListPageLoadInteractor {

		let interactor = AssetListPageLoadInteractor(
			screenlet: self,
			page: page,
			computeRowCount: computeRowCount,
			groupId: self.groupId,
			classNameId: self.classNameId,
			portletItemName: self.portletItemName)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst
		interactor.customEntryQuery = self.customEntryQuery

		return interactor
	}

	override open func onLoadPageError(page: Int, error: NSError) {
		super.onLoadPageError(page: page, error: error)

		assetListDelegate?.screenlet?(self, onAssetListError: error)
	}

	override open func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
		super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

		let assets = rows as! [Asset]

		assetListDelegate?.screenlet?(self, onAssetListResponse: assets)
	}

	override open func onSelectedRow(_ row: AnyObject) {
		assetListDelegate?.screenlet?(self, onAssetSelected: row as! Asset)
	}
}
