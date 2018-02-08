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

@objc(RatingView_default_emojis)
open class RatingView_default_emojis: BaseScreenletView, RatingViewModel {

	open var defaultRatingsGroupCount: Int32 {
		return Int32(self.emojis.count)
	}

	var emojis: [UIButton] = []
	var labels: [UILabel] = []

	// MARK: BaseScreenletView

	override open func onCreated() {
		emojis = subviews.map({ $0 as? UIButton }).flatMap({ $0 })
		labels = subviews.map({ $0 as? UILabel }).flatMap({ $0 })
	}

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
				emojis.forEach({
					$0.alpha = 0.5
					$0.restorationIdentifier = RatingScreenlet.UpdateRatingAction
					$0.addTarget(self, action: #selector(emojiClicked), for: .touchUpInside)
				})

				for i in 0 ..< emojis.count {
					labels[i].text = "\(emojis[i].titleLabel!.text!) \(rating.ratings[i])"
				}

				if rating.userScore != -1 {
					let index = rating.userScore == 1 ? emojis.count - 1 : Int(rating.userScore * Double(emojis.count))
					emojis[index].alpha = 1.0
					emojis[index].restorationIdentifier = RatingScreenlet.DeleteRatingAction
				}
			}
		}
	}

	// MARK: Internal methods

	func emojiClicked(_ sender: UIButton) {
		userAction(
			name: sender.restorationIdentifier,
			sender: (Double(emojis.index(of: sender)!) / Double(emojis.count)) as AnyObject?)
	}
}
