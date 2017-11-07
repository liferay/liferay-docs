# Implementing a Guestbook Asset Renderer [](id=implementing-a-guestbook-asset-renderer)

<div class="learn-path-step">
    <p>Implementing Asset Renderers<br>Step 1 of 2</p>
</div>

@product@'s asset renderers follow the factory pattern, so you must create a 
`GuestbookAssetRendererFactory` that instantiates the `GuestbookAssetRenderer`'s
private guestbook object. Here, you'll create both classes. 

Get started by creating the Asset Renderer class first. 

## Creating the AssetRenderer Class [](id=creating-the-assetrenderer-class)

Follow these steps to create the `GuestbookAssetRenderer` class: 

1.  Create a new package called `com.liferay.docs.guestbook.asset` in the
    `guestbook-service` module's `src/main/java` folder. In this package, create
    a `GuestbookAssetRenderer` class that extends @product@'s 
    `BaseJSPAssetRenderer` class. Extending this class gives you a head-start on 
    implementing the `AssetRenderer` interface. Start with this code: 

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
        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
        import java.util.Locale;
        import javax.portlet.PortletRequest;
        import javax.portlet.PortletResponse;
        import javax.portlet.PortletURL;
        import javax.portlet.WindowState;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class GuestbookAssetRenderer extends BaseJSPAssetRenderer<Guestbook> {

        }
 
2.  Add the constructor and the guestbook class variable next. Most of the 
    methods in this class are simply getters that return fields from this 
    private guestbook object: 
      
        public GuestbookAssetRenderer(Guestbook guestbook) {

                    _guestbook = guestbook;
        }
        
        private Guestbook _guestbook;
 
3.  The `BaseJSPAssetRenderer` abstract class that you're extending contains 
    dummy implementations of the `hasEditPermission` and `hasViewPermission`
    methods that you must override. Override these dummy implementations with 
    actual permission checks using the `GuestbookPermission` class that you 
    created earlier:

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

4.  Add the following getter methods to retrieve information about the guestbook 
    asset: 
            
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
        public String getSummary(PortletRequest portletRequest, PortletResponse 
          portletResponse) {
            return "Name: " + _guestbook.getName();
        }

        @Override
        public String getTitle(Locale locale) {
          return _guestbook.getName();
        }

        @Override
        public boolean include(HttpServletRequest request, HttpServletResponse 
          response, String template) throws Exception {
            request.setAttribute("GUESTBOOK", _guestbook);
            request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
            request.setAttribute("StringUtil", new StringUtil());
            return super.include(request, response, template);
        }

    The final method makes several utilities, as well as the `Guestbook`
    entity, available to @product@ in the `HttpServletRequest` object. 

5.  Override the `getJspPath` method. This method returns a string that 
    represents the path to the JSP that renders the guestbook asset. When the 
    Asset Publisher displays an asset's full content, it invokes the asset 
    renderer class's `getJspPath` method and passes a `template` string 
    parameter that equals `"full_content"`. This returns 
    `/asset/guestbook/full_content.jsp` when the `full_content` template string 
    is passed as a parameter. You'll create this JSP later when updating your 
    application's user interface: 

          @Override
          public String getJspPath(HttpServletRequest request, String template) {
            
            if (template.equals(TEMPLATE_FULL_CONTENT)) {
              request.setAttribute("gb_guestbook", _guestbook);

              return "/asset/guestbook/" + template + ".jsp";
            } else {
              return null;
            }
          }

6.  Override the `getURLEdit` method. This method returns a URL for editing the
    asset: 

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

7.  Override the `getURLViewInContext` method. This method returns a URL to view 
    the asset in its native application: 

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

            } catch (SystemException e) {
            }

            return noSuchEntryRedirect;
          }

8.  Override the `getURLView` method. This method returns a URL to view the 
    asset from within the Asset Publisher: 

          @Override
          public String getURLView(LiferayPortletResponse liferayPortletResponse, 
          WindowState windowState) throws Exception {

            return super.getURLView(liferayPortletResponse, windowState);
          }

9.  Save the class. 

10. You have an error in your class, because the `guestbook-service` project
    doesn't have access to the `GuestbookPortletKeys` object that's in the
    `guestbook-web` project. 
    
    It is logical to think this could be corrected by including the project as a
    dependency in `guestbook-service`'s `build.gradle` file, but that creates a
    circular dependency. `guestbook-web` already depends on `guestbook-service`,
    so you can't make `guestbook-service` depend circularly on `guestbook-web`. 

    So now what do you do? 

    Make sure you've opened both `guestbook-api` and `guestbook-web` projects.
    Drag the `com.liferay.docs.guestbook.portlet.constants` package from the
    `guestbook-web` project and drop it on the `guestbook-api` project's
    `src/main/java` folder. Blamo! You fixed the problem. `guestbook-service`
    depends on `guestbook-api` and implements its interfaces. `guestbook-web`
    depends on both. Now you have only linear dependencies. 

Next you can create the `AssetRendererFactory` class. 

## Creating the GuestbookAssetRendererFactory Class [](id=creating-the-guestbookassetrendererfactory-class)

Follow these steps to create the `GuestbookAssetRendererFactory`:

1.  In the `com.liferay.docs.guestbook.asset` package, create a class called 
    `GuestbookAssetRendererFactory` that extends @product@'s 
    `BaseAssetRendererFactory` class. Replace its code with this starter code: 

        package com.liferay.docs.guestbook.asset;

        import com.liferay.asset.kernel.model.AssetRenderer;
        import com.liferay.asset.kernel.model.AssetRendererFactory;
        import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.docs.guestbook.service.EntryLocalService;
        import com.liferay.docs.guestbook.service.GuestbookLocalService;
        import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
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


        @Component(immediate = true, 
          property = {"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK}, 
          service = AssetRendererFactory.class
          )
        public class GuestbookAssetRendererFactory extends 
          BaseAssetRendererFactory<Guestbook> {

          public GuestbookAssetRendererFactory() {
            setClassName(CLASS_NAME);
            setLinkable(_LINKABLE);
            setPortletId(GuestbookPortletKeys.GUESTBOOK);
            setSearchable(true);
            setSelectable(true);
          }

    This code contains the class declaration and the constructor. It sets the
    class name it creates an `AssetRenderer` for, a portlet ID, and a boolean
    (`_LINKABLE`) set to `true`. The boolean denotes the methods that provide 
    URLs in the generated `AssetRenderer` are implemented. 

2.  Implement the `getAssetRenderer` method, which constructs new 
    `GuestbookAssetRenderer` instances for specific guestbooks. It uses the 
    `classPK` (primary key) parameter to retrieve the guestbook from the 
    database. It then calls the `GuestbookAssetRenderer`'s constructor, passing 
    the retrieved guestbook as an argument: 

        @Override
        public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type) 
        throws PortalException {
          
          Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);

          GuestbookAssetRenderer guestbookAssetRenderer = 
          new GuestbookAssetRenderer(guestbook);

          guestbookAssetRenderer.setAssetRendererType(type);
          guestbookAssetRenderer.setServletContext(_servletContext);

          return guestbookAssetRenderer;
        }

2.  You're extending `BaseAssetRendererFactory`, an abstract class that
    implements the `AssetRendererFactory` interface. To ensure that your custom 
    asset is associated with the correct entity, each asset renderer factory 
    must implement the `getClassName` and `getType` methods (among others): 

          @Override
          public String getClassName() {
            return CLASS_NAME;
          }

          @Override
          public String getType() {
            return TYPE;
          }

3.  Implement the `hasPermission` method via the `GuestbookPermission` class:

        @Override
        public boolean hasPermission(PermissionChecker permissionChecker, 
        long classPK, String actionId) throws Exception {

          Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
          return GuestbookPermission.contains(permissionChecker, guestbook, 
          actionId);
        }

4.  Add the remaining code to create the portlet URL for the asset and specify 
    whether it's linkable:

              @Override
              public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
                  LiferayPortletResponse liferayPortletResponse, long classTypeId) {
                PortletURL portletURL = null;

                try {
                  ThemeDisplay themeDisplay = (ThemeDisplay) 
                  liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

                  portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
                      GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
                  portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
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
                  return "bookmarks";
              }
     
            @Reference(target = "(osgi.web.symbolicname=com.liferay.docs.guestbook.portlet)",
                    unbind = "-")
            public void setServletContext(ServletContext servletContext) {
                    _servletContext = servletContext;
                }
                private ServletContext _servletContext;

            @Reference(unbind = "-")
                protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
                    _guestbookLocalService = guestbookLocalService; 
            }

            private GuestbookLocalService _guestbookLocalService;
            private static final boolean _LINKABLE = true;
            public static final String CLASS_NAME = Guestbook.class.getName();
            public static final String TYPE = "guestbook";
        }

5.  Organize imports (Ctrl-Shift-O) and save the file. 

Great! The guestbook asset renderer is complete. Next, you'll create the entry 
asset renderer.
