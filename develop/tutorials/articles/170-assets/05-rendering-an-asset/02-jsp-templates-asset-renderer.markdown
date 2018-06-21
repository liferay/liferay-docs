# Configuring JSP Templates for an Asset Renderer [](id=configuring-jsp-templates-for-an-asset-renderer)

An asset can be displayed in several different ways in the Asset Publisher.
There are three templates to implement provided by the `AssetRenderer`
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

    ![Figure 1: The abstract and full content views are rendererd differently for blogs.](../../../images/blogs-asset-views.png)

Terrific! You've learned how to apply JSPs supported by the Asset Publisher for
your asset. That's not all you can do with JSP templates, however! The asset
renderer framework provides several other methods that let you render convenient
buttons for your asset.

1.  Blogs assets provide an Edit button that lets you edit the asset.
    Provide this by adding the following method to the `BlogsEntryAssetRenderer`
    class:

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
    view to render is configurable by navigating to the Asset Publisher's 
    *Options* &rarr; *Configuration*
    &rarr; *Setup* &rarr; *Display Settings* section and choosing between *Show
    Full Content* and *View in Context* for the Asset Link Behavior drop-down
    menu.

The Blogs application provides `abstract` and `full_content` JSP templates that
override the ones provided by the `AssetRenderer` interface. The third template,
`preview`, could also be customized. You can view the default `preview.jsp`
template rendered in the *Add* &rarr; *Content* menu.

![Figure 2: The `preview` template displays a preview of the asset in the Content section of the Add menu.](../../../images/preview-template-asset-renderer.png)

You've learned all about implementing the `AssetRenderer`'s provided templates
and customizing them to fit your needs. Next, you'll put your asset renderer
into action by creating a factory.
