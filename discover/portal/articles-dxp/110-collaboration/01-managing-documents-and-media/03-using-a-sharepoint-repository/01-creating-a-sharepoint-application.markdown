# Creating a SharePoint Application [](id=creating-a-sharepoint-application)

The steps covered in this section apply to SharePoint Online. If you're using 
SharePoint On-Premises, please consult your administrator for the requirements.

Follow these steps to register @product@ as an application in a SharePoint 
instance:

1.  Go to your SharePoint installation's URL: 
    `https://[your-site-name].sharepoint.com/_layouts/15/appregnew.aspx`.

2.  Provide the information below for your app:

    - **Title:** The name displayed in Documents and Media.
    - **Domain Name:** The application's domain name along with the port (e.g., 
    `localhost:8228`)
    - **Redirect URL:** The application's URL. The URL must use HTTPS.

3.  Click the two *Generate* buttons to generate a client ID and client secret 
    for @product@.

    Here's an example configuration:

    - **Client ID:** `1234a56b-7890-1234-5ccc-67d8ea9b0c1c`
    - **Client Secret:** `1ABCDEfGh2IJKLmNoP3QrStuvwX41YzAB+CDEFg20G3=`
    - **Title:** `My Application's Title`
    - **App Domain:** `localhost:8228`
    - **Redirect URL:** `https://localhost:8228/c/document_library/sharepoint/oauth2`

3.  Now you must grant @product@ write and search permissions over the 
    SharePoint instance. Other permissions are ignored. Go to 
    `https://[your-site-name].sharepoint.com/_layouts/15/appinv.aspx`.

    In the *APP ID* field, enter the Client ID of the application you just 
    created and click *Search*. Consult Microsoft's  [documentation](https://docs.microsoft.com/en-us/sharepoint/dev/sp-add-ins/add-in-permissions-in-sharepoint) 
    for details on how to configure the Permission Request XML. Here's an 
    example configuration that grants the application write and search 
    permissions over the SharePoint instance: 

        <AppPermissionRequests>
            <AppPermissionRequest
                scope="http://sharepoint/content/sitecollection/web/list"
                Right="Write" 
            />
            <AppPermissionRequest
                scope="http://sharepoint/search"
                Right="QueryAsUserIgnoreAppPrincipal" 
            />
        </AppPermissionRequests>
    
4.  Once you provide the permissions XML, click *Create*.

5.  Next, go to *Settings* &rarr; *Site App Permissions* in your SharePoint 
    installation. You can also access this page directly with a URL similar to 
    this: `https://[your-site-name].sharepoint.com/_layouts/15/appprincipals.aspx?Scope=Web`. 
    Find your application in the registration list and note its *app 
    identifier*. You'll use this to create a repository configuration. Here's an 
    example app identifier: 
    
        i:0i.t|ms.sp.ext|6123d38d-2998-4972-9aaa-71a4da9f3a5a@b9c24ab3-ad34-4943-ab57-729d8c329053
        
Great! Now @product@ is registered as an application in your SharePoint 
instance. Next, you must create a new SharePoint repository type. 
