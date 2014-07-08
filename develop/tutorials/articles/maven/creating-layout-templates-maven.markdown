# Developing Liferay Layout Template Plugins with Maven [](id=developing-liferay-layout-template-plugins-with-mav-lp-6-2-develop-tutorial)

You can create layout templates to customize the display of portlets on your
page and to embed commonly used portlets. The introduction to the themes
tutorial described a nice scene where you're relaxing in a luxurious chair,
computer in your lap, Lenore II (your cat) purring on the mantle above a dancing
fire. Sounds nice, doesn't it? It would be, but the chair's too small, so your
knees are up in the air when your feet are flat on the ground, and your laptop
is balanced precariously on top of them. The fire is also surprisingly large for
that fireplace. In fact, its flames are already licking at the bottom of the
mantle--which is made of wood! Remember Lenore II, softly purring on the mantle?
She's going to cook just like the original Lenore if you don't do something! But
it's so hard to get out of this tiny chair. Someone save Lenore II! 

In the words of Edgar Allen Poe (adapted from The Raven): "Tell this soul with
sorrow laden if, within the distant Aidenn, It shall clasp a sainted kitten whom
the angels named Lenore II--"

In memory of the late, now crispy Lenore II, it's time to create a layout
template plugin with Maven.

## Creating a Layout Template Plugin

To create a Liferay layout template plugin project, follow the
[Creating Liferay Maven Plugins from Liferay IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)
tutorial or the 
[Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
tutorial, making sure to select *Layout Template* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: If you're using Maven's
Archetype command line tool to generate your layout template project, you can
filter on group ID `liferay`, or even group ID / artifact ID combination
`liferay:layout`, to find the Liferay layout template archetypes.

---

Since it's helpful to familiarize yourself with the Liferay layout template
plugin project's anatomy, that topic is covered next. 

## Anatomy

A layout template project created from the
`com.liferay.maven.archetypes:liferay-layouttpl-archetype` has the following
directory structure:

- layouttpl-plugin/
    - src/
        - main/
            - resources/
            - webapp/
                - WEB-INF/
                    - liferay-layout-templates.xml
                    - liferay-plugin-package.properties
                    - web.xml
                - sample-layout.png
                - sample-layout.tpl
                - sample-layout.wap.tpl
    - pom.xml

There's a directory structure difference between plugin projects created using
Liferay Maven archetypes and those created using the Liferay Plugins SDK. The
following table illustrates this difference: 

Location   | Maven project     | Plugins SDK project |
---------- | ----------------- | ------------------- |
Web source | `src/main/webapp` | `docroot` |

You've passed your trial by fire (your new cat, Lenore III, thanks you),
developing yet another plugin type with Maven. Way to go!

## Next Steps

<!-- Revisit this after the content is available. Jim

[Anatomy of a Layout Template Project](http://www.liferay.com) 

[Creating Liferay Themes and Layout Templates](http://www.liferay.com)
-->

[Deploying Liferay Plugins with Maven](/tutorials/-/knowledge_base/deploying-liferay-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Portlets with Maven](/tutorials/-/knowledge_base/creating-liferay-portlets-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Hook Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Theme Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-theme-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Plugins with Additional Maven Archetypes](/tutorials/-/knowledge_base/developing-liferay-plugins-with-additional-maven-ar-lp-6-2-develop-tutorial)

