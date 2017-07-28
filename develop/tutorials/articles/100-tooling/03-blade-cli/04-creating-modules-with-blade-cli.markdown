# Creating Modules with Blade CLI [](id=creating-modules-with-blade-cli)

When you use Blade CLI to create a project, your project's folder structure,
build script (`build.gradle`), Java classes, and other resources (such as JSPs)
are created based on the chosen template. In this tutorial, you'll learn how to
use Blade CLI to create modules based on pre-existing templates and samples.

Using Blade CLI gives you the flexibility to choose how you want to create your
application. You can do so in your own standalone environment, or within a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace). 
Creating Liferay modules in a workspace using Blade CLI is very similar to
creating them in a standalone environment.

When creating modules in a workspace, you should navigate to the `/modules`
directory to create your project. You can also provide further directory nesting
into the `/modules` directory, if preferred. Your workspace, by default, sets
the directory where your modules should be stored by setting the following
property in the workspace's `gradle.properties` file:

    liferay.workspace.modules.dir=modules

Change this property if you'd like to store your modules in a different
directory.

+$$$

**Note:** Your modules should define a repository where external dependencies
can be downloaded. Unlike Maven, Gradle does not define any repositories by
default. For convenience, modules created with Blade CLI define Liferay's
public Nexus repository as its default repository. This is defined, however, in
different files depending on where the module was created.

If you used Blade CLI to create a module outside of a workspace, your
repository is defined in the module's `build.gradle` file. Likewise, if you
created your module inside a workspace, your repository is defined in the
`settings.gradle` file located in the workspace's ROOT folder. This ensures that
all modules residing in the workspace share the same repository URL.

$$$

First, you'll learn how to create a module using a template.

## Module Templates [](id=module-templates)

To create a new Liferay module project, you can run the Blade `create` command,
which offers many available templates. There are, however, many other options
you can specify to help mold your project just the way you want it. To learn how
to use the Blade `create` command and the many options it provides, enter `blade
help create` into a terminal. A list of the `create` options are listed below:

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
- `-l, --listtemplates`: Prints a list of available project templates.
- `-p, --packagename <string>`: The package name to use when creating the
  project.
- `-s, --service <string>`: If a new Declarative Services (DS) component needs
  to be created, provide the name of the service to be implemented. Note that in
  this context, the term *service* refers to an OSGi service, not to a Liferay
  API.
- `-t, --template <template>`: The project template to use when creating the
  project. Run `blade create -l` for a listing of available Blade CLI templates.

To create a module project, use the following syntax:

    blade create [OPTIONS] <NAME>

For example, if you wanted to create an MVC portlet project, you could execute
the following:

    blade create -t mvc-portlet -p com.liferay.docs.guestbook -c GuestbookPortlet my-guestbook-project

This command creates an MVC portlet project based on the template `mvc-portlet`.
It uses the package name `com.liferay.docs.guestbook` and creates the portlet
class `GuestbookPortlet`. The project name is `my-guestbook-project`. Since the
directory was not specified, it is created in the folder you executed the
command. When generating a project using Blade CLI, there is no downloading,
which means internet access is not required.

When using Blade CLI, you'll have to manually edit your project's component
class. Blade CLI gives you the ability to specify the class's name, but all
other contents of the class can only be edited after the class is created. See
the [Creating Modules with Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-liferay-ide)
tutorial for further details and important dependency information on component
classes.

Now that you know the basics on creating Liferay module projects using `blade
create`, you can visit the
[Project Templates](/develop/reference/-/knowledge_base/7-0/project-templates)
reference section to view specific `create` templates and how they work.

Next, you'll explore Liferay's provided module samples and how to generate them
using Blade CLI.

## Module Samples [](id=module-samples)

Liferay provides many sample modules that are useful for those interested in
learning best practices on structuring their modules to accomplish specific
tasks in @product@. These samples can be found in the
[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples) Github
repository. You can also learn more about these samples by visiting the
[Liferay Sample Modules](/develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules)
article.

You can generate these samples using Blade CLI for convenience, instead of
cloning the repository and manually copy/pasting them to your environment. To do
this, use the following syntax:

    blade samples <NAME>

For example, if you wanted to generate the
[portlet-ds](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/ds-portlet)
sample, you could execute

    blade samples ds-portlet

For a full listing of all the available Blade samples, run

    blade samples

Awesome! Now you know the basics on creating Liferay module projects with Blade
CLI.
