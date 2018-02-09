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
import LRMobileSDK

open class WebContentListPageLiferayConnector: PaginationLiferayConnector {

	open let groupId: Int64
	open let folderId: Int64

	// MARK: Initializers

	init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64, folderId: Int64) {
		self.groupId = groupId
		self.folderId = folderId

		super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
	}

}

open class Liferay62WebContentListPageConnector: WebContentListPageLiferayConnector {

	// MARK: PaginationLiferayConnector

	override open func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

		do {
			let service = LRJournalArticleService_v62(session: session)

			try service?.getArticlesWithGroupId(groupId,
				folderId: folderId,
				start: Int32(startRow),
				end: Int32(endRow),
				obc: obc)
		}
		catch _ as NSError {
		}
	}

	override open func doAddRowCountServiceCall(session: LRBatchSession) {
		do {
			let service = LRJournalArticleService_v62(session: session)
			try service?.getArticlesCount(withGroupId: groupId, folderId: folderId)
		}
		catch _ as NSError {
		}
	}

}

open class Liferay70WebContentListPageConnector: WebContentListPageLiferayConnector {

	// MARK: PaginationLiferayConnector

	override open func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

		do {
			let service = LRJournalArticleService_v7(session: session)
			try service?.getArticlesWithGroupId(groupId,
			                                   folderId: folderId,
			                                   start: Int32(startRow),
			                                   end: Int32(endRow),
			                                   obc: obc)
		}
		catch _ as NSError {
		}
	}

	override open func doAddRowCountServiceCall(session: LRBatchSession) {
		do {
			let service = LRJournalArticleService_v7(session: session)
			try service?.getArticlesCount(withGroupId: groupId, folderId: folderId)
		}
		catch _ as NSError {
		}
	}

}
