---
header-id: database-templates
---

# Database Templates

[TOC levels=1-4]

Below are templates (example
[portal properties](/docs/7-2/deploy/-/knowledge_base/deploy/portal-properties))
for configuring various databases as a built-in data source for @product@. 

## MariaDB

```properties
jdbc.default.driverClassName=org.mariadb.jdbc.Driver
jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.default.username=
jdbc.default.password=
```

## MySQL

```properties
jdbc.default.driverClassName=com.mysql.jdbc.Driver
jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
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
