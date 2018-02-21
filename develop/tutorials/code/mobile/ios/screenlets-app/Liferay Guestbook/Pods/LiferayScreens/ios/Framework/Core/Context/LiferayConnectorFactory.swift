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
// swiftlint:disable file_length
import Foundation

@objc(LiferayConnectorFactory)
public protocol LiferayConnectorFactory {

	func createGetUserByEmailConnector(
			companyId: Int64,
			emailAddress: String) -> GetUserByEmailLiferayConnector

	func createGetUserByScreenNameConnector(
			companyId: Int64,
			screenName: String) -> GetUserByScreenNameLiferayConnector

	func createGetUserByUserIdConnector(userId: Int64) -> GetUserByUserIdLiferayConnector

	func createLoginByEmailConnector(
		companyId: Int64,
		emailAddress: String,
		password: String) -> GetUserByEmailLiferayConnector

	func createLoginByScreenNameConnector(
		companyId: Int64,
		screenName: String,
		password: String) -> GetUserByScreenNameLiferayConnector

	func createLoginByUserIdConnector(
		userId: Int64,
		password: String) -> GetUserByUserIdLiferayConnector

	func createForgotPasswordByEmailConnector(
		viewModel: ForgotPasswordViewModel,
		anonymousUsername: String,
		anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector

	func createForgotPasswordByScreenNameConnector(
		viewModel: ForgotPasswordViewModel,
		anonymousUsername: String,
		anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector

	func createForgotPasswordByUserIdConnector(
		viewModel: ForgotPasswordViewModel,
		anonymousUsername: String,
		anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector

	func createSignUpConnector(
		viewModel: SignUpViewModel,
		anonymousUsername: String,
		anonymousPassword: String) -> SignUpLiferayConnector

	func createUpdateCurrentUserConnector(
		viewModel: SignUpViewModel) -> UpdateCurrentUserLiferayConnector

	func createUploadUserPortraitConnector(
		userId: Int64,
		image: UIImage) -> UploadUserPortraitLiferayConnector

	func createAssetListPageConnector(
		startRow: Int,
		endRow: Int,
		computeRowCount: Bool) -> AssetListPageLiferayConnector

	func createWebContentListPageConnector(
		groupId: Int64,
		folderId: Int64,
		startRow: Int,
		endRow: Int,
		computeRowCount: Bool) -> WebContentListPageLiferayConnector

	func createDDLListPageConnector(
		viewModel: DDLListViewModel,
		startRow: Int,
		endRow: Int,
		computeRowCount: Bool) -> DDLListPageLiferayConnector

	func createWebContentLoadHtmlConnector(
		groupId: Int64,
		articleId: String) -> WebContentLoadHtmlLiferayConnector

	func createWebContentLoadStructuredConnector(
		groupId: Int64,
		articleId: String,
		structureId: Int64) -> WebContentLoadStructuredLiferayConnector

	func createDDLFormLoadConnector(_ structureId: Int64) -> DDLFormLoadLiferayConnector

	func createDDLFormRecordLoadConnector(_ recordId: Int64) -> DDLFormRecordLoadLiferayConnector

	func createDDLFormSubmitConnector(
		values: [String:AnyObject],
		viewModel: DDLFormViewModel?) -> DDLFormSubmitLiferayConnector

	func createDDLFormUploadConnector(
		document: DDMFieldDocument,
		filePrefix: String,
		repositoryId: Int64,
		folderId: Int64,
		onProgress: DDLFormUploadLiferayConnector.OnProgress?) -> DDLFormUploadLiferayConnector

	func createAssetLoadByEntryIdConnector(_ entryId: Int64) -> AssetLoadByEntryIdLiferayConnector?

	func createAssetLoadByClassPKConnector(_ className: String, classPK: Int64) -> AssetLoadByClassPKLiferayConnector?

	func createAssetLoadByPortletItemNameConnector(portletItemName: String)
		-> AssetLoadByPortletItemNameLiferayConnector?

	func createRatingLoadByEntryIdConnector(
		entryId: Int64,
		ratingsGroupCount: Int32) -> RatingLoadByEntryIdLiferayConnector?

	func createRatingLoadByClassPKConnector(
		_ classPK: Int64,
		className: String,
		ratingsGroupCount: Int32) -> RatingLoadByClassPKLiferayConnector?

	func createRatingUpdateConnector(
		classPK: Int64,
		className: String,
		score: Double,
		ratingsGroupCount: Int32) -> RatingUpdateLiferayConnector?

	func createRatingDeleteConnector(
		classPK: Int64,
		className: String,
		ratingsGroupCount: Int32) -> RatingDeleteLiferayConnector?

	func createImageGalleryDeleteConnector(_ imageEntryId: Int64) -> ImageGalleryDeleteConnector?

	func createImageGalleryPageConnector(startRow: Int,
			endRow: Int,
			computeRowCount: Bool,
			repositoryId: Int64,
			folderId: Int64,
			mimeTypes: [String]) -> ImageGalleryPageLiferayConnector?

	func createImageGalleryUploadConnector(repositoryId: Int64,
			folderId: Int64,
			sourceFileName: String,
			mimeType: String,
			title: String,
			descrip: String,
			changeLog: String,
			image: UIImage,
			onUploadBytes: ImageGalleryUploadConnector.OnProgress?) -> ImageGalleryUploadConnector?

	func createCommentListPageConnector(
		className: String,
		classPK: Int64,
		startRow: Int,
		endRow: Int,
		computeRowCount: Bool) -> CommentListPageLiferayConnector?

	func createCommentAddConnector(
		className: String,
		classPK: Int64,
		body: String) -> CommentAddLiferayConnector?

	func createCommentLoadConnector(
		commentId: Int64) -> CommentLoadLiferayConnector?

	func createCommentDeleteConnector(
		commentId: Int64) -> CommentDeleteLiferayConnector?

	func createCommentUpdateConnector(
		commentId: Int64,
		body: String) -> CommentUpdateLiferayConnector?

}

@objc(Liferay62ConnectorFactory)
open class Liferay62ConnectorFactory: NSObject, LiferayConnectorFactory {

	open func createGetUserByEmailConnector(
			companyId: Int64,
			emailAddress: String) -> GetUserByEmailLiferayConnector {
		return GetUserByEmailLiferay62Connector(
			companyId: companyId,
			emailAddress: emailAddress)
	}

	open func createGetUserByScreenNameConnector(
			companyId: Int64,
			screenName: String) -> GetUserByScreenNameLiferayConnector {
		return GetUserByScreenNameLiferay62Connector(
			companyId: companyId,
			screenName: screenName)
	}

	open func createGetUserByUserIdConnector(userId: Int64) -> GetUserByUserIdLiferayConnector {
		return GetUserByUserIdLiferay62Connector(userId: userId)
	}

	open func createLoginByEmailConnector(
			companyId: Int64,
			emailAddress: String,
			password: String) -> GetUserByEmailLiferayConnector {
		return LoginByEmailLiferay62Connector(
			companyId: companyId,
			emailAddress: emailAddress,
			password: password)
	}

	open func createLoginByScreenNameConnector(
			companyId: Int64,
			screenName: String,
			password: String) -> GetUserByScreenNameLiferayConnector {
		return LoginByScreenNameLiferay62Connector(
			companyId: companyId,
			screenName: screenName,
			password: password)
	}

	open func createLoginByUserIdConnector(
			userId: Int64,
			password: String) -> GetUserByUserIdLiferayConnector {
		return LoginByUserIdLiferay62Connector(userId: userId, password: password)
	}

	open func createForgotPasswordByEmailConnector(
			viewModel: ForgotPasswordViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector {
		return ForgotPasswordEmailLiferay62Connector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createForgotPasswordByScreenNameConnector(
			viewModel: ForgotPasswordViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector {
		return ForgotPasswordScreenNameLiferay62Connector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createForgotPasswordByUserIdConnector(
			viewModel: ForgotPasswordViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector {
		return ForgotPasswordUserIdLiferay62Connector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createSignUpConnector(
			viewModel: SignUpViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> SignUpLiferayConnector {
		return Liferay62SignUpConnector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createUpdateCurrentUserConnector(
		viewModel: SignUpViewModel) -> UpdateCurrentUserLiferayConnector {
		return Liferay62UpdateCurrentUserConnector(viewModel: viewModel)
	}

	open func createUploadUserPortraitConnector(
			userId: Int64,
			image: UIImage) -> UploadUserPortraitLiferayConnector {
		return Liferay62UploadUserPortraitConnector(
			userId: userId,
			image: image)
	}

	open func createAssetListPageConnector(
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> AssetListPageLiferayConnector {
		return Liferay62AssetListPageConnector(
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount)
	}

	open func createWebContentListPageConnector(
			groupId: Int64,
			folderId: Int64,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> WebContentListPageLiferayConnector {
		return Liferay62WebContentListPageConnector(
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount,
			groupId: groupId,
			folderId: folderId)
	}

	open func createDDLListPageConnector(
			viewModel: DDLListViewModel,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> DDLListPageLiferayConnector {
		return Liferay62DDLListPageConnector(
			viewModel: viewModel,
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount)
	}

	open func createWebContentLoadHtmlConnector(
			groupId: Int64,
			articleId: String) -> WebContentLoadHtmlLiferayConnector {
		return Liferay62WebContentLoadHtmlConnector(
			groupId: groupId,
			articleId: articleId)
	}

	open func createWebContentLoadStructuredConnector(
			groupId: Int64,
			articleId: String,
			structureId: Int64) -> WebContentLoadStructuredLiferayConnector {
		return Liferay62WebContentLoadStructuredConnector(
			groupId: groupId,
			articleId: articleId,
			structureId: structureId)
	}

	open func createDDLFormLoadConnector(_ structureId: Int64) -> DDLFormLoadLiferayConnector {
		return Liferay62DDLFormLoadConnector(structureId: structureId)
	}

	open func createDDLFormRecordLoadConnector(_ recordId: Int64) -> DDLFormRecordLoadLiferayConnector {
		return Liferay62DDLFormRecordLoadConnector(recordId: recordId)
	}

	open func createDDLFormSubmitConnector(
			values: [String:AnyObject],
			viewModel: DDLFormViewModel?) -> DDLFormSubmitLiferayConnector {
		return Liferay62DDLFormSubmitConnector(
			values: values,
			viewModel: viewModel)
	}

	open func createDDLFormUploadConnector(
			document: DDMFieldDocument,
			filePrefix: String,
			repositoryId: Int64,
			folderId: Int64,
			onProgress: DDLFormUploadLiferayConnector.OnProgress?) -> DDLFormUploadLiferayConnector {
		return Liferay62DDLFormUploadConnector(
			document: document,
			filePrefix: filePrefix,
			repositoryId: repositoryId,
			folderId: folderId,
			onProgress: onProgress)
	}

	open func createAssetLoadByEntryIdConnector(_ entryId: Int64) -> AssetLoadByEntryIdLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: AssetLoadByEntryIdLiferayConnector")
		return nil
	}

	open func createAssetLoadByClassPKConnector(_ className: String, classPK: Int64)
			-> AssetLoadByClassPKLiferayConnector? {

		print("Unsupported connector in Liferay 6.2: AssetLoadByClassPKLiferayConnector")
		return nil
	}

	public func createAssetLoadByPortletItemNameConnector(portletItemName: String)
			-> AssetLoadByPortletItemNameLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: AssetLoadByPortletItemNameLiferayConnector")
		return nil
	}

	open func createRatingLoadByEntryIdConnector(
			entryId: Int64,
			ratingsGroupCount: Int32) -> RatingLoadByEntryIdLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: createRatingLoadByEntryIdConnector")
		return nil
	}

	open func createRatingLoadByClassPKConnector(
			_ classPK: Int64,
			className: String,
			ratingsGroupCount: Int32) -> RatingLoadByClassPKLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: RatingLoadByClassPKLiferayConnector")
		return nil
	}

	open func createRatingUpdateConnector(
			classPK: Int64,
			className: String,
			score: Double,
			ratingsGroupCount: Int32) -> RatingUpdateLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: RatingUpdateLiferayConnector")
		return nil
	}

	open func createRatingDeleteConnector(
			classPK: Int64,
			className: String,
			ratingsGroupCount: Int32) -> RatingDeleteLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: RatingDeleteLiferayConnector")
		return nil
	}

	open func createImageGalleryDeleteConnector(_ imageEntryId: Int64) -> ImageGalleryDeleteConnector? {
		print("Unsupported connector in Liferay 6.2: ImageGalleryDeleteConnector")
		return nil
	}

	open func createImageGalleryPageConnector(startRow: Int,
			endRow: Int,
			computeRowCount: Bool,
			repositoryId: Int64,
			folderId: Int64,
			mimeTypes: [String]) -> ImageGalleryPageLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: ImageGalleryPageLiferayConnector")
		return nil
	}

	open func createImageGalleryUploadConnector(repositoryId: Int64,
			folderId: Int64,
			sourceFileName: String,
			mimeType: String,
			title: String,
			descrip: String,
			changeLog: String,
			image: UIImage,
			onUploadBytes: ImageGalleryUploadConnector.OnProgress?) -> ImageGalleryUploadConnector? {
		print("Unsupported connector in Liferay 6.2: ImageGalleryUploadConnector")
		return nil
	}

	open func createCommentListPageConnector(
			className: String,
			classPK: Int64,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> CommentListPageLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: CommentListPageLiferayConnector")
		return nil
	}

	open func createCommentAddConnector(
			className: String,
			classPK: Int64,
			body: String) -> CommentAddLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: CommentAddLiferayConnector")
		return nil
	}

	open func createCommentLoadConnector(
			commentId: Int64) -> CommentLoadLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: CommentLoadLiferayConnector")
		return nil
	}

	open func createCommentDeleteConnector(commentId: Int64) -> CommentDeleteLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: CommentDeleteLiferayConnector")
		return nil
	}

	open func createCommentUpdateConnector(
			commentId: Int64,
			body: String) -> CommentUpdateLiferayConnector? {
		print("Unsupported connector in Liferay 6.2: CommentUpdateLiferayConnector")
		return nil
	}

}

@objc(Liferay70ConnectorFactory)
open class Liferay70ConnectorFactory: NSObject, LiferayConnectorFactory {

	open func createGetUserByEmailConnector(
			companyId: Int64,
			emailAddress: String) -> GetUserByEmailLiferayConnector {
		return GetUserByEmailLiferay70Connector(
			companyId: companyId,
			emailAddress: emailAddress)
	}

	open func createGetUserByScreenNameConnector(
			companyId: Int64,
			screenName: String) -> GetUserByScreenNameLiferayConnector {
		return GetUserByScreenNameLiferay70Connector(
			companyId: companyId,
			screenName: screenName)
	}

	open func createGetUserByUserIdConnector(userId: Int64) -> GetUserByUserIdLiferayConnector {
		return GetUserByUserIdLiferay70Connector(userId: userId)
	}

	open func createLoginByEmailConnector(
			companyId: Int64,
			emailAddress: String,
			password: String) -> GetUserByEmailLiferayConnector {
		return LoginByEmailLiferay70Connector(
			companyId: companyId,
			emailAddress: emailAddress,
			password: password)
	}

	open func createLoginByScreenNameConnector(
			companyId: Int64,
			screenName: String,
			password: String) -> GetUserByScreenNameLiferayConnector {
		return LoginByScreenNameLiferay70Connector(
			companyId: companyId,
			screenName: screenName,
			password: password)
	}

	open func createLoginByUserIdConnector(
			userId: Int64,
			password: String) -> GetUserByUserIdLiferayConnector {
		return LoginByUserIdLiferay70Connector(userId: userId, password: password)
	}

	open func createForgotPasswordByEmailConnector(
			viewModel: ForgotPasswordViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector {
		return ForgotPasswordEmailLiferay70Connector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createForgotPasswordByScreenNameConnector(
			viewModel: ForgotPasswordViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector {
		return ForgotPasswordScreenNameLiferay70Connector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createForgotPasswordByUserIdConnector(
			viewModel: ForgotPasswordViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> ForgotPasswordBaseLiferayConnector {
		return ForgotPasswordUserIdLiferay70Connector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createSignUpConnector(
			viewModel: SignUpViewModel,
			anonymousUsername: String,
			anonymousPassword: String) -> SignUpLiferayConnector {
		return Liferay70SignUpConnector(
			viewModel: viewModel,
			anonymousUsername: anonymousUsername,
			anonymousPassword: anonymousPassword)
	}

	open func createUpdateCurrentUserConnector(
			viewModel: SignUpViewModel) -> UpdateCurrentUserLiferayConnector {
		return Liferay70UpdateCurrentUserConnector(viewModel: viewModel)
	}

	open func createUploadUserPortraitConnector(
			userId: Int64,
			image: UIImage) -> UploadUserPortraitLiferayConnector {
		return Liferay70UploadUserPortraitConnector(
			userId: userId,
			image: image)
	}

	open func createAssetListPageConnector(
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> AssetListPageLiferayConnector {
		return Liferay70AssetListPageConnector(
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount)
	}

	open func createWebContentListPageConnector(
			groupId: Int64,
			folderId: Int64,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> WebContentListPageLiferayConnector {
		return Liferay70WebContentListPageConnector(
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount,
			groupId: groupId,
			folderId: folderId)
	}

	open func createDDLListPageConnector(
			viewModel: DDLListViewModel,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> DDLListPageLiferayConnector {
		return Liferay70DDLListPageConnector(
			viewModel: viewModel,
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount)
	}

	open func createWebContentLoadHtmlConnector(
			groupId: Int64,
			articleId: String) -> WebContentLoadHtmlLiferayConnector {
		return Liferay70WebContentLoadHtmlConnector(
			groupId: groupId,
			articleId: articleId)
	}

	open func createWebContentLoadStructuredConnector(
			groupId: Int64,
			articleId: String,
			structureId: Int64) -> WebContentLoadStructuredLiferayConnector {
		return Liferay70WebContentLoadStructuredConnector(
			groupId: groupId,
			articleId: articleId,
			structureId: structureId)
	}

	open func createDDLFormLoadConnector(_ structureId: Int64) -> DDLFormLoadLiferayConnector {
		return Liferay70DDLFormLoadConnector(structureId: structureId)
	}

	open func createDDLFormRecordLoadConnector(_ recordId: Int64) -> DDLFormRecordLoadLiferayConnector {
		return Liferay70DDLFormRecordLoadConnector(recordId: recordId)
	}

	open func createDDLFormSubmitConnector(
			values: [String:AnyObject],
			viewModel: DDLFormViewModel?) -> DDLFormSubmitLiferayConnector {
		return Liferay70DDLFormSubmitConnector(
			values: values,
			viewModel: viewModel)
	}

	open func createDDLFormUploadConnector(
			document: DDMFieldDocument,
			filePrefix: String,
			repositoryId: Int64,
			folderId: Int64,
			onProgress: DDLFormUploadLiferayConnector.OnProgress?) -> DDLFormUploadLiferayConnector {
		return Liferay70DDLFormUploadConnector(
			document: document,
			filePrefix: filePrefix,
			repositoryId: repositoryId,
			folderId: folderId,
			onProgress: onProgress)
	}

	open func createRatingLoadByEntryIdConnector(
			entryId: Int64,
			ratingsGroupCount: Int32) -> RatingLoadByEntryIdLiferayConnector? {
		return Liferay70RatingLoadByEntryIdConnector(
			entryId: entryId,
			ratingsGroupCount: ratingsGroupCount)
	}

	open func createRatingLoadByClassPKConnector(
			_ classPK: Int64,
			className: String,
			ratingsGroupCount: Int32) -> RatingLoadByClassPKLiferayConnector? {
		return Liferay70RatingLoadByClassPKConnector(
			classPK: classPK,
			className: className,
			ratingsGroupCount: ratingsGroupCount)
	}

	open func createRatingUpdateConnector(
			classPK: Int64,
			className: String,
			score: Double,
			ratingsGroupCount: Int32) -> RatingUpdateLiferayConnector? {
		return Liferay70RatingUpdateConnector(
			classPK: classPK,
			className: className,
			score: score,
			ratingsGroupCount: ratingsGroupCount)
	}

	open func createRatingDeleteConnector(
			classPK: Int64,
			className: String,
			ratingsGroupCount: Int32) -> RatingDeleteLiferayConnector? {
		return Liferay70RatingDeleteConnector(
			classPK: classPK,
			className: className,
			ratingsGroupCount: ratingsGroupCount)
	}

	open func createImageGalleryDeleteConnector(_ imageEntryId: Int64) -> ImageGalleryDeleteConnector? {
		return Liferay70ImageGalleryDeleteConnector(imageEntryId: imageEntryId)
	}

	open func createImageGalleryPageConnector(startRow: Int,
			endRow: Int,
			computeRowCount: Bool,
			repositoryId: Int64,
			folderId: Int64,
			mimeTypes: [String]) -> ImageGalleryPageLiferayConnector {
		return Liferay70ImageGalleryPageLiferayConnector(startRow: startRow,
			endRow:endRow,
			computeRowCount:computeRowCount,
			repositoryId: repositoryId,
			folderId: folderId,
			mimeTypes: mimeTypes
		)
	}

	open func createImageGalleryUploadConnector(repositoryId: Int64,
			folderId: Int64,
			sourceFileName: String,
			mimeType: String,
			title: String,
			descrip: String,
			changeLog: String,
			image: UIImage,
			onUploadBytes: ImageGalleryUploadConnector.OnProgress?) -> ImageGalleryUploadConnector {

		return Liferay70ImageGalleryUploadConnector(repositoryId: repositoryId, folderId: folderId,
			sourceFileName: sourceFileName,
			mimeType: mimeType,
			title: title,
			descrip: descrip,
			changeLog: changeLog,
			image: image,
			onUploadBytes: onUploadBytes)
	}

	open func createCommentListPageConnector(
			className: String,
			classPK: Int64,
			startRow: Int,
			endRow: Int,
			computeRowCount: Bool) -> CommentListPageLiferayConnector? {
		return Liferay70CommentListPageConnector(
			className: className,
			classPK: classPK,
			startRow: startRow,
			endRow: endRow,
			computeRowCount: computeRowCount)
	}

	open func createCommentAddConnector(
			className: String,
			classPK: Int64,
			body: String) -> CommentAddLiferayConnector? {
		return Liferay70CommentAddConnector(
			className: className,
			classPK: classPK,
			body: body)
	}

	open func createCommentLoadConnector(
			commentId: Int64) -> CommentLoadLiferayConnector? {
		return Liferay70CommentLoadConnector(
			commentId: commentId)
	}

	open func createCommentDeleteConnector(commentId: Int64) -> CommentDeleteLiferayConnector? {
		return Liferay70CommentDeleteConnector(
			commentId: commentId)
	}

	open func createCommentUpdateConnector(
			commentId: Int64,
			body: String) -> CommentUpdateLiferayConnector? {
		return Liferay70CommentUpdateConnector(
			commentId: commentId,
			body: body)
	}

	open func createAssetLoadByEntryIdConnector(_ entryId: Int64) -> AssetLoadByEntryIdLiferayConnector? {
		return Liferay70AssetLoadByEntryIdConnector(entryId: entryId)
	}

	open func createAssetLoadByClassPKConnector(_ className: String, classPK: Int64)
			-> AssetLoadByClassPKLiferayConnector? {
		return Liferay70AssetLoadByClassPKConnector(className: className, classPK: classPK)
	}

	public func createAssetLoadByPortletItemNameConnector(portletItemName: String)
			-> AssetLoadByPortletItemNameLiferayConnector? {
		return Liferay70AssetLoadByPortletItemNameConnector(portletItemName: portletItemName)
	}

}
