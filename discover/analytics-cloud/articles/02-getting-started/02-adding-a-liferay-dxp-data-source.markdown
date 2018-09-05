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

### Step 1: Register your Liferay DXP instance with Liferay Connected Services [](id=step-1-register-your-liferay-dxp-instance-with-liferay-connected-services)

Register your Liferay DXP instance with Liferay Connected Services:

- [Liferay Connected Services for Liferay DXP 7.0](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/managing-liferay-with-liferay-connected-services) 

- [Liferay Connected Services for Liferay DXP 7.1](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-liferay-dxp-with-liferay-connected-services)

### Step 2: Install Required Liferay DXP Fix Packs [](id=step-2-install-required-liferay-dxp-fix-packs)

For Liferay DXP 7.0, 
[install fix pack 52](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/patching-liferay). 

### Step 3: Install the Liferay Analytics Cloud Connector [](id=step-3-install-the-liferay-analytics-cloud-connector)

1. Go to the
[downloads page](https://web.liferay.com/group/customer/dxp/downloads/analytics-cloud). 

2. Download the Connector ZIP file for your Liferay DXP version.

3. Stop your server. 

4. Unzip the Connector ZIP file to your `[Liferay Home]/deploy` folder.

5. Start your server.

**Note:** If you’re running Liferay DXP in a cluster, install the Connector 
using 
[rolling restarts](https://customer.liferay.com/documentation/7-1/deploy/-/official_documentation/deployment/using-rolling-restarts). 

Congratulations on setting up your Liferay DXP instance to be an Analytics Cloud
data source! It's time to add your Liferay DXP instance as a data source. 

## Adding the DXP Data Source [](id=adding-the-dxp-data-source)

Adding a Liferay DXP Data Source connects your Analytics Cloud project with
a Liferay DXP instance. 

Here's how to add a Liferay DXP data source:

1.  If you haven't already, configure your Liferay DXP instance to connect with
    Liferay Analytics Cloud (see above).

2.  Select *Settings* in the navigation panel.

3.  Click *Data Sources*. A listing of your existing data sources appears.

4.  Click *Add Data Sources*. The *Connect Data Source* page appears. 

5.  Select the *Liferay DXP* icon. The *Connect to Instance* page appears and
    lists your available Liferay DXP instances. 

6.  Select the instance to connect. The Liferay DXP Data Sources page appears, 
    showing the instance description fields and configuration options. 

    *Description*

    - *URL:* The Liferay DXP instance URL.
    - *Name:* A name for your data source. 

    *Configuration Options*

    - *Configure All:* Launches a wizard for configuring assets, touchpoints, 
    and contacts from the instance. 
    - *Configure Contacts:* Configures the contact data only.
    - *Configure Analytics:* Configures the assets and touchpoints only.

7.  Name your data source.

8.  Click *Configure All*. The Liferay DXP data source wizard appears, showing
    the page for configuring contacts. 

9.  Configuring contacts involves selecting contacts to sync from the Liferay
    DXP instance and its Organizations and User Groups. 

    - *Sync All Contacts*: Selects all Liferay DXP instance contacts and disables options for selecting specific Organizations and User Groups.

    - *Sync By Organizations*: Selects contacts by Organization. 

    - *Sync By User Groups*: Selects contacts by User Group.

    ![Figure 1: Analytics Cloud lets you select and import contacts from a Liferay DXP instance and its Organizations and User Groups.](../../images/select-dxp-contacts-by-org.png)

10. Click *Next* to import the selected contacts. Analytics Cloud imports the
    contact data and attempts to map it to your Analytics Cloud contact data
    model. The initial contact data import can take 5 1/2 minutes per 1,000
    contacts. 

11. Follow instructions for 
    [Mapping Contact Data](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/02-getting-started/04-mapping-contact-data.markdown)
    to  map contact data from your Liferay DXP instance to your Analytics Cloud
    contact data model. Once you've mapped the data, click *Next*. The Liferay
    DXP site analytics registration page appears. 

12. Select the Liferay DXP sites to register for analytics and click *Next*.
    The analytics key page appears. 

13. Integrate your Liferay DXP instance with Analytics Cloud by addressing
    these options: 

    - *Analytics Key:* This key is generated automatically and used to 
      communicate with your Liferay DXP instance. 
    - *Send Code:* Send the code hash to your web administrator to integrate 
      your instance with Analytics Cloud.
    - *Connection Status:* Test communication between your Liferay DXP instance 
      and Analytics Cloud. 

14. Click the *Done* button. 

The Contacts and Analytics data start syncing into Analytics Cloud. **Initially 
the sync takes a while. After the initial sync, changes are synced 
periodically.**

If you have contact profile data from other sources such as a database, you
might be able to export the data to a CSV file. Then you can add the CSV file as
a data source and import the contact profiles from it. Adding a CSV data source
is next. 
