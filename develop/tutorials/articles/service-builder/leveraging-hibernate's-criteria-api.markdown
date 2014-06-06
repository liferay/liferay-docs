# Leveraging Hibernate's Criteria API

Liferay allows you to use custom SQL queries to retrieve data from the database.
However, sometimes it's more convenient to dynamically build queries at runtime
than it is to invoke predefined SQL queries. Liferay allows you to dynamically
build queries using its DynamicQuery API, which wraps Hibernate's Criteria API.
Using Liferay's DynamicQuery API allows you to dynamically build queries without
writing a single line of SQL. Using the DynamicQuery API allows you to think in
terms of objects and member variables instead of in terms of tables and columns.
Complex queries constructed via Hibernate's Criteria API can be significantly
easier to understand and maintain than the equivalent custom SQL (or HQL)
queries. While you technically don't need to know SQL to construct queries via
Hibernate's Criteria API, you still need to take care to construct efficient
queries. For information on Hibernate's Criteria API, please see [Hibernate's manual](http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/#querycriteria).
In this tutorial, we'll learn how to create custom finders for Liferay plugins
using Service Builder and Liferay's Dynamic Query API.

## Using Dynamic Query

To use Liferay's Dynamic Query API, you need to have a model entity for which to
create a finder. You can define model entities in `service.xml` and run Service
Builder to generate model, persistence, and service layers for your application.
See the [Service Builder Learning Path](www.liferay.com) for more information on
using Service Builder. Once you've used Service Builder to generate model,
persistence, and service layers for your application, you can create custom
finders using Liferay's Dynamic Query API by following these steps:

1. Create a custom `*FinderImpl` class and a define a custom `findBy*` finder
   method in this class. Run Service Builder to generate the required interfaces
   and utility classes.

2. Implement your finder method using Liferay's Dynamic Query API.

3. Add a method to your `*LocalServiceImpl` class which accesses your finder
   method. Run Service Builder to add the required method to the service
   interface.

Once you've taken these steps, you can access your custom finder as a service
method. Next, let's examine these steps in more detail.

### Step 1: Defining a Custom Finder Method

To define a custom query, whether by specifying custom SQL or by defining a
dynamic query, you need a finder class. Create a `[Entity]FinderImpl` class in
the generated `[Plugin Package Path].service.persistence` package of your
plugin's `docroot/WEB-INF/src` folder. Then define a custom `findBy...` finder
method in this class. Make sure to add any required arguments to your finder
method's method signature.

For example, consider the Guestbook application that we discussed in the
[Service Builder Learning Path](www.liferay.com). In that application, there are
two entities: guestbooks and entries. Each entry belongs to a guestbook so the
entry entity has a guestbookId field as a foreign key. Suppose you need to
create a custom finder to search for guestbook entries by the entry name and the
guestbook name. In this case, you'd add a custom finder method to
`GuestbookFinderImpl` and name it something like `findByEntryNameGuestbookName`.
The full method signature would appear as `findByEntryNameGuestbookName(String
entryName, String guestbookName)`.

<!--
Add a paragraph here discussing the optional int begin, int start parameters
that can be added to the custom finder method's method signature. - JR
-->

Once you've created a finder method with the appropriate method signature in
your finder class, run Service Builder to generate the appropriate interface and
utility class in the `[Plugin Package Path].service.persistence` package in your
plugin's `docroot/WEB-INF/service` folder.

For example, after adding `findByEntryNameGuestbookName(String entryName, String
guestbookName)` to `GuestbookFinderImpl` and running Service Builder, the
interface `com.liferay.docs.guestbook.service.persistence.GuestbookFinder` and
the utility class
`com.liferay.docs.guestbook.service.persistence.GuestbookFinderUtil` are
generated.

Once the finder interface has been generated, make sure that the finder class
implements the interface. For example, the class declaration should look like
this:

    public class GuestbookFinderImpl extends BasePersistenceImpl<Guestbook> implements GuestbookFinder

Your next step is to actually define your query in your custom finder method
using the Dynamic Query API.

### Step 2: Implementing Your Custom Finder Method Using Dynamic Query

Your first step in implementing your custom finder method in your `*FinderImpl`
class is to open a new Hibernate session. Since your `*FinderImpl` class extends
`BasePersistenceImpl<Entity>` and `BasePersistenceImpl<Entity>` contains a
session factory object and an `openSession` method, you can simply invoke the
`openSession` method of your `*FinderImpl`'s parent class to open a new
Hibernate session. The basic structure of your finder method should look like
this:

    public List<Entity> findBy*(...) {
                    
        Session session = null;
        try {
                // Try to open a new Hibernate session and create a dynamic
                // query to retrieve and return the desired list of entity
                // objects
        } catch (Exception e) {
                // Exception handling
        } finally {
                closeSession(session);
        }
        return null; // Return null only if there was an error returning the
                     // desired list of entity objects in the try block
    }

For example, in the case of the Guestbook application, you could write the
following finder method to retrieve a list of Guestbook entries that have a
specific name and that also belong to a Guestbook of a specific name:

    public List<Event> findByEntryNameGuestbookName(String entryName, String guestbookName) {
            
        Session session = null;
        try {
            session = openSession();
            
            DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
                    .add(RestrictionsFactoryUtil.eq("name", entryName))
                    .setProjection(PropertyFactoryUtil.forName("guestbookId"));
        
            List<Long> entryGuestbookIds = (List<Long>) EntryLocalServiceUtil.dynamicQuery(entryQuery);
            
            DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
                    .add(RestrictionsFactoryUtil.in("guestbookId",
                    entryGuestbookIds))
                    .add(RestrictionsFactoryUtil.eq("name", guestBookName))
                    .setProjection(PropertyFactoryUtil.forName("guestbookId"));;
            
            guestbookEntryIds = (List<Long>) GuestbookLocalServiceUtil.dynamicQuery(guestbookQuery);
            
            entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
                    .add(RestrictionsFactoryUtil.in("guestbookId",
                    guestbookEntryIds));
            
            List<Event> entries = EntryLocalServiceUtil.dynamicQuery(entryQuery);
            
            return entries;

        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        
        return null;
    }

Notice that in Liferay, we don't create criteria objects directly from the
Hibernate session. Instead, we create dynamic query objects using Liferay's
`DynamicQueryFactoryUtil` service. Thus, instead of

    Criteria entryCriteria = session.createCriteria(Entry.class);

we use

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class);

We can use two of the most important features of Hibernate's Criteria API,
restrictions and projections, on Liferay's dynamic query objects. Restrictions
in Hibernate's Criteria API roughly correspond to the *where* clause of an SQL
query: they offer a variety of ways to limit the results returned by the query.
You can use restrictions, for example, to restrict a query to only return
results where a certain field has a particular value, or a value in a certain
range, or a non-null value, etc.

Projections in Hibernate's Criteria API allow you to modify the kind of results
returned by a query. For example, if you don't want your query to return a list
of entity objects (the default), you can set a projection on query so that only
a list of the values of a certain field of the entity is returned. You can also
use projections on a query to return the maximum value of an entity field, or
the sum of all the values of a field, or the average. For more information on
restrictions and projections, please refer to Hibernate's [documentation](http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/querycriteria.html).

Like Hibernate criteria, Liferay's dynamic queries are *chainable*. This means
we can add criteria to and set projections on Liferay's dynamic query objects
just by appending the appropriate method calls to the dynamic query object. For
example, the following snippet demonstrates chaining the addition of a
restriction criterion and a projection to a dynamic query object declaration:

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
        .add(RestrictionsFactoryUtil.eq("name", entryName))
        .setProjection(PropertyFactoryUtil.forName("guestbookId"));

When we need to add restrictions to a dynamic query in Liferay, we don't call
Hibernate's `Restrictions` class directly. Instead, we
use the methods of Liferay's `RestrictionsFactoryUtil` service. You'll find the
same methods in Liferay's `RestrictionsFactoryUtil` service class that you're
used to from Hibernate's `Restrictions` class: `in`, `between`, `eq`, `ne, `gt`,
`ge`, `lt`, `le`, `like`, etc.

Thus, instead of 

    entryCriteria.add(Restrictions.in("name", entryName));

to specify that a guestbook entry must have a certain name, we use

    entryQuery.add(RestrictionsFactoryUtil.eq("name", entryName));

Similarly, to set projections, we create properties via Liferay's
`PropertyFactoryUtil` service instead of through Hibernate's `Property` class.
Thus, instead of 

    entryCriteria.setProjection(Property.forName("guestbookId"));

we use

    entryQuery.setProjection(PropertyFactoryUtil.forName("guestbookId"));

Notice that (for the sake of demonstration) we make three distinct dynamic
queries in the Guestbook example's `findByEntryNameGuestbookName` finder method.
Here's the first one:

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
        .add(RestrictionsFactoryUtil.eq("name", entryName))
        .setProjection(PropertyFactoryUtil.forName("guestbookId"));

    List<Long> entryGuestbookIds = (List<Long>) EntryLocalServiceUtil.dynamicQuery(entryQuery);

On the first line, we create a new dynamic query for the `Entry` class. Running
`EntryLocalServiceUtil.dynamicQuery` on this query returns a list of all
Guestbook entries by default. On the second line, we restrict the query to only
return guestbook entries whose name matches the `entryName`, which is an
argument of our finder method. On the third line, we set a projection so that
only the guestbook IDs of the guestbook entries are returned. On the last line,
we run the dynamic query and get the list of guestbook IDs.

### Step 3: Accessing Your Custom Finder Method from the Service Layer

3. Add a `findBy...` method to `[Entity]LocalServiceImpl.java` that calls the
   `findBy...` method that Service Builder generated in
   `[Entity]FinderUtil.java`. Since `[Entity]FinderUtil.java` is generated in
   the `persistence` package, it's a best practice not to call it directly but
   to instead call `[Entity]LocalServiceUtil.java`. For example, if you created
   a custom finder method called `findByUserId` in `GuestbookFinderImpl.java`,
   then you'd create a `findByUserId` method in `GuestbookLocalServiceImpl.java`
   that calls the `findByUserId` method of `GuestbookFinderUtil.java`.


