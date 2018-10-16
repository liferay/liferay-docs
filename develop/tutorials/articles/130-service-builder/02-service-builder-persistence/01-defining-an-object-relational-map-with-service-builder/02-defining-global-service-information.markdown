# Defining Global Service Information [](id=defining-global-service-information)

A service's global information applies to all its entities, so it's a good place
to start. In Liferay @ide@, select the *Service Builder* node in the upper left
corner of the Overview mode of your `service.xml` file. The main section of the
view now shows the Service Builder form in which to enter your service's global
information. The fields include the service's

- [Package path](#package-path)
- [Namespace options](#namespace-options)
- [Multiversion concurrency control](#multiversion-concurrency-control-mvcc)
- [Author](#author)

![Figure 1: This is the Service Builder form from the Bookmarks application's `service.xml`.](../../../../images/service-builder-main-form.png)

## Package Path [](id=package-path)

The package path specifies the package in which the service and persistence
classes are generated. The package path for Bookmarks ensures that the `*-api`
module's service classes are generated in the `com.liferay.bookmarks` package.
The persistence classes are generated in a package of the same name in the
`*-service` module. For example, examine the Bookmarks application's
[bookmarks-api](https://github.com/liferay/liferay-portal/tree/master/modules/apps/bookmarks/bookmarks-api)
and
[bookmarks-service](https://github.com/liferay/liferay-portal/tree/master/modules/apps/bookmarks/bookmarks-service)
modules to see how these are automatically generated for you. A
later tutorial 
[describes the package content](/develop/tutorials/-/knowledge_base/7-1/running-service-builder). 

## Namespace Options [](id=namespace-options)

Service Builder uses the service namespace in naming the database tables it
generates for the service. For example, *Bookmarks* could serve as the namespace for
a Bookmarks application service. Service Builder uses the namespace in the
following SQL scripts it generates in your `src/main/resources/sql` folder:

- `indexes.sql`
- `sequences.sql`
- `tables.sql`

+$$$

**Note:** The generated SQL script folder location is configurable. For example,
if you're using Gradle, you can define the `sqlDir` attribute in the app's
`build.gradle` file. Likewise, in Ant you can configure an argument in the
`build.xml` similar to this one:

    <arg value="service.sql.dir=${basedir}/../sql"/>

$$$

Service Builder uses the SQL scripts to create database tables for all the
entities the `service.xml` defines. The database table names have the namespace
prepended when they are created. Since the example namespace value is
`Bookmarks`, the database table names created for the entities start with
`Bookmarks_` as their prefix. Each Service Builder project's namespace must be
unique. Separate plugins should use separate namespaces and should not use
a namespace already used by Liferay entities (such as `Users` or `Groups`).
Check the table names in Liferay's database to see the namespaces already in
use.

+$$$

**Warning:** Use caution when assigning namespace values. Some databases have
strong restrictions on database table name lengths.

$$$

## Multiversion concurrency control (MVCC) [](id=multiversion-concurrency-control-mvcc)

The `service-builder` element's `mvcc-enabled` attribute is `false` by default.
Setting `mvcc-enabled="true"` (hint: edit `service.xml` in *Source* view)
enables
[multiversion concurrency control](https://en.wikipedia.org/wiki/Multiversion_concurrency_control)
(MVCC) for all of the service's entities. In systems, concurrent updates are
common. Without MVCC people may read or overwrite data from an invalid state
unknowingly. With MVCC, each modification is made upon a given base version
number. When Hibernate receives the update, it generates an `update` SQL
statement that uses a `where` clause to make sure the current data version is
the version you expect. 

If the current data version
 
- **matches the expected version**, your data operation is based on up-to-date 
  data and is accepted.

- **doesn't match the expected version**, the data you're operating on is
  outdated. @product@ rejects your data operation and throws an exception,
  which you can catch to help the user handle the exception (e.g., suggest
  retrying the operation). 

**Important:** Enable MVCC for all your services by setting 
`mvcc-enabled="true"` in your `<service-builder/>` element. When invoking
service entity updates (e.g., `fooService.update(object)`), make sure to do so
in transactions. Propagate rejected transactions to the UI for the user to
handle. 

## Author [](id=author)

As the last piece of global information, enter your name as the service's
*author* in your `service.xml` file. Service Builder adds `@author` annotations
with the specified name to all the Java classes and interfaces it generates.
Save your `service.xml` file. Next, you'll add entities for your services. 
