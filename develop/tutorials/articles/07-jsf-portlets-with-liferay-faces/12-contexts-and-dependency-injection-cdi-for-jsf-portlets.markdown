# Contexts and Dependency Injection for JSF Portlets [](id=contexts-and-dependency-injection-for-jsf-portlets)

In December 2009, [JSR 299](http://jcp.org/en/jsr/detail?id=299) introduced the
Contexts and Dependency Injection (CDI) 1.0 standard into the Java EE 6
platform. In April 2013, [JSR 346](http://jcp.org/en/jsr/detail?id=346) updated
CDI to version 1.1 for Java EE 7. In addition, [JSR 344](http://jcp.org/en/jsr/detail?id=344),
the JSF 2.2 specification which is
another component of Java EE 7, introduced a dependency on the CDI API for the
`javax.faces.view.ViewScoped` annotation and for the Faces Flows feature. JBoss
[Weld](http://seamframework.org/Weld) is the Reference Implementation (RI) for
CDI, and Apache [OpenWebBeans](http://openwebbeans.apache.org/) is another open
source implementation. 

Still wondering what CDI accomplishes? Visit the [CDI Specs](http://cdi-spec.org/)
to learn more about CDI and why it has become a popular component for JSF. 

This tutorial covers the following topics: 

- Configuring JSF Portlets to Use CDI
- Configuring the Liferay CDI Portlet Bridge
- Understanding CDI in JSF Annotations

First, you'll configure your portlets to use a CDI implementation, such as JBoss
Weld or [CanDI](http://www.caucho.com/candi-java-dependency-injection/). 

## Configuring JSF Portlets to Use CDI [](id=configuring-jsf-portlets-to-use-cdi)

Several app-server/portal/CDI-implementation combinations support using CDI with
JSF on Liferay Portal.

For Liferay Portal 6.2, you can use one of these combinations:

- GlassFish 3 with Weld
- [JBoss AS with Weld](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-jboss-7-1#jsf-configuration)
- Resin with [CanDI](http://www.caucho.com/candi-java-dependency-injection/)
- [Tomcat with Weld](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-tomcat-7)
- [WebLogic 12c with Weld](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-oracle-weblogic-12c-12-1-2-and-h)

For Liferay Portal 6.1, you can use one of these combinations:

- [GlassFish 3 with Weld](/discover/portal/-/knowledge_base/6-1/errata-for-using-liferay-portal-6-1#upgrading-weld-in-glassfish-3)
- [JBoss AS with Weld](/discover/portal/-/knowledge_base/6-1/errata-for-using-liferay-portal-6-1#upgrading-weld-in-jboss-7)
- [Resin with CanDI](/discover/portal/-/knowledge_base/6-1/errata-for-using-liferay-portal-6-1#page-504)
- [Tomcat with Weld](/discover/portal/-/knowledge_base/6-1/errata-for-using-liferay-portal-6-1#adding-mojarra-to-tomcat-7)

When developing portlets with CDI 1.0, you must include a `WEB-INF/beans.xml`
descriptor in your JSF portlet plugin's `.war` deployment, so that when the CDI
implementation scans the classpath it detects the CDI-related annotations of
your classes. 

Here's an example `WEB-INF/beans.xml` descriptor for a portlet plugin: 

    <beans xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
    </beans>
 
If you're deploying your portlet on JBoss AS 7, you must also include a
`WEB-INF/jboss-deployment-structure.xml` descriptor in your portlet plugin's
`.war` deployment to include the CDI-related modules. Here's an example of a
portlet's `WEB-INF/jboss-deployment-structure.xml` descriptor for JBoss: 

    <?xml version="1.0"?>
    <jboss-deployment-structure xmlns="urn:jboss:deployment-structure:1.0">
        <deployment>
            <exclusions>
                <module name="javaee.api" />
                <module name="org.apache.log4j" />
            </exclusions>
            <dependencies>
                <module name="com.liferay.portal" />
                <module name="javax.annotation.api" />
                <module name="javax.enterprise.api" />
                <module name="javax.inject.api" />
                <module name="javax.interceptor.api" />
                <module name="javax.validation.api" />
                <module name="javax.mail.api" />
                <module name="org.jboss.modules" />
            </dependencies>
        </deployment>
    </jboss-deployment-structure>

For a portlet to use Weld on most app servers (excluding Resin), the portlet's
`WEB-INF/web.xml` descriptor must include the following filter and filter
mapping: 

    <filter>    
        <filter-name>WeldCrossContextFilter</filter-name>    
        <filter-class>org.jboss.weld.servlet.WeldCrossContextFilter</filter-class>
    </filter>
    <filter-mapping>    
        <filter-name>WeldCrossContextFilter</filter-name>    
        <url-pattern>/*</url-pattern>    
        <dispatcher>INCLUDE</dispatcher>    
        <dispatcher>FORWARD</dispatcher>    
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>

Portlets on Resin can use the [CanDI](http://www.caucho.com/candi-java-dependency-injection/)
implementation of CDI, which Resin includes by default. 

If you're running on Tomcat and you choose not to include Weld in Tomcat's global
classpath, then you must include it in the portlet's classpath by adding it to
the portlet's `WEB-INF/lib` folder. 

If you're on Tomcat and you're using a Maven project for your portlet plugin,
you must specify the Weld servlet as a dependency. Here's an excerpt from a
Maven POM file that exemplifies specifying the Weld servlet dependency:

    <dependency>    
        <groupId>org.jboss.weld.servlet</groupId>    
        <artifactId>weld-servlet</artifactId>    
        <version>1.1.10.Final</version>
    </dependency>

If you're on Tomcat you must also add a Weld servlet listener in your portlet's
`WEB-INF/web.xml` file: 

    <listener>    
        <listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
    </listener>

Next, you'll learn how to configure the Liferay CDI Portlet Bridge

## Configuring the Liferay CDI Portlet Bridge [](id=configuring-the-liferay-cdi-portlet-bridge)

The Liferay CDI Portlet Bridge makes it possible to use CDI with your JSF
portlets on Liferay. Your JSF portlet projects must include the Liferay CDI
Portlet Bridge as a dependency.

For example, to specify the bridge dependency in a Maven project for Liferay
6.2, add to your POM's `dependencies` element a dependency like the following
one:

    <dependency>    
        <groupId>com.liferay.cdi</groupId>    
        <artifactId>cdi-portlet-bridge-shared</artifactId>    
        <version>6.2.0.2</version>
    </dependency>

The `WEB-INF/portlet.xml` descriptor of the portlet must include the following
markup: 

    <filter>    
        <filter-name>CDIPortletFilter</filter-name>    
        <filter-class>com.liferay.cdi.portlet.bridge.CDIPortletFilter</filter-class>
        <lifecycle>ACTION_PHASE</lifecycle>
        <lifecycle>EVENT_PHASE</lifecycle>
        <lifecycle>RENDER_PHASE</lifecycle>
        <lifecycle>RESOURCE_PHASE</lifecycle>
    </filter>
    <filter-mapping>
        <filter-name>CDIPortletFilter</filter-name>    
        <portlet-name>my-portlet-name</portlet-name>
    </filter-mapping>

Additionally, the portlet's `WEB-INF/web.xml` descriptor must include the
following declarations: 

    <filter>
        <filter-name>CDICrossContextFilter</filter-name>
        <filter-class>com.liferay.cdi.portlet.bridge.CDICrossContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>CDICrossContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>
    <listener>
        <listener-class>com.liferay.cdi.portlet.bridge.CDIContextListener</listener-class>
    </listener>

+$$$

**Tip:** The Liferay Faces Project features the
[jsf2-cdi-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-cdi-portlet)
demo (which is a variant of the
[jsf2-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
demo). It's a good idea to download and deploy the jsf2-cdi-portlet demo in your
development environment in order to verify that CDI functions properly. 

$$$

You've completed configuring the Liferay CDI Portlet Bridge for you JSF portlet.
For other configuration options dealing with CDI, visit the
[Configuring CDI for JSF Portlets](/develop/learning-paths/-/knowledge_base/6-2/configuring-cdi-for-jsf-portlets)
tutorial. 

Now that everything is configured, you are ready to begin development with CDI.

## Understanding CDI in JSF Annotations [](id=cdi-jsf-annotations-liferay-portal-6-2-dev-guide-en)

When developing portlets with CDI, you can annotate Java classes as CDI managed
beans using
[`@Named`](http://docs.oracle.com/javaee/6/api/javax/inject/Named.html) with the
following scopes: 

| CDI Annotation | Description |
|----------------|-------------|
| [`@ApplicationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html) | An `@ApplicationScoped` managed bean exists for the entire lifetime of the portlet application. |
| [`@ConversationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ConversationScoped.html) | A `@ConversationScoped` managed bean is created when `Conversation.begin()` is called and is scheduled for garbage collection when `Conversation.end()` is called. |
| [`@FlowScoped`](https://javaserverfaces.java.net/nonav/docs/2.2/javadocs/javax/faces/flow/FlowScoped.html) | A `@FlowScoped` managed bean is created when a JSF 2.2 Flow begins and scheduled for garbage collection when a JSF 2.2 Flow completes. |
| [`@RequestScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/RequestScoped.html) | A `@RequestScoped` managed bean exists during an `ActionRequest`, `RenderRequest`, or `ResourceRequest`. Beans that are created during the `ActionRequest` do not survive into the `RenderRequest`. |
| [`@SessionScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/SessionScoped.html) | A `@SessionScoped` managed bean exists for the duration of the user session. |

In addition to CDI scope annotations, it's important to understand JSF 2
annotations and their equivalence to CDI annotations. 

| JSF Annotation | Equivalent CDI Annotation |
|----------------|---------------------------|
| `javax.faces.ManagedBean` | [`javax.inject.Named`](http://docs.oracle.com/javaee/6/api/javax/inject/Named.html) |
| `javax.faces.ApplicationScoped` | [`javax.enterprise.context.ApplicationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html) |
| `javax.faces.RequestScoped` | No such equivalent, since [`javax.enterprise.context.RequestScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/RequestScoped.html) does not span portlet lifecycle phases. 
| `javax.faces.SessionScoped` | [`javax.enterprise.context.SessionScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/SessionScoped.html) |
| `javax.faces.ManagedProperty` (corresponding setter method required) | [`javax.inject.Inject`](http://docs.oracle.com/javaee/6/api/javax/inject/Inject.html) (corresponding setter method not required) |

This should help with your understanding of CDI and JSF annotations. And as
you've also seen in this tutorial, configuring CDI for your JSF portlets and
configuring the Liferay CDI Portlet Bridge is a snap!

**Related Topics**

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Communicating Between JSF Portlets Using IPC](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/communicating-between-jsf-portlets-using-ipc)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)
