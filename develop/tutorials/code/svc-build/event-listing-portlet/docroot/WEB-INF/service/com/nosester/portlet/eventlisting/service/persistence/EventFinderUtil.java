/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.nosester.portlet.eventlisting.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Joe Bloggs
 */
public class EventFinderUtil {
	public static java.util.List<com.nosester.portlet.eventlisting.model.Event> findByEventNameEventDescriptionLocationName(
		java.lang.String eventName, java.lang.String eventDescription,
		java.lang.String locationName, int begin, int end) {
		return getFinder()
				   .findByEventNameEventDescriptionLocationName(eventName,
			eventDescription, locationName, begin, end);
	}

	public static java.util.List<com.nosester.portlet.eventlisting.model.Event> findByEventNameEventDescriptionLocationNameDQ(
		java.lang.String eventName, java.lang.String eventDescription,
		java.lang.String locationName) {
		return getFinder()
				   .findByEventNameEventDescriptionLocationNameDQ(eventName,
			eventDescription, locationName);
	}

	public static EventFinder getFinder() {
		if (_finder == null) {
			_finder = (EventFinder)PortletBeanLocatorUtil.locate(com.nosester.portlet.eventlisting.service.ClpSerializer.getServletContextName(),
					EventFinder.class.getName());

			ReferenceRegistry.registerReference(EventFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(EventFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(EventFinderUtil.class, "_finder");
	}

	private static EventFinder _finder;
}