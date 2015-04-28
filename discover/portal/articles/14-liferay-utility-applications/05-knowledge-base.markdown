# Knowledge Base [](id=knowledge-base)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay's Knowledge Base app provides a means for creating and organizing
articles within a site. The knowledge base is perfect for creating and
organizing information more formally than in a wiki. For example, it can be used
to hierarchically organize articles to display professional product
documentation or to form complete books or guides. It even lets you import
article source files written in Markdown, automatically converting them into
full-fledged Knowledge Base articles. It's easy to set up the knowledge base
with a workflow that requires articles to be approved before they are published.
Additionally, the app lets administrators create article templates, to ensure
that articles using the template follow a common structure and include certain
kinds of information. 

The Knowledge Base app is available for purchase on the [Liferay Marketplace](http://www.liferay.com/marketplace).
On [installing](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps)
the app, its admin portlet is available in Site Administration's Content section
and the app's article portlet, display portlet, search portlet, and section
portlet are available in the Applications menu. 

## Knowledge Base Display Portlet [](id=knowledge-base-display-portlet)

The Knowledge Base app actually consists of four portlets that can be placed on
site pages as well as one that adds a page to Site Administration. The four
portlets that can be placed on a page are Knowledge Base (Display), Knowledge
Base Article, Knowledge Base Search, and Knowledge Base Section. When placed on
a page, the Knowledge Base display portlet presents many of the same options to
an administrator that are available from the Knowledge Base page of Site
Administration.

![Figure 14.26: The Knowledge Base Display portlet displays your recently added articles in a list.](../../images/liferay-collaboration-kb-display-portlet.png)

You can use the four links at the top of the Knowledge Base Display portlet to
control what it displays.

*Knowledge Base Home:* shows you a list of all top level articles.

*Recent Articles:* shows you a list of articles in order from most recent
activity to least recent activity.

*Administrator:* shows you a list of all articles, regardless of which ones are
parents or children of the others.

*My Subscriptions:* shows you a list of articles you are subscribed to.

The *Add Article* button is available from the Knowledge Base Home or
Administrator view of the Knowledge Base Display portlet or from the Articles
tab of the Knowledge Base page of Site Administration. Use this button to create
an article for the knowledge base. When creating an article, you can use the
same WYSIWYG editor you used to create wiki pages. Articles, however, are not
the same as wiki pages; they must be created in HTML, not MediaWiki or Creole.
Click the *Source* button in the editor to view the HTML source of what you've
written or write some HTML yourself.

![Figure 14.27: You can create a new Knowledge Base Article using the WYSIWYG editor.](../../images/kb-admin-new-article.png)

In addition to entering a title and creating content for your article, you can
use the editor to add attachments, add tags, and set permissions. By default,
view permission is granted to the guest role, meaning anyone can view your
article. After you're done using the editor, you can save it as draft and
continue working on it later or you can submit it for publication. Your article
may need to be approved before being published, depending on the workflow
defined for your portal.

You can find the *Permissions* button next to the Add Article button in the
Knowledge Base display portlet or on the Knowledge Base page of Site
Administration. Click this button to define permissions that apply to the
Knowledge Base Display portlet generally, not to particular articles. Here, you
can define which roles can add articles and templates, which are granted
knowledge base administrator privileges, which can change permissions on
articles, which can subscribe to articles, and which can view templates.

![Figure 14.28: Set various permissions for each of your portal's roles in the Knowledge Base Permissions interface.](../../images/liferay-collaboration-kb-permissions.png)

Users may need to be granted access to the knowledge base page of Site
Administration in order to exercise some of the above permissions. For example,
suppose the user role has been granted the *Add Article* and the *View
Templates* permissions. A user will be able to add articles from the Knowledge
Base Display portlet but will need access to the knowledge base page of Site
Administration in order to view templates. Note that the Knowledge Base
Display permissions are distinct from the Knowledge Base Admin portlet. The
display permissions define what a user can do with the Knowledge Base Display
portlet on a page while the admin permissions define what a user can do on the
Knowledge Base page of Site Administration.

## Knowledge Base Page of Site Administration [](id=knowledge-base-page-of-site-administration)

The Knowledge Base page of the Site Administration interface is the primary
place where administrators and authors create, organize, and manage articles. It
has three tabs: one for managing articles, one for managing templates, and the
last one for managing suggestions to articles. The *Articles* tab shows all the
article folders and articles in the knowledge base and lets you perform actions
on them. The *Templates* tab shows all the templates defined in the knowledge
base and lets you perform actions on them. The *Suggestions* tab shows all the
suggestions users have submitted for the articles and lets you act on them in
workflow fashion. 

![Figure 14.29: Administrators can control knowledge base articles and templates from the *Site Administration* &rarr; *Content* page.](../../images/kb-admin-page.png)

The *Add* drop-down lets you add a Basic Article, add an article based on an
available template, add a folder, or import articles from a Zip file. Selecting
*Basic Article* or the name of an available template brings up the  *New
Article* window. When you enter your article's title, a URL-safe version of the
title is added to the tail end of the article's friendly URL in the *Friendly
URL* field. You can edit the fiendly URL.  

You create the article's content using the Content section's WYSIWYG editor.
Knowledge Base articles are created in HTML. Click the *Source* button in the
editor to view the HTML source of what you've written or write some HTML
yourself. 

![Figure 14.27: You can create a new Knowledge Base article using the WYSIWYG editor.](../../images/kb-admin-new-article.png)

In addition to entering a title and creating content for your article, you can
use the editor to add attachments, add tags, specify related assets, and set
permissions. By default, view permission is granted to the guest role, meaning
anyone can view your article. After you're done using the editor, you can save
the article as draft and continue working on it later or you can submit it for
publication. Your article may need to be approved before being published,
depending on the workflow defined for your portal. 

The *Permissions* button is next to the Add drop-down in the Knowledge Base page
of Site Administration. Clicking this link brings up a window that lets you
define permissions that apply to the knowledge base generally, not to particular
articles. Here, you can define which roles can add/delete articles, folders and
templates, which can change general knowledge base permissions, which can
subscribe to articles, and which can view templates and view suggestions. 

The Add drop-down in the Knowledge Base page of Site Administration also has
options for adding a folder and importing articles using a Zip file. The
interface for adding folders is straightforward, letting you enter a name,
description, and permissions for the folder. Importing articles is a powerful
option that is covered later in this section. 

As you add folders and articles to your knowledge base, you'll notice the
actions you can perform on them. Administrators can perform the following
actions on a folder: 

*Edit:* lets you  change the folder's name and description.

*Move:* lets you relocate the folder under a new parent folder. 

*Delete:* lets you remove the folder and its contents from the knowledge base. 

*Permissions:* lets you grant or revoke a role's ability to add an article to
the folder, add a subfolder to the folder, delete the folder, move the folder,
set permissions on the folder, edit (update) the folder, and view the folder. 

Articles can be created or moved into in several places in the knowledge base.
They can reside in the root of the knowledge base, listed under the folder list,
they can reside in the folders, and they can be children of other articles
(parent articles). Regardless of where an article resides, administrators can
perform the following actions on an article: 

*View:* displays the article. 

*Edit:* lets you change the article's title and content. It also lets you manage
its attachments, categorization, and its related assets. 

*Add Child Article:* lets you add a new child article to the article. 

*Permissions:* lets you configure the permissions of roles with respect to the
article. 

*Subscribe:* lets you to choose to be notified of any updates to the article.
Once you've subscribed to an article, the *Unsubscribe* action is made available
giving you the option to unsubscribe from the article's notifications. 

*Move:* lets you move the article to a different folder or change an article's
position in the hierarchy by choosing a new parent article for it.

*Delete:* lets you remove the article from the knowledge base. 

The Templates tab of the Knowledge Base page of Site Administration allows
administrators to create templates to facilitate creating articles. A
template basically functions like a starting point for the creation of certain
types of articles. To create a new template, you can click on the *Add Template*
button in this tab. 

![Figure 14.30: As an administrator, you can add a new template to your knowledge base by navigating to *Site Administration* &rarr; *Content* &rarr; *Knowledge Base (Admin)*.](../../images/kb-admin-new-template.png)

Navigate back to the Templates tab of the Knowledge Base page of Site
Administration. You can perform the following actions on a template:

*View:* displays the template. From here, you can print the template, use it to
create an article, edit the template, modify the permissions on the template, or
delete it. 

*Edit:* lets you to change the template's title and content.

*Permissions:* lets you to configure the permissions on the template. You can
choose roles to have permission to update, view, delete or change the
permissions on the template.

*Delete:* lets you remove the template from the knowledge base.

The Suggestions tab of the Knowledge Base page of Site Administration lets
your view user feedback on articles and lets you mark progress on addressing the
feedback. 

![Figure 14.31: The Suggestions tag in the Knowledge Base page of Site Administration helps you monitor article feedback and mark progress towards addressing that feedback.](../../images/kb-admin-suggestions.png)

Each Suggestion entry provides the link to the article, the user's feedback, the
user's name and a link to the user's home page, the time the feedback was given,
and a status indicating your progress on the suggestion. You can move the entry
between New, In Progress, and Resolved states. 

## Knowledge Base Article Portlet [](id=knowledge-base-article-portlet)

The Knowledge Base Article portlet can be placed on a page to display an entire
article. When you first place this portlet on a page, it displays the message
*Please configure this portlet to make it visible to all users*. This message is
a link to the configuration dialog box for the portlet. Click *Select Article*
to choose an article to display. Pick an article and then click *Save*. When
your page refreshes it will display the article in the portlet.

![Figure 14.31: Display articles using the Knowledge Base Article portlet.](../../images/liferay-collaboration-kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the
article it displays. There are also links at the top of the portlet users can
use to subscribe to an RSS feed of the knowledge base, subscribe to the article,
view the history of the article, or print the article.

## Knowledge Base Section Portlet [](id=knowledge-base-section-portlet)

The Knowledge Base Section portlet allows administrators to selectively show
articles associated with a specific section. For example, a news site might have
a *World* section, a *Politics* section, a *Business* section and an
*Entertainment* section. In order to use sections, you need to set the
`admin.kb.article.sections` property in your knowledge base portlet's
`portlet.properties` file and redeploy the portlet. You can find the
`portlet.properties` file in the knowledge base portlet's source directory.
Updating the one in your server's directory won't work. Use comma delimited
section names to set the property, like
`admin.kb.article.sections=World,Politics,Business,Entertainment`, for example.

Once you have defined some sections in your knowledge base's
`portlet.properties` file, your users will see a multi-select box in the Add
Article and Edit Article screens that allows them to select which section an
article belongs to. You can add any number of Knowledge Base section portlets to
a page and you can configure each portlet to display articles from any number of
sections.

![Figure 14.32: Here's an image of Knowledge Base Section portlets being displayed on a page.](../../images/liferay-collaboration-kb-section-portlet.png)

The Knowledge Base section portlet has some additional configurations that allow
an administrator to select a display style (title or abstract), an article
window state (maximized or normal), how to order the articles, how many articles
to display per page, and whether or not to show pagination.

## Knowledge Base Navigation [](id=knowledge-base-navigation)

Wikis often have deeply nested articles that can be hard to find by browsing.
Liferay's knowledge base's ability to selectively display articles makes it
easier to browse than a Wiki. The knowledge base also features some other aids
to navigation. The Knowledge Base Search portlet allows you to search for
articles in the knowledge base. This portlet presents the search results to you
in order from most relevant to least relevant.

![Figure 14.33: The Knowledge Base Search portlet helps you search the knowledge base for keywords.](../../images/liferay-collaboration-kb-search-portlet.png)

You can also use the Categories Navigation portlet in conjunction with the
Knowledge Base Display portlet. When both of these portlets are placed on a page
you can select a topic in the Categories Navigation portlet and the Knowledge
Base Display portlet will show all of the articles that match the topic. You can
create topics from the Categories page of *Site Administration* &rarr;
*Content*.

![Figure 14.34: Use the Categories Navigation portlet to search articles that match a specific topic.](../../images/liferay-collaboration-kb-catnavandkbdisplay-portlets.png)

You can select topics for articles when you are creating or editing them.
