# Reading Unscoped Configuration Values from an MVC Portlet [](id=reading-configuration-values-from-a-mvc-portlet)

If your configuration is scoped to `SYSTEM` or is unscoped (which amounts to
the same thing), you have a couple of options for reading configuration values.
This section describes how to read values from an MVC portlet. There are two
ways to do this:

-   Add a configuration to the request and read it from the view layer
    (commonly a JSP).

-   Read values directly from the portlet class.

This tutorial uses dummy code from a portlet we'll call the Example
Configuration Portlet. The import statements are included in the code snippets
so that you can see the fully qualified class names (FQCNs) of all the classes
that are used.

## Accessing the Configuration Object in the Portlet Class [](id=accessing-the-configuration-object-in-the-portlet-class)

Whether you need the configuration values in the portlet class or the JSPs, the
first step is to get access to the configuration object in the `*Portlet`
class.

1.  Imports first:

        package com.liferay.docs.exampleconfig;

        import java.io.IOException;
        import java.util.Map;

        import javax.portlet.Portlet;
        import javax.portlet.PortletException;
        import javax.portlet.RenderRequest;
        import javax.portlet.RenderResponse;

        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Modified;

        import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

        import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

2.  Portlet classes are Component classes. To mate the configuration with the
    Component, provide the `configurationPid` property with the FQCN of the
    configuration class. 

        @Component(
            configurationPid = "com.liferay.docs.exampleconfig.ExampleConfiguration",
            immediate = true,
            property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.security-role-ref=power-user,user",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.resource-bundle=content.Language"
            },
            service = Portlet.class
        )
        public class ExampleConfigPortlet extends MVCPortlet {

    Note that you can specify more than one configuration PID here, by enclosing the
    values in curly braces (`{}`) and placing commas between each PID.

3.  Write an `activate`  method annotated with `@Activate` and `@Modified`.
    This ensures that the method is invoked when the Component is started, and
    again whenever the configuration is changed.

        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _configuration = ConfigurableUtil.createConfigurable(
            ExampleConfiguration.class, properties);
        }

        private volatile ExampleConfiguration _configuration;

    A volatile field `_configuration` is created by the `createConfigurable` method.
    Now the field can be used to retrieve configuration values or to set the values
    in the request, so they can be retrieved in the application's JSPs.

## Accessing the Configuration from a JSP [](id=accessing-the-configuration-from-a-jsp)

In the case of reading from a JSP, add the configuration object to the request
object so its values can be read from the JSPs that comprise the application's
view layer. 

1. Add the configuration object to the request. Here's what it looks like in a
   simple portlet's `doView` method:

        @Override
        public void doView(RenderRequest renderRequest,
            RenderResponse renderResponse) throws IOException, PortletException {

            renderRequest.setAttribute(
                ExampleConfiguration.class.getName(), _configuration);

            super.doView(renderRequest, renderResponse);
        }

    The main difference between this example and the component class covered in
    the
    [next section](/docs/7-2/frameworks/-/knowledge_base/f/reading-configuration-values-from-a-component) 
    is that this class is a portlet class and it sets the configuration object
    as a request attribute in its `doView()` method. 

2. Read configuration values from a JSP. First add these imports to the top of
   your `view.jsp` file:

        <%@ page import="com.liferay.docs.exampleconfig.ExampleConfiguration" %>
        <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

3. In the JSP, obtain the configuration object from the request object and read
   the desired configuration value from it. Here's a `view.jsp` file that does
   this:

        <%@ include file="/init.jsp" %>

        <p>
            <b>Hello from the Example Configuration portlet!</b>
        </p>

        <%
        ExampleConfiguration configuration = (ExampleConfiguration) GetterUtil.getObject(
            renderRequest.getAttribute(ExampleConfiguration.class.getName()));

        String favoriteColor = configuration.favoriteColor();
        %>

        <p>Favorite color: <span style="color: <%= favoriteColor %>;"><%= favoriteColor %></span></p

The example code here would make the application display a message like this:

    Favorite color: blue

The word <font color="blue">*blue*</font> is written in blue text. Note that *blue* is
displayed by default since you specified it as the default in your
`ExampleConfiguration` interface. If you go to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings* &rarr; *Platform* &rarr; *Third Party*
and click on the *Example configuration* link, you can find the `Favorite color`
setting and change its value. The JSP reads the configuration, and refreshing
the UI reflects this update.

## Accessing the Configuration from the Portlet Class [](id=accessing-the-configuration-from-the-portlet-class)

Now that you've seen a detailed example of accessing the configuration values in
a JSP, there's not much more to cover when accessing the configuration directly
in the `-Portlet` class. Wherever you require the value of a configuration
property, call `_configuration.propertyName` and you have access to the
currently configured value. For example, this code compares the `favoriteColor`
configuration value with a `userFavoriteColor` that's fetched from the request
object: 

    public boolean isFavoriteColorMatched {
    
        String userFavoriteColor = ParamUtil.getString(request, "userFavoriteColor");

        if (_configuration.favoriteColor == userFavoriteColor) {

            SessionMessages.add(request, "congratulateUser");

            return true;
        }

        return false;
    }

It returns true and adds a success message if the two Strings match each other,
but you can do anything that makes sense for your application's controller
logic.

That's all there is to reading configuration values in an MVC Portlet. The next
section covers reading configuration values from an OSGi Component class that
is not part of an MVC portlet.
