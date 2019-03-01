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
package com.liferay.docs.guestbook.web.internal.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rich Sezov
 */
public class GuestbookAssetRenderer extends BaseJSPAssetRenderer<Guestbook> {

	public GuestbookAssetRenderer(Guestbook guestbook) {

		_guestbook = guestbook;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws PortalException {

		long guestbookId = _guestbook.getGuestbookId();
		return GuestbookPermission.contains(permissionChecker, guestbookId,
			ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException {

		long guestbookId = _guestbook.getGuestbookId();
		return GuestbookPermission.contains(permissionChecker, guestbookId,
			ActionKeys.VIEW);
	}

	@Override
	public Guestbook getAssetObject() {
		return _guestbook;
	}

	@Override
	public long getGroupId() {
		return _guestbook.getGroupId();
	}

	@Override
	public long getUserId() {

		return _guestbook.getUserId();
	}

	@Override
	public String getUserName() {
		return _guestbook.getUserName();
	}

	@Override
	public String getUuid() {
		return _guestbook.getUuid();
	}

	@Override
	public String getClassName() {
		return Guestbook.class.getName();
	}

	@Override
	public long getClassPK() {
		return _guestbook.getGuestbookId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		return "Name: " + _guestbook.getName();
	}

	@Override
	public String getTitle(Locale locale) {
		return _guestbook.getName();
	}

	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
		request.setAttribute("GUESTBOOK", _guestbook);
		request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
		request.setAttribute("StringUtil", new StringUtil());
		return super.include(request, response, template);
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			request.setAttribute("gb_guestbook", _guestbook);

			return "/asset/guestbook/" + template + ".jsp";
		} else {
			return null;
		}
	}

	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) throws Exception {
		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest), GuestbookPortletKeys.GUESTBOOK,
			PortletRequest.RENDER_PHASE);
		portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
		portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));
		portletURL.setParameter("showback", Boolean.FALSE.toString());

		return portletURL;
	}

	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
		try {
			long plid = PortalUtil.getPlidFromPortletId(_guestbook.getGroupId(),
				GuestbookPortletKeys.GUESTBOOK);

			PortletURL portletURL;
			if (plid == LayoutConstants.DEFAULT_PLID) {
				portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(liferayPortletRequest),
					GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
			} else {
				portletURL = PortletURLFactoryUtil.create(liferayPortletRequest,
					GuestbookPortletKeys.GUESTBOOK, plid, PortletRequest.RENDER_PHASE);
			}

			portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/view");
			portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));

			String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

			portletURL.setParameter("redirect", currentUrl);

			return portletURL.toString();

		} catch (PortalException e) {

			logger.log(Level.SEVERE, e.getMessage());

		} catch (SystemException e) {

			logger.log(Level.SEVERE, e.getMessage());
		}

		return noSuchEntryRedirect;
	}

	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) throws Exception {

		return super.getURLView(liferayPortletResponse, windowState);
	}

	private Guestbook _guestbook;
	private Logger logger = Logger.getLogger(this.getClass().getName());
}
