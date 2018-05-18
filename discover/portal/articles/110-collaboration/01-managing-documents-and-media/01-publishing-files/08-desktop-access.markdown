# Desktop Access to Documents and Media [](id=desktop-access-to-documents-and-media)

You can access the Document Library from your desktop file manager via 
[WebDAV](https://en.wikipedia.org/wiki/WebDAV). 
WebDAV is a set of methods based on HTTP that let users create, edit, move, or 
delete files stored on web servers. WebDAV is supported by most major operating 
systems and desktop environments, including Linux, macOS, and Windows. Using
your file manager via WebDAV doesn't bypass the functionality of the web
interface---@product@ increments the version numbers of files edited and
uploaded via WebDAV. 

To access the Document Library folder from a file browser, you must use your
log-in credentials and the WebDAV URL of the folder you want to access. Follow
these steps: 

1.  Navigate to the Documents and Media app that contains the folder you want to 
    access. Click on the folder's Actions icon 
    ![Actions](../../../../images/icon-actions.png) and select *Access from 
    Desktop*. 

    ![Figure 1: Select *Access from Desktop* to get the folder's WebDAV URL.](../../../../images/dm-access-from-desktop-action.png)

2.  Copy the WebDAV URL and follow the instructions for your operating system:

    -   **Windows:** Map a network drive drive to the WebDAV URL. Enter your 
        credentials when prompted. The Document Library folder appears in the
        network drive. From your file browser, you can now add, edit, move, or
        delete files in this folder. 

    -   **macOS:** In the Finder, select *Go* &rarr; *Connect to Server*. In the 
        Server Address field, enter the WebDAV URL of the folder you want to 
        access, then click *Connect* and enter your credentials when prompted. 

    -   **Linux:** In your file manager, you must slightly modify the Document 
        Library folder's WebDAV URL. For KDE's Dolphin, change the URL's 
        protocol to `webdav://` instead of `http://`. For GNOME's Nautilus, 
        change the URL's protocol to `dav://` instead of `http://`. Then press 
        *Enter* and enter your credentials when prompted. 

Now you can access the Document Library folder from your desktop file system. If 
you edit a file in this folder on your file system, the change also shows up in 
the same Document Library folder in the portal. What's more, the file's minor 
version is incremented due to the edit. 
