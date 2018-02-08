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

@objc(RatingView_default_like)
open class RatingView_default_like: BaseScreenletView, RatingViewModel {

	// MARK: Outlets

	@IBOutlet weak var likeButton: UIButton? {
		didSet {
			let image = Bundle.imageInBundles(
				name: "default-thumb-up",
				currentClass: RatingView_default_like.self)?.withRenderingMode(.alwaysTemplate)
			self.likeButton?.setBackgroundImage(image, for: .normal)
		}
	}

	@IBOutlet weak var countLabel: UILabel?

	open var defaultRatingsGroupCount: Int32 = 1

	// MARK: BaseScreenletView

	override open func createProgressPresenter() -> ProgressPresenter {
		return NetworkActivityIndicatorPresenter()
	}

	override open var progressMessages: [String:ProgressMessages] {
		return [
			RatingScreenlet.LoadRatingsAction: [.working: ""],
			RatingScreenlet.UpdateRatingAction: [.working: ""],
			RatingScreenlet.DeleteRatingAction: [.working: ""]
		]
	}

	// MARK: RatingViewModel

	open var ratingEntry: RatingEntry? {
		didSet {
			if let rating = ratingEntry {
				self.countLabel?.text = NSString.localizedStringWithFormat(LocalizedString("default",
						key: "rating-total", obj: self) as NSString, rating.totalCount) as String

				if rating.userScore == -1 {
					self.likeButton?.tintColor = UIColor.gray
					self.likeButton?.restorationIdentifier = RatingScreenlet.UpdateRatingAction
				}
				else {
					self.likeButton?.tintColor = DefaultThemeBasicBlue
					self.likeButton?.restorationIdentifier = RatingScreenlet.DeleteRatingAction
				}
			}
		}
	}

	// MARK: Actions

	@IBAction func likeButtonClicked(_ sender: AnyObject) {
		let sender = self.ratingEntry?.userScore == -1 ? 1 : 0
		self.userAction(
			name: self.likeButton?.restorationIdentifier,
			sender: sender as AnyObject?)
	}

}
