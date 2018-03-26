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

@objc(DDLBaseFieldTextboxTableCell_default)
open class DDLBaseFieldTextboxTableCell_default: DDMFieldTableCell, UITextFieldDelegate {

	// MARK: Outlets

	@IBOutlet open var textField: UITextField?

	@IBOutlet open var label: UILabel?

	open var defaultTextField: DefaultTextField? {
		return textField as? DefaultTextField
	}

	// MARK: DDMFieldTableCell

	override open func onChangedField() {
		if field!.showLabel {
			textField?.placeholder = ""

			if let labelValue = label {
				labelValue.text = field!.label
				labelValue.isHidden = false

				moveSubviewsVertically(0.0)
			}
		}
		else {
			textField?.placeholder = field!.label

			if let labelValue = label {
				labelValue.isHidden = true

				moveSubviewsVertically(
					-(DDLFieldTextFieldHeightWithLabel - DDLFieldTextFieldHeightWithoutLabel))

				setCellHeight(DDLFieldTextFieldHeightWithoutLabel)
			}
		}

		textField?.returnKeyType = isLastCell ? .send : .next

		if field!.lastValidationResult != nil {
			onPostValidation(field!.lastValidationResult!)
		}

		if field!.currentValue != nil {
			textField?.text = field!.currentValueAsString
		}
	}

	override open func onPostValidation(_ valid: Bool) {
		super.onPostValidation(valid)

		if valid {
			defaultTextField?.setDefaultState()
		}
		else {
			defaultTextField?.setErrorState()
		}

	}

	// MARK: UITextFieldDelegate

	open func textField(_ textField: UITextField,
			shouldChangeCharactersIn range: NSRange,
			replacementString string: String) -> Bool {

		if field!.lastValidationResult != nil && !field!.lastValidationResult! {
			field!.lastValidationResult = true
			onPostValidation(true)
		}

		return true
	}

}
