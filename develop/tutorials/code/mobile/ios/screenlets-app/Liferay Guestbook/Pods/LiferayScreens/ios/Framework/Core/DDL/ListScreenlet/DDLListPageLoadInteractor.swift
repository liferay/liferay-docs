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

open class DDLListPageLoadInteractor: BaseListPageLoadInteractor {

	open let userId: Int64

	open let recordSetId: Int64

	public init(
			screenlet: BaseListScreenlet,
			page: Int,
			computeRowCount: Bool,
			userId: Int64,
			recordSetId: Int64) {

		self.userId = userId
		self.recordSetId = recordSetId

		super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
	}

	// MARK: BaseListPageLoadInteractor

	override open func createListPageConnector() -> PaginationLiferayConnector {
		let viewModel = (self.screenlet as! DDLListScreenlet).screenletView as! DDLListViewModel
		let pager = (self.screenlet as! BaseListScreenlet).firstRowForPage

		let connector = LiferayServerContext.connectorFactory.createDDLListPageConnector(
			viewModel: viewModel,
			startRow: pager(self.page),
			endRow: pager(self.page + 1),
			computeRowCount: self.computeRowCount)

		connector.userId = (self.userId != 0) ? self.userId : nil
		connector.recordSetId = self.recordSetId

		return connector
	}

	override open func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
		return DDLRecord(dataAndAttributes: serverResult)
	}

	override open func cacheKey(_ c: PaginationLiferayConnector) -> String {
		return "\(recordSetId)"
	}

}
