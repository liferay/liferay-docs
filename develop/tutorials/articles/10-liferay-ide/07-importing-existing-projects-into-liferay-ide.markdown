# Importing Existing Projects into Liferay IDE [](id=importing-existing-projects-into-liferay-ide)

What if you have existing projects that you want to work on in Liferay IDE?
You can import your existing projects into Liferay IDE to take advantage of
Liferay IDE's developer tools. 

We'll cover the following import scenarios: 

1.  Importing existing Liferay projects from the Plugins SDK. 

2.  Importing Eclipse projects that aren't in Liferay IDE (i.e., don't have
    the Liferay facet or target runtime).

3.  Importing existing Liferay IDE projects from another Liferay IDE version or
    workspace. 

Let's start by learning how to import existing Liferay projects from Liferay
Plugins SDK. 

You can import Liferay projects you created with the Plugins SDK, but aren't yet
in your Liferay IDE Eclipse workspace. These projects might already have
`.project` or `.classpath` files in them; regardless, we'll use the same
procedure to import them.

+$$$

**Note:** The next two sections assume that you've created projects with the
Plugins SDK and are familiar with the directory structure used by the Plugins
SDK. 

$$$

First let's look at the import process for creating a single Liferay IDE project
from a Plugins SDK project. Follow these steps:

1.  In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
    *Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *Create a New Liferay Project* button.

    ![Figure 10.10: Alternate method of Importing a project](../../images/ide-new-proj-existing-source.png)

2.  Browse to your project folder. It should be a subdirectory of one of the
    plugin types (e.g., portlets, hooks, themes, etc) or it won't import
    correctly. Once you select the plugin project folder, its type and SDK
    version values are updated to the correct values. If the SDK isn't recent
    enough or the project type is incorrect, it gets marked with an error. 

3.  After you select the plugin project folder, the *Liferay plugin type* and
    *Liferay plugin SDK version* values are updated. If your Plugins SDK is
    outdated or you entered an incorrect project type, its field gets marked
    with an error. 

4.  Select the *Liferay target runtime* to configure on the project once it's
    imported. If you don't have a Liferay Portal Runtime, use the *New...*
    button to create one now.  

5.  Click *Finish* to perform the import. 

Remember to verify that your project was configured correctly as a Liferay IDE
project. Verification is described right after our next topic, which is making
multiple Liferay IDE projects from a single Plugins SDK. 

Next we'll show you how to import all your Plugins SDK projects into Liferay
IDE. 

## Batch Importing Multiple Projects into Liferay IDE [](id=batch-importing-multiple-projects-into-liferay-ide)

Does your Plugins SDK contain multiple plugins? You can easily batch import each
plugin in your Plugins SDK as its own Liferay IDE project. 

1.  In Eclipse go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr; *Liferay
    Projects from Plugins SDK*. 

    ![Figure 10.11: Choosing the project type for import](../../images/ide-import-from-plugin-sdk.png)

2.  In the *Import Projects* window, use the combo box to select the *Liferay
    Plugins SDK* you're importing plugins from. 

    +$$$

    **Note:** If your SDK isn't configured in Liferay IDE (i.e., it's not in the
    dropdown list of the *Import Projects* window), use the *Configure* link to
    add one. To configure a Plugins SDK on the Installed SDKs on the pref page
    just click *Add* and then Browse to the directory of the Plugins SDK root
    directory.

    $$$

3.  Once you select your Plugins SDK in the combo box, the *Liferay Plugin SDK
    Location* and *Liferay Plugin SDK Version* fields are automatically filled
    in, as long as they're valid. Invalid entries are marked with an error. 

4.  The list of projects that are available for import are displayed in a table.
    Any projects already in the workspace are disabled. Projects available for
    import have an empty check box; select each project you'd like to import. 

5.  Select the Liferay runtime you want to set up for the imported projects. 

6.  If you don't have a Liferay runtime, can add one now with the *New...*
    button. 

7.  Click *Finish*. 

Your plugins are imported! Next, we'll talk about importing existing Eclipse
projects into Liferay IDE. 

## Importing Existing Eclipse Projects into Liferay IDE [](id=importing-existing-eclipse-projects-into-liferay-ide)

The steps we outlined above let you import projects that have never been opened
in Eclipse. If you've been using Eclipse without Liferay IDE, you may already
have a project in your Eclipse workspace. Follow the import steps below to
convert your project. 

1.  In Eclipse, right-click the project in your Project Explorer, and select
    *Liferay* &rarr; *Convert to Liferay plugin project*. 

    +$$$

    **Note:** If you don't have a convert action available it means either that
    the project is already a Liferay IDE project, or that it is not faceted
    (i.e., Java and Dynamic Web project facets are not yet configured).
    Configure accordingly before moving on. 

    $$$

2.  In the *Convert Project* wizard, your project should be selected and the SDK
    location and SDK version should be detected automatically. If they aren't
    valid, an error message is displayed. 

    ![Figure 10.12: Converting a project](../../images/ide-convert-plugin-project.png)

3.  Select the Liferay runtime to use for the project. If you don't have a
    Liferay Runtime defined, do so now by clicking *New...*. 

4.  Click *Finish*. 

Remember to verify that your import was successful and that your project was
configured correctly as a Liferay IDE project. We have one more import scenario
to cover--importing existing Liferay IDE projects into your Liferay IDE. 

## Importing an existing Liferay IDE project [](id=importing-an-existing-liferay-ide-project)

What if you had a Liferay IDE project in your workspace, but it's not there
anymore? Here's how you can import it into your current workspace: 

1.  Open Liferay IDE, go to *File* &rarr; *Import* ... &rarr; *General* &rarr;
    *Existing Projects into Workspace*. 

2.  Choose the option *Select root directory*, then click *Browse...*. 

3.  Select the directory of the Liferay IDE project to import. 

4.  In the *Projects:* list you should see your Liferay IDE project. 

5.  Click *Finish*. 

+$$$

**Note:** There are two common errors that occur when importing existing Liferay
IDE projects into your workspace:

- The SDK name used in that project doesn't exist in your new workspace. 

- The runtime id used doesn't exist in your new workspace. 

You can modify the SDK name in *Project Properties* &rarr; *Liferay* dialog
window; you can modify the targeted runtime in the *Project properties* &rarr;
*Targeted Runtimes* dialog window. 

$$$

Let's verify the success of your imports and ensure that they're properly
configured as Liferay IDE projects. 

## Verifying that the import has succeeded [](id=verifying-that-the-import-has-succeeded)

After importing projects into Liferay IDE, you'll want to verify that they
imported successfully, and that they're properly configured as Liferay IDE
projects. Here's how you verify that your imports were successful: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 10.13: Verifying that the import succeeded](../../images/ide-liferay-project-w-overlay-image.png)

2.  Let's check the project's target runtime and facets to make sure it's
    configured as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
         *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure both
         Liferay runtime and Liferay plugin facets are properly configured. 

    ![Figure 10.14: Project Facets](../../images/ide-project-facets.png)

Great! You've confirmed that your import was successful; you can now make
revisions to your configured Liferay IDE project. Next, let's explore Liferay
IDE's Remote Server Adapter feature. 
