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

@objc(DDMFieldDocument)
open class DDMFieldDocument: DDMField {

	public enum UploadStatus: Hashable, Equatable {
		case uploaded([String:AnyObject])
		case failed(NSError?)
		case uploading(UInt64, UInt64)
		case pending

		public static func CachedStatusData(_ cacheKey: String) -> [String:AnyObject] {
			return [
				"cached": cacheKey as AnyObject,
				"mimeType": "image/png" as AnyObject]
		}

		public var hashValue: Int {
			return toInt()
		}

		fileprivate func toInt() -> Int {
			switch self {
			case .uploaded:
				return 1
			case .failed:
				return 2
			case .uploading:
				return 3
			case .pending:
				return 4
			}
		}

	}

	open var uploadStatus = UploadStatus.pending

	open var mimeType: String? {
		if cachedKey != nil {
			switch uploadStatus {
			case .uploaded(let json):
				return json["mimeType"] as? String
			default: ()
			}
		}

		switch currentValue {
		case is UIImage:
			return "image/png"
		case is URL:
			return "video/mpeg"
		case is [String:AnyObject]:
			return nil
		default:
			return nil
		}
	}

	open var cachedKey: String? {
		switch uploadStatus {
		case .uploaded(let json):
			return json["cached"] as? String
		default: ()
		}

		return nil
	}

	// MARK: DDMField

	public override init(attributes: [String:AnyObject], locale: Locale) {
		super.init(attributes: attributes, locale: locale)
	}

	public required init?(coder aDecoder: NSCoder) {
		let uploadStatusHash = aDecoder.decodeInteger(forKey: "uploadStatusHash")

		switch uploadStatusHash {
		case UploadStatus.uploaded([:]).hashValue:
			let attributes = aDecoder.decodeObject(forKey: "uploadStatusUploadedAttributes") as!  [String:AnyObject]
			uploadStatus = .uploaded(attributes)

		case UploadStatus.failed(nil).hashValue:
			let err = aDecoder.decodeObject(forKey: "uploadStatusFailedError") as! NSError
			uploadStatus = .failed(err)

		case UploadStatus.uploading(0, 0).hashValue:
			let n1 = aDecoder.decodeObject(forKey: "uploadStatusUploading1") as! NSNumber
			let n2 = aDecoder.decodeObject(forKey: "uploadStatusUploading2") as! NSNumber
			uploadStatus = .uploading(n1.uint64Value, n2.uint64Value)

		default:
			()
		}

		super.init(coder: aDecoder)
	}

	open override func encode(with aCoder: NSCoder) {
		super.encode(with: aCoder)

		aCoder.encode(uploadStatus.hashValue, forKey: "uploadStatusHash")

		switch uploadStatus {
		case .uploaded(let attributes):
			aCoder.encode(attributes, forKey: "uploadStatusUploadedAttributes")
		case .failed(let error):
			aCoder.encode(error, forKey: "uploadStatusFailedError")
		case .uploading(let n1, let n2):
			aCoder.encode(NSNumber(value: n1 as UInt64), forKey: "uploadStatusUploading1")
			aCoder.encode(NSNumber(value: n2 as UInt64), forKey: "uploadStatusUploading2")
		case .pending:
			()
		}
	}

	override internal func convert(fromString value: String?) -> AnyObject? {
		var result: AnyObject?

		if let valueString = value {
			let data = valueString.data(using: String.Encoding.utf8,
				allowLossyConversion: false)

			let jsonObject = try? JSONSerialization.jsonObject(with: data!,
				options: [])

			if let jsonDict = jsonObject as? [String:AnyObject] {
				uploadStatus = .uploaded(jsonDict)
				result = jsonDict as AnyObject?
			}
			else if valueString != "" {
				uploadStatus = .pending
				result = valueString as AnyObject?
			}
		}

		return result
	}

	override func convert(fromLabel label: String?) -> AnyObject? {
		return nil
	}

	override internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		switch uploadStatus {
		case .uploaded(let json):
			let groupEntry = json["groupId"]
			if let groupId = groupEntry?.int64Value,
					let uuid = json["uuid"] as? String,
					let version = json["version"] as? String {
				return "{\"groupId\":\(groupId)," +
						"\"uuid\":\"\(uuid)\"," +
						"\"version\":\"\(version)\"}"
			}
			else {
				let data = try? JSONSerialization.data(withJSONObject: json,
					options: [])

				if let data = data {
					return String(data: data, encoding: String.Encoding.utf8)
				}
			}

		default: ()
		}

		return nil
	}

	override func convertToLabel(fromCurrentValue value: AnyObject?) -> String? {
		switch currentValue {
		case is UIImage:
			return LocalizedString("core", key: "an-image-has-been-selected", obj: self)
		case is URL:
			return LocalizedString("core", key: "a-video-has-been-selected", obj: self)
		case is [String:AnyObject]:
			return LocalizedString("core", key: "a-file-is-already-uploaded", obj: self)
		default: ()
		}

		return nil
	}

	override internal func doValidate() -> Bool {
		var result = super.doValidate()

		if result {
			switch uploadStatus {
			case .failed:
				result = false
			default:
				result = true
			}
		}

		return result
	}

	// MARK: Public methods

	open func getStream(_ size: inout Int64) -> InputStream? {
		var result: InputStream?

		switch currentValue {
		case let image as UIImage:
			if let imageData = UIImagePNGRepresentation(image) {
				size = Int64(imageData.count)
				result = InputStream(data: imageData)
			}

		case let videoURL as URL:
			let attributes = try? FileManager.default.attributesOfItem(
					atPath: videoURL.path)
			if let sizeValue = attributes?[FileAttributeKey.size] as? NSNumber {
				size = sizeValue.int64Value
			}
			result = InputStream(url: videoURL)

		default: ()
		}

		return result
	}

}

// MARK: Equatable

public func == (
		left: DDMFieldDocument.UploadStatus,
		right: DDMFieldDocument.UploadStatus)
		-> Bool {
	return left.hashValue == right.hashValue
}
