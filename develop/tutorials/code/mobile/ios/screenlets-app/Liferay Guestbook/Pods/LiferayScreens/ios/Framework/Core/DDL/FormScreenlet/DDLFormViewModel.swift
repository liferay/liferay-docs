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

@objc(DDLFormViewModel)
public protocol DDLFormViewModel {

	/// Show or hide the submit button of the form.
	var showSubmitButton: Bool { get set }

	/// Record to be loaded if exists.
	var record: DDLRecord? { get set }

	/// Gets if the record is empty or not.
	var isRecordEmpty: Bool { get }

	/// Called for validate the form data.
	///
	/// - Parameter autoscroll
	/// - Returns: error if exists.
	func validateForm(autoscroll: Bool) -> ValidationError?

}
