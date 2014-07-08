# Developing Liferay Theme Plugins with Maven [](id=developing-liferay-theme-plugins-with-maven-lp-6-2-develop-tutorial)

Lets use the style of Egar Allen Poe's poem The Raven to help us explain Liferay
theme development with Maven. To recap, since our narrative is a bit different
than Poe's original, so far we have you and your cat, Lenore II, sitting by a
fire on a cold winter evening.

So you're sitting in your armchair next to the fire, as the Maven tutorials
section
[introduction](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/maven-lp-6-2-develop-tutorial)
described. Shadows dance on the tapestry-covered wall, and Lenore II (your cat)
is purring atop the mantle. Yes, you're passing this cold winter's night in
grand style (in front of your computer, of course). Now imagine yourself
sitting on a cold hard metal chair inside an off-white cubicle with empty walls
(you're still in front of your computer, of course). These two descriptions
paint two very different pictures, but both describe what you're doing (sitting
and computing). Changing the "scenery" of your portal sets the mood for your
users.  This tutorial shows you how to develop your own theme plugin (i.e.,
your "scenery") using Maven so your portal has a lasting impression on anyone
who visits. 

## Creating a Theme Plugin

Theme plugin creation with Maven is similar to portlet plugin creation with
Maven. If you don't already have a parent Maven project, please refer to the
[Using Liferay Maven Parent Plugin Projects](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/using-liferay-maven-parent-plugin-projects-lp-6-2-develop-tutorial)
tutorial. That tutorial explains how to create a parent Maven project and its
`pom.xml`. 

To create your Liferay theme plugin project, just follow the
[Creating Liferay Maven Plugins from Liferay IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)
tutorial or the
[Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
tutorial, making sure to select *Theme* instead of *Portlet* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: If you're using Maven's
Archetype command line tool to generate your theme project, you can filter on
group ID `liferay`, or even the group ID/artifact ID combination
`liferay:theme`, to more easily find the Liferay portlet archetypes. 

---

Since it's helpful to familiarize yourself with the Liferay theme plugin
project's anatomy, that topic is covered next. 

## Anatomy

A theme project created from the
`com.liferay.maven.archetypes:liferay-theme-archetype` has the following
directory structure: 

- sample-theme/
    - pom.xml
    - src/
        - main/
            - resources/
                - resources-importer/
                    - document_library/
                    - journal/
                        - articles/
                        - structures/
                        - templates/
                    - readme.txt
                    - sitemap.json
            - webapp/
                - WEB-INF/
                    - liferay-look-and-feel.xml
                    - liferay-plugin-package.properties
                    - web.xml
                - css/ \* Optionally add to hold CSS customizations
                - images/ \* Optionally add to hold custom images
                - js/ \* Optionally add to hold JavaScript customizations
                - templates/ \* Optionally add to hold template customizations

The `src/main/webapp/` folder holds your theme's customizations. This folder is
used the same way that the `docroot/_diffs/` folder is used in themes developed
via the Liferay Plugins SDK. If you're using the Plugins SDK, any of your
theme's files that differ from the corresponding files of your theme's base
theme go in the `docroot/_diffs/` folder. If you're using Maven, any of your
theme's files that differ from the corresponding files of your theme's base
theme go in the `src/main/webapp/` folder. For example, if you've modified
`css/custom.css` in your theme, your `custom.css` file should go in the
`src/main/webapp/css/` folder. 

Here's a table describing the directory structure differences between themes
created using Maven and themes created using the Plugins SDK: 

Location       | Maven project      | Plugins SDK project |
-------------- | ------------------ | ------------------- |
customizations | `src/main/webapp/` | `docroot/_diffs/`   |

<!--
Revisit adding this link after the themes tutorials are published.

To view the directory structure of a theme developed by the Liferay Plugins SDK,
visit the *Anatomy of a Theme Project* section in the [Creating Liferay
Themes](http://www.liferay.com) tutorial.
-->

As with any Maven project's POM, you use the theme project's POM to define the
project's artifacts and specify its dependencies. 

The theme plugin project POM has two additional properties: 

- `liferay.theme.parent`: Sets the parent theme. You can define almost any WAR
  artifact as the parent using the syntax `groupId:artifactId:version`, or use
  the core themes by specifying `_unstyled`, `_styled`, `classic`, or
  `control_panel`. 
- `liferay.theme.type`: Sets the template theme language. 

    The default settings for the two theme properties look like this:
        
        <properties>
            <liferay.theme.parent>_styled</liferay.theme.parent>
            <liferay.theme.type>vm</liferay.theme.type>
        </properties>

To deploy your theme plugin, follow the instructions in the
[Deploying Liferay Plugins with Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/deploying-liferay-plugins-with-maven-lp-6-2-develop-tutorial)
tutorial. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you execute the *package*
goal, a WAR file is created; it's just like the Maven WAR type project.
Simultaneously, the parent theme is downloaded and copied, and your theme's
customizations are overlaid last. A thumbnail image of the theme is created and
placed in the *target* directory. Its path is
`target/[theme]/images/screenshot.png` in your theme project. 

---

You now know how to create a theme plugin using Maven. You're familiar with
its file structure and POM, and you know how to deploy the theme. Way to go!

## Next Steps

<!-- Revisit this link when the themes tutorial is published. Jim

For more information on Liferay themes and their settings, please refer to the
[Creating Themes](http://www.liferay.com) tutorial. 
-->

[Developing Liferay Portlets with Maven](/tutorials/-/knowledge_base/creating-liferay-portlets-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Hook Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Layout Template Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-layout-template-plugins-with-mav-lp-6-2-develop-tutorial)

[Developing Liferay Plugins with Additional Maven Archetypes](/tutorials/-/knowledge_base/developing-liferay-plugins-with-additional-maven-ar-lp-6-2-develop-tutorial)
