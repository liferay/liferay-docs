# Organizing Folders for Larger Applications

Currently all your JSPs sit in the `/resources/META-INF/resources`
folder, which serves as the context root folder. To make a clear separation
between the Guestbook portlet and the Guestbook Admin portlet, you must place
the files that make up their view layers in separate folders. 

1.  In the "guestbook-web" project, right click on the
    `/resources/META-INF/resources` folder and select *New* &rarr; *Folder*.

2.  Set the name as `guestbookwebportlet` and click *Finish*.

3.  Copy `view.jsp` and `edit_entry.jsp` into the new folder by dragging and
    dropping them there. 

4.  Open both files and change the `init.jsp` location at the top of the file to:

        <%@include file="../init.jsp"%>

As you update your view layer to take full advantage of the new back-end, you'll
update any references to the old paths. In addition, you must update the
resource location in your component properties. In your next step you'll update
all of those properties, including the one that defines the resource location.

