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
import Foundation

extension SyncManager {

	func commentsSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {

		if key.hasPrefix("delete-") {
			return deleteCommentSynchronizer(key, attributes: attributes)
		}
		else if key.hasPrefix("update-") {
			return updateCommentSynchronizer(key, attributes: attributes)
		}
		else if key.hasPrefix("add-") {
			return addCommentSynchronizer(key, attributes: attributes)
		}

		return { _ in }
	}

	func deleteCommentSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {
		return { signal in
			let commentId = (attributes["commentId"] as! NSNumber).int64Value

			let interactor = CommentDeleteInteractor(commentId: commentId)

			self.prepareInteractorForSync(interactor,
					key: key,
					attributes: attributes,
					signal: signal,
					screenletClassName: "CommentsScreenlet")

			if !interactor.start() {
				self.delegate?.syncManager?(self,
						onItemSyncScreenlet: "CommentsScreenlet",
						failedKey: key,
						attributes: attributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "Synchronizer for delete comment not available."))
				signal()
			}
		}
	}

	func updateCommentSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {
		return { signal in
			let commentId = (attributes["commentId"] as! NSNumber).int64Value
			let body = (attributes["body"] as! String)

			let interactor = CommentUpdateInteractor(
					commentId: commentId,
					body: body)

			self.prepareInteractorForSync(interactor,
					key: key,
					attributes: attributes,
					signal: signal,
					screenletClassName: "CommentsScreenlet")

			if !interactor.start() {
				self.delegate?.syncManager?(self,
						onItemSyncScreenlet: "CommentsScreenlet",
						failedKey: key,
						attributes: attributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "Synchronizer for update comment not available."))
				signal()
			}
		}
	}

	func addCommentSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {
		return { signal in
			let className = (attributes["className"] as! String)
			let classPK = (attributes["classPK"] as! NSNumber).int64Value
			let body = (attributes["body"] as! String)

			let interactor = CommentAddInteractor(
				className: className,
				classPK: classPK,
				body: body,
				cacheKeyUsed: key)

			self.prepareInteractorForSync(interactor,
					key: key,
					attributes: attributes,
					signal: signal,
					screenletClassName: "CommentsScreenlet")

			if !interactor.start() {
				self.delegate?.syncManager?(self,
						onItemSyncScreenlet: "CommentsScreenlet",
						failedKey: key,
						attributes: attributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "Synchronizer for add comment not available."))
				signal()
			}
		}
	}

}
