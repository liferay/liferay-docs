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

public protocol LoadAssetConnector {

	var resultAsset: Asset? { get set }
}

open class LoadAssetInteractor: ServerReadConnectorInteractor {

	open let assetEntryId: Int64?

	open let className: String?

	open let classPK: Int64?

	open let portletItemName: String?

	open var asset: Asset?

	// MARK: Initializers

	public convenience init(screenlet: BaseScreenlet, assetEntryId: Int64) {
		self.init(screenlet: screenlet,
				assetEntryId: assetEntryId,
				className: nil,
				classPK: nil,
				portletItemName: nil)
	}

	public convenience init(screenlet: BaseScreenlet, className: String, classPK: Int64) {
		self.init(screenlet: screenlet,
		          assetEntryId: nil,
		          className: className,
		          classPK: classPK,
		          portletItemName: nil)
	}

	public convenience init(screenlet: BaseScreenlet, portletItemName: String) {
		self.init(screenlet: screenlet,
		          assetEntryId: nil,
		          className: nil,
		          classPK: nil,
		          portletItemName: portletItemName)
	}

	private init(screenlet: BaseScreenlet, assetEntryId: Int64?, className: String?,
	             classPK: Int64?, portletItemName: String?) {
		self.assetEntryId = assetEntryId
		self.className = className
		self.classPK = classPK
		self.portletItemName = portletItemName

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> ServerConnector? {
		if let assetEntryId = self.assetEntryId {
			return LiferayServerContext.connectorFactory.createAssetLoadByEntryIdConnector(assetEntryId)
		}
		else if let className = self.className, let classPK = self.classPK {
			return LiferayServerContext.connectorFactory.createAssetLoadByClassPKConnector(className, classPK: classPK)
		}
		else if let portletItemName = self.portletItemName {
			return LiferayServerContext.connectorFactory
					.createAssetLoadByPortletItemNameConnector(portletItemName: portletItemName)
		}

		return nil
	}

	override open func completedConnector(_ c: ServerConnector) {
		asset = (c as? LoadAssetConnector)?.resultAsset
	}

	// MARK: Cache methods

	override open func readFromCache(_ c: ServerConnector, result: @escaping (AnyObject?) -> Void) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			result(nil)
			return
		}
		guard var loadCon = c as? LoadAssetConnector else {
			result(nil)
			return
		}

		cacheManager.getAny(
				collection: "AssetsScreenlet",
				key: self.assetCacheKey) {
			loadCon.resultAsset = $0 as? Asset
			result(loadCon.resultAsset)
		}
	}

	override open func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}
		guard let loadCon = c as? LoadAssetConnector else {
			return
		}
		guard let asset = loadCon.resultAsset else {
			return
		}

		cacheManager.setClean(
			collection: "AssetsScreenlet",
			key: self.assetCacheKey,
			value: asset,
			attributes: [
				"entryId": NSNumber(value: assetEntryId ?? 0),
				"className": className as AnyObject? ?? "" as AnyObject,
				"classPK": NSNumber(value: classPK ?? 0),
				"portletItemName": portletItemName as AnyObject? ?? ""  as AnyObject
			])
	}

	fileprivate var assetCacheKey: String {
		if let assetEntryId = self.assetEntryId {
			return "load-asset-entryId-\(assetEntryId)"
		}
		else if let className = self.className, let classPK = self.classPK {
			return "load-asset-cn-\(className)-cpk-\(classPK)"
		}
		else {
			return "load-asset-pin-\(portletItemName)"
		}

		fatalError("Need either assetEntryId, className+classPK or portletItemName")
	}

}
