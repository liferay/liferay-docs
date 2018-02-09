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
import YapDatabase

@objc open class CacheMetadata: NSObject, NSCoding {

	open let synchronized: Date?
	open let attributes: [String:AnyObject]

	public init(synchronized: Date?, attributes: [String:AnyObject]) {
		self.synchronized = synchronized
		self.attributes = attributes

		super.init()
	}

	public required convenience init?(coder aDecoder: NSCoder) {
		let synchronized = aDecoder.decodeObject(forKey: "sync_date") as? Date
		let attributes = (aDecoder.decodeObject(forKey: "attributes") as? [String:AnyObject]) ?? [:]

		self.init(synchronized: synchronized, attributes: attributes)
	}

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(synchronized, forKey:"sync_date")
		aCoder.encode(attributes, forKey:"attributes")
	}

}
