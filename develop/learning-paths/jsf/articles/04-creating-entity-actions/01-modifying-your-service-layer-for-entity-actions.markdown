# Modifying Your Service Layer for Entity Actions

Just as you did in the previous learning path on setting permissions, you'll
need to modify your service layer. Your current Guestbook portlet is only
allowed to add guestbooks and their resources, but you have no way of deleting
them. To be able to create an action method to delete entities, you'll need to
provide a delete method in your service layer. You'll do this next by editing
your `-LocalServiceImpl` classes. 

1. Open your `EntryLocalServiceImpl` class and add the following method: 

        @Override
        public Entry deleteEntry(Entry entry) throws PortalException, SystemException {

            resourceLocalService.deleteResource(entry.getCompanyId(), Entry.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, entry.getPrimaryKey());

            return super.deleteEntry(entry);
        }

    Similar to your `addEntry(...)` method, this method deletes the entry's
    resources, and then deletes the entry. 

2. Open your `GuestbookLocalServiceImpl` class and add the `deleteGuestbook`
   method. 

        @Override
        public Guestbook deleteGuestbook(Guestbook guestbook) throws PortalException, SystemException {

            resourceLocalService.deleteResource(guestbook.getCompanyId(), Guestbook.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, guestbook.getPrimaryKey());

            return super.deleteGuestbook(guestbook);
        }

    Similar to the `deleteEntry(Entry)` method, this method deletes guestbooks
    and their resources. 

3. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

4. Build your Guestbook portlet's services by right-clicking the project and
   selecting *Liferay* &rarr; *Build Services*. 

Now that your service layer has all the required methods for adding entity
actions, it's time to begin creating action methods in your managed beans that
your action buttons will use. 
