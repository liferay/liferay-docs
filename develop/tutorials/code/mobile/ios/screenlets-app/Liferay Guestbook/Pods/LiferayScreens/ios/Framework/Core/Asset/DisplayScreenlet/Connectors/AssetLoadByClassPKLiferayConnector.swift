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

open class AssetLoadByClassPKLiferayConnector: ServerConnector, LoadAssetConnector {

	open let className: String
	open let classPK: Int64

	open var resultAsset: Asset?

	// MARK: Initializers

	public init(className: String, classPK: Int64) {
		self.className = className
		self.classPK = classPK

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if classPK == 0 {
				return ValidationError("assetdisplay-screenlet", "undefined-classpk")
			}
			else if className.isEmpty {
				return ValidationError("assetdisplay-screenlet", "undefined-classname")
			}
		}

		return error
	}
}

open class Liferay70AssetLoadByClassPKConnector: AssetLoadByClassPKLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		resultAsset = nil

		let service = LRScreensassetentryService_v70(session: session)

		do {
			let result = try service?.getAssetEntry(withClassName: className, classPK: classPK,
				locale: NSLocale.currentLocaleString)

			resultAsset = Asset(attributes: result as! [String:AnyObject])
			lastError = nil
		}
		catch let error as NSError {
			lastError = error
			resultAsset = nil
		}
	}
}
