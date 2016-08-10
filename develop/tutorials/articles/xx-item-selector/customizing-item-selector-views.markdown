# Creating Custom Selection Views for the Item Selector [](id=creating-custom-selection-views-for-the-item-selector)

Item Selector views are determined by the type of entity selected. For example,
when using the Item Selector to select images, the Item Selector renders the
selection views shown below:

![Figure 1: The entity type determines the Item Selector view.](../../images/item-selector-tabs.png)

Each tab: *Blog Images*, *Documents and Media*, *URL*, and *Upload Image*,
corresponds to a selection view for the Item Selector. Each selection view is
represented by an `*ItemSelectorCriterion`:

-  `BlogsItemSelectorCriterion` -> Blog Image View
-  `DLImageItemSelectorCriterion` -> Docs and media
-  `URLItemSelectorCriterion` -> URL
-  `UploadItemSelectorCriterion` -> Upload an Image

These default views cover several use cases, but you may want more for your
application. Perhaps you have an external provider that you wish to include as a
view for the Item Selector, or maybe you have created a new entity type that
requires its own view. You can extend the Item Selector's default views to
include the new view.

Each selection view must have a criterion and return type specified. You can
read more about the default criterion and return types in the
[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api)
tutorial. If your new view does not use one of the default criterion or return
types, you must create those first before creating your view. You can learn how
to create custom criterion and custom return types in the
[Creating Custom Entities for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-elements-and-return-types)
tutorial.

This tutorial covers how to create new selection views for the Item Selector.

Get started by implementing the selection view next.

## Implementing the View [](id=implementing-the-view)

Imagine that you want to create a new image selection view to allow users to
select images from an external provider.

In order to create a new view you must first know what kind of entities you
want to select in the new view: images, videos, users, etc. The kind of entities
you choose determines the specific `ItemSelectorCriterion` you need to use.

In this example, since images are being selected, you would need to use the
`ImageItemSelectorCriterion`.

Next, you need to know the type of information the the entity can return when
it's selected.

The example image provider only returns URLs, so you would need to use
`URLItemSelectorReturnType` for the return type.

Once you know the entity and return type, you can get started. The steps below
use the [SampleItemSelectorView](https://github.com/robertoDiaz/liferay-portal/blob/item-selector-sample/modules/apps/collaboration/item-selector/item-selector-sample-web/src/main/java/com/liferay/item/selector/sample/web/SampleItemSelectorView.java)
class as an example.

Follow these steps to implement your view:

1.  Selection views are OSGi components that implement the `ItemSelectorView`
    interface, so in order to create a new view you'll need to create a new
    class that implements this interface and register it in OSGi.

    For example, the image provider example might have this implementation:

        @Component(
                property = {"item.selector.view.order:Integer=200"},
                service = ItemSelectorView.class
        )
        public class SampleItemSelectorView
                implements ItemSelectorView<ImageItemSelectorCriterion> {
            ....
        }

    If you want your new selection view to be available only when selecting the
    entity for a specific web content, such as a blog entry, simply replace the
    `itemSelectorCriterion` in your `*ItemSelectorView` class with the
    `*ItemSelectorCriterion` class you wish to use, such as
    `BlogsItemSelectorCriterion`.

    Note that the OSGi component is registered with the property
    `item.selector.view.order`. The Item Selector view order (the order of the
    rendered tab views) is prioritized according to a couple settings.

    First, it considers the order of the criteria decided by the client of the
    Item Selector. When the application invokes the Item Selector it will
    specify the criteria ordered by importance. Second, if there are multiple
    views for the same criteria, it will use the property
    `item.selector.view.order` to determine which will appear first. The lower
    the value is, the more priority it has, and the sooner it will appear in the
    order.

2.  Next, you'll need to specify which `*ItemSelectorCriterion`, i.e what type
    of entity, is required to display the selection view, using the
    `getItemSelectorCriterionClass` method.

    In the image provider example, the `ImageItemSelectorCriterion.class` is
    returned, so the criterion is declared this way:

        public Class<ImageItemSelectorCriterion> getItemSelectorCriterionClass() 
        {
            return ImageItemSelectorCriterion.class;
        }

3.  Declare a method to retrieve the servlet context:

        public ServletContext getServletContext() {
            return _servletContext;
        }

    You'll use this later on to help render the markup for your view.

4.  Next, Indicate what information you are returning when the entity is
    selected, using the `getSupportedItemSelectorReturnTypes` method.

    In the example `URLItemSelectorReturnType` is used, but you could use more
    return types if you have the information:

        public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes(
        ) {
            return _supportedItemSelectorReturnTypes;
        }

    This returns the supported Item Selector return types for the view. The
    return types are added to the list further down in the code. Follow this
    same pattern to add your return types to the list:

        private static final List<ItemSelectorReturnType>
            _supportedItemSelectorReturnTypes =
            Collections.unmodifiableList(
                ListUtil.fromArray(
                    new ItemSelectorReturnType[] {
                        new URLItemSelectorReturnType()
                    }));

5.  Next, configure the title, search options, and visibility settings for the
    selection view:

    For example, the sample selector view has the following settings:

        public String getTitle(Locale locale) {
            return "Sample Selector";
        }

        public boolean isShowSearch() {
            return false;
        }

        public boolean isVisible(ThemeDisplay themeDisplay) {
            return true;
        }

    The `getTitle` method returns the localized title of the tab to display in
    the Item Selector dialog. The `isShowSearch()` method returns whether the
    Item Selector view should show the the search field. Finally, the
    `isVisible()` method returns whether the Item Selector view is visible. In
    most cases, you'll want to set this to `true`. There are special cases where
    you may set this to `false`, such as if the view isn't ready or requires an
    additional third-party configuration, etc.

6.  Next, set the render settings for your view, using the `renderHTML` method.

    The sample Item Selector view has the following configuration:

        public void renderHTML(
            ServletRequest request, ServletResponse response,
            ImageItemSelectorCriterion itemSelectorCriterion,
            PortletURL portletURL, String itemSelectedEventName,
            boolean search
        )
        throws IOException, ServletException {
            request.setAttribute(_ITEM_SELECTED_EVENT_NAME,
            itemSelectedEventName);

            ServletContext servletContext = getServletContext();

            RequestDispatcher requestDispatcher =
            servletContext.getRequestDispatcher("/sample.jsp");

            requestDispatcher.include(request, response);
        }

    The servlet and request objects are set. `itemSelectorCriterion` sets the
    Item Selector criterion that is used to render this selection view. Next,
    the `portletURL` is set, which is used to create URLs in the selection view.
    Next the `itemSelectedEventName` is set. This is the event name that the
    caller listens for. When an element is selected, the view should fire a
    JavaScript event with this name. Finally a search boolean is set, specifying
    when the view should render search results. When the user performs a search,
    this boolean should be set to `true`.

    In the same Item Selector view the markup is rendered using a JSP with the
    code shown below:

        RequestDispatcher requestDispatcher =
                servletContext.getRequestDispatcher("/sample.jsp");

    Although the example uses JSPs, you could use another language such as
    FreeMarker to render the markup.

7.  Finally, use the `@Reference` annotation to reference the class for the
    `setServletContext()` method.

    For example, the sample selector view class has the configuration below:

        @Reference(
            target =
            "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
            unbind = "-"
        )

    The `target` parameter is used to specify the available services for the
    servlet context. In this case, it specifies the
    `com.liferay.selector.sample.web` class as the default value, using the
    `osgi.web.symbolicname` property. Finally, the `unbind = _` parameter
    specifies that there is no unbind method for this bundle.

Below is the full example [source code](https://github.com/robertoDiaz/liferay-portal/blob/item-selector-sample/modules/apps/collaboration/item-selector/item-selector-sample-web/src/main/java/com/liferay/item/selector/sample/web/SampleItemSelectorView.java)
for the `SampleItemSelectorView` Java class:

    @Component(
            property = {"item.selector.view.order:Integer=200"},
            service = ItemSelectorView.class
    )
    public class SampleItemSelectorView
            implements ItemSelectorView<ImageItemSelectorCriterion> {

            public Class<ImageItemSelectorCriterion>
    getItemSelectorCriterionClass() {
                    return ImageItemSelectorCriterion.class;
            }

            public ServletContext getServletContext() {
                    return _servletContext;
            }

            public List<ItemSelectorReturnType>
    getSupportedItemSelectorReturnTypes() {
                    return _supportedItemSelectorReturnTypes;
            }

            public String getTitle(Locale locale) {
                    return "Sample Selector";
            }

            public boolean isShowSearch() {
                    return false;
            }

            public boolean isVisible(ThemeDisplay themeDisplay) {
                    return true;
            }

            public void renderHTML(
                            ServletRequest request, ServletResponse
    response,
                            ImageItemSelectorCriterion
    itemSelectorCriterion,
                            PortletURL portletURL, String
    itemSelectedEventName, boolean search)
                    throws IOException, ServletException {

                    request.setAttribute(_ITEM_SELECTED_EVENT_NAME,
    itemSelectedEventName);

                    ServletContext servletContext = getServletContext();

                    RequestDispatcher requestDispatcher =
                            servletContext.getRequestDispatcher("/sample.jsp");

                    requestDispatcher.include(request, response);
            }

            @Reference(
                    target =
    "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
                    unbind = "-"
            )
            public void setServletContext(ServletContext servletContext) {
                    _servletContext = servletContext;
            }

            private static final String _ITEM_SELECTED_EVENT_NAME =
                    "ITEM_SELECTED_EVENT_NAME";

            private static final List<ItemSelectorReturnType>
                    _supportedItemSelectorReturnTypes =
    Collections.unmodifiableList(
                            ListUtil.fromArray(
                                    new ItemSelectorReturnType[] {
                                            new URLItemSelectorReturnType()
                                    }));

            private ServletContext _servletContext;

    }

Once you've written your Java class, you'll need to create the view markup next.

## Writing your View Markup [](id=writing-your-view-markup)

You've implemented your view, specifying the criteria and return types, along
with important configuration information, such as how to render the view. All
that is left is to write the markup for your selection view now.

Naturally, the markup for your selection view will vary greatly depending on the
requirements of your app.

The [sample Item Selector JSP](https://github.com/robertoDiaz/liferay-portal/blob/item-selector-sample/modules/apps/collaboration/item-selector/item-selector-sample-web/src/main/Resources/META-INF/resources/sample.jsp)
has the placeholder markup shown below:

    <div class="container-fluid-1280">
            <h1>Hi, I'm a sample ITEM SELECTOR VIEW</h1>

            <p>Here you could add your own logic!!</p>
    </div>

This view will render just fine, but of course it doesn't actually contain any
logic to display the entity.

The view markup render for the sample item selector module has been passed as a
request attribute in the `renderHTML` method in the `SampleItemSelectorView`
class, so it can be used in the jsp as follows:

    Liferay.fire(
            '<%= {ITEM_SELECTED_EVENT_NAME} %>',

            {
                    data:
    {the-data-your-client-needs-according-to-return-type}
            }

    );

This event may be triggered by a UI component such as a button, in which case
the JSP may contain the following markup:

    <aui:button cssClass="selector-button" disabled="<%= true %>" value="choose"
    />

    <aui:script use="aui-base">

    var getImage = A.one('#<portlet:namespace />getImage');

            var button = getImage.one('.selector-button');

            button.on(
                    'click',
                    function(event) {

                            Util.getOpener().Liferay.fire(
                                    '<%= ITEM_SELECTED_EVENT_NAME) %>',
                                    {
                                        data:
                                        {
                                            the-data-your-client-needs
                                        }
                                    }
                            );

                            Util.getWindow().destroy();
                    }
    );

    </aui:script>

<!-- Is the example markup above correct? -->

The data is returned according to the specified desired
`ItemSelectorReturnTypes`. This is covered in more detail in the
[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api#obtaining-the-url-for-the-item-selector-views)
tutorial.

Your new selection view will automatically be rendered by the Item Selector in
every portlet that uses the element and return types you defined, without
modifying anything in those portlets.

Once your view is complete, you can follow the
[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api)
to learn how to generate the URL to open the Item Selector dialog to display
your new view.

## Related Topics [](id=related-topics)

[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api)

[Creating Custom Entities for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-elements-and-return-types)
