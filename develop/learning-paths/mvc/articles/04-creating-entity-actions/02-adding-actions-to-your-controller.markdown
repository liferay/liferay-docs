# Adding Actions to Your Controller [](id=adding-actions-to-your-controller)

Remember that the controller layer of your application is like a traffic
director. The user tells your application what he or she wants to do, and the
controller calls the business logic that does that. For this reason, you don't
necessarily have to map one action in the view layer to one action in the
controller. Sometimes it's more convenient to have one action method that can
perform two functions. In this step of the learning path, you'll implement just
that. 

## One Action to Rule Them All [](id=one-action-to-rule-them-all)

When you created your form in the view layer, did you wonder why you named the
file `edit_entry.jsp`? You named it that way because we knew where we were going
in this tutorial. When you get to the view layer, in a similar fashion to what
you're about to do here, you'll use the one JSP for both adding and editing.
Right now, you'll retro-fit the `addEntry` method so that it can handle both
adding and editing. 

The first thing you need to do is ask yourself this question: *when a guestbook
entry is submitted, how do you tell if it's a new entry or one you'd like to
edit?* 

Let's wait a few moments while you think about that. Imagine the music from
*Jeopardy* is playing in the background while you think. 

Got the answer? If you said that the difference is an `entryId` that is greater
than zero, you are correct! An entry coming from the database has its primary
key already populated, while a new one does not. You'll modify the `addEntry`
method in your controller class along those lines. 

Open `GuestbookPortlet.java`. Replace the `addEntry` method with this code: 

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

				EntryLocalServiceUtil.updateEntry(serviceContext.getUserId(),
						guestbookId, entryId, userName, email, message,
						serviceContext);

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
                else {

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

	}

This method actually has not been changed very much. The first change is that
the `entryId` is now retrieved from the form via `ParamUtil`. Next, the original
logic is wrapped in an `if` statement that checks to see if the `entryId` has
a value of `0`. If it does, an update is performed; otherwise, an add is
performed. 

Next, you'll add an action that lets users delete guestbook entries: 

	public void deleteEntry (ActionRequest request, ActionResponse response) {
		
		long entryId = ParamUtil.getLong(request, "entryId");
		long guestbookId = ParamUtil.getLong(request, "guestbookId");
		
		try {

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Entry.class.getName(), request);

                        response.setRenderParameter("guestbookId", Long.toString(guestbookId));

			EntryLocalServiceUtil.deleteEntry(entryId, serviceContext);
			
		} catch (Exception e) {
			
			SessionErrors.add(request, e.getClass().getName());
		}
	}

This action method is fairly straightforward: the `entryId` is retrieved from
the request and is then passed to the service layer for deletion. 

You might think you're finished, and you could be. But your next task is to
create a class that represents a best practice when developing applications for
Liferay. You might even decide, if you haven't been doing this before, that it's
a good practice for your other applications. 

Every web developer sticks objects into the request or the session in one area
of code and then retrieves them in another. Sometimes, depending on the
complexity of the application, a developer can get confused by the names of the
objects that he or she is placing there. For this reason, you can create an
object that holds constants that contain the names of these objects, so you
never forget them and are always consistent in using them. The convention in
Liferay applications is to call this object `WebKeys`. 

1.  Create a new package called `com.liferay.docs.guestbook.util`. 

2.  Create a class in this package called `WebKeys.java`. It should implement
    the `com.liferay.portal.kernel.util.WebKeys` interface. 

3.  Create two final `String`s for your Guestbook and Guestbook Entry entities.
    When finished, your class should look like this: 

        package com.liferay.docs.guestbook.util;

        public class WebKeys implements com.liferay.portal.kernel.util.WebKeys {
            
            public static final String GUESTBOOK = "GUESTBOOK";

            public static final String GUESTBOOK_ENTRY = "GUESTBOOK_ENTRY";

        }

Excellent! You've now created a consistent way to refer to guestbook and
guestbook entry objects that you put the request as attributes. You won't be
using these variables just yet, but you will be using one that's inherited from
the interface you implemented. This is done in the next step of this learning
path: the user interface. 

