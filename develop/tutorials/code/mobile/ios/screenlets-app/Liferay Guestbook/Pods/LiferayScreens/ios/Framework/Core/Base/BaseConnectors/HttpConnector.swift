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

open class HttpConnector: ServerConnector {

	open var url: URL

	open var resultData: Data?

	// MARK: Initializers

	public init(url: URL) {
		self.url = url

		super.init()
	}

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let session = URLSession(configuration: .default)

		let requestSemaphore = DispatchSemaphore(value: 0)

		let urlRequest = NSMutableURLRequest(url: self.url)

		if let auth
			= SessionContext.currentContext?.session.authentication as? LRCookieAuthentication {

			auth.authenticate(urlRequest)
		}

		session.dataTask(with: urlRequest as URLRequest, completionHandler: { (data, _, error) -> Void in
			if let error = error {
				self.lastError = error as NSError?
				self.resultData = nil
			}
			else {
				self.resultData = data
				self.lastError = nil
			}
			requestSemaphore.signal()

		}).resume()

		_ = requestSemaphore.wait(timeout: .distantFuture)
	}

	override open func createSession() -> LRSession? {
		// dummy session: won't be used
		let port = (url.port == nil) ? "" : ":\(url.port!)"
		return LRSession(server: "http://\(url.host!)\(port)")
	}

}
