# Creating Custom Item Selector Views [](id=creating-custom-item-selector-views)

Item Selector's default selection views may provide everything you need for your 
app. Custom selection views are required, however, for certain situations. For 
example, if you want your users to be able to select images from an external 
image provider, then you must create a custom selection view. You can create a 
custom selection view by following the steps in this tutorial. Before getting 
started, you'll learn a bit more about selection views. 

Note that the view the Item Selector presents is determined by the type of 
entity the user is selecting. The Item Selector can also render multiple views 
for the same entity type. For example, several selection views are available 
when a user selects an image. Each selection view is a tab in the UI that 
corresponds to the image's location. 

![Figure 1: An entity type can have multiple selection views.](../../../images/item-selector-tabs.png)

Each selection view is represented by an `*ItemSelectorCriterion` class. The 
tabs in figure 1 are represented by the following `*ItemSelectorCriterion`: 

-  [`BlogsItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/item/selector/criterion/BlogsItemSelectorCriterion.html): 
   Blog Images View
-  [`ImageItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html): 
   Documents and Media View
-  [`URLItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html): 
   URL View
-  [`UploadItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html): 
   Upload Image View

You'll create a custom selection view by following these steps: 

1.  Configure your selection view's OSGi module. 
2.  Implement the selection view's class. 
3.  Write your selection view's markup. 

## Configuring Your Selection View's OSGi Module [](id=configuring-the-module)

Follow these steps to configure your selection view's module: 

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

2.  Add your module's information to the `bnd.bnd` file. For example, this 
    configuration adds the information for a module called `My Custom View`: 

        Bundle-Name: My Custom View
        Bundle-SymbolicName: com.liferay.docs.my.custom.view
        Bundle-Version: 1.0.0
 
3.  Add a `Web-ContextPath` to your `bnd.bnd` to point to your module's 
    resources: 

        Include-Resource:\
                META-INF/resources=src/main/resources/META-INF/resources
        Web-ContextPath: /my-custom-view

    If you don't have a `Web-ContextPath`, your module won't know where your 
    resources are. The `Include-Resource` header points to the relative path for 
    the module's resources. 

Now that your module is configured, you can create the selection view's class. 

## Implementing Your Selection View's Class [](id=implementing-the-view)

To create a new selection view, you must first know what kind of entities you 
want it to present (images, videos, users, etc.). This determines the specific 
`ItemSelectorCriterion` you need to use. For example, a selection view for 
images must use `ImageItemSelectorCriterion`. 

You must also know the entity's return type (the information type you expect 
from entities when users select them). For example, if a selected entity returns 
its URL, you would use `URLItemSelectorReturnType` for the return type. 

For a full list of the criterion and returns types available in @product@'s 
apps, see the reference document 
[Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-1/item-selector-criterion-and-return-types). 

Once you've determined these things, follow these steps to create your selection 
view's class: 

1.  Create an `ItemSelectorView` component class that implements the 
    [`ItemSelectorView` interface](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html). 
    Use the criterion the view requires as a type argument to this interface. In 
    the `@Component` annotation, set the `item.selector.view.order` property to 
    the order you want it to appear in when displayed alongside other selector 
    views of the same criterion. The lower this value is, the higher the view's 
    priority is and the sooner it appears in the order. 

    For example, this example selector view class is for images, so it 
    implements `ItemSelectorView` with the 
    [`ImageItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html) 
    as a type argument. The `@Component` annotation sets the `item.selector.view.order` 
    property to `200` and registers the class as an `ItemSelectorView` service: 

        @Component(
            property = {"item.selector.view.order:Integer=200"},
            service = ItemSelectorView.class
        )
        public class SampleItemSelectorView
            implements ItemSelectorView<ImageItemSelectorCriterion> {...

    Note that the criteria order can also be specified in the app's 
    [`getItemSelectorURL` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-).
 
2.  Create getter methods for the criterion class, servlet context, and return 
    types. You'll use these in the steps that follow: 

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

    Note that the 
    [`getSupportedItemSelectorReturnTypes` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getSupportedItemSelectorReturnTypes--) 
    returns a list of `ItemSelectorReturnType`s. You'll populate this list in a 
    later step to specify the return types that the selection view supports. 

3.  Configure the title, search options, and visibility settings for the 
    selection view. You'll do this via these methods: 

    - [`getTitle`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getTitle-java.util.Locale-): 
    returns the localized title of the tab to display in the Item Selector 
    dialog. 

    - [`isShowSearch()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isShowSearch--): 
    returns whether the Item Selector view should show the search field. 
    
    +$$$
    
    **Note:** To implement search, return `true` for this method. The 
    `renderHTML` method, covered in the next section, indicates whether a user 
    performed a search based on the value of the `search` parameter. Then the 
    keywords the user searched can be obtained as follows:

        String keywords = ParamUtil.getString(request, "keywords");

    $$$

    - [`isVisible()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isVisible-com.liferay.portal.kernel.theme.ThemeDisplay-):
    returns whether the Item Selector view is visible. In most cases, you'll 
    want to set this to `true`. You can use this method to add conditional logic 
    to disable the view. 

    Here's an example configuration for the `Sample Selector` selection view: 

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

4.  Use the 
    [`renderHTML` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#renderHTML-javax.servlet.ServletRequest-javax.servlet.ServletResponse-T-javax.portlet.PortletURL-java.lang.String-boolean-) 
    to set the render settings for your view. In addition to the servlet request 
    and response, this method takes the following arguments: 

    - `itemSelectorCriterion`: the `*ItemSelectorCriterion` required to display 
      the selection view. 
    - `portletURL`: the portlet URL used to invoke the Item Selector. 
    - `itemSelectedEventName`: the event name that the caller listens for. When 
      an element is selected, the view fires a JavaScript event with this name. 
    - `search`: a search boolean that specifies when the selection view should 
      render search results. When the user performs a search, this boolean 
      should be set to `true`. 

    Here's an example implementation of a `renderHTML` method that points to a 
    JSP file (`sample.jsp`) to render the view. Note that the 
    `itemSelectedEventName` is passed as a request attribute so it can be used 
    in the view markup. The view markup is specified via the `ServletContext` 
    method `getRequestDispatcher`. Although this example uses JSPs, you can use 
    another language such as FreeMarker to render the markup: 

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

5.  Use the `@Reference` annotation to reference your module's class 
    for the `setServletContext` method. In the annotation, use the `target` 
    parameter to specify the available services for the servlet context. This 
    example uses the `osgi.web.symbolicname` property to specify the 
    `com.liferay.selector.sample.web` class as the default value. You should 
    also use the `unbind = _` parameter to specify that there's no unbind method 
    for this module. In the method body, simply set the servlet context 
    variable: 

        @Reference(
            target =
            "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            _servletContext = servletContext;
        }

6.  Define the `_supportedItemSelectorReturnTypes` list that you referenced in 
    step 2 with the return types that this view supports. This example adds the 
    [`URLItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html) 
    and 
    [`FileEntryItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html) 
    to the list of supported return types (you can use more return types if 
    needed). More return types means that the view is more reusable. Also note 
    that this example defines its servlet context variable at the bottom of the 
    file:

        private static final List<ItemSelectorReturnType>
            _supportedItemSelectorReturnTypes =
            Collections.unmodifiableList(
                ListUtil.fromArray(
                    new ItemSelectorReturnType[] {
                        new FileEntryItemSelectorReturnType(),
                        new URLItemSelectorReturnType()
                    }));

         private ServletContext _servletContext;

For a real-world example of a view class, see the 
[`SiteNavigationMenuItemItemSelectorView` class](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/web-experience/site-navigation/site-navigation-item-selector-web/src/main/java/com/liferay/site/navigation/item/selector/web/internal/SiteNavigationMenuItemItemSelectorView.java). 

## Writing Your View Markup [](id=writing-your-view-markup)

Now that you've implemented your selection view's class, you must write the 
markup that renders the view. The exact markup you write depends on your app's 
needs. It also depends on your personal preferences, as you can write it with 
taglibs, AUI components, or even pure HTML and JavaScript. Therefore, there's no 
standard or typical view markup, even for simple applications. Regardless, the 
markup must do two key things: 

-   Render the entities for the user to select. 
-   When an entity is selected, pass the information specified by the Item 
    Selector return type via a JavaScript event. 

For example, the example view class in the previous section passes the 
JavaScript event name as a request attribute in the `renderHTML` method. You can 
therefore use this event name in the markup: 

    Liferay.fire(
            `<%= {ITEM_SELECTED_EVENT_NAME} %>',

            {
                data:{
                    the-data-your-client-needs-according-to-the-return-type
                }
            }
    );

For a complete, real-world example, see the 
[`layouts.jsp` view markup](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web/src/main/resources/META-INF/resources/layouts.jsp)
for the 
[`com.liferay.layout.item.selector.web` module](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web). 
Even though this example is for the previous version of @product@, it still 
applies to @product-ver@. Here's a walkthrough of this `layouts.jsp` file:

1.  This `layouts.jsp` file first defines some variables. Note that 
    `LayoutItemSelectorViewDisplayContext` is an optional class that contains 
    additional information about the criteria and view: 

        <%
        LayoutItemSelectorViewDisplayContext layoutItemSelectorViewDisplayContext = 
            (LayoutItemSelectorViewDisplayContext)request.getAttribute(
            BaseLayoutsItemSelectorView.LAYOUT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);

        LayoutItemSelectorCriterion layoutItemSelectorCriterion = 
            layoutItemSelectorViewDisplayContext.getLayoutItemSelectorCriterion();

        Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), 
            portletDisplay.getId());
        %>

2.  This snippet imports a CSS file for styling and places it in the `<head>` of 
    the page: 

        <liferay-util:html-top>
                <link href="<%= PortalUtil.getStaticResourceURL(
                request, application.getContextPath() + "/css/main.css", 
                portlet.getTimestamp()) 
                %>" rel="stylesheet" type="text/css" />
        </liferay-util:html-top>
    
    You can learn more about using the `liferay-util` taglibs in the tutorial 
    [Using the Liferay Util Taglib](/develop/tutorials/-/knowledge_base/7-1/using-the-liferay-util-taglib). 

3.  This snippet creates the UI to display the layout entities. It uses the 
    [`liferay-layout:layouts-tree` taglib](@platform-ref@/7.0-latest/taglibs/modules/apps/web-experience/layout/com.liferay.layout.taglib/liferay-layout/layouts-tree.html) 
    along with the 
    [Lexicon](https://liferay.github.io/clay/) 
    design language to create 
    [cards](https://liferay.github.io/clay/content/cards/):

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
    
    This renders the following UI: 

    ![Figure 2: The Layouts Item Selector view uses Lexicon and Liferay Layout taglibs to create the UI.](../../../images/layouts-item-selector-view.png)

4.  This portion of the `aui:script` returns the path for the page:

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

5.  The following snippet passes the return type data when the layout (entity) 
    is selected. Note the `url` and `uuid` variables retrieve the URL or UUID 
    for the layout: 

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

6.  This checks if the return type information is a URL or a UUID. It then sets 
    the value for the JSON object's `data` attribute accordingly. The last line 
    adds the `CKEditorFuncNum` for the editor to the JSON object's `data` 
    attribute: 

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

    The `data-url` and `data-uuid` attributes are in the HTML markup for the 
    Layouts Item Selector. The HTML markup for an instance of the Layouts Item 
    Selector is shown here: 

    ![Figure 3: The URL and UUID can be seen in the `data-url` and `data-uuid` attributes of the Layout Item Selector's HTML markup.](../../../images/layouts-item-selector-html.png)

7.  The JavaScript trigger event specified in the Item Selector return type is 
    fired, passing the data JSON object with the required return type 
    information: 

                    Liferay.Util.getOpener().Liferay.fire(
                            '<%= layoutItemSelectorViewDisplayContext.getItemSelectedEventName() %>',
                            {
                                    data: data
                            }
                    );
            };

8.  Finally, the layout is set to the selected page: 

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

Great! Now you know how to create custom views for the Item Selector. 

## Related Topics [](id=related-topics)

[Understanding the Item Selector API's Components](/develop/tutorials/-/knowledge_base/7-1/understanding-the-item-selector-apis-components)

[Selecting Entities Using the Item Selector](/develop/tutorials/-/knowledge_base/7-1/selecting-entities-using-the-item-selector)

[Creating Custom Item Selector Entities](/develop/tutorials/-/knowledge_base/7-1/creating-custom-item-selector-entities)
