# Implementing Permission Checks at the Service Layer [](id=implementing-permission-checks-at-the-service-layer)

<div class="learn-path-step">
    <p>Implementing Permission Checks<br>Step 1 of 2</p>
</div>

First, you'll add permission checks to `GuestbookServiceImpl`: 

1.  In `GuestbookServiceImpl`, replace the `addGuestbook`, `deleteGuestbook`, 
    and `updateGuestbook` methods with these versions that contain the 
    permission checks: 

        public Guestbook addGuestbook(long userId, String name,
                        ServiceContext serviceContext) throws SystemException,
                        PortalException {

                GuestbookModelPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.ADD_GUESTBOOK);

                return guestbookLocalService.addGuestbook(userId, name, serviceContext);
        }

        public Guestbook deleteGuestbook(long guestbookId,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {

                GuestbookPermission.check(getPermissionChecker(), guestbookId,
                                ActionKeys.DELETE);

                return guestbookLocalService.deleteGuestbook(guestbookId, serviceContext);
        }

        public Guestbook updateGuestbook(long userId, long guestbookId,
                        String name, ServiceContext serviceContext) throws PortalException,
                        SystemException {

                GuestbookPermission.check(getPermissionChecker(), guestbookId,
                                ActionKeys.UPDATE);

                return guestbookLocalService.updateGuestbook(userId, guestbookId, name, 
                                serviceContext);
        }

    Organize imports to add the imports for the `*Permissions` and `ActionKeys` 
    classes. 

    These methods add permission checks to the remote service methods by calling 
    the `check` helper methods in `GuestbookModelPermission` and 
    `GuestbookPermission`. Remember that these methods throw exceptions, so if 
    the user doesn't have permission, processing stops at the permission check. 
    The `GuestbookModelPermission.check` method takes three parameters: 

    - a `PermissionChecker` object
    - a `groupId`
    - an `actionId` string

    The `GuestbookModelPermission.check` and `EntryModelPermission.check` 
    methods take three parameters: 

    - a `PermissionChecker` object
    - an entity ID (either `guestbookId` or `entryId`)
    - an `actionId` string

    `BaseServiceImpl` contains a `getPermissionChecker` method that returns a
    `PermissionChecker` object. This is accessible since `GuestbookServiceImpl`
    extends `GuestbookServiceBaseImpl`, which extends `BaseServiceImpl`. The
    `serviceContext` method `getScopeGroupId` returns a `groupId`. The 
    `actionId` string comes from your `ActionKeys` class. Using an `ActionKeys` 
    field is less error prone than than manually typing the string's name every 
    time you want to check a permission. Using an `ActionKeys` string also 
    avoids creating a duplicate string. 

2.  Open `EntryServiceImpl` and replace the `addEntry`, `deleteEntry`, and 
    `updateEntry` methods with ones that contain permission checks: 

        public Entry addEntry(long userId, long guestbookId, String name,
                        String email, String message, ServiceContext serviceContext)
                        throws PortalException, SystemException {

                GuestbookModelPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.ADD_ENTRY);

                return entryLocalService.addEntry(userId, guestbookId, name, email,
                                message, serviceContext);
        }

        public Entry deleteEntry(long entryId, ServiceContext serviceContext)
                        throws PortalException, SystemException {

                EntryPermission.check(getPermissionChecker(), entryId, ActionKeys.DELETE);

                return entryLocalService.deleteEntry(entryId, serviceContext);
        }

        public Entry updateEntry(long userId, long guestbookId, long entryId,
                        String name, String email, String message,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {

                EntryPermission.check(getPermissionChecker(), entryId, ActionKeys.UPDATE);

                return entryLocalService.updateEntry(userId, guestbookId, entryId,
                                name, email, message, serviceContext);
        }

    As in step 1, organize imports to add the imports for the `*Permissions` and 
    `ActionKeys` classes. 

    The permission checks in these methods work the same as those in
    `GuestbookServiceImpl`. For `addEntry`, you use 
    `GuestbookModelPermission.check` for the permission check, since adding a
    guestbook entry is a top level model action. For `deleteEntry` and 
    `updateEntry`, you use `EntryPermission.check` since these operations each 
    require a specific permission on a specific entity. 

3.  Open `GuestbookServiceImpl` and replace both `getGuestbooks` methods (this 
    method is overloaded) and the `getGuestbookCount` method with these: 

        public List<Guestbook> getGuestbooks(long groupId) throws SystemException {
                return guestbookPersistence.filterFindByGroupId(groupId);
        }

        public List<Guestbook> getGuestbooks(long groupId, int start, int end)
                        throws SystemException {

                return guestbookPersistence.filterFindByGroupId(groupId, start, end);
        }

        public int getGuestbooksCount(long groupId) throws SystemException {
                return guestbookPersistence.filterCountByGroupId(groupId);
        }

4.  Open `EntryServiceImpl` and replace the `getEntries` methods (this method is
    overloaded) and the `getGuestbookCount` method with these: 

        public List<Entry> getEntries(long groupId, long guestbookId)
                        throws SystemException {

                return entryPersistence.filterFindByG_G(groupId, guestbookId);
        }

        public List<Entry> getEntries(long groupId, long guestbookId, int start,
                        int end) throws SystemException {

                return entryPersistence.filterFindByG_G(groupId, guestbookId, start,
                                end);
        }

        public int getEntriesCount(long groupId, long guestbookId)
                        throws SystemException {

                return entryPersistence.filterCountByG_G(groupId, guestbookId);
        }

5.  Run Service Builder and refresh the API and service modules. 

All remote service methods should include permission checks. In steps 1 and 2,
you directly invoked permission checks for the remote service methods 
`addGuestbook`, `deleteGuestbook`, `updateGuestbook`, `addEntry`, `deleteEntry`, 
and `updateEntry` by using the `check` methods of the permissions utility 
classes: `GuestbookModelPermission`, `GuestbookPermission`, and 
`EntryPermission`. In steps 3 and 4, you indirectly invoked permission checks 
for the remote service methods `getGuestbooks`, `getGuestbooksCount`, 
`getEntries`, and `getEntriesCount` by calling the `filterFindBy*` and 
`*filterCountBy*` methods of `GuestbookPersistenceImpl` and 
`EntryPersistenceImpl`. The `filterFindBy*` and `*filterCountBy*` methods are 
generated by Service Builder if the following conditions are met: 

- The entity has a simple primitive primary key 
- The entity has permission checks registered in an XML file in your project's 
  `docroot/WEB-INF/src/resource-actions` directory 
- The entity has `userId` and `groupId` fields 
- The finder method has a `groupId` argument in its method signature 

Since it would be a very expensive operation to retrieve a large list of 
guestbook or guestbook entry entities and run permission checks on each one, 
Service Builder generates the `filterFindBy*` and `filterCountBy*` helper 
methods in the persistence layer to handle permission checks. The permission 
checking of these helper methods is done in the database, resulting in a less 
expensive operation. The `filterFindBy*` and `filterCountBy*` methods work just 
like the ordinary `findBy*` and `countBy*` methods in the `*PersistenceImpl` 
classes, except that the `filterFindBy*` and `filterCountBy*` methods include 
permission checks. Instances of the `*PersistenceImpl` classes are made 
available as Spring beans in the `*ServiceImpl` classes. These beans are named 
`guestbookPersistence` and `entryPersistence` in `GuestbookServiceImpl` and 
`EntryServiceImpl`, respectively. 

Awesome! You're almost done. The only thing left is to secure the service calls
you make in the web client. 
