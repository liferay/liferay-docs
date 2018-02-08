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

@objc(CommentListView_default)
open class CommentListView_default: BaseListTableView, CommentListViewModel {

	let CommentCellId = "commentCell"

	var commentListScreenlet: CommentListScreenlet {
		return screenlet as! CommentListScreenlet
	}

	// MARK: BaseScreenletView

	override open func onShow() {
		super.onShow()
		self.tableView?.tableFooterView = UIView()
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	// MARK: CommentListViewModel

	open func addComment(_ comment: Comment) {
		addRow(BaseListView.DefaultSection, element: comment)
		let indexPath = IndexPath(row: (rows[BaseListView.DefaultSection]?.count)! - 1,
			section: 0)
		tableView?.beginUpdates()
		tableView?.insertRows(at: [indexPath], with: .automatic)
		tableView?.endUpdates()
		tableView?.scrollToRow(at: indexPath, at: .bottom, animated: true)
	}

	open func deleteComment(_ comment: Comment) {
		let row = rows[BaseListView.DefaultSection]?.index(where: {
			(($0 as? Comment)?.commentId ?? 0) == comment.commentId})
		if let row = row {
			deleteRow(BaseListView.DefaultSection, row: row)
			let indexPath = IndexPath(row: row, section: 0)
			tableView?.beginUpdates()
			tableView?.deleteRows(at: [indexPath], with: .automatic)
			tableView?.endUpdates()
		}
	}

	open func updateComment(_ comment: Comment) {
		let row = rows[BaseListView.DefaultSection]?.index(where: {
			(($0 as? Comment)?.commentId ?? 0) == comment.commentId})
		if let row = row {
			updateRow(BaseListView.DefaultSection, row: row, element: comment)
			let indexPath = IndexPath(row: row, section: 0)
			tableView?.scrollToRow(at: indexPath, at: .none, animated: true)
			tableView?.beginUpdates()
			tableView?.reloadRows(at: [indexPath], with: .automatic)
			tableView?.endUpdates()
		}
	}

	// MARK: BaseListTableView

	override open func doRegisterCellNibs() {
		let nib = Bundle.nibInBundles(
			name: "CommentTableViewCell_default", currentClass: type(of: self))

		if let commentNib = nib {
			tableView?.register(commentNib, forCellReuseIdentifier: CommentCellId)
		}
	}

	override open func doDequeueReusableCell(row: Int, object: AnyObject?)
			-> UITableViewCell {
		let cell = super.doDequeueReusableCell(row: row, object: object)
		if let commentCell = cell as? CommentTableViewCell_default {
			commentCell.commentDisplayScreenlet?.presentingViewController =
				self.presentingViewController
			commentCell.commentDisplayScreenlet?.delegate = screenlet
				as? CommentDisplayScreenletDelegate
		}
		return cell
	}

	override open func doGetCellId(row: Int, object: AnyObject?) -> String {
		return CommentCellId
	}

	override open func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
		if let comment = object as? Comment, let commentCell = cell as? CommentTableViewCell_default {
			commentCell.commentDisplayScreenlet?.comment = comment
			cell.accessoryType = .none
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
			cell.accessoryView!.frame = CGRect(x: 0, y: 0, width: image.size.width, height: image.size.height)
		}
	}

	// MARK: BaseListView

	open override func onClearRows(_ oldRows: [String : [AnyObject?]]) {
		super.onClearRows(oldRows)
		self.tableView?.tableFooterView = UIView()
	}

	// MARK: UITableViewDelegate

	open func tableView(_ tableView: UITableView, heightForRowAtIndexPath indexPath: IndexPath)
			-> CGFloat {
		let comment = rows[BaseListView.DefaultSection]?[indexPath.row] as? Comment
		return CommentDisplayView_default.heightForText(comment?.htmlBody,
			width: tableView.frame.width)
	}

	open func tableView(_ tableView: UITableView, canEditRowAtIndexPath indexPath: IndexPath)
			-> Bool {
		if let comment = rows[BaseListView.DefaultSection]?[indexPath.row] as? Comment {
			return comment.canEdit || comment.canDelete
		}

		return false
	}

	open func tableView(_ tableView: UITableView,
			editActionsForRowAtIndexPath indexPath: IndexPath) -> [AnyObject]? {
		let editRowAction = UITableViewRowAction(style: UITableViewRowActionStyle.default,
				title: "Edit", handler: {_, indexPath in
			let cell = tableView.cellForRow(at: indexPath) as? CommentTableViewCell_default
			cell?.commentDisplayScreenlet?.editComment()
			tableView.setEditing(false, animated: true)
		})
		editRowAction.backgroundColor = UIColor(red: 0, green: 0.7216, blue: 0.8784, alpha: 1.0)

		let deleteRowAction = UITableViewRowAction(style: UITableViewRowActionStyle.default,
				title: "Delete", handler: {_, indexPath in
			let cell = tableView.cellForRow(at: indexPath) as? CommentTableViewCell_default
			cell?.commentDisplayScreenlet?.deleteComment()
			tableView.setEditing(false, animated: true)
		})
		deleteRowAction.backgroundColor = UIColor(red: 0.7686, green: 0.2431, blue: 0, alpha: 1.0)

		return [deleteRowAction, editRowAction]
	}
}
