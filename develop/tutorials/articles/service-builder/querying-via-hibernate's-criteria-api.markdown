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

Our next step is to actually define our query in our custom finder method using
the Dynamic Query API.

### Step 2: Implementing Your Custom Finder Method Using Dynamic Query

2. Create your dynamic query in your custom `findBy...` method using Liferay's
   Dynamic Query API.

### Step 3: Accessing Your Custom Finder Method from the Service Layer

3. Add a `findBy...` method to `[Entity]LocalServiceImpl.java` that calls the
   `findBy...` method that Service Builder generated in
   `[Entity]FinderUtil.java`. Since `[Entity]FinderUtil.java` is generated in
   the `persistence` package, it's a best practice not to call it directly but
   to instead call `[Entity]LocalServiceUtil.java`. For example, if you created
   a custom finder method called `findByUserId` in `GuestbookFinderImpl.java`,
   then you'd create a `findByUserId` method in `GuestbookLocalServiceImpl.java`
   that calls the `findByUserId` method of `GuestbookFinderUtil.java`.


