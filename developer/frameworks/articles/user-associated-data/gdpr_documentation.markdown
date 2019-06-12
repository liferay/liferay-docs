# Search, Filtering, and Hierarchy in the GDPR Data Erasure app

Version 7.2 introduces a few refinements the GDPR Delete Peronsal Data API and interface. The user interface now supports three new features

1. **Filtering** - User content can now be viewed and acted upon based on whether it is part of the user's personal site, some other site, or the overall company.
2. **Search** - A search bar will now allow for filtering down content based on a search term.
3. **Hierarchy Display** - If there are multiple types of content that relate to one another in a hierarchy, you can define that relationship using a `UADHierarchyDeclaration`, and the user interface will display that hierarchy (e.g. files and folders).

Let's explore each of these.

## Filtering and Searching

You can take advantage of both of these features by implementing three methods on `UADDisplay`:

- `isSiteScoped`
- `search`
- `searchCount`

The `isSiteScoped` method should return a boolean based on whether or not the entities can be associated with a particular site. This is used to determine which filter they will be associated with ("instance", "personal-site", or "regular-sites").

The `search` method takes the following parameters:

- `userId` - the userId of the user whose data is being acted on.
- `groupIds` - this is an array of groupIds used to filter which data is shown by which groups it is associated with. In the case that no groupIds are given (it can be null), the search method should return data that is not scoped to any given group.
- `keywords` - this will be whatever is typed into the search bar. The search method should filter by whatever fields are relevant for the given entity.
- `orderByField` - the name of the field used to sort the results. This will be one of the names returned by `getSortingFieldNames`.
- `orderByType` - whether to sort the results in ascending order or descending order.  Will be one of "asc" or "desc".
 For pagination.
- `end` - the ending index of the result set. For pagination.

The `searchCount` method takes the following parameters, and should treat them identically to the ones in `search`:

- `userId`
- `groupIds`
- `keywords`

## Hierarchy Display

The Hieararcy Display SPI is completely optional, and should only be implemented when it is determined that viewing entities in a hierarchy is beneficial to an administrator's ability to make sense of the data.

In order to implement a hierarchy display, you'll need to do two things:

- Implement a `UADHierarchyDeclaration`
- Implement the proper methods in `UADDisplay` for each type involved in the hieararchy.

Once these are implemented, a hierarchy view will be displayed for any of the types returned in the `UADHierarchyDeclaration` instead of the normal flat list. For containers, a count will be calculated and displayed using the hierarchy-related methods in `UADDisplay`.

### 1. Implement a `UADHierarchyDeclaration`

The `UADHierarchyDeclaration` is responsible to define which types are part of the hierarchy.  There are two classifications for a type in a hierarchy: a *container*, and a *noncontainer*. These are defined by the following two methods:

`getContainerUADDisplays`
`getNoncontainerUADDisplays`

Each returns an array of one or more classes. The *containers* can be parents or children in the hierarchy. An example of this would be a folder in a filesystem. A folder can contain both files and other folders.  The *noncontainers* can only be children in a hierarcy.  An example of this would be a file in a filesystem.

Additionally, the `UADHierarchyDeclaration` provides some other methods for display purposes.

A label for the hierarchy is provided through the `getEntitiesTypeLabel` method.

If any additional information is to be displayed in the table for any of the entity types in addition to the count, those column names should be returned by `getExtraColumnNames`. This is optional.

See [DLUADHierarchyDeclaration](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-uad/src/main/java/com/liferay/document/library/uad/display/DLUADHierarchyDeclaration.java) for an exmaple.

### 2. Implement the `UADDisplay` hierarchy methods

Each type involved in the hierarchy should implement some additional methods in `UADDisplay`.

- `getName` - returns a display name for the given entity.
- `getParentContainerClass` - returns the class of the type that contains this type.  It can return itself (for example, a folder can contain a folder).
- `getParentContainerId` - returns the primary key of the container that contains the entity passed to this method.
- `isUserOwned` - returns whether or not the given entity is owned by the user

Additionally, the following method should be implemented by all container classes.

- `getTopLevelContainer` - this method is only to be implemented by container types.

This method is used to derive the count of how many user-owned entities are contained inside a given container's tree.

This method answers the question "which type `T` ancestor of `childObject` is an immediate child of the container identified by `parentContainerClass` and `parentContainerId`?". The method may return null, meaning that `childObject` is not a child of the parent container at all.

This method is the most complicated to implement and requires some consideration for each case. Refer to the test case for examples of the requirements used for `DLFolder`: [DLFolderUADDisplayTest#testGetTopLevelContainer](https://github.com/liferay/liferay-portal/blob/c8f78609353d6a83a0b755b0bbf93764959821ee/modules/apps/document-library/document-library-uad-test/src/testIntegration/java/com/liferay/document/library/uad/display/test/DLFolderUADDisplayTest.java#L67)

And see the actual implementation for `DLFolder` here: [DLFolderUADDisplay#getTopLevelContainer](https://github.com/liferay/liferay-portal/blob/c8f78609353d6a83a0b755b0bbf93764959821ee/modules/apps/document-library/document-library-uad/src/main/java/com/liferay/document/library/uad/display/DLFolderUADDisplay.java#L105)
