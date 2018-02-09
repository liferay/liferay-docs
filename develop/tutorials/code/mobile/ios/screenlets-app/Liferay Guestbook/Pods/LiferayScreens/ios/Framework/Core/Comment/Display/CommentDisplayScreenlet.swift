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

/// The CommentDisplayScreenletDelegate protocol defines some methods that you use to manage the
/// CommentDisplayScreenlet events. All of them are optional.
@objc(CommentDisplayScreenletDelegate)
public protocol CommentDisplayScreenletDelegate: BaseScreenletDelegate {

	/// Called when the screenlet loads the comment.
	///
	/// - Parameters:
	///   - screenlet: Comment display screenlet instance.
	///   - comment: Loaded comment.
	@objc optional func screenlet(_ screenlet: CommentDisplayScreenlet,
			onCommentLoaded comment: Comment)

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Comment display screenlet instance.
	///   - error: Error while loading the comment.
	@objc optional func screenlet(_ screenlet: CommentDisplayScreenlet,
			onLoadCommentError error: NSError)

	/// Called when the screenlet prepares the comment for deletion.
	///
	/// - Parameters:
	///   - screenlet: Comment display screenlet instance.
	///   - comment: Comment to be deleted.
	@objc optional func screenlet(_ screenlet: CommentDisplayScreenlet,
			onCommentDeleted comment: Comment)

	///  Called when a comment is deleted.
	///
	/// - Parameters:
	///   - screenlet: Comment display screenlet instance.
	///   - comment: Deleted comment.
	///   - error: Error while deleting the comment.
	@objc optional func screenlet(_ screenlet: CommentDisplayScreenlet,
			onDeleteComment comment: Comment,
			onError error: NSError)

	/// Called when the screenlet prepares the comment for update.
	///
	/// - Parameters:
	///   - screenlet: Comment display screenlet instance.
	///   - comment: Comment to be updated.
	@objc optional func screenlet(_ screenlet: CommentDisplayScreenlet,
			onCommentUpdated comment: Comment)

	/// Called when a comment is updated.
	///
	/// - Parameters:
	///   - screenlet: Comment display screenlet instance.
	///   - comment: Updated comment.
	///   - error: Error while updating the comment.
	@objc optional func screenlet(_ screenlet: CommentDisplayScreenlet,
			onUpdateComment comment: Comment,
			onError error: NSError)

}

/// Comment Display Screenlet can show one comment of an asset in a Liferay instance. It also lets 
/// the user update or delete the comment.
@objc(CommentDisplayScreenlet)
open class CommentDisplayScreenlet: BaseScreenlet {

	// MARK: Static properties

	open static let DeleteAction = "deleteAction"
	open static let UpdateAction = "updateAction"

	// MARK: Inspectables

	/// The primary key of the comment to display.
	@IBInspectable open var commentId: Int64 = 0

	/// Whether the list should automatically load when the Screenlet appears in the app’s UI. 
	/// The default value is true.
	@IBInspectable open var autoLoad: Bool = true

	/// The offline mode setting. The default is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	/// Whether the user can edit the comment.
	@IBInspectable open var editable: Bool = false {
		didSet {
			screenletView?.editable = self.editable
		}
	}

	// MARK: Public properties

	open var commentDisplayDelegate: CommentDisplayScreenletDelegate? {
		return delegate as? CommentDisplayScreenletDelegate
	}

	open var viewModel: CommentDisplayViewModel {
		return screenletView as! CommentDisplayViewModel
	}

	open var comment: Comment? {
		didSet {
			if let comment = comment {
				commentId = comment.commentId
			}
			viewModel.comment = self.comment
		}
	}

	// MARK: Public methods

	/// Loads the comment in the screenlet.
	open func load() {
		performDefaultAction()
	}

	/// Call this method to delete one comment.
	open func deleteComment() {
		performAction(name: CommentDisplayScreenlet.DeleteAction)
	}

	/// Call this method to edit the comment.
	open func editComment() {
		viewModel.editComment()
	}

	// MARK: BaseScreenlet

	override open func onCreated() {
		super.onCreated()
		screenletView?.editable = self.editable
	}

	override open func onShow() {
		if autoLoad {
			load()
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		switch name {
			case BaseScreenlet.DefaultAction:
				return createCommentLoadInteractor()
			case CommentDisplayScreenlet.DeleteAction:
				return createCommentDeleteInteractor()
			case CommentDisplayScreenlet.UpdateAction:
				guard let body = sender as? String else {
					return nil
				}
				return createCommentUpdateInteractor(body)
			default:
				return nil
		}
	}

	// MARK: Private methods

	fileprivate func createCommentLoadInteractor() -> Interactor {
		let interactor = CommentLoadInteractor(screenlet: self)

		interactor.cacheStrategy = CacheStrategyType(rawValue: offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultComment = interactor.resultComment {
				self.comment = resultComment
				self.viewModel.comment = resultComment
				self.commentDisplayDelegate?.screenlet?(self, onCommentLoaded: resultComment)
			}
			else {
				self.commentDisplayDelegate?.screenlet?(self,
					onLoadCommentError: NSError.errorWithCause(.invalidServerResponse,
							message: "Could not load comment."))
			}
		}

		interactor.onFailure = {
			self.commentDisplayDelegate?.screenlet?(self, onLoadCommentError: $0)
		}

		return interactor
	}

	fileprivate func createCommentDeleteInteractor() -> Interactor {
		let interactor = CommentDeleteInteractor(screenlet: self)

		interactor.onSuccess = {
			self.commentDisplayDelegate?.screenlet?(self, onCommentDeleted: self.comment!)
		}

		interactor.onFailure = {
			self.commentDisplayDelegate?.screenlet?(self, onDeleteComment: self.comment!, onError: $0)
		}

		return interactor
	}

	fileprivate func createCommentUpdateInteractor(_ body: String) -> Interactor {
		let interactor = CommentUpdateInteractor(screenlet: self, body: body)

		interactor.onSuccess = {
			if let resultComment = interactor.resultComment {
				self.comment = resultComment
				self.viewModel.comment = resultComment
				self.commentDisplayDelegate?.screenlet?(self, onCommentUpdated: resultComment)
			}
			else {
				self.commentDisplayDelegate?.screenlet?(self,
					onUpdateComment: self.comment!,
					onError: NSError.errorWithCause(.invalidServerResponse,
							message: "Could not update comment."))
			}
		}

		interactor.onFailure = {
			self.commentDisplayDelegate?.screenlet?(self, onLoadCommentError: $0)
		}

		return interactor

	}
}
