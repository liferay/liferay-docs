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

@objc(Asset)
open class Asset: NSObject, NSCoding, MimeTypeable {

	open let attributes: [String:AnyObject]

	open let title: String

	// MARK: MimeTypeable

	open var mimeType: String? {
		return attributes["mimeType"]?.description
	}

	override open var description: String {
		return attributes["description"] as! String
	}

	open var classNameId: Int64 {
		return attributes["classNameId"]!.int64Value
	}

	open var classPK: Int64 {
		return attributes["classPK"]!.int64Value
	}

	open var groupId: Int64 {
		return attributes["groupId"]!.int64Value
	}

	open var companyId: Int64 {
		return attributes["companyId"]!.int64Value
	}

	open var url: String {
		return attributes["url"]!.description!
	}

	open var entryId: Int64 {
		return attributes["entryId"]!.int64Value!
	}

	open var createDate: Date {
		let milliseconds = (attributes["createDate"]! as! NSNumber).doubleValue
		return Date(millisecondsSince1970: milliseconds)
	}

	open var modifiedDate: Date {
		let milliseconds = (attributes["modifiedDate"]! as! NSNumber).doubleValue
		return Date(millisecondsSince1970: milliseconds)
	}

	override open var debugDescription: String {
		return attributes.debugDescription
	}

	// MARK: Initializers

	public init(attributes: [String:AnyObject]) {
		self.attributes = attributes

		let xmlTitle = attributes["title"] as! String
		title = xmlTitle.asLocalized(Locale(identifier: NSLocale.currentLocaleString))

		super.init()
	}

	public required init?(coder aDecoder: NSCoder) {
		self.attributes = aDecoder.decodeObject(forKey: "asset-attrs") as? [String:AnyObject] ?? [:]

		let xmlTitle = attributes["title"] as! String
		title = xmlTitle.asLocalized(Locale(identifier: NSLocale.currentLocaleString))

		super.init()
	}

	// MARK: Public methods

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(attributes, forKey: "asset-attrs")
	}

}
