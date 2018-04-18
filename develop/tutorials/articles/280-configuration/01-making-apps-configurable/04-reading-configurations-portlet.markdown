# Reading Configuration Values from a MVC Portlet [](id=reading-configuration-values-from-a-mvc-portlet)

It's very common to read configuration values from a portlet class or its JSPs.
This tutorial shows how to add a configuration to the request and read it from
the view layer, and how to read it directly in the portlet class. This tutorial
uses dummy code from a portlet we'll call the Example Configuration Portlet. The
import statements are included in the code snippets so that you can see the
fully qualified class names (FQCNs) of all the classes that are used.

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

3.  Write an `activate`  method annotated with `@Activate` and `@Modified`. See
    the 
    [Making Applications Configurable](/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable)
    tutorial if you're unsure why these annotations are necessary:

        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _configuration = ConfigurableUtil.createConfigurable(
            "EXAMPLE_CONFIGRUATION", properties);
        }

        private volatile ExampleConfiguration _configuration;

    A volatile field `_configuration` is created by the `createConfigurable` method.
    Now the field can be used to retrieve configuration values or to set the values
    in the request, so they can be retrieved in the application's JSPs.

## Accessing the Configuration from a JSP [](id=accessing-the-configuration-from-a-jsp)

In the case of reading from a JSP, add the configuration object to the request
object so its values can be read from the JSPs that comprise the application's
view layer. 

+$$$

**Note:** There's a shortcut method for obtaining a portlet instance
configuration. The method described in this section takes a straightforward
approach that does not use this shortcut. See the 
[Accessing the Portlet Instance Configuration Through the `PortletDisplay`](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider#accessing-the-portlet-instance-configuration-through-the-portletdisplay) article to learn
about the shorter method.

$$$

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
    [previous tutorial](reading-configuration-values-from-a-component) 
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

<!--[Figure 2: Here, the Example Configuration portlet's `view.jsp` is rendered. This JSP reads the value of the `favoriteColor` configuration and displays it.](../../images/example-configuration-portlet.png)-->

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
tutorial covers categorizing the configuration and accessing it through a
Configuration Provider.

