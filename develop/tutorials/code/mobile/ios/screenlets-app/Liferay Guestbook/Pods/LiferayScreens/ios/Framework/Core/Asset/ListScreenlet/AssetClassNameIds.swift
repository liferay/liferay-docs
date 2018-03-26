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

// Users and sites
public let AssetClassNameKey_Group = "Group"
public let AssetClassNameKey_Layout = "Layout"
public let AssetClassNameKey_Organization = "Organization"
public let AssetClassNameKey_User = "User"
public let AssetClassNameKey_UserGroup = "UserGroup"

// Blogs
public let AssetClassNameKey_BlogsEntry = "BlogsEntry"

// Bookmarks
public let AssetClassNameKey_BookmarksEntry = "BookmarksEntry"
public let AssetClassNameKey_BookmarksFolder = "BookmarksFolder"

// Document Library
public let AssetClassNameKey_DLFileEntry = "DLFileEntry"
public let AssetClassNameKey_DLFolder = "DLFolder"
public let AssetClassNameKey_DLFileEntryMetadata = "DLFileEntryMetadata"
public let AssetClassNameKey_DLFileEntryType = "DLFileEntryType"
public let AssetClassNameKey_DLFileRank = "DLFileRank"
public let AssetClassNameKey_DLFileShortcut = "DLFileShortcut"
public let AssetClassNameKey_DLFileVersion = "DLFileVersion"

// DDL
public let AssetClassNameKey_DDLRecord = "DDLRecord"
public let AssetClassNameKey_DDLRecordSet = "DDLRecordSet"
public let AssetClassNameKey_DDLRecordVersion = "DDLRecordVersion"

// Journal
public let AssetClassNameKey_JournalArticle = "JournalArticle"
public let AssetClassNameKey_JournalArticleImage = "JournalArticleImage"
public let AssetClassNameKey_JournalFolder = "JournalFolder"

// MessageBoard
public let AssetClassNameKey_MBMessage = "MBMessage"
public let AssetClassNameKey_MBThread = "MBThread"
public let AssetClassNameKey_MBCategory = "MBCategory"
public let AssetClassNameKey_MBDiscussion = "MBDiscussion"
public let AssetClassNameKey_MBMailingList = "MBMailingList"

// Wiki
public let AssetClassNameKey_WikiPage = "WikiPage"
public let AssetClassNameKey_WikiPageResource = "WikiPageResource"
public let AssetClassNameKey_WikiNode = "WikiNode"

@objc(AssetClassEntry)
open class AssetClassEntry: NSObject {

	open let classNameId: Int64
	open let className: String

	public init(_ classNameId: Int64, _ className: String) {
		self.classNameId = classNameId
		self.className = className

		super.init()
	}

}

@objc(AssetClasses)
open class AssetClasses: NSObject {

	fileprivate static var classNameEntries: [String:AssetClassEntry] = {

		// These are the default values for a Liferay Portal 7.0 GA3
		// installation.
		// Be aware your installation probably have different identifiers, so
		// you probably we'll need to overwrite these values like this:
		//		AssetClassNameKey_s.setClassNameId(AssetClassNameKey_Group, 1234)
		return [
			// Users and sites
			AssetClassNameKey_Group: AssetClassEntry(20045, "com.liferay.portal.kernel.model.Group"),
			AssetClassNameKey_Layout: AssetClassEntry(20047, "com.liferay.portal.kernel.model.Layout"),
			AssetClassNameKey_Organization: AssetClassEntry(20059, "com.liferay.portal.kernel.model.Organization"),
			AssetClassNameKey_User: AssetClassEntry(20087, "com.liferay.portal.kernel.model.User"),
			AssetClassNameKey_UserGroup: AssetClassEntry(20088, "com.liferay.portal.kernel.model.UserGroup"),

			// Blogs
			AssetClassNameKey_BlogsEntry: AssetClassEntry(20011, "com.liferay.blogs.kernel.model.BlogsEntry"),

			// Bookmarks
			AssetClassNameKey_BookmarksEntry: AssetClassEntry(27401, "com.liferay.bookmarks.model.BookmarksEntry"),
			AssetClassNameKey_BookmarksFolder: AssetClassEntry(27402, "com.liferay.bookmarks.model.BookmarksFolder"),

			// Document Library
			AssetClassNameKey_DLFileEntry: AssetClassEntry(20015,
					"com.liferay.document.library.kernel.model.DLFileEntry"),
			AssetClassNameKey_DLFolder: AssetClassEntry(20021, "com.liferay.document.library.kernel.model.DLFolder"),
			AssetClassNameKey_DLFileEntryMetadata: AssetClassEntry(20016,
					"com.liferay.document.library.kernel.model.DLFileEntryMetadata"),
			AssetClassNameKey_DLFileEntryType: AssetClassEntry(20017,
					"com.liferay.document.library.kernel.model.DLFileEntryType"),
			AssetClassNameKey_DLFileRank: AssetClassEntry(20018,
					"com.liferay.document.library.kernel.model.DLFileRank"),
			AssetClassNameKey_DLFileShortcut: AssetClassEntry(20019,
					"com.liferay.document.library.kernel.model.DLFileShortcut"),
			AssetClassNameKey_DLFileVersion: AssetClassEntry(20020,
					"com.liferay.document.library.kernel.model.DLFileVersion"),

			// DDL
			AssetClassNameKey_DDLRecord: AssetClassEntry(29101, "com.liferay.dynamic.data.lists.model.DDLRecord"),
			AssetClassNameKey_DDLRecordSet: AssetClassEntry(29102, "com.liferay.dynamic.data.lists.model.DDLRecordSet"),
			AssetClassNameKey_DDLRecordVersion: AssetClassEntry(29103,
					"com.liferay.dynamic.data.lists.model.DDLRecordVersion"),

			// Journal
			AssetClassNameKey_JournalArticle: AssetClassEntry(29501, "com.liferay.journal.model.JournalArticle"),
			AssetClassNameKey_JournalArticleImage: AssetClassEntry(29502,
					"com.liferay.journal.model.JournalArticleImage"),
			AssetClassNameKey_JournalFolder: AssetClassEntry(29506, "com.liferay.journal.model.JournalFolder"),

			// MessageBoard
			AssetClassNameKey_MBMessage: AssetClassEntry(20032, "com.liferay.message.boards.kernel.model.MBMessage"),
			AssetClassNameKey_MBThread: AssetClassEntry(20034, "com.liferay.message.boards.kernel.model.MBThread"),
			AssetClassNameKey_MBCategory: AssetClassEntry(20029, "com.liferay.message.boards.kernel.model.MBCategory"),
			AssetClassNameKey_MBDiscussion: AssetClassEntry(20030,
					"com.liferay.message.boards.kernel.model.MBDiscussion"),
			AssetClassNameKey_MBMailingList: AssetClassEntry(20031,
					"com.liferay.message.boards.kernel.model.MBMailingList"),

			// Wiki
			AssetClassNameKey_WikiPage: AssetClassEntry(27902, "com.liferay.wiki.model.WikiPage"),
			AssetClassNameKey_WikiPageResource: AssetClassEntry(27903, "com.liferay.wiki.model.WikiPageResource"),
			AssetClassNameKey_WikiNode: AssetClassEntry(27901, "com.liferay.wiki.model.WikiNode")
		]
	}()

	open class func getClassNameId(_ key: String) -> Int64? {
		return classNameEntries[key]?.classNameId
	}

	open class func getClassName(_ key: String) -> String? {
		return classNameEntries[key]?.className
	}

	open class func getClassNameFromId(_ classNameId: Int64) -> String? {
		return classNameEntries.filter {
				$0.1.classNameId == classNameId
			}
			.first?.1.className
	}

	open class func set(_ key: String, newId: Int64) {
		if let currentEntry = classNameEntries[key] {
			classNameEntries[key] = AssetClassEntry(newId, currentEntry.className)
		}
	}

	open class func set(_ key: String, newClassName: String) {
		if let currentEntry = classNameEntries[key] {
			classNameEntries[key] = AssetClassEntry(currentEntry.classNameId, newClassName)
		}
	}
}
