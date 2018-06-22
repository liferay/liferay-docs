# Custom SQL [](id=custom-sql)

Service Builder creates finder methods that retrieve entities by their
attributes: their column values. When you add a column as a parameter for the
finder in your `service.xml` file and run Service Builder, it generates the
finder method in your persistence layer and adds methods to your service layer
that invoke the finder. If your queries are simple enough, consider using 
[Dynamic Query](/develop/tutorials/-/knowledge_base/7-1/dynamic-query) to access
Liferay's database. If you want to do something more complicated like JOINs, you
can write your own custom SQL queries. You'll learn how in this tutorial.

Say you have a Guestbook application with two tables, one for guestbooks and one
for guestbook entries. The entry entity's foreign key to its guestbook is the
guestbook's ID. That is, the entry entity table, `GB_Entry`, tracks an entry's
guestbook by its long integer ID in the table's `guestbookId` column. If you
want to find a guestbook entry based on its name, message, and guestbook name,
you must access the *name* of the entry's guestbook. Of course, with SQL you can
join the entry and guestbook tables to include the guestbook name. Service
Builder lets you do this by specifying the SQL as *Liferay custom SQL* and
invoking it in your service via a *custom finder method*.

Liferay custom SQL is a Service Builder-supported method for performing custom,
complex queries against the database by invoking custom SQL from a finder method
in your persistence layer. Service Builder helps you generate the interfaces
to your finder method. It's easy to do by following these steps:

1. [Specify your custom SQL.](#step-1-specify-your-custom-sql)

2. [Implement your finder method.](#step-2-implement-your-finder-method)

3. [Access your finder method from your service.](#step-3-access-your-finder-method-from-your-service)

Next, using the Guestbook application as an example, you'll learn how to
accomplish these steps.

## Step 1: Specify Your Custom SQL [](id=step-1-specify-your-custom-sql)

After you've tested your SQL, you must specify it in a particular file for
Liferay to access it. Liferay's
[`CustomSQLUtil`](@app-ref@/foundation/latest/javadocs/com/liferay/portal/dao/orm/custom/sql/CustomSQLUtil.html)
class retrieves SQL from a file called `default.xml` in your service module's
`src/main/resources/META-INF/custom-sql/` folder. You must create the
`custom-sql` folder and create the `default.xml` file in that `custom-sql`
folder. The `default.xml` file must adhere to the following format:

    <custom-sql>
        <sql id="[fully-qualified class name + method]">
        SQL query wrapped in <![CDATA[...]]>
        No terminating semi-colon
        </sql>
    </custom-sql>

Create a `custom-sql` element for every SQL query you want in your application,
and give each query a unique ID. The recommended convention to use for the
ID value is the fully-qualified class name of the finder followed by a dot (`.`)
character and the name of the finder method. More detail on the finder class and
finder methods is provided in Step 2.

For example, in the Guestbook application, you could use the following ID value
to specify a query:

    com.liferay.docs.guestbook.service.persistence.\
    EntryFinder.findByEntryNameEntryMessageGuestbookName

Custom SQL must be wrapped in character data (`CDATA`) for the `sql` element.
Importantly, do not terminate the SQL with a semi-colon. Following these rules,
the `default.xml` file of the Guestbook application specifies an SQL query that
joins the `GB_Entry` and `GB_Guestbook` tables:

    <?xml version="1.0" encoding="UTF-8"?>
    <custom-sql>
        <sql id="com.liferay.docs.guestbook.service.persistence.EntryFinder.findByEntryNameEntryMessageGuestbookName">
            SELECT GB_Entry.*
            FROM GB_Entry
            INNER JOIN 
                GB_Guestbook ON GB_Entry.guestbookId = GB_Guestbook.guestbookId
            WHERE
                (GB_Entry.name LIKE ?) AND
                (GB_Entry.message LIKE ?) AND
                (GB_Guestbook.name LIKE ?)
        </sql>
    </custom-sql>

Now that you've specified some custom SQL, the next step is to implement a
finder method to invoke it. The method name for the finder should match the ID
you just specified for the `sql` element.

## Step 2: Implement Your Finder Method [](id=step-2-implement-your-finder-method)

Next, implement the finder method in your persistence layer to invoke your
custom SQL query. Service Builder generates the interface for the finder in your
API module but you must create the implementation.

The first step is to create a `*FinderImpl` class in the service persistence
package. For the Guestbook application, you could create a `EntryFinderImpl`
class in the `com.liferay.docs.guestbook.service.persistence.impl` package. Your
class should extend `BasePersistenceImpl<Entry>`.

[Run Service Builder](/develop/tutorials/-/knowledge_base/7-1/running-service-builder)
to generate the `*Finder` interface based on the `*FinderImpl` class. Modify
your `*FinderImpl` class to have it implement the `*Finder` interface you just
generated:

    public class EntryFinderImpl extends BasePersistenceImpl<Event>
        implements EntryFinder {

    }

Now you can create a finder method in your `EntryFinderImpl` class. Add your
finder method and static field to the `*FinderImpl` class. For example, here's
how you could write the `EntryFinderImpl` class:

    public List<Entry> findByEntryNameEntryMessageGuestbookName(
        String entryName, String entryMessage, String guestbookName,
        int begin, int end) {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                getClass(),
                FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("GB_Entry", EntryImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(entryName);
            qPos.add(entryMessage);
            qPos.add(guestbookName);

            return (List<Entry>) QueryUtil.list(q, getDialect(), begin, end);
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                se.printStackTrace();
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME =
        EntryFinder.class.getName() +
            ".findByEntryNameEntryMessageGuestbookName";

The custom finder method opens a new Hibernate session and uses Liferay's
`CustomSQLUtil.get(String id)` method to get the custom SQL to use for the
database query. The `FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME` static field
contains the custom SQL query's ID. The
`FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` string is based on the
fully-qualified class name of the `*Finder` interface (`EventFinder`) and the
name of the finder method (`findByEntryNameEntryMessageGuestbookName`).

Awesome! Your custom SQL is in place and your finder method is implemented.
Next, you'll call the finder method from your service.

## Step 3: Access Your Finder Method from Your Service [](id=step-3-access-your-finder-method-from-your-service)

So far, you've created a `*FinderImpl` class, generated the `*Finder` interface,
and created a custom finder method that gets your custom SQL. Your last step is
to add a service method that calls your finder.

When you ran Service Builder after defining your custom finder method, the
`*Finder` interface was generated (e.g., `GuestbookFinder`). Your portlet class,
however, should not call the `*Finder` interface: only a local or remote service
implementation (i.e., `*LocalServiceImpl` or `*ServiceImpl`) in your service
module should invoke the `*Finder` class. This encourages a proper separation of
concerns: the portlet classes in your application's web module invoke the
business logic of the services published from your application's service module.
The services, in turn, access the data model using the persistence layer's
finder classes.

+$$$

**Note:** Liferay Portal 6.2 made finder methods accessible via static
`*FinderUtil` utility classes. Finder methods are now injected into your app's
local services, removing the need to call finder utilities.

$$$

So you'll add a method in the `*LocalServiceImpl` class that invokes the finder
method implementation via the `*Finder` class. Then you'll rebuild your
application's service layer so that the portlet classes and JSPs in your web
module can access the services.

For example, for the Guestbook application, you'd add the following method to
the `EntryLocalServiceImpl` class:

    public List<Entry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {

        return entryFinder.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

After you've added your `findBy-` method to your `*LocalServiceImpl` class, run
Service Builder to generate the interface and make the finder method available
in the `EntryLocalService` class.

Now you can indirectly call the finder method from your portlet class or a JSP
in your web module. For example, to call the finder method in the Guestbook
application, just call
`entryLocalService.findByEntryNameEntryMessageGuestbookName(...)`!

Congratulations on developing a custom SQL query and custom finder for your
application!

## Related Topics [](id=related-topics)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)
