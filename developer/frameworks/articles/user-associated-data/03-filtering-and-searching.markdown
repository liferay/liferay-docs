# Filtering and Searching UAD-Marked Entities

When you 

To add filtering and searching for your custom entities, implement three
methods in the `UADDisplay` class (in your application's `-uad` module):

## Implement isSiteScoped

The `isSiteScoped` method returns a boolean based on whether or not the
entities can be associated with a particular site. This is used to determine
which filter they will be associated with ("instance", "personal-site", or
"regular-sites").

NEED CODE

## Implement search

The `search` method takes the following parameters:

- `userId` - the `userId` of the User whose data is being acted on.
- `groupIds` - this is an array of groupIds used to filter which data is shown by which groups it is associated with. In the case that no groupIds are given (it can be null), the search method should return data that is not scoped to any given group.
- `keywords` - this will be whatever is typed into the search bar. The search method should filter by whatever fields are relevant for the given entity.
- `orderByField` - the name of the field used to sort the results. This will be one of the names returned by `getSortingFieldNames`.
- `orderByType` - whether to sort the results in ascending order or descending order.  Will be one of "asc" or "desc".
 For pagination.
- `end` - the ending index of the result set. For pagination.

## Implement searchCount

The `searchCount` method takes the following parameters. Treat them
identically to the ones in `search`:

- `userId`
- `groupIds`
- `keywords`

