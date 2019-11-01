---
header-id: propagation-of-changes
---

# Propagation of Changes

[TOC levels=1-4]

If you make an update to a Page Fragment or Content Page Template, it doesn't
automatically propagate changes, but you can access the *Usages and Propagation*
page to selectively propagate changes.

1.  From the Site Administration menu, go to *Site Builder* &rarr; *Page 
    Fragments*

2.  Select the Collection containing the changed fragment.

3.  Open the *Actions* menu (![Actions](../../../../../images/icon-actions.png)) for
    the Fragment and select *View Usages*.

The *Usages and Propagation* page shows a list of every Page, Page Template, 
and Display Page that uses the selected Page Fragment. You can then selectively
propagate Fragment changes to any or all of the pages listed. You can use the
various filters and selection options to apply updates to pages quickly.

![Figure 1: Viewing the Usages and Propagation page.](../../../../../images/fragment-usages-and-propagation.png)

| **Note:** Beginning in Liferay DXP 7.2 SP1+ and Liferay Portal 7.2 GA2+, you
| can propagate changes from global Fragments to their usages on child Sites.
| See 
| [Creating and Managing Fragments](/docs/7-2/user/-/knowledge_base/u/creating-page-fragments#creating-and-managing-fragments)
| for more information on global Fragments.

To update a page or template,

1.  Select the page or pages you want to update by checking the box next to the
    page name.

2.  Click the *Propagate* icon (
    ![Propagate](../../../../../images/icon-propagate.png))

    After you propagate changes, visit any affected page to verify there were no
    unexpected side effects of the changes.

    | **Note:** Developers or others testing a Fragment can enable Fragment change
    | propagation from the Control Panel. You can learn more about this
    | [here](/docs/7-2/frameworks/-/knowledge_base/f/managing-fragments-and-collections#propagating-fragment-changes-automatically).
    | It's recommended to only leverage this functionality during testing, as
    | automatic propagation on the production environment can cause unintended
    | consequences.

    Changes to existing `editable` fields are not propagated since this
    overwrites content currently in content pages. To force propagation to
    content in an `editable` field, a developer must change the field ID. Any
    content created in that field no longer appears in the Content Page
    when the changes are propagated, but it remains in the database and can
    be retrieved using the old ID.

Next you'll learn how to create your own Page Fragments.
