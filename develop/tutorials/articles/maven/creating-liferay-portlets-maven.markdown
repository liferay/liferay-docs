# Developing Liferay Portlets with Maven [](id=creating-liferay-portlets-with-maven-lp-6-2-develop-tutorial)

This tutorial explains how to create Liferay portlet plugins using Maven and the
anatomy of the Liferay Maven portlet plugin project. 

## Creating a Portlet Plugin

To create your Liferay theme plugin project, just follow the
[Creating Liferay Maven Plugins from Liferay IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)
tutorial or the
[Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
tutorial, making sure to select *Portlet* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: If you're using Maven's
Archetype command line tool to generate your portlet project, you can filter on
the group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to find the Liferay portlet archetypes more easily. 

---

Since it's helpful to familiarize yourself with the Liferay portlet plugin
project's anatomy, that topic is covered next. 

## Anatomy

A portlet project created from the
`com.liferay.maven.archetypes:liferay-portlet-archetype` has the following
directory structure: 

- portlet-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - css/
                    - main.css
                - js/
                    - main.js
                - WEB-INF/
                    - liferay-display.xml
                    - liferay-plugin-package.properties
                    - liferay-portlet.xml
                    - portlet.xml
                    - web.xml
                - icon.png
                - view.jsp
    - pom.xml

Maven creates the `src/main/java/` directory automatically. It holds the
portlet's Java source code (e.g., `com.liferay.sample.SamplePortlet.java`), and
`portlet-plugin/src/main/webapp` holds its web source code. If you've created
any portlet plugins using the Plugins SDK, you might have noted it uses a
different directory structure. 

The following table illustrates the differences in the locations of the Java
source code and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

You now know how to create Liferay portlet plugins in Maven. 

## Next Steps

<!-- Add this back when a tutorial is available that explains the anatomy. - Jim

To view the full directory structure of a portlet developed via the Liferay
Plugins SDK, visit our [Anatomy of a Portlet](www.liferay.com) tutorial.

For detailed information on creating portlet plugins, see 
[Developing Portlet Applications](http://www.liferay.com).
-->

[Deploying Liferay Plugins with Maven](/tutorials/-/knowledge_base/deploying-liferay-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Hook Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Theme Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-theme-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Layout Template Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-layout-template-plugins-with-mav-lp-6-2-develop-tutorial)

[Developing Liferay Plugins with Additional Maven Archetypes](/tutorials/-/knowledge_base/developing-liferay-plugins-with-additional-maven-ar-lp-6-2-develop-tutorial)

