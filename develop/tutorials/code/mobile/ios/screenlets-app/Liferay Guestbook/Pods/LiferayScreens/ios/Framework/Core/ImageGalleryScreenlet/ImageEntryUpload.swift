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

@objc(ImageEntryUpload)
open class ImageEntryUpload: NSObject, NSCoding {

	open let image: UIImage
	open let thumbnail: UIImage?

	open let title: String
	open let notes: String

	public init(image: UIImage, thumbnail: UIImage? = nil, title: String, notes: String = "") {
		self.image = image
		self.thumbnail = thumbnail
		self.title = title
		self.notes = notes
	}

	// MARK: NSCoding

	public required init?(coder aDecoder: NSCoder) {
		image = (aDecoder.decodeObject(forKey: "image") as? UIImage)!
		thumbnail = aDecoder.decodeObject(forKey: "thumbnail") as? UIImage
		title = aDecoder.decodeObject(forKey: "title") as! String
		notes = aDecoder.decodeObject(forKey: "notes") as! String
		super.init()
	}

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(image, forKey: "image")

		if let thumbnail = thumbnail {
			aCoder.encode(thumbnail, forKey: "thumbnail")
		}

		aCoder.encode(title, forKey: "title")
		aCoder.encode(notes, forKey: "notes")
	}

}
