# Repository Types [](id=repository-types)

Liferay's Documents and Media lets you store files in a number of different ways
and lets you access external repositories in addition to the built in document
repository. We'll explain how to set options for storing the Document Library's
files and how to connect with external repository types that Liferay supports.
Let's start with Document Library store options.

## Document Library Store Options [](id=document-library-store-options)

By default, Liferay stores documents and media files on the file system of the
server it's running on. You can specify a location for the
document library store's root directory by adding a
`dl.store.file.system.root.dir` portal property to a
`portal-ext.properties` file in your [Liferay home](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview#liferay-home)
and replacing the default path value with your custom path:

    dl.store.file.system.root.dir=${liferay.home}/data/document_library

You can also use an entirely different method for storing documents and media
files. You can use any of the following documents and media library stores with
Liferay:

- Advanced File System Store
- CMIS Store (Content Management Interoperability Services)
- DBStore (Database Storage)
- File System Store
- JCRStore (Java Content Repository)
- S3Store (Amazon Simple Storage)

+$$$

**Warning:** If a database transaction rollback occurs in a Document Library
that uses a file system based store, file system changes that have occurred
since the start of the transaction won't be reversed. Inconsistencies between
Document Library files and those in the file system store can occur and may
require manual synchronization. All stores except DBStore are vulnerable to this
limitation.

$$$

For example, you can store documents and media files in your Liferay instance's
database using DBStore. To enable DBStore, add the following [`dl.store.impl`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Service)
portal property to a `portal-ext.properties` file in your [Liferay home](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview#liferay-home):

    dl.store.impl=com.liferay.portlet.documentlibrary.store.DBStore

Remember to restart your Liferay server after updating your
`portal-ext.properties` file in order for your customizations to take effect.
Please refer to the [Document Library property reference](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Portlet)
for a complete list of supported customizations. You can customize features such
as the maximum allowed size of documents and media files, the list of allowed
file extensions, which types of files should be indexed, etc.

## Using External Repositories [](id=using-external-repositories)

Documents and Media allows users to connect to multiple third-party
repositories. Some of the features supported with third-party repositories
include:

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to a revision

Liferay's Documents and Media includes a generic integration for CMIS 1.0
repositories. CMIS stands for Content Management Interoperability Services,
which defines a model for interacting with a repository's files. It's been
tested with Alfresco and Nuxeo but supports the CMIS 1.0 standard with respect
to all repositories, where they implement CMIS 1.0. Liferay can access CMIS
repositories using AtomPub and Web Services protocols.

![Figure 1: In Documents and Media, external repositories look similar to folders. You can perform supported operations on their files. This figure shows a CMIS 1.0 repository named *My Alfresco*.](../../../images/dm-repo-types-actions.png)

There are some subtle differences in setting up the different kinds of
third-party repositories Documents and Media supports, but there are plenty of
similarities too. We'll walk through them, next.

### Repository Integration Steps [](id=repository-integration-steps)

Common integration steps include:

- Adjusting portal properties.
- Adding any user accounts required by the repository.
- Adding the repository.

<!-- 
Keep in mind your third-party repository may require installation and deployment
of an appropriate Liferay plugin. Plugins for SharePoint and Documentum are
available through Liferay's Marketplace (please note that they don't use CMIS,
but proprietary APIs).
-->

Let's start with the required portal property settings.

#### Adjusting Portal Properties [](id=adjusting-portal-properties)

As a Liferay system administrator, you must ensure that the same credentials and
authentication are being used in Liferay and in the external repository. [LDAP](/discover/deployment/-/knowledge_base/7-0/ldap)
is a typical mechanism you can use to synchronize them. If you don't have LDAP,
you need to ensure manually that the credentials and authentication methods are
the same.

In order to authenticate with the third-party repository, you need to store
passwords for the user sessions. In your [Liferay home](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview#liferay-home),
set a [`session.store.password`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Session)
portal property to `true` in a `portal-ext.properties` file:

    session.store.password=true

Next, make sure to authenticate the same way on both Liferay and
the external repository. You can do so by authenticating based on screen
name. So, add the following [`company.security.auth.type`]( https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Company)
portal property to your `portal-ext.properties` file: 

    company.security.auth.type=screenName

Alternatively, you can configure both of these properties in the Control Panel
under *Instance Settings &rarr; Authentication*.
                                          
#### Synchronizing Repository Users [](id=synchronizing-repository-users)

External repository integration requires you to have a Liferay user that uses
the same screen name and password an external repository user. For details on
adding and managing users, refer to [User Mangement](/discover/portal/-/knowledge_base/7-0/user-management).
Make sure to sign in to Liferay as this user whenever you're accessing the
external repository from it.

Next, we'll explain how to access a CMIS Repository using the AtomPub protocol. 

### Using a CMIS Repository with AtomPub [](id=using-a-cmis-repository-with-atompub)

Liferay can access external repositories that support the CMIS 1.0 standard
using the Atom Publishing Protocol (AtomPub). It's the easiest protocol to
specify for communicating with CMIS repositories. 

These instructions were written based on accessing Alfresco Community 5.1 and
Nuxeo Platform 7.10 repositories.

Follow these steps to add access to such as repository:

1.  Sign in to Liferay as a user that also exists in the external repository and
can access files you want to use in that repository. 

2.  In Documents and Media *Home*, click the *Add* icon
(![Add](../../../images/icon-add.png)) and select *Repository*. The *New
Repository* screen appears. 

    ![Figure 2: Here's the form for accessing a CMIS repository using AtomPub.](../../../images/dm-repo-types-new-repo-config.png)

3.  Enter an arbitrary *Name* for the repository and optionally enter a
*Description*.

4.  Click on the *Repository Configuration* section to access its form. Specify
the following values.
    - **Repository Type**: *CMIS Repository (AtomPub)*
    - **Repository ID**: Leave this blank, as Liferay generates the ID
    automatically
    - **AtomPub URL**: Enter the AtomPub URL for CMIS 1.0 per the
    repository's documentation. Example URLs are listed below. If you copy them,
    make sure to replace the `IP_ADDRESS` and `PORT` values with those of your
    external repository server. 
        -   Alfresco 5.1: `http://IP_ADDRESS:PORT/alfresco/cmisatom`
        -   Nuxeo Platform 7.10: `http://IP_ADDRESS:PORT/nuxeo/atom/cmis`

5.  Click *Save*

The external repository appears in Documents and Media. You can navigate into
the repository and manage files in it. 

![Figure 3: Documents and Media lets you perform CMIS 1.0 supported actions on external repository files that support those actions](../../../images/dm-repo-types-alfresco-site-doclib-file.png)

Liferay's CMIS repository integration gives you flexibility to access external
repository files in addition to your existing Documents and Media files. 

<!--
Note - As of writing this article, only a Liferay user matching the Nuxeo
administrator user (default is Administrator / Administrator) can connect with
Nuxeo.

I created another user on Nuxeo that matches a Liferay user's crendentials, but
the Liferay user gets this error when trying to browse the Nuxeo repo in
Liferay:

com.liferay.document.library.kernel.exception.NoSuchFolderException: No CMIS folder with {folderId=21578}
org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException: 1bf60c6e-7890-4c2a-9abf-7ff08ee44b22
...

-->

<!--
### Using a CMIS Repository with Web Services [](id=using-a-cmis-repository-with-web-services)


After completing the [Common Configuration](/discover/portal/-/knowledge_base/7-0/repository-types#common-configuration)
steps, you can add Documents and Media Repository that communicates with your
CMIS repository via web services. 

Follow these steps to add your repository:

1.  In Documents and Media *Home*, click the *Add* button and select
*Repository*. The *New Repository* screen appears.

2.  Enter an arbitrary *Name* for the repository and optionally enter a
*Description*.

3.  Click on the *Repository Configuration* section to access its form

4.  For **Repository Type:**, select *CMIS Repository (Web Services)*.

5.  Enter the web service URLs (WSDL URLs) per your CMIS repository's
documentation. Example URLs are listed after these steps. Make sure to enter
URLs for the following services: 

    - Web Services ACL Service
    - Web Services Discovery Service
    - Web Services Multifiling Service
    - Web Services Navigation Service
    - Web Services Object Service
    - Web Services Policy Service
    - Web Services Relationship Service
    - Web Services Repository Service
    - Web Services Versioning Service

6.  Leave *Repository ID* blank, as Liferay generates the ID
     automatically. 

7.  Click *Save*

Your repository appears in Documents and Media. You can navigate into the
repository and work with files as you do other files in Documents and Media.
-->

<!--
Possible formats ...

http://IP_ADDRESS:PORT/alfresco/cmis/RepositoryService?wsdl

http://IP_ADDRESS:PORT/nuxeo/webservices/cmis/RepositoryService?wsdl
-->


<!--
### Using a SharePoint Repository [](id=using-a-sharepoint-repository)

The following versions of Sharepoint are currently supported:

- **Sharepoint 2010**: officially supported. 

- **Sharepoint 2013**: experimental support.

> Note that there are some limitations for the Sharepoint connector when
    compared to the built-in Documents and Media repository (these limitations
    are use caused by Sharepoint's API):

> 1. File extensions cannot be changed.
> 2. File names are not versioned.
> 3. When files are moved their whole version history is lost. A rename is not
considered a file move. 
> 4. The *Working Copy* version is identified by a number but it's only visible
to the user who checked it out. 
> 5. Queries for suffixes are converted to queries for containment. For example:
a query criterion like *ends with 'txt'* is converted to *contains 'txt'*. 
> 6. Queries with intermediate wildcards are converted to multiple *contains*
queries. For example: a query criterion like *contains 'image' followed by 'jpg'
(with any separation in between)* is converted to *contains 'image' or 'jpg' (in
any order, with any separation in between)*.  

Now, let's see how to use SharePoint as a Liferay Documents and Media
repository. For that, we'll do the following: 

1. Enable Basic Authentication on the SharePoint host.
2. Enable Versioning Support on the SharePoint library.
3. Add SharePoint as a Liferay Documents and Media repository.

Note that this section is geared towards Liferay system administrators and
SharePoint system administrators.

Before you can use SharePoint as an external repository with Liferay, you must
verify that SharePoint is properly configured. Several services must be set up
on the SharePoint server before synchronizing with Liferay.

**Enable Basic Authentication on the SharePoint host**

For the connector to work, Basic Authentication on IIS must be enabled. This
lets Liferay's SharePoint connector authenticate against the SharePoint web
services. Enable Basic Authentication on your SharePoint host.

The steps needed to do it depend on your version of Windows. For example in
*Windows Server 2008* you need to:

1. Be a member of the *Administrators* group on the server on which you are
configuring IIS. 
2. Open *Administrative Tools*, and then click *Internet Information Services
(IIS) Manager* to start the *IIS Management Console*. 
3. Expand *Sites* on the console tree and click on Sharepoint's web site.
4. In *Features View*, double-click *Authentication*.
5. On the *Authentication* page, select *Basic Authentication*.
6. In the *Actions* pane, click *Enable* to use Basic Authentication with the
default settings. 
7. In the *Actions* pane, click *Edit* to enter a realm name.
8. In the *Edit Basic Authentication Settings* dialog box, make sure the
*Default domain* and *Realm* are empty and click *OK*. 

Now you have to enable versioning support in your Sharepoint library.

**Enable Versioning Support on the SharePoint library**

You must enable versioning in Sharepoint library for Liferay's check-in/out
features to work. To do it follow the next steps: 

1. Open the URL of your Sharepoint's library in a browser.
2. On the ribbons, click on *Library* actions.
3. On the toolbar click on *Library Settings*.
4. Open *General Settings &rarr; Versioning Settings*.
5. In *Document Version History*, select *Create major and minor (draft)
versions*. 
6. In *Require Check Out* select *Yes*.

You are now prepared to mount SharePoint as an external repository.

**Add SharePoint as a Liferay Documents and Media repository**

With the SharePoint server configured, we now turn our attention to Liferay. As
mentioned in the common steps for adding an external repository, be sure to
adjust the portal properties and add any user accounts required by the
repository.

Here are the steps specific to configuring Liferay to use SharePoint:

1. Download and install the [SharePoint Connector
EE](https://www.liferay.com/marketplace/-/mp/application/15188537) from
Marketplace. See the [Downloading and Installing
Apps](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps)
section of the *Leveraging the Liferay Marketplace* chapter of this document for
more information.

2. Add the Documents and Media application to a page, if you haven't done so
already.

3. In the Documents and Media application click *Add Repository* and enter the
following information:

	- **Name:** Enter an arbitrary name for the repository.

	- **Description:** Describe the repository.

	- **Repository Type:** Select *SharePoint*.

	- **Server Version:** Enter the version number of your *Sharepoint* server
	(for example: *2010*). 

	- **Site URL:** Enter the URL where your Sharepoint Library lives (for
	example: <http://liferay-20jf4ic>). 

	- **Library Name:** Enter the name of your Sharepoint Library (for example:
	*Documents*) 
    
	- **Library Path:** This field defaults to the *Library Name* if not
	specified. It can be used when the URL of your Sharepoint Library does not
	match the *Library Name* (for example: *Shared Documents*). 

Click *Save* and the left navigation panel of your Documents and Media
application will list your new repository. 
-->

<!--
### Using a Documentum Repository [](id=using-a-documentum-repository)

The following versions of Documentum are currently supported:

- **Documentum 6.6**: officially supported.

To use Documentum as a Liferay Documents and Media repository, we'll do the
following: 

1. Deploy the *Documentum Connector EE* plugin.
2. Generate *Documentum's cryptographic keys*.
3. Configure the keys and restart the server.
4. Add a *Documentum Repository* in *Documents and Media* application.

Before you can use Documentum as an external repository with Liferay, you must
verify that Documentum is properly configured.

**Deploy the Documentum Connector EE plugin**

As mentioned in the common steps for adding an external repository, be sure to
adjust the portal properties and add any user accounts required by the
repository.

Download and install the [Documentum Connector
EE](https://www.liferay.com/marketplace/-/mp/application/15098914) from
Marketplace. See the [Downloading and Installing
Apps](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps)
section of the *Leveraging the Liferay Marketplace* chapter of this document for
more information.

This will create a folder named
*${liferay_home}/documentum-hook/docroot/WEB-INF/classes*. In the next section
we will tweak some of the files in that folder.

**Generate Documentum's cryptographic keys**

In this step, you will generate the cryptographic keys needed to connect to
Documentum. Follow the next steps:

1. In Documentum's machine, open a *Command Prompt*, and go to folder
*${documentum_home}\jboss4.3.0\server\DctmServer_MethodServer\deploy\lib*.

2. Encode Documentum's Registry user password with the following command (note
that the default password is *documentum­1*):

		${java_home}\bin\java ­cp dfc.jar com.documentum.fc.tools.RegistryPasswordUtils documentum­1

3. Note down the encoded value.

**Configure the keys and restart the server**

Now, open the file named
*${liferay_home}/documentum-hook/docroot/WEB-INF/classes/dfc.properties* and
configure the following properties: 

1. Set *dfc.globalregistry.password* to the password encoded in the previous
step. 
2. Set *dfc.docbroker.host[0]* to the IP address of the Documentum server
machine. 
3. Set *dfc.globalregistry.username* to Documentum's Registry user (by default
*dm_bof_registry*). 

Save the *dfc.properties* file and restart Liferay.

**Add a Documentum Repository in Documents and Media**

This is the final step. Add the Documents and Media application to a page, if
you haven't done so already. Then, in the Documents and Media application click
*Add Repository* and enter the following information:

- **Name:** Enter an arbitrary name for the repository.

- **Description:** Describe the repository.

- **Repository Type:** Select *Documentum*.

- **Repository:** The name of the Documentum repository (example: *documentum*).

- **Cabinet:** The name of the cabinet in Documentum repository (example:
*Wombat Insurance Co*).

Click *Save* and the left navigation panel of your Documents and Media
application will list your new repository.
-->
