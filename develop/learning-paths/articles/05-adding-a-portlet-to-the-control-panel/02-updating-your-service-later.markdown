# Updating Your Service Layer [](id=updating-your-service-layer)

In an earlier learning path, you wrote an `addGuestbook` service method in
`GuestbookLocalServiceImpl`. The `addGuestbook` method of `GuestbookPortlet`
calls this service method. You can reuse the `addGuestbook` method of
`GuestbookLocalServiceImpl` by calling it from `GuestbookAdminPortlet` too.
However, you need to add a new service method for updating a guestbook and a new
service method for deleting a guestbook. You also need to add a service method
that returns the number of guestbooks in a site. You'll use this service method
when you're implementing your user interface since you'll use a Liferay tag
library construct called Search Container. Your search container needs to know
how many guestbooks to display.

## Adding Guestbook Service Methods

Remember that when working with Service Builder, the `*Impl` classes are your
extension points. After you add or remove a method from an `*Impl` class or
change a method signature of an `*Impl` class, you must run Service Builder.
Service Builder updates the affected interfaces and any other affected generated
code.

Use the following steps to add the required Guestbook service methods:

1. Open `GuestbookLocalServiceImpl.java` and add the following method for
   updating a guestbook:

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

                resourceLocalService.updateResources(serviceContext.getCompanyId(),
                                serviceContext.getScopeGroupId(), name, guestbookId,
                                serviceContext.getGroupPermissions(),
                                serviceContext.getGuestPermissions());

                return guestbook;
        }

    The `updateGuestbook` service method is similar to the `updateEntry`
    service method in `EntryLocalServiceImpl`. As with guestbook entries, you
    not only must update the guestbook model itself, but also must update
    the guestbook's resources. If your `updateGuestbook` service method didn't
    update a guestbook's resources, permissions updates for guestbooks would not
    be possible.

2. Next, add the following method for deleting a guestbook:

        public Guestbook deleteGuestbook(long guestbookId,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {

                Guestbook guestbook = getGuestbook(guestbookId);

                List<Entry> entries = EntryLocalServiceUtil.getEntries(
                                serviceContext.getScopeGroupId(), guestbookId);

                for (Entry entry : entries) {
                        EntryLocalServiceUtil.deleteEntry(entry.getEntryId(),
                                        serviceContext);
                }

                resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                                Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                                guestbookId);

                guestbook = deleteGuestbook(guestbook);

                return guestbook;
        }

    It's important to consider what should happen if you delete a guestbook for
    which there are existing entries. If you just deleted the guestbook, the
    guestbook's entries would still exist in the database, but they would be
    orphaned. Your `deleteGuestbook` service method makes a service call to
    delete all the entries associated with a guestbook before deleting the
    guestbook itself. This way, guestbook entries are never orphaned.

3. Finally, add the following method for returning the number of guestbooks in a
   site:

        public int getGuestbooksCount(long groupId) throws SystemException {
                return guestbookPersistence.countByGroupId(groupId);
        }

    `guestbookPersistence`, like `resourceLocalService`, is a Spring bean
    declared in `GuestbookLocalServiceImpl`'s parent class,
    `GuestbookLocalServiceBaseImpl`. The `guestbookPersistence` implementation
    class is `GuestbookPersistenceImpl`, which is one of the classes generated
    by Service Builder. `GuestbookPersistenceImpl` contains the `countByGroupId`
    method that you use in the `getGuestbooksCount` service method.

These are all the additions you need to make to the Guestbook service. Save
`GuestbookLocalServiceImpl.java` and run Service Builder.

Now that you've finished updating the service layer, it's time to work on the
Guestbook Admin portlet itself.

