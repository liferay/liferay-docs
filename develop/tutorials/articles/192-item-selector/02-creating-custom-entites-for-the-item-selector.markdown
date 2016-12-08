# Creating Custom Entities for the Item Selector [](id=creating-custom-entities-for-the-item-selector)

So, your app requires users to select an item that the Item Selector isn't 
configured for? No problem. You can create a new entity.

This tutorial explains how to create a new entity for the Item Selector.

Go ahead and get started.

## Creating Item Selector Criterion [](id=creating-item-selector-criterion)

First, you must create a new criterion for your entity. Follow these steps to 
create an Item Selector criterion:

1.  Create a class that extends `BaseItemSelectorCriterion`.

    The example below creates a class called `TaskItemSelectorCriterion`:

        public class TasksItemSelectorCriterion extends 
        BaseItemSelectorCriterion {
        
        }
        
    This class acts as an identifier for Item Selector.

    Note that you can use this class to pass information to the view if
    needed. For example, the [JournalItemSelectorCriterion](https://github.com/liferay/liferay-portal/blob/586f66c629b559e79c744559751ecb960218fe0b/modules/apps/web-experience/journal/journal-item-selector-api/src/main/java/com/liferay/journal/item/selector/criterion/JournalItemSelectorCriterion.java)
    passes information about the primary key so the view can use it:

        public class JournalItemSelectorCriterion extends 
        BaseItemSelectorCriterion {
        
                public JournalItemSelectorCriterion() {
                }
        
                public JournalItemSelectorCriterion(long resourcePrimKey) {
                        _resourcePrimKey = resourcePrimKey;
                }
        
                public long getResourcePrimKey() {
                        return _resourcePrimKey;
                }
        
                public void setResourcePrimKey(long resourcePrimKey) {
                        _resourcePrimKey = resourcePrimKey;
                }
        
                private long _resourcePrimKey;
        
        }

    +$$$

    **Note:** Criterion fields should be serializable and should expose a 
    public empty constructor(as shown above).
    
    $$$

2.  Create an OSGi component class that implements 
    `BaseItemSelectorCriterionHandler`. Each criterion requires a criterion 
    handler, which is responsible for obtaining the proper selection view.

    The example below creates a criterion handler for the 
    `TaskItemSelectorCriterion`:

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
        
    The @Activate and @Override tokens are required to activate this OSGi 
    component.
    
Depending on the needs of your app, you may not need to create a return type. If
your entity returns information that is already defined by an existing return
type, you can use that return type.

You can view the default available criteria in the 
[Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-0/item-selector-criterion-and-return-types) 
reference.

If, however, your entity returns information that is not covered by an existing 
return type, you'll need to create a new return type next.

## Creating Item Selector Return Types [](id=creating-item-selector-return-types)

To create a return type, you must create a class that implements 
`ItemSelectorReturnType`.

The example below creates a new return type called `TaskItemSelectorReturnType`:

    public class TasksItemSelectorReturnType implements ItemSelectorReturnType{
    
    }
    
Note that you can create a new return type for each piece of information that 
your entity should return, but you can also create one return type to identify
all the information if you wish. Like the `*ItemSelectorCriterion` class, **the 
`*ItemSelectorReturnType` class is simply used as an identifier by the Item 
Selector, and does not actually return any information itself.**

So far, you've created an API that you can use to create a selection view for 
your new entity. The entity's criterion and return type classes will be 
used by your application to create the Item Selector URL. You can follow the 
[Selecting Entities using the Item Selector](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector) 
tutorial to learn how to obtain the Item Selector URL.

**The selection view is responsible for returning the proper entity information 
specified by the return type.** Currently there isn't a selection view to select 
your entity. Follow the [Creating Selection Views](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views) 
tutorial to learn how to create your new view.

Now you know how to create an entity for the Item Selector!

## Related Topics [](id=related-topics)

[Selecting Entities using the Item Selector](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector)

[Creating Custom Selection Views for the Item Selector](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views-for-the-item-selector)
