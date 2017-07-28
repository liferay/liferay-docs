# Gradle [](id=gradle)

Liferay provides plugins that you can apply to your Gradle project. This
reference documentation describes how to apply and use Liferay's Gradle plugins.

**Important:** If you're using
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
to create Liferay apps, many Liferay Gradle plugins are already applied by
default. The `com.liferay.workspace` plugin provides the following plugins to
all your apps in a Liferay Workspace:

- `com.liferay.css.builder`
- `com.liferay.js.module.config.generator`
- `com.liferay.js.transpiler`
- `com.liferay.javadoc.formatter`
- `com.liferay.jspc`
- `com.liferay.lang.builder`
- `com.liferay.source.formatter`
- `com.liferay.soy`
- `com.liferay.soy.translation`
- `com.liferay.tlddoc.builder`
- `com.liferay.tld.formatter`
- `com.liferay.test.integration`
- `com.liferay.xml.formatter`

Do not apply a Liferay Gradle plugin to an app that already has access to it.

Each article in this section describes how to apply the plugin, what Gradle
tasks the plugin provides, the plugin's configuration properties, and the
plugin's dependencies. 
