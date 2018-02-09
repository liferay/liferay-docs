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

public protocol KeyboardLayoutable {

	func layoutWhenKeyboardShown(_ keyboardHeight: CGFloat, animation:(time: NSNumber, curve: NSNumber))

	func layoutWhenKeyboardHidden()
}

open class KeyboardManager {

	fileprivate struct StaticData {
		static var currentHeight: CGFloat?
		static var visible = false
	}

	open class var currentHeight: CGFloat? {
		get {
			return StaticData.currentHeight
		}
		set {
			StaticData.currentHeight = newValue
		}
	}

	open class var isVisible: Bool {
		return StaticData.visible
	}

	//FIXME
	open class var defaultHeight: CGFloat { return 253 }
	open class var defaultAutocorrectionBarHeight: CGFloat { return 38 }

	fileprivate var layoutable: KeyboardLayoutable?

	public init() {
	}

	deinit {
		unregisterObserver()
	}

	open func registerObserver(_ layoutable: KeyboardLayoutable) {
		self.layoutable = layoutable

		NotificationCenter.default.addObserver(self,
				selector: #selector(KeyboardManager.keyboardShown(_:)),
				name: .UIKeyboardWillShow,
				object: nil)

		NotificationCenter.default.addObserver(self,
				selector: #selector(KeyboardManager.keyboardHidden(_:)),
				name: .UIKeyboardWillHide,
				object: nil)
	}

	open func unregisterObserver() {
		self.layoutable = nil

		NotificationCenter.default.removeObserver(self,
				name: .UIKeyboardDidShow,
				object: nil)

		NotificationCenter.default.removeObserver(self,
				name: .UIKeyboardDidHide,
				object: nil)
	}

	// MARK: Private methods

	fileprivate dynamic func keyboardShown(_ notification: Notification?) {
		let value = notification!.userInfo![UIKeyboardFrameEndUserInfoKey] as! NSValue
		let frame = adjustRectForCurrentOrientation(value.cgRectValue)

		StaticData.currentHeight = frame.size.height
		StaticData.visible = true

		let animationDuration =
				notification!.userInfo![UIKeyboardAnimationDurationUserInfoKey] as! NSNumber
		let animationCurve =
				notification!.userInfo![UIKeyboardAnimationCurveUserInfoKey] as! NSNumber

		layoutable?.layoutWhenKeyboardShown(frame.size.height,
				animation: (time: animationDuration, curve: animationCurve))
	}

	fileprivate dynamic func keyboardHidden(_ notification: Notification?) {
		StaticData.visible = false

		layoutable?.layoutWhenKeyboardHidden()
	}

}
