# Displaying Files [](id=displaying-files)

Once you've uploaded image files and videos to your Document Library, you'll
probably want to display them in your site. Here are some ways you can display 
your media files:

- Show them in a Documents and Media app
- Display them in a Media Gallery
- Use the Asset Publisher
- Insert them in an asset, like a Web Content article or Blogs Entry

Continuing with the Lunar Resort example, the media team sits down with each
guest party to show them a page filled with photos and videos of their vacation
experience. The team likes to show guests a slideshow to get some oohs and ahh's
from them and determine their favorites. Then they offer to print and frame the
photos and plaster the photos on all kinds of fun items such as keychains,
coffee mugs, and tee-shirts. They even offer to burn media files onto DVDs.
Let's explore how the media team shows off the guest's pics and videos on a
site.

## Creating a Media Page [](id=creating-a-media-page)

The media team creates a page for each guest party. Team members can customize
each page to focus on each party's media. Each party's media page will have a
Media Gallery app. The Media Gallery shows a large thumbnail of each media file,
lets the user download individual files, and comes with slideshow capabilities.
Let's create a media page for the Spacey guest party and add the Media Gallery.

[Add a page](/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages)
named *Spacey Party*. Next, click the *Add* icon
(![Add](../../../../images/icon-add-app.png)), then navigate to *Applications
&rarr; Content Management*, and select *Add* next to *Media Gallery*. The app
appears on the page and shows your Home folder's files by default. Since we want
to focus on the Spacey party's files, let's configure the app to show files from
the *Spacey Party* folder. Click the app's Options icon
(![Options](../../../../images/icon-app-options.png)) and select 
*Configuration*. The Configuration window appears and shows the *Setup* tab.

The Setup tab's Display Settings section provides checkboxes to enable showing
each file's actions, gallery navigation links (for paging through media files),
and a search field. File search is enabled by default. The Media Type selector
lets you filter on media file types; all supported types are filtered on by
default. And the Media Gallery uses a
[Display Template](/participate/liferaypedia/-/wiki/Main/Application+Display+Templates).
You can create your own Display Templates for the Media Gallery or select an
existing display template, like the *Carousel* display template.

**Important**: To access the Carousel display template in Media Gallery, your
role must have *View* access for that template. Since the Carousel template is
in the Global scope, a Global scope administrator must grant the role permission
to view the template.

The Folders Listing section lets you select a Document Library folder to serve
as the root folder from which to display files. The root folder is the
highest-level folder that's accessible from the Documents and Media app. For
example, suppose you created a folder called *My Documents* in Documents and
Media's default Home folder. If you set the My Documents folder to be your app's
new root folder, the app accesses the My Documents folder and no longer accesses
the Home folder.

![Figure 1: The Documents and Media app can be configured to use any folder as a root folder to display.](../../../../images/dm-select-root-folder.png)

Lastly, the Ratings section of the Display Settings lets you enable users to
rate files and comment on them. 

Here are descriptions for the Media Gallery app's other configuration tabs:

**Communication**: lists public render parameters the application publishes to
other applications on the page. Other applications can read these and take
actions based on them. For each shared parameter, you can specify whether to
allow communication using the parameter and select which incoming parameter can
populate it. 

**Sharing**: lets you embed the application instance as a widget on on any
website, Facebook, or Netvibes, or as an OpenSocial Gadget.

**Scope**: lets you specify the Document Library instance the application uses:
the current site's instance (default), global instance, or the page's instance.
If the page doesn't already have an instance of the app, you can select scope
option *+ \[Page Name\] \(Create New\)* to create a page-scoped instance for the
application to display. 

While we're in the Configuration window there are a couple things we should do.
To enable the media team role member to act on the files, select the *Show
Actions* checkbox. Since we only want to show the Spacey's files, expand the
*Folders Listing* section, then click the *Select* button to browse and select
the *Spacey Party* folder. Then click *Save*.

The Media Gallery now shows all of the Spacey party's files. This app enables
the Lunar Resort media team to act on individual files. If Mrs. Spacey likes
particular files, the staff member can download them locally and add notes in
each file entry's comments.

![Figure 2: The Media Gallery renders large thumbnail images of media files. It gives users quick access to download files that interest them.](../../../../images/dm-media-gallery.png)

The Spacey Party's large image thumbnails display in the Media Gallery. When you
click on a file's thumbnail, a slideshow appears showing the selected image. The
window lists thumbnails of the folder's other images below the image that's
currently shown. And the slideshow continues until you either click pause or
view the folder's last image. The slideshow feature provides a nice way to show
off images. Click the X to close the slideshow window and return to the page. 

![Figure 3: The Media Gallery's slideshow provides a nice way to view images.](../../../../images/dm-media-gallery-slideshow.png) 

The Media Gallery and Documents and Media apps are at your service to show
off your Document Library files. The Lunar Resort's Souvenir and Memorabilia
group enjoys patron reactions to the great pics and videos that Snappy takes of
them. And the salespeople are more than happy to plaster the pictures onto
clothing and accessories to make a handsome commission. 

Next, you'll learn how to collaborate on files with other users. 
