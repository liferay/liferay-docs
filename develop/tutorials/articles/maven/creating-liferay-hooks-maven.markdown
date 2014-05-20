#### Developing Liferay Hook Plugins with Maven [](id=developing-hook-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook is almost identical to portlet plugin creation in Maven. Let's
take a look. 

##### Creating a Hook Plugin [](id=creating-a-hook-plugin-maven-liferay-portal-6-2-dev-guide-02-en)

To create a Liferay hook plugin project, follow the steps outlined in the
*Creating Liferay Plugins with Maven* section, making sure to select *Hook* as
the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your hook you can filter on group ID `liferay`, or even the
group ID/artifact ID combination `liferay:hook`, to more easily find the
Liferay portlet archetypes. 

---

##### Anatomy [](id=hook-plugin-archetype-anatomy-liferay-portal-6-2-dev-guide-02-en)

A hook project created from the
`com.liferay.maven.archetypes:liferay-hook-archetype` has the following
directory structure: 

- hook-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - WEB-INF/
                    - lib/  \* Optionally add to hold required libraries
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

The `hook-plugin/src/main/java/` directory holds the hook's Java source code
(e.g., `com.liferay.sample.SampleHook.java`) and `hook-plugin/src/main/webapp`
holds the hook's web source code. If you're familiar with creating hook plugins
using the Plugins SDK, you probably noticed that Maven uses a different plugin
directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the directory structure of a hook developed by Ant, visit the *Anatomy
of the Hook* section of the [Creating a
Hook](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-a-hook-liferay-portal-6-2-dev-guide-10-en)
section in this guide. 

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en-1)

To deploy your hook plugin, follow the instructions from the *Deploying Liferay
Plugins with Maven* section. 

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en-1)

For detailed information on creating hooks, see [Customizing and Extending
Functionality with
Hooks](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/customize-and-extend-functionality-hooks-liferay-portal-6-2-dev-guide-en).

You're nearly a Maven expert now; you're able to create portlets, themes, and
hooks. Let's round things out by learning to develop layout templates. 
