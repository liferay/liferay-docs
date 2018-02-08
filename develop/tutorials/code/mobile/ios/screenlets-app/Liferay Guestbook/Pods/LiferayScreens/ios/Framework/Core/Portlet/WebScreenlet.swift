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

/// The WebScreenletDelegate protocol defines some methods that you use to
/// manage the WebScreenlet events. All of them are optional.
@objc(WebScreenletDelegate)
public protocol WebScreenletDelegate: BaseScreenletDelegate {

	///  Called when the page is loaded.
	///
	/// - Parameters:
	///   - screenlet: Web display screenlet instance.
	@objc optional func onWebLoad(_ screenlet: WebScreenlet, url: String)

	/// Called when an error occurs in the process.
	///
	/// - Parameters:
	///   - screenlet: Web display screenlet instance.
	///   - error: Error object describing the error
	@objc optional func screenlet(_ screenlet: WebScreenlet,
	                              onError error: NSError)

	/// Called when we want to notify a message from the WKWebView used in the view.
	///
	/// - Parameters:
	///   - screenlet: Web display screenlet instance.
	///   - key: source message key.
	///   - body: source message body.
	@objc optional func screenlet(_ screenlet: WebScreenlet,
	                              onScriptMessageNamespace namespace: String,
	                              onScriptMessage message: String)
}

/// Web Screenlet can display a web page (Liferay page or not). 
/// With this screenlet you can inject custom css and js files to customize
/// your view.
@objc(WebScreenlet)
open class WebScreenlet: BaseScreenlet {

	let internalNamespace = "screensinternal"

	/// Whether the content should be retrieved from the portal as soon as the Screenlet appears.
	/// The default value is false.
	@IBInspectable open var autoLoad: Bool = false

	@IBInspectable open var loggingEnabled: Bool = true

	@IBInspectable open var isScrollEnabled: Bool = true

	open override var themeName: String? {
		didSet {
			guard let configuration = configuration else { return }
			webViewModel.configureView(with: configuration.isCordovaEnabled)
		}
	}

	/// The Web URL to be displayed.
	open var configuration: WebScreenletConfiguration? {
		didSet {
			guard let configuration = configuration else { return }
			webViewModel.configureView(with: configuration.isCordovaEnabled)
		}
	}

	// MARK: Public properties
	open var webDelegate: WebScreenletDelegate? {
		return delegate as? WebScreenletDelegate
	}

	open var webViewModel: WebViewModel {
		return screenletView as! WebViewModel
	}

	// MARK: BaseScreenlet

	override open func onShow() {
		if autoLoad {
			load()
		}
	}

	// MARK: Public methods

	open func clearCache() {
		webViewModel.clearCache()
	}

	open func handleJsCall(namespace: String, message: String) {
		if namespace.hasPrefix(internalNamespace) {
			handleInternal(namespace: namespace, message: message)
		}
		else {
			webDelegate?.screenlet?(self,
				onScriptMessageNamespace: namespace, onScriptMessage: message)
		}
	}

	open func inject(injectableScript: InjectableScript) {
		webViewModel.inject(injectableScript: injectableScript)
	}

	/// Loads the page specified in the configuraion.
	open func load() {
		guard let configuration = configuration else {
			webDelegate?.screenlet?(self, onError: NSError.errorWithCause(
				.invalidServerResponse, message: "You need to specify a web screnlet configuration first"))
			return
		}

		webViewModel.isLoggingEnabled = loggingEnabled

		let screensScript = Bundle.loadFile(name: "Screens", ofType: "js", currentClass: type(of: self))!

		webViewModel.add(injectableScript: JsScript(name: "Screens.js", js: screensScript))
		// disable selection
		webViewModel.add(injectableScript: CssScript(name: "disableSelection.css",
				css: "*:not(input):not(textarea) { -webkit-user-select: none; -webkit-touch-callout: none; }"))
		webViewModel.add(injectableScripts: configuration.scripts)

		webViewModel.isScrollEnabled = isScrollEnabled

		switch configuration.webType {
			case .liferayAuthenticated:
				guard SessionContext.isLoggedIn else {
					webDelegate?.screenlet?(self, onError: NSError.errorWithCause(
						.abortedDueToPreconditions, message: "You have to be logged to use this web type"))
					return
				}

				let html = configureInitialHtml(url: configuration.url)
				webViewModel.load(htmlString: html)
			case .other:
				webViewModel.load(request: URLRequest(url: URL(string: configuration.url)!))
		}
	}

	func handleInternal(namespace: String, message: Any) {
		if namespace.hasSuffix("consoleMessage") {
			if loggingEnabled {
				print("Console message: \(message)")
			}
		}
	}

	func configureInitialHtml(url: String) -> String {
		// With WKWebView we are not able to load a page using a POST request, in order to work around we have to
		// create a form with the parameters for the POST request and submit it to actually perform the request

		// This html is a template to build the form
		let html = Bundle.loadFile(name: "index", ofType: "html", currentClass: type(of: self))!

		let username = SessionContext.currentContext?.basicAuthUsername ?? ""
		let password = SessionContext.currentContext?.basicAuthPassword ?? ""

		let urlEscaped = url.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)

		return html.replacingOccurrences(of: "<webUrl>", with: urlEscaped!)
			.replacingOccurrences(of: "<login>", with: username)
			.replacingOccurrences(of: "<password>", with: password)
	}

}
