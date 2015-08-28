# Using JSPs in a Portlet Application [](id=using-jsps-in-a-portlet-application)

In this tutorial, you'll learn how to add JSP support to a portlet application.
If you haven't yet created a portlet application, please refer to the
[Creating a Portlet Application]() tutorial for instructions. You can find the
code referenced by this tutorial in a complete project on Github here:
[https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-jsp-portlet](https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-jsp-portlet)

To add JSP support to your portlet application, follow these steps:

1. Add the `portal-service` dependency to your project so that your portlet
   class can extend Liferay's `MVCPortlet` class.

2. Create or update your portlet to extend `MVCPortlet` (and not, for example,
   `GenericPortlet`).

3. Use properties of the `@Component` annotation of your portlet class to
   specify the location of your JSP files.

4. Create your JSP files in the locations that you specified in the previous
   step.

5. In your project's `bnd.bnd` file, specify the resources that must be included
   in your portlet application bundle.

Next, you'll examine these steps in more detail.

## Add the portal-service Dependency to Your Project [](id=add-the-portal-service-dependency-to-your-project)

To add the `portal-service` dependency to your project, edit your project's
`ivy.xml` file and add the following line inside the `<dependencies>` element:

    <dependency name="portal-service" org="com.liferay.portal" rev="7.0.0-nightly"/>

Save your `ivy.xml` file and then run this command from your project's directory
to download the dependency:

    ant clean

If you're working in Eclipse, add the `lib/portal-service.jar` file to your
project's build path.

## Make Your Portlet Extend MVCPortlet [](id=make-your-portlet-extend-mvcportlet)

Once you've added the `portal-service.jar` dependency to your project, you can
make your portlet class extend Liferay's `MVCPortlet` class. After performing
this step by either creating a new portlet class or updating an existing one,
your portlet class might look like this:

    package com.liferay.docs.examplejspportlet;

    import javax.portlet.Portlet;

    import org.osgi.service.component.annotations.Component;

    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Example JSP Portlet",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
    )
    public class ExampleJSPPortlet extends MVCPortlet {
            
    }

Note that the contents of your portlet class can be empty. All the required
functionality is supplied by `MVCPortlet`.

## Use the @Component Annotation to Specify the Location of Your JSPs [](id=use-the-component-annotation-to-specify-the-location-of-your-jsps)

Now you need to specify the location of your portlet's JSP files. To do so, add
the following two portlet properties to the `@Component` annotation's list of
properties:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp"

The `javax.portlet.init-param.template-path` property sets the path to the JSP
view templates. The `javax.portlet.init-param.view-template` sets the path to
the default JSP file (the one that's used for the portlet's initial view).

## Create Your Portlet's JSP Files [](id=create-your-portlets-jsp-files)

Now that you've specified locations for your portlet's JSPs, it's time to create
the JSPs in these locations.

In your project's `src` folder, create a JSP file with this path:
`src/META-INF/resources/view.jsp`. In a bundle, resources are stored in the
`src/META-INF/resources` folder and not in the `docroot/WEB-INF` folder (as in
WAR files). The `src/META-INF/resources` folder is treated as the root folder
for your application's resources. This is why you specified `/` and `/view.jsp`
as the values of the `javax.portlet.init-param.template-path` and
`javax.portlet.init-param.view-template` properties, respectively.

Add the following contents to your `view.jsp` file:

    <p>Hello from view.jsp!</p>

This simple example suffices for now. 

## In Your Project's bnd.bnd file, Specify Resources to Include [](id=in-your-projects-bnd-bnd-file-specify-resources-to-include)

Your last step is to specify the resources that must be included in your portlet
application bundle.

To do so, open your project's `bnd.bnd` file and add the following lines:

    Include-Resource:\
            classes,\
            META-INF/resources=src/META-INF/resources

This instructions tell the [bnd](http://www.aqute.biz/Bnd/Bnd) tool to include
the contents of your project's `src/META-INF/resources` folder, along with your
project's classes, when your portlet application bundle JAR file is generated.
The bnd tool is included in Liferay's Plugins SDK.

## Verifying Your Application's JSP Configuration [](id=verifying-your-applications-jsp-configuration)

To check that your application's JSPs are correctly configured, redeploy your
portlet bundle and confirm that the contents of your JSP is displayed by your
portlet.

Add your portlet to a page and confirm that this message (or whatever message
you configured) is displayed by the portlet:

    Hello from view.jsp!

Great! Now that you've verified that your application's JSPs are correctly
configured, it's time to learn how to use tag libraries.

## Using Tag Libraries in Your Application's JSPs [](id=using-tag-libraries-in-your-applications-jsps)

Liferay supports [JSTL](https://jstl.java.net) by default. To use JSTL tags in
one of your application's JSPs, add the following taglib declaration:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Add this declaration your project's `view.jsp` file. To test that it's working,
add the following lines to your `view.jsp` file:

    <c:if test="<%= true %>">
        <p>Hello from JSTL!</p>
    </c:if>

Then redeploy your portlet bundle and confirm that you can see the message that
you wrapped inside the `<c:if>` tag.

+$$$

Note: Since Liferay supports JSTL, you don't have to add the JSTL tag library
JAR as Ivy dependencies to your project. Just as an example, here's how you
could add JSTL to your project as a dependency if Liferay did not already
provide it. Just add the following line to your project's `ivy.xml` file, inside
of the `<dependencies>` tag:

    <dependency name="jstl" org="javax.servlet" rev="1.2" />

This step, however, isn't necessary. It would be necessary only for dependencies
that Liferay does not already provide. 

$$$

## Related Topics [](id=related-topics)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Creating a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/creating-a-portlet-application)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
