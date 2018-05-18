# Gradle [](id=gradle)

Liferay provides plugins that you can apply to your Gradle project. This
reference documentation describes how to apply and use Liferay's Gradle plugins.

**Important:** If you're using
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
to create Liferay apps, most of the Liferay Gradle plugins covered in this
section are already applied by default. The
[com.liferay.gradle.plugins.workspace](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-workspace)
and
[com.liferay.gradle.plugins](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins)
dependencies provide them, both of which are preset in workspace by default.

Do not apply a Liferay Gradle plugin to an app that already has access to it.

Each article in this section describes how to apply the plugin, what Gradle
tasks the plugin provides, the plugin's configuration properties, and the
plugin's dependencies. 
