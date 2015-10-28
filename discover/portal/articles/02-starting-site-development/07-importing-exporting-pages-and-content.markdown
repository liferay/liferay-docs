# Importing/Exporting Pages and Content

Liferay's Export/Import feature gives you the power to backup and restore your
site's pages and content. The export feature grants users the flexibility of
exporting a page or portlet-specific content they've created as a LAR (Liferay
Archive) file to other portal instances, or to save it for a later use. The
import feature can be used to ingest the LAR file you exported from Portal,
which restores the work you previously exported.

For example, suppose you're managing a site that celebrates the Thanksgiving
holiday every year by creating a *Thanksgiving* themed page during the month of
November. Every November, you'd like to publish the holiday themed page, but
want it removed after Thanksgiving ends. Instead of manually creating the page
every year, just to delete it and create it again next year, you can use the
Export/Import feature to streamline the process. When the holiday ends every
year, you can export the page as a LAR file and save it outside your site. Then
when the holiday season approaches the following year, you can import the page,
make some minor tweaks, and publish it with little effort.

There are two primary places Export/Import is used: pages and portlet content.
In this section, you'll learn how to export and import both kinds of assets. The
first thing you'll learn is using the Export/Import tool for pages.

## Backing Up and Restoring Pages

In the Sites tab of the Product Menu, there is an option called *Publishing
Tools*, which is where the *Export/Import* features reside for pages. If you
click on *Export/Import*, you are presented two buttons labeled *Export* and
*Import*, which can be used for your public or private pages. The Export button
allows you to export your site's data as a single LAR file. When importing data
into a site, it's best to use a newly created site to avoid potential conflicts
between the existing site data and the data about to be imported. When exporting
site data, you can specify exactly what data should be included in the LAR:

- Site pages (you can select exactly which ones)
- Page settings
- Theme
- Theme settings
- Logo
- Application configurations
- Application content
- Archived setups
- User preferences

Once you've created a LAR file, you can import it into a site on another Liferay
server. The data included in the LAR file, including all the site pages, will
be imported into the site. Exporting and importing LARs is a great way to take
content from a site in one environment (say, a development or QA environment)
and move it all in one shot to a site on another server. You can use LARs to
import data onto production servers, but you should not make this a regular
occurrence. If you want to regularly move pages from one server to another, you
should use Liferay's staging environment, which we discuss in the
[Advanced Web Content Management](/discover/portal/-/knowledge_base/6-2/advanced-web-content-management)
chapter.

LARs can be a good way to back up your site's content. You can export them to a
specific location on your server which is backed up. If you ever have to restore
your site, all you need to do is import the latest LAR file. However, please be
careful! If there's any content that exists both in the LAR and in the site
that's importing the data, there may be a conflict, and data could be
corrupted.  If you'd like to restore a Liferay site using a LAR file, it's best
to delete the site entirely, create a new site with the same name as the old
one (i.e., re-create the site), and then import the LAR file into the new site.
This way, there's no chance for there to be a data conflict.

Liferay can handle some kinds of naming collisions when importing a LAR file
into a site. For example, suppose you're importing a LAR file into a site and
the LAR file has a page with a certain friendly URL. If an existing page in the
site has the same friendly URL there will be a collision. Liferay resolves the
collision by adding a number to the end of the friendly URL and incrementing
until there's no collision. This behavior takes place for friendly URL
translations as well. Similarly, if importing a LAR into a site causes a
category name collision, Liferay renames the imported categories.

+$$$

**Note:** LAR files are version dependent. You can't import a LAR file that was
exported from one version of Liferay into a Liferay server that's running a
different version of Liferay. Also, note that periodically exporting LARs is
*not* a complete backup solution; please refer to the
[Backing up a Liferay Installation](/discover/deployment/-/knowledge_base/6-2/backing-up-a-liferay-installation)
section for information on backing up Liferay.

$$$

Next, you'll simulate being a good administrator and exporting a LAR file for
backup purposes. Click on the *Export* button and then name the file
`lunarresortv1.lar`. Use the check boxes to determine what you'd like to export.
For this initial export, select everything. Note that if you select one of the
*Choose* radio selectors or *Change* links, you're given checkboxes for options
to choose. The applications' content can also be selected for export, including
the Documents and Media Library, Message Boards, and Web Content assets. You can
even export the theme you're using!

Once you click *Export*, the menu automatically switches to the *Current and
Previous* tab, where you'll see the status of your exported LAR file. You can
select the link under *Download* to download the export to your local machine.
Once you have the file, you can copy it to a backup location for safekeeping or
import it into another installation of Liferay Portal. If you must rebuild or
wish to revert back to this version of your site, you can import this file by
clicking the *Import* button from the Site Pages dialog box, browsing to it, and
selecting it.

Other options you may have noticed when you were configuring your export
configuration were the *Custom* and *Export Templates* tabs. The exercise you
completed previously was in the *Custom* tab. This tab lets you configure a
custom export, which gives you the flexibility to include as much, or as little,
as you'd like in your exported LAR file. The *Export Templates* tab allows you
to create a template that saves the custom options you can select to export.
This streamlines the process of exporting pages.

![Figure 1: You can configure your export options manually, or via an export template.](../../images/export-page-templates.png)

If you export pages frequently and usually select the same options to export,
the task of selecting options repeatedly can become tedious. With export
templates, you can select a custom template and immediately export with the
options you configured.

To create an export template, select the *Export Templates* tab and click *New*.
Assign the template a name and description, and then fill out the configuration
options as you would during a custom export process. Once you've saved your
export template, it is available to use from the *Export Templates* tab. To use
the template, click the Up arrow button next to the template. This automatically
fills the fields and options for exporting pages and their content. Once you
click *Export* to confirm the configuration settings, your LAR file is
generated.

Next, you'll learn how to export and import portlet content.

## Importing/Exporting Portlet Content

<!-- This section needs heavy editing. -Cody -->

Some Liferay portlets allow you to export or import portlet data. These include
many of Liferay's collaborative applications, such as the Blogs, Wiki, and
Message Boards portlets. To export or import portlet data, click on the gear
icon of your portlet and select *Export/Import*. Exporting portlet data produces
a `.lar` file that you can save and import into another portlet application of
the same type. To import portlet data, you must select a `.lar` file. Be careful
not to confuse portlet-specific `.lar` files with site-specific `.lar` files.
See the section on [Creating and Managing Pages](discover/portal/-/knowledge_base/6-2/leveraging-liferays-multi-site-capabilities#creating-and-managing-pages) 
for a discussion of exporting and importing site page data. 

Let's explore the export process for portlets first.

![Figure 4.7: When exporting portlet data, you can choose what content to include.](../../images/portlet-export.png)

First, you can select a *Date Range* of content that you'd like to export.
Content that has been added to your portlet within your specified date range is
included in the `.lar` file. You also have options of choosing *All* dates or
just the most recently added content, or *Last*.

Next, by checking the *Content* box, you can choose specific content you'd like
to export. When you check the *Content* box, more options appear, allowing you
to choose specific kinds of metadata to include. For example, if you have a wiki
page with referenced content that you don't wish to include, you can simply
check the *Wiki Pages* checkbox and uncheck the *Referenced Content* checkbox.
Another option in the Content section of the Export/Import window is the
selection of content types. Two familiar content types in your portal is
*Comments* and *Ratings*. If you wish to include these entities in your `.lar`
file, select *Change* and select them from the checklist.

Lastly, you can choose whether to include permissions for your exported content.
The permissions assigned for the exported portlet window will be included if the
*Permissions* option is checked. When you check this box, a sub-box called
*Permissions Assigned to Roles* appears. If you wish, you can export your
portlet's permissions but not the permissions assigned to roles. After you've
exported your portlet's data, switch to the *Current and Previous* tab to view
ongoing export processes and the history of past exports.

![Figure 4.8: When importing portlet data, you can choose a LAR file using the file explorer or drag and drop the file between the dotted lines.](../../images/portlet-import.png)

To import portlet data, you can select the LAR using your file explorer or by
dragging and dropping the file between the dotted lines. After selecting the LAR
file, you're given a similar screen to what you'd be offered during export.
Select the appropriate content and permissions, and click *Continue*.

The next screen offers options split into two sections -- *Update Data* and
*Authorship of the Content*. Here's options and descriptions for each section:

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

Next, you'll discuss creating site teams.
