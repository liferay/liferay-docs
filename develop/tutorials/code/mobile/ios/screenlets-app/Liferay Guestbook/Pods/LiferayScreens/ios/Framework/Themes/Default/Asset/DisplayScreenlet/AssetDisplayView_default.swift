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

@objc(AssetDisplayView_default)
open class AssetDisplayView_default: BaseScreenletView, AssetDisplayViewModel {

	open override var progressMessages: [String : ProgressMessages] {
		return [
			BaseScreenlet.DefaultAction: [.working: NoProgressMessage]
		]
	}

	// MARK: AssetDisplayViewModel

	open var asset: Asset?

	open var innerScreenlet: UIView? {
		set {
			if let oldScreenlet = _innerScreenlet {
				oldScreenlet.removeFromSuperview()
				_innerScreenlet = nil
			}

			if let newScreenlet = newValue {
				_innerScreenlet = newScreenlet
				self.addSubview(newScreenlet)

				newScreenlet.translatesAutoresizingMaskIntoConstraints = false

				//Pin all edges from inner Screenlets to view edges
				let top = NSLayoutConstraint(item: newScreenlet, attribute: .top, relatedBy: .equal,
				                             toItem: self, attribute: .top, multiplier: 1, constant: 0)
				let bottom = NSLayoutConstraint(item: newScreenlet, attribute: .bottom, relatedBy: .equal,
				                                toItem: self, attribute: .bottom, multiplier: 1, constant: 0)
				let leading = NSLayoutConstraint(item: newScreenlet, attribute: .leading, relatedBy: .equal,
				                                 toItem: self, attribute: .leading, multiplier: 1, constant: 0)
				let trailing = NSLayoutConstraint(item: newScreenlet, attribute: .trailing, relatedBy: .equal,
				                                  toItem: self, attribute: .trailing, multiplier: 1, constant: 0)

				NSLayoutConstraint.activate([top, bottom, leading, trailing])

				newScreenlet.layoutIfNeeded()
			}
		}
		get {
			return _innerScreenlet
		}
	}

	fileprivate var _innerScreenlet: UIView?
}
