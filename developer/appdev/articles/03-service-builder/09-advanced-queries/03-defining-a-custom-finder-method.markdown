---
header-id: defining-a-custom-finder-method
---

# Defining a Custom Finder Method

[TOC levels=1-4]

Dynamic queries and custom SQL belong in finder methods. You implement them and
then make them available through an interface. This article demonstrates
defining the finder method in an implementation class, generating its interface
and tying the implementation to the interface. 

An example of this is a Guestbook application with two entities: guestbook and
entry. Each entry belongs to a guestbook so the entry entity has
a `guestbookId` field as a foreign key. If you need a finder to search for
guestbook entries by entry name and guestbook name, you'd add a finder method
to `GuestbookFinderImpl` and name it `findByEntryNameGuestbookName`. The full
method signature would be `findByEntryNameGuestbookName(String entryName,
String guestbookName)`. The steps are below. 

1.  Create a `[Entity]FinderImpl` class in the `[package 
    path].service.persistence.impl` package of your service module's
    `src/main/java` folder. Recall that you specify the `[package path]` in your
    `service.xml` file. Here's an example:

        <service-builder package-path="com.liferay.docs.guestbook">
            ...
        </service-builder>

2.  Define a `findBy*` finder method in the class you created. Make sure to add 
    any required arguments to your finder method signature.

4.  Run Service Builder to generate the appropriate interface in the `[package 
    path].service.persistence` package in the `service` folder of your API and
    service modules.

    For example, after adding `findByEntryNameGuestbookName(String entryName,
    String guestbookName)` to `GuestbookFinderImpl` and running Service Builder,
    the interface
    `com.liferay.docs.guestbook.service.persistence.GuestbookFinder` is
    generated.

5.  Make sure that the finder class implements the interface. For example, the 
    class declaration should look like this:

        public class GuestbookFinderImpl extends BasePersistenceImpl<Guestbook> implements GuestbookFinder

Your next step is to implement the query in your finder. You can do this via the
Dynamic Query API or Custom SQL. The next tutorial covers Dynamic Query. To
simply call custom SQL you have written, create a finder method to run your SQL: 

```xml
public List<Entry> findByEntryNameEntryMessageGuestbookName(
    String entryName, String entryMessage, String guestbookName,
    int begin, int end) {

    Session session = null;
    try {
        session = openSession();

        String sql = _customSQL.get(
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

@Reference
private CustomSQL _customSQL;
```

The custom finder method opens a new Hibernate session and uses Liferay's
`CustomSQL.get(Class<?> clazz, String id)` method to get the custom SQL to use
for the database query. The `FIND_BY_ENTRYNAME_ENTRYMESSAGE_GUESTBOOKNAME`
static field contains the custom SQL query's ID. The
`FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` string is based on the
fully-qualified class name of the `*Finder` interface (`EventFinder`) and the
name of the finder method (`findByEntryNameEntryMessageGuestbookName`).

Awesome! You've implemented your finder class, and if you're using custom SQL,
you've even implemented a method to call your finder. If you're using Dynamic
Query, the next tutorial shows you how to implement a dynamic query finder
method. 
