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

@objc(ScreensWebView)
public protocol ScreensWebView {

	typealias JsCallHandler = (String, String) -> Void
	typealias JsErrorHandler = (String) -> (Any?, Error?) -> Void
	typealias OnPageLoadFinished = (String, Error?) -> Void

	var view: UIView { get }

	var isScrollEnabled: Bool { get set }

	init(jsCallHandler: @escaping JsCallHandler,
	     jsErrorHandler: @escaping JsErrorHandler,
		 onPageLoadFinished: @escaping OnPageLoadFinished)

	func add(injectableScript: InjectableScript)

	func inject(injectableScript: InjectableScript)

	func load(request: URLRequest)

	func load(htmlString: String)

	func clearCache()
}
