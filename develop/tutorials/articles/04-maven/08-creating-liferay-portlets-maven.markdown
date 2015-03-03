# Developing Liferay Portlets with Maven [](id=creating-liferay-portlets-with-maven)

This tutorial explains how to create Liferay portlet plugins using Maven and the
anatomy of the Liferay Maven portlet plugin project. 

## Creating a Portlet Plugin [](id=creating-a-portlet-plugin)

To create your Liferay portlet plugin project, just follow the
[Creating Liferay Maven Plugins from Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide)
tutorial or the
[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-the-command-lin)
tutorial, making sure to select *Portlet* as the plugin type. 

+$$$

**Tip**: If you're using Maven's
Archetype command line tool to generate your portlet project, you can filter on
the group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to find the Liferay portlet archetypes more easily. 

$$$

Since it's helpful to familiarize yourself with the Liferay portlet plugin
project's anatomy, that topic is covered next. 

## Anatomy [](id=anatomy)

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
---

You now know how to create Liferay portlet plugins in Maven. 

## Related Topics [](id=related-topics)

[Deploying Liferay Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/deploying-liferay-plugins-with-maven)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[Liferay Faces](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-jsf-portlets)
