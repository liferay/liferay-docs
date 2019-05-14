# Deploying an Ext Module [](id=deploying-an-ext-module)

Before deploying your Ext module, you must stop the original bundle you intend
to override. This is because an Ext module's generated JAR includes the original
bundle source plus your modified source files.

+$$$

**Note:** Stopping the original bundle before deploying your Ext module is only
necessary if you've already started @product@.

$$$

Follow the instructions below to deploy your Ext module to a @product@ instance:

1.  Connect to your portal instance using
    [Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell).

2.  Search for the bundle ID of the original bundle to override. To find the
    `com.liferay.login.web` bundle, execute this command:

        lb -s | grep com.liferay.login.web

    This returns output similar to this:

        423|Active   |   10|com.liferay.login.web (3.0.4)

    Make note of the ID (e.g., `423`).

3.  Stop the bundle:

        stop 423

Once the original bundle is stopped, deploy the Ext module. Note that you cannot
leverage Blade or Gradle's `deploy` command to do this. The `deploy` command
deploys the module to the `osgi\marketplace\override` folder by default, which
does not configure Ext modules properly for usage. You should build and copy the
Ext module's JAR to the `deploy` folder manually.

You're all set to deploy Ext modules!
