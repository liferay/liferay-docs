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

@objc(DefaultTextField)
open class DefaultTextField: UITextField {

	// MARK: Public variables

	open var buttonMargin: CGFloat = 10

	open var onRightButtonClick: (() -> Void)?

	// MARK: IBInspectable

	@IBInspectable open var defaultColor: UIColor = .lightGray

	@IBInspectable open var highlightColor: UIColor = DefaultThemeBasicBlue

	@IBInspectable open var errorColor: UIColor = .red

	@IBInspectable open var paddingLeft: CGFloat = 15

	@IBInspectable open var paddingRight: CGFloat = 15

	@IBInspectable open var leftImage: UIImage? {
		didSet {
			if let image = leftImage {

				let icon = UIImageView(image: image)

				icon.contentMode = .center

				self.leftViewMode = .always
				self.leftView = icon
			}
		}
	}

	@IBInspectable open var rightButtonImage: UIImage? {
		didSet {
			if let image = rightButtonImage {

				self.rightViewMode = .always
				self.rightView = createButton(withImage: image)
			}
		}
	}

	@IBInspectable open var rightButtonTitle: String? {
		didSet {
			if let title = rightButtonTitle {

				self.rightViewMode = .always
				self.rightView = createButton(withTitle: title)
			}
		}
	}

	// MARK: Initializers

	override init(frame: CGRect) {
		super.init(frame: frame)

		setup()
	}

	public required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)

		setup()
	}

	open override func prepareForInterfaceBuilder() {
		setup()
	}

	// MARK: Public methods

	open func setDefaultState() {
		self.layer.borderColor = defaultColor.cgColor
	}

	open func setErrorState() {
		self.layer.borderColor = errorColor.cgColor
	}

	// MARK: Internal methods

	internal func setup() {
		self.layer.cornerRadius = 4.0
		self.layer.borderWidth = 1.0
		self.layer.borderColor = defaultColor.cgColor
	}

	internal func rightButtonClick() {
		onRightButtonClick?()
	}

	internal func createButton(withImage image: UIImage? = nil,
								withTitle title: String? = nil) -> UIButton {

		let button = UIButton()

		button.setImage(image, for: .normal)
		button.backgroundColor = highlightColor
		button.tintColor = .white
		button.setTitle(title, for: .normal)
		button.adjustsImageWhenHighlighted = false
		button.addTarget(self,
		                 action: #selector(rightButtonClick),
		                 for: .touchUpInside)

		setButtonDefaultStyle(button)

		return button
	}

	// MARK: UITextField

	open override func resignFirstResponder() -> Bool {
		self.layer.borderColor = defaultColor.cgColor

		return super.resignFirstResponder()
	}

	open override func becomeFirstResponder() -> Bool {
		self.layer.borderColor = highlightColor.cgColor

		return super.becomeFirstResponder()
	}

	open override func textRect(forBounds bounds: CGRect) -> CGRect {
		if leftView != nil {
			return super.textRect(forBounds: bounds)
		}

		return CGRect(x: paddingLeft, y: 0, width: bounds.width - paddingRight, height: bounds.height)
	}

	open override func editingRect(forBounds bounds: CGRect) -> CGRect {
		if leftView != nil {
			return super.editingRect(forBounds: bounds)
		}

		return textRect(forBounds: bounds)
	}

	open override func leftViewRect(forBounds bounds: CGRect) -> CGRect {
		return CGRect(origin: CGPoint.zero, size: CGSize(width: bounds.height, height: bounds.height))
	}

	open override func rightViewRect(forBounds bounds: CGRect) -> CGRect {
		let boundsCalculated = super.rightViewRect(forBounds: bounds)

		let origin = CGPoint(x: boundsCalculated.origin.x - buttonMargin/2,
		                     y: boundsCalculated.minY)

		let size = CGSize(width: bounds.height - buttonMargin,
		                  height: bounds.height - buttonMargin)

		return CGRect(origin: origin, size: size)
	}
}
