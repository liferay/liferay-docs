---
header-id: implementing-an-add-method
---

# Implementing an Add Method

[TOC levels=1-4]

Your `*LocalServiceImpl` represents your service layer, where you create the
business logic that operates on your application's data and then calls the
persistence layer to persist, retrieve, or delete your data, using the object
model defined in `service.xml`. 

One of the first methods you'll likely implement is one that creates entities.
Liferay's convention is to implement this in an `add*` method, where the part
after `add` is the entity name (or a shortened version of it). Here are the
steps for implementing an `add*` method:

1.  [Declare an `add*` method with parameters for creating the entity.](#declare-an-add-method-with-parameters-for-creating-the-entity)

2.  [Validate the parameters.](#validate-the-parameters)

3.  [Generate a primary key.](#generate-a-primary-key)

4.  [Create an entity instance.](#create-an-entity-instance)

5.  [Populate the entity attributes.](#populate-the-entity-attributes)

6.  [Persist the entity.](#persist-the-entity)

7.  [Return the entity instance.](#return-the-entity)

This article refers to the Guestbook application's `addGuestbookEntry` method
from `GuestbookEntryLocalServiceImpl`. To keep things simple, we have excluded
the code that integrates with Liferay services, such as assets, social
bookmarks, and more. 

Here's the Guestbook application's `addGuestbookEntry` method:

```java
public GuestbookEntry addEntry(long userId, long guestbookId, String name, String email, String message,
        ServiceContext serviceContext) throws PortalException {

    long groupId = serviceContext.getScopeGroupId();

    User user = userLocalService.getUserById(userId);

    Date now = new Date();

    validate(name, email, message);

    long entryId = counterLocalService.increment();

    GuestbookEntry entry = guestbookEntryPersistence.create(entryId);

    entry.setUuid(serviceContext.getUuid());
    entry.setUserId(userId);
    entry.setGroupId(groupId);
    entry.setCompanyId(user.getCompanyId());
    entry.setUserName(user.getFullName());
    entry.setCreateDate(serviceContext.getCreateDate(now));
    entry.setModifiedDate(serviceContext.getModifiedDate(now));
    entry.setExpandoBridgeAttributes(serviceContext);
    entry.setGuestbookId(guestbookId);
    entry.setName(name);
    entry.setEmail(email);
    entry.setMessage(message);

    guestbookEntryPersistence.update(entry);

    // Calls to other Liferay frameworks go here

    return entry;
}
```

This method uses the parameters to create `GuestbookEntry`. It validates the
parameters, creates an entry with a generated entry ID (primary key), populates
the entry, persists the entry, and returns it. You can refer to this method as
you create your own `add*` method. Note that there's no real business logic
here; it's a simple application that takes data the user entered, validates it,
and then persists it to the database. 

## Step 1: Declare an add method with parameters for creating the entity

Create a public method for *adding* (creating) your application's entity. Make
it a public method that returns the entity it creates. 

    public [ENTITY] add[ENTITY](...) {
        
    } 

For example, here's the `addEntry` method signature:

```java
public GuestbookEntry addEntry(long userId, long guestbookId, 
    String name, String email, String message, 
    ServiceContext serviceContext) throws PortalException {
    ...
    }
```

This method specifies all the parameters needed to create and populate a
`GuestbookEntry` as you specified them in your `service.xml` file. It throws
a `PortalException` in case the parameters are invalid or a processing exception
occurs (more on this in a later step). 

Make sure to account for primary keys of other related entities. For example,
the `addEntry` method above includes a parameter `long guestbookId` to associate
the new `GuestbookEntry` to a `Guestbook`. 

## Step 2:  Validate the parameters

Validate the parameters as needed. You might need to make sure a parameter is
not empty or `null`, or that a parameter value is within a valid range. Throw a
[`PortalException`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/exception/PortalException.html)
or an extension of `PortalException` for any invalid parameters.

For example, the `addEntry` method invokes the following `validate` method to
check if the URL parameter is `null`.

```java
protected void validate(String name, String email, String entry) throws PortalException {

    if (Validator.isNull(name)) {
        throw new GuestbookEntryNameException();
    }

    if (!Validator.isEmailAddress(email)) {
        throw new GuestbookEntryEmailException();
    }

    if (Validator.isNull(entry)) {
        throw new GuestbookEntryMessageException();
    }
}
```

Next, generate a primary key for the entity instance you're creating. 

## Step 3: Generate a primary key

Entities must each have a unique primary key. Liferay's
[`CounterLocalService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/counter/kernel/service/CounterLocalService.html) 
generates them per entity. Every `*BaseLocalServiceImpl` has a
`counterLocalService` field that references a `CounterLocalService` object for
the entity. Invoke the counter service's `increment` method to generate a
primary key for your entity instance.

```java
long id = counterLocalService.increment();
```

Now you have a unique ID for your entity instance. Always generate primary keys
in this way, as it ensures your code is compatible with all the databases
Liferay supports.

## Step 4: Create an entity instance

The `*Peristence` instance associated with your entity has a `create(long id)`
method that constructs an entity instance with the given ID. Every
`*BaseLocalServiceImpl` has a `*Persistence` field that references a
`*Persistence` object for the entity. For example,
`GuestbookEntryLocalServiceImpl` as a child of
`GuestbookEntryLocalServiceBaseImpl` has a field `guestbookEntryPersistence`,
which is a reference to a `GuestbookEntryPersistence` instance. 

    @Reference
    protected GuestbookEntryPersistence guestbookEntryPersistence;

`GuestbookEntryLocalServiceImpl`'s `addEntry` method creates a `GuestbookEntry`
instance using this call:

    GuestbookEntry entry = guestbookEntryPersistence.create(entryId);

To create an instance of your entity, invoke the `create` method on the
`*Persistence` field associated with the entity, making sure to pass in the
entity primary key you generated in the previous step.

     [ENTITY_NAME] entity = [ENTITY_NAME]Persistence.create(id);

It's time to populate the new entity instance. 

## Step 5: Populate the entity attributes

Use the `add*` method parameter values and the entity's setter methods to
populate your entity's attributes. For example, here are the `GuestbookEntry`
attribute assignments:

```java
entry.setUuid(serviceContext.getUuid());
entry.setUserId(userId);
entry.setGroupId(groupId);
entry.setCompanyId(user.getCompanyId());
entry.setUserName(user.getFullName());
entry.setCreateDate(serviceContext.getCreateDate(now));
entry.setModifiedDate(serviceContext.getModifiedDate(now));
entry.setExpandoBridgeAttributes(serviceContext);
entry.setGuestbookId(guestbookId);
entry.setName(name);
entry.setEmail(email);
entry.setMessage(message);
```

Note that the `ServiceContext` is commonly used to carry an entity's UUID and
the `User` is associated to a company. 

## Step 6: Persist the entity

It's time to store the entity. Invoke the `*Persistence` field's `update`
method, passing in the entity object. For example, here's how the new
`GuestbookEntry` is persisted:

```java
guestbookEntryPersistence.update(entry);
```

Your entity is persisted for the application. 

## Step 7: Return the entity

Finally, return the entity you just created so the caller can use it. 

[Run Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder)
to propagate your new service method to the `*LocalService` interface. 

You've implemented your local service's `add*` method to create and persist your
application's entities.
