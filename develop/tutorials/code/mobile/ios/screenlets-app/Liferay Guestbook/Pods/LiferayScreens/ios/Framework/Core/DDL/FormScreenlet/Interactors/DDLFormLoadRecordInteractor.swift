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

class DDLFormLoadRecordInteractor: ServerReadConnectorInteractor {

	let recordId: Int64

	let structureId: Int64?

	var resultRecordData: [String:AnyObject]?

	var resultRecordAttributes: [String:AnyObject]?

	var resultRecordId: Int64?

	var resultFormRecord: DDLRecord?

	var resultFormUserId: Int64?

	// MARK: Initializers

	init(screenlet: BaseScreenlet?, recordId: Int64, structureId: Int64?) {
		self.recordId = recordId
		self.structureId = structureId

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override func createConnector() -> ServerConnector {
		let connector: ServerConnector

		let loadRecordCon = LiferayServerContext.connectorFactory.createDDLFormRecordLoadConnector(recordId)

		if let structureId = structureId {
			let loadFormCon = LiferayServerContext.connectorFactory.createDDLFormLoadConnector(structureId)

			let connectorChain = ServerConnectorChain(head: loadFormCon)

			connectorChain.onNextStep = { (c, seq) -> ServerConnector? in
				if let loadFormCon = c as? DDLFormLoadLiferayConnector {
					self.resultFormRecord = loadFormCon.resultRecord
					self.resultFormUserId = loadFormCon.resultUserId

					return loadRecordCon
				}

				return nil
			}

			connector = connectorChain
		}
		else {
			connector = loadRecordCon
		}

		return connector
	}

	override func completedConnector(_ c: ServerConnector) {
		let recordData: [String:AnyObject]?
		let recordAttributes: [String:AnyObject]?
		let recordId: Int64?

		if let chain = c as? ServerConnectorChain,
				let loadFormOp = chain.headConnector as? DDLFormLoadLiferayConnector,
				let loadRecordOp = chain.currentConnector as? DDLFormRecordLoadLiferayConnector,
				let recordForm = loadFormOp.resultRecord,
				let formUserId = loadFormOp.resultUserId {

			recordData = loadRecordOp.resultRecordData
			recordAttributes = loadRecordOp.resultRecordAttributes
			recordId = loadRecordOp.resultRecordId

			self.resultFormRecord = recordForm
			self.resultFormUserId = formUserId
		}
		else if let loadRecordCon = c as? DDLFormRecordLoadLiferayConnector {
			recordData = loadRecordCon.resultRecordData
			recordAttributes = loadRecordCon.resultRecordAttributes
			recordId = loadRecordCon.resultRecordId
		}
		else {
			recordData = nil
			recordAttributes = nil
			recordId = nil
		}

		self.resultRecordData = recordData
		self.resultRecordAttributes = recordAttributes
		self.resultRecordId = recordId

		if let recordDataValue = recordData,
				let recordAttributesValue = recordAttributes {
				self.resultFormRecord?.updateCurrentValues(values: recordDataValue)
			self.resultFormRecord?.attributes = recordAttributesValue
		}
	}

	// MARK: Cache methods

	override func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		if let chain = c as? ServerConnectorChain,
				let loadFormOp = chain.headConnector as? DDLFormLoadLiferayConnector,
				let recordForm = loadFormOp.resultRecord,
				let formUserId = loadFormOp.resultUserId,
				let loadRecordOp = chain.currentConnector as? DDLFormRecordLoadLiferayConnector,
				let recordData = loadRecordOp.resultRecordData,
				let recordAttributes = loadRecordOp.resultRecordAttributes {

			cacheManager.setClean(
				collection: ScreenletName(DDLFormScreenlet.self),
				key: "structureId-\(self.structureId)",
				value: recordForm,
				attributes: [
					"userId": formUserId.description as AnyObject])

			let record = DDLRecord(
				data: recordData,
				attributes: recordAttributes)

			cacheManager.setClean(
				collection: ScreenletName(DDLFormScreenlet.self),
				key: "recordId-\(loadRecordOp.recordId)",
				value: recordData as NSCoding,
				attributes: ["record": record])
		}
		else if let loadRecordCon = c as? DDLFormRecordLoadLiferayConnector,
					let recordData = loadRecordCon.resultRecordData,
					let recordAttributes = loadRecordCon.resultRecordAttributes {

			let record = DDLRecord(
				data: recordData,
				attributes: recordAttributes)

			// save just record data
			cacheManager.setClean(
				collection: ScreenletName(DDLFormScreenlet.self),
				key: "recordId-\(loadRecordCon.recordId)",
				value: recordData as NSCoding,
				attributes: ["record": record])
		}
	}

	override func readFromCache(_ c: ServerConnector, result: @escaping (AnyObject?) -> Void) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			result(nil)
			return
		}

		if let chain = c as? ServerConnectorChain,
				let loadFormOp = chain.headConnector as? DDLFormLoadLiferayConnector, structureId != nil {

			// load form and record

			cacheManager.getSomeWithAttributes(
					collection: ScreenletName(DDLFormScreenlet.self),
					keys: ["structureId-\(structureId)", "recordId-\(recordId)"]) { objects, attributes in

				if let recordForm = objects[0] as? DDLRecord,
						let recordUserId = attributes[0]?["userId"]?.int64Value,
						let recordData = objects[1] as? [String:AnyObject],
						let recordAttributes = attributes[1] {

					let record = recordAttributes["record"] as! DDLRecord

					precondition(self.recordId == record.recordId, "RecordId is not consistent")

					loadFormOp.resultRecord = recordForm
					loadFormOp.resultUserId = recordUserId

					let loadRecordOp = LiferayServerContext.connectorFactory.createDDLFormRecordLoadConnector(self.recordId)

					loadRecordOp.resultRecordData = recordData
					loadRecordOp.resultRecordAttributes = record.attributes
					loadRecordOp.resultRecordId = self.recordId
					chain.currentConnector = loadRecordOp

					result(true as AnyObject?)
				}
				else {
					result(nil)
				}
			}
		}
		else if let loadRecordCon = c as? DDLFormRecordLoadLiferayConnector {
			// load just record
			cacheManager.getAnyWithAttributes(
					collection: ScreenletName(DDLFormScreenlet.self),
					key: "recordId-\(loadRecordCon.recordId)") { object, attributes in

				let record = attributes?["record"] as! DDLRecord

				precondition(self.recordId == record.recordId, "RecordId is not consistent")

				loadRecordCon.resultRecordData = object as? [String:AnyObject]
				loadRecordCon.resultRecordAttributes = record.attributes
				loadRecordCon.resultRecordId = loadRecordCon.recordId

				result(loadRecordCon.resultRecordData as AnyObject?)
			}
		}
		else {
			result(nil)
		}
	}

}
