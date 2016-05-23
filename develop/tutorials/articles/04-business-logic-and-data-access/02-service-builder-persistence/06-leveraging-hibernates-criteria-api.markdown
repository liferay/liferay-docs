# Dynamic Query [](id=dynamic-query)

Liferay allows you to use custom SQL queries to retrieve data from the
database. However, it's sometimes more convenient to build queries dynamically
at runtime than it is to invoke predefined SQL queries. Liferay allows you to
build queries dynamically using its DynamicQuery API, which wraps Hibernate's
Criteria API. Using Liferay's DynamicQuery API allows you to build queries
without writing a single line of SQL. The DynamicQuery API helps you think in
terms of objects and member variables instead of in terms of tables and
columns. Complex queries constructed via Hibernate's Criteria API can be
significantly easier to understand and maintain than the equivalent custom SQL
(or HQL) queries. While you technically don't need to know SQL to construct
queries via Hibernate's Criteria API, you still need to take care to construct
efficient queries. For information on Hibernate's Criteria API, please see
[Hibernate's manual](http://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/chapters/query/criteria/Criteria.html).
In this tutorial, you'll learn how to create custom finders for Liferay
applications using Service Builder and Liferay's Dynamic Query API.

To use Liferay's Dynamic Query API, you need to create a finder implementation
for your model entity. You can define model entities in `service.xml` and run
Service Builder to generate model, persistence, and service layers for your
application. See the
[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
learning path for more information on using Service Builder. This tutorial,
like the learning path, assumes that you're creating a Liferay application that
consists of a service module, and API module, and a web module. Once you've
used Service Builder to generate model, persistence, and service layers for
your application, you can create custom finders using Liferay's Dynamic Query
API by following these steps:

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
the generated `[package path].service.persistence` package of your service
module's `docroot/WEB-INF/src` folder. Recall that you specify the package path
in `service.xml`. Here's an example:

    <service-builder package-path="com.liferay.docs.guestbook">
    ...
    </service-builder>

Then define a `findBy-` finder method in the class you created. Make sure to
add any required arguments to your finder method's method signature.

For example, consider a fictitous Guestbook application.
In this application, there are two entities: guestbooks and entries. Each entry
belongs to a guestbook so the entry entity has a `guestbookId` field as a
foreign key. Suppose you need to create a custom finder to search for guestbook
entries by the entry name and the guestbook name. In this case, you'd add a
custom finder method to `GuestbookFinderImpl` and name it something like
`findByEntryNameGuestbookName`. The full method signature would appear as
`findByEntryNameGuestbookName(String entryName, String guestbookName)`.

Once you've created a finder method with the appropriate method signature in
your finder class, run Service Builder to generate the appropriate interface
and utility class in the `[Package Path].service.persistence` package in the
`service` folders of your API and service modules.

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

## Step 2: Implementing Your Custom Finder Method Using Dynamic Query [](id=step-2-implementing-your-custom-finder-method-using-dynamic-query)

Your first step in implementing your custom finder method in your `-FinderImpl`
class is to open a new Hibernate session. Since your `-FinderImpl` class extends
`BasePersistenceImpl<Entity>` and `BasePersistenceImpl<Entity>` contains a
session factory object and an `openSession` method, you can simply invoke the
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
`DynamicQueryFactoryUtil` service. Thus, instead of

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

Projections in Hibernate's Criteria API allow you to modify the kind of results
returned by a query. For example, if you don't want your query to return a list
of entity objects (the default), you can set a projection on a query so that
only a list of the values of a certain entity field, or fields, is returned.
You can also use projections on a query to return the maximum or minimum value
of an entity field, or the sum of all the values of a field, or the average,
etc. For more information on restrictions and projections, please refer to
Hibernate's
[documentation](http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/querycriteria.html).

Orders, another feature of Hibernate's Criteria API, allow you to control the
order of the elements in the list returned by a query. You can choose the
property or properties to which an order should be applied and you can choose
for the properties to appear in ascending or descending order in the list.

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
`RestrictionsFactoryUtil` service. You'll find the same methods in Liferay's
`RestrictionsFactoryUtil` service class that you're used to from Hibernate's
`Restrictions` class: `in`, `between`, `like`, `eq`, `ne`, `gt`, `ge`, `lt`,
`le`, etc.

Thus, instead of 

    entryCriteria.add(Restrictions.eq("name", guestbookName));

to specify that a guestbook must have a certain name, you use

    entryQuery.add(RestrictionsFactoryUtil.eq("name", guestbookName));

Similarly, to set projections, you create properties via Liferay's
`PropertyFactoryUtil` service instead of through Hibernate's `Property` class.
Thus, instead of 

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

    List<Event> entries = EventLocalServiceUtil.dynamicQuery(entryQuery);

By default, `DynamicQueryFactoryUtil.forClass(Entry.class)` returns of list of
all guestbook entry entities. The `.add(RestrictionsFactoryUtil.eq("name",
entryName))` restriction limits the results to only those guestbook entries
whose names match the `entryName` parameter of the finder method.
`PropertyFactoryUtil` is a Liferay utility class with a single method,
`forName(String propertyName)`, that returns the specified property. This
property can be passed to another Liferay dynamic query. This is exactly what
happens in the following line of our example:

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
`EventLocalServiceUtil.dynamicQuery(eventQuery)`, or
`EventLocalServiceUtil.dynamicQuery(eventQuery, start, end)` or
`EventLocalServiceUtil.dynamicQuery(eventQuery, start, end, orderByComparator)`.

Great! You've now created a custom finder method using Liferay's Dynamic Query
API. Your last step is to add a service method that calls your finder.

## Step 3: Accessing Your Custom Finder Method from the Service Layer [](id=step-3-accessing-your-custom-finder-method-from-the-service-layer)

So far, you've created a `-FinderImpl` class and generated a `-FinderUtil`
utility class. Your portlet class, however, should not use the finder utility
class directly: only a local or remote service implementation (i.e.,
`-LocalServiceImpl` or `-ServiceImpl`) in your service module should invoke the
`-FinderUtil` class. This encourages a proper separation of concerns: the
portlet classes in your application's web module invoke the business logic of
the services published from your application's service module. The services, in
turn, access the data model using the persistence layer's finder classes. So
you'll add a method in the `-LocalServiceImpl` class that invokes the finder
method implementation via the `-FinderUtil` class. Then you'll rebuild your
application's service layer so that the portlet classes and JSPs in your web
module can access the services.

For example, for the Guestbook application, you'd add the following method to
the `EntryLocalServiceImpl` class:

    public List<Entry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {

        return EntryFinderUtil.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

After you've added your `findBy-` method to your `-LocalServiceImpl` class, run
Service Builder to generate the interface and make the finder method available
in the `EntryLocalServiceUtil` class.

Now you can indirectly call the finder method from your portlet class or from a
JSP by calling `EntryLocalServiceUtil.findByEntryNameGuestbookName(...)`!

Congratulations on following the three step process of developing a dynamic
query in a custom finder and exposing it as a service for your portlet!

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)

[Creating Local Service](/develop/tutorials/-/knowledge_base/7-0/creating-local-services)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)
