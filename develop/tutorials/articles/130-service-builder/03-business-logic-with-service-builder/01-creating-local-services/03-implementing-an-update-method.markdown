# Implementing update and delete Methods [](id=implementing-update-and-delete-methods)

After you've implementing an
[`add*` method](/develop/tutorials/-/knowledge_base/7-1/implementing-an-add-method) for 
creating service entities, you'll want to create
[`update*`](#implementing-an-update-method)
and
[`delete*`](#implementing-a-delete-method)
methods for updating and deleting them. They're easy to implement. The main
difference between them and the `add*` method is they must know which entity
they're updating or deleting. 

## Implementing an update method [](id=implementing-an-update-method)

An `update*` method for a local service resembles an
[`add*` method](/develop/tutorials/-/knowledge_base/7-1/implementing-an-add-method)
most because it has parameters for setting entity attribute values. Create an
`update*` method this way:

1.  [Declare an `update*` method with parameters for updating the entity.](#declare-an-update-method-with-parameters-for-updating-the-entity)

2.  [Validate the parameters.](#validate-the-parameters)

3.  [Retrieve the entity instance, if necessary.](#retrieve-the-entity-instance)

4.  [Update the entity attributes.](#update-the-entity-attributes)

5.  [Persist the updated entity.](#persist-and-return-the-updated-entity)

6.  [Run Service Builder.](#run-service-builder)

The following code snippets from 
[`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)'s
`updateEntry` method are helpful to examine. 

	public BookmarksEntry updateEntry(
			long userId, long entryId, long groupId, long folderId, String name,
			String url, String description, ServiceContext serviceContext)
		throws PortalException {

		// Entry

		BookmarksEntry entry = bookmarksEntryPersistence.findByPrimaryKey(
			entryId);

		if (Validator.isNull(name)) {
			name = url;
		}

		validate(url);

		entry.setFolderId(folderId);
		entry.setTreePath(entry.buildTreePath());
		entry.setName(name);
		entry.setUrl(url);
		entry.setDescription(description);
		//...

		bookmarksEntryPersistence.update(entry);

        // Integrate with more Liferay services here ...

		return entry;
	}

This method has all the makings of a good `update*` method:

- parameter for looking up the entity instance
- parameters for updating the entity attributes
- parameter validation
- entity attribute updates
- entity persistence
- returns the entity instance

Refer to the example method above as you follow the steps to create your own
`update*` method. 

### Step 1: Declare an update method with parameters for updating the entity [](id=declare-an-update-method-with-parameters-for-updating-the-entity)

Create a public method for updating your application's entity. 

    public [ENTITY] update[ENTITY](...)
        throws PortalException {
        
    } 
 
Replace `[ENTITY]` with your entity's name or nickname.  Create a parameter list
that satisfies the entity attributes you're updating. Include an entity instance
parameter or an ID parameter for fetching the entity instance.

For example, the `BookmarksEntryLocalServiceImpl`'s `updateEntry` method
signature has an ID parameter (`entryId`) for fetching the `BookmarksEntry`
entity instance. Also it has parameters `folderId`, `name`, `url`, and
`description` for updating the `BookmarksEntry`'s respective attributes. 

    public BookmarksEntry updateEntry(
            long userId, long entryId, long groupId, long folderId, String name,
            String url, String description, ServiceContext serviceContext)
        throws PortalException {...} 

Note, user ID, group ID, and service context parameters are useful for
integrating with Liferay's services. More on that later. 

### Step 2: Validate the parameters [](id=validate-the-parameters)

Similar to validating the
[`add*` method](/develop/tutorials/-/knowledge_base/7-1/implementing-an-add-method)
parameters, validate your `update*` parameters. Your `add*` and `update*`
methods might be able to use the same validation code. Throw a 
[`PortalException`](@platform-ref@/javadocs/portal-kernel/com/liferay/portal/kernel/exception/PortalException.html)
or an extension of `PortalException` for any invalid parameters. 

### Step 3: Retrieve the entity instance [](id=retrieve-the-entity-instance)

If you're passing in an entity instance, you can update it directly. Otherwise,
pass in the entity ID (the primary key).  The `*Persistence` class Service
Builder injects into `*BaseLocalServiceImpl` classes has a
`findByPrimaryKey(long)` method that retrieves instances by ID. For example, the [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
retrieves the `BookmarksEntry` that matches the primary key `entryId`. 

    BookmarksEntry entry = bookmarksEntryPersistence.findByPrimaryKey(
        entryId);
 
Invoke the `findByPrimaryKey(long id)` method of your `*Persistence` class to
retrieve the entity instance that matches your primary key parameter. 

     [ENTITY] entity = [ENTITY]Persistence.findByPrimaryKey(id);

It's time to update the entity attributes. 

### Step 4: Update the entity attributes [](id=update-the-entity-attributes)

Invoke the entity's setter methods to replace its attribute values. 

### Step 5: Persist and return the updated entity instance [](id=persist-and-return-the-updated-entity)

Persist the updated entity to the database and return the instance to the
caller. 

    [ENTITY]Persistence.update(entity);
    
    ...

    return entity;

### Step 6: Run Service Builder [](id=run-service-builder)

Finally, run Service Builder to propagate your new service method to the
`*LocalService` interface. 

You've created a service method to update your entity. If you thought that was
easy, implementing a `delete*` method is even easier. 

## Implementing a delete method [](id=implementing-a-delete-method)

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
[`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)'s 
`deleteEntry` method:

    public BookmarksEntry deleteEntry(BookmarksEntry entry)
        throws PortalException {

        bookmarksEntryPersistence.remove(entry);

        // Clean up related to additional Liferay services goes here ...  

        return entry;
    }

After implementing your `delete*` method, run Service Builder to propagate your
new service method to the `*LocalService` interface. 

## Related Topics [](id=related-topics)

[Implementing an add method](/develop/tutorials/-/knowledge_base/7-1/implementing-an-add-method)

Implementing getter and counter methods 

Integrating with Liferay services 
