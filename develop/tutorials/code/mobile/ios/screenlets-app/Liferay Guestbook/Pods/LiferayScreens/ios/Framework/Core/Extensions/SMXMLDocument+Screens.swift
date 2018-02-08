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

#if LIFERAY_SCREENS_FRAMEWORK
	import SMXMLDocument
#endif

public extension SMXMLElement {

	public func deepChildWithAttribute(
			_ attributeName: String,
			value attributeValue: String) -> SMXMLElement? {

		if self.attributeNamed(attributeName) ?? "" == attributeValue {
			return self
		}

		guard let children = self.children as? [SMXMLElement] else {
			return nil
		}
		guard !children.isEmpty else {
			return nil
		}

		for child in children {
			if let foundChild = child.deepChildWithAttribute(attributeName, value: attributeValue) {
				return foundChild
			}
		}

		return nil
	}

}

public extension SMXMLDocument {

	override public func deepChildWithAttribute(
			_ attributeName: String,
			value attributeValue: String) -> SMXMLElement? {

		guard let children = self.children as? [SMXMLElement] else {
			return nil
		}
		guard !children.isEmpty else {
			return nil
		}

		for child in children {
			if let foundChild = child.deepChildWithAttribute(attributeName, value: attributeValue) {
				return foundChild
			}
		}

		return nil
	}

}
