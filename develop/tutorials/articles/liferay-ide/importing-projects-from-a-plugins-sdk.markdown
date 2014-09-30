# Importing Projects from a Plugins SDK [](id=importing-projects-from-plugins-sdk)

Do you want to import one or more Liferay projects into your Liferay IDE
workspace from a Liferay Plugins SDK? Liferay IDE makes it easy. Don't worry if
the projects already contain `.project` or `.classpath` files, the process used
in this tutorial assures they get imported into your workspace.

First, consider the steps for importing a single Liferay project from a
Plugins SDK project into your workspace.

<!-- To do: uncomment this note and add link to the new Plugins SDK tutorial once it exists -->

<!--
+$$$

**Note:** This section assumes that
you've created projects with the Plugins SDK and are familiar with the
directory structure used by the Plugins SDK. If you need to, check out the
*Plugins SDK* section of this chapter; it comes right after this section. 

$$$
-->

## Importing a Single Project

For these steps, it's assumed that you haven't yet configured your Plugins SDK
in Liferay IDE: 

1.  In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
    *Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *New* button and select *Liferay Project from Existing Source*.

    ![Figure 2.12: As an alternative to clicking *File* &rarr; *New* to create a new Liferay project from an existing source, you can click the button shown above from Liferay IDE's shortcut bar.](../../images/ide-new-proj-existing-source.png)

2.  In the *New Liferay Project* window, click the *Browse* button and navigate
    to the project folder of the plugin you'd like to import. The project folder
    must be a subfolder of one of the SDK's plugin type folders (e.g.,
    `portlets`, `hooks`, `themes`, etc); otherwise, Liferay IDE reports an error
    message stating that your Liferay project location is invalid. 

    On selecting the plugin project folder, the *Liferay plugin type* and
    *Liferay plugin SDK version* values are updated. If your Plugins SDK is
    outdated or you entered an incorrect project type, its field gets marked
    with an error. 

4.  Select the *Liferay target runtime* for the plugin project. If you don't
    have a Liferay Portal Runtime, use the *New...* button to create one now.
    <!-- For more detailed instructions, see the section *Liferay Portal Runtime
    and Server Setup*, found earlier in this chapter. -->
    <!-- To do: add link to portal runtime and server setup learning path article -->

5.  Click *Finish* to complete the import. 

You've imported your Liferay project into your workspace!

<!-- Any time you import a project into Liferay IDE, you can verify that it was
successfully configured as a Liferay IDE project by using the process outlined
in the tutorial *Verifying Successful Project Import*. 
-->

<!-- To do: Add link to Verifying Successful Project Import tutorial -->

## Importing Multiple Projects

Next, you'll learn how to import multiple projects from a Liferay Plugins SDK
you've already set up in Liferay IDE. Follow these steps: 

1.  In Liferay IDE, go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr;
    *Liferay Projects from Plugins SDK*. 

    ![Figure 2.13: To import projects from a Plugins SDK, choose *Liferay Projects from Plugins SDK* from the Import menu.](../../images/ide-import-from-plugin-sdk.png)

2.  In the *Import Liferay Projects* window, use the dropdown list to select the
    *Liferay Plugins SDK* from which you're importing plugins. 

+$$$

**Note:** If your Plugins SDK isn't
    configured in Liferay IDE (i.e., it's not in the dropdown list of the
    *Import Projects* window), use the *Configure* link to add one. To configure
    a Plugins SDK from the Installed SDKs window, just click *Add* and then
    browse to the Plugins SDK's root directory.

$$$

    Liferay IDE automatically fills in the *Liferay Plugin SDK Location* and
    *Liferay Plugin SDK Version* fields, as long as they're valid; invalid
    entries are marked with errors. 

5.  The list of projects that are available for import are displayed in a list.
    Any projects already in the workspace are disabled. Projects available for
    import have an empty check box. Select each project you'd like to import. 

6.  Select the Liferay runtime for the imported projects. If you don't have a
    Liferay runtime, you can add one now by clicking the *New...* button.

7.  Click *Finish*. 

Congratulations on importing *multiple* Liferay projects into your workspace! 

## Related Topics

[Verifying Successful Project Import](/develop/tutorials/-/knowledge_base/verifying-successful-project-import)

[Deploying Plugins](/develop/tutorials/-/knowledge_base/deploying-plugins)

