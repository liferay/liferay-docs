# Implementing a Custom Finder Method Using Dynamic Query [](id=implementing-a-custom-finder-method-using-dynamic-query)

Once you've
[defined your custom finder method](/develop/tutorials/-/knowledge_base/7-1/defining-a-custom-finder-method),
you can use the Dynamic Query API to implement your query in it. Here's what you
must do in your finder method:

1.  [Open a Hibernate Session](#using-a-hibernate-session)

2.  [Create a dynamic query using these Hibernate features](#creating-dynamic-queries): 
 
    -   *Restrictions*: Similar to `where` clauses of an SQL query, 
        restrictions limit results based on criteria. 
    -   *Projections*: Modify the kind of results the query returns. 
    -   *Orders*: Organize results.

3.  [Execute the Dynamic Query and return the results](#executing-the-dynamic-query) 

Before implementing a dynamic query in your own finder method, it can be helpful
to examine an example. The following example method uses multiple dynamic
queries and all the Hibernate features. Instructions for implementing your own
finder method follow the example. 

## Example Finder Method: findByGuestbookNameEntryName [](id=example-finder-method-findbyguestbooknameentryname)

This finder method for the Guestbook application retrieves a list of Guestbook
entries that have a specific name and that also belong to a Guestbook of a
specific name:

    public List<Entry> findByEntryNameGuestbookName(String entryName, String guestbookName) {

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

            List<Entry> entries = _entryLocalService.dynamicQuery(entryQuery);

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
    }

The method first opens a Hibernate session. While the session is open in the
`try` block, it creates and executes a dynamic query, which returns results (a
list of guestbook `Entry` objects) if all goes well. 

The finder method has two distinct dynamic queries.

1.  The first query retrieves a list of guestbook IDs corresponding to guestbook
    names that match the `guestbookName` parameter of the finder method.

2.  The second query retrieves a list of guestbook entries with entry names that
    match the `entryName` parameter and have `guestbookId` foreign keys
    belonging to the list returned by the first query.

Here's the first query:

    DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
        .add(RestrictionsFactoryUtil.eq("name", guestbookName))
        .setProjection(ProjectionFactoryUtil.property("guestbookId"));

By default, `DynamicQueryFactoryUtil.forClass(Guestbook.class)` returns a query
that retrieves a list of all guestbook entities. Adding the
`.add(RestrictionsFactoryUtil.eq("name", guestbookName))` restriction limits the
results to only those guestbooks whose guestbook names match the `guestbookName`
parameter. The `.setProjection(ProjectionFactoryUtil.property("guestbookId"))`
projection changes the result set from a list of guestbook entries to a list of
guestbook IDs. This is useful since guestbook IDs are much less expensive to
retrieve than full guestbook entities, and the entry query only needs the
guestbook IDs. 

Next appears an order:

    Order order = OrderFactoryUtil.desc("modifiedDate");

This arranges the results list in descending order of the query entity's
`modifiedDate` attribute. Thus the most recently modified entities (guestbook
entries, in our example) appear first and the least recently modified entities
appear last.

Here's the second query:

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class)
        .add(RestrictionsFactoryUtil.eq("name", entryName))
        .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))
        .addOrder(order);

By default, `DynamicQueryFactoryUtil.forClass(Entry.class)` returns a list of
all guestbook entry entities. The `.add(RestrictionsFactoryUtil.eq("name",
entryName))` restriction limits the results to only those guestbook entries
whose names match the finder method's `entryName` parameter.
[`PropertyFactoryUtil`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/PropertyFactoryUtil.html)
is a Liferay utility class whose method `forName(String propertyName)` returns
the specified property. This property can be passed to another Liferay dynamic
query. This is exactly what happens in the following line of our example:

    .add(PropertyFactoryUtil.forName("guestbookId").in(guestbookQuery))

Here, the code makes sure that the guestbook IDs (foreign keys) of the entry
entities in the `entityQuery` belong to the list of guestbook IDs returned
by the `guestbookQuery`. Declaring that an entity property in one query must
belong to the result list of another query is a way to use the dynamic query API
to create complex queries, similar to SQL joins.

Lastly, the order defined earlier is applied to the entries returned by the
`findByEntryNameGuestbookName` finder method:

    .addOrder(order);

This orders the list of guestbook entities by the `modifiedDate` attribute, from
most recent to least recent.

Lastly, the dynamic query is invoked on the `EntryLocalService` instance. It
returns a list of `Entry` objects which are then returned by the finder method. 

    List<Entry> entries = _entryLocalService.dynamicQuery(entryQuery);

    return entries;

It's time to implement your finder method to use Dynamic Query. Start with
opening and managing a Hibernate session. 

## Using a Hibernate Session [](id=using-a-hibernate-session)

Your first step in implementing your custom finder method in your `*FinderImpl`
class is to open a new Hibernate session. Since your `*FinderImpl` class extends
`BasePersistenceImpl<Entity>`, and `BasePersistenceImpl<Entity>` contains a
session factory object and an
[`openSession`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/persistence/impl/BasePersistenceImpl.html#openSession--)
method, you can simply invoke the `openSession` method of your `*FinderImpl`'s
parent class to open a new Hibernate session. The structure of your finder
method should look like this:

    public List<Entity> findBy-(...) {

        Session session = null;
        try {
                session = openSession();
                
                /*
                create a dynamic
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

Next, in the try block, create your dynamic query objects. 

## Creating Dynamic Queries [](id=creating-dynamic-queries)

In Liferay, you don't create criteria objects directly from the
Hibernate session. Instead, you create dynamic query objects using Liferay's
[`DynamicQueryFactoryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/DynamicQueryFactoryUtil.html)
service. Thus, instead of

    Criteria entryCriteria = session.createCriteria(Entry.class);

you use

    DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(Entry.class);

In your finder method, initialize your dynamic query for your entity class. 

Most features of Hibernate's Criteria API, including restrictions, projections,
and orders, can be used on Liferay dynamic query objects. Each criteria can be
applied to your query. The restriction criteria type is described first. 

### Restriction Criteria [](id=restriction-criteria)

Restrictions in Hibernate's Criteria API roughly correspond to the `where`
clause of an SQL query: they offer a variety of ways to limit the results
returned by the query. You can use restrictions, for example, to cause a query
to return only results where a certain field has a particular value, or a value
in a certain range, or a non-null value, etc.

When you need to add restrictions to a dynamic query, don't call Hibernate's
`Restrictions` class directly. Instead, use the
[`RestrictionsFactoryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/RestrictionsFactoryUtil.html)
service. `RestrictionsFactoryUtil` has the same methods that you're used to from
Hibernate's `Restrictions` class: `in`, `between`, `like`, `eq`, `ne`, `gt`,
`ge`, `lt`, `le`, etc.

Thus, instead of using the following call to specify that a guestbook must have
a certain name,

    entryCriteria.add(Restrictions.eq("name", guestbookName));

you use

    entryQuery.add(RestrictionsFactoryUtil.eq("name", guestbookName));

The restriction above limits the results to guestbook entries whose `name`
attribute matches the value of the variable `guestbookName`. Add the
restrictions you need to get the results you want. 

Projections are the next criteria type. They let you transform the query results
to return the field type you desire.  

### Projection Criteria [](id=projection-criteria)

Projections in Hibernate's Criteria API let you modify the kind of results
returned by a query. For example, if you don't want your query to return a list
of entity objects (the default), you can set a projection on a query to return
only a list of the values of a certain entity field, or fields. You can also use
projections on a query to return the maximum or minimum value of an entity
field, or the sum of all the values of a field, or the average, etc. For more
information on restrictions and projections, please refer to Hibernate's
[documentation](http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/querycriteria.html).

Similarly, to set projections, create properties via Liferay's
[PropertyFactoryUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/PropertyFactoryUtil.html)
service instead of through Hibernate's `Property` class. Thus, instead of 

    entryCriteria.setProjection(Property.forName("guestbookId"));

you use

    entryQuery.setProjection(PropertyFactoryUtil.forName("guestbookId"));

The projection above specifies the `guestbookId` entity field to changes the
result set to a list of those field values. If you want to return a specific
field type from your entities, add a projection for it. 

The last criteria type lets you organize results your way. 

### Order Criteria [](id=order-criteria)

Orders in Hibernate's Criteria API let you control the order of the elements in
the list a query returns. You can choose the property or properties to which an
order applies as well as whether they're in ascending or descending order.

This code creates an order by the entity's `modifiedDate` attribute:

    Order order = OrderFactoryUtil.desc("modifiedDate");

When you apply this order, the results are arranged in descending order of the
query entity's `modifiedDate` attribute. Thus the most recently modified
entities (guestbook entries, in our example) appear first and the least recently
modified entities appear last.
 
Like Hibernate criteria, Liferay's dynamic queries are *chain-able*: you can add
criteria to, set projections on, and add orders to Liferay's dynamic query
objects just by appending the appropriate method calls to the query object. For
example, the following snippet demonstrates chaining a restriction criterion and
a projection to a dynamic query object declaration:

    DynamicQuery guestbookQuery = DynamicQueryFactoryUtil.forClass(Guestbook.class)
        .add(RestrictionsFactoryUtil.eq("name", guestbookName))
        .setProjection(ProjectionFactoryUtil.property("guestbookId"));

It's time to execute your dynamic query.

## Executing the Dynamic Query [](id=executing-the-dynamic-query)

In the previous tutorial, you ran Service Builder after
[defining your custom finder](/develop/tutorials/-/knowledge_base/7-1/defining-a-custom-finder-method).
Service Builder
generated a `dynamicQuery(DynamicQuery dynamicQuery)` method in your
`*LocalServiceBaseImpl` class. Using a `*LocalService` instance, invoke
`dynamicQuery` method, passing it your dynamic query. Here's an example dynamic
query execution.

    List<Entity> entities = _someLocalService.dynamicQuery(entityQuery);

    return entities;

The dynamic query execution returns a list of entities and the finder method
returns that list. 

+$$$

**Note:** Service Builder not only generates a `public List
dynamicQuery(DynamicQuery dynamicQuery)` method in `*LocalServiceBaseImpl` but
it also generates `public List dynamicQuery(DynamicQuery dynamicQuery, int
start, int end)` and `public List dynamicQuery(DynamicQuery dynamicQuery, int
start, int end, OrderByComparator orderByComparator)` methods. You can go back
to
[defining custom finder methods](/develop/tutorials/-/knowledge_base/7-1/defining-a-custom-finder-method)
and either modify your finder method or create overloaded versions of it to take
advantage of these extra methods and their parameters. The `int start` and `int
end` parameters are useful when paginating a result list. `start` is the lower
bound of the range of model entity instances and `end` is the upper bound. The
`OrderByComparator orderByComparator` is the comparator by which to order the
results.

$$$

To use the overloaded `dynamicQuery` methods of your `*LocalServiceBaseImpl`
class in the (optionally overloaded) custom finders of your `*FinderImpl` class,
just choose the appropriate methods for running the dynamic queries: 
`dynamicQuery(entryQuery)`, or
`dynamicQuery(entryQuery, start, end)` or
`dynamicQuery(entryQuery, start, end, orderByComparator)`.

Great! You've now created a finder method using Liferay's Dynamic Query API.
Your last step is to add a service method that calls your finder.
