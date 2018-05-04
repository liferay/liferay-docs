# Displaying Files

Once your Document Library contains files, you'll likely want to display them in 
your site. Here are some ways you can display your media files:

-   Show them in a Documents and Media app.
-   Display them in a Media Gallery.
-   Use the Asset Publisher.
-   Insert them in an asset, like a Web Content article or Blogs Entry.

Here, you'll learn to use the Media Gallery. 

## Using the Media Gallery

The Media Gallery app, as its name implies, lets you display your media files in 
a simple gallery-like style. It shows a large thumbnail of each media file, lets 
the user download individual files, and comes with slideshow capabilities. A 
common way to use this app is to create a separate page for displaying media and 
add a Media Gallery app to it. This way, your media takes center stage. 

Follow these steps to create a page that contains the Media Gallery app: 

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages) 
    and navigate to it in your site. 

2.  An the top-right of the screen, click the *Add* icon
    (![Add](../../../../images/icon-add-app.png)) then navigate to 
    *Applications* &rarr; *Content Management* and select *Add* next to *Media 
    Gallery* (alternatively, drag the Media Gallery onto your page). The Media 
    Library app appears on the page. 

3.  Configure the Media Gallery to show the files you want. By default, it shows 
    files from the Home folder in your site's Documents Library. To choose a 
    different folder, click the app's Options icon
    (![Options](../../../../images/icon-app-options.png)) and select 
    *Configuration*. 

    The Configuration window appears and shows the *Setup* tab. This tab 
    contains these sections: 
    
    -   **Display Settings:** Lets you show each file's actions, filter the 
        media types to display, and choose a display template for your media. 
    -   **Folders Listing:** Lets you select a Document Library folder to serve
        as the root folder from which to display files. The root folder is the
        highest-level folder that's accessible from the Documents and Media app. 
        For example, if you create a subfolder of a parent folder, and then set 
        that subfolder as the Media Gallery's root folder, the app no longer 
        accesses the parent folder. 

    +$$$

    **Note**: To access the Carousel display template in Media Gallery, your
    role must have *View* access for that template. Since the Carousel template 
    is in the Global scope, a Global scope administrator must grant the role 
    permission to view the template. 

    $$$

    ![Figure 1: The Documents and Media app can be configured to use any folder as a root folder to display.](../../../../images/dm-select-root-folder.png)

4.  Configure the rest of the settings as desired in the Media Gallery app's 
    other configuration tabs: 

    -   **Communication:** Lists public render parameters the app publishes to 
        other apps on the page. Other apps can take actions based on these 
        parameters. For each shared parameter, you can specify whether to allow 
        communication using the parameter and select which incoming parameter 
        can populate it. 

    -   **Sharing:** Lets you embed the app instance as a widget on on any 
        website, Facebook, Netvibes, or as an OpenSocial Gadget.

    -   **Scope**: Lets you specify the Document Library instance the app uses:
        the current site's instance (default), global instance, or the page's 
        instance. If the page doesn't already have an instance of the app, you 
        can select *Your Page (Create New)* to create a page-scoped instance for 
        the app to display. 

5.  Click *Save* when you're finished configuring the Media Gallery app. 

![Figure 2: The Media Gallery renders large thumbnail images of media files. It gives users quick access to download files that interest them.](../../../../images/dm-media-gallery.png)

The Media Gallery now shows your files, with images appearing as thumbnails. 
When you click on a file's thumbnail, a slideshow appears showing the selected 
image. The window lists thumbnails of the folder's other images below the image 
that's currently shown. The slideshow continues until you either click pause 
or view the folder's last image. Closing the slideshow window and returns you to 
the page. 

![Figure 3: The Media Gallery's slideshow provides a nice way to view images.](../../../../images/dm-media-gallery-slideshow.png) 
