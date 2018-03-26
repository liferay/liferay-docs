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

extension UIButton {

	public func replaceAttributedTitle(_ title: String, forState state: UIControlState) {
		if let attributedTitle = self.attributedTitle(for: state) {
			let formattedString = NSMutableAttributedString(
					attributedString: attributedTitle)

			formattedString.replaceCharacters(
					in: NSRange(location: 0, length: formattedString.length),
					with: title)

			self.setAttributedTitle(formattedString, for: state)
		}
		else {
			self.setAttributedTitle(NSAttributedString(string: title), for: state)
		}

	}

}
