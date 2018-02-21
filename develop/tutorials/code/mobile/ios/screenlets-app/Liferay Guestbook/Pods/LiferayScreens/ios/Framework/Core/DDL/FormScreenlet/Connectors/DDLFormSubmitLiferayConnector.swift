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
import LRMobileSDK

open class DDLFormSubmitLiferayConnector: ServerConnector {

	open var groupId: Int64?
	open var userId: Int64?
	open var recordId: Int64?
	open var recordSetId: Int64?

	open var autoscrollOnValidation = true

	open var resultRecordId: Int64?
	open var resultAttributes: NSDictionary?

	fileprivate let values: [String:AnyObject]
	fileprivate let viewModel: DDLFormViewModel?

	// MARK: Initializers

	public init(values: [String:AnyObject], viewModel: DDLFormViewModel?) {
		self.values = values
		self.viewModel = viewModel

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		guard error == nil else {
			return error
		}
		guard (userId ?? 0) != 0 else {
			return ValidationError("ddlform-screenlet", "undefined-user")
		}
		guard groupId != nil else {
			return ValidationError("ddlform-screenlet", "undefined-group")
		}
		guard recordSetId != nil else {
			return ValidationError("ddlform-screenlet", "undefined-recordset")
		}
		guard !values.isEmpty else {
			return ValidationError("ddlform-screenlet", "undefined-values")
		}

		return viewModel?.validateForm(autoscroll: autoscrollOnValidation)
	}

}

open class Liferay62DDLFormSubmitConnector: DDLFormSubmitLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let service = LRDDLRecordService_v62(session: session)

		let serviceContextAttributes = [
			"userId": NSNumber(value: userId!),
			"scopeGroupId": NSNumber(value: groupId!)]

		let serviceContextWrapper = LRJSONObjectWrapper(jsonObject: serviceContextAttributes)

		do {
			let recordDictionary: [AnyHashable: Any]?

			if recordId == nil {
				recordDictionary = try service?.addRecord(withGroupId: groupId!,
					recordSetId: recordSetId!,
					display: 0,
					fieldsMap: values,
					serviceContext: serviceContextWrapper)
			}
			else {
				recordDictionary = try service?.updateRecord(withRecordId: recordId!,
					display: 0,
					fieldsMap: values,
					mergeFields: true,
					serviceContext: serviceContextWrapper)
			}

			if let recordIdValue = (recordDictionary?["recordId"] as AnyObject).int64Value {
				resultRecordId = recordIdValue
				resultAttributes = recordDictionary as NSDictionary?
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not submit ddl form.")
			}
		}
		catch let error as NSError {
			lastError = error
			resultRecordId = nil
			resultAttributes = nil
		}
	}

}

open class Liferay70DDLFormSubmitConnector: DDLFormSubmitLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let service = LRDDLRecordService_v7(session: session)

		let serviceContextAttributes = [
			"userId": NSNumber(value: userId!),
			"scopeGroupId": NSNumber(value: groupId!)]

		let serviceContextWrapper = LRJSONObjectWrapper(jsonObject: serviceContextAttributes)

		do {
			let recordDictionary: [AnyHashable: Any]?

			if recordId == nil {
				recordDictionary = try service?.addRecord(withGroupId: groupId!,
					recordSetId: recordSetId!,
					display: 0,
					fieldsMap: values,
					serviceContext: serviceContextWrapper)
			}
			else {
				recordDictionary = try service?.updateRecord(withRecordId: recordId!,
					display: 0,
					fieldsMap: values,
					mergeFields: false,
					serviceContext: serviceContextWrapper)
			}

			if let recordIdValue = (recordDictionary?["recordId"] as AnyObject).int64Value {
				resultRecordId = recordIdValue
				resultAttributes = recordDictionary as NSDictionary?
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not submit ddl form.")
			}
		}
		catch let error as NSError {
			lastError = error
			resultRecordId = nil
			resultAttributes = nil
		}
	}

}
