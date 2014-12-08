# Defining Portlet Actions [](id=defining-portlet-actions)

You need to add action methods to the Guestbook Admin portlet for adding,
updating, and deleting guestbooks. The portlet action methods call the
corresponding service methods. Using Service Builder in your guestbook-portlet
project has provided your project with a service-oriented architecture. Note
that due to Liferay's service-oriented architecture, different portlets or web
applications can call the same services. Since you're about to design the
Guestbook Admin portlet to consume one of the same services consumed by the
Guestbook portlet (the `addGuestbook` service), you're about to take advantage
of one tangible benefit of Liferay's service-oriented architecture. Of course,
there are many other benefits to having a service-oriented architecture, such as
the fact that it provides a clean separation of concerns. Portlet classes should
not communicate directly with the database or the persistence layer. Instead,
they should call services. Services can call the persistence layer to
invoke database transactions.

## Adding Three Basic Portlet Actions [](id=adding-three-basic-portlet-actions)

Your requirements for the Guestbook Admin portlet specify that the portlet must
allow administrators to add guestbooks, update guestbooks, and delete
guestbooks. It must also allow guestbook permissions to be configured. You can
create portlet actions to meet the first three requirements by opening
`GuestbookAdminPortlet.java` and following these steps:

1. Add the following portlet method for adding a new guestbook:

        public void addGuestbook(ActionRequest request, ActionResponse response)
                        throws PortalException, SystemException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                                Guestbook.class.getName(), request);

                String name = ParamUtil.getString(request, "name");

                try {
                        GuestbookLocalServiceUtil.addGuestbook(serviceContext.getUserId(),
                                        name, serviceContext);
                        
                        SessionMessages.add(request, "guestbookAdded");
                } catch (PortalException e) {
                        SessionErrors.add(request, e.getClass().getName());

                        response.setRenderParameter("mvcPath",
                                        "/html/guestbookadmin/edit_guestbook.jsp");
                }
        }

    This method is nearly identical to the Guestbook portlet's `addGuestbook`
    method. Since `addGuestbook` is a portlet action method, it takes
    `ActionRequest` and `ActionResponse` parameters. In order to make the
    service call to add a new guestbook, the name of the Guestbook must be
    retrieved from the request. The `serviceContext` must also be retrieved from
    the request and passed as an argument in the service call. If there's a
    problem with adding the Guestbook (an invalid or blank guestbook name, for
    example), you should display the Add Guestbook form and not the default
    view. That's why you add this line in the `catch` block:

        response.setRenderParameter("mvcPath",
                "/html/guestbookadmin/edit_guestbook.jsp");

    Don't worry about the fact that you haven't created
    `docroot/html/guestbookadmin/edit_guestbook.jsp` yet; you'll create it in
    the next section when you're designing the Guestbook Admin portlet's user
    interface.

2. Add the following portlet method for updating an existing guestbook's name:

        public void updateGuestbook(ActionRequest request, ActionResponse response)
                        throws PortalException, SystemException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                                Guestbook.class.getName(), request);

                String name = ParamUtil.getString(request, "name");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");

                try {
                        GuestbookLocalServiceUtil.updateGuestbook(serviceContext.getUserId(), 
                                guestbookId, name, serviceContext);
                        
                        SessionMessages.add(request, "guestbookUpdated");
                } catch (PortalException pe) {
                        SessionErrors.add(request, pe.getClass().getName());

                        response.setRenderParameter("mvcPath",
                                        "/html/guestbookadmin/edit_guestbook.jsp");
                }
        }

    This method is very similar to the `addGuestbook` method. In addition to
    retrieving the guestbook name and the `serviceContext` from the request, the
    `updateGuestbook` method also retrieves the `guestbookId` so that it can
    specify which guestbook to update in the service call. If there's a problem
    with the service call, the Guestbook Admin portlet displays the Edit
    Guestbook form again so that the user can edit the form and resubmit:

        response.setRenderParameter("mvcPath",
                "/html/guestbookadmin/edit_guestbook.jsp");

    Note that the Edit Guestbook form uses the same JSP as the Add Guestbook
    form. The reason for this is to avoid duplication of code. When you're
    creating the Guestbook Admin portlet user interface, you'll see how to
    distinguish between adding a guestbook and editing a guestbook in
    `edit_guestbook.jsp`. Note also that while you could have reused the
    `edit_guestbook.jsp` from the Guestbook portlet, you created another one, with
    almost the same functionality. Why? Because later, you'll be adding
    functionality to the Guestbook Admin portlet that will not be available to
    regular users adding guestbooks. 

3. Add the following portlet method for deleting a guestbook:

        public void deleteGuestbook(ActionRequest request, ActionResponse response)
                        throws PortalException, SystemException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                                Guestbook.class.getName(), request);

                long guestbookId = ParamUtil.getLong(request, "guestbookId");
                
                try {
                        GuestbookLocalServiceUtil.deleteGuestbook(guestbookId, serviceContext);
                        
                        SessionMessages.add(request, "guestbookDeleted");
                } catch (PortalException pe) {
                        SessionErrors.add(request, pe.getClass().getName());
                }
        }

    This method is similar to the `addGuestbook` and `updateGuestbook` methods
    that you added above. Its service call requires only the `serviceContext`
    and the `guestbookId`, so these are retrieved from the request. Since the
    `deleteGuestbook` action is invoked from the default view of the
    Guestbook Admin portlet, there's no need to set the `mvcPath` render
    parameter to point to a particular JSP if there was a problem with the
    `deleteGuestbook` service call.

You now have your service methods and your portlet action methods in place. Your
last task is to implement a user interface for the Guestbook Admin portlet.
Along the way, you'll learn how to create an interface for configuring Guestbook
permissions.
