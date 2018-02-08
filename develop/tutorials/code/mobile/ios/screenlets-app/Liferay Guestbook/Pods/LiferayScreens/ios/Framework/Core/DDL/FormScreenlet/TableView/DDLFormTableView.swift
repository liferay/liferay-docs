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

@objc(DDLFormTableView)
open class DDLFormTableView: DDLFormView,
		UITableViewDataSource, UITableViewDelegate, KeyboardLayoutable {

	// MARK: Outlets

	@IBOutlet open var tableView: UITableView?

	override open var record: DDLRecord? {
		didSet {
			if cellHeights.isEmpty {
				registerFieldCells()
			}

			forEachField {
				self.registerCustomEditor($0)
				self.resetCellHeightForField($0)
			}

			refresh()
		}
	}

	override open var themeName: String {
		didSet {
			registerFieldCells()
		}
	}

	internal var firstCellResponder: UIResponder?

	internal var cellHeights: [String : (registered: CGFloat, current: CGFloat)] = [:]
	internal var submitButtonHeight: CGFloat = 0.0

	internal var originalFrame: CGRect?
	internal var keyboardManager = KeyboardManager()

	// MARK: DDLFormView

	override open func refresh() {
		tableView!.reloadData()
	}

	override open func resignFirstResponder() -> Bool {
		var result = false

		if let cellValue = firstCellResponder {
			result = cellValue.resignFirstResponder()
			if result {
				firstCellResponder = nil
			}
		}

		return result
	}

	override open func becomeFirstResponder() -> Bool {
		var result = false

		let rowCount = tableView!.numberOfRows(inSection: 0)
		var indexPath = IndexPath(row: 0, section: 0)

		while !result && indexPath.row < rowCount {
			if let cell = tableView!.cellForRow(at: indexPath) {
				if cell.canBecomeFirstResponder {
					result = cell.becomeFirstResponder()
				}
			}

			indexPath = IndexPath(
					row: (indexPath.row + 1),
					section: indexPath.section)
		}

		return result
	}

	override open func onShow() {
		keyboardManager.registerObserver(self)
	}

	override open func onHide() {
		keyboardManager.unregisterObserver()
	}

	override internal func showField(_ field: DDMField) {
		if let row = getFieldIndex(field) {
			tableView!.scrollToRow(
				at: IndexPath(row: row, section: 0),
				at: .top, animated: true)
		}
	}

	override internal func changeDocumentUploadStatus(_ field: DDMFieldDocument) {
		if let row = getFieldIndex(field) {
			if let cell = tableView!.cellForRow(
					at: IndexPath(row: row, section: 0)) as? DDMFieldTableCell {
				cell.changeDocumentUploadStatus(field)
			}
		}
	}

	// MARK: KeyboardLayoutable

	open func layoutWhenKeyboardShown(_ keyboardHeight: CGFloat,
			animation:(time: NSNumber, curve: NSNumber)) {

		let cell = DDMFieldTableCell.viewAsFieldCell(firstCellResponder as? UIView)
		var actualKeyboardHeight = keyboardHeight

		var scrollDone = false
		let scrollClosure = { (completedAnimation: Bool) -> Void in
			if let cellValue = cell {
				if !cellValue.isFullyVisible {
					cellValue.tableView!.scrollToRow(at: cellValue.indexPath!,
							at: .middle,
							animated: true)
				}
			}
		}

		if let textInput = firstCellResponder as? UITextInputTraits {

			var shouldWorkaroundUIPickerViewBug = false
			if let cellValue = cell {
				shouldWorkaroundUIPickerViewBug =
						cellValue.field!.editorType == DDMField.Editor.Document ||
						cellValue.field!.editorType == DDMField.Editor.Select
			}

			if shouldWorkaroundUIPickerViewBug {
				//FIXME
				// Height used by UIPickerView is 216, when the standard keyboard have 253
				actualKeyboardHeight = 253
			}
			else if textInput.autocorrectionType == UITextAutocorrectionType.default ||
				textInput.autocorrectionType == UITextAutocorrectionType.yes {

				actualKeyboardHeight += KeyboardManager.defaultAutocorrectionBarHeight
			}

			let absoluteFrame = adjustRectForCurrentOrientation(convert(frame, to: window!))
			let screenHeight = adjustRectForCurrentOrientation(UIScreen.main.bounds).height

			if (absoluteFrame.origin.y + absoluteFrame.size.height >
					screenHeight - actualKeyboardHeight) || originalFrame != nil {

				let newHeight = screenHeight - actualKeyboardHeight + absoluteFrame.origin.y

				if Int(newHeight) != Int(self.frame.size.height) {
					if originalFrame == nil {
						originalFrame = frame
					}

					scrollDone = true

					UIView.animate(withDuration: animation.time.doubleValue,
							delay: 0,
							options: UIViewAnimationOptions(rawValue: animation.curve.uintValue),
							animations: {
								self.frame = CGRect(
										x: self.frame.origin.x,
										y: self.frame.origin.y,
										width: self.frame.size.width,
										height: newHeight)
							},
							completion: scrollClosure)
				}
			}
		}

		if !scrollDone {
			scrollClosure(true)
		}
	}

	// MARK: Public methods

	open func layoutWhenKeyboardHidden() {
		if let originalFrameValue = originalFrame {
			self.frame = originalFrameValue
			originalFrame = nil
		}
	}

	// MARK: UITableViewDataSource

	open func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		if isRecordEmpty {
			return 0
		}

		return record!.fields.count + (showSubmitButton ? 1 : 0)
	}

	open func tableView(_ tableView: UITableView,
			cellForRowAt indexPath: IndexPath)
			-> UITableViewCell {

		var cell: DDMFieldTableCell?
		let row = indexPath.row

		if row == record!.fields.count {
			cell = tableView.dequeueReusableCell(withIdentifier: "SubmitButton")
					as? DDMFieldTableCell

			cell!.formView = self
		}
		else if let field = getField(row) {
			cell = tableView.dequeueReusableCell(withIdentifier: field.name)
					as? DDMFieldTableCell

			if cell == nil {
				cell = tableView.dequeueReusableCell(
						withIdentifier: field.editorType.toCapitalizedName()) as? DDMFieldTableCell
			}

			if let cellValue = cell {
				cellValue.formView = self
				cellValue.tableView = tableView
				cellValue.indexPath = indexPath
				cellValue.field = field
			}
			else {
				print("ERROR: Cell XIB is not registerd for type \(field.editorType.toCapitalizedName())\n")
			}
		}

		return cell!
	}

	open func tableView(_ tableView: UITableView,
			heightForRowAt indexPath: IndexPath)
			-> CGFloat {

		let row = indexPath.row

		return (row == record!.fields.count) ? submitButtonHeight : cellHeightForField(getField(row)!)
	}

	// MARK: Internal methods

	internal func registerFieldCells() {
		let bundles = Bundle.allBundles(type(of: self))

		for fieldEditor in DDMField.Editor.all() {
			for bundle in bundles {
				let cellId = fieldEditor.toCapitalizedName()

				if let cellView = registerEditorCellInBundle(bundle,
						nibName: "DDLField\(cellId)TableCell",
						cellId: cellId) {
					cellHeights[cellId] = (cellView.bounds.size.height, cellView.bounds.size.height)

					break
				}
			}
		}

		if showSubmitButton {
			for bundle in bundles {
				if let cellView = registerEditorCellInBundle(bundle,
						nibName: "DDLSubmitButtonTableCell",
						cellId: "SubmitButton") {
					submitButtonHeight = cellView.bounds.size.height

					break
				}
			}
		}
	}

	@discardableResult
	internal func registerCustomEditor(_ field: DDMField) -> Bool {
		let bundles = Bundle.allBundles(type(of: self))

		for bundle in bundles {
			if let cellView = registerEditorCellInBundle(bundle,
					nibName: "DDLCustomField\(field.name)TableCell",
					cellId: field.name) {

				setCellHeight(cellView.bounds.size.height, forField: field)

				return true
			}
		}

		return false
	}

	internal func registerEditorCellInBundle(_ bundle: Bundle,
			nibName: String,
			cellId: String)
			-> UITableViewCell? {

		let existingNibName = { (themeName: String) -> String? in
			let themedNibName = "\(nibName)_\(themeName)"

			return bundle.path(forResource: themedNibName, ofType: "nib") != nil
						? themedNibName
						: nil
		}

		let themedNibName = existingNibName(self.themeName)
				?? existingNibName("default")

		if let themedNibNameValue = themedNibName {
			let nib = UINib(nibName: themedNibNameValue, bundle: bundle)

			tableView?.register(nib, forCellReuseIdentifier: cellId)

			let views = nib.instantiate(withOwner: nil, options: nil)

			if let cell = views.first as? UITableViewCell {
				return cell
			}
			else {
				print("ERROR: Cell XIB \(themedNibName) couldn't be registered (no root view?)\n")
			}
		}

		return nil
	}

	internal func cellHeightForField(_ field: DDMField) -> CGFloat {
		var result: CGFloat = 0.0

		if let cellHeight = cellHeights[field.name] {
			result = cellHeight.current
		}
		else if let typeHeight = cellHeights[field.editorType.toCapitalizedName()] {
			result = typeHeight.current
		}
		else {
			print("ERROR: Height doesn't found for field \(field)\n")
		}

		return result
	}

	internal func setCellHeight(_ height: CGFloat, forField field: DDMField) {
		if let cellHeight = cellHeights[field.name] {
			cellHeights[field.name] = (cellHeight.registered, height)
		}
		else {
			cellHeights[field.name] = (height, height)
		}
	}

	@discardableResult
	internal func resetCellHeightForField(_ field: DDMField) -> CGFloat {
		var result: CGFloat = 0.0

		if let cellHeight = cellHeights[field.name] {
			cellHeights[field.name] = (cellHeight.registered, cellHeight.registered)
			result = cellHeight.registered
		}
		else if let typeHeight = cellHeights[field.editorType.toCapitalizedName()] {
			cellHeights[field.name] = (typeHeight.registered, typeHeight.registered)
			result = typeHeight.registered
		}

		return result
	}

}
