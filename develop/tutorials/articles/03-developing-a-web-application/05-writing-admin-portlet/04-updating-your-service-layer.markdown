# Updating Your Service Layer [](id=updating-your-service-layer)

<div class="learn-path-step">
    <p>Writing the Guestbook Admin App<br>Step 3 of 5</p>
</div>

In an earlier section, you wrote an `addGuestbook` service method in
`GuestbookLocalServiceImpl`, but you never used it anywhere. To have full
functionality over Guestbooks, you must also add methods for updating and
deleting Guestbooks, as well as for returning the number of Guestbooks in a
site. 

## Adding Guestbook Service Methods [](id=adding-guestbook-service-methods)

Remember that when working with Service Builder, the `*Impl` classes are where
you define your service. After you add or remove a method from an `*Impl` class
or change a method signature of an `*Impl` class, you must run Service Builder.
Service Builder updates the affected interfaces and any other generated code.

Follow these steps to add the required Guestbook service methods:

1.  Go to the `guestbook-service` project and open 
    `GuestbookLocalServiceImpl.java` in the 
    `com.liferay.docs.gradebook.service.impl` package. Add the following 
    method for updating a guestbook:

        public Guestbook updateGuestbook(long userId, long guestbookId,
        String name, ServiceContext serviceContext) throws PortalException,
                        SystemException {

                Date now = new Date();

                validate(name);

                Guestbook guestbook = getGuestbook(guestbookId);

                User user = UserLocalServiceUtil.getUser(userId);

                guestbook.setUserId(userId);
                guestbook.setUserName(user.getFullName());
                guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
                guestbook.setName(name);
                guestbook.setExpandoBridgeAttributes(serviceContext);

                guestbookPersistence.update(guestbook);

                return guestbook;
        }

    The `updateGuestbook` retrieves the `Guestbook` by its ID, replaces its data
    with what the user entered, and then calls the persistence layer to save it
    back to the database. 

2.  Next, add the following method for deleting a guestbook:

        public Guestbook deleteGuestbook(long guestbookId,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {

                Guestbook guestbook = getGuestbook(guestbookId);

                List<Entry> entries = EntryLocalServiceUtil.getEntries(
                                serviceContext.getScopeGroupId(), guestbookId);

                for (Entry entry : entries) {
                        EntryLocalServiceUtil.deleteEntry(entry.getEntryId());
                }

                guestbook = deleteGuestbook(guestbook);

                return guestbook;
        }

    It's important to consider what should happen if you delete a guestbook that
    has existing entries. If you just deleted the guestbook, the guestbook's
    entries would still exist in the database, but they'd be orphaned. Your
    `deleteGuestbook` service method makes a service call to delete all the
    entries associated with a guestbook before deleting the guestbook itself.
    This way, guestbook entries are never orphaned.
 
3.  Update your imports using [CTRL]+[SHIFT]+O.

4. Save `GuestbookLocalServiceImpl.java` and run Service Builder by opening
    the `guestbook-service` module in the build panel on the right side in
    @ide@ and selecting `buildService`.

Now that you've finished updating the service layer, it's time to work on the
Guestbook Admin portlet itself. 
