---
header-id: database-templates
---

# Database Templates

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/database-templates.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Below are templates (example
[portal properties](/docs/7-2/deploy/-/knowledge_base/d/portal-properties))
for configuring various databases as a built-in data source for @product@.

| **Note:** The [Liferay DXP Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?) specifies supported databases and environments.

## MariaDB

```properties
jdbc.default.driverClassName=org.mariadb.jdbc.Driver
jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.default.username=
jdbc.default.password=
```

## MySQL

| **Note:** MySQL Connector/J 8.0 is highly recommended for use with MySQL
| Server 8.0 and 5.7.

```properties
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true
jdbc.default.username=
jdbc.default.password=
```

## PostgreSQL

```properties
jdbc.default.driverClassName=org.postgresql.Driver
jdbc.default.url=jdbc:postgresql://localhost:5432/lportal
jdbc.default.username=sa
jdbc.default.password
```

See the
[default portal properties](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#JDBC)
for more database templates.
