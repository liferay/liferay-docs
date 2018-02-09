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

@objc(InjectableScript)
public protocol InjectableScript {
	var name: String { get }
	var content: String { get }
}

public class BaseScript: InjectableScript {
	public let name: String
	public let content: String

	fileprivate init(name: String, content: String) {
		self.name = name
		self.content = content
	}
}

public class JsScript: BaseScript {

	public init(name: String, js: String) {
		super.init(name: name, content: js)
	}
}

public class CssScript: BaseScript {

	public init(name: String, css: String) {
		let content = "var style = document.createElement('style');"
			+ "style.type = 'text/css';"
			+ "style.innerHTML = '\(css.replacingOccurrences(of: "\n", with: ""))';"
			+ "var head = document.getElementsByTagName('head')[0];"
			+ "head.appendChild(style);"

		super.init(name: name, content: content)
	}
}

public class RemoteJsScript: BaseScript {

	public init(name: String, url: String) {
		let content = "var script = document.createElement('script');"
			+ "script.language = 'javascript';"
			+ "script.type = 'text/javascript';"
			+ "script.src = '\(url)';"
			+ "var body = document.getElementsByTagName('body')[0];"
			+ "body.appendChild(script);"

		super.init(name: name, content: content)
	}
}

public class RemoteCssScript: BaseScript {

	public init(name: String, url: String) {
		let content = "var link = document.createElement('link');"
			+ "link.type = 'text/css';"
			+ "link.rel = 'stylesheet';"
			+ "link.href = '\(url)';"
			+ "var head = document.getElementsByTagName('head')[0];"
			+ "head.appendChild(link);"

		super.init(name: name, content: content)
	}
}
