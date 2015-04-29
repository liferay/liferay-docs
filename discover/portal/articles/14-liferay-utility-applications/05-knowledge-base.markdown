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
articles and article folders in the knowledge base, and it lets you perform
actions on them. The *Templates* tab shows all the templates defined in the
knowledge base and lets you perform actions on them. The *Suggestions* tab shows
all the suggestions users have submitted for the articles and lets you act on
them in workflow fashion. 

![Figure 14.29: Administrators can manage knowledge base articles and templates from the *Site Administration* &rarr; *Content* page.](../../images/kb-admin-page.png)

The *Add* drop-down lets you add a Basic Article, add an article based on an
available template, add a folder, or import articles from a Zip file. Selecting
*Basic Article* or the name of an available template brings up the *New Article*
window. From this interface as you enter your article's title, a URL-safe
version of the title is added to the tail end of the article's friendly URL--see
the value in the *Friendly URL* field. You can, of course, edit the friendly URL.  

You create the article's content using the Content section's WYSIWYG editor.
Knowledge Base articles are created in HTML. Click the *Source* button in the
editor to view the HTML source based on what you've written using the WYSIWYG
editor or your can write some HTML yourself. 

![Figure 14.27: You can create a new Knowledge Base article using the WYSIWYG editor.](../../images/kb-admin-new-article.png)

In addition to entering your article's title and creating its content, you can
use the interface to add attachments, add tags, specify related assets, and set
permissions. By default, view permission is granted to the guest role, meaning
anyone can view your article. After you're done using the editor, you can save
the article as a draft and continue working on it later, or you can submit it
for publication. Your article may need to be approved before being published,
depending on the workflow defined for your portal. 

The *Permissions* button is next to the Add drop-down in the Knowledge Base page
of Site Administration. Clicking this button brings up a window that lets you
define permissions that apply to the knowledge base generally, not to particular
articles. Here, you can define which roles can add/delete articles, folders and
templates, which roles can change general knowledge base permissions, which
roles can subscribe to articles, and which roles can view templates and
suggestions. 

The Add drop-down in the Knowledge Base page of Site Administration also has
options for adding a folder and importing articles using a Zip file. The
interface for adding folders is straightforward, letting you enter a name,
description, and permissions for the folder. Importing articles is a powerful
option covered at the end of this section. 

As you add folders and articles to your knowledge base, you'll notice the
actions you can perform on them. Administrators can perform the following
actions on a folder: 

*Edit:* lets you  change the folder's name and description.

*Move:* lets you relocate the folder under a new parent folder. 

*Delete:* lets you remove from the knowledge base the folder and its articles. 

*Permissions:* lets you grant or revoke a role's ability to add an article to
the folder, add a sub-folder to the folder, delete the folder, move the folder,
set permissions on the folder, edit (update) the folder, and view the folder. 

Articles can be created or moved into in several places in the knowledge base.
They can reside in the root of the knowledge base, they can reside in the
folders, and they can be children of other articles (parent articles).
Regardless of where an article resides, administrators can perform the following
actions on an article: 

*View:* displays the article. 

*Edit:* lets you change the article's title and content. It also lets you manage
its attachments, categorization, and its related assets. 

*Add Child Article:* lets you add a new child article to the article. 

*Permissions:* lets you configure the permissions of roles with respect to the
article. 

*Subscribe:* lets you to choose to be notified of any updates to the article.
Once you've subscribed to an article, the *Unsubscribe* action is made available
giving you the option of unsubscribing from the article's notifications. 

*Move:* lets you move the article to a different folder or change an article's
position in the hierarchy by choosing a new parent article for it.

*Delete:* lets you remove the article from the knowledge base. 

The *Templates* tab of the Knowledge Base page of Site Administration allows
administrators to create templates to facilitate creating articles. A
template basically functions like a starting point for the creation of certain
types of articles. To create a new template, you can click on the *Add Template*
button in this tab. 

![Figure 14.30: As an administrator, you can add a new template to your knowledge base by navigating to *Site Administration* &rarr; *Content* &rarr; *Knowledge Base*.](../../images/kb-admin-new-template.png)

Back in the Templates tab of the Knowledge Base page of Site Administration, you
can perform the following actions on a template:

*View:* displays the template. From here, you can print the template, use it to
create an article, edit the template, modify the permissions on the template, or
delete it. 

*Edit:* lets you to change the template's title and content.

*Permissions:* lets you to configure the permissions on the template. You can
choose whether a role is permitted to update, view, or delete the template, or
change the permissions on the template.

*Delete:* lets you remove the template from the knowledge base.

The *Suggestions* tab of the Knowledge Base page of Site Administration lets
you view user feedback on articles and lets you mark progress on addressing the
feedback. 

![Figure 14.31: The Suggestions tab in the Knowledge Base page of Site Administration helps you monitor article feedback and mark progress towards addressing feedback.](../../images/kb-admin-suggestions.png)

Each Suggestion entry provides the link to the associated article, the user's
feedback, the user's name linked to the user's home page, the time the feedback
was given, and a status indicating progress on addressing the suggestion. You
can move the entry between *New*, *In Progress*, and *Resolved* states. 

Circling back to the *Add* drop-down in the *Articles* tab, the Import option
lets you import a Zip file full of articles and their images directly into your
knowledge base. All you need to do is write your articles in a text format
called Markdown, Zip up your Markdown article source files and their
corresponding images, and import the Zip file into the knowledge base. The
Knowledge Base app's importer automatically converts each source file's Markdown
text to HTML, applying the HTML to the resulting Knowledge Base article. Any
image files referenced in an article source file are imported as attachments to
the article. The [Markdown](http://fletcher.github.io/MultiMarkdown-4/) syntax
is easy to write, easy to read, and it supports plenty of markup features to
produce high quality articles. Here are the simple requirements for the article
source files: 

- All article source files much end in the portlet's required suffixes (default
acceptable suffixes are `.markdown` or `.md`).
- Each article's Markdown file must start with a top level header (e.g., `# Some
Heading`). 
- Each header must have an associated ID to use in the article's friendly URL
title and to use as anchors for the article's sub headers. Here's an example of
a top level header that correctly specifies an ID: `# Some Heading  [](id=some-heading)`

Below is Markdown source text for a simple article: 

    # The Moons of Mars [](id=the-moons-of-mars)

    As you look up from your chaise lounge, you're sure to see our neighboring
    planet Mars. Did you know that Mars has two moons? You might have to break out a
    pair of binoculars to see them.

    ![Mars and its moons.](./images/kb-display-mars-moons.png)

    Its two moons ar aptly named after the two sons of mythical Roman God Mars.
    Their names are Phobos and Diemos.

In the first line of the above example content, notice the friendly URL title
specified via the header's ID assignment `id=the-moons-of-mars`. On import, the
ID value becomes the URL title of the resulting knowledge base article. Also
note that the referenced image file `kb-display-mars-moons.png` resides in a
folder called `images`. 

When you're finished editing Markdown files that you want to import, you can Zip
them up with their accompanying image files. 

**Importer Zip file requirements:**

- Each Zip file must end in the suffix `.zip`.
- Each Zip file must contain at least one Markdown source file.
- All referenced image files must be in a folder named `images`, in the Zip
file's root. 
- Image files must use one of the portlet's designated suffixes (the default
suffixes supported are `.bmp`,`.gif`,`.jpeg`,`.jpg`, and `.png`).

**Example basic Zip file structure:**

- `folder-1/`
    - `some-article.markdown`
    - `another-article.markdown`
- `images/`
    - `some-image.png`
    - `another-image.jpeg`

Markdown files can be specified anywhere in the Zip file's directory structure.
They can be nested in as many different folders as you like. 

In addition to article Markdown files and their images you can pass to the
importer a base source URL property that specifies your source file's online
reposiotry location. The importer appends each Markdown file's path from the Zip
file to the base source URL to construct a URL to the article's repository
source location. Each imported article's source URL is set to `[base
URL]/[article filename]`. Here's an example base source URL property:

    base.source.url=https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

The source URL constructed form the above base URL and the example file
`folder-1/some-article.markdown` would be this:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

To use this feature, you must specify the portlet property setting
`knowledge.base.source.url.enabled=false`. You specify the base source URL in a
file called `.METADATA` in the Zip file's root folder and the importer applies
that base source URL to all of the Zip file's resulting articles. 

The Zip file importer also supports importing a two level hierarchy of articles
and assigning articles a priority based on numerical file prefixes. Here's a
more advanced listing that shows a Zip file structure that leverages these
features:

- `01-intro/`
    - `00-winter-excursions-intro.markdown`
    - `01-star-dust-snow-shoeing.markdown`
    - `02-lunar-alpine.markdown`

- `02-intro/`
    - `00-summer-excursions-intro.markdown`
    - `01-lunar-rock-scrambling.markdown`
    - `02-extra-terrestrial-mountain-biking.markdown`

- `images/`
    - `some-image.png`
    - `another-image.jpeg`

- `.METADATA`

To designate an article to be the parent of all other articles in the same
source folder, end the article source file's name with `intro.markdown`. This
creates a two-level parent-child hierarchy. Using this convention, the above Zip
file specifies `00-winter-excursions-intro.markdown` as the parent of its
neighboring Markdown files: `01-star-dust-snow-shoeing.markdown` and
`02-lunar-alpine.markdown`. 

To designate an article's priority via it's source file, add a non-zero prefix to
its file name. Example, the priorities for articles created from files named
`01-file.markdown` and `02-file.markdown` would be `1.0` and `2.0`. In the
importer's user interface, make sure to keep selected the import checkbox *Apply
numerical prefixes of article files as priorities.* If a file doesn't have such
a prefix, its article gets the next increment priority (i.e., that highest
currently assigned priority, plus one). The Zip file's articles are processed in
file order. 

Also in the above example Zip file listing, notice that the `*intro.markdown`
files have the prefix `00`. You can optionally start your intro files with `00`
so that they're conveniently listed at the top of the folder's file listing in
your local file browser. The real trick is that the importer uses the prefix of
such a file's folder as its knowledge base article priority. Here's the
underlying logic: if you use a prefix `00` for a file, the importer assigns the
resulting article a priority of `1.0`. A top level intro file, however, gets
special treatment: if its prefix is less than `1`, the importer checks to see if
the intro file's folder(s) have a prefix of `1` or greater. It assigns, as the
intro article's priority, the first folder prefix it finds that is `1` or
greater. This folder prefix convention enables you to specify priorities for
top-level (non-child) articles in your hierarchy. 

That wraps it up for working with the Knowledge Base from within Site
Administration. The Knowledge Base Article portlet is up next. 

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
