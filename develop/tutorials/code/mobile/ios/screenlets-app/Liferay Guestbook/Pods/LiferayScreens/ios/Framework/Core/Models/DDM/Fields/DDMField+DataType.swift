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

extension DDMField {

	public enum DataType: String {

		case DDMBoolean = "boolean"
		case DDMString = "string"
		case DDMDate = "date"
		case DDMInteger = "integer"
		case DDMNumber = "number"
		case DDMDouble = "double"
		case DDMDocument = "document-library"
		case DDMImage = "image"
		case Unsupported = ""

		public static func from(xmlElement: SMXMLElement) -> DataType {
			return DataType(rawValue: xmlElement.attributeNamed("dataType") ?? "") ?? .Unsupported
		}

		public static func from(json: JSONObject) -> DataType {
			return DataType(rawValue: (json["dataType"] as? String) ?? "") ?? .Unsupported
		}

		// swiftlint:disable cyclomatic_complexity
		public func createField(
				attributes: [String:AnyObject],
				locale: Locale,
				version: LiferayServerVersion)
				-> DDMField? {

			switch self {
			case .DDMBoolean:
				return DDMFieldBoolean(
						attributes:attributes,
						locale: locale)

			case .DDMString:
				switch DDMField.Editor.from(attributes: attributes) {
					case .Select, .Radio:
						return DDMFieldStringWithOptions(
								attributes: attributes,
								locale: locale)
					default:
						return DDMFieldString(
								attributes:attributes,
								locale: locale)
				}

			case .DDMDate:
				switch version {
				case .v62:
					return DDMFieldDate_v62(
						attributes:attributes,
						locale: locale)
				case .v70:
					return DDMFieldDate_v70(
						attributes:attributes,
						locale: locale)
				}

			case .DDMInteger, .DDMNumber, .DDMDouble:
				return DDMFieldNumber(
						attributes:attributes,
						locale: locale)

			case .DDMDocument:
				return DDMFieldDocument(
						attributes:attributes,
						locale: locale)

			case .DDMImage:
				return DDMFieldImage(
					attributes:attributes,
					locale: locale)

			default: ()
			}

			return nil
		}

	}
	// swiftlint:enable cyclomatic_complexity
}
