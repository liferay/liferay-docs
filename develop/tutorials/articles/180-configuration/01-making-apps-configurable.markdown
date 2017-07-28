# Making Your Applications Configurable [](id=making-your-applications-configurable)

This tutorial explains how to make your applications configurable. It starts
with basic configuration and then covers some advanced use cases.

Note that the methods described here are not mandatory. You can make your
applications configurable using any other mechanism that you're already
familiar with. We have found, however, that the method described below provides
the greatest benefit with the least amount of effort.

## Fundamentals [](id=fundamentals)

While you don't need to know much to make your applications configurable,
understanding a few key concepts helps you achieve a higher degree of
configurability with little effort.

- Typed Configuration: The method described here uses *typed*
configuration. This means that the application configuration isn't just a list
of key-value pairs. The values can have types, like `Integer`, a list of
`Strings`, a URL, etc. It's even possible to use your own custom types, although
that's beyond the scope of this tutorial. Typed configurations are easier to use
than untyped configurations, and they prevent many programmatic errors. Related
to this, the configuration options should be programmatically explicit, so that
developers can use autocomplete in modern IDEs to find out all of the
configuration options of a given application or one of its components.

- Modularity: In @product@, applications are *modular* and built as a collection
of lightweight components. A *component* is just a class that has the
`@Component` annotation, often along with a set of properties to provide
metadata. The configuration mechanisms described here leverage the concept of
components.

- Configuration Scope: If your application must support different
configurations at different scopes, the APIs described below handle most of the
burden for you. It's still important, however, for you to understand the term
*configuration scope*. Here are the most common configuration scopes that
Liferay applications can have:

   1.  **System:** configuration that is unique for the complete installation of
        the application.

   2.  **Virtual Instance:** configuration that can vary per virtual instance.
 
   3.  **Site:** configuration that can vary per Liferay site.

   4.  **Portlet Instance:** applicable for applications that are placed on a page
        (i.e., portlets). Each placement (instance) of the application on the page
        can have a different configuration.

Enough with the conceptual stuff. You're ready to get started with some code. If
you already had a portlet or service that was configurable using the traditional
mechanisms of Liferay Portal 6.2 and before, refer to the
[Transitioning from Portlet Preferences to the Configuration API](/develop/tutorials/-/knowledge_base/7-0/transitioning-from-portlet-preferences-to-the-configuration-api) tutorial.

## Making Your Application Configurable [](id=making-your-application-configurable)

There's a minimal amount of code you need to write to make your application
configurable the @product@ way. First, you'll learn how to create a
configuration at the system scope.

First create a Java interface to represent the configuration and its default
values. Using a Java interface allows for an advanced type system for each
configuration option. Here is an example of such an interface:

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

1.  **Meta.OCD** Registers this class as a configuration with a specific id.
    You can choose any string you want, but make sure it's unique. A common
    pattern is to use the fully qualified class name.
2.  **Meta.AD** Specifies the default value of a configuration field as well
    as whether it's required or not. Note that if you set a field as required
    and don't specify a default value, the system administrator must specify a
    value in order for your application to work properly. Use the `deflt`
    property to specify a default value.

The fully-qualified class name of the `Meta` class referred to above is
`aQute.bnd.annotation.metatype.Meta`. For more information about this class and
the `Meta.OCD` and `Meta.AD` annotations, please refer to this bnd
documentation:
[http://bnd.bndtools.org/chapters/210-metatype.html](http://bnd.bndtools.org/chapters/210-metatype.html).
In order to use the `Meta.OCD` and `Meta.AD` annotations in your modules, you
must 
[specify a dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) 
on the bnd library. We recommend using bnd version 3. Here's an example of how 
to include this dependency in a Gradle project: 

    dependencies {
        compile group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.1.0"
    }

+$$$

**Note:** The annotations `@Meta.OCD` and `@Meta.AD` are part of the bnd library
but have also been included as part of the OSGi standard version R6 with the
names `@ObjectClassDefinition` and `@AttributeDefinition`. However, Liferay
still uses the bnd annotations since the standard annotations are not available
at runtime, which is necessary for some of the Liferay specific features
described below. For the basic usage (the one described in this section) the
standard annotations can be used safely.

$$$

Add the following line to your project's `bnd.bnd` file:

    -metatype: *

This line lets bnd use your configuration interface to generate an XML
configuration file. With this information, Liferay already knows a lot about
your application's configuration options. In fact, it knows enough to generate
a user interface automatically. Cool, isn't it?

![Figure 1: Navigate to the Control Panel and then click on *Configuration* &rarr; *System Settings*. Then click on *Other*, find the *Example configuration* link, and click on it.](../../images/example-configuration-system-settings.png)

Even if you agree that this is pretty cool, you might be wondering how to read
the configuration from your application's code. It's actually quite easy.
Here's a simple example:

    @Component(configurationPid = "com.liferay.docs.exampleconfig.ExampleConfiguration")
    public class MyAppManager {

        public String getFavoriteColor(Map colors) {
           return colors.get(_configuration.favoriteColor());
        }

        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _configuration = ConfigurableUtil.createConfigurable(
            ExampleConfiguration.class, properties);
        }

        private volatile ExampleConfiguration _configuration;

    }

Here are the most relevant aspects of this example:

1.  This class is a component, specified with the `@Component` annotation.
2.  This component uses the configuration with the ID
    `com.liferay.docs.exampleconfig.ExampleConfiguration`. As a result, this
    method is invoked when the application starts (due to the `@Activate`
    annotation) and whenever the configuration is modified (due to the
    `@Modified` annotation).
3.  The `activate()` method uses the method `ConfigurableUtil.createConfigurable()`
    to convert a map of properties with the configuration to our typed class,
    which is easier to handle.
4.  The configuration is stored in a `volatile` field. Don't forget to make it
    `volatile` or you'll run into weird problems.

+$$$

**Note:** The bnd library also provides a class called
`aQute.bnd.annotation.metatype.Configurable` with a `createConfigurable()`
method. You can use that instead of Liferay's
`com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil` without
any problems. Liferay's developers created the `ConfigurableUtil` class to
improve the performance of bnd's implementation, and it's used in internal code.
Feel free to use whichever method you prefer. 

$$$

That's it. With very few lines of code, you have a configurable application that
dynamically changes its configuration, has an auto-generated UI, and uses a
simple API to access the configuration.

## Accessing Your Configuration in a JSP Portlet Application [](id=accessing-your-configuration-in-a-jsp-portlet-application)

In @product@ it's very common to read a configuration from a portlet class. If
the portlet is a JSP portlet, the configuration object can be added to the
request object so that configurations can be read from the JSPs that comprise
the application's view layer. In this section, you'll see an example of reading
a configuration from a portlet class, adding it to the request, and reading from
the view layer. The import statements are included in the code snippets so that
you can see the fully qualified class names of all the classes that are used.

+$$$

**Note:** There's shortcut method for obtaining a portlet instance
configuration. The method described in this section takes a straightforward
approach that does not use this shortcut. See the [Accessing the Portlet
Instance Configuration Through the PortletDisplay](#accessing-the-portlet-instance-configuration-through-the-portletdisplay) section below to learn about
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

    import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

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
            _configuration = ConfigurableUtil.createConfigurable(
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
an `init.jsp` file. All of your application's other JSPs import `init.jsp`. This
convention ensures that you only have to manage JSP dependencies in a single
file. 

Next, obtain the configuration object from request object and read the desired
configuration value from it. Here's an example `view.jsp` file that does this:

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

![Figure 2: Here, the Example Configuration portlet's `view.jsp` is rendered. This JSP reads the value of the `favoriteColor` configuration and displays it.](../../images/example-configuration-portlet.png)

<!-- Update the link below when the example has been completed and the name
finalized. -->
The example code here would make the application display a message like this:

    Favorite color: blue

The word 'blue' should be written in blue text. Note that 'blue' is displayed
by default since you specified it as the default in your `ExampleConfiguration`
interface. If you go to *System* &rarr; *System Settings* &rarr; *Other* and
click on the *Example configuration* link, you can find the `Favorite color`
setting and change its value. Your application's JSP will reflect this update
when you refresh the page.

## Categorizing the Configuration [](id=categorizing-the-configuration)

Because it's easy to make any application or service configurable, there
are already lots of configuration options in @product@ by default. If you've
deployed custom applications and services to your portal, there will be even
more. To make it easier for portal administrators to find the right
configuration options, Liferay provides a mechanism for developers to specify a
category in which the configuration will be shown in the auto-generated System
Settings UI in the Control Panel.

Here's how the System Settings UI looks:

![Figure 3: Navigate to the Control Panel, click on *Configuration* and then
*System Settings*. You'll find five categories of configurations, including
Other. Click on any configuration to access a form through which the
configuration values can be updated.](../../images/system-settings-ui.png)

By default, the following configuration categories are defined:

1.  Web Experience
2.  Collaboration
3.  Forms and Workflow
4.  Foundation
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
`com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition`.

Note: Currently, the infrastructure used by System Settings relies on the
`configurationPid` being the same as the class name of the interface. If they
don't match, it will not be able to provide any information provided through
`ExtendedObjectClassConfiguration`.

The `@ExtendedObjectClassDefinition` annotation is distributed through the
`com.liferay.portal.configuration.metatype` module, which you can 
[configure as a dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies). 

## Supporting Different Configurations per Virtual Instance, Site, or Portlet Instance [](id=supporting-different-configurations-per-scope)

When an application is deployed to Liferay, it's common to need different
configurations depending on the scope. That means having different
configurations for a given application per virtual instance (a.k.a. Company),
site (a.k.a. Group), or portlet instance. @product@ provides an easy way to
achieve this with little effort through a new framework called the Configuration
API that is based on the standard OSGi Configuration Admin API shown in the
previous section.

### Using the Configuration Provider [](id=using-the-configuration-provider)

When using the Configuration Provider, instead of receiving the configuration
directly, the class that wants to access it will need to receive a
`ConfigurationProvider` from which to obtain the configuration. Additionally,
you need to "register" your class.

Note: `ConfigurationProvider` is part of Liferay's kernel API so you don't need
to add a new dependency to use it. However, its implementation is distributed
as a module called `portal-configuration-module-configuration`, so you will need
to make sure it is installed in order to use it.

Before using the `ConfigurationProvider`, register the configuration class by
writing a class that implements `ConfigurationBeanDeclaration`. This class only
has one method that returns the class of the interface you created in the
previous section. By doing this, the system is able to keep track of any
configuration changes as they happen. This makes requests for the configuration
very fast.

Declare the configuration interface by creating a `ConfigurationBeanDeclaration`
class:

    @Component
    public class RSSPortletInstanceConfigurationBeanDeclaration
        implements ConfigurationBeanDeclaration {

        @Override
        public Class getConfigurationBeanClass() {
            return RSSPortletInstanceConfiguration.class;
        }

    }

Once you have created your `ConfigurationBeanDeclaration`, you can use a
`ConfigurationProvider`. Here's how you can obtain a reference to it:

- For components:

        @Reference
        protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
            _configurationProvider = configurationProvider;
        }

- For Service Builder services:

        @ServiceReference(type = ConfigurationProvider.class)
        protected ConfigurationProvider configurationProvider;

- For Spring beans: It is possible to use the same mechanism as for Service
  Builder services (`@ServiceReference`). Check the documentation on [how to
  integrate Spring beans with OSGi services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services#referencing-osgi-services) for more details.

Later, the configuration can be obtained using one of the following methods of
the provider:

- `getCompanyConfiguration()`: Used when you want to support
  different configurations per virtual instance. In this case, the
  configuration is usually entered by an admin through Control Panel &rarr;
  Configuration &rarr; Instance Settings. Since this UI is not automatically
  generated (yet) you will need to extend the UI with your own form.

- `getGroupConfiguration()`: Used when you want to support different
  configurations per site (or, if desired, per page scope). Usually this
  configuration is specified by an admin through the Configuration menu option
  in an app accessing through the site administration menu. That UI is
  developed as a portlet configuration view.

- `getPortletInstanceConfiguration()`: Used to obtain the
  configuration for an specific portlet instance. Most often you should not be
  using this directly and use the convenience method in `PortletDisplay` instead
  as shown below.

- `getSystemConfiguration`: Used to obtain the configuration for the system
    scope. These settings are specified by an admin via the System Settings
    application or with an OSGi configuration file.

Here are a couple real world examples from Liferay’s source code:

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);

    MentionsGroupServiceConfiguration configuration =
      _configurationProvider.getCompanyConfiguration(
         MentionsGroupServiceConfiguration.class, entry.getCompanyId());

Next, you'll learn how to access a portlet's configuration from outside of an
OSGi component.

### Accessing the Portlet Instance Configuration Through the PortletDisplay [](id=accessing-the-portlet-instance-configuration-through-the-portletdisplay)

Often it's necessary to access a portlet's settings from its JSPs or from Java
classes that are not OSGi components. To make it easier to read the settings in
these cases, a new method has been added to `PortletDisplay` (available as a
request object). Here is an example of how to use it:

    RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
        portletDisplay.getPortletInstanceConfiguration(
            RSSPortletInstanceConfiguration.class);

As you can see, it knows how to find the values and returns a typed bean
containing them just by passing the configuration class.

## Specifying the Scope of the Configuration [](id=specifying-the-scope-of-the-configuration)

The `ExtendedObjectClassDefinition` annotation allows you to specify the scope
of the configuration. This should match how the configuration object is
retrieved through the provider (your choice). The valid options are:

- `Scope.GROUP`: for site scope
- `Scope.COMPANY`: For virtual instance scope
- `Scope.SYSTEM`: for system scope

Here is an example:

    @ExtendedObjectClassDefinition(
        category = "productivity", scope = ExtendedObjectClassDefinition.Scope.GROUP
    )
    @Meta.OCD(
        id = "com.liferay.dynamic.data.lists.form.web.configuration.DDLFormWebConfiguration",
        localization = "content/Language", name = "%ddl.form.web.configuration.name"
    )
    public interface DDLFormWebConfiguration {
    ...
    }

In @product@ version 7.0, the scope property isn't used for anything other than
making it appear in System Settings so that an administrator can change its
value. In future releases, may have more purposes.

## Summary [](id=summary)

In this tutorial, you've learned how to make your applications configurable.
Creating a simple configuration interface allows Liferay to auto-generate a
configuration UI that's accessible via System Settings in the Control Panel.
You've also learned how to categorize your configurations within System
Settings, how read configuration settings in your application at runtime, how to
support different configurations at different scopes, and how to reuse the same
configuration class for different scenarios.
