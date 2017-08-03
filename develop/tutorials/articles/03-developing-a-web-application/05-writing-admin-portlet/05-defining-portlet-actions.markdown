# Defining Portlet Actions [](id=defining-portlet-actions)

<div class="learn-path-step">
    <p>Writing the Guestbook Admin App<br>Step 4 of 5</p>
</div>

The Guestbook Admin portlet now needs action methods for adding, updating, and
deleting guestbooks. As with the Guestbook portlet, action methods call the
corresponding service methods. Note that since your services and your
applications are all running in the same container, any application can call the
Guestbook services. Since you're about to design the Guestbook Admin portlet to
consume one of the same services consumed by the Guestbook portlet (the
`addGuestbook` service), you're about to take advantage of one tangible benefit
of @product@'s OSGi-based architecture. Different applications or modules can
call services published by other modules. This works whether your application is
in the same module or a different one: if a service is published, it can be used
via `@Reference`. 

## Adding Three Portlet Actions

The Guestbook Admin portlet must allow administrators to add `Guestbooks`,
update `Guestbooks`, and delete `Guestbooks`. You can create portlet actions to
meet these requirements by opening `GuestbookAdminPortlet.java` and following
these steps:

1.  Add the following action method and instance variables needed for adding a
    new guestbook:

        public void addGuestbook(ActionRequest request, ActionResponse response)
          throws PortalException {

          ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Guestbook.class.getName(), request);

          String name = ParamUtil.getString(request, "name");

          try {
            _guestbookLocalService.addGuestbook(
              serviceContext.getUserId(), name, serviceContext);

          }
          catch (PortalException pe) {

          Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
            Level.SEVERE, null, pe);

            response.setRenderParameter(
              "mvcPath", "/html/guestbookadminportlet/edit_guestbook.jsp");
          }
        }
        
        private GuestbookLocalService _guestbookLocalService;
        
        @Reference(unbind = "-")
        protected void setGuestbookService(GuestbookLocalService guestbookLocalService) {
        _guestbookLocalService = guestbookLocalService;

        }
 
    Since `addGuestbook` is a portlet action method, it takes `ActionRequest` and
    `ActionResponse` parameters. To make the service call to add a new guestbook,
    the name of the Guestbook must be retrieved from the request. The
    `serviceContext` must also be retrieved from the request and passed as an
    argument in the service call. If an exception is thrown, you should display the
    Add Guestbook form and not the default view. That's why you add this line in the
    `catch` block:

        response.setRenderParameter("mvcPath",
                "/guestbookadminportlet/edit_guestbook.jsp");

    Later, you'll use this for field validation and to show error messages to the
    user. Don't worry about the fact that you haven't created
    `/guestbookadminportlet/edit_guestbook.jsp` yet; you'll create it in the next
    section when you're designing the Guestbook Admin portlet's user interface.

2.  Add the following action method for updating an existing guestbook:

        public void updateGuestbook(ActionRequest request, ActionResponse response)
          throws PortalException {

          ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Guestbook.class.getName(), request);

          String name = ParamUtil.getString(request, "name");
          long guestbookId = ParamUtil.getLong(request, "guestbookId");

          try {
            _guestbookLocalService.updateGuestbook(
              serviceContext.getUserId(), guestbookId, name, serviceContext);

          } catch (PortalException pe) {
            
            Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
            Level.SEVERE, null, pe);

            response.setRenderParameter(
              "mvcPath", "/guestbookadminportlet/edit_guestbook.jsp");
          }
        }
 
    This method retrieves the guestbook name, ID, and the `serviceContext` from
    the request. It retrieves the guestbook to update by its ID first. If
    there's a problem with the service call, the Guestbook Admin portlet
    displays the Edit Guestbook form again so that the user can edit the form
    and resubmit:

        response.setRenderParameter("mvcPath",
                "/guestbookadminportlet/edit_guestbook.jsp");

    Note that the Edit Guestbook form uses the same JSP as the Add Guestbook
    form to avoid duplication of code. 

3.  Add the following action method for deleting a guestbook:

        public void deleteGuestbook(ActionRequest request, ActionResponse response)
          throws PortalException {

          ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Guestbook.class.getName(), request);

          long guestbookId = ParamUtil.getLong(request, "guestbookId");

          try {
            _guestbookLocalService.deleteGuestbook(guestbookId, serviceContext);

          }
          catch (PortalException pe) {

            Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                Level.SEVERE, null, pe);

          }
        }

    This method uses the service layer to delete the guestbook by its ID. Since the
    `deleteGuestbook` action is invoked from the default view of the Guestbook Admin
    portlet, there's no need to set the `mvcPath` render parameter to point to a
    particular JSP if there was a problem with the `deleteGuestbook` service call.

4.  Hit Ctrl-Shift-O to organize imports. Save the file. 

You now have your service methods and your portlet action methods in place. Your
last task is to implement a user interface for the Guestbook Admin portlet.

