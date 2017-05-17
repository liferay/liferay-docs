# Rendering an Asset [](id=rendering-an-asset)

There are several options you have for rendering an asset in @product@. Before
setting up the rendering process for your asset, make sure it's added to the
asset framework by following the
[Adding, Updating, and Deleting Assets](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)
tutorial. Once you have your asset added to the framework, @product@ can
render the asset by default using the Asset Publisher application. The default
rendering process @product@ provides, however, only displays the asset's title
and description text. Any further rendering of your asset requires additional
coding. For instance, you might want these additional things:

- An edit feature for modifying an asset.
- Viewing an asset in its original context (e.g., a blog
  in the Blogs application; a post in the Message Boards application).
- Embedding images, videos, and audio.
- Restricting access to users who do not have permissions to interact with the
  asset.
- Allowing users to comment on the asset. 

Liferay lets you dictate your asset's rendering capabilities by providing the
*Asset Renderer* framework. Implementing an asset renderer for an existing asset
is easy because Liferay offers interfaces and factories to help get your custom
asset rendering implemented fast. There are two things you must do to get your
asset renderer functioning properly for your asset:

- Create an asset renderer for your custom asset.
- Create an asset renderer factory to create an instance of the asset renderer
  for each asset entity.

![Figure 1: The asset renderer factory creates an asset renderer for each asset instance.](../../images/asset-renderer-diagram.png)

You'll learn how to create an asset renderer and an asset renderer factory by
studying a Liferay asset that already uses both by default: Blogs. The Blogs
application offers many different ways to access and render a blogs asset.
You'll learn how a blogs asset provides an edit feature, comment section,
original context viewing (i.e., viewing an asset from the Blogs application),
workflow, etc. You'll also learn how it uses JSP templates to display various
blog views. The Blogs application is an extensive example of how an asset
renderer can be customized to fit your needs. 

If you want to create an asset and make it do more than display its title and
description, read on to learn more!

## Creating an Asset Renderer [](id=creating-an-asset-renderer)

An asset renderer lets you provide your own HTML for your asset. The
`AssetRenderer` interface requires that you choose a templating technology (JSP,
FreeMarker, Soy, etc.) to display an asset's HTML. For this tutorial, you'll use
JSP templates to render an asset's HTML. You'll learn how to associate your JSP
templates with an asset renderer, along with configuring several other options. 

To learn how an asset renderer is created, you'll create the pre-existing
[BlogsEntryAssetRenderer](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/web/asset/BlogsEntryAssetRenderer.html)
class, which configures the asset renderer framework for the Blogs application.

1.  Create a new package in your existing project for your asset-related
    classes. For instance, the `BlogsEntryAssetRenderer` class resides in the
    `com.liferay.blogs.web` module's `com.liferay.blogs.web.asset` package.

2.  Create your `-AssetEntry` class for your application in the new `-.asset`
    package and have it implement the
    [AssetEntry](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/asset/kernel/model/AssetEntry.html)
    interface. Consider the `BlogsEntryAssetRenderer` class as an example:

        public class BlogsEntryAssetRenderer
            extends BaseJSPAssetRenderer<BlogsEntry> implements TrashRenderer {

    The `BlogsEntryAssetRenderer` class extends the
    [BaseJSPAssetRenderer](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/asset/kernel/model/BaseJSPAssetRenderer.html),
    which is an extension class intended for those who plan on using JSP
    templates to generate their asset's HTML. The `BaseJSPAssetRenderer` class
    implements the `AssetRenderer` interface. You'll notice the asset renderer
    is also implementing the
    [TrashRenderer](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/trash/TrashRenderer.html)
    interface. This is a common practice for many @product@ applications, which
    enables its assets to use @product@'s Recycle Bin.

3.  Define the asset renderer class's constructor, which typically sets the
    asset object to use in the asset renderer class.

        public BlogsEntryAssetRenderer(
            BlogsEntry entry, ResourceBundleLoader resourceBundleLoader) {

            _entry = entry;
            _resourceBundleLoader = resourceBundleLoader;
        }

    The `BlogsEntryAssetRenderer` also sets the resource bundle loader, which
    loads the language keys for a module. You can learn more about the resource
    bundle loader in the
    [Overriding Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys)
    tutorial.

    Also, make sure to define the `_entry` and `_resourceBundleLoader` fields in
    the class:

        private final BlogsEntry _entry;
        private final ResourceBundleLoader _resourceBundleLoader;

4.  Now that your class declaration and constructor are defined for the blogs
    asset renderer, you must begin connecting your asset renderer to your
    asset. The following getter methods accomplish this:

        @Override
        public BlogsEntry getAssetObject() {
            return _entry;
        }

        @Override
        public String getClassName() {
            return BlogsEntry.class.getName();
        }

        @Override
        public long getClassPK() {
            return _entry.getEntryId();
        }

        @Override
        public long getGroupId() {
            return _entry.getGroupId();
        }

        @Override
        public String getType() {
            return BlogsEntryAssetRendererFactory.TYPE;
        }

        @Override
        public String getUuid() {
            return _entry.getUuid();
        }

    These methods are pretty self-explanatory, but there are a couple things to
    point out. The `getAssetObject()` method sets the `BlogsEntry` that
    was set in the constructor as your asset to track. Likewise, the `getType()`
    method references the blogs asset renderer factory for the type of asset
    your asset renderer renders. Of course, the asset renderer type is
    `blog`, which you'll set in the factory later. 

5.  Your asset renderer must link to the portlet that owns the entity. In the
    case of a blogs asset, its portlet ID should be linked to the Blogs
    application.

        @Override
        public String getPortletId() {
            AssetRendererFactory<BlogsEntry> assetRendererFactory =
                getAssetRendererFactory();

            return assetRendererFactory.getPortletId();
        }

    The `getPortletId()` method instantiates an asset renderer factory for a
    `BlogsEntry` and retrieves the portlet ID for the portlet used to display
    blogs entries.

6.  If you're interested in enabling workflow for your asset, add the following
    method similar to what was done for the Blogs application:

        @Override
        public int getStatus() {
            return _entry.getStatus();
        }

    This method retrieves the workflow status for the asset.

7.  Another popular feature many developers want for their asset is to comment
    on it. This is enabled for the Blogs application with the following method:

        @Override
        public String getDiscussionPath() {
            if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
                return "edit_entry_discussion";
            }
            else {
                return null;
            }
        }

    A comments section is an available option if it returns a non-null value. A
    JSP template defining a comments section is not required. For the comments
    section to display for your asset, you must enable it in the Asset
    Publisher's *Options* (![Options](../../images/icon-app-options.png)) &rarr;
    *Configuration* &rarr; *Setup* &rarr; *Display Settings* section.

8.  At a minimum, you should create a title and summary for your asset. Here's
    how the `BlogsEntryAssetRenderer` does it:

        @Override
        public String getSummary(
            PortletRequest portletRequest, PortletResponse portletResponse) {

            int abstractLength = AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH;

            if (portletRequest != null) {
                abstractLength = GetterUtil.getInteger(
                    portletRequest.getAttribute(
                        WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH),
                    AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH);
            }

            String summary = _entry.getDescription();

            if (Validator.isNull(summary)) {
                summary = HtmlUtil.stripHtml(
                    StringUtil.shorten(_entry.getContent(), abstractLength));
            }

            return summary;
        }

        @Override
        public String getTitle(Locale locale) {
            ResourceBundle resourceBundle =
                _resourceBundleLoader.loadResourceBundle(
                    LanguageUtil.getLanguageId(locale));

            return BlogsEntryUtil.getDisplayTitle(resourceBundle, _entry);
        }

    These two methods return information about your asset in a generic way, so
    the asset publisher can display it. Anything appropriate for your asset can
    be the title or the summary. 

    The `getSummary(...)` method for Blogs returns the abstract description for
    a blog asset. If the abstract description does not exist, the content of the
    blog is used as an abstract. You'll learn more about abstracts and other
    content specifications later. 

    The `getTitle(...)` method for Blogs uses the resource bundle loader you
    configured in the constructor to load your module's resource bundle and
    return the display title for your asset.

9.  If you want to provide a unique URL for your asset, you can specify a URL
    title. A URL title is the URL used to access your asset directly (e.g.,
    localhost:8080/-/this-is-my-blog-asset). You can do this by providing the
    following method:

        @Override
        public String getUrlTitle() {
            return _entry.getUrlTitle();
        }

10. Insert the `isPrintable()` method, which enables the Asset Publisher's
    printing capability for your asset.

        @Override
        public boolean isPrintable() {
            return true;
        }

    This displays a Print icon when your asset is displayed in the Asset
    Publisher. For the icon to appear, you must enable it in the Asset
    Publisher's *Options* (![Options](../../images/icon-app-options.png)) &rarr;
    *Configuration* &rarr; *Setup* &rarr; *Display Settings* section.

    ![Figure 2: Enable printing in the Asset Publisher to display the Print icon for your asset.](../../images/asset-publisher-printing.png)

11. If your asset is protected by permissions, you should set permissions for
    the asset. You can do this via the asset renderer as well. See the logic
    below for an example used in the `BlogsEntryAssetRenderer` class:

        @Override
        public long getUserId() {
            return _entry.getUserId();
        }

        @Override
        public String getUserName() {
            return _entry.getUserName();
        }

        public boolean hasDeletePermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.DELETE);
        }

        @Override
        public boolean hasEditPermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.UPDATE);
        }

        @Override
        public boolean hasViewPermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.VIEW);
        }

    Before you can check if a user has permission to view your asset, you must
    distinguish the user. The `getUserId()` and `getUserName()` retrieves the
    entry's user ID and username, respectively. Then there are three boolean
    permission methods, which check if the user can view, edit, or delete your
    blogs entry. These permissions are for specific entity instances. Global
    permissions for blog entries are implemented in the factory, which you'll do
    later.

Awesome! You've learned how to set up the blogs asset renderer to

- connect to an asset
- connect to the asset's portlet
- use workflow management
- use a comments section
- retrieve the asset's title and summary
- generate the asset's unique URL
- display a print icon
- set up permissioning for the asset

You may recall that a major cog in asset renderer development generating HTML
using a templating technology. The `BlogsEntryAssetRenderer` is configured to
use JSP templates to generate HTML for the Asset Publisher. You'll learn more
about how to do this next.

### Configuring JSP Templates for an Asset Renderer [](id=configuring-jsp-templates-for-an-asset-renderer)

An asset can be displayed in several different ways in the Asset Publisher, by
default. There are three templates to implement provided by the `AssetRenderer`
interface:

- `abstract`
- `full_content`
- `preview`

Besides these supported templates, you can also create JSPs for buttons you'd
like to provide for direct access and manipulation of the asset. For example,

- Edit
- View
- View in Context

The `BlogsEntryAssetRenderer` customizes the `AssetRenderer`'s provided JSP
templates and adds a few other features using JSPs. You'll inspect how the blogs
asset renderer is put together to satisfy JSP template development requirements.

1.  Add the `getJspPath(...)` method to your asset renderer. This method should
    return the path to your JSP, which is rendered inside the Asset Publisher.
    This is how the `BlogsEntryAssetRenderer` uses this method:

        @Override
        public String getJspPath(HttpServletRequest request, String template) {
            if (template.equals(TEMPLATE_ABSTRACT) ||
                template.equals(TEMPLATE_FULL_CONTENT)) {

                return "/blogs/asset/" + template + ".jsp";
            }
            else {
                return null;
            }
        }

    Blogs assets provide `abstract.jsp` and `full_content.jsp` templates. This
    means that a blogs asset can render a blog's abstract description or the
    blog's full content in the Asset Publisher. Those templates are located in
    the `com.liferay.blogs.web` module's
    `src/main/resources/META-INF/resources/blogs/asset` folder. You could create
    a similar folder for your JSP templates used for this method. The other
    template provided by the `AssetRenderer` interface, `preview.jsp`, is not
    customized by the blogs asset renderer, so its default template is
    implemented.

    You must create a link to display the full content of the asset. You'll
    do this later.

2.  Now that you've added the path to your JSP, you must include that JSP. Since
    the `BlogsEntryAssetRenderer` class extends the `BaseJSPAssetRenderer`,
    it already has an `include(...)` method to render a specific JSP. You
    must override this method to set an attribute in the request to use in
    the blog's views:

        @Override
        public boolean include(
                HttpServletRequest request, HttpServletResponse response,
                String template)
            throws Exception {

            request.setAttribute(WebKeys.BLOGS_ENTRY, _entry);

            return super.include(request, response, template);
        }

    The attribute includes the blogs entry object. Adding the blog object this
    way is not mandatory; you could obtain the blog entry directly from the
    view. Using the `include(...)` method, however, follows the best practice
    for MVC portlets.

    ![Figure 3: The abstract and full content views are rendererd differently for blogs.](../../images/blogs-asset-views.png)

Terrific! You've learned how to apply JSPs supported by the Asset Publisher for
your asset. That's not all you can do with JSP templates, however! The asset
renderer framework provides several other methods that let you render convenient
buttons for your asset.

1.  Blogs assets provide an Edit button (![Edit Blog](../../images/icon-edit.png))
    that lets you edit the asset. This is provided by adding the following
    method to the `BlogsEntryAssetRenderer` class:

        @Override
        public PortletURL getURLEdit(
                LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse)
            throws Exception {

            Group group = GroupLocalServiceUtil.fetchGroup(_entry.getGroupId());

            PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
                liferayPortletRequest, group, BlogsPortletKeys.BLOGS, 0, 0,
                PortletRequest.RENDER_PHASE);

            portletURL.setParameter("mvcRenderCommandName", "/blogs/edit_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));

            return portletURL;
        }

    The Asset Publisher loads the blogs asset using the Blogs application. Then
    the `edit_entry.jsp` template generates the HTML for an editing UI. Once the
    necessary edits are made to the asset, it can be saved from the Asset
    Publisher. Pretty cool, right?

2.  You can specify how to view your asset by providing methods similar to the
    methods outlined below in the `BlogsEntryAssetRenderer` class:

        @Override
        public String getURLView(
                LiferayPortletResponse liferayPortletResponse,
                WindowState windowState)
            throws Exception {

            AssetRendererFactory<BlogsEntry> assetRendererFactory =
                getAssetRendererFactory();

            PortletURL portletURL = assetRendererFactory.getURLView(
                liferayPortletResponse, windowState);

            portletURL.setParameter("mvcRenderCommandName", "/blogs/view_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));
            portletURL.setWindowState(windowState);

            return portletURL.toString();
        }

        @Override
        public String getURLViewInContext(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse,
            String noSuchEntryRedirect) {

            return getURLViewInContext(
                liferayPortletRequest, noSuchEntryRedirect, "/blogs/find_entry",
                "entryId", _entry.getEntryId());
        }

    The `getURLView(...)` method generates a URL that displays the full content
    of the asset in the Asset Publisher. This is assigned to the clickable asset
    name. The `getURLViewInContext(...)` method provides a similar URL assigned
    to the asset name, but the URL redirects to the original context of the
    asset (e.g., viewing a blogs asset in the Blogs application). Deciding which
    view to render in @product@ is configurable by navigating to the Asset
    Publisher's *Options*
    (![Options](../../images/icon-app-options.png)) &rarr; *Configuration*
    &rarr; *Setup* &rarr; *Display Settings* section and choosing between *Show
    Full Content* and *View in Context* for the Asset Link Behavior drop-down
    menu.

The Blogs application provides `abstract` and `full_content` JSP templates that
override the ones provided by the `AssetRenderer` interface. The third template,
`preview`, could also be customized. You can view the default `preview.jsp`
template rendered in the *Add* (![Add](../../images/icon-control-menu-add.png))
&rarr; *Content* menu.

![Figure 4: The `preview` template displays a preview of the asset in the Content section of the Add menu.](../../images/preview-template-asset-renderer.png)

You've learned all about implementing the `AssetRenderer`'s provided templates
and customizing them to fit your needs. Next, you'll put your asset renderer
into action by creating a factory.

## Creating a Factory for the Asset Renderer [](id=creating-a-factory-for-the-asset-renderer)

You've successfully created an asset renderer, but you must create a factory
class to generate asset renderers for each asset instance. For example, the
blogs asset renderer factory instantiates `BlogsEntryAssetRenderer` for each
blogs asset displayed in an Asset Publisher. 

You'll continue the blogs asset renderer example by creating the blogs asset
renderer factory.

1.  Create an `-AssetRenderFactory` class in the same folder as its asset
    renderer class. For blogs, the
    [BlogsEntryAssetRendererFactory](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/web/asset/BlogsEntryAssetRendererFactory.html)
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
    [http://liferay.github.io/lexicon/content/icons-lexicon/](http://liferay.github.io/lexicon/content/icons-lexicon/).

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
