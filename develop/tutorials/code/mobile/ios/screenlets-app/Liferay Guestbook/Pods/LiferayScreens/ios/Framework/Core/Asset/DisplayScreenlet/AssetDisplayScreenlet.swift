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

/// The AssetDisplayScreenletDelegate protocol defines some methods that you use to manage the
/// AssetDisplayScreenlet events. All of them are optional.
@objc(AssetDisplayScreenletDelegate)
public protocol AssetDisplayScreenletDelegate: BaseScreenletDelegate {

	///  Called when the screenlet receives the asset.
	///
	/// - Parameters:
	///   - screenlet: Asset display screenlet instance.
	///   - asset: Asset object.
	@objc optional func screenlet(_ screenlet: AssetDisplayScreenlet,
	                        onAssetResponse asset: Asset)

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Asset display screenlet instance.
	///   - error: Error while retrieving the asset.
	@objc optional func screenlet(_ screenlet: AssetDisplayScreenlet,
	                        onAssetError error: NSError)

	/// Called when the child Screenlet (the Screenlet rendered inside Asset Display Screenlet) 
	/// has been successfully initialized.
	/// Use this method to configure or customize it.
	///
	/// - Parameters:
	///   - screenlet: Asset display screenlet instance.
	///   - childScreenlet: Asset display screenlet inner screenlet.
	///   - asset: Asset object.
	@objc optional func screenlet(_ screenlet: AssetDisplayScreenlet,
	                        onConfigureScreenlet childScreenlet: BaseScreenlet?,
							onAsset asset: Asset)

	/// Called to render a custom asset.
	///
	/// - Parameters:
	///   - screenlet: Asset display screenlet instance.
	///   - asset: Custom asset.
	/// - Returns: Custom view.
	@objc optional func screenlet(_ screenlet: AssetDisplayScreenlet, onAsset asset: Asset) -> UIView?
}

/// Asset Display Screenlet can display an asset from a Liferay instance. The Screenlet can 
/// currently display Documents and Media files (DLFileEntry images, videos, audio files, and PDFs),
/// blogs entries (BlogsEntry) and web content articles (WebContent). 
/// Asset Display Screenlet can also display your custom asset types.
@objc(AssetDisplayScreenlet)
open class AssetDisplayScreenlet: BaseScreenlet {

	// MARK: Inspectables

	/// The primary key of the asset.
	@IBInspectable open var assetEntryId: Int64 = 0

	/// The asset’s fully qualified class name. For example, a blog entry’s className is 
	/// com.liferay.blogs.kernel.model.BlogsEntry. The className and classPK attributes are 
	/// required to instantiate the Screenlet.
	@IBInspectable open var className: String = ""

	///	The asset’s unique identifier. The className and classPK attributes are required to 
	/// instantiate the Screenlet.
	@IBInspectable open var classPK: Int64 = 0

	/// Saved configuration name for the Asset publisher portlet.
	@IBInspectable public var portletItemName: String?

	/// Whether the asset automatically loads when the Screenlet appears in the app’s UI. 
	/// The default value is true.
	@IBInspectable public var autoLoad: Bool = true

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var assetDisplayDelegate: AssetDisplayScreenletDelegate? {
		return delegate as? AssetDisplayScreenletDelegate
	}

	open var assetDisplayViewModel: AssetDisplayViewModel? {
		return screenletView as? AssetDisplayViewModel
	}

	open var assetEntry: Asset? {
		didSet {
			if let asset = assetEntry {
				if let innerScreenlet = self.createInnerScreenlet(asset) {
					self.assetDisplayViewModel?.innerScreenlet = innerScreenlet
					self.assetDisplayViewModel?.asset = asset

					self.assetDisplayDelegate?.screenlet?(self, onAssetResponse: asset)
				}
				else {
					self.assetDisplayViewModel?.asset = nil

					self.assetDisplayDelegate?.screenlet?(self,
							onAssetError: NSError.errorWithCause(.invalidServerResponse,
									message: "Could not create inner screenlet."))
				}
			}
		}
	}

	// MARK: BaseScreenlet

	override open func onShow() {
		if autoLoad {
			load()
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		let interactor: LoadAssetInteractor

		if assetEntryId != 0 {
			interactor = LoadAssetInteractor(screenlet: self, assetEntryId: assetEntryId)
		}
		else if className != "" && classPK != 0 {
			interactor = LoadAssetInteractor(
				screenlet: self, className: self.className, classPK: self.classPK)
		}
		else {
			interactor = LoadAssetInteractor(screenlet: self, portletItemName: self.portletItemName!)
		}

		interactor.cacheStrategy = CacheStrategyType(rawValue: offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			self.assetEntry = interactor.asset
		}

		interactor.onFailure = {
			self.assetDisplayDelegate?.screenlet?(self, onAssetError: $0)
		}

		return interactor
	}

	// MARK: Public methods

	/// Call this method to load the asset.
	///
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load() -> Bool {
		return self.performDefaultAction()
	}

	/// Creates Asset display screenlet inner screenlet.
	///
	/// - Parameter asset: Asset object.
	/// - Returns: Inner screenlet (UIView)
	open func createInnerScreenlet(_ asset: Asset) -> UIView? {
		guard let view = screenletView else {
			return nil
		}

		let frame = CGRect(origin: CGPoint.zero, size: view.frame.size)

		guard let innerScreenlet = AssetDisplayBuilder.createScreenlet(frame,
				asset: asset, themeName: themeName) else {

			return assetDisplayDelegate?.screenlet?(self, onAsset: asset)
		}

		configureInnerScreenlet(innerScreenlet, asset: asset)

		return innerScreenlet
	}

	/// Configures Asset display screenlet inner screenlet.
	///
	/// - Parameters:
	///   - innerScreenlet: Asset display screenlet inner screenlet (UIView).
	///   - asset: Asset object.
	open func configureInnerScreenlet(_ innerScreenlet: BaseScreenlet, asset: Asset) {
		if let screenlet = innerScreenlet as? FileDisplayScreenlet {
			screenlet.autoLoad = false
			screenlet.offlinePolicy = self.offlinePolicy
			screenlet.presentingViewController = self.presentingViewController

			if let image = asset as? ImageEntry {
				screenlet.classPK = image.imageEntryId
			}
			else {
				screenlet.fileEntry = FileEntry(attributes: asset.attributes)
			}

			screenlet.load()
		}
		else if let screenlet = innerScreenlet as? BlogsEntryDisplayScreenlet {
			screenlet.blogsEntry = BlogsEntry(attributes: asset.attributes)
			screenlet.autoLoad = false
			screenlet.offlinePolicy = self.offlinePolicy
		}
		else if let screenlet = innerScreenlet as? WebContentDisplayScreenlet {
			let webContent = WebContent(attributes: asset.attributes)
			screenlet.articleId = (webContent.attributes["articleId"] as? String) ?? ""
			screenlet.autoLoad = false
			screenlet.loadWebContent()
		}

		assetDisplayDelegate?.screenlet?(self, onConfigureScreenlet: innerScreenlet, onAsset: asset)
	}

	/// Removes Asset display screenlet inner screenlet.
	open func removeInnerScreenlet() {
		assetDisplayViewModel?.innerScreenlet = nil
	}
}
