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

open class ImageGalleryPageLiferayConnector: PaginationLiferayConnector {

	open let repositoryId: Int64
    open let folderId: Int64
	open let mimeTypes: [String]

	// MARK: Initializers

	public init(
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool,
			repositoryId: Int64,
			folderId: Int64,
			mimeTypes: [String]) {

        self.repositoryId = repositoryId
        self.folderId = folderId
		self.mimeTypes = mimeTypes

        super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
    }

	// MARK: ServerConnector

	open override func validateData() -> ValidationError? {
		var error = super.validateData()

		if error == nil {
			if repositoryId < 0 {
				error = ValidationError("imagegallery-screenlet", "undefined-repositoryid")
			}
			else if folderId < 0 {
				error = ValidationError("imagegallery-screenlet", "undefined-folderid")
			}
		}

		return error
	}

}

open class Liferay70ImageGalleryPageLiferayConnector: ImageGalleryPageLiferayConnector {

	override open func doAddPageRowsServiceCall(
			session: LRBatchSession,
			startRow: Int,
			endRow: Int,
			obc: LRJSONObjectWrapper?) {

        let service = LRDLAppService_v7(session: session)

        do {
            try service?.getFileEntries(withRepositoryId: repositoryId,
					folderId: folderId,
					mimeTypes: mimeTypes,
					start: Int32(startRow),
					end: Int32(endRow),
					obc: obc)
        }
        catch {
		}
    }

    override open func doAddRowCountServiceCall(session: LRBatchSession) {
        let service = LRDLAppService_v7(session: session)

        do {
            try service?.getFileEntriesCount(withRepositoryId: repositoryId,
					folderId: folderId,
					mimeTypes: mimeTypes)
        }
        catch {
		}
    }

}
