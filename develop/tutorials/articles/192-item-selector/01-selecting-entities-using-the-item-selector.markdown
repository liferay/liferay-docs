# Selecting Entities Using the Item Selector API

The Item Selector allows users to quickly and easily select entities. Images,
videos, documents, and sites are just some of the things Item Selectors operate
on. The Item Selector API lets developers use Item Selectors in their own
applications.

![Figure 1: The Item Selector makes selecting entities a breeze.](../../images/item-selector-dialog-01.png)

Here's what's required to set up an Item Selector:

1.  **Determining Item Criteria**

2.  **Getting an Item Selector for the Criteria**

3.  **Using an Item Selector Dialog**

Let's get started. 

## Determining Item Selector Criteria

The first step is determining entity types to select from the Item Selector and
the data you expect from them. What kind of entity do you want to select? Do you
want to select a user, an image, a video, etc?

Once you know the entities you want, you need *criterion* classes to represent
them in the Item Selector. Criterion classes must implement the [`ItemSelectorCriterion` interface](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html). 

@product@ bundles come with app suites (e.g., the Collaboration and Web
Experience app suites) that have criterion classes developers can use.

**Collaboration App Suite Modules:**

- [`com.liferay.item.selector.criteria.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/):

    -   [ImageItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html):
        Image file entity type.

    -   [AudioItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/audio/criterion/AudioItemSelectorCriterion.html):
        Audio file entity type.

    -   [FileItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/file/criterion/FileItemSelectorCriterion.html):
        Document Library file entity type.

    -   [UploadItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html):
        Uploadable file entity type.

    -   [URLItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html):
        URL entity type.

    -   [VideoItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/video/criterion/VideoItemSelectorCriterion.html):
        Video file entity type.

-   [`com.liferay.wiki.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/wiki/com.liferay.wiki.api/) has wiki criterion.
    
**Web Experience App Suite Modules:**

-   [`com.liferay.site.item.selector.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/site/com.liferay.site.item.selector.api/) has site criterion.    

-   [`com.liferay.layout.item.selector.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/layout/com.liferay.layout.item.selector.api/) has layout criterion.

-   [`com.liferay.journal.item.selector.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/journal/com.liferay.journal.item.selector.api/) has web content criterion.

If there's no criterion class to represent your entity, you can implement your
own [`ItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html).

Then determine the type of information (return type) you expect from the
entities when users select them. Do you expect a URL? A Universally Unique
Identifier (UUID)? A primary key? etc. Each return type must be represented by
an implementation of the [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html).

The Liferay Collaboration app suite's [`com.liferay.item.selector.criteria.api` module](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/)
includes the following return types:

-   [Base64ItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/Base64ItemSelectorReturnType.html):
    Base64 encoding of the entity as a `String`.
   
-   [FileEntryItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html):
    File entry information as a JSON object.

-   [URLItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html):
    URL of the entity as a `String`.

-   [UUIDItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.html):
    Universally Unique Identifier (UUID) of the entity as a `String`.

If there's no return type classes that meets your needs, you can implement your
own [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html).

+$$$

**Note**: Each criterion must have at least one `ItemSelectorReturnType` (return
type) associated with it.

$$$

If you want to allow users to select an image and want the image's URL returned
with it, for example, you could use the `ImageItemSelectorCriterion` criterion
class and the `URLItemSelectorReturnType` return type classes.

The criterion and return types are collectively referred to as the Item
Selector's *criteria*. The Item Selector uses it to decide which selection views
(tabs of items) to show.

Once you've defined your criteria, you can get an Item Selector to use with it.

## Getting an Item Selector for the Criteria

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

The following code demonstates getting a URL to an Item Selector configured with
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

An Item Selector can be configured to use any number of criterion, that can in turn use any
number of return types.

The order of the Item Selector criterion decides the order of the selections
views. For example, if you pass the item selector criteria in this order:
`ImageItemSelectorCriterion`, `VideoItemSelectorCriterion`, the Item Selector
displays first the selection views for images and then the ones for videos. But
if the order is reversed, the video selection views are shown first followed by
the image selection views.

Return type order is also significant. Each view uses the first return type it
supports from each criterion's list of return types.

Now that you've provided a means to get a URL to the Item Selector you've
configured, you can start using the Item Selector in your UI. 

## Using the Item Selector Dialog

To open the Item Selector in your UI, you must use the
`LiferayItemSelectorDialog` JavaScript component from [AlloyUI's](http://alloyui.com/)
`liferay-item-selector-dialog` module. The component listens for the item
selected event that you specified for the Item Selector URL. The event returns
the information for the selected element according to its return type.

Here are the steps for using the Item Selector dialog in a JSP:

1.  Add a directive to use the AUI tag library. 

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  Add the `<aui:script>` tag and configure it to use the
    `liferay-item-selector-dialog` module: 
    
        <aui:script use="liferay-item-selector-dialog">
        
        </aui:script>
        
3.  Attach an event handler to the UI element you want to use to open the Item 
    Selector dialog. 

    For example, the configuration below creates a *Choose* button with the ID
    `chooseImage`. Then a click event and function are attached to it. When the
    user clicks the button the function is executed.
    
        <aui:button name="chooseImage" value="Choose" />
    
        <aui:script use="liferay-item-selector-dialog">

            $('#<portlet:namespace />chooseImage').on(
            'click',
              function(event) {
                <!-- function logic goes here -->
              }
            );

        </aui:script>
        
5.  Inside the function, you have to create a new instance of the
    `LiferayItemSelectorDialog` AlloyUI Component and configure it to use the
    Item Selector. Here's what you need to do in the function:

    -   Set the function's `eventName` attribute to the item selected even name
        you specified in your Java code (the code that gets the Item Selector
        URL).
    -   For the `on` attribute, implement a function that operates on the
        selected item change. The example code that follows these steps
        demonstrates this.
    -   Set the function's `title` attribute to a title you want to apply to the
        dialog. 
    -   Set the function's `url` attribute to the Item Selector URL you obtained
        in the last section. 
    -   Open the dialog by calling its `open` method.

Here is an example Item Selector dialog configuration:

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

Here's what happens in the example code above:

1.  A new instance of the Liferay Item Selector dialog is created with this
    line: 

        var itemSelectorDialog = new A.LiferayItemSelectorDialog(...)

2.  The `eventName` attribute is set for the dialog to listen for the item
    selected event. The event name is the same as the one specified for the Item
    Selector URL that generated in the Java code from the previous section.

3.  When the user selects a new item, the variables within the
    `selectedItemChange` function of the `on` attribute are updated and set for
    the newly selected item. The information available to parse depends on the
    return type(s) that were set. 

4.  As the comment indicates, this is where the developer adds logic for using
    the selected elment. 

5.  The `LiferayItemSelectorDialog`'s `title` attribute sets the dialog's title.

6.  The `url` attribute is set to the Item Selector URL specified in the Java
    code in the previous section. 

7.  The Item Selector dialog is opened by calling its `open` method.

When the user clicks the *Choose* button, in this example, a new dialog opens,
rendering the Item Selector with the views that support the criterion and return
type(s) that were set.

Adding an Item Selector to your app is as easy as what's been demonstrated! It
involves specifying criteria for the selectable items, applying it to an Item
Selector, and configuring an Item Selector dialog to operate on the selected
item. Using Item Selector API, you can give app users the power of choice!

## Related Articles

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-0/asset-framework)

[Frontend Building Blocks](/develop/tutorials/-/knowledge_base/7-0/frontend-building-blocks)
