# Creating Modules in a Workspace

Modules are OSGi-based projects used to develop Liferay applications. In Liferay
Workspaces, Gradle is the build tool used to create Liferay modules. Before you
can begin creating your Liferay module projects from a Liferay Workspace, you'll
need to create a Liferay Workspace, which is covered in the
[Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial. Once you've created your Liferay Workspace, you can begin using it to
create Gradle-based OSGi modules for @product@.

You can create modules in a Workspace via Blade CLI or by using Liferay IDE.
Because Liferay IDE uses the Blade CLI infrastructure under the hood, both
options offer the same flexibility and options. You can learn more about Blade
CLI in the [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)
section of tutorials. In this tutorial, you'll learn about using a Liferay
Workspace to create modules via Blade CLI and Liferay IDE.

Once you've created a project, you can
import your project into your favorite IDE and proceed with development. All
three of the major Java IDEs (Eclipse, IntelliJ, and Netbeans) provide Gradle
support. Liferay's Eclipse-based IDEs (Liferay IDE and Liferay Developer Studio)
provide wizards that allow you to create new Liferay module projects. These
wizards use Blade CLI under the hood.
