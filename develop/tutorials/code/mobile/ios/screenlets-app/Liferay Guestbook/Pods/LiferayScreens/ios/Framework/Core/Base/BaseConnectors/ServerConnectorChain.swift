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

@objc open class ServerConnectorChain: ServerConnector {

	fileprivate struct StreamConnectorsQueue {

		static fileprivate var queue: OperationQueue?

		static func addConnector(_ connector: Operation) {
			if queue == nil {
				queue = OperationQueue()
				queue!.maxConcurrentOperationCount = 1
			}

			queue!.addOperation(connector)
		}

	}

	open var onNextStep: ((ServerConnector, Int) -> ServerConnector?)?

	open let headConnector: ServerConnector
	open var currentConnector: ServerConnector

	// MARK: Initializers

	public init(head: ServerConnector) {
		headConnector = head
		currentConnector = head

		super.init()
	}

	// MARK: ServerConnector

	override open func createSession() -> LRSession? {
		return headConnector.createSession()
	}

	override open func enqueue(_ onComplete: ((ServerConnector) -> Void)?) {
		if onComplete != nil {
			self.onComplete = onComplete
		}

		StreamConnectorsQueue.addConnector(self)
	}

	override open func doRun(session: LRSession) {
		let waitGroup = DispatchGroup()

		waitGroup.enter()

		if let validationError = doStep(0, headConnector, waitGroup) {
			self.lastError = validationError
		}

		_ = waitGroup.wait(timeout: .distantFuture)
	}

	override open func callOnComplete() {
		super.callOnComplete()

		self.onNextStep = nil
	}

	// MARK: Private methods

	fileprivate func doStep(
		_ number: Int,
		_ c: ServerConnector,
		  _ waitGroup: DispatchGroup) -> ValidationError? {

		let originalCallback = c.onComplete

		return c.validateAndEnqueue { connector in
			self.lastError = connector.lastError ?? self.lastError

			originalCallback?(connector)

			if let nextOp = self.onNextStep?(connector, number) {
				let validationError = self.doStep(number + 1, nextOp, waitGroup)

				if let validationError = validationError {
					self.lastError = validationError
					waitGroup.leave()
				}
				else {
					self.currentConnector = nextOp
				}
			}
			else {
				waitGroup.leave()
			}
		}
	}

	override func canBeCookieExpiredError(session: LRSession) -> Bool {
		return false
	}
}
