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

@objc(DDLFieldTextareaTableCell_default)
open class DDLFieldTextareaTableCell_default: DDMFieldTableCell, UITextViewDelegate {

	// MARK: Outlets

	@IBOutlet open var textView: UITextView? {
		didSet {
			textView?.layer.cornerRadius = 4.0
			textView?.layer.borderWidth = 1.0
			textView?.layer.borderColor = UIColor.lightGray.cgColor
		}
	}

	@IBOutlet open var placeholder: UILabel?

	@IBOutlet open var label: UILabel?

	@IBOutlet open var separator: UIView?

	fileprivate var originalTextViewRect = CGRect.zero
	fileprivate var originalSeparatorY: CGFloat?
	fileprivate var originalSeparatorDistance: CGFloat?

	// MARK: DDMFieldTableCell

	override open var canBecomeFirstResponder: Bool {
		return textView!.canBecomeFirstResponder
	}

	override open func becomeFirstResponder() -> Bool {
		return textView!.becomeFirstResponder()
	}

	override open func onChangedField() {
		if let stringField = field as? DDMFieldString {

			if stringField.currentValue != nil {
				textView?.text = stringField.currentValueAsString
			}

			var currentHeight = self.frame.size.height

			if stringField.showLabel {
				placeholder?.text = ""

				if let labelValue = label {
					labelValue.text = stringField.label
					labelValue.isHidden = false

					moveSubviewsVertically(0.0)
				}
			}
			else {
				placeholder?.text = stringField.label
				placeholder?.alpha = (textView?.text == "") ? 1.0 : 0.0

				if let labelValue = label {
					labelValue.isHidden = true

					moveSubviewsVertically(
						-(DDLFieldTextFieldHeightWithLabel - DDLFieldTextFieldHeightWithoutLabel))

					setCellHeight(DDLFieldTextFieldHeightWithoutLabel)

					currentHeight = DDLFieldTextFieldHeightWithoutLabel
				}
			}

			textView?.returnKeyType = isLastCell ? .send : .next

			originalTextViewRect = textView!.frame

			if separator != nil {
				originalSeparatorY = separator!.frame.origin.y
				originalSeparatorDistance = currentHeight - originalSeparatorY!
			}

			if stringField.lastValidationResult != nil {
				onPostValidation(stringField.lastValidationResult!)
			}
		}
	}

	override open func onPostValidation(_ valid: Bool) {
		super.onPostValidation(valid)

	}

	// MARK: UITextViewDelegate

	open func textViewShouldBeginEditing(_ textView: UITextView) -> Bool {
		let heightLabelOffset: CGFloat =
			DDLFieldTextFieldHeightWithLabel - DDLFieldTextFieldHeightWithoutLabel

		let newCellHeight = DDLFieldTextareaExpandedCellHeight +
			(field!.showLabel ? heightLabelOffset : 0.0)

		setCellHeight(newCellHeight)

		if let value = originalSeparatorDistance {
			separator?.frame.origin.y = newCellHeight - value
		}

		textView.frame = CGRect(
			x: originalTextViewRect.origin.x,
			y: originalTextViewRect.origin.y,
			width: originalTextViewRect.size.width,
			height: DDLFieldTextareaExpandedTextViewHeight)

		textView.layer.borderColor = DefaultThemeBasicBlue.cgColor
		formView!.firstCellResponder = textView

		return true
	}

	open func textViewDidEndEditing(_ textView: UITextView) {
		if let originalSeparatorY = originalSeparatorY {
			separator?.frame.origin.y = originalSeparatorY
		}

		textView.frame = originalTextViewRect

		let heightLabelOffset: CGFloat =
			DDLFieldTextFieldHeightWithLabel - DDLFieldTextFieldHeightWithoutLabel

		let height = resetCellHeight()

		if !field!.showLabel {
			setCellHeight(height - heightLabelOffset)
		}

		textView.layer.borderColor = UIColor.lightGray.cgColor
	}

	open func textView(_ textView: UITextView,
	                     shouldChangeTextIn range: NSRange,
	                     replacementText text: String) -> Bool {

		var result = false

		if text == "\n" {
			textViewDidEndEditing(textView)
			_ = nextCellResponder(textView)
			result = false
		}
		else {
			result = true

			let oriText = textView.text as NSString
			let newText = oriText.replacingCharacters(in: range, with: text)

			placeholder!.changeVisibility(visible: newText != "")

			field?.currentValue = newText as AnyObject?

			if field!.lastValidationResult != nil && !field!.lastValidationResult! {
				field!.lastValidationResult = true
				onPostValidation(true)
			}
		}

		return result
	}

}
