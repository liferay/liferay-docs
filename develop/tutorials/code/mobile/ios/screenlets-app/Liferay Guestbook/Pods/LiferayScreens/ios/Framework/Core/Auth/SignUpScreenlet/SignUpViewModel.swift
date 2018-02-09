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

@objc(SignUpViewModel)
public protocol SignUpViewModel {

	/// User email address.
	var emailAddress: String? { get set }

	// User screen name.
	var screenName: String? { get set }

	/// User password.
	var password: String? { get set }

	/// User first name.
	var firstName: String? { get set }

	/// User middle name.
	var middleName: String? { get set }

	/// User last name.
	var lastName: String? { get set }

	/// User job title.
	var jobTitle: String? { get set }

	/// Edit current session user.
	var editCurrentUser: Bool { get set }

}
