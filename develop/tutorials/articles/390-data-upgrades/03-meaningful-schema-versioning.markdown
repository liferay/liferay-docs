# Meaningful Schema Versioning [](id=meaningful-schema-versioning)

Data schema versions can be as arbitrary as you like; but they are most helpful
when they provide meaning. Liferay's data schema version convention communicates
a schema's compatibility with older versions of the software. It tells you
whether a schema's changes maintain or break compatibility with existing
software. For example, if a new data schema removes a field your software
expects, the schema breaks compatibility. But if a new schema's changes are
non-breaking (e.g., adds a new field), the schema is compatible and can be used
with existing software. Since @product@ 7.1, Liferay uses a meaningful schema
version convention (similar to [Semantic Versioning](http://semver.org)) to
define new [upgrade steps](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app)
and support rollback of schema micro versions. The convention is optional but
tracks data schema backwards compatibility. 

$$$

Here's Liferay's schema version convention:

**MAJOR.MINOR.MICRO**

Each part means something: 

**MAJOR:** Contains breaking schema/data changes that are incompatible with the 
latest version of existing code. 

**MINOR:** Contains schema/data changes compatible with the latest 
version of existing code. The changes typically involve supporting new
functionality. 

**MICRO:** Contains schema/data changes that are compatible with the latest 
version of existing code. 

Next are some concrete examples of micro, minor, and major changes. 

## Micro change examples [](id=micro-change-examples)

Here are common micro changes:

-   Increasing `VARCHAR` field sizes. 
-   Modifying DB indexes. 
-   Modifying data values to adapt to current logic. These include backwards 
    compatible data changes only. These changes commonly occur when data updates
    are missed for new functionalities. 
-   Converting a field from a String to a CLOB, as long as the field has few 
    records and isn't used in `DISTINCT` or `GROUP BY` SQL clauses. 

## Minor change examples [](id=minor-change-examples)

Here are common minor changes:

-   Adding a new DB field.
-   Creating a new DB table. 

## Major change examples [](id=major-change-examples)

Here are common major changes:

-   Removing a DB field
-   Removing a DB table. 
-   Altering a column name. 
-   Decreasing the size of a `VARCHAR` field. 
-   Converting a field from a String to a CLOB, where the field is has many 
    records or is used in `DISTINCT` or `GROUP BY` SQL clauses. 

Now you can ascribe meaningful versions to your module's data schemas. 
