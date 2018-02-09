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

@objc(BlogsEntryDisplayView_default)
open class BlogsEntryDisplayView_default: BaseScreenletView, BlogsDisplayViewModel {

	// MARK: Outlets

	@IBOutlet weak var imageDisplayScreenlet: ImageDisplayScreenlet?

	@IBOutlet weak var userPortraitScreenlet: UserPortraitScreenlet?

	@IBOutlet weak var usernameLabel: UILabel?

	@IBOutlet weak var dateLabel: UILabel?

	@IBOutlet weak var titleLabel: UILabel?

	@IBOutlet weak var subtitleLabel: UILabel?

	@IBOutlet weak var contentLabel: UILabel?

	@IBOutlet weak var imageHeightConstraint: NSLayoutConstraint?

	open var headerImageHeight: CGFloat = 125.0

	open let dateFormatter: DateFormatter = {
		let dateFormatter = DateFormatter()
		dateFormatter.dateStyle = DateFormatter.Style.long
		dateFormatter.locale = Locale(
			identifier: NSLocale.currentLocaleString)
		return dateFormatter
	}()

	// MARK: BlogsDisplayViewModel

	open var blogsEntry: BlogsEntry? {
		didSet {
			if blogsEntry != nil {
				self.loadBlog()
			}
		}
	}

	// MARK: BaseScreenletView

	override open func onSetTranslations() {
		dateLabel?.text = LocalizedString("default", key: "blog-unknown-date", obj: self)
	}

	// MARK: Public methods

	open func loadBlog() {
		self.loadImage()
		self.loadUserInfo()
		self.loadDate()
		self.loadTitleSubtitle()
		self.loadContent()
	}

	open func loadImage() {
		let imageId = self.blogsEntry!.coverImageFileEntryId
		if imageId != 0 {
			imageHeightConstraint?.constant = self.headerImageHeight

			imageDisplayScreenlet?.className = AssetClasses.getClassName(AssetClassNameKey_DLFileEntry)!
			imageDisplayScreenlet?.classPK = imageId
			imageDisplayScreenlet?.load()
		}
		else {
			imageHeightConstraint?.constant = 0
		}
	}

	open func loadUserInfo() {
		userPortraitScreenlet?.load(userId: self.blogsEntry!.userId)
		usernameLabel?.text = self.blogsEntry!.userName
	}

	open func loadDate() {
		if let date = self.blogsEntry!.displayDate {
			dateLabel?.text = dateFormatter.string(from: date as Date)
		}
	}

	open func loadTitleSubtitle() {
		titleLabel?.text = self.blogsEntry!.title
		subtitleLabel?.text = self.blogsEntry!.subtitle
	}

	open func loadContent() {
		contentLabel?.attributedText = self.blogsEntry!.content
				.toHtmlTextWithAttributes(type(of: self).defaultAttributedTextAttributes())
	}

	open class func defaultAttributedTextAttributes() -> [String: NSObject] {
		let paragrahpStyle = NSMutableParagraphStyle()
		paragrahpStyle.lineBreakMode = .byWordWrapping

		var attributes: [String: NSObject] = [NSParagraphStyleAttributeName: paragrahpStyle]

		let font = UIFont(name: "HelveticaNeue", size: 17)

		if let font = font {
			attributes[NSFontAttributeName] = font
		}

		return attributes
	}
}
