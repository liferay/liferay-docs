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

@objc open class ScreensWKUIDelegate: NSObject, WKUIDelegate {

	weak var viewController: UIViewController?

	public init(viewController: UIViewController?) {
		self.viewController = viewController

		if viewController == nil {
			print("Error: you have to set the presentingViewController property on your WebScreenlet"
			+ "if you want to show alerts")
		}
	}

	public func webView(_ webView: WKWebView, runJavaScriptAlertPanelWithMessage message: String,
			initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping () -> Void) {

		let alert = UIAlertController(title: nil, message: message, preferredStyle: .alert)

		let okAction = UIAlertAction(title: NSLocalizedString("OK", comment: "OK"), style: .default) { _ in
			completionHandler()
			alert.dismiss(animated: true, completion: nil)
		}

		alert.addAction(okAction)

		viewController?.present(alert, animated: true, completion: nil)
	}

	public func webView(_ webView: WKWebView, runJavaScriptConfirmPanelWithMessage message: String,
			initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping (Bool) -> Void) {

		let alert = UIAlertController(title: nil, message: message, preferredStyle: .alert)

		let okAction = UIAlertAction(title: NSLocalizedString("OK", comment: "OK"), style: .default) { _ in
			completionHandler(true)
			alert.dismiss(animated: true, completion: nil)
		}

		let cancelAction = UIAlertAction(title: NSLocalizedString("Cancel", comment: "Cancel"), style: .default) { _ in
			completionHandler(false)
			alert.dismiss(animated: true, completion: nil)
		}

		alert.addAction(okAction)
		alert.addAction(cancelAction)

		viewController?.present(alert, animated: true, completion: nil)
	}

	public func webView(_ webView: WKWebView, runJavaScriptTextInputPanelWithPrompt prompt: String,
			defaultText: String?, initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping (String?) -> Void) {

		let alert = UIAlertController(title: nil, message: prompt, preferredStyle: .alert)

		let okAction = UIAlertAction(title: NSLocalizedString("OK", comment: "OK"), style: .default) { _ in
			completionHandler(alert.textFields?[0].text)
			alert.dismiss(animated: true, completion: nil)
		}

		let cancelAction = UIAlertAction(title: NSLocalizedString("Cancel", comment: "Cancel"), style: .default) { _ in
			completionHandler(nil)
			alert.dismiss(animated: true, completion: nil)
		}

		alert.addAction(okAction)
		alert.addAction(cancelAction)

		alert.addTextField { textField in
			textField.text = defaultText
		}

		viewController?.present(alert, animated: true, completion: nil)
	}
}
