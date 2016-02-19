# Repository Types

## Media Library Store Options

By default, Liferay stores documents and media files on the file system of the
server on which it's running. You can choose a specific location for the
document library store's root directory by adding the following property to your
`portal-ext.properties` file and replacing the default path with your custom
path:

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

For example, you can store documents and media files in your Liferay instance's
database using DBStore. To enable DBStore, add the following line to your
`portal-ext.properties` file:

    dl.store.impl=com.liferay.portlet.documentlibrary.store.DBStore

Remember to restart your Liferay server after updating your
`portal-ext.properties` file in order for your customizations to take effect.
Please refer to the Document Library Portlet section of your `portal.properties`
file to find a complete list of supported customizations. You can customize
features such as the maximum allowed size of documents and media files, the list
of allowed file extensions, which types of files should be indexed, etc.

## Using External Repositories [](id=using-external-repositories)

Documents and Media allows users to connect to multiple third-party
repositories. Some of the features supported with third-party repositories
include:

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to revision

A generic integration for CMIS 1.0 (Content Management Interoperability
Services) repositories is included by default. It is tested with Alfresco and
Nuxeo but should support all repositories implementing CMIS 1.0 with AtomPub or
Web Services protocols.

Nevertheless, support for different versions of each repository type is detailed
in the following repository setup sections. Versions are classified in three
support levels:

- **Officially supported** versions are the ones we develop against in our
development-QA cycle.

- **Experimental support** versions should work but may not be as stable as
those officially supported. They have been tested in development but are not
certified by QA.

- **Unsupported** versions (those not listed in this document) may or may not
work at all, depending entirely on how the third party vendor implements
backwards compatibility. These are versions we haven't even tested.

There are some subtle differences in setting up the different kinds of
third-party repositories for use in Documents and Media. But there are plenty of
similarities too. Common Liferay configuration steps include:

- Adjusting the portal properties.
- Adding any user accounts required by the repository.
- Adding the repository.

Keep in mind your third-party repository may require installation and deployment
of an appropriate Liferay plugin. 
<!-- Plugins for SharePoint and Documentum are
available through Liferay's Marketplace (please note that they don't use CMIS,
but proprietary APIs).
-->

Now, let's go through the common configuration steps.

### Common Configuration

There are several steps common to setting up any external repository type. Let's
start with setting the necessary portal properties.

#### Adjusting portal properties [](id=adjusting-portal-properties)

The admin must ensure that the same credentials and authentication are being
used in Liferay and in the external repository. This is normally synchronized
using a mechanism like LDAP. If you don't have LDAP, you need to ensure manually
that the credentials and authentication are the same. In order to authenticate
with the third-party repository, you need to store passwords for the user
sessions. Set the following portal property in your `portal-ext.properties`:

    session.store.password=true

Next, we need to make sure the login and password for Liferay are the same as
the external repository. This is easily accomplished by using identical screen
names, so in `portal-ext.properties` add the following: 

    company.security.auth.type=screenName

Alternatively, configure these properties in the Control Panel under *Portal
Settings* &rarr; *Authentication*.

#### Adding required repository users [](id=adding-required-repository-users)

Once these properties are set, you must create a user in Liferay with a
screen name and password matching the administrative user of your external
repository. Be sure to assign appropriate roles (e.g. Administrator) to that
user. Sign out of Liferay and sign in again as that new user. See sections of
the *Management* chapter on adding and managing users.

You can add new repositories from the UI by clicking the *Add* button from the
Home folder. Repositories can only be mounted in the Home folder.

Figure x: You can add a new repository by navigating to *Add* &rarr; *Repository* in the Documents and Media application. 05-new-repository

The fields in this form change depending on the selected *Repository Type*. In
the following sections, you will see how to configure different repositories.

### Accessing a CMIS Repository Using AtomPub

After completing the [Common Configuration](/discover/portal/-/knowledge_base/7-0/repository-types#common-configuration)
steps, you can add Documents and Media Repository that communicats with your
CMIS repository via AtomPub. 

These instructions were written based on using Alfresco Community 5.1 and Nuxeo
Platform 7.10.

Follow these steps to add your repository:

1.  In Documents and Media *Home*, click the *Add* button and select
*Repository*. The *New Repository* screen appears.

2.  Enter an arbitrary *Name* for the repository and optionally enter a
*Description*.

3.  Click on the *Repository Configuration* section to access its form

4.  For **Repository Type:**, select *CMIS Repository (AtomPub)*.

5.  Leave **Repository ID** blank, as Liferay generates the ID
    automatically. 

6.  For **AtomPub URL**, enter the AtomPub URL for CMIS 1.0 per the CMIS
    repository's documentation. Below are some example URLs. Make sure to
    replace the `IP_ADDRESS` and `PORT` values with those of your CMIS
    repository server. 
    -   Alfresco 5.1: `http://IP_ADDRESS:PORT/alfresco/cmisatom`
    -   Nuxeo Platform 7.10: `http://IP_ADDRESS:PORT/nuxeo/atom/cmis`

7.  Click *Save*

Your repository appears in Documents and Media. You can navigate into the
repository and work with files as you do other files in Documents and Media.

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
### Accessing a CMIS Repository Using Web Services


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
### Adding a Sharepoint Repository

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
### Adding a Documentum Repository

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
