# Implementing Permission Checks [](id=implementing-permission-checks)

Now that your guestbook and guestbook entry web services are up and running, 
it's time to implement permission checks for them. Implementing permission
checks for a web service ensures that only users with the correct permissions
can invoke the web service. To implement permission checks in your remote
services, you'll use the `GuestbookModelPermission`, `GuestbookPermission`, and
`EntryPermission` helper classes that you created in an earlier section of the
Learning Path. These classes provide helper methods for checking permissions.
The helper methods in `GuestbookModelPermission` are for checking top level
model permissions. For example, if you need to check whether a user has
permission to add a new guestbook or guestbook entry, you can use
`GuestbookModelPermission`'s helper methods. If, on the other hand, you need to
check whether a user has permission to update or delete an existing guestbook
or guestbook entry, you can use `GuestbookPermission` or `EntryPermission`.


Once you've secured your remote services with permission checks, you'll update
your portlet classes so that they call the remote services instead of the local
services. This prevents attackers from trying to bypass your app's UI by
playing with URL parameters to access sensitive portions of your app.


## Implementing Permission Checks at the Service Layer [](id=implementing-permission-checks-at-the-service-layer)

To implement permission checks for the `guestbook-service` module's remote 
services, use the following steps: 

1. In `GuestbookServiceImpl`, replace the `addGuestbook`, `deleteGuestbook`, and 
   `updateGuestbook` methods with the following: 

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

    Here, you've added permission checks to the remote service methods by 
    calling the `check` helper methods of `GuestbookModelPermission` and 
    `GuestbookPermission`. Remember that these methods throw exceptions, so if 
    the user doesn't have permission, processing stops at the permission check. 
    The `GuestbookModelPermission.check` method takes the following three 
    parameters: 

    - a `PermissionChecker` object
    - a `groupId`
    - an `actionId` string

    The `GuestbookModelPermission.check` and `EntryModelPermission.check` 
    methods take the following three parameters: 

    - a `PermissionChecker` object
    - an entity ID (either `guestbookId` or `entryId`)
    - an `actionId` string

    `BaseServiceImpl` contains a `getPermissionChecker` method which you can 
    invoke to get a `PermissionChecker` object. This is possible since 
    `GuestbookServiceImpl` extends `GuestbookServiceBaseImpl`, which extends 
    `BaseServiceImpl`. Getting a `groupId` can be easily done using the 
    `serviceContext` method `getScopeGroupId`. The `actionId` string determines 
    the particular action for which you're checking permissions. For this, you 
    use a specific field of your `ActionKeys` class. Choosing an `ActionKeys` 
    field is less error prone than than manually typing the name of the string 
    every time you want to check a permission. Also, by using a string from 
    `ActionKeys`, you avoid creating a duplicate string. 

2. Open `EntryServiceImpl` and replace the `addEntry`, `deleteEntry`, and
   `updateEntry` methods with the following: 

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

    The permission checks in these methods work the same way as the permissions 
    checks that you added to `GuestbookServiceImpl`. For `addEntry`, you use 
    `GuestbookModelPermission.check` for the permission check since adding a 
    guestbook entry is a top level model action. For `deleteEntry` and 
    `updateEntry`, you use `EntryPermission.check` since these operations each 
    require a specific permission on a specific entity. 

3. Open `GuestbookServiceImpl` and replace both `getGuestbooks` methods (this
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

4. Open `EntryServiceImpl` and replace the `getEntries` methods (this method is
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

All remote service methods should include permission checks. In steps 1 and 2,
you directly invoked permission checks for the remote service methods
`addGuestbook`, `deleteGuestbook`, `updateGuestbook`, `addEntry`,
`deleteEntry`, and `updateEntry` by using the `check` methods of the
permissions utility classes: `GuestbookModelPermission`, `GuestbookPermission`,
and `EntryPermission`. In steps 3 and 4, you indirectly invoked permission
checks for the remote service methods `getGuestbooks`, `getGuestbooksCount`,
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
classes except that the `filterFindBy*` and `filterCountBy*` methods include 
permission checks. Instances of the `*PersistenceImpl` classes are made 
available as Spring beans in the `*ServiceImpl` classes. These beans are named 
`guestbookPersistence` and `entryPersistence` in `GuestbookServiceImpl` and 
`EntryServiceImpl`, respectively. 

## Securing Service Calls at the Portlet Layer [](id=securing-service-calls-at-the-portlet-layer)

Your remote services are now secure, but you still have some work to do. In a
previous section of the learning path, you implemented portlet action methods
such as `addGuestbook`, `addEntry`, `deleteEntry`, and so on in your
`GuestbookMVCPortlet` and `GuestbookAdminMVCPortlet` classes. When you did
this, you ended up calling local service methods. For example, in the
`addEntry` method of `GuestbookMVCPortlet`, you used the following call to add
a new guestbook entry:


    _entryLocalService.addEntry(serviceContext.getUserId(), guestbookId, userName, 
        email, message, serviceContext);

Calling local services from an app's portlet layer isn't recommended. Why?
Because they don't contain permission checks. Nefarious individuals messing
with URL parameters might be able to access areas of your app that they're not
authorized to access. To make sure that your app is secure, you should only
call remote services from the portlet layer, because that's the layer that has
permission checking. Thus, to secure service calls at the portlet layer, all
you have to do is replace the local service calls with remote service calls.


+$$$

**Note:** An alternative approach to securing service calls at the portlet
layer is to manually check permissions at the portlet layer. To do so, you
could obtain a `ThemeDisplay` from the `ActionRequest` (`ThemeDisplay
themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);`)
and obtain a `PermissionChecker` from the `ThemeDisplay` (`PermissionChecker
permissionChecker = themeDisplay.getPermissionChecker();`). If the user passes
the permission check, then you could call the local service method. However,
it's best to avoid rewriting permission checks whenever possible. For this
reason, if you need to wrap a service call in a permission check, it's best to
implement that service method as a remote service and to add the permission
check to the remote service. This is the pattern that Liferay uses, and that
you have followed in this Learning Path.


$$$

Use the following steps to secure the service calls in the `GuestbookMVCPortlet` 
class: 

1. Replace the `_guestbookLocalService` and `_entryLocalService` variable 
   declarations with the following: 

        private GuestbookService _guestbookService;
        private EntryService _entryService;

2. Replace the `GuestbookLocalService` and `EntryLocalService` imports with the 
   following: 

        import com.liferay.docs.guestbook.service.GuestbookService;
        import com.liferay.docs.guestbook.service.EntryService;

3. Replace all instances of `_guestbookLocalService` and `_entryLocalService` 
   with `_guestbookService` and `_entryService`, respectively. 

Now follow the same steps to change guestbook services in 
`GuestbookAdminMVCPortlet`. Note that service calls and variables for guestbook 
entries aren't necessary in `GuestbookAdminMVCPortlet`. 

To check that you haven't made a mistake in your `GuestbookMVCPortlet` class, 
refer to the following complete `GuestbookMVCPortlet` class: 

    package com.liferay.docs.guestbook.portlet;

    import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.EntryService;
    import com.liferay.docs.guestbook.service.GuestbookService;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
    import com.liferay.portal.kernel.service.ServiceContext;
    import com.liferay.portal.kernel.service.ServiceContextFactory;
    import com.liferay.portal.kernel.servlet.SessionErrors;
    import com.liferay.portal.kernel.servlet.SessionMessages;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.kernel.util.PortalUtil;

    import java.io.IOException;
    import java.util.List;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "com.liferay.portlet.scopeable=true",
            "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
            "javax.portlet.display-name=Guestbook",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/html/guestbookmvcportlet/view.jsp",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user",
            "javax.portlet.supports.mime-type=text/html",
            "javax.portlet.expiration-cache=0"
        },
        service = Portlet.class
    )
    public class GuestbookMVCPortlet extends MVCPortlet {

        private GuestbookService _guestbookService;
        private EntryService _entryService;

        @Reference(unbind = "-")
        protected void setGuestbookService(GuestbookService guestbookService) {
            _guestbookService = guestbookService;
        }

        @Reference(unbind = "-")
        protected void setEntryService(EntryService entryService) {
            _entryService = entryService;
        }

        public void addEntry(ActionRequest request, ActionResponse response)
                throws PortalException, SystemException {

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Entry.class.getName(), request);

            String userName = ParamUtil.getString(request, "name");
            String email = ParamUtil.getString(request, "email");
            String message = ParamUtil.getString(request, "message");
            long guestbookId = ParamUtil.getLong(request, "guestbookId");
            long entryId = ParamUtil.getLong(request, "entryId");

            if (entryId > 0) {
                try {
                    _entryService.updateEntry(serviceContext.getUserId(),
                            guestbookId, entryId, userName, email, message,
                            serviceContext);

                            SessionMessages.add(request, "entryAdded");

                            response.setRenderParameter("guestbookId",
                                    Long.toString(guestbookId));

                } catch (Exception e) {
                    SessionErrors.add(request, e.getClass().getName());

                    PortalUtil.copyRequestParameters(request, response);

                    response.setRenderParameter("mvcPath",
                            "/html/guestbookmvcportlet/edit_entry.jsp");
                }
            } else {
                try {
                    _entryService.addEntry(serviceContext.getUserId(),
                            guestbookId, userName, email, message, serviceContext);

                    SessionMessages.add(request, "entryAdded");

                    response.setRenderParameter("guestbookId",
                            Long.toString(guestbookId));
                } catch (Exception e) {
                    SessionErrors.add(request, e.getClass().getName());

                    PortalUtil.copyRequestParameters(request, response);

                    response.setRenderParameter("mvcPath",
                            "/html/guestbookmvcportlet/edit_entry.jsp");
                }
            }
        }

        public void deleteEntry (ActionRequest request, ActionResponse response) {

            long entryId = ParamUtil.getLong(request, "entryId");
            long guestbookId = ParamUtil.getLong(request, "guestbookId");

            try {
                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Entry.class.getName(), request);

                response.setRenderParameter("guestbookId", Long.toString(guestbookId));

                _entryService.deleteEntry(entryId, serviceContext);
            } catch (Exception e) {

                SessionErrors.add(request, e.getClass().getName());
            }
        }

        public void addGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException, SystemException {

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), request);

            String name = ParamUtil.getString(request, "name");

            try {
                _guestbookService.addGuestbook(serviceContext.getUserId(),
                        name, serviceContext);

                SessionMessages.add(request, "guestbookAdded");
            } catch (Exception e) {
                SessionErrors.add(request, e.getClass().getName());

                response.setRenderParameter("mvcPath",
                        "/html/guestbookmvcportlet/edit_guestbook.jsp");
            }

        }

        @Override
        public void render(RenderRequest renderRequest,
                RenderResponse renderResponse) throws PortletException, IOException {

            try {
                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Guestbook.class.getName(), renderRequest);

                long groupId = serviceContext.getScopeGroupId();

                long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

                List<Guestbook> guestbooks = _guestbookService
                        .getGuestbooks(groupId);

                if (guestbooks.size() == 0) {
                    Guestbook guestbook = _guestbookService.addGuestbook(
                            serviceContext.getUserId(), "Main", serviceContext);

                    guestbookId = guestbook.getGuestbookId();
                }

                if (!(guestbookId > 0)) {
                    guestbookId = guestbooks.get(0).getGuestbookId();
                }

                renderRequest.setAttribute("guestbookId", guestbookId);

            } catch (Exception e) {
                throw new PortletException(e);
            }

            super.render(renderRequest, renderResponse);
        }
    }

To check that you haven't made a mistake in your `GuestbookAdminMVCPortlet` 
class, refer to the following complete `GuestbookAdminPortlet` class: 

    package com.liferay.docs.guestbook.portlet;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.Portlet;

    import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.GuestbookService;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
    import com.liferay.portal.kernel.service.ServiceContext;
    import com.liferay.portal.kernel.service.ServiceContextFactory;
    import com.liferay.portal.kernel.servlet.SessionErrors;
    import com.liferay.portal.kernel.servlet.SessionMessages;
    import com.liferay.portal.kernel.util.ParamUtil;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.hidden",
            "com.liferay.portlet.scopeable=true",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/html/guestbookadminmvcportlet/view.jsp",
            "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN,
            "javax.portlet.display-name=Guestbooks",
            "javax.portlet.init-param.portlet-title-based-navigation=true",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=administrator",
            "javax.portlet.supports.mime-type=text/html",
            "javax.portlet.expiration-cache=0"
        },
        service = Portlet.class
    )
    public class GuestbookAdminMVCPortlet extends MVCPortlet {

        private GuestbookService _guestbookService;

        @Reference(unbind = "-")
        protected void setGuestbookService(GuestbookService guestbookService) {
            _guestbookService = guestbookService;
        }

        public void addGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException, SystemException {

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), request);

            String name = ParamUtil.getString(request, "name");

            try {
                _guestbookService.addGuestbook(serviceContext.getUserId(),
                        name, serviceContext);

                SessionMessages.add(request, "guestbookAdded");
            } catch (PortalException e) {
                SessionErrors.add(request, e.getClass().getName());

                response.setRenderParameter("mvcPath",
                        "/html/guestbookadminmvcportlet/edit_guestbook.jsp");
            }
        }

        public void updateGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException, SystemException {

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), request);

            String name = ParamUtil.getString(request, "name");
            long guestbookId = ParamUtil.getLong(request, "guestbookId");

            try {
                _guestbookService.updateGuestbook(serviceContext.getUserId(), 
                        guestbookId, name, serviceContext);

                SessionMessages.add(request, "guestbookUpdated");
            } catch (PortalException pe) {
                SessionErrors.add(request, pe.getClass().getName());

                response.setRenderParameter("mvcPath",
                        "/html/guestbookadminmvcportlet/edit_guestbook.jsp");
            }
        }

        public void deleteGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException, SystemException {

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), request);

            long guestbookId = ParamUtil.getLong(request, "guestbookId");

            try {
                _guestbookService.deleteGuestbook(guestbookId, serviceContext);

                SessionMessages.add(request, "guestbookDeleted");
            } catch (PortalException pe) {
                SessionErrors.add(request, pe.getClass().getName());
            }
        }
    }

Now that you've implemented permission checks at the portlet layer, users
without the proper permissions cannot add, update, or delete a guestbook or a
guestbook entry entity. Even if such a user manually entered a URL pointing to
one of your portlet action methods, the portlet action now calls a remote
service. The permission check in the remote service would fail and the user's
request would be aborted. Excellent work on securing your application's
services!
