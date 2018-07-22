# Connecting to JNDI Data Sources [](id=connecting-to-data-sources-using-jndi)

Connecting to an application server's JNDI data sources from @product@'s OSGi
environment is almost the same as connecting to them from the Java EE
environment. In an OSGi environment, the only difference is that you must use
@product@'s class loader to load the application server's JNDI classes. The
following code demonstrates this.

    Thread thread = Thread.currentThread();

    // Get the thread's class loader. You'll reinstate it after using
    // the data source you look up using JNDI

    ClassLoader origLoader = thread.getContextClassLoader();

    // Set Liferay's class loader on the thread

    thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());

    try {

            // Look up the data source and connect to it

            InitialContext ctx = new InitialContext();
            DataSource datasource = (DataSource)
                ctx.lookup("java:comp/env/jdbc/TestDB");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();

            // Execute SQL statements here ...

            connection.close();
    }
    catch (NamingException ne) {

        ne.printStackTrace();
    }
	catch (SQLException sqle) {

		sqle.printStackTrace();
	}
    finally {
           // Switch back to the original context class loader

           thread.setContextClassLoader(origLoader);
    }

The example code sets @product@'s classloader on the thread to access the JNDI
API. 

    thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());

It uses JNDI to look up the data source.

    InitialContext ctx = new InitialContext();
    DataSource datasource = (DataSource)
        ctx.lookup("java:comp/env/jdbc/TestDB"); 

After working with the data source, the code reinstates the thread's
original classloader.

     thread.setContextClassLoader(origLoader);

Here are the class imports for the example code:

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.naming.InitialContext;
    import javax.naming.NamingException;
    import javax.sql.DataSource;
    import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

Your applications can use similar code to access a data source. Make sure to
substitute `jdbc/TestDB` with your data source name. 

+$$$

**Note**: An OSGi bundle's attempt to connect to a JNDI data source without
using @product@'s classloader results in a `java.lang.ClassNotFoundException`.
For example, here's an exception from attempting to use Apache Tomcat's JNDI API
without using @product@'s classloader:

    javax.naming.NoInitialContextException: Cannot instantiate class:
    org.apache.naming.java.javaURLContextFactory [Root exception is
    java.lang.ClassNotFoundException:
    org.apache.naming.java.javaURLContextFactory]

$$$

An easier way to work with databases is to 
[connect to them using Service Builder](/develop/tutorials/-/knowledge_base/7-1/connecting-service-builder-to-external-databases). 

## Related Topics [](id=related-topics)

[Connecting Service Builder to External Databases](/develop/tutorials/-/knowledge_base/7-1/connecting-service-builder-to-external-databases)
