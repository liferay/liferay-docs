# Converting Eclipse Projects into Liferay IDE Projects [](id=converting-eclipse-projects-into-liferay-ide-projec-lp-6-2-develop-tutorial)

Liferay IDE lets you import non-Liferay projects in your Eclipse workspace
(i.e., you can see them in Eclipse's Project Explorer) and convert them to
Liferay projects.

This tutorial shows the steps required to accomplish this. 

## Converting Your Eclipse Project

1.  Move the project into a Liferay Plugins SDK, if it is not already in one. 
    To import the project, select *File* &rarr; *Import...* and then follow the 
    import instructions that appear.

2.  In Eclipse's Project Explorer, right-click on the project and select
    *Liferay* &rarr; *Convert to Liferay plugin project*. 

    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** If no convert action is
    available, either the project is already a Liferay IDE project or it is not
    faceted (i.e., Java and Dynamic Web project facets are not yet configured
    for it). For instructions on resolving these issues, see the
    [Faceting a Plugin Project for Liferay IDE](/tutorials/-/knowledge_base/faceting-a-plugin-project-for-liferay-ide-lp-6-2-develop-tutorial)
    tutorial and the 
    [Verifying Successful Project Import](/tutorials/-/knowledge_base/verifying-successful-project-import-lp-6-2-develop-tutorial)
    tutorial. 

    ---

3.  In the *Convert Project* wizard, your project is selected and the SDK
    location and SDK version of your project are displayed.

    ![Figure 1: The *Convert Project* wizard detects your Plugin's SDK's the location and version.](../../images/ide-convert-plugin-project.png)

4.  Select a Liferay Runtime to use for the project. If you don't have a
    Liferay Runtime defined, define one now by clicking *New...*. 

5.  Click *Finish*. 

Great! Now that you've imported and converted your project, you can work with it 
just as you would any other Liferay project.

## Next Steps 

[Verifying Successful Project Import](/tutorials/-/knowledge_base/verifying-successful-project-import-lp-6-2-develop-tutorial)

[Faceting a Plugin Project for Liferay IDE](/tutorials/-/knowledge_base/faceting-a-plugin-project-for-liferay-ide-lp-6-2-develop-tutorial)

[Using Liferay IDE's Remote Server Adapter](/tutorials/-/knowledge_base/using-liferay-ides-remote-server-adapter-lp-6-2-develop-tutorial)

<!-- TODO - add links to deployment tutorials. Jim -->

