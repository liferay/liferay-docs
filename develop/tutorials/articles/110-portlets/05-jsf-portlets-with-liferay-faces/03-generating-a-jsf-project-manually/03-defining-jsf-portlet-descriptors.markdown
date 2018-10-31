# Defining JSF Portlet Descriptors [](id=defining-jsf-portlet-descriptors)

Since JSF portlets must follow a WAR-style folder structure, they must also have
WAR-style portlet descriptors.

1.  Create a `portlet.xml` file in the `webapp/WEB-INF` folder. All portlet WARs
    require this file. In this file, make sure to declare the following portlet
    class:

        <portlet>
            ...
            <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
            ...
        </portlet>

    The `javax.portlet.faces.GenericFacesPortlet` class handles invocations to
    your JSF portlet and makes your portlet, since it relies on Liferay Faces
    Bridge, easy to develop by acting as a turnkey implementation.

2.  Define a default view file as an `init-param` in the `portlet.xml`. This
    ensures your portlet is visible when deployed to @product@.

        <init-param>
            <name>javax.portlet.faces.defaultViewId.view</name>
            <value>/WEB-INF/views/view.xhtml</value>
        </init-param>

    You'll create this view later. 

    The `portlet.xml` file holds other important details too, like portlet info
    and security settings. Look at the `portlet.xml` file for the example Hello
    User JSF application.

        <?xml version="1.0"?>

        <portlet-app xmlns="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" version="2.0">
            <portlet>
                <portlet-name>hello-user-jsf-portlet</portlet-name>
                <display-name>Hello User JSF Portlet</display-name>
                <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
                <init-param>
                    <name>javax.portlet.faces.defaultViewId.view</name>
                    <value>/WEB-INF/views/view.xhtml</value>
                </init-param>
                <expiration-cache>0</expiration-cache>
                <supports>
                    <mime-type>text/html</mime-type>
                </supports>
                <portlet-info>
                    <title>Hello User JSF Portlet</title>
                    <short-title>Hello User</short-title>
                    <keywords>com.liferay.hello.user.jsf.portlet</keywords>
                </portlet-info>
                <security-role-ref>
                    <role-name>administrator</role-name>
                </security-role-ref>
                <security-role-ref>
                    <role-name>guest</role-name>
                </security-role-ref>
                <security-role-ref>
                    <role-name>power-user</role-name>
                </security-role-ref>
                <security-role-ref>
                    <role-name>user</role-name>
                </security-role-ref>
            </portlet>
        </portlet-app>

    The above configuration sets your portlet's various names, MIME type,
    expiration cache, and security roles.

    <!-- Should probably discuss security settings in more detail elsewhere,
    similar to /develop/tutorials/-/knowledge_base/6-2/using-portal-roles-in-a-portlet.
    It would be getting a bit off track talking about it here, though. -Cody -->

3.  Create a `web.xml` file in your JSF application's `webapp/WEB-INF` folder.
    The `web.xml` file serves as a deployment descriptor that provides necessary
    configurations for your JSF portlet to deploy and function in @product@.
    Copy the XML code below into your Hello User JSF application.

        <?xml version="1.0" encoding="UTF-8"?>

        <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">

            <context-param>
                <param-name>javax.faces.PROJECT_STAGE</param-name>
                <param-value>${project.stage}</param-value>
            </context-param>
            <context-param>
                <param-name>javax.faces.WEBAPP_RESOURCES_DIRECTORY</param-name>
                <param-value>/WEB-INF/resources</param-value>
            </context-param>
            <servlet>
                <servlet-name>Faces Servlet</servlet-name>
                <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
                <load-on-startup>1</load-on-startup>
            </servlet>
            <security-constraint>
                <display-name>Prevent direct access to Facelet XHTML</display-name>
                <web-resource-collection>
                    <web-resource-name>Facelet XHTML</web-resource-name>
                    <url-pattern>*.xhtml</url-pattern>
                </web-resource-collection>
                <auth-constraint/>
            </security-constraint>
        </web-app>

    First, you set the `javax.faces.PROJECT_STAGE` parameter to the
    `${project.stage}` variable, which is defined in your build file (e.g.,
    `pom.xml`) as `Development`. When set to `Development`, the JSF
    implementation will perform the following steps at runtime:

    1. Log more verbose messages.
    2. Render tips and/or warnings in the view markup.
    3. Cause the default `ExceptionHandler` to display a developer-friendly
    error page.

    The `javax.faces.WEBAPP_RESOURCES_DIRECTORY` parameter sets the resources
    folder inside the `WEB-INF` folder. This setting makes the resources in that
    folder (e.g., CSS, JavaScript, XHTML) secure from non-JSF calls. You'll
    [create resources](/develop/tutorials/-/knowledge_base/7-1/defining-resources-for-a-jsf-application)
    for your app later.

    The Faces Servlet configuration is required to initialize JSF and should be
    defined in all JSF portlets deployed to @product@.

    Finally, a security restraint is set on Facelet XHTML, which prevents direct
    access to XHTML files in your JSF application.

4.  Create a `faces-config.xml` file in your JSF application's `webapp/WEB-INF`
    folder. The `faces-config.xml` descriptor is a JSF portlet's application
    configuration file, which is used to register and configure objects and
    navigation rules. The Hello User portlet's `faces-config.xml` file has the
    following contents:

        <?xml version="1.0"?>

        <faces-config version="2.2"
            xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd"
        >
            <lifecycle>
                <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
            </lifecycle>
        </faces-config>

    Many auto-generated `faces-config.xml` files have the following
    configuration:

        <lifecycle>
            <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
        </lifecycle>

    This configures your JSF portlet to log the before/after phases of the JSF
    lifecycle to your console in debug mode. Remove this declaration before
    deploying to production.

Great! You now have a good idea of how to specify and define general descriptor
files for your JSF portlet. JSF portlets also use Liferay descriptors, which you
can learn more about in the
[Liferay Descriptors](/develop/tutorials/-/knowledge_base/7-1/spring-mvc#liferay-descriptors)
sub-section.

Now that your portlet descriptors are defined, you'll begin working on your JSF
application's resources next.
