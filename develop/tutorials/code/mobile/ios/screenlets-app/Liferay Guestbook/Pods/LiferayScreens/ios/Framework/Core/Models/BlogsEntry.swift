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

@objc(BlogsEntry)
open class BlogsEntry: Asset {

	open var blogsEntry: [String:AnyObject]? {
		return attributes["object"]?["blogsEntry"] as? [String:AnyObject]
	}

	open var blogId: Int64 {
		return int64Value("blogId") ?? 0
	}

	open var subtitle: String {
		return stringValue("subtitle") ?? ""
	}

	open var userName: String {
		return stringValue("userName") ?? ""
	}

	open var displayDate: Date? {
		guard let value = int64Value("displayDate") else {
			return nil
		}

		let timeStamp = TimeInterval(value)/1000.0
		return Date(timeIntervalSince1970: timeStamp)
	}

	open var content: String {
		return stringValue("content") ?? ""
	}

	open var userId: Int64 {
		return int64Value("userId") ?? 0
	}

	open var coverImageFileEntryId: Int64 {
		return int64Value("coverImageFileEntryId") ?? 0
	}

	// MARK: MimeTypeable

	override open var mimeType: String? {
		return "text/html"
	}

	// MARK: Private methods

	fileprivate func int64Value(_ key: String) -> Int64? {
		return blogsEntry?[key]?.int64Value
	}

	fileprivate func stringValue(_ key: String) -> String? {
		return blogsEntry?[key]?.description
	}

}
