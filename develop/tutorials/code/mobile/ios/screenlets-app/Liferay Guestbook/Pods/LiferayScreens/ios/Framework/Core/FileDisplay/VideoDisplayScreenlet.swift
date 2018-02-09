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

///Video Display Screenlet displays a video file from a Liferay instance’s Documents and Media 
/// Library.
@objc(VideoDisplayScreenlet)
open class VideoDisplayScreenlet: FileDisplayScreenlet {

	// MARK: Inspectables

	/// Supported screenlet mime types. If the mime type not matches with the requested video mime
	/// type, the video doesn't show in the screenlet.
	@IBInspectable open var mimeTypes: String = ""

	let DefaultVideoMimeTypes = ["video/mp4", "video/3gp", "video/quicktime"]

	// MARK: FileDisplayScreenlet

	override open var supportedMimeTypes: [String] {

		return (mimeTypes.isEmpty) ? DefaultVideoMimeTypes : mimeTypes.characters.split(separator: ",")
			.map(String.init)
	}

}
