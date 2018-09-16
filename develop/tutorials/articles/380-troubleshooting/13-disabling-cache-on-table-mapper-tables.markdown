# Disabling Cache for Table Mapper Tables

When creating custom service builder entities, you may find it necessary to map
tables together, in order to create a relational mapping between the two. This
can be clearly seen when a table has the naming syntax of `table1_table2`, with
an example being `AssetEntries_AssetCategories`. This particular table maps a
given `AssetEntry` with a given `AssetCategory`. These table mappings also have
a cache by default, which makes retrievals much faster. These types of mappings
are defined in `service.xml`. Here's an example entry in the `AssetCategories`
entity:

    <column entity="AssetEntry" mapping-table="AssetEntries_AssetCategories" name="entries" type="Collection" />

Also, a similar entry must exist under the `AssetEntries` entity:

    <column entity="AssetCategory" mapping-table="AssetEntries_AssetCategories" name="categories" type="Collection" />

Note, the only differences are the entity type and name.

## Why would I want to disable cache on a table mapper?

Looking at the portal property which disables table mapper tables, we see there
are already a few defined:

    ##
    ## Table Mapper
    ##

      #
      # Set a list of comma delimited mapping table names that will not be using
      # cache in their table mappers.
      #
      table.mapper.cacheless.mapping.table.names=\
        Users_Groups,\
        Users_Orgs,\
        Users_Roles,\
        Users_Teams,\
        Users_UserGroups

All of the disabled caches pertain to the `User` object. These tables have cache
disabled because they tend to be much too large to have a useful cache, as each
user can potentially have several entries in each of the table. So, one reason
to disable a table mapper cache is due to the table being too large, therefore
creating a huge cache which can hog memory.

Another reason may be due to race conditions when retrieving objects from the
cache. Take into consideration LPS-84374, where a client is having troubles
with their custom portlet. This portlet calls get methods on the
`AssetEntries_AssetCategories` table mapper, while also possibly publishing
`AssetEntries`, clearing the table mapper cache. While caching table mapper
tables is useful for faster retrieves, it has the stipulation of being able to
be cleared while still in use, and causing transactional rollbacks.

## How to resolve the issue seen in LPS-84374

While engineering works on a permanent solution, a quick and safe workaround
would be to simply disable table mapper cache for affected tables. Doing so is
simple, all which needs to be done is for the table mapper table name to be
added to the `table.mapper.cacheless.mapping.table.names` property. Make sure it
is *added*, and not just *overwriting* existing entries. Simple copy/paste
the entire property (including existing values) from
`webapps~/ROOT~/WEB-INF~/lib~/portal-impl.jar~/portal.properties` into your
`portal-ext.properties`, and add the necessary table names to the list. For the
example in LPS-84374, the property should look like so:


    table.mapper.cacheless.mapping.table.names=\
        Users_Groups,\
        Users_Orgs,\
        Users_Roles,\
        Users_Teams,\
        Users_UserGroups,\
        AssetEntries_AssetCategories

After restarting the portal, the cache for the `AssetEntries_AssetCategories`
table should no longer exist, resolving the transaction issue.
