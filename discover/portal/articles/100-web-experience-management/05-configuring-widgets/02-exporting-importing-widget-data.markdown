# Exporting/Importing Widget Data [](id=exporting-importing-app-data)

There are times when you want to export/import specific content created from an
application, and not deal with site data at all. Many Liferay apps allow you to
export or import their widget data individually.

Exporting widget data produces a `.lar` file that you can save and import into 
another widget of the same type. Be careful not to confuse widget-specific 
`.lar` files with  site-specific `.lar` files. See the [Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content) section for a discussion of importing and exporting site page data.

You can export/import widget content two ways. You can navigate to the
widget's administrative area located in Site Administration, or you can visit 
the individual widget that resides on a Liferay page. If your widget is not 
site-scoped, it will have different data to export than the Site Administration 
configuration application. To reach the Export/Import menu for an widget listed 
in the Menu:

1.  Go to the widget in the Menu.

2.  Select the *Options* icon (![Options](../../../images/icon-options.png)) from the top right of the page.

3.  Click *Export/Import*.

Likewise, you can navigate to the Export/Import menu for an individual widget:

1.  Go to the page where the widget resides

2.  Click on the *Options* icon (![Options](../../../images/icon-options.png))

3.  Select *Export/Import*.

![Figure 1: You can access the *Export/Import* feature for an widget by selecting its Options menu.](../../../images/portlet-export-import-feature.png)

## Exporting Data

The export process for widgets goes like this:

1.  Choose if you want to export your widgets's configuration settings. This
    exports your customized settings that you've configured from your 
    widget's *Options* &rarr; *Configuration* menu.

2.  Select a *Date Range* of content that you'd like to export. Content that 
    has been added to your widget within your specified date range is included in 
    the `.lar` file. You also have options of choosing *All* dates or just the 
    most recently added content, or *Last*.

3.  (Optional) Check the *Content* box, to choose specific content you'd like to
    export. When you check the *Content* box, more options appear, allowing you 
    to choose specific kinds of metadata to include. For example, if you have a 
    wiki page with referenced content that you don't wish to include, you can 
    simply check the *Wiki Pages* checkbox and uncheck the *Referenced Content* 
    checkbox.

4.  (Optional) Click *Change* under *Content Types* to specify specific content
    types to export.

5.  Check *Export Individual Deletions* if you want the history of delete 
    operations to be included in the export.

6.  Check *Export Permissions* if you want permissions assigned for the exported widget window to be included in the export.

7.  Click *Export*.

After you've exported your widget's data, switch to the *Current and Previous* tab to view ongoing export processes and the history of past exports.

![Figure 2: When importing widget data, you can choose a LAR file using the file explorer or drag and drop the file between the dotted lines.](../../../images/import-menu.png)

## Importing Data

To import widget data:

1.  Select the *Import* tab.

2.  Select the LAR using your file explorer or by dragging and dropping the file between the dotted lines. Your LAR file is uploaded and displayed to you for review.

3.  Click *Continue*.

Now that you've uploaded and confirmed your LAR file, you're given a similar
screen to what you'd be offered during export. Several of these options are
covered in great detail in the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content)
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
widget's data is imported and ready for use.

Next, you'll learn how to communicate between widgets in a Liferay
instance.
