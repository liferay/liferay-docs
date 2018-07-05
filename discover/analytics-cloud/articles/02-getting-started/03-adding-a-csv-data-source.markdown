# Adding a CSV Data Source [](id=adding-a-csv-data-source)

You can import contact profile data from CSV files to enrich customer profiles
with additional data related to a user's industry, job title, annual income, or
whatever metrics are important to your business. If you have customer profile
data in databases or collect it through web forms, you can export the data into
CSV files. 

**Important:** The CSV files must have an email column. 

Here's how to integrate contact data from a CSV file:

1. In the Data Sources page, click *Add Data Source*. A page
   appears showing the data source types. 

2. Select the *CSV File* icon. The CSV file upload page appears.

3. Upload your CSV file by dragging it into the upload area or browsing and
   selecting it from your file system. Click *Next* to upload the
   file. The CSV configuration page appears. 

4. Configure details and metadata about your CSV file data source, and click
   *Next* when you’re done.

    1. *Name:* Enter a name for your data source. 

    2. *Context:* Select a context that describes where the contact data is
       from. On selecting context, fields appear for you to further describe the
       data.

    3. *View Data Preview:* Shows the raw data in table format. If it's not
       formatted the way you want, adjust your CSV file's format. 

5. Follow instructions for
[Mapping Contact Data](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/04-mapping-contact-data.markdown)
to map contact data from your CSV file to your Analytics Cloud contact data
model. Once you've mapped the data, click *Next*. 

![Figure 1: When configuring a CSV file data source, you can describe the data context and view the data to make sure it’s formatted properly.](../../images/configure-csv-data-source.png)

The contact profile data starts syncing. The time it takes to sync depends on
the number of contacts. 

From time to time you might need to modify your data source configurations. If
you've already explored *Mapping Contact Data* (the next topic) skip
ahead to *Editing Data Sources*, to see how easy it is.
