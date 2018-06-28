# Managing Data Sources [](id=managing-data-sources)

Analytics Cloud lets you sync contact data, such as customer profiles from CSV
files and Liferay DXP user models, and web analytics data on user interaction
with Liferay DXP pages and assets. In a future Analytics Cloud release, Liferay
plans to support syncing with contact data from Salesforce too.

The contact data is merged and consolidated into a single customer view. Liferay
DXP data sources let you select Organizations and User Groups from which to
sync. Individual contacts are matched by email address. The best part is that
you can define the contact data model your way---you can use field names and
types that work best for you. Analytics Cloud even takes the guesswork out of
defining trivial model fields by suggesting values. 

Configuring web analytics from Liferay DXP sites is straightforward, letting you
select the sites from which to sync.  

To see your data sources:

1. Select *Settings* in the navigation panel.

2. Click on *Data Sources*.

The Data Sources page appears and lists all existing data sources.  

![Figure 1: The Data Sources page lets you view, edit, and add data sources.](../../images/data-source-list.png)

Unless a teammate has already added a data source, the list is empty. You'll add
some data sources shortly. Before then, you'll get an overview of adding and
managing data sources.

## Adding a Data Source [](id=adding-a-data-source)

Adding a data source straightforward:

1.  Click on the *Add Data Source* button. The *Connect Data Source* page 
appears and lists the supported data source types. 

    - *Liferay DXP:* A Liferay DXP 7.1 or 7.0 instance.
    - *CSV File:* A comma-separated version file. 

2.  Here you can select the type of data source to add. The following articles 
show you how to configure their prerequisites and add them. 

    - [Adding a Liferay a DXP Data Source](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown)
    - [Adding a CSV Data Source](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/03-adding-a-csv-data-source.markdown)

Once you've created your data sources, you might need to modify them from time to time.

## Editing a Data Source [](id=editing-a-data-source)

The Data Sources lets you page through the data source listing or use *Search*
to find one that matches a keyword. Once you've found the data source, click on
it to edit it. Here’s what you can modify:

- Name of the data source
- Contact mapping
- Enable/disable Contact or Analytics sync 

Your Liferay DXP instances are a rich source for customer profile and behavior
data. Adding a Liferay DXP instance as a data source is next. 
