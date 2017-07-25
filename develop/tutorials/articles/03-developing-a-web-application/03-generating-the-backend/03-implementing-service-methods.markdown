# Implementing Service Methods

When you use Service Builder, you implement the services in the service module.
Because your application's projects are 
[components](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/fundamentals#components),
you can reference your service layer from your web application. 

1.  From the `guestbook-service` module, in the 
    `com.liferay.docs.guestbook.service.impl` package, open 
    `EntryLocalServiceImpl`. You have several methods to add.

2.  Create the `addEntry` method:

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
	
    This method retrieves metadata from the environment (such as the current
    user's ID, the group ID, etc.) along with data passed from the user,
    validates it, and uses it to construct an `Entry` object. Once created, it
    persists it to the database and returns the object. Service Builder
    generated not only the model, but also all the code that maps that model to
    the database, so you only have to implement the business logic. 
	
3.  Below that, create the `deleteEntry` method:

        public Entry deleteEntry (long entryId)
            throws PortalException {

            Entry entry = getEntry(entryId);

            entry = deleteEntry(entryId);

            return entry;
        }
	
    This method retrieves the `entry` object based on the `entryId`, deletes it
    from the database, and then returns the deleted object.
	
4.  Then add the getters:

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
	
    These call the finders you generated with Service Builder: getting a full
    list of entries from a given guestbook and group, getting a paginated list
    by specifying a start and end point (and optionally ordering that list), and
    finally, getting the number of entries as an integer.
	
5.  A convenience method called `validate` is next:
	
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
	
    The `validate` method uses Liferay's `Validator` to make sure the user
    entered relevant (i.e., not null and a real email address) data. 
	
6.  Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date`,
    `com.liferay.portal.kernel.service.ServiceContext,`
    `com.liferay.docs.guestbook.model.Entry,`, and 
    `com.liferay.portal.kernel.util.Validator.` when prompted.

Those are all the methods for managing an `Entry`. Next, create the methods for
the Guestbook.

1.  From the same package, open `GuestbookLocalServiceImpl` to update its methods.

2.  First there's `addGuestbook`:

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
 
    Like the `addEntry` method, `addGuestbook` takes data from the current
    context along with data the user entered, validates it, and creates a
    `Guestbook` model object. That object is then persisted to the database and
    returned. 

3.  Next, the methods for getting `Guestbook`s:

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

    The first method retrieves a list of Guestbooks by `groupId` (that is, the
    Site). The next two get paginated lists, optionally in a particular order.
    The final method gives you the total number of guestbooks for a given site.

4.  Finally, add the guestbook validator:

        protected void validate(String name) throws PortalException {
            if (Validator.isNull(name)) {
                throw new GuestbookNameException();
            }
        }
	
    This verifies that text was entered for the guestbook name.
	
5.  Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date`, 
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,`, and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

These local service methods provide the implementation for the services that 
are referenced in the portlet class. 

## Updating Generated Classes
 
You've created the implementation methods, and now you must make them available
to your application. To do that, run `buildService` again.

1.  In *Gradle Tasks* &rarr; *guestbook-service* &rarr; *build*, right-click
   `buildService` and select *Run Gradle Tasks*. 

2.  Service Builder runs and populates calls to your newly created service
    methods into the utility classes. 

3.  Right-click the `guestbook-service` project and select *Gradle* &rarr;
    *Refresh Gradle Project*. 

+$$$

**Tip:** When working with Service Builder, if something isn't behaving as
expected, before you get too far into troubleshooting:


1.  Run `buildService` in your service module.

2.  Run *Gradle* &rarr; *Refresh Gradle Projects*

$$$

Excellent! Your new back-end has been generated. Now it's time to refactor your
prototype to use it. 

