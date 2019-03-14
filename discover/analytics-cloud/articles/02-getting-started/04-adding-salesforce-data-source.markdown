# Adding a Salesforce Data Source

Analytics Cloud can integrate Salesforce data with data from Liferay DXP and 
other sources, automatically blending the data to build a comprehensive customer 
profile. This lets you create more accurate segmentation and enhance your site's 
personalization strategy. To do this, you must first add a Salesforce instance 
as a data source. Here, you'll learn how. 

## Adding the Data Source

1.  Select *Settings* &rarr; *Data Sources* &rarr; *Add Data Source*. This opens 
    the Add Data Source screen. 

2.  Select *Salesforce*. This opens the Configure Salesforce screen. 

3.  In the *Authorization* tab, enter the Salesforce instance's name and URL. 
    Under the *CLIENT CREDENTIALS* section, enter the Salesforce instance's 
    OAuth client ID and client secret. If you need help finding this 
    information, see the 
    [Salesforce documentation](https://help.salesforce.com/articleView?id=connected_app_overview.htm&type=5). 

4.  Click *Authorize & Save*. This begins importing the leads, contacts, and 
    accounts data from Salesforce. This data is integrated with the existing 
    Analytics Cloud data (creates new and merges existing individuals/accounts). 
    Note that this may take some time, depending how much data exists in the 
    Salesforce instance. 

    ![Figure 1: Enter the information needed to connect to your Salesforce instance.](../../images/salesforce-auth.png)

5.  The import progress is visible on the *CONFIGURE DATA SOURCE* tab. Click 
    *View* to see the mapping of each Salesforce field (for Accounts and 
    Individuals) to its corresponding Analytics Cloud field. 

    ![Figure 2: The CONFIGURE DATA SOURCE tab shows the status of the accounts and individuals imported from Salesforce, as well as the field mapping.](../../images/salesforce-config.png)
