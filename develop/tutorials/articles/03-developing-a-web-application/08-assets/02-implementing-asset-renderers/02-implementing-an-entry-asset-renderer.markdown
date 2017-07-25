# Implementing an Entry Asset Renderer [](id=implementing-an-entry-asset-renderer)

The classes you'll create in this section are nearly identical to the 
`*AssetRenderer` and `*AssetRendererFactory` classes you created for guestbooks 
in the last section. This section provides the code needed for guestbook entries. 
For more information on these methods, please review the last section.

## Creating the Entry AssetRenderer Class

In the `com.liferay.docs.guestbook.asset` package, create an 
`EntryAssetRenderer` class that extends Liferay's `BaseJSPAssetRenderer` class.
Replace the contents of your `EntryAssetRenderer` class with the following code:

    package com.liferay.docs.guestbook.asset;

    import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
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
    import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.permission.EntryPermission;
    import java.util.Locale;
    import javax.portlet.PortletRequest;
    import javax.portlet.PortletResponse;
    import javax.portlet.PortletURL;
    import javax.portlet.WindowState;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    public class EntryAssetRenderer extends BaseJSPAssetRenderer<Entry> {

      public EntryAssetRenderer(Entry entry) {

        _entry = entry;
      }

      @Override
      public boolean hasViewPermission(PermissionChecker permissionChecker) 
      throws PortalException {

        long entryId = _entry.getEntryId();
        return EntryPermission.contains(permissionChecker, entryId, 
        ActionKeys.VIEW);
      }

      @Override
      public Entry getAssetObject() {
        return _entry;
      }

      @Override
      public long getGroupId() {
        return _entry.getGroupId();
      }

      @Override
      public long getUserId() {

        return _entry.getUserId();
      }

      @Override
      public String getUserName() {
        return _entry.getUserName();
      }

      @Override
      public String getUuid() {
        return _entry.getUuid();
      }

      @Override
      public String getClassName() {
        return Entry.class.getName();
      }

      @Override
      public long getClassPK() {
        return _entry.getEntryId();
      }

      @Override
      public String getSummary(PortletRequest portletRequest, 
      PortletResponse portletResponse) {
        return "Name: " + _entry.getName() + ". Message: " + _entry.getMessage();
      }

      @Override
      public String getTitle(Locale locale) {
        return _entry.getMessage();
      }

      @Override
      public boolean include(HttpServletRequest request, 
      HttpServletResponse response, String template) throws Exception {
        request.setAttribute("ENTRY", _entry);
        request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
        request.setAttribute("StringUtil", new StringUtil());
        return super.include(request, response, template);
      }

      @Override
      public String getJspPath(HttpServletRequest request, String template) {
        
        if (template.equals(TEMPLATE_FULL_CONTENT)) {
          request.setAttribute("gb_entry", _entry);

          return "/asset/entry/" + template + ".jsp";
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
        portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_entry");
        portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));
        portletURL.setParameter("showback", Boolean.FALSE.toString());

        return portletURL;
      }

      @Override
      public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
          LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) 
          throws Exception {
        try {
          long plid = PortalUtil.getPlidFromPortletId(_entry.getGroupId(),
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
          portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));

          String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

          portletURL.setParameter("redirect", currentUrl);

          return portletURL.toString();

        } catch (PortalException e) {

        } catch (SystemException e) {
        }

        return noSuchEntryRedirect;
      }

      @Override
      public String getURLView(LiferayPortletResponse liferayPortletResponse, 
      WindowState windowState) throws Exception {

        return super.getURLView(liferayPortletResponse, windowState);
      }

      private Entry _entry;
    }

This class is very similar to the `GuestbookAssetRenderer` class. For the
`EntryAssetRenderer.getSummary` method, you're returning a summary that displays
both the entry name (the name of the user who created the entry) and the entry
message. `GuestbookAssetRenderer.getSummary` just returned a summary that
displayed the guestbook name. `EntryAssetRenderer.getTitle` returns the entry
message. `GuestbookAssetRenderer.getTitle` returned the guestbook name. The rest
of the methods of `EntryAssetRenderer` are nearly identical to those of
`GuestbookAssetRenderer`.

## Creating the Entry AssetRendererFactory Class

Next, you need to create the factory class for the guestbook entry asset
renderer. In the `com.liferay.docs.guestbook.asset` package, create a class
called `EntryAssetRendererFactory` that extends Liferay's
`BaseAssetRendererFactory` class. Replace its contents with the following code:

    package com.liferay.docs.guestbook.asset;

    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.EntryLocalService;
    import com.liferay.docs.guestbook.service.permission.EntryPermission;
    import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
    import com.liferay.portal.kernel.util.WebKeys;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
    import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
    import com.liferay.portal.kernel.security.permission.PermissionChecker;
    import com.liferay.portal.kernel.theme.ThemeDisplay;

    import javax.portlet.PortletRequest;
    import javax.portlet.PortletURL;
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
        return EntryPermission.contains(permissionChecker, classPK, actionId);
      }

      @Override
      public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
          LiferayPortletResponse liferayPortletResponse, long classTypeId) {
        
        PortletURL portletURL = null;

        try {
          ThemeDisplay themeDisplay = (ThemeDisplay) 
          liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

          portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
              GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
          portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_entry");
          portletURL.setParameter("showback", Boolean.FALSE.toString());
        } catch (PortalException e) {
        }

        return portletURL;
      }

      @Override
      public boolean isLinkable() {
        return _LINKABLE;
      }
      
      @Override
      public String getIconCssClass() {
          return "pencil";
      }
      
      @Reference(target = "(osgi.web.symbolicname=com.liferay.docs.guestbook)")
      private ServletContext _servletContext;
      @Reference
      private EntryLocalService _entryLocalService;
      
      private static final boolean _LINKABLE = true;
      public static final String CLASS_NAME = Entry.class.getName();
      public static final String TYPE = "entry";

    }

Now your guestbook project's entities have been fully asset-enabled. To test the
functionality that you added, add the Asset Publisher portlet to a page of your
locally running Liferay Portal instance. Then add a few guestbooks and guestbook
entries. Edit a few of them too. Then check the Asset Publisher portlet. By
default, the Asset Publisher is configured to dynamically display assets of any
kind from the current site.

![Figure 1: After you've implemented and registered your asset renderers for your custom entities, the Asset Publisher can display your entities.](../../../../images/custom-entities-asset-publisher.png)

Confirm that the Asset Publisher is displaying the guestbooks and guestbook
entries that you added.

Great! In the next section, you'll update your portlets' user interfaces to use
several features of Liferay's asset framework: comments, ratings, tags,
categories, and related assets.
