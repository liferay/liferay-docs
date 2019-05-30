---
header-id: fixing-upgrade-problems
---

# Fixing Upgrade Problems

[TOC levels=1-4]

Now that your development environment build configuration is settled, you can
start upgrading your project(s). The two most common upgrade problems are

- [Broken project dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)
- [Breaking changes](/docs/7-2/tutorials/-/knowledge_base/t/resolving-breaking-changes)

Visit these upgrade problem tutorials for tips on how to fix them.

This tutorial is heavily focused on the Liferay Upgrade Planner. If you're
upgrading your code manually, continue to the listed tutorials above to fix your
code upgrade problems.

You'll begin auto-correcting upgrade problems first.

## Auto-Correcting Upgrade Problems

Initiate this step to auto-correct straightforward updates like

- package imports
- JSP tag names
- Liferay descriptor versions
- XML descriptor content
- etc.

If you choose to preview the auto-correct upgrade problems first, you can view
them in the Project Explorer under the *Liferay Upgrade Problems* dropdown. If
you click one of the upgrade problems listed with the preview, you're offered
documentation in the *Liferay Upgrade Plan Info* window on the proposed change.

Once you've performed this step, the result list is removed.

## Finding Upgrade Problems

Initiating this step finds the upgrade problems that were not eligible for
auto-correction. The problems are listed under the *Liferay Upgrade Problems*
dropdown. If you click one of the upgrade problems listed with the preview,
you're offered documentation in the *Liferay Upgrade Plan Info* window on the
proposed change.

These upgrade problems are available in the
[breaking changes](/docs/7-2/tutorials/-/knowledge_base/t/resolving-breaking-changes)
for the version upgrade you're performing.

The next step is resolving the reported upgrade problems.

## Resolving Upgrade Problems

Now that the upgrade problems have been located, you must resolve them. As you
select each upgrade problem, the documentation for how to adapt your code is
displayed in the *Liferay Upgrade Plan Info* window.

For each upgrade problem node, you're also given the version the upgrade problem
applies to (e.g., when upgrading to @product@ 7.2 from Liferay Portal 6.2, you
could have upgrade problems from the 7.0, 7.1, or 7.2 upgrade). As you step
through the reported problems, mark them as resolved/skipped using the context
menu. You can right-click on the problem in the Project Explorer and choose from
four options:

- Mark done
- Mark undone
- Ignore
- Ignore all problems of this type

Leave this step marked as *Incomplete* until you have resolved all upgrade
problems accordingly.

## Removing Problem Markers

After resolving all the reported upgrade problems, you must remove all
previously found markers because, in most cases, the line number and other
accompanying marker information are out of date and must be removed before
continuing. Initiate this step to remove all the problem markers.

Great! You've fixed all the upgrade problems that could be automatically
detected by the Code Upgrade Tool. Next, you'll take a deeper look at resolving
project dependency errors.

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies">Let's Go!<span class="icon-circle-arrow-right"></span></a>
