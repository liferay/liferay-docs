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

open class SaveUserInteractor: ServerConnectorInteractor {

	open var resultUserAttributes: [String:AnyObject]?

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> UpdateCurrentUserLiferayConnector {
		let screenlet = self.screenlet as! SignUpScreenlet

		return LiferayServerContext.connectorFactory.createUpdateCurrentUserConnector(
			viewModel: screenlet.viewModel)
	}

	override open func completedConnector(_ c: ServerConnector) {
		self.resultUserAttributes = (c as! UpdateCurrentUserLiferayConnector).resultUserAttributes
	}

}
