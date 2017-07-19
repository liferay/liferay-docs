# Permissions in the Service Layer

Liferay provides a complete API for managing resources that's integrated with 
Service Builder. This API is injected into your implementation classes 
automatically, so all you need to modify your exist add and delete methods to also manage the resource portion.

1. Open `GuestbookLocalServiceImpl.java` from your `guestbook-service` module 
    in the `com.liferay.docs.guestbook.service.impl` package, and find the 
    `addGuestbook` method.

2. Just before the the return statement, add the following code:

        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Guestbook.class.getName(), guestbookId, false, true, true);

    Notice that the `resourceLocalService` object is already there, ready for 
    you to use. This is one of several utilities that are automatically 
    injected by Service Builder. You'll see the rest in future Learning Paths.

    This code adds a resource to Liferay's database to correspond with your 
    entity (notice that the guestbookId is included in the call). The three 
    booleans at the end are settings. The first is whether to add portlet 
    action permissions. This should only be true if the permission is for a 
    portlet resource. Since this permission is for a model resource (an 
    entity), it's false. The other two are settings for adding group 
    permissions and adding guest permissions. If you set these to true as has 
    been done here, you'll add the default permissions configured in the 
    permissions configuration file that you created in the previous step. Since 
    you definitely want to do this, these booleans are set to `true.`

3. Next go to the `updateGuestbook` method. Add a similar bit of code in between
    the `guestbookPersistence.update(guestbook)` line and the `return` 
    statement:
    
        resourceLocalService.updateResources(serviceContext.getCompanyId(),
                        serviceContext.getScopeGroupId(), 
                        Guestbook.class.getName(), guestbookId,
                        serviceContext.getGroupPermissions(),
                        serviceContext.getGuestPermissions());

4. Do the same for `deleteGuestbook`, this in between `guestbook = 
    deleteGuestbook(guestbook);` and the `return` statement:
    
        resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                        Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                        guestbookId);

4. Save the file and then open `EntryLocalServiceImpl.java` from the same 
    package.

5. Add a line of code that adds resources for this entity, near the end of the 
    method, just before the return statement:

        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
          Entry.class.getName(), entryId, false, true, true);

6. Again, you need to do the same for `updateEntry` and `deleteEntry`. First
    Find `deleteEntry` and add this code above the `return` statement:
    
        resourceLocalService.deleteResource(
                       serviceContext.getCompanyId(), Entry.class.getName(),
                       ResourceConstants.SCOPE_INDIVIDUAL, entryId);
            

7. Finally find `updateEntry` and add it's resource action:

        resourceLocalService.updateResources(
                      user.getCompanyId(), groupId, Entry.class.getName(), entryId,
                      serviceContext.getGroupPermissions(),
                      serviceContext.getGuestPermissions());

    As you can see, this does the same thing for the Entry entity.

That's all it takes to add permissions resources. Note that at this point, any 
entities you've already added to your Guestbook don't have corresponding 
resources, and thus can't be protected by permissions. It's probably wise to 
delete them, which at this point, you'll have to do via SQL commands in the 
database, since you haven't implemented delete functionality in your 
application yet (though that's coming). Future entities added to the database 
are fully permissions-enabled.

Your next task is to create permissions helper classes to make it easier to 
check permissions.
