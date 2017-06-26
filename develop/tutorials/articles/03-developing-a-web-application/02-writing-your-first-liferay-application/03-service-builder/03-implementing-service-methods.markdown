# Implementing Service Methods

Using Liferay and Service Builder, the implementations are defined in 
'*ServiceImpl' classes in the service module. The '*LocalServiceImpl' contains the local service implementation, and the '*ServiceImpl' provides the remote
service implementation.

1. Right-click on your project and select *Gradle* &rarr; *Refresh Gradle Project* to display all of the generated class.

2. Open `com.liferay.docs.guestbook.service.imply.EntryLocalServiceImpl` and add these methods:

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

	public Entry deleteEntry(long entryId, ServiceContext serviceContext)
		throws PortalException {

		Entry entry = getEntry(entryId);

		resourceLocalService.deleteResource(
			serviceContext.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entryId);

		entry = deleteEntry(entryId);

		return entry;
	}

	public List<Entry> getEntries(long groupId, long guestbookId) {
		return entryPersistence.findByG_G(groupId, guestbookId);
	}

	public List<Entry> getEntries(
		long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

		return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
	}

	public int getEntriesCount(long groupId, long guestbookId) {
		return entryPersistence.countByG_G(groupId, guestbookId);
	}
	
	public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
			     throws SystemException {

			    return entryPersistence.findByG_G(groupId, guestbookId, start, end);
			}
	
	
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
	
	
	
3. Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date` and
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,` and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

The `addEntry` method gets the `groupId` and the `userId` from the portal, 
gets the date from the Java service, and validates the users text entry. It then
instantiates an `Entry` object, adds the necessary fields to the object, 
creates a new resource in the database, and returns the new `entry`. The 
`deleteEntry` method removes an item based on the primary key (`entryId`.) You 
also have the "get" methods defined as well as a utility method, `validate`, 
which verifies that field data was properly entered for the fields before storing them in the database.

1. Open `com.liferay.docs.guestbook.service.imply.GuestbookLocalServiceImpl`.
2. Add these methods:

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

		public List<Guestbook> getGuestbooks(long groupId) {
			return guestbookPersistence.findByGroupId(groupId);
		}

		public List<Guestbook> getGuestbooks(long groupId, int start, int end, OrderByComparator<Guestbook> obc) {
			return guestbookPersistence.findByGroupId(groupId, start, end, obc);
		}

		public int getGuestbooksCount(long groupId) {
			return guestbookPersistence.countByGroupId(groupId);
		}


		protected void validate(String name) throws PortalException {
			if (Validator.isNull(name)) {
				throw new GuestbookNameException();
			}
		}
	
	
3. Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date` and
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,` and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

Again, you've added the basic operations that you'll need to manage the 
Guestbook. The `addGuestbook` method gets the necessary information from the portal and the Java services, and validates the users text entry. It then instantiates an `Entry` object, adds the necessary fields to the object, 
creates a new resource in the database, and returns the new `entry`. The 
`deleteEntry` method removes an item based on the primary key (`entryId`.) You 
also have the "get" methods defined as well as a utility method, `validate`, 
that your class needs.

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
