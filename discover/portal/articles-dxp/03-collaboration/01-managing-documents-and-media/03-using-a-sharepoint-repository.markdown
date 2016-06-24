# Using a SharePoint Repository [](id=using-a-sharepoint-repository)

With the help of Liferay's SharePoint Connector app, users can access SharePoint
2013 and SharePoint 2010 libraries from within a Liferay Documents and Media
Library. The app installs a SharePoint repository type that you can select on
adding a new *Repository* to Documents and Media. The SharePoint Connector
integrates sites with existing SharePoint libraries, so you can access all of
your organization's files.

The [Liferay SharePoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
app is available to EE subscribers on Liferay's
[Marketplace](http://marketplace.liferay.com/). Refer to [Managing Apps](/discover/portal/-/knowledge_base/7-0/managing-apps)
for details on installing and activating apps.

As with other third-party repositories that support the CMIS standard, users can
gain these benefits by accessing the SharePoint repository through the Liferay
SharePoint Connector: 

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

1. Enable Basic Authentication on the SharePoint host
2. Install SharePoint's CMIS Connector on SharePoint
3. Activate the SharePoint site as a CMIS Producer
4. Enable Versioning Support on the SharePoint library
5. Configure Authentication on @product@
6. Synchronize user credentials between @product@ and SharePoint

Note, these instructions are geared to @product@ and SharePoint system
administrators.

Before you can use SharePoint as an external repository with @product@, you must
set up a few things on the SharePoint host and in the SharePoint server application.

### Step 1: Enable Basic Authentication on the SharePoint Host [](id=step-1-enable-basic-authentication-on-the-sharepoint-host)

So that Liferay's SharePoint Connector can authenticate against the SharePoint
web services, you must enable Basic Authentication on the SharePoint host. As
you do this, make sure to empty Basic Authentication's default domain and realm
fields of all values. 

Authentication setup steps differ between Windows versions. But as an
example, here are steps for enabling Basic Authentication on *Windows Server
2008*: 

1.  Sign in to the Windows server as a member of the Administrators group. 
2.  Open *Administrative Tools*, and then click *Internet Information Services
    (IIS) Manager* to launch the IIS Manager console. 
3.  In the Connections navigation panel, navigate to the SharePoint web site
    options by clicking on the server's name, then *Sites*, and then the name of
    the SharePoint site. The site's Features View is available in the main
    viewing area of the IIS Manager console.
4.  Select the *Features View* tab and then double-click on the *Authentication* icon
    in the IIS section of the Features View. The Authentication panel appears.
    ![Figure 1: The Features View for the site shows the Authentication icon.](../../../images-dxp/sharepoint-site-iis-authentication.png)
5.  In the Authentication panel, select the row named *Basic Authentication*.
    The Actions panel appears next to the main panel.
6.  In the Actions panel, click *Enable* to activate Basic Authentication. 
7.  Also in the Actions panel, click *Edit*. An Edit Basic Authentication Settings
    dialog box appears.
   ![Figure 2: Clicking the *Edit...* action brings up the a dialog for setting the Default domain and Realm.](../../../images-dxp/sharepoint-host-edit-basic-auth-settings.png)
8.  In the dialog box, empty the *Default domain* and *Realm* fields of any
    values and click *OK*.

You've configured Basic Authentication on the SharePoint host. Next, you must
make sure you install SharePoint's CMIS Connector add-on, if it's not already
installed on your SharePoint server.

### Step 2: Install SharePoint's CMIS Connector on SharePoint [](id=step-2-install-sharepoints-cmis-connector-on-sharepoint)

SharePoint uses a CMIS Connector and a CMIS Producer to interface with
@product@. If they're already installed on your SharePoint server, you can skip
this section.

The CMIS Connector is installed with the SharePoint Administrator
Toolkit using a solution package called a Windows SharePoint file (.wsp). If you
don't have it already, install the SharePoint Administrator Toolkit for its CMIS
Connector. Install and deploy the CMIS Connector as a Farm Solution on
SharePoint.

The folder `Content Management Interoperability Services (CMIS) Connectors`
contains the `spscmis.wsp` file. Choose the appropriate deployment settings and
deploy that file. When deployment completes, Solution Properties shows the
solution is successfully deployed to all target sites. Now it's time to
activate the SharePoint site as a CMIS Producer.

### Step 3: Activate the SharePoint Site as a CMIS Producer [](id=step-3-activate-the-sharepoint-site-as-a-cmis-producer)

The Producer makes SharePoint repositories available through the CMIS Connector.
Choose the SharePoint site containing the document libraries to be used as
document repositories. Every document library in this site is made available as
a repository through the CMIS connector, but the site must first be activated as
a CMIS Producer. 

Go to *Site Actions* &rarr; *Site Settings* &rarr; *Manage Site Features*.
Enable the *Content Management Interoperability Services (CMIS) Producer* by
clicking *Activate*.

Now any document library created under this SharePoint site is CMIS enabled. 

Lastly, so that users can leverage file versioning between @product@ and
SharePoint, you must enable versioning support in your SharePoint library. 

### Step 4: Enable Versioning Support in the SharePoint Library [](id=step-4-enable-versioning-support-in-the-sharepoint-library)

You must enable versioning in SharePoint library for @product@'s check-in/out
features to work with SharePoint. To enable it, follow these steps: 

1.  Open a browser to the SharePoint library's URL.
2.  Click on the SharePoint library's name.
3.  At the top of the toolbar, click on *Liferay Tools* and then on *Library*,
    underneath *Liferay Tools*. 
4.  In the toolbar, click on *Library Settings*.
5.  Under *General Settings*, click on *Versioning settings*.
6.  In *Document Version History*, select *Create major and minor (draft)
    versions*. 
7.  In *Require Check Out* select *Yes*.

You've set SharePoint to accept versioning requests from @product@.

Next, you'll configure authentication for @product@. 

### Step 5: Authentication on Liferay [](id=step-5-authentication-on-liferay)

In order to authenticate with the Sharepoint repository, you need to store
passwords for the user sessions. You must configure an authentication type that
supports storing passwords for the user sessions.

**Important**: Since authentication with single sign-on (SSO) does not store
encrypted passwords in the user sessions, SSO can't be used with the SharePoint
Connector app.

Let's configure @product@ for authentication.  In your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home),
create a `portal-ext.properties` file, if one doesn't already exist, and add a
[`session.store.password`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Session)
portal property set to `true`:

    session.store.password=true

Next, make sure to authenticate the same way on both @product@ and
the external repository. You can do so by authenticating based on screen
name. Add the following [`company.security.auth.type`]( https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Company)
portal property to your `portal-ext.properties` file: 

    company.security.auth.type=screenName

Alternatively, you can configure this property in the Control Panel
under *Configuration* &rarr; *Instance Settings* &rarr; *Authentication*.

Next, you must give @product@ users access to the SharePoint server.

### Step 6: Synchronize Credentials [](id=step-6-synchronize-credentials)

As a @product@ system administrator, you must ensure that the same credentials
and authentication are used in @product@ and in SharePoint.
[LDAP](/discover/deployment/-/knowledge_base/7-0/ldap) is a typical mechanism
for synchronizing them. If you don't have LDAP, however, you must manually
synchronize the credentials and authentication. 
 
For @product@ users to access the external repository, their screen names and
passwords must be the same in @product@ and in Sharepoint. For details on adding
and managing @product@ users, refer to [User Mangement](/discover/portal/-/knowledge_base/7-0/user-management).

From within Liferay's Documents and Media Library, you are now prepared to mount
the SharePoint library as an external repository. 

## Add SharePoint as a Liferay Documents and Media repository [](id=add-sharepoint-as-a-liferay-documents-and-media-repository)

It's time to add a Documents and Media Library Repository of type SharePoint,
so users can work with SharePoint in @product@. 

Here are the steps for adding a SharePoint repository type:

1.  Add the Documents and Media application to a page, if you haven't done so
    already.

2.  From the home location in the Documents and Media application, click the
    *Add* icon (![Add](../../../images-dxp/icon-portlet-add-control.png)) and select
    *Repository*. The  New Repository screen appears. 

3.  In the New Repository screen enter an arbitrary repository *Name* and a
    *Description* (optional).

4.  Click on the *Repository Configuration* section to access its form. Then
    specify values for the following fields:

    **Repository Type**: Select the SharePoint option.

    **Library Name**: Enter a name for the library. Typically you'd 
    enter the name of the SharePoint Library you're connecting to.
  
    **Library Path**: This field defaults to the *Library Name*, if no path is
	specified. If the Library Name you specified differs from the SharePoint
	Library's name, specify the URL of the SharePoint Library here.

    **Server Version**: Enter the SharePoint server version (e.g., `2013` or
    `2010`). 

    **Site URL:** Enter the URL of the site where your SharePoint Library lives
    (e.g., `http://your-site`). 

    ![Figure 3: The Repostiory Configuration form is where you specify access to the SharePoint Library you want to use.](../../../images-dxp/sharepoint-repo-configuration-form.png)

5.  After you've finished entering any additional options, click *Save*.

Your Documents and Media Library is now connected to the SharePoint repository.
The new external repository is now listed in the Documents and Media home. 

Now that you've added a SharePoint Repository to Documents and Media, you can
access and modify SharePoint Library files from within @product@'s Documents and
Media Library. 

## Related Articles [](id=related-articles)

[Publishing Files](/discover/portal/-/knowledge_base/7-0/publishing-files)

[Administering Liferay Sync](/discover/portal/-/knowledge_base/7-0/administering-liferay-sync)

[Repository Types](/discover/portal/-/knowledge_base/7-0/repository-types)
