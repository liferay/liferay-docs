---
header-id: cdi-dependency-injection
---

# CDI Dependency Injection

[TOC levels=1-4]

Portlet 3.0 (see [JSR 362](https://jcp.org/en/jsr/detail?id=362)) supports
Contexts and Dependency Injection (CDI) so you can create and use
injectable classes (CDI beans) in your portlet. It also provides injectable
portlet artifacts called
[Portlet Predefined Beans](/docs/7-2/reference/-/knowledge_base/r/cdi-portlet-predefined-beans).
They give a portlet's CDI beans access to the portlet configuration,
preferences, requests, responses, and more. Here's how to create and use CDI
beans and Portlet Predefined Beans:

1.  Create a portlet WAR project, if you haven't created one
    already.

    -   Any project that has a class that implements the
        [`javax.portlet.Portlet`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/Portlet.html)
        interface, either directly or indirectly.
    <!-- -   [Bean Portlet](/docs/7-2/appdev/-/knowledge_base/a/bean-portlet) TODO uncomment when Bean Portlet is available. jhinkey -->

    | **Note:** If you're developing a portlet JAR, such as a
    | [Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet),
    | use CDI via
    | [OSGi CDI Integration](/docs/7-2/frameworks/-/knowledge_base/f/osgi-cdi-integration).

    | **Note:**
    | @product@ exports the packages provided by the Portlet API and CDI API.
    | Liferay project templates typically include them as transitive
    | dependencies. If you must explicitly depend on the portlet API and CDI
    | artifacts, add them as `compileOnly` (Gradle) or `provided` (Maven)
    | dependencies.

2.  If your portlet WAR project isn't a Bean Portlet, add this
    `src/main/webapp/WEB-INF/beans.xml` file to it. This file tells CDI to
    scan the project for CDI annotations.

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://xmlns.jcp.org/xml/ns/javaee" bean-discovery-mode="all" version="1.2"
    	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	   xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd">
    	<!-- This file is necessary in order to inform CDI that scanning should occur for CDI annotations. -->
    </beans>
    ```

3.  Add the
    [`@ApplicationScoped`](https://docs.oracle.com/javaee/7/api/javax/enterprise/context/ApplicationScoped.html)
    annotation to your portlet class.

    ```java
    import javax.enterprise.context.ApplicationScoped;

    @ApplicationScoped
    public class MyPortlet ... {
        ...
    }
    ```

4.  Make sure all concrete classes you want to make injectable have the default
    constructor. These classes are now CDI beans.

5.  Add a scope to each CDI bean.

    | Bean Scope              | Description      |
    | ----------------------- | ---------------- |
    | [`@ApplicationScoped`](https://docs.oracle.com/javaee/7/api/javax/enterprise/context/ApplicationScoped.html) | Shares the bean's state across all users' interactions with the portlet. |
    | [`@Dependent`](https://docs.oracle.com/javaee/7/api/javax/enterprise/context/Dependent.html) | (default scope) Designates the bean to be for the client bean and share the client bean's lifecycle. |
    | [`@PortletRequestScoped`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/PortletRequestScoped.html) | Associates the bean with the portlet request. |
    | [`@PortletSessionScoped`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/PortletSessionScoped.html) | Places the bean in the portlet session. |
    | [`@RenderStateScoped`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/RenderStateScoped.html) | Stores the bean as part of the portlet's render state. **Important:** The bean must implement the `PortletSerializable` interface. |

6.  Use the [JSR 330](https://jcp.org/en/jsr/detail?id=330)
    [`@Inject`](https://docs.oracle.com/javaee/7/api/javax/inject/Inject.html)
    annotation in a CDI bean to inject another CDI bean into it. For example,
    this code informs @product@'s CDI bean container to inject a `GuestBook` CDI
    bean into this `guestbook` field.

    ```java
    @Inject
    private GuestBook guestbook;
    ```

7.  Inject any [Portlet Predefined Beans](/docs/7-2/reference/-/knowledge_base/r/cdi-portlet-predefined-beans)
    (portlet request scoped or dependent scoped beans) into your
    `@PortletRequestScoped` CDI beans.

    ```java
    @PortletRequestScoped
    public class RequestProcessor ... {

        @Inject
        private PortletRequest portletRequest;
        ...
    }
    ```

8.  Inject any [dependent scoped Portlet Predefined Beans](/docs/7-2/reference/-/knowledge_base/r/cdi-portlet-predefined-beans)
    into your `ApplicationScoped` or `@Dependent` scoped CDI beans. For example,

    ```java
    @ApplicationScoped
    public class MyPortlet ... {

        @Inject
        private PortletConfig portletConfig;
        ...
    }
    ```

9.  Use bean EL names to reference any
    [portlet redefined *named beans*](/docs/7-2/reference/-/knowledge_base/r/cdi-portlet-predefined-beans)
    in your JSP or JSF pages.

10. [Deploy](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)
    your project.

Congratulations! You have created and used CDI beans and Portlet Predefined
Beans in your portlet.

## Related Topics

[CDI Portlet Predefined Beans](/docs/7-2/reference/-/knowledge_base/r/cdi-portlet-predefined-beans)

<!--[Bean Portlet](/docs/7-2/appdev/-/knowledge_base/a/bean-portlet) TODO uncomment when Bean Portlet is available. jhinkey -->

[Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
