# Developing Liferay Faces Portlets with Maven

If you're unfamiliar with the Liferay Faces project, it's important to know that
Liferay Faces is an umbrella project that provides support for the JavaServer
Faces (JSF) standard within Liferay Portal. Thus, Liferay Faces portlets and JSF
portlets are very closely related with each other. Visit the [JSF-related
learning
path](/develop/learning-paths/-/knowledge_base/writing-your-first-jsf-application)
for further details on JSF portlets and the Liferay Faces project. 

This tutorial explains how to create Liferay Faces portlets using Maven and the
anatomy of a JSF portlet. 

<!-- Make sure the JSF learning path link above is updated once it has been
created. -Cody -->

Liferay Faces portlets can be built as a number of different portlets related to
the JSF technology. When developing Liferay Faces portlets with Maven, you have
a few different JSF frameworks you can choose to build your portlet with, which
include: [*RichFaces*](http://richfaces.jboss.org/),
[*PrimeFaces*](http://primefaces.org/), [*Liferay Faces
Alloy*](https://www.liferay.com/community/liferay-projects/liferay-faces/alloy),
[*JSF standard*](http://en.wikipedia.org/wiki/JavaServer_Faces), and
[*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf)
portlets. 

You'll begin creating a Liferay Faces portlet plugin next. 

## Creating a Liferay Faces Portlet Plugin

To create your Liferay Faces portlet plugin project, just follow the [Creating
Liferay Maven Plugins from Liferay
IDE](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide)
tutorial or the [Creating Liferay Maven Plugins from the Command
Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin)
tutorial, making sure to select *Portlet* as the plugin type, and *JSF 2.x* as
the portlet framework. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: If you're using Maven's
Archetype command line tool to generate your portlet project, you can filter on
the group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to find the Liferay portlet archetypes more easily. 

---

It's very helpful to familiarize yourself with a Liferay Faces's portlet plugin
project's directory structure, so you'll explore its anatomy next. 

## Anatomy

A portlet project created from the
`com.liferay.maven.archetypes:liferay-portlet-jsf-archetype` has the following
directory structure: 

- jsf-portlet-plugin/
    - src/
        - main/
            - java/
            - resources/
                - Language_[locale].properties
                - log4j.properties
            - webapp/
                - css/
                    - main.css
                - views/
                    - view.xhtml
                - WEB-INF/
                    - faces-config.xml
                    - liferay-display.xml
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - liferay-portlet.xml
                    - portlet.xml
                    - web.xml
                    - weblogic.xml
                - icon.png
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

Congratulations! You now have the necessary knowledge to create Liferay Faces
portlets using several different JSF frameworks. 

## Related Topics

[Developing Liferay Plugins with Additional Maven Archetypes](/develop/tutorials/-/knowledge_base/developing-liferay-plugins-with-additional-maven-ar)

[Deploying Liferay Plugins with Maven](/develop/tutorials/-/knowledge_base/deploying-liferay-plugins-with-maven)

[Liferay Faces](/develop/tutorials/-/knowledge_base/developing-jsp-portlets-using-liferay-mvc)

