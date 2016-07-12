# Repository Types [](id=repository-types)

@product@'s Documents and Media lets you store files in a number of
different ways and lets you access external repositories in addition to the
built in document repository. We'll explain how to set options for storing the
Document Library's files and how to connect with external repository types that
@product@ supports. Let's start with Document Library store options.

## Document Library Store Options [](id=document-library-store-options)

By default, @product@ uses a document library store option called Simple File
Store to store documents and media files on the file system (local or mounted)
of the server @product@'s running on. The store's default root directory is
`[Liferay Home]/data/document_library`. You can specify a different root
directory from within [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings).
To access System Settings, open the *Menu*
(![Menu](../../../images/icon-menu.png)) and navigate to *Control Panel &rarr;
Configuration &rarr; System Settings*. From System Settings, navigate to
*Platform* and then search for and select the entry *Simple File System Store*.
For the store's *Root dir* value, specify a path relative to the [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
or an absolute path; then click the *Update* button. The document library store
switches immediately to the new root dir. 

You can also use an entirely different method for storing documents and media
files. You can use any of the following documents and media library stores with
@product@:

**Simple File System Store**: uses the file system (local or a mounted share) to
store files.

**Advanced File System Store**: in addition to using the file system (local or a
mounted share) to store files, Advanced File System Store nests the files into
more directories by version, for faster performance and to store more files.

**CMIS Store (Content Management Interoperability Services)**: uses a system
separate from Liferay to store files. 

**DBStore (Database Storage)**: stores files in the @product@ database.

**JCRStore (Java Content Repository)**: stores files to a JSR-170 compliant
document repository. You can use any JCR client to access the files. The files
are stored to the server's file system by default. You can optionally configure
JCRStore to store files in a database. 

**S3Store (Amazon Simple Storage)**: uses Amazon's cloud-based storage solution.

For example, you can store documents and media files in your Liferay instance's
database using DBStore. To enable DBStore, add the following [`dl.store.impl`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Service)
portal property to a `portal-ext.properties` file in your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home):

    dl.store.impl=com.liferay.portlet.documentlibrary.store.DBStore

Remember to restart your @product@ server after updating your
`portal-ext.properties` file in order for your customizations to take effect.

There are properties related to document library stores that have been moved
from `portal-ext.properties` to OSGI configuration files. The following mapping
shows you how to configure those properties if needed:

#### CMIS [](id=cmis)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.cmis.CMISStore`

To `osgi/configs`: `com.liferay.portal.store.cmis.configuration.CMISStoreConfiguration.cfg`
 
Property | Default | Required
---------|---------|---------
`repositoryUrl` | `http://localhost:8080/alfresco/service/api/cmis` | `true`
`credentialsUsername` | none | `true`
`credentialsPassword` | none | `true`
`systemRootDir` | Liferay Home | `true`
 
#### File Store [](id=file-store)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore`

To `osgi/configs`: `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `false`

#### Advanced File Store [](id=advanced-file-store)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore`

To `osgi/configs`: `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`
 
Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `false`
 
#### JCR [](id=jcr)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.jcr.JCRStore`

To `osgi/configs`: `com.liferay.portal.store.jcr.configuration.JCRStoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`initializeOnStartup` | `false`| `true`
`wrapSession` | `true` | `true`
`moveVersionLabels` | `false` | `true`
`workspaceName` | `liferay` | `true`
`nodeDocumentlibrary` | `documentlibrary` | `true`
`jackrabbitRepositoryRoot` | `data/jackrabbit` | `true`
`jackrabbitConfigFilePath` | `repository.xml` | `true`
`jackrabbitRepositoryHome` | `home` | `true`
`jackrabbitCredentialsUsername` | none | `true`
`jackrabbitCredentialsPassword` | none | `true`

#### S3 [](id=s3)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.s3.S3Store`

To `osgi/configs`: `com.liferay.portal.store.s3.configuration.S3StoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`accessKey` | | `false`
`secretKey` | | `false`
`s3Region` | `us-east-1` | `false`
`bucketName` | | `true`
`s3StorageClass` | STANDARD | `false`
`httpClientMaxConnections` | `50` | `false`
`cacheDirCleanUpExpunge` | `7` | `false`
`cacheDirCleanUpFrequency` | `100` | `false`

+$$$

**Warning:** If a database transaction rollback occurs in a Document Library
that uses a file system based store, file system changes that have occurred
since the start of the transaction won't be reversed. Inconsistencies between
Document Library files and those in the file system store can occur and may
require manual synchronization. All stores except DBStore are vulnerable to this
limitation.

$$$

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

@product@'s Documents and Media can inter-operate with repositories that support
the Content Management Interoperability Services (CMIS) standard. CMIS defines a
model for interacting with repository files. @product@ supports interoperability
through CMIS version 1.1 (the current CMIS version) and earlier. @product@ can
access CMIS repositories using AtomPub and Web Services protocols.

![Figure 1: In Documents and Media, external repositories look similar to folders. You can perform supported operations on their files. This figure shows an Alfresco CMIS repository named *My Alfresco*.](../../../images/dm-repo-types-actions.png)

Here are the CMIS repository integration steps:

1.   Adjust portal properties
2.   Synchronize user accounts between @product@ and the repository
3.   Add the repository to Documents and Media

+$$$

Interoperability with SharePoint and Documtum repositories is available to EE
subscribers with the [Liferay SharePoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
and [Liferay Documentum Connector](https://web.liferay.com/marketplace/-/mp/application/15098914)
apps, available on the Marketplace.

$$$

Let's start with setting portal properties.

### Step 1: Adjust Portal Properties [](id=adjusting-portal-properties)

In order to authenticate with the third-party repository, you need to store
passwords for the user sessions. You must configure an authentication type that
supports storing passwords to the user sessions.

**Important**: Since authentication with single sign-on (SSO) does not store
encrypted passwords in the user sessions, SSO can't be used with the external
repository types. 

Let's configure Liferay Portal for what's required in authentication.
In  a `portal-ext.properties` file in your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home),
set a [`session.store.password`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Session)
portal property to `true`:

    session.store.password=true

Next, make sure to authenticate the same way on both @product@ and
the repository. You can do so by authenticating based on screen name. Set the
following [`company.security.auth.type`]( https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Company)
portal property: 

    company.security.auth.type=screenName

Alternatively, you can configure both of these properties in the Control Panel
under *Instance Settings &rarr; Authentication*.
                                          
### Step 2: Synchronize the User Accounts [](id=synchronizing-repository-users)

External repository integration requires @product@ users use the same screen
name and password as their external repository user. For details on adding and
managing users, refer to [User Mangement](/discover/portal/-/knowledge_base/7-0/user-management).
Make sure to sign in to @product@ as such a user whenever you're accessing the
repository from it.

+$$$

**Important**: Use lowercase for user names in the external repository, as
Liferay screen names are lowercase.

$$$

+$$$

**Note**: In the external repository, make sure to associate users with
necessary roles for accessing repository files. In Nuxeo, for example, a user
must belong to the Nuxeo *Administrators* group to access Nuxeo repository files
via CMIS.

$$$

Adding a *Repository* mapped to the external repository is the final step.

### Step 3: Add the Repository to Documents and Media

The last step is adding a to Documents and Media a *Repository* to inter-operate
with the external repository. Documents and Media can connect with a CMIS
repository using the Atom Publishing (AtomPub) protocol or Web Services
protocol. Let's explore accessing a CMIS Repository using AtomPub. 

#### Adding a CMIS Repository that Uses AtomPub [](id=using-a-cmis-repository-with-atompub)

AtomPub is the easiest protocol to specify for communicating with a CMIS repository. 

Follow these steps to access a repository with CMIS AtomPub:

1.  Sign in to @product@ as a user that also exists in the external
repository and can access files you want to use in that repository. 

2.  In Documents and Media *Home*, click the *Add* icon
(![Add](../../../images/icon-add.png)) and select *Repository*. The *New
Repository* screen appears. 

    ![Figure 2: Here's the form for accessing a CMIS repository using AtomPub.](../../../images/dm-repo-types-new-repo-config.png)

3.  Enter an arbitrary *Name* for the repository and optionally enter a
*Description*.

4.  Click on the *Repository Configuration* section to access its form. Specify
values for the following fields:
    - **Repository Type**: *CMIS Repository (AtomPub)*
    - **Repository ID**: Leave this blank, as @product@ generates the ID
    automatically
    - **AtomPub URL**: Enter the AtomPub URL for CMIS per the
    repository's documentation. Example URLs are listed below. If you copy them,
    make sure to replace the `[host]` and `[port]` values with your own. 
        -   Alfresco 5.1 CMIS 1.0: `http://[host]:[port]/alfresco/cmisatom`
        -   Alfresco 5.1 CMIS 1.1: `http://[host]:[port]/alfresco/api/-default-/public/cmis/versions/1.1/atom`
        -   Nuxeo 7.10: `http://[host]:[port]/nuxeo/atom/cmis`

5.  Click *Save*

The external repository appears in Documents and Media. You can navigate
the repository and manage files. 

![Figure 3: Documents and Media lets you perform CMIS supported actions on external repository files.](../../../images/dm-repo-types-alfresco-site-doclib-file.png)

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

Next, let's access a CMIS repository with Web Services.

### Adding a CMIS Repository that Uses Web Services [](id=using-a-cmis-repository-with-web-services)

After adjusting your portal properties and adding any user accounts required by
the repository, you can add a Documents and Media Repository that inter-operates
with an external repository that supports CMIS with Web Services. 

Follow these steps to add such a repository:

1.  In Documents and Media *Home*, click the *Add* button and select
*Repository*. The *New Repository* screen appears.

2.  Enter an arbitrary *Name* for the repository and optionally enter a
*Description*.

3.  Click on the *Repository Configuration* section to access its form

4.  For **Repository Type:**, select *CMIS Repository (Web Services)*.

5.  Enter the web service URLs (WSDL URLs) per your CMIS repository's
documentation. Example URLs are listed after these steps. Make sure to enter
**URLs** for the following services: 

    - Web Services ACL Service
    - Web Services Discovery Service
    - Web Services Multi-Filing Service
    - Web Services Navigation Service
    - Web Services Object Service
    - Web Services Policy Service
    - Web Services Relationship Service
    - Web Services Repository Service
    - Web Services Versioning Service

    +$$$

    Tips: In a browser, you can open the repository's WSDL  file and verify each
    service in it. Also make sure to specify the entire URLs--they all end with
    `?wsdl`. 

    $$$

    ![Figure 4: Here's an example of the web service URLs for an Alfresco repository.](../../../images/dm-repo-types-alfresco-ws.png)

    
6.  Leave *Repository ID* blank, as @product@ generates the ID
     automatically. 

7.  Click *Save*

Your repository appears in Documents and Media. You can navigate into the
repository and work with files as you do other files in Documents and Media.

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

Before you can use Documentum as an external repository with @product@, you must
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

Save the *dfc.properties* file and restart @product@.

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

@product@'s CMIS repository integration gives you flexibility to access external
repository files in addition to your existing Documents and Media files. 

