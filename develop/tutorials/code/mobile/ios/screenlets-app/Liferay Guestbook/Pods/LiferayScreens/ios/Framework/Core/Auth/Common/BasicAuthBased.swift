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

@objc(AnonymousBasicAuthType)
public protocol AnonymousBasicAuthType {

	var anonymousApiUserName: String? { get set }
	var anonymousApiPassword: String? { get set }

}

@objc(BasicAuthBasedType)
public protocol BasicAuthBasedType {

	var basicAuthMethod: String? { get set }

}

public enum BasicAuthMethod: String {

	case email
	case screenName
	case userId

	public static func all() -> [BasicAuthMethod] {
		return [.email, .screenName, .userId]
	}

	public static func create(_ text: String?) -> BasicAuthMethod {
		return all().filter {
				$0.rawValue.lowercased() == text?.lowercased()
			}.first ?? .email
	}

	public static func fromUserName(_ userName: String) -> BasicAuthMethod {
		if userName.characters.index(of: "@") != nil {
			return .email
		}

		if Int(userName) != nil {
			return .userId
		}

		return .screenName
	}

	public var iconType: String {
		let iconTypes: [BasicAuthMethod:String] = [
				.email: "mail",
				.screenName: "user",
				.userId: "user"]

		return iconTypes[self] ?? ""
	}

	public var keyboardType: UIKeyboardType {
		let keyboardTypes: [BasicAuthMethod:UIKeyboardType] = [
				.email: .emailAddress,
				.screenName: .asciiCapable,
				.userId: .numberPad]

		return keyboardTypes[self] ?? .default
	}

	public var description: String {
		let descriptions: [BasicAuthMethod : String] = [
			.email: "auth-method-email",
			.screenName: "auth-method-screenname",
			.userId: "auth-method-userid"]

		return descriptions[self] ?? ""
	}

}
