# Permissions in the Service Layer

Liferay provides a complete API for managing resources that's integrated with 
Service Builder. This API is injected into your implementation classes 
automatically, so all you need to modify your exist add and delete methods to also manage the resource portion.

1. Open GuestbookLocalServiceImpl.java and find the addGuestbook method.

2. Just before the the return statement, add the following code:

	resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
			Guestbook.class.getName(), guestbookId, false, true, true);

Notice that the `resourceLocalService` object is already there, ready for you 
to use. This is one of several utilities that are automatically injected by 
Service Builder. You'll see the rest in future Learning Paths.

This code adds a resource to Liferay's database to correspond with your entity 
(notice that the guestbookId is included in the call). The three booleans at 
the end are settings. The first is whether to add portlet action permissions. 
This should only be true if the permission is for a portlet resource. Since 
this permission is for a model resource (an entity), it's false. The other two 
are settings for adding group permissions and adding guest permissions. If you 
set these to true as has been done here, you'll add the default permissions 
configured in the permissions configuration file that you created in the 
previous step. Since you definitely want to do this, these booleans are set to 
`true.`

3. Save the file and then open EntryLocalServiceImpl.java.

4. Add a line of code that adds resources for this entity:

    resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
		Entry.class.getName(), entryId, false, true, true);

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
