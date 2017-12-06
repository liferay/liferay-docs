# Implementing an Entry Asset Renderer [](id=implementing-an-entry-asset-renderer)

<div class="learn-path-step">
    <p>Implementing Asset Renderers<br>Step 2 of 2</p>
</div>

The classes you'll create here are nearly identical to the 
`GuestbookAssetRenderer` and `GuestbookAssetRendererFactory` classes you created
for guestbooks in the previous step. This step provides the code needed for 
guestbook entries. Please review the previous sections for more information on 
this code. 

## Creating the EntryAssetRenderer Class [](id=creating-the-entryassetrenderer-class)

In the `com.liferay.docs.guestbook.asset` package, create an 
`EntryAssetRenderer` class that extends @product@'s `BaseJSPAssetRenderer` 
class. Replace the contents of your `EntryAssetRenderer` class with the 
following code: 

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
    import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
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

      @Override
      public boolean isPrintable() {
            return true;
      }

      private Entry _entry;
    }

This class is similar to the `GuestbookAssetRenderer` class. For the
`EntryAssetRenderer.getSummary` method, you return a summary that displays the 
entry name (the name of the user who created the entry) and the entry message. 

`GuestbookAssetRenderer.getSummary` returns a summary that displays the 
guestbook name. `EntryAssetRenderer.getTitle` returns the entry message. 
`GuestbookAssetRenderer.getTitle` returns the guestbook name. The rest of the
methods of `EntryAssetRenderer` are nearly identical to those of
`GuestbookAssetRenderer`. 

## Creating the EntryAssetRendererFactory Class [](id=creating-the-entryassetrendererfactory-class)

Next, you must create the guestbook entry asset renderer's factory class. In the 
`com.liferay.docs.guestbook.asset` package, create a class called 
`EntryAssetRendererFactory` that extends @product@'s `BaseAssetRendererFactory` 
class. Replace its contents with the following code: 

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

## Exporting the Asset Package [](id=exporting-the-asset-package)

The container needs to make the asset renderers and their factories available to
@product@ when it needs them. To do this, you must export the package. 

Open the `guestbook-service` module's `bnd.bnd` file and add the asset package
to the `Export-Package` declaration. When you're finished, it should look like
this: 

    Export-Package: com.liferay.docs.guestbook.asset,\
                    com.liferay.docs.guestbook.service.permission,\
                    com.liferay.docs.guestbook.search

Now your guestbook project's entities are fully asset-enabled. To test the
functionality, add the Asset Publisher portlet to a page and add a few
guestbooks and guestbook entries. Edit a few of them, too. Then, check the Asset 
Publisher portlet. The Asset Publisher, by default, dynamically displays assets 
of any kind from the current site. 

![Figure 1: After you've implemented and registered your asset renderers for your custom entities, the Asset Publisher can display your entities.](../../../../images/custom-entities-asset-publisher.png)

Confirm that the Asset Publisher displays the guestbooks and guestbook entries
that you added. 

Great! In the next section, you'll update your portlets' user interfaces to use
several features of @product@'s asset framework: comments, ratings, tags, 
categories, and related assets. 
