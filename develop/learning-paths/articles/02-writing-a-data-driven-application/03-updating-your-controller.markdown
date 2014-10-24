# Updating Your Controller to Use Services [](id=updating-your-controller-to-use-services)

Now that you've created your service and persistence layers, it's time to update
your controller so that it uses them to store and retrieve guestbooks and their
entries for your users. 

You have a concept in your new, service-driven version of the application that
you didn't have before. In the previous, preferences-based Guestbook
application, you had only one guestbook. Now that you've added a Guestbook
entity, the one application can store many guestbooks, and each guestbook can
have wholly separate sets of entries. For this reason, you'll need to add a new
action and a new JSP to handle adding guestbooks. 

Actions go in the controller (your portlet class) so you'll implement that
first. 

1. Open your `GuestbookPortlet.java` class. 

2. Add the following method to the class: 

        public void addGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException, SystemException {

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Guestbook.class.getName(), request);

            String name = ParamUtil.getString(request, "name");

            try {
                GuestbookLocalServiceUtil.addGuestbook(serviceContext.getUserId(),
                        name, serviceContext);

                SessionMessages.add(request, "guestbookAdded");

            } catch (Exception e) {
                SessionErrors.add(request, e.getClass().getName());

                response.setRenderParameter("mvcPath",
                    "/html/guestbook/edit_guestbook.jsp");
            }

        }

You can already see differences in this action from the other one you have in
the class. First, you obtain a `ServiceContext` [object](https://dev.liferay.com/encyclopedia/-/wiki/Main/Service+Context) 
so that you can use some of the information, such as the ID of the current user,
in your call to your service layer. Then you get the value of the `name` field
from the form (which you haven't implemented yet). Once you have all the
information you need, you can call your service layer, which happens inside a
try/catch block. 

Remember those `validate()` methods you created in your service layer? This is
how they come into play in the controller layer. If the user didn't type
anything in the `name` field, your `validate()` method throws an exception. This
exception is caught here, and error messages can be added to Liferay's
`SessionErrors` object, which is then used by your view layer to display those
messages to your users. In this case, users are directed back to the
`edit_guestbook.jsp` that you'll create, so they can correct the error.
Otherwise, your guestbook is added correctly, and a message is added to
Liferay's `SessionMessages` object. For now, these are simple messages, but it's
easy to create a `Language.properties` file later to contain all the messages
for your application. 

The next thing you have to do is replace your `addEntry` method with a
service-enabled version: 

    public void addEntry(ActionRequest request, ActionResponse response)
            throws PortalException, SystemException {

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Entry.class.getName(), request);

        String userName = ParamUtil.getString(request, "name");
        String email = ParamUtil.getString(request, "email");
        String message = ParamUtil.getString(request, "message");
        long guestbookId = ParamUtil.getLong(request, "guestbookId");

        try {
            EntryLocalServiceUtil.addEntry(serviceContext.getUserId(),
                    guestbookId, userName, email, message, serviceContext);

            SessionMessages.add(request, "entryAdded");

            response.setRenderParameter("guestbookId",
                    Long.toString(guestbookId));

        } catch (Exception e) {
            SessionErrors.add(request, e.getClass().getName());

            PortalUtil.copyRequestParameters(request, response);

            response.setRenderParameter("mvcPath",
                    "/html/guestbook/edit_entry.jsp");
        }

    }

As you can see, apart from having a few more fields to process, this method is
much the same. 

Next, you'll replace the `render()` method with the service-enabled version. 

1. Remove the `parseEntries()` method. You now have no use for it. 

2. Replace the `render()` method with one that makes use of your services: 

        @Override
        public void render(RenderRequest renderRequest,
                RenderResponse renderResponse) throws PortletException, IOException {

            try {
                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Guestbook.class.getName(), renderRequest);

                long groupId = serviceContext.getScopeGroupId();

                long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

                List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                        .getGuestbooks(groupId);

                if (guestbooks.size() == 0) {
                    Guestbook guestbook = GuestbookLocalServiceUtil.addGuestbook(
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

There's some logic going on here, but it's pretty straightforward. The main
question you want to ask yourself when determining rendering logic is, *What do
I need to provide to the view layer so it can display data to the user?* In this
case, you need at least one `Guestbook` entity in the database so that you can
display its entities. When this portlet is first placed on the page, there
aren't any `Guestbook` entities in the database. It's easy to think of at least
two ways to handle this. One way to do it is if there is no `Guestbook` in the
database, you could show a page that contains only a button that allows users to
add `Guestbook`s. Another way to do it is to generate a `Guestbook`
automatically, and then show the resulting empty entries list, ready to use.
Since Liferay's Wiki portlet has a similar construct (you can add many wiki
nodes, each of which is a wiki by itself), this is the way the Guestbook portlet
works. 

After getting a `ServiceContext` and the current `groupId`, the portlet attempts
to retrieve a `guestbookId` from the request. If one is there, then a particular
guestbook has been selected at some point by the user. If there is no
`guestbookId` in the request, then the default guestbook should be displayed.
Next, a list of `Guestbook` entities is retrieved from the database, and the
total number of entities available is checked. If there are no guestbooks in the
database, then this is the first time this portlet is being used, and the
default guestbook needs to be created. This is done by calling the
`addGuestbook` method from the service layer and setting the `guestbookId` to
the value of the newly added `Guestbook` entity. 

If `Guestbook` entities were found in the database, but no `guestbookId` was
found in the request, then this portlet has been used before, but it's now being
used by this particular user for the first time in this browsing session. For
that reason, the default guestbook should be shown, so the `guestbookId` is set
to the value of the first guestbook in the list of `Guestbook` entities. This is
then placed in the request, so it can be used by the view layer of the
application to display that guestbook's entries. 

This logic makes the Guestbook portlet work exactly like Liferay's Wiki portlet:
by default, if no guestbook has been chosen to view by the user, the default
guestbook is shown. If that default guestbook doesn't exist already, it is
created. 

Great! You've now written every component of your guestbook application except
for the view layer. That's your next task. 
