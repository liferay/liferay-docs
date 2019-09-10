---
header-id: updating-your-service-layer
---

# Updating Your Service Layer

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Writing the Guestbook Admin App</p><p>Step 3 of 6</p>
</div>

Earlier, you wrote an `addGuestbook` service method in
`GuestbookLocalServiceImpl` and only used it to add a default guestbook. To
have full functionality over guestbooks, you must also add methods for updating
and deleting guestbooks, as well as for returning the number of guestbooks in
a Site. 

## Adding Guestbook Service Methods

Remember that when working with Service Builder, you define your service in the
`*Impl` classes. After you add, remove, or change the signature of a method in
an `*Impl` class, you must run Service Builder. Service Builder updates the
affected interfaces and any other generated code. 

Follow these steps to add the required guestbook service methods: 

1.  Go to the `guestbook-service` project and open 
    `GuestbookLocalServiceImpl.java` in the 
    `com.liferay.docs.guestbook.service.impl` package. Add the following method 
    for updating a guestbook: 

    ```java
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
    ```

    The `updateGuestbook` method retrieves the `Guestbook` by its ID, replaces 
    its data with what the user entered, and then calls the persistence layer to 
    save it back to the database. 

2.  Next, add the following method for deleting a guestbook: 

    ```java
	public Guestbook deleteGuestbook(long guestbookId,
                    ServiceContext serviceContext) throws PortalException,
                    SystemException {

            Guestbook guestbook = getGuestbook(guestbookId);

            List<GuestbookEntry> entries = _guestbookEntryLocalService.getGuestbookEntries(
                            serviceContext.getScopeGroupId(), guestbookId);

            for (GuestbookEntry entry : entries) {
                    _guestbookEntryLocalService.deleteGuestbookEntry(entry.getEntryId());
            }

            guestbook = deleteGuestbook(guestbook);

            return guestbook;
    }
    ```

    It's important to consider what should happen if you delete a guestbook that
    has existing entries. If you only deleted the guestbook, the guestbook's 
    orphaned entries would still exist in the database. Your `deleteGuestbook`
    service method makes a service call to delete a guestbook's entries before
    deleting that guestbook. This way, guestbook entries are never orphaned. 

3.  Add a reference to the `GuestbookEntry` local service, so it can be injected
    and used by the `deleteGuestbook` method: 

    ```java
	@Reference
	private GuestbookEntryLocalService _guestbookEntryLocalService;
    ```

    By convention, Liferay adds these to the bottom of the class. 

4.  Use [CTRL]+[SHIFT]+O to update your imports, choosing
    `com.liferay.portal.kernel.exception.SystemException`, and then save
    `GuestbookLocalServiceImpl.java`.

5.  In the Gradle Tasks pane on the right side in Liferay @ide@, run Service 
    Builder by opening the `guestbook-service` module and double-clicking 
    `buildService`. 

| **Note:** If you prefer, you can use [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) 
| to run your Gradle tasks. If you have Blade CLI installed, go to the
| `guestbook-service` folder on your CLI and enter the command `blade gw
| buildService`. This runs Service Builder to build your services outside of
| Eclipse. 

Now that you've finished updating the service layer, it's time to work on the
Guestbook Admin portlet itself. 
