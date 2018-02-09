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

@objc(DDLFieldSelectTableCell_default)
open class DDLFieldSelectTableCell_default: DDLBaseFieldTextboxTableCell_default,
		UITableViewDataSource, UITableViewDelegate {

	let rowHeight: CGFloat = 50

	open var cellBackgroundColor: UIColor = DefaultThemeBasicBlue

	open var options: [DDMFieldStringWithOptions.Option] = []

	open var selectedOptions: [DDMFieldStringWithOptions.Option] {
		return field?.currentValue as? [DDMFieldStringWithOptions.Option] ?? []
	}

	// MARK: UITableViewCell

	override open func  awakeFromNib() {
		super.awakeFromNib()

		defaultTextField?.onRightButtonClick = { [weak self] in
			self?.textField?.becomeFirstResponder()
		}
	}

	// MARK: DDLBaseFieldTextboxTableCell

	override open func onChangedField() {
		super.onChangedField()

		if let stringField = field as? DDMFieldStringWithOptions {
			textField?.text = stringField.currentValueAsLabel

			setFieldPresenter(stringField)
		}
	}

	// MARK: Private methods

	fileprivate func setFieldPresenter(_ field: DDMFieldStringWithOptions) {

		options = field.options

		//Fill the textfield with the selected options (If we are editing an existing record)
		textField?.text = selectedOptions.map { $0.label }
					.sorted()
					.reduce("", { $0 + " " + $1 })
					.trim()

		let tableView = createPresenterTableView(multipleSelection: field.multiple)

		let optionsPresenter = DTViewPresenter(view: tableView)!

		optionsPresenter.presenterView.backgroundColor = UIColor.white
		optionsPresenter.presenterView.layer.borderColor = UIColor.lightGray.cgColor
		optionsPresenter.presenterView.layer.borderWidth = 1.5

		textField?.dt_setPresenter(optionsPresenter)
	}

	open func createPresenterTableView(multipleSelection multiple: Bool) -> UITableView {

		let tableView = UITableView(
			frame: CGRect(x: 0, y: 0, width: 0,
				height: min(300, rowHeight * CGFloat(options.count))))

		tableView.dataSource = self
		tableView.delegate = self
		tableView.rowHeight = rowHeight
		tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
		tableView.allowsMultipleSelection = multiple

		return tableView
	}

	open func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return options.count
	}

	// MARK: UITableViewDataSource

	open func tableView(
			_ tableView: UITableView,
			cellForRowAt indexPath: IndexPath) -> UITableViewCell {

		let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)

		let backgroundView = UIView()
		backgroundView.backgroundColor = cellBackgroundColor

		cell.selectedBackgroundView = backgroundView

		cell.textLabel?.text = options[indexPath.row].label
		cell.textLabel?.textAlignment = .center
		cell.textLabel?.highlightedTextColor = .white

		//Select cells from current selected options
		if selectedOptions.contains(options[indexPath.row]) {
			tableView.selectRow(at: indexPath, animated: false, scrollPosition: .middle)
		}

		return cell
	}

	// MARK: UITableViewDelegate

	open func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		let selectedIndexPaths = tableView.indexPathsForSelectedRows
		updateFieldCurrentValue(selectedIndexPaths)
	}

	open func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
		let selectedIndexPaths = tableView.indexPathsForSelectedRows
		updateFieldCurrentValue(selectedIndexPaths)
	}

	open func updateFieldCurrentValue(_ selectedIndexPaths: [IndexPath]?) {
		let currentValue = selectedIndexPaths?
			.map { self.options[$0.row].label }
			.sorted()
			.reduce("", { $0 + "," + $1 }) ?? ""

		field?.currentValue = currentValue as AnyObject?
		textField?.text = currentValue
				.replacingOccurrences(of: ",", with: " ")
				.trim()
	}

}
