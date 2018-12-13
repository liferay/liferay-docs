# Deploying Modules with Blade CLI [](id=deploying-modules-with-blade-cli)

Deploying modules to a Liferay server using Blade CLI is easy. To use the Blade
`deploy` command, you must first have built a module to deploy. See the
[Creating Projects with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)
tutorials for more information about creating Liferay projects. Once you've
built a module, navigate to it in your terminal window and execute the following
command to deploy it to @product@:

    blade deploy

You can also deploy all modules in a folder by running the `deploy` command from
the parent folder (e.g., `[WORKSPACE_ROOT]/modules`).

When deploying a module using Blade CLI, the module is directly installed into
@product@'s OSGi container. This means that the module isn't stored in the
`LIFERAY_HOME/osgi/modules` folder; only modules copied to the
`LIFERAY_HOME/deploy` folder (i.e., leveraging the auto deployment mechanism)
are stored there. A module deployed by Blade CLI is stored only as bytecode in
`LIFERAY_HOME/osgi/state/org.eclipse.osgi` in a subfolder named after its bundle
ID. All modules installed in OSGi's registry are stored this way, even those
copied to the `/deploy` folder. Visit the [Using the Felix Gogo
Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
article for instructions on finding a bundle's ID.

The `deploy` command is intended for modules that are built into a JAR file. If
you've created a WAR style project (e.g., projects based on the Blade templates
`spring-mvc-portlet`, `theme`, etc.), you'll need to deploy it using a different
tool like Gradle (e.g., `./gradlew deploy`).

Blade CLI can detect a locally running Liferay instance and automatically
deploys your module to that Liferay instance. Blade communicates with
@product@'s OSGi framework using Felix Gogo shell and deploys the module
directly to the OSGi container using Felix File Install commands. The command
uses the default `11311` port by default.

<!--
You can also specify a custom port to deploy your module to using the `-p`
parameter followed by the port number. For instance, you could run `blade deploy
-p 8090` to deploy to port 8090.
-->

You can also watch the deployed module for changes by specifying the `-w`
parameter.

    blade deploy -w

This parameter automatically redeploys the module when changes are detected.

+$$$

**Note:** The `blade deploy` command requires a Gradle/Maven wrapper to
successfully execute. To ensure the availability of a build tool wrapper, be
sure to work in a Liferay Workspace. For more information on Liferay Workspaces,
see the
[Creating a Liferay Workspace with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-blade-cli)
tutorial.

$$$

Cool! You've successfully deployed your module project using Blade CLI.
