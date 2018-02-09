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

@objc(WebContent)
open class WebContent: Asset {

	open let structure: DDMStructure?
	open let structuredRecord: DDLRecord?
	open let html: String?

	override open var debugDescription: String {
		if let structuredRecord = structuredRecord {
			return "structuredRecord(\(structuredRecord.debugDescription))"
		}
		else if let html = html {
			return "html(\(html))"
		}

		return super.debugDescription
	}

	// MARK: Public methods

	open class func isWebContentClassName(_ className: String) -> Bool {
		return className.hasPrefix("com.liferay.") && className.hasSuffix(".JournalArticle")
	}

	// MARK: Initializers

	override public init(attributes: [String:AnyObject]) {
		func loadStructuredRecord(_ content: String, _ attributes: [String:AnyObject]) -> DDLRecord? {
			let record = DDLRecord(data: [:], attributes: attributes)
			record.updateCurrentValues(xmlValues: content)

			return record.structure == nil ? nil : record
		}

		func loadHtml(_ content: String) -> String? {
			return content.asLocalized(Locale(identifier: NSLocale.currentLocaleString))
		}

		let newAttributes: [String:AnyObject]

		if let className = attributes["className"] as? String,
			let object = attributes["object"] as? [String:AnyObject],
			let modelAttributes = object["modelAttributes"] as? [String:AnyObject],
			let modelValues = object["modelValues"] as? String, WebContent.isWebContentClassName(className) {
			newAttributes = attributes.copyAndMerge(modelAttributes).copyAndRemove("object")

			if let structureData = object["DDMStructure"] as? [String:AnyObject] {
				// structured web content
				html = nil

				self.structure = DDMStructure(
					structureData: structureData,
					locale: Locale(identifier: NSLocale.currentLocaleString))

				if let structure = self.structure {
					structuredRecord = DDLRecord(structure: structure)
					if let structuredRecord = self.structuredRecord {
						if modelValues.isXml {
							structuredRecord.updateCurrentValues(xmlValues: modelValues)
						}
						else {
							print("ERROR: Structured web content's values should always be returned in xml")
							structuredRecord.clearValues()
						}
						structuredRecord.attributes = modelAttributes
					}
				}
				else {
					structuredRecord = nil
				}
			}
			else {
				// basic web content
				html = loadHtml(modelValues)

				structuredRecord = nil
				structure = nil
			}
		}
		else if let content = attributes["content"] as? String {
			newAttributes = attributes.copyAndRemove("content")

			structuredRecord = loadStructuredRecord(content, newAttributes)
			structure = structuredRecord?.structure

			html = (structuredRecord == nil) ? loadHtml(content) : nil
		}
		else {
			newAttributes = attributes

			structuredRecord = nil
			structure = nil
			html = nil
		}

		super.init(attributes: newAttributes)
	}

	public required init?(coder aDecoder: NSCoder) {
		self.structure = aDecoder.decodeObject(forKey: "webcontent-structure") as? DDMStructure
		self.structuredRecord = aDecoder.decodeObject(forKey: "webcontent-structuredRecord") as? DDLRecord
		self.html = aDecoder.decodeObject(forKey: "webcontent-html") as? String

		super.init(coder: aDecoder)
	}

	// MARK: Asset

	override open func encode(with aCoder: NSCoder) {
		if let structure = self.structure {
			aCoder.encode(structure, forKey:"webcontent-structure")
		}
		if let structuredRecord = self.structuredRecord {
			aCoder.encode(structuredRecord, forKey:"webcontent-structuredRecord")
		}
		if let html = self.html {
			aCoder.encode(html, forKey:"webcontent-html")
		}
	}

}
