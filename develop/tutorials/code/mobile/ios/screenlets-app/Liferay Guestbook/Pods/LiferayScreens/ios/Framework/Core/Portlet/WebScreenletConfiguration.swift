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

@objc public enum WebType: Int {
	case liferayAuthenticated, other
}

@objc(WebScreenletConfiguration)
public class WebScreenletConfiguration: NSObject {
	public let url: String
	public let scripts: [InjectableScript]
	public let isCordovaEnabled: Bool
	public let webType: WebType

	public init(url: String, scripts: [InjectableScript], isCordovaEnabled: Bool, webType: WebType) {
		self.url = url
		self.scripts = scripts
		self.isCordovaEnabled = isCordovaEnabled
		self.webType = webType
	}
}

@objc(WebScreenletConfigurationBuilder)
public class WebScreenletConfigurationBuilder: NSObject {
	let url: String
	var localJs: [String]
	var localCss: [String]
	var remoteJs: [String]
	var remoteCss: [String]
	var isCordovaEnabled: Bool
	var webType: WebType

	public init(url: String) {
		self.url = url
		self.localJs = []
		self.localCss = []
		self.remoteJs = []
		self.remoteCss = []
		self.isCordovaEnabled = false
		self.webType = .liferayAuthenticated
	}

	public func addJs(localFile: String) -> Self {
		self.localJs.append(localFile)
		return self
	}

	public func addCss(localFile: String) -> Self {
		self.localCss.append(localFile)
		return self
	}

	public func addJs(url: String) -> Self {
		self.remoteJs.append(url)
		return self
	}

	public func addCss(url: String) -> Self {
		self.remoteCss.append(url)
		return self
	}

	public func enableCordova() -> Self {
		self.isCordovaEnabled = true
		return self
	}

	public func set(webType: WebType) -> Self {
		self.webType = webType
		return self
	}

	public func load() -> WebScreenletConfiguration {
		let localJsScripts: [InjectableScript] = localJs
			.map { fileName in JsScript(name: "\(fileName).js", js: loadLocalJsContent(fileName: fileName)) }

		let localCssScripts: [InjectableScript] = localCss
			.map { fileName in CssScript(name: "\(fileName).css", css: loadLocalCssContent(fileName: fileName)) }

		let remoteJsScripts: [InjectableScript] = remoteJs
			.map { url in RemoteJsScript(name: "Remote js url: \(url)", url: url) }

		let remoteCssScripts: [InjectableScript] = remoteCss
			.map { url in RemoteCssScript(name: "Remote css url: \(url)", url: url) }

		let allScripts: [InjectableScript] = localJsScripts + localCssScripts + remoteJsScripts + remoteCssScripts

		return WebScreenletConfiguration(url: url, scripts: allScripts,
		                                 isCordovaEnabled: isCordovaEnabled, webType: webType)
	}

	private func loadLocalCssContent(fileName: String) -> String {
		guard let content = Bundle.loadFile(name: fileName, ofType: "css", currentClass: type(of: self))
		else {
			print("file named \(fileName) not found")
			return ""
		}

		return content
	}

	private func loadLocalJsContent(fileName: String) -> String {
		guard let content = Bundle.loadFile(name: fileName, ofType: "js", currentClass: type(of: self))
		else {
			print("file named \(fileName) not qfound")
			return ""
		}

		return content
	}
}
