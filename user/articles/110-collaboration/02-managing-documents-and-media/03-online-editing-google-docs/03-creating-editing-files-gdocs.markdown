---
header-id: creating-and-editing-files-with-google-docs
---

# Creating and Editing Files with Google Docs™

[TOC levels=1-4]

The Documents and Media Library lets you use Google Docs&trade; to create and 
edit text documents, spreadsheets, or presentations. When you finish your Google 
Docs&trade; editing session, your changes are automatically saved in the 
Documents and Media Library. This article contains instructions on using Google 
Docs&trade; for the following: 

-   [Creating Files](#creating-files)
-   [Editing Files](#editing-files)
-   [Multiple Editing Sessions](#multiple-editing-sessions)

## Authentication

Note that the first time you create or edit a Documents and Media file via 
Google Docs&trade;, you must authenticate with your Google account. This links 
Google Drive&trade; to your portal account, so you only need to do this 
authentication once. You can unlink your account at any time by navigating to 
User Menu &rarr; *Account Settings* &rarr; *General* &rarr; *Apps*, and clicking 
*Revoke* next to Google Drive&trade;. 

![Figure 1: You can unlink your Google account from the portal.](../../../../images/google-docs-unlink.png)

## Creating Files

Follow these steps to create a new Documents and Media file via Google 
Docs&trade;:

1.  Open the *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)), 
    click your site's name, and navigate to *Content & Data* &rarr; 
    *Documents and Media*. 

2.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) 
    and select the type of Google document to add to the Document Library. You 
    can select the following:

    -   Google Docs&trade;
    -   Google Slides&trade;
    -   Google Sheets&trade;

    When you select one of these options, @product@ creates a temporary 
    Documents and Media file and links it to a new Google file. Your browser 
    then redirects you to that Google file so you can create its content. 

    Note that some actions on the temporary Documents and Media file can affect 
    its linked Google file. For more information, see the section below on 
    [multiple editing sessions](#multiple-editing-sessions). 

    ![Figure 2: Select the type of Google document you want to create.](../../../../images/google-docs-new.png)

3.  Use the Google Docs&trade; editor to create your document's content. All 
    Google Docs&trade; features are available except for sharing. 

4.  Save or discard your changes by clicking one of these toolbar buttons in the 
    Google Docs&trade; editor: 

    -   **Save and Return to Liferay:** Saves your document as a new file in the 
        Documents and Media Library, deletes the Google file, and returns you to 
        the portal. The saved file's format depends on the type of Google 
        document you selected in step two above: 

        -   Google Docs&trade;: Microsoft Word (`.docx`)
        -   Google Slides&trade;: Microsoft PowerPoint (`.pptx`)
        -   Google Sheets&trade;: Microsoft Excel (`.xlsx`)

    -   **Discard Changes:** Returns you to the portal without saving your file 
        in the Documents and Media Library or Google Docs&trade;. 

    Note that it's also possible to close the Google Docs&trade; window without 
    clicking either button. In this case, the editing session remains open even 
    though the window that displayed it is closed. For more information, see the 
    section below on 
    [multiple editing sessions](#multiple-editing-sessions). 

    ![Figure 3: Save or discard your changes by using the toolbar in the editor.](../../../../images/google-docs-save-discard.png)

## Editing Files

You can use Google Docs&trade; to edit the following types of Documents and 
Media files: 

-   Text files (`.docx`, `.html`, `.txt`, `.rtf`, `.odt`)
-   Presentation files (`.pptx`, `.odp`)
-   Spreadsheet files (`.xlsx`, `.ods`, `.csv`, `.tsv`)
-   PDF files

| **Note:** Google Docs&trade; doesn't support older, non-XML-based Microsoft 
| Office file types (`.doc`, `.ppt`, `.xls`). 

Follow these steps to edit a Documents and Media file in Google Docs&trade;: 

1.  Navigate to the file in the Documents and Media Library. 

2.  Click the file's Actions icon 
    (![Actions](../../../../images/icon-actions.png)) 
    and select *Edit in Google Docs*. This automatically 
    [checks out](/discover/portal/-/knowledge_base/7-2/checking-out-and-editing-files) 
    the file, transfers its content to a new Google Docs&trade; file, and 
    redirects you to that Google Docs&trade; file. 

    ![Figure 4: Select *Edit in Google Docs* from the file's Actions menu.](../../../../images/google-docs-edit.png)

3.  Edit the file in Google Docs&trade;. The editing process is exactly the same 
    as described above for creating files. 

## Multiple Editing Sessions

When you create or edit a Documents and Media file in Google Docs&trade;, you 
can save or discard your changes by clicking *Save and Return to Liferay* or 
*Discard Changes*, respectively. If you instead close the window without 
clicking either, the editing session still exists. You can access it via the 
original file in Documents and Media. If the file didn't exist before (e.g., you 
were creating a new file), it appears in Documents and Media as a temporary 
file. 

When an editing session already exists for a Documents and Media file, the 
following actions are available via that file's Actions icon 
(![Actions](../../../../images/icon-actions.png)): 

-   **Edit in Google Docs:** Resume editing the file in Google Docs&trade;. 

-   **Check in:** Saves the Google file (including any changes) to the Documents 
    and Media file, then deletes the Google file. This is equivalent to clicking 
    *Save and Return to Liferay* in a Google Docs&trade; editing window. 

-   **Cancel Checkout:** Deletes the Google file, discarding any changes. This 
    is equivalent to clicking *Discard Changes* in a Google Docs&trade; editing 
    window. 
