# Dynamic Query [](id=dynamic-query)

Liferay lets you use custom SQL queries to retrieve data from the database.
However, it's sometimes more convenient to build queries dynamically at runtime
than it is to invoke predefined SQL queries. Liferay allows you to build queries
dynamically using its DynamicQuery API, which wraps Hibernate's Criteria API.
Using Liferay's DynamicQuery API allows you to build queries without writing a
single line of SQL. The DynamicQuery API helps you think in terms of objects and
member variables instead of in terms of tables and columns. Complex queries
constructed via Hibernate's Criteria API can be significantly easier to
understand and maintain than the equivalent custom SQL (or HQL) queries. While
you technically don't need to know SQL to construct queries via Hibernate's
Criteria API, you still need to take care to construct efficient queries. For
information on Hibernate's Criteria API, please see
[Hibernate's manual](http://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/chapters/query/criteria/Criteria.html).
In this tutorial, you'll learn how to create custom finders for Liferay
applications using Service Builder and Liferay's Dynamic Query API.

To use Liferay's Dynamic Query API, you need to create a finder implementation
for your model entity. You can define model entities in `service.xml` and run
Service Builder to generate model, persistence, and service layers for your
application. See the
[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
learning path for more information on using Service Builder. This tutorial
assumes that you're creating a Liferay application that consists of a service
module, an API module, and a web module. Once you've used Service Builder to
generate model, persistence, and service layers for your application, you can
call custom finders using Liferay's Dynamic Query API by following these steps:

1. Create a custom `-FinderImpl` class and define a `findBy-` finder method in
   this class. Run Service Builder to generate the required interfaces and
   utility classes.

2. Implement your finder method using Liferay's Dynamic Query API.

3. Add a method to your `-LocalServiceImpl` class that invokes your finder
   method. Run Service Builder to add the required method to the service
   interface.

Once you've taken these steps, you can access your custom finder as a service
method. Note: You can create multiple or overloaded `findBy-` finder methods in
your `-FinderImpl` class. Next, let's examine these steps in more detail.

## Step 1: Defining a Custom Finder Method [](id=step-1-defining-a-custom-finder-method)

To define any custom query, either by specifying custom SQL or by defining a
dynamic query, you need a finder class. Create a `[Entity]FinderImpl` class in
the generated `[package path].service.persistence.impl` package of your service
module's `src/main/java` folder. Recall that you specify the package path
in `service.xml`. Here's an example:

    <service-builder package-path="com.liferay.docs.guestbook">
        ...
    </service-builder>

Then define a `findBy-` finder method in the class you created. Make sure to
add any required arguments to your finder method's method signature.

For example, consider a fictitious Guestbook application. In this application,
there are two entities: guestbooks and entries. Each entry belongs to a
guestbook so the entry entity has a `guestbookId` field as a foreign key.
Suppose you need to create a custom finder to search for guestbook entries by
the entry name and the guestbook name. In this case, you'd add a custom finder
method to `GuestbookFinderImpl` and name it something like
`findByEntryNameGuestbookName`. The full method signature would appear as
`findByEntryNameGuestbookName(String entryName, String guestbookName)`.

Once you've created a finder method with the appropriate method signature in
your finder class, run Service Builder to generate the appropriate interface in
the `[package path].service.persistence` package in the `service` folders of
your API and service modules.

For example, after adding `findByEntryNameGuestbookName(String entryName, String
guestbookName)` to `GuestbookFinderImpl` and running Service Builder, the
interface `com.liferay.docs.guestbook.service.persistence.GuestbookFinder` is
generated.

Once the finder interface has been generated, make sure that the finder class
implements the interface. For example, the class declaration should look like
this:

    public class GuestbookFinderImpl extends BasePersistenceImpl<Guestbook> implements GuestbookFinder

Your next step is to actually define your query in your custom finder method
using the Dynamic Query API.

## Step 2: Implementing Your Custom Finder Method Using Dynamic Query [](id=step-2-implementing-your-custom-finder-method-using-dynamic-query)

Your first step in implementing your custom finder method in your `-FinderImpl`
class is to open a new Hibernate session. Since your `-FinderImpl` class extends
`BasePersistenceImpl<Entity>`, and `BasePersistenceImpl<Entity>` contains a
session factory object and an
[openSession](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/persistence/impl/BasePersistenceImpl.html#openSession--)
method, you can simply invoke the
`openSession` method of your `-FinderImpl`'s parent class to open a new
Hibernate session. The basic structure of your finder method should look like
this:

    public List<Entity> findBy-(...) {

        Session session = null;
        try {
                /*
                Try to open a new Hibernate session and create a dynamic
                query to retrieve and return the desired list of entity
                objects
                */
        }
        catch (Exception e) {
                // Exception handling
        }
        finally {
                closeSession(session);
        }

        return null;
        /*
        Return null only if there was an error returning the
        desired list of entity objects in the try block
        */

    }

For example, in the case of the Guestbook application, you could write the
following finder method to retrieve a list of Guestbook entries that have a
specific name and that also belong to a Guestbook of a specific name:

    public List<Event> findByEntryNameGuestbookName(String entryName, String guestbookName) {

        Session session = null;
        try {
            session = openSession();

            DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
                .add(RestrictionsFactoryUtil.eq("name", guestbookName))
                .setProjection(ProjectionFactoryUtil.property("guestbookId"));

            Order order = OrderFactoryUtil.desc("modifiedDate");

            DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
                .add(RestrictionsFactoryUtil.eq("name", entryName))
                .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))
                .addOrder(order);

            List<Event> entries = EventLocalServiceUtil.dynamicQuery(entryQuery);

            return entries;
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

Notice that in Liferay, you don't create criteria objects directly from the
Hibernate session. Instead, you create dynamic query objects using Liferay's
[DynamicQueryFactoryUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/DynamicQueryFactoryUtil.html)
service. Thus, instead of

    Criteria entryCriteria = session.createCriteria(Entry.class);

you use

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class);

Most features of Hibernate's Criteria API, including restrictions, projections,
and orders, can be used on Liferay's dynamic query objects. Restrictions in
Hibernate's Criteria API roughly correspond to the `where` clause of an SQL
query: they offer a variety of ways to limit the results returned by the query.
You can use restrictions, for example, to cause a query to return only results
where a certain field has a particular value, or a value in a certain range, or
a non-null value, etc.

Projections in Hibernate's Criteria API let you modify the kind of results
returned by a query. For example, if you don't want your query to return a list
of entity objects (the default), you can set a projection on a query so that
only a list of the values of a certain entity field, or fields, is returned.
You can also use projections on a query to return the maximum or minimum value
of an entity field, or the sum of all the values of a field, or the average,
etc. For more information on restrictions and projections, please refer to
Hibernate's
[documentation](http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/querycriteria.html).

Orders, another feature of Hibernate's Criteria API, let you control the order
of the elements in the list returned by a query. You can choose the property or
properties to which an order should be applied and you can choose for the
properties to appear in ascending or descending order in the list.

Like Hibernate criteria, Liferay's dynamic queries are *chainable*. This means
that you can add criteria to, set projections on, and add orders to Liferay's
dynamic query objects just by appending the appropriate method calls to the
query object. For example, the following snippet demonstrates chaining the
addition of a restriction criterion and a projection to a dynamic query object
declaration:

    DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
        .add(RestrictionsFactoryUtil.eq("name", guestbookName))
        .setProjection(ProjectionFactoryUtil.property("guestbookId"));

When you need to add restrictions to a dynamic query in Liferay, don't call
Hibernate's `Restrictions` class directly. Instead, use the methods of Liferay's
[RestrictionsFactoryUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/RestrictionsFactoryUtil.html)
service. You'll find the same methods in Liferay's `RestrictionsFactoryUtil`
service class that you're used to from Hibernate's `Restrictions` class: `in`,
`between`, `like`, `eq`, `ne`, `gt`, `ge`, `lt`, `le`, etc.

Thus, instead of 

    entryCriteria.add(Restrictions.eq("name", guestbookName));

to specify that a guestbook must have a certain name, you use

    entryQuery.add(RestrictionsFactoryUtil.eq("name", guestbookName));

Similarly, to set projections, you create properties via Liferay's
[PropertyFactoryUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/PropertyFactoryUtil.html)
service instead of through Hibernate's `Property` class. Thus, instead of 

    entryCriteria.setProjection(Property.forName("guestbookId"));

you use

    entryQuery.setProjection(PropertyFactoryUtil.forName("guestbookId"));

Notice that in the custom `findByGuestbookNameEntryName` finder method, there
are two distinct dynamic queries. The first query retrieves a list of guestbook
IDs corresponding to guestbook names that match the `guestbookName` parameter
of the finder method. The second query retrieves a list of guestbook entries
with entry names that match the `entryName` parameter and have `guestbookId`
foreign keys belonging to the list returned by the first query.

Here's the first query:

    DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
        .add(RestrictionsFactoryUtil.eq("name", guestbookName))
        .setProjection(ProjectionFactoryUtil.property("guestbookId"));

By default, `DynamicQueryFactoryUtil.forClass(Guestbook.class)` returns a query
that retrieves a list of all guestbook entities. Adding the
`.add(RestrictionsFactoryUtil.eq("name", guestbookName))` restriction limits the
results to only those guestbooks whose guestbook names match the
`guestbookName` parameter. The
`.setProjection(ProjectionFactoryUtil.property("guestbookId"))` projection
changes the result set from a list of guestbook entries to a list of guestbook
IDs. This is useful since guestbook IDs are much less expensive to retrieve than
full guestbook entities and the guestbook IDs are all that the guestbook entry
query requires.

Next is an order which applies to the list of entries returned by the
`findByEntryNameGuestbookName` finder method:

    Order order = OrderFactoryUtil.desc("modifiedDate");

When this order is applied to a query, the list of results returned by the query
are arranged in descending order of the query entity's `modifiedDate` attribute.
Thus the most recently modified entities (guestbook entries, in our example)
appear first and the least recently modified entities appear last.

Here's the second query:

    DynamicQuery eventQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
        .add(RestrictionsFactoryUtil.eq("name", entryName))
        .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))
        .addOrder(order);

    List<Event> entries = _eventLocalService.dynamicQuery(entryQuery);

By default, `DynamicQueryFactoryUtil.forClass(Entry.class)` returns of list of
all guestbook entry entities. The `.add(RestrictionsFactoryUtil.eq("name",
entryName))` restriction limits the results to only those guestbook entries
whose names match the `entryName` parameter of the finder method.
[PropertyFactoryUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/PropertyFactoryUtil.html)
is a Liferay utility class with the method `forName(String propertyName)`, which
returns the specified property. This property can be passed to another Liferay
dynamic query. This is exactly what happens in the following line of our
example:

    .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))

Here, the code makes sure that the guestbook IDs (foreign keys) of the entry
entities in the `entityQuery` must belong to the list of guestbook IDs returned
by the `guestbookQuery`. Declaring that an entity property in one query must
belong to the result list of another query is a way to use Liferay's dynamic
query API to create complex queries, similar to SQL joins.

Lastly, you apply the order defined earlier to the entries returned by the
`findByEntryNameGuestbookName` finder method:

    .addOrder(order);

This orders the list of guestbook entities by the `modifiedDate` attribute, from
most recent to least recent.

+$$$

**Note:** Service Builder not only generates a `public List
dynamicQuery(DynamicQuery dynamicQuery)` method in `-LocalServiceBaseImpl` but it
also generates `public List dynamicQuery(DynamicQuery dynamicQuery, int start,
int end)` and `public List dynamicQuery(DynamicQuery dynamicQuery, int start,
int end, OrderByComparator orderByComparator)` methods. You can go back to step
1 and either modify your custom finder method or create overloaded versions of
your custom finder method to take advantage of these extra methods and their
parameters. The `int start` and `int end` parameters are useful when paginating
a result list. `start` is the lower bound of the range of model entity instances
and `end` is the upper bound. The `OrderByComparator orderByComparator` is the
comparator by which to order the results.

$$$

To use the overloaded `dynamicQuery` methods of your `-LocalServiceBaseImpl`
class in the (optionally overloaded) custom finders of your `-FinderImpl` class,
just choose the appropriate methods for running the dynamic queries: 
`EventLocalService.dynamicQuery(eventQuery)`, or
`EventLocalService.dynamicQuery(eventQuery, start, end)` or
`EventLocalService.dynamicQuery(eventQuery, start, end, orderByComparator)`.

Great! You've now created a custom finder method using Liferay's Dynamic Query
API. Your last step is to add a service method that calls your finder.

## Step 3: Accessing Your Custom Finder Method from the Service Layer [](id=step-3-accessing-your-custom-finder-method-from-the-service-layer)

So far, you've created a `-FinderImpl` class, defined a custom `findBy-` finder
method in that class, and implemented the custom finder method using Dynamic
Query. Now how do you call your custom finder method from the service layer?

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

Now you can indirectly call the finder method from your portlet class or from a
JSP by calling `EntryLocalService.findByEntryNameGuestbookName(...)`!

Congratulations on following the three step process of developing a dynamic
query in a custom finder and exposing it as a service for your portlet!

## Actionable Dynamic Queries [](id=actionable-dynamic-queries)

Suppose you have over a million users on your portal, and you want to perform
some kind of mass update to a large portion of them. One approach might be
to use a dynamic query to retrieve the list of users in question. Once loaded
into memory, you could loop through the list and update each user. However,
with over a million users, the memory cost of such an operation would be too
great. In general, if you have very large numbers of service builder entities,
it can be too expensive in terms of memory and speed to run a dynamic query to
retrieve a list of such entities in order to do some processing on them. 

Liferay provides actionable dynamic queries to solve this kind of situation.
An actionable dynamic query does not return a list of service builder entities
like a regular dynamic query. Instead, it uses a pagination strategy to
load only small numbers of service builder entities into memory at a time and
performs some processing (i.e., performs an *action*) on each entity. So
instead of trying to use a dynamic query to load a million users into memory
and then perform some processing on each of them, a much better strategy is to
use an actionable dynamic query to process them. This way, only small numbers of
users are loaded into memory at a time, but you still process all the users.

When you run Service Builder, it includes actionable dynamic query support in
the generated API and service modules. For example, consider the API module of
the BLADE service builder example project. 

The `FooLocalService` interface in the API module contains these methods:

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

The `FooLocalServiceBaseImpl` class in the service module implements each of
these methods. See
[here](https://github.com/liferay/liferay-blade-samples/blob/master/gradle/apps/service-builder/foo-api/src/main/java/com/liferay/blade/samples/servicebuilder/service/FooLocalService.java#L156-L166)
and
[here](https://github.com/liferay/liferay-blade-samples/blob/master/gradle/apps/service-builder/foo-service/src/main/java/com/liferay/blade/samples/servicebuilder/service/base/FooLocalServiceBaseImpl.java#L247-L330)
for details.

The implementation of `FooLocalService.dynamicQuery()` uses
`DynamicQueryFactoryUtil` to obtain new (regular) dynamic query instance for
the `Foo` entity. This is the pattern shown earlier.

You can use `FooLocalService.getActionableDynamicQuery()` to obtain a new
actionable dynamic query instance for the `Foo` entity. Once you have the
instance, you can use chaining to build up the query using any of the techniques
described above for regular dynamic queries, such as restrictions or
projections. But the point of using an *actionable* dynamic query is to specify
an action to perform on each entity that results from the query. To specify such an
action, use the
`ActionableDynamicQuery.setPerformActionMethod<PerformActionMethod<?>` method.
Once the query has been defined and an action has been specified, use the
`ActionableDynamicQuery.performActions` to perform the action on each entity
that results from the query.

Here's an example from a test for @product@'s Bookmarks application:

	ActionableDynamicQuery actionableDynamicQuery = BookmarksEntryLocalServiceUtil.getActionableDynamicQuery();

	actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BookmarksEntry>() {
			@Override
			public void performAction(BookmarksEntry bookmarksEntry) {
				Assert.assertNotNull(bookmarksEntry);

				count.increment();
			}
		});

	actionableDynamicQuery.performActions();

You can see the full context [here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-test/src/testIntegration/java/com/liferay/bookmarks/service/persistence/test/BookmarksEntryPersistenceTest.java#L483-L501).

Consider the `FooLocalService` from the BLADE service builder API project
again. For most of your actionable dynamic query use cases, the actionable
query returned by `FooLocalService.getActionableDynamicQuery` will suffice.
This actionable dynamic query is an instance of the concrete class
`DefaultActionableDynamicQuery`. However, in addition to
`FooLocalService.getActionableDynamicQuery`, there are two additional methods
related to actionable dynamic queries:
`FooLocalService.getExportActionableDynamicQuery` and
`FooLocalService.getIndexableActionableDynamicQuery`. These methods return
instances of concrete classes (either `IndexableActionableDynamicQuery` or
`ExportActionableDynamicQuery`) that extend `DefaultActionableDynamicQuery`.
`IndexableActionableDynamicQuery` contains methods designed to facilitate
processing that involves search indexing and `ExportActionableDynamicQuery`
contains methods designed to facilitate processing that involves export /
import functionality.

To see examples of configuring indexer and export actionable dynamic queries,
see see the Bookmarks application
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/base/BookmarksEntryLocalServiceBaseImpl.java#L285-L296)
and
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/base/BookmarksEntryLocalServiceBaseImpl.java#L307-L405).
To see an example invocation of an indexable actionable dynamic query, see the
`reindexEntries` method of the Bookmarks application's indexer
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/search/BookmarksEntryIndexer.java#L155-L210).

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)

[Creating Local Service](/develop/tutorials/-/knowledge_base/7-0/creating-local-services)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)
