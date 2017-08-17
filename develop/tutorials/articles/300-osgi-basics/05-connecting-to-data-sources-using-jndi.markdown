# Connecting to Data Sources Using JNDI [](id=connecting-to-data-sources-using-jndi)

Your application server's Java Naming and Directory Inteface (JNDI)
implementation lets you access data outside of @product@'s default data source.
@product-ver@'s OSGi runtime requires using @product@'s classloader to access
JNDI data sources. This tutorial demonstrates the following things:

-   [Using JNDI to connect to a data source](using-jndi-to-connect-to-a-data-source)
-   [Configuring a JNDI Data source](configuring-a-jndi-data-source)

## Using JNDI to Connect to a Data Source [](id=using-jndi-to-connect-to-a-data-source)

Using @product@'s JNDI data source from inside modules or traditional plugins is
straightforward. The following example code demonstrates this.

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

@product@'s OSGi runtime lets you
access the `javax.naming` API the application server provides. The example code
sets the @product@'s classloader on the thread to use JNDI to connect with
the data source. After working with the data source, the code reinstates the
thread's original classloader. 

You can use similar code to access a data source from your application. Make
sure to substitute `TestDB` with your data source name and replace the SQL
statements and result set code with your own.

## Configuring a JNDI Data Source [](id=configuring-a-jndi-data-source)

Configuring JNDI data sources can vary between application servers. Let's
consider accessing a MariaDB database called `TestDB` from an Apache Tomcat
application server.  `TestDB` has a table called `testdata`, that
has three columns: `id`, `foo`, and `bar`. And there's one table record. (This follows the Apache Tomcat JNDI Datasource example
[here](https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html)).
To configure the database as a JNDI data source available to @product@, follow
these steps:

1.  Edit the `[LIFERAY_HOME]/tomcat-8.0.32/conf/Catalina/localhost/ROOT.xml`
    file and add this resource element within the `context` element:

        <Resource name="jdbc/TestDB" auth="Container"
            type="javax.sql.DataSource" maxTotal="100" maxIdle="30"
            maxWaitMillis="10000" username="yourusername"
            password="yourpassword" driverClassName="org.mariadb.jdbc.Driver"
            url="jdbc:mariadb://localhost:3306/javatest"/>

2.  Edit the `[LIFERAY_HOME]/tomcat-8.0.32/webapps/ROOT/WEB-INF/web.xml` file
    and add this resource reference element just before the closing `web-app`
    tag:

        <resource-ref>
           <res-ref-name>jdbc/TestDB</res-ref-name>
           <res-type>javax.sql.DataSource</res-type>
           <res-auth>Container</res-auth>
       </resource-ref>

Following the data source connection instructions the previous section
explained, you can access JNDI data sources like this one from your modules and
traditional plugins.

## Related Topics [](id=related-topics)

-   [Using Data Sources](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually#using-data-sources)
