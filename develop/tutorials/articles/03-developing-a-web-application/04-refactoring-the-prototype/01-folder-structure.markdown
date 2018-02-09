# Organizing Folders for Larger Applications [](id=organizing-folders-for-larger-applications)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 1 of 6</p>
</div>

Currently, all your JSPs sit in your web module's 
`src/main/resources/META-INF/resources` folder, which serves as the context root 
folder. To make a clear separation between the Guestbook portlet and the 
Guestbook Admin portlet, you must place the files that make up their view layers 
in separate folders: 

1.  In the `guestbook-web` project, right click the
    `src/main/resources/META-INF/resources` folder and select *New* &rarr; 
    *Folder*. Name the new folder `guestbookwebportlet` and click *Finish*. 

2.  Copy `view.jsp` and `edit_entry.jsp` into the new folder by dragging and
    dropping them there. 

3.  Open both files and change the `init.jsp` location at the top of the file: 

        <%@include file="../init.jsp"%>        

4.  Check the other references to JSPs within the files to make sure that they
    point to the new locations.

As you update your view layer to take full advantage of the new back-end, you'll
update any references to the old paths. In addition, you must update the 
resource location in your component properties. In the next step, you'll update 
all of those properties, including the one that defines the resource location. 
