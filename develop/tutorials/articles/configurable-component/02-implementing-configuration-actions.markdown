# Implementing Configuration Actions

When developing a Liferay application, it's important to think about the
different configuration options that your application should support. It's also
important to think about how users should be able to access your application's
configuration interface. Liferay supports a flexible mechanism for configuring
applications. You can read about it in the
[Making Your Applications Configurable]() tutorial. In this tutorial, you'll
learn to implement a configuration action for your application. The
configuration action is invoked when a user clicks on the gear icon of a Liferay
application and selects *Configuration*.

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
2. Reference your configuration from your application class
3. Implement your configuration action class
4. Implement your view layer of your application's configuration user interface

## Creating a Configuration Interface

As explained in the [Making Your Applications Configurable]() tutorial, if you
want to make your application configurable, you should create a Java interface
to represent the configuration. Decorate your interface with the `@Meta.OCD`
annotation and specify a unique ID using the annotation's `id` attribute. A
common pattern is to use the fully qualified class name of the interface for the
ID since fully qualified class names are unique. Create public methods to
represent configuration fields and decorate the methods with the `@Meta.AD`
annotation. The return type of the method specifies the type of the field. To
specify a field's default value, use the annotation's `deflt` attribute. To
specify that a field is optional, set `required=false`. For more information
about the `Meta.OCD` and `Meta.AD` annotations, please see the
[bnd](http://www.aqute.biz/Bnd/MetaType) documentation. Here's a simple example:

    package com.liferay.docs.exampleconfig.configuration;

    import aQute.bnd.annotation.metatype.Meta;

    @Meta.OCD(id = "com.liferay.docs.exampleconfig.configuration.ExampleConfiguration")
    public interface ExampleConfiguration {

        @Meta.AD(required = false)
        public String favoriteColor();

    }

This sample configuration contains a single string field called `favoriteColor`.

## Referencing Your Configuration From Your Application Class

As was also explained in the [Making Your Applications Configurable]() tutorial,
if you want a reference to the configuration in your application class, you need
to declare the configuration as a `volatile` member variable, decorate your
application class with the `@Component` annotation, specify the appropriate
`configurationPid` in the `@Component` annotation, add an appropriately
annotated `activate` method that instantiates the configuration variable, and
add a public getter method for each configuration field. Here's a simple
example that makes the sample configuration discussed earlier available to a
portlet class:

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
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

    import aQute.bnd.annotation.metatype.Configurable;

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
                    _exampleConfiguration = Configurable.createConfigurable(
                            ExampleConfiguration.class, properties);
            }

            private volatile ExampleConfiguration _exampleConfiguration;

    }

In this example, overriding the `doView` method is not strictly necessary.
However, it's useful since adding the configuration to the request object before
calling `super.doView` makes the configuration able to be read from the request
by the application's JSPs.

## Implementing a Configuration Action
