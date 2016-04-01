# Creating Liferay Applications [](id=creating-liferay-applications)

Blade Tools allows you to very easily create Liferay applications. In this
tutorial, you'll learn how. If you haven't yet installed Blade
Tools, please refer to the
[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)
tutorial. Blade Tools provides project templates for creating certain types of
Liferay modules. At the time of this writing, the available templates include
activators, JSP hooks, MVC portlets, portlets, services, service builders, and
service wrappers. When creating a new module project, Blade Tools uses Gradle.

When you use Blade Tools to create a project, your project's folder structure,
build script (`build.gradle`), Java classes, and other resources (such as JSPs)
are created based on the chosen template. Once you've created a project, you can
import your project into your favorite IDE and proceed with development. All
three of the major Java IDEs (Eclipse, IntelliJ, and Netbeans) provide Gradle
support. Liferay's Eclipse-based IDEs (Liferay IDE and Liferay Developer Studio)
provide wizards that allow you to create new Liferay module projects. These
wizards use Blade Tools under the hood. Once you finish developing your project,
you can use Gradle to build your project's JAR file and use Blade Tools to
deploy your module to Liferay's module framework.

In this tutorial, you'll learn how to create a Liferay MVC portlet application
as a Liferay module. Before creating your application, make sure you've created
a Liferay Workspace and navigated to the workspace's `modules` directory. If
you'd like to learn more about Liferay Workspaces and how to create/use them,
see the [Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial.

To create a Liferay MVC portlet application as a module, use a command like the
following:

    blade create -t mvcportlet -p com.liferay.docs.mvcportlet -c MyMvcPortlet my-mvc-portlet-project

The command above creates an MVC portlet project named `my-mvc-portlet-project`
in the current directory. You set the package name to
`com.liferay.docs.mvcportlet` and your portlet component class name to
`MyMvcPortlet`. Note that in that class, you're creating a service of type
`Portlet`. Here, *service* means an OSGi service, not a Liferay API. Another way
to say *service type* is to say *component type*.

After running the command above, your project's directory structure looks like
this:

- `my-mvc-portlet-project`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/mvcportlet`
                    - `MyMvcPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `init.jsp`
                        - `view.jsp`
    - `bnd.bnd`
    - `build.gradle`

Here's the generated Java class:

    package com.liferay.docs.mvcportlet;

    import javax.portlet.Portlet;

    import org.osgi.service.component.annotations.Component;

    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

    @Component(
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
    public class MyMvcPortlet extends MVCPortlet {

    }

Notice that this class is decorated with the `@Component` annotation. All of the
necessary properties are configured. The line `service = Portlet.class` means
that your class represents a portlet component. In other words, your class
provides an implementation of the `Portlet` interface.

Notice further that your project does not contain any configuration files like
`portlet.xml`, `liferay-portlet.xml`, or `liferay-plugin-package.properties`.
All the necessary properties are defined in the `property = { ... }` block.

The `immediate = true` specification in the `@Component` annotation instructs
Liferay's module framework to start your component immediately. This is
appropriate since your portlet should be available right away for users to add
to a page. If `immediate = false` were set, your component would be lazily
instantiated (it would delay instantiation until some other component requested
it as a dependency). This can be appropriate for some components, but not
portlet components. 

Finally, notice that all the required imports are declared. All the dependencies
required by an MVC portlet are declared in your project's `build.gradle` file.

Since you created an MVC portlet, your project includes a few JSP files that
constitute your application's view layer: `init.jsp` and `view.jsp`. By
convention, `init.jsp` contains all the imports required by any of the other
JSPs that make up your application's view layer. All the other JSPs import
`init.jsp`. This convention simplifies dependency management for your
application's view layer.

Here's the contents of the generated `init.jsp`:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    <liferay-theme:defineObjects />

The `init.jsp` includes some tag library declarations. After the declarations,
it uses two specific tags: `<portlet:defineObjects />` and
`<liferay-theme:defineObjects />`. These tags make a variety of portlet-specific
and Liferay-specific variables available to the JSP.

Here are the contents of the generated `view.jsp`:

    <%@ include file="/init.jsp" %>

    <p>
        <b><liferay-ui:message key="com_liferay_docs_jspportlet_MyJspPortlet.caption"/></b>
    </p>

As you can see, `view.jsp` imports `init.jsp`. Thus, all the tag libraries
declared in `init.jsp` are available in `view.jsp`. The variables made available
by the `<portlet:defineObjects />` and `<liferay-theme:defineObjects />` tags
are also available. `view.jsp` uses the `liferay-ui:message />` tag to display a
message with a certain key. That key and its value are defined in your project's
`Language.properties` file. Remember, the location of your project's
`Language.properties` file was configured by this line in your portlet class's
`@Component` annotation: `javax.portlet.resource-bundle=content.Language`

Your project also includes generated `bnd.bnd` and `build.gradle` files. The
`bnd.bnd` is used to generated the `MANIFEST.MF` file that ends up in the JAR
file that's produced when you build your project. For more information about
`bnd.bnd`, see [http://aqute.biz/Code/bnd](http://aqute.biz/Code/bnd). The
generated `build.gradle` file specifies all dependencies.

To build your project, navigate to your project's root directory and run `gradle
build`. The JAR file representing your portlet module is produced in your
project's `build/libs` directory. If you followed the example in this tutorial,
it should be called `com.liferay.docs.mvcportlet-1.0.0.jar`.

Blade Tools makes it extremely easy to deploy your project. From your project's
root directory, run this command:

    blade deploy

Blade Tools detects your locally running Liferay instance and deploys the
specified module to Liferay's module framework. This command requires the remote
agent be installed for Blate tools. To install this, run `blade agent install`.
After running the `blade deploy` command, you should see a message like this:

    Installed or updated bundle 333

Use the Gogo shell via Blade to confirm your module was installed: Open a
terminal and Enter `blade sh lb` at the prompt. If your module was installed,
you'll see an entry like this:

    333|Active     |    1|com.liferay.docs.mvcportlet (1.0.0.201602111940)

Finally, log into your portal. Add your portlet to a page and confirm that it
works correctly. Congratulations! You've created and deployed a Liferay 7 style
application!

## Related Topics [](id=related-topics)

[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)

[Using BLADE to Create Modules](/develop/tutorials/-/knowledge_base/7-0/using-blade-to-create-modules)
