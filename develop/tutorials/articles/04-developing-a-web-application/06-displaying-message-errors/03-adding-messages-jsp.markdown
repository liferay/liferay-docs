# Adding Messages to JSPs [](id=adding-messages-to-jsps)

<div class="learn-path-step">
    <p>Displaying Messages and Errors<br>Step 3 of 3</p>
</div>

Any messages the user should see are now stored in either `SessionMessages` or
`SessionErrors`. Next, you'll make these messages appear in your JSPs. 

1.  In the `guestbook-web` module, open `guestbookwebportlet/view.jsp`. Add the 
    following block of success messages to the top of the file, just below the 
    `init.jsp` include statement:
    
        <liferay-ui:success key="entryAdded" message="entry-added" />
        <liferay-ui:success key="entryDeleted" message="entry-deleted" />

    This tag accesses what's stored in `SessionMessages`. It has two attributes. 
    The first is the `SessionMessages` key that you provided in the 
    `GuestbookPortlet.java` class's add and delete methods. The second looks up 
    the specified key in the `Language.properties` file. You could have 
    specified a hard-coded message here, but it's far better to provide a 
    localized key. 

2.  Now open `guestbookadminportlet/view.jsp`. Add the following block of 
    success messages in the same spot below the include: 

        <liferay-ui:success key="guestbookAdded" message="guestbook-added" />
        <liferay-ui:success key="guestbookUpdated" message="guestbook-updated" />
        <liferay-ui:success key="guestbookDeleted" message="guestbook-deleted" />

![Figure 1: Now the message displays the value you specified in `Language.properties`.](../../../images/message-complete.png)

Congratulations! You've added useful feedback for operations in your 
application. 

Your application is shaping up, but it is missing another important feature:
permissions. Next, you'll add permission checking for your guestbooks and
entries. 
