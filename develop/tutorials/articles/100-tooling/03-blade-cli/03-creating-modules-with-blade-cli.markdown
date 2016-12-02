# Creating Modules with Blade CLI [](id=creating-modules-with-blade-cli)

When you use Blade CLI to create a project, your project's folder structure,
build script (`build.gradle`), Java classes, and other resources (such as JSPs)
are created based on the chosen template. Once you finish developing your
project, you can use Gradle to build your project's JAR file and use Blade CLI
to deploy your module to @product@'s module framework. In this tutorial, you'll
learn how to use Blade CLI standalone. You can, however, use Blade CLI from a
Liferay Workspace, which offers additional benefits. You can learn more about
Liferay Workspaces in its dedicated
[tutorial](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace).

To create a new Liferay module project, you can run the Blade `create` command,
which offers many available templates. There are, however, many other options
you can specify to help mold your project just the way you want it. To learn how
to use the Blade `create` command and the many options it provides, enter `blade
create -x` into a terminal. A list of the `create` options are listed below:

<!-- The option `-x` is an arbitrary value; the only way to print create options
in the terminal currently is to specify an option that does not exist. The
option can be anything that doesn't already exist. When there's a specific value
to prompt the list of options, I'll update the docs. -Cody -->

- `-c, --classname <string>`: If a class is generated in the project, provide
  the name of the class to be generated. If not provided, the class name
  defaults to the project name.
- `C, --contributorType <string>`: Identifies your module as a theme
  contributor. This is also used to add the `Liferay-Theme-Contributor-Type` and
  `Web-ContextPath` bundle headers to the BND file.
- `-d, --dir <file>`: The directory to create the new project.
- `-h, --hostbundlebsn <string>`: If a new JSP hook fragment needs to be
  created, provide the name of the host bundle symbolic name.
- `-H, --hostbundleversion <string>`: If a new JSP hook fragment needs to be
  created, provide the name of the host bundle version.
- `l, --listtemplates`: Prints a list of available project templates.
- `-p, --packagename <string>`: The package name to use when creating the
  project.
- `-s, --service <string>`: If a new Declarative Services (DS) component needs
  to be created, provide the name of the service to be implemented. Note that in
  this context, the term *service* refers to an OSGi service, not to a Liferay
  API.
- `-t, --template <template>`: The project template to use when creating the
  project. Run `blade create -l` for a listing of available Blade CLI templates.

To create a module project, follow the following syntax:

    blade create [OPTIONS] <NAME>

For example, if you wanted to create an MVC portlet project, you could execute
the following:

    blade create -t mvcportlet -p com.liferay.docs.guestbook -c GuestbookPortlet
    my-guestbook-project

This command creates an MVC portlet project based on the template `mvcportlet`.
It uses the package name `com.liferay.docs.guestbook` and creates the portlet
class `GuestbookPortlet`. The project name is `my-guestbook-project`. Since the
directory was not specified, it is created in the folder you executed the
command. When generating a project using Blade CLI, there is no downloading,
which means internet access is not required.

Using Blade CLI gives you the flexibility to choose how you want to create your
application. You can do so in your own standalone environment, or within a
Liferay Workspace. If you'd like to learn more about Liferay Workspaces see the
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
tutorial.

Creating Liferay modules in a workspace using Blade CLI is very similar to
creating them in a standalone environment. You can find the details of how to
create a Liferay Workspace using Blade CLI in the
[Creating a Liferay Workspace with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-blade-cli)
tutorial.

When creating modules in a workspace, you should navigate to
the `/modules` directory to create your project. You can also provide further
directory nesting into the `/modules` directory, if preferred. Your workspace,
by default, sets the directory where your modules should be stored by setting
the following property in the workspace's `gradle.properties` file:

    liferay.workspace.modules.dir=modules

Change this property if you'd like to store your modules in a different
directory.

When using Blade CLI, you'll have to manually edit your project's component
class. Blade CLI gives you the ability to specify the class's name, but all
other contents of the class can only be edited after the class is created. See
the [Creating Modules with Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-liferay-ide)
tutorial for further details and important dependency information on component
classes.

Now that you know the basics on creating Liferay module projects using `blade
create`, you can visit some of the tutorials in this section on specific
`create` templates. The templates will be discussed further so you know exactly
what each one has to offer.
