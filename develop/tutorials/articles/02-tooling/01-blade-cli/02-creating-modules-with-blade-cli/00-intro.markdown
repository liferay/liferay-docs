# Creating Modules with Blade CLI [](id=creating-modules-with-blade-cli)

When you use Blade CLI to create a project, your project's folder structure,
build script (`build.gradle`), Java classes, and other resources (such as JSPs)
are created based on the chosen template. Once you finish developing your
project, you can use Gradle to build your project's JAR file and use Blade CLI
to deploy your module to @product@'s module framework. In this tutorial, you'll
learn how to use Blade CLI standalone. You can, however, use Blade CLI from a
Liferay Workspace, which offers additional benefits. You can learn more about
creating modules with a Liferay Workspace in the
[Creating Modules in a Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-modules-in-a-workspace)
tutorial.

To create a new Liferay module project, you can run the Blade `create` command,
which offers many available templates. There are, however, many other options
you can specify to help mold your project just the way you want it. To learn how
to use the Blade `create` command and the many options it provides, enter *blade
create* into a terminal. By running this command, all options for this command
are displayed. A list of the `create` options are listed below:

- `-c, --classname <string>`: If a class is generated in the project, provide
  the name of the class to be generated. If not provided, the class name
  defaults to the project name.
- `-d, --dir <file>`: The directory to create the new project.
- `-h, --hostbundlebsn <string>`: If a new JSP hook fragment needs to be
  created, provide the name of the host bundle symbolic name.
- `-H, --hostbundleversion <string>`: If a new JSP hook fragment needs to be
  created, provide the name of the host bundle version.
- `-p, --packagename <string>`: The package name to use when creating the
  project.
- `-s, --service <string>`: If a new DS component needs to be created, provide
  the name of the service to be implemented. Note that in this context, the term
  *service* refers to an OSGi service, not to a Liferay API.
- `-t, --template <template>`: The project template to use when creating the
  project. The following templates are available:
    - `activator`
    - `fragment`
    - `mvcportlet`
    - `portlet`
    - `service`
    - `servicebuilder`
    - `servicewrapper`

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
command.

Now that you know the basics on creating Liferay module projects using `blade
create`, you can visit some of the tutorials in this section on specific
`create` templates. The templates will be discussed further so you know exactly
what each one has to offer.

Next, you'll learn how to deploy a module to @product@.

## Deploying to Liferay [](id=deploying-to-liferay)

Once you've created your Liferay module project, you'll need to deploy it to
your Liferay instance. To use Blade CLI to deploy your module, use the following
command:

    blade deploy

Blade CLI can detect a locally running Liferay instance and automatically
deploys your module to that Liferay instance. You can also specify a custom port
to deploy your module to using the `-p` parameter followed by the port number.
For instance, you could run `blade deploy -p 8090` to deploy to port 8090. You
can also watch the deployed module for changes by specifying the `-w` parameter.
This parameter automatically redeploys the module when changes are detected.

+$$$

**Note:** The `blade deploy` command requires Gradle to be installed on your
machine, or the use of a Gradle wrapper. To ensure the availability of the
Gradle wrapper, you can work in a Liferay Workspace. For more information on
Liferay Workspaces, see the
[Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial.

$$$

Awesome! You've successfully created a module project using one of Blade CLI's
existing templates and deployed it to a running Liferay server.
