# Creating Custom Item Selector Views [](id=creating-custom-item-selector-views)

Have you found you need to create a new selection view for your app? No problem.
Item Selector views are determined by the type of entity the user is selecting.
The Item Selector can render multiple views for the same entity type. For
example, when a user requires an image from the Item Selector, the selection
views shown below are rendered:

![Figure 1: An entity type can have multiple selection views.](../../images/item-selector-tabs.png)

Each tab: *Blog Images*, *Documents and Media*, *URL*, and *Upload Image*, is a 
selection view for the Item Selector, each one represented by an 
`*ItemSelectorCriterion` class. The tabs in figure 1 are represented by the 
following `*ItemSelectorCriterion`:

-  [`BlogsItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/item/selector/criterion/BlogsItemSelectorCriterion.html): 
   Blog Images View
-  [`ImageItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html): 
   Documents and Media View
-  [`URLItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html): 
   URL View
-  [`UploadItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html): 
   Upload Image View

The default selection views may provide everything you need for your application. 
If, however, your application requires a custom selection view, for instance to 
link to an external image provider, you can follow the steps outlined in this 
tutorial.

This tutorial covers how to create new selection views for the Item Selector.

Get started by configuring the module for your view next.

## Configuring the Module [](id=configuring-the-module)

Follow these steps to prepare your module:

1.  Add these dependencies to your module's `build.gradle`:

        dependencies {
                compileOnly group: "com.liferay", name: "com.liferay.item.selector.api", version: "2.0.0"
                compileOnly group: "com.liferay", name: "com.liferay.item.selector.criteria.api", version: "2.0.0"
                compileOnly group: "com.liferay.portal", name: "com.liferay.portal.impl", version: "2.0.0"
                compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
                compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
                compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
                compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
                compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        }

2.  Add your module's information to the `bnd.bnd` file. For example, the 
    configuration below adds the information for a module called `My Custom 
    View`. 

        Bundle-Name: My Custom View
        Bundle-SymbolicName: com.liferay.docs.my.custom.view
        Bundle-Version: 1.0.0
 
3.  Add a `Web-ContextPath` to your `bnd.bnd` to point to the resources for your
    module. For example:
    
        Include-Resource:\
                META-INF/resources=src/main/resources/META-INF/resources
        Web-ContextPath: /my-custom-view
 
    If you don't have a `Web-ContextPath` your module won't know where your
    resources are. The `Include-Resource` header points to the relative
    path for the module's resources.

Now that your module is configured, you can create the view next.

## Implementing the View [](id=implementing-the-view)

To create a new view you must first know what kind of entities you want to 
select in the new view: images, videos, users, etc. The kind of entities you 
choose determines the specific `ItemSelectorCriterion` you need to use. For 
example if you were selecting images, you must use the
`ImageItemSelectorCriterion`.

Next, you need to know the type of information the entity can return when it's
selected. For example, if the entity returns its URL, you would use 
`URLItemSelectorReturnType` for the return type.

For a full list of the available criterion and returns types that Liferay's apps
and app suite's provide see the [Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-0/item-selector-criterion-and-return-types)
reference.

Once you've determined the kinds of entities you wish to select, follow these 
steps to create your selection view:

1.  Create an Item Selector View Component class:

        @Component(
            property = {"item.selector.view.order:Integer=200"},
            service = ItemSelectorView.class
        )

    Note that the OSGi component is registered with the property
    `item.selector.view.order`. The Item Selector view order (the order of the
    rendered tab views) is prioritized according to these settings:

    - The criteria order specified in the [`getItemSelectorURL` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) 
    of the application.

    - The `item.selector.view.order` property's value for multiple views with 
    the same criteria. The lower the value is, the more priority it has, and the 
    sooner it will appear in the order.
 
2.  Implement the [`ItemSelectorView` interface](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html) 
    using the *criterion* the view requires. For example, the configuration 
    below uses the [`ImageItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html) 
    to implement the view:
        
        public class SampleItemSelectorView
            implements ItemSelectorView<ImageItemSelectorCriterion> {

            @Override
            public Class<ImageItemSelectorCriterion> getItemSelectorCriterionClass() 
            {
                return ImageItemSelectorCriterion.class;
            }

            @Override            
            public ServletContext getServletContext() {
                return _servletContext;
            }

            @Override            
            public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
                return _supportedItemSelectorReturnTypes;
            }

        }
 
    The implementation above also sets up some methods you'll use in the steps
    that follow. The [`getSupportedItemSelectorReturnTypes` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getSupportedItemSelectorReturnTypes--) 
    returns a list of *ItemSelectorReturnType*s. You'll populate this list in a 
    later step to specify the return types the selection view supports.

    +$$$
    
    **Note:** If you want your new selection view to be available only when
    selecting the entity for something specific such as a blog entry, replace
    the `*ItemSelectorCriterion` in your `*ItemSelectorView` class with the
    `*ItemSelectorCriterion` class you wish to use, such as the
    [`BlogsItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/item/selector/criterion/BlogsItemSelectorCriterion.html).
    
    $$$

3.  Configure the title, search options, and visibility settings for the
    selection view:

    An example configuration is shown below for a selection view called 
    `Sample Selector`:

        @Override
        public String getTitle(Locale locale) {
            return "Sample Selector";
        }

        @Override
        public boolean isShowSearch() {
            return false;
        }

        @Override
        public boolean isVisible(ThemeDisplay themeDisplay) {
            return true;
        }

    The following methods are demonstrated above:
    
    - [`getTitle` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getTitle-java.util.Locale-): 
    returns the localized title of the tab to display in the Item Selector 
    dialog.

    - [`isShowSearch()` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isShowSearch--): 
    returns whether the Item Selector view should show the search field. 
    
    +$$$
    
    **Note:** To implement search, return `true` for this method. The 
    `renderHTML` method, covered in the next section, indicates whether a user 
    performed a search based on the value of the `search` parameter. Then the 
    keywords the user searched can be obtained as follows:

        String keywords = ParamUtil.getString(request, "keywords");

    $$$

    - [`isVisible()` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isVisible-com.liferay.portal.kernel.theme.ThemeDisplay-):
    returns whether the Item Selector view is visible. In most cases, you'll 
    want to set this to `true`. You can use this method to add conditional logic 
    to disable the view.

4.  Next, set the render settings for your view, using the [`renderHTML` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#renderHTML-javax.servlet.ServletRequest-javax.servlet.ServletResponse-T-javax.portlet.PortletURL-java.lang.String-boolean-).
    The example below points to a JSP file to render the view:

        @Override
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

    The `renderHTML` methods passes the `*ItemSelectorCriterion` required to 
    display the selection view. Next, the `portletURL`, used to invoke the Item 
    Selector, is passed. Then the `itemSelectedEventName` is passed. This is the 
    event name that the caller listens for. When an element is selected, the
    view fires a JavaScript event with this name. Finally, a search boolean is
    passed, specifying when the view should render search results.  When the
    user performs a search, this boolean should be set to `true`.
    
    Note that the `itemSelectedEventName` is passed as a request attribute, so
    it can be used in the view markup.

    The view markup is specified this way: 

        RequestDispatcher requestDispatcher =
                servletContext.getRequestDispatcher("/sample.jsp");

    Although the example uses JSPs, you can use another language such as
    FreeMarker to render the markup.

5.  Use the `@Reference` annotation to reference your module's class 
    for the `setServletContext` method.

    Below is an example configuration:

        @Reference(
            target =
            "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
            unbind = "-"
        )
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

    The `target` parameter is used to specify the available services for the
    servlet context. In this case, it specifies the
    `com.liferay.selector.sample.web` class as the default value, using the
    `osgi.web.symbolicname` property. Finally, the `unbind = _` parameter
    specifies that there is no unbind method for this module. A method is 
    defined to set the servlet context as well.

6.  Finally, populate the `_supportedItemSelectorReturnTypes` list specified in 
    step 2 with the return types that this view supports.
    
    The example below adds the [`URLItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html) 
    and [`FileEntryItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html) 
    to the list of supported return types, but you could use more return types 
    if the view could return them. More return types means that the view is more 
    reusable:

        private static final List<ItemSelectorReturnType>
            _supportedItemSelectorReturnTypes =
            Collections.unmodifiableList(
                ListUtil.fromArray(
                    new ItemSelectorReturnType[] {
                        new FileEntryItemSelectorReturnType(),
                        new URLItemSelectorReturnType()
                    }));

         private ServletContext _servletContext;
         
    The servlet context variable is declared at the bottom of the file.
    
As a complete example, below is the full code for the 
[`FlickrItemSelectorView` class](https://github.com/liferay/liferay-portal/blob/586f66c629b559e79c744559751ecb960218fe0b/modules/apps/collaboration/item-selector/item-selector-web/src/test/java/com/liferay/item/selector/web/internal/FlickrItemSelectorView.java):

    public class FlickrItemSelectorView
            implements ItemSelectorView<FlickrItemSelectorCriterion> {
    
            @Override
            public Class<FlickrItemSelectorCriterion> getItemSelectorCriterionClass() {
                    return FlickrItemSelectorCriterion.class;
            }
    
            @Override
            public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
                    return _supportedItemSelectorReturnTypes;
            }
    
            @Override
            public String getTitle(Locale locale) {
                    return FlickrItemSelectorView.class.getName();
            }
    
            @Override
            public boolean isShowSearch() {
                    return false;
            }
    
            @Override
            public boolean isVisible(ThemeDisplay themeDisplay) {
                    return true;
            }
    
            @Override
            public void renderHTML(
                            ServletRequest request, ServletResponse response,
                            FlickrItemSelectorCriterion flickrItemSelectorCriterion,
                            PortletURL portletURL, String itemSelectedEventName, 
                            boolean search)
                    throws IOException {
    
                    PrintWriter printWriter = response.getWriter();
    
                    printWriter.print(
                            "<html>" + FlickrItemSelectorView.class.getName() + 
                            "</html>");
            }
    
            private static final List<ItemSelectorReturnType>
                    _supportedItemSelectorReturnTypes = 
                    Collections.unmodifiableList(
                            ListUtil.fromArray(
                                    new ItemSelectorReturnType[] {
                                            new TestURLItemSelectorReturnType()
                                    }));
    
    }
    
The diagram below illustrates how the Item Selector's API works (right-click to view larger image):

![Figure 2: Item Selector views are determined by the desired return types of the criterion, the supported return types of the view, and the criterion supported by the view.](../../images/item-selector-architecture.png)

Once you've implemented your Item Selector view, you must create the view 
markup.

## Writing your View Markup [](id=writing-your-view-markup)

You've implemented your view, specifying the criteria and return types, along
with important configuration information, such as how to render the view. All
that's left is to write the markup for your selection view.

Naturally, the markup for your selection view will vary greatly depending on the
requirements of your app. You can use taglibs, AUI components, or even pure HTML 
and JavaScript if you prefer, to write your view. Regardless of the approach you 
choose to create your view, the view must do two key things:

- Render the entities for the user to select
- Contain JavaScript logic that passes the information specified by the Item 
  Selector return type via a JavaScript event when an entity is selected.

If you're following the example in the last section, the JavaScript event name 
has been passed as a request attribute in the `renderHTML` method of the 
`*ItemSelectorView` class, so it can be used in the view markup as follows:

    Liferay.fire(
            `<%= {ITEM_SELECTED_EVENT_NAME} %>',
            
            {
                data:{
                    the-data-your-client-needs-according-to-the-return-type
                }
            }
    );

Below is the complete [`Layouts.jsp` view markup](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web/src/main/resources/META-INF/resources/layouts.jsp)
for the [`com.liferay.layout.item.selector.web` module](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web):

Some Java imports are defined first:
    
    <%
    LayoutItemSelectorViewDisplayContext layoutItemSelectorViewDisplayContext = 
    (LayoutItemSelectorViewDisplayContext)request.getAttribute(
    BaseLayoutsItemSelectorView.LAYOUT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
    
    LayoutItemSelectorCriterion layoutItemSelectorCriterion = 
    layoutItemSelectorViewDisplayContext.getLayoutItemSelectorCriterion();
    
    Portlet portlet = PortletLocalServiceUtil.getPortletById(
    company.getCompanyId(), portletDisplay.getId());
    %>
    
Note that the `LayoutItemSelectorViewDisplayContext` is an optional class that
contains additional information about the criteria and view, but it isn't
required.

The snippet below imports a CSS file for styling and places it in the `<head>` 
of the page:
    
    <liferay-util:html-top>
            <link href="<%= PortalUtil.getStaticResourceURL(
            request, application.getContextPath() + "/css/main.css", 
            portlet.getTimestamp()) 
            %>" rel="stylesheet" type="text/css" />
    </liferay-util:html-top>
    
You can learn more about using the `liferay-util` taglibs in the 
[Using the Liferay Util Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-util-taglib)
tutorial.

This snippet creates the UI to display the layout entities. It uses the 
[`liferay-layout:layouts-tree` taglib](@platform-ref@/7.0-latest/taglibs/modules/apps/web-experience/layout/com.liferay.layout.taglib/liferay-layout/layouts-tree.html) 
along with the [Lexicon](http://liferay.github.io/lexicon/) design language to 
create [cards](http://liferay.github.io/lexicon/content/cards/):

    <div class="container-fluid-1280 layouts-selector">
            <div class="card-horizontal main-content-card">
                    <div class="card-row card-row-padded">
                            <liferay-layout:layouts-tree
                                    checkContentDisplayPage="<%= layoutItemSelectorCriterion.isCheckDisplayPage() %>"
                                    draggableTree="<%= false %>"
                                    expandFirstNode="<%= true %>"
                                    groupId="<%= scopeGroupId %>"
                                    portletURL="<%= layoutItemSelectorViewDisplayContext.getEditLayoutURL() %>"
                                    privateLayout="<%= layoutItemSelectorViewDisplayContext.isPrivateLayout() %>"
                                    rootNodeName="<%= layoutItemSelectorViewDisplayContext.getRootNodeName() %>"
                                    saveState="<%= false %>"
                                    selectedLayoutIds="<%= layoutItemSelectorViewDisplayContext.getSelectedLayoutIds() %>"
                                    selPlid="<%= layoutItemSelectorViewDisplayContext.getSelPlid() %>"
                                    treeId="treeContainer"
                            />
                    </div>
            </div>
    </div>
    
The configuration above renders the UI shown in the figure below:

![Figure 3: The Layouts Item Selector view uses the Lexicon design language and Liferay Layout taglibs to create the UI.](../../images/layouts-item-selector-view.png)

This portion of the `aui:script` returns the path for the page:

    <aui:script use="aui-base">
            var LString = A.Lang.String;
    
            var getChosenPagePath = function(node) {
                    var buffer = [];
    
                    if (A.instanceOf(node, A.TreeNode)) {
                            var labelText = LString.escapeHTML(node.get('labelEl').text());
    
                            buffer.push(labelText);
    
                            node.eachParent(
                                    function(treeNode) {
                                            var labelEl = treeNode.get('labelEl');
    
                                            if (labelEl) {
                                                    labelText = LString.escapeHTML(labelEl.text());
    
                                                    buffer.unshift(labelText);
                                            }
                                    }
                            );
                    }
    
                    return buffer.join(' > ');
            };

The snippet below passes the return type data when the layout(entity) is 
selected. In particular, take note of the `url` and `uuid` variables, which
retrieve the URL or UUID for the layout:
            
            var setSelectedPage = function(event) {
                    var disabled = true;
    
                    var messageText = '<%= UnicodeLanguageUtil.get(request, "there-is-no-selected-page") %>';
    
                    var lastSelectedNode = event.newVal;
    
                    var labelEl = lastSelectedNode.get('labelEl');
    
                    var link = labelEl.one('a');
    
                    var url = link.attr('data-url');
                    var uuid = link.attr('data-uuid');
    
                    var data = {};
    
                    if (link && url) {
                            disabled = false;
    
                            data.layoutpath = getChosenPagePath(lastSelectedNode);

This checks if the return type information is a URL or a UUID; then it sets the 
value for the JSON object's `data` attribute accordingly:
                            
                            <c:choose>
                                    <c:when test="<%= Objects.equals(layoutItemSelectorViewDisplayContext.getItemSelectorReturnTypeName(), URLItemSelectorReturnType.class.getName()) %>">
                                            data.value = url;
                                    </c:when>
                                    <c:when test="<%= Objects.equals(layoutItemSelectorViewDisplayContext.getItemSelectorReturnTypeName(), UUIDItemSelectorReturnType.class.getName()) %>">
                                            data.value = uuid;
                                    </c:when>
                            </c:choose>
                    }
    
                    <c:if test="<%= Validator.isNotNull(layoutItemSelectorViewDisplayContext.getCkEditorFuncNum()) %>">
                            data.ckeditorfuncnum: <%= layoutItemSelectorViewDisplayContext.getCkEditorFuncNum() %>;
                    </c:if>

The `data-url` and `data-uuid` attributes are in the HTML markup for the Layouts
Item Selector. The HTML markup for an instance of the Layouts Item Selector is
shown below:

![Figure 4: The URL and UUID can be seen in the `data-url` and `data-uuid` attributes of the Layout Item Selector's HTML markup.](../../images/layouts-item-selector-html.png)

The last line adds the `CKEditorFuncNum` for the editor to the JSON object's 
`data` attribute.

The JavaScript trigger event, specified in the Item Selector return type, is 
fired, passing the data JSON object with the required return type information:

                    Liferay.Util.getOpener().Liferay.fire(
                            '<%= layoutItemSelectorViewDisplayContext.getItemSelectedEventName() %>',
                            {
                                    data: data
                            }
                    );
            };

Finally, the layout is set to the selected page:
            
            var container = A.one('#<portlet:namespace />treeContainerOutput');
    
            if (container) {
                    container.swallowEvent('click', true);
    
                    var tree = container.getData('tree-view');
    
                    tree.after('lastSelectedChange', setSelectedPage);
            }
    </aui:script>

Your new selection view is automatically rendered by the Item Selector in every
portlet that uses the criterion and return types you defined, without modifying
anything in those portlets.

Now you know how to create custom views for the Item Selector!

## Related Topics [](id=related-topics)

[Selecting Entities Using the Item Selector](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector)

[Creating Custom Item Selector Entities](/develop/tutorials/-/knowledge_base/7-0/creating-custom-item-selector-entities)
