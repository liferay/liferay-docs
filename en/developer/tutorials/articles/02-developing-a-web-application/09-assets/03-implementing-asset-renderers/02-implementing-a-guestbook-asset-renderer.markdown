---
header-id: implementing-a-guestbook-asset-renderer
---

# Implementing a Guestbook Asset Renderer

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Implementing Asset Renderers</p><p>Step 1 of 2</p>
</div>

@product@'s asset renderers follow the factory pattern, so you must create a 
`GuestbookAssetRendererFactory` that instantiates the `GuestbookAssetRenderer`'s
private guestbook object. Here, you'll create both classes. 

You'll create the Asset Renderer class first. 

## Creating the AssetRenderer Class

Follow these steps to create the `GuestbookAssetRenderer` class: 

1.  Create a new package called `com.liferay.docs.guestbook.web.internal.asset`
    in the `guestbook-web` module's `src/main/java` folder. In this package,
    create a `GuestbookAssetRenderer` class that extends @product@'s
    `BaseJSPAssetRenderer` class. Extending this class gives you a head-start on
    implementing the `AssetRenderer` interface: 

    ```java
    public class GuestbookAssetRenderer extends BaseJSPAssetRenderer<Guestbook> {

    }
    ```
 
2.  Add the constructor, the guestbook class variable, and the permissions 
    model resource. Most of the methods in this class are getters that return 
    fields from the private `_guestbook` object. Methods requiring a 
    permission check use `_guestbookModelResourcePermission`: 

    ```java
    public GuestbookAssetRenderer(Guestbook guestbook, ModelResourcePermission<Guestbook> modelResourcePermission) {

                _guestbook = guestbook;
                _guestbookModelResourcePermission = modelResourcePermission;
    }

    // Add the other methods here
    
    private Guestbook _guestbook;
    private final ModelResourcePermission<Guestbook> _guestbookModelResourcePermission;   
    private Logger logger = Logger.getLogger(this.getClass().getName());
    ```
 
3.  The `BaseJSPAssetRenderer` abstract class that you're extending contains 
    dummy implementations of the `hasEditPermission` and `hasViewPermission`
    methods that you must override with actual permission checks using the
    permissions resources that you created earlier. Add these methods below the
    comment labeled `Add the other methods here`:

    ```java
    @Override
    public boolean hasEditPermission(PermissionChecker permissionChecker) 
    {
        try {
            return _guestbookModelResourcePermission.contains(
                permissionChecker, _guestbook, ActionKeys.UPDATE);
        }
        catch (Exception e) {
        }

        return false;
    }

    @Override
    public boolean hasViewPermission(PermissionChecker permissionChecker) 
    {
        try {
            return _guestbookModelResourcePermission.contains(
                permissionChecker, _guestbook, ActionKeys.VIEW);
        }
        catch (Exception e) {
        }

        return true;
    }
    ```

4.  Add the following getter methods to retrieve information about the 
    guestbook asset: 

    ```java
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
    ```

    The final method makes several utilities and the `Guestbook`
    entity available in the `HttpServletRequest` object. 

5.  Override the `getJspPath` method. It returns a string representing the
    path to the JSP that renders the guestbook asset. When the Asset Publisher
    displays an asset's full content, it invokes the asset renderer class's
    `getJspPath` method and passes a `template` string parameter that equals
    `"full_content"`. This returns `/asset/guestbook/full_content.jsp` when the
    `full_content` template string is passed as a parameter. You'll create this
    JSP later when updating your application's user interface: 

    ```java
    @Override
    public String getJspPath(HttpServletRequest request, String template) {

        if (template.equals(TEMPLATE_FULL_CONTENT)) {
          request.setAttribute("gb_guestbook", _guestbook);

          return "/asset/guestbook/" + template + ".jsp";
        } else {
          return null;
        }
    }
    ```

6.  Override the `getURLEdit` method. This method returns a URL for editing the
    asset: 

    @Override
    public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
      LiferayPortletResponse liferayPortletResponse) throws Exception {

        PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
            getControlPanelPlid(liferayPortletRequest), GuestbookPortletKeys.GUESTBOOK,
            PortletRequest.RENDER_PHASE);
        portletURL.setParameter("mvcPath", "/guestbook/edit_guestbook.jsp");
        portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));
        portletURL.setParameter("showback", Boolean.FALSE.toString());

        return portletURL;
    }

7.  Override the `getURLViewInContext` method. This method returns a URL to 
    view the asset in its native application: 

    ```java
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

          portletURL.setParameter("mvcPath", "/guestbook/view.jsp");
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
    ```

8.  Override the `getURLView` method. This method returns a URL to view the 
    asset from within the Asset Publisher: 

    ```java
    @Override
    public String getURLView(LiferayPortletResponse liferayPortletResponse, 
    WindowState windowState) throws Exception {

        return super.getURLView(liferayPortletResponse, windowState);
    }
    ```

10.  Organize imports (Ctrl-Shift-O) and save the file. Choose these imports: 

    - `java.util.logging.Logger` 
    - `com.liferay.portal.kernel.exception.SystemException` 
    - `java.util.logging.Level` 
    - `com.liferay.petra.string.StringUtil`
 
Next you can create the `AssetRendererFactory` class. 

## Creating the GuestbookAssetRendererFactory Class

Follow these steps to create the `GuestbookAssetRendererFactory`:

1.  In the `com.liferay.docs.guestbook.web.internal.asset` package, create
    a class called `GuestbookAssetRendererFactory` that extends @product@'s
    `BaseAssetRendererFactory` class, and overwrite the generated constructor
    and class variables with this: 

    ```java
    @Component(immediate = true, 
      property = {"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK}, 
      service = AssetRendererFactory.class
      )
    public class GuestbookAssetRendererFactory extends 
      BaseAssetRendererFactory<Guestbook> {

      public GuestbookAssetRendererFactory() {
        setClassName(CLASS_NAME);
        setLinkable(_LINKABLE);
        setPortletId(GuestbookPortletKeys.GUESTBOOK); setSearchable(true);
        setSelectable(true); 
      }         
     
      // Add the other methods here

      private ServletContext _servletContext;
      private GuestbookLocalService _guestbookLocalService;
      private static final boolean _LINKABLE = true;
      public static final String CLASS_NAME = Guestbook.class.getName();
      public static final String TYPE = "guestbook";
      private Logger logger = Logger.getLogger(this.getClass().getName());
      @Reference(target = "(model.class.name=com.liferay.docs.guestbook.model.Guestbook)")
      private ModelResourcePermission<Guestbook> _guestbookModelResourcePermission;
    }
    ```

    This code contains the class declaration, the constructor, and the class
    variables. It sets the class name it creates an `AssetRenderer` for,
    a portlet ID, and a `true` boolean (`_LINKABLE`). The boolean denotes
    implemented methods that provide URLs in the generated `AssetRenderer`. 

    Insert the methods below where you see the comment `Add the other methods
    here`. 

2.  Implement the `getAssetRenderer` method, which constructs new 
    `GuestbookAssetRenderer` instances for particular guestbooks. It uses the 
    `classPK` (primary key) parameter to retrieve the guestbook from the 
    database. It then calls the `GuestbookAssetRenderer`'s constructor, passing 
    the retrieved guestbook and permissions resource model as arguments: 

    ```java
    @Override
    public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type) 
    throws PortalException {
      
      Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);

      GuestbookAssetRenderer guestbookAssetRenderer = 
      new GuestbookAssetRenderer(guestbook, _guestbookModelResourcePermission);

      guestbookAssetRenderer.setAssetRendererType(type);
      guestbookAssetRenderer.setServletContext(_servletContext);

      return guestbookAssetRenderer;
    }
    ```

2.  You're extending `BaseAssetRendererFactory`, an abstract class that
    implements the `AssetRendererFactory` interface. To ensure that your custom 
    asset is associated with the correct entity, each asset renderer factory 
    must implement the `getClassName` and `getType` methods (among others): 

    ```java
    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    ```

3.  Implement the `hasPermission` method:

    ```java
    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, 
    long classPK, String actionId) throws Exception {
      Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
      return _guestbookModelResourcePermission.contains(permissionChecker, guestbook.getGuestbookId(), 
      actionId);
    }
    ```

4.  Add the remaining code to create the portlet URL for the asset and specify 
    whether it's linkable:

    ```java
    @Override
    public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
      LiferayPortletResponse liferayPortletResponse, long classTypeId) {
        PortletURL portletURL = null;

        try {
          ThemeDisplay themeDisplay = (ThemeDisplay) 
          liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

          portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
              GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
          portletURL.setParameter("mvcPath", "/guestbook/edit_guestbook.jsp");
          portletURL.setParameter("showback", Boolean.FALSE.toString());

          } catch (PortalException e) {
          
                logger.log(Level.SEVERE, e.getMessage()); 
                
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

    @Reference(unbind = "-")
    protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
        _guestbookLocalService = guestbookLocalService; 
    }
    ```

5.  Organize imports (Ctrl-Shift-O) and save the file. Select these imports:

    - `java.util.logging.Logger`
    - `java.util.logging.Level`

Great! The guestbook asset renderer is complete. Next, you'll create the entry 
asset renderer.
