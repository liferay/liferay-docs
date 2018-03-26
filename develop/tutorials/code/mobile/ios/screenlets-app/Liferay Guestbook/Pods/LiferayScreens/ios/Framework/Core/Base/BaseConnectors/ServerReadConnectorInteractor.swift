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

open class ServerReadConnectorInteractor: ServerConnectorInteractor {

	// MARK: ServerConnectorInteractor

	override open func getCacheStrategyImpl(_ strategyType: CacheStrategyType) -> CacheStrategy {
		switch strategyType {
		case .remoteOnly:
			return createStrategyReadRemoteOnly()

		case .cacheOnly:
			return defaultStrategyReadFromCache

		case .remoteFirst:
			return createStrategy(
				whenFails: createStrategyReadRemoteOnly(),
				then: defaultStrategyReadFromCache)

		case .cacheFirst:
			return createStrategy(
				whenFails: defaultStrategyReadFromCache,
				then: createStrategyReadRemoteOnly())
		}
	}

	// MARK: Private methods

	fileprivate func createStrategyReadRemoteOnly() -> CacheStrategy {
		return createStrategy(
			whenSucceeds: defaultStrategyRemote,
			then: defaultStrategyWriteToCache)
	}

}
