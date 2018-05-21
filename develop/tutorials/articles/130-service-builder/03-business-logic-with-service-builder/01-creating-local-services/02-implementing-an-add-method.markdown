# Implementing an Add Method [](id=implementing-an-add-method)

Your `*LocalServiceImpl` operates on the entities your `service.xml` defines.
The first method to implement, therefore, is one that creates entities.
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

This tutorial refers to the Bookmarks application's `addEntry` method from 
[`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
as an example. To keep things simple, we have excluded the code that integrates
with Liferay services, such as assets, social bookmarks, and more. 

Here's the Bookmarks application's `addEntry` method:

	public BookmarksEntry addEntry(
			long userId, long groupId, long folderId, String name, String url,
			String description, ServiceContext serviceContext)
		throws PortalException {

		// Entry

		User user = userLocalService.getUser(userId);

		if (Validator.isNull(name)) {
			name = url;
		}

		validate(url);

		long entryId = counterLocalService.increment();

		BookmarksEntry entry = bookmarksEntryPersistence.create(entryId);

		entry.setUuid(serviceContext.getUuid());
		entry.setGroupId(groupId);
		entry.setCompanyId(user.getCompanyId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setFolderId(folderId);
		entry.setTreePath(entry.buildTreePath());
		entry.setName(name);
		entry.setUrl(url);
		entry.setDescription(description);
		// More assignments ... 

		bookmarksEntryPersistence.update(entry);

        // Integrate with more Liferay services here ...

		return entry;
	}

This method uses the parameters to create `BookmarksEntry`. It validates the
parameters, creates an entry with a generated entry ID (primary key), populates
the entry, persists the entry, and returns it. You can refer to this method as
you create your own `add*` method. 

## Step 1: Declare an add method with parameters for creating the entity [](id=declare-an-add-method-with-parameters-for-creating-the-entity)

Create a public method for *adding* (creating) your application's entity. Make
it a public method that returns the entity it creates. 

    public [ENTITY] add[ENTITY](...) {
        
    } 

For example, here's the `addEntry` method signature:

    public BookmarksEntry addEntry(
            long userId, long groupId, long folderId, String name, String url,
            String description, ServiceContext serviceContext)
        throws PortalException {
            ...
    }

This method specifies all the parameters needed to create and populate a
`BookmarksEntry`. It throws a `PortalException` in case the parameters are
invalid or a processing exception occurs (more on this in a later step). 

Your add method must specify parameters that satisfy the entity's attributes
specified in your `service.xml` file. Make sure to account for primary keys of
other related entities. For example, the `addEntry` method above includes
a parameter `long folderId` to associate the new `BookmarksEntry` to
a `BookmarksFolder`. 

## Step 2:  Validate the parameters [](id=validate-the-parameters)

Validate the parameters as needed. You might need to make sure a parameter is
not empty or `null`, or that a parameter value is within a valid range. Throw a
[`PortalException`](@platform-ref@/javadocs/portal-kernel/com/liferay/portal/kernel/exception/PortalException.html)
or an extension of `PortalException` for any invalid parameters.

For example, the `addEntry` method invokes the following `validate` method to
check if the URL parameter is `null`.

    protected void validate(String url) throws PortalException {
        if (!Validator.isUrl(url)) {
            throw new EntryURLException();
        }
    }

Next, generate a primary key for the entity instance you're creating. 

## Step 3: Generate a primary key [](id=generate-a-primary-key)

Every entity instance needs a unique primary key. Liferay's
[`CounterLocalService`](@platform-ref@/javadocs/portal-kernel/com/liferay/counter/kernel/service/CounterLocalService.html)
generates them per entity. Every `*BaseLocalServiceImpl` has a
`counterLocalService` field that references a `CounterLocalService` object for
the entity. Invoke the counter service's `increment` method to generate a
primary key for your entity instance.

    long id = counterLocalService.increment();

Now you have a unique ID for your entity instance. 

## Step 4: Create an entity instance [](id=create-an-entity-instance)

The `*Peristence` instance associated with your entity has a `create(long id)`
method that constructs an entity instance with the given ID. Every
`*BaseLocalServiceImpl` has a `*Persistence` field that references a
`*Persistence` object for the entity. For example,
[`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
can access
[`BookmarksEntryLocalServiceBaseImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/base/BookmarksEntryLocalServiceBaseImpl.java)'s
field `bookmarksEntryPersistence`, which is a reference to a
`BookmarksEntryPersistence` instance. 

    @BeanReference(type = BookmarksEntryPersistence.class)
    protected BookmarksEntryPersistence bookmarksEntryPersistence;

`BookmarksEntryLocalServiceImpl`'s `addEntry` method creates a `BookmarksEntry`
instance using this call:

    BookmarksEntry entry = bookmarksEntryPersistence.create(entryId);

To create an instance of your entity, invoke the `create` method on the
`*Persistence` field associated with the entity, making sure to pass in the
entity primary key you generated in the previous step.

     [ENTITY_NAME] entity = [ENTITY_NAME]Persistence.create(id);

It's time to populate the new entity instance. 

## Step 5: Populate the entity attributes [](id=populate-the-entity-attributes)

Use the `add*` method parameter values and the entity's setter methods to
populate your entity's attributes. For example, here are the `BookmarksEntry`
attribute assignments:

    entry.setUuid(serviceContext.getUuid());
    entry.setGroupId(groupId);
    entry.setCompanyId(user.getCompanyId());
    entry.setUserId(user.getUserId());
    entry.setUserName(user.getFullName());
    entry.setFolderId(folderId);
    entry.setTreePath(entry.buildTreePath());
    entry.setName(name);
    entry.setUrl(url);
    entry.setDescription(description);

Note that the `ServiceContext` is commonly used to carry an entity's UUID and
the `User` is associated to a company. 

## Step 6: Persist the entity [](id=persist-the-entity)

It's time to store the entity. Invoke the `*Persistence` field's `update`
method, passing in the entity object. For example, here's how the new
`BookmarksEntry` is persisted:

    bookmarksEntryPersistence.update(entry);

Your entity is persisted for the application. 

## Step 7: Return the entity [](id=return-the-entity)

Finally, return the entity you just created so the caller can use it. 

[Run Service Builder](/develop/tutorials/-/knowledge_base/7-1/running-service-builder)
to propagate your new service method to the `*LocalService` interface. 

You've implemented your local service's `add*` method to create and persist your
application's entities.
