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

@objc(RatingView_default_thumbs)
open class RatingView_default_thumbs: BaseScreenletView, RatingViewModel {

	// MARK: Outlets

	@IBOutlet weak var negativeButton: UIButton? {
		didSet {
			let image = Bundle.imageInBundles(
				name: "default-thumb-down",
				currentClass: RatingView_default_thumbs.self)?.withRenderingMode(.alwaysTemplate)
			self.negativeButton?.setBackgroundImage(image, for: .normal)
		}
	}

	@IBOutlet weak var possitiveButton: UIButton? {
		didSet {
			let image = Bundle.imageInBundles(
				name: "default-thumb-up",
				currentClass: RatingView_default_thumbs.self)?.withRenderingMode(.alwaysTemplate)
			self.possitiveButton?.setBackgroundImage(image, for: .normal)
		}
	}

	@IBOutlet weak var negativeCountLabel: UILabel?

	@IBOutlet weak var possitiveCountLabel: UILabel?

	open var defaultRatingsGroupCount: Int32 = 2

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
				self.negativeCountLabel?.text = NSString.localizedStringWithFormat(LocalizedString("default",
						key: "rating-total", obj: self) as NSString, rating.ratings[0]) as String
				self.possitiveCountLabel?.text = NSString.localizedStringWithFormat(LocalizedString("default",
						key: "rating-total", obj: self) as NSString, rating.ratings[1]) as String

				let score = rating.userScore

				if score == 0 {
					// thumbs down
					self.possitiveButton?.tintColor = UIColor.gray
					self.negativeButton?.tintColor = DefaultThemeBasicBlue

					self.possitiveButton?.restorationIdentifier = RatingScreenlet.UpdateRatingAction
					self.negativeButton?.restorationIdentifier = RatingScreenlet.DeleteRatingAction
				}
				else if score > 0 {
					// thumbs up
					self.possitiveButton?.tintColor = DefaultThemeBasicBlue
					self.negativeButton?.tintColor = UIColor.gray

					self.possitiveButton?.restorationIdentifier = RatingScreenlet.DeleteRatingAction
					self.negativeButton?.restorationIdentifier = RatingScreenlet.UpdateRatingAction
				}
				else if score == -1 {
					// no rating yet
					self.possitiveButton?.tintColor = UIColor.gray
					self.negativeButton?.tintColor = UIColor.gray

					self.possitiveButton?.restorationIdentifier = RatingScreenlet.UpdateRatingAction
					self.negativeButton?.restorationIdentifier = RatingScreenlet.UpdateRatingAction
				}
			}
		}
	}

	// MARK: Actions

	@IBAction func possitiveButtonClicked(_ sender: UIButton) {
		self.userAction(name: sender.restorationIdentifier, sender: 1 as AnyObject?)
	}
	@IBAction func negativeButtonClicked(_ sender: UIButton) {
		self.userAction(name: sender.restorationIdentifier, sender: 0 as AnyObject?)
	}
}
