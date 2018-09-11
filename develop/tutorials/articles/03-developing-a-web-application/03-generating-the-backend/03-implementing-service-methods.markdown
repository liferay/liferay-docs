# Implementing Service Methods [](id=implementing-service-methods)

<div class="learn-path-step">
    <p>Generating the Back-end<br>Step 3 of 3</p>
</div>

When you use Service Builder, you implement the services in the service module.
Because your application's projects are 
[components](/develop/tutorials/-/knowledge_base/7-0/fundamentals#components), 
you can reference your service layer from your web module. 

You'll implement services for guestbooks and entries in the `guestbook-service` 
module's `GuestbookLocalServiceImpl` and `EntryLocalServiceImpl`, respectively. 

Follow these steps to implement services for guestbooks in 
`GuestbookLocalServiceImpl`: 

1.  In the `com.liferay.docs.guestbook.service.impl` package, open 
    `GuestbookLocalServiceImpl`. Then add this `addGuestbook` method: 

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

    This method adds a guestbook to the database. It retrieves metadata from the 
    environment (such as the current user's ID, the group ID, etc.), along with 
    data passed from the user. It validates this data and uses it to construct a 
    `Guestbook` object. The method then persists this object to the database and 
    returns the object. You only implement the business logic here because 
    Service Builder already generated the model and all the code that maps that 
    model to the database. 

2.  Add the methods for getting `Guestbook` objects: 

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

    These call the finders you generated with Service Builder. The first method 
    retrieves a list of guestbooks from the Site specified by `groupId`. The 
    next two methods get paginated lists, optionally in a particular order. The 
    final method gives you the total number of guestbooks for a given site. 

3.  Finally, add the guestbook validator method:

        protected void validate(String name) throws PortalException {
            if (Validator.isNull(name)) {
                throw new GuestbookNameException();
            }
        }
	
    This method uses @product@'s `Validator` to make sure the user entered text 
    for the guestbook name. 

4.  Press [CTRL]+[SHIFT]+O to organize imports and select the following classes
    when prompted: 

    - `java.util.Date`
    - `com.liferay.portal.kernel.service.ServiceContext`
    - `com.liferay.docs.guestbook.model.Entry`
    - `com.liferay.portal.kernel.util.Validator`

Now you're ready to implement services for entries in `EntryLocalServiceImpl`. 
Do so now by following these steps: 

1.  In the `com.liferay.docs.guestbook.service.impl` package, open 
    `EntryLocalServiceImpl`. Add this `addEntry` method: 

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
	
    Like the `addGuestbook` method, `addEntry` takes data from the current 
    context along with data the user entered, validates it, and creates a model 
    object. That object is then persisted to the database and returned. 
	
2.  Add this `updateEntry` method:

        public Entry updateEntry (
            long userId, long guestbookId, long entryId, String name, String email,
            String message, ServiceContext serviceContext)
            throws PortalException, SystemException {

            Date now = new Date();

            validate(name, email, message);

            Entry entry = getEntry(entryId);

            User user = userLocalService.getUserById(userId);

            entry.setUserId(userId);
            entry.setUserName(user.getFullName());
            entry.setModifiedDate(serviceContext.getModifiedDate(now));
            entry.setName(name);
            entry.setEmail(email);
            entry.setMessage(message);
            entry.setExpandoBridgeAttributes(serviceContext);

            entryPersistence.update(entry);

            return entry;
        }

    This method first retrieves the entry and updates its data to reflect what
    the user submitted, including its date modified. 

2.  Add this `deleteEntry` method: 

        public Entry deleteEntry (long entryId, ServiceContext serviceContext)
            throws PortalException {

            Entry entry = getEntry(entryId);

            entry = deleteEntry(entryId);

            return entry;
        }
	
    This method retrieves the `entry` object defined by `entryId`, deletes it
    from the database, and then returns the deleted object. 
	
3.  Add the methods for getting `Entry` objects: 

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
	
    These methods, like the getters in `GuestbookLocalServiceImpl`, call the 
    finders you generated with Service Builder. These `getEntries*` methods, 
    however, retrieve entries from a specified guestbook and Site. The first 
    method gets a list of entries. The next method gets a paginated list. The 
    third method sorts the paginated list, and the last method gets the total
    number of entries as an integer. 

4.  Add the `validate` method: 

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

    This method makes sure the user entered relevant data when creating an 
    entry. 

5.  Press [CTRL]+[SHIFT]+O to organize imports and select the following classes
    when prompted: 

    - `java.util.Date`
    - `com.liferay.portal.kernel.service.ServiceContext`
    - `com.liferay.docs.guestbook.model.Entry`
    - `com.liferay.portal.kernel.util.Validator`

Nice work! These local service methods implement the services that are 
referenced in the portlet class. 

## Updating Generated Classes [](id=updating-generated-classes)

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

+$$$

**Tip:** If something goes awry when working with Service Builder, repeat these 
steps to run Service Builder again and refresh your API and service modules. 

$$$

Excellent! Your new back-end has been generated. Now it's time to refactor your
prototype to use it. 
