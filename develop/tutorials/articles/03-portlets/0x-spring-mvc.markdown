# Spring MVC

If you're developing applications for Liferay, it makes sense to use Liferay's
MCVC framework and Service Builder. If you're already a wizard with Spring
MVC, you might instead decide to use that framework for your application's controller.

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
`ServletRequest`s and back again. To do do ad this to `web.xml`:

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
