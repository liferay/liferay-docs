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

class LoadFileEntryInteractor: ServerReadConnectorInteractor {

	let fileEntry: FileEntry

	var resultUrl: URL?

	// MARK: Initializers

	init(screenlet: BaseScreenlet, fileEntry: FileEntry) {
		self.fileEntry = fileEntry

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override func createConnector() -> ServerConnector? {
		guard let url = URL(string: LiferayServerContext.server + fileEntry.url) else {
			return nil
		}

		return HttpDownloadConnector(url: url)
	}

	override func completedConnector(_ op: ServerConnector) {
		resultUrl = (op as? HttpDownloadConnector)?.resultUrl as URL?
	}

	// MARK: Cache methods

	override func readFromCache(_ c: ServerConnector, result: @escaping (AnyObject?) -> Void) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			result(nil)
			return
		}
		guard let loadCon = c as? HttpDownloadConnector else {
			result(nil)
			return
		}

		cacheManager.getLocalFileURL(
				collection: "load-file-entry",
				key: "filEntryId-\(fileEntry.fileEntryId)") {
			loadCon.resultUrl = $0
			result($0 as AnyObject?)
		}
	}

	override func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}
		guard let downloadCon = c as? HttpDownloadConnector else {
			return
		}
		guard let resultUrl = downloadCon.resultUrl else {
			return
		}

		cacheManager.setClean(
			collection: "load-file-entry",
			key: "filEntryId-\(fileEntry.fileEntryId)",
			localFileURL: resultUrl,
			attributes: ["fileEntry": fileEntry])
	}

}
