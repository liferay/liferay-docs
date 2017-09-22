# Securing Service Calls at the Portlet Layer [](id=securing-service-calls-at-the-portlet-layer)

<div class="learn-path-step">
    <p>Implementing Permission Checks<br>Step 2 of 2</p>
</div>

Your remote services are now secure for direct use. Your web application, 
however, is another story--it's still calling local services. In a perfect 
world, that would be fine, but this isn't a perfect world. 

Previously, you implemented portlet action methods such as `addGuestbook`, 
`addEntry`, `deleteEntry`, and so on in `GuestbookPortlet` and 
`GuestbookAdminPortlet` classes. These methods call local services. For example,
in the `addEntry` method of `GuestbookPortlet`, you used the following call to 
add a new guestbook entry: 

    _entryLocalService.addEntry(serviceContext.getUserId(), guestbookId, userName, 
        email, message, serviceContext);

Calling local services from an app's portlet layer isn't recommended because 
they don't contain permission checks. Nefarious individuals messing with URL 
parameters might be able to access protected areas of your app. To secure your 
app, only call *remote* services from the portlet layer, because that's the 
layer that has permission checking. Thus, to secure service calls at the portlet 
layer, all you have to do is replace the local service calls with remote service 
calls. 

+$$$

**Note:** An alternative approach to securing service calls at the portlet
layer is to check permissions at the portlet layer manually. To do this, get a 
`ThemeDisplay` from the `ActionRequest` (`ThemeDisplay themeDisplay = 
(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);`) and get a 
`PermissionChecker` from the `ThemeDisplay` (`PermissionChecker 
permissionChecker = themeDisplay.getPermissionChecker();`). If the user passes
the permission check, then call the local service method. However, it's best to
avoid rewriting permission checks whenever possible. For this reason, if you 
must wrap a service call in a permission check, implement that service method as
a remote service and add the permission check to the remote service. This is the 
pattern @product@ uses and that you have followed in this Learning Path. 

$$$

Use the following steps to secure the service calls in the `GuestbookPortlet` 
class: 

1.  Replace the `_guestbookLocalService` and `_entryLocalService` variable 
    declarations with these: 

        private GuestbookService _guestbookService;
        private EntryService _entryService;

2.  Replace the `GuestbookLocalService` and `EntryLocalService` imports with the 
    these: 

        import com.liferay.docs.guestbook.service.GuestbookService;
        import com.liferay.docs.guestbook.service.EntryService;

3.  Replace all instances of `_guestbookLocalService` and `_entryLocalService` 
    with `_guestbookService` and `_entryService`, respectively. Also make sure 
    to replace any `GuestbookLocalService` and `EntryLocalService` method 
    arguments with `GuestbookService` and `EntryService`, respectively. 

Now follow the same steps to change guestbook services in 
`GuestbookAdminPortlet`. Note that service calls and variables for guestbook 
entries aren't necessary in `GuestbookAdminPortlet`. 

To check that you haven't made a mistake in your `GuestbookPortlet` class, refer 
to the following complete class: 

    package com.liferay.docs.guestbook.portlet;

    import java.io.IOException;
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.service.GuestbookService;
    import com.liferay.docs.guestbook.service.EntryService;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
    import com.liferay.portal.kernel.service.ServiceContext;
    import com.liferay.portal.kernel.service.ServiceContextFactory;
    import com.liferay.portal.kernel.servlet.SessionErrors;
    import com.liferay.portal.kernel.servlet.SessionMessages;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.kernel.util.PortalUtil;

    /**
     * @author sezovr
     */
    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.social",
            "com.liferay.portlet.instanceable=false",
            "com.liferay.portlet.scopeable=true",
            "javax.portlet.display-name=Guestbook",
            "javax.portlet.expiration-cache=0",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/guestbookwebportlet/view.jsp",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user",
            "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
            "javax.portlet.supports.mime-type=text/html"
        },
        service = Portlet.class
    )
    public class GuestbookPortlet extends MVCPortlet {

        public void addEntry(ActionRequest request, ActionResponse response)
                        throws PortalException {

                    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Entry.class.getName(), request);

                    String userName = ParamUtil.getString(request, "name");
                    String email = ParamUtil.getString(request, "email");
                    String message = ParamUtil.getString(request, "message");
                    long guestbookId = ParamUtil.getLong(request, "guestbookId");
                    long entryId = ParamUtil.getLong(request, "entryId");

                if (entryId > 0) {

                    try {

                        _entryService.updateEntry(
                            serviceContext.getUserId(), guestbookId, entryId, userName,
                            email, message, serviceContext);

                        SessionMessages.add(request, "entryAdded");

                        response.setRenderParameter(
                            "guestbookId", Long.toString(guestbookId));

                    }
                    catch (Exception e) {
                        System.out.println(e);

                        SessionErrors.add(request, e.getClass().getName());

                        PortalUtil.copyRequestParameters(request, response);

                        response.setRenderParameter(
                            "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                    }

                }
                else {

                    try {
                        _entryService.addEntry(
                            serviceContext.getUserId(), guestbookId, userName, email,
                            message, serviceContext);

                        SessionMessages.add(request, "entryAdded");

                        response.setRenderParameter(
                            "guestbookId", Long.toString(guestbookId));

                    }
                    catch (Exception e) {
                        SessionErrors.add(request, e.getClass().getName());

                        PortalUtil.copyRequestParameters(request, response);

                        response.setRenderParameter(
                            "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                    }
                }
            }

        public void deleteEntry(ActionRequest request, ActionResponse response) throws PortalException {
            long entryId = ParamUtil.getLong(request, "entryId");
            long guestbookId = ParamUtil.getLong(request, "guestbookId");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Entry.class.getName(), request);

            try {

                response.setRenderParameter(
                    "guestbookId", Long.toString(guestbookId));

                _entryService.deleteEntry(entryId, serviceContext);
                SessionMessages.add(request, "entryDeleted");

            }

            catch (Exception e) {
                Logger.getLogger(GuestbookPortlet.class.getName()).log(
                    Level.SEVERE, null, e);
                SessionErrors.add(request, e.getClass().getName());


            }
        }


        @Override
        public void render(
                RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

            try {
                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), renderRequest);

                long groupId = serviceContext.getScopeGroupId();

                long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

                List<Guestbook> guestbooks = _guestbookService.getGuestbooks(
                    groupId);

                if (guestbooks.isEmpty()) {
                    Guestbook guestbook = _guestbookService.addGuestbook(
                        serviceContext.getUserId(), "Main", serviceContext);

                    guestbookId = guestbook.getGuestbookId();
                }

                if (guestbookId == 0) {
                    guestbookId = guestbooks.get(0).getGuestbookId();
                }

                renderRequest.setAttribute("guestbookId", guestbookId);
            }
            catch (Exception e) {
                throw new PortletException(e);
            }

            super.render(renderRequest, renderResponse);
        }


        @Reference(unbind = "-")
        protected void setEntryService(EntryService entryService) {
            _entryService = entryService;
        }

        @Reference(unbind = "-")
        protected void setGuestbookService(GuestbookService guestbookService) {
            _guestbookService = guestbookService;
        }

        private GuestbookService _guestbookService;
        private EntryService _entryService;


    }

To check that you haven't made a mistake in your `GuestbookAdminPortlet` class, 
refer to the following complete class: 

    package com.liferay.docs.guestbook.portlet;

    import java.util.logging.Level;
    import java.util.logging.Logger;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.Portlet;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.service.GuestbookService;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
    import com.liferay.portal.kernel.service.ServiceContext;
    import com.liferay.portal.kernel.service.ServiceContextFactory;
    import com.liferay.portal.kernel.servlet.SessionErrors;
    import com.liferay.portal.kernel.servlet.SessionMessages;
    import com.liferay.portal.kernel.util.ParamUtil;

    @Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.hidden",
                "com.liferay.portlet.scopeable=true",
                "javax.portlet.display-name=Guestbooks",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.portlet-title-based-navigation=true",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/guestbookadminportlet/view.jsp",
                "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=administrator",
                "javax.portlet.supports.mime-type=text/html",
                "com.liferay.portlet.add-default-resource=true"
        },
        service = Portlet.class
    )
    public class GuestbookAdminPortlet extends MVCPortlet {

        public void addGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                  Guestbook.class.getName(), request);

                String name = ParamUtil.getString(request, "name");

                try {
                  _guestbookService.addGuestbook(
                    serviceContext.getUserId(), name, serviceContext);
                  SessionMessages.add(request, "guestbookAdded");

                }
                catch (PortalException e) {

                Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                  Level.SEVERE, null, e);
                SessionErrors.add(request, e.getClass().getName());


                  response.setRenderParameter(
                    "mvcPath", "/guestbookadminportlet/edit_guestbook.jsp");
                }
              }

        public void updateGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                  Guestbook.class.getName(), request);

                String name = ParamUtil.getString(request, "name");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");

                try {
                  _guestbookService.updateGuestbook(
                    serviceContext.getUserId(), guestbookId, name, serviceContext);
                  SessionMessages.add(request, "guestbookUpdated");


                } catch (PortalException e) {

                  Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                  Level.SEVERE, null, e);
                  SessionErrors.add(request, e.getClass().getName());


                  response.setRenderParameter(
                    "mvcPath", "/guestbookadminportlet/edit_guestbook.jsp");
                }
              }

        public void deleteGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                  Guestbook.class.getName(), request);

                long guestbookId = ParamUtil.getLong(request, "guestbookId");

                try {
                  _guestbookService.deleteGuestbook(guestbookId, serviceContext);
                  SessionMessages.add(request, "guestbookDeleted");


                }
                catch (PortalException e) {

                  Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                      Level.SEVERE, null, e);
                  SessionErrors.add(request, e.getClass().getName());


                }
              }


              private GuestbookService _guestbookService;

              @Reference(unbind = "-")
              protected void setGuestbookService(GuestbookService guestbookService) {
              _guestbookService = guestbookService;

              }


    }

Now that you've implemented permission checks at the portlet layer, users 
without the proper permissions can't add, update, or delete a guestbook or 
guestbook entry entity. Even if a user manually entered a URL pointing to one of 
your portlet action methods, the portlet action now calls a remote service. The 
permission check in the remote service aborts the unauthorized user's request. 
Excellent work on securing your application's services! 

From here, you can diverge. The later steps of this Learning Path (some still in
progress) introduce you to other parts of @product@'s development framework: 
workflow, the Recycle Bin, Lexicon, Friendly URLs, Staging and import/export,
the Message Bus, and distributing your application on Marketplace. If you're 
interested in these topics, continue. But there's also another option. 

Now that you've implemented web services, you can create a mobile Guestbook app 
with Liferay Screens. That Learning Path is available from the navigation on the 
left. 
