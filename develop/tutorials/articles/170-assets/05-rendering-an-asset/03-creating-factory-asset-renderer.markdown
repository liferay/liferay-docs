# Creating a Factory for the Asset Renderer [](id=creating-a-factory-for-the-asset-renderer)

You've successfully created an asset renderer, but you must create a factory
class to generate asset renderers for each asset instance. For example, the
blogs asset renderer factory instantiates `BlogsEntryAssetRenderer` for each
blogs asset displayed in an Asset Publisher. 

You'll continue the blogs asset renderer example by creating the blogs asset
renderer factory.

1.  Create an `-AssetRenderFactory` class in the same folder as its asset
    renderer class. For blogs, the
    [`BlogsEntryAssetRendererFactory`](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/web/asset/BlogsEntryAssetRendererFactory.html)
    class resides in the `com.liferay.blogs.web` module's
    `com.liferay.blogs.web.asset` package. The factory class should extend the
    `BaseAssetRendererFactory` class and the asset type should be specified as
    its parameter. You can see how this was done in the
    `BlogsEntryAssetRendererFactory` class below

        public class BlogsEntryAssetRendererFactory
            extends BaseAssetRendererFactory<BlogsEntry> {

2.  Create an `@Component` annotation section above the class declaration. This
    annotation is responsible for registering the factory instance for the
    asset.

        @Component(
            immediate = true,
            property = {"javax.portlet.name=" + BlogsPortletKeys.BLOGS},
            service = AssetRendererFactory.class
        )
        public class BlogsEntryAssetRendererFactory
            extends BaseAssetRendererFactory<BlogsEntry> {

    There are a few annotation elements you should set:

    - The `immediate` element directs the factory to start in @product@ when
      its module starts.
    - The `property` element sets the portlet that is associated with the asset.
      The Blogs portlet is specified, since this is the Blogs asset renderer
      factory. 
    - The `service` element should point to the `AssetRendererFactory.class`
      interface.

    +$$$

    **Note:** In previous versions of @product@, you had to register the asset
    renderer factory in a portlet's `liferay-portlet.xml` file. The registration
    process is now completed automatically by OSGi using the `@Component`
    annotation.

    $$$

3.  Create a constructor for the factory class that presets private attributes
    of the factory.

        public BlogsEntryAssetRendererFactory() {
            setClassName(BlogsEntry.class.getName());
            setCategorizable(true);
            setLinkable(true);
            setPortletId(BlogsPortletKeys.BLOGS);
            setSearchable(true);
            setSelectable(true);
        }

    - *linkable*: other assets can select blogs assets as their related
      assets.
    - *categorizable*: blogs can be used to delimit the scope of a
      vocabulary from the Categories Administration.
    - *searchable*: blogs can be found when searching for assets.
    - *selectable*: blogs can be selected when choosing assets to display in
      the Asset Publisher.

    Setting the class name and portlet ID links the asset renderer factory to
    the entity.

4.  Create the asset renderer for your asset. This is done by calling its
    constructor.

        @Override
        public AssetRenderer<BlogsEntry> getAssetRenderer(long classPK, int type)
            throws PortalException {

            BlogsEntry entry = _blogsEntryLocalService.getEntry(classPK);

            BlogsEntryAssetRenderer blogsEntryAssetRenderer =
                new BlogsEntryAssetRenderer(entry, _resourceBundleLoader);

            blogsEntryAssetRenderer.setAssetRendererType(type);
            blogsEntryAssetRenderer.setServletContext(_servletContext);

            return blogsEntryAssetRenderer;
        }

    For blogs, the asset is retrieved by calling the Blogs application's local
    service. Then the asset renderer is instantiated using the blogs asset and
    resource bundle loader. Next, the type and servlet context is
    set for the asset renderer. Finally, the configured asset renderer is
    returned.

    There are a few variables in the `getAssetRenderer(...)` method you must
    create. You'll set those variables and learn what they're doing next.

    a. You must get the entry by calling the Blogs application's local service.
       You can instantiate this service by creating a private field and setting
       it using a setter method:

        @Reference(unbind = "-")
        protected void setBlogsEntryLocalService(
            BlogsEntryLocalService blogsEntryLocalService) {

            _blogsEntryLocalService = blogsEntryLocalService;
        }

        private BlogsEntryLocalService _blogsEntryLocalService;

    The setter method is annotated with the `@Reference` tag. Visit the
    [Invoking Liferay Services Locally](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services#invoking-liferay-services-locally)
    section of the *Finding and Invoking Liferay Services* tutorial for more
    information.

    b. You must specify the resource bundle loader since it was specified in
       the `BlogsEntryAssetRenderer`'s constructor:

        @Reference(
            target = "(bundle.symbolic.name=com.liferay.blogs.web)", unbind = "-"
        )
        public void setResourceBundleLoader(
            ResourceBundleLoader resourceBundleLoader) {

            _resourceBundleLoader = resourceBundleLoader;
        }

        private ResourceBundleLoader _resourceBundleLoader;

    Make sure the `osgi.web.symbolicname` in the `target` property of the
    `@Reference` annotation is set to the same value as the
    `Bundle-SymbolicName` defined in the `bnd.bnd` file of the module the
    factory resides in.

    c. The asset renderer `type` integer is set for the asset renderer, but why
       an integer? @product@ needs to differentiate when it should display the
       latest *approved* version of the asset, or the latest version, even if
       it's unapproved (e.g., unapproved versions would be displayed for
       reviewers of the asset in a workflow). For these situations, the asset
       renderer factory should receive either
       
       - `0` for the latest version of the asset
       - `1` for the latest approved version of the asset

    d. Since the Blogs application provides its own JSPs, it must pass a
    reference of the servlet context to the asset renderer. This is always
    required when using custom JSPs in an asset renderer:

        @Reference(
            target = "(osgi.web.symbolicname=com.liferay.blogs.web)", unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            _servletContext = servletContext;
        }
 
        private ServletContext _servletContext;

5.  Set the type of asset that the asset factory associates with and provide
    a getter method to retrieve that type. Also, provide another getter to
    retrieve the blogs entry class name, which is required:

        public static final String TYPE = "blog";

        @Override
        public String getType() {
            return TYPE;
        }

        @Override
        public String getClassName() {
            return BlogsEntry.class.getName();
        }

6.  Set the Lexicon icon for the asset:

        @Override
        public String getIconCssClass() {
            return "blogs";
        }

    You can find a list of all available Lexicon icons at
    [https://liferay.github.io/clay/content/icons-lexicon/](https://liferay.github.io/clay/content/icons-lexicon/).

7.  Add methods that generate URLs to add and view the asset.

        @Override
        public PortletURL getURLAdd(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse, long classTypeId) {

            PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
                liferayPortletRequest, getGroup(liferayPortletRequest),
                BlogsPortletKeys.BLOGS, 0, 0, PortletRequest.RENDER_PHASE);

            portletURL.setParameter("mvcRenderCommandName", "/blogs/edit_entry");

            return portletURL;
        }

        @Override
        public PortletURL getURLView(
            LiferayPortletResponse liferayPortletResponse,
            WindowState windowState) {

            LiferayPortletURL liferayPortletURL =
                liferayPortletResponse.createLiferayPortletURL(
                    BlogsPortletKeys.BLOGS, PortletRequest.RENDER_PHASE);

            try {
                liferayPortletURL.setWindowState(windowState);
            }
            catch (WindowStateException wse) {
            }

            return liferayPortletURL;
        }

    If you're paying close attention, you may have noticed the `getURLView(...)`
    method was also implemented in the `BlogsEntryAssetRenderer` class. The
    asset renderer's `getURLView(...)` method creates a URL for the specific
    asset instance, whereas the factory uses the method to create a generic URL
    that only points to the application managing the assets (e.g., Blogs
    application).

11. Set the global permissions for all blogs assets:

        @Override
        public boolean hasAddPermission(
                PermissionChecker permissionChecker, long groupId, long classTypeId)
            throws Exception {

            return BlogsPermission.contains(
                permissionChecker, groupId, ActionKeys.ADD_ENTRY);
        }

        @Override
        public boolean hasPermission(
                PermissionChecker permissionChecker, long classPK, String actionId)
            throws Exception {

            return BlogsEntryPermission.contains(
                permissionChecker, classPK, actionId);
        }

Great! You've finished creating the Blogs application's asset renderer factory!
Now you have the knowledge to implement an asset renderer and produce an asset
renderer for each asset instance using a factory!
