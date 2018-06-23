# Adding and Editing Wiki Pages [](id=adding-and-editing-wiki-pages)

Wiki nodes initially have no pages. When you navigate into a node for the first 
time, a default page called *FrontPage* is created automatically. To view the 
page, click the wiki node's name and then click *FrontPage*. The FrontPage 
appears and shows a message that explains the page is empty and needs you to add 
content. That message is a link; click it to start editing the page. The wiki 
page editing form then appears. 

+$$$

**Note:** See the 
[getting started article](/discover/portal/-/knowledge_base/7-1/getting-started-with-wikis) 
for instructions on accessing your wiki nodes.

$$$

![Figure 1: Each empty wiki page presents a default message link you can click to edit the page.](../../../../images/wiki-empty-frontpage.png)

![Figure 2: The wiki page editing form lets you create and edit your page's content.](../../../../images/wiki-page-editor.png)

Follow these steps to use the wiki page editing form: 

1.  Enter your content in the field that contains the text *Write your content
    here...*. This is a rich-text, WYSIWYG editor that is almost identical to
    the one used in the Blogs app. The only difference is that the wiki editor
    uses Creole instead of HTML as its source. Click the link *Show Syntax
    Help* if you need help with Creole syntax (e.g., syntax for text styling,
    header formatting, link creation, etc.). For a detailed explanation of the
    rest of the editor, see the 
    [Blogs documentation](/discover/portal/-/knowledge_base/7-1/using-the-blog-entry-editor). 

2.  If you want to attach files to the page, open the *Attachments* section of 
    the form and add them via drag and drop or the *Select Files* button. 

3.  If you want to associate a tag with the page, open the *Categorization* 
    section and use the *Select* button to select an existing tag. You can also 
    create a new tag by entering it in the *Tags* field and clicking *Add*. See 
    [the documentation on tags](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags) 
    for more information. 

4.  If you want to select an existing asset in the portal (e.g., a media file, 
    blog post, etc.) to relate to the page, open the *Related Assets* section 
    and use the *Select* button to select that asset. 

5.  In the form's *Configuration* section, you can set the page to use Creole 
    (default), MediaWiki, plain text, or HTML. We recommend that you stick with 
    the Creole format, as it allows for a much cleaner separation of content and 
    code. You can also use the Configuration section to summarize your edit, and 
    specify whether it's a minor edit. 

6.  Click *Publish* to publish the page when you're done editing it. 

As is common with wikis in general, if you link to a page that doesn't exist, 
clicking that link opens the new page form with a note stating that the page 
doesn't exist and that you are creating it. 

+$$$

**Note**: When you create a page by clicking a link to a page that doesn't 
exist, the new page is **not** a child of the current page. The page is created 
at the wiki node's root. From Wiki in Site Administration, you can use the 
page's Move action to assign it a new parent page. Clicking the Move action 
brings up a window that lets you select a new parent for the wiki page. 

$$$

Return to the wiki node view to see a list of the node's top-level pages. If you 
navigate to a page that has child pages, its child pages are listed. In these 
page listings, each page's Actions menu 
(![Actions](../../../../images/icon-actions.png)) lists the following actions 
you can take on the page: 

**Edit**: Opens the page in the page editor.

**Permissions**: Lets you determine which roles can view, update, delete,
subscribe to, or set permissions on the page, and add, update, or delete page
discussions (comments).

**Copy**: Opens a page editor window with all the content from the source wiki
page. You're prompted to specify a new title for it. 

**Move**: Opens a dialog that lets you rename the page or assign the page to
a new parent page within the wiki node. 

**Subscribe (or Unsubscribe)**: Subscribes you to (or unsubscribes you from)
notifications for the wiki page's modifications. 

**Move to Recycle Bin**: Moves the wiki node to the Recycle Bin.

Each wiki page has a check box next to it. When you select a page's check box, 
the Management Bar changes to show an Info icon 
(![Info](../../../../images/icon-information.png)) and Recycle Bin icon 
(![Recycle Bin](../../../../images/icon-trash.png)). To move the selected page 
to the Recycle Bin, click the Recycle Bin icon. To get additional information 
about the page via an info panel, click the Info icon. The info panel provides a 
star icon that you can select to subscribe to the page's modifications. The info 
panel's Details section displays the page's summary, format, version, creation 
and modification dates, number of attachments, and RSS link.

There are several more features in the wiki node view's Management Bar. The 
*Filter and Order* menu orders the pages by title or modification date 
and filters them by page type. The arrows button sorts the pages in 
ascending or descending order. The search bar searches for pages. 

The *View Types* button is next to the Info icon. It lets you choose how to 
display the pages. The View Types button's icon depends on the selected view 
type: 

**List** (![List](../../../../images/icon-view-type-list.png)): Shows the pages
in a list with an icon representing each page. Each page's entry contains the
name of its author, when it was last modified, and its
[workflow](/discover/portal/-/knowledge_base/7-1/using-workflow) status (e.g.,
Approved, Draft, etc.). 

**Table** (![Table](../../../../images/icon-view-type-table.png)): Shows the
same information as the List view type, in a smaller list with no page icon.
Also, the page's information is in columns and includes the revision number. 

![Figure 3: The wiki node's view in site administration has features that help you access and learn information about a wiki node's pages.](../../../../images/wiki-node-view-in-admin.png)
