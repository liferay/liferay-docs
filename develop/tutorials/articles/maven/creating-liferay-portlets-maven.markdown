# Creating Liferay Portlets with Maven [](id=creating-liferay-portlets-with-maven-lp-6-2-develop-tutorial)

Let's discuss Liferay Maven portlet plugin projects. To learn how to create a
Liferay portlet plugin project using Liferay IDE, please refer to the [Creating Liferay Maven Plugins from Liferay IDE](http://www.liferay.com)
tutorial. For information on creating a Liferay portlet plugin project without
Liferay IDE, please refer to the [Creating Liferay Maven Plugins from the Command Line](http://www.liferay.com)
tutorial.

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your portlet project, you can filter on the group ID `liferay`,
or even the group ID/artifact ID combination `liferay:portlet`, to find the
Liferay portlet archetypes more easily. 

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

To view the full directory structure of a portlet developed via the Liferay
Plugins SDK, visit our [Anatomy of a Portlet](www.liferay.com) tutorial. 

## Deployment

To deploy your portlet plugin, follow the instructions detailed in the 
[Deploying Liferay Plugins with Maven](http://www.liferay.com) tutorial. 

## More Information

For detailed information on creating portlet plugins, see 
[Developing Portlet Applications](http://www.liferay.com). 

## Next Steps

To learn how to develop a theme plugin using Maven, please refer to the
following tutorial:

[Creating Theme Plugins with Maven](http://www.liferay.com)
