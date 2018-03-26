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

/// The RatingScreenletDelegate protocol defines some methods that you use to manage the
/// RatingScreenlet events. All of them are optional.
@objc(RatingScreenletDelegate)
public protocol RatingScreenletDelegate: BaseScreenletDelegate {

	/// Called when the ratings are received.
	///
	/// - Parameters:
	///   - screenlet: Rating screenlet instance.
	///   - rating: Asset's rating.
	@objc optional func screenlet(_ screenlet: RatingScreenlet,
	                        onRatingRetrieve rating: RatingEntry)

	/// Called when a rating is deleted.
	///
	/// - Parameters:
	///   - screenlet: Rating screenlet instance.
	///   - rating: Asset's rating.
	@objc optional func screenlet(_ screenlet: RatingScreenlet,
	                        onRatingDeleted rating: RatingEntry)

	/// Called when a rating is updated.
	///
	/// - Parameters:
	///   - screenlet: Rating screenlet instance.
	///   - rating: Asset's rating.
	@objc optional func screenlet(_ screenlet: RatingScreenlet,
	                        onRatingUpdated rating: RatingEntry)

	/// Called when an error occurs in the process. The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Rating screenlet instance.
	///   - error: Error retrieving, updating or deleting asset's rating.
	@objc optional func screenlet(_ screenlet: RatingScreenlet,
	                        onRatingError error: NSError)

}

///Rating Screenlet shows an asset’s rating. It also lets users update or delete the rating. 
/// This Screenlet comes with different Themes that display ratings as thumbs, stars, and emojis.
@objc(RatingScreenlet)
open class RatingScreenlet: BaseScreenlet {

	// MARK: Static properties

	open static let DeleteRatingAction = "deleteRating"
	open static let UpdateRatingAction = "updateRating"
	open static let LoadRatingsAction = "loadRatings"

	// MARK: Inspectables

	/// The primary key of the asset with the rating to display.
	@IBInspectable open var entryId: Int64 = 0

	/// The asset’s fully qualified class name. For example, a blog entry’s className is
	/// com.liferay.blogs.kernel.model.BlogsEntry. The className attribute is required when using 
	/// it with classPK to instantiate the Screenlet.
	@IBInspectable open var className: String = ""

	/// The asset’s unique identifier. Only use this attribute when also using className to 
	/// instantiate the Screenlet.
	@IBInspectable open var classPK: Int64 = 0

	/// Number of possible ratings the user can interact with.
	@IBInspectable open var ratingsGroupCount: Int32 = -1

	/// Whether the rating loads automatically when the Screenlet appears in the app’s UI. 
	/// The default value is true.
	@IBInspectable open var autoLoad: Bool = true

	/// Whether the user can change the rating.
	@IBInspectable open var editable: Bool = false {
		didSet {
			screenletView?.editable = self.editable
		}
	}

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var ratingDisplayDelegate: RatingScreenletDelegate? {
		return delegate as? RatingScreenletDelegate
	}

	open var viewModel: RatingViewModel? {
		return screenletView as? RatingViewModel
	}

	// MARK: BaseScreenlet

	override open func prepareForInterfaceBuilder() {
		setCustomDefaultThemeName()
		super.prepareForInterfaceBuilder()
	}

	override open func onPreCreate() {
		setCustomDefaultThemeName()
	}

	override open func onCreated() {
		if let defaultRatingsGroupCount = viewModel?.defaultRatingsGroupCount, ratingsGroupCount == -1 {
			ratingsGroupCount = defaultRatingsGroupCount
		}
		screenletView?.editable = self.editable
	}

	override open func onShow() {
		if autoLoad {
			loadRatings()
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		let interactor: ServerConnectorInteractor?

		switch name {
		case RatingScreenlet.LoadRatingsAction:
			interactor = createLoadRatingsInteractor()
		case RatingScreenlet.DeleteRatingAction:
			interactor = createDeleteRatingInteractor()
		case RatingScreenlet.UpdateRatingAction:
			let selectedScore = sender!.doubleValue!
			interactor = createUpdateRatingInteractor(selectedScore)
		default:
			return nil
		}

		interactor?.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor?.onFailure = {
			self.ratingDisplayDelegate?.screenlet?(self, onRatingError: $0)
		}

		return interactor
	}

	override open func performDefaultAction() -> Bool {
		return performAction(name: RatingScreenlet.LoadRatingsAction, sender: nil)
	}

	// MARK: Public methods

	/// Starts the request to load the asset's ratings.
	///
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func loadRatings() -> Bool {
		return self.performDefaultAction()
	}

	// MARK: Private methods

	fileprivate func setCustomDefaultThemeName() {
		if themeName == BaseScreenlet.DefaultThemeName {
			themeName = "default-thumbs"
		}
	}

	fileprivate func createLoadRatingsInteractor() -> LoadRatingsInteractor? {
		let interactor: LoadRatingsInteractor?

		if entryId != 0 {
			interactor = LoadRatingsInteractor(
				screenlet: self,
				entryId: entryId,
				ratingsGroupCount: ratingsGroupCount)
		}
		else if className != "" && classPK != 0 {
			interactor = LoadRatingsInteractor(
				screenlet: self,
				className: className,
				classPK: classPK,
				ratingsGroupCount: ratingsGroupCount)
		}
		else {
			interactor = nil
		}

		interactor?.onSuccess = {
			if let result = interactor?.resultRating {
				self.className = result.className
				self.classPK = result.classPK

				self.viewModel?.ratingEntry = result

				self.ratingDisplayDelegate?.screenlet?(self, onRatingRetrieve: result)
			}
			else {
				self.ratingDisplayDelegate?.screenlet?(self,
						onRatingError: NSError.errorWithCause(.invalidServerResponse,
								message: "Could not load ratings."))
			}
		}

		return interactor
	}

	fileprivate func createDeleteRatingInteractor() -> DeleteRatingInteractor {
		let interactor = DeleteRatingInteractor(
				screenlet: self,
				className: className,
				classPK: classPK,
				ratingsGroupCount: ratingsGroupCount)

		interactor.onSuccess = {
			if let result = interactor.resultRating {
				self.viewModel?.ratingEntry = result

				self.ratingDisplayDelegate?.screenlet?(self, onRatingDeleted: result)
			}
			else {
				self.ratingDisplayDelegate?.screenlet?(self,
						onRatingError: NSError.errorWithCause(.invalidServerResponse,
								message: "Could not delete the rating."))
			}
		}

		return interactor
	}

	fileprivate func createUpdateRatingInteractor(_ selectedScore: Double) -> UpdateRatingInteractor {
		let interactor = UpdateRatingInteractor(
				screenlet: self,
				className: className,
				classPK: classPK,
				score: selectedScore,
				ratingsGroupCount: ratingsGroupCount)

		interactor.onSuccess = {
			if let result = interactor.resultRating {
				self.viewModel?.ratingEntry = result

				self.ratingDisplayDelegate?.screenlet?(self, onRatingUpdated: result)
			}
			else {
				self.ratingDisplayDelegate?.screenlet?(self,
						onRatingError: NSError.errorWithCause(.invalidServerResponse,
								message: "Could not update the rating."))
			}
		}

		return interactor
	}

}
