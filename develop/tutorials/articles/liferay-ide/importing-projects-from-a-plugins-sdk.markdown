# Importing Projects from a Plugins SDK

Do you want to import one or more Liferay projects into your Liferay IDE
workspace from a Liferay Plugins SDK? Liferay IDE makes it easy.  Don't worry
if the projects already contain `.project` or `.classpath` files, the process
we'll show you will still import them into your workspace. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** This section assumes that
 you've created projects with the Plugins SDK and are familiar with the
 directory structure used by the Plugins SDK. If you need to, check out the
 *Plugins SDK* section of this chapter; it comes right after this section. 

---

## Importing a Single Project

First, let's look at the steps for importing a single Liferay project from a
Plugins SDK project into your workspace. For these steps, we'll assume you
haven't yet configured your Plugins SDK in Liferay IDE: 

1.  In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
    *Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *New* button and select *Liferay Project from Existing Source*.

    ![Figure 2.12: Instead of clicking *File* &rarr; *New* to create a new Liferay project from an existing source, you can click the button shown above from Liferay IDE's shortcut bar.](../../images/ide-new-proj-existing-source.png)

2.  In the *New Liferay Project* window, click the *Browse* button and 
    navigate to the project folder of the plugin you'd like to import. It should
    be a subfolder of one of the SDK's plugin type folders (e.g., portlets,
    hooks, themes, etc) or you'll get an error message stating
    that your Liferay project location is invalid.  

    On selecting the plugin project folder, the *Liferay plugin type* and
    *Liferay plugin SDK version* values are updated. If your Plugins SDK is
    outdated or you entered an incorrect project type, its field gets marked
    with an error. 

4.  Select the *Liferay target runtime* for the plugin project. If you don't
    have a Liferay Portal Runtime, use the *New...* button to create one now.  For
    more detailed instructions, see the section *Liferay Portal Runtime and Server
    Setup*, found earlier in this chapter. 

5.  Click *Finish* to complete the import. 

Any time you import a project into Liferay IDE, you can verify that it was
successfully configured as a Liferay IDE project by using the process outlined
in the section *Verifying Successful Project Import*, found later in this
chapter. 

## Importing Multiple Projects

Next, let's import multiple projects from a Liferay Plugins SDK you've already
set up in Liferay IDE. You can use these steps: 

1.  In Liferay IDE, go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr;
    *Liferay Projects from Plugins SDK*. 

    ![Figure 2.13: To import projects from a Plugins SDK, choose *Liferay Projects from Plugins SDK* from the Import menu.](../../images/ide-import-from-plugin-sdk.png)

2.  In the *Import Liferay Projects* window, use the combo box to select the
    *Liferay Plugins SDK* from which you're importing plugins. 

    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** If your SDK isn't
    configured in Liferay IDE (i.e., it's not in the dropdown list of the
    *Import Projects* window), use the *Configure* link to add one. To configure
    a Plugins SDK from the Installed SDKs window, just click *Add* and then
    browse to the Plugins SDK's root directory.

    ---

3.  Once you select your Plugins SDK in the combo box, the *Liferay Plugin SDK
    Location* and *Liferay Plugin SDK Version* fields are automatically filled
    in, as long as they're valid. Invalid entries are marked with an error. 

4.  The list of projects that are available for import are displayed in a list.
    Any projects already in the workspace are disabled. Projects available for
    import have an empty check box; select each project you'd like to import. 

5.  Select the Liferay runtime for the imported projects. If you don't have a
    Liferay runtime, can add one now with the *New...* button.

6.  Click *Finish*. 

You've imported your plugins into your workspace! Next, we'll discuss a
different scenario; converting existing Eclipse projects into Liferay projects.
