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

@objc open class NetworkActivityIndicatorPresenter: NSObject, ProgressPresenter {
	static var numberOfVisibilityCalls = 0

	open func showHUDInView(_ view: UIView, message: String?, forInteractor interactor: Interactor) {
		setNetworkActivityIndicatorVisible(true)
	}

	open func hideHUDFromView(_ view: UIView?, message: String?, forInteractor interactor: Interactor,
			withError error: NSError?) {

		setNetworkActivityIndicatorVisible(false)
	}

	fileprivate func setNetworkActivityIndicatorVisible(_ visible: Bool) {
		NetworkActivityIndicatorPresenter.numberOfVisibilityCalls += visible ? 1 : -1
		UIApplication.shared.isNetworkActivityIndicatorVisible =
				NetworkActivityIndicatorPresenter.numberOfVisibilityCalls > 0
	}
}
