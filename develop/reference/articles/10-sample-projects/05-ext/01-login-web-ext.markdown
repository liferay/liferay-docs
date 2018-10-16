# Login Web Ext [](id=login-web-ext)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Login Ext Module sample demonstrates how to customize a default Liferay
module's source code. This example replaces the default `login.jsp` file in the
`com.liferay.login.web` bundle by adding the text *Hello from
com.liferay.login.web.ext module! 2 + 2 = 4* to the Sign In form.

![Figure 1: The Login Ext module customizes the original Login module.](../../../images/login-ext.png)

It also prints the following text to the console when you select *Forgot
Password* from the Sign In form:

    In com.liferay.login.web.internal.portlet.action.ForgotPasswordMVCRenderCommand render

Before deploying the sample, you must stop the original bundle you intend to
override. This is because the Ext sample's generated JAR includes the original
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
Ext module's JAR to the `deploy` folder manually, or leverage Liferay Dev
Studio's [drag-and-drop deployment](/develop/tutorials/-/knowledge_base/7-1/deploying-projects-with-liferay-ide)
feature.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample demonstrates how to create an Ext module and configure it to replace
a default module bundle.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

You can create your own Ext module project by

- Declaring the original module name and version.
- Providing the source code that will replace the original.

To declare the original module in the `build.gradle` file properly (only
supports Gradle), you must specify the original module's Bundle Symbolic Name
and the original module's exact version. In this example, this is configured
like this:

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

## Where Is This Sample? [](id=where-is-this-sample)

There are two different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/ext/login-web-ext)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/ext/login-web-ext)
