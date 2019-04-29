---
header-id: developing-a-portlet-using-portletmvc4spring
---

# Developing a Portlet Using PortletMVC4Spring

[TOC levels=1-4]

PortletMVC4Spring supports developing portlets that use the Spring Framework and
the template languages supported by Spring Web MVC such as JSP, Thymeleaf, Velocity, and more.
The PortletMVC4Spring archetypes include JSP and Thymeleaf templates and
demonstrate the Model View Controller (MVC) pattern. Your first step in
developing a PortletMVC4Spring Portlet is to create a PortletMVC4Spring Portlet
project and deploy your PortletMVC4Spring Portlet to @product@. 

1.  Create a PortletMVC4Spring project. Here are Maven commands for generating 
    projects that use JSP (JSPX) and
    [Thymeleaf](https://www.thymeleaf.org)
    view templates: 

    **JSP Form**

        mvn archetype:generate \
    	-DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
    	-DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.jsp.portlet \
    	-DarchetypeVersion=5.1.0 \ 
    	-DgroupId=com.mycompany \ 
    	-DartifactId=com.mycompany.my.form.jsp.portlet

    **Thymeleaf Form**

        mvn archetype:generate \
    	-DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
    	-DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.thymeleaf.portlet \
    	-DarchetypeVersion=5.1.0 \
    	-DgroupId=com.mycompany \
    	-DartifactId=com.mycompany.my.form.thymeleaf.portlet

    Here's the resulting project structure: 

    -   `[com.mycompany.my.form.jsp.portlet]`/ &rarr; Arbitrary project name
        -   `src/`
            -   `main/`
                -   `java/[my-package-path]/`
                    -   `controller/` &rarr; Sub-package for controller classes 
                        (optional)
                    -   `dto/` &rarr; Sub-package for model (data transfer 
                        object) classes (optional)
                    -    `resources/` &rarr; Resources to include in the class 
                        path 
                        -   `content/` &rarr; Resource bundles 
                        -   `log4j.properties` &rarr; Log4J logging 
                            configuration
                    -   `webapp/` 
                        -   `resources/`
                            -   `css/` &rarr; Style sheets
                            -   `images/` &rarr; Images 
                        -   `WEB-INF/`
                            -   `spring-context/` &rarr; Contexts
                                -   `portlet/` &rarr; Portlet contexts 
                                    -   `portlet1-context.xml` &rarr; Portlet 
                                        context
                                -   `portlet-application-context.xml` &rarr; 
                                    Application context
                            -   `views/` &rarr; View templates 
                            -   `liferay-display.xml` &rarr; Portlet display 
                                configuration 
                            -   `liferay-plugin-package.properties` &rarr; 
                                Packaging descriptor 
                            -   `liferay-portlet.xml` &rarr; Liferay-specific 
                                portlet configuration 
                            -   `portlet.xml` &rarr; Portlet configuration
                            -   `web.xml` &rarr; Web application configuration
            -   `test/java/` &rarr; Test source files

2.  Modify your dependencies as desired. PortletMVC4Spring portlets must 
    include the PortletMVC4Spring framework and PortletMVC4Spring security
    artifacts: 

    **Maven:**

    ```xml
    <dependencies>
        <dependency>
            <groupId>com.liferay.portletmvc4spring</groupId>
            <artifactId>com.liferay.portletmvc4spring.framework</artifactId>
            <version>5.1.0</version>	
        </dependency>
        <dependency>
            <groupId>com.liferay.portletmvc4spring</groupId>
            <artifactId>com.liferay.portletmvc4spring.security</artifactId>
            <version>5.1.0</version>	
        </dependency>
    <dependencies>
    ```

    **Gradle:**

    ```
    dependencies {
        compile group: 'com.liferay.portletmvc4spring', name: 'com.liferay.portletmvc4spring.framework', version: '5.1.0'
        compile group: 'com.liferay.portletmvc4spring', name: 'com.liferay.portletmvc4spring.security', version: '5.1.0'
    }
    ```

3.  Create model classes. (default folder `java/[your-package-path]/dto/`)

4.  Create view templates. (default folder `webapp/WEB-INF/views/`) 

5.  Add CSS and image files to the `css/` and `images/` folders under
    `webapp/resources/`. 

6.  Add localized messages using resource bundles. (default location
    `resources/content/`) 

7.  Add controller classes, following these basic steps. 

    | **Note:**
    | [PortletMVC4Spring Annotation-based Controller Development](/docs/7-2/appdev/-/knowledge_base/appdev/portletmvc4spring-annotation-based-controller-development)
    | provides more controller development details. 

    1.  Add the
        [`@Controller`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/mvc/Controller.html)
        annotation to your controller class. 

    2.  Add an
        `org.springframework.web.bind.annotation.RequestMapping`
        annotation (e.g. `@RequestMapping("VIEW")`) for the
        [portlet mode](/docs/7-2/frameworks/-/knowledge_base/frameworks/portlets)
        that the controller handles requests for. Here are the portlet modes:

        -   `VIEW`
        -   `EDIT`
        -   `HELP`

        The `VIEW` mode is the most common mode for handling requests. 

    3.  Add request handling methods and use annotations to map them to the 
        portlet phase they handle requests for. Here are the
        [portlet phase mapping annotations](/docs/7-2/frameworks/-/knowledge_base/frameworks/portlets):

        -   `@RenderMapping`
        -   `@ActionMapping`
        -   `@EventMapping`
        -   `@ResourceMapping`

        Optionally, use conditional parameters. For example, this annotation and
        condition maps to a render phase request that has the param
        `javax.portlet.action=success`. 
 
        ```java
        @RenderMapping(params = "javax.portlet.action=success")
        ``` 

8.  Modify your application context
    `webapp/WEB-INF/spring-context/portlet-application-context.xml` as desired.

    | **Note:** The generated `webapp/WEB-INF/web.xml` (described later) 
    | specifies the application context location. 

    The application context beans and directives apply to all the web
    application's portlets. The generated application context's
    `springSecurityPortletConfigurer` bean facilitates using Spring Security: 

    ```xml
	<bean id="springSecurityPortletConfigurer" 
        class="com.liferay.portletmvc4spring.security.SpringSecurityPortletConfigurer" />
    ```

9.  Modify your portlet context
    `webapp/WEB-INF/spring-context/portlet/portlet1-context.xml` as desired.

    | **Note:** Each `<portlet>` in the `WEB-INF/portlet.xml` (described later) 
    | can specify its own context. The generated portlet's context is
    | `webapp/WEB-INF/spring-context/portlet/[portlet-name]-context.xml`. 

    For example, this portlet context enables MVC annotations for its components
    whose base package starts with `portlet1`:

    ```xml 
    <?xml version="1.0"?>

    <beans
    	xmlns="http://www.springframework.org/schema/beans"
    	xmlns:context="http://www.springframework.org/schema/context"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xmlns:mvc="http://www.springframework.org/schema/mvc"
    	xsi:schemaLocation="
    		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
    		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    	<context:component-scan base-package="portlet1**"/>
    	<mvc:annotation-driven/>
    </beans>
    ```

10. Modify your `webapp/WEB-INF/web.xml` as desired. It specifies 
    [`ViewRendererServlet`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/ViewRendererServlet.html)
    (required). `ViewRendererServlet` converts portlet requests into servlet
    requests and enables the view to be rendered using the
    entire normal Spring Web MVC infrastructure and the infrastructure's
    renderers for JSP, Thymeleaf, Velocity, and more. Here's the
    `ViewRendererServlet` servlet element:

    ```xml
    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>com.liferay.portletmvc4spring.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>
    ```

11. Modify your `webapp/WEB-INF/portlet.xml` as desired. The portlet descriptor
    `webapp/WEB-INF/portlet.xml` describes the portlet to the portlet container and follows the
    [Portlet 3.0 deployment descriptor schema](https://docs.liferay.com/portlet-api/3.0/portlet-app_3_0.xsd). 

    The
    [DispatcherPortlet](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/DispatcherPortlet.html)
    `<portlet-class>` integrates Spring contexts and sends requests to
    the portlet's controllers and handlers. 

    The generated `portlet.xml`'s init param (shown below) specifies a
    portlet context file `/WEB-INF/spring-context/portlet/portlet1-context.xml`.
    If no `contextConfigLocation` init param is specified, the default portlet
    context location is `/WEB-INF/[portlet-name]-portlet.xml`. 

    ```xml
    <init-param>
        <name>contextConfigLocation</name>
        <value>/WEB-INF/spring-context/portlet/portlet1-context.xml</value>
    </init-param>
    ```

    The `<filter>` and `filter-mapping>` elements specify processors to invoke
    before or after handling requests or responses. The generated `<filter>` and
    `filter-mapping>` elements, for example, specify
    [`SpringSecurityPortletFilter`](https://liferay.github.io/portletmvc4spring/apidocs/index.html),
    which prevents Cross-Site Request Forgery (CSRF). 

    ```xml
	<filter>
		<filter-name>SpringSecurityPortletFilter</filter-name>
		<filter-class>com.liferay.portletmvc4spring.security.SpringSecurityPortletFilter</filter-class>
		<lifecycle>ACTION_PHASE</lifecycle>
		<lifecycle>RENDER_PHASE</lifecycle>
		<lifecycle>RESOURCE_PHASE</lifecycle>
	</filter>
	<filter-mapping>
		<filter-name>SpringSecurityPortletFilter</filter-name>
		<portlet-name>portlet1</portlet-name>
	</filter-mapping>
    ```

12. Modify your `webapp/WEB-INF/liferay-portlet.xml` as desired. It specifies 
    additional information @product@ uses to enhance your portlet: supported
    security roles, portlet icon, CSS and JavaScript locations, and more. The
    [liferay-portlet-app DTD](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html)
    defines the `liferay-portlet.xml` elements. 

13. Modify your `webapp/WEB-INF/liferay-display.xml` as desired. It configures 
    characteristics for displaying your portlet. For example, this
    `liferay-display.xml` specifies the Widget category where the portlet is
    available for users to add to a page. 

    ```xml
    <?xml version="1.0"?>
    <!DOCTYPE display PUBLIC "-//Liferay//DTD Display 7.1.0//EN" "http://www.liferay.com/dtd/liferay-display_7_1_0.dtd">

    <display>
    <category name="category.sample">
        <portlet id="portlet1" />
    </category>
    </display>
    ```

14. Modify your `webapp/WEB-INF/liferay-plugin-package.properties` as desired. 
    It describes the portlet application's packaging and version information
    and specifies any required OSGi metadata. For example, the generated
    `liferay-plugin-package.properties` file uses this OSGi metadata header to
    import required Java packages:

    ```properties
    Import-Package: com.liferay.portal.webserver,\
    com.liferay.portal.kernel.servlet.filters.invoker
    ```

    On deploying the WAR file, the
    [WAB Generator](/develop/tutorials/-/knowledge_base/7-2/using-the-wab-generator)
    adds the specified OSGi metadata to the resulting web application bundle
    (WAB) that's deployed to Liferay's runtime framework.

    The
    [liferay-plugin-package reference document](@platform-ref@/7.2-latest/propertiesdoc/liferay-plugin-package_7_2_0.properties.html)
    describes the `liferay-plugin-package.properties` file. 

15. [Build and deploy your project](/docs/7-2/reference/-/knowledge_base/reference/deploying-a-project). 

@product@ logs the deployment. 

```
2019-04-29 14:26:16.602 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.mycompany.my.form.jsp.portlet-1.0-SNAPSHOT.war
...
2019-04-29 14:27:30.605 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.2.0-b3/osgi/war][BundleStartStopLogger:39] STARTED com.mycompany.my.form.jsp.portlet_1.0.0 [2155]
...
2019-04-29 14:27:46.727 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.2.0-b3/osgi/war][PortletHotDeployListener:288] 1 portlet for com.mycompany.my.form.jsp.portlet is available for use
```

The portlet is now available in the @product@ UI. Find your portlet by selecting
the *Add* icon
(![Add](../../../images/icon-add-app.png))
and navigating to *Widgets* and the category you specified to the Liferay Bundle
Generator (*Sample* is the default category). 

![Figure 1: The archetype's sample portlet prints a greeting (e.g., *Hello, Joe Bloggs*) on submitting the personal information.](../../../images/portletmvc4Spring-developing.png)

Congratulations on creating and deploying a PortletMVC4Spring Portlet. 

## Related Topics 

[Service Builder](/docs/7-2/frameworks/-/knowledge_base/f/service-builder)
