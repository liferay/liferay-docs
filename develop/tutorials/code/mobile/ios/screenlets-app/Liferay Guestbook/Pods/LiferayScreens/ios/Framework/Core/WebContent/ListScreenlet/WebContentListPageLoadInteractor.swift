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

open class WebContentListPageLoadInteractor: BaseListPageLoadInteractor {

	fileprivate let groupId: Int64

	fileprivate let folderId: Int64

	// MARK: Initializers

	init(screenlet: BaseListScreenlet,
			page: Int,
			computeRowCount: Bool,
			groupId: Int64,
			folderId: Int64) {

		self.groupId = groupId
		self.folderId = folderId

		super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
	}

	// MARK: BaseListPageLoadInteractor

	open override func createListPageConnector() -> PaginationLiferayConnector {
		let pager = (self.screenlet as! BaseListScreenlet).firstRowForPage

		return LiferayServerContext.connectorFactory.createWebContentListPageConnector(
			groupId: (self.groupId != 0) ? self.groupId : LiferayServerContext.groupId,
			folderId: folderId,
			startRow: pager(self.page),
			endRow: pager(self.page + 1),
			computeRowCount: self.computeRowCount)
	}

	override open func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
		return WebContent(attributes: serverResult)
	}

	override open func cacheKey(_ c: PaginationLiferayConnector) -> String {
		return "\((groupId != 0) ? groupId : LiferayServerContext.groupId)-\(folderId)"
	}

}
