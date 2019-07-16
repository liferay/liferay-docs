---
header-id: defining-global-service-information
---

# Defining Global Service Information

[TOC levels=1-4]

A service's global information applies to all its entities. It contains the 

- [Dependency Injector](#dependency-injector)
- [Package path](#package-path)
- [Namespace options](#namespace-options)
- [Multiversion concurrency control](#multiversion-concurrency-control-mvcc)
- [Author](#author)

## Dependency Injector

The default dependency injector is OSGi Declarative Services. This makes Service
Builder work consistently the way other modules do. Prior versions of Liferay
used Spring. The only difference is how you inject the services when you 
[go to use them later](/docs/7-2/appdev/-/knowledge_base/a/invoking-services-from-service-builder-code). 

Declarative Services Dependency Injector: 

```xml

<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.2.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_2_0.dtd">

<service-builder dependency-injector="ds"
		 package-path="com.liferay.docs.guestbook">
```

Spring Dependency Injector: 

```xml

<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.2.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_2_0.dtd">

<service-builder dependency-injector="spring"
		 package-path="com.liferay.docs.guestbook">
```

## Package Path

The package path specifies the package where the service and persistence
classes are generated. The package path for Guestbook ensures that the `*-api`
module's service classes are generated in the `com.liferay.docs.guestbook` package.
The persistence classes are generated in a package of the same name in the
`*-service` module. A later article 
[describes the package content](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder). 

## Multiversion concurrency control (MVCC)

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

```xml
<service-builder dependency-injector="ds"
		 package-path="com.liferay.docs.guestbook"
		 mvcc-enabled="true">
```

## Namespace Options

Service Builder names the database tables using the service namespace. For
example, *GB* could serve as the namespace for a Guestbook application
service. Service Builder uses the namespace in the following SQL scripts it
generates in your `src/main/resources/sql` folder:

- `indexes.sql`
- `sequences.sql`
- `tables.sql`

| **Note:** The generated SQL script folder location is configurable. For example,
| if you're using Gradle, you can define the `sqlDir` attribute in the app's
| `build.gradle` file. Likewise, in Ant you can configure an argument in the
| `build.xml` similar to this one:
| 
|     <arg value="service.sql.dir=${basedir}/../sql"/>

Service Builder uses the SQL scripts to create database tables for all the
entities the `service.xml` defines. The database table names have the namespace
prepended when they are created. Since the example namespace value is
`GB`, the database table names created for the entities start with
`GB__` as their prefix. Each Service Builder project's namespace must be
unique. Separate plugins should use separate namespaces and should not use
a namespace already used by Liferay entities (such as `Users` or `Groups`).
Check the table names in Liferay's database to see the namespaces already in
use.

| **Warning:** Use caution when assigning namespace values. Some databases have
| strong restrictions on database table name lengths.

```xml

<namespace>GB</namespace>
```

## Author

As the last piece of global information, enter your name as the service's
*author* in your `service.xml` file. Service Builder adds `@author` annotations
with the specified name to all the Java classes and interfaces it generates.
Save your `service.xml` file. Next, you'll add entities for your services. 

```xml

<author>Liferay</author>
```

