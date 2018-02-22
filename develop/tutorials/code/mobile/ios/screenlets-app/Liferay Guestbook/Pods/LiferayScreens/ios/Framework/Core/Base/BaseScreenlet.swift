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
// swiftlint:disable file_length
import UIKit
import QuartzCore

/// The BaseScreenletDelegate protocol defines one method that you use to manage the
/// BaseScreenlet events. This method is optional.
@objc(BaseScreenletDelegate)
public protocol BaseScreenletDelegate: NSObjectProtocol {

	/// Called when we want to return a custom interactor (use case) with the given action name.
	///
	/// - Parameters:
	///   - screenlet: Base screenlet instance.
	///   - customInteractorForAction: Action name.
	///   - withSender: Source of the event.
	/// - Returns: Custom interactor.
	@objc optional func screenlet(_ screenlet: BaseScreenlet,
		customInteractorForAction: String,
		withSender: AnyObject?) -> Interactor?

}

/// BaseScreenlet is the base class from which all Screenlet classes must inherit.
/// A screenlet is the container for a screenlet view.
@objc(BaseScreenlet)
@IBDesignable open class BaseScreenlet: UIView {

	// MARK: Static properties

	open static let DefaultAction = "defaultAction"
	open static let DefaultThemeName = "default"

	// MARK: IBOutlets

	@IBOutlet open weak var delegate: BaseScreenletDelegate?

	// MARK: Inspectables

	@IBInspectable open var themeName: String? {
		set {
			_themeName = (newValue ?? BaseScreenlet.DefaultThemeName).lowercased()

			if _runningOnInterfaceBuilder {
				_themeName = updateCurrentPreviewImage()
			}
			else {
				onPreCreate()
				loadScreenletView()
				onCreated()
			}

			screenletView?.themeName = _themeName
		}
		get {
			return _themeName
		}
	}

	// MARK: Public properties

	open weak var screenletView: BaseScreenletView?

	open weak var presentingViewController: UIViewController? {
		didSet {
			screenletView?.presentingViewController = self.presentingViewController
		}
	}

	// MARK: Internal properties

	internal var isRunningOnInterfaceBuilder: Bool {
		return _runningOnInterfaceBuilder
	}

	// MARK: Private properties

	fileprivate var _themeName = BaseScreenlet.DefaultThemeName

	fileprivate var _runningOnInterfaceBuilder = false

	fileprivate var _currentPreviewImage: UIImage?

	fileprivate var _previewLayer: CALayer?

	fileprivate var _runningInteractors = [String: [Interactor]]()

	fileprivate var _progressPresenter: ProgressPresenter?

	// MARK: Initializers

	/// Initializer for instantiate screenlets from code with its frame and theme name.
	///
	/// - Parameters:
	///   - frame: Size and position of the screenlet view.
	///   - themeName: Name of the theme to be used. If nil, default theme will be used.
	public init(frame: CGRect, themeName: String?) {
		super.init(frame: frame)

		clipsToBounds = true

		self.themeName = themeName
	}

	override convenience init(frame: CGRect) {
		self.init(frame: frame, themeName: nil)
	}

	required public init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	// MARK: UIView

	override open func awakeFromNib() {
		super.awakeFromNib()

		clipsToBounds = true

		if themeName == BaseScreenlet.DefaultThemeName {
			onPreCreate()
			loadScreenletView()
		presentingViewController = UIApplication.shared.keyWindow?.rootViewController

			onCreated()
		}
	}

	override open func becomeFirstResponder() -> Bool {
		return screenletView!.becomeFirstResponder()
	}

	override open func didMoveToWindow() {
		if window != nil {
			onShow()
		}
		else {
			onHide()
		}
	}

	// MARK: Interface Builder management methods

	override open func prepareForInterfaceBuilder() {
		_runningOnInterfaceBuilder = true

		_previewLayer = CALayer()

		updateCurrentPreviewImage()
	}

	// MARK: Internal methods

	internal func loadScreenletView() {
		let view = createScreenletViewFromNib()

		if let viewValue = view {
			viewValue.onPerformAction = { [weak self] name, sender in
				return self!.performAction(name: name, sender: sender)
			}

			viewValue.screenlet = self
			viewValue.presentingViewController = self.presentingViewController
			viewValue.themeName = _themeName

			if let oldView = self.screenletView {
				oldView.removeFromSuperview()
			}

			self._progressPresenter = viewValue.createProgressPresenter()
			self.screenletView = viewValue

			viewValue.translatesAutoresizingMaskIntoConstraints = false

			addSubview(viewValue)
			sendSubview(toBack: viewValue)

			//Pin all edges from Screenlet View to the Screenlet's edges
			let top = NSLayoutConstraint(item: viewValue, attribute: .top, relatedBy: .equal,
			                             toItem: self, attribute: .top, multiplier: 1, constant: 0)
			let bottom = NSLayoutConstraint(item: viewValue, attribute: .bottom, relatedBy: .equal,
			                                toItem: self, attribute: .bottom, multiplier: 1, constant: 0)
			let leading = NSLayoutConstraint(item: viewValue, attribute: .leading, relatedBy: .equal,
			                                 toItem: self, attribute: .leading, multiplier: 1, constant: 0)
			let trailing = NSLayoutConstraint(item: viewValue, attribute: .trailing, relatedBy: .equal,
			                                  toItem: self, attribute: .trailing, multiplier: 1, constant: 0)

			NSLayoutConstraint.activate([top, bottom, leading, trailing])

			viewValue.layoutIfNeeded()
		}
		else {
			self._progressPresenter = nil
			self.screenletView = nil
		}
	}

	/// previewImageForTheme loads the preview image for the screenlet with the given theme.
	///
	/// - Parameter themeName: Screenlet theme.
	/// - Returns: Preview image.
	internal func previewImageForTheme(_ themeName: String) -> UIImage? {
		let bundles = Bundle.allBundles(type(of: self))

		for b in bundles {
			let imageName = "\(themeName)-preview-\(ScreenletName(type(of: self)).lowercased())@2x"

			if let imagePath = b.path(forResource: imageName, ofType: "png") {
				if let imageData = try? Data(contentsOf: URL(fileURLWithPath: imagePath)) {
					return UIImage(data: imageData, scale: 2.0)
				}
			}
		}

		return nil
	}

	// MARK: Templated/event methods: intended to be overwritten by children classes

	/// onCreated is invoked after the screenlet is created.
	/// Override this method to set custom values for the screenlet properties.
	open dynamic func onCreated() {
	}

	/// onPreCreate is invoked before the screenlet is created.
	/// Override this method to set create new UI components programatically.
	open dynamic func onPreCreate() {
	}

	/// onHide is invoked when the screenlet is hidden from the app window.
	open dynamic func onHide() {
	}

	/// onShow is invoked when the screenlet is displayed on the app window.
	/// Override this method for example to reset values when the screenlet is shown.
	open dynamic func onShow() {
	}

	/// performAction is invoked when we want to start an interaction (use case)
	/// Typically, it's called from TouchUpInside UI event or when the programmer wants to
	/// start the interaction programatically.
	///
	/// - Parameters:
	///   - name: Action name.
	///   - sender: Source of the event.
	/// - Returns: False interactor ready to be started.
	@discardableResult
	open dynamic func performAction(name: String, sender: AnyObject? = nil) -> Bool {
		guard !isRunningOnInterfaceBuilder else {
			return false
		}

		let customInteractor = self.delegate?.screenlet?(self,
				customInteractorForAction: name,
				withSender: sender)

		let standardInteractor = self.createInteractor(
				name: name,
				sender: sender)

		if let interactor = customInteractor ?? standardInteractor {
			trackInteractor(interactor, withName: name)

			if let message = screenletView?.progressMessageForAction(name, messageType: .working) {
				showHUDWithMessage(message, forInteractor: interactor)
			}

			return onAction(name: name, interactor: interactor, sender: sender)
		}

		return false
	}

	/// performDefaultAction is invoked when we want to start the default use case.
	///
	/// - Returns: Call to performAction with the default action.
	@discardableResult
	open dynamic func performDefaultAction() -> Bool {
		return performAction(name: BaseScreenlet.DefaultAction, sender: nil)
	}

	/// onAction is invoked when an interaction should be started.
	///
	/// - Parameters:
	///   - name: Action name.
	///   - interactor: Custom or standard interactor.
	///   - sender: Source of the event.
	/// - Returns: Call for starting the interactor.
	@discardableResult
	open dynamic func onAction(name: String, interactor: Interactor, sender: AnyObject?) -> Bool {
		onStartInteraction()
		screenletView?.onStartInteraction()

		return interactor.start()
	}

	/// isActionRunnung checks if there is another action running at the same time.
	///
	/// - Parameter name: Action name.
	/// - Returns: True if there is another one running.
	open dynamic func isActionRunning(_ name: String) -> Bool {
		var firstInteractor: Interactor? = nil

		synchronized(_runningInteractors as AnyObject) {
			firstInteractor = self._runningInteractors[name]?.first
		}

		return firstInteractor != nil
	}

	/// cancelInteractorsForAction cancels all the interactors with the given action name.
	///
	/// - Parameter name: Action name.
	open dynamic func cancelInteractorsForAction(_ name: String) {
		let interactors = _runningInteractors[name] ?? []

		for interactor in interactors {
			interactor.cancel()
		}
	}

	/// createInteractor creates the proper interactor with the given action name.
	///
	/// - Parameters:
	///   - name: Action name.
	///   - sender: Source of the event.
	/// - Returns: Proper interactor for each use case.
	open dynamic func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		return nil
	}

	/// endInteractor is invoked when an interaction ends.
	///
	/// - Parameters:
	///   - interactor: A started interactor.
	///   - error: Nil if there is no error.
	open dynamic func endInteractor(_ interactor: Interactor, error: NSError?) {

		func getMessage() -> String? {
			if let error = error as? ValidationError {
				return error.localizedDescription
			}

			return screenletView?.progressMessageForAction(
					interactor.actionName ?? BaseScreenlet.DefaultAction,
					messageType: error == nil ? .success : .failure)
		}

		untrackInteractor(interactor)

		let result: AnyObject? = interactor.interactionResult()
		onFinishInteraction(result, error: error)
		screenletView?.onFinishInteraction(result, error: error)
		hideHUDWithMessage(getMessage(), forInteractor: interactor, withError: error)
	}

	/// onStartInteraction is called just before a screenlet request is sent to server.
	open dynamic func onStartInteraction() {
	}

	/// onFinishInteraction is called when the server response arrives
	open dynamic func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
	}

	// MARK: HUD methods

	/// showHUDWithMessage shows a HUD with the given message for the given use case.
	///
	/// - Parameters:
	///   - message: HUD message.
	///   - interactor: Interaction (use case).
	open dynamic func showHUDWithMessage(_ message: String?,
			forInteractor interactor: Interactor) {

		_progressPresenter?.showHUDInView(rootView(self),
			message: message,
			forInteractor: interactor)
	}

	/// hideHUDWithMessage hides a HUD with the given message for the given use case with an error.
	///
	/// - Parameters:
	///   - message: HUD message.
	///   - interactor: Interaction (use case).
	///   - error: Interaction error.
	open dynamic func hideHUDWithMessage(_ message: String?,
			forInteractor interactor: Interactor,
			withError error: NSError?) {

		_progressPresenter?.hideHUDFromView(rootView(self),
			message: message,
			forInteractor: interactor,
			withError: error)
	}

	// MARK: Public methods

	/// refreshTranslations refreshes all translations of the screenlet view.
	open dynamic func refreshTranslations() {
		screenletView?.onSetTranslations()
	}

	// MARK: Private methods

	fileprivate func createScreenletViewFromNib() -> BaseScreenletView? {

		let viewName = "\(ScreenletName(type(of: self)))View"

		if let foundView = Bundle.viewForThemeOrDefault(
				name: viewName,
				themeName: _themeName,
				currentClass: type(of: self)) as? BaseScreenletView {

			return foundView
		}

		print("ERROR: Xib file doesn't found for screenlet '\(ScreenletName(type(of: self)))' and theme '\(_themeName)'\n")

		return nil
	}

	@discardableResult
	fileprivate func updateCurrentPreviewImage() -> String {
		var appliedTheme = _themeName

		_currentPreviewImage = previewImageForTheme(_themeName)
		if _currentPreviewImage == nil {
			if let previewImage = previewImageForTheme(BaseScreenlet.DefaultThemeName) {
				_currentPreviewImage = previewImage
				appliedTheme = BaseScreenlet.DefaultThemeName
			}
		}

		if let screenletViewValue = screenletView {
			screenletViewValue.removeFromSuperview()
		}

		if let currentPreviewImageValue = _currentPreviewImage {
			_previewLayer!.frame = centeredRectInView(self, size: currentPreviewImageValue.size)
			_previewLayer!.contents = currentPreviewImageValue.cgImage

			if _previewLayer!.superlayer != layer {
				layer.addSublayer(_previewLayer!)
			}
		}
		else {
			loadScreenletView()
		}

		setNeedsLayout()

		return appliedTheme
	}

	fileprivate func rootView(_ currentView: UIView) -> UIView {
		if currentView.superview == nil {
			return currentView
		}

		return rootView(currentView.superview!)
	}

	fileprivate func trackInteractor(_ interactor: Interactor, withName name: String) {
		synchronized(_runningInteractors as AnyObject) {
			var interactors = self._runningInteractors[name]
			if interactors?.count ?? 0 == 0 {
				interactors = [Interactor]()
			}

			interactors?.append(interactor)

			self._runningInteractors[name] = interactors
			interactor.actionName = name
		}
	}

	fileprivate func untrackInteractor(_ interactor: Interactor) {
		synchronized(_runningInteractors as AnyObject) {
			let name = interactor.actionName!
			let interactors = self._runningInteractors[name] ?? []

			if let foundIndex = interactors.index(of: interactor) {
				self._runningInteractors[name]?.remove(at: foundIndex)
			}
			else {
				print("ERROR: There's no interactors tracked for name \(interactor.actionName!)\n")
			}
		}
	}
}
