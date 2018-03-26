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

public enum CommentDisplayState_default {
	case normal
	case deleting
	case editing
}

@objc(CommentDisplayView_default)
open class CommentDisplayView_default: BaseScreenletView, CommentDisplayViewModel {

	//Left/right UILabel padding
	fileprivate static let LabelPadding: CGFloat = 16

	//This fixed height equals the sum of UserPortraitScreenlet height, plus UILabel insets,
	//plus margin between user portrait - text view, plus one pixel for rounding
	fileprivate static let FixedHeight: CGFloat = 50 + 8 + 8 + 8 + 1

	//Top/bottom UILabel insets
	fileprivate static let LabelInsets: CGFloat = 16

	// MARK: Outlets

	@IBOutlet weak var userPortraitScreenlet: UserPortraitScreenlet?

	@IBOutlet weak var userNameLabel: UILabel?

	@IBOutlet weak var createdDateLabel: UILabel?

	@IBOutlet weak var editedLabel: UILabel?

	@IBOutlet weak var bodyLabel: UILabel?

	@IBOutlet weak var bodyLabelBottomMarginConstraint: NSLayoutConstraint?

	@IBOutlet weak var normalStateButtonsContainer: UIView?

	@IBOutlet weak var deletingStateButtonsContainer: UIView?

	@IBOutlet weak var deleteButton: UIButton?

	@IBOutlet weak var editButton: UIButton?

	@IBOutlet weak var confirmButton: UIButton?

	@IBOutlet weak var cancelButton: UIButton?

	var editViewController: CommentEditViewController_default?

	open var state: CommentDisplayState_default = .normal {
		didSet {
			normalStateButtonsContainer?.isHidden = state == .deleting || !editable
			deletingStateButtonsContainer?.isHidden = state != .deleting || !editable

			if state == .editing {
				editViewController = CommentEditViewController_default(body: comment?.plainBody)
				editViewController?.modalPresentationStyle = .overCurrentContext
				editViewController!.confirmBodyClosure = confirmBodyClosure

				if let vc = self.presentingViewController {
					vc.present(editViewController!, animated: true, completion: {})
				}
				else {
					print("ERROR: You neet to set the presentingViewController " +
						"before editing comments")
				}
			}
		}
	}

	// MARK: CommentDisplayViewModel

	open func editComment() {
		if let viewController = self.presentingViewController, let editedComment = self.comment, editedComment.isStyled {
			let alertController = UIAlertController(
				title: LocalizedString("default", key: "comment-display-warning", obj: self),
				message: LocalizedString("default", key: "comment-display-styled", obj: self),
				preferredStyle: UIAlertControllerStyle.alert)

			let dismissAction = UIAlertAction(
				title: LocalizedString("default", key: "comment-display-dismiss", obj: self),
				style: UIAlertActionStyle.default) { _ in
					self.state = .editing
			}
			alertController.addAction(dismissAction)

			viewController.present(alertController, animated: true, completion: nil)
		}
		else {
			self.state = .editing
		}
	}

	open var comment: Comment? {
		didSet {
			self.state = .normal

			if let comment = comment {
				bodyLabel?.attributedText = comment.htmlBody.toHtmlTextWithAttributes(
					type(of: self).defaultAttributedTextAttributes())

				deleteButton?.isEnabled = comment.canDelete
				editButton?.isEnabled = comment.canEdit

				let loadedUserId = userPortraitScreenlet?.userId
				if loadedUserId == nil || loadedUserId != comment.userId {
					userPortraitScreenlet?.load(userId: comment.userId)
				}

				userNameLabel?.text = comment.userName
				createdDateLabel?.text = comment.createDate.timeAgo
				editedLabel?.isHidden = comment.createDate.equalToDate(comment.modifiedDate)
			}
		}
	}

	// MARK: BaseScreenletView

	override open func onSetTranslations() {
		editedLabel?.text = LocalizedString("default", key: "comment-display-edited", obj: self)
		editButton?.titleLabel?.text = LocalizedString("default", key: "comment-display-edit-button", obj: self)
		deleteButton?.titleLabel?.text = LocalizedString("default", key: "comment-display-delete-button", obj: self)
		confirmButton?.titleLabel?.text = LocalizedString("default", key: "comment-display-confirm-button", obj: self)
		cancelButton?.titleLabel?.text = LocalizedString("default", key: "comment-display-cancel-button", obj: self)
	}

	override open var editable: Bool {
		didSet {
			normalStateButtonsContainer?.isHidden = !editable
		}
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	override open var progressMessages: [String : ProgressMessages] {
		return [
			CommentDisplayScreenlet.DeleteAction: [.working: NoProgressMessage],
			CommentDisplayScreenlet.UpdateAction: [.working: NoProgressMessage]
		]
	}

	// MARK: Public methods

	open func confirmBodyClosure(_ body: String?) {
		editViewController?.dismiss(animated: true, completion: nil)

		if let updatedBody = body, updatedBody != comment?.plainBody {
			userAction(name: CommentDisplayScreenlet.UpdateAction, sender: updatedBody as AnyObject?)
		}
	}

	open class func heightForText(_ text: String?, width: CGFloat) -> CGFloat {
		let realWidth = width - LabelPadding

		let attributedText = text?.toHtmlTextWithAttributes(self.defaultAttributedTextAttributes())

		if let attributedText = attributedText {
			let rect = attributedText.boundingRect(
				with: CGSize(width: realWidth, height: CGFloat.greatestFiniteMagnitude),
				options: [.usesLineFragmentOrigin, .usesFontLeading],
				context: nil)

			return rect.height + FixedHeight + LabelInsets
		}

		return 110
	}

	open class func defaultAttributedTextAttributes() -> [String: NSObject] {
		let paragrahpStyle = NSMutableParagraphStyle()
		paragrahpStyle.lineBreakMode = .byWordWrapping

		var attributes: [String: NSObject] = [NSParagraphStyleAttributeName: paragrahpStyle]

		let font = UIFont(name: "HelveticaNeue", size: 17)

		if let font = font {
			attributes[NSFontAttributeName] = font
		}

		return attributes
	}

	// MARK: Actions

	@IBAction func deleteButtonClicked() {
		self.state = .deleting
	}

	@IBAction func editButtonClicked() {
		editComment()
	}

	@IBAction func cancelDeletionButtonClicked() {
		self.state = .normal
	}

	@IBAction func confirmDeletionButtonClicked() {
		userAction(name: CommentDisplayScreenlet.DeleteAction)
		self.state = .normal
	}
}
