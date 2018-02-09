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

/// The WebContentDisplayScreenletDelegate protocol defines some methods that you use to manage the
/// WebContentDisplayScreenlet events. All of them are optional.
@objc(WebContentDisplayScreenletDelegate)
public protocol WebContentDisplayScreenletDelegate: BaseScreenletDelegate {

	///  Called when the web content’s HTML is received.
	///
	/// - Parameters:
	///   - screenlet: Web content display screenlet instance.
	///   - html: Web content html.
	/// - Returns: Original or modified html.
	@objc optional func screenlet(_ screenlet: WebContentDisplayScreenlet,
			onWebContentResponse html: String) -> String?

	/// Called when a web content record is received.
	///
	/// - Parameters:
	///   - screenlet: Web content display screenlet instance.
	///   - record: Web content record.
	@objc optional func screenlet(_ screenlet: WebContentDisplayScreenlet,
		onRecordContentResponse record: DDLRecord)

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Web content display screenlet instance.
	///   - error: Error while retrieving web content or record.
	@objc optional func screenlet(_ screenlet: WebContentDisplayScreenlet,
			onWebContentError error: NSError)

}

/// The Web Content Display Screenlet shows web content elements in your app, rendering the inner 
/// HTML of the web content. The Screenlet also supports i18n, rendering contents differently 
/// depending on the device’s current locale.
@objc(WebContentDisplayScreenlet)
open class WebContentDisplayScreenlet: BaseScreenlet {

	// MARK: Inspectables

	/// The site (group) identifier where the asset is stored. If this value is 0, the groupId 
	/// specified in LiferayServerContext is used.
	@IBInspectable open var groupId: Int64 = 0

	/// The identifier of the web content to display. You can find the identifier by clicking Edit 
	/// on the web content in the Liferay instance.
	@IBInspectable open var articleId: String = ""

	/// The identifier of the template used to render the web content. This is applicable only with 
	/// [structured web content](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/designing-uniform-content).
	@IBInspectable open var templateId: Int64 = 0

	/// Specifies the identifier of a data definition for your site in Liferay.
	@IBInspectable open var structureId: Int64 = 0

	/// Whether the content should be retrieved from the portal as soon as the Screenlet appears. 
	/// The default value is true.
	@IBInspectable open var autoLoad: Bool = true

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// Custom css file to customize the WebContent
	@IBInspectable open var customCssFile: String = "default"

	// MARK: Public properties

	open var webContentDisplayDelegate: WebContentDisplayScreenletDelegate? {
		return delegate as? WebContentDisplayScreenletDelegate
	}

	open var webContentDisplayViewModel: WebContentDisplayViewModel? {
		return screenletView as? WebContentDisplayViewModel
	}

	// MARK: BaseScreenlet

	override open func onShow() {
		if autoLoad && articleId != "" {
			loadWebContent()
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		let interactor = WebContentDisplayLoadInteractor(
			screenlet: self,
			groupId: self.groupId,
			articleId: self.articleId,
			structureId: self.structureId,
			templateId: self.templateId)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultHtml = interactor.resultHTML {
				let modifiedHtml = self.webContentDisplayDelegate?.screenlet?(self,
					onWebContentResponse: resultHtml)

				self.webContentDisplayViewModel?.customCssFile = self.customCssFile
				self.webContentDisplayViewModel?.htmlContent = modifiedHtml ?? resultHtml
			}
			else if let resultRecord = interactor.resultRecord {
				self.webContentDisplayDelegate?.screenlet?(self,
					onRecordContentResponse: resultRecord)

				self.webContentDisplayViewModel?.recordContent = resultRecord
			}
		}

		interactor.onFailure = {
			self.webContentDisplayDelegate?.screenlet?(self, onWebContentError: $0)
		}

		return interactor
	}

	// MARK: Public methods

	/// Loads a web content in the screenlet.
	///
	/// - Returns: True if default use case has been perform, false otherwise.
	@discardableResult
	open func loadWebContent() -> Bool {
		return self.performDefaultAction()
	}

}
