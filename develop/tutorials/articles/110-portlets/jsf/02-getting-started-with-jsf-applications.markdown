# Getting Started with JSF Applications [](id=getting-started-with-jsf-applications)

@product@'s modular architecture lends itself well to modular applications
created by a multitude of different technologies. JSF applications are no
different, and can be developed to seamlessly integrate into the Liferay
platform. In this tutorial, you'll step through packaging and creating a JSF
application that is deployable as an OSGi module at runtime. First, you'll learn
how to package a JSF application as a module.

## Packaging a JSF Application

Developers creating portlets for Liferay 7.0 can package their portlets as Java
EE style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR) OSGi
bundle artifacts. JSF portlet developers, however, must package their portlets
as WAR artifacts because the JSF framework expects a WAR layout and often
requires the `WEB-INF/faces-config.xml` descriptor and other Java EE resources
such as the `WEB-INF/web.xml` descriptor.

Liferay 7.0 supports the OSGi Web Application Bundle (WAB) standard for
deployment of Java EE style WARs. Simply put, a WAB is an archive that has a WAR
layout and contains a `META-INF/MANIFEST.MF` file with the `Bundle-SymbolicName`
OSGi directive. Although the source of the project has a WAR layout, the
artifact filename may end with either the `.jar` or `.war` extension.

Enabling WABs to run as OSGi modules at runtime is made possible by the Liferay
WAB Extender. The Liferay WAB Generator converts your WAR artifact to a WAB at
deployment time. You can learn more about the WAB Generator in the
[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
tutorial.

This is how a JSF WAR artifact is structured: 

- `META-INF/`
    - `MANIFEST.MF` (Made OSGi-ready at deploy time via the WAB Generator)
- `WEB-INF/`
    - `classes/`
        - Class files and related properties
    - `lib/`
        - JAR dependencies
    - `resources/`
        - CSS, XHTML, PNG or other frontend files
    - `views/`
        - XHTML views
    - `faces-config.xml`
    - `liferay-plugin-package.properties`
    - `liferay-portlet.xml`
    - `portlet.xml`
    - `web.xml`

In the next section, you'll begin creating a simple JSF application that is
deployable to @product@.

## Creating a JSF Application

Liferay supports developing and deploying JSF portlets on Liferay Portal by
using
[Liferay Faces Bridge](/develop/reference/-/knowledge_base/7-0/understanding-liferay-faces-bridge).
The bridge supports deploying JSF web applications as portlets to @product@.
Liferay Faces Bridge makes developing JSF portlets as similar as possible to JSF
web app development. In this section, you'll learn the portlet development
process and how to leverage Liferay Faces Bridge's full potential with your JSF
portlets. This makes it easy for you to implement portlets using JSF.

You'll create a simple *Hello User* application that asks for the user's name,
and then greets him or her with the name. You'll begin by creating the WAR-style
folder structure and configure dependencies like Liferay Faces Bridge.

1.  Create a WAR-style folder structure for your module.
    [Maven archetypes](https://web.liferay.com/web/neil.griffin/blog/-/blogs/new-maven-archetypes-for-jsf-portlets)
    are available to help you get started quickly. They set the default
    configuration for you, and contain boilerplate code so you can skip the file
    creation steps and get started right away. In this tutorial, you'll set up
    the folder structure manually. Follow the folder structure outline below:

        - com.liferay.hello.user.jsf.portlet
            - src
                - main
                    - java
                    - resources
                    - webapp
                        - WEB-INF
                            - resources
                            - views

2.  Make sure your module specifies the dependencies necessary for a Liferay JSF
    application. For instance, you must specify the Liferay Faces Bridge as a
    dependency. For example, this is the `pom.xml` file used from the Maven
    based Hello User JSF application:

        <?xml version="1.0"?>

        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.hello.user.jsf.portlet</artifactId>
            <packaging>war</packaging>
            <name>hello-user-jsf-portlet</name>
            <version>1.0-SNAPSHOT</version>
            <properties>
                <faces.api.version>2.2</faces.api.version>
                <liferay.faces.bridge.ext.version>5.0.0-SNAPSHOT</liferay.faces.bridge.ext.version>
                <liferay.faces.bridge.version>4.0.0-SNAPSHOT</liferay.faces.bridge.version>
                <mojarra.version>2.2.13</mojarra.version>
                <project.stage>Development</project.stage>
            </properties>
            <build>
                <plugins>
                    <plugin>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>3.3</version>
                        <configuration>
                            <encoding>UTF-8</encoding>
                            <source>1.8</source>
                            <target>1.8</target>
                        </configuration>
                    </plugin>
                    <plugin>
                        <artifactId>maven-war-plugin</artifactId>
                        <version>2.3</version>
                        <configuration>
                            <filteringDeploymentDescriptors>true</filteringDeploymentDescriptors>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
            <dependencies>
                <dependency>
                    <groupId>commons-fileupload</groupId>
                    <artifactId>commons-fileupload</artifactId>
                    <version>1.3.1</version>
                    <optional>true</optional>
                </dependency>
                <dependency>
                    <groupId>commons-io</groupId>
                    <artifactId>commons-io</artifactId>
                    <version>2.4</version>
                    <optional>true</optional>
                </dependency>
                <dependency>
                    <groupId>javax.faces</groupId>
                    <artifactId>javax.faces-api</artifactId>
                    <version>${faces.api.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>org.glassfish</groupId>
                    <artifactId>javax.faces</artifactId>
                    <version>${mojarra.version}</version>
                    <scope>runtime</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.faces</groupId>
                    <artifactId>com.liferay.faces.bridge.ext</artifactId>
                    <version>${liferay.faces.bridge.ext.version}</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.faces</groupId>
                    <artifactId>com.liferay.faces.bridge.impl</artifactId>
                    <version>${liferay.faces.bridge.version}</version>
                </dependency>
                <dependency>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                    <version>1.2.14</version>
                </dependency>
            </dependencies>
        </project>

    Note that in this example build configuration, there are several other
    dependencies besides Liferay Faces Bridge, which include the JSF API,
    Mojarra, and utility libraries like Commons IO. These dependenices are
    required for this tutorial's example JSF application, but they can be
    modified based on the application you're building.

    There are several UI component suites that a JSF application can use,
    which include
    [*Liferay Faces Alloy*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-alloy],
    [*PrimeFaces*](http://primefaces.org/),
    [*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf),
    and
    [*RichFaces*](http://richfaces.jboss.org/). Furthermore, you can take
    advantage of
    [*Liferay Faces Portal*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-portal)
    in order to use Liferay-specific utilities and UI components. These
    components can be used by specifying them as dependencies in your build
    file, as well.
    
    Now that your build file is configured, you must define the JSF-specific
    configurations for your application. There are two categories this tutorial
    will group the example portlet configurations into: general descriptors and
    Liferay descriptors. You'll start with creating the necessary general
    descriptors.

### Defining JSF Portlet Descriptors

Since JSF portlets must follow a WAR-style folder structure, they must also have
general WAR-style portlet descriptors.

1.  Create a `portlet.xml` file in the `webapp/WEB-INF` folder. All portlet WARs
    require this file. In this file, make sure to add the following
    configuration:

        <portlet>
            ...
            <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
            ...
        </portlet>

    The
    [`GenericFacesPortlet` class](https://myfaces.apache.org/portlet-bridge/2.0/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html)
    handles invocations to your JSF portlet and makes your portlet, since it
    relies on Liferay Faces Bridge, easier to develop by acting as a turnkey
    implementation.

4.  Define a default view file as an `init-param` in the `portlet.xml`. This
    ensures your portlet is visible when deployed to @product@.

        <init-param>
            <name>javax.portlet.faces.defaultViewId.view</name>
            <value>/WEB-INF/views/view.xhtml</value>
        </init-param>

    You'll create this view later in the tutorial.

    The `portlet.xml` file generally holds other important details for the
    portlet too, like general portlet info and security settings. Take a look at
    the `portlet.xml` file for the example Hello User JSF application.

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

    The above XML code sets your portlet's various names, MIME type, expiration
    cache, and security roles.

    <!-- Should probably discuss security settings in more detail elsewhere,
    similar to https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-portal-roles-in-a-portlet.
    It would be getting a bit off track talking about it here, though. -Cody -->

5.  Create a `web.xml` file in your JSF application's `webapp/WEB-INF` folder.
    The `web.xml` file serves as a deployment descriptor that provides necessary
    configurations for your JSF portlet to successfully deploy and function in
    @product@. Copy the XML code below into your Hello User JSF application.

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

    When setting the `javax.faces.PROJECT_STAGE` parameter to `Development`, the
    JSF implementation will do the following at runtime:

    1. Log more verbose messages.
    2. Render tips and/or warnings in the view markup.
    3. Cause the default `ExceptionHandler` to display a developer-friendly
    error page.

    Recall that you set the `${project.stage}` variable to `Development` in your
    build file in the previous section (e.g., `pom.xml`).

    The `javax.faces.WEBAPP_RESOURCES_DIRECTORY` parameter sets the resources
    directory inside the `WEB-INF` folder, which means your resources are secure
    from non-JSF calls. You'll create some resources in this folder later.

    The Faces Servlet configuration is required to initialize JSF, and should be
    defined in all JSF portlets deployed to @product@.

    Finally, a security restraint is set on Facelet XHTML, which prevents direct
    access to XHTML files in your JSF application.

6.  Create a `faces-config.xml` file in your JSF application's `webapp/WEB-INF`
    folder. The `faces-config.xml` descriptor serves as a JSF portlet's
    application configuration file, which is used to register and configure
    objects and navigation rules. The Hello User portlet's `faces-config.xml`
    file has the following contents:

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

    +$$$

    **Note:** Many auto-generated `faces-config.xml` files have the following
    configuration:

        <lifecycle>
            <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
        </lifecycle>

    This configures your JSF portlet to log the before/after phases of the JSF
    lifecycle to your console in debug mode. You might want to remove this
    declaration before deploying to production.

    $$$

Great! You now have a better idea of how to specify and define general
descriptor files for your JSF portlet. JSF portlets also use Liferay
descriptors, which you can further learn about in the
[Liferay Descriptors](/develop/tutorials/-/knowledge_base/7-0/spring-mvc#liferay-descriptors)
sub-section.

<!-- Pointing to existing Liferay descriptors section. I'll make sure to
redirect this link once Spring MVC is reorganized and the Liferay Descriptors
section is (possibly) relocated. -Cody -->

Now that your portlet descriptors are defined, you should begin working on your
JSF application's resources.

### Defining Resources for a JSF Application

If you look back at the structure of the example Hello User portlet, you'll
notice two `resources` folders defined. Why are there two of these folder for
one portlet? These two folders have distinct differences in how they're used and
what should be inserted in them.

The `resources` folder residing in the application's `src/main` folder is
intended for resources that need to be in the classpath. Generally, files in
this folder consist of properties files. For your Hello User example JSF
portlet, you'll create two properties files to reside in this folder.

1.  Create the `i18n.properties` file in the `src/main/resources` folder. Add
    the following property to this file:

        enter-your-name-in-the-field-below=Enter your name in the field below.

    This is a language key your JSF portlet displays in its view XHTML. The
    messages in the `i18n.properties` file can be accessed via the
    [Expression Language](http://docs.oracle.com/javaee/6/tutorial/doc/bnahq.html)
    using the implicit `i18n` object provided by Liferay Faces Util. When a
    portlet is deployed to @product@, the `i18n` object can also access messages
    found in a resource bundle defined in the portlet's `portlet.xml` file, and
    messages defined in @product@'s `Language.properties` file.

2.  Create the `log4j.properties` file in the `src/main/resources` folder. This
    file sets properties for the Log4j logging utility defined in your JSF
    application (i.e., `faces-config.xml`). Insert the properties below into
    your JSF application's `log4j.properties` file.

        log4j.rootLogger=INFO, CONSOLE

        log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
        log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
        log4j.appender.CONSOLE.layout.ConversionPattern=%d{ABSOLUTE} %-5p [%c{1}:%L] %m%n

        log4j.logger.com.liferay.faces.util.lifecycle.DebugPhaseListener=DEBUG

The second `resources` folder in your JSF application is located in the
`src/main/webapp/WEB-INF` folder. This folder holds CSS/JS/XHTML resources that
shouldn't be accessed directly by the browser. For the Hello User JSF
application, create a `css` folder with a `main.css` file inside. In the
`main.css` file, add the following logic:

    .com.liferay.hello.user.jsf.portlet {
        font-weight: bold;
    }

This file gives your JSF portlet bold font.

Now that your resources are defined, it's time to begin developing the Hello
User application's behavior and UI.

### Developing a JSF Application's Behavior and UI






















X.  Create a unique package name in the module's `src/main/java` folder and
    create a new public Java class named `ExampleBacking.java` in that package.
    For example, the class's folder structure could be
    `src/main/java/com/liferay/example/ExampleBacking.java`. Make sure the class
    is annotated with
    [@RequestScoped](http://docs.oracle.com/javaee/7/api/javax/faces/bean/RequestScoped.html)
    and
    [@ManagedBean](http://docs.oracle.com/javaee/7/api/javax/faces/bean/ManagedBean.html):

        @RequestScoped
        @ManagedBean
        public class ExampleBacking {




























This tutorial is not a comprehensive guide for configuring JSF applications in
Liferay. You'll examine the high points, assuming you already understand JSF. 

How and why is JSF so easy to deploy to Liferay? Liferay supports JSF portlets
with the use of [*Liferay Faces*](https://web.liferay.com/community/liferay-projects/liferay-faces/overview),
which is Liferay project that provides support for the JSF standard.

You'll learn how to leverage Liferay Faces and other JSF-specific dependencies
in the next section.

## Configuring a JSF Portlet for Liferay [](id=configuring-a-jsf-portlet-for-liferay)

JSF portlets use 
[*Liferay Faces Bridge*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-bridge),
which supports deploying JSF web applications as portlets on any JSR 286
(Portlet 2.0) compliant portlet container, like @product@. Liferay
Faces Bridge is applied to a portlet as a dependency in a build file, such as a
`pom.xml`:

    <dependencies>
        ...
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>${liferay.faces.bridge.ext.version}</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>${liferay.faces.bridge.version}</version>
        </dependency>
        ...
    </dependencies>

There are several UI component suites that a JSF application can use. These include

- [*Liferay Faces Alloy*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-alloy)
- [*PrimeFaces*](http://primefaces.org/)
- [*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf) 
- [*RichFaces*](http://richfaces.jboss.org/). 
- [*Liferay Faces Portal*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-portal)

You'd use Liferay Faces Portal to use Liferay-specific utilities and UI
components. These components can be used by specifying them as dependencies in
your build file, as well.

Make sure to configure the `GenericFacesPortlet` class in the `portlet.xml`.
This class handles invocations to your JSF portlet and makes portlets relying
on Liferay Faces Bridge easier to develop by acting as a turnkey implementation.

    <portlet>
        ...
        <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
        ...
    </portlet>

Also, make sure to define a default view file. Specifically, the view file is
configured as an `init-param` in the `portlet.xml` file.

    <init-param>
        <name>javax.portlet.faces.defaultViewId.view</name>
        <value>/WEB-INF/views/view.xhtml</value>
    </init-param>

There are other descriptors that must reside in your JSF portlet:
`faces-config.xml` and `web.xml`. You can visit the
[Packaging a JSF Application](/develop/tutorials/-/knowledge_base/7-0/packaging-a-jsf-application)
tutorial for more information on where these descriptors go. 

The `faces-config.xml` descriptor serves as a JSF portlet's application
configuration file, which is used to register and configure objects and
navigation rules.

+$$$

**Note:** Many auto-generated `faces-config.xml` files have the following
configuration:

    <lifecycle>
        <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
    </lifecycle>

This configures your JSF portlet to log the before/after phases of the JSF
lifecycle to your console in debug mode. You might want to remove this
declaration before deploying to production.

$$$

The `web.xml` file serves as a deployment descriptor that provides necessary
configurations for your JSF portlet to successfully deploy and function in
@product@. A sample `web.xml` for a JSF portlet is given below, with
explanations for each element documented in-line:

    <?xml version="1.0"?>

    <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
        <display-name>/jsf.test.portlet</display-name>
        <!-- Sets the resources directory inside the WEB-INF folder, which means your resources are secure from non-JSF calls.  -->
        <context-param>
            <param-name>javax.faces.WEBAPP_RESOURCES_DIRECTORY</param-name>
            <param-value>/WEB-INF/resources</param-value>
        </context-param>
        <!-- Instruct Mojarra to namespace parameters according to NamingContainer rules. -->
        <context-param>
            <param-name>com.sun.faces.namespaceParameters</param-name>
            <param-value>true</param-value>
        </context-param>
        <!-- Although the FacesServlet will not be invoked by any portlet requests, it is required to initialize JSF. -->
        <servlet>
            <servlet-name>FacesServlet</servlet-name>
            <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <!-- MyFaces will not initialize unless a servlet-mapping to the Faces Servlet is present. -->
        <servlet-mapping>
            <servlet-name>Faces Servlet</servlet-name>
            <url-pattern>*.xhtml</url-pattern>
        </servlet-mapping>
        <!-- Prevent direct access to Facelet XHTML -->
        <security-constraint>
            <web-resource-collection>
                <web-resource-name>Facelet XHTML</web-resource-name>
                <url-pattern>*.xhtml</url-pattern>
            </web-resource-collection>
            <auth-constraint/>
        </security-constraint>
    </web-app>

JSF portlets also use Liferay descriptors, which you can further learn about in
the
[Liferay Descriptors](/develop/tutorials/-/knowledge_base/7-0/spring-mvc#liferay-descriptors)
sub-section.

Next, you'll learn how to generate and call services in a Liferay JSF portlet.

## Services in JSF [](id=services-in-jsf)

Service Builder works the same in a JSF portlet as it would in any other
standard WAR-style MVC portlet. To learn more about how Service Builder works in
Liferay, visit the
[Business Logic and Data Access](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)
tutorials. You can create a `service.xml` file and run Service Builder in your
JSF project to build services.

To call OSGi-based Service Builder services from your JSF portlet, you need a
mechanism that gives you access to the OSGi service registry. You can't look up
services published to the OSGi runtime using Declarative Services. Instead, you
should open a
[ServiceTracker](https://osgi.org/javadoc/r6/core/org/osgi/util/tracker/ServiceTracker.html)
when you want to call a service that's in the OSGi service registry.

To implement a service tracker in your JSF portlet, you can add a type-safe
wrapper class that extends `org.osgi.util.tracker.ServiceTracker`. For example,
this is done in a demo JSF portlet as follows:

    public class UserLocalServiceTracker extends ServiceTracker<UserLocalService, UserLocalService> {

        public UserLocalServiceTracker(BundleContext bundleContext) {
            super(bundleContext, UserLocalService.class, null);
        }
    }

After extending the `ServiceTracker`, just call the constructor and the service
tracker is ready to use in your managed bean.

In a managed bean, whenever you need to call a service, open the service
tracker. For example, this is done in the same demo JSF portlet to open the
service tracker, using the
[@PostContruct](http://docs.oracle.com/javaee/7/api/javax/annotation/PostConstruct.html)
annotation:

    @PostConstruct
    public void postConstruct() {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        BundleContext bundleContext = bundle.getBundleContext();
        userLocalServiceTracker = new UserLocalServiceTracker(bundleContext);
        userLocalServiceTracker.open();
    }

Then the service can be called:

    UserLocalService userLocalService = userLocalServiceTracker.getService();
    ...

    userLocalService.updateUser(user);

When it's time for the managed bean to go out of scope, you must close the
service tracker using the
[@PreDestroy](http://docs.oracle.com/javaee/7/api/javax/annotation/PreDestroy.html)
annotation:

    @PreDestroy
    public void preDestroy() {
        userLocalServiceTracker.close();
    }

For more information on service trackers and how to use them in WAR-style
portlets, see the
[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers)
tutorial.

Hopefully this introductory tutorial for JSF portlets developed for Liferay 7 is
helpful for a big-picture look on the JSF technology developed for @product@. To
learn more in-depth concepts and configurations for JSF portlets, visit the
other tutorials contained in the
[JSF Portlets with Liferay Faces](/develop/tutorials/-/knowledge_base/7-0/jsf-portlets-with-liferay-faces)
tutorial set.

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
