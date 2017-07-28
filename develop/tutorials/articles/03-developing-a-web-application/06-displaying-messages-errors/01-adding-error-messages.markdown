# Adding Failure and Success Messages  

To properly display the correct feedback to users you need to 
edit your portlet classes to add the necessary code in the places where 
some feedback is needed. The primary places that you're concerned with  
are when a user is creating, editing, or deleting an Entry or Guestbook.

You'll need to update these methods to handle failure and success states in both `GuestbookPortlet.java` and `GuestbookAdminPortlet.java`. Start by updating the `addEntry`, `deleteEntry`, and `addGuestbook` methods in `GuestbookPortlet.java`:

1. Find the `addEntry` method in `GuestbookPortlet.java`.
    In the `try` section of the `try...catch` block, you need to add a success 
    message. After the logic, but before the closing `}` add a success message:
    
        SessionMessages.add(request, "entryAdded");

    This will use Liferay's `SessionMessages` API to add a success message
    whenever a Guestbook is successfully added.

2.  Below that, in the `catch` section, where you handle the case where the
    `addEntry` operation was not successful, find the following code:
    
        Logger.getLogger(GuestbookPortlet.class.getName()).log(
        Level.SEVERE, null, e);

2. Replace it with:
    
		SessionErrors.add(request, e.getClass().getName());

    The previous version of the code was using the generic Java logger to write 
    a message to the log, featuring only the classname, and setting the level 
    as severe. The new version uses Liferay's `SessionMessages` and 
    `SessionErrors` API. These APIs enable you to go beyond a log message 
    (which most users won't see) provide feedback to the user 
    through your UI.
    
For the `deleteEntry` method, do the same.

1. After the logic to delete the entry, add a success message:

        SessionMessages.add(request, "entryDeleted");å

2. Find the same `Logger...` block of code in the `deleteEntry` method and, just like you did with `addEntry` replace it with:

		SessionErrors.add(request, e.getClass().getName());

That covers the methods that need to provide more feedback in the main 
`GuestbookPortlet`, but you also need to update the 
`GuestbookAdminPortlet.java`.

1. Open `GuestbookAdminPortlet.java` and look for the same cues.

2. Add the appropriate success messages to the `try` section of the `try...catch` in `addGuestbook`, `updateGuestbook`, 
    and `deleteGuestbook` respectively:
 
         SessionMessages.add(request, "guestbookAdded");
         
         SessionMessages.add(request, "guestbookUpdated");
         
         SessionMessages.add(request, "guestbookDeleted");

3. In those same methods, in the `catch` section, find `Logger.getlogger...` and
    replace it with the `SessionErrors` block:

		SessionErrors.add(request, e.getClass().getName());

Your Controller is now configured to provide more relevant and 
detailed feedback. Now it's time to configure your View layer to display these message.

