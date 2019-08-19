# Connecting Service Builder to an External Database 

Sometimes you want to use a database other than @product@'s. Doing this requires doing these things:

1.  Define a data source for your database. 

2.  Connect your Service Builder module to the data source. 

3.  Run Service Builder. 

There are two different ways to create the connection.

1.  **`DataSourceProvider`:** This approach involves implementing a `TODO` and 
    registering it with your module using a JDK
    [ServiceProviderInterface](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html)
    (SPI). This approach works regardless of whether your Service Builder module uses the `ds` or `spring`
    [dependency injection option](TODO). 

2.  **Spring Beans:** Configure the connection using Spring XML files. This 
    approach only works with  Service Builder module that use the `spring` [dependency injection option](TODO). 
    
| **Note**: All entities defined in a Service Builder module's `service.xml` 
| file are bound to the same data source. Binding different entities to
| different data sources requires defining the entities in separate Service
| Builder modules and configuring each of the modules to use a different data
| source.

Since `DataSourceProvider` is the most versatile approach, it's explained first. 
 
## Using a `DataSourceProvider` 

Connecting to an external data base by creating and registering a `DataSourceProvider` as a JDK
[ServiceProviderInterface](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html)
(SPI) requires the fewest files and steps. 

1.  Define your data source. One way is to use
    [portal properties](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    in a `portal-ext.properties` file. Distinguish your data source from Liferay's default data source by giving it a prefix other
than `jdbc.default.`. This example uses prefix `jdbc.ext.`. 

```properties
jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
jdbc.ext.password=userpassword
jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.ext.username=yourusername
```

2.  Restart your server if you defined your data source using portal properties.

3.  Implement the
    [`DataSourceProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/jdbc/DataSourceProvider.html)
    interface. Since the `DataSourceProvider` must be visible to your `*-service` module's class
    loader, it's common to put the `DataSourceProvider` in that module. 

    This example implementation uses
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
    [ServiceProviderInterface](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html)
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

Now your module's Service Builder services persist data to the external data
source. 

## Using Spring Beans

TODO
