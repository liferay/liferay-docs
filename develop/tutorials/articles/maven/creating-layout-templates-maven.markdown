#### Developing Liferay Layout Template Plugins with Maven [](id=develop-layout-templates-maven-liferay-portal-6-2-dev-guide-02-en)

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

In memory of the late, now crispy Lenore II, let's create a layout template
plugin with Maven. 

##### Creating a Layout Template Plugin [](id=creating-layout-template-plugin-maven-liferay-portal-6-2-dev-guide-en)

To create a Liferay layout template plugin project follow the *Creating Liferay
Plugins with Maven* section, making sure to select *Layout Template* as the
plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your layout template project, you can filter on group ID
`liferay`, or even group ID / artifact ID combination `liferay:layout`, to find
the Liferay layout template archetypes.

---

##### Anatomy [](id=layout-template-plugin-archetype-anatomy-liferay-portal-6-2-dev-guide-en)

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
Project](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-liferay-layout-templates-liferay-portal-6-2-dev-guide-09-en)
section in this guide. 

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en-2)

To deploy your layout template plugin, follow the instructions detailed above in
the *Deploying Liferay Plugins with Maven* section. 

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en-2)

For detailed information on creating layout templates, see [Creating
Liferay Themes and Layout
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-themes-and-layout-templates-liferay-portal-6-2-dev-guide-09-en). 

You've passed your trial by fire (the cat thanks you), developing yet another
plugin type with Maven. Way to go! In the next section we'll cover other
Liferay-provided Maven archetypes. 
