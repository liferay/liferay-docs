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

/// The FileDisplayScreenletDelegate protocol defines some methods that you use to manage the
/// FileDisplayScreenlet events. All of them are optional.
@objc(FileDisplayScreenletDelegate)
public protocol FileDisplayScreenletDelegate: BaseScreenletDelegate {

	/// Called when the screenlet receives the file.
	///
	/// - Parameters:
	///   - screenlet: File display screenlet instance.
	///   - url: File URL.
	@objc optional func screenlet(_ screenlet: FileDisplayScreenlet, onFileAssetResponse url: URL)

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: File display screenlet instance.
	///   - error: Error while retrieving the file.
	@objc optional func screenlet(_ screenlet: FileDisplayScreenlet, onFileAssetError error: NSError)
}

/// File Display Screenlet shows a single file from a Liferay Portal instance’s Documents and Media
/// Library. Use this Screenlet to display file types not covered by the other display Screenlets 
/// (e.g., DOC, PPT, XLS).
@objc(FileDisplayScreenlet)
open class FileDisplayScreenlet: BaseScreenlet {

	// MARK: Static properties

	open static let LoadFileAction = "LoadFileAction"

	// MARK: Inspectables

	/// The primary key of the file.
	@IBInspectable open var assetEntryId: Int64 = 0

	/// The file’s fully qualified class name. Since files in a Documents and Media Library are 
	/// DLFileEntry objects, their className is com.liferay.document.library.kernel.model.DLFileEntry. 
	/// The className and classPK attributes are required to instantiate the Screenlet.
	@IBInspectable open var className: String =
		AssetClasses.getClassName(AssetClassNameKey_DLFileEntry)!

	/// The file’s unique identifier. The className and classPK attributes are required to 
	/// instantiate the Screenlet.
	@IBInspectable open var classPK: Int64 = 0

	/// Whether the file automatically loads when the Screenlet appears in the app’s UI. 
	/// The default value is true.
	@IBInspectable open var autoLoad: Bool = true

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var fileDisplayDelegate: FileDisplayScreenletDelegate? {
		return delegate as? FileDisplayScreenletDelegate
	}

	open var fileDisplayViewModel: FileDisplayViewModel? {
		return screenletView as? FileDisplayViewModel
	}

	open var supportedMimeTypes: [String] {
		return []
	}

	open var fileEntry: FileEntry?

	// MARK: BaseScreenlet

	override open func onShow() {
		if autoLoad {
			load()
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		if isActionRunning(name) {
			cancelInteractorsForAction(name)
		}

		switch name {
			case BaseScreenlet.DefaultAction:
				return createLoadAssetInteractor()
			case FileDisplayScreenlet.LoadFileAction:
				return createLoadFileInteractor()
			default:
				return nil
		}
	}

	// MARK: Public methods

	/// Call this method to load the file.
	///
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load() -> Bool {
		if fileEntry == nil {
			return performDefaultAction()
		}
		else {
			return self.performAction(name: FileDisplayScreenlet.LoadFileAction)
		}
	}

	// MARK: Private methods

	fileprivate func createLoadAssetInteractor() -> Interactor? {
		let interactor: LoadAssetInteractor

		if assetEntryId != 0 {
			interactor = LoadAssetInteractor(screenlet: self, assetEntryId: assetEntryId)
		}
		else {
			interactor = LoadAssetInteractor(
				screenlet: self, className: self.className, classPK: self.classPK)
		}

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultAsset = interactor.asset {

				guard let assetMimeType = resultAsset.mimeType,
						self.supportedMimeTypes.contains(assetMimeType) else {

						self.fileDisplayDelegate?.screenlet?(self,
						        onFileAssetError: NSError.errorWithCause(.invalidServerResponse,
						                message: "Asset mimeType is not supported."))
						return
				}

				self.fileEntry = FileEntry(attributes: resultAsset.attributes)
				self.load()
			}
			else {
				self.fileDisplayDelegate?.screenlet?(self,
						onFileAssetError: NSError.errorWithCause(.invalidServerResponse,
								message: "No file entry found."))
			}
		}

		interactor.onFailure = {
			self.fileDisplayDelegate?.screenlet?(self, onFileAssetError: $0)
		}

		return interactor
	}

	fileprivate func createLoadFileInteractor() -> Interactor? {
		guard let fileEntry = fileEntry else {
			return nil
		}

		let interactor = LoadFileEntryInteractor(screenlet: self, fileEntry: fileEntry)

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultUrl = interactor.resultUrl {
				let title = fileEntry.title

				self.fileDisplayDelegate?.screenlet?(self, onFileAssetResponse: resultUrl as URL)

				self.fileDisplayViewModel?.url = resultUrl
				self.fileDisplayViewModel?.title = title
			}
			else {
				self.fileDisplayDelegate?.screenlet?(self,
						onFileAssetError: NSError.errorWithCause(.invalidServerResponse,
								message: "No file entry found."))
			}
		}

		interactor.onFailure = {
			self.fileDisplayDelegate?.screenlet?(self, onFileAssetError: $0)
		}

		return interactor
	}

}
