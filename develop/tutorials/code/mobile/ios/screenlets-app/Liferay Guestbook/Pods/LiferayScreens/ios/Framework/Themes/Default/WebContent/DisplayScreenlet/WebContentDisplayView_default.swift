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
import WebKit

@objc(WebContentDisplayView_default)
open class WebContentDisplayView_default: BaseScreenletView, WebContentDisplayViewModel {

	// MARK: Outlets

	open var webView: WKWebView?

	open var injectedCss: String?

	override open var progressMessages: [String:ProgressMessages] {
		return [
			BaseScreenlet.DefaultAction: [
				.working: LocalizedString("default", key: "webcontentdisplay-loading-message", obj: self),
				.failure: LocalizedString("default", key: "webcontentdisplay-loading-error", obj: self)
			]
		]
	}

	open override func onCreated() {
		super.onCreated()

		let config = WKWebViewConfiguration.noCacheConfiguration

		webView = WKWebView(frame: self.frame, configuration: config)

		webView?.injectCookies()
		webView?.injectViewportMetaTag()

		addWebView()
	}

	open func addWebView() {
		webView?.translatesAutoresizingMaskIntoConstraints = false

		addSubview(webView!)

		let top = NSLayoutConstraint(item: webView!, attribute: .top, relatedBy: .equal,
		                             toItem: self, attribute: .top, multiplier: 1, constant: 0)
		let bottom = NSLayoutConstraint(item: webView!, attribute: .bottom, relatedBy: .equal,
		                                toItem: self, attribute: .bottom, multiplier: 1, constant: 0)
		let leading = NSLayoutConstraint(item: webView!, attribute: .leading, relatedBy: .equal,
		                                 toItem: self, attribute: .leading, multiplier: 1, constant: 0)
		let trailing = NSLayoutConstraint(item: webView!, attribute: .trailing, relatedBy: .equal,
		                                  toItem: self, attribute: .trailing, multiplier: 1, constant: 0)

		NSLayoutConstraint.activate([top, bottom, leading, trailing])
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	// MARK: WebContentDisplayViewModel

	open var htmlContent: String? {
		get {
			return ""
		}
		set {
			let styledHtml = "<style>\(injectedCss ?? "")</style><div class=\"MobileCSS\">\(newValue ?? "")</div>"
			webView!.loadHTMLString(styledHtml, baseURL: URL(string:LiferayServerContext.server))
		}
	}

	open var recordContent: DDLRecord?

	open var customCssFile: String? {
		didSet {
			if let css = customCssFile {
				injectedCss = webView?.loadCss(file: css)
			}
		}
	}
}
