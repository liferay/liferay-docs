# Adding a Liferay DXP Data Source [](id=adding-a-liferay-dxp-data-source)

Your Liferay DXP instances are rich with contact data from Users and web
analytics data on user interaction with Liferay DXP pages and assets. When you
Create Liferay DXP data sources, you can select web analytics data from the
Liferay DXP Sites you want. In Users and Organizations, you can choose to use
contact data from all Users or a specified subset of them. Before adding an
instance as a data source, though, you must connect it to your Analytics Cloud
project. 

## Liferay DXP Data Source Prerequisites [](id=liferay-dxp-data-source-prerequisites)

Follow these steps to connect your project to your Liferay DXP instance.

### Step 1: Install Required Liferay DXP Fix Packs [](id=step-2-install-required-liferay-dxp-fix-packs)

For Liferay DXP 7.1, 
[install fix pack 3](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/patching-liferay). 

For Liferay DXP 7.0, 
[install fix pack 60](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/patching-liferay). 

### Step 2: Register Analytics Cloud with your Liferay DXP instance 

Liferay DXP 7.1: 

1.  Download and install the
    [Liferay Plugin for OAuth 2.0](https://web.liferay.com/marketplace/-/mp/application/109571986).

2.  The plugin comes with Analytics Cloud pre-registered. Copy the *Client ID*
    and *Client Secret*  for connecting DXP with Analytics Cloud as a data
    source, as described in the next section.

Liferay DXP 7.0:

1.  Download and install the
    [Liferay Connector for OAuth 1.0a](https://web.liferay.com/marketplace/-/mp/application/45261909).

2.  [Register](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/oauth)
    Analytics Cloud as an OAuth application. 

3.  Copy the *Consumer ID* and *Consumer Secret* for connecting DXP with 
    Analytics Cloud as a data source, as described in the next section. 

Congratulations on setting up your Liferay DXP instance to be an Analytics Cloud
data source! It's time to add your Liferay DXP instance as a data source. 

## Adding the DXP Data Source [](id=adding-the-dxp-data-source)

Adding a Liferay DXP Data Source connects your Analytics Cloud project with
a Liferay DXP instance. 

Here's how to start adding your Liferay DXP data source:

1.  Select *Settings* in the navigation panel.

2.  Click *Data Sources*. A listing of your existing data sources appears.

3.  Click *Add Data Source*. The *Add Data Source* page appears. 

4.  Select the *Liferay DXP* icon. The *Configure Liferay DXP* page appears. The
    Authorization tab (selected by default) shows data source and client
    credentials fields. 

    *Description* 

    - *Name:* A name for your data source.
    - *URL:* The Liferay DXP instance URL. 

    *Client Credentials*

    - *Consumer Key/Client ID:* Key/ID for Analytics Cloud to access your Liferay DXP instance. 
    - *Consumer Secret/Client Secret:* Secret for Analytics Cloud to access your
    Liferay DXP instance. 

    In Liferay DXP 7.1, you can find the Client ID and Secret by navigating to 
    *Control Panel* &rarr; *Configuration* &rarr; *OAuth 2 Admin*.

    In Liferay DXP 7.0, you can find the Consumer Key and Secret on the *OAuth
    Admin* page of the *Control Panel*. 

5.  Click *Authorize*. A window appears and asks if you want to authorize 
    Analytics Cloud. 

6.  Click *Authorize*. You've advanced to the *Configure Data Source* tab. 

Here are the configuration options:

- *Configure All:* Launches a wizard for configuring assets, touchpoints, 
and contacts from the instance. 
- *Configure Contacts:* Configures the contact data only.
- *Configure Analytics:* Configures the assets and touchpoints only.

Configure everything at once:

1.  Click *Configure All*. The Liferay DXP data source wizard appears, showing
    the page for configuring contacts. 

2.  Configuring contacts involves selecting contacts to sync from the Liferay
    DXP instance and its Organizations and User Groups. 

    - *Sync All Contacts*: Selects all Liferay DXP instance contacts and disables options for selecting specific Organizations and User Groups.

    - *Sync By Organizations*: Selects contacts by Organization. 

    - *Sync By User Groups*: Selects contacts by User Group.

    ![Figure 1: Analytics Cloud lets you select and import contacts from a Liferay DXP instance and its Organizations and User Groups.](../../images/select-dxp-contacts-by-org.png)

3. Click *Next* to import the selected contacts. Analytics Cloud imports the
    contact data and attempts to map it to your Analytics Cloud contact data
    model. The initial contact data import can take 5 1/2 minutes per 1,000
    contacts. 

4. Follow instructions for 
    [Mapping Contact Data](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/04-mapping-contact-data.markdown)
    to  map contact data from your Liferay DXP instance to your Analytics Cloud
    contact data model. Once you've mapped the data, click *Next*. 

5. Click the *Done* button. 

The Contacts and Analytics data start syncing into Analytics Cloud. **Initially 
the sync takes a while. After the initial sync, changes are synced 
periodically.**

If you have contact profile data from other sources such as a database, you
might be able to export the data to a CSV file. Then you can add the CSV file as
a data source and import the contact profiles from it. Adding a CSV data source
is next. 
