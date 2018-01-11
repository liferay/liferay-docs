# Exporting/Importing App Data [](id=exporting-importing-app-data)

There are times when you want to export/import specific content created from an
application, and not deal with site data at all. Many Liferay apps allow you to
export or import their app data indiviually. These include many of Liferay's
collaborative applications, such as the Blogs, Wiki, and Message Boards apps. To
export or import app data, click on the *Options* icon
(![Options](../../../images/icon-options.png)) of your app and select
*Export/Import*. Exporting app data produces a `.lar` file that you can save
and import into another application of the same type. To import app
data, you must select a `.lar` file. Be careful not to confuse app-specific
`.lar` files with site-specific `.lar` files. See the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
section for a discussion of importing and exporting site page data.

You can export/import app content two ways. You can navigate to the
app's administrative area located in the Menu, or you can visit the
indiviual app that resides on a Liferay page. Both export/import menus work
the same, but the administrative area may hold different content than its indiviual
app counterpart (e.g., Web Content Admin in Menu and Web Content Display app
will not offer same content for export/import), so be wary of your app
selection. To reach the Export/Import menu for an app listed in the Menu,
navigate to the app in the Menu, and then select the *Options* icon
(![Options](../../../images/icon-options.png)) from the top right of the page
and click *Export/Import*. Likewise, you can navigate to the Export/Import menu
for an individual app by navigating to the app's Options menu and selecting
*Export/Import*.

![Figure 1: You can access the *Export/Import* feature for an app by selecting its Options menu.](../../../images/portlet-export-import-feature.png)

You'll explore the export process for apps first.

First, you can choose to export your application's configuration settings. This
exports your customized settings that you've configured from your application's
*Options* &rarr; *Configuration* menu.

Next, you can select a *Date Range* of content that you'd like to export.
Content that has been added to your app within your specified date range is
included in the `.lar` file. You also have options of choosing *All* dates or
just the most recently added content, or *Last*.

By checking the *Content* box, you can choose specific content you'd like to
export. When you check the *Content* box, more options appear, allowing you to
choose specific kinds of metadata to include. For example, if you have a wiki
page with referenced content that you don't wish to include, you can simply
check the *Wiki Pages* checkbox and uncheck the *Referenced Content* checkbox.
Another option in the Content section of the Export/Import window is the
selection of content types. Two familiar content types in your Liferay instance
is *Comments* and *Ratings*. If you wish to include these entities in your
`.lar` file, select *Change* and select them from the checklist.

Lastly, you can choose whether to include permissions for your exported content.
The permissions assigned for the exported app window will be included if the
*Export Permissions* selector is enabled. After you've exported your app's data,
switch to the *Current and Previous* tab to view ongoing export processes and
the history of past exports.

![Figure 2: When importing app data, you can choose a LAR file using the file explorer or drag and drop the file between the dotted lines.](../../../images/import-menu.png)

To import app data, you can select the LAR using your file explorer or by
dragging and dropping the file between the dotted lines. Your LAR file is
uploaded and displayed to you for review. Click *Continue*.

Now that you've uploaded and confirmed your LAR file, you're given a similar
screen to what you'd be offered during export. Several of these options are
covered in great detail in the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
section. There are a couple of additional options, however, available -- *Update
Data* and *Authorship of the Content*. Here's options and descriptions for each
section:

**Update Data**

* *Mirror*: All data and content inside the imported LAR will be created as new
the first time while maintaining a reference to the source. Subsequent imports
from the same source will update entries instead of creating new entries.
* *Mirror with overwriting*: Same behavior as the mirror strategy, but if a
document or an image with the same name is found, it is overwritten.
* *Copy as New*: All data and content inside the imported LAR will be created as
new entries within the current site every time the LAR is imported.

**Authorship of the Content**

* *Use the Original Author*: Keep authorship of imported content whenever
possible. Use the current user as author if the original one is not found.
* *Use the Current User as Author*: Assign the current user as the author of all
imported content.

Once you've selected the appropriate options, select *Import*, and your
app's data is imported and ready for use.

Next, you'll learn how to communicate between applications in a Liferay
instance.
