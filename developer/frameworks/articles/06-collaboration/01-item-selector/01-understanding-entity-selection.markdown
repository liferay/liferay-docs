# Understanding Entity Selection

When you use an 
[Item Selector](/discover/portal/-/knowledge_base/7-1/item-selector) 
in your app, you must follow these steps:

1.  **Determine the Criteria for an Item Selector:** Define which entities the 
    Item Selector selects. 

2.  **Get an Item Selector for Your Criteria:** If your criteria is for images, 
    for example, then you must get an Item Selector that selects images. 

3.  **Use an Item Selector Dialog:** Display the Item Selector in your UI. 

Here, you'll learn the background info necessary to complete these steps. For 
step-by-step instructions on these steps, see 
[Selecting Entities with an Item Selector](liferay.com). 

## Getting an Item Selector for the Criteria [](id=getting-an-item-selector-for-the-criteria)

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

## Using the Item Selector Dialog [](id=using-the-item-selector-dialog)

To open the Item Selector in your UI, you must use the JavaScript component 
`LiferayItemSelectorDialog` from 
[AlloyUI's](http://alloyui.com/) 
`liferay-item-selector-dialog` module. The component listens for the item 
selected event that you specified for the Item Selector URL. The event returns 
the selected element's information according to its return type. 
