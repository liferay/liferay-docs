# Deploying Projects with Blade CLI [](id=deploying-projects-with-blade-cli)

Deploying projects to a Liferay server using Blade CLI is easy. To use
the Blade `deploy` command, you must first have built a project to deploy. See
the
[Creating Projects with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli)
tutorials for more information about creating Liferay projects. Once you've
built a project, navigate to it in your terminal window and execute the following
command to deploy it:

    blade deploy

This can be used for WAR-style projects and modules (JARs). You can also deploy
all projects in a folder by running the `deploy` command from the parent folder
(e.g., `[WORKSPACE_ROOT]/modules`).

When deploying a project using Blade CLI, the project is directly installed into
the OSGi container. This means, for example, that a deployed module isn't stored
in the `LIFERAY_HOME/osgi/modules` folder; only modules copied to the
`LIFERAY_HOME/deploy` folder (i.e., leveraging the auto deployment mechanism)
are stored there. A module deployed by Blade CLI is stored only as bytecode in
`LIFERAY_HOME/osgi/state/org.eclipse.osgi` in a subfolder named after its bundle
ID. All modules installed in OSGi's registry are stored this way, even those
copied to the `/deploy` folder. Visit the 
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
article for instructions on finding a bundle's ID.

Blade CLI can detect a locally running Liferay instance and automatically
deploys your project to that Liferay instance. Blade communicates with the OSGi
framework using Felix Gogo shell and deploys the project directly to the OSGi
container using Felix File Install commands. The command uses the default
`11311` port by default.

+$$$

**Note:** The `blade deploy` command requires a Gradle/Maven wrapper to
successfully execute. To ensure the availability of a build tool wrapper, work
in a Liferay Workspace. For more information on Liferay Workspaces, see the
[Creating a Liferay Workspace with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-blade-cli)
tutorial.

$$$

Blade CLI also offers a way to *watch* a deployed project, which compiles and
redeploys a project when changes are detected. There are two ways to do this:

- `blade watch`
- `blade deploy -w`

The `blade watch` command is the fastest way for Portal to recognize your
changes. This is because the `watch` command does not rebuild your project every
time a change is detected. When running `blade watch`, your project is not
physically deployed to Portal, but rather, is recreated temporarily as an
exploded JAR and mapped to Portal. This allows the Portal to quickly update your
project's installation by recognizing changes and only updating the recently
changed files. When the Portal is shut down, your project is automatically
uninstalled. The `watch` command is only available for JAR-based projects.

+$$$

**Note:** The `blade watch` command is available for Liferay Workspace versions
1.10.9+ (i.e., the `com.liferay.gradle.plugins.workspace` dependency). Maven
projects cannot leverage the `watch` feature at this time.

$$$

The `blade deploy -w` command works similarly to `blade watch`, except it
manually deploys your project to the portal instance and is recompiled and
deployed every time a change is detected. This causes slower update times, but
does preserve your deployed project in Portal when it's shut down.

Cool! You've successfully deployed your module project using Blade CLI.
