---
header-id: actionable-dynamic-queries
---

# Actionable Dynamic Queries

[TOC levels=1-4]

Suppose you have over a million users, and you want to perform some kind of mass
update to some of them. One approach might be to use a dynamic query to retrieve
the list of users in question. Once loaded into memory, you could loop through
the list and update each user. However, with over a million users, the memory
cost of such an operation would be too great. In general, retrieving large
numbers of Service Builder entities using dynamic queries requires too much
memory and time. 

Liferay actionable dynamic queries solve this problem. Actionable dynamic
queries use a pagination strategy to load only small numbers of entities into
memory at a time and perform processing (i.e., perform an *action*) on each
entity. So instead of trying to use a dynamic query to load a million users into
memory and then perform some processing on each of them, a much better strategy
is to use an actionable dynamic query. This way, you can still process your
million users, but only small numbers are loaded into memory at a time. 

Here's how to use actionable dynamic query:

1.  Get an
    [`ActionableDynamicQuery`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ActionableDynamicQuery.html)
    from your `*LocalService` by invoking its `getActionableDynamicQuery` 
    method. 

2.  Add query criteria and constraints, using the query's `setAddCriteriaMethod`
    and `setAddOrderCriteriaMethod` methods. 

3.  Set an action to perform on the matching entities, using 
    `setPerformActionMethod`. 

4.  Execute the action on each matching entity, by invoking the query's 
    `performActions` method. 

This method from a sample portlet creates an actionable dynamic query, adds a
query restriction and an action, and executes the query:

```java
protected void massUpdate() {
    ActionableDynamicQuery adq = _barLocalService.getActionableDynamicQuery();
    
    adq.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
        
        @Override
        public void addCriteria(DynamicQuery dynamicQuery) {
            dynamicQuery.add(RestrictionsFactoryUtil.lt("field3", 100));
        }
        
    });
    
    adq.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Bar>() {
        
        @Override
        public void performAction(Bar bar) {
            int field3 = bar.getField3();
            field3++;
            bar.setField3(field3);
            _barLocalService.updateBar(bar);
        }
        
    });
    
    try {
        adq.performActions();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
```
 
The example method demonstrates executing an actionable dynamic query on `Bar`
entities that match certain criteria. 

1.  Retrieve an 
    [`ActionableDynamicQuery`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ActionableDynamicQuery.html)
    from local service `BarLocalService`.

    ```java
    ActionableDynamicQuery adq = _barLocalService.getActionableDynamicQuery();
    ```

    | **Note:** Service Builder generates method `getActionableDynamicQuery()` in
    | each entity's `*LocalService` interface and implements it in each entity's
    | `*BaseLocalServiceImpl` class.
    | 
    |     @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    |     public ActionableDynamicQuery getActionableDynamicQuery();

2.  Set query criteria to match `field3` values less than `100`.

    ```java
    adq.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {

         @Override
         public void addCriteria(DynamicQuery dynamicQuery) {
             dynamicQuery.add(RestrictionsFactoryUtil.lt("field3", 100));
         }

     });
     ```

3.  Set an action to perform. The action increments the matching entity's 
    `field3` value. 

    ```java
    adq.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Bar>() {

         @Override
         public void performAction(Bar bar) {
             int field3 = bar.getField3();
             field3++;
             bar.setField3(field3);
             _barLocalService.updateBar(bar);
         }

     });
     ```

4.  Execute the action on each matching entity. 

    ```java
    try {
        adq.performActions();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    ```

Actionable dynamic queries let you act on large numbers of entities in smaller
groups. It's an efficient and high performing way to update entities. 

## Related Topics

[Service Builder Web Services](/docs/7-2/appdev/-/knowledge_base/a/service-builder-web-services)

[Creating Local Service](/docs/7-2/appdev/-/knowledge_base/a/creating-local-services)

[Invoking Local Services](/docs/7-2/appdev/-/knowledge_base/a/invoking-local-services)
