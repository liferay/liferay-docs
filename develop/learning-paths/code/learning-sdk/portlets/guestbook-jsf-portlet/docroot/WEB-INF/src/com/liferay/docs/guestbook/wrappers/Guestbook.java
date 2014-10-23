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
package com.liferay.docs.guestbook.wrappers;

import javax.el.ELContext;
import javax.faces.context.ExternalContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.docs.guestbook.model.GuestbookWrapper;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.jsp.PageContextAdapter;
import com.liferay.faces.util.jsp.StringJspWriter;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.taglib.security.PermissionsURLTag;


/**
 * @author  Vernon Singleton
 */
public class Guestbook extends GuestbookWrapper {

	// serialVersionUID
	private static final long serialVersionUID = -420986486105631030L;

	private static final Logger logger = LoggerFactory.getLogger(Guestbook.class);
	private static final String MODEL = "com.liferay.docs.guestbook.model.Guestbook";

	// private members
	private Boolean deleteable;
	private Boolean permissible;
	private String permissionsUrl;
	private Boolean updateable;
	private Boolean viewable;

	public Guestbook(com.liferay.docs.guestbook.model.Guestbook guestbook) {
		super(guestbook);
	}

	public Boolean getDeleteable() {

		if (deleteable == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			deleteable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
					MODEL, getGuestbookId(), ActionKeys.DELETE);
		}

		return deleteable;
	}

	public Boolean getPermissible() {

		if (permissible == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			permissible = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
					MODEL, getGuestbookId(), ActionKeys.PERMISSIONS);
		}

		return permissible;
	}

	public String getPermissionsUrl() {

		if (permissionsUrl == null) {

			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			ExternalContext externalContext = liferayFacesContext.getExternalContext();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();

			// Get the underlying HttpServletRequest and HttpServletResponse
			PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
			HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
			PortletResponse portletResponse = (PortletResponse) externalContext.getResponse();
			HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(portletResponse);
			ELContext elContext = liferayFacesContext.getELContext();
			StringJspWriter stringJspWriter = new StringJspWriter();
			PageContextAdapter pageContextAdapter = new PageContextAdapter(httpServletRequest, httpServletResponse,
					elContext, stringJspWriter);

			// Invoke the Liferay Tag class directly (rather than using the tag from a JSP).
			PermissionsURLTag permissionsURLTag = new PermissionsURLTag();

			permissionsURLTag.setPageContext(pageContextAdapter);
			permissionsURLTag.setModelResource(MODEL);
			permissionsURLTag.setModelResourceDescription(getName());
			permissionsURLTag.setRedirect("false");
			permissionsURLTag.setResourceGroupId(scopeGroupId);
			permissionsURLTag.setResourcePrimKey(String.valueOf(getGuestbookId()));

			// Set var to null if you want the tag to write out the url
			permissionsURLTag.setVar(null);

			try {
				permissionsURLTag.doStartTag();
				permissionsURLTag.doEndTag();
				permissionsUrl = stringJspWriter.toString();
			}
			catch (Exception e) {
				logger.error(e);
			}

		}

		return permissionsUrl;
	}

	public Boolean getUpdateable() {

		if (updateable == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			updateable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
					MODEL, getGuestbookId(), ActionKeys.UPDATE);
		}

		return updateable;
	}

	public Boolean getViewable() {

		if (viewable == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			viewable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
					MODEL, getGuestbookId(), ActionKeys.VIEW);
		}

		return viewable;
	}

}
