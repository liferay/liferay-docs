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

    <!-- Where is edit_entry_discussion coming from? -Cody -->

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

10.  If your asset is only intended for certain users to access, you may want to
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

    

### Configuring JSP Templates for an Asset Renderer








## Creating a Factory for Asset Renderers
