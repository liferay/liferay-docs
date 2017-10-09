package com.liferay.docs.guestbook.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.docs.guestbook.service.permission.EntryPermission;
import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK},
	service = AssetRendererFactory.class
)
public class EntryAssetRendererFactory extends BaseAssetRendererFactory<Entry> {

	public EntryAssetRendererFactory() {
		setClassName(CLASS_NAME);
		setLinkable(_LINKABLE);
		setPortletId(GuestbookPortletKeys.GUESTBOOK);
		setSearchable(true);
		setSelectable(true);
	}

	@Override
	public AssetRenderer<Entry> getAssetRenderer(long classPK, int type)
		throws PortalException {

		Entry entry = _entryLocalService.getEntry(classPK);

		EntryAssetRenderer entryAssetRenderer = new EntryAssetRenderer(entry);

		entryAssetRenderer.setAssetRendererType(type);
		entryAssetRenderer.setServletContext(_servletContext);

		return entryAssetRenderer;
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
		long classPK, String actionId) throws Exception {

		Entry entry = _entryLocalService.getEntry(classPK);
		return EntryPermission.contains(permissionChecker, entry, actionId);
	}

	@Override
	public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, long classTypeId) {

		PortletURL portletURL = null;

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

			portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
				GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
			portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_entry");
			portletURL.setParameter("showback", Boolean.FALSE.toString());
		} catch (PortalException e) {
		}

		return portletURL;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) {

		LiferayPortletURL liferayPortletURL
			= liferayPortletResponse.createLiferayPortletURL(
				GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		} catch (WindowStateException wse) {

		}
		return liferayPortletURL;
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	public String getIconCssClass() {
		return "pencil";
	}

	@Reference(target = "(osgi.web.symbolicname=com.liferay.docs.guestbook.portlet)",
		unbind = "-")
	public void setServletContext (ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference(unbind = "-")
	protected void setEntryLocalService(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	private EntryLocalService _entryLocalService;
	private ServletContext _servletContext;
	private static final boolean _LINKABLE = true;
	public static final String CLASS_NAME = Entry.class.getName();
	public static final String TYPE = "entry";

}
