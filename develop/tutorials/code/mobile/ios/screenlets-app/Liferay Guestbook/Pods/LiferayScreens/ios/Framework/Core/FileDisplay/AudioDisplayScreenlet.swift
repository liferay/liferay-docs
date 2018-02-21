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

/// Audio Display Screenlet displays an audio file from a Liferay instance’s Documents and Media 
///Library.
@objc(AudioDisplayScreenlet)
open class AudioDisplayScreenlet: FileDisplayScreenlet {

	// MARK: Inspectables

	/// Supported screenlet mime types. If the mime type not matches with the requested audio mime
	/// type, the audio doesn't show in the screenlet.
	@IBInspectable open var mimeTypes: String = ""

	let DefaultAudioMimeTypes = ["audio/mpeg", "audio/mpeg3", "audio/wav"]

	// MARK: FileDisplayScreenlet

	override open var supportedMimeTypes: [String] {

		return (mimeTypes.isEmpty) ? DefaultAudioMimeTypes : mimeTypes.characters.split(separator: ",")
			.map(String.init)
	}

}
