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

@objc(DDMFieldTableCell)
open class DDMFieldTableCell: UITableViewCell {

	open class func viewAsFieldCell(_ view: UIView?) -> DDMFieldTableCell? {
		if view == nil {
			return nil
		}
		else if view is DDMFieldTableCell {
			return (view as! DDMFieldTableCell)
		}

		return viewAsFieldCell(view!.superview)
	}

	open var tableView: UITableView?
	open var indexPath: IndexPath?
	open var formView: DDLFormTableView?

	open var field: DDMField? {
		didSet {
			field?.onPostValidation = onPostValidation
			onChangedField()
		}
	}

	open var isLastCell: Bool {
		var result = false

		if let indexPathValue = indexPath {
			if let rowCount = tableView?.numberOfRows(inSection: indexPathValue.section) {
				if formView!.showSubmitButton {
					result = (indexPathValue.row == rowCount - 2)
				}
				else {
					result = (indexPathValue.row == rowCount - 1)
				}
			}
		}

		return result
	}

	open var isFullyVisible: Bool {
		let cellRect = tableView!.convert(self.frame, to: tableView!.superview)
		return tableView!.frame.contains(cellRect)
	}

	// MARK: UITableViewCell

	override open func awakeFromNib() {
		let simpleTapRecognizer = UITapGestureRecognizer(target: self, action: #selector(DDMFieldTableCell.simpleTapDetected))
		addGestureRecognizer(simpleTapRecognizer)
	}

	// MARK: Internal methods

	open func onChangedField() {
	}

	open func onPostValidation(_ valid: Bool) {
	}

	open func setCellHeight(_ height: CGFloat) {
		formView?.setCellHeight(height, forField: field!)

		//FIXME Hack to fire the repaint of the cells
		tableView!.beginUpdates()
		tableView!.endUpdates()
	}

	open func resetCellHeight() -> CGFloat {
		let height = formView!.resetCellHeightForField(field!)

		//FIXME Hack to fire the repaint of the cells
		tableView!.beginUpdates()
		tableView!.endUpdates()

		return height
	}

	internal func nextCell(_ indexPath: IndexPath) -> UITableViewCell? {
		var result: UITableViewCell?

		var row = indexPath.row
		let section = indexPath.section
		let rowCount = tableView?.numberOfRows(inSection: section)

		while row < rowCount {
			row += 1
			let currentPath = IndexPath(row: row, section: section)
			if let rowCell = tableView?.cellForRow(at: currentPath) {
				if rowCell.canBecomeFirstResponder {
					result = rowCell
					break
				}
			}
		}

		return result
	}

	internal func nextCellResponder(_ currentView: UIView) -> Bool {
		var result = false

		if let currentTextInput = currentView as? UITextInput {
			switch currentTextInput.returnKeyType! {

				case .next:
					if let nextCell = nextCell(indexPath!) {
						if currentView.canResignFirstResponder {
							currentView.resignFirstResponder()

							if nextCell.canBecomeFirstResponder {
								result = nextCell.becomeFirstResponder()
							}

						}
					}

				case .send:
					formView?.userAction(name: DDLFormScreenlet.SubmitFormAction)
					result = true

				default: ()
			}
		}

		return result
	}

	internal func changeDocumentUploadStatus(_ field: DDMFieldDocument) {
	}

	internal func simpleTapDetected() {
		formView?.endEditing(true)
	}

	internal func moveSubviewsVertically(_ offsetY: CGFloat) {
		for subview in contentView.subviews {
			if offsetY == 0.0 {
				subview.transform = CGAffineTransform.identity
			}
			else {
				subview.transform = CGAffineTransform.identity.translatedBy(x: 0.0, y: offsetY)
			}
		}
	}

}
