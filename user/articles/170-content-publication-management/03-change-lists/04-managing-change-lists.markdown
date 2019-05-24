# Managing Change Lists

Managing change lists is primarily done from the Overview page. You can get
there by navigating to the Control Panel &rarr; *Change Lists* &rarr;
*Overview*.

![Figure 1: The Overview screen provides several sections to help manage you change lists.](../../../images/change-lists-overview.png)

There are three sections on the Overview page that help you manage change lists
and provide useful information about the current state of your changes.

- Active Change List
- Published Change List
- Latest Changes in Change List

You'll explore each section next.

## Using the Change List Environment

The Active Change List section (top left) of the Overview screen provides
information for your active change list.

![Figure 2: The Active Change List section provides information on the current change list.](../../../images/active-change-list.png)

You're provided information on three change types tracked in your change list:

- Added
- Modified
- Deleted

Remember, the active change list only tracks the
[supported content types](/docs/7-2/user/-/knowledge_base/u/change-lists).

While the change list is active, the changes you make are recorded and your
current environment is preserved separate from other change lists or the
production environment. Will you have a team of editors that must edit your
content at the same time? Visit the
[Editing Multiple Change Lists in Parallel](/docs/7-2/user/-/knowledge_base/u/editing-multiple-change-lists-in-parallel)
for more information.

You're also presented the *Select Change List* dropdown, which lets you navigate
between multiple change lists and the production environment. If you choose to
work from production or choose the *Select Change List* option, you're taken to
the Select Change List screen.

![Figure 3: Select the change list you want to activate..](../../../images/select-change-list.png)

Click a change list title to activate the change list. You can also choose a
change list's *Actions* button (![Actions](../../../images/icon-staging-bar-options.png))
for additional options:

- Activate: set the change list as your active working environment.
- Edit: modify the change list's name and description.
- Publish: publish the change list to production.
- Delete: delete the change list.

When switching between change lists, a popup loads by default asking you to
confirm that you want to change your active change list. This can be disabled by
navigating to the Control Panel &rarr; *Change Lists* &rarr; *Settings* &rarr;
*User Settings* and unselecting the *Required confirmation when switching change
lists* option. This setting only applies to the current user.

Also, when the Change Lists framework is enabled, a new button is displayed in
the Control Menu.

![Figure 4: The Active Change List button displays your active change list.](../../../images/active-change-list-indicator.png)

Hovering over this button displays your active change list. Clicking this button
navigates you back to the Change Lists Overview screen. This button is always
available in the Control Menu while the Change Lists framework is enabled.

As you make changes, you can view them in the Latest Changes section (bottom).
This section lists important information like author, site, content type, change
type, version, etc.

Once you're finished adding/modifying/deleting content, you can publish your
change list. Click the *Publish* button to do this. You're offered a Publish
Change List wizard.

![Figure 5: Configure your publication before clicking *Publish*.](../../../images/publish-change-list.png)

You can disable collision detection if you choose. See the
[Editing Multiple Change Lists in Parallel](/docs/7-2/user/-/knowledge_base/u/editing-multiple-change-lists-in-parallel)
for more information on collisions.

<!-- Cover how to schedule the publication when the feature is not grayed out.
-->

When you've published your change list, you're redirected to the History screen.
You can view the status of your current change list publication and others
you've performed in the past.

![Figure 5: You can always refer to your change list publication history.](../../../images/change-list-history.png)

If you navigate back to the Overview screen, the Published Change List section
(top right) now provides information on your publication. You can click the
*Production View* link to view the change list publication's changes on the
production environment. Once a change list is published, it cannot be edited; no
new changes can be added. This is for the sake of maintaining a clear audit
trail. 

<!-- Rolling back change lists is an upcoming feature, but not available
currently. -->

Great! You've learned about the Change Lists UI and workflow. Now you'll step
through an example workflow to test this out yourself.

## Example: Trying the Change List Workflow

Complete the steps outlined below to test out an example Change List workflow:

1.  [Enable](/docs/7-2/user/-/knowledge_base/u/enabling-change-lists) the Change
    Lists framework (if necessary).

2.  [Create](/docs/7-2/user/-/knowledge_base/u/creating-change-lists) a new
    change list.

3.  Navigate to your site's *Content & Data* &rarr; *Web Content* section and
    create a new web content article. Click the *Publish to Change List* button
    to add the drafted article to your active change list.

4.  Navigate back to your active change list Overview screen by clicking the
    Control Menu's *Active Change List* button.

5.  Verify your active change list has one *added* change.

6.  Go back to that same web content article and modify it.

7.  Repeat step 4. Verify your active change list has one *added* change and one
    *modified* change.

8.  Click the *Publish* button from the Overview screen, accept the defaults,
    and select *Publish* again.

9.  Verify your change list has successfully published from the History screen.

Terrific! You created a change list, made some changes, and published the change
list to your production environment. Next you'll learn about editing multiple
change lists at once.
