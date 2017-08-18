# Connecting to Data Sources Using JNDI [](id=connecting-to-data-sources-using-jndi)

Your application server's Java Naming and Directory Interface (JNDI)
implementation lets you access data outside @product@'s default data source.
@product-ver@'s OSGi runtime requires using @product@'s classloader to access
JNDI data sources. This tutorial demonstrates how:

-   [Using JNDI to connect to a data source](using-jndi-to-connect-to-a-data-source)
-   [Configuring a JNDI Data source](configuring-a-jndi-data-source)

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

            // Execute SQL statements via the data source connection

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select id, foo, bar from testdata");

            while (resultSet.next()) {

                System.out.println("Record:");
                String id = resultSet.getString("id");
                System.out.println("ID: " + id);
                String foo = resultSet.getString("foo");
                System.out.println("Foo: " + foo);
                String bar = resultSet.getString("bar");
                System.out.println("Bar: " + bar);
                System.out.println();
            }

            connection.close();
    }

    catch (NamingException ne) {

           ne.printStackTrace();
    }

    finally {
           // Switch back to the original context class loader

           thread.setContextClassLoader(origLoader);
    }

Here are the class imports for the code above:

    import java.sql.Connection;
    import java.sql.ResultSet;
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
sure to substitute `TestDB` with your data source name and replace the SQL
statements and result set code with your own.

## Related Topics [](id=related-topics)

-   [Using Data Sources](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually#using-data-sources)
