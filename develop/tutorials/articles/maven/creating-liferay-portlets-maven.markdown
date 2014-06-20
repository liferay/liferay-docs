# Creating Liferay Portlets with Maven [](id=creating-liferay-portlets-with-maven-lp-6-2-develop-tutorial)

Let's discuss Liferay Maven portlet plugin projects. To learn how to create a
Liferay portlet plugin project using Liferay IDE, please refer to the
[Creating Liferay Maven Plugins from Liferay IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)
tutorial. For information on creating a Liferay portlet plugin project without
Liferay IDE, please refer to the
[Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
tutorial.

---

![tip](../../images/tip-pen-paper.png) **Tip**: If you're using Maven's
Archetype command line tool to generate your portlet project, you can filter on
the group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to find the Liferay portlet archetypes more easily. 

---

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

To deploy your plugin, follow the instructions detailed in the following
tutorial:

[Deploying Liferay Plugins with Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/deploying-liferay-plugins-with-maven-lp-6-2-develop-tutorial)

To learn how to develop another type of Liferay plugin using Maven, read one of
the following applicable tutorials: 

[Developing Liferay Hook Plugins with Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Theme Plugins with Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/developing-liferay-theme-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Layout Template Plugins with Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/developing-liferay-layout-template-plugins-with-mav-lp-6-2-develop-tutorial)

[Developing Liferay Plugins with Additional Maven Archetypes](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/developing-liferay-plugins-with-additional-maven-ar-lp-6-2-develop-tutorial)

