---
header-id: meaningful-schema-versioning
---

# Meaningful Schema Versioning

[TOC levels=1-4]

Liferay's data schema version convention communicates a schema's compatibility
with older versions of the software. It tells you whether a schema's changes
maintain or break compatibility with existing software. For example, if a new
data schema removes a field your software expects, the schema breaks
compatibility. But if a new schema's changes are non-breaking (e.g., adds a new
field), the schema is compatible and can be used with existing software.

Since @product@ 7.1, Liferay uses a meaningful schema version convention
(similar to
[Semantic Versioning](http://semver.org))
to define new
[upgrade steps](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-upgrade-process-for-your-app) 
and support rollback of schema micro versions. The schema version defines the
status of the database schema and its data belonging to that module or Core in a
certain moment. The concept of schema versioning is different from bundle
versioning. The biggest concern in versioning a data schema is
**backward-compatibility** between the new schema and the code that operates on
the data. 

Here's Liferay's schema version convention:

**MAJOR.MINOR.MICRO**

Each part means something: 

**MAJOR:** Contains breaking schema/data changes that are incompatible with the 
previous version of the code. 

**MINOR:** Contains schema/data changes compatible with the previous version of 
the code. The changes are required for the new version of the code to work (the
application will fail without applying the schema/data changes)

**MICRO:** Contains schema/data changes that are compatible with the previous 
version of the code. The changes are optional. 

If you're not sure what kind of schema version change represents your upgrade
step, ask yourself these questions:

1.  Will the previous code version (previous FP, SP, or GA) work with these 
    schema/data changes? 

    -   If not, it is a major change. 
    -   If yes, continue. 

2.  Are the schema/data changes required for the application to work? 
    (Obviously, all changes are intended to improve the application but in some
    cases the application is fully functional without them)

    -   If yes, it is a minor change.
    -   If not, it is a micro change. 

Next are some concrete examples of micro, minor, and major changes. 

## Micro change examples

Here are common micro changes:

-   Increasing `VARCHAR` field sizes. 
-   Modifying DB indexes. 
-   Modifying data values to adapt to current logic. These include backwards 
    compatible data changes only. These changes commonly occur when data updates
    are missed for new functionalities. 
-   Converting a field from a String to a CLOB, as long as the field has few 
    records and isn't used in `DISTINCT` or `GROUP BY` SQL clauses. 

## Minor change examples

Here are common minor changes:

-   Adding a new DB field.
-   Adding a new DB table. 

**Important:** The changes above are major if they require modifying current 
existing data or extract information to populate the new field or table. In such
cases, the data can become incorrect if you rolled back to the previous code
version and then, after some time, installed the new code again. 

## Major change examples

Here are common major changes:

-   Making data modifications that are not backward compatible. 
-   Removing a DB field
-   Removing a DB table. 
-   Altering a column name. 
-   Decreasing the size of a `VARCHAR` field. 
-   Converting a field from a String to a CLOB, where the field is has many 
    records or is used in `DISTINCT` or `GROUP BY` SQL clauses. 
-   Adding a new DB field or table that requires modifying current existing data
    or extracts information to populate the new field or table. 

Now you can ascribe meaningful versions to your module's data schemas. 


