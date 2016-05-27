## Using Liferay IDE to Create Your Workspace

*New Liferay Workspace* project has more functions as follows:

Create a Liferay Workspace in IDE, select *File* &rarr; *New* &rarr; *Liferay
Workspace*.

Add workspace name(WorkspaceProject), then click *Finish*. A popup window asks you if you want to open *Liferay Workspace perspective*. Click *Yes*, the perspective will switch to *Liferay Workspace*.

![workspace_perspective](../../../images/workspace_1.png)

Then create a `module project`. You can see the module project will be added to the modules dir inside the workpspace you just created by default.

![module_project_location](../../../images/workspace_2.png)


Finish creating this module project, in *Project Explorer*, the defaut *Projects Presentation* is "Hierarchical". The subfolder and subproject are under the workspace project.

![module_project_hierarchical](../images/workspace_3.png)

*Known issue:* Delete this workspace project(don't select Delete project contents on disk), switch perspective to *Liferay*. In *Package Explorer*, could see the module project still exist.

In the same IDE, there only support one workspace project. If you create another, this error message will alart you.

![duplicated_workspace](../images/workspace_4.png)

You can also *import* an existing workspace project to your IDE. Go to *File* &rarr; *Import...* &rarr; *Liferay* &rarr; *Liferay
Workspace Project* &rarr; *Next* Browse the worspace project in your folder.

![import_workspace_project](../images/workspace_5.png)
