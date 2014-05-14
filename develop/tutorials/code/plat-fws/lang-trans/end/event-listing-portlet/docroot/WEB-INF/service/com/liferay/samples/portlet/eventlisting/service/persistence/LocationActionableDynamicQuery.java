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

package com.liferay.samples.portlet.eventlisting.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.samples.portlet.eventlisting.model.Location;
import com.liferay.samples.portlet.eventlisting.service.LocationLocalServiceUtil;

/**
 * @author Joe Bloggs
 * @generated
 */
public abstract class LocationActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public LocationActionableDynamicQuery() throws SystemException {
		setBaseLocalService(LocationLocalServiceUtil.getService());
		setClass(Location.class);

		setClassLoader(com.liferay.samples.portlet.eventlisting.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("locationId");
	}
}