# Getting Started with JSF Applications [](id=getting-started-with-jsf-applications)

@product@'s modular architecture lends itself well to modular applications
created using a multitude of different technologies. JSF applications are no
different and can be developed to integrate seamlessly into the Liferay
platform. 

In this tutorial, you'll step through packaging and creating a JSF
application that is deployable as an OSGi module at runtime. First, you'll learn
how to package a JSF application as a module.

## Packaging a JSF Application [](id=packaging-a-jsf-application)

Developers creating portlets for Liferay 7.0 can package their portlets as Java
EE style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR) OSGi
bundle artifacts. JSF portlet developers, however, must package their portlets
as WAR artifacts because the JSF framework expects a WAR layout and often
requires the `WEB-INF/faces-config.xml` descriptor and other Java EE resources
such as the `WEB-INF/web.xml` descriptor.

Liferay provides a way for these WAR-styled portlets to be deployed and treated
like OSGi modules by Liferay's OSGi runtime. The WAB Generator does this
automatically by converting your WAR artifact to a WAB at deployment time. You
can learn more about WABs and the WAB Generator in the
[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
tutorial.

This is how a JSF WAR artifact is structured: 

- `jsf-portlet`
    - `src`
        - `main`
            - `java`
                - Java Classes
            - `resources`
                - Properties files
            - `webapp`
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
                    - `liferay-display.xml`
                    - `liferay-plugin-package.properties`
                    - `liferay-portlet.xml`
                    - `portlet.xml`
                    - `web.xml`

Next, you'll begin creating a simple JSF application that is deployable to
@product@.

## Creating a JSF Application [](id=creating-a-jsf-application)

JSF portlets are supported on Liferay Portal by using
[Liferay Faces Bridge](/develop/reference/-/knowledge_base/7-0/understanding-liferay-faces-bridge).
Liferay Faces Bridge makes developing JSF portlets as similar as possible to JSF
web app development. 

You'll create a simple *Hello User* application that asks for the user's name
and then greets him or her with the name. You'll begin by creating the WAR-style
folder structure, and then you'll configure dependencies like Liferay Faces
Bridge.

1.  Create a WAR-style folder structure for your module.
    [Maven archetypes](https://web.liferay.com/web/neil.griffin/blog/-/blogs/new-maven-archetypes-for-jsf-portlets)
    are available to help you get started quickly. They set the default
    configuration for you and contain boilerplate code so you can skip the file
    creation steps and get started right away. For your JSF application, you'll
    set up the folder structure manually. Follow the folder structure outline
    below:

        - hello-user-jsf-portlet
            - src
                - main
                    - java
                    - resources
                    - webapp
                        - WEB-INF
                            - resources
                            - views

2.  Make sure your module specifies the dependencies necessary for a Liferay JSF
    application. For instance, you must always specify the Faces API, Faces
    Reference Implementation (Mojarra), and Liferay Faces Bridge as dependencies
    in a Liferay-compatible JSF application. Also, an important, but not
    required, dependency is the Log4j logging utility. This is highly
    recommended for development purposes because it logs DEBUG messages in the
    console. You'll configure the logging utility later.

    For an example build file, the `pom.xml` file used for the Maven based Hello
    User JSF application is below. All the dependencies described above are
    configured in the Hello User JSF application's `pom.xml` file.

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

    There are also two plugins the Hello User JSF application defined in its
    `pom.xml`:
    [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)
    and
    [maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/).
    These two plugins are responsible for building and compiling the JSF
    application using Maven.

    There are several UI component suites that a JSF application can use,
    which include
    [*Liferay Faces Alloy*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-alloy),
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
    configurations for your application. These fall into two convenient
    categories: general descriptors and Liferay descriptors. You'll start with
    creating the necessary general descriptors.

### Defining JSF Portlet Descriptors [](id=defining-jsf-portlet-descriptors)

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

    The
    [`GenericFacesPortlet` class](https://myfaces.apache.org/portlet-bridge/2.0/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html)
    handles invocations to your JSF portlet and makes your portlet, since it
    relies on Liferay Faces Bridge, easy to develop by acting as a turnkey
    implementation.

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
    similar to https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-portal-roles-in-a-portlet.
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
    folder inside the `WEB-INF` folder. This setting makes the resources in
    that folder (e.g., CSS, JavaScript, XHTML) secure from non-JSF calls. You'll
    [create resources](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-jsf-applications#defining-resources-for-a-jsf-application)
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
[Liferay Descriptors](/develop/tutorials/-/knowledge_base/7-0/spring-mvc#liferay-descriptors)
sub-section.

<!-- Pointing to existing Liferay descriptors section. I'll make sure to
redirect this link once Spring MVC is reorganized and the Liferay Descriptors
section is (possibly) relocated. -Cody -->

Now that your portlet descriptors are defined, you should begin working on your
JSF application's resources.

### Defining Resources for a JSF Application [](id=defining-resources-for-a-jsf-application)

If you look back at the Hello User portlet's structure, you'll notice two
`resources` folders defined. Why are there two of these folders for one portlet?
These two folders have distinct differences in how they're used and what should
be placed in them.

The `resources` folder in the application's `src/main` folder is intended for
resources that need to be on the classpath. Files in this folder are usually 
properties files. For this portlet, you'll create two properties files to reside
in this folder.

1.  Create the `i18n.properties` file in the `src/main/resources` folder. Add
    the following property to this file:

        enter-your-name=Enter your name:

    This is a language key your JSF portlet displays in its view XHTML. The
    messages in the `i18n.properties` file can be accessed via the
    [Expression Language](http://docs.oracle.com/javaee/6/tutorial/doc/bnahq.html)
    using the implicit `i18n` object provided by the Liferay Faces Util class.
    The `i18n` object can access messages both from a resource bundle
    defined in the portlet's `portlet.xml` file, and from @product@'s
    `Language.properties` file.

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
`main.css` file, add the following style:

    .com.liferay.hello.user.jsf.portlet {
        font-weight: bold;
    }

This file gives your JSF portlet a bold font.

Now that your resources are defined, it's time to begin developing the Hello
User application's behavior and UI.

### Developing a JSF Application's Behavior and UI [](id=developing-a-jsf-applications-behavior-and-ui)

Your current JSF application satisfies the requirements for portlet descriptors
and WAR-style structure, but it doesn't do anything yet. You'll learn how to
develop a JSF application's back-end and give it a simple UI next.

The first thing to do is create a Java class for your module. Your JSF portlet's
behavior is defined here. In the case of the Hello User portlet, you should
provide Java methods that can get/set a name and facilitate the submission
process.

1.  Create a unique package name in the module's `src/main/java` folder and
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

    Managed beans are Java beans that are managed by the JSF framework. Managed
    beans annotated with `@RequestScoped` are usually responsible for handling
    actions and listeners. JSF *manages* these beans by creating and removing
    the bean object from the server. Visit the linked annotations above for more
    details.

2.  Add the following methods and field to your `ExampleBacking.java` class:

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void submit(ActionEvent actionEvent) {
            FacesContextHelperUtil.addGlobalSuccessInfoMessage();
        }

        private String name;

    You've provided a getter and setter method for the private `name` field.
    You've also provided a `submit(...)` method, which is called when the
    *Submit* button is selected. A success info message is displayed once the
    method is invoked.

    You've defined your Hello User portlet's Java behavior; now create its UI!

3.  Create a `view.xhtml` file in the `webapp/WEB-INF/views` folder. Add the
    following logic to that file:

        <?xml version="1.0"?>

        <f:view
            xmlns="http://www.w3.org/1999/xhtml"
            xmlns:f="http://java.sun.com/jsf/core"
            xmlns:h="http://java.sun.com/jsf/html"
        >
            <h:head>
                <h:outputStylesheet library="css" name="main.css" />
            </h:head>
            <h:form>
                <h:messages globalOnly="true" />
                <h:outputLabel value="#{i18n['enter-your-name']}" />
                <h:inputText value="#{exampleBacking.name}" />
                <h:commandButton actionListener="#{exampleBacking.submit}" value="#{i18n['submit']}">
                    <f:ajax execute="@form" render="@form" />
                </h:commandButton>
                <br />
                <h:outputText value="Hello #{exampleBacking.name}" />
            </h:form>
        </f:view>

    The first thing to notice is the `main.css` file you created is specified
    here, which makes your portlet's heading typeface bold. Next, your form is
    defined within the `<h:form>` tags. The form asks the user to enter his or
    her name, and then sets that value to the `name` field in your Java class.
    The form uses the `<h:commandButton>` tag to execute the Submit button and
    render the form after submission.

    Notice the `i18n` object call for the `enter-your-name` and `submit`
    properties. The `enter-your-name` property was set in the `i18n.properties`
    file you specified, but what about the `submit` property? This was not
    defined in your portlet's `i18n.properties` file, so how does your portlet
    know to use the string *Submit* for your button? If you recall, the `i18n`
    object can also access messages in @product@'s `Language.properties` file.
    This is where the `submit` language key comes from.

    Finally, the `<h:outputText>` tag prints the submitted name on the page,
    prefixed with *Hello*.

Awesome! Your Hello User JSF application is complete! Deploy your WAR to
@product@. Remember, when your WAR-style portlet is deployed, it's converted to
a WAB via the WAB Generator. Visit the 
[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
tutorial for more information on this process and your portlet's resulting
folder structure.

![Figure 1: After submitting the user's name, it's displayed with a greeting.](../../../images/hello-user-jsf-portlet.png)

To recap, you created your JSF application in the following steps:

- Construct the WAR-style folder structure.
- Specify the necessary dependencies in a build file of your choice.
- Create JSF portlet descriptors and Liferay descriptors.
- Add resource files in the two designated `resources` folders.
- Define the portlet's behavior using a Java class.
- Design a view XHTML form to let the user interact with the portlet.

You can view the finished version of the Hello User JSF application by
downloading its
[ZIP file](https://dev.liferay.com/documents/10184/656312/hello-user-jsf-portlet.zip/8601978f-6072-4445-8e1d-86a2d5ae34ca?download=true).

Now you have the knowledge to create your own JSF applications!

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
