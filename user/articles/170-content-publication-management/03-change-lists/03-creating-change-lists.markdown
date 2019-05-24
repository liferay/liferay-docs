# Creating Change Lists

Creating change lists is straightforward once you've
[enabled them](/docs/7-2/user/-/knowledge_base/user/enabling-change-lists).
Change lists can be created on a per user basis. This means multiple users of a
@product@ instance can manage their own personal change lists.

To create a change list, follow these steps:

1.  Go to the Change Lists Overview page by navigating to the Control Panel
    &rarr; *Change Lists* &rarr; *Overview*.

2.  Select the *Add Change List* button (![Add](../../../images/icon-add.png)).

3.  Assign the new change list a name and description (optional).

    ![Figure 1: Give your new change list a name and description.](../../../images/creating-change-lists.png)

4.  Click *Save*.

The new change list is automatically set as your active change list. It's
created with a baseline of the current production environment. This means all
content available on the production environment is visible to the change list;
any modifications made while the new change list is active are added on top of
the production environment. Of course, content modifications tracked in the
change list are never available on the live site until you've published the
change list.

| **Note:** A new change list is always created as an empty list; no changes are
| added by default.

Great! You've created a change list! Next, you'll learn how to manage it.
