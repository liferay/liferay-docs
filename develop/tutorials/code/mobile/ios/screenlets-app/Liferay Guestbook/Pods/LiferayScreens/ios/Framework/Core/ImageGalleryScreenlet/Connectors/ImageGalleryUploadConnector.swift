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
import LRMobileSDK

open class ImageGalleryUploadConnector: UploadFileConnector {

	open let repositoryId: Int64
	open let folderId: Int64
	open let sourceFileName: String
	open let title: String
	open let descrip: String
	open let changeLog: String

	// MARK: Initializers

	public init(repositoryId: Int64,
			folderId: Int64,
			sourceFileName: String,
			mimeType: String,
			title: String,
			descrip: String,
			changeLog: String,
			image: UIImage,
			onUploadBytes: OnProgress?) {

		self.repositoryId = repositoryId
		self.folderId = folderId
		self.sourceFileName = sourceFileName
		self.title = title
		self.descrip = descrip
		self.changeLog = changeLog

		super.init(
			image: image,
			fileName: sourceFileName,
			mimeType: mimeType,
			parameter: title,
			onUploadedBytes: onUploadBytes)
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
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

open class Liferay70ImageGalleryUploadConnector: ImageGalleryUploadConnector {

	override open func doSendFile(_ session: LRSession, data: LRUploadData) throws {
		let service = LRDLAppService_v7(session: session)

		let serviceContext = LRJSONObjectWrapper(jsonObject: ["addGuestPermissions": true])
		try service?.addFileEntry(withRepositoryId: repositoryId,
				folderId: folderId,
			 	sourceFileName: fileName,
			 	mimeType: mimeType,
			 	title: title,
			 	description: descrip,
			 	changeLog: changeLog,
			 	file: data,
			 	serviceContext: serviceContext)
	}

}
