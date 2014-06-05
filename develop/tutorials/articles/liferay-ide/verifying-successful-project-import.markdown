# Verifying Successful Project Import

Once you've imported a project, you can verify that Liferay IDE correctly 
recognizes it as a Liferay project. This verification is also useful for 
Liferay plugin projects that you developed in a Liferay Plugins SDK, but outside
of Liferay IDE. 

## Verifying Projects in Liferay IDE

Here's how you verify that your project is a Liferay project: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 1: Look for an "L" overlay image to verify that the import succeeded.](../../images/ide-liferay-project-w-overlay-image.png)

    If your project doesn't have the "L" overlay image, it needs to be faceted
    for Liferay and/or it needs to be targeted for a Liferay Runtime
    environment. If it needs faceting, follow steps in the next section
    *Faceting a Plugin Project for Liferay IDE*.

2.  Check the project's target runtime and facets to make sure they're
    configured property for a *Liferay IDE* project:

    In the *Package Explorer*, right click your project's name &rarr;
    *Properties* &rarr; *Targeted Runtimes*. Make sure the proper Liferay
    Runtime is selected. 

    In the *Properties* window, click *Project Facets* and make sure that the
    Liferay plugin facets are properly configured. 

    ![Figure 2: Make sure that your project's Liferay plugin facets are properly configured.](../../images/ide-project-facets.png)

If your project needs to be faceted, continue on with the next part of this
tutorial. Otherwise, congratulations on verifying that your project imported
properly! 

## Faceting a Plugin Project for Liferay IDE

In order for a plugin project to work properly in Liferay IDE, it must have
Dynamic Web, Java, and Liferay Plugin facets. To facet your plugin project
properly, follow these steps: 

1.  In the *Package Explorer*, right click your project's name &rarr;
    *Properties* &rarr; *Project Facets*. If the Project Facets window reports
    that the project is not configured to use project facets, click the link
    *Convert to faceted form...*

    ![Figure 3: Enabling your project to use facets is easy in Liferay IDE.](../../images/ide-project-not-configured-to-use-project-facets.png)

2.  Select *Dynamic Web Module*, *Java*, and *JavaScript* (required if your
    plugin project uses JavaScript). 

3.  Expand the *Liferay Plugins* facet type and select the plugin type matching
    your project's plugin type. 

4.  Click the *OK* button. 

Your plugin project is now faceted for Liferay IDE!

