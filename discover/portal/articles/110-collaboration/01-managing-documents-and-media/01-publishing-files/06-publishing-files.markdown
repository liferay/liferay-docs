# Publishing Files [](id=publishing-files-site)

Once your Document Library contains files, you may want to publish them in your
site. Here are some ways to publish files:

-   Show them in a Documents and Media app.
-   Display them in a Media Gallery.
-   Use the Asset Publisher.
-   Insert them in an asset like a web content article or blog entry.

Here, you'll learn to use the Media Gallery. 

## Using the Media Gallery [](id=using-the-media-gallery)

The Media Gallery publishes your media files in a simple gallery-like style. It
shows a large thumbnail of each media file, lets the user download files, and
has slideshow capabilities. A common way to use the Media Gallery is to create
a separate page for displaying media and add a Media Gallery widget to it. This
way, your media takes center stage. 

Follow these steps to create a page that contains a Media Gallery widget: 

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages) 
    and navigate to it in your site. 

2.  At the top-right of the screen, click the *Add* icon
    (![Add](../../../../images/icon-add-app.png)) then navigate to 
    *Widgets* &rarr; *Content Management* and select *Add* next to *Media 
    Gallery* (alternatively, drag the Media Gallery onto your page). The Media 
    Gallery widget appears on the page. 

3.  Configure the Media Gallery widget to show your files. By default, it shows 
    files from the Home folder of your site's Documents Library. To choose a 
    different folder, click the widget's Options icon 
    (![Options](../../../../images/icon-app-options.png)) and select 
    *Configuration*. 

    The Configuration window appears and shows the *Setup* tab. This tab 
    contains these sections: 
 
    **Display Settings:** Lets you show each file's actions, filter the 
    media types to display, and choose a display template for your media. 

    **Folders Listing:** Lets you select a Document Library folder to serve
    as the root folder from which to display files. The root folder you 
    select becomes the highest-level folder the Media Gallery can access. 
    For example, if you create a subfolder of a parent folder, and then set 
    that subfolder as the Media Gallery's root folder, the Media Gallery can 
    no longer access the parent folder. 

    +$$$

    **Note**: To access the Carousel display template in Media Gallery, your 
    role must have View access for that template. Since the Carousel template is 
    in the Global scope, a Global-scope administrator must grant the role 
    permission to view the template. 

    $$$

    ![Figure 1: You can configure the Media Gallery to use any Documents and Media folder as its root folder.](../../../../images/dm-select-root-folder.png)

4.  Configure the rest of the settings as desired in the Media Gallery app's 
    other configuration tabs: 

    **Communication:** Lists public render parameters the widget publishes 
    to other widgets on the page. Other widgets can take action on these 
    parameters. For each shared parameter, you can specify whether to allow 
    communication via the parameter and select which incoming parameter can 
    can populate it. 

    **Sharing:** Lets you embed the widget instance as a widget on on any 
    website, Facebook, Netvibes, or as an OpenSocial Gadget. 

    **Scope**: Lets you specify the Document Library instance the widget 
    uses: the current site's instance (default), the global instance, or the 
    page's instance. If the page doesn't already have an instance of the 
    widget, you can select *Your Page (Create New)* to create a page-scoped 
    instance for the widget to display. 

5.  Click *Save* when you're finished configuring the Media Gallery widget. 

The Media Gallery now shows your files, with images appearing as thumbnails. 
When you click a thumbnail, a slideshow appears showing the selected image. 
Below that image, thumbnails of the folder's other images are displayed. The 
slideshow continues until you click pause or view the last image. Closing the 
slideshow window returns you to the page. 

![Figure 2: The Media Gallery renders large thumbnail images of media files.](../../../../images/dm-media-gallery.png)

![Figure 3: The Media Gallery's slideshow provides a nice way to view images.](../../../../images/dm-media-gallery-slideshow.png) 
