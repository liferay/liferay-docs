# Implementing Service Methods

Using Liferay and Service Builder, the implementations are defined in 
`*ServiceImpl` classes in the service module. The 
`*LocalServiceImpl` contains the local service implementation, and the 
`*ServiceImpl` provides the remote service implementation. For now you only
need to create the local implementation, but later you'll implement remote 
services.

1. Right-click on your project and select *Gradle* &rarr; *Refresh Gradle Project* to display all of the generated class.

2. Open `com.liferay.docs.guestbook.service.imply.EntryLocalServiceImpl` you have several methods to add.

3. Create the `addEntry` method:

	public Entry addEntry(
			long userId, long guestbookId, String name, String email,
			String message, ServiceContext serviceContext)
		throws PortalException {

		long groupId = serviceContext.getScopeGroupId();

		User user = userLocalService.getUserById(userId);

		Date now = new Date();

		validate(name, email, message);

		long entryId = counterLocalService.increment();

		Entry entry = entryPersistence.create(entryId);

		entry.setUuid(serviceContext.getUuid());
		entry.setUserId(userId);
		entry.setGroupId(groupId);
		entry.setCompanyId(user.getCompanyId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(serviceContext.getCreateDate(now));
		entry.setModifiedDate(serviceContext.getModifiedDate(now));
		entry.setExpandoBridgeAttributes(serviceContext);
		entry.setGuestbookId(guestbookId);
		entry.setName(name);
		entry.setEmail(email);
		entry.setMessage(message);

		entryPersistence.update(entry);

		return entry;
	}
	
	In this method, you retrieve all of the information provided by the service 
	contents, just as you defined it in your `service.xml` (like the `userId` 
	and `groupId`) and all of the information provided by the user (like the 
	`message` and `email`), and add those as attributes to the `entry` object. 
	The user provided info is validated upon submission. You then update the 
	status of the object and return the new `entry` object.
	
4. Create the `deleteEntry` method:

	public Entry deleteEntry(long entryId, ServiceContext serviceContext)
		throws PortalException {

		Entry entry = getEntry(entryId);

		entry = deleteEntry(entryId);

		return entry;
	}
	
	This method retrieves the `entry` object based on the `entryId`, and then returns the object to be deleted.
	
5. Add the "getters":

	public List<Entry> getEntries(long groupId, long guestbookId) {
		return entryPersistence.findByG_G(groupId, guestbookId);
	}

	public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
			     throws SystemException {

			    return entryPersistence.findByG_G(groupId, guestbookId, start, end);
			}

	public List<Entry> getEntries(
		long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

		return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
	}

	public int getEntriesCount(long groupId, long guestbookId) {
		return entryPersistence.countByG_G(groupId, guestbookId);
	}
	
	
	These provide several options for retrieving entries. First you can 
	retrieve a full list of entries from a given guestbook and group. Next, you 
	can retrieve partial list while specifying a start and end point (hint: this might be useful for a paginated list). One version of that method uses a comparator, the other does not. Then, `getEntriesCount` returns
	the total number of entries as an integer.
	
6. Add the `validate` method:
	
	protected void validate(String name, String email, String entry)
				throws PortalException {

				if (Validator.isNull(name)) {
					throw new EntryNameException();
				}

				if (!Validator.isEmailAddress(email)) {
					throw new EntryEmailException();
				}

				if (Validator.isNull(entry)) {
					throw new EntryMessageException();
				}
	}
	
	Finally, your `validate` method checks the text fields that the use filled 
	out and checks that they are not null, and that the email address field is 
	properly formatted (`somename@someservice.domain`). The email address does
	not need to be a valid address capable of receiving mail. It just needs to look like one.
	
	
7. Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date` and
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,` and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

8. Open `com.liferay.docs.guestbook.service.imply.GuestbookLocalServiceImpl` to update its methods.

9. First there's `addGuestbook`:

	public Guestbook addGuestbook(
				long userId, String name, ServiceContext serviceContext)
			throws PortalException {

			long groupId = serviceContext.getScopeGroupId();

			User user = userLocalService.getUserById(userId);

			Date now = new Date();

			validate(name);

			long guestbookId = counterLocalService.increment();

			Guestbook guestbook = guestbookPersistence.create(guestbookId);

			guestbook.setUuid(serviceContext.getUuid());
			guestbook.setUserId(userId);
			guestbook.setGroupId(groupId);
			guestbook.setCompanyId(user.getCompanyId());
			guestbook.setUserName(user.getFullName());
			guestbook.setCreateDate(serviceContext.getCreateDate(now));
			guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
			guestbook.setName(name);
			guestbook.setExpandoBridgeAttributes(serviceContext);

			guestbookPersistence.update(guestbook);

			return guestbook;
		}
    
	Like the `addEntry` method, `addGuestbook` retrieves a mix of user provided
	input and information provided by the Liferay's context to populate the 
	fields that make up the Guestbook. The method then updates the `guestbook` 
	object and gives you that object as the return value.
	
10. Next, the methods for getting guestbooks:

		public List<Guestbook> getGuestbooks(long groupId) {
			return guestbookPersistence.findByGroupId(groupId);
		}

		public List<Guestbook> getGuestbooks(long groupId, int start, int end, OrderByComparator<Guestbook> obc) {
			return guestbookPersistence.findByGroupId(groupId, start, end, obc);
		}
		
		public List<Guestbook> getGuestbooks(long groupId, int start, int end) {
				return guestbookPersistence.findByGroupId(groupId, start, end);
			}
		
		public int getGuestbooksCount(long groupId) {
			return guestbookPersistence.countByGroupId(groupId);
		}

    The first method retrieves a list of Guestbooks based on the provided 
	`groupId` (that is, the Site). The second gets a list with a defined start
	and end point, with a comparator to determine how they are ordered. You can
	also retrieve the same list without a comparator. The final method gives you
	the total number of guestbooks for a given site.

11. And the guestbook validator:

		protected void validate(String name) throws PortalException {
			if (Validator.isNull(name)) {
				throw new GuestbookNameException();
			}
		}
	
	The `validate` method simply verifies that text was entered for the 
	guestbook name and that the value isn't "null."
	
12. Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date` and
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,` and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

These local service methods provide the implementation for the services that 
you will reference in your portlet class. You should never directly call a
`*LocalServiceImpl` method, instead you use a service reference the 
`*LocalServiceUtil` method which these methods implement. 

## Updating Generated Classes
 
You've created the implementation methods, but these are not the classes that 
you access if you want to actually run any of these methods. In order to use 
these methods, you would actually call them through the service class, and the
correct service methods are generated by Service Builder when you run 
`buildService` again.

1. Run `buildService`
2. Your service methods will now be generated.

+$$$

**Tip:** When working with Service Builder, if something isn't behaving as
expected, before you get too far into troubleshooting:

1. Run 'buildService' in your service module.
2. Run *Gradle* &rarr; *Refresh Gradle Projects*

If you've made any change to your services or any change that needs to be picked
up by Service Builder or Gradle's dependency management, that will catch it.

$$$
