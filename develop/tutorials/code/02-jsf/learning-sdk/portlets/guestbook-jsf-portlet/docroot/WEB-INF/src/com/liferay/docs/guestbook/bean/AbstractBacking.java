/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
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
package com.liferay.docs.guestbook.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


abstract class AbstractBacking {

	// Logger
	protected static final Logger logger = LoggerFactory.getLogger(AbstractBacking.class);

	protected static final String UNEXPECTED_ERROR_MSG_ID = "an-unexpected-error-occurred";
	protected static final String SUCCESS_INFO_MSG_ID = "your-request-processed-successfully";

	public void addGlobalSuccessInfoMessage() {
		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
		liferayFacesContext.addGlobalSuccessInfoMessage();
	}

	public void addGlobalUnexpectedErrorMessage() {
		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
		liferayFacesContext.addGlobalUnexpectedErrorMessage();
	}

}
