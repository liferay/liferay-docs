# Accessing Google Docs&trade; [](id=accessing-google-docs)

You can access files stored in Google applications, such as Google Drive&trade; 
and Google Photos&trade;, through the Document Library. This integration isn't 
available by default, but you can get it by installing the Liferay Plugin for 
Google Drive&trade; from the 
[Liferay Marketplace](https://web.liferay.com/marketplace). 

+$$$

**Important:** The Liferay Plugin for Google Drive&trade; is a Labs application 
available for Liferay DXP and Liferay Portal CE 7.0 GA6+. Apps designated as 
Labs are experimental and not supported by Liferay. They're released to 
accelerate the availability of useful and cutting-edge features. This status may 
change without notice. Please download and use Labs apps at your own discretion. 

$$$

You must configure @product@ to access Google's Picker API, which accesses files
stored on Google's servers. Invoking the Google Picker API requires unique API
keys and a client ID. The API keys are for accessing public data and the
client ID is for authenticating and authorizing user access to non-public
data. To get the keys and client ID, you must use a Google developer project.
This takes three steps: 

- **Step 1: Enable the Google Picker API**
- **Step 2: Create Credentials**
- **Step 3: Configure @product@'s Google Apps Settings**

Start by enabling the Google Picker API in a Google developer project. 

## Step 1: Enable the Google Picker API [](id=step-1-enable-the-google-api-picker)

@product@ uses the *Google Picker API* to access files in Google. 

1.  Create a Google account or use an existing one. 

2.  Open the 
    [Google Developer Console](https://console.developers.google.com). 

3.  Create a new project or use an existing one. 

4.  Search for the *Google Picker API* and enable it. @product@ uses this API to 
    access Google documents. 

Next, you must create credentials to use with the Google API. 

## Step 2: Create Credentials [](id=step-2-create-credentials)

1.  In the Google Developer Console, navigate to the *Credentials* screen for
    APIs &amp; services. 

2.  Create a new OAuth client ID for a *Web application*. Specify the following
    attributes:

    - **Name**: `Google Docs Hook`

    - **Authorized JavaScript origins**: `[liferay-instance-URL]` (e.g., 
    `http://localhost:8080` by default for local development machines)

    - **Authorized redirect URIs**: `[liferay-instance-URL]/oath2callback`

3.  Create a new API key. Restrict the key to HTTP referrers (web sites), and 
    set it to accept requests from your @product@ instance's URL. 

Your new OAuth client ID and public API access key now appear on the Credentials
screen. Keep this screen open to reference these values as you specify them in
@product@. All that's left is to enter the API key and client ID into
@product@'s Google Apps configuration. 

## Step 3: Configure @product@'s Google Apps Settings [](id=step-3-configure-liferays-google-apps-settings)

To call Google's APIs, you must configure @product@ with your Google project's
OAuth client ID and public API access key:

1.  Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Instance 
    Settings*. 

2.  Click the *Miscellaneous* tab and expand the *Google Apps* section. 

3.  For *Google Apps API Key*, enter the Google API key that you created in the 
    previous step. 

4.  For *Google Client ID*, enter the Google OAuth client ID that you created in 
    the previous step. 

5.  *Save* your changes. 

Your @product@ instance is now ready to access Google documents from the 
Document Library. 

## Linking to Google Docs&trade; [](id=linking-to-google-docs)

In your Document Library, you can create file entries that link to Google 
documents, such as files stored in Google Drive&trade; or photos saved to 
Google. To link to a Google document in Documents and Media, click the Add icon
(![Add](../../../../images/icon-add.png)) and select *Google Docs*. The 
*New Google Docs* screen appears. 

This screen is similar to the edit screen for basic documents, except for file 
selection. Clicking the *Select File* button brings up Google's file picker to 
select files from your Google Drive&trade; or your photos. Select the Google 
file you want to link to and click *Publish*. 

![Figure 1: You can select files from Google Drive&trade; or your photos.](../../../../images/dm-google-select-a-file.png)

A new file entry appears for the Google document you linked to. You can view the
file entry as you would any file entry. The Google document's contents show in 
the file entry's preview pane. As with any file entry, the *Options* button 
(![Options](../../../../images/icon-options.png)) gives you access to the 
Download, Edit, Move, Permissions, Move to Recycle Bin, and 
Checkin/Checkout/Cancel Checkout options. 

![Figure 2: The Google document's file entry view displays the file's information and provides a preview of the file.](../../../../images/dm-google-doc-file-entry.png)

Great! Now you know how to access Google Docs&trade; from your document 
libraries. Next, you'll learn how to display your Document Library's files on 
your site. 
