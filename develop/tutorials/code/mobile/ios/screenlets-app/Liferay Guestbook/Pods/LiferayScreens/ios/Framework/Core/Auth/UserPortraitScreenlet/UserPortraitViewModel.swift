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

@objc(UserPortraitViewModel)
public protocol UserPortraitViewModel {

	/// User portrait image to be displayed.
	var image: UIImage? { get set }

	/// Custom border width for the user portrait.
	var borderWidth: CGFloat { get set }

	/// Custom border color for the user portrait.
	var borderColor: UIColor? { get set }

	// Specifies a custom placeholder with user info if there isn't user portrait.
	func loadPlaceholder(for user: User)
}
