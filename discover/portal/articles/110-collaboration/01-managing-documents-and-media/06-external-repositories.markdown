# Using External Repositories [](id=using-external-repositories)

@product@'s Documents and Media lets you access external repositories as well as 
the built-in document repository. Administrators can learn how to set options 
for storing the Documents and Media Library's files
[here](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration). 
To connect the Documents and Media library to external repositories, read on. 

Documents and Media lets users connect to multiple third-party repositories. 
Some of the features supported with third-party repositories include:

-   Reading/writing documents and folders
-   Document check-in, check-out, and undo check-out
-   Downloading documents
-   Moving folders and documents within the repository
-   Getting revision history
-   Reverting to a revision

@product@'s Documents and Media can interoperate with repositories that support 
the Content Management Interoperability Services (CMIS) standard. CMIS defines a 
model for interacting with repository files. @product@ supports interoperability 
through CMIS version 1.1 (the current CMIS version) and earlier. @product@ can 
access CMIS repositories using AtomPub and Web Services protocols. 

Here are the CMIS repository integration steps:

1.   Adjust portal properties.
2.   Synchronize user accounts between @product@ and the repository.
3.   Add the repository to Documents and Media.

<!--
+$$$

Interoperability with SharePoint repositories is available to DXP subscribers 
with the 
[Liferay Connector for SharePoint](https://web.liferay.com/marketplace/-/mp/application/15188537)
app, which is available on Liferay Marketplace. 

$$$
-->

## Step 1: Adjust Portal Properties [](id=adjusting-portal-properties)

To authenticate to the third-party repository, you must store passwords for the 
user sessions. You must configure an authentication type that supports this. 

+$$$

**Important**: Since authentication with single sign-on (SSO) doesn't store
encrypted passwords in the user sessions, SSO can't be used with the external
repository types. 

$$$

Follow these steps to configure @product@ for this authentication requirement: 

1.  In a `portal-ext.properties` file in your 
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home) 
    folder, set a 
    [`session.store.password`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Session)
    portal property to `true`:

        session.store.password=true

2.  Make sure to authenticate the same way on both @product@ and the repository. 
    You can do so by authenticating based on screen name. To do this in the same 
    `portal-ext.properties` file, set the 
    [`company.security.auth.type`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Company)
    portal property to `screenName`: 

        company.security.auth.type=screenName

Alternatively, you can configure both of these properties in the Control Panel
under *Configuration* &rarr; *Instance Settings &rarr; Authentication*. 
 
## Step 2: Synchronize the User Accounts [](id=synchronizing-repository-users)

External repository integration requires @product@ users use the same screen
name and password as their external repository user. For details on adding and
managing users, see the 
[Managing Users guide](/discover/portal/-/knowledge_base/7-1/managing-users). 
Make sure to sign in to @product@ as such a user whenever you access the 
repository from it.

+$$$

**Important**: Use lowercase for user names in the external repository, as 
@product@ screen names are lowercase. 

$$$

+$$$

**Note**: In the external repository, make sure to associate users with the 
roles necessary to access repository files. In Nuxeo, for example, a user must 
belong to the Nuxeo *Administrators* group to access Nuxeo repository files via 
CMIS.

$$$

## Step 3: Add the Repository to Documents and Media [](id=step-3-add-the-repository-to-documents-and-media)

The last step is to add a Documents and Media repository to interoperate with 
the external repository. Documents and Media can connect with a CMIS repository 
using the Atom Publishing (AtomPub) or Web Services protocols. This section 
shows you how to add an AtomPub repository, while the next section shows you how 
to add a Web Services repository. 

Follow these steps to access a repository with CMIS AtomPub:

1.  Sign in to @product@ as a user that also exists in the external repository 
    and can access files you want to use in that repository. 

2.  In the Documents and Media *Home* folder, click the *Add* button 
    (![Add](../../../images/icon-add.png)) and select *Repository*. The *New
    Repository* form appears. 

    ![Figure 1: Here's the form for accessing a CMIS repository using AtomPub.](../../../images/dm-repo-types-new-repo-config.png)

3.  Enter an arbitrary name for the repository and optionally enter a 
    description. 

4.  Open the *Repository Configuration* section of the form. Specify values for 
    the following fields: 

    -   **Repository Type**: CMIS Repository (AtomPub)
    -   **Repository ID**: Leave this blank, as @product@ generates the ID
        automatically
    -   **AtomPub URL**: Enter the AtomPub URL for CMIS per the repository's 
        documentation. If you copy these example values, make sure to replace 
        the `[host]` and `[port]` values with your own: 

        -   Alfresco 5.1 CMIS 1.0: `http://[host]:[port]/alfresco/cmisatom`
        -   Alfresco 5.1 CMIS 1.1: `http://[host]:[port]/alfresco/api/-default-/public/cmis/versions/1.1/atom`
        -   Nuxeo 7.10: `http://[host]:[port]/nuxeo/atom/cmis`

5.  Click *Save*.

The external repository appears in Documents and Media. You can navigate the 
repository and manage its files. 

### Adding a CMIS Repository that Uses Web Services [](id=using-a-cmis-repository-with-web-services)

Follow these steps to access a repository with CMIS Web Services:

1.  Sign in to @product@ as a user that also exists in the external repository 
    and can access files you want to use in that repository. 

2.  In the Documents and Media *Home* folder, click the *Add* button 
    (![Add](../../../images/icon-add.png)) and select *Repository*. The *New
    Repository* form appears. 

3.  Enter an arbitrary name for the repository and optionally enter a 
    description. 

4.  Open the *Repository Configuration* section of the form. For *Repository 
    Type*, select *CMIS Repository (Web Services)*. 

5.  Enter the web service URLs (WSDL URLs) per your CMIS repository's 
    documentation. Make sure to enter URLs for the following services: 

    -   Web Services ACL Service
    -   Web Services Discovery Service
    -   Web Services Multi-Filing Service
    -   Web Services Navigation Service
    -   Web Services Object Service
    -   Web Services Policy Service
    -   Web Services Relationship Service
    -   Web Services Repository Service
    -   Web Services Versioning Service

    +$$$

    Tips: In a browser, you can open the repository's WSDL  file and verify each
    service in it. Also make sure to specify the entire URLs--they all end with
    `?wsdl`. 

    $$$

    ![Figure 2: Here's an example of the web service URLs for an Alfresco repository.](../../../images/dm-repo-types-alfresco-ws.png)
 
6.  Leave *Repository ID* blank, as @product@ generates the ID automatically. 

7.  Click *Save*.

Your repository appears in Documents and Media. You can navigate to the 
repository and work with files as you do other files in Documents and Media. 
