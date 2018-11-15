# Linking to Google Drive™ [](id=linking-to-google-drive)

You can create Document Library files that link to files in Google Drive&trade; 
and Google Photos&trade;. This lets you access your Google files from the 
Document Library. Note that this functionality isn't available by default. To 
enable it, you must complete these steps: 

1.  Install the Liferay Plugin for Google Drive&trade; from Liferay Marketplace. 

2.  Create and/or configure a Google project capable of communicating with your 
    @product@ instance. The 
    [Google Picker API](https://developers.google.com/picker/) 
    must be enabled for this project. This API lets you select Google files to 
    link to. You must also create the credentials the Google project needs to 
    communicate with your @product@ instance. 

3.  Configure your @product@ instance to communicate with your Google project. 

This article shows you how to complete these steps and finishes with an example 
of linking to a Google file from the Document Library. 

+$$$

**Important:** The Liferay Plugin for Google Drive&trade; is a Labs application 
available for Liferay CE Portal and Liferay DXP. Labs apps are experimental and 
not supported by Liferay. They're released to accelerate the availability of 
useful and cutting-edge features. This status may change without notice. Use 
Labs apps at your own discretion. 

$$$

## Install the App [](id=install-the-app)

First, you must install the the Liferay Plugin for Google Drive&trade; from 
Liferay Marketplace. This app is available via the following links for Liferay 
CE Portal and Liferay DXP: 

-   [Liferay Plugin for Google Drive - CE](https://web.liferay.com/marketplace/-/mp/application/105847499)
-   [Liferay Plugin for Google Drive - DXP](https://web.liferay.com/marketplace/-/mp/application/98011653)

If you need help installing apps from Marketplace, see the documentation on 
[using Marketplace](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace). 

## Configure Your Google Project [](id=configure-your-google-project)

Follow these steps to create and/or configure your Google project so it can 
communicate with your @product@ instance: 

1.  Go to the
    [Google API Console](https://console.developers.google.com). 
    If you don't have a suitable project, 
    [create a new one](https://support.google.com/googleapi/answer/6251787?hl=en&ref_topic=7014522). 

2.  Enable the Google Picker API for your project. For instructions, see the 
    Google API Console documentation on 
    [enabling and disabling APIs](https://support.google.com/googleapi/answer/6158841). 

3.  Create an OAuth 2 client ID in your Google project. For instructions, see 
    the Google API Console documentation on 
    [setting up OAuth 2.0](https://support.google.com/googleapi/answer/6158849). 
    Enter these values when creating your client ID: 

    -   **Application type:** Web application
    -   **Name:** Google Docs Hook
    -   **Authorized JavaScript origins**: `[liferay-instance-URL]` (e.g., 
        `http://localhost:8080` is the default for local development machines)
    -   **Authorized redirect URIs**: `[liferay-instance-URL]/oath2callback` 

4.  Create a new API key in your Google project. For instructions, see the 
    Google API Console documentation on 
    [creating API keys](https://support.google.com/googleapi/answer/6158862?hl=en). 
    Be sure to restrict the key to HTTP referrers (web sites), and set it to 
    accept requests from your @product@ instance's URL. 

Your new OAuth client ID and public API access key now appear on your Google 
project's Credentials screen. Keep this screen open to reference these values as 
you specify them in @product@. 

## Configure @product@'s Google Apps Settings [](id=configure-products-google-apps-settings)

Configure your @product@ instance with the client ID and API key that you 
created in the previous step: 

1.  Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Instance 
    Settings*. 

2.  Click the *Miscellaneous* tab and expand the *Google Apps* section. 

3.  For *Google Apps API Key*, enter the Google API key that you created in the 
    previous section. 

4.  For *Google Client ID*, enter the Google OAuth client ID that you created in 
    the previous section. 

5.  *Save* your changes. 

## Linking to Google Drive&trade; [](id=linking-to-google-drivetrade)

With the preceding configuration steps complete, you can create files in your 
Document Library that link to files in Google Drive&trade; or images in Google 
Photos&trade;. Follow these steps to do so:

1.  In your Document Library, click the *Add* button 
    (![Add](../../../../images/icon-add.png)) 
    and select *Google Docs*. The *New Google Docs* screen appears. 

2.  Click the *Select File* button to open Google's file picker. 

3.  Use the file picker to select a file from Google Drive&trade; or Google 
    Photos&trade;. 

4.  Click *Publish*. 

![Figure 1: You can select files from Google Drive&trade; or your photos.](../../../../images/dm-google-select-a-file.png)

A new file entry appears for the Google document you linked to. You can view the
file entry as you would any file entry. The Google document's contents show in 
the file entry's preview pane. As with any file entry, the *Options* button 
(![Options](../../../../images/icon-options.png)) 
gives you access to the Download, Edit, Move, Permissions, Move to Recycle Bin, 
and Checkin/Checkout/Cancel Checkout options. 
