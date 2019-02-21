# Item Selector [](id=item-selector)

An *Item Selector* is a UI component for selecting entities in a user-friendly 
manner. 

![Figure 1: Item Selectors select different kinds of entities.](../../../images/item-selector-dialog-02.png)

Here's what you'll learn to do with Item Selectors: 

1.  Selecting Entities with an Item Selector. 

2.  Creating Custom Item Selector Entities. 

3.  Creating Custom Item Selector Views. 

## Understanding the Item Selector API's Components [](id=understanding-the-item-selector-apis-components)

Before working with the Item Selector API, you should learn about its 
components. You'll work with these components as you leverage the API in your 
apps: 

**Selection Views:** These are the framework's key components. They show 
entities of particular types from different sources. For example, an Item 
Selector configured to show images might show selection views from Documents and 
Media, a third-party image provider, or a drag-and-drop UI. 

**Markup:** A markup file that renders the selection view. You have a great deal 
of flexibility in the markup language you choose. For example, you can use a 
JSP, FreeMarker, or even pure HTML and JavaScript. 

**Return Type:** A class that represents the type of information returned from 
the entities selected by the users. For example, if users select images and you 
want to return the selected image's URL, then you need a URL return type class. 
Each return type class must implement 
[`ItemSelectorReturnType`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html). 
Such classes are named after the data they return and suffixed with 
`ItemSelectorReturnType`. For example, the URL return type class is 
`URLItemSelectorReturnType`. A return type class is used as an identifier by the 
Item Selector and does not return any information itself (the return type class 
is empty). The return type class is an API that connects the return type to the 
Item Selector views. Whenever the return type is used, the view must ensure that 
the proper information is returned. If you need to create your own return type, 
you should specify the information that the return type returns, as well as the 
format, as Javadoc. 

**Criterion:** A class that represents the entity selected by users. For 
example, if users select images then you need an image criterion class. Each 
criterion class must implement 
[`ItemSelectorCriterion`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html). 
Criterion classes are named for the entity they represent and suffixed with 
`ItemSelectorCriterion`. For example, the criterion class for images is 
`ImageItemSelectorCriterion`. If you need to create your own criterion class, 
extend 
[`BaseItemSelectorCriterion`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterion.html). 
`BaseItemSelectorCriterion` implements `ItemSelectorCriterion` and provides 
methods that handle the Item Selector's return types. Your criterion class can 
therefore be empty, unless you also want to use it to pass information to the 
view. 

Note that criterion and return types together form an Item Selector's 
*criteria*. The Item Selector uses its criteria to decide which selection views 
(tabs of items) to show. 

+$$$

**Note:** For a list of the criterion classes and return types that @product@ 
provides, see 
[Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-2/item-selector-criterion-and-return-types). 

$$$

**Criterion Handler:** A class that gets the appropriate selection view. Each 
criterion requires a criterion handler. Criterion handler classes extend the 
[`BaseItemSelectorCriterionHandler` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html) 
with the criterion's entity as a type argument. Criterion handler classes are 
named after the criterion's entity and suffixed by 
`ItemSelectorCriterionHandler`. For example, the image criterion handler class 
is `ImageItemSelectorCriterionHandler`. It extends 
`BaseItemSelectorCriterionHandler<ImageItemSelectorCriterion>`. 

This diagram shows how these components interact to form a working API. 

![Figure 1: Item Selector views (selection views) are determined by the return type and criterion, and rendered by the markup.](../../../images/item-selector-architecture.png)

## Getting an Item Selector

To use an Item Selector with your criteria, you must get that Item Selector's 
URL. The URL is needed to open the Item Selector dialog in your UI. To get this 
URL, you must get an `ItemSelector` reference and call its 
[`getItemSelectorURL` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) 
with the following parameters: 

`RequestBackedPortletURLFactory`: A factory that creates portlet URLs. 

`ItemSelectedEventName`: A unique, arbitrary JavaScript event name that the Item 
Selector triggers when the element is selected. 

`ItemSelectorCriterion`: The criterion (or an array of criterion objects) that 
specifies the type of elements to make available in the Item Selector. 

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

## Understanding Custom Selection Views

The default selection views may provide everything you need for your app. Custom 
selection views are required, however, for certain situations. For example, if 
you want your users to be able to select images from an external image provider, 
then you must create a custom selection view. 

The entity type the user selects determines the view the Item Selector presents. 
The Item Selector can also render multiple views for the same entity type. For 
example, several selection views are available for images. Each selection view 
is a tab in the UI that corresponds to the image's location. An 
`*ItemSelectorCriterion` class represents each selection view. 

![Figure 1: An entity type can have multiple selection views.](../../../images/item-selector-tabs.png)

### The Selection View's Class

The criterion and return types determine the selection view's class. A selection 
view's class is an `ItemSelectorView` component class that implements 
[`ItemSelectorView`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html), 
parameterized with the criterion the view requires. Note the following when 
creating this class: 

-   Configure the title by implementing the 
    [`getTitle`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getTitle-java.util.Locale-) 
    method to return the localized title of the tab to display in the Item 
    Selector dialog. 

-   Configure the search options by implementing the 
    [`isShowSearch()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isShowSearch--) 
    method to return whether your view should show the search field. To 
    implement search, you must return `true` for this method. The `renderHTML` 
    method indicates whether a user performed a search based on the value of the 
    `search` parameter. You can obtain the user's search keywords as follows: 

        String keywords = ParamUtil.getString(request, "keywords");

-   Make your view visible by implementing the 
    [`isVisible()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isVisible-com.liferay.portal.kernel.theme.ThemeDisplay-) 
    method to return `true`. Note that you can use this method to add 
    conditional logic to disable the view. 
