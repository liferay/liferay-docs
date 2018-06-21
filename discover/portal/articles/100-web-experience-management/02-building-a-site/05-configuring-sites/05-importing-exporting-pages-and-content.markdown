# Importing/Exporting Sites and Content [](id=importing-exporting-pages-and-content)

Export/Import lets you backup and restore your site and app data as a LAR (Liferay Archive). There are two primary places Export/Import is used: sites and apps. You can learn more about exporting/importing app data in the
[Exporting/Importing App Content](/discover/portal/-/knowledge_base/7-1/exporting-importing-app-data)
section. In this section, you'll learn how to export and import content for
sites.

## Backing Up and Restoring Pages and Their Content [](id=backing-up-and-restoring-pages-and-their-content)

In *Site Administration* &rarr; *Publishing*, you can find the *Export* and 
*Import* option for pages. If you click on *Export*, you are presented a simple 
interface for exporting your public or private pages. The Export feature 
exports your site's data as a single LAR file. Similarly, if you click 
*Import*, you're provided a similar interface for importing public or private pages from a LAR file.

When importing data into a site, it's best to use a newly created site to avoid
conflicts between the existing data and the data being imported. When exporting site data, you can specify exactly what data should be included in the LAR:

- Site pages (you can select exactly which ones)
- Page settings
- Theme
- Theme settings
- Logo
- Application configurations
- Application content
- Archived setups
- User preferences

A LAR file, can be imported into a site on another Liferay server. The data 
included in the LAR file will be imported into the site. You can take content 
from a site in one environment (say, a development or QA environment) and move 
it all to a site on another server with LARs. You can use LARs to import data 
onto production servers, but you should not make this a regular occurrence. If 
you want to regularly move pages from one server to another, you should use 
@product@'s staging environment, which is discussed in the [Staging Content for Publication](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication) section.

You can export LARs to a specific location use them as a backup. If you ever 
have to restore your site, all you need to do is import the latest LAR file. 
However, please be careful! If there's any content that exists both in the LAR 
and in the site that's importing the data, there may be a conflict, and data 
could be corrupted. If you want to restore a Liferay site using a LAR file, 
delete the site entirely, create a new site with the same name as the old one, 
and then import the LAR file into the new site. This way, there's no chance for 
there to be a data conflict.

Some naming collisions are handled automatically. For example, if the LAR your importing and the site both have a page with the same friendly URL there will be a collision. @product@ resolves the collision by adding a number to the end of the friendly URL and incrementing until there's no collision. Similarly, if importing a LAR into a site causes a category name collision, the imported categories are automatically renamed.

+$$$

**Note:** LAR files are version dependent. You can't import a LAR file that was
exported from one version of Liferay into a Liferay server that's running a
different version of Liferay. Also, note that periodically exporting LARs is
*not* a complete backup solution; please refer to the
[Backing up a Liferay Installation](/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation)
section for information on backing up Liferay.

$$$

## Page Export Example

Next, you'll simulate being a good administrator and exporting a LAR file for
backup purposes.

1.  Go the *Site Administration* &rarr; *Publishing*

2.  Click on the *Export* button.

3.  Click the *Add* button (![Custom Export](../../../images/icon-add.png)). 

A *New Custom Export* page loads, allowing you to configure what pages and 
content you'd like to export from your site. 

1.  Enter "Lunar Resort Version 1" for the *Title*.

2.  Under *Pages* select public or private pages and their settings you'd like 
    to export.
    
3.  Under the *Content* category, select *All*.

    Note that if you select one of the *Choose* radio selectors or *Change* 
    links, you're given checkboxes for options to choose. The applications' 
    content can also be selected for export, including the Documents and Media 
    Library, Message Boards, and Web Content assets. You can even export the 
    theme you're using. Lastly, you can select whether the permissions for your 
    exported pages and content are included.

![Figure 1: You can configure your export options manually by selecting pages, content, and permissions.](../../../images/export-page-templates.png)

4. Click *Export*.

Once you click *Export*, the menu automatically switches to the *Processes* tab,
where you'll see the status of your exported LAR file. You can select the
*Download* icon (![Download](../../../images/icon-download.png)) to download the
export to your local machine. Once you have the file, you can copy it to a
backup location for safekeeping or import it into another installation of
Liferay. If you must rebuild or wish to revert back to this version of your
site, you can import this file by clicking the *Import* button from the
Publishing menu, browsing to it, and selecting it. You also have the option of
dragging a LAR file inside the dotted area, which also executes the import
process.

## Export Templates

You can also use *Export Templates* when exporting content.Instead of manually 
having to customize an export process every time you export pages/content, you 
can use an export template. This provides you the convenience of storing export 
process settings so they can be reused. If you export pages frequently and 
usually select the same options to export, you can create an export template to 
quickly export with your standard options.

To create an export template:

1.  Select the *Options* icon (![Options](../../../images/icon-options.png)) from the top right corner of the screen and select *Export Templates*.

2.  Click the *Add* button (![Add Export Template](../../../images/icon-add.png)).

3.  Assign the template a *Name* and *Description*.

4.  Fill out the configuration options for your export process.

5.  Click *Save*.

Your template is now available to use from the *Export Templates* menu. To use 
the template:

1.  Click the *Actions* (![Actions](../../../images/icon-actions.png)) next to the template.

2.  Select *Export*.

    This automatically fills the fields and options for exporting pages and 
    their content.

3.  Give the export a name.


4.  Click *Export* and your LAR file is generated.

