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

/// The CommentAddScreenletDelegate protocol defines some methods that you use to manage the
/// CommentAddScreenlet events. All of them are optional.
@objc(CommentAddScreenletDelegate)
public protocol CommentAddScreenletDelegate: BaseScreenletDelegate {

	/// Called when the screenlet adds a comment.
	///
	/// - Parameters:
	///   - screenlet: Comment add screenlet instance.
	///   - comment: Asset's comment.
	@objc optional func screenlet(_ screenlet: CommentAddScreenlet,
			onCommentAdded comment: Comment)

	/// Called when an error occurs while adding a comment.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Comment add screenlet instance.
	///   - error: Error while adding comment.
	@objc optional func screenlet(_ screenlet: CommentAddScreenlet,
			onAddCommentError error: NSError)

	/// Called when the screenlet prepares a comment for update.
	///
	/// - Parameters:
	///   - screenlet: Comment add screenlet instance.
	///   - comment: Asset's comment.
	@objc optional func screenlet(_ screenlet: CommentAddScreenlet,
			onCommentUpdated comment: Comment)

	/// Called when an error occurs while updating a comment.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Comment add screenlet instance.
	///   - error: Error while updating comment.
	@objc optional func screenlet(_ screenlet: CommentAddScreenlet,
			onUpdateCommentError error: NSError)

}

/// Comment Add Screenlet can add a comment to an asset in a Liferay instance.
@objc(CommentAddScreenlet)
open class CommentAddScreenlet: BaseScreenlet {

	// MARK: Inspectables

	/// The asset’s fully qualified class name. For example, a blog entry’s className is 
	/// com.liferay.blogs.kernel.model.BlogsEntry. The className and classPK attributes are 
	/// required to instantiate the Screenlet.
	@IBInspectable open var className: String = ""

	/// The asset’s unique identifier. The className and classPK attributes are required to 
	/// instantiate the Screenlet.
	@IBInspectable open var classPK: Int64 = 0

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var commentAddDelegate: CommentAddScreenletDelegate? {
		return delegate as? CommentAddScreenletDelegate
	}

	open var viewModel: CommentAddViewModel {
		return screenletView as! CommentAddViewModel
	}

	open var comment: Comment? {
		didSet {
			if let comment = self.comment {
				viewModel.body = comment.plainBody
			}
		}
	}

	// MARK: BaseScreenlet

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		if comment != nil {
			return createUpdateCommentInteractor()
		}
		return createAddCommentInteractor()
	}

	// MARK: Private methods

	fileprivate func createAddCommentInteractor() -> Interactor {
		let interactor = CommentAddInteractor(screenlet: self, body: self.viewModel.body)

		interactor.cacheStrategy = CacheStrategyType(rawValue: offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultComment = interactor.resultComment {
				self.commentAddDelegate?.screenlet?(self, onCommentAdded: resultComment)
				self.viewModel.body = ""
			}
			else {
				self.commentAddDelegate?.screenlet?(self, onAddCommentError: NSError.errorWithCause(
						.invalidServerResponse, message: "Could not add comment."))
			}
		}

		interactor.onFailure = {
			self.commentAddDelegate?.screenlet?(self, onAddCommentError: $0)
		}

		return interactor
	}

	fileprivate func createUpdateCommentInteractor() -> Interactor {
		let interactor = CommentUpdateInteractor(
			commentId: comment!.commentId,
			body: self.viewModel.body)

		interactor.onSuccess = {
			if let resultComment = interactor.resultComment {
				self.commentAddDelegate?.screenlet?(self, onCommentUpdated: resultComment)
				self.viewModel.body = ""
			}
			else {
				self.commentAddDelegate?.screenlet?(self,
				                                    onUpdateCommentError: NSError.errorWithCause(
														.invalidServerResponse,
														message: "Could not update comment."))
			}
		}

		interactor.onFailure = {
			self.commentAddDelegate?.screenlet?(self, onUpdateCommentError: $0)
		}

		return interactor
	}
}
