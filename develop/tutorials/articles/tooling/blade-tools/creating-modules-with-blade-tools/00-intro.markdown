# Creating Modules with Blade Tools

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

Before creating your application, make sure you've created a Liferay Workspace
and navigated to the workspace's `modules` directory. If you'd like to learn
more about Liferay Workspaces and how to create/use them, see the
[Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial.

Now that you know the basics on creating Liferay module projects using `blade
create`, you can visit some of the tutorials in this section on specific
`create` templates. The templates will be discussed further so you know exactly
what each one has to offer.
