---
header-id: selecting-entities-with-an-item-selector
---

# Selecting Entities with an Item Selector

[TOC levels=1-4]

The steps here show you how to get and use an Item Selector to select entities 
in your app. For an explanation of the Item Selector API and more information on 
these steps, see the [Item Selector introduction](/developer/frameworks/-/knowledge_base/7-2/item-selector). 

## Get an Item Selector

First, you must get an Item Selector for your use case. Follow these steps: 

1.  Determine the criterion and return types for the Item Selector. The 
    criterion corresponds to the selected entity type, and the return types 
    correspond to the data you expect to receive from those selections. For a 
    list of the criterion and return types that @product@ provides, see 
    [Item Selector Criterion and Return Types](/developer/reference/-/knowledge_base/7-2/item-selector-criterion-and-return-types).
    For example, if you need an Item Selector that selects images and returns
    their URLs, use `ImageItemSelectorCriterion` and
    `URLItemSelectorReturnType`. You can
    [create](/developer/frameworks/-/knowledge_base/7-2/creating-custom-criterion-and-return-types)
    criterion and/or return types if there aren't existing ones for your use
    case. 

2.  Use Declarative Services to get an `ItemSelector` OSGi Service Component: 

    ```java

        import com.liferay.item.selector.ItemSelector;
        import org.osgi.service.component.annotations.Reference;

        ...

        @Reference
        private ItemSelector _itemSelector

    ```

    The component annotations are available in the module 
    [`org.osgi.service.component.annotations`](http://mvnrepository.com/artifact/org.osgi/org.osgi.service.component.annotations). 

3.  Create the factory you'll use to create the Item Selector's URL. To do this, 
    invoke the `RequestBackedPortletURLFactoryUtil.create` method with the 
    current request object. The request can be an `HttpServletRequest` or 
    `PortletRequest`: 

    ```java

        RequestBackedPortletURLFactory requestBackedPortletURLFactory =
            RequestBackedPortletURLFactoryUtil.create(request);

    ```

4.  Create a list of return types expected for the entity. For example, the 
    return types list here consists of `URLItemSelectorReturnType`: 

    ```java

        List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
            new ArrayList<>();
        desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());

    ```

5.  Create an object for the criterion. This example creates a new 
    `ImageItemSelectorCriterion`: 

    ```java

        ImageItemSelectorCriterion imageItemSelectorCriterion =
            new ImageItemSelectorCriterion();

    ```

6.  Use the criterion's `setDesiredItemSelectorReturnTypes` method to set the 
    return types list to the criterion: 

    ```java

        imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
            desiredItemSelectorReturnTypes);

    ```

7.  Call the Item Selector's `getItemSelectorURL` method to get an Item Selector 
    URL for the criterion. The method requires the URL factory, an arbitrary 
    event name, and a series of criterion instances (one, in this case): 

    ```java

        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
            requestBackedPortletURLFactory, "sampleTestSelectItem",
            imageItemSelectorCriterion);

    ```

## Using the Item Selector Dialog

To open the Item Selector in your UI, you must use the JavaScript component 
`LiferayItemSelectorDialog` from 
[AlloyUI's](http://alloyui.com) 
`liferay-item-selector-dialog` module. The component listens for the item 
selected event that you specified for the Item Selector URL. The event returns 
the selected element's information according to its return type. 

Follow these steps to use the Item Selector's dialog in a JSP: 

1.  Declare the AUI tag library: 

    ```java

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    ```

2.  Define the UI element you'll use to open the Item Selector dialog. For 
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
    created in step two. For example, this attaches a click event and a function 
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

5.  Create the function's logic. First, create a new instance of the 
    Liferay Item Selector dialog: 

        var itemSelectorDialog = new A.LiferayItemSelectorDialog(  
            {
                ...
            }
        );

6.  Inside the braces of the `LiferayItemSelectorDialog` constructor, first set 
    set the `eventName` attribute. This makes the dialog listen for the item 
    selected event. The event name is the Item Selector's event name that 
    you specified in your Java code (the code that gets the Item Selector URL): 

        eventName: 'ItemSelectedEventName',

7.  Immediately after the `eventName` setting, set the `on` attribute to 
    implement a function that operates on the selected item change. For example, 
    this function sets its variables for the newly selected item. The 
    information available to parse depends on the return type(s). As the comment
    below indicates, you must add the logic for using the selected element: 

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

10. To conclude the logic of the function from step four, open the Item Selector 
    dialog by calling its `open` method: 

        itemSelectorDialog.open();

When the user clicks the *Choose* button, a new dialog opens, rendering the Item
Selector with the views that support the criterion and return type(s). 

Here's the complete example code for these steps: 

```java

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

```

## Related Topics

[Item Selector](/developer/frameworks/-/knowledge_base/7-2/item-selector)

[Creating Custom Criterion and Return Types](/developer/frameworks/-/knowledge_base/7-2/creating-custom-criterion-and-return-types)

[Creating Custom Item Selector Views](/developer/frameworks/-/knowledge_base/7-2/creating-custom-item-selector-views)
