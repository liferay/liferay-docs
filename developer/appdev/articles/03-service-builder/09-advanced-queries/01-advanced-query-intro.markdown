---
header-id: advanced-queries
---

# Advanced Queries

[TOC levels=1-4]

Service Builder doesn't limit you to what you can cook up with `<finder />`
elements in `service.xml`. If simple finders aren't sufficient for getting data
out of your application, you can use Liferay's Dynamic Query API, which wraps
Hibernate's Criteria API, or your own SQL to make exactly the queries you need. 

Though you can use custom SQL queries with Service Builder to retrieve data
from the database, sometimes it's more convenient to build queries dynamically
at runtime. You can do this with Liferay's Dynamic Query API, which wraps
Hibernate's Criteria API. The Dynamic Query API lets you build queries without
writing any SQL. It helps you think in terms of objects and member variables
instead of tables and columns. Complex queries can be significantly easier to
understand and maintain than the equivalent custom SQL (or HQL) queries. While
you technically don't need to know SQL to construct Dynamic Queries, you still
must take care to construct efficient queries. For information on Hibernate's
Criteria API, please see 
[Hibernate's manual](http://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/chapters/query/criteria/Criteria.html).

Whichever way you decide to implement your custom queries, this guide shows you
how. Here are the steps: 

1.  If using SQL, [create your SQL query](/docs/7-2/appdev/-/knowledge_base/a/create-your-sql-query).

2.  [Define a custom finder class](/docs/7-2/appdev/-/knowledge_base/a/defining-a-custom-finder-class).

3.  [Implement your finder using Dynamic Query API or SQL](/docs/7-2/appdev/-/knowledge_base/a/implementing-a-custom-finder-method-using-dynamic-query).

4.  [Add a method to your `*LocalServiceImpl` class that invokes your finder](/docs/7-2/appdev/-/knowledge_base/a/accessing-your-custom-finder-method-from-the-service-layer). 

Once you've taken these steps, you can access your custom finder as a service
method. Note: You can create multiple or overloaded `findBy*` finder methods in
your `*FinderImpl` class. Next, you'll examine these steps in more detail.
