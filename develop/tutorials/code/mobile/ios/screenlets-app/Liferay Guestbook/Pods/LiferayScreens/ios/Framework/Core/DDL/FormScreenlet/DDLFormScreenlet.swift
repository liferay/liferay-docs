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

/// The DDLFormScreenletDelegate protocol defines some methods that you use to manage the
/// DDLFormScreenlet events. All of them are optional.
@objc(DDLFormScreenletDelegate)
public protocol DDLFormScreenletDelegate: BaseScreenletDelegate {

	/// Called when the form is loaded. The second parameter (record) 
	/// contains only field definitions.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - record: Record definitions.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onFormLoaded record: DDLRecord)

	/// Called when an error occurs while loading the form.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - error: Error when loading the form.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onFormLoadError error: NSError)

	/// Called when a form with values loads. The second parameter (record) 
	/// contains field definitions and values. The method onFormLoadResult 
	/// is called before onRecordLoaded.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - record: Record definitions.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onRecordLoaded record: DDLRecord)

	/// Called when an error occurs while loading a record.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - error: Error when loading the record.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onRecordLoadError error: NSError)

	/// Called when the form values are successfully submitted to the server.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - record: Record definitions.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onFormSubmitted record: DDLRecord)

	/// Called when an error occurs while submitting the form.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - error: Error when submitting the form.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onFormSubmitError error: NSError)

	/// Called when the upload of a Documents and Media field begins.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - field: Document field of the form.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onDocumentFieldUploadStarted field: DDMFieldDocument)

	/// Called when a block of bytes in a Documents and Media field is uploaded.
	/// This method is intended to track progress of the uploads.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - field: Document field of the form.
	///   - bytes: Uploaded bytes.
	///   - total: Total bytes.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onDocumentField field: DDMFieldDocument,
			uploadedBytes bytes: UInt64,
			totalBytes total: UInt64)

	/// Called when a Documents and Media field upload is completed.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - field: Document field of the form.
	///   - result: Document data attributes.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onDocumentField field: DDMFieldDocument,
			uploadResult result: [String:AnyObject])

	/// Called when an error occurs in the Documents and Media upload process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: DDL Form screenlet instance.
	///   - field: Document field of the form.
	///   - error: Error when uploading the document.
	@objc optional func screenlet(_ screenlet: DDLFormScreenlet,
			onDocumentField field: DDMFieldDocument,
			uploadError error: NSError)

}

/// DDLFormScreenlet can be used to show a collection of fields so that a user can fill in their
/// values. Initial or existing values may be shown in the fields.
/// DDLFormScreenlet also supports the following features:
/// * Stored records can support a specific workflow.
/// * A Submit button can be shown at the end of the form.
/// * Required values and validation for fields can be used.
/// * Users can traverse the form fields from the keyboard.
/// * Supports i18n in record values and labels.
@objc(DDLFormScreenlet)
open class DDLFormScreenlet: BaseScreenlet {

	fileprivate enum UploadStatus {
		case idle
		case uploading(Int, Bool)
		case failed(NSError)
	}

	// MARK: Class properties

	open class var LoadFormAction: String { return "load-form" }
	open class var LoadRecordAction: String { return "load-record" }
	open class var SubmitFormAction: String { return "submit-form" }
	open class var UploadDocumentAction: String { return "upload-document" }

	// MARK: Inspectables

	/// Specifies the identifier of a data definition for your site in Liferay.
	@IBInspectable open var structureId: Int64 = 0

	/// The groupId of the Liferay instance.
	@IBInspectable open var groupId: Int64 = 0

	/// The identifier of a dynamic data list.
	@IBInspectable open var recordSetId: Int64 = 0

	/// The identifier of the record you want to show.
	@IBInspectable open var recordId: Int64 = 0

	/// The user identifier of the form or the record.
	@IBInspectable open var userId: Int64 = 0

	/// The identifier of the Documents and Media repository to upload to
	@IBInspectable open var repositoryId: Int64 = 0

	/// The identifier of the folder where Documents and Media files are uploaded.
	/// If this value is 0, the root folder is used.
	@IBInspectable open var folderId: Int64 = 0

	/// The prefix to attach to the names of files uploaded to a Documents and Media repository.
	/// A random GUID string is appended following the prefix.
	@IBInspectable open var filePrefix: String = "form-file-"

	/// Sets whether or not the form is loaded when the Screenlet is shown.
	@IBInspectable open var autoLoad: Bool = true

	/// Sets whether or not the form automatically scrolls to the first failed field when validation 
	/// is used.
	@IBInspectable open var autoscrollOnValidation: Bool = true

	/// Sets whether or not the form shows a submit button at the bottom. If this is set to false, 
	/// you should call the `submitForm()` method.
	@IBInspectable open var showSubmitButton: Bool = true {
		didSet {
			(screenletView as? DDLFormView)?.showSubmitButton = showSubmitButton
		}
	}

	/// Sets whether the values can be changed by the user. The default is true.
	@IBInspectable open var editable: Bool = true {
		didSet {
			screenletView?.editable = editable
		}
	}

	/// Specifies the ddl form offline policy. The default is remote first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var ddlFormDelegate: DDLFormScreenletDelegate? {
		return delegate as? DDLFormScreenletDelegate
	}

	open var viewModel: DDLFormViewModel {
		return screenletView as! DDLFormViewModel
	}

	open var isFormLoaded: Bool {
		return !((screenletView as? DDLFormView)?.isRecordEmpty ?? true)
	}

	// MARK: Internal properties

	internal var formView: DDLFormView {
		return screenletView as! DDLFormView
	}

	// MARK: Private properties

	fileprivate var uploadStatus = UploadStatus.idle

	// MARK: BaseScreenlet

	override open func onCreated() {
		formView.showSubmitButton = showSubmitButton
		screenletView?.editable = self.editable
	}

	override open func onShow() {
		if autoLoad {
			if recordId != 0 {
				loadRecord()
			}
			else {
				loadForm()
			}
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		switch name {
			case DDLFormScreenlet.LoadFormAction:
				return createLoadFormInteractor()
			case DDLFormScreenlet.LoadRecordAction:
				return createLoadRecordInteractor()
			case DDLFormScreenlet.SubmitFormAction:
				return createSubmitFormInteractor()
			case DDLFormScreenlet.UploadDocumentAction:
				if sender is DDMFieldDocument {
					return createUploadDocumentInteractor(sender as! DDMFieldDocument)
				}
			default: ()
		}

		return nil
	}

	override open func onAction(name: String, interactor: Interactor, sender: AnyObject?) -> Bool {
		let result = super.onAction(name: name, interactor: interactor, sender: sender)

		if result && name == DDLFormScreenlet.UploadDocumentAction {
			let uploadInteractor = interactor as! DDLFormUploadDocumentInteractor

			ddlFormDelegate?.screenlet?(self,
					onDocumentFieldUploadStarted: uploadInteractor.document)

			switch uploadStatus {
				case .uploading(let uploadCount, let submitRequested):
					uploadStatus = .uploading(uploadCount + 1, submitRequested)

				default:
					uploadStatus = .uploading(1, false)
			}

		}

		return result
	}

	// MARK: Internal methods

	internal func createLoadFormInteractor() -> DDLFormLoadFormInteractor {
		let interactor = DDLFormLoadFormInteractor(screenlet: self)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultRecordValue = interactor.resultRecord {
				self.userId = interactor.resultUserId ?? self.userId
				self.formView.record = resultRecordValue

				self.ddlFormDelegate?.screenlet?(self,
						onFormLoaded: resultRecordValue)
			}
		}

		interactor.onFailure = {
			self.ddlFormDelegate?.screenlet?(self, onFormLoadError: $0)
		}

		return interactor
	}

	internal func createSubmitFormInteractor() -> DDLFormSubmitFormInteractor? {
		let interactor = DDLFormSubmitFormInteractor(screenlet: self, record: self.formView.record!)

		if waitForInProgressUpload(interactor) {
			return nil
		}
		if self.formView.record == nil {
			return nil
		}

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultRecordIdValue = interactor.resultRecordId {
				self.recordId = resultRecordIdValue
				self.formView.record!.recordId = resultRecordIdValue

				self.ddlFormDelegate?.screenlet?(self,
						onFormSubmitted: self.formView.record!)
			}
		}

		interactor.onFailure = {
			self.ddlFormDelegate?.screenlet?(self, onFormSubmitError: $0)
		}

		return interactor
	}

	internal func createLoadRecordInteractor() -> DDLFormLoadRecordInteractor {
		let neededStructureId: Int64? =
			formView.isRecordEmpty ? structureId : nil

		let interactor = DDLFormLoadRecordInteractor(screenlet: self, recordId: self.recordId, structureId: neededStructureId)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			// first set structure if loaded
			if let resultFormRecordValue = interactor.resultFormRecord {
				self.userId = interactor.resultFormUserId ?? self.userId
				self.formView.record = resultFormRecordValue

				self.ddlFormDelegate?.screenlet?(self,
						onFormLoaded: resultFormRecordValue)
			}

			// then set data
			if let recordValue = self.formView.record,
					let data = interactor.resultRecordData,
					let recordId = interactor.resultRecordId, interactor.lastError == nil {

				recordValue.updateCurrentValues(values: data)
				recordValue.recordId = recordId

				self.formView.refresh()

				self.ddlFormDelegate?.screenlet?(self, onRecordLoaded: recordValue)
			}
			else {
				self.ddlFormDelegate?.screenlet?(self,
						onRecordLoadError: interactor.lastError
								?? NSError.errorWithCause(.invalidServerResponse,
										message: "Could not load record data."))
			}
		}

		interactor.onFailure = {
			self.ddlFormDelegate?.screenlet?(self, onRecordLoadError: $0)
		}

		return interactor
	}

	internal func createUploadDocumentInteractor(
			_ document: DDMFieldDocument)
			-> DDLFormUploadDocumentInteractor {

		func onUploadedBytes(_ document: DDMFieldDocument, sent: UInt64, total: UInt64) {
			switch uploadStatus {
				case .uploading(_, _):
					formView.changeDocumentUploadStatus(document)

					ddlFormDelegate?.screenlet?(self,
						onDocumentField: document,
						uploadedBytes: sent,
						totalBytes: total)

				default: ()
			}
		}

		let interactor = DDLFormUploadDocumentInteractor(
				screenlet: self,
				document: document,
				onProgressClosure: onUploadedBytes)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			self.formView.changeDocumentUploadStatus(interactor.document)

			self.ddlFormDelegate?.screenlet?(self,
					onDocumentField: interactor.document,
					uploadResult: interactor.resultResponse!)

			// set new status
			switch self.uploadStatus {
				case .uploading(let uploadCount, let submitRequest)
				where uploadCount > 1:
					// more than one upload in progress
					self.uploadStatus = .uploading(uploadCount - 1, submitRequest)

				case .uploading(let uploadCount, let submitRequested)
				where uploadCount == 1:
					self.uploadStatus = .idle

					if submitRequested {
						// waiting for upload completion to submit the form
						self.submitForm()
				}

				default: ()
			}
		}

		interactor.onFailure = {
			self.formView.changeDocumentUploadStatus(interactor.document)

			if !document.validate() {
				self.formView.showField(interactor.document)
			}

			self.ddlFormDelegate?.screenlet?(self,
					onDocumentField: interactor.document,
					uploadError: $0)

			self.uploadStatus = .failed($0)
		}

		return interactor
	}

	// MARK: Public methods

	/// Performs LoadFormAction.
	@discardableResult
	open func loadForm() -> Bool {
		return performAction(name: DDLFormScreenlet.LoadFormAction)
	}

	/// Clears record definitions and refresh the form.
	open func clearForm() {
		formView.record?.clearValues()
		formView.refresh()
	}

	/// Performs LoadRecordAction.
	@discardableResult
	open func loadRecord() -> Bool {
		return performAction(name: DDLFormScreenlet.LoadRecordAction)
	}

	/// Performs SubmitFormAction.
	@discardableResult
	open func submitForm() -> Bool {
		return performAction(name: DDLFormScreenlet.SubmitFormAction)
	}

	// MARK: Private methods

	fileprivate func waitForInProgressUpload(_ interactor: Interactor) -> Bool {
		switch uploadStatus {
			case .failed(_):
				retryUploads(interactor)

				return true

			case .uploading(_, let submitRequested)
			where submitRequested:
				return true

			case .uploading(let uploadCount, let submitRequested)
			where !submitRequested:
				uploadStatus = .uploading(uploadCount, true)

				let uploadMessage = (uploadCount == 1)
						? "uploading-message-singular" : "uploading-message-plural"

				showHUDWithMessage(LocalizedString("ddlform-screenlet", key: uploadMessage, obj: self),
					forInteractor: interactor)

				return true

			default: ()
		}

		return false
	}

	fileprivate func retryUploads(_ interactor: Interactor) {
		let failedDocumentFields = formView.record?.fields.filter {
			if let fieldUploadStatus = ($0 as? DDMFieldDocument)?.uploadStatus {
				switch fieldUploadStatus {
					case .failed(_): return true
					default: ()
				}
			}

			return false
		}

		if let failedUploads = failedDocumentFields {
			if !failedUploads.isEmpty {
				showHUDWithMessage(LocalizedString("ddlform-screenlet", key: "uploading-retry", obj: self),
					forInteractor: interactor)

				for failedDocumentField in failedUploads {
					performAction(
						name: DDLFormScreenlet.UploadDocumentAction,
						sender: failedDocumentField)
				}

				uploadStatus = .uploading(failedUploads.count, true)

				return
			}
		}

		fatalError("[ERROR] Inconsistency: No failedUploads but uploadState is failed")
	}

}
