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
import WebKit

@objc(FileDisplayView_default)
open class FileDisplayView_default: BaseScreenletView, FileDisplayViewModel {

	// MARK: Outlets

	open var webView: WKWebView?

	// MARK: FileDisplayViewModel

	open var title: String? {
		didSet {
			self.presentingViewController?.title = title
		}
	}

	open var url: URL? {
		get {
			return url
		}
		set {
			webView?.load(URLRequest(url: newValue!))
		}
	}

	// MARK: BaseScreenletView

	open override func onCreated() {
		super.onCreated()

		webView = WKWebView(frame: self.frame)

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
}
