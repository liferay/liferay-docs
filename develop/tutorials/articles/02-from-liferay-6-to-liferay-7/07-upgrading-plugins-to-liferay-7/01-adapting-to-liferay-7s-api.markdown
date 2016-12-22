# Adapting to @product-ver@'s API with the Code Upgrade Tool [](id=adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

The first upgrade process step is to adapt your existing plugin's code to
@product-ver@'s API. @product-ver@ is a major release that contains significant API
changes. As part of the modularization process, many packages have been renamed
to adopt a new standard. But each API change has been carefully documented to
explain what changed, how to adapt to the change, and why the change was made.

To go above and beyond documenting the changes, there's the Code Upgrade
Tool: a tool that knows about these changes, analyzes your code, and suggests
how to adapt the code to the new APIs.

The Liferay Code Upgrade Tool (initially called the Migration Tool) is in
Liferay @ide@. You should use the tool to adapt code to @product-ver@; you can
switch back to your favorite tool afterwards.

This tutorial shows you how to adapt existing plugin code to @product-ver@'s
API. As a prerequisite, you set up your existing traditional plugin in a
@product-ver@ Plugins SDK, in Liferay @ide@. Then you find your plugin's
dependencies and configure them. Finally, you use the Code Upgrade Tool to
address upgrade issues. It's all straightforward.

+$$$

Note: Even if Liferay @ide@ (which is based on Eclipse) isn't your favorite,
you should use it to leverage the Code Upgrade Tool. When you're done adapting
your code, you can go back to your preferred IDE and tool chain. 

$$$

Let's start by setting up your plugin in a @product-ver@ Plugins SDK using
Liferay @ide@.

## Setup [](id=setup)

The Code Upgrade Tool is available in Liferay @ide@ (versions 3.0 and newer). 

If you already have Liferay @ide@ (@ide@), upgrade it to version 3.0 or newer.
Otherwise, download it from the [downloads page](https://www.liferay.com/downloads).

Next, you need a @product-ver@ Plugins SDK for your traditional plugin. The new SDK
is available on the [downloads page](https://www.liferay.com/downloads).

Then, either move your plugin into the @product-ver@ Plugins SDK or unzip the 
@product-ver@ Plugins SDK on top of your existing Plugins SDK. This upgrades it 
to the latest version. 

In your `build.[username].properties` file, make sure to set the
`app.server.type` and `app.server.parent.dir` properties to refer to your
@product@ installation.

In @ide@, set up your Liferay server and plugin project as follows:

1.  Add a Liferay server and underlying runtime environment that targets your
    @product@ installation.

2.  Import your plugin project. Select *File &rarr; Import &rarr; Liferay &rarr;
    Liferay Plugins SDK Project* to bring up the wizard for importing and
    configuring your project.

Your existing plugin project, along with its Plugins SDK, appears in @ide@.
You're ready to adapt the plugin to @product-ver@!

## Adapting to the API with the Code Upgrade Tool [](id=adapting-to-the-api-with-the-code-upgrade-tool)

The Code Upgrade Tool identifies areas in your code that need to be adapted to
Liferay's APIs changes. As @product-ver@ was being developed, some of the changes
were unavoidable and resulted in problems that affect plugin upgrades. They're
commonly known as *breaking changes* and are captured in @product@'s [Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes)
document.

Liferay's Code Upgrade Tool finds and describes the upgrade issues. You can
either resolve each issue yourself or, if the tool enables an auto-correct
option for the issue, you can apply the tool's automatic fix. As you resolve the
upgrade problems, mark them complete in the Code Upgrade Tool's list.

To start the Code Upgrade Tool, follow these steps:

1. In the *Project Explorer*, right-select your plugin project.

2. Select *Liferay &rarr; Find Liferay 7 breaking API changes*

The following view appears.

![Figure 1: This view shows you where breaking changes affect your plugin. In addition, it provides background information on each change and explains how to adapt to it.](../../../images/from-liferay-6-upgrade-problems.png)

Upgrade problems are reported by file and line number. On selecting a problem
from the list, the following information about the problem appears: 

-  **Date**: When the change was introduced to the product.

-  **JIRA Ticket**: Corresponding issue number.

-  **What Changed?**: Summary of the code change.

-  **How should I update my code?**: Instructions on how to adapt your code in
    response to the change. 
    
-  **Why was the change made?**: Reasons for the change.

Here's the Code Upgrade Tool workflow:

1.  Double-click a problem in the list to go to the problem in the affected
    file.
                    
2.  Correct the problem manually or automatically, if the Code Upgrade Tool
    offers an automatic fix. 

    To verify whether there is an automatic correction, right-click on the
    problem in the problem list. If there's a *Correct Automatically* option,
    you can select it to apply that correction. 

    ![Figure 2: The Code Upgrade Tool provides auto-correction for some issues.](../../../images/from-liferay-6-correct-automatically.png)

When an auto correction is applied to the code, an information icon (i) appears
next to the line of code. The icon identifies the affected line of code and
displays the issue's title when you hover over the icon. 

    ![Figure 3: On applying auto-correction, an information icon appears next to the affected line of code](../../../images/from-liferay-6-upgrade-tool-at-line.png)

3.  Mark the problem resolved.

4.  If more unresolved problems exist, go back to step 1.

For more details on an issue, inspect its JIRA ticket by visiting
<https://issues.liferay.com>, entering the ticket number in the search box, and
clicking the search icon. The issue's description and comments provide relevant
information.

Resolving all of a plugin's reported upgrade problems makes for a great start
in adapting your plugin to @product-ver@.

## Summary [](id=summary)

Congratulations on completing the first step in upgrading your plugin to @product@
7! Let's consider all that you've done.

You set up your plugin in a @product-ver@ Plugins SDK, imported it into @ide@,
and set up a @product-ver@ server in it. Then, you leveraged Liferay's Code
Upgrade Tool to hunt down and adapt to breaking API changes. Way to go!

It's onward and upward with upgrading your traditional plugins on @product-ver@!

## Related Articles [](id=related-articles)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)

