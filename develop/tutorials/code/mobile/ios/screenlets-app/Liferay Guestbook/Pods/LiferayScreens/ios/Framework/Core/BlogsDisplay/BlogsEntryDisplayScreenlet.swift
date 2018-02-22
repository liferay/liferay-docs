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

/// The BlogsEntryDisplayScreenletDelegate protocol defines some methods that you use to manage the
/// BlogsEntryDisplayScreenlet events. All of them are optional.
@objc(BlogsEntryDisplayScreenletDelegate)
public protocol BlogsEntryDisplayScreenletDelegate: BaseScreenletDelegate {

	/// Called when the Screenlet receives the BlogsEntry object.
	///
	/// - Parameters:
	///   - screenlet: Blogs entry display screenlet instance.
	///   - blogEntry: Blog entry object.
	@objc optional func screenlet(_ screenlet: BlogsEntryDisplayScreenlet,
			onBlogEntryResponse blogEntry: BlogsEntry)

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Blogs entry display screenlet instance.
	///   - error: Error while retrieving blog entry.
	@objc optional func screenlet(_ screenlet: BlogsEntryDisplayScreenlet,
			onBlogEntryError error: NSError)
}

/// Blogs Entry Display Screenlet displays a single blog entry. Image Display Screenlet renders any
/// header image the blogs entry may have.
@objc(BlogsEntryDisplayScreenlet)
open class BlogsEntryDisplayScreenlet: BaseScreenlet {

	// MARK: Inspectables

	/// The primary key of the blog entry (BlogsEntry).
	@IBInspectable open var assetEntryId: Int64 = 0

	/// The BlogsEntry object’s unique identifier. If you don’t use assetEntryId, the className and 
	/// classPK attributes are required to instantiate the Screenlet.
	@IBInspectable open var classPK: Int64 = 0

	/// Whether the blog entry automatically loads when the Screenlet appears in the app’s UI. 
	/// The default value is true.
	@IBInspectable open var autoLoad: Bool = true

	/// The offline mode setting. The default value is remote-first.
	@IBInspectable open var offlinePolicy: String? = CacheStrategyType.remoteFirst.rawValue

	// MARK: Public properties

	open var blogsEntryDisplayDelegate: BlogsEntryDisplayScreenletDelegate? {
		return delegate as? BlogsEntryDisplayScreenletDelegate
	}

	open var blogsEntryViewModel: BlogsDisplayViewModel? {
		return screenletView as? BlogsDisplayViewModel
	}

	open var blogsEntry: BlogsEntry? {
		didSet {
			blogsEntryViewModel?.blogsEntry = self.blogsEntry
		}
	}

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

		let interactor: LoadAssetInteractor

		if assetEntryId != 0 {
			interactor = LoadAssetInteractor(screenlet: self, assetEntryId: assetEntryId)
		}
		else {
			interactor = LoadAssetInteractor(
				screenlet: self,
				className: AssetClasses.getClassName(AssetClassNameKey_BlogsEntry)!,
				classPK: self.classPK)
		}

		interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .remoteFirst

		interactor.onSuccess = {
			if let resultAsset = interactor.asset {
				self.blogsEntry = BlogsEntry(attributes: resultAsset.attributes)
				self.blogsEntryViewModel?.blogsEntry = self.blogsEntry
				self.blogsEntryDisplayDelegate?.screenlet?(self, onBlogEntryResponse: self.blogsEntry!)
			}
			else {
				self.blogsEntryDisplayDelegate?.screenlet?(self, onBlogEntryError:
					NSError.errorWithCause(.invalidServerResponse, message: "No blog entry found."))
			}
		}

		interactor.onFailure = {
			self.blogsEntryDisplayDelegate?.screenlet?(self, onBlogEntryError: $0)
		}

		return interactor
	}

	// MARK: Public methods

	/// Loads a blog entry in the screenlet.
	///
	/// - Returns: True if the interactor was able to start, false otherwise.
	@discardableResult
	open func load() -> Bool {
		return self.performDefaultAction()
	}
}
