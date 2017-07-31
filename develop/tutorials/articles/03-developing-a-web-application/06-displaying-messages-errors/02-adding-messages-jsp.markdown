# Adding Messages to JSPs

Using `SessionMessages` makes it easy to provide clear, useful feedback to 
users. Now you're going to add the necessary tags to the JSPs to make these 
messages appear. In this application, you don't have useful feedback to provide users when an action failed, so the default message provided by the portal will suffice for those. Let's focus on the positive and providing success messages.

1. In your `guestbook-web` module, open `guestbookwebportlet/view.jsp`.

2. Add the following block of success messages to the top of the file, just 
    below the `int.jsp` include statement:
    
        <liferay-ui:success key="entryAdded" message="entry-added" />
        <liferay-ui:success key="guestbookAdded" message="guestbook-added" />
        <liferay-ui:success key="entryDeleted" message="entry-deleted" />
    
    This contains two keys. The first key is the `SessionMessages` key which you
    provided in the add and delete methods in the `GuestbookPortlet.java` class.
    The second is in the `message` field. You could have simply specified a 
    hard-coded message here, but instead you provided a localizable key. After 
    you finish adding all of the success and error messages, you'll need to
    add the corresponding messages to `Language.properties`.
    
3. Now open `guestbookadminportlet/view.jsp`.

4. Add the following block of success messages for that portlet in the same 
    spot below the include:

        <liferay-ui:success key="guestbookAdded" message="guestbook-added" />
        <liferay-ui:success key="guestbookUpdated" message="guestbook-updated" />
        <liferay-ui:success key="guestbookDeleted" message="guestbook-deleted" />
    
    Notice that one of the `message` values is the same for both portlets. 
    There's no need to write redundant messages - keys are reusable throughout 
    your module.
    
Now your JSPs are updated to display feedback to your users, but it's not quite 
ready for prime time yet. You still need to add the keys.

![Figure X: At this stage the message will display, but there is no corresponding key to display a nice localized message yet.](../../../images/message-key.png)
