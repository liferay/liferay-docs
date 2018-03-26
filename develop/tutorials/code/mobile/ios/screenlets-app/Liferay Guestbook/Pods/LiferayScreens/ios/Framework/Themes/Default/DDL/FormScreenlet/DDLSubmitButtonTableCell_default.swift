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

@objc(DDLSubmitButtonTableCell_default)
open class DDLSubmitButtonTableCell_default: DDMFieldTableCell {

	// MARK: Outlets

	@IBOutlet open var submitButton: UIButton?

	// MARK: Actions

	@IBAction fileprivate func submitButtonAction(_ sender: AnyObject) {
		formView!.userActionWithSender(sender)
	}

	// MARK: DDMFieldTableCell

	override open func awakeFromNib() {
		super.awakeFromNib()

		setButtonDefaultStyle(submitButton)

		submitButton?.replaceAttributedTitle(
				LocalizedString("default", key: "ddlform-submit-button", obj: self),
				forState: .normal)
	}

	override open var canBecomeFirstResponder: Bool {
		return false
	}

}
