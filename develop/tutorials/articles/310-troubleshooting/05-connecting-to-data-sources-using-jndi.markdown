# Connecting to Data Sources Using JNDI [](id=connecting-to-data-sources-using-jndi)

Your application server's Java Naming and Directory Interface (JNDI)
implementation lets you access data outside @product@'s data sources.
@product-ver@'s OSGi runtime requires using @product@'s class loader to load the
application server's JNDI classes for accessing its data sources. This tutorial
demonstrates how. 

## Using JNDI to Connect to a Data Source [](id=using-jndi-to-connect-to-a-data-source)

You can use @product@'s JNDI data source from inside modules or traditional
plugins. The following example code demonstrates this.

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

Here are the class imports for the code above:

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.naming.InitialContext;
    import javax.naming.NamingException;
    import javax.sql.DataSource;
    import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

In traditional Java EE environments, you can access the application server's
`javax.naming` API directly. In @product@'s OSGi environment, you must use
@product@'s classloader to access it. The example code sets @product@'s
classloader on the thread to access the classes needed to use JNDI. After
working with the data source, the code reinstates the thread's original
classloader.

You can use similar code to access a data source from your application. Make
sure to substitute `jdbc/TestDB` with your data source name and invoke SQL
statements as you desire.

How you define a JNDI data source depends on your application server. If you
need help defining a JNDI data source, see your application server's
documentation. If you're running Tomcat, for  example, see 
[Tomcat's JNDI Datasource HOW-TO](https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html).

Configure @product@ with your external data source following
[@product@'s manual installation documentation](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-manually#manual-configuration).

## Related Topics [](id=related-topics)

[Installing @product@ Manually](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually)
