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
import Foundation

private let dummyObject = MBProgressHUDPresenter()

open class ValidationError: NSError {

	public init(_ message: String) {
		super.init(domain: "LiferayScreens",
			code: ScreensErrorCause.validationFailed.rawValue,
			userInfo: [NSLocalizedDescriptionKey: message])
	}

	public convenience init(_ key: String, _ message: String) {
		self.init(LocalizedString(key, key: message, obj: dummyObject))
	}

	public convenience init(_ key: String, _ message: String, _ bundleObject: AnyObject) {
		self.init(LocalizedString(key, key: message, obj: bundleObject))
	}

	required public init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

}

public enum ScreensErrorCause: Int {

	case abortedDueToPreconditions = -2
	case invalidServerResponse = -3
	case validationFailed = -4
	case notAvailable = -5
	case cancelled = -6

}

public extension NSError {

	public class func errorWithCause(
			_ cause: ScreensErrorCause,
			userInfo: [AnyHashable: Any]? = nil)
			-> NSError {

		return NSError(
				domain: "LiferayScreens",
				code: cause.rawValue,
				userInfo: userInfo)
}

	public class func errorWithCause(
			_ cause: ScreensErrorCause,
			message: String)
			-> NSError {

		let userInfo = [NSLocalizedDescriptionKey: message]

		return NSError(
				domain: "LiferayScreens",
				code: cause.rawValue,
				userInfo: userInfo)
	}

}
