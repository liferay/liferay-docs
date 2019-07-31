---
header-id: implementing-update-and-delete-methods
---

# Implementing Update and Delete Methods

[TOC levels=1-4]

After you've implementing an
[`add*` method](/docs/7-2/appdev/-/knowledge_base/a/implementing-an-add-method) for 
creating service entities, you'll want to create
[`update*`](#implementing-an-update-method)
and
[`delete*`](#implementing-a-delete-method)
methods for updating and deleting them. The main
difference between these and the `add*` method is they must know which entity
they're updating or deleting. 

## Implementing an Update Method

An `update*` method for a local service resembles an
[`add*` method](/docs/7-2/appdev/-/knowledge_base/a/implementing-an-add-method)
most because it has parameters for setting entity attribute values. Create an
`update*` method this way:

1.  [Declare an `update*` method with parameters for updating the entity.](#declare-an-update-method-with-parameters-for-updating-the-entity)

2.  [Validate the parameters.](#validate-the-parameters)

3.  [Retrieve the entity instance, if necessary.](#retrieve-the-entity-instance)

4.  [Update the entity attributes.](#update-the-entity-attributes)

5.  [Persist the updated entity.](#persist-and-return-the-updated-entity)

6.  [Run Service Builder.](#run-service-builder)

The following code snippets from 
`GuestbookEntryLocalServiceImpl`'s `updateEntry` method are helpful to examine. 

```java
public GuestbookEntry updateEntry(long userId, long guestbookId, long entryId, String name, String email, String message,
        ServiceContext serviceContext) throws PortalException, SystemException {

    Date now = new Date();

    validate(name, email, message);

    GuestbookEntry entry = getGuestbookEntry(entryId);

    User user = userLocalService.getUserById(userId);

    entry.setUserId(userId);
    entry.setUserName(user.getFullName());
    entry.setModifiedDate(serviceContext.getModifiedDate(now));
    entry.setName(name);
    entry.setEmail(email);
    entry.setMessage(message);
    entry.setExpandoBridgeAttributes(serviceContext);

    guestbookEntryPersistence.update(entry);

    // Integrate with Liferay frameworks here.

    return entry;
}
```

This method has all the makings of a good `update*` method:

- parameter for looking up the entity instance
- parameters for updating the entity attributes
- parameter validation
- entity attribute updates
- entity persistence
- returns the entity instance

Refer to the example method above as you follow the steps to create your own
`update*` method. 

### Step 1: Declare an Update Method with Parameters for Updating the Entity

Create a public method for updating your application's entity. 

    public [ENTITY] update[ENTITY](...)
        throws PortalException {
        
    } 
 
Replace `[ENTITY]` with your entity's name or nickname.  Create a parameter list
that satisfies the entity attributes you're updating. Include an entity instance
parameter or an ID parameter for fetching the entity instance.

For example, the `GuestbookEntryLocalServiceImpl`'s `updateEntry` method
signature has an ID parameter (`entryId`) for fetching the `GuestbookEntry`
entity instance. Also it has parameters `folderId`, `name`, `url`, and
`description` for updating the `GuestbookEntry`'s respective attributes. 

```java
public GuestbookEntry updateEntry(long userId, long guestbookId, long entryId, String name, String email, String message,
        ServiceContext serviceContext) throws PortalException, SystemException {
```

Note, user ID, group ID, and service context parameters are useful for
integrating with Liferay's services. More on that later. 

### Step 2: Validate the Parameters

Similar to validating the
[`add*` method](/docs/7-2/appdev/-/knowledge_base/a/implementing-an-add-method)
parameters, validate your `update*` parameters. Your `add*` and `update*`
methods might be able to use the same validation code. Throw a 
[`PortalException`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/exception/PortalException.html)
or an extension of `PortalException` for any invalid parameters. 

### Step 3: Retrieve the Entity Instance

If you're passing in an entity instance, you can update it directly. Otherwise,
pass in the entity ID (the primary key).  The `*Persistence` class Service
Builder injects into `*BaseLocalServiceImpl` classes has a
`findByPrimaryKey(long)` method that retrieves instances by ID. For example, the
`GuestbookEntryLocalServiceImpl` retrieves the `GuestbookEntry` with the primary
key `entryId`. 

    GuestbookEntry entry = guestbookEntryPersistence.findByPrimaryKey(
        entryId);
 
Invoke the `findByPrimaryKey(long id)` method of your `*Persistence` class to
retrieve the entity instance that matches your primary key parameter. 

     [ENTITY] entity = [ENTITY]Persistence.findByPrimaryKey(id);

It's time to update the entity attributes. 

### Step 4: Update the Entity Attributes

Invoke the entity's setter methods to replace its attribute values. 

### Step 5: Persist and Return the Updated Entity Instance

Persist the updated entity to the database and return the instance to the
caller. 

    [ENTITY]Persistence.update(entity);
    
    ...

    return entity;

### Step 6: Run Service Builder

Finally, run Service Builder to propagate your new service method to the
`*LocalService` interface. 

You've created a service method to update your entity. If you thought that was
easy, implementing a `delete*` method is even easier. 

## Implementing a Delete Method

The `remove` method of an entity's `*Persistence` class deletes an entity
instance from the database. Use it in your local service's `delete*` method.
Here's what a `delete*` method looks like:

    public [ENTITY] delete[ENTITY](ENTITY entity) throws PortalException
    {
        [ENTITY]Persistence.remove(entity);

        // Clean up related to additional Liferay services goes here ... 

        return entity;
    }

Make sure to replace `[ENTITY]` with your entity's name or nickname. 

For example, here's paraphrased code from 
`GuestbookEntryLocalServiceImpl`'s `deleteEntry` method:

    public GuestbookEntry deleteEntry(GuestbookEntry entry)
        throws PortalException {

        guestbookEntryPersistence.remove(entry);

        // Clean up related to additional Liferay services goes here ...  

        return entry;
    }

After implementing your `delete*` method, run Service Builder to propagate your 
new service method to the `*LocalService` interface. 

## Related Topics

[Implementing an add method](/docs/7-2/appdev/-/knowledge_base/a/implementing-an-add-method)
