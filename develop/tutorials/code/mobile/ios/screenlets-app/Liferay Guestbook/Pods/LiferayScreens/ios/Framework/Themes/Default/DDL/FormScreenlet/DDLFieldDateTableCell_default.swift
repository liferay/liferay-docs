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

#if LIFERAY_SCREENS_FRAMEWORK
	import DTPickerPresenter
#endif

@objc(DDLFieldDateTableCell_default)
open class DDLFieldDateTableCell_default: DDLBaseFieldTextboxTableCell_default {

	// MARK: UITableViewCell

	override open func awakeFromNib() {
		super.awakeFromNib()

		defaultTextField?.onRightButtonClick = { [weak self] in
			self?.textField?.becomeFirstResponder()
		}
	}

	// MARK: DDLBaseFieldTextboxTableCell

	override open func onChangedField() {
		super.onChangedField()

		if let fieldValue = field {
			setFieldPresenter(fieldValue)

			if fieldValue.currentValue != nil {
				textField?.text = fieldValue.currentValueAsLabel
			}
		}
	}

	// MARK: Private methods

	fileprivate func setFieldPresenter(_ field: DDMField) {

		func onChange(_ selectedDate: Date?) {
			field.currentValue = selectedDate as AnyObject?
			textField!.text = field.currentValueAsLabel

			let fullRange = NSRange(location: 0, length: textField!.text!.characters.count)

			_ = textField(textField!,
				shouldChangeCharactersIn: fullRange,
				replacementString: textField!.text!)
		}

		let presenter = DTDatePickerPresenter(change: onChange)!

		presenter.datePicker.datePickerMode = .date
		presenter.datePicker.timeZone = TimeZone(abbreviation: "GMT")
		presenter.datePicker.backgroundColor = .white
		presenter.datePicker.layer.borderColor = UIColor.lightGray.cgColor
		presenter.datePicker.layer.borderWidth = 1.5

		if let currentDate = field.currentValue as? Date {
			presenter.datePicker.setDate(currentDate, animated: false)
		}

		textField?.dt_setPresenter(presenter)
	}

}
