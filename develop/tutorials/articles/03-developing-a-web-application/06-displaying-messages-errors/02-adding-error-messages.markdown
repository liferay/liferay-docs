# Adding Failure and Success Messages  

To display the correct feedback to users properly, you must edit your portlet
classes to make use of @product@'s `SessionMessages` and `SessionErrors` classes.
These classes collect feedback messages that are shown to the user in the view
layer using a simple tag. 

The primary places you'll add these messages are when the user triggers a system
function that can succeed or fail, such as creating, editing, or deleting an
Entry or Guestbook. This generally happens in action methods. 

You must update these methods to handle failure and success states in both
`GuestbookPortlet.java` and `GuestbookAdminPortlet.java`. Start by updating 
`addEntry` and `deleteEntry` in `GuestbookPortlet.java`:

1.  Find the `addEntry` method in `GuestbookPortlet.java`. In the `try` section
    of the `try...catch` block just before the closing `}` add a success
    message:
    
        SessionMessages.add(request, "entryAdded");

    This uses Liferay's `SessionMessages` API to add a success message whenever
    a Guestbook is successfully added. It looks up the message you placed in
    the `Language.properties` file and inserts the message for the key
    `entry-added` (it automatically converts the key to camel case). 

2.  Below that, in the `catch` block, find the following code:
    
        Logger.getLogger(GuestbookPortlet.class.getName()).log(
        Level.SEVERE, null, e);

3.  Beneath it, paste this line:
    
		SessionErrors.add(request, e.getClass().getName());

    Now you not only log the message to the console, you also show the message
    to the user, using the `SessionErrors` object.
 
For the `deleteEntry` method, do the same.

1.  After the logic to delete the entry, add a success message:

        SessionMessages.add(request, "entryDeleted");

2.  Find the same `Logger...` block of code in the `deleteEntry` method and
    after it, paste this line: 

		SessionErrors.add(request, e.getClass().getName());

3.  Hit Ctrl-Shift-O to import `SessionErrors` and `SessionMessages`. Save the
    file. 

Well done! You've added the messages to `GuestbookPortlet`; now you must update
`GuestbookAdminPortlet.java`.

1.  Open `GuestbookAdminPortlet.java` and look for the same cues.

2.  Add the appropriate success messages to the `try` section of the
    `try...catch` in `addGuestbook`, `updateGuestbook`, and `deleteGuestbook`
    respectively:
 
         SessionMessages.add(request, "guestbookAdded");
         
         SessionMessages.add(request, "guestbookUpdated");
         
         SessionMessages.add(request, "guestbookDeleted");

3. In those same methods, in the `catch` section, find `Logger.getlogger...` and
    beneath it paste the `SessionErrors` block:

		SessionErrors.add(request, pe.getClass().getName());

4.  Hit Ctrl-Shift-O to import `SessionErrors` and `SessionMessages`. Save the
    file. 

The controller now makes relevant and detailed feedback available. Now all you
need to do is publish this feedback in the view layer. 

