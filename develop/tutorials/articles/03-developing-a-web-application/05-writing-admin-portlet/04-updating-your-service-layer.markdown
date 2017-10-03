# Updating Your Service Layer [](id=updating-your-service-layer)

<div class="learn-path-step">
    <p>Writing the Guestbook Admin App<br>Step 3 of 5</p>
</div>

In an earlier section, you wrote an `addGuestbook` service method in
`GuestbookLocalServiceImpl`, but you never used it. To have full functionality 
over guestbooks, you must also add methods for updating and deleting guestbooks, 
as well as for returning the number of guestbooks in a site. 

## Adding Guestbook Service Methods [](id=adding-guestbook-service-methods)

Remember that when working with Service Builder, you define your service in the 
`*Impl` classes. After you add or remove a method from an `*Impl` class, or 
change the signature of a method in an `*Impl` class, you must run Service 
Builder. Service Builder updates the affected interfaces and any other generated 
code. 

Follow these steps to add the required guestbook service methods: 

1.  Go to the `guestbook-service` project and open 
    `GuestbookLocalServiceImpl.java` in the 
    `com.liferay.docs.gradebook.service.impl` package. Add the following method 
    for updating a guestbook: 

        public Guestbook updateGuestbook(long userId, long guestbookId,
            String name, ServiceContext serviceContext) throws PortalException,
                        SystemException {

                Date now = new Date();

                validate(name);

                Guestbook guestbook = getGuestbook(guestbookId);

                User user = userLocalService.getUser(userId);

                guestbook.setUserId(userId);
                guestbook.setUserName(user.getFullName());
                guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
                guestbook.setName(name);
                guestbook.setExpandoBridgeAttributes(serviceContext);

                guestbookPersistence.update(guestbook);

                return guestbook;
        }

    The `updateGuestbook` method retrieves the `Guestbook` by its ID, replaces 
    its data with what the user entered, and then calls the persistence layer to 
    save it back to the database. 

2.  Next, add the following method for deleting a guestbook: 

        public Guestbook deleteGuestbook(long guestbookId,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {

                Guestbook guestbook = getGuestbook(guestbookId);

                List<Entry> entries = entryLocalService.getEntries(
                                serviceContext.getScopeGroupId(), guestbookId);

                for (Entry entry : entries) {
                        entryLocalService.deleteEntry(entry.getEntryId());
                }

                guestbook = deleteGuestbook(guestbook);

                return guestbook;
        }

    It's important to consider what should happen if you delete a guestbook that
    has existing entries. If you just deleted the guestbook, the guestbook's 
    entries would still exist in the database, but they'd be orphaned. Your 
    `deleteGuestbook` service method makes a service call to delete a 
    guestbook's entries before deleting that guestbook. This way, guestbook 
    entries are never orphaned. 

3.  Use [CTRL]+[SHIFT]+O to update your imports, then save 
    `GuestbookLocalServiceImpl.java`.

4.  In the Gradle Tasks pane on the right side in Liferay @ide@, run Service 
    Builder by opening the `guestbook-service` module and double-clicking 
    `buildService`. 

Now that you've finished updating the service layer, it's time to work on the
Guestbook Admin portlet itself. 
