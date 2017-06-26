# Custom SQL [](id=custom-sql)

Service Builder's finder methods facilitate searching for entities by their
attributes--their column values. Add the column as a parameter for the finder
in your `service.xml` file, run Service Builder, and it generates the finder
method in your persistence layer and adds methods to your service layer that
invoke the finder. If your queries are simple enough, you should consider using
[Dynamic Query](/develop/tutorials/-/knowledge_base/7-0/dynamic-query) to access
Liferay's database. But what if you'd like to do more complicated searches that
incorporate attributes from multiple entities? You can always write your own
custom SQL queries. You'll learn how in this tutorial.

For example, consider a fictitious Guestbook application. Suppose you want to
find a guestbook entry based on its name, message, and guestbook name. In the
Guestbook application, the entry entity refers to its guestbook by the
guestbook's ID, not by its name. That is, the entry entity table, `GB_Entry`,
refers to an entry's guestbook by its long integer ID in the table's
`guestbookId` column. But you need to access the *name* of the entry's
guestbook. Of course, with SQL you can join the entry and guestbook tables to
include the guestbook name. But how would you incorporate custom SQL into your
application? And how would you invoke the SQL from your service? Service Builder
lets you do this by specifying the SQL as *Liferay custom SQL* and invoking it
in your service via a *custom finder method*.

Liferay custom SQL is a Service Builder-supported method for performing complex
and custom queries against the database. Invoking custom SQL from a finder
method in your persistence layer is straightforward. And Service Builder helps
you generate the interfaces to your finder method. It's easy to do by following
these steps:

1. Specify your custom SQL.

2. Implement your finder method.

3. Access your finder method from your service.

Next, using the Guestbook application as an example, you'll learn how to
accomplish these steps.

## Step 1: Specify Your Custom SQL [](id=step-1-specify-your-custom-sql)

After you've tested your SQL, you must specify it in a particular file for
Liferay to access it. Liferay's
[CustomSQLUtil](@app-ref@/foundation/latest/javadocs/com/liferay/portal/dao/orm/custom/sql/CustomSQLUtil.html)
class looks up custom SQL from a file called `default.xml` in your service
module's `src/custom-sql/` folder. You must create the `custom-sql` folder and
create the `default.xml` file in that `custom-sql` folder. The `default.xml`
file must adhere to the following format:

    <custom-sql>
        <sql id="[fully-qualified class name + method]">
        SQL query wrapped in <![CDATA[...]]>
        No terminating semi-colon
        </sql>
    </custom-sql>

You can add a `custom-sql` element for every custom SQL query you'd like to
include in your application, as long as each query has a unique ID. The recommended
convention to use for the ID value is the fully-qualified class
name of the finder followed by a dot (`.`) character and the name of the finder
method. More detail on the finder class and finder methods is provided in Step
2.

For example, in the Guestbook application, you could use the following ID value
to specify a query:

    com.liferay.docs.guestbook.service.persistence.\
    EntryFinder.findByEntryNameEntryMessageGuestbookName

Custom SQL must be wrapped in character data (`CDATA`) for the `sql` element.
Importantly, the SQL must *not* be terminated with a semi-colon. Following
these rules, the `default.xml` file of the Event Listing project specifies an
SQL query that joins the Event and Location tables:

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

After specifying your custom SQL query, you need to implement the finder method
to invoke it. This should be done in your service module's persistence layer.
Service Builder generates the interface for the finder in your API module but
you need to create the implementation.

The first step is to create a `*FinderImpl` class in the service persistence
package. For the Guestbook application, for example, you could create a
`EntryFinderImpl` class in the
`com.liferay.docs.guestbook.service.persistence.impl` package. Your class should
extend `BasePersistenceImpl<Entry>`.

Run Service Builder to generate the `*Finder` interface, which is based on
the `*FinderImpl` class. Modify your `*FinderImpl` class to have it implement
the `*Finder` interface you just generated:

    public class *FinderImpl extends BasePersistenceImpl<Event>
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
`-Finder` interface was generated (e.g., `GuestbookFinder`). Your portlet class,
however, should not call the `-Finder` interface: only a local or remote service
implementation (i.e., `-LocalServiceImpl` or `-ServiceImpl`) in your service
module should invoke the `-Finder` class. This encourages a proper separation of
concerns: the portlet classes in your application's web module invoke the
business logic of the services published from your application's service module.
The services, in turn, access the data model using the persistence layer's
finder classes.

+$$$

**Note:** In previous versions of Liferay Portal, your finder methods were
accessible via `-FinderUtil` utility classes. Finder methods are now injected
into your app's local services, removing the need to call finder utilities.

$$$

So you'll add a method in the `-LocalServiceImpl` class that invokes the finder
method implementation via the `-Finder` class. Then you'll rebuild your
application's service layer so that the portlet classes and JSPs in your web
module can access the services.

For example, for the Guestbook application, you'd add the following method to
the `EntryLocalServiceImpl` class:

    public List<Entry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {

        return entryFinder.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

After you've added your `findBy-` method to your `-LocalServiceImpl` class, run
Service Builder to generate the interface and make the finder method available
in the `EntryLocalService` class.

Now you can indirectly call the finder method from your portlet class or a JSP
in your web module. For example, to call the finder method in the Guestbook
application, just call
`EntryLocalService.findByEntryNameEntryMessageGuestbookName(...)`!

Congratulations on developing a custom SQL query and custom finder for your
application!

## Related Topics [](id=related-topics)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services-service-wrappers)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)
