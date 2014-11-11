# Modifying Your Service Layer [](id=modifying-your-service-layer)

You want to add three new functions to your portlet: modifying guestbook
entries, deleting guestbook entries, and setting permissions for guestbook
entries. Only two out of the three of these requires modifying your service
layer. The third, permissions, takes advantage of built-in Liferay
functionality, only requiring that you add the user interface element to access
this functionality. 

It's time to jump back into your service layer to enable deleting and updating
guestbook entries. 

## Adding Methods to the Service Layer [](id=adding-methods-to-the-service-layer)

Remember: with Service Builder, you work in reverse. Instead of modifying the
interface and then implementing it, all you need to do is add the method you
want in the `-Impl` class and then run Service Builder. It then propagates the
method you added up the chain all the way to the interface. 

Open `EntryLocalServiceImpl.java`. You'll add the *delete* method first, as
that's the easiest one to implement. 

1.  Scroll to an appropriate place in the file. If you like keeping your methods
    in alphabetical order, you might scroll to after the `addEntry` method.

2.  Add the following code to the file: 

       	public Entry deleteEntry(long entryId, ServiceContext serviceContext)
			throws PortalException, SystemException {

            Entry entry = getEntry(entryId);

            resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                    Entry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                    entryId);
            
            entry = deleteEntry(entryId);

            return entry;

    	}

In the last step, you added resources to your entities. This means that when you
delete your entities, you have to delete the resource also. Since your entities
are owned by a single user, you delete the resource using the individual scope.
Once you've deleted the resource, you can delete the entry itself, using a
convenience method provided by Service Builder. 

Next, you'll add a method for updating an entry: 

	public Entry updateEntry(long userId, long guestbookId, long entryId,
			String name, String email, String message,
			ServiceContext serviceContext) throws PortalException,
			SystemException {
		long groupId = serviceContext.getScopeGroupId();

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(name, email, message);

		Entry entry = getEntry(entryId);

		entry.setUserId(userId);
		entry.setUserName(user.getFullName());
		entry.setName(name);
		entry.setEmail(email);
		entry.setMessage(message);
		entry.setModifiedDate(serviceContext.getModifiedDate(now));
		entry.setExpandoBridgeAttributes(serviceContext);

		entryPersistence.update(entry);

		resourceLocalService.updateResources(user.getCompanyId(), groupId,
				Entry.class.getName(), entryId,
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());

		return entry;

	}

As you can see, this method is extremely similar to the `addEntry` method. The
only difference in the method signature is that you pass the `entryId` for the
entry you want to modify. This entry is then retrieved, the three user-editable
fields are validated, and then those fields, along with other fields for
auditing purposes (modified date, etc.), are set in the retrieved entry. This
entry is then persisted, the resources are updated, and the entry is returned. 

This is all you need to add to the service layer. Save the file and run Service
Builder. 

The next step in this learning path is to modify the controller to define
*delete* and *update* actions. 


