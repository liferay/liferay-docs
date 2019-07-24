---
header-id: custom-sql
---

# Custom SQL

[TOC levels=1-4]

Service Builder creates finder methods that retrieve entities by their
attributes: their column values. When you add a column as a parameter for the
finder in your `service.xml` file and run Service Builder, it generates the
finder method in your persistence layer and adds methods to your service layer
that invoke the finder. If your queries are simple enough, consider using 
[Dynamic Query](/docs/7-2/appdev/-/knowledge_base/a/dynamic-query) to access
Liferay's database. If you want to do something more complicated like JOINs, you
can write your own custom SQL queries. Here, you'll learn how.

The Guestbook application has two tables, one for guestbooks and one
for guestbook entries. The entry entity's foreign key to its guestbook is the
guestbook's ID. That is, the entry entity table, `GB_GuestbookEntry`, tracks an
entry's guestbook by its long integer ID in the table's `guestbookId` column. If
you want to find a guestbook entry based on its name, message, and guestbook
name, you must access the *name* of the entry's guestbook. Of course, with SQL
you can join the entry and guestbook tables to include the guestbook name.
Service Builder lets you do this by specifying the SQL as *Liferay custom SQL*
and invoking it in your service via a *custom finder method*.

Using Custom SQL in Service Builder is the same as using dynamic queries; it
just takes an additional first step to place the SQL you want to run in an XML
file. If you plan to use dynamic queries instead, skip the rest of this tutorial
and move on to the next one. 

## Specify Your Custom SQL

After you've tested your SQL, you must specify it in a particular file for
Liferay to access it. `CustomSQL` class (from module
[`com.liferay.portal.dao.orm.custom.sql.api`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.dao.orm.custom.sql.api/))
retrieves SQL from a file called `default.xml` in your service module's
`src/main/resources/META-INF/custom-sql/` folder. You must create the
`custom-sql` folder and create the `default.xml` file in that `custom-sql`
folder. The `default.xml` file must adhere to the following format:

```xml
<custom-sql>
    <sql id="[fully-qualified class name + method]">
    SQL query wrapped in <![CDATA[...]]>
    No terminating semi-colon
    </sql>
</custom-sql>
```

Create a `custom-sql` element for every SQL query you want in your application,
and give each query a unique ID. The recommended convention to use for the
ID value is the fully-qualified class name of the finder followed by a dot (`.`)
character and the name of the finder method. More detail on the finder class and
finder methods is provided in the next step.

For example, in the Guestbook application, you could use the following ID value
to specify a query:

```java
com.liferay.docs.guestbook.service.persistence.EntryFinder.findByEntryNameEntryMessageGuestbookName
```

Custom SQL must be wrapped in character data (`CDATA`) for the `sql` element.
Importantly, do not terminate the SQL with a semi-colon. Following these rules,
the `default.xml` file of the Guestbook application specifies an SQL query that
joins the `GB_GuestbookEntry` and `GB_Guestbook` tables:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<custom-sql>
    <sql id="com.liferay.docs.guestbook.service.persistence.EntryFinder.findByEntryNameEntryMessageGuestbookName">
        <![CDATA[
            SELECT GB_GuestbookEntry.*
            FROM GB_GuestbookEntry
            INNER JOIN 
                GB_Guestbook ON GB_GuestbookEntry.guestbookId = GB_Guestbook.guestbookId
            WHERE
                (GB_GuestbookEntry.name LIKE ?) AND
                (GB_GuestbookEntry.message LIKE ?) AND
                (GB_Guestbook.name LIKE ?)
        ]]>
    </sql>
</custom-sql>
```

Now that you've specified some custom SQL, the next step is to implement a
finder method to invoke it. The method name for the finder should match the ID
you just specified for the `sql` element.

Congratulations on developing a custom SQL query and custom finder for your
application!

## Related Topics

[Customizing Liferay Services](/docs/7-2/appdev/-/knowledge_base/a/customizing-liferay-services-service-wrappers)

[Service Builder Web Services](/docs/7-2/appdev/-/knowledge_base/a/service-builder-web-services)
