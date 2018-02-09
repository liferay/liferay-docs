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

public class AssetLoadByPortletItemNameLiferayConnector: ServerConnector, LoadAssetConnector {

	public let portletItemName: String?

	public var resultAsset: Asset?

	public init(portletItemName: String) {
		self.portletItemName = portletItemName

		super.init()
	}

	// MARK: ServerConnector

	override public func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if portletItemName == "" {
				return ValidationError("assetdisplay-screenlet", "undefined-portlet-item-name")
			}
		}

		return error
	}
}

public class Liferay70AssetLoadByPortletItemNameConnector: AssetLoadByPortletItemNameLiferayConnector {

	override public func doRun(session session: LRSession) {
		resultAsset = nil

		let service = LRScreensassetentryService_v70(session: session)

		do {
			let result = try service?.getAssetEntries(withCompanyId: LiferayServerContext.companyId,
					groupId: LiferayServerContext.groupId,
					portletItemName: portletItemName!, locale: NSLocale.currentLocaleString,
			max: 1) as? [[String: AnyObject]]

			guard let assets = result, !assets.isEmpty, let asset = assets[0] as? [String: AnyObject] else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "There was an error retrieving the asset.")
				return
			}

			resultAsset = Asset(attributes: asset)

			lastError = nil
		}
		catch {
			lastError = NSError.errorWithCause(.invalidServerResponse,
					message: "There was an error retrieving the asset.")
		}
	}
}
