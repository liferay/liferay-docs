# Item Selector [](id=item-selector)

An *Item Selector* is a UI component for selecting entities in a user-friendly 
manner. 

![Figure 1: Item Selectors select different kinds of entities.](../../../images/item-selector-dialog-02.png)

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

Here's what you'll learn to do with Item Selectors: 

1.  Selecting Entities with an Item Selector. 

2.  Creating Custom Item Selector Entities. 

3.  Creating Custom Item Selector Views. 
