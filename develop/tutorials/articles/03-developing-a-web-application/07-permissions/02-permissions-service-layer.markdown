# Permissions in the Service Layer [](id=permissions-in-the-service-layer)

<div class="learn-path-step">
    <p>Implementing Permissions<br>Step 2 of 4</p>
</div>

The last step introduced the concept of *resources*. Resources are data stored 
with your entities that define how they can be accessed. For example, when the 
configuration in your `default.xml` files is applied to your application's 
entities in the database, resources are created. These resources are then used 
in conjunction with @product@'s permissions system to determine who can do what 
to the entities. 

@product@ provides a complete API for managing resources that's integrated with 
Service Builder. This API is injected into your implementation classes
automatically. To manage the resources, you need only call the API in the
service's add and delete methods. Follow these steps to do this in your
application: 

1.  In your `guestbook-service` module, open `GuestbookLocalServiceImpl.java` 
    from the `com.liferay.docs.guestbook.service.impl` package. 

2.  Just before the `addGuestbook` method's `return` statement, add this code: 

        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Guestbook.class.getName(), guestbookId, false, true, true);

    Note that the `resourceLocalService` object is already there, ready for you 
    to use. This is one of several utilities that are injected automatically
    by Service Builder. You'll see the rest in the future.

    This code adds a resource to @product@'s database to correspond with your 
    entity (note that the `guestbookId` is included in the call). The three 
    booleans at the end are settings. The first is whether to add portlet 
    action permissions. This should only be `true` if the permission is for a 
    portlet resource. Since this permission is for a model resource (an entity), 
    it's `false`. The other two are settings for adding group and guest 
    permissions. If you set these to `true`, you'll add the default permissions 
    you defined in the permissions configuration file (`default.xml`) in the 
    previous step. Since you definitely want to do this, these booleans are set 
    to `true`. 

3.  Next, go to the `updateGuestbook` method. Add a similar bit of code in 
    between `guestbookPersistence.update(guestbook);` and the `return` 
    statement: 
    
        resourceLocalService.updateResources(serviceContext.getCompanyId(),
                        serviceContext.getScopeGroupId(), 
                        Guestbook.class.getName(), guestbookId,
                        serviceContext.getGroupPermissions(),
                        serviceContext.getGuestPermissions());

4.  Now you'll do the same for `deleteGuestbook`. Add this code in between 
    `guestbook = deleteGuestbook(guestbook);` and the `return` statement:
    
        resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                        Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                        guestbookId);

5.  Hit [CTRL]+[SHIFT]+O to organize the imports and save the file. 

6.  Now you'll add resources for the `Entry` entity. Open 
    `EntryLocalServiceImpl.java` from the same package. For `addEntry`, add a 
    line of code that adds resources for this entity, just before the return 
    statement: 

        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Entry.class.getName(), entryId, false, true, true);

7.  For `deleteEntry`, add this code just before the `return` statement:
    
        resourceLocalService.deleteResource(
                       serviceContext.getCompanyId(), Entry.class.getName(),
                       ResourceConstants.SCOPE_INDIVIDUAL, entryId);

8.  Finally, find `updateEntry` and add its resource action, also just before 
    the `return` statement: 

        resourceLocalService.updateResources(
              user.getCompanyId(), serviceContext.getScopeGroupId(), 
              Entry.class.getName(), entryId, serviceContext.getGroupPermissions(),
              serviceContext.getGuestPermissions());

That's all it takes to add permissions resources. Future entities added to the 
database are fully permissions-enabled. Note, however, that any entities you've 
already added to your Guestbook application in the portal don't have resources 
and thus can't be protected by permissions. You'll fix this at the end of this 
section. 

Next, you'll create helper classes to make it easier to check permissions. 
