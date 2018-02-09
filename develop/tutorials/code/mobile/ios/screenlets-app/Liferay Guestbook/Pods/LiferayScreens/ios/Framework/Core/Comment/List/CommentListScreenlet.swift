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

/// The CommentListScreenletDelegate protocol defines some methods that you use to manage the
/// CommentListScreenlet events. All of them are optional.
@objc(CommentListScreenletDelegate)
public protocol CommentListScreenletDelegate: BaseScreenletDelegate {

	/// Called when the screenlet receives the comments.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - comments: Asset's comments.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onListResponseComments comments: [Comment])

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - error: Error while retrieving comments.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onCommentListError error: NSError)

	/// Called when a comment is selected.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - comment: Selected comment.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onSelectedComment comment: Comment)

	/// Called when a comment is deleted.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - comment: Deleted comment.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onDeletedComment comment: Comment)

	/// Called when the screenlet prepares a comment for deletion.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - comment: Deleted comment.
	///   - error: Error while deleting comment.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onCommentDelete comment: Comment,
			onError error: NSError)

	/// Called when a comment is updated.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - comment: Updated comment.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onUpdatedComment comment: Comment)

	/// Called when the screenlet prepares a comment for update.
	///
	/// - Parameters:
	///   - screenlet: Comment list screenlet instance.
	///   - comment: Updated comment.
	///   - error: Error while updating comment.
	@objc optional func screenlet(_ screenlet: CommentListScreenlet,
			onCommentUpdate comment: Comment,
			onError error: NSError)

}

/// Comment List Screenlet can list all the comments of an asset in a Liferay instance. It also 
// lets the user update or delete comments.
@objc(CommentListScreenlet)
open class CommentListScreenlet: BaseListScreenlet, CommentDisplayScreenletDelegate {

	// MARK: Inspectables

	/// The asset’s fully qualified class name. For example, a blog entry’s className is 
	/// com.liferay.blogs.kernel.model.BlogsEntry. The className and classPK attributes are 
	/// required to instantiate the Screenlet.
	@IBInspectable open var className: String = ""

	/// The asset’s unique identifier. The className and classPK attributes are required to 
	/// instantiate the Screenlet.
	@IBInspectable open var classPK: Int64 = 0

	///The offline mode setting. The default is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	/// Whether the user can edit the comment.
	@IBInspectable open var editable: Bool = true {
		didSet {
			screenletView?.editable = self.editable
		}
	}

	// MARK: Public properties

	open var viewModel: CommentListViewModel? {
		return screenletView as? CommentListViewModel
	}

	open var commentListDelegate: CommentListScreenletDelegate? {
		return delegate as? CommentListScreenletDelegate
	}

	// MARK: Public methods

	/// Call this method to add a new asset comment.
	///
	/// - Parameter comment: Asset comment.
	open func addComment(_ comment: Comment) {
		viewModel?.addComment(comment)
	}

	/// Call this method to delete an asset comment.
	///
	/// - Parameter comment: Asset comment.
	open func deleteComment(_ comment: Comment) {
		viewModel?.deleteComment(comment)
	}

	/// Call this method to update an asset comment.
	///
	/// - Parameter comment: Asset comment.
	open func updateComment(_ comment: Comment) {
		viewModel?.updateComment(comment)
	}

	// MARK: BaseListScreenlet

	override open func onCreated() {
		super.onCreated()
		screenletView?.editable = self.editable
	}

	override open func createPageLoadInteractor(
			page: Int,
			computeRowCount: Bool) -> BaseListPageLoadInteractor {
		let interactor = CommentListPageLoadInteractor(
				screenlet: self,
				page: page,
				computeRowCount: computeRowCount,
				className: className,
				classPK: classPK)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		return interactor
	}

	override open func onLoadPageError(page: Int, error: NSError) {
		super.onLoadPageError(page: page, error: error)

		commentListDelegate?.screenlet?(self, onCommentListError: error)
	}

	override open func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
		super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

		commentListDelegate?.screenlet?(self,
				onListResponseComments: rows as! [Comment])
	}

	override open func onSelectedRow(_ row: AnyObject) {
		commentListDelegate?.screenlet?(self,
				onSelectedComment: row as! Comment)
	}

	// MARK: CommentDisplayScreenletDelegate

	open func screenlet(
			_ screenlet: CommentDisplayScreenlet,
			onCommentDeleted comment: Comment) {
		deleteComment(comment)
		commentListDelegate?.screenlet?(self, onDeletedComment: comment)
	}

	open func screenlet(
			_ screenlet: CommentDisplayScreenlet,
			onDeleteComment comment: Comment,
			onError error: NSError) {
		commentListDelegate?.screenlet?(self, onCommentDelete: comment, onError: error)
	}

	open func screenlet(
			_ screenlet: CommentDisplayScreenlet,
			onCommentUpdated comment: Comment) {
		updateComment(comment)
		commentListDelegate?.screenlet?(self, onUpdatedComment: comment)
	}

	open func screenlet(
			_ screenlet: CommentDisplayScreenlet,
			onUpdateComment comment: Comment,
			onError error: NSError) {
		commentListDelegate?.screenlet?(self, onCommentUpdate: comment, onError: error)
	}

}
