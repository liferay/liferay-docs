# Accessing Google Docs&trade; [](id=accessing-google-docs)

You can access files stored in Google applications, such as Google Drive&trade; 
and Google Photos&trade;, through the Document Library. This integration isn't 
available by default, but you can get it by installing the Liferay Plugin for 
Google Drive&trade; from the [Marketplace](https://web.liferay.com/marketplace). 

**Important:** The Liferay Plugin for Google Drive&trade; is a Labs application.
Apps designated as Labs are experimental and not supported by Liferay. They're
released to accelerate the availability of useful and cutting-edge features. 
This status may change without notice. Please download and use Labs apps at your 
own discretion. 

You must configure your @product@ instance's access to Google's API Picker,
which is called to access files stored on Google's servers. This requires
unique API keys and client ID to invoke the Google API Picker. The API key is
used for accessing public data and the client ID is for authenticating and
authorizing user access to non-public data. To get the keys and client ID, you
must use a Google developer project. This takes three steps: 

- **Step 1: Enable the Google API Picker**

- **Step 2: Create Credentials**

- **Step 3: Configure @product@'s Google Apps Settings**

Start by enabling the Google API Picker in a Google developer project. 

## Step 1: Enable the Google API Picker [](id=step-1-enable-the-google-api-picker)

@product@ uses the *Google API Picker* to access files in Google. 

1.  Create a Google account (or use an existing one). 

2.  Open the 
    [Google Developer Console](https://console.developers.google.com).

3.  Create a new project or use an existing one. 

4.  In the console's product and services menu, click on the API Manager. An
    overview screen lists the Google APIs. 

5.  In the list of Google APIs, find the *Google API Picker* and enable it.
    @product@ uses the API to access Google documents. 

Next you must create credentials to use with the Google API. 

## Step 2: Create Credentials [](id=step-2-create-credentials)

1.  In the API Manager navigation, click on *Credentials*. 

2.  Create a new OAuth client ID for a *Web application*. Specify the following
    attributes:
 
    - **Name**: `Google Docs Hook`
 
    - **Authorized JavaScript origins**: `[liferay-instance-URL]` (for
    example, `http://localhost:8080` for development machines)
 
    - **Authorized redirect URIs**: `[liferay-instance-URL]/oath2callback`

3.  Create a new API key of type *Browser key*. Specify your @product@ 
    instance's URL in the field listing sites from which your Google project 
    should accept requests.

Your new OAuth client ID and public API access key appear in your Google
project's credentials. Keep the credentials screen open so you can easily
reference these values as you specify them in @product@. All that's left is to
enter the API key and client ID into your @product@ instance's Google Apps
configuration. 

## Step 3: Configure @product@'s Google Apps Settings [](id=step-3-configure-liferays-google-apps-settings)

To call Google's APIs, you must configure @product@ with your Google project's
OAuth client ID and public API access key.

1.  From the *Control Panel*, navigate to *Configuration &rarr; *Instance
    Settings*.

2.  Click on the *Miscellaneous* tab and expand the *Google Apps* section.

3.  For *Google Apps API Key*, enter your Google project's API key.

4.  For *Google Client ID*, enter your Google project's OAuth client ID. 

5.  *Save* your changes. 

Your @product@ instance is ready to access Google documents from the Document
Library. 

## Linking to Google Docs&trade; [](id=linking-to-google-docs)

In your Document Library, you can create file entries that link to Google
documents, such as files stored in Google Drive&trade; or photos saved to
Google. To link to a Google doc in Documents and Media, click the Add icon
(![Add](../../../../images/icon-add.png)) and select *Google Docs*. The 
*New Google Docs* screen appears. 

The Google docs edit screen is similar to the edit screen for basic documents,
except for file selection. Clicking on the *Select File* button for Google docs
brings up Google's file picker to select files from your Google Drive&trade; or 
your photos. Select the Google file you want to link to and click *Publish*. 

![Figure 1: You can select files from Google Drive&trade; or your photos.](../../../../images/dm-google-select-a-file.png)

A new file entry appears for the Google document you linked to. You can view the
file entry as you would any file entry. Contents of the Google doc show in the
file entry preview pane. As with any file entry, Documents and Media's *Options*
icon (![Options](../../../../images/icon-options.png)) gives you access to the
Download, Edit, Move, Permissions, Move to Recycle Bin, and
Checkin/Checkout/Cancel Checkout options. 

![Figure 2: The Google doc file entry view displays the file's information and provides a preview of it.](../../../../images/dm-google-doc-file-entry.png)

Now you know how to access Google Docs&trade; from your document libraries.
