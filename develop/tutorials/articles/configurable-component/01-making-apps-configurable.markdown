# Making Your Applications Configurable

This tutorial explains how to make your applications configurable. It starts
with basic configuration and then covers some advanced use cases.

Note that the methods described here are not mandatory. You can make your
applications configurable using any other mechanism that you're already
familiar with. We have found, however, that the method described below provides
the greatest benefit with the least amount of effort.

## Fundamentals

While you don't need to know much to make your applications configurable,
understanding a few key concepts helps you achieve a higher degree of
configurability with little effort.

The first important concept is that the method described here uses *typed*
configuration. This means that the application configuration isn't just a list
of key-value pairs. The values can be types, like `Integer`, a list of
`Strings`, a URL, etc. We believe that using typed configurations prevents many
programmatic errors. Moreover, we believe that typed configurations are easier
to use than untyped configurations. Related to this, the configuration options
should be programmatically explicit, so that developers can use autocomplete in
modern IDEs to find out all of the configuration options of a given application
or one of its components.

A second concept that you should know about is *modularity*. With Liferay 7,
applications are modular and are built as a collection of lightweight
components. A *component* is just a class that has the `@Component` annotation,
often along with a set of properties to provide metadata. The configuration
mechanisms described here leverage the concept of components.

A third important concept is the ability to have different configurations for
the same application at different scopes. Not all applications need this.
However, if your applications needs to support different configurations at
different scopes, the APIs described below handle most of the burden for you.
It's still important, however, for you to understand the term *configuration
scope*. Here are the most common configuration scopes that Liferay applications
can have:

1.  **System:** configuration that is unique for the complete installation of
    the application.
2.  **Portal Instance:** configuration that can vary per portal instance.
3.  **Site:** configuration that can vary per Liferay site.
4.  **Portlet Instance:** applicable for applications that are placed on a page
    (i.e., portlets). Each placement (instance) of the application on the page
    can have a different configuration.

That's it for now. You are ready to get started with some code. If you already
had a portlet or service that was configurable using the traditional mechanisms
of Liferay 6.2 and before, you might also want to read the
[How to change your portlets and services to use the new Configuration API (not yet written)]() tutorial.

## Making Your Application Configurable

Now you'll see the minimum amount of code you need to write to make your
application configurable the Liferay 7 way. First, you'll learn how to create a
configuration at the system scope.

The first step is to create a Java interface to represent the configuration and
its default values. The reason for using a Java interface is that it allows for
an advanced type system for each of the configuration options. Here is an
example of such an interface:

    @Meta.OCD(id = "com.foo.bar.MyAppConfiguration")
    public interface MyAppConfiguration {

        @Meta.AD(
            deflt = "blue",
            required = false
        )
        public String favoriteColor();

        @Meta.AD(
           deflt = "red|green|blue",
           required = false
        )
        public String[] validLanguages();

        @Meta.AD(required = false)
        public int itemsPerPage();

    }

As you can see, you are using two Java annotations to provide some metadata about
the configuration. Here is what they do:

1.  **Meta.OCD:** Registers this class as a configuration with a specific id.
    You can choose any string you want, but make sure it's unique. A common
    pattern is to use the fully qualified class name.
2.  **Meta.AD:** Specifies the default value of a configuration field as well
    as whether it's required or not. Note that if you set a field as required
    and don't specify a default value, the system administrator must specify a
    value in order for your application to work properly. Use the `deflt`
    property to specify a default value.

The fully-qualified class name of the `Meta` class referred to above is
`aQute.bnd.annotation.metatype.Meta`. For more information about this and
related annotations, please refer to this bnd documentation:
[http://bnd.bndtools.org/chapters/210-metatype.html](http://bnd.bndtools.org/chapters/210-metatype.html).
Add the following line to your project's `bnd.bnd` file:

    -metatype: *

This line lets bnd use your configuration interface to generate an XML
configuration file. With this information, Liferay already knows a lot about
your application's configuration options. In fact, it knows enough to generate
a user interface automatically. Cool, isn't it?

![Figure x: Navigate to the Control Panel and then click on *System* &rarr; *System Settings*. Then click on *Other*, find the *Example configuration* link, and click on it.](../../images/example-configuration-system-settings.png)

At this point you might be wondering, "While this is pretty cool, how can I
read the configuration from my application code?"" It's actually quite easy.
Here's a simple example:

    @Component(configurationPid = "com.liferay.docs.exampleconfig.ExampleConfiguration")
    public class MyAppManager {

        public String getFavoriteColor(Map colors) {
           return colors.get(_configuration.favoriteColor());
        }

        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _configuration = Configurable.createConfigurable(
            ExampleConfiguration.class, properties);
        }

        private volatile ExampleConfiguration _configuration;

    }

Here are the most relevant aspects of this example one by one:

1.  This class is a component, specified with the `@Component` annotation.
2.  This component uses the configuration with the ID
    `com.liferay.docs.exampleconfig.ExampleConfiguration`. As a result, this
    method is invoked when the application starts (due to the `@Activate`
    annotation) and whenever the configuration is modified (due to the
    `@Modified` annotation).
3.  The `activate()` method uses the method `Configurable.createConfigurable()`
    to convert a map of properties with the configuration to our typed class,
    which is easier to handle.
4.  The configuration is stored in a `volatile` field. Don't forget to make it
    `volatile` or you'll run into weird problems.

That's it. As you can see with very few lines of code, you have a configurable
application that dynamically changes its configuration, has an auto-generated
UI, and uses a simple API to access the configuration.

## Accessing Your Configuration in a JSP Portlet Application

In Liferay, it's very common to read a configuration from a portlet class. If
the portlet is a JSP portlet, the configuration object can be added to the
request object so that configurations can be read from the JSPs that comprise
the application's view layer. In this section, you'll see an example of reading
a configuration from a portlet class, adding it to the request, and reading
from the view layer. In this example, we provide the import statements so that
you can see the fully qualified class names of all the classes that are used.

+$$$

**Note:** There's shortcut method for obtaining a portlet instance
configuration. The method described in this section takes a straightforward
approach that does not use this shortcut. See the Accessing the Portlet
Instance Configuration Through the PortletDisplay section below to learn about
the shorter method.

$$$

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

    import aQute.bnd.annotation.metatype.Configurable;

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

        @Override
        public void doView(RenderRequest renderRequest,
            RenderResponse renderResponse) throws IOException, PortletException {

            renderRequest.setAttribute(
                ExampleConfiguration.class.getName(), _configuration);

            super.doView(renderRequest, renderResponse);
        }


        public String getFavoriteColor(Map colors) {
            return (String) colors.get(_configuration.favoriteColor());
        }

        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _configuration = Configurable.createConfigurable(
            ExampleConfiguration.class, properties);
        }

        private volatile ExampleConfiguration _configuration;

    }

The main difference between this example and the first one is that this class
is a portlet class and it sets the configuration object as a request attribute
in its `doView()` method. To read configuration values from a JSP, first add
these imports to your `init.jsp` file:

    <%@ page import="com.liferay.docs.exampleconfig.ExampleConfiguration" %>
    <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

In a JSP portlet application, your full `init.jsp` file should at least have
contents like this:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <%@ page import="com.liferay.docs.exampleconfig.ExampleConfiguration" %>
    <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

    <portlet:defineObjects />
    <liferay-theme:defineObjects />

It's a Liferay convention that all JSP imports in your application should go in
an `init.jsp` file and all of your application's othe JSPs should import
`init.jsp`. This convention ensures that you only have to manage JSP
dependencies in a single file. Next, obtain the configuration object from
request object and read the desired configuration value from it. Here's an
example `view.jsp` file that does this:

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

![Figure x: Here, the Example Configuration portlet's `view.jsp` is rendered. This JSP reads the value of the `favoriteColor` configuration and displays it.](../../images/example-configuration-portlet.png)

<!-- Update the link below when the example has been completed and the name
finalized. -->
Please see []() for a complete example. If you run this example, you'll see the
application display a message like this:

    Favorite color: blue

The word 'blue' should be written in blue text. Note that 'blue' is displayed
by default since you specified it as the default in your `ExampleConfiguration`
interface. If you go to *System* &rarr; *System Settings* &rarr; *Other* and
click on the *Example configuration* link, you can find the `Favorite color`
setting and change its value. Your application's JSP will reflect this update
when you refresh the page.

## Categorizing the Configuration

Since it's now very easy to make any application or service configurable, there
are already lots of configuration options in Liferay by default. If you've
deployed custom applications and services to your portal, there will be even
more. To make it easier for portal administrators to find the right
configuration options, Liferay provides a mechanism for developers to specify a
category in which the configuration will be shown in the auto-generated System
Settings UI in the Control Panel.

Here's how the System Settings UI looks:

![Figure x: Navigate to the Control Panel, click on *System* and then *System Settings*. You'll find five categories of configurations, including Other. Click on any configuration to access a form through which the configuration values can be updated.](../../images/system-settings-ui.png)

By default, the following configuration categories are defined:

1.  Web Experience Management
2.  Collaboration
3.  Productivity
4.  Platform
5.  Other

You can use any other category and it will be injected in alphabetical order
after Platform. Other will always be shown last. In order to specify a
category, you must use the `@ExtendedObjectClassDefinition` annotation as in
the following example:

    @ExtendedObjectClassDefinition(category = "platform")
    @Meta.OCD(
        factory = true,
        id = "com.liferay.portal.ldap.configuration.SystemLDAPConfiguration",
        localization = "content/Language"
    )

The fully qualified class name of the `@ExtendedObjectClassDefinition` class is
`com.liferay.portal.metatype.annotations.ExtendedObjectClassDefinition`.

## Supporting Different Configurations per Scope

When an application is deployed to Liferay, it's common to need different
configurations depending on the scope. That means having different
configurations for a given application per portal instance or per site. It's
also very common to need different configurations for each portlet instance.
Liferay 7 provides an easy way to achieve this with little effort through a new
framework called the Module Configuration API.

In order to use the Module Configuration API, you need to

1.  Declare the configuration interface by creating a
    `ConfigurationBeanDeclaration` class:

        @Component
        public class RSSPortletInstanceConfigurationBeanDeclaration
            implements ConfigurationBeanDeclaration {

            @Override
            public Class getConfigurationBeanClass() {
                return RSSPortletInstanceConfiguration.class;
            }

        }

2.  Obtain the configuration using one of the these two methods:

        <!--
        Maybe we should mention that we have two configuration APIs, the old
        one and the new one. In this case, we are using import
        com.liferay.portal.kernel.module.configuration.ConfigurationFactory
        -Juergen
        -->

        RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
            _configurationFactory.getConfiguration(
                RSSPortletInstanceConfiguration.class,
                new PortletInstanceSettingsLocator(
                    themeDisplay.getLayout(), portletDisplay.getId()));

        RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
            _settingsFactory.getConfiguration(
                RSSPortletInstanceConfiguration.class,
                themeDisplay.getLayout(), portletDisplay.getId());

    The first method above is preferred. This method uses
    `com.liferay.portal.kernel.module.configuration.ConfigurationFactory`. The
    other method is older.

3.  In order to get the `ConfigurationFactory`, your class must have a setter
    method with the `@Reference` annotation. Use a setter method like this for
    components:

        @Reference
        protected void setConfigurationFactory(ConfigurationFactory configurationFactory) {
            _configurationFactory = _configurationFactory;
        }

    Use a setter method like this for OSGi services:

        @ServiceReference(type = ConfigurationFactory.class)
        protected ConfigurationFactory configurationFactory;

    Remember not to confuse an OSGi service with Liferay service. A Liferay
    service is also known as a Liferay API.

### Accessing the Portlet Instance Configuration Through the PortletDisplay

Often it's necessary to access a portlet's settings from its JSPs or from Java
classes that are not OSGi components. To make it easier to read the settings in
these cases, a new method has been added to `PortletDisplay` (available as a
request object). Here is an example of how to use it:

    RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
        portletDisplay.getPortletInstanceConfiguration(
            RSSPortletInstanceConfiguration.class);

As you can see, it knows how to find the values and returns a typed bean
containing them just by passing the configuration class.

### Reusing the Same Configuration Class for Different Purposes

The configuration service also allows a single class to be reused for different
configuration needs. In order to support this, you must create a class that
maps the different scenarios to the class that will be used. Here is an
example:

    @Component
    public class RSSPortletInstanceConfigurationPidMapping implements ConfigurationPidMapping {

        @Override
        public Class<?> getConfigurationBeanClass() {
            return RSSPortletInstanceConfiguration.class;
        }

        @Override
        public String getConfigurationPid() {
            return RSSPortletKeys.RSS;
        }

    }

The class above allows the `RSSPortletInstanceConfiguration` class to be used
in different scenarios.

## Summary

Excellent! In this tutorial, you've learned how to make your applications
configurable. Creating a simple configuration interface allows Liferay to
auto-generate a configuration UI that's accessible via System Settings in the
Control Panel. You've also learned how to categorize your configurations within
System Settings, how read configuration settings in your application at
runtime, how to support different configurations at different scopes, and how
to reuse the same configuration class for different scenarios.
