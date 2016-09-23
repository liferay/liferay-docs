# Enabling Code Assist Features in Your Project [](id=enabling-code-assist-features-in-your-project)

Liferay IDE's integration of [Tern](http://ternjs.net/) provides many valuable
front-end and back-end development tools for code inference and completion. This
tutorial covers how to enable Tern features for your projects. 

Before beginning this tutorial, make sure your IDE instance has the necessary
development tooling and Tern integration installed. To to this, go to *Help*
&rarr; *Installation Details* and search for *Liferay IDE AlloyUI* under
*Installed Software*. If you have it installed, you can continue to the *Setting
Up Tern Features* section; if you do not, you'll need to install it by following
the instructions below. 

Navigate to *Help* &rarr; *Install New Software...* and paste the following
link into the *Work with* field: 

    http://releases.liferay.com/tools/ide/latest/stable/

Make sure the *Liferay IDE AlloyUI* option is checked and finish the
installation process. 

![Figure 1: The *Liferay IDE AlloyUI* option is actually a sub-option listed within the *Liferay IDE* option.](../../../images/alloyui-ide-feature.png)

Now that the necessary features are installed, follow the steps below to learn
how to enable Tern's code assist features in your project. 

## Setting Up Tern Features [](id=setting-up-tern-features)

Tern features are enabled on a project-by-project basis. By default, Tern 
is already enabled for Liferay portlet plugins. For all other project 
types, you'll need to follow the steps below: 

1.  Right-click on your project and select *Configure* &rarr; *Convert to Tern 
    Project*. 

    Your project is now configured to use Tern. Now that you have your project
    configured, you need to enable the modules you want to use for your project. 

2.  Right-click your project and select *Properties*. 

3.  Select *Tern* &rarr; *Modules*. 

    Here you'll find a list of all the available Tern modules you currently have
    installed. To use AlloyUI features, you'll need the *AlloyUI*, *Browser*,
    *JSCS*, *Liferay*, and *YUI Library* modules enabled. The figure below shows
    the Tern Modules menu. 

    ![Figure 2: By selecting these Tern modules, you can use AlloyUI code assist features in your project.](../../../images/tern-modules.png)

4.  Check any additional modules you wish to use in your project and click *OK*. 

Your project is now set up to use IDE's Tern features. 

## Related Topics [](id=related-topics)

[Using Front-End Development Code Assist Features in IDE](/develop/tutorials/-/knowledge_base/7-0/using-front-end-development-code-assist-features-in-ide)

[Creating Modules with Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-liferay-ide)

[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)
