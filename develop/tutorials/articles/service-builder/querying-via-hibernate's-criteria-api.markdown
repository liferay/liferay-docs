# Querying Liferay's Database via Hibernate's Criteria API

Liferay allows you to use custom SQL queries to retrieve data from the database.
However, sometimes it's more convenient to dynamically build queries than it is
to write custom SQL queries. Liferay allows you to dynamically build queries
using its DynamicQuery API, which wrap's Hibernate's Criteria API. Using
Hibernate's Criteria API allows you to dynamically build queries while thinking
in terms of objects and member variables instead of writing custom SQL queries
while thinking in terms of tables and columns. Complex queries constructed via
Hibernate's Criteria API are often significantly easier to understand and
maintain than the equivalent custom SQL (or HQL) queries. In fact, you don't
even need to know SQL to construct queries via Hibernate's Criteria API. For
information on Hibernate's Criteria API, please see [Hibernate's manual](http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/#querycriteria).
In this tutorial, we'll learn how to use Liferay's Dynamic Query API.

## Using Dynamic Query

To use Liferay's Dynamic Query API, you need to have a model entity for which to
create a finder. You can define model entities in `service.xml` and use Service
Builder to generate model, persistence, and service layers for your application.
See the [Service Builder Learning Path](www.liferay.com) for more information on
using Service Builder. To define a query via Liferay's Dynamic Query API, you
need to take two steps:

1. Define a custom `findBy...` finder method in `[Entity]FinderImpl.java` and
   run Service Builder. This custom `findBy...` method is where you'll create
   your dynamic query. For example, if your custom entity is called `Guestbook`,
   then you'd define your your custom `findBy...` finder method in
   `GuestbookFinderImpl.java` and then run Service Builder.

2. Add a `findBy...` method to `[Entity]LocalServiceImpl.java` that calls the
   `findBy...` method that Service Builder generated in
   `[Entity]FinderUtil.java`. Since `[Entity]FinderUtil.java` is generated in
   the `persistence` package, it's a best practice not to call it directly but
   to instead call `[Entity]LocalServiceUtil.java`. For example, if you created
   a custom finder method called `findByUserId` in `GuestbookFinderImpl.java`,
   then you'd create a `findByUserId` method in `GuestbookLocalServiceImpl.java`
   that calls the `findByUserId` method of `GuestbookFinderUtil.java`.


