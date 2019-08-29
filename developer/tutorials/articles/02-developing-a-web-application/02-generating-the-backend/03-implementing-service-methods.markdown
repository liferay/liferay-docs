---
header-id: implementing-service-methods
---

# Implementing Service Methods

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Generating the Back-End</p><p>Step 3 of 3</p>
</div>

When you use Service Builder, you implement the services in the service module.
Because your application's projects are 
[components](/docs/7-2/appdev/-/knowledge_base/a/invoking-local-services), 
you can reference your service layer from your web module. 

You'll implement services for guestbooks and entries in the `guestbook-service` 
module's `GuestbookLocalServiceImpl` and `GuestbookEntryLocalServiceImpl`,
respectively. 

Follow these steps to implement services for guestbooks in 
`GuestbookLocalServiceImpl`: 

1.  In the `com.liferay.docs.guestbook.service.impl` package, open 
    `GuestbookLocalServiceImpl`. Then add this `addGuestbook` method: 

    ```java
	public Guestbook addGuestbook(long userId, String name,
			ServiceContext serviceContext) throws PortalException {

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
    ```

    This method adds a guestbook to the database. It retrieves metadata from the 
    environment (such as the current user's ID, the group ID, etc.), along with 
    data passed from the user. It validates this data and uses it to construct a 
    `Guestbook` object. The method then persists this object to the database and 
    returns the object. You implement the business logic here because Service
    Builder already generated the model and all the code that maps that model to
    the database. 

2.  Add the methods for getting `Guestbook` objects: 

    ```java
	public List<Guestbook> getGuestbooks(long groupId) {

		return guestbookPersistence.findByGroupId(groupId);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end,
			OrderByComparator<Guestbook> obc) {

		return guestbookPersistence.findByGroupId(groupId, start, end, obc);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end) {

		return guestbookPersistence.findByGroupId(groupId, start, end);
	}

	public int getGuestbooksCount(long groupId) {

		return guestbookPersistence.countByGroupId(groupId);
	}
    ```

    These call the finders you generated with Service Builder. The first method 
    retrieves a list of guestbooks from the Site specified by `groupId`. The 
    next two methods get paginated lists, optionally in a particular order. The 
    final method gives you the total number of guestbooks for a given Site. 

3.  Finally, add the guestbook validator method:

    ```java
	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GuestbookNameException();
		}
	}
    ```
	
    This method uses @product@'s `Validator` to make sure the user entered text 
    for the guestbook name. 

4.  Press [CTRL]+[SHIFT]+O to organize imports and select the following classes
    when prompted: 

    - `java.util.List`
    - `java.util.Date`
    - `com.liferay.portal.kernel.util.Validator`

Now you're ready to implement services for entries in `GuestbookEntryLocalServiceImpl`. 

1.  In the `com.liferay.docs.guestbook.service.impl` package, open 
    `GuestbookEntryLocalServiceImpl`. Add this `addEntry` method: 

    ```java
	public GuestbookEntry addGuestbookEntry(long userId, long guestbookId, String name,
			String email, String message, ServiceContext serviceContext)
		throws PortalException {

		long groupId = serviceContext.getScopeGroupId();

		User user = userLocalService.getUserById(userId);

		Date now = new Date();

		validate(name, email, message);

		long entryId = counterLocalService.increment();

		GuestbookEntry entry = guestbookEntryPersistence.create(entryId);

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

		guestbookEntryPersistence.update(entry);

		// Calls to other Liferay frameworks go here

		return entry;
	}
    ```
	
    Like the `addGuestbook` method, `addGuestbookEntry` takes data from the
    current context along with data the user entered, validates it, and creates
    a model object. That object is then persisted to the database and returned. 
	
2.  Add this `updateGuestbookEntry` method:

    ```java
	public GuestbookEntry updateGuestbookEntry(long userId, long guestbookId,
			long entryId, String name, String email, String message,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name, email, message);

		GuestbookEntry entry =
			guestbookEntryPersistence.findByPrimaryKey(entryId);

		User user = userLocalService.getUserById(userId);

		entry.setUserId(userId);
		entry.setUserName(user.getFullName());
		entry.setModifiedDate(serviceContext.getModifiedDate(now));
		entry.setName(name);
		entry.setEmail(email);
		entry.setMessage(message);
		entry.setExpandoBridgeAttributes(serviceContext);

		guestbookEntryPersistence.update(entry);

		// Integrate with Liferay frameworks here.

		return entry;
	}
    ```

    This method first retrieves the entry and updates its data to reflect what
    the user submitted, including its date modified. 

2.  Add two `deleteGuestbookEntry` methods: 

    ```java
	public GuestbookEntry deleteGuestbookEntry(GuestbookEntry entry)
		throws PortalException {

		guestbookEntryPersistence.remove(entry);

		return entry;
	}

	public GuestbookEntry deleteGuestbookEntry(long entryId) throws PortalException {

		GuestbookEntry entry =
			guestbookEntryPersistence.findByPrimaryKey(entryId);

		return deleteGuestbookEntry(entry);
	}
    ```
	
    These methods delete entries using the `entryId` or the object. If you
    supply the `entryId`, the object is retrieved and passed to the method that
    deletes the object. 	

3.  Add the methods for getting `GuestbookEntry` objects: 

    ```java
	public List<GuestbookEntry> getGuestbookEntries(long groupId, long guestbookId) {
		return guestbookEntryPersistence.findByG_G(groupId, guestbookId);
	}

	public List<GuestbookEntry> getGuestbookEntries(long groupId, long guestbookId,
			int start, int end) throws SystemException {

		return guestbookEntryPersistence.findByG_G(groupId, guestbookId, start,
				end);
	}

	public List<GuestbookEntry> getGuestbookEntries(long groupId, long guestbookId,
			int start, int end, OrderByComparator<GuestbookEntry> obc) {

		return guestbookEntryPersistence.findByG_G(groupId, guestbookId, start,
				end, obc);
	}

	public GuestbookEntry getGuestbookEntry(long entryId) throws PortalException {
		return guestbookEntryPersistence.findByPrimaryKey(entryId);
	}

	public int getGuestbookEntriesCount(long groupId, long guestbookId) {
		return guestbookEntryPersistence.countByG_G(groupId, guestbookId);
	}
    ```
	
    These methods, like the getters in `GuestbookLocalServiceImpl`, call the 
    finders you generated with Service Builder. These `getGuestbookEntries*`
    methods, however, retrieve entries from a specified Guestbook and Site. The
    first method gets a list of entries. The next method gets a paginated list.
    The third method sorts the paginated list, and the last method gets the
    total number of entries as an integer. 

4.  Add the `validate` method: 

    ```java
	protected void validate(String name, String email, String entry)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new GuestbookEntryNameException();
		}

		if (!Validator.isEmailAddress(email)) {
			throw new GuestbookEntryEmailException();
		}

		if (Validator.isNull(entry)) {
			throw new GuestbookEntryMessageException();
		}
	}
    ```

    This method makes sure the user entered relevant data when creating an 
    entry. 

5.  Press [CTRL]+[SHIFT]+O to organize imports and select the following classes
    when prompted: 

    - `java.util.List`
    - `java.util.Date`
    - `com.liferay.portal.kernel.util.Validator`

Nice work! These local service methods implement the services that are 
referenced in the portlet class. 

## Updating Generated Classes

Now that you've implemented the service methods, you must make them available to 
the rest of your application. To do this, run `buildService` again: 

1.  In *Gradle Tasks* &rarr; *guestbook-service* &rarr; *build*, right-click 
    `buildService` and select *Run Gradle Tasks*. In the utility classes, Service 
    Builder populates calls to your newly created service methods. 

2.  In the Project Explorer, right-click the `guestbook-service` module and 
    select *Refresh*. Repeat this step for the `guestbook-api` module. This 
    ensures that the changes made by Service Builder show up in Liferay @ide@.

3.  In the Project Explorer, right-click the `guestbook-service` module and 
    select *Gradle* &rarr; *Refresh Gradle Project*. Repeat this step for the 
    `guestbook-api` module. This ensures that your modules' Gradle dependencies 
    are up to date. 

| **Tip:** If something goes awry when working with Service Builder, repeat these
| steps to run Service Builder again and refresh your API and service modules.

Excellent! Your new back-end has been generated. Now it's time to create a web
application that uses it. 
