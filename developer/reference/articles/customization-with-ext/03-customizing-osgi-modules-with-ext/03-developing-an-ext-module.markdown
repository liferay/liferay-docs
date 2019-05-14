---
header-id: developing-an-ext-module
---

# Developing an Ext Module

You can create your own Ext module project by

- Declaring the original module name and version.
- Providing the source code that will replace the original.

To declare the original module in the `build.gradle` file properly (only
supports Gradle), you must specify the original module's Bundle Symbolic Name
and the original module's exact version. For example, overriding the
`com.liferay.login.web` module would be configured like this:

    originalModule group: "com.liferay", name: "com.liferay.login.web", version: "3.0.4"

If you're leveraging
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace),
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
