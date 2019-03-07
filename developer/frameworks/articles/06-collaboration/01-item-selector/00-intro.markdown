---
header-id: item-selector
---

# Item Selector

An *Item Selector* is a UI component for selecting entities in a user-friendly 
manner. 

Here's what you'll learn to do with Item Selectors: 

1.  Select Entities. 

2.  Create Custom Item Selector Criteria.

3.  Create Custom Item Selector Views. 

![Figure 1: Item Selectors select entities.](../../../images/item-selector-dialog-02.png)

## Understanding the Item Selector API's Components

Before working with the Item Selector API, you should learn about its 
components. You'll work with these components as you leverage the API in your 
apps: 

**Selection View:** A class that shows entities of particular types from 
different sources. For example, an Item Selector configured to show images might 
show selection views from Documents and Media, a third-party image provider, or 
a drag-and-drop UI. Selection views are the framework's key components. 

**Markup:** A markup file that renders the selection view. You can choose from
JSP, FreeMarker, or even pure HTML and JavaScript. 

**Return Type:** A class that represents the data type that entity selections 
return. For example, if users select images and you want to return the selected 
image's URL, then you need a URL return type. Each return type class must 
implement 
[`ItemSelectorReturnType`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html). 
Such classes are named after the return type's data and suffixed with 
`ItemSelectorReturnType`. For example, the URL return type class is 
`URLItemSelectorReturnType`. The return type class is an API that connects the 
return type to the Item Selector's views. The Item Selector uses the return type 
class, which is empty and returns no information, as an identifier. The view 
ensures that the proper information is returned. If you create your own return 
type, you should specify its data type and format in Javadoc. 

**Criterion:** A class that represents the selected entity. For example, if
users select images, you need an image criterion class. Each criterion class
must implement
[`ItemSelectorCriterion`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html).
Such classes are named for the entity they represent and suffixed with
`ItemSelectorCriterion`. For example, the criterion class for images is
`ImageItemSelectorCriterion`. If you create your own criterion class, extend
[`BaseItemSelectorCriterion`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterion.html).
This base class implements `ItemSelectorCriterion` and provides methods that
handle the Item Selector's return types. Your criterion class can therefore be
empty, unless you also want to use it to pass information to the view. 

Note that criterion and return types together form an Item Selector's 
*criteria*. The Item Selector uses its criteria to decide which selection views 
to show. 

| **Note:** For a list of the criterion classes and return types that @product@
| provides, see
| [Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-2/item-selector-criterion-and-return-types).

**Criterion Handler:** A class that gets the appropriate selection view. Each 
criterion requires a criterion handler. Criterion handler classes extend 
[`BaseItemSelectorCriterionHandler`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html) 
with the criterion's entity as a type argument. Criterion handler classes are 
named after the criterion's entity and suffixed by 
`ItemSelectorCriterionHandler`. For example, the image criterion handler class 
is `ImageItemSelectorCriterionHandler` and extends 
`BaseItemSelectorCriterionHandler<ImageItemSelectorCriterion>`. 

![Figure 2: Item Selector views (selection views) are determined by the return type and criterion, and rendered by the markup.](../../../images/item-selector-architecture.png)

## Getting an Item Selector

To use an Item Selector with your criteria, you must get that Item Selector's 
URL. The URL is needed to open the Item Selector dialog in your UI. To get this 
URL, you must get an `ItemSelector` reference and call its 
[`getItemSelectorURL`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) 
method with the following parameters: 

`RequestBackedPortletURLFactory`: A factory that creates portlet URLs. 

`ItemSelectedEventName`: A unique, arbitrary JavaScript event name that the Item 
Selector triggers when the entity is selected. 

`ItemSelectorCriterion`: The criterion (or an array of criterion objects) that 
specifies the type of entities to make available in the Item Selector. 

Keep these points in mind when getting an Item Selector's URL: 

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
selection views are required, however, for certain situations. For example, you 
must create a custom selection view for your users to select images from an 
external image provider. 

The selected entity type determines the view the Item Selector presents. The 
Item Selector can also render multiple views for the same entity type. For 
example, several selection views are available for images. Each selection view 
is a tab in the UI that corresponds to the image's location. An 
`*ItemSelectorCriterion` class represents each selection view. 

![Figure 3: An entity type can have multiple selection views.](../../../images/item-selector-tabs.png)

### The Selection View's Class

The criterion and return types determine the selection view's class. This class 
is an `ItemSelectorView` component class that implements 
[`ItemSelectorView`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html)
parameterized with the view's criterion. Remember these things when creating
this class: 

-   Configure the title by implementing the 
    [`getTitle`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getTitle-java.util.Locale-) 
    method to return the localized title of the tab to display in the Item 
    Selector dialog. 

-   Configure the search options by implementing the 
    [`isShowSearch()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isShowSearch--) 
    method to return whether your view should show the search field. To 
    implement search, this method must return `true`. The `renderHTML` method 
    indicates whether a user performed a search based on the value of the 
    `search` parameter. You can get the user's search keywords as follows: 

        String keywords = ParamUtil.getString(request, "keywords");

-   Make your view visible by implementing the 
    [`isVisible()`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isVisible-com.liferay.portal.kernel.theme.ThemeDisplay-) 
    method to return `true`. Note that you can use this method to add 
    conditional logic to disable the view. 
