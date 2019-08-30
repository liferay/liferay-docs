---
header-id: enhancing-the-data-erasure-ui
---

# Enhancing the Data Erasure UI

[TOC levels=1-4]

As of @product-ver@, there are new features that enhance an administrator's
experience finding data in the Personal Data Erasure UI:

**Filtering**
: User content can now be viewed and acted upon based on
   whether it is part of the User's personal Site, some other Site, or the
   overall company.

**Search**
: A search bar now allows for filtering content based on a search term.

**Hierarchy Display**
: If there are multiple types of content that are related in a
hierarchy, you can define that relationship using a `UADHierarchyDeclaration`,
and the user interface shows that hierarchy (e.g. files and folders).

## Filtering and Searching in the Data Erasure UI

To support filtering and searching in your custom entities, implement three
methods in the `UADDisplay` class (found in your `-uad` module):

- `isSiteScoped`
- `search`
- `searchCount`

The `isSiteScoped` method returns a boolean, determining if the entity can be
associated with a particular Site. This is used to determine which filter they
are associated with ("instance", "personal-site", or "regular-sites").

![Figure 1: Items in the Personal Data Erasure screen can be filtered by scope.](../../images/uad-scope-filter.png) 

The `search` method takes the following parameters:

`userId`: The `userId` of the selected User.

`groupIds`: An array of `groupId`s used to filter which data is shown
by which groups it is associated with. In the case that no `groupId`s are
given (it can be null), the search method should return data that is not
scoped to any given group.

`keywords`: The contents of the search bar. The search method should filter by
whatever fields are relevant for the given entity.

`orderByField`: The name of the field used to sort the results. This is
one of the names returned by `getSortingFieldNames`.

`orderByType`: Sort the results in ascending order or descending
order (`asc` or `desc`), for pagination.

`start`: The starting index of the result set. For pagination.

`end`: The ending index of the result set. For pagination.

The `searchCount` method takes the following parameters, which are treated
identically to the ones in `search`:

- `userId`

- `groupIds`

- `keywords`

Read
[here](/docs/7-2/frameworks/-/knowledge_base/f/filtering-and-searching-uad-marked-entities)
for instructions on how to implement search and filtering for your entity. 

## Hierarchy Display

Hierarchical UAD display optionally shows entities with a natural parent-child
relationship (for example, Document Library Folders and Document Library File
Entries). Viewing these entities in a hierarchy helps administrators make sense
of the data they're reviewing for possible erasure.

![Figure 2: Hierarchical representation of nested entities is useful for administrators reviewing User data for possible deletion.](../../images/uad-hierarchy.png)

To implement a hierarchy display, you must do two things:

1.  Implement a
    [`UADHierarchyDeclaration`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/user-associated-data/user-associated-data-api/src/main/java/com/liferay/user/associated/data/display/UADHierarchyDeclaration.java)
    class.

2.  Add a method to the `*UADDisplay` class for each type involved in the
    hierarchy.

Once implemented, a hierarchy view is displayed for any of the types returned in
the `UADHierarchyDeclaration`. For container entities, a count of all child
entities is calculated and displayed using the hierarchy-related methods in
`UADDisplay`.

### UAD Hierarchy Declaration

The `UADHierarchyDeclaration` defines the types in the hierarchical
relationship. There are two classifications for a type in a hierarchy: a
*container*, and a *non-container*. These are defined by
`getContainerUADDisplays` and `getNoncontainerUADDisplays`.

Each returns an array of one or more `UADDisplay` classes. Containers can be
parents or children in the hierarchy. An example is a folder in a file system,
which can contain both files and other folders. The non-container entities can
only be children in the hierarchy. An example is a file in a file
system.

The `UADHierarchyDeclaration` provides some other methods for display purposes.

- A label for the hierarchy is provided through the `getEntitiesTypeLabel`
  method.

- If any additional information is to be displayed in the table for any of the
  entity types in addition to the count, those column names should be returned
  by `getExtraColumnNames`. This is optional.

See [`DLUADHierarchyDeclaration`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-uad/src/main/java/com/liferay/document/library/uad/display/DLUADHierarchyDeclaration.java) for an example.

### Add methods to UADDisplay

Each type involved in the hierarchy should implement some additional methods in
`UADDisplay`.

These methods must be implemented by containers and non-containers alike, in the
`*UADDisplay` class:

`getName` returns a display name for the given entity.

`getParentContainerClass` returns the class of the type that contains this type.
It can return itself (for example, a folder can contain a folder).

`getParentContainerId` returns the primary key of the container that contains
the entity passed to this method.

`isUserOwned` returns whether or not the given entity is owned by the user. 

Additionally, implement `getTopLevelContainer` in the `*UADDisplay` class for all types
classified as _containers_. It's used to derive the count of how many user-owned
entities are contained inside a given container's tree. It answers the question
"which type `T` ancestor of `childObject` is an immediate child of the container
identified by `parentContainerClass` and `parentContainerId`?" The method may
return `null` if `childObject` is not a child of the parent container. This
method is the most complicated to implement and requires some consideration for
each case. Refer to the test case for examples of the requirements used for
`DLFolder`:
[DLFolderUADDisplayTest#testGetTopLevelContainer](https://github.com/liferay/liferay-portal/blob/c8f78609353d6a83a0b755b0bbf93764959821ee/modules/apps/document-library/document-library-uad-test/src/testIntegration/java/com/liferay/document/library/uad/display/test/DLFolderUADDisplayTest.java#L67)

See the actual implementation for `DLFolder` in
[DLFolderUADDisplay#getTopLevelContainer](https://github.com/liferay/liferay-portal/blob/c8f78609353d6a83a0b755b0bbf93764959821ee/modules/apps/document-library/document-library-uad/src/main/java/com/liferay/document/library/uad/display/DLFolderUADDisplay.java#L105).

The method returns either `null` or the container object of type T that is the
top level container of the `childObject` (which could be any type of object that
is a part of the hierarchy). This container does not necessarily have to be
owned by the user, but is understood to contain data related to the user. This
information is used to count how much user data is inside the container
designated by `parentContainerClass` and `parentContainerId`.

```java
@Override
public DLFolder getTopLevelContainer(
    Class<?> parentContainerClass, Serializable parentContainerId,
    Object childObject) {

    try {
        DLFolder childFolder = null;

        if (childObject instanceof DLFileEntry) {
            DLFileEntry dlFileEntry = (DLFileEntry)childObject;

            childFolder = dlFileEntry.getFolder();
        }
        else {
            childFolder = (DLFolder)childObject;
        }

        long parentFolderId = (long)parentContainerId;

        if ((childFolder.getFolderId() == parentFolderId) ||
            ((parentFolderId !=
                DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) &&
             !StringUtil.contains(
                 childFolder.getTreePath(), String.valueOf(parentFolderId),
                 "/"))) {

            return null;
        }

        if (childFolder.getParentFolderId() == parentFolderId) {
            return childFolder;
        }

        List<Long> ancestorFolderIds = childFolder.getAncestorFolderIds();

        if (parentFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
            return get(ancestorFolderIds.get(ancestorFolderIds.size() - 1));
        }

        if (ancestorFolderIds.contains(parentFolderId)) {
            return get(
                ancestorFolderIds.get(
                    ancestorFolderIds.indexOf(parentFolderId) - 1));
        }
    }
    catch (PortalException pe) {
        _log.error(pe, pe);
    }

    return null;
}
```

The exact implementation details vary for each entity type.
