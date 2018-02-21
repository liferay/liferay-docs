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

@objc(DDLFormView)
open class DDLFormView: BaseScreenletView, DDLFormViewModel {

	// MARK: DDLFormViewModel

	open var showSubmitButton = true

	open var record: DDLRecord?

	open var isRecordEmpty: Bool {
		return (record == nil) ? true : record!.fields.isEmpty
	}

	// MARK: Public methods

	open func refresh() {
	}

	open func validateForm(autoscroll: Bool) -> ValidationError? {
		var firstError: ValidationError?
		var firstFailedField: DDMField?

		forEachField {
			if !$0.validate() {
				if firstFailedField == nil {
					firstFailedField = $0
				}
				if firstError == nil {
					let fmt = LocalizedString("ddlform-screenlet", key: "validation-field", obj: self)
					let msg = NSString(format: fmt as NSString, $0.label).description
					firstError = ValidationError(msg)
				}
			}
		}

		if autoscroll && firstFailedField != nil {
			showField(firstFailedField!)
		}

		return firstError
	}

	open func getField(_ index: Int) -> DDMField? {
		return (record == nil) ? nil : record!.fields[index]
	}

	open func getFieldIndex(_ field: DDMField) -> Int? {
		return (record == nil) ? nil : record!.fields.index(of: field)
	}

	// MARK: Internal methods

	internal func changeDocumentUploadStatus(_ field: DDMFieldDocument) {
	}

	internal func forEachField(_ body: (DDMField) -> Void) {
		if let recordValue = record {
			for field in recordValue.fields {
				body(field)
			}
		}
	}

	internal func showField(_ field: DDMField) {
	}

}
