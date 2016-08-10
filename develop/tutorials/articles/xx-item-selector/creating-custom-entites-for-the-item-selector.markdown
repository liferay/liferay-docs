# Creating Custom Entities for the Item Selector [](id=creating-custom-entities-for-the-item-selector)

The Item Selector allows users to select entities using Item Selector 
criteria(`*ItemSelectorCriterion.class`) and return 
types(`*ItemSelectorReturnType.class`). You can read more about how to select 
entities using the Item Selector, and the default available criteria and return 
types, in the [Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api) 
tutorial.

The Item Selector's APIs offer a lot out of the box for your app. However, your 
app may require a new entity type for selection. For instance your app may be a 
task manager that requires users to select tasks to assign to themselves. This 
new task entity needs a new matching Item Selector criterion. Likewise, if your 
app requires custom information to be returned, such as the creator of the task 
and the user selecting it, you'll need to create new return types.

This tutorial demonstrates these key concepts:

- How to create new Item Selector criterion
- How to create new Item Selector return types

Go ahead and get started.

## Creating Custom Item Selector Criterion [](id=creating-custom-item-selector-criterion)

When you want to create a custom entity, you must first create a new criterion
for the entity.

Follow the steps below to create your custom Item Selector criterion:

1.  Create a class that extends `BaseItemSelectorCriterion`.

    Using the task manager example mentioned in the intro, you would need to 
    create a new criterion for the task entity. The declaration might look like
    the one shown below:

        public class TasksItemSelectorCriterion extends 
        BaseItemSelectorCriterion {
        
        }
        
    This class acts as a flag of sorts, allowing the Item Selector to accept it 
    as a valid criterion. That's all that is needed for this class.

2.  When you create a new Item Selector criterion, you need to register an OSGi 
    component to handle obtaining the selection views for the new item 
    selector criterion as well.
    
    Create a new class, registered as an OSGI component, that implements 
    `BaseItemSelectorCriterionHandler`.

    For example, the task Item Selector criterion might have the following item 
    selector criterion handler:

        @Component(service = ItemSelectorCriterionHandler.class)
        public class TaskItemSelectorCriterionHandler extends 
        BaseItemSelectorCriterionHandler<TaskItemSelectorCriterion> {

            public Class <TaskItemSelectorCriterion> 
            getItemSelectorCriterionClass() {
                return TasksItemSelectorCriterionHandler.class;
            }
            
            @Activate
            @Override
            protected void activate(BundleContext bundleContext) {
                    super.activate(bundleContext);
            }
    
        }
        
        <!-- Is the @Activate @Override code needed here? I added it in because 
        I've seen it in other criterion handlers. For example: https://github.com/liferay/liferay-portal/blob/a5a0a3e961c2d87d565ca55d4df03985de72a4f6/modules/apps/collaboration/wiki/wiki-api/src/main/java/com/liferay/wiki/internal/item/selector/handler/WikiAttachmentItemSelectorCriterionHandler.java
        -->
  
This ties this handler class to the new criterion class you just created, and 
activates the OSGi bundle, so it's available for use.
<!-- Is the information above correct? -->

The selection view is determined by the criterion specified in the 
`*ItemSelectorCriterionHandler` class. You can learn more about how selection 
views are created in the [Creating Custom Selection Views for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views-for-the-item-selector)
tutorial.

Now that your Item Selector criterion classes are taken care of, you'll need to
decide if you need a new return type as well next.

## Creating Custom Item Selector Return Types [](id=creating-custom-item-selector-return-types)

If your new entity can return default Item Selector information, such as a URL 
or primary key, you can use existing Item Selector return types, and can skip
this section. 

 These are the default return types:
 
-  [Base64ItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/Base64ItemSelectorReturnType.html)  
-  [FileEntryItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html)
-  [URLItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html)
-  [UUIDItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.html)

Return types are covered in more detail in the 
[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api) 
tutorial.

If however your app requires custom information returned, you need to create 
new return types to handle the new information.

You must create a class that implements `ItemSelectorReturnType`.

    The example task manager app requires this information when a user 
    selects a task:

    - the task identifier
    - the task creator user

Since this information is not provided by a default Item Selector return type, a 
new Item Selector return type is needed.

Using the task manager example, the class implementation might look like 
this:

    public class TasksItemSelectorReturnType implements 
    ItemSelectorReturnType{
    
    }
    
Note that you could create two separate return types to handle each piece of
information for the task manager app example, but for the sake of simplicity,
they have been grouped into one return type.
    
Like the `*ItemSelectorCriterion` class you created in the last section,
this class acts as a flag that indicates to the Item Selector that it is a
valid return type. The selection view will use this to return the proper
information.

Now you know how to create a custom entity!
    
So far, you've created a new API to allow users to select your new entity, tasks 
in the example, and return the required information, the task identifier and
task creator in the example. However, there is currently no selection view for
your new entity, to access the new criterion and returns types you've defined.

You can learn how to create your new selection view in the
[Creating Custom Selection Views](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views) 
tutorial.

## Related Topics [](id=related-topics)

[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api)

[Creating Custom Selection Views for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views-for-the-item-selector)
