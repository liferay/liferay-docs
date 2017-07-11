# Implementing Configuration Actions [](id=implementing-configuration-actions)

When developing a Liferay application, it's important to think about the
different configuration options that your application should support. It's also
important to think about how users should be able to access your application's
configuration interface. Liferay supports a flexible mechanism for configuring
applications. You can read about it in the
[Making Your Applications Configurable](/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable) tutorial. In this
tutorial, you'll learn to implement a configuration action for your
application. The configuration action is invoked when a user clicks on the gear
icon of a Liferay application and selects *Configuration*.

![When a user clicks on a Liferay application's gear icon and selects *Configuration*, the application's configuration action is invoked.](../../images/gear-configuration.png)

Liferay applications support a default configuration action. If you click on the
gear icon of a Liferay application that has not been customized and then select
*Configuration*, you'll find two standard tabs: Permissions and Sharing. These
tabs provide standard options for configuring who can access your application
and how you can make your application more widely available. If you follow the
instructions in this tutorial, you'll learn how to create a Setup tab that
allows custom configuration fields to be manipulated. To implement a
configuration action, follow these steps:

1. Create an interface to represent your configuration
2. Implement your application class and add a reference to your configuration in
   your application class
3. Implement your configuration action class and add a reference to your
   configuration in your configuration action class
4. Implement the user interface for configuring your application

Let's get started.

## Creating a Configuration Interface [](id=creating-a-configuration-interface)

As explained in the
[Making Your Applications Configurable](/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable)
tutorial, if you want to make your application configurable, you should create a
Java interface to represent the configuration. Decorate your interface with the
`@Meta.OCD` annotation and specify a unique ID using the annotation's `id`
attribute. A common pattern is to use the fully qualified class name of the
interface for the ID since fully qualified class names are unique. Create public
methods to represent configuration fields and decorate the methods with the
`@Meta.AD` annotation. The return type of the method specifies the type of the
field. To specify a field's default value, use the annotation's `deflt`
attribute. To specify that a field is optional, set `required=false`. For more
information about the `Meta.OCD` and `Meta.AD` annotations, please see the
[bnd](http://www.aqute.biz/Bnd/MetaType) documentation. Here's a simple example:

    package com.liferay.docs.exampleconfig.configuration;

    import aQute.bnd.annotation.metatype.Meta;

    @Meta.OCD(id = "com.liferay.docs.exampleconfig.configuration.ExampleConfiguration")
    public interface ExampleConfiguration {

        @Meta.AD(required = false)
        public String favoriteColor();

    }

Add the following line to your project's `bnd.bnd` file:

    -metatype: *

This line lets bnd use your configuration interface to generate an XML
configuration file. This lets Liferay auto-generate a UI for your configuration
in the System Settings area of the Control Panel. However, it's sometimes
preferable for users to be able to access your configuration directly from the
portlet without having to go to the Control Panel. In this tutorial, you'll
learn how to facilitate this.

This sample configuration contains a single string field called `favoriteColor`.

## Referencing Your Configuration From Your Application Class [](id=referencing-your-configuration-from-your-application-class)

As was also explained in the
[Making Your Applications Configurable](/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable)
tutorial, if you want a reference to the configuration in your application
class, you need to declare the configuration as a `volatile` member variable,
decorate your application class with the `@Component` annotation, specify the
appropriate `configurationPid` in the `@Component` annotation, add an
appropriately annotated `activate` method that instantiates the configuration
variable, and add a public getter method for each configuration field. Here's a
simple example that makes the sample configuration discussed earlier available
to a portlet class:

    package com.liferay.docs.exampleconfig.portlet;

    import java.io.IOException;
    import java.util.Map;

    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.service.component.annotations.Activate;
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Modified;

    import com.liferay.docs.exampleconfig.configuration.ExampleConfiguration;
    import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

    @Component(
        configurationPid =
        "com.liferay.docs.exampleconfig.configuration.ExampleConfiguration",
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
                            ExampleConfiguration.class.getName(),
                            _exampleConfiguration);

                    super.doView(renderRequest, renderResponse);
            }
            
            public String getFavoriteColor(Map labels) {
                    return (String) labels.get(_exampleConfiguration.favoriteColor());
            }

            @Activate
            @Modified
            protected void activate(Map<Object, Object> properties) {
                    _exampleConfiguration = ConfigurableUtil.createConfigurable(
                            ExampleConfiguration.class, properties);
            }

            private volatile ExampleConfiguration _exampleConfiguration;

    }

In this example, overriding the `doView` method is not strictly necessary.
However, it's useful since adding the configuration to the request object before
calling `super.doView` makes the configuration able to be read from the request
by the application's JSPs.

## Implementing a Configuration Action [](id=implementing-a-configuration-action)

To implement a configuration action, you should create a class that extends
Liferay's `DefaultConfigurationAction` class. Then you need to add a reference
to your configuration the same way that you added such a reference to your
application class. Declare the configuration as a `volatile` member variable,
decorate your configuration action class with the `@Component` annotation,
specify the appropriate `configurationPid` in the `@Component` annotation, add
an appropriately annotated `activate` method that instantiates the configuration
variable, and add a public getter method for each configuration field.

Next, you should specify `configurationPolicy = ConfigurationPolicy.OPTIONAL` in
your class's `@Component` annotation. An optional configuration policy means
that the component is created regardless of whether or not the configuration was
set. You also need to specify the portlet to which your configuration action
class applies. To do so, make the following specification in your class's
`@Component` annotation:

    property = {
        "javax.portlet.name=com_liferay_docs_exampleconfig_portlet_ExampleConfigPortlet"
    },

Your component should be registered as a configuration action class so you
should specify `service = ConfigurationAction.class` in your class's
`@Component` annotation.

Next, you should override the `processAction` method so that it reads a URL
parameter from the action request, sets the value as a portlet preference, and
invokes the `processAction` method of the `SettingsConfigurationAction` ancestor
class. Finally, you should override the `include` method so that it sets the
configuration as an attribute of the HTTP servlet request and then invokes the
`include` method of the `BaseJSPSettingsConfigurationAction` class. Here's an
example:

    package com.liferay.docs.exampleconfig.action;

    import java.util.Map;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.PortletConfig;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.osgi.service.component.annotations.Activate;
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.ConfigurationPolicy;
    import org.osgi.service.component.annotations.Modified;

    import com.liferay.docs.exampleconfig.configuration.ExampleConfiguration;
    import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
    import com.liferay.portal.kernel.portlet.ConfigurationAction;
    import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
    import com.liferay.portal.kernel.util.ParamUtil;

    @Component(
        configurationPid = "com.liferay.docs.exampleconfig.configuration.ExampleConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {
            "javax.portlet.name=com_liferay_docs_exampleconfig_portlet_ExampleConfigPortlet"
        },
        service = ConfigurationAction.class
    )
    public class ExampleConfigurationAction extends DefaultConfigurationAction {

        @Override
        public void processAction(
                PortletConfig portletConfig, ActionRequest actionRequest,
                ActionResponse actionResponse)
            throws Exception {

            String favoriteColor = ParamUtil.getString(actionRequest, "favoriteColor");
            setPreference(actionRequest, "favoriteColor", favoriteColor);

            super.processAction(portletConfig, actionRequest, actionResponse);
        }

        @Override
        public void include(
            PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

            httpServletRequest.setAttribute(
                ExampleConfiguration.class.getName(),
                _exampleConfiguration);

            super.include(portletConfig, httpServletRequest, httpServletResponse);
        }

        @Activate
        @Modified
        protected void activate(Map<Object, Object> properties) {
            _exampleConfiguration = ConfigurableUtil.createConfigurable(
                ExampleConfiguration.class, properties);
        }

        private volatile ExampleConfiguration _exampleConfiguration;

    }

Now that your configuration action class has been created, you're ready to
create a user interface for selecting configuration options and submitting the
selections.

## Implementing the User Interface For Configuring Your Application [](id=implementing-the-user-interface-for-configuring-your-application)

When creating a JSP-based user interface, it's convenient to create an
`init.jsp` page for your application. The `init.jsp` page should contain all of
the imports, tag library declarations, and other page components are required by
your other JSPs. Each of your other pages should import `init.jsp` so that you
don't need to duplicate code. Liferay follows this convention.

Here's an example `init.jsp` file:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <%@ page import="com.liferay.docs.exampleconfig.configuration.ExampleConfiguration" %>
    <%@ page import="com.liferay.portal.kernel.util.StringPool" %>
    <%@ page import="com.liferay.portal.kernel.util.Validator" %>

    <portlet:defineObjects />

    <liferay-theme:defineObjects />

    <%
        ExampleConfiguration exampleConfiguration =
            (ExampleConfiguration)
            renderRequest.getAttribute(ExampleConfiguration.class.getName());

        String favoriteColor = StringPool.BLANK;
        
        if (Validator.isNotNull(exampleConfiguration)) {
            favoriteColor =
                portletPreferences.getValue(
                    "favoriteColor", exampleConfiguration.favoriteColor());
        }
    %>

This JSP not only declares some tag libraries and imports some classes. It uses
the `<portlet:defineObjects />` and `<liferay-theme:defineObjects />` tags to
make certain variables available on the page. The scriptlet at the end of the
file uses one of these variables, `renderRequest`, to get the configuration
which was stored in the `renderRequest` by your portlet's `doView` method.
Finally, the value of a specific field (`favoriteColor`) is read from the
configuration.

The default view of your application is provided by your application's
`view.jsp`. Your `view.jsp` should import your `init.jsp` so that the same tag
libraries, imports, and variables are available on the page. Here's an example
`view.jsp` file:

    <%@ include file="/init.jsp" %>

    <p>
        <liferay-ui:message key="com_liferay_docs_exampleconfig_portlet_ExampleConfigPortlet.caption"/>
    </p>

    <%
        boolean noConfig = Validator.isNull(favoriteColor);
    %>

    <c:choose>
        <c:when test="<%= noConfig %>">
            <p>
                Please select use the portlet configuration to select a favorite color.
            </p>
        </c:when>

        <c:otherwise>
            <p style="color: <%= favoriteColor %>">
                Favorite color: <%= favoriteColor %>!
            </p>
        </c:otherwise>
    </c:choose>

This JSP simply checks whether or not the `favoriteColor` variable is empty. If
it's empty, a message is displayed that tells the user that they need to select
a favorite color in the portlet's configuration. If the `favoriteColor` variable
is not empty, the name of the selected color is displayed in the selected color.
Note: The value of the
`com_liferay_docs_exampleconfig_portlet_ExampleConfigPortlet.caption` language
key must be specified in your application's `Language.properties` file. The
default location for this file is in the `content` package.

The configuration user interface of your application is provided by your
application's `configuration.jsp` file. This interface is displayed on the Setup
tab when a user clicks on your application's gear icon and then selects
*Configuration*. As previously discussed, your `configuration.jsp` should import
your `init.jsp` file. Here's an example `configuration.jsp` file:

    <%@ include file="/init.jsp" %>

    <%@ page import="com.liferay.portal.kernel.util.Constants" %>

    <liferay-portlet:actionURL portletConfiguration="<%= true %>"
        var="configurationActionURL" />

    <liferay-portlet:renderURL portletConfiguration="<%= true %>"
        var="configurationRenderURL" />

    <aui:form action="<%= configurationActionURL %>" method="post" name="fm">

        <aui:input name="<%= Constants.CMD %>" type="hidden"
            value="<%= Constants.UPDATE %>" />

        <aui:input name="redirect" type="hidden"
            value="<%= configurationRenderURL %>" />

        <aui:fieldset>

            <aui:select name="favoriteColor" label="Favorite Color"
                value="<%= favoriteColor %>">
                <aui:option value="indigo">Indigo</aui:option>
                <aui:option value="blue">Blue</aui:option>
                <aui:option value="green">Green</aui:option>
                <aui:option value="yellow">Yellow</aui:option>
                <aui:option value="orange">Orange</aui:option>
                <aui:option value="red">Red</aui:option>
            </aui:select>

        </aui:fieldset>
        <aui:button-row>
            <aui:button type="submit"></aui:button>
        </aui:button-row>
    </aui:form>

This JSP uses the `<liferay-portlet:actionURL />` and
`<liferay-portlet:renderURL />` tags to construct two URLs in the variables
`configurationActionURL` and `configurationRenderURL`. The JSP presents a simple
form that allows the user to select a favorite color. When the user submits the
form, the `configurationActionURL` is invoked and the application's
`processAction` method is invoked with the `favoriteAction` included as a
request parameter:

    <aui:form action="<%= configurationActionURL %>" method="post" name="fm">

If the request fails, the user is redirected to the configuration page:

    <aui:input name="redirect" type="hidden"
        value="<%= configurationRenderURL %>" />

It's a best practice to supply a URL parameter named `cmd` (`Constants.CMD`
equals `cmd`) whose value indicates the purpose of the request. In this example,
the value of the `cmd` parameter is `update` (`Constants.CMD` equals `update`):

    <aui:input name="<%= Constants.CMD %>" type="hidden"
        value="<%= Constants.UPDATE %>" />

Many Liferay applications read the value of the `cmd` parameter and perform some
processing depending on its value.

You can find a complete example project on Github here:
[(not yet written)]()

To test this example project, deploy the application to Liferay, add it to a
page, click on the gear icon, and select *Configuration*. Select a favorite
color and click *Save*. To confirm that your selection was saved as a portlet
configuration setting, look for the application to display a message like this:

    Favorite color: blue!

Excellent! Now you know how to create application configurations and how to
create a mechanism to allow users to edit the configuration.
