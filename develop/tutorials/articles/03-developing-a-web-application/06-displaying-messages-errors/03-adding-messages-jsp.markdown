# Adding Messages to JSPs

Any messages the user should see are now stored in either `SessionMessages` or
`SessionErrors`. Next, you'll make these messages appear in your JSPs. 

1.  In the `guestbook-web` module, open `guestbookwebportlet/view.jsp`.

2.  Add the following block of success messages to the top of the file, just 
    below the `init.jsp` include statement:
    
        <liferay-ui:success key="entryAdded" message="entry-added" />
        <liferay-ui:success key="guestbookAdded" message="guestbook-added" />
        <liferay-ui:success key="entryDeleted" message="entry-deleted" />
 
    This tag accesses what's stored in `SessionMessages`. It has two attributes.
    The first attribute is the `SessionMessages` key that you provided in the
    add and delete methods in the `GuestbookPortlet.java` class. The second
    looks up the specified key in the `Language.properties` file. You could have
    specified a hard-coded message here, but it's far better to provide
    localized key. 

3. Now open `guestbookadminportlet/view.jsp`.

4.  Add the following block of success messages for that portlet in the same 
    spot below the include:

        <liferay-ui:success key="guestbookAdded" message="guestbook-added" />
        <liferay-ui:success key="guestbookUpdated" message="guestbook-updated" />
        <liferay-ui:success key="guestbookDeleted" message="guestbook-deleted" />
 
    Notice that one of the `message` values is the same for both portlets. 
    There's no need to write redundant messages--keys are reusable. 

![Figure x: Now the message will display the value you specified in Language.properties.](../../../images/message-complete.png)

Congratulations! You've added useful feedback for operations in your 
application. Next, you'll add permission checking for your Guestbooks and 
Entries.

