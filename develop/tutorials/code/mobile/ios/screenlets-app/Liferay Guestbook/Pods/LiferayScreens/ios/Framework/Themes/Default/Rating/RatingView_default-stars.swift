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
import Cosmos

@objc(RatingView_default_stars)
open class RatingView_default_stars: BaseScreenletView, RatingViewModel {

	// MARK: Outlets

	@IBOutlet weak var userRatingBar: CosmosView? {
		didSet {
			userRatingBar?.didFinishTouchingCosmos = {
				let score = $0 / Double(self.userRatingBar!.settings.totalStars)

				if self.selectedUserScore != NSNumber(value: score) {
					self.selectedUserScore = score as NSNumber?
					self.userAction(
						name: RatingScreenlet.UpdateRatingAction,
						sender: score as AnyObject?)
				}
			}
		}
	}

	@IBOutlet weak var averageRatingBar: CosmosView?

	open var defaultRatingsGroupCount: Int32 = 1

	fileprivate var selectedUserScore: NSNumber?

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
				averageRatingBar?.rating =
					rating.average * Double(self.averageRatingBar!.settings.totalStars)
				averageRatingBar?.text = LocalizedPlural("default",
					keySingular: "rating-ratings.one", keyPlural: "rating-ratings.other",
					obj: self, count: NSNumber(value: rating.totalCount))
				userRatingBar?.rating = rating.userScore * Double(self.userRatingBar!.settings.totalStars)
				selectedUserScore = rating.userScore as NSNumber?
			}
		}
	}
}
