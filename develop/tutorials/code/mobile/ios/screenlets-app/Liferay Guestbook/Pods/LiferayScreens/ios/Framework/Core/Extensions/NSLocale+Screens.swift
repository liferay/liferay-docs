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

extension NSLocale {

	public static var currentLanguageString: String {
		get {
			var preferredLanguage = Locale.preferredLanguages[0]

			preferredLanguage = preferredLanguage.substring(
				to: preferredLanguage.characters.index(preferredLanguage.startIndex, offsetBy: 2))

			return preferredLanguage
		}
		set {
			UserDefaults.standard.set([newValue], forKey: "AppleLanguages")
			UserDefaults.standard.synchronize()
		}
	}

	public static var currentLocaleString: String {
		//FIXME Portal doesn't support any country, just few predefined ones
		let language = NSLocale.currentLanguageString

		switch language {
			case "ca", "es":
				return language + "_ES"
			case "zh":
				return "zh_CN"
			case "fi":
				return "fi_FI"
			case "fr":
				return "fr_FR"
			case "de":
				return "de_DE"
			case "iw", "he":
				return "iw_IL"
			case "hu":
				return "hu_HU"
			case "ja":
				return "ja_JP"
			case "pt":
				return "pr_BR"
			default: ()
		}

		return "en_US"
	}

	public static func bundleForLanguage(_ language: String, bundle: Bundle) -> Bundle? {
		guard let path = bundle.path(forResource: language, ofType: "lproj") else {
			return nil
		}

		return Bundle(path: path)
	}

}
