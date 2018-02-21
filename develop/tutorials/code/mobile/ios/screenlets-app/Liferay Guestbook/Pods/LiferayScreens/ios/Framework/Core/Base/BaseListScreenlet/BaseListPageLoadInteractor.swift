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

@objc(BaseListPageLoadInteractor)
open class BaseListPageLoadInteractor: ServerReadConnectorInteractor {

	open let page: Int

	open let computeRowCount: Bool

	open var obcClassName: String?

	open var resultAllPagesContent: [String : [AnyObject?]]?

	open var resultPageContent: [String : [AnyObject]]?

	open var resultRowCount: Int?

	open var sections: [String]?

	// MARK: Initializers

	public init(screenlet: BaseListScreenlet, page: Int, computeRowCount: Bool) {
		self.page = page
		self.computeRowCount = computeRowCount
		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> PaginationLiferayConnector {
		let connector = createListPageConnector()

		connector.obcClassName = self.obcClassName

		return connector
	}

	override open func completedConnector(_ c: ServerConnector) {
		if let pageCon = c as? PaginationLiferayConnector {
			processLoadPageResult(pageCon.resultPageContent ?? [], rowCount: pageCon.resultRowCount)
		}
	}

	// MARK: Public methods

	open func createListPageConnector() -> PaginationLiferayConnector {
		fatalError("createListPageConnector must be overriden")
	}

	// swiftlint:disable cyclomatic_complexity
	open func processLoadPageResult(_ serverRows: [[String : AnyObject]], rowCount: Int?) {
		let screenlet = self.screenlet as! BaseListScreenlet
		let baseListView = screenlet.screenletView as! BaseListView

		let actualRowCount = rowCount ?? baseListView.rows[BaseListView.DefaultSection]!.count

		let convertedRows = serverRows.map { self.convertResult($0) }

		var allRows = baseListView.rows
		var convertedRowsWithSection = [String: [AnyObject]]()
		var sections = baseListView.sections

		let isFirstPage = (page == 0)
		let isPageFull = isFirstPage
				? (convertedRows.count == screenlet.firstPageSize)
				: (convertedRows.count == screenlet.pageSize)

		var hasSections = (sections.count > 1) || (sections.count == 1 && sections.first != BaseListView.DefaultSection)

		// Fill sections
		if isFirstPage || hasSections {

			// Group rows loop
			for obj in convertedRows {
				let sectionName = sectionForRowObject(obj) ?? BaseListView.DefaultSection

				if convertedRowsWithSection.index(forKey: sectionName) == nil {
					convertedRowsWithSection[sectionName] = [AnyObject]()

					if !sections.contains(sectionName) {
						sections.append(sectionName)

						if sectionName != BaseListView.DefaultSection {
							hasSections = true
						}
					}
				}

				if hasSections && sectionName == BaseListView.DefaultSection {
					print("ERROR: you returned mixed empty and non-empty sections in sectionForRowObject()")
				}

				convertedRowsWithSection[sectionName]!.append(obj)
			}
		}
		else {
			// Without sections simply assign incoming rows to the default section
			convertedRowsWithSection[BaseListView.DefaultSection] = convertedRows
		}

		// StreamMode is only decided by the interactor in the first page load
		// otherwise this state could be changed for other interactors
		if isFirstPage && (hasSections || rowCount == nil) {
			screenlet.streamMode = true
		}

		//Fill rows
		if screenlet.streamMode {
			allRows = baseListView.rows

			for section in convertedRowsWithSection.keys {
				if allRows.index(forKey: section) == nil {
					allRows[section] = [AnyObject?]()
				}

				let rowsInSection = convertedRowsWithSection[section]!

				for row in rowsInSection {
					allRows[section]!.append(row)
				}
			}
		}
		else {
			//If we reach this point we will have only one section with key ""
			allRows[BaseListView.DefaultSection] = [AnyObject?](repeating: nil, count: actualRowCount)

			//Insert existing elements in the list
			for (index, row) in baseListView.rows[BaseListView.DefaultSection]!.enumerated() {
				allRows[BaseListView.DefaultSection]![index] = row
			}

			let offset = screenlet.firstRowForPage(page)
			var lastIndexInserted = 0

			//Insert new elements
			for (index, row) in convertedRows.enumerated() {
				if index + offset < actualRowCount {
					allRows[BaseListView.DefaultSection]![index + offset] = row
					lastIndexInserted = index + offset
				}
				else {
					allRows[BaseListView.DefaultSection]!.append(row)
				}
			}

			let lessItemsThanExpected = (lastIndexInserted + 1 < actualRowCount)
			let incompleteMiddlePage = (!isPageFull && !isFirstPage)

			let streamMode = screenlet.streamMode

			//Deleted elements since row count computation
			if lessItemsThanExpected && !streamMode && incompleteMiddlePage {
				for _ in lastIndexInserted+1..<actualRowCount {
					_ = allRows[BaseListView.DefaultSection]!.popLast()
				}
			}
		}

		self.resultRowCount = actualRowCount
		self.resultPageContent = convertedRowsWithSection
		self.resultAllPagesContent = allRows
		self.sections = sections
	}
	// swiftlint:enable cyclomatic_complexity

	open func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
		fatalError("convert(serverResult) must be overriden")
	}

	open func sectionForRowObject(_ object: AnyObject) -> String? {
		return nil
	}

	open func cacheKey(_ c: PaginationLiferayConnector) -> String {
		fatalError("cacheKey must be overriden")
	}

	// MARK: Cache methods

	override open func readFromCache(_ c: ServerConnector, result: @escaping (AnyObject?) -> Void) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			result(nil)
			return
		}

		if let loadCon = c as? PaginationLiferayConnector {
			let key = cacheKey(loadCon)
			cacheManager.getSome(
				collection: ScreenletName(type(of: screenlet!)),
				keys: ["\(key)-\(page)", "\(key)-count"]) {

					loadCon.resultPageContent = $0.first as? [[String:AnyObject]]
					if $0.count > 1 {
						loadCon.resultRowCount = $0.last as? Int
					}

					result(loadCon.resultPageContent as AnyObject?)
			}
		}
		else {
			result(nil)
		}
	}

	override open func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		if let loadCon = c as? PaginationLiferayConnector,
			let pageContent = loadCon.resultPageContent, !pageContent.isEmpty {

			let key = cacheKey(loadCon)

			cacheManager.setClean(
				collection: ScreenletName(type(of: screenlet!)),
				key: "\(key)-\(page)",
				value: pageContent as NSCoding,
				attributes: [:])

			if let rowCount = loadCon.resultRowCount {
				cacheManager.setClean(
					collection: ScreenletName(type(of: screenlet!)),
					key: "\(key)-count",
					value: rowCount as NSCoding,
					attributes: [:])
			}
		}
	}

}
