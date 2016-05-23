# Spring MVC

If you're developing applications for Liferay, it makes sense to use Liferay's
MCVC framework and Service Builder. If you're already a wizard with Spring
MVC, you might instead decide to use that framework for your application's
controller. You can still use Service Builder for your Model layer.

So, what does it take to implement a Spring MVC application in Liferay? Here's
the easiest way:

-  Create a project from the Liferay 6.2 Maven archetype:
http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liferay-portlet-spring-mvc-archetype%22

-  Update the descriptors `liferay-portlet.xml`,
   `liferay-display.xml`, and `liferay-plugin-package.properties` to Liferay 7.

-  Deploy the WAR. How does WAR deployment work in Liferay 7? Check out the
   tutorial on [packaging JSF applications](develop/tutorials/-/knowledge_base/7-0/packaging-a-jsf-application)
for an explanation.

Aside from the creation of a Liferay Spring MVC project, you'll want to know how
a Spring MVC portlet differs from a Liferay MVC portlet.

Start with the `<portlet-class>` tag in `portlet.xml`. It needs to specify 

    org.springframework.web.portlet.DispatcherPortlet

In your application context file, specify the beans you want to use, such as
`viewResolver`.

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

The front controller needs to know where the application context file is, so
specify it as an initialization parameter in `portlet.xml` (update the path as
needed):

    <init-param>
        <name>contextConfigLocation</name>
        <value>/WEB-INF/spring/portlet-context.xml</value>
    </init-param>

Your application needs the ability to convert `PortletRequest`s to
`ServletRequest`s and back again. Add this to `web.xml`:

    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>

To configure the Spring view resolver, add a bean in your application context
file:

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

Now the front controller, `DispatcherPortlet`, can get a request from the view
layer, but there are no actual controller classes to delegate the request
handling to.

When you create your controller, it's convenient to separate it into classes
that will handle a particular portlet mode.

You'll use Spring's annotations to configure the controller and tell
`DispatcherPortlet` which mode the controller supports. 

A simple controller supporting view mode might look like this:

    @Controller("myAppController")
    @RequestMapping("VIEW")
    public class MyAppController {

        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {

            return "defaultView";
        }
    }

The `return defaultView` statement should be understood in terms of the view
resolver bean in the application context file, which gives the String
`defaultView` a prefix of `WEB-INF/views/`, and a suffix of `.jsp`. That maps to
the path `WEB-INF/views/defaultView.jsp`, where you would place your default
view for the application. 

With Spring MVC, you can only support one portlet phase in each controller. 

An edit mode controller might contain render methods and action methods.

    @Controller("myAppEditController")
    @RequestMapping("EDIT")
    public class MyAppEditController {

        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {

            return "thisView";
        }

        @ActionMapping(params="action=doSomething")
        public void doSomething(Actionrequest request, ActionResponse response){
        
            // Do something here

        }
    }

You need to define any controller classes in your application context file by
adding a `<bean>` tag for each one:

    <bean class="com.liferay.docs.springmvc.portlet.MyAppController" />
    <bean class="com.liferay.docs.springmvc.portlet.MyAppEditController" />

## Service Builder and Spring MVC

Create a service builder project using Blade CLI.

    springmvc-service-builder
        ├── build.gradle
        ├── springmvc-service-builder-api
        │   ├── bnd.bnd
        │   └── build.gradle
        └── springmvc-service-builder-service
            ├── bnd.bnd
            ├── build.gradle
            └── service.xml

Design your model entity and write your service layer as normal (see the
tutorials on Service Builder
[here](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder). After
that, you just need to add your service's API JAR as a dependency in your Spring
MVC project.

## Creating a Spring MVC Liferay project in Liferay IDE

The easiest route to developing a Spring MVC application is by using Liferay
IDE. This is accomplished very simply:

-  Configuring Eclipse Maven plugins and Liferay IDE
-  Creating a Liferay Plugin Project
-  Choosing Maven as the Build Type
-  Choosing Spring MVC as the portlet framework

Liferay IDE creates a project based on the [Spring MVC archetype project](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liferay-portlet-spring-mvc-archetype%22)
in the Maven Central repository.

From there, you're free to develop your Liferay Maven Spring MVC portlet. You'll
have the necessary descriptor files, a default controller for *View* mode, a
default JSP, a `pom.xml`, and more.

+$$$

**Note:** The Liferay Maven Spring MVC archetype is for Liferay 6.2. To update
it for Liferay 7, update the descriptor files, and you'll be on your way.

$$$


Stuff from [Packaging a JSF
Application](develop/tutorials/-/knowledge_base/7-0/packaging-a-jsf-application)

Developers creating portlets for Liferay 7.0 can usually deploy their portlet as
Java EE style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. Spring MVC portlet developers don't have that
flexibility. Spring MVC portlets must be packaged as WAR artifacts because the
Spring MVC framework expects a WAR layout and requires Java EE resources such as
the `WEB-INF/web.xml` descriptor. In this tutorial you’ll learn two ways to
package a Spring MVC portlet so it can be treated as an OSGi module at runtime.
The benefits and drawbacks of each approach are listed.


Because Liferay supports the OSGi WAB standard for deployment, you can deploy
your WAR and it will run as expected in the OSGi runtime. So what's a WAB?
Basically, it's a WAR file with a `META-INF/MANIFEST.MF` file that includes the
`Bundle-SymbolicName` OSGi directive. 

There are a couple of ways to make your source code into a WAB:

-  
