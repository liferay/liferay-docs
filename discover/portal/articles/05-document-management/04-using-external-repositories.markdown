# Using External Repositories [](id=using-external-repositories)

Liferay Portal's Documents and Media lets you access external repositories in
addition to the built in document repository. Some of the features supported
with third-party repositories include:

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to a revision

Using external repositories involves configuring Liferay and the external
repository for interoperability and adding a *Repository* that binds them in
Documents and Media. 

There are also different kinds of repositories Liferay can connect with through
Documents and Media. Liferay can connect with repositories, such as Alfresco,
Nuxeo, and more, that support the Content Management Interoperability Services
(CMIS) standard. In addition, EE subscribers can use the [Liferay SharePoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
and [Liferay Documentum Connector](https://web.liferay.com/marketplace/-/mp/application/15098914)
apps to work with SharePoint and Documentum in Documents and Media. 

Let's start with the common configuration steps.

## Common Configuration Steps

The configuration steps are the same for repositories that support CMIS, and are
even similar between CMIS repositories and those that use proprietary APIs. So
it makes sense to cover these steps now.

The common configuration steps are:

1.   Adjust portal properties
2.   Synchronize user accounts between Liferay Portal and the repository
3.   Add the repository to Documents and Media

Let's adjust the portal properties.

### Step 1: Adjust Portal Properties [](id=adjusting-portal-properties)

In order to authenticate with the third-party repository, you need to store
passwords for the user sessions. You must configure an authentication type that
supports storing passwords to the user sessions.

**Important**: Since authentication with single sign-on (SSO) does not store
encrypted passwords in the user sessions, SSO can't be used with the external
repository types. 

Let's configure Liferay Portal for what's required in authentication.
In your [Liferay Home](/discover/deployment/-/knowledge_base/6-2/liferay-home),
create a `portal-ext.properties` file, if one doesn't already exist, and add a [`session.store.password`](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Session)
portal property set to `true`:

    session.store.password=true

Next, make sure to authenticate the same way on both Liferay Portal and
the external repository. You can do so by authenticating based on screen
name. Add the following [`company.security.auth.type`]( https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Company)
portal property to your `portal-ext.properties` file: 

    company.security.auth.type=screenName

Alternatively, configure these properties in the Control Panel under *Portal 
Settings* &rarr; *Authentication*.

### Step 2: Synchronize the User Accounts [](id=adding-required-repository-users)

External repository integration requires Liferay Portal users use the same
screen name and password as their external repository user. See sections of the
*Management* chapter on adding and managing users. Make sure to sign in to
Liferay Portal as such a user whenever you're accessing the repository from it.

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

### Step 3: Add the Repository to Documents and Media [](id=adding-the-repository)

You can add new repositories from the UI by clicking the *Add* button from the
Home folder. Repositories can only be mounted in the Home folder.

![Figure 5.5: You can add a new repository by navigating to *Add* &rarr; *Repository* in the Documents and Media portlet.](../../images/05-new-repository.png)

The next sections explain how to add specific types of repositories including
CMIS repositories and SharePoint repositories.

## Adding a CMIS Repository

Liferay Portal's Documents and Media can inter-operate with repositories that
support the Content Management Interoperability Services (CMIS) standard. CMIS
defines a model for interacting with repository files. Portal supports
interoperability through CMIS version 1.1 (the current CMIS version) and
earlier. It can access CMIS repositories using AtomPub and Web Services
protocols.

Let's explore accessing a CMIS Repository using AtomPub. 

### Adding a CMIS Repository that Uses AtomPub

AtomPub is the easiest protocol to specify for communicating with a CMIS repository. 

Follow these steps to access a repository with CMIS AtomPub:

1.  Sign in to Liferay Portal as a user that also exists in the external
    repository and can access files you want to use in that repository. 

2.  In Documents and Media *Home*, click the *Add* and select *Repository*. The
    *New Repository* screen appears. 

3.  Enter an arbitrary *Name* for the repository and optionally enter a
    *Description*.

4.  Click on the *Repository Configuration* section to access its form. Specify
    values for the following fields:
    - **Repository Type**: *CMIS Repository (AtomPub)*
    - **Repository ID**: Leave this blank, as Liferay generates the ID
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

Next, let's access a CMIS repository with Web Services.

### Adding a CMIS Repository that Uses Web Services

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
    
6.  Leave *Repository ID* blank, as Liferay generates the ID
     automatically. 

7.  Click *Save*

Your repository appears in Documents and Media. You can navigate into the
repository and work with files as you do other files in Documents and Media.

Next, let's learn how to use a SharePoint repository. 
                                                
## Using a SharePoint Repository [](id=example-repository-setup-sharepoint)

![EE Only Feature](../../images/ee-feature-web.png)

With the help of Liferay's SharePoint Connector app, users can access SharePoint
2010 libraries from within a Liferay Documents and Media Library. The app
installs a SharePoint repository type that you can select on adding a new
*Repository* to Documents and Media. The SharePoint Connector integrates sites
with existing SharePoint libraries, so you can access all of your organization's
files.

The [Liferay SharePoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
app is available to EE subscribers on Liferay's [Marketplace](http://marketplace.liferay.com/).
Refer to  the [Downloading and Installing Apps](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps)
section of the *Leveraging the Liferay Marketplace* article for more
information. 

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

### Environment Setup [](id=environment-setup)

Here's an overview of what you must do to configure your host, Liferay Portal,
and SharePoint to use SharePoint from Liferay's Documents and Media Library:


1. Enable Basic Authentication on the SharePoint host
2. Enable Versioning Support on the SharePoint library
3. Configure Authentication on Liferay Portal
4. Synchronize user credentials between Liferay Portal and SharePoint

Note, these instructions are geared to Liferay Portal and SharePoint system
administrators.

Before you can use SharePoint as an external repository with Liferay Portal, you
must set up a few things on the SharePoint host and in the SharePoint server
application.

#### Step 1: Enable Basic Authentication on the SharePoint Host [](id=step-1-enable-basic-authentication-on-the-sharepoint-host)

So that Liferay's SharePoint Connector can authenticate against the SharePoint
web services, you must enable Basic Authentication on the SharePoint host. As
you do this, make sure to empty Basic Authentication's default domain and realm
fields of all values.

Authentication setup steps differ between Windows versions. But as an example,
here are steps for enabling Basic Authentication on *Windows Server 2008*: 

1.  Sign in to the Windows server as a member of the Administrators group. 
2.  Open *Administrative Tools*, and then click *Internet Information Services
    (IIS) Manager* to launch the IIS Manager console. 
3.  In the Connections navigation panel, navigate to the SharePoint web site
    options by clicking on the server's name, then *Sites*, and then the name of
    the SharePoint site. The site's Features View is available in the main
    viewing area of the IIS Manager console.
4.  Select the *Features View* tab and then double-click on the *Authentication*
    icon in the IIS section of the Features View. The Authentication panel
    appears. 
    ![Figure 1: The Features View for the site shows the Authentication icon.](../../images/sharepoint-site-iis-authentication.png)
5.  In the Authentication panel, select the row named *Basic Authentication*.
    The Actions panel appears next to the main panel.
6.  In the Actions panel, click *Enable* to activate Basic Authentication. 
7.  Also in the Actions panel, click *Edit*. An Edit Basic Authentication
    Settings dialog box appears.
    ![Figure 2: Clicking the *Edit...* action brings up the a dialog for setting the Default domain and Realm.](../../images/sharepoint-host-edit-basic-auth-settings.png)
8.  In the dialog box, empty the *Default domain* and *Realm* fields of any
    values and click *OK*.

You've configured Basic Authentication on the SharePoint host.
8.  In the dialog box, empty the *Default domain* and *Realm* fields of any
    values and click *OK*.

You've configured Basic Authentication on the SharePoint host.

Next, you should enable versioning support in your SharePoint library so that
users can leverage file versioning between Liferay Portal and SharePoint. 

#### Step 2: Enable Versioning Support in the SharePoint Library [](id=step-2-enable-versioning-support-in-the-sharepoint-library)

You must enable versioning in SharePoint library for Liferay Portal's
check-in/out features to work with SharePoint. To enable it, follow these steps:

1.  Open a browser to the SharePoint library's URL.
2.  Click on the SharePoint library's name.
3.  At the top of the toolbar, click on *Liferay Tools* and then on *Library*,
    underneath *Liferay Tools*. 
4.  In the toolbar, click on *Library Settings*.
5.  Under *General Settings*, click on *Versioning settings*.
6.  In *Document Version History*, select *Create major and minor (draft)
    versions*. 
7.  In *Require Check Out* select *Yes*.

You've set SharePoint to accept versioning requests from Liferay Portal.

Next, you'll configure authentication for Liferay Portal. 

#### Step 3: Authentication on Liferay [](id=step-3-authentication-on-liferay)

In order to authenticate with the Sharepoint repository, you need to store
passwords for the user sessions. You must configure an authentication type that
supports storing passwords for the user sessions.

**Important**: Since authentication with single sign-on (SSO) does not store
encrypted passwords in the user sessions, SSO can't be used with the SharePoint
Connector app.

Let's configure Liferay Portal for what's required in authentication.
In your [Liferay Home](/discover/deployment/-/knowledge_base/6-2/liferay-home),
create a `portal-ext.properties` file, if one doesn't already exist, and add a [`session.store.password`](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Session)
portal property set to `true`:

    session.store.password=true

Next, make sure to authenticate the same way on both Liferay Portal and
the external repository. You can do so by authenticating based on screen
name. Add the following [`company.security.auth.type`]( https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Company)
portal property to your `portal-ext.properties` file: 

    company.security.auth.type=screenName

Alternatively, configure these properties in the Control Panel under *Portal 
Settings* &rarr; *Authentication*.

#### Step 4: Synchronize Credentials [](id=step-4-synchronize-credentials)

As a Liferay Portal system administrator, you must ensure that the same credentials
and authentication are used in Liferay Portal and in SharePoint. [LDAP](/discover/portal/-/knowledge_base/6-2/integrating-existing-users-into-liferay#ldap)
is a typical mechanism for synchronizing them. If you don't have LDAP, however,
you must manually synchronize the credentials and authentication. 
 
For Liferay Portal users to access the external repository, their screen names
and passwords must be the same in Liferay Portal and in Sharepoint. For details
on adding and managing Liferay Portal users, refer to [User Mangement](/discover/portal/-/knowledge_base/6-2/user-management).

From within Liferay's Documents and Media Library, you are now prepared to mount
the SharePoint library as an additional external repository. 

### Add SharePoint as a Liferay Documents and Media repository [](id=add-sharepoint-as-a-liferay-documents-and-media-repository)

It's time to add a Documents and Media Library Repository of type SharePoint,
so users can work with SharePoint in Liferay Portal. 

Here are the steps for adding a SharePoint repository type:

1.  Add the Documents and Media application to a page, if you haven't done so
    already.

2.  From the home location in the Documents and Media application, click *Add*
    and select *Repository*. The  *New Repository* screen appears. 

3.  In the New Repository screen enter an arbitrary repository *Name* and a
    *Description* (optional).

4.  Click on the *Repository Configuration* section to access its form. Then
    specify values for the following fields:

    **Repository Type**: Select the SharePoint option.

    **Site URL:** Enter the URL of the site where your SharePoint Library lives (e.g., `http://your-site`). 

    **Library Name**: Enter a name for the library. Typically you'd 
    enter the name of the SharePoint Library you're connecting to.

5.  After you've finished entering any additional options, click *Save*.

Your Documents and Media Library is now connected to the SharePoint repository.
The new external repository is now listed in the Documents and Media home.

Now that you've added a SharePoint Repository to Documents and Media, you can
access and modify SharePoint Library files from within Liferay Portal's
Documents and Media Library.

Next, let's look at configuring the Documents and Media portlet.
