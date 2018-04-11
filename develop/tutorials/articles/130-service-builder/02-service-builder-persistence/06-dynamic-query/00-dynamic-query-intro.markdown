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
