# Knowledge Base [](id=knowledge-base)

Liferay's Knowledge Base portlet provides a means for creating and organizing
articles within a site. The knowledge base is perfect for creating and
organizing information more formally than in a wiki. For example, it can be used
to organize and display professional product documentation. It's easy to set up
the knowledge base with a workflow that requires articles to be approved before
they are published. Additionally, it allows administrators to create article
templates. Templates can be used to insure certain kinds of articles possess a
common structure and include certain kinds of information. Knowledge base
articles can be categorized to make them easy to find. They can also be
organized hierarchically to form complete books or guides. The Knowledge Base
portlet is available as an app from Liferay Marketplace. Please see chapter 2
for installation instructions.

## Knowledge Base Display Portlet [](id=knowledge-base-display-portlet)

The Knowledge Base app actually consists of four portlets that can be placed on
site pages as well as one that adds a page to the Control Panel. The four
portlets that can be placed on a page are Knowledge Base (Display), Knowledge
Base Search, Knowledge Base Article and Knowledge Base Section. When placed on a
page, the Knowledge Base display portlet presents many of the same options to an
administrator that are available from the Knowledge Base page of the control
panel.

![Figure 12.32: Knowledge Base Display Portlet](../../images/liferay-collaboration-kb-display-portlet.png)

You can use the four links at the top of the Knowledge Base display portlet to
control what it displays.

*Knowledge Base Home:* shows you a list of all top level articles.

*Recent Articles:* shows you a list of articles in order from most recent
activity to least recent activity.

*Administrator:* shows you a list of all articles, regardless of which ones are
parents or children of the others.

*My Subscriptions:* shows you a list of articles you are subscribed to.

The *Add Article* button is available from the Knowledge Base Home or
Administrator view of the Knowledge Base display portlet or from the Articles
tab of the Knowledge Base page of the Control Panel. Use this button to create
an article for the knowledge base. When creating an article, you can use the
same WYSIWYG editor you used to create wiki pages. Articles, however, are not
the same as wiki pages: they must be created in HTML, not MediaWiki or Creole.
Click the *Source* button in the editor to view the HTML source of what you've
written or write some HTML yourself.

![Figure 12.33: New Knowledge Base
Article](../../images/liferay-collaboration-kb-new-article.png)

In addition to entering a title and creating content for your article, you can
use the editor to add attachments, add tags and set permissions. By default,
view permission is granted to the guest role, meaning anyone can view your
article. After you're done using the editor, you can save it as draft and
continue working on it later or you can submit it for publication. Your article
may need to be approved before being published, depending on the workflow
defined for your portal.

You can find the *Permissions* button next to the Add Article button in the
Knowledge Base display portlet or on the Knowledge Base page of the control
panel. Click this button to define permissions that apply to the Knowledge Base
display portlet generally, not to particular articles. Here, you can define
which roles can add articles and templates, which are granted knowledge base
administrator privileges, which can change permissions on articles, which can
subscribe to articles and which can view templates.

![Figure 12.34: Knowledge Base
Permissions](../../images/liferay-collaboration-kb-permissions.png)

Users may need to be granted access to the knowledge base page of the control
panel in order to exercise some of the above permissions. For example, suppose
the user role has been granted the Add Article and the View Templates
permissions. A user will be able to add articles from the knowledge base display
portlet but will need access to the knowledge base page of the Control Panel in
order to view templates. Note that the Knowledge Base (Display) permissions are
distinct from the Knowledge Base (Admin) portlet. The display permissions define
what a user can do with the Knowledge Base display portlet on a page while the
admin permissions define what a user can do on the Knowledge Base page of the
Control Panel.

## Knowledge Base Page of the Control Panel [](id=knowledge-base-page-of-the-control-panel)

The Knowledge Base page of the Control Panel has two tabs: one for articles and
one for templates. The articles tab shows all the articles in the knowledge base
and lets you perform actions on them. The templates tab shows all the templates
defined in the knowledge base and lets you perform actions on them.

![Figure 12.35: Knowledge Base Control Panel Page](../../images/liferay-collaboration-kb-control-panel.png)

Administrators can perform the following actions on an article:

*View:* displays an article. From here, you can add a child article, edit the
article, change its permissions, move it or delete it.

*Edit:* allows you to change the title and content of an article as well as add
attachments, select topics and add tags.

*Permissions:* lets you configure the permissions on a specific article.

*Subscribe:* lets you to choose to be notified of any updates to a particular
article.

*Move:* lets you change an article's position in the hierarchy by choosing a new
parent article for it.

*Delete:* lets you remove an article from the knowledge base.

These actions are similar to the ones that can be performed from the
Administrator view of the Knowledge Base display portlet. However, the Knowledge
Base display portlet is intended to be placed on a page for the end user so an
additional action is available: *RSS* is a link to an RSS feed of an article.
Also, the *View* action is only available from the Control Panel since the
Knowledge Base Article portlet can be used to display an article on a page.

The templates tab of the Knowledge Base page of the Control Panel allows
administrators to create templates to facilitate the creation of articles. A
template basically functions like a starting point for the creation of certain
types of articles. Click the *Add Template* button on the Templates tab of the
Knowledge Base page of the Control Panel to create a new template.

![Figure 12.36: Adding a New Template From the Control Panel](../../images/liferay-collaboration-kb-new-template.png)

Navigate back to the templates tab of the Knowledge Base page of the control
panel. You can perform the following actions on a template:

*View:* displays a template. From here, you can use the template to create an
article, edit the template, modify the permissions on the template or delete it.

*Edit:* allows you to change the title and content of a template.

*Permissions:* allows you to configure the permissions on a template. You can
choose roles to have permission to update, view, delete or change the
permissions on templates.

*Delete:* lets you remove a template from the knowledge base.

![Figure 12.37: Knowledge Base Section Portlets](../../images/liferay-collaboration-kb-section-portlet.png)

To use a template to create a new article, you have to view the template and
then click *Use this Template*. This brings you to the New Article editor with
the contents of the template copied for you.

## Knowledge Base Article Portlet [](id=knowledge-base-article-portlet)

The Knowledge Base Article portlet can be placed on a page to display an entire
article. When you first place this portlet on a page it displays the message
*Please configure this portlet to make it visible to all users*. This message is
a link to the configuration dialog box for the portlet. Click *Select Article*
to choose an article to display. Pick an article and then click *Save*. When
your page refreshes it will display the article in the portlet.

![Figure 12.38: Knowledge Base Article Portlet](../../images/liferay-collaboration-kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the
article it displays. There are also links at the top of the portlet users can
use to subscribe to an RSS feed of the knowledge base, subscribe to the article,
view the history of the article or print the article.

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

![Figure 12.39: Knowledge Base Section Portlets](../../images/liferay-collaboration-kb-section-portlet.png)

The Knowledge Base section portlet has some additional configurations that allow
an administrator to select a display style (title or abstract), an article
window state (maximized or normal), how to order the articles, how many articles
to display per page and whether or not to show pagination.

## Knowledge Base Navigation [](id=knowledge-base-navigation)

Wikis often have deeply nested articles that can be hard to find by browsing.
Liferay's knowledge base's ability to selectively display articles makes it
easier to browse than a Wiki. The knowledge base also features some other aids
to navigation. The Knowledge Base Search portlet allows you to search for
articles in the knowledge base. This portlet presents the search results to you
in order from most relevant to least relevant.

![Figure 12.40: Knowledge Base Search Portlet](../../images/liferay-collaboration-kb-search-portlet.png)

You can also use the Categories Navigation portlet in conjunction with the
Knowledge Base display portlet. When both of these portlets are placed on a page
you can select a topic in the Categories Navigation portlet and the Knowledge
Base display portlet will show all of the articles that match the topic. You can
create topics from the Categories page of the Control Panel.

![Figure 12.41: Knowledge Base Category Navigation Portlet](../../images/liferay-collaboration-kb-catnavandkbdisplay-portlets.png)

You can select topics for articles when you are creating or editing them.
