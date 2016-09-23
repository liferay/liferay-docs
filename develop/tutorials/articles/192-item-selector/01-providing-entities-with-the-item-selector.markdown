# Introduction to the Item Selector's APIs [](id=introduction-to-the-item-selectors-apis)

The Item Selector allows users to select entities in the Portal. If you have 
ever added an image to a web content article or blog entry within the Portal, 
you used the Item Selector to select the image file.

The Item Selector is quite powerful, providing only the selection views specific 
to the type of entity a user is selecting.

For instance, when selecting the image file to add to a blog entry, only the 
selection views that allow image selection are rendered to the user (Blog Images, 
Documents and Media, URL, and Upload Image). If the user is selecting a video 
instead, the Item Selector provides the selection views for video. This removes 
the clutter of unnecessary options, and provides the user with a clean and clear 
UI.

The Item Selector's APIs allow you to harness the power of this solid framework 
within your own application, as well as to build upon it to create custom 
entities and selection views.

This tutorial covers these key topics:

-  The Item Selector API Architecture
-  The default criterion and return types for the Item Selector
-  How to use the Item Selector in your own application to select default
   entities

Get started by learning more about the Item Selector's API architecture next.

## API Architecture Overview [](id=api-architecture-overview)

The Item Selector API has a few key moving parts that you should be aware of.
Once you familiarize yourself with these key pieces of the architecture, you'll
have a solid foundational understanding that you can build on.

As stated in the beginning the Item Selector allows users to select entities in
the portal. An entity simply refers to the type of element the user 
selects (e.g. an image, video, etc.). These entities can be hosted within the 
portal already, or they can be uploaded from your local file system.

Entities have a matching implementation of a criterion class and a set of 
desired return types(information that is returned to the user when the entity is 
selected).

![Figure 1: Entities consist of an element and information.](../../images/item-selector-criteria.png)

### Item Selector Criterion [](id=item-selector-criterion)

Entities are defined in an implementation of the [`ItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html).
This class provides an interface that specifies the entity type to select and 
the information to return. The [`BaseItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/BaseItemSelectorCriterion.html) 
implements the `ItemSelectorCriterion` interface, so all that is required to 
specify a criterion is to extend the `BaseItemSelectorCriterion` class with the 
name of the entity type.

For example the `ImageItemSelectorCriterion` class contains the following
information:

    public class ImageItemSelectorCriterion extends BaseItemSelectorCriterion {
    }
    
It's important to note that implementations of the `ItemSelectorCriterion` class 
can also contain details about the entity that can be selected. This information 
should be specified using primitive types, or using very simple types that can 
be JSON serialized. The data can be accessible however you desire, but it must 
specify a non-parametrized constructor.

For example the [`UploadItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/) 
has the following non-parametrized constructor listed first:

    public UploadItemSelectorCriterion() {
    }

Then it specifies the parametrized constructor:

    public UploadItemSelectorCriterion(
            String url, String repositoryName, long maxFileSize) {
    
            _url = url;
            _repositoryName = repositoryName;
            _maxFileSize = maxFileSize;
    }

### Item Selector Return Types [](id=item-selector-return-types)
    
Information to return when an entity is selected is defined in an implementation 
of the [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html). 
This class provides an interface that specifies the information to return by the 
item selector view, when any entity is selected. Return types simply need to 
implement this interface.

For example the `URLItemSelectorReturnType` class contains the following
information:

    public class URLItemSelectorReturnType implements ItemSelectorReturnType {
    }
    
Return type classes are used in two key ways within the item selector API:

-  item selector views specify a list of supported return types for the view.

-  When constructing a new item selector criterion, you must specify a list of 
   the desired item selector return types that the user should receive when the 
   entity is selected.

### Item Selector Views [](id=item-selector-views)
   
When the supported return types for the item selector view and the desired 
return types for an entity are in agreement, the matching item selector view is
rendered.

As you can see, return type classes are empty, and even the 
`ItemSelectorReturnType` interface provides no method signatures. The Item 
Selector uses these classes as flags to render the proper item selector view to 
the user. Only the item selector views that support the return type are 
displayed.

Item selector views are specified with an implementation of the 
[`ItemSelectorView` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorView.html). 
This class provides an interface to render a specific selection view for an item 
selector criterion. Each implementation of this class specifies the item 
selector criterion class associated with the view, the supported item selector 
return types, the title for the selection view tab in the item selector dialog, 
as well as other configuration options.

An item selector view only associates with one criterion. Multiple 
selection views can also share the same criterion. For instance, the 
Image View and the Documents and Media view can both use the 
`ImageItemSelectorCriterion`.

If an item selector view can be displayed for multiple different criteria, it 
needs as many implementations of the `ItemSelectorView` interface as criteria.

![Figure 2: item selector blogs tabs view screenshot.](../../images/item-selector-tabs.png)

The item selector dialog window in the figure above consists of four selection 
views, each with their own implementation of the `ItemSelectorView` class:

**Blog Images:** `BlogsItemSelectorView`
**Documents and Media:** `BaseDLItemSelectorView`
**URL:** `ItemSelectorURLView`
**Upload Image:** `ItemSelectorUploadView`

The `renderHTML()` method within these item selector view classes is responsible 
for rendering the selection view. The markup to display the selection view is 
specified within this method, along with the name of a JavaScript event that 
should fire when an element is selected. This event should return the specific 
data(information) for the selected criterion based on the specified return 
type(s).

For example, the item selector for layouts has the following configuration in 
its [layouts.jsp](https://github.com/liferay/liferay-portal/blob/2f70a9e4dc8dbdd96646d0febbe14f69d7ee9e75/modules/apps/web-experience/layout/layout-item-selector-web/src/main/resources/META-INF/resources/layouts.jsp):

    Liferay.Util.getOpener().Liferay.fire(
            '<%= 
                layoutItemSelectorViewDisplayContext.getItemSelectedEventName() 
            %>',
            {
                    data: data
            }
    );
    
The `data` attribute points to a JSON object called `data`, in this case, which 
gets populated with the URL and UUID return type information, defined within its
`supportedItemSelectorReturnTypes` Array List within its 
[`BaseLayoutsItemSelectorView` class](https://github.com/liferay/liferay-portal/blob/2f70a9e4dc8dbdd96646d0febbe14f69d7ee9e75/modules/apps/web-experience/layout/layout-item-selector-web/src/main/java/com/liferay/layout/item/selector/web/BaseLayoutsItemSelectorView.java)
, when an entity is selected.

If you're selecting default entities, the information for the selected item is
already defined. This information will be passed automatically when you select 
the entity. You can learn how to create custom selection views in the 
[Creating Custom Selection Views for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views-for-the-item-selector) 
tutorial.

### Item Selector Criterion Handlers [](id=item-selector-criterion-handlers)

Each item selector criterion must have a matching item selector criterion 
handler. Item selector criterion handlers are specified with an implementation 
of the [`ItemSelectorCriterionHandler` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterionHandler.html). 
This class provides an interface that returns the item selector view for a 
specific criterion. The [`BaseItemSelectorCriterionHandler` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html) 
implements this interface, so unless an implementation needs custom logic to 
filter selection views, most criterion handler classes only need to extend the 
`BaseItemSelectorCriterionHandler` class and implement the 
`getItemSelectorCriterionClass()` method. This method simply returns the name of 
the associated criterion class for the criterion handler. The criterion handler 
can then automatically obtain the item selector view based on the desired return 
types of the specified criterion class, the supported return types of the item 
selector view, and the criterion supported by the item selector view.

### Item Selector URL [](id=item-selector-url)

The item selector dialog is invoked with a unique URL, based on the criteria
specified (i.e. criterion and return type(s)). There are two main approaches 
to obtain the item selector URL:

-  One approach is to obtain the item selector in an OSGi Component class and 
   pass the item selector URL, or the whole item selector object, to the JSP or 
   template(e.g. FreeMarker).
   
-  Alternatively, you can obtain the item selector in a `*ConfigContributor.java`
   OSGi Component class (used to extend the configuration options for an editor). 
   The item selector URL would then be included in the JSON object that will be 
   interpreted by the editor. See the [Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)
   tutorial for more information on editor config contributors.
   
   For example, the AlloyEditor used for writing a Blog entry uses this approach
   to obtain the item selector in the [BlogsEditorContentConfigContributor](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-editor-configuration/src/main/java/com/liferay/blogs/editor/configuration/internal/BlogsContentEditorConfigContributor.java).
   This approach is covered in more detail in the 
   [Obtaining the Item Selector URL](#obtaining-the-item-selector-url) section.

**Important:** The order of the item selector criterion in the 
`itemSelectorURL` is important because it decides the order of the selection 
views (i.e. the order the selection view tabs will appear in the item 
selector dialog). For example, in the configuration above, the selection 
views for the `BlogsItemSelectorCriterion` will be displayed first, followed 
by the selection views for the `ImageItemSelectorCriterion`, and finally the
selection views for the `uploadItemSelectorCriterion`.

If two or more selection views share the same item selector criterion, the 
order of the selection view tabs for those selection views is determined by 
the service ranking property of the views.

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
   
The diagram below illustrates the architectural overview for the Item Selector 
APIs:

![Figure 3: the Item Selector provides a lot through its APIs.](../../images/item-selector-architecture.png)

Now that you have a better understanding of the Item Selector API architecture, 
and how each piece relates to one another, you can learn how to use the item 
selector APIs in your application next.

## Using the Item Selector APIs in your Application [](id=using-the-item-selector-apis-in-your-application)

As mentioned in the previous section, you must provide the item selector URL to 
render the Item Selector. This URL is configured uniquely, depending on the set 
of item selector criteria your application requires (i.e. the criterion and 
return type(s)). So, in order to obtain the item selector URL, you must first 
determine the type of entity the user can select and the associated criterion 
and return type(s) to use.

This tutorial focuses on the default criterion and return types that are
available for use. You can learn how to create custom criterion and return types
in the [Creating Custom Entities for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-entities-for-the-item-selector)
tutorial.

The default item selector criterion are covered next.

### Default Criterion [](id=default-criterion)

The Item Selector supports the following criterion by default:

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
   
Once you know the item selector criterion you would like to use, you can decide
on the return type(s) next.
   
### Default Return Types [](id=default-return-types)

The Item Selector supports the following return types by default:

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
   Returns the Universally Unique Identifier (UUID) of the entity as a 
   `String`.
   
A criterion can have multiple return types or just one. The number of return 
types depends on the type of information you wish to return to the user when the 
entity is selected.

For example, if you would like to allow users to select images within your
application and you need the URL and the ID of the image file returned, you 
would use the `ImageItemSelectorCriterion`, `FileEntryItemSelectorReturnType`, 
and `URLItemSelectorReturnType` classes.

Now that you have chosen your criterion and return type(s), you can create
the item selector URL next.

### Obtaining the Item Selector URL [](id=obtaining-the-item-selector-url)

If you wish to use the Item Selector within a WYSIWYG editor, such as the Alloy
editor, then you will need to obtain the item selector URL within a 
`*ConfigContributor` OSGi Component class, otherwise you can obtain the URL in a
standard OSGi Component class. The steps are generally the same, regardless of
where you decide to invoke the item selector. The steps outlined in this section
apply to both use cases, and clearly state where the implementation differs.

+$$$

**Note:** These steps show how to obtain the item selector URL within java 
classes, but you could also implement the code within JSP markup 
if you wished.

$$$

Follow these steps to obtain the item selector URL:

1.  The URL is generated by the Item Selector OSGi Component, so you'll need to 
    import the Item Selector OSGi Component into your class to use it.

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
    configure the URL next.
    
    The URL to the item selector is obtained by invoking the method 
    `getItemSelectorURL()` with the following parameters:
    
    - `RequestBackedPortletURLFactory`: factory to create portlet URLs. It
        can be created by invoking with either `HttpServletRequest` or
        `PortletRequest`. Use the following pattern to create your URL:
        `RequestBackedPortletURLFactoryUtil.create(request)`.
    - `ItemSelectedEventName`: a unique JavaScript event name that is
        triggered by the item selector when the element is selected.
    - `ItemSelectorCriterion`: the criterion (or array of criteria) that
        specifies the element that should be selected.
        
    If you're retrieving the item selector URL from within a 
    `*ConfigContributor` class for an editor, you can use these same parameters,
    but you must also pass in one additional parameter:
    
    -  `JSONObject`: the JSON object that holds the editor configuration for the
    contributor. Since this new parameter does not match the argument list found
    in the `ItemSelector` interface, you'll need to create a custom method to
    pass this information.
        
3.  Add the `getItemSelectorURL()` method with the following configuration if 
    you're invoking the item selector outside of a `*ConfigContributor` class:

        public String getItemSelectorURL(
		RequestBackedPortletURLFactory requestBackedPortletURLFactory,
                String itemSelectedEventName, ItemSelectorCriterion 
                itemSelectorCriterion) {
        
        }
        
   <!-- Jim, will this method accept one ItemSelectorCriterion or an array? 
   Would this configuration work for the arguments in step 4? I've seen another
   form of this:
   
          public String getItemSelectorURL(
		RequestBackedPortletURLFactory requestBackedPortletURLFactory,
                String itemSelectedEventName, ItemSelectorCriterion ...
                itemSelectorCriteria) {
        
        }
        
        I imagine this approach is for an array. For example:
        
        List<ItemSelectorCriterion> myCriterionList = new ArrayList<>();
        
        
        
        myCriterionList.add(
        blogsItemSelectorCriterion, imageItemSelectorCriterion, 
        uploadItemSelectorCriterion
        )
        
        Then I could pass this in like this:
        
        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
                requestBackedPortletURLFactory, itemSelectedEventName,
                myCriterionList);
                
        Does that seem like the correct syntax?
   
   -->
        
    If you're invoking the item selector within an editor, you'll need to 
    declare a new method that passes the same parameters as above, along with 
    the `JSONObject` for the editor configuration. For example, the 
    [`BlogsContentEditorConfigContributor` class](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-editor-configuration/src/main/java/com/liferay/blogs/editor/configuration/internal/BlogsContentEditorConfigContributor.java) Use the following configuration if you're invoking the item selector within
    declare the following method:
    
        protected void populateFileBrowserURL(
                JSONObject jsonObject, ThemeDisplay themeDisplay,
                RequestBackedPortletURLFactory requestBackedPortletURLFactory,
                String eventName) {
                
        This method must be added to the `populateConfigJSONObject()` method of
        your config contributor class. For example, the 
        `BlogsContentEditorConfigContributor` class includes the following in
        its `populateConfigJSONObject()` method:
        
        populateFileBrowserURL(
                jsonObject, themeDisplay, requestBackedPortletURLFactory,
                namespace + name + "selectItem");
        
    This sets the basic configuration for the method as described in the last
    step. Now you can populate the method with the criterion and return type(s)
    of your choosing.
        
4.  Within the body of the method you just added, create an array list to hold 
    the return types for your criterion and configure the criterion to use it, 
    using the `setDesiredItemSelectorReturnTypes()` method.
    
    For example, the [Blogs-web module](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/BlogsItemSelectorHelper.java)
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
    `UploadItemSelectorCriterion`, as well sets some base configuration options
    for the `UploadItemSelectorCriterion`.

5.  Set the value for the `itemSelectorURL` based on the criterion you just
    selected. For example, the Blogs-web module has the following value for the
    `itemSelectorURL`, based on the configuration shown in step 3:
    
        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
                requestBackedPortletURLFactory, itemSelectedEventName,
                blogsItemSelectorCriterion, imageItemSelectorCriterion,
                uploadItemSelectorCriterion);
                

    The order of the item selector criterion determines the order that the 
    selection view tabs are rendered in the item selector dialog. When the two 
    item selector views share the same criterion, the order is determined by the
    service order property.
    
    The order of the desired return types in the array list also determines the 
    priority for the return types. This is covered in more detail in the 
    [API Architecture Overview](#item-selector-url).

6.  Finally, return the `itemSelectorURL` as a string.

    If you're invoking the item selector outside of an editor, add the following 
    code just before the closing of the body of the `getItemSelectorURL(){...}` 
    method:

        return itemSelectorURL.toString();
        
    If you're invoking the item selector URL from within an editor config
    contributor class, add the `itemSelectorURL` to the `JSONObject` you passed
    in your custom method. For example, the 
    `BlogsContentEditorConfigContributor` class uses the following code:
    
        jsonObject.put(
            "filebrowserImageBrowseLinkUrl", itemSelectorURL.toString());            

Now that you've obtained the item selector URL, you can use it to open the item
selector dialog next.

### Opening the Item Selector Dialog [](id=opening-the-item-selector-dialog)

To open the item selector dialog, you must use the `LiferayItemSelectorDialog` 
JavaScript component, an [AlloyUI](http://alloyui.com/) 
module, which allows you to invoke the Item Selector dialog using the URL you 
created in the last section.

This JavaScript component will open the dialog with the item selector and listen 
to the JavaScript `itemSelectedEventName` that you configured in the 
`itemSelectorURL`, to obtain the information about the selected element. Note
that the JavaScript event should return the information for the selected element
according to the return type(s) you configured for your criterion.

Follow these steps to open the Item Selector Dialog:

1.  Create a JSP in your app's `src/main/resources/META-INF/resources` folder to
    hold the AlloyUI script for the Item Selector dialog.
    
    The Item Selector Dialog is an Alloy UI component, so in order to use it
    you'll need to add the AUI directive to your JSP.
    
2.  Add the following AUI taglib directive to the top of your JSP:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

3.  Add `<aui:script>...</aui:script>` tags and configure them to use the 
    `liferay-item-selector-dialog` module:
    
        <aui:script use="liferay-item-selector-dialog">
        
        </aui:script>
        
4.  Attach an event handler to the DOM element you want to trigger the item
    selector dialog. 
    
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
    selector dialog, you can create a new instance of the 
    `LiferayItemSelectorDialog` AlloyUI Component. Add the configuration code 
    within the body of the `function(event){...}`.
    
    Below is a sample configuration:

        var itemSelectorDialog = new A.LiferayItemSelectorDialog(
        
                {
        
                   eventName: 'ItemSelectedEventName',
        
                   on: {
        
                      selectedItemChange: function(event) {
        
                         var selectedItem = event.newVal;
        
                         var itemValue = JSON.parse(selectedItem.value);
        
                         itemSrc =  itemValue.url;
        
                         }
        
                   },
        
                   title: '<liferay-ui:message key="select-image" />',
        
                   url: '<%= itemSelectorURL.toString() %>'
        
                }
        
             );
        
             itemSelectorDialog.open();
        
A new instance of the Liferay Item Selector dialog is created with the 
`new A.LiferayItemSelectorDialog(...)` line and is set to the variable 
`itemSelectorDialog`. Next, the `eventName` attribute is set for the dialog.
This will listen for the event name specified when an item is selected. This 
should point to the `itemSelectedEventName` that you passed in the 
`itemSelectorURL` of your Java OSGi Component class in the last section.

Next, the dialog listens for when the selected item has changed in the item 
selector dialog. In other words, when the user selects a new item, the variables 
within the `selectedItemChange()` function of the `on` attribute are updated and 
set for the newly selected item. This allows you to parse the JSON object to 
return information about the currently selected item. In the example above, the
selected item's information is parsed using `JSON.parse(...)`, then the URL is
retrieved for the selected item and set to the variable `itemSrc`. The
information available to parse will depend on the return type(s) you've set.

The data for your entity that is returned (i.e. the return types) is defined
within the selection view for your entity. This data is already defined for the 
default entities, so there is no further action needed on your part to retrieve 
this information. This is explained further in the [API Architecture Overview](#item-selector-views).

Next, the `title` attribute, responsible for setting the title that appears for 
the item selector dialog, is assigned to a language key. The `url` attribute is 
then set to the `itemSelectorURL` you obtained in the last section.

Finally, the item selector dialog is invoked by calling the `open()` method on 
the item selector dialog (the variable you set the item selector dialog 
configuration to). So, when the user clicks the *Choose* button, in this example, 
a new dialog will pop up, rendering the Item Selector with the proper views that
are supported by the criterion and return type(s) you set.

Now you know how to use the Item Selector's APIs to select default entities!

## Related Topics [](id=related-topics)

[Creating Custom Selection Views for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views-for-the-item-selector)

[Creating Custom Entities for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-entities-for-the-item-selector)
