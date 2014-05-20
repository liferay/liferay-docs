/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.samples.portlet.eventlisting.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import com.liferay.samples.portlet.eventlisting.service.ClpSerializer;
import com.liferay.samples.portlet.eventlisting.service.EventLocalServiceUtil;
import com.liferay.samples.portlet.eventlisting.service.EventServiceUtil;
import com.liferay.samples.portlet.eventlisting.service.LocationLocalServiceUtil;
import com.liferay.samples.portlet.eventlisting.service.LocationServiceUtil;

/**
 * @author Joe Bloggs
 */
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			EventLocalServiceUtil.clearService();

			EventServiceUtil.clearService();
			LocationLocalServiceUtil.clearService();

			LocationServiceUtil.clearService();
		}
	}
}