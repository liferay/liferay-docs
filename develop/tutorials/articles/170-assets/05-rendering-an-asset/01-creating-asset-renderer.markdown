# Creating an Asset Renderer [](id=creating-an-asset-renderer)

In this tutorial, you'll learn how to create an `Asset Renderer` and associate your JSP templates with it, along with configuring several other options.

To learn how an asset renderer is created, you'll create the pre-existing
[`BlogsEntryAssetRenderer`](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/web/asset/BlogsEntryAssetRenderer.html)
class, which configures the asset renderer framework for the Blogs application.

1.  Create a new package in your existing project for your asset-related
    classes. For instance, the `BlogsEntryAssetRenderer` class resides in the
    `com.liferay.blogs.web` module's `com.liferay.blogs.web.asset` package.

2.  Create your `-AssetEntry` class for your application in the new `-.asset`
    package and have it implement the
    [`AssetEntry`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/asset/kernel/model/AssetEntry.html)
    interface. Consider the `BlogsEntryAssetRenderer` class as an example:

        public class BlogsEntryAssetRenderer
            extends BaseJSPAssetRenderer<BlogsEntry> implements TrashRenderer {

    The `BlogsEntryAssetRenderer` class extends the
    [`BaseJSPAssetRenderer`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/asset/kernel/model/BaseJSPAssetRenderer.html),
    which is an extension class intended for those who plan on using JSP
    templates to generate their asset's HTML. The `BaseJSPAssetRenderer` class
    implements the `AssetRenderer` interface. You'll notice the asset renderer
    is also implementing the
    [`TrashRenderer`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/trash/TrashRenderer.html)
    interface. This is a common practice for many applications, so they can use
    @product@'s Recycle Bin.

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

    The `getAssetObject()` method sets the `BlogsEntry` that was set in the
    constructor as your asset to track. Likewise, the `getType()` method
    references the blogs asset renderer factory for the type of asset your
    asset renderer renders. Of course, the asset renderer type is `blog`, which
    you'll set in the factory later. 

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

    A comments section is an available option if it returns a non-null value. 
    For the comments section to display for your asset, you must enable it in 
    the Asset Publisher's *Options* 
    (![Options](../../../images/icon-app-options.png)) &rarr;
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

    These two methods return information about your asset, so the asset 
    publisher can display it. The title and summary can be anything. 

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
    Publisher's *Options* &rarr;
    *Configuration* &rarr; *Setup* &rarr; *Display Settings* section.

    ![Figure 1: Enable printing in the Asset Publisher to display the Print icon for your asset.](../../../images/asset-publisher-printing.png)

11. If your asset is protected by permissions, you can set permissions for
    the asset via the asset renderer. See the logic below for an example used 
    in the `BlogsEntryAssetRenderer` class:

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
    use the `getUserId()` and `getUserName()` to retrieve the entry's user ID 
    and username, respectively. Then there are three boolean permission 
    methods that check if the user can view, edit, or delete your blogs 
    entry. These permissions are for specific entity instances. Global
    permissions for blog entries are implemented in the factory, which you'll
    do later.

Awesome! You've learned how to set up the blogs asset renderer to

- connect to an asset
- connect to the asset's portlet
- use workflow management
- use a comments section
- retrieve the asset's title and summary
- generate the asset's unique URL
- display a print icon
- check permissions for the asset

Now you need to create the templates to render the HTML. The 
`BlogsEntryAssetRenderer` is configured to use JSP templates to generate HTML 
for the Asset Publisher. You'll learn more about how to do this next.
