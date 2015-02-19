# Setup Google Docs Hook

1. Deploy **document-library-google-docs** OSGi module. 
2. Deploy **google-apps-configuration-hook** hook.
3. Go to _Control Panel -> Settings -> Portal Settings -> Google Apps_.
4. Fill in Google’s **API key** and **Client ID** (see _Setup Google API account_ section on how to get these values).
5. Check that the **Google Docs** document type appears in **Documents and Media** portlet.
6. Enjoy the hook! ;-)

# Setup Google API account

1. Create a Google account (or use an existing one).
2. Open Google Developers Console at [https://console.developers.google.com]().
3. Create a new project:
![](3.png)
4. Open the new project and go to _APIs & auth -> APIs_:
![](4.png)
5. Enable **Google Picker API**:
![](5.png)
6. Go to _Credentials_:
![](6.png)
7. Create a new **Client ID**:
	1. It will ask you to create a new **Consent screen**:
	![](7a.png)
	2. Then you will be able to create the **Client ID**. Choose **Web application** type and introduce the address of the portal from which you will access Google API (for example: http://localhost:8080 for development machines) in the **AUTHORIZED JAVASCRIPT ORIGINS** textbox. You can leave the default value for **AUTHORIZED REDIRECT URIS** because we are not using it for Google Docs Hook:
	![](7b.png)
8. Once you have the **Client ID**, you must create a new **Key**. Choose **Browser key** type and introduce the address of the portal from which you will access Google API (for example: http://localhost:8080 for development machines) in the **ACCEPT REQUESTS FROM THESE HTTP REFERERS (WEB SITES)** field: 
	![](8a.png)
	![](8b.png)
	![](8c.png)
	![](8d.png)


