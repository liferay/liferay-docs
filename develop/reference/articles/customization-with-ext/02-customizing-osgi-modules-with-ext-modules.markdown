# Customizing OSGi Modules with Ext Modules

An Ext module is a powerful tool for extending @product@'s OSGi modules. For
example, if you want to overwrite a default module's JSP to display a different
view, you can create an Ext module to customize the original module's JSP (for
example, see the
[Login Web Ext sample](/develop/reference/-/knowledge_base/7-1/login-web-ext)).
Because this increases the complexity of your @product@ installation, you should
only use an Ext module if you're sure you can't accomplish your goal in a
different way (e.g., leveraging an extension point).

The following app servers should be used for Ext module development in
@product@:

- Tomcat 9.0

In this tutorial, you'll learn how to

- [Create an Ext module](#creating-an-ext-module)
- [Develop an Ext module](#developing-an-ext-module)
- [Deploy an Ext module](#deploying-an-ext-module)

You'll start by creating an Ext plugin.

## Creating an Ext Module [](id=creating-an-ext-module)

You can create an Ext module based off the pre-configured `modules-ext` project
template/archetype. See the
[modules-ext](/develop/reference/-/knowledge_base/7-1/modules-ext-template)
project template article for information on how to create an Ext module, its
folder structure, and other important details.

It's recommended to create and develop your Ext module in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
Workspace is preconfigured with an `ext` folder, which applies important
settings (via the `LiferayOSGiExtPlugin`) to your Ext module when it's deployed
to @product@. You'll learn more about this in the next section.

Great! You know how to create an Ext module and are familiar with its folder
structure and most significant files. Next, you'll learn how to develop your Ext
module to customize @product@. 

## Developing an Ext Module

You can create your own Ext module project by

- Declaring the original module name and version.
- Providing the source code that will replace the original.

To declare the original module in the `build.gradle` file properly (only
supports Gradle), you must specify the original module's Bundle Symbolic Name
and the original module's exact version. For example, overriding the
`com.liferay.login.web` module would be configured like this:

    originalModule group: "com.liferay", name: "com.liferay.login.web", version: "3.0.4"

If you're leveraging
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
you should put your Ext module project in the `/ext` folder (default); you can
specify a different Ext folder name in workspace's `gradle.properties` by adding

    liferay.workspace.ext.dir=EXT_DIR

If you are developing an Ext module project in standalone mode (not associated
with Liferay Workspace), you must declare the Ext Gradle plugin in your
`build.gradle`:

    apply plugin: 'com.liferay.osgi.ext.plugin'

Then you must provide your own code intended to replace the original one. **Be
sure to mimic the original module's folder structure when overriding its JAR.**

The following file types can be overlaid with an Ext module:

- CSS
- Java
- JavaScript
- Language files (`Language.properties`)
- Scss
- Soy
- etc.

The
[Ext Gradle Plugin](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins/src/main/java/com/liferay/gradle/plugins/LiferayOSGiExtPlugin.java)
helps compile your code into the JAR. For example, `.scss` files are compiled
into `.css` files, which are included in your module's JAR file artifact. This
is done by the `buildCSS` task.

Once you're finished developing your Ext module, you must deploy it. Continue on
to learn how.

## Deploying an Ext Module

Before deploying your Ext module, you must stop the original bundle you intend
to override. This is because an Ext module's generated JAR includes the original
bundle source plus your modified source files. Follow the instructions below to
do this:

1.  Connect to your portal instance using
    [Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).

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

You're all set to create, develop, and deploy Ext modules!
