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

extension Bundle {

	public class func allBundles(_ currentClass: AnyClass) -> [Bundle] {
		let bundles =
			[
				discoverBundles(),
				bundlesForDefaultTheme(),
				bundlesForCore(),
				bundlesForApp(),
				[Bundle(for: currentClass)]
			]
			.flatMap { $0 }

		return bundles.reduce([]) { ac, x in
			ac.contains(x) ? ac : ac + [x]
		}
	}

	public class func discoverBundles() -> [Bundle] {
		let allBundles = Bundle.allFrameworks

		return allBundles.filter {
			let screensPrefix = "LiferayScreens"
			let bundleName = (($0.bundleIdentifier ?? "") as NSString).pathExtension

			return bundleName.characters.count > screensPrefix.characters.count
					&& bundleName.hasPrefix(screensPrefix)
		}
	}

	// MARK: bundlesForX methods

	public class func bundlesForDefaultTheme() -> [Bundle] {
		return [bundleForName("LiferayScreens-default"), bundleForName("LiferayScreens-ee-default")]
	}

	public class func bundlesForCore() -> [Bundle] {
		return [bundleForName("LiferayScreens-core"), bundleForName("LiferayScreens-ee-core")]
	}

	public class func bundleForName(_ name: String) -> Bundle {
		let frameworkBundle = Bundle(for: BaseScreenlet.self)

		let bundlePath = frameworkBundle.path(forResource: name, ofType: "bundle")

		// In test environment, separated bundles don't exist.
		// In such case, the frameworkBundle is used
		return (bundlePath != nil) ? Bundle(path: bundlePath!)! : frameworkBundle
	}

	public class func bundlesForApp() -> [Bundle] {

		func appFile(_ path: String) -> String? {
			let files = try? FileManager.default.contentsOfDirectory(atPath: path)
			return (files ?? []).filter {
					($0 as NSString).pathExtension == "app"
				}
				.first
		}

		let components = ((Bundle.main.resourcePath ?? "") as NSString).pathComponents

		if components.last == "Overlays" {
			// running into IB
			let coreBundle = bundlesForCore()[0]

			if let range = coreBundle.resourcePath?.range(of: "Debug-iphonesimulator"),
					let path = coreBundle.resourcePath?.substring(to: range.upperBound),
					let appName = appFile(path),
					let appBundle = Bundle(path: (path as NSString).appendingPathComponent(appName)) {
				return [Bundle.main, appBundle]
			}
		}

		return [Bundle.main]
	}

	public class func bundleForNibName(_ name: String, currentClass: AnyClass) -> Bundle? {
		return Bundle.allBundles(currentClass)
			.filter {
				$0.path(forResource: name, ofType:"nib") != nil
			}
			.first
	}

	// MARK: xInBundles methods

	public class func imageInBundles(name: String, currentClass: AnyClass) -> UIImage? {
		for bundle in allBundles(currentClass) {
			if let path = bundle.path(forResource: name, ofType: "png") {
				return UIImage(contentsOfFile: path)
			}
		}

		return nil
	}

    public class func nibInBundles(name: String, currentClass: AnyClass) -> UINib? {
        return resourceInBundle(
            	name: name,
            	ofType: "nib",
            	currentClass: currentClass) { _, bundle in
			return UINib(nibName: name, bundle: bundle)
        }
    }

	// MARK: xforTheme methods

	public class func viewForThemeOrDefault(
			name: String,
			themeName: String,
			currentClass: AnyClass) -> UIView? {

		return rootNibObjectForThemeOrDefault(
			name: name,
			themeName: themeName,
			currentClass: currentClass) as? UIView
	}

	public class func viewForTheme(
			name: String,
			themeName: String,
			currentClass: AnyClass) -> UIView? {

		return rootNibObjectForTheme(
			name: name,
			themeName: themeName,
			currentClass: currentClass) as? UIView
	}

	public class func rootNibObjectForThemeOrDefault(
			name: String,
			themeName: String,
			currentClass: AnyClass) -> AnyObject? {

		if let foundObject = Bundle.rootNibObjectForTheme(
				name: name,
				themeName: themeName,
				currentClass: currentClass) {

			return foundObject
		}

		if themeName == BaseScreenlet.DefaultThemeName {
			return nil
		}

		if let foundObject = Bundle.rootNibObjectForTheme(
				name: name,
				themeName: BaseScreenlet.DefaultThemeName,
				currentClass: currentClass) {

			return foundObject
		}

		return nil
	}

	public class func rootNibObjectForTheme(
			name: String,
			themeName: String,
			currentClass: AnyClass) -> AnyObject? {

		let nibName = "\(name)_\(themeName)"
		return resourceInBundle(
				name: nibName,
				ofType: "nib",
				currentClass: currentClass) {_, bundle in

			let objects = bundle.loadNibNamed(nibName, owner: currentClass, options: nil)

			assert(objects == nil || !objects!.isEmpty, "Malformed xib \(nibName). Without objects")

			return objects![0] as AnyObject?
		}
	}

    public class func resourceInBundle<T>(
			name: String,
			ofType type: String,
			currentClass: AnyClass,
			resourceInit: (String, Bundle) -> T?) -> T? {

        for bundle in allBundles(currentClass) {
            if let path = bundle.path(forResource: name, ofType: type) {
                return resourceInit(path, bundle)
            }
        }

        return nil
    }

	public class func loadFile(name: String, ofType type: String, currentClass: AnyClass) -> String? {
		let content = resourceInBundle(name: name, ofType: type, currentClass: currentClass) { (path, _) in
			return try? String(contentsOfFile: path, encoding: String.Encoding.utf8)
		}

		return content
	}

}
