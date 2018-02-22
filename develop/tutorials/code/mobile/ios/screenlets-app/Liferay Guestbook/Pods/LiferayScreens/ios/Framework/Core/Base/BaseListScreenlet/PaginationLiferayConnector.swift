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

open class PaginationLiferayConnector: ServerConnector {

	open let startRow: Int
	open let endRow: Int
	open let computeRowCount: Bool

	open var obcClassName: String?

	open var resultPageContent: [[String:AnyObject]]?
	open var resultRowCount: Int?

	// MARK: Initializers

	public init(startRow: Int, endRow: Int, computeRowCount: Bool) {
		self.startRow = startRow
		self.endRow = endRow
		self.computeRowCount = computeRowCount

		super.init()
	}

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let batchSession = LRBatchSession(session: session)!

		resultPageContent = nil
		resultRowCount = nil
		lastError = nil

		let obc = obcClassName.flatMap {
			LRJSONObjectWrapper(className: $0, jsonObject: [:])
		}

		doAddPageRowsServiceCall(session: batchSession, startRow: startRow, endRow: endRow, obc: obc)

		if batchSession.commands.count < 1 {
			lastError = NSError.errorWithCause(.abortedDueToPreconditions, userInfo: nil)
			return
		}

		if computeRowCount {
			doAddRowCountServiceCall(session: batchSession)
		}

		do {
			let responses = try batchSession.invoke()

			if let entriesResponse = responses[0] as? [[String:AnyObject]] {
				let serverPageContent = entriesResponse
				var serverRowCount: Int?

				if responses.count > 1 {
					if let countResponse = responses[1] as? Int {
						serverRowCount = countResponse
					}
				}

				resultPageContent = serverPageContent
				resultRowCount = serverRowCount
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "No entries found.")
			}
		}
		catch let error as NSError {
			lastError = error
		}
	}

	// MARK: Public methods

	open func doAddPageRowsServiceCall(session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

		fatalError("doGetPageRowsConnector must be overriden")
	}

	open func doAddRowCountServiceCall(session: LRBatchSession) {
		fatalError("doGetRowCountConnector must be overriden")
	}

}
