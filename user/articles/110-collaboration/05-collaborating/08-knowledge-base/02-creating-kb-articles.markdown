# Creating Knowledge Base Articles [](id=creating-knowledge-base-articles)

The Knowledge Base app in Site Administration contains everything you need to 
create articles in the Knowledge Base. You can create articles by authoring them 
in the app's WYSIWYG editor or by importing them from Markdown files 
(`.markdown`, `.md`) in a ZIP archive. The sections below cover both ways of 
creating articles. 

+$$$

**Note:** To access Knowledge Base in Site Administration, a Role must have the 
permission *Knowledge Base* &rarr; *Access in Site Administration*. To add or 
act on articles, folders, or suggestions, the Site administrator must grant the 
appropriate permissions using the Permissions window in Knowledge Base. 

$$$

To navigate to the Knowledge Base app, open the Menu 
(![Menu](../../../../images/icon-menu.png)) 
then go to Site Administration (the menu for your Site) &rarr; *Content* &rarr; 
*Knowledge Base*. The Knowledge Base app has 3 tabs: 

-   **Articles:** Create and manage articles and folders. 
-   **Templates:** Create and manage templates. 
-   **Suggestions:** Manage user-submitted feedback for articles. 

Select the *Articles* tab, then proceed to the sections below for instructions 
on creating articles. 

![Figure 1: The Knowledge Base app in Site Administration lets you create Knowledge Base articles.](../../../../images/kb-admin-articles.png)

## Authoring Articles in the Editor [](id=authoring-articles-in-the-editor)

Follow these steps to create an article in the editor: 

1.  In the Articles tab, click the *Add* button 
    (![Add](../../../../images/icon-add.png)) 
    and choose *Basic Article* or the name of an available template. This brings 
    up the New Article form. 

2.  Enter a title for the article. A URL-safe version of the title you provide 
    is added to the end of the article's friendly URL. You can manage the 
    friendly URL in the *Configuration* section's *Friendly URL* field. 

3.  Use the WYSIWYG editor to create the article's content. To view or edit the 
    article's HTML source, click the *Source* button in the editor. The sections 
    below the editor let you add attachments and tags, specify related assets, 
    and set permissions for the article. By default, View permission is granted 
    to the Guest role, meaning anyone can view your article. 

    ![Figure 2: You can create and modify a Knowledge Base article's content using the WYSIWYG editor.](../../../../images/kb-admin-new-article.png)

4.  Click *Publish* to submit the article for publication or click 
    *Save as Draft* to continue working on it later. Note that if you've enabled 
    workflow for the Knowledge Base, your article must be approved before 
    publication. 

Once the article is saved, it is converted automatically to HTML for the 
Knowledge Base. Articles are listed in a table in the Articles tab. 

## Importing Knowledge Base Articles [](id=importing-knowledge-base-articles)

You can also create new Knowledge Base articles by importing them from a ZIP 
archive that contains articles in the Markdown format (`.markdown`, `.md`). For 
example, you could write articles in your favorite Markdown editor, package them 
in a ZIP file, and then import that ZIP file to create those articles in the 
Knowledge Base. The Knowledge Base can also prioritize articles by their 
filenames' numerical prefixes. For example, the Knowledge Base would list 
`01-article.markdown` and `02-article.markdown` in ascending order by their 
numerical prefix (`01`, `02`). For more information on article priority, see 
[Managing Knowledge Base Articles](/discover/portal/-/knowledge_base/7-1/managing-the-knowledge-base#managing-knowledge-base-articles)
For detailed information on the Knowledge Base importer, see the following 
topics: 

-   [Importing Knowledge Base Articles](/discover/portal/-/knowledge_base/7-1/importing-knowledge-base-articles)
-   [Knowledge Base ZIP File Requirements](/discover/portal/-/knowledge_base/7-1/knowledge-base-zip-file-requirements)
-   [Knowledge Base Importer FAQs](/discover/portal/-/knowledge_base/7-1/knowledge-base-importer-faqs)

+$$$

**Note:** To import articles, your Role must have the permission *Knowledge
Base* &rarr; *Resource Permissions: Import Articles*. 

$$$

Follow these steps to import articles into the Knowledge Base: 

1.  In the Articles tab, click the *Add* button 
    (![Add](../../../../images/icon-add.png)) 
    and choose *Import*. This brings up the Import form. 

2.  Click *Browse* and select the ZIP file that contains the articles you want 
    to import. 

3.  If you want to use the files' numerical prefixes to prioritize the imported 
    articles in the Knowledge Base, select 
    *Apply numerical prefixes of article files as priorities*. 

4.  Click *Save* when you're finished. 

Like all articles, imported articles are automatically converted to HTML for the 
Knowledge Base and listed in a table with the rest of the articles in the 
Articles tab. 

![Figure 3: You can import ZIP files that contain Knowledge Base articles in Markdown format.](../../../../images/kb-admin-import.png)
