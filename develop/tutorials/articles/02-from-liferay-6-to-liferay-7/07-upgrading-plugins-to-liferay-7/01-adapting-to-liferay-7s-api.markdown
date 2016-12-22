# Adapting to @product-ver@'s API with the Code Upgrade Tool [](id=adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

The first upgrade process step is to adapt your existing plugin's code to
@product-ver@'s API. @product-ver@ is a major release that contains significant API
changes. As part of the modularization process, many packages have been renamed
to adopt a new standard. But each API change has been carefully documented to
explain what changed, how to adapt to the change, and why the change was made.

To go above and beyond documenting the changes, there's the Code Upgrade
Tool: a tool that knows about these changes, analyzes your code, and suggests
how to adapt the code to the new APIs.

The Liferay Code Upgrade Tool is in Liferay @ide@. You should use the tool to
adapt code to @product-ver@; you can switch back to your favorite tool
afterwards.

+$$$

**Note**: Even if Liferay @ide@ (which is based on Eclipse) isn't your favorite,
you should use it to leverage the Code Upgrade Tool. When you're done adapting
your code, you can go back to your preferred IDE and tool chain.

$$$

This tutorial shows you how the following Code Upgrade Tool steps adapt existing
plugin code to @product-ver@'s API. 

- [Configure Project](#configuring-the-upgrade-project)
- [Upgrade Descriptor Files](#upgrading-descriptor-files)
- [Find Breaking Changes](#finding-breaking-changes)
- [Build Service](#rebuilding-services)
- [Upgrade Layout Template](#upgrading-layout-templates)
- [Convert Custom JSP Hooks](#converting-custom-jsp-hooks-to-modules-or-fragments)
- [Build](#building-the-plugins-sdk-projects)

## Using the Code Upgrade Tool [](id=using-the-code-upgrade-tool)

The Code Upgrade Tool is available in [Liferay @ide@](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/installing-liferay-ide)
(versions 3.1 milestones and newer).

The current Code Upgrade Tool assumes that you are starting with a clean Eclipse
workspace. If you already have existing projects in your current Eclipse
workspace, simply create a new Eclipse workspace from the *File &rarr; Launch
Workspace &rarr; New...* wizard and can continue with this tutorial. Note:
Future versions of the Code Upgrade Tool will relax this requirement.

To launch the Code Upgrade Tool, select *Project &rarr; Liferay Code Upgrade Tool...*

![Figure 1: The Code Upgrade Tool is available from the *Project* menu.](../../../images/code-upgrade-invoke-tool.png)

The Welcome page appears and describes the tool's navigation and key functions. 

![Figure 2: The Welcome page describes the tool and how to use it. The developer navigates between steps by clicking the left and right arrows or a step's gear icon.](../../../images/code-upgrade-welcome.png) 

Here's the tool's navigational features:

-   Gears (![gears image](../../../images/icon-code-upgrade-step-gears.png)):
    Represent code upgrade steps. Clicking a step's gear takes the user to that
    step. 

-   Check mark (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)):
    Indicates the step is done.

-   "X"s (![x icon](../../../images/icon-code-upgrade-mark-not-done.png)):
    Indicates the step is done.

-   Arrows (![left arrow icon](../../../images/icon-code-upgrade-prev-step.png)
    and ![right arrow icon](../../../images/icon-code-upgrade-next-step.png)):
    Navigate to the previous or next step, respectively. 

Liferay @ide@ tracks the state of the Plugins SDK projects. Developers can close
the Code Upgrade View at any step and pick up where they left off by selecting
*Project &rarr; Liferay Code Upgrade Tool...*

+$$$

**Note**: To delete all the project configuration files and restart upgrading
the Plugins SDK projects, the developer can click on the Restart Code Upgrade
icon (![restart icon](../../../images/icon-restart-code-upgrade.png)) in the
view's toolbar. 

$$$


![Figure 3: The Code Upgrade Tool lets developers mark steps done (checkmark) and not done ('X'). Upgrade state is saved so that developers can revisit projects they're not done upgrading.](../../../images/code-upgrade-gears-done-and-not-done.png)

+$$$

**Tip**: Toggling on the Code Upgrade Tool's view icon (![view icon](../../../images/icon-code-upgrade-tool.png))
after the view's been minimized makes it easier to resize. 

$$$

Click the right arrow to advance to the next step.

## Configuring the Upgrade Project [](id=configuring-the-upgrade-project)

The Configure Project step lets the developer specify these things:

- Plugins SDK to upgrade

- Whether to host the Plugins SDK in a new [Liferay Workspace](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace) 

- @product-ver@ server bundle to use

The configuration fields are described below:

**Liferay Plugins SDK Location**: Path to the Plugins SDK to upgrade

**Select Migrate Layout**: 

-   *Upgrade to Liferay Workspace*: Moves the Plugins SDK projects into a new
    Liferay Plugins SDK 7 in subfolder `plugins-sdk` and applies the Workspace
    project layout to the original Plugins SDK root folder.

-   *Upgrade to Liferay Plugins SDK 7*: Upgrades the Plugins SDK to version 7.

**Server Name**: Arbitrary name for the server bundle being installed.

**Bundle URL**: Location of a @product-ver@ bundle.

**Backup SDK into Folder**: Copies the original Plugins SDK to the specified
folder.

![Figure 4: The Configure Project step imports the Plugins SDK projects and configures them to use a @product-ver@ bundle.](../../../images/code-upgrade-configure-project.png)

In your existing Plugins SDK's `build.[username].properties` file, make sure to
set the `app.server.dir` property to refer to your Liferay Portal 6.x bundle.

If the user chooses the option *Upgrade to Liferay Workspace*, clicking the
*Import Projects* button migrates the SDK projects to a Plugins SDK 7 in Liferay
Workspace, installs a @product-ver@ bundle to the Workspace, and imports the
Plugins SDK projects to Liferay @ide@.

To mark a step complete, click on its check mark icon (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)).
To mark a step incomplete, click on its X icon (![x icon](../../../images/icon-code-upgrade-mark-not-done.png)).

## Upgrading Descriptor Files [](id=upgrading-descriptor-files)

The Upgrade Descriptor Files screen lists Plugins SDK project descriptor files to upgrade. 

![Figure 5: The Upgrade Descriptor Files screen lists Plugins SDK project descriptor files to upgrade.](../../../images/code-upgrade-upgrade-descriptor-files.png)

Clicking on a filename opens a text compare view that shows the difference
between the original descriptor file and the proposed upgraded descriptor file. 

![Figure 6: The Upgrade Descriptor File step adapts the descriptor files to @product-ver@. Clicking on a listed descriptor file compares the adapted descriptor with the original.](../../../images/code-upgrade-compare-descriptor-files.png)

Clicking the *Find...* button on an upgrade that's been restarted detects and
lists all descriptor files that need upgrading. On initial upgrade, the
descriptor files are listed already. 

Clicking the *Upgrade...* button applies ALL the descriptor file changes.

## Finding Breaking Changes [](id=finding-breaking-changes)

The Find Breaking Changes step lists lines of code affected by API changes,
describes the changes, and explains how to adapt to the changes. 

Clicking the project selection icon (![project selection icon](../../../images/icon-code-upgrade-tool-select-project.png))
opens a window for selecting projects in which to find and fix breaking changes.
After selecting projects, click *OK* to apply any automatic proposed fixes.

- Upper-left pane lists the projects that have outstanding breaking changes.

- Lower-left pane lists the currently selected project's breaking changes.

- Right pane shows the breaking change documentation.

![Figure 7: The Finding Breaking Changes step shows you where breaking changes affect your plugin. In addition, it provides background information on each change and explains how to adapt to it.](../../../images/code-upgrade-find-breaking-changes.png)

The upper left panel's *Code Problems* folder shows a tree of projects and
project files that have code problems. On selecting a project file, its lines
that have problems are listed in the panel below. A line number accompanies each
problem. As developers resolve problems, they can mark them resolved by checking
on their respective checkboxes in the *Resolved* column.

On selecting a problem from the list, the for following information appears in
the right panel:

-   **Date**: When the change was introduced to the product.

-   **JIRA Ticket**: Corresponding issue number.

-   **What Changed?**: Summary of the code change.

-   **How should I update my code?**: Instructions on how to adapt your code in
    response to the change.

-   **Why was the change made?**: Reasons for the change.

Clicking on the bandage icon (![bandage icon](../../../images/icon-bandage.png))
brings up a project selection dialog. Clicking *OK* in the dialog corrects the
project's problems that are clearly understood and easy to fix (e.g., package
imports and @product@ version properties) automatically.

For each problem, select it from the tree, read its documentation, and double
click the problem to fix it an editor. 

+$$$

**Tip**: To use the entire Code Upgrade Tool view to show the current breaking
change's documentation, click on the close Hide Tree icon (![hide tree icon](../../../images/icon-hide-breaking-change-tree.png)).

$$$

Right selecting a problem shows the following options:

- *Mark done*: Indicate it's resolved

- *Mark undone*: Indicate it isn't resolved

- *Ignore*: Skip it

- *Correct automatically*: Let the Code Upgrade Tool resolve it automatically

- *Ignore all problems of this type*: Skip addressing this type of problem

![Figure 8: The Code Upgrade Tool can correct some problems automatically. Also it lets developers ignore the problem, ignore problems like it, or mark it done/undone.](../../../images/code-upgrade-problem-options.png)

To revisit problems ignored previously, click on the Open Ignored List icon (![ignored list icon](../../../images/icon-code-upgrade-list-ignored-problems.png)). 

## Rebuilding Services [](id=rebuilding-services)

The Build Service step re-runs Service Builder on the projects and removes
legacy Service Builder files from them. 

![Figure 9: The Build Service step re-runs Service Builder on the projects and removes legacy Service Builder related files from them.](../../../images/code-upgrade-build-service.png)

## Upgrading Layout Templates [](id=upgrading-layout-templates)

This step lets developers view and apply proposed layout template upgrades. 

![Figure 10: The Upgrade Layout Templates step lists all layout templates to upgrade.](../../../images/code-upgrade-upgrade-layout-templates.png)

Clicking the *Find...* button lists the layout template files that need
upgrades.

Double clicking on a layout template opens it in a view that compares its
original code to the proposed upgraded code.

Clicking the *Upgrade...* button applies the proposed upgrades to all the layout
templates.

The word *Finished* shows after each upgraded layout template's name. 

## Converting Custom JSP Hooks to Modules or Fragments [](id=converting-custom-jsp-hooks-to-modules-or-fragments)

This step converts the SDK's custom JSP hooks to modules or module fragments. It
lets the developer compare the SDK's custom JSP and the newly generated JSP with
the original JSP from Liferay Portal 6.2.

The UI provides these options:

**Converted Project Location**: Path to the resulting module or module fragment
projects (e.g., `<workspace>/modules/`)

**Select Projects**: Shows a window from which to select projects that have
custom JSP hooks.

**Refresh Results**: Synchronizes the JSP file trees to reflect all new changes.

**Clear Results**: Empties the panels of all content

![Figure 11: The Conver Custom JSP Hooks lets developers select JSP hook plugin projects to convert to modules or module fragments. After they're converted, the panels make the originals available for comparing with the adapted custom JSPs.](../../../images/code-upgrade-convert-custom-jsp-hooks.png)

To convert the Liferay Portal 6.2 custom JSP hooks to new modules or module
fragments for @product-ver@, follow these steps:

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
developer must decide how to implement the customization in @product-ver@. It
might make sense to create a similar new JSP in a new or converted module
project. 

![Figure 17: The Code Upgrade Tool indicates whether a custom JSP is *found* or *unfound* (not found) in Liferay Portal 6.2.](../../../images/code-upgrade-converted-jsp-hooks-found-unfound.png) 

Referring to the JSP comparisons facilitates implementing the new JSP
customization for @product-ver@. 

The Code Upgrade Tool creates a `ConvertedCustomJspBag` class in the converted
module project. It tells the OSGi container that the module modifies a core JSP.
The class's path in the module project is
`src/main/java/codeupgrade.corejsphook/ConvertedCustomJspBag.java`.  

![Figure 18: Modules for core JSP customizations are created with a `ConvertedCustomJspBag` class that informs the OSGi container that the module provides a customized JSP.](../../../images/code-upgrade-converted-core-jsp-hook-module.png)  

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

## Building the Plugins SDK Projects [](id=building-the-plugins-sdk-projects)

This step rebuilds the Plugins SDK projects. To rebuild the projects click on
the *Build...* button. 

![Figure 20: Developers can rebuild their SDK projects at any time.](../../../images/code-upgrade-build.png)

## Upgrade Summary [](id=upgrade-summary)

The last step lists all the steps. A check mark indicates the step is done, an
'X' indicates it's not done, and a question mark indicates it's status isn't
decided. To revisit a step, click its name. 

![Figure 21: The Summary step lists each step's status.](../../../images/code-upgrade-summary.png)

You can always revisit the Liferay Code Upgrade view by selecting *Project
&rarr; Liferay Code Upgrade Tool...*

You've leveraged Liferay's Code Upgrade Tool to start adapting your existing
plugins to @product-ver@. As you continue upgrading your plugins you can re-open
the Code Upgrade Tool and track progress as you wish.  

## Related Articles [](id=related-articles)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
