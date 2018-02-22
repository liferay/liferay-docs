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

public extension Dictionary {

	public func copyAndAdd(_ key: Key, value: Value) -> [Key: Value] {
		var result = self

		result.updateValue(value, forKey: key)

		return result
	}

	public func copyAndRemove(_ key: Key) -> [Key: Value] {
		var result = self

		result.removeValue(forKey: key)

		return result
	}

	public func copyAndRemove(_ keys: [Key]) -> [Key: Value] {
		var result = self

		result.removeValueForKeys(keys)

		return result
	}

	public func copyAndMerge(_ otherDict: [Key: Value]) -> [Key: Value] {
		var result = self

		result.merge(otherDict)

		return result
	}

	public mutating func merge(_ otherDict: [Key: Value]) {
		for (k, v) in otherDict {
			self.updateValue(v, forKey: k)
		}
	}

	public mutating func removeValueForKeys(_ keys: [Key]) {
		for (k, _) in self {
			self.removeValue(forKey: k)
		}
	}

}
