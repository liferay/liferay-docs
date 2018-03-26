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
import Kingfisher

private var lr_lastURLKey: Void?

extension UIImageView {

	public var lr_webURL: URL? {
		return objc_getAssociatedObject(self, &lr_lastURLKey) as? URL
	}

	fileprivate func lr_setWebURL(_ URL: Foundation.URL) {
		objc_setAssociatedObject(self, &lr_lastURLKey, URL, .OBJC_ASSOCIATION_RETAIN_NONATOMIC)
	}

	public func lr_setImageWithURL(
			_ URL: Foundation.URL,
			placeholderImage: Image? = nil,
			optionsInfo: KingfisherOptionsInfo? = nil) {

		var optionsInfo = optionsInfo ?? []
		optionsInfo.append(.requestModifier(AnyModifier(modify: { request -> URLRequest? in
			guard let auth = SessionContext.currentContext?.session.authentication,
				auth is LRCookieAuthentication else {

					return request
			}
			let mutableRequest = (request as NSURLRequest).mutableCopy() as! NSMutableURLRequest

			auth.authenticate(mutableRequest)

			return mutableRequest as URLRequest
		})))

		switch ImageCache.screensOfflinePolicy {

		case CacheStrategyType.remoteOnly.rawValue:
			var optionsInfoFinal = optionsInfo ?? []
			optionsInfoFinal.append(.forceRefresh)
			optionsInfoFinal.append(.transition(.fade(0.2)))

			self.kf.setImage(with: URL, placeholder: placeholderImage, options: optionsInfoFinal)

		case CacheStrategyType.remoteFirst.rawValue:
			var optionsInfoFinal = optionsInfo ?? []
			optionsInfoFinal.append(.forceRefresh)

			self.kf.setImage(with:
					URL,
					placeholder: placeholderImage,
					options: optionsInfoFinal,
					completionHandler: { (_, error, _, _) in

						if error != nil {
							var optionsInfo = optionsInfo ?? []
							optionsInfo.append(.onlyFromCache)

							self.kf.setImage(with:
								URL,
								placeholder: placeholderImage,
								options: optionsInfo)
						}
					})

		case CacheStrategyType.cacheFirst.rawValue:
			self.kf.setImage(with:URL, placeholder: placeholderImage, options: optionsInfo)

		case CacheStrategyType.cacheOnly.rawValue:
			var optionsInfoFinal = optionsInfo ?? []
			optionsInfoFinal.append(.onlyFromCache)

			self.kf.setImage(with:
				URL,
				placeholder: placeholderImage,
				options: optionsInfoFinal)

		default: break
		}
	}
}
