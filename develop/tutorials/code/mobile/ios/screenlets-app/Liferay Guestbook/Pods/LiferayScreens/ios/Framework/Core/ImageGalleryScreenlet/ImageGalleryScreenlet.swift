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
import Kingfisher

/// The ImageGalleryScreenletDelegate protocol defines some methods that you use to manage the
/// ImageGalleryScreenlet events. All of them are optional.
@objc(ImageGalleryScreenletDelegate)
public protocol ImageGalleryScreenletDelegate: BaseScreenletDelegate {

	/// Called when a page of contents is received.
	/// Note that this method may be called more than once: one call for each page received.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - imageEntries: Image gallery entries.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageEntriesResponse imageEntries: [ImageEntry])

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - error: Error while retrieving image gallery entries.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageEntriesError error: NSError)

	/// Called when an item in the list is selected.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - imageEntry: Selected image entry.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageEntrySelected imageEntry: ImageEntry)

	/// Called when an image in the list is deleted.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - imageEntry: Deleted image entry.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageEntryDeleted imageEntry: ImageEntry)

	/// Called when an error occurs during image file deletion.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - error: Error while deleting image entry.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageEntryDeleteError error: NSError)

	/// Called when an image is prepared for upload.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - imageEntryUpload: Image entry to be uploaded.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageUploadStart imageEntryUpload: ImageEntryUpload)

	/// Called when the image upload progress changes.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - imageEntryUpload: The image entry being uploaded.
	///   - totalBytesSent: Image entry bytes sent.
	///   - totalBytesToSend: Image entry bytes to send.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageUploadProgress imageEntryUpload: ImageEntryUpload,
			totalBytesSent: UInt64,
			totalBytesToSend: UInt64)

	/// Called when an error occurs in the image upload process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - error: Error while uploading the image entry.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageUploadError error: NSError)

	/// Called when the image upload finishes.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - image: Uploaded image entry.
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageUploaded image: ImageEntry)

	/// Called when the image upload View is instantiated.
	/// By default, the screenlet uses a modal view controller to present this View.
	/// You only need to implement this method if you want to override this behavior.
	/// This method should present the View, passed as parameter, and then return true.
	///
	/// - Parameters:
	///   - screenlet: Image gallery screenlet instance.
	///   - view: Custom detail view to upload an image entry.
	/// - Returns: true
	@objc optional func screenlet(_ screenlet: ImageGalleryScreenlet,
			onImageUploadDetailViewCreated view: ImageUploadDetailViewBase) -> Bool
}

/// Image Gallery Screenlet shows a list of images from a Documents and Media folder in a Liferay 
/// instance. You can also use Image Gallery Screenlet to upload images to and delete images from 
/// the same folder. The Screenlet implements [fluent pagination]
/// (http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) with configurable page size, 
/// and supports i18n in asset values.
@objc(ImageGalleryScreenlet)
open class ImageGalleryScreenlet: BaseListScreenlet {

	// MARK: Static properties

	open static let DeleteImageAction = "delete-image-action"
	open static let UploadImageAction = "upload-image-action"
	open static let EnqueueUploadAction = "enqueue-upload-action"

	// MARK: Inspectables

	/// The ID of the Liferay instance’s Documents and Media repository that contains the image 
	/// gallery.
    @IBInspectable open var repositoryId: Int64 = -1

	/// The ID of the Documents and Media repository folder that contains the image gallery.
    @IBInspectable open var folderId: Int64 = -1

	/// The comma-separated list of MIME types for the Screenlet to support.
	@IBInspectable open var mimeTypes: String = ""

	/// The prefix to use on uploaded image file names.
	@IBInspectable open var filePrefix: String = "gallery-"

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue {
		didSet {
			ImageCache.screensOfflinePolicy =
				offlinePolicy ?? CacheStrategyType.remoteFirst.rawValue
		}
	}

	// MARK: Public properties

	open var imageGalleryDelegate: ImageGalleryScreenletDelegate? {
		return delegate as? ImageGalleryScreenletDelegate
	}

	open var viewModel: ImageGalleryViewModel {
		return screenletView as! ImageGalleryViewModel
	}

	open var uploadDetailViewName = "ImageUploadDetailView"

	open let DefaultMimeTypes = ["image/png", "image/jpeg", "image/gif"]

	// MARK: Internal properties

	internal var uploadsQueue = [ImageEntryUpload]()

	internal var loadedOnce = false

	// MARK: Public methods

	/// Cancels all the uploads to the image gallery.
	open func cancelUploads() {
		uploadsQueue.removeAll()
		cancelInteractorsForAction(ImageGalleryScreenlet.UploadImageAction)
	}

	/// Clears image gallery cache.
	open func deleteImageCache() {
		KingfisherManager.shared.cache.clearMemoryCache()
		KingfisherManager.shared.cache.clearDiskCache()

		SessionContext.currentContext?.cacheManager.remove(
			collection: ScreenletName(type(of: self)))
	}

	/// Shows an alert to choose the source of the image and then shows the custom detail view.
	open func startMediaSelectorAndUpload() {
		if let viewController = presentingViewController {

			let takeNewPicture = LocalizedString("default",
					key: "userportrait-take-new-picture",
					obj: self)
			let chooseExisting = LocalizedString("default",
					key: "userportrait-choose-existing-picture",
					obj: self)

			let cancelText = LocalizedString("imagegallery-screenlet", key: "cancel", obj: self)

			let alert = MediaSelector(
					viewController: viewController,
					types: [
						.camera: takeNewPicture,
						.image: chooseExisting
					],
					cancelMessage: cancelText) { (image, _) in

				guard let image = image else {
					return
				}

				let imageUpload = ImageEntryUpload(image: image, title: "")
				self.showDetailUploadView(imageUpload)
			}
			alert.show()
		}
	}

	/// Shows the detail upload view to fill the image entry information.
	///
	/// - Parameter imageUpload: Image entry to upload.
	open func showDetailUploadView(_ imageUpload: ImageEntryUpload) {
		let detailUploadView = createImageUploadDetailViewFromNib()

		if let detailUploadView = detailUploadView {
			detailUploadView.image = imageUpload.image
			detailUploadView.screenlet = self

			let presented = imageGalleryDelegate?.screenlet?(
					self,
					onImageUploadDetailViewCreated: detailUploadView) ?? false

			if !presented {
				let defaultViewController =
						ImageUploadDetailViewController_default(imageUploadDetailview: detailUploadView)
				let navigationController = UINavigationController(rootViewController: defaultViewController)
				presentingViewController?.present(navigationController, animated: true) {}
			}
		}
	}

	/// Call this method to delete an image entry.
	///
	/// - Parameter imageEntry: Image entry to be deleted.
	open func deleteImageEntry(_ imageEntry: ImageEntry) {
		if offlinePolicy == CacheStrategyType.remoteOnly.rawValue ||
				offlinePolicy == CacheStrategyType.remoteFirst.rawValue {

			_ = performAction(name: ImageGalleryScreenlet.DeleteImageAction, sender: imageEntry)
		}
		else {
			print("Error, delete only works on RemoteOnly or RemoteFirst mode")
		}
	}

	// MARK: BaseScreenlet

	override open func onCreated() {
		super.onCreated()
		ImageCache.screensOfflinePolicy = offlinePolicy ?? CacheStrategyType.remoteFirst.rawValue
	}

	override open func onShow() {
		// Don't reload the view if the picker is presented
		if !loadedOnce {
			loadedOnce = true
			super.onShow()
		}
	}

	@discardableResult
	override open func performAction(name: String, sender: AnyObject?) -> Bool {
		if name == ImageGalleryScreenlet.EnqueueUploadAction {
			guard let uploadEntry = sender as? ImageEntryUpload else {
				return false
			}

			viewModel.onImageUploadEnqueued?(uploadEntry)

			if uploadsQueue.isEmpty {
				uploadsQueue.insert(uploadEntry, at: 0)
				return super.performAction(
						name: ImageGalleryScreenlet.UploadImageAction,
						sender: sender)
			}
			else {
				uploadsQueue.insert(uploadEntry, at: 0)
				return false
			}
		}

		return super.performAction(name: name, sender: sender)
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		switch name {
		case BaseListScreenlet.LoadInitialPageAction, BaseListScreenlet.LoadPageAction:
			return super.createInteractor(name: name, sender: sender)

		case ImageGalleryScreenlet.DeleteImageAction:
			let imageEntry = sender as! ImageEntry
			return createImageGalleryDeleteInteractor(imageEntry)

		case ImageGalleryScreenlet.UploadImageAction:
			let imageUpload = sender as! ImageEntryUpload
			return createImageUploadInteractor(imageUpload)

		default:
			return nil
		}
	}

	// MARK: BaseListScreenlet

	override open  func onLoadPageError(page: Int, error: NSError) {
		super.onLoadPageError(page: page, error: error)
		imageGalleryDelegate?.screenlet?(self, onImageEntriesError: error)
	}

	override open func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
		super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)
		imageGalleryDelegate?.screenlet?(self, onImageEntriesResponse: rows as! [ImageEntry])
	}

	override open func onSelectedRow(_ row: AnyObject) {
		super.onSelectedRow(row)
		imageGalleryDelegate?.screenlet?(self, onImageEntrySelected: row as! ImageEntry)
	}

	override open func createPageLoadInteractor(
		page: Int,
		     computeRowCount: Bool) -> BaseListPageLoadInteractor {

		let finalMimeTypes = mimeTypes.isEmpty ? DefaultMimeTypes : parseMimeTypes(mimeTypes)

		let interactor = ImageGalleryLoadInteractor(
			screenlet: self,
			page: page,
			computeRowCount: computeRowCount,
			repositoryId: repositoryId,
			folderId: folderId,
			mimeTypes: finalMimeTypes)

		interactor.cacheStrategy =
			CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		return interactor
	}

	// MARK: Internal methods

	internal func createImageGalleryDeleteInteractor(
			_ imageEntry: ImageEntry) -> ImageGalleryDeleteInteractor? {

		let index = viewModel.indexOf?(imageEntry: imageEntry) ?? -1

		if index != -1 {
			let page = pageFromRow(index)

			let interactor = ImageGalleryDeleteInteractor(
				screenlet: self,
				imageEntryId: imageEntry.imageEntryId,
				repositoryId: repositoryId,
				folderId: folderId,
				page: page)

			interactor.onSuccess = {
				self.imageGalleryDelegate?.screenlet?(self, onImageEntryDeleted: imageEntry)
				self.viewModel.onImageEntryDeleted?(imageEntry)
			}

			interactor.onFailure = {
				self.imageGalleryDelegate?.screenlet?(self, onImageEntryDeleteError: $0)
			}

			interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

			return interactor
		}
		else {
			print("ERROR Image entry does not exist")

			return nil
		}
	}

	internal func createImageUploadInteractor(
			_ imageUpload: ImageEntryUpload) -> ImageGalleryUploadInteractor {

		let rowCount = viewModel.totalEntries
		let page = pageFromRow(rowCount)

		let interactor = ImageGalleryUploadInteractor(
				screenlet: self,
				imageUpload: imageUpload,
				repositoryId: repositoryId,
				folderId: folderId,
				page: page) { (_, totalBytesSent, totalBytesToSend) in

			self.viewModel.onImageUploadProgress?(
					totalBytesSent,
					bytesToSend: totalBytesToSend,
					imageEntryUpload: imageUpload)

			self.imageGalleryDelegate?.screenlet?(
					self, onImageUploadProgress: imageUpload,
					totalBytesSent: totalBytesSent,
					totalBytesToSend: totalBytesToSend)
		}

		interactor.onSuccess = {
			guard let result = interactor.result else {
				return
			}

			let imageEntry = ImageEntry(attributes: result)
			imageEntry.image = imageUpload.thumbnail

			self.imageGalleryDelegate?.screenlet?(self, onImageUploaded: imageEntry)
			self.viewModel.onImageUploaded?(imageEntry)
			self.startNextUploadIfExist()
		}

		interactor.onFailure = {
			if $0.code == ScreensErrorCause.cancelled.rawValue {
				self.uploadsQueue.removeAll()
			}
			else {
				self.viewModel.onImageUploadError?(imageUpload, error: $0)
				self.startNextUploadIfExist()
			}

			self.imageGalleryDelegate?.screenlet?(self, onImageUploadError: $0)
		}

		interactor.cacheStrategy =
			CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		self.imageGalleryDelegate?.screenlet?(self, onImageUploadStart: imageUpload)

		return interactor
	}

	internal func startNextUploadIfExist() {
		_ = uploadsQueue.popLast()

		if !uploadsQueue.isEmpty {
			performAction(name: ImageGalleryScreenlet.UploadImageAction, sender: uploadsQueue.last)
		}
	}

	internal func createImageUploadDetailViewFromNib() -> ImageUploadDetailViewBase? {

		if let foundView = Bundle.viewForThemeOrDefault(
				name: uploadDetailViewName,
				themeName: themeName ?? BaseScreenlet.DefaultThemeName,
				currentClass: type(of: self)) as? ImageUploadDetailViewBase {

			return foundView
		}

		print("ERROR: Xib file doesn't found for \(uploadDetailViewName) and theme '\(themeName)'\n")

		return nil
	}

	internal func parseMimeTypes(_ mimeTypes: String) -> [String] {
		return mimeTypes.characters.split(separator: ",").map(String.init)
	}

}
