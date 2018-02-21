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

/// The UserPortraitScreenletDelegate protocol defines some methods that you use to manage the 
/// UserPortraitScreenlet events. All of them are optional.
@objc(UserPortraitScreenletDelegate)
public protocol UserPortraitScreenletDelegate: BaseScreenletDelegate {

	/// Called when an image is received from the server. You can then apply image filters 
	/// (grayscale, for example) and return the new image. You can return the original image 
	/// supplied as the argument if you don’t want to modify it.
	///
	/// - Parameters:
	///   - screenlet: User portrait screenlet instance.
	///   - image: User portrait image.
	/// - Returns: Original or modify image.
	@objc optional func screenlet(_ screenlet: UserPortraitScreenlet,
			onUserPortraitResponseImage image: UIImage) -> UIImage

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: User portrait screenlet instance.
	///   - error: Error while retrieving user portrait image.
	@objc optional func screenlet(_ screenlet: UserPortraitScreenlet,
			onUserPortraitError error: NSError)

	/// Called when a new portrait is uploaded to the server. You receive the user 
	/// attributes as a parameter.
	///
	/// - Parameters:
	///   - screenlet: User portrait screenlet istance.
	///   - attributes: User portrait attributes.
	@objc optional func screenlet(_ screenlet: UserPortraitScreenlet,
			onUserPortraitUploaded attributes: [String:AnyObject])

	/// Called when an error occurs in the upload process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: User portrair screenlet instance.
	///   - error: Error while uploading the user portrait image.
	@objc optional func screenlet(_ screenlet: UserPortraitScreenlet,
			onUserPortraitUploadError error: NSError)
}

/// The UserPortraitScreenlet shows the user’s portrait from Liferay instance.
/// If the user doesn’t have a portrait configured, a default placeholder image is shown.
@objc(UserPortraitScreenlet)
open class UserPortraitScreenlet: BaseScreenlet {

	// MARK: Class properties

	open class var LoadPortrait: String { return "load-portrait" }
	open class var UploadPortrait: String { return "upload-portrait" }

	// MARK: Inspectables

	/// The size in pixels for the portrait’s border. The default value is 1.
	/// Set this to 0 if you want to hide the border.
	@IBInspectable open var borderWidth: CGFloat = 1.0 {
		didSet {
			(screenletView as? UserPortraitViewModel)?.borderWidth = self.borderWidth
		}
	}

	/// The border’s color. Use the system’s transparent color to hide the border.
	@IBInspectable open var borderColor: UIColor? {
		didSet {
			(screenletView as? UserPortraitViewModel)?.borderColor = self.borderColor
		}
	}

	/// Lets the user change the portrait image by taking a photo or selecting a gallery picture. 
	/// The default value is false. Portraits loaded with the load(portraitId, uuid, male) method 
	/// aren’t editable.
	@IBInspectable open var editable: Bool = false {
		didSet {
			screenletView?.editable = self.editable
		}
	}

	/// Configure the loading and saving behavior in case of connectivity issues. The default value 
	/// is remote first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	/// Whether the user portrait automatically loads when the Screenlet appears in the app’s UI.
	/// The default value is true.
	@IBInspectable open var autoLoad: Bool = true

	// MARK: Public properties

	open var userPortraitDelegate: UserPortraitScreenletDelegate? {
		return self.delegate as? UserPortraitScreenletDelegate
	}

	open var viewModel: UserPortraitViewModel {
		return screenletView as! UserPortraitViewModel
	}

	open var userId: Int64? {
		return loadedUserId
	}

	// MARK: Private properties

	fileprivate var loadedUserId: Int64?

	// MARK: BaseScreenlet

	override open func onShow() {
		if autoLoad {
			loadLoggedUserPortrait()
		}
	}

	override open func onCreated() {
		super.onCreated()

		viewModel.borderWidth = self.borderWidth
		viewModel.borderColor = self.borderColor
		screenletView?.editable = self.editable
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		if isActionRunning(name) {
			cancelInteractorsForAction(name)
		}

		switch name {
			case UserPortraitScreenlet.LoadPortrait:
				let downloadInteractor = sender as! DownloadUserPortraitInteractor
				return createLoadPortraitInteractor(downloadInteractor)
			case UserPortraitScreenlet.UploadPortrait:
				let image = sender as! UIImage
				return createUploadPortraitInteractor(image)
			default:
				return nil
		}
	}

	// MARK: Public methods

	/// Loads the user portrait that correspond to the user logged.
	///
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func loadLoggedUserPortrait() -> Bool {
		guard let userId = SessionContext.currentContext?.user.userId else {
			return false
		}

		let interactor = DownloadUserPortraitInteractor(
			screenlet: self,
			userId: userId)

		loadedUserId = userId

		return performAction(name: UserPortraitScreenlet.LoadPortrait, sender: interactor)
	}

	/// Loads the user portrait image throught user portraitId and uuid.
	///
	/// - Parameters:
	///   - portraitId: Portrait identifier.
	///   - uuid: User portrait unique identifier.
	///   - male: True if the user is male, false otherwise.
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load(portraitId: Int64, uuid: String, male: Bool = true) -> Bool {
		let interactor = DownloadUserPortraitInteractor(
				screenlet: self,
				portraitId: portraitId,
				uuid: uuid,
				male: male)

		loadedUserId = nil

		return performAction(name: UserPortraitScreenlet.LoadPortrait, sender: interactor)
	}

	/// Loads the user portrait image throught user identifier.
	///
	/// - Parameter userId: User identifier.
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load(userId: Int64) -> Bool {
		let interactor = DownloadUserPortraitInteractor(
				screenlet: self,
				userId: userId)

		loadedUserId = userId

		return performAction(name: UserPortraitScreenlet.LoadPortrait, sender: interactor)
	}

	/// Loads the user portrait throught company identifier and email address.
	///
	/// - Parameters:
	///   - companyId: Company identifier.
	///   - emailAddress: User email.
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load(companyId: Int64, emailAddress: String) -> Bool {
		let interactor = DownloadUserPortraitInteractor(
				screenlet: self,
				companyId: companyId,
				emailAddress: emailAddress)

		loadedUserId = nil

		return performAction(name: UserPortraitScreenlet.LoadPortrait, sender: interactor)
	}

	/// Loads the user portrait throught company identifier and user screen name.
	///
	/// - Parameters:
	///   - companyId: Company identifier.
	///   - screenName: User screen name.
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load(companyId: Int64, screenName: String) -> Bool {
		let interactor = DownloadUserPortraitInteractor(
				screenlet: self,
				companyId: companyId,
				screenName: screenName)

		loadedUserId = nil

		return performAction(name: UserPortraitScreenlet.LoadPortrait, sender: interactor)
	}

	/// Loads the user portrait placeholder when the user portrait is empty.
	open func loadPlaceholder() {
		setPortraitImage(nil)
	}

	// MARK: Private methods

	fileprivate func setPortraitImage(_ image: UIImage?) {
		viewModel.image = image

		if image == nil {
			let error = NSError.errorWithCause(.abortedDueToPreconditions,
					message: "Could not set user portrait image.")
			userPortraitDelegate?.screenlet?(self, onUserPortraitError: error)
		}
	}

	fileprivate func createLoadPortraitInteractor(
		_ downloadInteractor: DownloadUserPortraitInteractor) -> DownloadUserPortraitInteractor? {

		downloadInteractor.cacheStrategy = CacheStrategyType(
			rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		downloadInteractor.onSuccess = {
			if downloadInteractor.userHasDefaultPortrait, let user = downloadInteractor.resultUser {
				self.viewModel.loadPlaceholder(for: user)
			}
			else if let imageValue = downloadInteractor.resultImage {
				let finalImage = self.userPortraitDelegate?.screenlet?(
					self, onUserPortraitResponseImage: imageValue)

				self.loadedUserId = downloadInteractor.resultUser?.userId ?? self.loadedUserId
				self.setPortraitImage(finalImage ?? imageValue)
			}
			else {
				self.userPortraitDelegate?.screenlet?(
					self, onUserPortraitError: NSError.errorWithCause(
						.invalidServerResponse, message: "Could not load user portrait image."))

				self.loadedUserId = nil
				self.setPortraitImage(nil)
			}
		}

		downloadInteractor.onFailure = {
			self.userPortraitDelegate?.screenlet?(self, onUserPortraitError: $0)

			self.loadedUserId = nil
			self.setPortraitImage(nil)
		}

		return downloadInteractor
	}

	fileprivate func createUploadPortraitInteractor(_ image: UIImage) -> UploadUserPortraitInteractor? {

		let userId: Int64

		if let loadedUserIdValue = loadedUserId {
			userId = loadedUserIdValue
		}
		else {
			print("ERROR: Can't change the portrait without an userId\n")

			return nil
		}

		let uploadInteractor = UploadUserPortraitInteractor(
			screenlet: self,
			userId: userId,
			image: image)

		uploadInteractor.cacheStrategy = CacheStrategyType(
			rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		uploadInteractor.onSuccess = {
			self.userPortraitDelegate?.screenlet?(
				self, onUserPortraitUploaded: uploadInteractor.uploadResult!)

			self.loadedUserId = uploadInteractor.userId
			self.setPortraitImage(uploadInteractor.image)
		}

		uploadInteractor.onFailure = {
			self.userPortraitDelegate?.screenlet?(self, onUserPortraitUploadError: $0)
		}

		return uploadInteractor
	}

}
