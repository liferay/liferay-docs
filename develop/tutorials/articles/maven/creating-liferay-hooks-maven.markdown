# Developing Liferay Hook Plugins with Maven [](id=developing-liferay-hook-plugins-with-maven-lp-6-2-develop-tutorial)

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook plugin with Maven is very similar to creating a portlet plugin
with Maven. Let's take a look. 

## Creating a Hook Plugin

To create a Liferay hook plugin project, follow the steps outlined in the
[Creating Liferay Plugins with Maven](http://www.liferay.com) tutorial, making
sure to select *Hook* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your hook you can filter on group ID `liferay`, or even the
group ID/artifact ID combination `liferay:hook`, to more easily find the
Liferay portlet archetypes. 

---

## Anatomy

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
using the Plugins SDK, you've probably noticed that Maven uses a different
plugin directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the directory structure of a hook developed by Ant, please refer to the
*Anatomy of the Hook* section of the [Creating a Hook](http://www.liferay.com)
tutorial on creating hook plugins via the Liferay Plugins SDK. 

## Deployment

To deploy your hook plugin, follow the instructions from the 
[Deploying Liferay Plugins with Maven](http://www.liferay.com) tutorial. 

## More Information

For detailed information on creating hooks, see the [Customizing and Extending
Liferay Functionality with Hooks](http://www.liferay.com) tutorial.

## Next Steps

If you've followed our other tutorial on developing Liferay plugins with Maven,
you should nearly be a Maven expert by now. We've explained how to create
portlets, themes, and hooks. To round things out, check out the following
tutorial on creating layout templates:

[Developing Layout Template Plugins with Maven](http://www.liferay.com)
