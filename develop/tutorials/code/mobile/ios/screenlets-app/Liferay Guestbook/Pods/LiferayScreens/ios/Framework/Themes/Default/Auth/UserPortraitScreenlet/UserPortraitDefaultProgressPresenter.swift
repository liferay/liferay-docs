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

@objc open class UserPortraitDefaultProgressPresenter: DefaultProgressPresenter {

	fileprivate weak var spinner: UIActivityIndicatorView?

	public init(spinner: UIActivityIndicatorView) {
		self.spinner = spinner

		super.init()
	}

	override open func hideHUDFromView(_ view: UIView?, message: String?, forInteractor interactor: Interactor,
			withError error: NSError?) {

		if error != nil {
			showSpinner(false)
			super.hideHUDFromView(view, message: message, forInteractor: interactor, withError: error)
		}
		else {
			if message != nil {
				showSpinner(true)
			}
			else {
				super.hideHUDFromView(view, message: message, forInteractor: interactor, withError: error)
				showSpinner(false)
			}
		}
	}

	open override func showHUDInView(_ view: UIView, message: String?, forInteractor interactor: Interactor) {
		showSpinner(true)
	}

	fileprivate func showSpinner(_ show: Bool) {
		if self.spinner != nil && self.spinner!.isAnimating != show {
			dispatch_main {
				if show {
					self.spinner?.startAnimating()
				}
				else {
					self.spinner?.stopAnimating()
				}
			}
		}
	}

}
