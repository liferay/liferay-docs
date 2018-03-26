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

public typealias CacheStrategy = (
	ServerConnector,
	_ whenSuccess: @escaping () -> Void,
	_ whenFailure: @escaping (NSError) -> Void) -> Void

open class ServerConnectorInteractor: Interactor {

	open var cacheStrategy = CacheStrategyType.remoteFirst

	open var currentConnector: ServerConnector?

	// MARK: Interactor

	override open func start() -> Bool {
		self.currentConnector = createConnector()

		if let currentConnector = self.currentConnector {
			getCacheStrategyImpl(cacheStrategy)(
				currentConnector, {
					self.completedConnector(currentConnector)
					self.callOnSuccess()
				}, { err in
					currentConnector.lastError = err
					self.completedConnector(currentConnector)
					self.callOnFailure(err)
				})

			return true
		}

		self.callOnFailure(NSError.errorWithCause(.abortedDueToPreconditions,
				message: "An error ocurred when creating the connector."))

		return false
	}

	override open func cancel() {
		currentConnector?.cancel()
		super.cancel()
	}

	// MARK: Interactor

	override open func callOnSuccess() {
		super.callOnSuccess()
		currentConnector = nil
	}

	override open func callOnFailure(_ error: NSError) {
		super.callOnFailure(error)
		currentConnector = nil
	}

	// MARK: Public methods

	open func createConnector() -> ServerConnector? {
		return nil
	}

	open func completedConnector(_ c: ServerConnector) {
	}

	open func readFromCache(_ c: ServerConnector, result: @escaping (AnyObject?) -> Void) {
		result(nil)
	}

	open func writeToCache(_ c: ServerConnector) {
	}

	open func getCacheStrategyImpl(_ strategyType: CacheStrategyType) -> CacheStrategy {
		return defaultStrategyRemote
	}

	// MARK: Default strategy implementations

	open func defaultStrategyRemote(
			_ connector: ServerConnector,
			whenSuccess: @escaping () -> Void,
			whenFailure: @escaping (NSError) -> Void) {

		let validationError = connector.validateAndEnqueue {
			if let error = $0.lastError {
				if error.domain == "NSURLErrorDomain" {
					$0.lastError = NSError.errorWithCause(.notAvailable,
							message: "URL error domain.")
				}
				whenFailure($0.lastError!)
			}
			else {
				whenSuccess()
			}
		}

		if let validationError = validationError {
			whenFailure(validationError)
		}
	}

	open func defaultStrategyReadFromCache(
			_ connector: ServerConnector,
			whenSuccess: @escaping () -> Void,
			whenFailure: @escaping (NSError) -> Void) {
		self.readFromCache(connector) {
			if $0 != nil {
				whenSuccess()
			}
			else {
				whenFailure(NSError.errorWithCause(.notAvailable,
						message: "Could not read from cache."))
			}
		}
	}

	open func defaultStrategyWriteToCache(
			_ connector: ServerConnector,
			whenSuccess: () -> Void,
			whenFailure: (NSError) -> Void) {

		// the closure is called before because it fires the 
		// "completedConnector" method and it should be run
		// before the write
		whenSuccess()
		self.writeToCache(connector)
	}

	// MARK: Strategy builders

	open func createStrategy(
			whenFails mainStrategy: @escaping CacheStrategy,
			then onFailStrategy: @escaping CacheStrategy) -> CacheStrategy {

		return { (connector: ServerConnector,
				whenSuccess: @escaping () -> Void,
				whenFailure: @escaping (NSError) -> Void) -> Void in
			mainStrategy(connector,
				whenSuccess, { err -> Void in
					if err.code == ScreensErrorCause.notAvailable.rawValue {
						onFailStrategy(connector,
							whenSuccess,
							whenFailure)
					}
					else {
						whenFailure(err)
					}
				})
		}
	}

	open func createStrategy(
			whenSucceeds mainStrategy: @escaping CacheStrategy,
			then onSuccessStrategy: @escaping CacheStrategy) -> CacheStrategy {

		return { (connector: ServerConnector,
				whenSuccess: @escaping () -> Void,
				whenFailure: @escaping (NSError) -> Void) -> Void in
			mainStrategy(connector, {
					onSuccessStrategy(connector,
						whenSuccess,
						whenFailure)
				},
				whenFailure)
		}
	}

	open func createStrategy(
			_ firstStrategy: @escaping CacheStrategy,
			andThen secondStrategy: @escaping CacheStrategy) -> CacheStrategy {

		return { (connector: ServerConnector,
				whenSuccess: @escaping () -> Void,
				whenFailure: @escaping (NSError) -> Void) -> Void in
			firstStrategy(connector, {
					secondStrategy(connector,
						whenSuccess,
						whenFailure)
				}, { err -> Void in
					if err.code == ScreensErrorCause.notAvailable.rawValue {
						secondStrategy(connector,
							whenSuccess,
							whenFailure)
					}
					else {
						whenFailure(err)
					}
				})
			}
	}

}
