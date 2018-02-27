# Creating Custom Item Selector Entities [](id=creating-custom-item-selector-entities)

Does your app require users to select an item that the Item Selector isn't 
configured for? No problem. You can create a new entity.

This tutorial explains how to create a new entity for the Item Selector.

## Creating Item Selector Criterion [](id=creating-item-selector-criterion)

First, you must create a new criterion for your entity: 

1.  Create a class that extends the 
    [`BaseItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterion.html).
    This class specifies what kind of entity the user is selecting and what 
    information the Item Selector should return. The methods inherited from 
    `BaseItemSelectorCriterion` provide the logic for obtaining this 
    information. 

    Note that you can use this class to pass information to the view if
    needed. For example, the 
    [`JournalItemSelectorCriterion` class](@app-ref@/web-experience/latest/javadocs/com/liferay/journal/item/selector/criterion/JournalItemSelectorCriterion.html)
    passes information about the primary key so the view can use it: 

        public class JournalItemSelectorCriterion extends BaseItemSelectorCriterion {

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
    public empty constructor (as shown above).
    
    $$$

2.  Create an OSGi component class that implements the 
    [`BaseItemSelectorCriterionHandler` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html). 
    Each criterion requires a criterion handler, which is responsible for 
    obtaining the proper selection view. 

    This example creates a criterion handler for the `TaskItemSelectorCriterion` 
    class: 

        @Component(service = ItemSelectorCriterionHandler.class)
        public class TaskItemSelectorCriterionHandler extends 
            BaseItemSelectorCriterionHandler<TaskItemSelectorCriterion> {

            public Class <TaskItemSelectorCriterion> getItemSelectorCriterionClass() {
                return TasksItemSelectorCriterionHandler.class;
            }

            @Activate
            @Override
            protected void activate(BundleContext bundleContext) {
                    super.activate(bundleContext);
            }

        }
 
    The `@Activate` and `@Override` tokens are required to activate this OSGi 
    component. 

Depending on your app's needs, you may not need to create a return type. If your 
entity returns information that is already defined by an existing return type, 
you can use that return type instead. You can view the default available 
criteria in the reference document 
[Item Selector Criterion and Return Types](/develop/reference/-/knowledge_base/7-1/item-selector-criterion-and-return-types). 
If, however, your entity returns information that is not covered by an existing 
return type, you'll need to create a new return type next. 

## Creating Item Selector Return Types [](id=creating-item-selector-return-types)

To create a return type, you must create a class that implements the 
[`ItemSelectorReturnType` interface](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html). 
You should name such classes after their entity, and suffix them with 
`ItemSelectorReturnType`. For example, if you were to create a return type for a 
task item, its return type class would be `TaskItemSelectorReturnType`. Such a 
`*ItemSelectorReturnType` class is used as an identifier by the Item Selector 
and does not return any information itself. The return type class is an API that 
connects the return type to the Item Selector views. Whenever the return type is 
used, the view must ensure that the proper information is returned. It's 
recommended that you specify the information that the return type returns, as 
well as the format, as Javadoc. For example, here's the example return type 
class `TaskItemSelectorReturnType`: 

    /**
    * This return type should return the task ID and the user who
    * created the task as a string.
    *
    * @author Joe Bloggs
    */
    public class TaskItemSelectorReturnType implements ItemSelectorReturnType{

    }

Nice work! Your new entity's criterion and return type classes can be used by 
your app to create the Item Selector's URL. To learn how to obtain the Item 
Selector URL's, see the tutorial 
[Selecting Entities Using the Item Selector](/develop/tutorials/-/knowledge_base/7-1/selecting-entities-using-the-item-selector). 

Once you have the Item Selector's URL, a selection view is responsible for 
returning the proper entity information specified by the return type. To create 
such a selection view for your entity, see the tutorial 
[Creating Custom Item Selector Views](/develop/tutorials/-/knowledge_base/7-1/creating-custom-item-selector-views). 

## Related Topics [](id=related-topics)

[Understanding the Item Selector API's Components](/develop/tutorials/-/knowledge_base/7-1/understanding-the-item-selector-apis-components)

[Selecting Entities using the Item Selector](/develop/tutorials/-/knowledge_base/7-1/selecting-entities-using-the-item-selector)

[Creating Custom Item Selector Views](/develop/tutorials/-/knowledge_base/7-1/creating-custom-item-selector-views)
