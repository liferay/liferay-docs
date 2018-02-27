# Selecting Entities Using an Item Selector [](id=selecting-entities-using-the-item-selector)

An Item Selector lets users select entities such as images, videos, documents, 
and sites. You can use an Item Selector in your app to let users select such 
entities. This tutorial shows you how to do this via these steps: 

1.  **Determine the Criteria for an Item Selector:** You must first define which 
    entities an Item Selector can let users select. 

2.  **Get an Item Selector for Your Criteria:** If your criteria is for images, 
    for example, then in this step you'll get an Item Selector capable of 
    selecting images. 

3.  **Use an Item Selector Dialog:** Display the Item Selector in your UI. 

![Figure 1: An Item Selector makes selecting entities a breeze.](../../../images/item-selector-dialog-01.png)

## Determining Item Criteria [](id=determining-item-selector-criteria)

The first step is determining entity types to select from the Item Selector and
the data you expect from them. What kind of entity do you want to select? Do you
want to select a user, an image, a video, or something else?

Once you know the entities you want, you need *criterion* classes to represent
them in the Item Selector. Criterion classes must implement the 
[`ItemSelectorCriterion` interface](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html). 
The 
[Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-1/item-selector-criterion-and-return-types)
reference lists criterion classes Liferay's apps and app suites provide. 

If there's no criterion class for your entity, you can create your own 
[`ItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html). 

Then determine the type of information (return type) you expect from the
entities when users select them. Do you expect a URL? A Universally Unique
Identifier (UUID)? A primary key? Each return type must be represented by
an implementation of the 
[`ItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html).
The 
[Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-1/item-selector-criterion-and-return-types)
reference also lists return type classes @product@'s apps and app suites 
provide. 

If there's no return type class that meets your needs, you can implement your
own 
[`ItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html). 

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

To use an Item Selector with your criteria, you must get that Item Selector's 
URL. The URL is needed to open the Item Selector dialog in your UI. To get this 
URL, you must get an `ItemSelector` reference and call its 
[`getItemSelectorURL` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) 
with the following parameters: 

-   `RequestBackedPortletURLFactory`: A factory that creates portlet URLs. 
-   `ItemSelectedEventName`: A unique, arbitrary JavaScript event name that the 
    Item Selector triggers when the element is selected. 
-   `ItemSelectorCriterion`: The criterion (or an array of criterion objects) 
    that specifies the type of elements to make available in the Item Selector. 

The following code demonstrates getting a URL to an Item Selector configured 
with criteria for images:

1.  Use Declarative Services to get an `ItemSelector` OSGi Service Component: 

        import com.liferay.item.selector.ItemSelector;
        import org.osgi.service.component.annotations.Reference;

        ...

        @Reference
        private ItemSelector _itemSelector

    The component annotations are available in the 
    [`org.osgi.service.component.annotations` module](http://mvnrepository.com/artifact/org.osgi/org.osgi.service.component.annotations). 

2.  Create the factory you'll use to create the URL. To do this, invoke the 
    `RequestBackedPortletURLFactoryUtil.create` method with the current request 
    object. The request can be an `HttpServletRequest` or `PortletRequest`: 

        RequestBackedPortletURLFactory requestBackedPortletURLFactory =
            RequestBackedPortletURLFactoryUtil.create(request);

3.  Create a list of return types expected for the image entity. The return 
    types list consists of a URL return type `URLItemSelectorReturnType`: 

        List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
            new ArrayList<>();
        desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());

4.  Create a criterion object for images (`ImageItemSelectorCriterion`): 

        ImageItemSelectorCriterion imageItemSelectorCriterion =
            new ImageItemSelectorCriterion();

5.  Use the criterion's `setDesiredItemSelectorReturnTypes` method to set the 
    return types list from step 3 to the criterion: 

        imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
            desiredItemSelectorReturnTypes);

6.  Call the Item Selector's `getItemSelectorURL` method to get an Item Selector 
    URL based on the criterion. The method requires the URL factory, an 
    arbitrary event name, and a series of criterion (one, in this case): 

        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
            requestBackedPortletURLFactory, "sampleTestSelectItem",
            imageItemSelectorCriterion);

There are a few things to keep in mind when getting an Item Selector's URL: 

-   You can invoke the URL object's `toString` method to get its value.

-   You can configure an Item Selector to use any number of criterion. The 
    criterion can use any number of return types. 

-   The order of the Item Selector's criteria determines the selection view 
    order. For example, if you pass the Item Selector an 
    `ImageItemSelectorCriterion` followed by a `VideoItemSelectorCriterion`, the 
    Item Selector displays the image selection views first. 

-   The return type order is also significant. A view uses the first return type 
    it supports from each criterion's return type list. 

Now that you've got a URL to an Item Selector, you can start using that Item 
Selector in your UI. 

## Using the Item Selector Dialog [](id=using-the-item-selector-dialog)

To open the Item Selector in your UI, you must use the JavaScript component 
`LiferayItemSelectorDialog` from 
[AlloyUI's](http://alloyui.com/) 
`liferay-item-selector-dialog` module. The component listens for the item 
selected event that you specified for the Item Selector URL. The event returns
the selected element's information according to its return type.

Here are the steps for using the Item Selector dialog in a JSP:

1.  Declare the AUI tag library: 

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  Define the UI element that you'll use to open the Item Selector dialog. For 
    example, this creates a *Choose* button with the ID `chooseImage`:

        <aui:button name="chooseImage" value="Choose" />

3.  Get the Item Selector's URL: 

        <%
        String itemSelectorURL = GetterUtil.getString(request.getAttribute("itemSelectorURL"));
        %>

3.  Add the `<aui:script>` tag and set it to use the 
    `liferay-item-selector-dialog` module: 

        <aui:script use="liferay-item-selector-dialog">

        </aui:script>

4.  Inside the `<aui:script>` tag, attach an event handler to the UI element you 
    created in step 2. For example, this attaches a click event and a function 
    to the *Choose* button: 

        <aui:script use="liferay-item-selector-dialog">

            $('#<portlet:namespace />chooseImage').on(
            'click',
              function(event) {
                <!-- function logic goes here -->
              }
            );

        </aui:script>

    Inside the function, you must create a new instance of the 
    `LiferayItemSelectorDialog` AlloyUI component and configure it to use the 
    Item Selector. The next steps walk you through this. 

5.  Now you must create the function logic. First, create a new instance of the 
    Liferay Item Selector dialog: 

        var itemSelectorDialog = new A.LiferayItemSelectorDialog(  
            {
                ...
            }
        );

6.  Inside the braces of the `LiferayItemSelectorDialog` constructor, first set 
    set the `eventName` attribute. This makes the dialog listen for the item 
    selected event. The event name is the the Item Selector's event name that 
    you specified in your Java code (the code that gets the Item Selector URL): 

        eventName: 'ItemSelectedEventName',

7.  Immediately after the `eventName` setting, set the `on` attribute to 
    implement a function that operates on the selected item change. For example, 
    this function sets its variables for the newly selected item. The 
    information available to parse depends on the return type(s) that were set. 
    As the comment indicates, you must add the logic for using the selected 
    element: 

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

8.  Immediately after the `on` setting, set the `title` attribute to the 
    dialog's title: 

        title: '<liferay-ui:message key="select-image" />',

9.  Immediately after the `title` setting, set the `url` attribute to the 
    previously retrieved Item Selector URL. This concludes the attribute 
    settings inside the `LiferayItemSelectorDialog` constructor: 

        url: '<%= itemSelectorURL.toString() %>'

10. To conclude the logic of the function from step 4, open the Item Selector 
    dialog by calling its `open` method: 

        itemSelectorDialog.open();

Here's the complete example code for these steps: 

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

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

When the user clicks the *Choose* button, a new dialog opens, rendering the Item
Selector with the views that support the criterion and return type(s) that were
set. 

Great! Now you know how to select entities using an Item Selector. Using the 
Item Selector API, you can give your app's users the power of choice!

## Related Articles [](id=related-articles)

[Understanding the Item Selector API's Components](/develop/tutorials/-/knowledge_base/7-1/understanding-the-item-selector-apis-components)

[Creating Custom Item Selector Views](/develop/tutorials/-/knowledge_base/7-1/creating-custom-item-selector-views)

[Creating Custom Item Selector Entities](/develop/tutorials/-/knowledge_base/7-1/creating-custom-item-selector-entities)

[Front-End Taglibs](/develop/tutorials/-/knowledge_base/7-1/front-end-taglibs)
