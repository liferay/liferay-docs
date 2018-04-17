# Dynamic Query [](id=dynamic-query)

@product@ lets you use custom SQL queries to retrieve data from the database.
However, it's sometimes more convenient to build queries dynamically at runtime
than it is to invoke predefined SQL queries. Liferay allows you to build queries
dynamically using its Dynamic Query API, which wraps Hibernate's Criteria API.
The Dynamic Query API lets you build queries without writing any SQL. It helps
you think in terms of objects and member variables instead of tables and
columns. Complex queries constructed via Hibernate's Criteria API can be
significantly easier to understand and maintain than the equivalent custom SQL
(or HQL) queries. While you technically don't need to know SQL to construct
queries via Hibernate's Criteria API, you still must take care to construct
efficient queries. For information on Hibernate's Criteria API, please see
[Hibernate's manual](http://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/chapters/query/criteria/Criteria.html).
This tutorial demonstrates creating custom finders for Liferay applications
using Service Builder and Dynamic Query API.

To use Liferay's Dynamic Query API, you need to create a finder implementation
for your model entity. You can
[define model entities in `service.xml`](/develop/tutorials/-/knowledge_base/7-1/defining-an-object-relational-map-with-service-builder)
and
[run Service Builder](/develop/tutorials/-/knowledge_base/7-1/running-service-builder-and-understanding-the-generated-code)
to generate model, persistence, and service layers for your application. This
tutorial assumes that you're creating a Liferay application that consists of a
service module, an API module, and a web module. Once you've used Service
Builder to generate model, persistence, and service layers for your application,
follow these steps to call custom finders using the Dynamic Query API:

1. [Define a custom finder method](/develop/tutorials/-/knowledge_base/7-1/defining-a-custom-finder-method).

2. [Implement your finder using the Dynamic Query API](/develop/tutorials/-/knowledge_base/7-1/implementing-a-custom-finder-method-using-dynamic-query).

3. [Add a method to your `*LocalServiceImpl` class that invokes your finder method](/develop/tutorials/-/knowledge_base/7-1/accessing-your-custom-finder-method-from-the-service-layer). 

Once you've taken these steps, you can access your custom finder as a service
method. Note: You can create multiple or overloaded `findBy*` finder methods in
your `*FinderImpl` class. Next, let's examine these steps in more detail.
