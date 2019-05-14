# Adding to the web.xml with Ext Plugins [](id=adding-to-the-web-xml-with-ext-plugins)

A supported use case for using Ext Plugins in @product@ is adding additional
functionality to its `web.xml` file. Before beginning, make sure you've reviewed
the generalized
[Customization with Ext Plugins](/docs/7-2/reference/-/knowledge_base/r/customizing-core-functionality-with-ext)
section.

As an example, you'll create a sample Ext plugin that adds to your @product@'s
existing `web.xml` file (e.g., in the `/tomcat-[version]/webapps/ROOT/WEB-INF`
folder). You'll add a new printout in the console during startup.

1.  Navigate to your Liferay Workspace's root folder and run the following
    command:

        blade create -t war-core-ext add-printout

    Your Ext plugin is generated and now resides in the workspace's `/ext`
    folder with the name you assigned.

2.  For your @product@ installation to recognize new functionality in the
    `web.xml`, you must create a class that implements the
    [ServletContextListener](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/ServletContextListener.html)
    interface. This class will initialize a servlet context event for which
    you'll add your new functionality. In the `extImpl/java` folder, create the
    folder structure representing the package name you want your new class to
    reside in (e.g., `com/liferay/portal/servlet/context`). Then create your new
    Java class:

        package com.liferay.portal.servlet.context;

        import javax.servlet.ServletContextEvent;
        import javax.servlet.ServletContextListener;

        public class ExtAddEntryWebXmlPortalContextLoaderListener
                implements ServletContextListener {

            public void contextDestroyed(ServletContextEvent servletContextEvent) {
            }

            public void contextInitialized(ServletContextEvent servletContextEvent) {
                System.out.println("EXT_ADD_ENTRY_WEBXML_INSTALLED_SUCCESSFULLY");
            }

        }

    The above class includes two methods that initialize and destroy your
    servlet context event. Be sure to add the new `web.xml`'s functionality when
    the portal context is initializing. To add a printout verifying the Ext
    plugins installation, a simple print statement was defined in the
    `contextInitialized(...)` method:

        System.out.println("EXT_ADD_ENTRY_WEBXML_INSTALLED_SUCCESSFULLY");

3.  Now that you've defined a servlet context event, you should add a listener
    to your `web.xml` that listens for it. In the `ext-web/docroot/WEB-INF`
    folder, open the `web.xml` file, which was generated for you by default.

4.  Add the following <listener> tag between the <web-app> tags:

        <listener>
            <listener-class>com.liferay.portal.servlet.context.ExtAddEntryWebXmlPortalContextLoaderListener</listener-class>
        </listener>

Excellent! Now when your Ext plugin is deployed, your @product@ installation
will create a `ServletContextListener` instance, which will initialize a custom
servlet context event. This event will be recognized by the `web.xml` file,
which will add the new functionality to your @product@ installation. Follow the
instructions in the
[Deploy the Plugin](/docs/7-2/reference/-/knowledge_base/r/deploying-an-ext-plugin)
article for help deploying the Ext plugin to your server.
