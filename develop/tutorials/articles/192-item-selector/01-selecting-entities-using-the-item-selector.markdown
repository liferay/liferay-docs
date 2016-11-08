# Selecting Entities Using the Item Selector [](id=selecting-entities-using-the-item-selector)

The Item Selector allows users to select entities, such as images, videos,
documents, and sites. 

![Figure 1: The Item Selector makes selecting entities a breeze.](../../images/item-selector-dialog-01.png)

Here's what's required to use an Item Selector:

1.  **Determine Item Criteria**

2.  **Get an Item Selector for the Criteria**

3.  **Use an Item Selector Dialog**

## Determining Item Selector Criteria [](id=determining-item-selector-criteria)

The first step is determining entity types to select from the Item Selector and
the data you expect from them. What kind of entity do you want to select? Do you
want to select a user, an image, a video, or something else?

Once you know the entities you want, you need *criterion* classes to represent
them in the Item Selector. Criterion classes must implement the [`ItemSelectorCriterion` interface](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html). 
The [Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-0/item-selector-criterion-and-return-types)
reference lists criterion classes Liferay's apps and app suites provide. 

If there's no criterion class for your entity, you can create your
own [`ItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html)
(tutorial coming soon). 

Then determine the type of information (return type) you expect from the
entities when users select them. Do you expect a URL? A Universally Unique
Identifier (UUID)? A primary key? Each return type must be represented by
an implementation of the [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html).
The [Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-0/item-selector-criterion-and-return-types)
reference also lists return type classes Liferay's apps and app suites provide. 

If there's no return type class that meets your needs, you can implement your
own [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html)
(tutorial coming soon). 

+$$$

**Note**: Each criterion must have at least one `ItemSelectorReturnType` (return
type) associated with it.

$$$

For example, if you want to allow users to select an image and want the image's
URL returned, you could use the `ImageItemSelectorCriterion` criterion class and
the `URLItemSelectorReturnType` return type.

The criterion and return types are collectively referred to as the Item
Selector's *criteria*. The Item Selector uses it to decide which selection views
(tabs of items) to show.

Once you've defined your criteria, you can get an Item Selector to use with it.

## Getting an Item Selector for the Criteria [](id=getting-an-item-selector-for-the-criteria)

In order to use an Item Selector with your criteria, you must get an Item
Selector URL based on the criteria. The URL is needed to open the Item Selector
dialog in your UI. In Java, you build the criteria and pass it in a call to get
the Item Selector's URL.

First, get an Item Selector OSGi Service Component using Declarative Services. 

    import com.liferay.item.selector.ItemSelector;
    import org.osgi.service.component.annotations.Reference;

    @Reference
    private ItemSelector _itemSelector

The component annotations are available in the [`org.osgi.service.component.annotations` module](http://mvnrepository.com/artifact/org.osgi/org.osgi.service.component.annotations). 

To get a URL to the Item Selector, you must call its [`getItemSelectorURL` method](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) 
using the following parameters:

-   `RequestBackedPortletURLFactory`: Factory that creates portlet URLs. 
-   `ItemSelectedEventName`: Unique arbitrary JavaScript event name that is
    triggered by the Item Selector when the element is selected.
-   `ItemSelectorCriterion`: Criterion (or an array of criterion objects) that
    specifies the type of elements to make available in the Item Selector. 

The following code demonstrates getting a URL to an Item Selector configured with
criteria for images:

    RequestBackedPortletURLFactory requestBackedPortletURLFactory =
        RequestBackedPortletURLFactoryUtil.create(request);

    List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
        new ArrayList<>();
    desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());

    ImageItemSelectorCriterion imageItemSelectorCriterion =
        new ImageItemSelectorCriterion();

    imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
        desiredItemSelectorReturnTypes);

    PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
        requestBackedPortletURLFactory, "sampleTestSelectItem",
        imageItemSelectorCriterion);

First it gets a factory to create the URL by invoking the
`RequestBackedPortletURLFactoryUtil.create` method, passing it the current
request object. The request can be an `HttpServletRequest` or `PortletRequest`.

Then it creates a list of return types expected for the image entity and a
criterion for images. The return types list consists of a URL return type
`URLItemSelectorReturnType`. The list is passed to the criterion's
`setDesiredItemSelectorReturnTypes` method.

Lastly the method `getItemSelectorURL` is called to return a URL based on the
criteria. The method requires a URL factory, an arbitrary event name, and a
series of criterion (one, in this case).

+$$$

**Note**: You can invoke the URL object's `toString` method to get its value.

$$$

An Item Selector can be configured to use any number of criterion. The criterion
can use any number of return types.

The Item Selector's criterion order determines the selection view order. For
example, if you pass the item selector criteria in this order:
`ImageItemSelectorCriterion`, `VideoItemSelectorCriterion`, the Item Selector
displays the image selection views first and then the ones for videos. If you
reverse the order, it shows the video selection views first and the image
selection views second.

Return type order is also significant. A view uses the first return type it
supports from each criterion's return type list. 

Now that you've got a URL to the Item Selector you've configured, you can start
using the Item Selector in your UI. 

## Using the Item Selector Dialog [](id=using-the-item-selector-dialog)

To open the Item Selector in your UI, you must use the
`LiferayItemSelectorDialog` JavaScript component from [AlloyUI's](http://alloyui.com/)
`liferay-item-selector-dialog` module. The component listens for the item
selected event that you specified for the Item Selector URL. The event returns
the selected element's information according to its return type.

Here are the steps for using the Item Selector dialog in a JSP:

1.  Declare the AUI tag library. 

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  Add the `<aui:script>` tag and configure it to use the
    `liferay-item-selector-dialog` module: 
    
        <aui:script use="liferay-item-selector-dialog">
        
        </aui:script>
 
3.  Attach an event handler to the UI element you want to use to open the Item 
    Selector dialog. 

    For example, the configuration below creates a *Choose* button with the ID
    `chooseImage` with a click event and function attached. 

        <aui:button name="chooseImage" value="Choose" />
    
        <aui:script use="liferay-item-selector-dialog">

            $('#<portlet:namespace />chooseImage').on(
            'click',
              function(event) {
                <!-- function logic goes here -->
              }
            );

        </aui:script>
 
5.  Inside the function, you must create a new instance of the
    `LiferayItemSelectorDialog` AlloyUI Component and configure it to use the
    Item Selector. Here's what you need to do in the function:

    -   Set the function's `eventName` attribute to the item selected event name
        you specified in your Java code (the code that gets the Item Selector
        URL).
    -   For the `on` attribute, implement a function that operates on the
        selected item change. The example code below demonstrates this.
    -   Set the function's `title` attribute. This becomes the dialog's title. 
    -   Set the function's `url` attribute to the Item Selector URL you obtained
        earlier. 
    -   Call the dialog's `open` method. 

The example code below and steps that follow show how to configure an Item
Selector dialog to work with selected items.

    <aui:button name="chooseImage" value="Choose" />

    <%
    String itemSelectorURL = GetterUtil.getString(request.getAttribute("itemSelectorURL"));
    %>

    <aui:script use="liferay-item-selector-dialog">

        $('#<portlet:namespace />chooseImage').on(
            'click', 
            function(event) {
                var itemSelectorDialog = new A.LiferayItemSelectorDialog(  
                    {
                        eventName: 'ItemSelectedEventName',
                        on: {
                                selectedItemChange: function(event) {
                                    var selectedItem = event.newVal;

                                    if (selectedItem) {
                                        var itemValue = JSON.parse(
                                        selectedItem.value
                                        );
                                        itemSrc = itemValue.url;
    
                                        <!-- use item as needed -->
                                    }
                                }
                        },
                        title: '<liferay-ui:message key="select-image" />',
                        url: '<%= itemSelectorURL.toString() %>'
                    }
                );
                itemSelectorDialog.open();
            }
        );
    </aui:script>

Here's what the code does: 

1.  This line creates a new instance of the Liferay Item Selector dialog: 

        var itemSelectorDialog = new A.LiferayItemSelectorDialog(...)

2.  The constructor sets the `eventName` attribute. This makes the dialog listen
    for the item selected event. The event name is the same as the one specified
    for the Item Selector URL generated earlier in the Java code.

3.  When the user selects a new item, the `selectedItemChange` function fires,
    setting its variables for the newly selected item. The information available
    to parse depends on the return type(s) that were set. 

4.  As the comment indicates, the developer adds logic for using the selected
    element here. 

5.  The `LiferayItemSelectorDialog`'s `title` attribute sets the dialog's title.

6.  The Item Selector URL retrieved previously in the Java code becomes the
    `url` attribute's value. 

7.  The `open` method opens the Item Selector dialog.

When the user clicks the *Choose* button, a new dialog opens, rendering the Item
Selector with the views that support the criterion and return type(s) that were
set.

Adding an Item Selector to your app is as easy as what's been demonstrated! It
involves specifying criteria for the selectable items, applying them to an Item
Selector, and configuring an Item Selector dialog to operate on the selected
item. Using Item Selector API, you can give app users the power of choice!

## Related Articles [](id=related-articles)

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-0/asset-framework)

[Frontend Building Blocks](/develop/tutorials/-/knowledge_base/7-0/frontend-building-blocks)
