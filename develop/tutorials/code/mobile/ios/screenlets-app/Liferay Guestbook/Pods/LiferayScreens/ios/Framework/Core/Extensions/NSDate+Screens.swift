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

extension Date {

	public init(millisecondsSince1970 millis: Double) {
		let timeInterval = TimeInterval(millis/1000)
		self.init(timeIntervalSince1970: timeInterval)
	}

	public func equalToDate(_ date: Date) -> Bool {
		return self.compare(date) == ComparisonResult.orderedSame
	}

	public var timeAgo: String {
		let components = self.dateComponents()

		switch (components.year ?? -1, components.month ?? -1, components.day ?? -1,
		        components.hour ?? -1, components.minute ?? -1, components.second ?? -1) {
		case (1, _, _, _, _, _):
			return LocalizedString("default", key: "time-last-year", obj: self as AnyObject)
		case let (y, _, _, _, _, _) where y > 1:
			return String(format: LocalizedString("default", key: "time-ago-years", obj: self as AnyObject), y)
		case (0, 1, _, _, _, _):
			return LocalizedString("default", key: "time-last-month", obj: self as AnyObject)
		case let (0, m, _, _, _, _) where m > 1:
			return String(format: LocalizedString("default", key: "time-ago-months", obj: self as AnyObject), m)
		case (0, 0, 7...13, _, _, _):
			return LocalizedString("default", key: "time-last-week", obj: self as AnyObject)
		case let (0, 0, d, _, _, _) where d > 13:
			return String(format: LocalizedString("default", key: "time-ago-weeks", obj: self as AnyObject), d/7)
		case (0, 0, 1, _, _, _):
			return LocalizedString("default", key: "time-yesterday", obj: self as AnyObject)
		case let (0, 0, d, _, _, _) where d > 1:
			return String(format: LocalizedString("default", key: "time-ago-days", obj: self as AnyObject), d)
		case (0, 0, 0, 1, _, _):
			return LocalizedString("default", key: "time-ago-hour", obj: self as AnyObject)
		case let (0, 0, 0, h, _, _) where h > 1:
			return String(format: LocalizedString("default", key: "time-ago-hours", obj: self as AnyObject), h)
		case (0, 0, 0, 0, 1, _):
			return LocalizedString("default", key: "time-ago-minute", obj: self as AnyObject)
		case let (0, 0, 0, 0, m, _) where m > 1:
			return String(format: LocalizedString("default", key: "time-ago-minutes", obj: self as AnyObject), m)
		case let (0, 0, 0, 0, 0, s) where s > 5:
			return String(format: LocalizedString("default", key: "time-ago-seconds", obj: self as AnyObject), s)
		default:
			return LocalizedString("default", key: "time-now", obj: self as AnyObject)
		}
	}

	fileprivate func dateComponents() -> DateComponents {
		let calendar = Calendar.current
		let components: NSCalendar.Unit = [.second, .minute, .hour, .day, .month, .year]
		return (calendar as NSCalendar).components(components, from: self, to: Date(), options: [])
	}

}
