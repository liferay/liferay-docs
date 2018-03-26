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

open class WebContentLoadHtmlLiferayConnector: WebContentLoadBaseLiferayConnector {

	open var templateId: Int64?

	open var resultHTML: String?

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		resultHTML = nil

		var result: String?

		if templateId ?? 0 != 0 {
			result = doGetJournalArticleWithTemplate(templateId!, session: session)
		}
		else {
			result = doGetJournalArticle(session)
		}

		if lastError == nil {
			if let result = result {
				resultHTML = replaceHTMLPlaceholders(result)
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
						message: "Could not get journal article with the given template.")
			}
		}
	}

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if articleId == "" {
				return ValidationError("webcontentdisplay-screenlet", "undefined-article")
			}
		}

		return error
	}

	// MARK: Internal methods

	internal func replaceHTMLPlaceholders(_ html: String) -> String {
		return html
			.replacingOccurrences(
				of: "@cdn_host@", with: "")
			.replacingOccurrences(
				of: "@root_path@", with: LiferayServerContext.server)
	}

	internal func doGetJournalArticleWithTemplate(
			_ templateId: Int64,
			session: LRSession) -> String? {
		fatalError("doGetJournalArticleWithTemplate method must be overwritten")
	}

	internal func doGetJournalArticle(_ session: LRSession) -> String? {
		fatalError("doGetJournalArticle method must be overwritten")
	}

}

open class Liferay62WebContentLoadHtmlConnector: WebContentLoadHtmlLiferayConnector {

	// MARK: WebContentLoadHtmlLiferayConnector

	override internal func doGetJournalArticleWithTemplate(
			_ templateId: Int64,
			session: LRSession) -> String? {
		let service = LRScreensjournalarticleService_v62(session: session)

		do {
			let result = try service?.getJournalArticleContent(withGroupId: groupId,
				articleId: articleId,
			    ddmTemplateId: templateId,
			    locale: NSLocale.currentLocaleString)

			lastError = nil

			return result
		}
		catch let error as NSError {
			lastError = error
		}

		return nil
	}

	override internal func doGetJournalArticle(_ session: LRSession) -> String? {
		let service = LRJournalArticleService_v62(session: session)

		do {
			let result = try service?.getArticleContent(withGroupId: groupId,
				articleId: articleId,
			    languageId: NSLocale.currentLocaleString,
			    themeDisplay: nil)

			lastError = nil

			return result
		}
		catch let error as NSError {
			lastError = error
		}

		return nil
	}

}

open class Liferay70WebContentLoadHtmlConnector: WebContentLoadHtmlLiferayConnector {

	// MARK: WebContentLoadHtmlLiferayConnector

	override internal func doGetJournalArticleWithTemplate(
			_ templateId: Int64,
			session: LRSession) -> String? {
		let service = LRScreensjournalarticleService_v70(session: session)

		do {
			let result = try service?.getJournalArticleContent(withGroupId: groupId,
				articleId: articleId,
				ddmTemplateId: templateId,
				locale: NSLocale.currentLocaleString)

			lastError = nil

			return result
		}
		catch let error as NSError {
			lastError = error
		}

		return nil
	}

	override internal func doGetJournalArticle(_ session: LRSession) -> String? {
		let service = LRJournalArticleService_v7(session: session)

		do {
			let result = try service?.getArticleContent(withGroupId: groupId,
				articleId: articleId,
			    languageId: NSLocale.currentLocaleString,
				themeDisplay: nil)

			lastError = nil

			return result
		}
		catch let error as NSError {
			lastError = error
		}

		return nil
	}

}
