---
header-id: connecting-the-data-source-using-a-datasourceprovider
---

# Connecting the Data Source Using a DataSourceProvider

[TOC levels=1-4]

Connecting to an external database by creating and registering a
`DataSourceProvider` as a JDK
[`ServiceProviderInterface`](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html)
(SPI) is the easiest way. This approach works regardless of whether your Service
Builder module uses the `ds` or `spring`
[dependency injection option](/docs/7-2/appdev/-/knowledge_base/a/defining-global-service-information#dependency-injector)
and it requires the fewest files and steps. 

| **Note:** All entities defined in a Service Builder module's
| [`service.xml`](/docs/7-2/appdev/-/knowledge_base/a/creating-the-service-xml-file) 
| file are bound to the same data source. Binding different entities to
| different data sources requires defining the entities in separate Service
| Builder modules and configuring each of the modules to use a different data
| source.

| **Warning:** If your Service Builder services require nested transactions, 
| using an external data source may not be appropriate for you. Transactions
| between separate data sources cannot be fully nested. Rollbacks may not
| propagate between a module that uses an external data source and @product@
| services (or another app's services) that use a different data source. 

Here are the steps:

1.  Define your data source. One way is to use
    [portal properties](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    in a `portal-ext.properties` file. Distinguish your data source from
    Liferay's default data source by giving it a prefix other than
    `jdbc.default.`. This example uses prefix `jdbc.ext.`. 

```properties
jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
jdbc.ext.password=userpassword
jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.ext.username=yourusername
```

2.  Restart your server if you defined your data source using portal properties.

3.  Implement the
    [`DataSourceProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/jdbc/DataSourceProvider.html)
    interface. Since the `DataSourceProvider` must be visible to your
    `*-service` module class loader, it's common to put the `DataSourceProvider`
    in the `*-service` module. 

    This example uses
    [`DataSourceFactoryUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/jdbc/DataSourceFactoryUtil.html)
    to create a data source from
    [portal properties](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    that have the prefix `jdbc.ext.`. 

```java
import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataSourceProvider;
import com.liferay.portal.kernel.util.PropsUtil;

import javax.sql.DataSource;

public class DataSourceProviderImpl implements DataSourceProvider {

	@Override
	public DataSource getDataSource() {
		try {
			return DataSourceFactoryUtil.initDataSource(
				PropsUtil.getProperties("jdbc.ext.", true));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
```

4.  Register the implementation as a JDK
    [`ServiceProviderInterface`](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html)
    (SPI) in a
    `/META-INF/services/com.liferay.portal.kernel.dao.jdbc.DataSourceProvider`
    file in your `*-service` module. For example, this file registers the
    `DataSourceProvider` implementation from the previous step.

```
com.liferay.external.data.source.test.internal.DataSourceProviderImpl
````

5.  [Run Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder). 

6.  [Deploy](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)
    your `-service` module. If your `DataSourceProvider` is in a 
    different project, deploy it too. 

Congratulations! Your module's Service Builder services are persisting data to
your external data source. 

## Related Topics

[Connecting to JNDI Data Sources](/docs/7-2/appdev/-/knowledge_base/a/connecting-to-data-sources-using-jndi)

[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)

[Business Logic with Service Builder](/docs/7-2/appdev/-/knowledge_base/a/business-logic-with-service-builder)
