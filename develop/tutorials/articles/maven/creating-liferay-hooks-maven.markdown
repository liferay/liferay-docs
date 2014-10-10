# Developing Liferay Hook Plugins with Maven [](id=developing-liferay-hook-plugins-with-maven)

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook plugin with Maven is very similar to creating a portlet plugin
with Maven. This tutorial explains how to create a hook plugin and explains
the Liferay Maven hook plugin project's anatomy. 

## Creating a Hook Plugin

To create a Liferay hook plugin project, follow the steps outlined in the
[Creating Liferay Maven Plugins from Liferay IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide)
tutorial or the
[Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-the-command-lin)
tutorial, making sure to select *Hook* as the plugin type. 

+$$$

**Tip**: If you're using Maven's
Archetype command line tool to generate your hook you can filter on group ID
`liferay`, or even the group ID/artifact ID combination `liferay:hook`, to more
easily find the Liferay portlet archetypes. 

$$$

Since it's helpful to familiarize yourself with the Liferay hook plugin
project's anatomy, that topic is covered next. 

## Anatomy

A hook project created from the
`com.liferay.maven.archetypes:liferay-hook-archetype` has the following
directory structure: 

- hook-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - WEB-INF/
                    - lib/  \* Optionally add to hold required libraries
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

The `hook-plugin/src/main/java/` directory holds the hook's Java source code
(e.g., `com.liferay.sample.SampleHook.java`) and `hook-plugin/src/main/webapp`
holds the hook's web source code. If you're familiar with creating hook plugins
using the Plugins SDK, you've probably noticed that Maven uses a different
plugin directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |
---

You now know how to create Liferay hook plugins to customize Liferay. 

## Related Topics

[Deploying Liferay Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/deploying-liferay-plugins-with-maven)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

