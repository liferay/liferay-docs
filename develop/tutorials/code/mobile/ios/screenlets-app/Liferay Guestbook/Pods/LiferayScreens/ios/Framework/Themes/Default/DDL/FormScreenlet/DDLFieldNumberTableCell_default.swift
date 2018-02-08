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

@objc(DDLFieldNumberTableCell_default)
open class DDLFieldNumberTableCell_default: DDLBaseFieldTextboxTableCell_default {

	// MARK: Outlets

	@IBOutlet open var stepper: UIStepper?

	// MARK: Actions

	@IBAction fileprivate func stepperChanged(_ sender: AnyObject) {
		field!.currentValue = NSDecimalNumber(value: stepper!.value as Double)
		textField?.text = field!.currentValueAsString
	}

	// MARK: DDLBaseFieldTextboxTableCell

	override open func awakeFromNib() {
		super.awakeFromNib()

		stepper?.maximumValue = Double(UInt16.max)
	}

	override open func onChangedField() {
		super.onChangedField()

		if let numberField = field as? DDMFieldNumber {
			if let currentValue = numberField.currentValue as? NSNumber {
				stepper?.value = currentValue.doubleValue
			}
			textField!.keyboardType = (numberField.isDecimal) ? .decimalPad : .numberPad
		}
	}

	override open func textField(_ textField: UITextField,
			shouldChangeCharactersIn range: NSRange,
			replacementString string: String) -> Bool {

		let newText = (textField.text! as NSString).replacingCharacters(in: range,
				with:string)

		if newText != "" {
			field!.currentValueAsString = newText
		}
		else {
			field!.currentValue = NSDecimalNumber(value: 0 as Double)
		}

		stepper?.value = Double(field!.currentValue as! NSNumber)

		return super.textField(textField,
				shouldChangeCharactersIn: range,
				replacementString: string)
	}

	// MARK: UITextFieldDelegate

	open func textFieldShouldReturn(_ textField: UITextField) -> Bool {
		return nextCellResponder(textField)
	}

}
