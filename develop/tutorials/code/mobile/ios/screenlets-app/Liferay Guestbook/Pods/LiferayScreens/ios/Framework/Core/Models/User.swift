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

@objc(User)
open class User: NSObject, NSCoding {

	open let attributes: [String:AnyObject]

	open var firstName: String {
		return stringAttribute("firstName")
	}

	open var lastName: String {
		return stringAttribute("lastName")
	}

	open var middleName: String {
		return stringAttribute("middleName")
	}

	open var screenName: String {
		return stringAttribute("screenName")
	}

	open var greeting: String {
		return stringAttribute("greeting")
	}

	open var jobTitle: String {
		return stringAttribute("jobTitle")
	}

	open var email: String {
		return stringAttribute("emailAddress")
	}

	open var userId: Int64 {
		return int64Attribute("userId")
	}

	// MARK: Initializers

	public init(attributes: [String : AnyObject]) {
		self.attributes = attributes

		super.init()
	}

	public required init?(coder aDecoder: NSCoder) {
		self.attributes = aDecoder.decodeObject(forKey: "asset-attrs") as? [String:AnyObject] ?? [:]

		super.init()
	}

	// MARK: Public methods

	open func int64Attribute(_ key: String) -> Int64 {
		return attributes[key]?.int64Value ?? 0
	}

	open func stringAttribute(_ key: String) -> String {
		return attributes[key]?.description ?? ""
	}

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(attributes, forKey: "asset-attrs")
	}
}
