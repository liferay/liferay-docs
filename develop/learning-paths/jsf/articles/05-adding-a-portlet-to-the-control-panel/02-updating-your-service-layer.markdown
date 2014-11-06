# Updating Your Service Layer [](id=updating-your-service-layer)

Just as you did in a previous JSF learning path on
[Adding Permissions Resources to the Service Layer](/develop/learning-paths/jsf/-/knowledge_base/jsf/adding-permissions-resources-to-the-service-layer),
you'll need to add a service method in your `GuestbookLocalServiceImpl` class.
The new service method you'll create needs to delete a guestbook and its
resources from the database. By creating this method, you'll be able to create
the Delete button, which will be used to delete guestbook entities from your
Guestbook Admin portlet. 

## Adding the Delete Service Method for Your Guestbook Entities [](id=adding-the-delete-service-method-for-your-guestbook-entities)

Remember from previous learning paths that when editing your `-LocalServiceImpl`
classes, you must run Service Builder to update the affected interfaces and
other affected generated code. Begin adding the new service method by following
the instructions below: 

1. Open the `GuestbookLocalServiceImpl` class and add the `deleteGuestbook`
   method. 

        @Override
        public Guestbook deleteGuestbook(Guestbook guestbook) throws PortalException, SystemException {

            resourceLocalService.deleteResource(guestbook.getCompanyId(), Guestbook.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, guestbook.getPrimaryKey());

            return super.deleteGuestbook(guestbook);
        }

    Similar to the addGuestbook(...) method you added previously to this class,
    this method deletes the specified guestbook and any resources it possesses. 

2. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

3. Build your Guestbook portlet's services by right-clicking the project and
   selecting *Liferay* &rarr; *Build Services*. 


You're probably wondering why you're not adding service methods for the Edit and
Permissions buttons. There is no need to provide an `update` method for the Edit
button because editing a guestbook will be controlled by the `GuestbookBacking`
bean and the `guestbook` view. 

Recall when the Edit button is selected for entries, your `master` view calls
the `edit(entry)` method from the `EntryBacking` bean. This method sets the
selected entry and then calls the `GuestbookBacking` bean's `editEntry()`
method. The `editEntry()` method sets the `editingEntry` boolean variable to
`true`. In turn, when the `view.xhtml` runs, it chooses to display the `entry`
view when this boolean variable is `true`, which brings up the editing menu.
Once you've finished editing and click the *Save* button, your `entry` view
calls the `EntryBacking` bean's `save()` method, which updates the entry. This
process will be followed similarly with guestbook entities. 

For permissions, you'll also create a process similar to the Entry entity's
process of creating the `permissionsURL` property and calling that URL when the
Permissions button is selected. 

You now have the appropriate service method for deleting guestbooks, and a
general idea of how the other buttons will be implemented. Next, you'll begin
working on the Guestbook Admin's portlet actions and permissions. 
