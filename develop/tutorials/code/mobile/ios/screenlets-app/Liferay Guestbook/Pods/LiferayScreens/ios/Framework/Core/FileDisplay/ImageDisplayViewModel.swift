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

@objc(ImageDisplayViewModel)
public protocol ImageDisplayViewModel: FileDisplayViewModel {

	/// Image mode for the screenlet image. See UIViewContentMode enum for
	/// knowing all the posible modes.
	var imageMode: UIViewContentMode { get set }

	/// Placeholder image to show before the asset image or if the asset not exists.
	var placeholder: UIImage? { get set }

	/// Image mode for the placeholder image.
	var placeholderImageMode: UIViewContentMode { get set }
}
