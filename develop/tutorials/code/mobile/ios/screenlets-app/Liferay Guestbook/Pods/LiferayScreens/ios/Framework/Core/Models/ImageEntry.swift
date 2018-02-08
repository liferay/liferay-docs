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

@objc(ImageEntry)
open class ImageEntry: Asset {

	open var image: UIImage?

    open var thumbnailUrl: String {
        return createThumbnailUrl()
    }

	open var imageUrl: String {
		return createImageUrl()
	}

	open var imageEntryId: Int64 {
		return attributes["fileEntryId"]?.int64Value ?? 0
	}

	// MARK: Initializers

    override public init(attributes: [String:AnyObject]) {
        super.init(attributes: attributes)
		image = attributes["image"] as? UIImage
    }

    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

	// MARK: Private methods

    fileprivate func createThumbnailUrl() -> String {
		guard let version = attributes["version"]
		else {
			return ""
		}
        return "\(createImageUrl())?version=\(version)&imageThumbnail=1"
    }

    fileprivate func createImageUrl() -> String {
		guard let groupId = attributes["groupId"], let folderId = attributes["folderId"],
				let uuid = attributes["uuid"]
		else {
			return ""
		}
        return "\(LiferayServerContext.server)/documents/\(groupId)/" +
            "\(folderId)/\(encodeUrlString(title))/\(uuid)"
    }

    fileprivate func encodeUrlString(_ originalString: String) -> String {
        return originalString.addingPercentEncoding(
            withAllowedCharacters: .urlHostAllowed) ?? ""
    }

}

// MARK: Equatable

public func == (lhs: ImageEntry, rhs: ImageEntry) -> Bool {
	return lhs.imageEntryId == rhs.imageEntryId
}
