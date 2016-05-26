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

package com.liferay.docs.guestbook.service.http;

import com.liferay.docs.guestbook.service.EntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.docs.guestbook.service.EntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.docs.guestbook.model.EntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.docs.guestbook.model.Entry}, that is translated to a
 * {@link com.liferay.docs.guestbook.model.EntrySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Rich Sezov
 * @see EntryServiceHttp
 * @see com.liferay.docs.guestbook.model.EntrySoap
 * @see com.liferay.docs.guestbook.service.EntryServiceUtil
 * @generated
 */
public class EntryServiceSoap {
	public static com.liferay.docs.guestbook.model.EntrySoap addEntry(
		long userId, long guestbookId, java.lang.String name,
		java.lang.String email, java.lang.String message,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.docs.guestbook.model.Entry returnValue = EntryServiceUtil.addEntry(userId,
					guestbookId, name, email, message, serviceContext);

			return com.liferay.docs.guestbook.model.EntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.docs.guestbook.model.EntrySoap deleteEntry(
		long entryId, com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.docs.guestbook.model.Entry returnValue = EntryServiceUtil.deleteEntry(entryId,
					serviceContext);

			return com.liferay.docs.guestbook.model.EntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.docs.guestbook.model.EntrySoap[] getEntries(
		long groupId, long guestbookId) throws RemoteException {
		try {
			java.util.List<com.liferay.docs.guestbook.model.Entry> returnValue = EntryServiceUtil.getEntries(groupId,
					guestbookId);

			return com.liferay.docs.guestbook.model.EntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.docs.guestbook.model.EntrySoap[] getEntries(
		long groupId, long guestbookId, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.docs.guestbook.model.Entry> returnValue = EntryServiceUtil.getEntries(groupId,
					guestbookId, start, end);

			return com.liferay.docs.guestbook.model.EntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getEntriesCount(long groupId, long guestbookId)
		throws RemoteException {
		try {
			int returnValue = EntryServiceUtil.getEntriesCount(groupId,
					guestbookId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.docs.guestbook.model.EntrySoap updateEntry(
		long userId, long guestbookId, long entryId, java.lang.String name,
		java.lang.String email, java.lang.String message,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.docs.guestbook.model.Entry returnValue = EntryServiceUtil.updateEntry(userId,
					guestbookId, entryId, name, email, message, serviceContext);

			return com.liferay.docs.guestbook.model.EntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(EntryServiceSoap.class);
}