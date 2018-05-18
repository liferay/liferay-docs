# Implementing Methods to Get and Count Entities [](id=implementing-methods-to-get-and-count-entities)

Service Builder generates `findBy*` methods and `countBy*` methods in your
[`*Persistence` classes](/develop/tutorials/-/knowledge_base/7-1/understanding-the-code-generated-by-service-builder)
based on your  `service.xml` file's
[finders](/develop/tutorials/-/knowledge_base/7-1/defining-service-entity-finder-methods).
You can leverage finder methods in your local services to get and count
entities.

-   [Getters](#getter-methods): `get*` methods return entity instances matching 
    criteria.
-   [Counters](#counter-methods): `get*Count` methods return the number of 
    instances matching criteria

Start with getting entities that match criteria. 

## Getter Methods [](id=getter-methods)

The `findByPrimaryKey` methods and `findBy*` methods search for and return
entity instances based on criteria. Your local service implementation must only
wrap calls to the finder methods that get what you want.

Here's how to create a method that gets an entity based on an ID (primary key):

1.  Create a method using this format:

        public [ENTITY] get[ENTITY_NAME](long id) {
            return [ENTITY]Persistence.findByPrimaryKey(id);
        }

2.  Replace `[ENTITY]` and `[ENTITY_NAME]` with the respective entity type and
    entity name (or nickname).

3.  Run Service Builder to propagate the method to your local service 
    interface. 

Here's how to get entities based on criteria:

1.  Identify the criteria for finding the entity instance(s). 

2.  If there is no
    [`finder` element](/develop/tutorials/-/knowledge_base/7-1/defining-service-entity-finder-methods)
    for the criteria, create one for it and run Service Builder. 

3.  Determine the
    [`*Persistence` class](/develop/tutorials/-/knowledge_base/7-1/understanding-the-code-generated-by-service-builder)
    `findBy*` method you want to call.  Depending on your `finder` element
    columns, Service Builder might overload the method to include these
    parameters:
 
    -   `int start` and `int end` parameters for specifying a range of 
        entities.
    -   `com.liferay.portal.kernel.util.OrderByComparator orderByComparator` 
        parameter for arranging the matching entities. 

4.  Specify your `get*` method signature, making sure to account for the 
    `*Persistence` class `findBy*` method parameters you must satisfy. Use this 
    method format: 

        public List<[ENTITY]> get[DESCRIBE_THE_ENTITIES](...) {
            
        }

    Replace `[ENTITY]` with the entity type. Replace `[DESCRIBE_THE_ENTITIES]`
    with a descriptive name for the entities you're getting. 

6.  Call the `*Persistence` class `findBy*` method and return the list of 
    matching entities. 

7.  Run Service Builder. 

For example, method `getGroupEntries` from [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.1.0-a1/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
returns a range of `BookmarksEntry`s associated with a `Group` primary key: 

    public List<BookmarksEntry> getGroupEntries(
        long groupId, int start, int end) {

        return bookmarksEntryPersistence.findByG_S(
            groupId, WorkflowConstants.STATUS_APPROVED, start, end,
            new EntryModifiedDateComparator());
    }

Of the `BookmarksEntry`s associated with workflows, this method only matches
approved ones (`WorkflowConstants.STATUS_APPROVED`). It uses a new
`EntryModifiedDateComparator` to order the matching `BookmarksEntry`s by
modification date. 

Now you know how to leverage finder methods to get entities. Methods that count
entities are next.

## Counter Methods [](id=counter-methods)

Counting entities is just as easy as getting them. Your `*Persistence` class
`countBy*` methods do all the work. Service Builder generates `countBy*` methods
based on each finder and its columns. 

1.  Identify the criteria for entity instances you're counting and determine
    the
    [`*Persistence` class](/develop/tutorials/-/knowledge_base/7-1/understanding-the-code-generated-by-service-builder)
    `countBy*` method that satisfies the criteria.

2.  Create a `get*Count` method signature following this format:

        public int get[DESCRIBE_THE_ENTITIES]Count(...) {
            
        }

    Replace `[DESCRIBE_THE_ENTITIES]` with a descriptive name for the entities
    you're counting.

3.  Call the `*Persistence` class' `countBy` method and return the value. For 
    example, the method `getEntriesCount` from
    [`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.1.0-a1/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
    returns the number of `BookmarksEntry`s that have a workflow status of
    `status` and that are associated with a group (matching `groupId`) and a
    folder (matching `folderId`). 

        public int getEntriesCount(long groupId, long folderId, int status) {
    		return bookmarksEntryPersistence.countByG_F_S(
    			groupId, folderId, status);
    	}

Now your local service can get entities matching your criteria and return quick
entity counts. 

## Related Topics [](id=related-topics)

[Creating Local Services](/develop/tutorials/-/knowledge_base/7-1/creating-local-services)

[Implementing an add method](/develop/tutorials/-/knowledge_base/7-1/implementing-an-add-method)

[Defining Service Entity Finder Methods](/develop/tutorials/-/knowledge_base/7-1/defining-service-entity-finder-methods)

[Understanding the Code Generated by Service Builder](/develop/tutorials/-/knowledge_base/7-1/understanding-the-code-generated-by-service-builder)
