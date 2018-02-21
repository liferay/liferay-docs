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

#if LIFERAY_SCREENS_FRAMEWORK
	import SMXMLDocument
#endif

extension String {

	public func toSafeFilename() -> String {
		let regex = try! NSRegularExpression(
			pattern: "[^a-zA-Z0-9_]+",
			options: [])

		return regex.stringByReplacingMatches(in: self,
			options: [],
			range: NSRange(location: 0, length: self.characters.count),
			withTemplate: "-")
	}

	public var asNumber: NSNumber? {
		guard let number = Int64(self) else {
			return nil
		}

		return NSNumber(value: number)
	}

	public var isXml: Bool {
		return self.hasPrefix("<?xml")
	}

	public func asLocalized(_ locale: Locale) -> String {
		guard self.isXml else {
			return self
		}

		let data = self.data(using: .utf8)

		guard let document = try? SMXMLDocument(data: data) else {
			return self
		}

		let defaultLocale = document.attributeNamed("default-locale") ?? "en_US"

		let found =
			document.deepChildWithAttribute("language-id", value: locale.identifier)
			??
			document.deepChildWithAttribute("language-id", value: defaultLocale)

		return found?.value ?? self
	}

	public func toHtmlTextWithAttributes(_ attributes: [String: Any]) -> NSAttributedString? {

		//Init text with default paragraph style
		var text = "<style>p:last-of-type{ margin-bottom: 0px; padding-bottom: 0px; }</style>"
			+ "<div style=\""

		if let font = attributes[NSFontAttributeName] as? UIFont {
			text.append("font-family: \(font.fontName);font-size: \(font.pointSize);")
		}

		if let color = attributes[NSForegroundColorAttributeName] as? UIColor {
			text.append("color: \(self.toHexString(color));")
		}

		text.append("\">\(self)</div>")

		let encodedData = text.data(using: .utf8)

		if let data = encodedData {

			let attributes = attributes.copyAndMerge([
				NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType,
				NSCharacterEncodingDocumentAttribute: String.Encoding.utf8.rawValue
			])

			return try! NSAttributedString(data: data, options: attributes, documentAttributes: nil)
		}

		return nil
	}

	func toHexString(_ color: UIColor) -> String {
		var r: CGFloat = 0
		var g: CGFloat = 0
		var b: CGFloat = 0
		var a: CGFloat = 0

		color.getRed(&r, green: &g, blue: &b, alpha: &a)

		let rgb: Int = (Int)(r*255)<<16 | (Int)(g*255)<<8 | (Int)(b*255)<<0

		return NSString(format:"#%06x", rgb) as String
	}

	func removeFirstAndLastChars() -> String {
		if characters.count >= 2 {
			let range = characters.index(after: startIndex)..<characters.index(before: endIndex)
			return substring(with: range)
		}

		return self
	}

	func trim() -> String {
		return trimmingCharacters(
			in: .whitespacesAndNewlines)
	}

}
