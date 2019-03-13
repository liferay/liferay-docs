---
header-id: creating-custom-item-selector-views
---

# Creating Custom Item Selector Views

You can create your own selection view if an Item Selector doesn't contain the 
one you need. The steps here show you how. For more information on custom 
selection views and the Item Selector API, see the 
[Item Selector introduction](/developer/frameworks/-/knowledge_base/7-2/item-selector). 

## Configuring Your Selection View's OSGi Module

First, you must configure your selection view's OSGi module: 

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

## Implementing Your Selection View's Class

Follow these steps to implement your selection view's class: 

1.  Create an `ItemSelectorView` component class that implements 
    [`ItemSelectorView`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html) 
    with the criterion as a type argument. In the `@Component` annotation, set 
    the `item.selector.view.order` property to the order you want the view to 
    appear in when displayed alongside other selector views (lower values get 
    higher priority). 

    This example selector view class is for images, so it implements 
    `ItemSelectorView` with 
    [`ImageItemSelectorCriterion`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html) 
    as a type argument. The `@Component` annotation sets the 
    `item.selector.view.order` property to `200` and registers the class as an 
    `ItemSelectorView` service: 

        @Component(
            property = {"item.selector.view.order:Integer=200"},
            service = ItemSelectorView.class
        )
        public class SampleItemSelectorView
            implements ItemSelectorView<ImageItemSelectorCriterion> {...

2.  Create getter methods for the criterion class, servlet context, and return 
    types. Do this by implementing the methods 
    [`getItemSelectorCriterionClass()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getItemSelectorCriterionClass--), 
    `getServletContext()`, and 
    [`getSupportedItemSelectorReturnTypes()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getSupportedItemSelectorReturnTypes--), 
    respectively: 

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

3.  Configure the selection view's title, search options, and visibility 
    settings. Here's an example configuration for the `Sample Selector` 
    selection view: 

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

    See [The Selection View's Class](/developer/frameworks/-/knowledge_base/7-2/item-selector#the-selection-views-class)
    for more information on these methods. 

4.  Implement the 
    [`renderHTML`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#renderHTML-javax.servlet.ServletRequest-javax.servlet.ServletResponse-T-javax.portlet.PortletURL-java.lang.String-boolean-) 
    method to set your view's render settings and render its markup. 

    Here's an example implementation of a `renderHTML` method that points to a 
    JSP file (`sample.jsp`) to render the view. Note that 
    `itemSelectedEventName` is passed as a request attribute so it can be used 
    in the view markup. The view markup is specified via the `ServletContext` 
    method `getRequestDispatcher`. Although this example uses a JSP, you can 
    render the markup in another language such as FreeMarker. 

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
    for this module. In the method body, set the servlet context variable: 

        @Reference(
            target =
            "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            _servletContext = servletContext;
        }

6.  Define the `_supportedItemSelectorReturnTypes` list with the return types 
    that this view supports (you referenced this list in step two). This example 
    adds 
    [`URLItemSelectorReturnType`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html) 
    and 
    [`FileEntryItemSelectorReturnType`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html) 
    to the list of supported return types (you can use more if needed). More 
    return types means that the view is more reusable. Also note that this 
    example defines its servlet context variable at the bottom of the file: 

        private static final List<ItemSelectorReturnType>
            _supportedItemSelectorReturnTypes =
            Collections.unmodifiableList(
                ListUtil.fromArray(
                    new ItemSelectorReturnType[] {
                        new FileEntryItemSelectorReturnType(),
                        new URLItemSelectorReturnType()
                    }));

         private ServletContext _servletContext;

For a real-world example of a view class, see 
[`SiteNavigationMenuItemItemSelectorView`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/site-navigation/site-navigation-item-selector-web/src/main/java/com/liferay/site/navigation/item/selector/web/internal/SiteNavigationMenuItemItemSelectorView.java). 

## Writing Your View Markup

You can write your view markup however you wish---there's no typical or average 
case. You can write it with taglibs, AUI components, or even pure HTML and
JavaScript. The markup must do two key things: 

-   Render the entities for the user to select. 
-   When an entity is selected, pass the return type information via a 
    JavaScript event. 

The example view class in the previous section passes the JavaScript event name 
as a request attribute in the `renderHTML` method. You can therefore use this 
event name in the markup: 

    Liferay.fire(
            `<%= {ITEM_SELECTED_EVENT_NAME} %>',

            {
                data:{
                    the-data-your-client-needs-according-to-the-return-type
                }
            }
    );

For a complete, real-world example, see 
[`layouts.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web/src/main/resources/META-INF/resources/layouts.jsp)
for the module 
[`com.liferay.layout.item.selector.web`](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web). 
Even though this example is for a previous version of @product@, it still 
applies to @product-ver@. Here's a walkthrough of this `layouts.jsp` file: 

1.  First, some variables are defined. Note that 
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

    You can learn more about using the `liferay-util` taglibs in 
    [Using the Liferay Util Taglib](/developer/frameworks/-/knowledge_base/7-2/using-the-liferay-util-taglib). 

3.  This snippet creates the UI to display the layout entities. It uses the 
    [`liferay-layout:layouts-tree`](@app-ref@/layout/latest/taglibdocs/liferay-layout/layouts-tree.html) 
    taglib along with the 
    [Lexicon](https://lexicondesign.io/) 
    design language to create 
    [cards](https://clayui.com/docs/components/cards.html): 

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

    ![Figure 1: The Layouts Item Selector view uses Lexicon and Liferay Layout taglibs to create the UI.](../../../images/layouts-item-selector-view.png)

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

    The `data-url` and `data-uuid` attributes are in the HTML for the Layouts 
    Item Selector. The HTML for an instance of the Layouts Item Selector is 
    shown here: 

    ![Figure 2: The URL and UUID can be seen in the `data-url` and `data-uuid` attributes of the Layout Item Selector's HTML.](../../../images/layouts-item-selector-html.png)

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
app that uses the criterion and return types you defined, without modifying 
anything in those apps. 

## Related Topics

[Item Selector](/developer/frameworks/-/knowledge_base/7-2/item-selector)

[Creating Custom Criterion and Return Types](/developer/frameworks/-/knowledge_base/7-2/creating-custom-criterion-and-return-types)

[Selecting Entities with an Item Selector](/developer/frameworks/-/knowledge_base/7-2/selecting-entities-with-an-item-selector)
