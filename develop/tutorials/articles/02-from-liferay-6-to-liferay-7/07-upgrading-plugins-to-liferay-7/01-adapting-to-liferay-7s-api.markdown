# Adapting to @product-ver@'s API with the Code Upgrade Tool [](id=adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

The first and easiest plugin upgrade step is adapting to
@product-ver@'s API. @product-ver@ is a major release. It includes a new modular
architecture that [benefits Liferay Portal 6 developers](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers),
but required renaming many packages to adopt a new standard. Of course,
Liferay's carefully [documented](/develop/reference/-/knowledge_base/7-0/breaking-changes)
each API change to explain what changed, why the change was made, and how to
adapt to it.

Going above and beyond documentation, Liferay provides the Code
Upgrade Tool in [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide).
Here's what it does:

1.  Identifies code affected by the API changes
2.  Describes each API change related to the code
3.  Suggests how to adapt the code
4.  Provides options, in some cases, to adapt the code automatically

Even if you prefer tools other than Liferay @ide@ (which is based on Eclipse),
you should use the Code Upgrade Tool. You can use your favorite tools
afterwards. 

All the Code Upgrade Tool's steps after the *Configure Project* step can be
executed (and revisited) in any order. 

Here are the code upgrade steps: 

1.  [Welcome to the Liferay Code Upgrade Tool](#step-1-welcome-to-the-liferay-code-upgrade-tool):
    Introduces the Code Upgrade Tool. 
2.  [Configure the Project](#step-2-configure-the-project): Upgrades an existing
    Plugins SDK to a Plugins SDK 7 and prepares its plugins for upgrading. 
3.  [Upgrade Descriptor Files](#step-3-upgrade-descriptor-files): Upgrades
    descriptor files. 
4.  [Find Breaking Changes](#step-4-find-breaking-changes): Finds breaking
    changes, describes them, and prescribes adaptations (some of which can be
    applied automatically). 
5.  [Build Services](#step-5-build-services): Runs Service Builder on plugins
    that use it. 
6.  [Upgrade Layout Templates](#step-6-upgrade-layout-templates): Upgrades
    layout templates. 
7.  [Convert Custom JSP Hooks](#step-7-convert-custom-jsp-hooks): Converts
    JSP hooks to modules or module fragments. 
8.  [Build](#step-8-build): Compiles the plugins. 
9.  [Summary](#step-9-summary): Lists each upgrade step's status. 

## Launching the Code Upgrade Tool [](id=launching-the-code-upgrade-tool)

The Code Upgrade Tool is available in [Liferay @ide@](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/installing-liferay-ide)
(versions 3.1 milestones and newer).

The current tool requires using a new empty Eclipse workspace. Future versions
of the tool will relax this requirement. If you already have existing projects
in your current Eclipse workspace, create a new Eclipse workspace from the *File
&rarr; Launch Workspace &rarr; New...* wizard.

To launch the Code Upgrade Tool, select *Project &rarr; Liferay Code Upgrade Tool...*

![Figure 1: The Code Upgrade Tool is available in the *Project* menu.](../../../images/code-upgrade-invoke-tool.png)

The Welcome page appears.

## Step 1: Welcome to the Liferay Code Upgrade Tool [](id=step-1-welcome-to-the-liferay-code-upgrade-tool)

The Welcome page describes the tool's navigation and key functions. 

![Figure 2: The Welcome page introduces the tool. The developer navigates between steps by clicking the left and right arrows or a step's gear icon.](../../../images/code-upgrade-welcome.png) 

**Navigation**:

-   Gears (![gears image](../../../images/icon-code-upgrade-step-gears.png)):
    Represent code upgrade steps. Clicking a step's gear takes the user to that
    step. 

-   Check mark (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)):
    Indicates the step is done.

-   X (![x icon](../../../images/icon-code-upgrade-mark-not-done.png)):
    Indicates the step is done.

-   Arrows (![left arrow icon](../../../images/icon-code-upgrade-prev-step.png)
    and ![right arrow icon](../../../images/icon-code-upgrade-next-step.png)):
    Navigate to the previous or next step, respectively. 

Liferay @ide@ tracks each Plugins SDK project's state. Developers can close the
Code Upgrade View at any step. Selecting *Project &rarr; Liferay Code Upgrade
Tool...* relaunches the Code Upgrade Tool for developers to continue upgrading
plugins.

+$$$

**Note**: To delete all project configuration files and restart upgrading the
Plugins SDK projects, the developer can click on the Restart Code Upgrade icon (![restart icon](../../../images/icon-restart-code-upgrade.png)) in the
view's toolbar. 

$$$


![Figure 3: The Code Upgrade Tool lets developers mark steps done (checkmark) and not done (X). Upgrade state is saved so that developers can revisit projects they're not done upgrading.](../../../images/code-upgrade-gears-done-and-not-done.png)

+$$$

**Tip**: The Code Uprade Tool view is easiest to work with when developers can
resize it. To enable resizing the view, minimize it and then click on the view's
icon (![view icon](../../../images/icon-code-upgrade-tool.png)). 

$$$

Advance to the next step.

## Step 2: Configure the Project [](id=step-2-configure-the-project)

The Configure Project step lets the developer specify these things:

- Plugins SDK to upgrade

- Whether to host the Plugins SDK in a new [Liferay Workspace](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace) 

- @product-ver@ server bundle for the plugins to use

Here are the project configuration fields:

*Liferay Plugins SDK Location*: Path to the Plugins SDK to upgrade

*Select Migrate Layout*: 

-   *Upgrade to Liferay Workspace*: Moves the Plugins SDK projects into a new
    Liferay Plugins SDK 7 in subfolder `plugins-sdk` and applies the Workspace
    project layout to the original Plugins SDK root folder.

-   *Upgrade to Liferay Plugins SDK 7*: Upgrades the Plugins SDK to version 7.

*Server Name*: Arbitrary name for the server bundle being installed.

*Bundle URL*: Location of a @product-ver@ bundle.

*Backup SDK into Folder*: Copies the original Plugins SDK to the specified
folder.

![Figure 4: The Configure Project step imports Plugins SDK projects and integrates them with a @product-ver@ bundle.](../../../images/code-upgrade-configure-project.png)

If the Plugins SDK is the same version as the Liferay Portal bundle, the
developer need only set the `app.server.parent.dir` property in the Plugins
SDK's `build.[username].properties` file to the bundle path. Otherwise, the
developer must also set the `app.server.type` and `app.server.[server-type].dir`
properties. 

To convert the Plugins SDK root folder to a Liferay Workspace project, select
the *Upgrade to Liferay Workspace* migration layout and click on the *Import
Projects* button. Here's what that does:

1.  Migrates the SDK projects to a Plugins SDK 7 in Liferay Workspace
2.  Installs a @product-ver@ bundle to the Workspace
3.  Imports the Plugins SDK projects to Liferay @ide@.

<!-- TODO - describe upgrading a Plugins SDK to SDK 7. Jim -->

To mark a step complete, click on its check mark icon (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)).
To mark a step incomplete, click on its X icon (![x icon](../../../images/icon-code-upgrade-mark-not-done.png)).

## Step 3: Upgrade Descriptor Files [](id=step-3-upgrade-descriptor-files)

The Upgrade Descriptor Files screen lists Plugins SDK project descriptor files
to upgrade and compares the proposed upgrades to the original content. 

![Figure 5: The Upgrade Descriptor Files screen lists Plugins SDK project descriptor files to upgrade.](../../../images/code-upgrade-upgrade-descriptor-files.png)

Clicking on a filename opens a text compare view that shows the difference
between the original descriptor file and the proposed upgraded descriptor file. 

![Figure 6: The Upgrade Descriptor File step adapts the descriptor files to @product-ver@. Clicking on a descriptor file name compares the upgraded file with the original.](../../../images/code-upgrade-compare-descriptor-files.png)

**Options**:

- *Find...*: Clicking on this button on an upgrade project that's been restarted detects and
lists all descriptor files that need upgrading. On initial upgrade, the
descriptor files are listed already. 

- *Upgrade...*: Clicking on this button applies ALL the descriptor file changes.

## Step 4: Find Breaking Changes [](id=step-4-find-breaking-changes)

The Find Breaking Changes step lists lines of code affected by API changes,
describes the changes, and explains how to adapt to the changes. 

Clicking the project selection icon (![project selection icon](../../../images/icon-code-upgrade-tool-select-project.png))
opens a window for selecting projects to adapt to breaking changes. After
selecting projects, click *OK* to apply some of the proposed adaptations
automatically. <!-- TODO redo this paragraph--maybe as steps. -->

- Upper-left pane lists the projects that have outstanding breaking changes.

- Lower-left pane lists the currently selected project's breaking changes.

- Right pane shows the breaking change documentation.

![Figure 7: The Finding Breaking Changes step shows you where breaking changes affect your plugin. In addition, it provides background information on each change and explains how to adapt to it.](../../../images/code-upgrade-find-breaking-changes.png)

The upper left panel's *Code Problems* folder shows a tree of projects and
project files that have code problems. On selecting a project file, its lines
that have problems are listed in the panel below. A line number accompanies each
problem. As developers resolve problems, they can mark them resolved by checking
on their respective checkboxes in the *Resolved* column.

On selecting a problem, it's described in the side panel:

-   **Date**: When the change was introduced.

-   **JIRA Ticket**: Corresponding issue number.

-   **What Changed?**: Change summary.

-   **How should I update my code?**: Adaptation instructions.

-   **Why was the change made?**: Reasons for the change.

Clicking on the bandage icon (![bandage icon](../../../images/icon-bandage.png))
brings up a project selection dialog. Clicking *OK* in the dialog corrects the
project's problems that are clearly understood and easy to fix (e.g., package
imports and @product@ version properties) automatically.

For each problem, select it from the tree, read its documentation, and double
click the problem to fix it an editor. 

+$$$

**Tip**: To use the entire Code Upgrade Tool view to show breaking change
documentation, click on the Hide Tree icon (![hide tree icon](../../../images/icon-hide-breaking-change-tree.png)). 

$$$

Right selecting a problem shows these options:

- *Mark done*: Indicate it's resolved.

- *Mark undone*: Indicate it isn't resolved.

- *Ignore*: Skip it.

- *Correct automatically*: Let the Code Upgrade Tool resolve it automatically.

- *Ignore all problems of this type*: Skip addressing this type of problem.

![Figure 8: The Code Upgrade Tool can correct some problems automatically. Also it lets developers ignore the problem, ignore problems like it, or mark it done/undone.](../../../images/code-upgrade-problem-options.png)

To revisit problems ignored previously, click on the Open Ignored List icon (![ignored list icon](../../../images/icon-code-upgrade-list-ignored-problems.png)). 

## Step 5: Build Services [](id=step-5-build-services)

The Build Service step re-runs Service Builder on the projects and removes
legacy Service Builder files from them. 

![Figure 9: The Build Service step re-runs Service Builder on the projects and removes legacy Service Builder related files from them.](../../../images/code-upgrade-build-service.png)

## Step 6: Upgrade Layout Templates [](id=step-6-upgrade-layout-templates)

This step lets developers view and apply proposed layout template upgrades. 

![Figure 10: The Upgrade Layout Templates step lists all layout templates to upgrade.](../../../images/code-upgrade-upgrade-layout-templates.png)

Clicking the *Find...* button lists the layout template files that need
upgrades.

Double clicking on a layout template opens it in a view that compares its
original code to the proposed upgraded code.

Clicking the *Upgrade...* button applies the proposed upgrades to all the layout
templates.

The word *Finished* shows after each upgraded layout template's name. 

## Step 7: Convert Custom JSP Hooks [](id=step-7-convert-custom-jsp-hooks)

This step converts the SDK's custom JSP hooks to modules or module fragments. It
lets the developer compare the SDK's custom JSP and the newly generated JSP with
the original JSP from Liferay Portal 6.2.

**Options:**

*Converted Project Location*: Path to the resulting module or module fragment
projects (e.g., `<workspace>/modules/`)

*Select Projects*: Shows a window from which to select projects that have
custom JSP hooks.

*Refresh Results*: Synchronizes the JSP file trees to reflect all new changes.

*Clear Results*: Empties the panels of all content

![Figure 11: The Conver Custom JSP Hooks lets developers select JSP hook plugin projects to convert to modules or module fragments. After they're converted, the panels make the originals available for comparing with the adapted custom JSPs.](../../../images/code-upgrade-convert-custom-jsp-hooks.png)

Convert the Liferay Portal 6.2 custom JSP hooks to new modules or module
fragments for @product-ver@:

1.   Set the *Converted Project Location* to the Workspace's *modules* folder.

2.  Click on the *Select Projects* button. The *Custom JSP Hook Project* window
    appears.

3.  In the *Custom JSP Hook Project* window, select projects to convert and click on the *OK* button.

    ![Figure 12: The Code Upgrade Tool lets developers select custom JSP hook projects to upgrade.](../../../images/code-upgrade-custom-jsp-hook-project-seclector.png)

The JSP hooks are converted to new projects (modules or module fragments) in the
Converted Project Location (e.g., the Workspace's `modules` folder). 

![Figure 13: Custom JSP hook projects are converted to module or module fragment projects.](../../../images/code-upgrade-converted-jsp-hooks-in-modules.png)

The Code Upgrade Tool lists the custom JSPs from the 6.2 SDK and the new
converted projects for developers to compare with the original Liferay Portal 6.2
JSPs. 

![Figure 14: The Code Upgrade Tool lets developers compare their 6.2 and new 7.x custom JSPs with Liferay Portal 6.2 originals.](../../../images/code-upgrade-convert-jsp-hooks.png)

In the left panel, clicking a JSP filename compares its 6.2 custom JSP with the
Liferay Portal 6.2 original. 

![Figure 15: This view compares an original 6.2 JSP with the 6.2 custom JSP.](../../../images/code-upgrade-converted-jsp-hooks-compare-6.2-original.png)

In the right panel, clicking a JSP filename compares its new 7.x custom JSP
with the Liferay Portal 6.2 original.

![Figure 16: This view compares the new module's 7.x custom JSP with the Liferay Portal 6.2 original. ](../../../images/code-upgrade-converted-jsp-hooks-compare-6.2-original-to-7.0.png)

If the 6.2 hook plugin's JSP didn't customize an existing Liferay Portal 6.2
core JSP, there's no original. The JSP is marked *unfound* in the panels. The
developer decides how to implement the customization for @product-ver@. It might
make sense to create a similar new JSP in a new or converted module project. 

![Figure 17: The Code Upgrade Tool indicates whether a custom JSP is *found* or *unfound* (not found) in Liferay Portal 6.2.](../../../images/code-upgrade-converted-jsp-hooks-found-unfound.png) 

Referring to the JSP comparisons facilitates implementing the new JSP
customization for @product-ver@. 

The Code Upgrade Tool creates a `ConvertedCustomJspBag` class in the converted
module project. It tells the OSGi container that the module modifies a core JSP.
The class's path in the module project is
`src/main/java/codeupgrade.corejsphook/ConvertedCustomJspBag.java`.  

![Figure 18: A `ConvertedCustomJspBag` class in each core JSP customization module informs the OSGi container about the customized JSP.](../../../images/code-upgrade-converted-core-jsp-hook-module.png)  

The new upgraded core JSPs reside under the module's folder
`src/main/resources/META-INF/resources/html/`. 

The Code Upgrade Tool converts Liferay Portal 6.2 module custom JSP hooks to
module fragments. Here's a fragment's `bnd.bnd` file: 

![Figure 19: The Fragment-Host header specifies the module on which to apply the fragment.](../../../images/code-upgrade-converted-jsp-hook-fragment-bnd.png)

Developers can convert custom JSPs as needed.

To restart converting custom JSPs from SDK projects, follow these steps:

1. Delete the converted module projects from the *Converted Project Location*.

2. Click on the Clear Results button in the Convert Custom JSP Hooks view

3. Select the projects again from the *Custom JSP Hook Project* window and click
*OK*.

The Convert Custom JSP Hooks view empowers developers to upgrade JSP hooks at
their leisure.

## Step 8: Build [](id=step-8-build)

This step rebuilds the Plugins SDK projects. To rebuild the projects, click on
the *Build...* button. 

![Figure 20: Developers can build SDK projects any time.](../../../images/code-upgrade-build.png)

## Step 9: Summary [](id=step-9-summary)

The last step lists each step's status. A check mark indicates the step is done,
an X indicates it's not done, and a question mark indicates it's status isn't
decided. To revisit a step, click its name. 

![Figure 21: The Summary step lists each step's status.](../../../images/code-upgrade-summary.png)

You can always revisit the Liferay Code Upgrade view by selecting *Project
&rarr; Liferay Code Upgrade Tool...*

You've leveraged Liferay's Code Upgrade Tool to start adapting your existing
plugins to @product-ver@. As you continue upgrading your plugins you can re-open
the Code Upgrade Tool and track progress as you wish.  

## Related Topics [](id=related-topics)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
