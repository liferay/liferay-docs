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

@objc(WebViewModel)
public protocol WebViewModel {

	/// Whether or not it should show log messages
	var isLoggingEnabled: Bool { get set }

	/// Whether or not the scroll of the webview is enabled
	var isScrollEnabled: Bool { get set }

	/// Used to create the WebView depending on the cordovaEnabled flag
	///
	/// - Parameter cordovaEnabled: it tell the view if it has to create a cordova WebView
	func configureView(with cordovaEnabled: Bool)

	/// Add scripts to be injected when the page is loaded
	func add(injectableScripts: [InjectableScript])

	/// Add script to be injected when the page is loaded
	func add(injectableScript: InjectableScript)

	/// Inject script into the page
	func inject(injectableScript: InjectableScript)

	/// Loads the content of the WebView using the request received as argument
	///
	/// - Parameter request
	func load(request: URLRequest)

	/// Loads the content of the WebView using the html received as argument
	///
	/// - Parameter htmlString
	func load(htmlString: String)

	/// Clears the WebView cache
	func clearCache()
}
