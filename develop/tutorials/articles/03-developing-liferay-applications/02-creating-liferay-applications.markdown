# Creating Liferay Applications

Blade Tools allows you to very easily create Liferay applications. In this
tutorial, you'll learn how. If you haven't yet installed Blade
Tools, please refer to the [Introduction to Blade Tools](need link) tutorial.
Blade Tools provides project templates for creating certain types of Liferay
modules. At the time of this writing, the available templates include portlets,
JSP portlets, services, and service wrappers. When creating a new module
project, Blade Tools allows for a build type to be chosen. At the time of this
writing, the supported build types are Gradle and Maven.

When you use Blade Tools to create a project, your project's folder structure,
build script (`build.gradle` or `pom.xml`), Java classes, and other resources
(such as JSPs) are created based on the chosen template. Once you've created a
project, you can import your project into your favorite IDE and proceed with
development. All three of the major Java IDEs (Eclipse, IntelliJ, and Netbeans)
provide Maven and Gradle support. Liferay's Eclipse-based IDEs (Liferay
IDE and Liferay Developer Studio) provide wizards that allow you to create new
Liferay module projects. These wizards use Blade Tools under the hood. Once you
finish developing your project, you can use Gradle or Maven to build your
project's JAR file and use Blade Tools to deploy your module to Liferay's module
framework.

In this tutorial, you'll learn how to create a JSP portlet application as a
Liferay module.

To create a JSP portlet application as a module, use a command like the
following:

    blade create --projectType "jspportlet" --classname "MyJsp" --dir . com.liferay.docs.jspportlet Portlet

The command above creates a JSP Portlet (`--projectType`) with a component class
named `MyJspPortlet` (`--classname`), in the current directory (`--dir`). The
project `name` (and the package name) is `com.liferay.jspportlet`, and the
`service` type we are creating is `Portlet`. Here, 'service' means an OSGi
service, not a Liferay API. Another way to say 'service type' would be to say
'component type'.

Note that you can abbreviate the command above by using the short forms of the
options:

    blade create -p "jspportlet" -c "MyJsp" -d . com.liferay.docs.jspportlet Portlet

Running the command above creates a project with the following directory
structure:

- `com.liferay.docs.jspportlet`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/jspportlet`
                    - `MyJspPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `init.jsp`
                        - `view.jsp`
    - `bnd.bnd`
    - `build.gradle`

Here's the Java class that's created by the command above:

    package com.liferay.docs.jspportlet;

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
    public class MyJspPortlet extends MVCPortlet {

    }

Notice that when creating a JSP portlet, the string 'Portlet' is appended to the
string that you specify with the `--classpath` or `-c` option. Thus, the
resulting name of your class was `MyJspPortlet`, and not just `MyJsp`.

Notice also that this class is decorated with the `@Component` annotation. All
of the necessary properties are configured. The line `service = Portlet.class`
means that your class represents a portlet component. In other words, your class
provides an implementation of the `Portlet` interface.

Notice further that your project does not contain any configuration files like
`portlet.xml`, `liferay-portlet.xml`, or `liferay-plugin-package.properties`.
All of the necessary properties are defined in the `property = { ... }` block.

The `immediate = true` specification in the `@Component` annotation instructs
Liferay's module framework to start your component immediately. This is
appropriate since you want your portlet to be immediately available to be added
to a page. If `immediate = false` were set, your component would be lazily
instantiated (it would delay instantiation until some other component requested
it as a dependency).

Finally, notice that all of the required imports are declared. All of the
dependencies required by a JSP portlet are declared in your project's
`build.gradle` file.

Since you created a JSP portlet, your project includes a few JSP files that
constitute your application's view layer: `init.jsp` and `view.jsp`. By
convention, `init.jsp` contains all of the imports required by any of the other
JSPs that make up your application's view layer. All of these other JSPs import
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

`init.jsp` includes some tag library declarations. After the declarations, it
uses two specific tags: `<portlet:defineObjects />` and
`<liferay-theme:defineObjects />`. These tags make a variety of portlet-specific
and Liferay-specific variables available to the JSP. See []() and []() for more
information.

Here's the contents of the generated `view.jsp`:

    <%@ include file="/init.jsp" %>

    <p>
        <b><liferay-ui:message key="com_liferay_docs_jspportlet_MyJspPortlet.caption"/></b>
    </p>

As you can see, `view.jsp` imports `init.jsp`. Thus, all of the tag libraries
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
generated `build.gradle` file specifies all of your project's dependencies.

To build your project, simply navigate to your project's root directory and run
`gradle build`. The JAR file representing your portlet module is produced in
your project's `build/libs` directory. If you followed the example in this
tutorial, it should be called `com.liferay.docs.jspportlet-1.0.jar`.

Blade Tools makes it extremely easy to deploy your project. Still from your
project's root directory, run this command:

    blade deploy build/libs/com.liferay.docs.jspportlet-1.0.jar

Blade Tools detects your locally running Liferay instance and deploys the
specified module to Liferay's module framework. After running the `blade deploy
[path to JAR]` command, you should see a message like this:

    Installed or updated bundle 333

Use the Gogo shell to confirm that your module
was installed: Open a terminal and enter `telnet localhost 11311`. Then enter
`lb` at the prompt. If your module was installed, you'll see an entry like this:

    333|Active     |    1|com.liferay.docs.jspportlet (1.0.0.201510171940)

Finally, log into your portal. Add your portlet to a page and confirm that it
works correctly. Congratulations! You've created and deployed a Liferay 7 style
application!
