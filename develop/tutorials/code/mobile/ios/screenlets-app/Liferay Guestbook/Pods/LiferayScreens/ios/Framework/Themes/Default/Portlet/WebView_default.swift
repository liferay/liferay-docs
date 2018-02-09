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

@objc(WebView_default)
open class WebView_default: BaseScreenletView, WebViewModel {

	// MARK: Public properties

    var progressPresenter: ProgressPresenter?

	// MARK: WebViewModel

	open var isLoggingEnabled = true

	open var isScrollEnabled: Bool {
		get {
			return screensWebView?.isScrollEnabled ?? false
		}
		set {
			screensWebView?.isScrollEnabled = newValue
		}
	}

	open var screensWebView: ScreensWebView?

	open var webScreenlet: WebScreenlet {
		return self.screenlet as! WebScreenlet
	}

	open func configureView(with cordovaEnabled: Bool) {
		if screensWebView != nil {
			return
		}

		let jsCallHandler = weakify(owner: self, f: WebView_default.handleJsCall)
		let onPageLoadFinished = weakify(owner: self, f: WebView_default.onPageLoadFinished)

		let jsErrorHandler: ScreensWebView.JsErrorHandler = { [weak self] scriptName in
			return { _, error in
				guard self?.isLoggingEnabled ?? false else { return }
				print("executed \(scriptName)")
				if let error = error as NSError? {

					if error.domain == "WKErrorDomain" && error.code != 5 {
						print("\nError injecting \(scriptName): \(self?.parseJavaScriptError(error) ?? "")")
					}
				}
			}
		}

		if cordovaEnabled {
			screensWebView = ScreensCordovaWebView(
					jsCallHandler: jsCallHandler,
					jsErrorHandler: jsErrorHandler,
					onPageLoadFinished: onPageLoadFinished)
		}
		else {
			screensWebView = ScreensWKWebView(
					jsCallHandler: jsCallHandler,
					jsErrorHandler: jsErrorHandler,
					onPageLoadFinished: onPageLoadFinished)

			(screensWebView as? ScreensWKWebView)?.viewController = screenlet?.presentingViewController
		}

		addWebView()
	}

	open func add(injectableScripts: [InjectableScript]) {
		injectableScripts.forEach { add(injectableScript: $0) }
	}

	open func add(injectableScript: InjectableScript) {
		screensWebView?.add(injectableScript: injectableScript)
	}

	open func inject(injectableScript: InjectableScript) {
		screensWebView?.inject(injectableScript: injectableScript)
	}

	open func load(request: URLRequest) {
		showHud()
		screensWebView?.view.isHidden = true
		screensWebView?.load(request: request)
	}

	open func load(htmlString: String) {
		showHud()
		screensWebView?.view.isHidden = true
		screensWebView?.load(htmlString: htmlString)
	}

	open func clearCache() {
		screensWebView?.clearCache()
	}

    // MARK: BaseScreenletView

    open override func onCreated() {
        super.onCreated()
        backgroundColor = .clear
        self.progressPresenter = createProgressPresenter()
    }

    open override func createProgressPresenter() -> ProgressPresenter {
        return DefaultProgressPresenter()
    }

	open func addWebView() {
		guard let webView = screensWebView?.view else { return }

		webView.translatesAutoresizingMaskIntoConstraints = false

		webView.backgroundColor = .clear

		addSubview(webView)

		let top = NSLayoutConstraint(item: webView, attribute: .top, relatedBy: .equal,
		                             toItem: self, attribute: .top, multiplier: 1, constant: 0)
		let bottom = NSLayoutConstraint(item: webView, attribute: .bottom, relatedBy: .equal,
		                                toItem: self, attribute: .bottom, multiplier: 1, constant: 0)
		let leading = NSLayoutConstraint(item: webView, attribute: .leading, relatedBy: .equal,
		                                 toItem: self, attribute: .leading, multiplier: 1, constant: 0)
		let trailing = NSLayoutConstraint(item: webView, attribute: .trailing, relatedBy: .equal,
		                                  toItem: self, attribute: .trailing, multiplier: 1, constant: 0)

		NSLayoutConstraint.activate([top, bottom, leading, trailing])
	}

	open func onPageLoadFinished(url: String, with error: Error?) {
		if let error = error {
			self.progressPresenter?
				.hideHUDFromView(self,
				message: LocalizedString("default", key: "web-loading-error", obj: self),
				forInteractor: Interactor(), withError: error as NSError?)

			webScreenlet
				.webDelegate?.screenlet?(webScreenlet, onError: error as NSError)
		}
		else {
			screensWebView?.inject(injectableScript: JsScript(name: "waitForJs",
				js: "window.Screens.waitForJsLoaded()"))
		}
	}

	open func handleJsCall(namespace: String, message: String) {
			if namespace == "jsloaded" {
				progressPresenter?.hideHUDFromView(self, message: nil, forInteractor: Interactor(), withError: nil)

				UIView.transition(
					with: screensWebView!.view,
					duration: 0.4,
					options: .transitionCrossDissolve,
					animations: {
						self.screensWebView?.view.isHidden = false
					},
					completion: nil)

				webScreenlet.webDelegate?
					.onWebLoad?(webScreenlet, url: message)
			}
			else {
				webScreenlet.handleJsCall(namespace: namespace, message: message)
			}
		}

	open func showHud() {
		progressPresenter?.showHUDInView(self, message: LocalizedString(
			"default", key: "web-loading-message", obj: self), forInteractor: Interactor())
	}

	func parseJavaScriptError(_ error: NSError) -> String {
		let lineNumber = error.userInfo["WKJavaScriptExceptionLineNumber"] ?? ""
		let message = error.userInfo["WKJavaScriptExceptionMessage"] ?? ""

		return "Line \(lineNumber), \(message)"
	}
}
