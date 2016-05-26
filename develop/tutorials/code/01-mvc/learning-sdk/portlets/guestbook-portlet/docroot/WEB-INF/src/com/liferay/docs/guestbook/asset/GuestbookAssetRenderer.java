package com.liferay.docs.guestbook.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

public class GuestbookAssetRenderer extends BaseAssetRenderer {
	
	private Guestbook _guestbook;
	
	public GuestbookAssetRenderer (Guestbook guestbook) {
		
		_guestbook = guestbook;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		
		long guestbookId = _guestbook.getGuestbookId();
		
		boolean contains = false;
		
		try {
			contains = GuestbookPermission.contains(permissionChecker,
					guestbookId, ActionKeys.UPDATE);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		
		long guestbookId = _guestbook.getGuestbookId();
		
		boolean contains = false;
		
		try {
			contains = GuestbookPermission.contains(permissionChecker,
					guestbookId, ActionKeys.VIEW);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		return contains;
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
	public long getGroupId() {
		return _guestbook.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		return "Name: " + _guestbook.getName();
	}

	@Override
	public String getTitle(Locale locale) {
		return _guestbook.getName();
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
	public String render(RenderRequest renderRequest,
			RenderResponse renderResponse, String template) throws Exception {
		
		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("gb_guestbook", _guestbook);

			return "/html/guestbookadmin/" + template + ".jsp";
		}
		else {
			return null;
		}
	}
	
	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {

		return themeDisplay.getURLPortal()
				+ "/guestbook-portlet/guestbook.png";

	}
	
	private Log _log;

}
