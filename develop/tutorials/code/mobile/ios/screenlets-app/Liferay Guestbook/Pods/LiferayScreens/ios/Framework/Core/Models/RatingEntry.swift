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

@objc(RatingEntry)
open class RatingEntry: NSObject, NSCoding {

	open let attributes: [String:AnyObject]

	open var totalCount: Int {
		return attributes["totalCount"]! as! Int
	}

	open var average: Double {
		return attributes["average"]! as! Double
	}

	open var userScore: Double {
		return attributes["userScore"]! as! Double
	}

	open var classPK: Int64 {
		return attributes["classPK"]!.int64Value
	}

	open var className: String {
		return attributes["className"]! as! String
	}

	open var ratings: [Int] {
		return attributes["ratings"]! as! [Int]
	}

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(self.attributes, forKey:"rating-attrs")
	}

	// MARK: Initializers

	public init(attributes: [String:AnyObject]) {
		self.attributes = attributes

		super.init()
	}

	public required init?(coder aDecoder: NSCoder) {
		self.attributes = aDecoder.decodeObject(forKey: "rating-attrs") as? [String:AnyObject] ?? [:]

		super.init()
	}

}
