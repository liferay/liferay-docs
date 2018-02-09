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

@objc(Comment)
open class Comment: NSObject, NSCoding {

	open static func plainBodyToHtml(_ plainBody: String) -> String {
		return plainBody
			.replacingOccurrences(of: "<", with: "&lt;")
			.replacingOccurrences(of: ">", with: "&gt;")
			.characters
			.split(separator: "\n")
			.map(String.init)
			.map { "<p>\($0)</p>" }
			.joined(separator: "")
	}

	fileprivate let AllowedTags = ["strong", "i", "b", "a"]

	open let attributes: [String:AnyObject]

	open var originalBody: String {
		return attributes["body"]!.description
			.replacingOccurrences(of: "\n ", with: "")
			.replacingOccurrences(of: "\n", with: "")
			.replacingOccurrences(of: "</p><p>", with: "\n")
			.replacingOccurrences(of: "<p>", with: "")
			.replacingOccurrences(of: "</p>", with: "")
	}

	open var plainBody: String {
		return originalBody
			.replacingOccurrences(
				of: "<[^>]+>",
				with: "",
				options: .regularExpression,
				range: nil)
			.replacingOccurrences(of: "&lt;", with: "<")
			.replacingOccurrences(of: "&gt;", with: ">")
	}

	open var htmlBody: String {
		let closeTags = AllowedTags.map { "/\($0)" }
		let allTags = closeTags + AllowedTags

		return originalBody
			.replacingOccurrences(
				of: "(?i)<(?!\(allTags.joined(separator: "|"))).*?>",
				with: "",
				options: .regularExpression,
				range: nil)
			.replacingOccurrences(of: "\n", with: "</br>")
	}

	open var isStyled: Bool {
		return originalBody.contains("<")
	}

	open var commentId: Int64 {
		return (attributes["commentId"]! as! NSNumber).int64Value
	}

	open var userName: String {
		return attributes["userName"]!.description
	}

	open var userId: Int64 {
		return (attributes["userId"]! as! NSNumber).int64Value
	}

	open var createDate: Date {
		let milliseconds = (attributes["createDate"]! as! NSNumber).doubleValue
		return Date(millisecondsSince1970: milliseconds)
	}

	open var modifiedDate: Date {
		let milliseconds = (attributes["modifiedDate"]! as! NSNumber).doubleValue
		return Date(millisecondsSince1970: milliseconds)
	}

	open var canDelete: Bool {
		return attributes["deletePermission"] as? Bool ?? false
	}

	open var canEdit: Bool {
		return attributes["updatePermission"] as? Bool ?? false
	}

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(self.attributes, forKey:"comment-attrs")
	}

	// MARK: Initializers

	public init(attributes: [String:AnyObject]) {
		self.attributes = attributes

		super.init()
	}

	public required init?(coder aDecoder: NSCoder) {
		self.attributes = aDecoder.decodeObject(forKey: "comment-attrs") as? [String:AnyObject] ?? [:]

		super.init()
	}
}
