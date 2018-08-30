# Adding Failure and Success Messages [](id=adding-failure-and-success-messages)

<div class="learn-path-step">
    <p>Displaying Messages and Errors<br>Step 2 of 3</p>
</div>

To display correct feedback to users properly, you must edit your portlet 
classes to use @product@'s `SessionMessages` and `SessionErrors` classes. These 
classes collect messages that the view layer shows to the user by using a simple 
tag. 

You'll add these messages to code that runs when the user triggers a system 
function that can succeed or fail, such as creating, editing, or deleting an
entry or guestbook. This generally happens in action methods. You must update 
these methods to handle failure and success states in `GuestbookPortlet.java` 
and `GuestbookAdminPortlet.java`. Start by updating `addEntry` and `deleteEntry` 
in `GuestbookPortlet.java`: 

1.  Find the `addEntry` method in `GuestbookPortlet.java`. In the first
    `try...catch` block's `try` section, and add the success message just 
    before the closing `}`: 
    
        SessionMessages.add(request, "entryAdded");

    This uses Liferay's `SessionMessages` API to add a success message whenever
    a Guestbook is successfully added. It looks up the message you placed in
    the `Language.properties` file and inserts the message for the key
    `entry-added` (it automatically converts the key to camel case). 

2.  Below that, in the `catch` block, find the following code:
    
        System.out.println(e);

3.  Beneath it, paste this line:
    
		SessionErrors.add(request, e.getClass().getName());

    Now you not only log the message to the console, you also use the 
    `SessionErrors` object to show the message to the user. 
 
Next, do the same for the `deleteEntry` method: 

1.  After the logic to delete the entry, add a success message:

        SessionMessages.add(request, "entryDeleted");

2.  Find the same `Logger...` block of code in the `deleteEntry` method and
    after it, paste this line: 

		SessionErrors.add(request, e.getClass().getName());

3.  Hit [CTRL]+[SHIFT]+O to import
    `com.liferay.portal.kernel.servlet.SessionErrors` and
    `com.liferay.portal.kernel.servlet.SessionMessages`. Save the file. 

Well done! You've added the messages to `GuestbookPortlet`. Now you must update 
`GuestbookAdminPortlet.java`:

1.  Open `GuestbookAdminPortlet.java` and look for the same cues.

2.  Add the appropriate success messages to the `try` section of the
    `try...catch` in `addGuestbook`, `updateGuestbook`, and `deleteGuestbook`, 
    respectively: 

         SessionMessages.add(request, "guestbookAdded");

         SessionMessages.add(request, "guestbookUpdated");

         SessionMessages.add(request, "guestbookDeleted");

3.  In the `catch` section of those same methods, find `Logger.getlogger...` and 
    paste the `SessionErrors` block beneath it: 

		SessionErrors.add(request, pe.getClass().getName());

4.  Hit [CTRL]+[SHIFT]+O to import `SessionErrors` and `SessionMessages`. Save 
    the file. 

Great! The controller now makes relevant and detailed feedback available. Now 
all you need to do is publish this feedback in the view layer. 
