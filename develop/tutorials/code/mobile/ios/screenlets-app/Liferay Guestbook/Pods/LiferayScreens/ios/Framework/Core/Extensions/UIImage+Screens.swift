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

extension UIImage {

	public func resizeImage(toWidth width: Int, completion: @escaping (UIImage?) -> Void) {
		dispatch_async {
			let newImage = self.resizeImage(toWidth: width)
			dispatch_main {
				completion(newImage)
			}
		}
	}

	public func resizeImage(toWidth width: Int) -> UIImage? {
		let oldWidth = self.size.width
		let scaleFactor = CGFloat(width) / oldWidth

		let newHeight = self.size.height * scaleFactor
		let newWidth = CGFloat(width)

		UIGraphicsBeginImageContext(CGSize(width: newWidth, height: newHeight))
		self.draw(in: CGRect(x: 0, y: 0, width: newWidth, height: newHeight))
		let newImage = UIGraphicsGetImageFromCurrentImageContext()
		UIGraphicsEndImageContext()

		return newImage
	}
}
