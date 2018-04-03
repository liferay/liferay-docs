# Using a SharePoint Repository [](id=using-a-sharepoint-repository)

The 
[Liferay Marketplace](http://marketplace.liferay.com/) 
app *Liferay Connector for SharePoint* lets users access SharePoint 2013 
and SharePoint 2016 libraries from @product@'s Documents and Media Library. Once 
this app is installed, you can add a SharePoint repository type to @product@'s 
Documents and Media Library to access your SharePoint files. 

+$$$

**Note:** To access SharePoint 2010, you must use 
[the app *Liferay Connector for SharePoint 2010*](https://web.liferay.com/marketplace/-/mp/application/15188537). 

$$$

+$$$

**Note:** Liferay Connector for SharePoint uses Azure ACS with OAuth 2 for 
SharePoint server authorization. You must therefore enable HTTPS support in your 
app server. Consult your app server's documentation for instructions. For 
example, the required steps for Tomcat can be found in 
[its documentation](https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html). 

$$$

Liferay Connector for SharePoint provides these key benefits: 

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to a revision

The app uses SharePoint's API, which has these limitations: 

- Version history is lost when moving or renaming a file without first 
  checking it out.
- You can't change file extensions, you can only change file names.
- A file's current name propagates to all previous versions.
- The user who checks out a file is the only one who can see the version number 
  of that file's working copy.
- Queries for suffixes or intermediate wildcards convert to queries for 
  containment.  
- Comments, ratings, and using a SharePoint folder as a Documents and Media root 
  folder are unsupported. 

To use a SharePoint repository in Documents and Media, you must first create 
an application in SharePoint and authorize it to access the repository. The 
guides in this section walk you through this process. 
