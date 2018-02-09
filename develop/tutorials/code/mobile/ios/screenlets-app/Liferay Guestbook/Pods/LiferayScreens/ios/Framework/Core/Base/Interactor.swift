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

@objc(Interactor)
open class Interactor: NSObject {

	open var actionName: String?

	open var onSuccess: (() -> Void)?

	open var onFailure: ((NSError) -> Void)?

	open var lastError: NSError?

	open let screenlet: BaseScreenlet?

	internal var cancelled = false

	// MARK: Initializers

	public init(screenlet: BaseScreenlet?) {
		self.screenlet = screenlet

		super.init()
	}

	override public convenience init() {
		self.init(screenlet: nil)
	}

	// MARK: Public methods

	open func callOnSuccess() {
		if !cancelled {
			dispatch_main {
				self.onSuccess?()
				self.finishWithError(nil)
			}
		}
	}

	open func callOnFailure(_ error: NSError) {
		if !cancelled {
			dispatch_main {
				self.onFailure?(error)
				self.finishWithError(error)
			}
		}
	}

	open func start() -> Bool {
		cancelled = false
		return false
	}

	open func cancel() {
		callOnFailure(NSError.errorWithCause(.cancelled))
		cancelled = true
	}

	open func interactionResult() -> AnyObject? {
		return nil
	}

	// MARK: Private methods

	fileprivate func finishWithError(_ error: NSError?) {
		screenlet?.endInteractor(self, error: error)

		// break retain cycle
		onSuccess = nil
		onFailure = nil
	}

}
