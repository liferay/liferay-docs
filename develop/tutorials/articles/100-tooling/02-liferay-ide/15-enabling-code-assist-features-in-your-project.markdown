# Enabling Code Assist Features in Your Project [](id=enabling-code-assist-features-in-your-project)

Liferay @ide@'s integration of [Tern](http://ternjs.net/) provides many valuable
front-end and back-end development tools for code inference and completion. This
tutorial covers how to enable Tern features for your projects. 

Before beginning this tutorial, make sure your @ide@ instance has the necessary
development tooling and Tern integration installed. To do this, go to *Help*
&rarr; *About Eclipse* &rarr; *Installation Details* and search for *Liferay IDE
AlloyUI* under *Installed Software*. If you have it installed, you can continue
to the *Setting Up Tern Features* section; if you do not, you'll need to install
it by following the instructions below. 

1.  Navigate to *Help* &rarr; *Install New Software...*.

2.  Paste the following link into the *Work with* field: 

        http://releases.liferay.com/tools/ide/latest/stable/

3.  Make sure the *Liferay IDE AlloyUI* option is checked and finish the
    installation process. 

    ![Figure 1: The *Liferay IDE AlloyUI* option is actually a sub-option listed within the *Liferay IDE* option.](../../../images/alloyui-ide-feature.png)

Now that the necessary features are installed, follow the steps below to learn
how to enable Tern's code assist features in your project. 

## Setting Up Tern Features [](id=setting-up-tern-features)

Tern features are enabled on a project-by-project basis. By default, Tern is
already enabled for Liferay WAR-style portlets generated using the Plugins SDK.
For all other project types, (e.g., Liferay module projects), you'll need to
follow the steps below: 

1.  Right-click on your project and select *Configure* &rarr; *Convert to Tern 
    Project*. 

    Your project is now configured to use Tern. Now that you have your project
    configured, you need to enable the modules you want to use for your project.

2.  You're presented a menu listing Tern plugins that are installed. For
    example, to use AlloyUI features, you'll need the *AlloyUI*, *Browser*,
    *JSCS*, *Liferay*, and *YUI Library* modules enabled. The figure below shows
    the Tern Modules menu.

    ![Figure 2: By selecting these Tern modules, you can use AlloyUI code assist features in your project.](../../../images/tern-modules.png)

    If you need to refer back to this list of installed Tern plugins,
    right-click your project and select *Properties*. Then select *Tern* &rarr;
    *Modules*. 

3.  Check any additional modules you wish to use in your project and click *OK*. 

Your project is now set up to use @ide@'s Tern features. 

## Related Topics [](id=related-topics)

[Using Front-End Code Assist Features in @ide@](/develop/tutorials/-/knowledge_base/7-1/using-front-end-code-assist-features-in-ide)

[Creating Modules with Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide)

[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)
