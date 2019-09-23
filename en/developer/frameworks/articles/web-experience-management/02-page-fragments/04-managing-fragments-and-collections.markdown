---
header-id: managing-fragments-and-collections
---

# Managing Fragments and Collections

[TOC levels=1-4]

After you create Collections and Fragments, you have a handful of options for 
managing them. You'll explore several management options next.

## Collections Management Menu

To access the collections management menu,

1.  Select the Collection you want to manage from the *Collections* list.

2.  Click on the ![Actions](../../../images/icon-actions.png) menu next to the 
    collection name.
 
3.  Select whether you want to *Edit*, *Export*, *Import*, or *Delete* the
    collection.

    **Edit**: change the name or description for the collection.

    **Export**: download a `.zip` file containing the full collection.

    **Import**: select a `.zip` file to upload with additional Fragments.

    **Delete**: remove the current collection and all of its contents.

Next, you'll learn about the Fragment Management Menu.

## Fragment Management Menu

To access the fragment management menu,

1.  Select the Collection containing the Fragment you want to manage from the 
    *Collections* list.
 
2.  Click on the ![Actions](../../../images/icon-actions.png) menu next to the 
    Fragment name.

3.  Select whether you want to *Edit*, *Rename*, *Move*, *Make a Copy*, *Change 
    Thumbnail* *Export*, or *Delete*.

Did you know you can enable automatic propagation for Fragments? You'll do this
next.

## Propagating Fragment Changes Automatically

| **Note:** Propagating Fragment changes is available in Liferay DXP 7.2 SP1+
| and Liferay Portal GA2+.

By default, when a Fragment developer makes a change to an existing fragment,
the change is not automatically propagated to the pages that were using it. This
gives marketers and page authors more control over the pages they own, avoiding
unexpected changes. For example, if three pages were using the same Fragment, an
update to the Fragment could introduce unintended changes to some of the pages
using it. While this is a safeguard for the production environment, developers
must
[manually propagate Fragment changes](/docs/7-2/user/-/knowledge_base/u/propagation-of-changes)
during testing, which can be tedious. To give developers more freedom, you can
enable automatic propagation for Fragment changes:

1.  Navigate to the Control Panel &rarr; *Configuration* &rarr;
    *System Settings* &rarr; *Page Fragments*.

2.  Enable the checkbox *Propagate Fragment Changes Automatically*.

3.  Click *Save*.

![Figure 1: Once Fragment propagation is enabled, developers can automatically propagate Fragment changes to all pages using them.](../../../images/fragment-propagation.png)

Great! You've enabled Fragment propagation system wide! Now when a developer
publishes a Fragment, the changes apply immediately to all Content Pages,
Content Page Templates, and Display Page Templates using it, overwriting
existing Fragment code. Automatic propagation works only for HTML, CSS, and JS
Fragment code, not the editable values.

| **Note:** It's recommended to only leverage this functionality during testing,
| as automatic propagation on the production environment can cause unintended
| consequences.

When using the Fragment Editor, you're now notified that automatic Fragment
propagation is enabled.

![Figure 2: You're notified when automatic propagation is enabled.](../../../images/fragment-propagation-info.png)

Now that you've seen how to use Liferay's built-in tools to manage Fragments,
you can see how to do it using your own tools of choice and the Fragments
Toolkit.
