# Organizing Folders for Larger Applications [](id=organizing-folders-for-larger-applications)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 1 of 6</p>
</div>

In larger projects, it is important to have all of your files and modules well 
organized. You'll make two changes to help better organize your project:

1.  Move the `guestbook-web` module into the `guestbook` folder so that it's in 
    the same place as the `guestbook-service` and `guestbook-api` modules that
    you created.
    
2.  Reorganize your JSPs to make room for more JSPs that will be part of the web
    module.

## Moving guestbook-web [](id=moving-guestbook-web)

The best way to move modules around is to your @ide@'s *Refactor* function. The 
refactor function will check any dependencies or links to the module being 
moved and attempt to update those as well.

1.  In the *Project Explorer*, right-click on `guestbook-web` and select
    *Refactor* &rarr; *Move*.

2.  In the window that appears, click *Browse*, navigate to the `guestbook` 
    folder and then click *New Folder*.
    
3.  Name the new folder `guestbook-web`.

4.  Click *Open* and then *OK* to confirm.

Your `guestbook-web` folder will now appear in the structure at the same level as the other modules.

![Figure 1: After you move it using the Refactor function, all of your modules will be in the same folder..](../../../images/guestbook-refactor.png)

## Reorganizing JSPs [](id=reorganizing-jsps)

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
