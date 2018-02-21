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

open class AssetListPageLoadInteractor: BaseListPageLoadInteractor {

	open var customEntryQuery: [String:AnyObject]?

	fileprivate let groupId: Int64

	fileprivate let classNameId: Int64

	fileprivate let portletItemName: String?

	init(screenlet: BaseListScreenlet,
			page: Int,
			computeRowCount: Bool,
			groupId: Int64,
			classNameId: Int64,
			portletItemName: String?) {

		self.groupId = groupId
		self.classNameId = classNameId
		self.portletItemName = portletItemName

		super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
	}

	// MARK: BaseListPageLoadInteractor

	override open func createListPageConnector() -> PaginationLiferayConnector {
		let pager = (self.screenlet as! BaseListScreenlet).firstRowForPage

		let connector = LiferayServerContext.connectorFactory.createAssetListPageConnector(
			startRow: pager(self.page),
			endRow: pager(self.page + 1),
			computeRowCount: self.computeRowCount)

		connector.groupId = (self.groupId != 0)
			? self.groupId : LiferayServerContext.groupId
		connector.classNameId = self.classNameId

		connector.portletItemName = self.portletItemName
		connector.customEntryQuery = self.customEntryQuery

		return connector
	}

	override open func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
		guard let className = serverResult["className"] as? String else {
			return Asset(attributes: serverResult)
		}

		if WebContent.isWebContentClassName(className) {
			return WebContent(attributes: serverResult)
		}
		else {
			return Asset(attributes: serverResult)
		}
	}

	override open func cacheKey(_ c: PaginationLiferayConnector) -> String {
		return "\((groupId != 0) ? groupId : LiferayServerContext.groupId)-\(classNameId)"
	}

}
