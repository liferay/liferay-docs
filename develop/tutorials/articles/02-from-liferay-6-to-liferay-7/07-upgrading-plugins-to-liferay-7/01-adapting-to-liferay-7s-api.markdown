# Adapting to @product-ver@'s API with the Code Upgrade Tool [](id=adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

The first and easiest plugin upgrade step is adapting to
@product-ver@'s API. @product-ver@ is a major release whose new modular
architecture [benefits Liferay Portal 6 developers.](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers)
Modularizing the product, however, required renaming many packages to adopt a
new standard. Of course, Liferay's carefully [documented](/develop/reference/-/knowledge_base/7-0/breaking-changes)
each API change to explain what changed, why it was changed, and how to adapt to
it. 

Going above and beyond documentation, Liferay provides the Code
Upgrade Tool in  [Liferay @ide@](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/installing-liferay-ide)
(versions 3.1 milestones and newer). Here's what it does:

-   Identifies code affected by the API changes

-   Describes each API change related to the code

-   Suggests how to adapt the code

-   Provides options, in some cases, to adapt code automatically

Even if developers prefer tools other than Liferay @ide@ (which is based on
Eclipse), they should upgrade Plugins SDK plugins using the Code Upgrade Tool
first--they can use their favorite tools afterward. 

**Code Upgrade Tool Steps**

 Step | Name | Description |
:----- | :------ | :------------ |
1.  | [Welcome to the Liferay Code Upgrade Tool](#step-1-welcome-to-the-liferay-code-upgrade-tool) | Introduces the Code Upgrade Tool. |
2.  | [Configure the Project](#step-2-configure-the-project) | Upgrades an existing Plugins SDK to a Plugins SDK 7 and prepares its plugins for upgrading.  |
3.  | [Upgrade Descriptor Files](#step-3-upgrade-descriptor-files) | Upgrades descriptor files.  |
4.  | [Find Breaking Changes](#step-4-find-breaking-changes) | Finds breaking changes, describes them, and prescribes adaptations (some of which can be applied automatically).  |
5.  | [Build Services](#step-5-build-services) | Runs Service Builder on plugins that use it.  |
6.  | [Upgrade Layout Templates](#step-6-upgrade-layout-templates) | Upgrades layout templates.  |
7.  | [Convert Custom JSP Hooks](#step-7-convert-custom-jsp-hooks) | Converts JSP hooks to modules or module fragments.  |
8.  | [Build](#step-8-build) | Compiles the plugins.  |
9.  | [Summary](#step-9-summary) | Lists each upgrade step's status.  |

After completing [step 2](#step-2-configure-the-project), the steps can be
executed in any order. 

Launch the Code Upgrade Tool now. 

## Launching the Code Upgrade Tool [](id=launching-the-code-upgrade-tool)

Developers can launch the Code Upgrade Tool to start new upgrades or continue
previously started upgrades. 

1.  Upgrading plugins with the Code Upgrade Tool for first time requires a new
    empty Eclipse workspace. Bring up the Workspace Launcher by selecting *File
    &rarr; Launch Workspace &rarr; New...* and create a workspace. 

    +$$$

    **Note**: The current tool requires using a new empty Eclipse workspace.
    Future tool versions will relax this requirement. 

    $$$

2.  Select *Project &rarr; Liferay Code Upgrade Tool...* to launch the Code
    Upgrade Tool. 

    ![Figure 1: The Code Upgrade Tool is available in the *Project* menu.](../../../images/code-upgrade-invoke-tool.png)

The Welcome screen appears. 

## Step 1: Welcome to the Liferay Code Upgrade Tool [](id=step-1-welcome-to-the-liferay-code-upgrade-tool)

This screen describes the tool's navigation and key functions. 

![Figure 2: The Welcome page introduces the tool. The developer navigates between steps by clicking the left and right arrows or a step's gear icon.](../../../images/code-upgrade-welcome.png) 

**Navigation**

 Icon | Description |
:----- | :------------ |
 ![gears image](../../../images/icon-code-upgrade-step-gears.png) | Gears represent code upgrade steps. Clicking a step's gear takes the user to that step. |
 ![check mark icon](../../../images/icon-code-upgrade-mark-done.png) | Check marks specify step completion and advance the user to the next step. |
 ![x icon](../../../images/icon-code-upgrade-mark-not-done.png) | Xs mark steps incomplete and advance the user to the next step. |
 ![left arrow icon](../../../images/icon-code-upgrade-prev-step.png) | Left arrows navigate the user to the previous  step. |
 ![right arrow icon](../../../images/icon-code-upgrade-next-step.png) | Right arrows advance the user to the next step. |

Liferay @ide@ tracks each plugin's state. Users can close the Code Upgrade Tool
view at any step and re-launch it later, to continue upgrading plugins.

+$$$

**Note**: To restart upgrades from scratch, users can click on the Restart Code
Upgrade icon (![restart icon](../../../images/icon-restart-code-upgrade.png)) in
the view's toolbar. 

$$$


![Figure 3: The Code Upgrade Tool lets developers mark steps complete (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)) and incomplete (![x icon](../../../images/icon-code-upgrade-mark-not-done.png)). Upgrade state is saved so developers can revisit upgrading plugins.](../../../images/code-upgrade-gears-done-and-not-done.png)

+$$$

**Tip**: The Code Upgrade Tool view is easiest to work with when it's resizable.
To enable resizing, minimize the view and then click on its icon (![view icon](../../../images/icon-code-upgrade-tool.png)).

$$$

Advance to the next step.

## Step 2: Configure the Project [](id=step-2-configure-the-project)

Before upgrading a plugin to @product-ver@, its Plugins SDK must be integrated
with a @product-ver@ server. Developers can work with plugins in place in their
current Plugins SDK or in a copy of the Plugins SDK in a new [Liferay Workspace](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace).
Liferay Workspace offers the best of both worlds in that it lets developers
continue to work with plugins in an SDK while facilitating plugin migration to
modules or Workspace WAR projects. 

**Option 1: Upgrading to Liferay Workspace**:

-   Converts the SDK root folder to a new Liferay Workspace. 

-   Upgrades the Plugins SDK to a Liferay Plugins SDK 7. 

-   Migrates the Plugins SDK to a new subfolder called `plugins-sdk`, in the
    Workspace. 

-   Installs a @product-ver@ server bundle to a new subfolder called `bundles`. 

**Option 2: Upgrading to Liferay Plugins SDK 7**:

-   Upgrades the Plugins SDK to a Liferay Plugins SDK 7. 

-   Integrates the SDK with the specified @product-ver@ server. 

Configure the upgrade project using the option you prefer. 

### Option 1: Upgrading to Liferay Workspace [](id=option-1-upgrading-to-liferay-workspace)

1.  Configure the project: 

    -   *Liferay Plugins SDK Location*: Path to the Plugins SDK to upgrade. 

    -   *Select Migrate Layout*: Select *Upgrade to Liferay Workspace*. 

    -   *Server Name*: Arbitrary name for the @product-ver@ server bundle. 

    -   *Bundle URL*: Location of a server bundle to download and install. 

    -   *Backup SDK into Folder*: Whether to copy the original Plugins SDK to
        the specified folder. 

    ![Figure 4: The Configure Project step imports Plugins SDK projects and integrates them with a @product-ver@ server.](../../../images/code-upgrade-configure-project.png)

2.  Click on the *Import Projects* button. 

The Liferay Workspace and the Plugins SDK projects are imported to Liferay
@ide@. 

### Option 2: Upgrading to Liferay Plugins SDK 7 [](id=option-2-upgrading-to-liferay-plugins-sdk-7)

1.  Configure the project: 

    -   *Liferay Plugins SDK Location*: Path to the Plugins SDK to upgrade. 

    -   *Select Migrate Layout*: Select *Upgrade to Liferay Plugins SDK 7*. 

    -   *Liferay Server Name*: Select or add a @product-ver@ server. 

    -   *Backup SDK into Folder*: Whether to copy the original Plugins SDK to the
        specified folder. 

    ![Figure 5: Defining a @product@ server is straightforward.](../../../images/code-upgrade-define-new-server.png)

2.  Click on the *Import Projects* button. 

The Plugins SDK projects are imported to Liferay @ide@.  

+$$$

**Note**: If the Plugins SDK is the same version as the Liferay Portal bundle,
the developer need only set the SDK's `app.server.parent.dir` property to the
bundle path. Otherwise, the developer must also set the `app.server.type` and
`app.server.[server-type].dir` properties. 

$$$

To mark the step complete, click on the check mark icon (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)).

## Step 3: Upgrade Descriptor Files [](id=step-3-upgrade-descriptor-files)

The Upgrade Descriptor Files screen lists plugin descriptor files to upgrade. It
proposes updates for them and lets the user compare proposed updates with the
current content. 

![Figure 6: The Upgrade Descriptor Files screen lists Plugins SDK project descriptor files to upgrade.](../../../images/code-upgrade-upgrade-descriptor-files.png)

To compare a descriptor's proposed update with its current content, click on the
descriptor entry in the list. 

![Figure 7: The Upgrade Descriptor File step adapts descriptor files to @product-ver@. Clicking on a descriptor file opens a window that compares proposed updates to the current content.](../../../images/code-upgrade-compare-descriptor-files.png)

To apply ALL proposed descriptor file updates, click on the *Upgrade...* button.

If a plugin was imported to the Code Upgrade Tool *after* the initial [upgrade project configuration](#step-2-configure-the-project)

Clicking on the *Find...* button finds plugins whose descriptor files need
upgrading. 

## Step 4: Find Breaking Changes [](id=step-4-find-breaking-changes)

*Breaking changes* are API changes that affect (and might *break*)
existing API consumers and implementations. The Code Upgrade Tool finds 
affected plugin code and shows documentation that describes how to adapt it. 

Breaking Change Documentation:

-   *Date*: Date the change was introduced.
	
-   *JIRA Ticket*: Corresponding issue number.
	
-   *What Changed?*: Change summary.
	
-   *How should I update my code?*: Adaptation instructions.
	
-   *Why was the change made?*: Reasons for the change

The tool helps users address problems. It offers to auto-correct problems that
are clearly understood and easy to fix (e.g., package imports and @product@
version properties). It facilitates opening affected code in a editor to check
and modify it. Lastly, the tool lets users mark progress in resolving the
problems.  

Developers can use one or both of the following options to resolve problems:

-   **Option 1: Correct Problems Automatically, First** (![bandage icon](../../../images/icon-bandage.png)):
    Fixes problems that are clearly understood and easy to fix, leaving the
    remaining problems for users to address individually. 

-   **Option 2: Address Problems Individually** (![project selection icon](../../../images/icon-code-upgrade-tool-select-project.png)): 
    Finds problems for users to address at their own pace. Problems that the
    tool can auto-fix have an option called *Correct automatically*. 

The following sections explain each option. 

### Option 1: Correct Problems Automatically, First [](id=option-1-correct-problems-automatically-first)

This option fixes easy problems automatically right away, so users can focus on
fixing tougher problems individually afterward. 

1.  Click the *Automatically Correct Problems* icon (![bandage icon](../../../images/icon-bandage.png))
    to open the project selection window. 

2.  Select projects in which to correct problems automatically and click on the
    *OK* button. 

    After problems, the Find Breaking Changes window appears and
    lists any remaining problems. 

3.  Address any remaining problems individually (as described next).

### Option 2: Address Problems Individually [](id=option-2-address-problems-individually)

This option finds and describes all the problems, so users can decide on a
case-by-case basis how to address them. 

1.  Click the *Find Breaking Changes* icon (![project selection icon](../../../images/icon-code-upgrade-tool-select-project.png))
    to open the project selection window. 

2.  Select the projects in which to find problems and click on the *OK* button. 
    
    The Find Breaking Changes window appears:

    -   Upper-left panel: lists the projects that have outstanding problems. The
        *Code Problems* folder holds a tree of projects and project files that
        have code problems. 

    -   Lower-left panel: lists the currently selected project's problems.

    -   Right panel: shows breaking change documentation for selected problems.

    ![Figure 8: The Finding Breaking Changes step shows users where breaking changes affect plugins. It describes each change and explains how to adapt to it.](../../../images/code-upgrade-find-breaking-changes.png)

3.  In the upper left panel's *Code Problems* tree, select a project file.

    The bottom left panel lists the file's problems.

3.  Select a problem to view its description in the right side panel. 

4.  Right-select the problem to show options for handling it. 

    ![Figure 9: The Code Upgrade Tool can correct some problems automatically. There are also options to ignore the problem, ignore problems like it, or mark it done/undone.](../../../images/code-upgrade-problem-options.png)

5.  Based on the problem description, handle it using one of the following options. 

    Option | Instructions |
    :------- | :------------- |
    Correct automatically (if available) |  1.  Right select the problem.<br>2.  Select the option *Correct automatically*.<br>3. Mark the problem resolved by checking on its checkbox in the *Resolved* column.  |
    Fix manually | 1.  Double click on the problem to open an editor.<br>2.  Fix the problem based on the problem's instructions on **How should I update my code?**.<br>3.  Mark the problem resolved by checking on its checkbox in the *Resolved* column. |
    Ignore | 1.  Right select the problem.<br>2.  Ignore this problem (*Ignore*) or all problems like it (*Ignore all problems of this type*). Ignoring problems marks them for the user to revisit later. Clicking on the Open Ignored List icon (![ignored list icon](../../../images/icon-code-upgrade-list-ignored-problems.png)) lists ignored problems. |

6.  Continue addressing problems individually or try *Correcting Problems
    Automatically* (as explained previously). 

+$$$

**Tip**: To use the entire Code Upgrade Tool view to show breaking change
documentation, click on the Hide Tree icon (![hide tree icon](../../../images/icon-hide-breaking-change-tree.png)). 

$$$

Congratulations on addressing breaking change problems! 

## Step 5: Build Services [](id=step-5-build-services)

The Build Service step re-runs Service Builder on the projects and deletes
legacy Service Builder files. 

![Figure 10: The Build Service step re-runs Service Builder on the projects and removes legacy Service Builder related files from them.](../../../images/code-upgrade-build-service.png)

Click on *Build Service* to build the services and remove all legacy Service
Builder files.

## Step 6: Upgrade Layout Templates [](id=step-6-upgrade-layout-templates)

The Upgrade Layout Templates step proposes updates for Layout Templates. It
lists Layout Templates for users to view and compare proposed updates with
original content. 

![Figure 11: The Upgrade Layout Templates step lists all Layout Templates to upgrade.](../../../images/code-upgrade-upgrade-layout-templates.png)

1.  Click on the *Find...* button to list Layout Templates that need updates. 

2.  To compare a Layout Template's original code with proposed code updates,
    double click on its name. 

3.  Click on the *Upgrade...* button to apply all proposed Layout Template
    updates. 

The word *Finished* shows after each upgraded Layout Template's name. 

## Step 7: Convert Custom JSP Hooks [](id=step-7-convert-custom-jsp-hooks)

This step converts custom JSP hooks to modules or module fragments. It lets
developers compare their custom JSPs with newly generated module custom JSPs and
original Liferay Portal 6.2 JSPs. 

![Figure 12: The Convert Custom JSP Hooks step lets developers select JSP hook plugin projects to convert to modules or module fragments. After they're converted, the panels make the originals available for comparing with the adapted custom JSPs.](../../../images/code-upgrade-convert-custom-jsp-hooks.png)

Convert the Liferay Portal 6.2 custom JSP hooks to new modules or module
fragments for @product-ver@:

1.   Set the *Converted Project Location* to the Workspace's `modules` folder.

2.  Click on the *Select Projects* button. The *Custom JSP Hook Project* window
    appears.

3.  In the *Custom JSP Hook Project* window, select projects to convert and
    click on the *OK* button. 

    ![Figure 13: The Code Upgrade Tool lets developers select custom JSP hook projects to upgrade.](../../../images/code-upgrade-custom-jsp-hook-project-seclector.png)

The JSP hooks are converted to new modules or module fragments in the Workspace. 

![Figure 14: Custom JSP hook projects are converted to module or module fragment projects.](../../../images/code-upgrade-converted-jsp-hooks-in-modules.png)

The Code Upgrade Tool helps developers review the changes, so they can make any
additional changes needed. It lists the 6.2 custom JSPs and the new converted
custom JSPs for developers to compare with the 6.2 originals. 

-   To compare a 6.2 custom JSP with the original, click on the custom JSP filename.

-   To compare a newly converted 7.x custom JSP with the original, click on the
    7.x custom JSP filename. 

![Figure 15: The Code Upgrade Tool lets developers compare their 6.2 and new 7.x custom JSPs with Liferay Portal 6.2 originals.](../../../images/code-upgrade-convert-jsp-hooks.png)

![Figure 16: This view compares an original 6.2 JSP with their 6.2 custom JSP.](../../../images/code-upgrade-converted-jsp-hooks-compare-6.2-original.png)

![Figure 17: This view compares the new module's 7.x custom JSP with the Liferay Portal 6.2 original. ](../../../images/code-upgrade-converted-jsp-hooks-compare-6.2-original-to-7.0.png)

If the 6.2 hook plugin's JSP didn't customize an existing Liferay Portal 6.2
JSP, there's no original. The JSP is marked *unfound*. The developer decides how
to implement the customization for @product-ver@. It might make sense to create
a similar new JSP in a new or converted module project. 

![Figure 18: The Code Upgrade Tool indicates whether a custom JSP is *found* or *unfound* in Liferay Portal 6.2.](../../../images/code-upgrade-converted-jsp-hooks-found-unfound.png) 

Referring to the JSP comparisons facilitates implementing new JSP customizations
for @product-ver@. 

The Code Upgrade Tool creates a `ConvertedCustomJspBag` class in the converted
module project. It tells the OSGi container that the module modifies a core JSP.
The class resides in the module's `src/main/java/codeupgrade.corejsphook/`
folder.  

![Figure 19: Each Portal core JSP customization module's `ConvertedCustomJspBag` class informs the OSGi container about the customized JSP.](../../../images/code-upgrade-converted-core-jsp-hook-module.png)  

The new upgraded Portal core JSPs reside under the module's folder
`src/main/resources/META-INF/resources/html/`. 

The Code Upgrade Tool converts Liferay Portal 6.2 module custom JSP hooks to
module fragments. Here's a fragment's `bnd.bnd` file: 

![Figure 20: The `Fragment-Host` header specifies the module on which to apply the fragment.](../../../images/code-upgrade-converted-jsp-hook-fragment-bnd.png)

Developers can convert custom JSPs as needed.

If you want to restart converting a custom JSP, follow these steps:

1.  Delete the converted module project(s).

2.  Click on the *Clear Results* button in the *Convert Custom JSP Hooks* view. 

3.  Select the project(s) again from the *Custom JSP Hook Project* window and
    click *OK*.

The Convert Custom JSP Hooks view empowers developers to upgrade JSP hooks at
their leisure. 

## Step 8: Build [](id=step-8-build)

This step rebuilds the Plugins SDK projects.

To rebuild the plugin projects, click on the *Build...* button. 

![Figure 21: Developers can build SDK projects any time.](../../../images/code-upgrade-build.png)

## Step 9: Summary [](id=step-9-summary)

The last screen lists each step's status. A check mark indicates the step is done,
an X indicates it's not done, and a question mark indicates it's status is to be
determined.

To revisit a step, click its name. 

![Figure 22: The Summary step lists each step's status.](../../../images/code-upgrade-summary.png)

Developers can always revisit the Liferay Code Upgrade view by selecting *Project
&rarr; Liferay Code Upgrade Tool...*

You've leveraged Liferay's Code Upgrade Tool to adapt plugins to @product-ver@.
As you continue upgrading your plugins, you can re-open the Code Upgrade Tool to
facilitate upgrades. 

## Related Topics [](id=related-topics)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
