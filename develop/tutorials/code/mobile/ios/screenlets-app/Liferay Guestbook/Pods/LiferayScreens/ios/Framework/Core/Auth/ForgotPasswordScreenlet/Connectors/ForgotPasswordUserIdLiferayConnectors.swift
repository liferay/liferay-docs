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
import LRMobileSDK

open class ForgotPasswordUserIdLiferay62Connector: ForgotPasswordBaseLiferayConnector {

	// MARK: ForgotPasswordBaseLiferayConnector

	override open func sendForgotPasswordRequest(_ session: LRSession) throws -> Bool {

		let userId = viewModel.userName!.asNumber!.int64Value

		let service = LRScreensuserService_v62(session: session)

		// TODO change plugin service to return integer code instead of boolean.
		// Xcode transpiler is messing it up.
		try service?.sendPasswordByUserId(withUserId: userId)

		return true
	}

}

open class ForgotPasswordUserIdLiferay70Connector: ForgotPasswordBaseLiferayConnector {

	// MARK: ForgotPasswordBaseLiferayConnector

	override open func sendForgotPasswordRequest(_ session: LRSession) throws -> Bool {

		let userId = Int64(Int(viewModel.userName!)!)

		let service = LRUserService_v7(session: session)

		// TODO change plugin service to return integer code instead of boolean.
		// Xcode transpiler is messing it up.
		try service?.sendPasswordByUserId(withUserId: userId)

		return true
	}

}
