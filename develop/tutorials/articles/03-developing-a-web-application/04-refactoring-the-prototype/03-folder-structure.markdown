# Organizing Folders for Larger Applications

Currently all of your JSPs are residing in the `/resources/WEB-INF/resources`
folder, which serves as the root folder. As you continue, you'll need to make
multiples of certain files, like `view.jsp`, for different aspects of the
application.

1. In the "guestbook-web" project, right click on the 
    `/resources/WEB-INF/resources` folder and select *New* &rarr; *Folder*.
2. Set the name as `guestbookwebportlet` and click *Finish*.
3. Copy `view.jsp` and `edit_guestbook.jsp` into the new folder.
4. Open both files and change the `init.jsp` location at the top of the file to:

	<%@include file="../init.jsp"%>
    
5. Search both files for references to `/view.jsp` and `/edit_guestbook.jsp` and
    change them to '/guestbookwebportlet/view.jsp` and
	`/guestbookwebportlet/edit_guestbook.jsp`._

You also need to update the resource location in your component properties. In 
your next step you'll update all of those properties, including the one that
defines the resource location.


