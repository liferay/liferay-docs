# Enabling Code Assist Features in Your Project

Liferay IDE's integration of Tern provides many valuable front-end and back-end
development tools for code inference and completion. This tutorial covers how 
to enable Tern features for your projects.

Follow the steps below to learn how to enable Tern's code assist features in 
your project.

## Setting up Tern Features

Tern features are enabled on a project by project basis. By default Tern 
features are already enabled for liferay portlet plugins. For all other project 
types you will need to follow the step below:

1.  Right-click on your project and select *Configure*&rarr;*Convert to Tern 
    Project*.

There you go! Your project is now configured to use Tern features. Now that you 
have your project configured, you need to enable the modules you want to use for 
your project next. Follow the steps below to enable your modules:

1.  Right-click your project and select *Properties*.

2.  Select *Tern*&rarr;*Modules*.

    Here you will find a list of all the available Tern modules you currently 
    have installed. To use AlloyUI features, you will need the aui, browser, 
    ecma5, and yui modules enabled. The figure below shows the proper modules 
    enabled:

    ![Figure 1: There are several modules available to you for your Tern project](../../images/tern-modules.png)

3.  Check the modules you wish to use in your project and click *ok*.

Your project is now setup to use IDE's Tern features.

## Related Topics

 [Using Front-End Development Code Assist Features in IDE](/develop/tutorials/-/knowledge_base/6-2/using-tern-front-end-development-features)
