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

open class ForgotPasswordBaseLiferayConnector: ServerConnector {

	open var companyId: Int64 = 0

	open var resultPasswordSent: Bool?

	internal let viewModel: ForgotPasswordViewModel

	fileprivate let anonymousUsername: String
	fileprivate let anonymousPassword: String

	// MARK: Initializers

	public init(viewModel: ForgotPasswordViewModel, anonymousUsername: String, anonymousPassword: String) {
		self.viewModel = viewModel
		self.anonymousUsername = anonymousUsername
		self.anonymousPassword = anonymousPassword

		super.init()
	}

	//MARK ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if viewModel.userName == nil {
				return ValidationError("forgotpassword-screenlet", "validation")
			}
		}

		return error
	}

	override open func doRun(session: LRSession) {
		do {
			resultPasswordSent = try sendForgotPasswordRequest(session)
			lastError = nil
		}
		catch (let error as NSError) {
			lastError = error
			resultPasswordSent = nil
		}
	}

	override open func createSession() -> LRSession? {
		return SessionContext.createEphemeralBasicSession(anonymousUsername, anonymousPassword)
	}

	// MARK: Template Methods

	open func sendForgotPasswordRequest(_ session: LRSession) throws -> Bool {
		fatalError("sendForgotPasswordRequest must be overriden")
	}

}
