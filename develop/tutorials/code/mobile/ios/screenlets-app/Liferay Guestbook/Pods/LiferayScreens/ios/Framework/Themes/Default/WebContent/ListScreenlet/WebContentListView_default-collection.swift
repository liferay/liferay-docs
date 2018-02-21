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
import WebKit

open class WebContentListView_default_collection: BaseListCollectionView {

	// MARK: BaseScreenletView

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	override open func doConfigureCollectionView(_ collectionView: UICollectionView) {
		collectionView.backgroundColor = .white
	}

	override open func doCreateLayout() -> UICollectionViewLayout {
		let layout = UICollectionViewFlowLayout()
		layout.itemSize = CGSize(width: 300, height: 500)
		layout.sectionInset = UIEdgeInsets(top: 0, left: 20, bottom: 0, right: 20)
		layout.scrollDirection = .horizontal

		return layout
	}

	override open func doRegisterCellNibs() {
		collectionView?.register(WebViewCell.self, forCellWithReuseIdentifier: "cell")
		collectionView?.register(UICollectionViewCell.self, forCellWithReuseIdentifier: "progress")
	}

	override open func doFillLoadedCell(
			indexPath: IndexPath,
			cell: UICollectionViewCell,
			object: AnyObject) {

		guard let cell = cell as? WebViewCell, let object = object as? WebContent
			else {
				return
		}

		cell.html = object.html ?? "No html available"
		cell.layer.borderWidth = 1
		cell.layer.borderColor = UIColor.black.cgColor
	}

	override open func doFillInProgressCell(
			indexPath: IndexPath,
			cell: UICollectionViewCell) {

		cell.backgroundColor = .black
	}

	override open func doGetCellId(indexPath: IndexPath, object: AnyObject?) -> String {
		if object != nil {
			return "cell"
		}

		return "progress"
	}
}

open class WebViewCell: UICollectionViewCell {

	fileprivate var webView: WKWebView?

	fileprivate let styles =
		".MobileCSS {padding: 4%; width: 92%;} " +
		".MobileCSS, .MobileCSS span, .MobileCSS p, .MobileCSS h1, .MobileCSS h2, .MobileCSS h3 { " +
		"font-size: 110%; font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif; font-weight: 200; } " +
		".MobileCSS img { width: 100% !important; } " +
		".span2, .span3, .span4, .span6, .span8, .span10 { width: 100%; }"

	open var html: String {
		get {
			return ""
		}
		set {
			let styledHtml = "<style>\(styles)</style><div class=\"MobileCSS\">\(newValue)</div>"

			webView?.loadHTMLString(styledHtml, baseURL: URL(string:LiferayServerContext.server))
		}
	}

	override init(frame: CGRect) {

		super.init(frame: frame)

		initialize()
	}

	required public init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)

		initialize()
	}

	func initialize() {
		let config = WKWebViewConfiguration.noCacheConfiguration
		webView = WKWebView(frame: CGRect.zero, configuration: config)
		webView?.frame = bounds
		webView?.injectCookies()
		webView?.injectViewportMetaTag()

		addSubview(webView!)
	}
}
