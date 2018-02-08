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

open class WebContentLoadStructuredLiferayConnector: WebContentLoadBaseLiferayConnector {

	open let structureId: Int64

	open var resultRecord: DDLRecord?

	// MARK: Initializers

	public init(groupId: Int64, articleId: String, structureId: Int64) {
		self.structureId = structureId

		super.init(groupId: groupId, articleId: articleId)
	}

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		if let resultRecord = doGetJournalArticleStructure(session) {
			self.resultRecord = resultRecord
			lastError = nil
		}
		else {
			if lastError == nil {
				lastError = NSError.errorWithCause(.invalidServerResponse,
						message: "Could not get journal article with the given structure.")
			}
			self.resultRecord = nil
		}
	}

	// MARK: Internal methods

	internal func doGetJournalArticleStructure(_ session: LRSession) -> DDLRecord? {
		fatalError("doGetJournalArticle method must be overwritten")
	}

}

open class Liferay62WebContentLoadStructuredConnector: WebContentLoadStructuredLiferayConnector {

	// MARK: WebContentLoadStructuredLiferayConnector

	override internal func doGetJournalArticleStructure(_ session: LRSession) -> DDLRecord? {
		let batchSession = LRBatchSession(session: session)

		let structureSrv = LRDDMStructureService_v62(session: batchSession)
		_ = try? structureSrv?.getStructureWithStructureId(self.structureId)

		let journalArticleSrv = LRJournalArticleService_v62(session: batchSession)
		_ = try? journalArticleSrv?.getArticleWithGroupId(self.groupId, articleId: self.articleId)

		guard batchSession?.commands.count == 2 else {
			return nil
		}

		do {
			let results = try batchSession?.invoke()
			guard results?.count == 2 else {
				return nil
			}
			guard var structureResult = results?[0] as? [String:AnyObject] else {
				return nil
			}
			guard let xsd = structureResult["xsd"] as? String else {
				return nil
			}
			guard var articleResult = results?[1] as? [String:AnyObject] else {
				return nil
			}
			guard let xmlContent = articleResult["content"] as? String else {
				return nil
			}

			structureResult.removeValue(forKey: "xsd")
			articleResult.removeValue(forKey: "content")

			guard let structure = DDMStructure(
					xsd: xsd,
					locale: Locale(identifier: NSLocale.currentLocaleString),
					attributes: structureResult) else {
				return nil
			}

			let record = DDLRecord(structure: structure)
			record.updateCurrentValues(xmlValues: xmlContent)
			record.attributes = articleResult

			return record
		}
		catch let error as NSError {
			lastError = error
		}

		return nil
	}

}

open class Liferay70WebContentLoadStructuredConnector: WebContentLoadStructuredLiferayConnector {

	// MARK: WebContentLoadStructuredLiferayConnector

	override internal func doGetJournalArticleStructure(_ session: LRSession) -> DDLRecord? {
		let batchSession = LRBatchSession(session: session)

		let structureSrv = LRDDMStructureService_v7(session: batchSession)
		_ = try? structureSrv?.getStructureWithStructureId(self.structureId)

		let journalArticleSrv = LRJournalArticleService_v7(session: batchSession)
		_ = try? journalArticleSrv?.getArticleWithGroupId(self.groupId, articleId: self.articleId)

		guard batchSession?.commands.count == 2 else {
			return nil
		}

		do {
			let results = try batchSession?.invoke()
			guard results?.count == 2 else {
				return nil
			}
			guard var structureResult = results?[0] as? [String:AnyObject] else {
				return nil
			}
			guard let json = structureResult["definition"] as? String else {
				return nil
			}
			guard var articleResult = results?[1] as? [String:AnyObject] else {
				return nil
			}
			guard let xmlContent = articleResult["content"] as? String else {
				return nil
			}

			structureResult.removeValue(forKey: "definition")
			articleResult.removeValue(forKey: "content")

			guard let structure = DDMStructure(
					json: json,
					locale: Locale(identifier: NSLocale.currentLocaleString),
					attributes: structureResult) else {
				return nil
			}

			let record = DDLRecord(structure: structure)
			record.updateCurrentValues(xmlValues: xmlContent)
			record.attributes = articleResult

			return record
		}
		catch let error as NSError {
			lastError = error
		}

		return nil
	}

}
