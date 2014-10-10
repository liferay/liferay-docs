# Faceting a Plugin Project for Liferay IDE [](id=faceting-a-plugin-project-for-liferay-ide)

When you drag your plugin project onto your Liferay server in Liferay IDE, is
the plugin not deploying? Is there no "L" overlay image on your project's icon
in the *Package Explorer*? These are symptoms of an unfaceted plugin project. In
order for a plugin project to work properly in Liferay IDE, it must have Dynamic
Web, Java, and Liferay Plugin facets. Don't worry, they're easy to configure. 

In this tutorial, you'll learn how to facet your plugin project into a bonafide
Liferay IDE project in no time! 

## Faceting a Liferay Plugin Project

To facet your plugin project properly, follow these steps: 

1.  In the *Package Explorer*, right click your project's name &rarr;
    *Properties* &rarr; *Project Facets*. If the Project Facets window reports
    that the project is not configured to use project facets, click the link
    *Convert to faceted form...*

    ![Figure 1: It's easy to enable your project to use facets in Liferay IDE.](../../images/ide-project-not-configured-to-use-project-facets.png)

2.  Select the *Dynamic Web Module*, *Java*, and *JavaScript* (required if your
    plugin project uses JavaScript) facets. 

3.  Expand the *Liferay Plugins* facet type and select the plugin type matching
    your project's plugin type. 

    ![Figure 2: Make sure to select the plugin type matching your project's plugin type.](../../images/ide-project-facets.png)

4.  Click the *OK* button. 

Your plugin project is now faceted for Liferay IDE!

## Related Topics 

[Deploying Plugins](/develop/tutorials/-/knowledge_base/6-2/deploying-plugins)

