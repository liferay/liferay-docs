---
header-id: liferay-upgrade-planner
---

# Liferay Upgrade Planner

[TOC levels=1-4]

The Liferay Upgrade Planner provides an automated way to adapt your
installation's data and legacy plugins to your desired @product@ upgrade
version. We recommend leveraging this tool for any of the following upgrades:

- Liferay Portal 6.2 &rarr; @product@ 7.0, 7.1, or 7.2
- @product@ 7.0 &rarr; @product@ 7.1 or 7.2
- @product@ 7.1 &rarr; @product@ 7.2

For step-by-step instructions for following the two upgrade paths, see the
following documentation:

- [Data Upgrade](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver)
- [Code Upgrade](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver)

The Upgrade Planner is provided in
[Liferay Dev Studio](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio) 
(versions 3.6+). Here's what the Upgrade Planner does: 

<!-- Standalone app is planned, but not available yet. -->

- Updates your development environment.
- Identifies code affected by the API changes.
- Describes each API change related to the code.
- Suggests how to adapt the code.
- Provides options, in some cases, to adapt code automatically.
- Transfers database and server data to your new environment.

Even if you prefer tools other than Dev Studio (which is based on Eclipse), you
should upgrade your data and legacy plugins using the Upgrade Planner first--you
can use your favorite tools afterward.

To start the Upgrade Planner in Dev Studio, do this:

1.  Navigate to *Project* &rarr; *New Liferay Upgrade Plan...*.

2.  In the New Liferay Upgrade Plan wizard, assign your plan a name and choose
    an upgrade plan outline. The data and code upgrade processes are separate,
    so you must step through each process independently.

3.  Choose your current Liferay version and the new version you're upgrading to.

4.  If you chose to complete a code upgrade, you must also select the folder
    where your legacy plugins reside (e.g., Plugins SDK for Liferay 6.2
    projects).

5.  Click *Finish*.

![Figure 1: Configure your upgrade plan before beginning the upgrade process.](../../../images/upgrade-plan-wizard.png)

Switch to the new Liferay Upgrade Planner perspective (prompted automatically).
You're now offered several windows in the UI:

- *Project Explorer:* displays your legacy plugin environment and new
  development environment. It also displays your
  [upgrade problems](/docs/7-2/tutorials/-/knowledge_base/t/fixing-upgrade-problems)
  that are detected during the *Fix Upgrade Problems* step.
- *Liferay Upgrade Plan:* outlines the upgrade plan's steps and step summaries.
- *Liferay Upgrade Plan Info:* shows official documentation that describes the
  upgrade step.

To progress through your upgrade plan, click the steps outlined in the Liferay
Upgrade Plan window. Each step can have several options:

- *Click to preview:* previews what an automated step will perform.
- *Click to perform:* executes an automated process provided with the step. This
  is only offered for steps where the Upgrade Planner can assist.
- *Click when complete:* marks the step as complete. This is only offered when
  the Upgrade Planner cannot provide automated assistance and, instead, only
  offers documentation to assist in completing the step manually.
- *Restart:* marks a completed step as unfinished. The step is performed again
  if automation is involved.
- *Skip:* skips the step and jumps to the next step in the outline.

![Figure 2: You can preview the Upgrade Planner's automated updates before you perform them.](../../../images/preview-upgrade-planner-changes.png)

Great! You now have a good understanding of the Liferay Upgrade Planner's UI and
how to get started. Visit the
[Data Upgrade](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver) and
[Code Upgrade](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver)
sections for more information on those upgrade processes.
