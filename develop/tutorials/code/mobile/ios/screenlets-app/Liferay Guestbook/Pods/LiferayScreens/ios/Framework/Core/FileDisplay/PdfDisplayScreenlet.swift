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

/// PDF Display Screenlet displays a PDF file from a Liferay Instance’s Documents and Media Library.
@objc(PdfDisplayScreenlet)
open class PdfDisplayScreenlet: FileDisplayScreenlet {

	// MARK: Inspectables

	/// Supported screenlet mime types. If the mime type not matches with the requested pdf mime
	/// type, the pdf doesn't show in the screenlet.
	@IBInspectable open var mimeTypes: String = ""

	let DefaultPdfMimeTypes = ["application/pdf"]

	// MARK: FileDisplayScreenlet

	override open var supportedMimeTypes: [String] {

		return (mimeTypes.isEmpty) ? DefaultPdfMimeTypes :
				mimeTypes.characters.split(separator: ",").map(String.init)
	}

}
