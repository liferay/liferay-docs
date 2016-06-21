# Using a SharePoint Repository [](id=using-a-sharepoint-repository)

With the help of Liferay's SharePoint Connector app, users can access SharePoint
libraries from within a Liferay Documents and Media Library. The app installs a
SharePoint repository type that you can select on adding a new *Repository* to
Documents and Media. The SharePoint Connector integrates sites with existing
SharePoint libraries, so you can access all of your organization's files.

The [Liferay SharePoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
app is available to EE subscribers on Liferay's
[Marketplace](http://marketplace.liferay.com/). Refer to [Managing Apps](/discover/portal/-/knowledge_base/7-0/managing-apps)
for details on installing and activating apps.

As with other third-party repositories that support the CMIS standard, users can
exercise the following capabilities with the SharePoint repository they access via
the Liferay SharePoint Connector: 

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to a revision

The SharePoint Connector uses SharePoint's API, which has some limitations: 1)
Version history is lost when moving or renaming a file without first checking it
out; 2) you can't change file extensions, you can only change file names; 3) a
file's current name propagates to all previous versions; 4) the user who checks
out a file is the only one who can see its working copy version number; 5)
queries for suffixes or intermediate wildcards convert to queries for
containment; 6) ratings are unsupported. 

To use a SharePoint repository inside Documents and Media, you must first
configure the SharePoint and Liferay environments to support authentication with
SharePoint and then add a Documents and Media Repository that connects to
SharePoint. Let's start by configuring the environments.

## Environment Setup [](id=environment-setup)

Here's an overview of what you must do to configure your host, @product@, and
SharePoint to use SharePoint from Liferay's Documents and Media Library:

1. Enable Basic Authentication on the SharePoint host.
2. Synchronize user crendentials between @product@ and SharePoint
2. Enable Versioning Support on the SharePoint library.

Note, these instructions are geared to @product@ and SharePoint system
administrators.

Before you can use SharePoint as an external repository with @product@, you must
set up Basic Authentication on the SharePoint server.

### Enabling Basic Authentication [](id=enabling-basic-authentication)

So that Liferay's SharePoint Connector can authenticate against the SharePoint
web services, you must enable Basic Authentication on the SharePoint host. As
you do this, you must make sure to empty Basic Authentication's default domain
and realm fields of all values. We'll show you how to do this. 

Authentication setup steps differ between versions of Windows. But as an
example, here are steps for enabling Basic Authentication on *Windows Server
2008*: 

1. Sign in to the Windows server as a member of the *Administrators* group. 
2. Open *Administrative Tools*, and then click *Internet Information Services
(IIS) Manager* to launch the *IIS Manager* console. 
3. In the *Connections* navigation panel, navigate to the SharePoint web site
options by clicking on the server's name, then *Sites*, and then the name of the  
SharePoint's web site. The site's Features View is available in the main viewing
area of the IIS Manager console.
4. Select *Features View* tab and then double-click on *Authentication*
in the *IIS* section of the Features View. The *Authentication* panel appears.
5. In the *Authentication* panel, select the row named *Basic Authentication*.
The *Actions* panel appears next to the main panel.
6. In the *Actions* panel, click *Enable* to activate Basic Authentication. 
7. Also in the *Actions* panel, click *Edit*. An *Edit Basic Authentication Settings*
dialog box appears.
8. In the dialog box, empty the *Default domain* and *Realm* fields of any
values and click *OK*.

You've configured Basic Authentication on the SharePoint host. Next, you must
enable @product@ users to access the SharePoint server.

### Synchronizing Credentials [](id=synchronizing-credentials)

As a @product@ system administrator, you must ensure that the same credentials
and authentication are used in @product@ and in SharePoint.
[LDAP](/discover/deployment/-/knowledge_base/7-0/ldap) is a typical mechanism
for synchronizing them. If you don't have LDAP, however, you must manually
synchronize the credentials and authentication. 

In order to authenticate with the third-party repository, you need to store
passwords for the user sessions. In your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home),
create a `portal-ext.properties` file, if one doesn't already exist, and add a [`session.store.password`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Session)
portal property set to `true`:

    session.store.password=true

Next, make sure to authenticate the same way on both @product@ and
the external repository. You can do so by authenticating based on screen
name. Add the following [`company.security.auth.type`]( https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Company)
portal property to your `portal-ext.properties` file: 

    company.security.auth.type=screenName

Alternatively, you can configure this property in the Control Panel
under *Configuration &rarr; Instance Settings &rarr; Authentication*.
                                         
For @product@ users to access the external repository, their screen name and
password must be the same in @product@ and in external repository application.
For details on adding and managing @product@ users, refer to [User Mangement](/discover/portal/-/knowledge_base/7-0/user-management).

You've synchronized user credentials and authentication between @product@ and
SharePoint. Lastly for environment setup, you'll want to enable versioning
support in your SharePoint library, so that users can leverage file versioning
between @product@ and SharePoint. 

### Enabling Versioning Support in the SharePoint library [](id=enabling-versioning-support-in-the-sharepoint-library)

You must enable versioning in SharePoint library for @product@'s check-in/out
features to work with SharePoint. To do it, follow the next steps: 

1. Open a browser to the SharePoint library's URL.
2. Click on the library's name.
2. At the top of the toolbar, click on *Liferay Tools* and then on *Library*
underneath *Liferay Tools*. 
3. In the toolbar, click on *Library Settings*.
4. Under *General Settings*, click on *Versioning settings*.
5. In *Document Version History*, select *Create major and minor (draft)
versions*. 
6. In *Require Check Out* select *Yes*.

You've set SharePoint to accept versioning requests from @product@. From within
Liferay's Documents and Media Library, you are now prepared to mount the
SharePoint library as an external repository. 

## Add SharePoint as a Liferay Documents and Media repository [](id=add-sharepoint-as-a-liferay-documents-and-media-repository)

It's time to add a Documents and Media Library *Repository* of type SharePoint,
so users can work with SharePoint in @product@. 

Here are the steps for adding a SharePoint repository type:

1. Add the Documents and Media application to a page, if you haven't done so
already.

2. From the home location in the Documents and Media application, click the
*Add* icon (![Add](../../../images-dxp/icon-portlet-add-control.png)) and select
*Repository*. The  *New Repository* screen appears. 

3. In the New Repository screen enter an arbitrary repository *Name* and a
*Description* (optional).

4. Click on the *Repository Configuration* section to access its form. Then
specify values for the following fields:

    - **Repository Type**: Select the SharePoint option.

    - **Library Name**: Enter a name for the library. Typically you'd 
    enter the name of the SharePoint Library you're connecting to.
    
    - **Library Path**: This field defaults to the *Library Name*, if no path is
	specified. If the *Library Name* you specified differs from the SharePoint
	Library's name, specify the URL of the SharePoint Library here.

    - **Server Version**: Enter the SharePoint server version (e.g., `2013` or
    `2010`). 

    - **Site URL:** Enter the URL of the site where your SharePoint Library lives (e.g., `http://your-site`). 

    ![Figure 1: The Repostiory Configuration form is where you specify access to the SharePoint Library you want to use.](../../../images-dxp/sharepoint-repo-configuration-form.png)

5. After you've finished entering any additional options, click *Save*.

Your Documents and Media Library is now connected to the SharePoint repository.
The new external repository is now listed in the Documents and Media home. 

Now that you've added a SharePoint Repository to Documents and Media, you can
access and modify SharePoint Library files from within @product@'s Documents and
Media Library.  

## Related Articles [](id=related-articles)

[Publishing Files](/discover/portal/-/knowledge_base/7-0/publishing-files)

[Administering Liferay Sync](/discover/portal/-/knowledge_base/7-0/administering-liferay-sync)

[Repository Types](/discover/portal/-/knowledge_base/7-0/repository-types)
