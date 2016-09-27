# Selecting Entities Using the Item Selector API [](id=selecting-entities-using-the-item-selector-api)

The Item Selector is used throughout @product@, allowing users to quickly and
easily select images, video, etc. to include in their web content.

![Figure 1: The Item Selector makes selecting entities a breeze.](../../images/item-selector-dialog-01.png)

The Item Selector's APIs make it possible for you to have this same framework in 
your own application.

This tutorial covers these key topics:

-  The default criteria for the Item Selector
-  How to Configure the Item Selector
-  How to use the Item Selector Dialog

Go ahead and get started.

## Determining Item Selector Criteria [](id=determining-item-selector-criteria)

To use the Item Selector, you must first decide the type of element you would
like to select (e.g. an image, a video, a user, etc.). The answer to this
question determines the implementation of the [`ItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html) 
you must use. The criterion communicates key information about the type of
element the Item Selector will be selecting.

Next, you must decide what information you expect to be returned when the
element is selected. The answer to this question determines the implementation
of the [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html) 
you must use. The return type defines key information about the data that should
be returned when the element is selected.

![Figure 2: Item Selector criteria consist of criterion and return types.](../../images/item-selector-criteria.png)

A criterion can have multiple return types or just one. The number of return 
types depends on the type of information you wish to return to the user when the 
element is selected.

Once you know the kind of element you want to select and the return type(s) you
want for the selected element, you can use the corresponding classes for the 
Item Selector.

For example, if you would like to allow users to select images within your
application and you need the URL and the ID of the image file returned, you 
would use the `ImageItemSelectorCriterion`, `FileEntryItemSelectorReturnType`, 
and `URLItemSelectorReturnType` classes.

The criterion specifies the return type(s) it would like to use via its 
[`getDesiredItemSelectorReturnTypes()` method](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html#getDesiredItemSelectorReturnTypes--). 
More on this in a bit.

Each element type corresponds to a matching implementation of 
`ItemSelectorCriterion`. The following criteria is included by the Item Selector 
api module:

-  [ImageItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html)
   Specifies an image file entity type.
   
-  [AudioItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/audio/criterion/AudioItemSelectorCriterion.html)
   Specifies an audio file entity type.
   
-  [FileItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/file/criterion/FileItemSelectorCriterion.html)
   Specifies a Document Library file entity type.
   
-  [UploadItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html)
   Specifies an uploadable file entity type.
   
-  [URLItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html)
   Specifies a URL entity type.
   
-  [VideoItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/video/criterion/VideoItemSelectorCriterion.html)
   Specifies a video file entity type.

Each return type corresponds to a matching implementation of 
`ItemSelectorReturnType`. The following return types are included by the Item 
Selector api module:

-  [Base64ItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/Base64ItemSelectorReturnType.html)
   Returns the Base64 encoding of the entity as a `String`.
   
-  [FileEntryItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html)
   Returns the following information of a file entry as a JSON object:
   
   -  `fileEntryId`: The ID of the selected file entry.
   -  `groupId`: The group ID of the selected file entry.
   -  `title`: The title of the selected file entry.
   -  `url`: The URL of the selected FileEntry.
   -  `uuid`: The UUID of the selected file entry.

-  [URLItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html)
   Returns the URL of the entity as a `String`.

-  [UUIDItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.html)
   Returns the Universally Unique Identifier (UUID) of the entity as a `String`.

The criterion and return types that you choose collectively form the criteria
for the Item Selector. This information determines the selection views the Item
Selector renders.
   
Next, you'll learn how to configure the Item Selector to use the criteria you've
chosen.

## Configuring an Item Selector with the Criteria[](id=configuring-an-item-selector-with-the-criteria)

In this section, you'll use the criteria(criterion and return types) that you've
selected, to configure the Item Selector. Specifically, the criteria is used
to create a unique Item Selector URL that opens the Item Selector dialog.

The URL to the Item Selector is obtained by calling the 
[`getItemSelectorURL()` method](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) 
with the following parameters:
    
-  `RequestBackedPortletURLFactory`: factory to create portlet URLs. It can be 
   created by invoking with either `HttpServletRequest` or `PortletRequest`. Use 
   the following pattern to create your URL: 
   `RequestBackedPortletURLFactoryUtil.create(request)`.

-  `ItemSelectedEventName`: a unique JavaScript event name that is triggered by 
   the Item Selector when the element is selected.

-  `ItemSelectorCriterion`: the criterion (or array of criteria) that specifies 
   the element that should be selected.

Follow these steps to obtain the Item Selector URL:

1.  The URL is generated by the Item Selector OSGi Component, so you'll need to 
    import the Item Selector OSGi Component into your Component class to use it.

    Add the following import:

        import com.liferay.item.selector.ItemSelector;
        
2.  Reference the `ItemSelector` service so you can have access to your own 
    instance of the Item Selector.

        @Reference(unbind = "-")
    
        public void setItemSelector(ItemSelector itemSelector) {
    
            _itemSelector = itemSelector;
    
        }
        
        private ItemSelector _itemSelector
        
3.  Create a method to populate an Item Selector, with the criteria you've 
    chosen, that returns the URL for the Item Selector.
    
    The name of this method is arbitrary, but it should return the URL for the
    Item Selector as a String.
    
    For example, the [com.liferay.blogs.web module](http://mvnrepository.com/artifact/com.liferay/com.liferay.blogs.web)
    uses its `getItemSelectorURL()` method to populate an Item Selector with its 
    criteria:

        public String getItemSelectorURL(
                RequestBackedPortletURLFactory requestBackedPortletURLFactory,
                String itemSelectedEventName, ItemSelectorCriterion
                itemSelectorCriterion) {
        
        }
        
4.  Within the body of the method you just added, create an array list to hold 
    the return types for your criterion and configure the criterion to use it, 
    using the criterion's `setDesiredItemSelectorReturnTypes()` method.
    
    For example, the [com.liferay.blogs.web module](http://mvnrepository.com/artifact/com.liferay/com.liferay.blogs.web)
    has the following array lists in its [`BlogsItemSelectorHelper` class](https://github.com/liferay/liferay-portal/blob/d7f211c0cbb5374c41e1b8e4b1f4b024baae5bc6/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/BlogsItemSelectorHelper.java):
    
        List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
                new ArrayList<>();

        desiredItemSelectorReturnTypes.add(
                new FileEntryItemSelectorReturnType());

        BlogsItemSelectorCriterion blogsItemSelectorCriterion =
                new BlogsItemSelectorCriterion();

        blogsItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
                desiredItemSelectorReturnTypes);

        ImageItemSelectorCriterion imageItemSelectorCriterion =
                new ImageItemSelectorCriterion();

        imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
                desiredItemSelectorReturnTypes);
                
        List<ItemSelectorReturnType>
                        uploadCriterionDesiredItemSelectorReturnTypes = 
                        new ArrayList<>();

                uploadCriterionDesiredItemSelectorReturnTypes.add(
                        new FileEntryItemSelectorReturnType());

        ...


                UploadItemSelectorCriterion uploadItemSelectorCriterion =
                        new UploadItemSelectorCriterion(
                                uploadURL.toString(),
                                LanguageUtil.get(themeDisplay.getLocale(), 
                                "blog-images"));

                uploadItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
                        uploadCriterionDesiredItemSelectorReturnTypes);
                        
    The array lists above sets the desired returns types for the 
    `BlogsItemSelectorCriterion`, `ImageItemSelectorCriterion`, and 
    `UploadItemSelectorCriterion`, as well as sets some base configuration 
    options for the `UploadItemSelectorCriterion`. Each criterion will now 
    retrieve the desired return types you just set, when the 
    `getDesiredItemSelectorReturnTypes()` method is called.

5.  Set the value for the `itemSelectorURL` based on the criterion you just
    selected. For example, the [com.liferay.blogs.web module](http://mvnrepository.com/artifact/com.liferay/com.liferay.blogs.web) 
    has the following value for the `itemSelectorURL`, based on the criteria 
    shown in step 4:
    
        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
                requestBackedPortletURLFactory, itemSelectedEventName,
                blogsItemSelectorCriterion, imageItemSelectorCriterion,
                uploadItemSelectorCriterion);
    
    The Item Selector uses this information to determine the selection views 
    automatically. For example, if you specify the `ImageItemSelectorCriterion`, 
    it will display the image selection views, while if you use 
    `VideoItemSelectorCriterion`, it will display the selection views that 
    support video.
                
    **Important:** The order of the Item Selector criterion in the 
    `itemSelectorURL` is important because it decides the order of the selection 
    views (i.e. the order the selection view tabs will appear in the Item 
    Selector dialog). For example, in the configuration above, the selection 
    views for the `BlogsItemSelectorCriterion` will be displayed first, followed 
    by the selection views for the `ImageItemSelectorCriterion`, and finally the
    selection views for the `uploadItemSelectorCriterion`.
    
    The order of the desired return types in the array list also determines the 
    priority for the return types. The selection views will use the first
    available return type that is supported.

6.  Finally, return the Item Selector URL as a string:

        return itemSelectorURL.toString();
            
Here is the full [`BlogsItemSelectorHelper` class](https://github.com/liferay/liferay-portal/blob/d7f211c0cbb5374c41e1b8e4b1f4b024baae5bc6/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/BlogsItemSelectorHelper.java)
which demonstrates how to populate an Item Selector:

    package com.liferay.blogs.web.internal;
    
    import com.liferay.blogs.item.selector.criterion.BlogsItemSelectorCriterion;
    import com.liferay.blogs.web.constants.BlogsPortletKeys;
    import com.liferay.item.selector.ItemSelector;
    import com.liferay.item.selector.ItemSelectorReturnType;
    import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
    import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
    import com.liferay.item.selector.criteria.upload.criterion.UploadItemSelectorCriterion;
    import com.liferay.portal.kernel.language.LanguageUtil;
    import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    
    import java.util.ArrayList;
    import java.util.List;
    
    import javax.portlet.ActionRequest;
    import javax.portlet.PortletURL;
    
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    import org.osgi.service.component.annotations.ReferenceCardinality;
    import org.osgi.service.component.annotations.ReferencePolicy;
    import org.osgi.service.component.annotations.ReferencePolicyOption;


    @Component(service = BlogsItemSelectorHelper.class)
    public class BlogsItemSelectorHelper {
    
            public String getItemSelectorURL(
                    RequestBackedPortletURLFactory requestBackedPortletURLFactory,
                    ThemeDisplay themeDisplay, String itemSelectedEventName) {
    
                    if (_itemSelector == null) {
                            return null;
                    }
    
                    List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
                            new ArrayList<>();
    
                    desiredItemSelectorReturnTypes.add(
                            new FileEntryItemSelectorReturnType());
    
                    BlogsItemSelectorCriterion blogsItemSelectorCriterion =
                            new BlogsItemSelectorCriterion();
    
                    blogsItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
                            desiredItemSelectorReturnTypes);
    
                    ImageItemSelectorCriterion imageItemSelectorCriterion =
                            new ImageItemSelectorCriterion();
    
                    imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
                            desiredItemSelectorReturnTypes);
    
                    List<ItemSelectorReturnType>
                            uploadCriterionDesiredItemSelectorReturnTypes = new 
                            ArrayList<>();
    
                    uploadCriterionDesiredItemSelectorReturnTypes.add(
                            new FileEntryItemSelectorReturnType());
    
                    PortletURL uploadURL = 
                    requestBackedPortletURLFactory.createActionURL(
                            BlogsPortletKeys.BLOGS);
    
                    uploadURL.setParameter(
                            ActionRequest.ACTION_NAME, 
                            "/blogs/upload_cover_image");
    
                    UploadItemSelectorCriterion uploadItemSelectorCriterion =
                            new UploadItemSelectorCriterion(
                                    uploadURL.toString(),
                                    LanguageUtil.get(themeDisplay.getLocale(), 
                                    "blog-images"));
    
                    uploadItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
                            uploadCriterionDesiredItemSelectorReturnTypes);
    
                    PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
                            requestBackedPortletURLFactory, 
                            itemSelectedEventName, blogsItemSelectorCriterion, 
                            imageItemSelectorCriterion, 
                            uploadItemSelectorCriterion);
    
                    return itemSelectorURL.toString();
            }
    
            @Reference(
                    cardinality = ReferenceCardinality.OPTIONAL,
                    policy = ReferencePolicy.DYNAMIC,
                    policyOption = ReferencePolicyOption.GREEDY
            )
            public void setItemSelector(ItemSelector itemSelector) {
                    _itemSelector = itemSelector;
            }
    
            public void unsetItemSelector(ItemSelector itemSelector) {
                    _itemSelector = null;
            }
    
            private ItemSelector _itemSelector;
    
    }

Now that you've populated the Item Selector, and returned the Item Selector URL 
as a String, you can use it to open the Item Selector's dialog next.

## Using the Item Selector Dialog [](id=using-the-item-selector-dialog)

To open the Item Selector, you must use the `LiferayItemSelectorDialog` 
JavaScript component, an [AlloyUI](http://alloyui.com/) module, which allows you 
to invoke the Item Selector dialog using the URL you obtained in the last 
section.

The `LiferayItemSelectorDialog` JavaScript component opens the Item Selector 
dialog and listens to the JavaScript `itemSelectedEventName` that you 
configured in the Item Selector URL, to obtain the information about the 
selected element. Note that the JavaScript event returns the information for the 
selected element according to the return type(s) you configured for your 
criterion.

Follow these steps to open the Item Selector Dialog:

1.  Create a JSP in your app's `src/main/resources/META-INF/resources` folder to
    hold the AlloyUI script for the Item Selector dialog.
    
2.  The Item Selector Dialog is an Alloy UI component, so in order to use it
    you'll need to add the AUI directive to your JSP.
    
    Add the following AUI taglib directive to the top of your JSP:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

3.  Add the `<aui:script>` tag to your JSP and configure it to use the 
    `liferay-item-selector-dialog` module:
    
        <aui:script use="liferay-item-selector-dialog">
        
        </aui:script>
        
4.  Attach an event handler to the UI element you want to use to open the Item 
    Selector dialog. 
    
    For example, the configuration below creates a *Choose* button with the ID 
    `chooseImage` and then attaches a click event to it to open the Item 
    Selector dialog:
    
        <aui:button name="chooseImage" value="Choose" />
    
        <aui:script use="liferay-item-selector-dialog">
        
        $('#<portlet:namespace />chooseImage').on(
        
        'click',
        
          function(event) {
        
        
        
          }
        
        );
        
        </aui:script>
        
5.  Create a new instance of the `LiferayItemSelectorDialog` AlloyUI Component 
    and configure it to use the Item Selector URL and JavaScript event that you 
    configured.

    Add the configuration within the body of the `function(event){...}`.

    Below is a sample JSP configuration:

        <aui:button name="chooseImage" value="Choose" />

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
                                        var itemValue = JSON.parse(
                                        selectedItem.value
                                        );
                                        itemSrc = itemValue.url;
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

In the example above, a new instance of the Liferay Item Selector dialog is 
created with the `new A.LiferayItemSelectorDialog(...)` line and is set to the 
variable `itemSelectorDialog`. Next, the `eventName` attribute is set for the 
dialog. This will listen for the event name specified when an item is selected. 
This must point to the `itemSelectedEventName` that you passed in the 
Item Selector URL of your Java OSGi Component class in the last section.

Next, the dialog listens for when the selected item has changed in the Item 
Selector dialog. In other words, when the user selects a new item, the variables 
within the `selectedItemChange()` function of the `on` attribute are updated and 
set for the newly selected item. This allows you to parse the JSON object to 
return information about the currently selected item. You can use this 
information to do whatever your application requires.

In the example above, the selected item's information is parsed using 
`JSON.parse(...)`, then the URL is retrieved for the selected item and set to 
the variable `itemSrc`. The information available to parse will depend on the 
return type(s) you've set. 

Next, the `title` attribute, responsible for setting the title that appears for 
the Item Selector dialog, is assigned to a language key. The `url` attribute is 
then set to the Item Selector URL you obtained in the last section.

Finally, the Item Selector dialog is opened by calling the `open()` method on 
the Item Selector dialog (the variable you set the Item Selector dialog 
configuration to). So, when the user clicks the *Choose* button, in this example, 
a new dialog will pop up, rendering the Item Selector with the proper views that
are supported by the criterion and return type(s) you set.

The diagram below illustrates the architectural overview for the Item Selector 
APIs:

![Figure 3: the Item Selector provides a lot through its APIs.](../../images/item-selector-architecture.png)

This tutorial covers how to use the Item Selector's default entities, however
the Item Selector is fully extensible and allows you to create your own custom
entities and selection views if needed.

<!-- Note: Add this back after tutorial has been written.
This is covered in the [Creating Custom Entities for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-entities-for-the-item-selector)
tutorial. -->

Now you know how to use the Item Selector's APIs to select default entities!

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)
<!-- Note: removing these for now until they are written
[Creating Custom Selection Views for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views-for-the-item-selector)

[Creating Custom Entities for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-entities-for-the-item-selector)
-->
