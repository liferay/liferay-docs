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

package com.liferay.docs.guestbook.service.base;

import com.liferay.docs.guestbook.service.GuestbookServiceUtil;

import java.util.Arrays;

/**
 * @author Rich Sezov
 * @generated
 */
public class GuestbookServiceClpInvoker {
	public GuestbookServiceClpInvoker() {
		_methodName34 = "getBeanIdentifier";

		_methodParameterTypes34 = new String[] {  };

		_methodName35 = "setBeanIdentifier";

		_methodParameterTypes35 = new String[] { "java.lang.String" };

		_methodName40 = "addGuestbook";

		_methodParameterTypes40 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName41 = "deleteGuestbook";

		_methodParameterTypes41 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName42 = "getGuestbooks";

		_methodParameterTypes42 = new String[] { "long" };

		_methodName43 = "getGuestbooks";

		_methodParameterTypes43 = new String[] { "long", "int", "int" };

		_methodName44 = "getGuestbookByG_N";

		_methodParameterTypes44 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName45 = "getGuestbooksCount";

		_methodParameterTypes45 = new String[] { "long" };

		_methodName46 = "updateGuestbook";

		_methodParameterTypes46 = new String[] {
				"long", "long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return GuestbookServiceUtil.getBeanIdentifier();
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			GuestbookServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return GuestbookServiceUtil.addGuestbook(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return GuestbookServiceUtil.deleteGuestbook(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return GuestbookServiceUtil.getGuestbooks(((Long)arguments[0]).longValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return GuestbookServiceUtil.getGuestbooks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return GuestbookServiceUtil.getGuestbookByG_N(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return GuestbookServiceUtil.getGuestbooksCount(((Long)arguments[0]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return GuestbookServiceUtil.updateGuestbook(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
}