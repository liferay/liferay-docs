# Rendering an Asset

There are several options you have for rendering an asset in @product@. Before
setting up the rendering process for your asset, make sure it's properly added
to the asset framework by following the
[Adding, Updating, and Deleting Assets](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)
tutorial. Once you have your asset properly added to the framework, Liferay can
render the asset by default. The default rendering process @product@ provides,
however, only displays the asset's title and description text, which can be
published by the Asset Publisher application. Any further rendering of your
asset requires additional set up. For instance, consider the list below of
popular cases when you'd want to provide additional rendering capabilities:

- Providing an edit feature to let users modify an asset.
- Allowing users to view an asset in its original context (e.g., viewing a blog
  in the Blogs application rather than restricting its viewing capabilities to
  the Asset Publisher).
- Including interactive files like images, videos, and audio.
- Restricting access to users who do not have permissions to interact with the
  asset.
- Allowing users to comment on the asset by providing a comments section.

Now that we've enticed you with a slew of rendering possibilities, you may be
curious as to how this is all possible.

Liferay lets you dictate your asset's rendering capabilities by providing the
*Asset Renderer* framework. Implementing an asset renderer for an existing asset
is easy because Liferay offers applicable interfaces and factories to help get
your custom asset rendering implemented fast. There are two things you'll need
to do to get your asset renderer functioning properly for your asset:

- Create an asset renderer for your custom entity.
- Create an asset renderer factory to track each entity instance's asset
  renderer.

You'll learn how to create an asset renderer and an asset renderer factory by
studying a Liferay asset that already uses both by default: Blogs. The Blogs
application offers many different ways to access and render a blogs asset.
You'll learn how a blogs asset provides an edit feature, comment
section, original context viewing (i.e., viewing an asset from the Blogs
application), workflow, etc. You'll also learn how it uses JSP templates to
display various blog views.

If you want to create an asset and give it the ability to do more than display
its title and description, read on to learn more!

## Creating an Asset Renderer

An asset renderer, in simple terms, lets you provide your own HTML for your
asset. Liferay provides the `AssetRenderer` interface that lets you get started
quickly. Implementing `AssetRenderer` requires that you choose a templating
technology (JSP, FreeMarker, Soy, etc.) to display an asset's HTML. For this
tutorial, you'll use JSP templates to render an asset's HTML. You'll learn how
to associate your JSP templates with an asset renderer, along with configuring
several other options next. 

To learn how an asset renderer is created, you'll create the pre-existing
[BlogsEntryAssetRenderer](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/blogs/com.liferay.blogs.web/com/liferay/blogs/web/asset/BlogsEntryAssetRenderer.html)
class, which configures the asset renderer framework for the Blogs application.
To create a `-AssetRenderer` class for your custom asset, first make sure you've
properly added your asset to the asset framework by following the
[Adding, Updating, and Deleting Assets](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)
tutorial.

1.  Create a new package in your existing project for your asset-related
    classes. For instance, the `BlogsEntryAssetRenderer` class resides in the
    `com.liferay.blogs.web` module's `com.liferay.blogs.web.asset` package.

2.  Create your `-AssetEntry` class for your application and have it implement
    the
    [AssetEntry](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/asset/kernel/model/AssetEntry.html)
    interface. Consider the `BlogsEntryAssetRenderer` class as an example:

        public class BlogsEntryAssetRenderer
            extends BaseJSPAssetRenderer<BlogsEntry> implements TrashRenderer {

    The `BlogsEntryAssetRenderer` class extends the
    [BaseJSPAssetRenderer](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/asset/kernel/model/BaseJSPAssetRenderer.html),
    which is an extension class intended for those who plan on using JSP
    templates to generate their asset's HTML. The `BaseJSPAssetRenderer` class
    implements the `AssetRenderer` interface. You'll notice the asset renderer
    is also implementing the
    [TrashRenderer](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/trash/TrashRenderer.html)
    interface. This is a common practice for many Liferay applications, which
    enables its assets to use Liferay's Recycle Bin.

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
    [Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-language-keys)
    tutorial.

    Also, make sure to define the `_entry` and `_resourceBundleLoader` fields in
    the `BlogsEntryAssetRenderer` class:

        private final BlogsEntry _entry;
        private final ResourceBundleLoader _resourceBundleLoader;

4.  Now that your class declaration and constructor are defined for the blogs
    asset renderer, you'll need to begin connecting your asset renderer with
    your asset. The following getter methods accomplish this:

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
    point out. The `getAssetObject()` method is setting the `BlogsEntry` that
    was set in the constructor as your asset to track. Likewise, the `getType()`
    method references the blogs asset renderer factory for the type of asset
    your asset renderer will work with. Of course, the asset renderer type will
    be `blog`, which you'll set in the factory later. This type is important
    because it's called in CSS files (among others) to style a blog asset's UI.

5.  Your asset renderer must linked to the portlet that owns the entity. In the
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
    blogs entries. A factory object is created for each entity instance, and
    this method links the asset renderer with that specific entity instance.

6.  If you're interested in enabling workflow for your asset, add the following
    method similar to what was done for the Blogs application:

        @Override
        public int getStatus() {
            return _entry.getStatus();
        }

    This method retrieves the workflow status for the asset.

7.  Another popular feature many developers want for their asset is the ability
    to comment on it. This is enabled for the Blogs application with the
    following method:

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
    Publisher's *Options* (![Options](../../../images/icon-app-options.png))
    &rarr; *Configuration* &rarr; *Setup* &rarr; *Display Settings* section.

8.  Retrieving the title and summary is both simple and important for any asset
    in Liferay. You can add this functionality in your asset renderer by adding
    methods similar to the ones outlined below, which are used for the blogs
    asset renderer:

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

    The `getSummary(...)` method returns the abstract description for a blog
    asset. If the abstract description does not exist, the content of the blog
    is used as an abstract. You'll learn more about abstracts and other content
    specifications later.

    The `getTitle(...)` method uses the resource bundle loader you configured in
    the constructor to load your module's resource bundle and return the display
    title for your asset.

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

    This displays a Print icon for your asset when displayed in the Asset
    Publisher. For the icon to display for your asset, you must enable it in the
    Asset Publisher's *Options* (![Options](../../../images/icon-app-options.png))
    &rarr; *Configuration* &rarr; *Setup* &rarr; *Display Settings* section.

    ![Figure 1: Enable printing in the Asset Publisher to display the Print icon for your asset.](../../../images/asset-publisher-printing.png)

11. If your asset is only intended for certain users to access, you'll want to
    set permissions for the asset. You can do this via the asset renderer, as
    well. See the logic below for an example used by the blogs asset renderer:

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
    blogs entry.

Awesome! You've learned how to set up the blogs asset renderer to

- connect with an asset
- connect with the asset's portlet
- use workflow management
- use a comments section
- retrieve the asset's title and summary
- generate the asset's unique URL
- display a print icon
- set up permissioning for the asset

You may recall that a major cog in asset renderer development is the ability to
generate HTML using a templating technology. The `BlogsEntryAssetRenderer` is
configured to use JSP templates to generate HTML for the Asset Publisher. You'll
learn more about how to do this next.

### Configuring JSP Templates for an Asset Renderer

An asset can be displayed in several different ways in the Asset Publisher, by
default. There are three templates to implement provided by the `AssetRenderer`
interface:

- `abstract`
- `full_content`
- `preview`

Besides these supported templates, you can also create JSPs for buttons you'd
like to provide for direct access and manipulation of the asset:

- Edit
- View
- View in Context

The `BlogsEntryAssetRenderer` customizes the `AssetRenderer`'s provided JSP
templates and adds a few other features using JSPs. You'll inspect how the blogs
asset renderer is put together to satisfy JSP template development requirements.

1.  Add the `getJspPath(...)` method to your asset renderer. This method should
    return the path to your JSP, which is rendered as HTML inside the Asset
    Publisher. Take a look at how the `BlogsEntryAssetRenderer` uses this
    method:

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

    Blogs assets implement the `abstract.jsp` and `full_content.jsp` templates.
    This means that a blogs asset can render a blog's abstract description or
    the blog's full content in the Asset Publisher. Those templates are located
    in the `com.liferay.blogs.web` module's
    `src/main/resources/META-INF/resources/blogs/asset` folder. You could create
    a similar folder for your JSP templates used for this method.

    You must configure a link to display the full content of the asset. You'll
    do this later.

2.  Now that you've added the path for your JSP, you'll need a way to include
    that JSP. If you're familiar with MVCPortlet development, you may be aware
    of this common practice. To display a JSP other than the default
    `view.jsp` template, you must specify an `include(...)` method to render
    the JSP. In the case for blogs, you want to render either the `abstract` or
    `full_content` template, so the `include` method is specified to do so:

        @Override
        public boolean include(
                HttpServletRequest request, HttpServletResponse response,
                String template)
            throws Exception {

            request.setAttribute(WebKeys.BLOGS_ENTRY, _entry);

            return super.include(request, response, template);
	    }

    This method sets the blogs entry asset as an attribute in the request and
    then calls the `BaseJspAssetRenderer` class's `include` method. By doing
    this, the appropriate JSP to display in the Asset Publisher is rendered.

    ![Figure 2: The abstract and full content views are rendererd differently for blogs.](../../../images/blogs-asset-views.png)

Terrific! You've learned how to apply JSPs supported by the Asset Publisher for
your asset. That's not all you can do with JSPs templates, however! The asset
renderer framework provides several other methods that let you render convenient
buttons for your asset.

1.  Blogs assets provide an Edit [Blog Name]
    (![Edit Blog](../../../images/icon-edit.png)) button that you can select to
    edit the asset. This is provided for blog assets by adding the following
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
    methods outlined below in the `BlogsEntryAssetRenderer class:

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

    The `getURLView(...)` method generates a URL which displays the full content
    of the asset in the Asset Publisher. This is assigned to the clickable asset
    name. The `getURLViewInContext(...)` method provides a similar URL assigned
    to the asset name, but the URL redirects to the original context of the
    asset (e.g., viewing a blogs asset in the Blogs application). Deciding which
    view to render in @product@ is configurable by navigating to the Asset
    Publisher's *Options*
    (![Options](../../../images/icon-app-options.png)) &rarr; *Configuration*
    &rarr; *Setup* &rarr; *Display Settings* section and choosing between *Show
    Full Content* and *View in Context* for the Asset Link Behavior drop-down
    menu.

The Blogs application provides customized `abstract` and `full_content` JSP
templates that override the ones provided by the `AssetRenderer` interface. The
third template, `preview`, could also be customized. You can view the default
`preview.jsp` template rendered in the *Add*
(![Add](../../../images/icon-control-menu-add.png)) &rarr; *Content*
menu.

![Figure 3: The `preview` template displays a preview of the asset in the Content section of the Add menu.](../../../images/preview-template-asset-renderer.png)

You've learned all about implementing the `AssetRenderer`'s provided templates
and customizing them to fit your needs. Next, you'll put your asset renderer
into action by creating a factory.

## Creating a Factory for Asset Renderers


