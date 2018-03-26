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

@objc(DDLFormView_default)
open class DDLFormView_default: DDLFormTableView {

	override open var progressMessages: [String:ProgressMessages] {
		return [
			DDLFormScreenlet.LoadFormAction: [
				.working: LocalizedString("default", key: "ddlform-loading-message", obj: self),
				.failure: LocalizedString("default", key: "ddlform-loading-error", obj: self)
			],
			DDLFormScreenlet.LoadRecordAction: [
				.working: LocalizedString("default", key: "ddlform-loading-record-message", obj: self),
				.failure: LocalizedString("default", key: "ddlform-loading-record-error", obj: self)
			],
			DDLFormScreenlet.SubmitFormAction: [
				.working: LocalizedString("default", key: "ddlform-submitting-message", obj: self),
				.failure: LocalizedString("default", key: "ddlform-submitting-error", obj: self),
				.success: LocalizedString("default", key: "ddlform-submitted", obj: self)
			],
			DDLFormScreenlet.UploadDocumentAction: [
				.failure: LocalizedString("default", key: "ddlform-uploading-error", obj: self)
			]
		]
	}

	// MARK: DDLFormTableView 

	override open func onCreated() {
		super.onCreated()

		self.tableView?.alpha = 0.0
	}

	override open func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
		if self.tableView?.alpha == 0 {
			UIView.animate(withDuration: 0.3, animations: {
				self.tableView!.alpha = 1.0
			})
		}
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	override open func changeEditable(_ editable: Bool) {
		tableView?.subviews.forEach { $0.isUserInteractionEnabled = editable }
	}

}
