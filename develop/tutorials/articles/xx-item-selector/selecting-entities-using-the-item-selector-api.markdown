# Selecting Entities Using the Item Selector API [](id=selecting-entities-using-the-item-selector-api)

The Item Selector allows users to select any kind of entity in a very consistent 
manner. It is used throughout Liferay Portal to allow users to quickly and 
easily select images, users, videos etc. to include in their web content, or 
complete an action. 

![Figure 1: The Item Selector makes selecting entities a breeze.](../../images/item-selector-dialog-01.png)

You can use this framework and interface in your app by utilizing the Item 
Selector's APIs.

This tutorial demonstrates the following steps for using the Item Selector's 
APIs:

-  Specifying the elements and return types for the Item Selector.
-  Obtaining the Item Selector URL using the corresponding criterion and return 
   type classes.
-  Using the obtained URL to open the Item Selector Dialog.

Go ahead and get started

## Determining Entities and Return Types [](id=determining-entities-and-return-types)

The Item Selector operates by selecting an entity i.e. an image, a video, a user 
etc. and then returning information for that entity i.e. a URL, a 
UUID(Universally Unique Identifier), a primary key etc.

The Item Selector refers to the entities and information as elements and return 
types respectively.

There are two class implementations that you must use in order to specify these 
in the Item Selector's API: `ItemSelectorCriterion`, which specifies the kind of 
element you want to select, and `ItemSelectorReturnType`, which specifies the 
type of information returned when the element is selected.

Each element type corresponds to a matching implementation of 
`ItemSelectorCriterion`. The following criteria is included by the item selector 
api module:

-  [ImageItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.java)
-  [AudioItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/audio/criterion/AudioItemSelectorCriterion.java)
-  [FileItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/file/criterion/FileItemSelectorCriterion.java)
-  [UploadItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.java)
-  [URLItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.java)
-  [VideoItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/video/criterion/VideoItemSelectorCriterion.java)

Each return type corresponds to a matching implementation of 
`ItemSelectorReturnType`. The following return types are included by the item 
selector api module:

-  [FileEntryItemSelectorReturnType](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.java)
-  [URLItemSelectorReturnType](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/URLItemSelectorReturnType.java)
-  [UUIDItemSelectorReturnType](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/item-selector/item-selector-criteria-api/src/main/java/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.java)

These are the default element types and return types available to the Item 
Selector. However, applications can create their own criteria and return types 
to select new types of entities. This is covered in more detail in the 
[Creating Custom Elements and Return Types](/develop/tutorials/-/knowledge_base/7-0/creating-custom-elements-and-return-types) 
tutorial.

Once you know the kind of element you want to select and the return type you
want for the selected element, you can use the corresponding classes for the 
item selector.

For example, imagine that you want to select images and you expect the item 
selector to return the URL of the selected image, then you can use the following 
classes (both of them in the `item-selector-criteria-api` module):

    com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion

    com.liferay.item.selector.criteria.URLItemSelectorReturnType

The Item Selector uses this information to automatically decide which selection 
views should be displayed based on the criteria and the return types. For 
example, if you use `ImageItemSelectorCriterion`, it will display the views that 
allow image selection, while if you use `VideoItemSelectorCriterion`, it will 
display the views that allow video selection.

Now that you know how to specify the kind of element and return type that you 
want to use for the Item Selector, you can learn how to use that information to 
create a URL to open the Item Selector Dialog next.

## Obtaining the URL for the Item Selector Views [](id=obtaining-the-url-for-the-item-selector-views)

In order to use the Item Selector you'll need to obtain a URL to invoke it. This 
URL is used to provide the proper views for the selected entity.

The URL is generated by the Item Selector OSGi Component, so you'll need to 
import the Item Selector OSGi Component into your class to use it.

1.  Import the Item Selector OSGi Component into your class using the following
    code:

        import com.liferay.item.selector.ItemSelector;
    
        @Reference(unbind = "-")
    
        public void setItemSelector(ItemSelector itemSelector) {
    
          _itemSelector = itemSelector;
    
        }
    
        private ItemSelector _itemSelector;

Once you've added the import above into your class, you can retrieve the URL 
next.

The URL to the item selector is obtained by invoking the method 
`getItemSelectorURL` with the following parameters:

- `RequestBackedPortletURLFactory`: factory to create portlet URLs. It
    can be created by invoking with either `HttpServletRequest` or
    `PortletRequest`. Use the following pattern to create your URL:
    `RequestBackedPortletURLFactoryUtil.create(request)`.
- `ItemSelectedEventName`: a unique JavaScript event name that is
    triggered by the item selector when the element is selected.
- `ItemSelectorCriterion`: the criterion (or array of criteria) that
    specifies the element that should be selected.
    
Follow the pattern below to obtain the URL for the Item Selector:

    List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =  new
    ArrayList<>();
    
    desiredItemSelectorReturnTypes.add(new *ItemSelectorReturnType());
    
    *ItemSelectorCriterion class *nameItemSelectorCriterion =   new
    *ItemSelectorCriterion();
    
    *nameItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
    desiredItemSelectorReturnTypes);
    
    PortletURL itemSelectorURL = `_itemSelector.getItemSelectorURL`(
    
      requestBackedPortletURLFactory, "ItemSelectedEventName",
    
      *ItemSelectorCriterion);

For example, the following configuration obtains the item selector URL for the
`ImageItemSelectorCriterion`:

    List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =  new
    ArrayList<>();
    
    desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());
    
    ImageItemSelectorCriterion imageItemSelectorCriterion =   new
    ImageItemSelectorCriterion();
    
    imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
    desiredItemSelectorReturnTypes);
    
    PortletURL itemSelectorURL = `_itemSelector.getItemSelectorURL`(
    
      requestBackedPortletURLFactory, "sampleTestSelectItem",
    
      imageItemSelectorCriterion);

The return types are declared in an `ArrayList`. The `URLItemSelectorReturnType`
is added to the list of desired return types, and the 
`imageItemSelectorCriterion` is set as the kind of element to select. The 
`ImageItemSelectorCriterion()` constructor is referenced so it can be used. The 
return types are then set to the item selector criterion using the setter 
`setDesiredItemSelectorReturnTypes()`. Finally, the item selector URL is created
using the `getItemSelectorURL()` method, with the value of the JavaScript item 
selected event name set to `sampleTestSelectItem`.

The order of the item selector criteria is important because it decides the 
order of the selection views. If you are using one item selector criteria, then 
this is not an issue for you, but for multiple types of criteria listed in an 
array this can be an issue. For example, if you pass the item selector criteria 
in this order: `ImageItemSelectorCriterion`, `VideoItemSelectorCriterion` it 
will display first the selection views for images and then for videos, but if 
you reverse the item selector criteria it will display first the video selection 
view and then the image selection view.

The order also determines the priority for return types. The selection view will 
use the first return type that it can return.

Once you've written the code to obtain your Item Selector URL, you can use it to 
open the dialog for the Item Selector next.

## Opening the Item Selector Dialog [](id=opening-the-item-selector-dialog)

The Item Selector Dialog JavaScript component allows you to trigger the Item 
Selector's UI using the URL you created in the last section.

This JavaScript component will open the dialog with the item selector and listen 
to the JavaScript event name, the URL value that you set in the last section, to 
obtain the information about the selected element.

Follow these steps to open the Item Selector Dialog:

1.  Create a JSP in your app's `src/main/resources/META-INF/resources` folder to
    hold the AlloyUI script for the Item Selector dialog.
    
    You'll need to decide how you want to open the Item Selector dialog. For
    simplicity, imagine that you want to open the item selector to select images 
    when clicking a *Choose* button.
    
    The Item Selector Dialog is an Alloy UI component, so in order to use it
    you'll need to add the AUI directive to your JSP.
    
2.  Add the following AUI taglib directive to the top of your JSP:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
        
    Now that the AUI directive is added to your JSP, you can add the AUI scripts
    next.
        
3.  Add the AUI script for your Item Selector trigger.

    For example, you can use the following script to add a trigger button:
    
            <aui:button name="name" value="value" />
            
4.  Use the following pattern to attach a click event to the trigger you just 
    created, to open the Item Selector dialog.

        <aui:script use="liferay-item-selector-dialog">
        
        $('#<portlet:namespace />triggerID').on(
        
          'click',
        
          function(event) {
        
             var itemSelectorDialog = new A.LiferayItemSelectorDialog(
        
                {
        
                   eventName: 'itemSelectedEventName',
        
                   on: {
        
                      selectedItemChange: function(event) {
        
                         var selectedItem = event.newVal;
        
        var itemValue = JSON.parse(selectedItem.value);
        
        itemSrc =  itemValue.url;
        
        [use itemSrc as needed]
        
                         }
        
                      }
        
                   },
        
                   title: '<liferay-ui:message key="language-message-key" />',
        
                   url: '<%= itemSelectorURL.toString() %>'
        
                }
        
             );
        
             itemSelectorDialog.open();
        
          }
        
        );
        
        </aui:script>
    
    For example, using the Java code example in the last section, the logic 
    would be as follows:
    
        <aui:button name="chooseImage" value="Choose" />
    
        <aui:script use="liferay-item-selector-dialog">
        
        $('#<portlet:namespace />chooseImage').on(
        
          'click',
        
          function(event) {
        
             var itemSelectorDialog = new A.LiferayItemSelectorDialog(
        
                {
        
                   eventName: 'sampleTestSelectItem',
        
                   on: {
        
                      selectedItemChange: function(event) {
        
                         var selectedItem = event.newVal;
        
        var itemValue = JSON.parse(selectedItem.value);
        
        itemSrc =  itemValue.url;
        
        <!-- What does the `itemSrc` attribute do? I take it specifies the newly
        selected item? -->
        
        [use itemSrc as needed]
        
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

When the user clicks the *Choose* button, a new dialog will pop up, rendering 
the Item Selector. The `<aui:script>` `use` attribute is set to use the 
`liferay-item-selector-dialog` module. The trigger is referenced by its ID, 
using the `$()` selector. Note that the `<portlet:namespace />` tag is prefixed 
to the ID to avoid name conflicts with other elements on the page. Next, the 
Item Selector dialog is configured. This item selector renders the views that 
meet the `ImageItemSelectorCriterion` and returns a URL when the image is 
selected. Note that the JavaScript event name you created in the last section is 
used as the `eventName` attribute value and the `PortletURL` you obtained in the 
last section is used as the `url` attribute value.

Now you know how to use the Item Selector's APIs to select entities!

## Related Topics [](id=related-topics)
