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

@objc public enum LiferayServerVersion: Int {

	case v62 = 62
	case v70 = 70

}

@objc(LiferayServerContext)
open class LiferayServerContext: NSObject {

	// MARK: Singleton type

	fileprivate struct StaticInstance {
		static var serverProperties: NSMutableDictionary?
	}

	// MARK: Public properties

	open class var server: String {
		get {
			loadContextFile()
			return StaticInstance.serverProperties!["server"] as! String
		}
		set {
			loadContextFile()
			StaticInstance.serverProperties!["server"] = newValue
		}
	}

	open class var serverVersion: LiferayServerVersion {
		get {
			loadContextFile()
			let value = StaticInstance.serverProperties?["version"]
			let version =  (value as? Int) ?? Int((value as? String) ?? "")
			return LiferayServerVersion(
				rawValue: version ?? LiferayServerVersion.v70.rawValue)
					?? .v70
		}
		set {
			loadContextFile()
			StaticInstance.serverProperties!["version"] = newValue.rawValue
		}
	}

	open class var companyId: Int64 {
		get {
			loadContextFile()
			return (StaticInstance.serverProperties!["companyId"] as! NSNumber).int64Value
		}
		set {
			loadContextFile()
			StaticInstance.serverProperties!["companyId"] = NSNumber(value: newValue)
		}
	}

	open class var groupId: Int64 {
		get {
			loadContextFile()
			return (StaticInstance.serverProperties!["groupId"] as! NSNumber).int64Value
		}
		set {
			loadContextFile()
			StaticInstance.serverProperties!["groupId"] = NSNumber(value: newValue)
		}
	}

	open class var factory: ScreensFactory {
		get {
			loadContextFile()
			return StaticInstance.serverProperties!["factory"] as! ScreensFactory
		}
		set {
			loadContextFile()
			StaticInstance.serverProperties!["factory"] = newValue
		}
	}

	open class var connectorFactory: LiferayConnectorFactory {
		get {
			loadContextFile()
			return StaticInstance.serverProperties!["connectorFactory"] as! LiferayConnectorFactory
		}
		set {
			loadContextFile()
			StaticInstance.serverProperties!["connectorFactory"] = newValue
		}
	}

	// MARK: Public methods

	open class func setPropertyValue(_ value: AnyObject, forKey key: String) {
		loadContextFile()
		return StaticInstance.serverProperties![key] = value
	}

	open class func propertyForKey(_ key: String) -> AnyObject {
		loadContextFile()

		guard let value = StaticInstance.serverProperties?[key] else {
			fatalError("Missing key \(key) on liferay-server-context.plist file")
		}

		return value as AnyObject
	}

	open class func numberPropertyForKey(_ key: String) -> NSNumber {
		guard let value = propertyForKey(key) as? NSNumber else {
			fatalError("Key \(key) is not a NSNumber")
		}

		return value
	}

	open class func longPropertyForKey(_ key: String) -> Int64 {
		return numberPropertyForKey(key).int64Value
	}

	open class func intPropertyForKey(_ key: String) -> Int {
		return numberPropertyForKey(key).intValue
	}

	open class func booleanPropertyForKey(_ key: String) -> Bool {
		guard let value = propertyForKey(key) as? Bool else {
			fatalError("Key \(key) is not a Boolean")
		}

		return value
	}

	open class func datePropertyForKey(_ key: String) -> Date {
		guard let value = propertyForKey(key) as? Date else {
			fatalError("Key \(key) is not a NSDate")
		}

		return value
	}

	open class func stringPropertyForKey(_ key: String) -> String {
		guard let value = propertyForKey(key) as? String else {
			fatalError("Key \(key) is not a String")
		}

		return value
	}

	// MARK: Private methods

	fileprivate class func loadContextFile() {

		func createFactory() {
			guard let className = StaticInstance.serverProperties?["factory"] as? String else {
				StaticInstance.serverProperties!["factory"] = ScreensFactoryImpl()
				return
			}
			guard let factoryInstance = dynamicInit(className) as? ScreensFactory else {
				StaticInstance.serverProperties!["factory"] = ScreensFactoryImpl()
				return
			}

			StaticInstance.serverProperties!["factory"] = factoryInstance
		}

		func createConnectorFactory() {

			func createDynamicConnectorFactory() -> LiferayConnectorFactory? {
				guard let className = StaticInstance.serverProperties?["connectorFactoryClassName"] as? String else {
					return nil
				}

				return dynamicInit(className) as? LiferayConnectorFactory
			}

			func createVersionConnectorFactory() -> LiferayConnectorFactory {
				switch self.serverVersion {
				case .v62:
					return Liferay62ConnectorFactory()
				case .v70:
					return Liferay70ConnectorFactory()
				}
			}

			StaticInstance.serverProperties!["connectorFactory"] =
				createDynamicConnectorFactory() ?? createVersionConnectorFactory()
		}

		guard StaticInstance.serverProperties == nil else {
			return
		}

		let bundles = Array(Bundle.allBundles(self).reversed())

		var found = false
		var foundFallback = false

		var i = 0
		let length = bundles.count

		while !found && i < length {
			let bundle = bundles[i]
			i += 1

			if let path = bundle.path(forResource: PlistFile, ofType:"plist") {
				StaticInstance.serverProperties = NSMutableDictionary(contentsOfFile: path)
				createFactory()
				createConnectorFactory()
				found = true
			}
			else {
				if let path = bundle.path(forResource: PlistFileSample, ofType:"plist") {
					StaticInstance.serverProperties = NSMutableDictionary(contentsOfFile: path)
					createFactory()
					createConnectorFactory()
					foundFallback = true
				}
				else {
					StaticInstance.serverProperties = [
						"companyId": 10157,
						"groupId": 10184,
						"server": "http://localhost:8080",
						"version": LiferayServerVersion.v70.rawValue
					]
					createFactory()
					createConnectorFactory()
				}
			}
		}

		if found {
			// everything is ok
		}
		else {
			if foundFallback {
				print("WARNING: \(PlistFile).plist file is not found. " +
						"Falling back to template \(PlistFileSample).list\n")
			}
			else {
				print("ERROR: \(PlistFileSample).plist file is not found. " +
						"Using default values which will work in a default Liferay bundle " +
						"running on localhost:8080\n")
			}
		}
	}

}

private let PlistFile = "liferay-server-context"
private let PlistFileSample = "liferay-server-context-sample"
