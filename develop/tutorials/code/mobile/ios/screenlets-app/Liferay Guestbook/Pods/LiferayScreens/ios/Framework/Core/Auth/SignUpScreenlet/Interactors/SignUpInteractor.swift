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

class SignUpInteractor: ServerConnectorInteractor {

	var resultUserAttributes: [String:AnyObject]?

	// MARK: ServerConnectorInteractor

	override func createConnector() -> ServerConnector? {
		let screenlet = self.screenlet as! SignUpScreenlet

		if screenlet.anonymousApiUserName == nil || screenlet.anonymousApiPassword == nil {
			print("[ERROR] Anonymous credentials are required for this interaction\n")
			return nil
		}

		let connector = LiferayServerContext.connectorFactory.createSignUpConnector(
			viewModel: screenlet.viewModel,
			anonymousUsername: screenlet.anonymousApiUserName!,
			anonymousPassword: screenlet.anonymousApiPassword!)

		connector.companyId = screenlet.companyId

		return connector
	}

	override func completedConnector(_ c: ServerConnector) {
		self.resultUserAttributes = (c as! SignUpLiferayConnector).resultUserAttributes
	}

}
