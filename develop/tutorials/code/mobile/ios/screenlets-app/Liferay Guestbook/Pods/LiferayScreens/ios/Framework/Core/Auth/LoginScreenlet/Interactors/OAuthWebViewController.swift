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
import LROAuth

class OAuthWebViewController: UIViewController, UIWebViewDelegate {

	@IBOutlet weak var webView: UIWebView?
	@IBOutlet weak var activityIndicator: UIActivityIndicatorView?
	@IBOutlet weak var closeButton: UIButton?

	var onAuthorized: ((String) -> Void)?

	fileprivate let URL: Foundation.URL

	init(URL: Foundation.URL, themeName: String) {
		var nibName = "OAuthWebViewController_\(themeName)"
		var bundle = Bundle.bundleForNibName(nibName, currentClass: type(of: self))
		if bundle == nil {
			nibName = "OAuthWebViewController_default"
			bundle = Bundle.bundleForNibName(nibName, currentClass: type(of: self))
		}

		self.URL = URL

		super.init(
				nibName: nibName,
				bundle: bundle)
	}

	required init?(coder aDecoder: NSCoder) {
		URL = Foundation.URL(string: "")!
		super.init(coder: aDecoder)
	}

	override func viewWillAppear(_ animated: Bool) {
		activityIndicator?.startAnimating()
		webView?.delegate = self
		webView?.loadRequest(URLRequest(url: URL))
		closeButton?.titleLabel?.text = NSLocalizedString("default-oauth-close", comment: "Close")
	}

	@IBAction func closeAction(_ sender: AnyObject) {
		activityIndicator?.stopAnimating()

		self.dismiss(animated: true, completion: nil)
	}

	func webView(_ webView: UIWebView,
			shouldStartLoadWith request: URLRequest,
			navigationType: UIWebViewNavigationType) -> Bool {

		let URL = request.url?.absoluteString

		if request.url?.scheme == "screens"
				&& URL?.range(of: "oauth_verifier=") != nil {

			let params = LROAuth.extractRequestParams(URL!)
			onAuthorized?(params!["oauth_verifier"] as! String)
			return false
		}

		return true
	}

	func webViewDidFinishLoad(_ webView: UIWebView) {
		activityIndicator?.stopAnimating()
	}

}
