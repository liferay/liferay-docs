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

open class CommentListPageLiferayConnector: PaginationLiferayConnector {

	open let className: String
	open let classPK: Int64

	// MARK: Initializers

	public init(
			className: String,
			classPK: Int64,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) {
		self.className = className
		self.classPK = classPK

		super.init(startRow: startRow, endRow: endRow, computeRowCount: true)
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if classPK <= 0 {
				return ValidationError("comment-list-screenlet", "undefined-classPK")
			}

			if className.isEmpty {
				return ValidationError("comment-list-screenlet", "undefined-className")
			}
		}

		return error
	}
}

open class Liferay70CommentListPageConnector: CommentListPageLiferayConnector {

	// MARK: PaginationLiferayConnector

	open override func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {
		let service = LRScreenscommentService_v70(session: session)

		do {
			try service?.getCommentsWithClassName(className,
					classPK: classPK,
					start: Int32(startRow),
					end: Int32(endRow))
		}
		catch _ as NSError {
		}
	}

	override open func doAddRowCountServiceCall(session: LRBatchSession) {
		let service = LRScreenscommentService_v70(session: session)

		do {
			try service?.getCommentsCount(withClassName: className, classPK: classPK)
		}
		catch _ as NSError {
		}
	}
}
