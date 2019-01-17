# Deploying Projects with Blade CLI [](id=deploying-projects-with-blade-cli)

Deploying projects to a Liferay server using Blade CLI is easy. To use
the Blade `deploy` command, you must first have built a project to deploy. See
the
[Creating Projects with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli)
tutorials for more information about creating Liferay projects. Once you've
built a project, navigate to it with your CLI and execute the following command
to deploy it:

    blade deploy

This can be used for WAR-style projects and modules (JARs). You can also deploy
all projects in a folder by running the `deploy` command from the parent folder
(e.g., `[WORKSPACE_ROOT]/modules`).

If you're using Liferay Workspace, the `deploy` command copies your project to
the @product@ `/deploy` folder, which is found by reading the Liferay Home
folder set in your workspace's `gradle.properties` or `pom.xml` file. The
`deploy` command works similarly if you're working outside of workspace; the
Liferay Home folder, in contrast, is set by loading the Liferay extension object
(Gradle) or the effective POM (Maven) and searching for the Liferay Home
property stored there. If it's not stored, Blade prompts you to set it so it's
available.

+$$$

**Note:** If you prefer using pure Gradle or Maven to deploy your project, you
can do this by applying the appropriate plugin and configuring your Liferay Home
property. Here's how you can do this for Gradle and Maven:

**Gradle:**

First ensure the Liferay Gradle plugin is applied in your `build.gradle` file:

    apply plugin: "com.liferay.plugin"

Then extend the Liferay extension object to set your Liferay Home and `deploy`
folder:

    liferay {
        liferayHome = "../../../../liferay-ce-portal-7.1.1-ga2"
        deployDir = file("${liferayHome}/deploy")
    }

**Maven:**

Ensure the Bundle Support plugin is applied and configure Liferay Home in your
`pom.xml`. See the
[Deploying a Project Built with Maven to Liferay Portal](/develop/tutorials/-/knowledge_base/7-1/deploying-a-project-built-with-maven-to-product)
for details.

$$$

Blade CLI also offers a way to *watch* a deployed project, which compiles and
redeploys a project when changes are detected. There are two ways to do this:

- `blade watch`
- `blade deploy -w`

The `blade watch` command is the fastest way to develop and test module changes,
because the `watch` command does not rebuild your project every time
a change is detected. When running `blade watch`, your project is not copied to
Portal, but rather, is installed into the runtime as a reference. This means
that the Portal does not make a cached copy of the project. This allows the
Portal to see changes that are made to your project's files immediately. When
you cancel the `watch` task, your module is uninstalled automatically.

+$$$

**Note:** The `blade watch` command is available for Liferay Workspace versions
1.10.9+ (i.e., the `com.liferay.gradle.plugins.workspace` dependency). Maven
projects cannot leverage the `watch` feature at this time.

$$$

The `blade deploy -w` command works similarly to `blade watch`, except it
manually recompiles and deploys your project every time a change is detected.
This causes slower update times, but does preserve your deployed project in
Portal when it's shut down.

Cool! You've successfully deployed your module project using Blade CLI.
