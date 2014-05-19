# Converting Eclipse Projects into Liferay IDE Projects

Liferay IDE lets you import non-Liferay projects in your Eclipse workspace 
(i.e., you can see it in Eclipse's project explorer) and convert them to Liferay 
projects. After importing and converting projects into Liferay IDE, you'll want 
to verify that they imported successfully, and that they're properly configured 
as Liferay IDE projects. 

This tutorial shows the steps required to accomplish this. Let's get started by 
importing a project into Liferay IDE.

## Importing and Converting a Project

1.  Move the project into a Liferay Plugins SDK if it is not already in one. 
    To import the project, select *File* &rarr; *Import...* and then follow the 
    import instructions that appear.

2.  In Eclipse's Project Explorer, right-click on the project and select
    *Liferay* &rarr; *Convert to Liferay plugin project*. 

    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** If no convert action is
    available, either the project is already a Liferay IDE project or it is not
    faceted (i.e., Java and Dynamic Web project facets are not yet configured
    for it). For instructions on resolving these issues, see the section
    *Verifying Successful Project Import*, found in the next section of this 
    tutorial. 

    ---

3.  In the *Convert Project* wizard, your project is selected and the SDK
    location and SDK version of your project is displayed.

    ![Figure 1: The *Convert Project* wizard detects your Plugin's SDK's the location and version.](../../images/ide-convert-plugin-project.png)

4.  Select the Liferay runtime to use for the project. If you don't have a
    Liferay Runtime defined, define one now by clicking *New...*. 

5.  Click *Finish*. 

Let's verify the success of your imports and ensure that they're properly
configured as Liferay IDE projects. 

## Verifying Successful Project Import

Here's how you verify that your imports were successful: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 2: Look for an "L" overlay image to verify that the import succeeded.](../../images/ide-liferay-project-w-overlay-image.png)

2.  Check the project's target runtime and facets to make sure it's configured
    as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
         *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure that
    the Liferay plugin facets are properly configured. 

    ![Figure 3: Make sure that your project's Liferay plugin facets are properly configured.](../../images/ide-project-facets.png)

Great! You've confirmed that your import was successful; you can now make
revisions to your configured Liferay IDE project. 
