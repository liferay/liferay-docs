---
header-id: configuring-office-365-integration
---

# Configuring Office 365™ Integration

[TOC levels=1-4]

Before you can use Office 365&trade; to create and edit Documents and Media 
files, you must configure @product@ to connect with an application in the 
[Azure portal](https://portal.azure.com/). 

| **Note:** You must be an administrator to complete these steps. 

## Register an Application with the Microsoft Identity Platform

First, configure your application with the Microsoft identity platform&trade;. 
To do so, follow the steps described in 
[Microsoft's documentation](https://docs.microsoft.com/en-gb/graph/auth-register-app-v2). 

To construct a URL for the *Redirect URI* parameter, follow this pattern: 

    https://[hostname]/o/document_library/onedrive/oauth2

Here's the minimum permission set needed to use Office 365&trade; integration: 

-   Files.Read.All
-   Files.ReadWrite.All

For more information about permissions, see 
[Microsoft's documentation](https://docs.microsoft.com/graph/permissions-reference). 

## Configuring @product@

Now you must connect your @product@ installation with your Microsoft identity 
platform&trade; application. You can do this at two scopes: 

1.  Globally, for all instances in your @product@ installation.
2.  At the instance scope, for one or more instances in your @product@ 
    installation. 

You can override the global configuration for one or more instances by 
configuring those instances separately. Similarly, you can configure only the 
instances you want to connect to your application and leave the global 
configuration empty. 

Follow these steps to configure your @product@ installation to connect to your 
application: 

1.  Note that the configuration options are the same in the global and 
    instance-level configurations. 

    To access the global configuration, go to *Control Panel* &rarr; 
    *Configuration* &rarr; *System Settings* &rarr; *Documents and Media*. 

    To access the instance-level configuration, go to *Control Panel* &rarr; 
    *Configuration* &rarr; *Instance Settings* &rarr; *Documents and Media*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *OneDrive*. 

3.  Enter your application's OAuth 2 client ID and client secret in the 
    *Client ID* and *Client Secret* fields, respectively. 

4.  Enter your tenant ID in the *Tenant* field. To find your tenant ID, see 
    [Microsoft's documentation](https://docs.microsoft.com/onedrive/find-your-office-365-tenant-id). 

4.  Click *Save*. 

| **Note:** Once enabled, you can disable this feature by deleting the client 
| ID, client secret, and tenant values from the form. 

![Figure 1: Enter your application's client ID, client secret, and tenant.](../../../../images/onedrive-system-settings.png)
