# Liferay Plugin Types to Develop with Maven [](id=liferay-plugin-types-to-develop-with-maven)

You can develop all Liferay plugin types with Maven: portlets, themes,
layout templates, hooks, and Ext. Next, you'll learn how to create each plugin
type using Maven, and we'll point out where each plugin's directory structure is
different than if you created it using the Plugins SDK. We'll often refer to the
previous sections for creating and deploying these plugin types in Maven using
Liferay artifacts.  We'll also reference sections of some other chapters in this
guide, since they're still relevant to Maven developers: they explain how you
develop each type of plugin regardless of development environment. 

Let's start with portlet plugins. 


## Creating a Portlet Plugin [](id=creating-a-portlet-plugin)

To create a Liferay portlet plugin project, follow the *Creating Liferay Plugins
with Maven* section. 

+$$$

**Tip**: As you use Maven's Archetype tool to generate your portlet project, you
can filter on group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to find the Liferay portlet archetypes more easily. 

$$$

### Anatomy [](id=anatomy)

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

The `portlet-plugin/src/main/java/` directory is created automatically when you
create a portlet project with Maven. It holds the portlet's Java source code
(e.g., `com.liferay.sample.SamplePortlet.java`), and
`portlet-plugin/src/main/webapp` holds its web source code. If you've created
any portlet plugins using the Plugins SDK, you might have noted it uses a
different directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the full directory structure of a portlet developed by Ant, visit our
[Anatomy of a Portlet](/develop/tutorials/-/knowledge_base/6-1/anatomy-of-a-portlet)
section in this guide. 

### Deployment [](id=deployment)

To deploy your portlet plugin, follow the instructions detailed above in
*Deploying Liferay Plugins with Maven*. 

Congratulations! You successfully created a Liferay portlet plugin using Maven. 

### More Information [](id=more-information)

For detailed information on creating portlet plugins, see Chapter 3, 
[Portlet Development](/develop/tutorials/-/knowledge_base/6-1/portlet-development). 

Next, let's run through a brief example for developing a theme plugin using
Maven.

## Developing Liferay Theme Plugins with Maven [](id=developing-liferay-theme-plugins-with-maven)

So you're sitting in your armchair next to the fire, just as we described in our
chapter introduction; shadows dance on the tapestry-covered wall, and Lenore II
(your cat) is purring atop the mantle. Yes, you're passing this cold winter's
night in grand style (in front of your computer, of course). Now imagine
yourself sitting on a cold hard wooden chair inside an off-white cubicle with
empty walls (you're still in front of your computer, of course). These two
descriptions paint two very different pictures, but both describe what you're
doing (sitting and computing). Changing the "scenery" of your portal sets the
mood for your users. We'll show you how to develop your own theme plugin (i.e.,
your "scenery") using Maven so your portal has a lasting impression on anyone
who visits. 

### Creating a Theme Plugin [](id=creating-a-theme-plugin)

Theme plugin creation is similar to portlet plugin creation. We'll start by
assuming you already created the `sample-parent-project` and its `pom.xml`. 

To create your Liferay theme plugin project follow the *Creating Liferay Plugins
with Maven* section, making sure to select *Theme* as the plugin type. 

+$$$

**Tip**: As you use Maven's Archetype tool to generate your theme project, you
can filter on group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to more easily find the Liferay portlet archetypes. 

$$$

### Anatomy [](id=anatomy-0)

A theme project created from the
`com.liferay.maven.archetypes:liferay-theme-archetype` has the following
directory structure: 

- sample-theme/
    - pom.xml
    - src/
        - main/
            - resources/
            - webapp/
                - WEB-INF/
                    - liferay-plugin-package.properties
                    - web.xml
            - css/
            - images/
            - js/
            - templates/

Several of the directories listed in the structure above are not created
automatically; you'll create them as needed, depending on the customizations
you're making. Here's a list of these directories, with a brief description of
each: 

- `src/main/css/`: Holds your CSS customizations.
- `src/main/images/`: Holds custom images for your theme.
- `src/main/js/`: Holds your JavaScript customizations.
- `src/main/templates/`: Holds your template customizations. 

The `src/main/webapp/` folder contains your theme's customizations. If you've
ever created a theme plugin using the Plugins SDK, this folder is used the same
way as the `docroot/_diffs/` folder. For example, `custom.css` should go in
`src/main/webapp/css/custom.css`. 

Here's a table describing the directory structure differences between themes
created using Maven and themes created using the Plugins SDK: 

Location       | Maven project     | Plugins SDK project |
-------------- | ----------------- | ------------------- |
customizations | `src/main/webapp` | `docroot/_diffs`    |

To view the directory structure of a theme developed by Ant, visit the [Anatomy
of a
Theme](/develop/tutorials/-/knowledge_base/6-1/anatomy-of-a-theme)
section in this guide. 

### Theme POM [](id=theme-pom)

The theme plugin project POM has two additional properties: 

-   `liferay.theme.parent`: Sets the parent theme. You can define almost any WAR
    artifact as the parent using the syntax `groupId:artifactId:version`, or use
    the core themes by specifying `_unstyled`, `_styled`, `classic`, or
    `control_panel`. 
-   `liferay.theme.type`: Sets the template theme language. 

    The default settings for the two theme properties look like this:
        
        <properties>
            <liferay.theme.parent>_styled</liferay.theme.parent>
            <liferay.theme.type>vm</liferay.theme.type>
        </properties>

### Deployment [](id=deployment-0)

To deploy your theme plugin, follow the instructions in the *Deploying Liferay
Plugins with Maven* section. 

+$$$

**Note:** When you execute the *package* goal, a WAR file is created; it's just
like the Maven WAR type project. Simultaneously, the parent theme is downloaded
and copied, and your theme's customizations are overlaid last. A thumbnail image
of the theme is created and placed in the *target* directory. Its path is
`target/<theme>/images/screenshot.png` in your theme project. 

$$$

### More Information [](id=more-information-0)

For more information on Liferay themes and its settings, see Chapter 5,
[Creating Liferay
Themes](/develop/tutorials/-/knowledge_base/6-1/creating-a-theme).

You successfully developed a Liferay theme using Maven. Find out about
developing hook plugins next. 

## Developing Liferay Hook Plugins with Maven [](id=developing-liferay-hook-plugins-with-maven)

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook is almost identical to portlet plugin creation in Maven. Let's
take a look. 

### Creating a Hook Plugin [](id=creating-a-hook-plugin)

To create a Liferay hook plugin project, follow the steps outlined in the
*Creating Liferay Plugins with Maven* section, making sure to select *Hook* as
the plugin type. 

+$$$

**Tip**: As you use Maven's Archetype tool to generate your hook you can filter
on group ID `liferay`, or even the group ID/artifact ID combination
`liferay:portlet`, to more easily find the Liferay portlet archetypes. 

$$$

### Anatomy [](id=anatomy-1)

A hook project created from the
`com.liferay.maven.archetypes:liferay-hook-archetype` has the following
directory structure: 

- hook-plugin/
    - src/
        - main/
            - java/ \* Optionally add to hold Java source
            - webapp/
                - WEB-INF/
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

The `hook-plugin/src/main/java/` directory holds the hook's Java source code
(e.g., `com.liferay.sample.SampleHook.java`), and `hook-plugin/src/main/webapp`
holds its web source code. If you're familiar with creating hook plugins using
the Plugins SDK, you probably noticed that Maven uses a different plugin
directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the directory structure of a hook developed by Ant, visit the *Anatomy
of a Hook* section of the [Creating a
Hook](/develop/tutorials/-/knowledge_base/6-1/anatomy-of-the-hook)
section in this guide. 

### Deployment [](id=deployment-1)

To deploy your hook plugin, follow the instructions from the *Deploying Liferay
Plugins with Maven* section. 

### More Information [](id=more-information-1)

For detailed information on creating hooks, see Chapter 7,
[Hooks](/develop/tutorials/-/knowledge_base/6-1/creating-a-hook). 

You're nearly a Maven expert now; you're able to create portlets, themes, and
hooks. Let's round things out by learning to develop layout templates. 

## Developing Liferay Layout Template Plugins with Maven [](id=developing-liferay-layout-template-plugins-with-maven)

You can create layout templates to customize the display of portlets on your
page and to embed commonly used portlets. In our introduction to themes, we
described a nice scene where you're relaxing in a luxurious chair, computer in
your lap, Lenore II (your cat) purring on the mantle above a dancing fire.
Sounds nice, doesn't it? It would be, but the chair's too small, so your knees
are up in the air when your feet are flat on the ground, and your laptop is
balanced precariously on top of them. The fire is also surprisingly large for
that fireplace. In fact, its flames are already licking at the bottom of the
mantle--which is made of wood! Remember Lenore II, softly purring on the mantle?
She's going to cook just like the original Lenore if we don't do something!
But it's so hard to get out of this tiny chair. Someone save Lenore II! 

"Tell this soul with sorrow laden if, within the distant Aidenn,
It shall clasp a sainted kitten whom the angels named Lenore II--"

In memory of the late, now cripsy Lenore II, let's create a layout template
plugin with Maven. 

### Creating a Layout Template Plugin [](id=creating-a-layout-template-plugin)

To create a Liferay layout template plugin project follow the *Creating Liferay
Plugins with Maven* section, making sure to select *Layout Template* as the
plugin type.

+$$$

**Tip**: As you use Maven's Archetype tool to generate your layout template
project, you can filter on group ID `liferay`, or even group ID / artifact ID
combination `liferay:layout`, to find the Liferay layout template archetypes.

$$$

### Anatomy [](id=anatomy-2)

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

To view the directory structure of a layout template developed by Ant, visit the
[Anatomy of a Layout Template
Project](/develop/tutorials/-/knowledge_base/6-1/anatomy-of-a-layout-template-project)
section in this guide. 

### Deployment [](id=deployment-2)

To deploy your layout template plugin, follow the instructions detailed above in
the *Deploying Liferay Plugins with Maven* section. 

### More Information [](id=more-information-2)

For detailed information on creating layout templates, see Chapter 6, [Creating
Liferay Layout
Templates](/develop/tutorials/-/knowledge_base/6-1/creating-liferay-layout-templates). 

You've passed your trial by fire (the cat thanks you), developing yet another
plugin type with Maven. Way to go! In the next section we'll cover other
Liferay-provided Maven archetypes. 

## Developing More Liferay Plugins with Maven [](id=developing-more-liferay-plugins-with-maven)

Did you think we covered all the available archetypes for developing Liferay
plugins? The Liferay team has been busy expanding our archetype list, and we're
proud to show you some additional plugins that you can create using Maven
archetypes. 

Check out these exciting archetypes that are now available: 

- Liferay ServiceBuilder portlets 
- Liferay webs 
- Liferay Ext 
- JSF Portlet Archetype 
- ICEFaces Portlet Archetype 
- PrimeFaces Portlet Archetype 
- Liferay Faces Alloy Portlet Archetype 
- Liferay Rich Faces Portlet Archetype 

In addition, there are two new Maven *goals* now available: 

- DBBuilder - The `build-db` goal lets you execute the DBBuilder to generate SQL
  files. 
- SassToCSSBuilder - The `build-css` goal precompiles SASS in your css; this
  goal has been added to theme archetype. 

You now have plenty of archetypes at your disposal! 
