# Managing Data Sources [](id=managing-data-sources)

Liferay Analytics Cloud requires two kinds of data. First, you need web
analytics data on user interactions with Liferay DXP Pages and Assets. Second,
you need profile data on the users themselves. Analytics Cloud can sync these
two kinds of data so that you can see not only how users are interacting with
your site, but also who those users are. First, however, you must provide it
with appropriate data sources.

Both kinds of data can be obtained from a Liferay DXP instance. If you have
other user profile data---in addition to what is already stored in Liferay DXP
user models---you can import it from a CSV file. In a future Analytics Cloud
release, Liferay plans to support syncing with contact data from Salesforce too.

The contact data is merged and consolidated into a single customer view. Liferay
DXP data sources let you select Organizations and User Groups to sync.
Individual contacts are matched by email address. You can define the contact
data model using whatever field names and types you want, but Analytics Cloud
makes it easy to define trivial model fields by suggesting values.

Your data sources can be viewed from the navigation panel.

1. Select *Settings*.

2. Click on *Data Sources*.

The Data Sources page appears and lists all existing data sources.

![Figure 1: View, edit, and add data sources from the Data Sources page.](../../images/data-source-list.png)

Unless a teammate has already added a data source, the list is empty. To add
a new data source, see the following tutorials:

- [Adding a Liferay DXP Data Source](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown)
- [Adding a CSV Data Source](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/03-adding-a-csv-data-source.markdown)

Once you've created your data sources, you might need to modify them from time to time.

## Modifying a Data Source [](id=editing-a-data-source)

The Data Sources lets you page through the data source listing or use *Search*
to find one that matches a keyword. Once you've found the data source, click on
it to edit it. Here's what you can modify:

- Name of the data source
- Contact mapping
- Enable/disable Contact or Analytics sync 

Here's how to delete a data source:

1. Click on the listed data source. The data source's page appears. 

2. Click *Delete Data Source*. 

Your Liferay DXP instances are rich sources of customer profile and behavior
data. Adding a Liferay DXP instance as a data source is next. 
