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

@objc(DDLFieldTextTableCell_default)
open class DDLFieldTextTableCell_default: DDLBaseFieldTextboxTableCell_default {

	// MARK: DDLBaseFieldTextboxTableCell

	override open func textField(_ textField: UITextField,
			shouldChangeCharactersIn range: NSRange,
			replacementString string: String)
			-> Bool {

		let newText = (textField.text! as NSString).replacingCharacters(in: range,
				with:string)

		field!.currentValue = newText as AnyObject?

		return super.textField(textField, shouldChangeCharactersIn: range,
				replacementString: string)
	}

	// MARK: UITextFieldDelegate

	open func textFieldShouldReturn(_ textField: UITextField) -> Bool {
		return nextCellResponder(textField)
	}

}
