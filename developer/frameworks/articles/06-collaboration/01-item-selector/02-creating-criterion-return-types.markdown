---
header-id: creating-custom-criterion-and-return-types
---

# Creating Custom Criterion and Return Types

[TOC levels=1-4]

If an existing criterion or return type doesn't fit your use case, you can
create them. The steps here show you how. For more detailed information on Item
Selector criterion and return types, see the 
[Item Selector introduction](/docs/7-2/frameworks/-/knowledge_base/f/item-selector). 

1.  Create your criterion class by extending 
    [`BaseItemSelectorCriterion`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterion.html). 
    Name the class after the entity it represents and suffix it with 
    `ItemSelectorCriterion`. You can use the class to pass information to the 
    view if needed. Otherwise, your criterion class can be empty. If you pass 
    information to the view, any fields in your criterion class should be 
    serializable and you should expose an empty public constructor. 

    For example, 
    [`JournalItemSelectorCriterion`](@app-ref@/web-experience/latest/javadocs/com/liferay/journal/item/selector/criterion/JournalItemSelectorCriterion.html) 
    is the criterion class for `Journal` entities (Web Content) and passes 
    primary key information to the view: 

    ```java

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

    ```

2.  Create a criterion handler by creating an OSGi component class that 
    implements 
    [`BaseItemSelectorCriterionHandler`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html). 
    This example creates a criterion handler for the `TaskItemSelectorCriterion` 
    class. The `@Activate` and `@Override` tokens are required to activate this 
    OSGi component: 

    ```java

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

    ```

3.  If you need a new return type, create it by implementing 
    [`ItemSelectorReturnType`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html). 
    Name your return type class after the return type's data and suffix it with 
    `ItemSelectorReturnType`. Specify the data and its format in Javadoc. Return 
    type classes need no content. For example, here's a return type for a task: 

    ```java

        /**
        * This return type should return the task ID and the user who
        * created the task as a string.
        *
        * @author Joe Bloggs
        */
        public class TaskItemSelectorReturnType implements ItemSelectorReturnType{

        }

    ```

## Related Topics

[Item Selector](/docs/7-2/frameworks/-/knowledge_base/f/item-selector)

[Creating Custom Item Selector Views](/docs/7-2/frameworks/-/knowledge_base/f/creating-custom-item-selector-views)

[Selecting Entities with an Item Selector](/docs/7-2/frameworks/-/knowledge_base/f/selecting-entities-with-an-item-selector)
