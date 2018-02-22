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

@objc(CommentListViewModel)
public protocol CommentListViewModel {

	/// Call this method to add a new asset comment.
	///
	/// - Parameter comment: asset's comment.
	func addComment(_ comment: Comment)

	/// Call this method to delete an asset comment.
	///
	/// - Parameter comment: asset's comment.
	func deleteComment(_ comment: Comment)

	/// Call this method to update an asset comment.
	///
	/// - Parameter comment: asset's comment.
	func updateComment(_ comment: Comment)

}
