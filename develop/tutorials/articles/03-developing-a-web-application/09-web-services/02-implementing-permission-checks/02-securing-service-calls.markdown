# Securing Service Calls at the Portlet Layer [](id=securing-service-calls-at-the-portlet-layer)

Your remote services are now secure, but you still have some work to do. In a
previous section of the learning path, you implemented portlet action methods
such as `addGuestbook`, `addEntry`, `deleteEntry`, and so on in your
`GuestbookWebPortlet` and `GuestbookAdminPortlet` classes. When you did
this, you ended up calling local service methods. For example, in the
`addEntry` method of `GuestbookWebPortlet`, you used the following call to add
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

Use the following steps to secure the service calls in the `GuestbookWebPortlet` 
class: 

1.  Replace the `_guestbookLocalService` and `_entryLocalService` variable 
    declarations with the following: 

        private GuestbookService _guestbookService;
        private EntryService _entryService;

2.  Replace the `GuestbookLocalService` and `EntryLocalService` imports with the 
    following: 

        import com.liferay.docs.guestbook.service.GuestbookService;
        import com.liferay.docs.guestbook.service.EntryService;

3.  Replace all instances of `_guestbookLocalService` and `_entryLocalService` 
    with `_guestbookService` and `_entryService`, respectively. Also make sure 
    to replace any `GuestbookLocalService` and `EntryLocalService` method 
    arguments with `GuestbookService` and `EntryService`, respectively. 

Now follow the same steps to change guestbook services in 
`GuestbookAdminPortlet`. Note that service calls and variables for guestbook 
entries aren't necessary in `GuestbookAdminPortlet`. 

To check that you haven't made a mistake in your `GuestbookWebPortlet` class, 
refer to the following complete `GuestbookWebPortlet` class: 
<!-- Replace these finished example classes with the ones from the final review app -->

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
            "javax.portlet.init-param.view-template=/guestbookwebportlet/view.jsp",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user",
            "javax.portlet.supports.mime-type=text/html",
            "javax.portlet.expiration-cache=0"
        },
        service = Portlet.class
    )
    public class GuestbookWebPortlet extends MVCPortlet {

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
                            "/guestbookwebportlet/edit_entry.jsp");
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
                            "/guestbookwebportlet/edit_entry.jsp");
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
                        "/guestbookwebportlet/edit_guestbook.jsp");
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

To check that you haven't made a mistake in your `GuestbookAdminPortlet` 
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
            "javax.portlet.init-param.view-template=/guestbookadminportlet/view.jsp",
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
    public class GuestbookAdminPortlet extends MVCPortlet {

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
                        "/guestbookadminportlet/edit_guestbook.jsp");
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
                        "/guestbookadminportlet/edit_guestbook.jsp");
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
