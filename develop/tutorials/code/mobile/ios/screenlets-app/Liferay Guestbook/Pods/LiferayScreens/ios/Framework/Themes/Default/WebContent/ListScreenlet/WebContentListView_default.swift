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

@objc(WebContentListView_default)
open class WebContentListView_default: WebContentListTableView {

	// MARK: BaseScreenletView

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	override open func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
		if let entry = object as? WebContent {
			cell.textLabel?.text = entry.title
			cell.accessoryType = .disclosureIndicator
			cell.accessoryView = nil
		}
	}

	override open func doFillInProgressCell(row: Int, cell: UITableViewCell) {
		cell.textLabel?.text = "..."
		cell.accessoryType = .none

		if let image = Bundle.imageInBundles(
				name: "default-hourglass",
				currentClass: type(of: self)) {

			cell.accessoryView = UIImageView(image: image)
			cell.accessoryView?.frame = CGRect(x: 0, y: 0, width: image.size.width, height: image.size.height)
		}
	}

}
