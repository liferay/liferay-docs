# Creating Bundles with Blade Tools [](id=creating-bundles-with-blade-tools)

Some folks just don't want to use Liferay IDE, or the Liferay Plugins SDK, to
develop their plugins for Liferay, and that's fine. Where your code lives, and
how it's handled before it become a JAR file that can be added to Liferay's
module framework, is between you and your code (and your boss). The Blade Tools
project can be used to create projects based on Maven or Gradle, and then you
can manage them however you like. 

Blade Tools provides project templates for Liferay 7 that don't require any
special developer tools. Like IntelliJ or NetBeans? Like to code with just a
text editor? Then BLADE is for you. 

This tutorial assumes you already installed Blade Tools. There's an
[installation tutorial](/develop/tutorials/-/knowledge_base/7-0/installing-blade-tools) 
if you haven't yet installed the tools.

The tool's `create` subcommand creates a project from a template, including
the folder structure, `build.gradle` script, any resources (such as
JSPs)--depending on the template--, and a Java class with the `@Component`
annotation. After that, you just develop as you would normally, build the JAR
file, and deploy the bundle to Liferay's module framework.

This tutorial focuses only on two commands available with Blade Tools; the
`blade create ...`  and `blade deploy ...` commands.

## Using the Create Command [](id=using-the-create-command)

At the time of this writing, typing *blade create* into a terminal produces this output:

    NAME
      create                      - Creates a new Liferay module project.

    SYNOPSIS
       create [options] <name> <[service]>

    OPTIONS

       [ -b, --build <build> ]    - The build type of project to create. Valid
                                    values are maven or gradle. Default: gradle
       [ -c, --classname <string> ] - If a class is generated in the project,
                                    provide the name of the class to be generated.
                                    If not provided defaults to Project name.
       [ -d, --dir <file> ]       - The directory where to create the new project.
       [ -i, --ide <ide> ]        - The type of IDE metadata to create along side
                                    the new project.
       [ -p, --projectType <type> ] - The type of Liferay module to create. Valid
                                    values are portlet, jspportlet, or service.
       [ -v, --version <version> ] - The version of Liferay to create the module
                                    for, by default its 7.0.0

The correct command syntax for creating a project is found under SYNOPSIS:

    create [options] [name] [service]

To create a bundle with a JSP Portlet, you could specify

    blade create --projectType "jspportlet" --classname "MyJsp" --dir . com.liferay.jspportlet Portlet

The above command creates a JSP Portlet (`projectType`) with a component class
called MyJspPortlet (`classname`), in the current directory (`dir`). The project
`name` is `com.liferay.jspportlet`, and the `service` type we are creating is
`Portlet`.

Here's the class that's created based on the above command:

    package com.liferay.jspportlet;

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

Your `@Component` is already set up for you, with all necessary properties
configured and the necessary imports declared.

Since the above command included `projectType jspportlet`, you also have JSPs at
`[Project_Root]/src/main/resources/META-INF/resources`. Both `init.jsp` and
`view.jsp` are included by default.

The default view looks like this:

    <%@ include file="/init.jsp" %>

    <p>
        <b><liferay-ui:message key="____________________________.caption"/></b>
    </p>

Here's the full view of your new project: 

![Figure 1: The `create` command generates a project structure and some basic files so you can begin developing quickly.](../../images/blade-jspportlet-structure.png)

You probably noticed that *Portlet* was appended to the string specified with
the `classname` option, resulting in a Java class with the name `MyJspPortlet`.
If you instead specified `--classname "MyJspPortlet"`, the resulting class
becomes `MyJspPortletPortlet`, which is rather redundant, don't you think?

In addition to the auto-generated files and directories, you get a `build.gradle`
build script, along with the necessary `bnd.bnd` file.

## Using the Deploy Command [](id=using-the-deploy-command)

Once you have your portlet developed and built (`gradle build` if you use
Gradle), deploy it to Liferay. With Blade Tools, you can take advantage of the
`deploy` subcommand. If you have Liferay 7 running, just open a terminal and
type `blade deploy [path-to-JAR]`. The blade tool connects to the Liferay
Module Framework and installs your module's JAR file. By typing `telnet
localhost 11311` in a terminal and then `lb` once inside the Gogo Shell, you
can confirm that your bundle was installed properly.

     312|Active     |    1|com.liferay.jspportlet (1.0.0.201508271714)

The above output in the Gogo Shell indicates your module is installed and
active.

If Blade Tools doesn't meet your needs, consider using one of the BLADE
repository project templates to manually create Liferay bundles. Check out our
[tutorial on that next](/develop/tutorials/-/knowledge_base/7-0/blade-manual-bundle-creation).

## Related Topics [](id=related-topics)

[Installing Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-tools)

[Using the Felix Gogo Shell](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework#using-the-felix-gogo-shell)

[Using JSPs in a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/using-jsps-in-a-portlet-application)
