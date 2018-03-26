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

/*
 * Delayed show and hide
 */
extension UIView {

	fileprivate var defaultHideDelay: Double {
		return 0.3
	}

	// MARK: Public methods

	public func changeVisibility(visible: Bool) {
		changeVisibility(visible: visible, delay: defaultHideDelay)
	}

	public func changeVisibility(visible: Bool, delay: Double) {
		let targetAlpha: CGFloat = visible ? 0.0 : 1.0

		if self.alpha != targetAlpha {
			if delay == 0.0 {
				self.alpha = targetAlpha
			}
			else {
				UIView.animate(withDuration: delay, animations: {
					self.alpha = targetAlpha
				})
			}
		}
	}

}
