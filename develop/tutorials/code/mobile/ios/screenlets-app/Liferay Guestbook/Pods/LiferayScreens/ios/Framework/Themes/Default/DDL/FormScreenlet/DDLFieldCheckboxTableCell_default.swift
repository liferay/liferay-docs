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

@objc(DDLFieldCheckboxTableCell_default)
open class DDLFieldCheckboxTableCell_default: DDMFieldTableCell {

	// MARK: Outlets

	@IBOutlet open var switchView: UISwitch?

	@IBOutlet open var label: UILabel?

	// MARK: Actions

	@IBAction fileprivate func switchValueChanged(_ sender: AnyObject) {
		field?.currentValue = switchView?.isOn as AnyObject?

		if field!.lastValidationResult != nil && !field!.lastValidationResult! {
			field!.lastValidationResult = true
			onPostValidation(true)
		}
	}

	// MARK: DDMFieldTableCell

	override open var canBecomeFirstResponder: Bool {
		return false
	}

	override open func onChangedField() {
		if let boolField = field as? DDMFieldBoolean {
			switchView?.isOn = boolField.currentValue as! Bool
			label?.text = boolField.label

			if boolField.lastValidationResult != nil {
				onPostValidation(boolField.lastValidationResult!)
			}
		}
	}

	override open func onPostValidation(_ valid: Bool) {
		super.onPostValidation(valid)

		label?.textColor = valid ? UIColor.black : UIColor.red
	}

}
