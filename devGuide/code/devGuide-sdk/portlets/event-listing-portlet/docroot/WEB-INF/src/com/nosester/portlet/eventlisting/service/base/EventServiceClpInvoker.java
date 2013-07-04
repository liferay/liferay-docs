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

package com.nosester.portlet.eventlisting.service.base;

import com.nosester.portlet.eventlisting.service.EventServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class EventServiceClpInvoker {
	public EventServiceClpInvoker() {
		_methodName30 = "getBeanIdentifier";

		_methodParameterTypes30 = new String[] {  };

		_methodName31 = "setBeanIdentifier";

		_methodParameterTypes31 = new String[] { "java.lang.String" };

		_methodName36 = "addEvent";

		_methodParameterTypes36 = new String[] {
				"com.nosester.portlet.eventlisting.model.Event"
			};

		_methodName37 = "addEvent";

		_methodParameterTypes37 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName38 = "update";

		_methodParameterTypes38 = new String[] {
				"com.nosester.portlet.eventlisting.model.Event"
			};

		_methodName39 = "updateEvent";

		_methodParameterTypes39 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName40 = "delete";

		_methodParameterTypes40 = new String[] {
				"com.nosester.portlet.eventlisting.model.Event"
			};

		_methodName41 = "deleteEvent";

		_methodParameterTypes41 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return EventServiceUtil.getBeanIdentifier();
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			EventServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return EventServiceUtil.addEvent((com.nosester.portlet.eventlisting.model.Event)arguments[0]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return EventServiceUtil.addEvent((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2],
				((Long)arguments[3]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return EventServiceUtil.update((com.nosester.portlet.eventlisting.model.Event)arguments[0]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return EventServiceUtil.updateEvent(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3], ((Long)arguments[4]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return EventServiceUtil.delete((com.nosester.portlet.eventlisting.model.Event)arguments[0]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return EventServiceUtil.deleteEvent(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
}