package com.liferay.docs.guestbook.portlet.toolbar.contributor;

import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.service.permission.GuestbookModelPermission;
import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.toolbar.contributor.BasePortletToolbarContributor;
import com.liferay.portal.kernel.portlet.toolbar.contributor.PortletToolbarContributor;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author mike
 */
@Component(
    	immediate = true,
    	property = {
    		"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK, "mvc.path=-",
    		"mvc.path=/html/guestbookmvcportlet/view.jsp"
    	},
    	service = {
    		GuestbookPortletToolbarContributor.class,
    		PortletToolbarContributor.class
    	}
    )
public class GuestbookPortletToolbarContributor
extends BasePortletToolbarContributor {

	protected void addPortletTitleAddMenuItems(
            List<MenuItem> menuItems, PortletRequest portletRequest,
            PortletResponse portletResponse)
          throws Exception {

          ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

          LiferayPortletResponse liferayPortletResponse =
            _portal.getLiferayPortletResponse(portletResponse);

          long guestbookId = ParamUtil.getLong(portletRequest, "guestbookId");

          if ((guestbookId > 0) &&
            GuestbookPermission.contains(
              themeDisplay.getPermissionChecker(), guestbookId,
              "ADD_ENTRY")) {

            URLMenuItem addEntryURLMenuItem = new URLMenuItem();

            addEntryURLMenuItem.setLabel("Add Entry");

            PortletURL addEntryURL = liferayPortletResponse.createRenderURL();

            addEntryURL.setParameter(
              "mvcPath", "/html/guestbookmvcportlet/edit_entry.jsp");
            addEntryURL.setParameter(
    		  "guestbookId", String.valueOf(guestbookId));

            addEntryURLMenuItem.setURL(addEntryURL.toString());

            menuItems.add(addEntryURLMenuItem);
          }

          if (GuestbookModelPermission.contains(
              themeDisplay.getPermissionChecker(),
              themeDisplay.getScopeGroupId(), "ADD_GUESTBOOK")) {

            URLMenuItem addGuestbookURLMenuItem = new URLMenuItem();

            addGuestbookURLMenuItem.setLabel("Add Guestbook");

            PortletURL addGuestbookURL =
              liferayPortletResponse.createRenderURL();

            addGuestbookURL.setParameter(
              "mvcPath", "/html/guestbookmvcportlet/edit_guestbook.jsp");

            addGuestbookURLMenuItem.setURL(addGuestbookURL.toString());

            menuItems.add(addGuestbookURLMenuItem);
          }
        }
	
	@Override
    protected List<MenuItem> getPortletTitleMenuItems(
      PortletRequest portletRequest, PortletResponse portletResponse) {

      List<MenuItem> menuItems = new ArrayList<>();

      try {
        addPortletTitleAddMenuItems(
          menuItems, portletRequest, portletResponse);
      }
      catch (Exception e) {
        _log.error("Unable to add folder menu item", e);
      }

      return menuItems;
    }

    private static final Log _log = LogFactoryUtil.getLog(
      GuestbookPortletToolbarContributor.class);

    @Reference
    private Portal _portal;

    }