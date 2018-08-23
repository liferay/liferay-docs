# Adding Files to a Document Library [](id=adding-files-to-a-document-library)

This article covers the following topics to help you get started adding files to
your Document Library: 

1.  **Granting File Permissions and Roles:** Determine who can add, view, and 
    update files. Doing this before adding files ensures that only those you 
    wish can access your Document Library. 

2.  **Adding Files:** Add specific types of files and their associated 
    metadata to your Document Library. 

## Granting File Permissions and Roles [](id=granting-file-permissions-and-roles)

You should carefully manage who can add, view, and update files. You can store
files of all kinds for various purposes. For example, you may have one set of
files intended for only specific site members and another intended for everyone,
including guests. You can use 
[Roles and Permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions) 
to control access to Document Library files. The Document Library's folder
permissions also help you organize files. 

Follow these steps to create a Role for managing files in your site's Documents
and Media: 

1.  Open the *Menu* (![Product Menu](../../../../images/icon-menu.png)) and 
    navigate to *Control Panel &rarr; Users &rarr; Roles*. 

2.  Select the *Site Roles* tab (or *Organization Roles*, for 
    an Organization Role) and then click the *Add* button 
    (![Add](../../../../images/icon-add.png)) to begin creating a role.

3.  Give your Role a name and a description, then click *Save*.

4.  Select your Role's *Define Permissions* tab. In the Role's permission 
    definition screen, navigate to *Site Administration* &rarr; *Content* &rarr; 
    *Documents and Media*. In the *General Permissions* section, select 
    *Access in Site Administration* and click *Save*.

    ![Figure 1: It's often helpful to define a role for specific users to access Documents and Media from Site Administration.](../../../../images/dm-define-role-permissions.png)

5.  Assign this Role to the Users that should manage media. For more 
    information on this and other topics related to Roles, see 
    [the documentation on Roles and permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions).

## Using the Add Menu [](id=using-the-add-menu)

Follow these steps to add files to your site's Document Library:

1.  Open the *Menu* (![Product Menu](../../../../images/icon-menu.png)), click 
    on your site's name, and navigate to *Content &rarr; Documents and Media*. 
    The Documents and Media screen appears and displays the Documents and Media 
    library's *Home* (its root folder). As you add files and folders to the 
    Document Library, they're listed here. 

    ![Figure 2: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload and add all kinds of documents to the library.](../../../../images/dm-admin-add-menu.png)

2.  Click the *Add* icon (![Add](../../../../images/icon-add.png)) and select 
    the type of document to add to the Document Library. You can add documents, 
    folders, and shortcuts much like you would on a desktop file system. You can
    even configure access to an entirely different repository. The Add menu's
    options are described below. 

3.  When you're finished selecting the file to upload and filling out any 
    document type fields that are necessary, click *Publish*. 

**Folder**: Create a new folder in the app's file system. 

**Shortcut**: Create a shortcut to any document that you can view. You 
can set permissions on the shortcut to specify who can access the 
original document via the shortcut.

**Repository**: Add access to an external repository. See the 
[store types documentation](/discover/portal/-/knowledge_base/7-0/liferay-store-types)
for more information. 

**Multiple Documents**: Upload several files at once. You can apply a 
single description and document type to all the files. You can also 
[categorize and tag](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories) 
the files, and assign them default permissions. 

**Basic Document**: Upload a single file. By default, basic documents 
aren't described by any metadata sets. 

The remaining items in the Add menu are default document types described by 
a unique metadata set. When you add a document belonging to a document type,
a form appears that lets you pick the file to upload and enter the data defined
by the document type's metadata set. For example, the *Contract* document type
is used to describe legal contracts. By default, contracts are described by
fields for the effective date, expiration date, contract type, status, legal
reviewer, signing authority, and deal name. 

Any custom document types that have been defined also appear in the Add 
menu. 

<!--
Add this to the Add menu items in step 2 once the Google Drive plugin is released
for 7.1

    -   **Google Docs**: Available via the Liferay Plugin for Google Drive&trade; 
        from the 
        [Liferay Marketplace](https://web.liferay.com/marketplace). 
        This lets you create a file entry that links to a Google document. For 
        more information, see 
        [the documentation on accessing Google Docs&trade;](/discover/portal/-/knowledge_base/7-1/accessing-google-docs). 
-->
