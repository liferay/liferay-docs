# Importing/Exporting Sites and Content [](id=importing-exporting-pages-and-content)

Export/Import lets you backup and restore your Site and app data as a LAR
(Liferay Archive). There are two primary places Export/Import is used: Sites
and apps. You can learn more about exporting/importing app data in the
[Exporting/Importing Widget Content](/discover/portal/-/knowledge_base/7-1/exporting-importing-widget-data)
section. In this section, you'll learn how to export and import content for
Sites.

## Backing Up and Restoring Pages and Their Content [](id=backing-up-and-restoring-pages-and-their-content)

In *Site Administration* &rarr; *Publishing*, you can find the *Export* and 
*Import* option for pages. If you click on *Export*, you see an interface for
exporting your public or private pages. The Export feature exports your Site's
data as a single LAR file. Similarly, *Import* is a similar interface for
importing public or private pages from a LAR file.

When importing data into a Site, you should use a newly created Site to avoid
conflicts between the existing data and the data being imported. When exporting
Site data, you can specify exactly what data should be included in the LAR:

- Site pages (you can select exactly which ones)
- Page settings
- Theme
- Theme settings
- Logo
- Application configurations
- Application content
- Archived setups
- User preferences

A LAR file can be imported into a Site on another Liferay server. You can take
content from a Site in one environment (say, a development or QA environment)
and move it all to a Site on another server with LARs. You can use LARs to
import data onto production servers, but you should not make this a regular
occurrence. If you want to regularly move pages from one server to another, you
should use @product@'s staging environment. See 
[Staging Content for Publication](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication).

You can export LARs to use them as a backup. If you ever have to restore your
Site, you must only import the latest LAR file. However, please be careful! If
there's content that exists both in the LAR and in the Site that's importing
the data, there may be a conflict, and data could be corrupted. If you want to
restore a Liferay Site using a LAR file, delete the Site entirely, create a new
Site with the same name as the old one, and then import the LAR file into the
new Site. This way, there's no chance for there to be a data conflict.

Some naming collisions are handled automatically. For example, a collision
occurs if the LAR you're importing and the Site both have a page with the same
friendly URL. @product@ resolves the collision by adding a number to the end of
the friendly URL and incrementing until there's no collision. Similarly, if
importing a LAR into a Site causes a category name collision, the imported
categories are automatically renamed.

+$$$

**Note:** LAR files are version dependent. You can't import a LAR file that was
exported from one version of Liferay into a Liferay server that's running a
different version of Liferay. Also, note that periodically exporting LARs is
*not* a complete backup solution; please refer to the
[Backing up a Liferay Installation](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)
section for information on backing up Liferay.

$$$

## Page Export Example [](id=page-export-example)

Here's how the export process works: 

1.  Go the *Site Administration* &rarr; *Publishing*. 

2.  Click *Export*.

3.  Click *Add* (![Custom Export](../../../../images/icon-add.png)). 

    A *New Custom Export* page loads, so you can choose the pages and 
    content you want to export from your Site. 

4.  Enter "Lunar Resort Version 1" for the *Title*.

5.  Under *Pages*, select public or private pages and the settings you want 
    to export.
 
6.  Under the *Content* category, select *All*.

    Note that if you select one of the *Choose* radio selectors or *Change* 
    links, you're given checkboxes for options to choose. The applications' 
    content can also be selected for export, including the Documents and Media 
    Library, Message Boards, and Web Content assets. You can even export the 
    theme you're using. Finally, you can select whether the permissions for your 
    exported pages and content are included.

    ![Figure 1: You can configure your export options manually by selecting pages, content, and permissions.](../../../../images/export-page-templates.png)

7.  Click *Export*.

Once you click *Export*, the menu automatically switches to the *Processes* tab,
where you see the status of your exported LAR file. You can select the
*Download* icon (![Download](../../../../images/icon-download.png)) to download the
export to your local machine. Once you have the file, you can copy it to a
backup location for safekeeping or import it into another installation of
Liferay. If you must rebuild or wish to revert back to this version of your
Site, you can import this file by clicking the *Import* button from the
Publishing menu, browsing to it, and selecting it. You can also drag a LAR file
inside the dotted area, which also executes the import process.

## Export Templates [](id=export-templates)

Instead of manually customizing an export process every time you export
pages/content, you can use an Export Template. This provides you the
convenience of storing export process settings so they can be reused. If you
export pages frequently and usually select the same options to export, you can
create an export template to export with your standard options.

To create an export template,

1.  Select the *Options* icon (![Options](../../../../images/icon-options.png))
    from the top right corner of the screen and select *Export Templates*.

2.  Click the *Add* button (![Add Export Template](../../../../images/icon-add.png)).

3.  Assign the template a *Name* and *Description*.

4.  Fill out the configuration options for your export process.

5.  Click *Save*.

Your template is now available to use from the *Export Templates* menu. To use 
the template,

1.  Click the *Actions* (![Actions](../../../../images/icon-actions.png)) next to the template.

2.  Select *Export*.

    This automatically fills the fields and options for exporting pages and 
    their content.

3.  Give the export a name.

4.  Click *Export* and your LAR file is generated.

