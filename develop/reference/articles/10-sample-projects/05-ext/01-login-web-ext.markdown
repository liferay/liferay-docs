# Login Web Ext [](id=login-web-ext)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Login Ext Module sample lets you customize the original Liferay module source code.
This example replaces the default login.jsp file in the com.liferay.login.web bundle
by adding the addtional text `"Hello from com.liferay.login.web.ext module"` to the Sign In form
and replaces the `ForgotPasswordMVCRenderCommand` class to print the text `"In ForgotPasswordMVCRenderCommand render"` to the console.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample demonstrates how to create a ext module and configure it to replace an original module bundle.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

You can create your own ext module project by

- Declaring the original module name and version.
- Providing the source code that will replace the original.

To properly declare the original module in the `build.gradle` file (only supports Gradle), you must specify the
original module's Bundle Symbolic Name and the original module's exact version. In this example, this is configured like this:

	originalModule group: "com.liferay", name: "com.liferay.login.web", version: "2.0.4"

You should put your ext module project in `LiferayWorkspaceDir/ext` by default, or you can specify it in `gradle.properties` by adding:

	liferay.workspace.ext.dir=ext

Then you need to provide your own code intended to replace the original one. **Be sure
to mimic the original module's folder structure when overriding its JAR.**

If you are developing an ext module project in standalone mode(not associated with Liferay workspace),
you also need to declare the ext gradle plugin in your `build.gradle`.

	apply plugin: 'com.liferay.osgi.ext.plugin'

## What Is the Ext module difference with OSGI Fragment module? [](id=what-is-the-difference)

After you deploy your ext project in portal, you will see the original module will be stopped
(`com.liferay.login.web_2.0.4`), and then the new bundle `com.liferay.login.web_2.0.4.ext` will be started,
 which is different from the fragment running the host bundle.

## What kind of type's file could be overlay? [](id=what-kind-of-type)
`Java, Css, Javascript, Language.properties, Scss, Soy` etc

It depends on the [Ext Gradle Plugin](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins/src/main/java/com/liferay/gradle/plugins/LiferayOSGiExtPlugin.java),
 who can help you compile the code into the jar. Such as `.scss`, they'll be compiled into .css files for you which will be included in your module's .jar file artifact. This is done by the buildCSS task.

## Where Is This Sample? [](id=where-is-this-sample)

There are two different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/ext/login-web-ext)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/ext/login-web-ext)