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

class WebContentDisplayLoadInteractor: ServerReadConnectorInteractor {

	let groupId: Int64

	let articleId: String

	let structureId: Int64?

	let templateId: Int64?

	var resultHTML: String?

	var resultRecord: DDLRecord?

	// MARK: Interactor

	init(screenlet: BaseScreenlet, groupId: Int64, articleId: String, structureId: Int64?, templateId: Int64?) {
		self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
		self.articleId = articleId
		self.structureId = (structureId ?? 0 == 0) ? nil : structureId
		self.templateId = (templateId ?? 0 == 0) ? nil : templateId

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override func createConnector() -> WebContentLoadBaseLiferayConnector? {
		if let structureId = self.structureId, structureId != 0 {
			return LiferayServerContext.connectorFactory.createWebContentLoadStructuredConnector(
				groupId: groupId,
				articleId: articleId,
				structureId: structureId)
		}
		else {
			let htmlConnector = LiferayServerContext.connectorFactory.createWebContentLoadHtmlConnector(
				groupId: groupId,
				articleId: articleId)
			htmlConnector.templateId = templateId
			return htmlConnector
		}
	}

	override func completedConnector(_ c: ServerConnector) {
		if let htmlConnector = (c as? WebContentLoadHtmlLiferayConnector),
				let html = htmlConnector.resultHTML {
			self.resultHTML = html
		}
		else if let record = (c as? WebContentLoadStructuredLiferayConnector)?.resultRecord {
			self.resultRecord = record
		}
		else {
			self.resultHTML = nil
			self.resultRecord = nil
		}
	}

	// MARK: Cache methods

	override func readFromCache(_ c: ServerConnector, result: @escaping (AnyObject?) -> Void) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			result(nil)
			return
		}
		guard let loadCon = c as? WebContentLoadBaseLiferayConnector else {
			result(nil)
			return
		}

		if let loadHtml = loadCon as? WebContentLoadHtmlLiferayConnector {
			cacheManager.getString(
					collection: ScreenletName(WebContentDisplayScreenlet.self),
					key: articleCacheKey(loadCon.groupId, loadCon.articleId)) {
				loadHtml.resultHTML = $0
				result($0 as AnyObject?)
			}
		}
		else if let loadStructured = loadCon as? WebContentLoadStructuredLiferayConnector {
			cacheManager.getAny(
					collection: ScreenletName(WebContentDisplayScreenlet.self),
					key: articleCacheKey(loadCon.groupId, loadCon.articleId)) {
				loadStructured.resultRecord = $0 as? DDLRecord
				result(loadStructured.resultRecord)
			}
		}
		else {
			result(nil)
		}
	}

	override func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}
		guard let loadCon = c as? WebContentLoadBaseLiferayConnector else {
			return
		}
		guard let value: NSCoding =
				(loadCon as? WebContentLoadHtmlLiferayConnector)?.resultHTML as NSCoding??
				??
				(loadCon as? WebContentLoadStructuredLiferayConnector)?.resultRecord else {
			return
		}

		cacheManager.setClean(
			collection: ScreenletName(WebContentDisplayScreenlet.self),
			key: articleCacheKey(loadCon.groupId, loadCon.articleId),
			value: value,
			attributes: [
				"groupId": NSNumber(value: groupId),
				"articleId": loadCon.articleId as AnyObject])
	}

	// MARK: Private methods

	fileprivate func articleCacheKey(_ groupId: Int64, _ articleId: String) -> String {
		return "\((groupId != 0) ? groupId : LiferayServerContext.groupId)-articleId-\(articleId)"
	}

}
