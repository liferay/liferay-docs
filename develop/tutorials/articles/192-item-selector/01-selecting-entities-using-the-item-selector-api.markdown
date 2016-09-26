# Selecting Entities Using the Item Selector API [](id=selecting-entities-using-the-item-selector-api)

The Item Selector allows users to select any kind of entity in a very consistent 
manner. It is used throughout @product@ to allow users to quickly and 
easily select images, users, videos etc. to include in their web content, or 
complete an action.

![Figure 1: The Item Selector makes selecting entities a breeze.](../../images/item-selector-dialog-01.png)

You can use this same UI in your own application by utilizing the Item 
Selector's APIs.

This tutorial covers these key topics:

-  The default criterion and return types for the Item Selector
-  How to obtain the item selector URL
-  How to invoke the Item Selector's dialog

Go ahead and get started.

## Determining Elements and Return Types [](id=determining-elements-and-return-types)

To use the Item Selector, you must first decide the type of element you would
like to select (e.g. an image, a video, a user, etc.). The answer to this
question determines the implementation of the [`ItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html) 
you must use. The criterion communicates key information about the type of
element the Item Selector will be selecting.

Next, you must decided what information you expect to be returned when the
element is selected. The answer to this questions determines the implementation
of the [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html) 
you must use. The return type defines key information about the data that should
be returned when the element is selected.

![Figure 2: Item Selector criteria consist of criterion and return types.](../../images/item-selector-criteria.png)

A criterion can have multiple return types or just one. The number of return 
types depends on the type of information you wish to return to the user when the 
element is selected.

Once you know the kind of element you want to select and the return type(s) you
want for the selected element, you can use the corresponding classes for the 
item selector.

For example, if you would like to allow users to select images within your
application and you need the URL and the ID of the image file returned, you 
would use the `ImageItemSelectorCriterion`, `FileEntryItemSelectorReturnType`, 
and `URLItemSelectorReturnType` classes.

The criterion specifies the return type(s) it would like to use via its 
`getDesiredItemSelectorReturnTypes()` method. More on this in a bit.

Each element type corresponds to a matching implementation of 
`ItemSelectorCriterion`. The following criteria is included by the item selector 
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
`ItemSelectorReturnType`. The following return types are included by the item 
selector api module:

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

To use the Item Selector, you must provide a URL that specifies the criterion
and return types you would like to use. You'll learn how to obtain this next.

## Obtaining the URL for the Item Selector Views [](id=obtaining-the-url-for-the-item-selector-views)

The URL to the item selector is obtained by invoking the method 
`getItemSelectorURL()` with the following parameters:
    
- `RequestBackedPortletURLFactory`: factory to create portlet URLs. It can be 
  created by invoking with either `HttpServletRequest` or `PortletRequest`. Use 
  the following pattern to create your URL: 
  `RequestBackedPortletURLFactoryUtil.create(request)`.
- `ItemSelectedEventName`: a unique JavaScript event name that is triggered by 
  the item selector when the element is selected.
- `ItemSelectorCriterion`: the criterion (or array of criteria) that specifies 
  the element that should be selected.

Follow these steps to obtain the item selector URL:

1.  The URL is generated by the Item Selector OSGi Component, so you'll need to 
    import the Item Selector OSGi Component into your Component class to use it.

    Add the following import:

        import com.liferay.item.selector.ItemSelector;
        
2.  Next you need to reference the ItemSelector service so you can have access
    to your own instance of the item selector.

        @Reference(unbind = "-")
    
        public void setItemSelector(ItemSelector itemSelector) {
    
            _itemSelector = itemSelector;
    
        }
        
        private ItemSelector _itemSelector

    Now that you have the item selector OSGI Component available, you can
    configure the `getItemSelectorURL()` method next.
        
3.  Add the `getItemSelectorURL()` method with the following configuration:

        public String getItemSelectorURL(
		RequestBackedPortletURLFactory requestBackedPortletURLFactory,
                String itemSelectedEventName, ItemSelectorCriterion
                itemSelectorCriterion) {
        
        }
        
    This sets the basic configuration for the method as described in the last
    step. Now you can populate the method with the criterion and return type(s)
    of your choosing.
        
4.  Within the body of the method you just added, create an array list to hold 
    the return types for your criterion and configure the criterion to use it, 
    using the criterion's `setDesiredItemSelectorReturnTypes()` method.
    
    For example, the [Blogs Web module](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/BlogsItemSelectorHelper.java)
    has the following configuration:
    
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
			
    The configuration above sets the desired returns types for the 
    `BlogsItemSelectorCriterion`, `ImageItemSelectorCriterion`, and 
    `UploadItemSelectorCriterion`, as well as sets some base configuration 
    options for the `UploadItemSelectorCriterion`. Each criterion will now 
    retrieve the desired return types you just set, when the 
    `getDesiredItemSelectorReturnTypes()` method is called.

5.  Set the value for the `itemSelectorURL` based on the criterion you just
    selected. For example, the Blogs-web module has the following value for the
    `itemSelectorURL`, based on the configuration shown in step 4:
    
        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
                requestBackedPortletURLFactory, itemSelectedEventName,
                blogsItemSelectorCriterion, imageItemSelectorCriterion,
                uploadItemSelectorCriterion);
                

    The item selector uses this information to determine the selection views 
    automatically. For example, if you use `ImageItemSelectorCriterion`, it will 
    display the image selection views, while if you use 
    `VideoItemSelectorCriterion`, it will display the selection views that 
    support video.
    
    **Important:** The order of the item selector criterion in the 
    `itemSelectorURL` is important because it decides the order of the selection 
    views (i.e. the order the selection view tabs will appear in the item 
    selector dialog). For example, in the configuration above, the selection 
    views for the `BlogsItemSelectorCriterion` will be displayed first, followed 
    by the selection views for the `ImageItemSelectorCriterion`, and finally the
    selection views for the `uploadItemSelectorCriterion`.
    
    If two or more selection views share the same item selector criterion, the 
    order of the selection view tabs for those selection views is determined by 
    the service ranking property declared in the selection views.
    
    For example, say you have the following views listed below:
    
    -  Documents and Media View(ImageItemSelectorCriterion) with a service
    ranking property of 100(`item.selector.view.order:Integer=100`)
    
    -  Custom Image view(ImageItemSelectorCriterion) with a service
    ranking property of 200(`item.selector.view.order:Integer=200`)
    
    -  URL View(URLItemSelectorCriterion) with a service
    ranking property of 150(`item.selector.view.order:Integer=150`)
    
    If you invoke the item selector with the order `URLItemSelectorCriterion`,
    followed by `ImageItemSelectorCriterion`, the order of the selection view
    tabs will be URL View, Custom Image View, and Documents and Media View. URL
    view is rendered first because it is the first criterion listed, while
    Custom Image View and Documents and Media View both use the 
    `ImageItemSelectorCriterion`, so their order is determined by their service 
    ranking. In this example, the Custom Image View will be rendered
    as the second tab because it has a higher service ranking, followed by the 
    Documents and Media View as the third tab. The higher the service ranking
    is, the higher priority it has.
    
    The order of the desired return types in the array list also determines the 
    priority for the return types. The selection views will use the first
    available return type that is supported.

6.  Finally, return the item selector URL as a string.

    For example the Blogs item selector helper has the following configuration:

        return itemSelectorURL.toString();
            
Here is the full configuration for the [`BlogsItemSelectorHelper` class](https://github.com/liferay/liferay-portal/blob/d7f211c0cbb5374c41e1b8e4b1f4b024baae5bc6/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/BlogsItemSelectorHelper.java):

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

Now that you've obtained the item selector URL, you can use it to open the item
selector dialog next.

## Opening the Item Selector Dialog [](id=opening-the-item-selector-dialog)

To open the item selector dialog, you must use the `LiferayItemSelectorDialog` 
JavaScript component, an [AlloyUI](http://alloyui.com/) 
module, which allows you to invoke the Item Selector dialog using the URL you 
obtained in the last section.

This JavaScript component will open the dialog with the item selector and listen 
to the JavaScript `itemSelectedEventName` that you configured in the 
item selector URL, to obtain the information about the selected element. Note
that the JavaScript event returns the information for the selected element
according to the return type(s) you configured for your criterion.

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
        
4.  Now that the AUI Script tag is configured to use the item selector dialog 
    module, you must attach an event handler to the UI element you want to 
    use to open the Item Selector dialog. 
    
    For example, the configuration below creates a *Choose* button with the ID 
    `chooseImage` and then attaches a click event to it to invoke the item 
    selector dialog:
    
        <aui:button name="chooseImage" value="Choose" />
    
        <aui:script use="liferay-item-selector-dialog">
        
        $('#<portlet:namespace />chooseImage').on(
        
          'click',
        
          function(event) {
        
        
        
          }
        
        );
        
        </aui:script>
        
5.  Once, you've determined the method you want to use to invoke the item 
    selector dialog, you must create a new instance of the 
    `LiferayItemSelectorDialog` AlloyUI Component and configure it to use the 
    item selector URL and JavaScript event that you configured.
    
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
                                        itemSrc =  itemValue.url;
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
item selector URL of your Java OSGi Component class in the last section.

Next, the dialog listens for when the selected item has changed in the item 
selector dialog. In other words, when the user selects a new item, the variables 
within the `selectedItemChange()` function of the `on` attribute are updated and 
set for the newly selected item. This allows you to parse the JSON object to 
return information about the currently selected item. In the example above, the
selected item's information is parsed using `JSON.parse(...)`, then the URL is
retrieved for the selected item and set to the variable `itemSrc`. The
information available to parse will depend on the return type(s) you've set.

Next, the `title` attribute, responsible for setting the title that appears for 
the item selector dialog, is assigned to a language key. The `url` attribute is 
then set to the item selector URL you obtained in the last section.

Finally, the item selector dialog is invoked by calling the `open()` method on 
the item selector dialog (the variable you set the item selector dialog 
configuration to). So, when the user clicks the *Choose* button, in this example, 
a new dialog will pop up, rendering the Item Selector with the proper views that
are supported by the criterion and return type(s) you set.

The diagram below illustrates the architectural overview for the Item Selector 
APIs:

![Figure 3: the Item Selector provides a lot through its APIs.](../../images/item-selector-architecture.png)

This tutorial covers how to use the item selector's default entities, however
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
