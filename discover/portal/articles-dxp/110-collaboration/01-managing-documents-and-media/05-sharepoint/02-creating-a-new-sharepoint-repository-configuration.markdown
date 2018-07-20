# Creating a New SharePoint Repository Configuration [](id=creating-a-new-sharepoint-repo-configuration)

To connect to a remote SharePoint server you must create a repository 
configuration. To do so, follow these steps: 

1.  Inside your @product@ instance, open the Control Panel and go to 
    *Configuration* &rarr; *System Settings* &rarr; *Collaboration* &rarr; 
    *Sharepoint OAuth2*.
    
    ![Figure 1: Use the *Sharepoint OAuth2* system setting to create a new SharePoint repository configuration.](../../../../images-dxp/sharepoint-system-setting.png)

2.  Click the *Add* icon 
    (![Add](../../../../images-dxp/icon-portlet-add-control.png)) to begin 
    creating a new configuration. 

3.  In the new Repository form, specify values for the following fields (your 
    SharePoint administrator can provide you with this information): 

    - **Name:** The configuration's name.

    - **Authorization grant endpoint:** The URL used to request OAuth2 
    authorization grants. The URL follows this pattern in SharePoint Online: 
    `https://[your-site-name]/sharepoint.com/_layouts/oauthauthorize.aspx`. 

    - **Authorization token endpoint:** The ACS URL. In SharePoint Online with 
    Azure ACS, the URL follows this pattern: 
    `https://accounts.accesscontrol.windows.net/[App ID]/tokens/OAuth/2`.

    - **Client ID:** The client ID. 

    - **Client Secret:** The client secret. 

    - **Scope:** The permission set required for your tokens. Valid scopes are 
    configured during your app's SharePoint registration. 

    - **Tenant ID:** The Tenant ID. If you're using SharePoint Online, you can 
    use the same App ID you embedded in the *Authorization token endpoint*. 

    - **Site domain:** The site domain registered for your application. 

    - **Resource:** This value depends on the ACS service you use. In SharePoint 
    Online with Azure ACS, the value follows this pattern: 
    `00000003-0000-0ff1-ce00-000000000000/[your-site-name].sharepoint.com@[tenant ID]`.
    
    ![Figure 2: The New Repository form is where you specify access to the remote SharePoint server.](../../../../images-dxp/sharepoint-new-repo-configuration.png)

4.  Click *Save*.

Awesome! Now that your SharePoint repository is configured, you can mount it in 
@product@'s Documents and Media Library. 
