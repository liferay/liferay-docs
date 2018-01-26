# Adapting to @product-ver@'s API with the Code Upgrade Tool [](id=adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

The first and easiest plugin upgrade step is adapting to  @product-ver@'s API.
@product-ver@'s modular architecture 
[benefits Liferay Portal 6 developers](/develop/tutorials/-/knowledge_base/7-1/benefits-of-liferay-7-for-liferay-6-developers). 
Modularizing the product, however, required renaming many packages. Of course, 
we documented each API change
([7.0 breaking changes](/develop/reference/-/knowledge_base/7-0/breaking-changes)
and
[7.1 Breaking Changes](/develop/reference/-/knowledge_base/7-1/breaking-changes))
to explain what changed, why it changed, and how to adapt to it. 

Going above and beyond documentation, Liferay provides the Code Upgrade Tool in 
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/installing-liferay-ide) 
(versions 3.1+). Here's what the Code Upgrade Tool does: 

- Identifies code affected by the API changes.
- Describes each API change related to the code.
- Suggests how to adapt the code.
- Provides options, in some cases, to adapt code automatically.

Even if you prefer tools other than Liferay @ide@ (which is based on Eclipse), 
you should upgrade Plugins SDK plugins using the Code Upgrade Tool first--you 
can use your favorite tools afterward. This tutorial walks you through the steps 
required to use the Code Upgrade Tool: 

**Code Upgrade Tool Steps**

 Step | Name | Description |
:----- | :------ | :------------ |
1.  | [Welcome to the Liferay Code Upgrade Tool](#step-1-welcome-to-the-liferay-code-upgrade-tool) | Introduces the Code Upgrade Tool. |
2.  | [Configure the Project](#step-2-configure-the-project) | Imports an existing Maven project or Plugins SDK project, and prepares its plugins for upgrading. |
Maven only | [Upgrade POM Files](#upgrade-pom-files) | Upgrades POM files so they use the latest Maven plugins and Liferay dependencies. |
4.  | [Find Breaking Changes](#step-4-find-breaking-changes) | Finds breaking changes, describes them, and prescribes adaptations (some of which can be applied automatically). |
3.  | [Upgrade Descriptor Files](#step-3-upgrade-descriptor-files) | Moves descriptor files to their new versions. |
5.  | [Build Services](#step-5-build-services) | Runs Service Builder on plugins that use it. |
6.  | [Upgrade Layout Templates](#step-6-upgrade-layout-templates) | Upgrades layout templates. |
7.  | [Convert Custom JSP Hooks](#step-7-convert-custom-jsp-hooks) | Converts JSP hooks to modules or module fragments. |
8.  | [Build](#step-8-build) | Compiles the plugins |
9.  | [Summary](#step-9-summary) | Lists each upgrade step's status |

After completing [step 2](#step-2-configure-the-project), you can execute the
steps in any order. Also, be aware that you may not be presented with all nine
steps for your project. For example, if you don't have a JSP hook in your
Plugins SDK, step 7 will not be available.

Launch the Code Upgrade Tool now. 

## Launching the Code Upgrade Tool [](id=launching-the-code-upgrade-tool)

Launch the Code Upgrade Tool to start new upgrades or continue previously 
started upgrades:

1.  Upgrading plugins with the Code Upgrade Tool for first time requires a new
    empty Eclipse workspace. Bring up the Workspace Launcher by selecting *File
    &rarr; Launch Workspace &rarr; New...*. Then create a workspace. 

    +$$$

    **Note**: The current tool requires using a new empty Eclipse workspace.
    Future tool versions will relax this requirement. 

    $$$

    <!-- Greg told me the empty workspace requirement may be relaxed in Liferay
    IDE 3.2, but it'll stay a requirement for version 3.1. -Cody -->

    <!-- Still a requirement for IDE 3.2. -Cody -->

2.  Select *Project &rarr; Liferay Code Upgrade Tool...* to launch the Code
    Upgrade Tool. 

    ![Figure 1: The Code Upgrade Tool is available in the *Project* menu.](../../../images/code-upgrade-invoke-tool.png)

The Welcome screen appears. 

## Step 1: Welcome to the Liferay Code Upgrade Tool [](id=step-1-welcome-to-the-liferay-code-upgrade-tool)

This screen describes the tool's navigation and key functions. 

![Figure 2: The Welcome page introduces the tool. You navigate between steps by clicking the left and right arrows or a step's gear icon.](../../../images/code-upgrade-welcome.png) 

**Navigation**

 Icon | Description |
:----- | :------------ |
 ![gears image](../../../images/icon-code-upgrade-step-gears.png) | Represents code upgrade steps. Clicking a step's gear takes you to that step. |
 ![check mark icon](../../../images/icon-code-upgrade-mark-done.png) | Indicates step completion and takes you to the next step. |
 ![x icon](../../../images/icon-code-upgrade-mark-not-done.png) | Indicates an incomplete step and advances you to the next step. |
 ![left arrow icon](../../../images/icon-code-upgrade-prev-step.png) | Navigate to the previous step. |
 ![right arrow icon](../../../images/icon-code-upgrade-next-step.png) | Advance to the next step. |

Liferay @ide@ tracks each plugin's state. You can close the Code Upgrade Tool 
view at any step and re-launch it later to resume upgrading plugins. 

+$$$

**Note**: To restart upgrades from scratch, click on the Restart Code Upgrade 
icon (![restart icon](../../../images/icon-restart-code-upgrade.png)) in the 
view's toolbar. 

$$$


![Figure 3: The Code Upgrade Tool marks steps complete (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)) or incomplete (![x icon](../../../images/icon-code-upgrade-mark-not-done.png)). It also saves its state so you can resume upgrades later.](../../../images/code-upgrade-gears-done-and-not-done.png)

+$$$

**Tip**: The Code Upgrade Tool view is easiest to work with when it's resizable.
To enable resizing, minimize the view and then click its icon (![view icon](../../../images/icon-code-upgrade-tool.png)). 

$$$

Now you're ready to configure your project. 

## Step 2: Configure the Project [](id=step-2-configure-the-project)

The Code Upgrade Tool can upgrade Liferay 6.2 plugins built with Maven or the
Plugins SDK. Browse to the Plugins SDK or Maven project root location to proceed
with importing your plugin into the workspace.

### Plugins SDK Plugin [](id=plugins-sdk-plugin)

If you're upgrading a Plugins SDK plugin, you must integrate its Plugins SDK
with a @product-ver@ server before beginning the upgrade. You can work with
plugins in your current Plugins SDK, or in a copy of the Plugins SDK in a new 
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace). 
Liferay Workspace offers the best of both worlds: it lets you continue to work 
with plugins in an SDK while facilitating plugin migration to modules or 
Workspace WAR projects. 

+$$$

**Note:** If the *Upgrading to Liferay Plugins SDK 7* option is unavailable to
select, this means that your Plugins SDK has already been upgraded to version 7.
To check this, navigate to your Plugins SDK's `build.properties` file; if the
`lp.version` property is set to `7.1.0`, your Plugins SDK is already upgraded.
In some cases, the version can be auto-generated if your Plugins SDK was
mistakenly linked to a @product-ver@ installation.

$$$

Configure the upgrade project using the option you prefer. 

#### Option 1: Upgrading to Liferay Workspace [](id=option-1-upgrading-to-liferay-workspace)

Upgrading to Liferay Workspace does these things:

-   Converts the SDK root folder to a new Liferay Workspace
-   Upgrades the Plugins SDK to a Liferay Plugins SDK 7
-   In the Workspace, migrates the Plugins SDK to a new `plugins-sdk` subfolder
-   Installs a @product-ver@ server bundle to a new `bundles` subfolder

Use these steps to do this:

1.  Configure the project: 

    -   *Liferay Plugins SDK Location*: Path to the Plugins SDK to upgrade 
    -   *Select Migrate Layout*: Select *Upgrade to Liferay Workspace* 
    -   *Server Name*: Arbitrary name for the @product-ver@ server bundle 
    -   *Bundle URL*: Location of a server bundle to download and install 
    -   *Backup SDK into Folder*: Whether to copy the original Plugins SDK to
        the specified folder 

    ![Figure 4: The Configure Project step imports Plugins SDK projects and integrates them with a @product-ver@ server.](../../../images/code-upgrade-configure-project.png)

2.  Click the *Import Projects* button. 

The Liferay Workspace and the Plugins SDK projects are imported to Liferay @ide@. 

To mark the step complete, click the check mark icon (![check mark icon](../../../images/icon-code-upgrade-mark-done.png)). 

#### Option 2: Upgrading to Liferay Plugins SDK 7 [](id=option-2-upgrading-to-liferay-plugins-sdk-7)

Upgrading to Liferay Plugins SDK 7 does these things:

-   Upgrades the Plugins SDK to a Liferay Plugins SDK 7
-   Integrates the SDK with the specified @product-ver@ server

Use these steps to do this:

1.  Configure the project: 

    -   *Liferay Plugins SDK Location*: Path to the Plugins SDK to upgrade. 
    -   *Select Migrate Layout*: Select *Upgrade to Liferay Plugins SDK 7*. 
    -   *Liferay Server Name*: Select or add a @product-ver@ server. 
    -   *Backup SDK into Folder*: Whether to copy the original Plugins SDK to 
        the specified folder. 

    ![Figure 5: Defining a @product@ server is straightforward.](../../../images/code-upgrade-define-new-server.png)

2.  Click the *Import Projects* button. 

The Plugins SDK projects are imported to Liferay @ide@. 

+$$$

**Note**: If the Plugins SDK is the same version as the Liferay Portal bundle, 
you need only set the SDK's `app.server.parent.dir` property to the  bundle
path. Otherwise, you must also set the `app.server.type` and
`app.server.[server-type].dir` properties. 

$$$

To mark the step complete, click the check mark icon
(![check mark icon](../../../images/icon-code-upgrade-mark-done.png)). 

### Maven Plugin [](id=maven-plugin)

To configure your Maven plugin project(s) in the Code Upgrade Tool, browse to
the parent project in which they reside. Then select *Import Projects* to import
the Maven plugins into the Eclipse workspace.

When upgrading a Maven plugin, there is one extra step compared to importing a
Plugins SDK project: upgrading your POM files. You'll see how this works next.

#### Upgrade POM Files [](id=upgrade-pom-files)

The Code Upgrade Tool scans through all of your Maven project's POM files. Here
is an outline of the types of changes it suggests:

- Remove declarations of the `liferay-maven-plugin`.
- Add new Maven plugins like
    - CSS Builder
    - Service Builder
    - Theme Builder
- Find dependency elements that point to old Liferay Portal 6.2 artifacts and
  update them. For example, `portal-service` dependency elements will be updated
  to `com.liferay.portal.kernel`.

For more information on the upgrade process for your Maven build, see
[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)

If the tool finds any necessary updates, it lists them in the view.

![Figure 6: Defining a @product@ server is straightforward.](../../../images/upgrade-tool-pom-list.png)

You can double-click any of the POM files to display a preview of the changes
that will be made if you upgrade that file.

![Figure 7: Defining a @product@ server is straightforward.](../../../images/code-upgrade-pom-compare.png)

Select the `pom.xml` files you'd like to upgrade and click *Upgrade Selected*.

Next, you'll begin updating your project's descriptor files.

## Step 3: Find Breaking Changes [](id=step-3-find-breaking-changes)

*Breaking changes* are API changes that affect (and might *break*) existing API 
consumers and implementations. The Code Upgrade Tool finds affected plugin code 
and explains how to adapt it. 

Breaking changes documentation contains the following information for each 
change: 

-   *Date*: Date the change was introduced	
-   *JIRA Ticket*: Corresponding issue number
-   *What Changed?*: Change summary
-   *How should I update my code?*: Adaptation instructions
-   *Why was the change made?*: Reasons for the change

The tool helps you address problems. It offers to auto-correct problems that
are clearly understood and easy to fix (e.g., package imports and @product@
version properties). It facilitates opening affected code in an editor to check
and modify it. Lastly, the tool lets you mark progress in resolving the 
problems. 

You can use one or both of the following options to resolve problems.

-   **Option 1: Correct Problems Automatically First**
-   **Option 2: Address Problems Individually**

The following sections explain each option. 

### Option 1: Correct Problems Automatically First [](id=option-1-correct-problems-automatically-first)

Use this option (![bandage icon](../../../images/icon-bandage.png)) to 
fix easy problems right away automatically (you can fix tougher problems later).
Follow these steps to use this option: 

1.  Click the *Automatically Correct Problems* icon (![bandage icon](../../../images/icon-bandage.png))
    to open the project selection window. 

2.  Select projects in which to correct problems automatically and click the 
    *OK* button. After addressing those problems, the Find Breaking Changes
    window appears and lists any remaining problems. 

3.  Address any remaining problems individually (as described next). 

### Option 2: Address Problems Individually [](id=option-2-address-problems-individually)

This option (![project selection icon](../../../images/icon-code-upgrade-tool-select-project.png)) 
finds and describes all the problems, leaving you to address them on a 
case-by-case basis. Problems that the tool can auto-fix have the *Correct 
automatically* option. Follow these steps to address problems individually: 

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

3.  In the upper left panel's *Code Problems* tree, select a project file. The 
    bottom left panel lists the file's problems. 

4.  Select a problem to view its description in the right side panel. 

5.  Right click the problem to show options for handling it. 

    ![Figure 9: The Code Upgrade Tool can correct some problems automatically. There are also options to ignore the problem, ignore problems like it, or mark it done/undone.](../../../images/code-upgrade-problem-options.png)

6.  Based on the problem description, handle it using one of the following options: 

    Option | Instructions |
    :------- | :------------- |
    Correct automatically (if available) |  1.  Right click the problem.<br>2.  Select *Correct automatically*.<br>3. Mark the problem resolved by checking its checkbox in the *Resolved* column.  |
    Fix manually | 1.  Double click the problem to open an editor.<br>2.  Fix the problem based on the instructions in *How should I update my code?*.<br>3.  Mark the problem resolved by checking its checkbox in the *Resolved* column. |
    Ignore | 1.  Right click the problem.<br>2.  Ignore this problem (*Ignore*) or all problems like it (*Ignore all problems of this type*). Ignoring problems marks them for you to revisit. Clicking the *Open Ignored List* icon (![ignored list icon](../../../images/icon-code-upgrade-list-ignored-problems.png)) lists ignored problems. |

7.  Continue addressing problems individually or try *Correcting Problems
    Automatically* (as explained previously). 

+$$$

**Tip**: To use the entire Code Upgrade Tool view to show breaking change
documentation, click the Hide Tree icon (![hide tree icon](../../../images/icon-hide-breaking-change-tree.png)). 

$$$

Congratulations on addressing breaking change problems! Next, you'll upgrade
your descriptor files. 

## Step 4: Upgrade Descriptor Files [](id=step-4-upgrade-descriptor-files)

The Upgrade Descriptor Files screen lists plugin descriptor files to upgrade. It 
proposes updates for them and lets you compare proposed updates with the current 
content. To compare a descriptor's proposed update with its current content, 
click the descriptor entry in the list. To apply all proposed descriptor file 
updates, click *Upgrade...*. To find plugins whose descriptor files need 
upgrading, click *Find...*. 

![Figure 10: The Upgrade Descriptor Files screen lists Plugins SDK project descriptor files to upgrade.](../../../images/code-upgrade-upgrade-descriptor-files.png)

![Figure 11: The Upgrade Descriptor File step adapts descriptor files to @product-ver@. Clicking a descriptor file opens a window that compares proposed updates to the current content.](../../../images/code-upgrade-compare-descriptor-files.png)

In the next step, you'll build your project's services. 

## Step 5: Build Services [](id=step-5-build-services)

The Build Service step re-runs Service Builder on the projects and deletes 
legacy Service Builder files. To do this, click *Build Services*. 

![Figure 12: The Build Service step re-runs Service Builder on the projects and removes legacy Service Builder files.](../../../images/code-upgrade-build-service.png)

Next, you'll upgrade any layout templates. 

## Step 6: Upgrade Layout Templates [](id=step-6-upgrade-layout-templates)

The Upgrade Layout Templates step proposes Layout Template updates. It
lists Layout Templates for you to view and compare proposed updates with
original content. 

Follow these steps to upgrade your Layout Templates:

1.  Click the *Find...* button to list Layout Templates that need updates. 

2.  To compare a Layout Template's original code with proposed code updates,
    double click its name. 

3.  Click the *Upgrade...* button to apply all proposed Layout Template updates. 

*Finished* shows after each upgraded Layout Template. 

![Figure 13: The Upgrade Layout Templates step lists all Layout Templates to upgrade.](../../../images/code-upgrade-upgrade-layout-templates.png)

Next, you'll convert any custom JSP hooks. 

## Step 7: Convert Custom JSP Hooks [](id=step-7-convert-custom-jsp-hooks)

This step converts custom JSP hooks to modules or module fragments. It lets you 
compare your custom JSPs with original Liferay Portal 6.2 JSPs, and newly 
generated module custom JSPs. 

![Figure 14: The Convert Custom JSP Hooks step lets you select JSP hook plugin projects to convert to modules or module fragments. After they're converted, the originals are available for comparing with the adapted custom JSPs.](../../../images/code-upgrade-convert-custom-jsp-hooks.png)

Use these steps to convert the Liferay Portal 6.2 custom JSP hooks to new 
modules or module fragments for @product-ver@: 

1.  Set the *Converted Project Location* to the Workspace's `modules` folder. 

2.  Click the *Select Projects* button. The *Custom JSP Hook Project* window
    appears. 

3.  In the *Custom JSP Hook Project* window, select projects to convert and
    click *OK*. 

    ![Figure 15: The Code Upgrade Tool lets you select custom JSP hook projects to upgrade.](../../../images/code-upgrade-custom-jsp-hook-project-seclector.png)

The JSP hooks are converted to new modules or module fragments in the Workspace. 

![Figure 16: Custom JSP hook projects are converted to module or module fragment projects.](../../../images/code-upgrade-converted-jsp-hooks-in-modules.png)

The Code Upgrade Tool helps you review the changes, so you can make any 
additional changes. It lists the 6.2 custom JSPs and the new converted custom 
JSPs for you to compare with the 6.2 originals: 

-   To compare a 6.2 custom JSP with the original, click the custom JSP 
    filename. 
-   To compare a newly converted 7.x custom JSP with the original, click the 7.x 
    custom JSP filename. 

Referring to these comparisons helps you to implement new JSP customizations for 
@product-ver@. 

![Figure 17: The Code Upgrade Tool lets you compare your 6.2 and new 7.x custom JSPs with Liferay Portal 6.2 originals.](../../../images/code-upgrade-convert-jsp-hooks.png)

![Figure 18: This view compares an original 6.2 JSP with a 6.2 custom JSP.](../../../images/code-upgrade-converted-jsp-hooks-compare-6.2-original.png)

![Figure 19: This view compares the new module's 7.x custom JSP with the Liferay Portal 6.2 original. ](../../../images/code-upgrade-converted-jsp-hooks-compare-6.2-original-to-7.0.png)

If the 6.2 hook plugin's JSP didn't customize an existing Liferay Portal 6.2 
JSP, there's no original and the JSP is marked *unfound*. You must then decide 
how to implement the customization for @product-ver@. It might make sense to 
create a similar new JSP in a new or converted module project. 

![Figure 20: The Code Upgrade Tool indicates whether a custom JSP is *found* or *unfound* in Liferay Portal 6.2.](../../../images/code-upgrade-converted-jsp-hooks-found-unfound.png) 

The Code Upgrade Tool creates a `ConvertedCustomJspBag` class in the converted
module project. It tells the OSGi container that the module modifies a core JSP.
The class resides in the module's `src/main/java/codeupgrade.corejsphook/`
folder. 

![Figure 21: Each Portal core JSP customization module's `ConvertedCustomJspBag` class informs the OSGi container about the customized JSP.](../../../images/code-upgrade-converted-core-jsp-hook-module.png)  

The new upgraded Portal core JSPs are under the module's 
`src/main/resources/META-INF/resources/html/` folder. 

The Code Upgrade Tool converts Liferay Portal 6.2 app custom JSP hooks to
module fragments. Here's a fragment's `bnd.bnd` file: 

![Figure 22: The `Fragment-Host` header specifies the module on which to apply the fragment.](../../../images/code-upgrade-converted-jsp-hook-fragment-bnd.png)

The Code Upgrade Tool also gives you a great deal of flexibility to convert 
custom JSPs as needed. For example, if you make a mistake or aren't fond of 
the results of converting a custom JSP, you can start over. Follow these steps 
to do so: 

1.  Delete the converted module project(s). 

2.  Click on the *Clear Results* button in the *Convert Custom JSP Hooks* view. 

3.  Select the project(s) again from the *Custom JSP Hook Project* window and
    click *OK*. 

The Convert Custom JSP Hooks view lets you upgrade JSP hooks at your leisure. 
Now you're ready to rebuild your projects. 

## Step 8: Build [](id=step-8-build)

You must now rebuild any Plugins SDK projects. To do so, click the *Build...* 
button. 

![Figure 23: You can build SDK projects any time.](../../../images/code-upgrade-build.png)

Great, you're almost done! 

## Step 9: Summary [](id=step-9-summary)

The last screen in the Code Upgrade Tool lists each step's status. A check mark 
indicates the step is done, an X indicates it's not done, and a question mark 
indicates its status is to be determined. To revisit a step, click its name. 

![Figure 24: The Summary step lists each step's status.](../../../images/code-upgrade-summary.png)

You can always revisit the Liferay Code Upgrade view by selecting *Project 
&rarr; Liferay Code Upgrade Tool...*. 

Awesome! You now know how to leverage Liferay's Code Upgrade Tool to adapt 
plugins to @product-ver@. As you continue upgrading your plugins, you can 
re-open the Code Upgrade Tool to facilitate upgrades. 

## Related Topics [](id=related-topics)

[Development Reference](/develop/reference/-/knowledge_base/7-1/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-1/modularizing-an-existing-portlet)

[Tooling](/develop/tutorials/-/knowledge_base/7-1/tooling)
