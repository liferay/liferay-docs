#### Developing Liferay Theme Plugins with Maven [](id=develop-liferay-theme-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

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

##### Creating a Theme Plugin [](id=create-a-theme-plugin-maven-liferay-portal-6-2-dev-guide-02-en)

Theme plugin creation is similar to portlet plugin creation. We'll start by
assuming you already created the `sample-parent-project` and its `pom.xml`. 

To create your Liferay theme plugin project follow the *Creating Liferay Plugins
with Maven* section, making sure to select *Theme* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your theme project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:theme`, to more easily
find the Liferay portlet archetypes. 

---

##### Anatomy [](id=theme-archetype-anatomy-liferay-portal-6-2-dev-guide-02-en-0)

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
                    - liferay-plugin-package.properties
                    - web.xml
                - css/ \* Optionally add to hold CSS customizations
                - images/ \* Optionally add to hold custom images
                - js/ \* Optionally add to hold JavaScript customizations
                - templates/ \* Optionally add to hold template customizations

The `src/main/webapp/` folder holds your theme's customizations. If you've ever
created a theme plugin using Liferay IDE or the Plugins SDK, this folder is used
the same way as the `docroot/_diffs/` folder. For example, `custom.css` should
go in `src/main/webapp/css/custom.css`. 

Here's a table describing the directory structure differences between themes
created using Maven and themes created using the Plugins SDK: 

Location       | Maven project      | Plugins SDK project |
-------------- | ------------------ | ------------------- |
customizations | `src/main/webapp/` | `docroot/_diffs/`   |

To view the directory structure of a theme developed by Ant, visit the [Anatomy
of a Theme
Project](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-liferay-themes-liferay-portal-6-2-dev-guide-09-en)
section in this guide.

##### Theme POM [](id=theme-pom-liferay-portal-6-2-dev-guide-02-en)

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

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en-0)

To deploy your theme plugin, follow the instructions in the *Deploying Liferay
Plugins with Maven* section. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you execute the *package*
goal, a WAR file is created; it's just like the Maven WAR type project.
Simultaneously, the parent theme is downloaded and copied, and your theme's
customizations are overlaid last. A thumbnail image of the theme is created and
placed in the *target* directory. Its path is
`target/[theme]/images/screenshot.png` in your theme project. 

---

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en-0)

For more information on Liferay themes and its settings, see
[Creating Liferay Themes and Layout
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-themes-and-layout-templates-liferay-portal-6-2-dev-guide-09-en). 

You successfully developed a Liferay theme using Maven. Find out about
developing hook plugins next. 

