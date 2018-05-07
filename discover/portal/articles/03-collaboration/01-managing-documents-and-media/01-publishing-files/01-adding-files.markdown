# Adding Files to a Document Library

Adding files to your site's Document Library is the first step to letting 
administrators and users work with files throughout your site. This article 
covers the following topics to help you get started adding files to your 
Document Library: 

1.  **Granting File Permissions and Roles:** Determine who can add, view, and 
    update files. Doing this before adding files ensures that only those you 
    wish can access your Document Library. 

2.  **Adding Files:** Add specific types of files, and their associated 
    metadata, to your Document Library. 

## Granting File Permissions and Roles

You should carefully manage who can add, view, and update files in your Document 
Library. You can store files of all kinds for various purposes. For example, you 
may have one set of files intended for only specific site members, and another 
set of files intended for everyone, including guests. You can use @product@'s 
[Roles and Permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions) 
to precisely control access to Document Library files. The Document Library's 
folder permissions also help you organize files. 

Follow these steps to create a role that lets users assigned to it manage files 
in your site's Documents and Media: 

1.  Open the *Menu* (![Product Menu](../../../../images/icon-menu.png)) and 
    navigate to *Control Panel &rarr; Users &rarr; Roles*. 

2.  Select the *Site Roles* tab (or *Organization Roles*, if you'd like to 
    create an organization role) and then click the Add button 
    (![Add](../../../../images/icon-add.png)) to begin creating a role.

3.  Give your role a name and a description, then click *Save*.

4.  Select your role's *Define Permissions* tab. In the role's permission 
    definition screen, navigate to *Site Administration* &rarr; *Content* &rarr; 
    *Documents and Media*. In the *General Permissions* section, select 
    *Access in Site Administration* and click *Save*.

    ![Figure 1: It's often helpful to define a role for specific users to access Documents and Media from Site Administration.](../../../../images/dm-define-role-permissions.png)

5.  Assign this role to the users that you want to manage media. For more 
    information on this and other topics related to roles, see 
    [the documentation on roles and permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions).

## Using the Add Menu

Follow these steps to add files to your site's Document Library:

1.  Open the *Menu* (![Product Menu](../../../../images/icon-menu.png)), click 
    on your site's name, and navigate to *Content &rarr; Documents and Media* 
    for your site. The Documents and Media screen appears and displays the 
    Documents and Media library's *Home* (its root folder). As you add files and 
    folders to the Document Library, they're listed here. 

    ![Figure 2: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload and add all kinds of documents to the library.](../../../../images/dm-admin-add-menu.png)

2.  Click the *Add* icon (![Add](../../../../images/icon-add.png)) and select 
    the type of document to add to the Document Library. You can add documents, 
    folders, and shortcuts much like you would on a desktop file system. You can 
    even configure access to an entirely different repository. Here are the Add 
    menu's options: 

    -   **Folder**: Create a new folder in the app's file system. 

    -   **Shortcut**: Create a shortcut to any document that you can view. You 
        can set permissions on the shortcut to specify who can access the 
        original document via the shortcut.

    -   **Repository**: Add access to an external repository. See the 
        [store types documentation](/discover/portal/-/knowledge_base/7-0/liferay-store-types)
        for more information. 

    -   **Multiple Documents**: Upload several documents at once.

    -   **Basic Document**: Upload a single file. By default, basic documents 
        aren't described by any metadata sets. 

<!--
    -   **Google Docs**: Available via the Liferay Plugin for Google Drive&trade; 
        from the 
        [Liferay Marketplace](https://web.liferay.com/marketplace). 
        This lets you create a file entry that links to a Google document. For 
        more information, see 
        [the documentation on accessing Google Docs&trade;](/discover/portal/-/knowledge_base/7-1/accessing-google-docs). 
-->

    The remaining items in the Add menu are default document types that are each 
    described by a unique metadata set. When you add a document belonging to a 
    specific document type, a form appears for picking the file to upload and 
    entering the data defined by the document type's metadata set. The 
    *Contract* type appears below as an example. 

    -   **Contract**: Upload a file and apply the *Contract* document type to 
        it. This document type is intended to be used to describe legal 
        contracts. By default, contracts are described by fields for the 
        effective date, expiration date, contract type, status, legal reviewer, 
        signing authority, and deal name. 

    -   Any custom documents types that have been defined also appear in the Add 
        menu. 

3.  When you're finished selecting the file to upload and filling out any 
    document type fields that are necessary, click *Publish*. 
