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
 * @author Joe Bloggs
 * @generated
 */
public class EventServiceClpInvoker {
	public EventServiceClpInvoker() {
		_methodName26 = "getBeanIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "setBeanIdentifier";

		_methodParameterTypes27 = new String[] { "java.lang.String" };

		_methodName32 = "addEvent";

		_methodParameterTypes32 = new String[] {
				"com.nosester.portlet.eventlisting.model.Event"
			};

		_methodName33 = "addEvent";

		_methodParameterTypes33 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName34 = "update";

		_methodParameterTypes34 = new String[] {
				"com.nosester.portlet.eventlisting.model.Event"
			};

		_methodName35 = "updateEvent";

		_methodParameterTypes35 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName36 = "delete";

		_methodParameterTypes36 = new String[] {
				"com.nosester.portlet.eventlisting.model.Event"
			};

		_methodName37 = "deleteEvent";

		_methodParameterTypes37 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return EventServiceUtil.getBeanIdentifier();
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			EventServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return EventServiceUtil.addEvent((com.nosester.portlet.eventlisting.model.Event)arguments[0]);
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return EventServiceUtil.addEvent((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2],
				((Long)arguments[3]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return EventServiceUtil.update((com.nosester.portlet.eventlisting.model.Event)arguments[0]);
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return EventServiceUtil.updateEvent(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3], ((Long)arguments[4]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return EventServiceUtil.delete((com.nosester.portlet.eventlisting.model.Event)arguments[0]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return EventServiceUtil.deleteEvent(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
}