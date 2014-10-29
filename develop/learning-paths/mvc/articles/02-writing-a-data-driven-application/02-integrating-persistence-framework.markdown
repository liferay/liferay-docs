# Integrating Your Persistence Framework into Your Application [](id=integrating-your-persistence-framework-into-your-a)

Once you've generated your persistence framework with Service Builder, your next
task is to bring all that functionality into your application. On the
persistence side of things, Service Builder has generated all the basic
functionality you'll need for the Guestbook application. All you need to do is
define the service layer, which is the buffer between your controller and your
persistence layer. It is this layer that gives you the flexibility in your
application to swap components in and out, should you decide that Service
Builder is not for you. 

First, you'll implement a service layer for your `Guestbook` entity, and then
you'll implement one for your `Entry` entity. 

## Writing a Service API for Guestbooks [](id=writing-a-service-api-for-guestbooks)

1.  Open the `com.liferay.docs.guestbook.service.impl` package. Service Builder
    has generated several stub classes for you here, two for each entity. The
    `-LocalServiceImpl.java` class is your implementation for local services, and
    the `-ServiceImpl.java` class is your implementation for remote services. 

2.  Open the `GuestbookLocalServiceImpl.java` class. This is the core class
    you'll customize in the service layer of your application. The controller (your
    portlet class) calls methods from here (not directly, but exposed via a `-Util`
    class), and the methods here call your service layer. 

3.  It's best to implement the simplest method first. What you're doing here is
    exposing methods from the persistence layer in your service layer. Add the
    following two methods to your class: 

        public List<Guestbook> getGuestbooks (long groupId) throws SystemException {
            return guestbookPersistence.findByGroupId(groupId);
        }
        
        public List<Guestbook> getGuestbooks (long groupId, int start, int end)
           throws SystemException {
            return guestbookPersistence.findByGroupId(groupId, start, end);
        }

    Notice that the work of actually going to the database and finding your
    `Guestbook` entities has already been done for you: all you're doing is calling
    the generated methods. In a sense, you're creating your own API for your
    application's data. The first method gets all the entities that match a given
    `groupId`, and the second method does the same thing, but includes start and end
    markers to paginate the data. 

Next, you'll create a method to add a `Guestbook` entity. Remember in the last
section how you created custom `Exception` classes for field validation? Now's
your chance to use them. Before you create a method for adding `Guestbook`s,
you can create a method for validating them which you'll use in the method that
adds them: 

	protected void validate (String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GuestbookNameException();
		}
	}

Use the IDE's right click &rarr; *Source* &rarr; *Organize Imports* facility to
add Liferay's `com.liferay.portal.kernel.exception.PortalException`,
`com.liferay.portal.util.Validator`, and of course your
`com.liferay.docs.guestbook.GuestbookNameException` to the class's imports. 

Now you're ready to create a method that adds `Guestbook`s to the database. 

1.  Add the following method signature: 

        public Guestbook addGuestbook(long userId, String name, 
            ServiceContext serviceContext) throws SystemException, PortalException {

    Most of this makes sense at the moment: you know that you want the ID of the
    user who is adding the guestbook, along with its name. But what's this
    `ServiceContext` thing? [We're glad you asked](https://www-ldn.liferay.com/encyclopedia/-/wiki/Main/Service+Context).
    Beyond the encyclopedia entry, `ServiceContext` is needed in order to pass
    information about the browsing session that triggered your method that adds a
    guestbook. Liferay uses the `ServiceContext` as a convenient container for
    relevant data like the current site, the current page, and more. 

2. Next, add the rest of the method: 

		long groupId = serviceContext.getScopeGroupId();
		
		User user = userPersistence.findByPrimaryKey(userId);
		
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

    You can see how to get the `groupId`, which is the ID of the site the
    application is on, from the `ServiceContext`. You may want to use the code
    completion function of Liferay IDE/Developer Studio on the `serviceContext`
    variable to see what other information it carries. 

    Next, you get the `User` object for the ID that was passed into the method,
    since you'll need to populate its ID and full name attributes to your own
    `Guestbook` entity. After creating a `Date` with the current time in it, you
    call the `validate(name)` method you just created so that you can make sure the
    guestbook that somebody wants to create actually has a name. After this, you use
    Liferay's database-agnostic primary key generator to create a new ID for your
    guestbook, and then you create the `Guestbook` entity that you'll persist to the
    database once you populate it. 

    All that's left is to populate the entity's fields, persist it, and return it to
    the caller. 

Congratulations! You've successfully created a service API to retrieve and add
`Guestbook`s. To enable it, you must run Service Builder again. When you do,
it'll generate the methods you just created in your service layer's Interface
classes. If you want, implement a similar method for updating a `Guestbook`. You
already have the skills to do it. 

Next, you'll create similar methods for adding guestbook entries, and then
you'll move over the UI of your app and update it to take advantage of your new
service and persistence layers. 

## Writing a Service API for Guestbook Entries [](id=writing-a-service-api-for-guestbook-entries)

Now it's time to do the same thing for the guestbook entries. You did the
`Guestbook` entity first, because all `Entry` entities have a `Guestbook` as a
foreign key, and also because quite frankly, it's a simpler, smaller entity than
`Entry`. Now that you have that experience under your belt, though, you're ready
to jump in and create a service API for guestbook entries. 

1.  Open `EntryLocalServiceImpl.java`, which is in the same package as the class
    you've been working on, `GuestbookLocalServiceImpl.java`. 

2.  Create two methods for finding guestbook entries, like the methods you
    already created for `Guestbook` entities. Remember that the finder you created
    required two fields: a `groupId` and a `guestbookId`: 

        public List<Entry> getEntries(long groupId, long guestbookId) throws SystemException {
            
            return entryPersistence.findByG_G(groupId, guestbookId);
        }
        
        public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
             throws SystemException {

            return entryPersistence.findByG_G(groupId, guestbookId, start, end);
        }

3.  Create a method for validating `Entry` objects. There are three required
    fields in an `Entry` object: name, email, and message. You can use Liferay's
    included `Validator` to validate these fields: 

        protected void validate (String name, String email, String entry) 
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

    Notice that in addition to checking to make sure two `String` fields are not
    null, you're also using the `Validator` to make sure a given `String` is an
    email address. 

4.  Create a method for adding guestbook entries. This method is similar to the
    method you created earlier for adding guestbooks: 

        public Entry addEntry(long userId, long guestbookId, String name,
                String email, String message, ServiceContext serviceContext)
                 throws PortalException, SystemException {
            long groupId = serviceContext.getScopeGroupId();
            
            User user = userPersistence.findByPrimaryKey(userId);
            
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

Excellent! You've now created a service API for both `Guestbook` and guestbook
`Entry` entities. Now it's time to modify your controller and view layers to
make use of your new service API. You'll do that in the next step.
