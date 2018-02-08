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

@objc(FileEntry)
open class FileEntry: Asset {

	override open var url: String {
		let obj = attributes["object"]
		let objDict = obj as! [String:AnyObject]
		let url = objDict["url"]
		return url as! String
	}

	open var fileExtension: String? {
		if let assetObject = attributes["object"] {
			if let fileEntry = assetObject["fileEntry"] as? [String : AnyObject] {
				return fileEntry["extension"] as? String
			}
		}
		return ""
	}

	open var fileEntry: [String:AnyObject] {
		return attributes["object"]!["fileEntry"] as! [String:AnyObject]
	}

	open var fileEntryId: Int64 {
		return self.fileEntry["fileEntryId"]!.int64Value
	}

}
