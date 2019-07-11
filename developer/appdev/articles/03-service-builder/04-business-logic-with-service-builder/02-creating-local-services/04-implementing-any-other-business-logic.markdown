---
header-id: implementing-any-other-business-logic
---

# Implementing Any Other Business Logic

[TOC levels=1-4]

This section's earlier local service articles focus on CRUD methods: methods
that **c**reate (add), **r**ead (get), **u**pdate, and **d**elete entities. But
you might also need methods that provide business logic. 

Since the Guestbook application doesn't have any business logic, the Bookmarks
application, which is extremely similar, (Bookmark Folders and Bookmark entries
instead of Guestbooks and Guestbook entries) is used here to illustrate simple
business logic. Bookmarks application users *open* bookmarks (navigate to a
URLs) by clicking on them. 
[`BookmarksEntryLocalServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)'s 
`openEntry` method supports this functionality: 

```java
public BookmarksEntry openEntry(long userId, BookmarksEntry entry) {
    entry.setVisits(entry.getVisits() + 1);

    bookmarksEntryPersistence.update(entry);

    assetEntryLocalService.incrementViewCounter(
        userId, BookmarksEntry.class.getName(), entry.getEntryId(), 1);

    return entry;
}
```

The `openEntry` method tracks and persists the number of visits to the
`BookmarksEntry`'s URL, increments the number of views for the `BookmarksEntry`
as an asset, and  returns the `BookmarksEntry`. This method implements required
business logic that compliments the CRUD methods. 

*Convenience methods* might also be appropriate for your app. They're easier to
use because they typically have these characteristics: 

- Shorter parameter list
- Intuitive name

Short parameter lists are easier to satisfy, and methods that have intuitive
names are easier to find in Javadoc.

For example, the Bookmarks application lets users move bookmarks to different
folders. Moving a bookmark can be done using the service's `updateEntry(...)`
method, but its long parameter list is overkill since all the operation requires
is the bookmarks entry and the folder where it's going. Compare the following
`update*` method call to a convenience method call. 

**Update method**:

```java
bookmarksEntryLocalService.updateEntry(userId, entryId, groupId, folderId, name, url, description, serviceContext);
```

**Convenience method**:

```java
bookmarksEntryLocalService.moveEntry(entryId, folderId);
```

Here's the [`moveEntry`
method](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java):

```java
public BookmarksEntry moveEntry(long entryId, long parentFolderId)
    throws PortalException {

    BookmarksEntry entry = getBookmarksEntry(entryId);

    entry.setFolderId(parentFolderId);
    entry.setTreePath(entry.buildTreePath());

    bookmarksEntryPersistence.update(entry);

    return entry;
}
```

The `moveEntry` method retrieves the `BookmarksEntry` entity by its ID, assigns
it a new parent folder, updates its tree path, persists all the entity's
changes, and returns the entity. Convenience methods like this one facilitate
updating a subset of the entity's attributes. 

After implementing your custom business methods, 
[run Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder)
to propagate them to the interface.

In your local services, you can implement business logic methods that suit your
application. 

**Related Topics**

[Creating Local Services](/docs/7-2/appdev/-/knowledge_base/a/creating-local-services)

[Invoking Local Services](/docs/7-2/appdev/-/knowledge_base/a/invoking-local-services)

[Invoking Services from Service Builder Code](/docs/7-2/appdev/-/knowledge_base/a/invoking-services-from-service-builder-code)
