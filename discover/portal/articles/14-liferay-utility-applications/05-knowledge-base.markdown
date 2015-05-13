# Knowledge Base [](id=knowledge-base)

Liferay's Knowledge Base application provides a way to create and organize
articles in a site. For example, it can be used to display professional product
documentation or form complete books or guides. It even lets you import
article source files written in Markdown. It's
[workflow-enabled](/discover/portal/-/knowledge_base/6-2/configuring-assets-to-use-workflow-process-definitions), 
so you can require articles to be approved before they are published.
Additionally, the Knowledge Base application lets administrators create article
templates that help users follow a common structure and include consistent kinds
of information. 

Here's what the Knowledge Base can do:

- Navigation is built into the Knowledge Base Display 
- Has a suggestions interface for article feedback
- Stores articles in folders
- Contains metadata fields for friendly URL, source URL, categorization, and related assets
- Button ![*Edit on GitHub*](../../images/kb-display-edit-on-github-button.png)
can be enabled to take readers to an article's online source repository location
- Imports article Markdown source files to create and update articles

The Knowledge Base application is available for purchase on the [Liferay Marketplace](http://www.liferay.com/marketplace).
Once you've [installed](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps)
the app, the Knowledge Base portlet is available in the *Site Administration*
&rarr; *Content* section and the following portlets are available at
*Applications* &rarr; *Knowledge Base*: Knowledge Base Display, Knowledge Base
Article, Knowledge Base Search, and Knowledge Base Section. 

## Knowledge Base Portlet [](id=knowledge-base-portlet)

To navigate to the Knowledge Base portlet (admin portlet) page, go to *Site
Administration* &rarr; *Content* &rarr; *Knowledge Base*. The admin portlet has
everything administrators and authors need to create, update, and manage
articles. It has three tabs: one for managing articles, one for managing
templates, and one for managing article suggestions. The Articles tab is for
managing all the articles and article folders in the Knowledge Base. The
Templates tab is for managing all the article templates. The Suggestions tab is
for managing user-submitted feedback. 

![Figure 14.26: You can manage Knowledge Base articles, folders, templates, and suggestions from the *Knowledge Base* portlet.](../../images/kb-admin-page.png)

The Add selector shows you all the options for adding articles. You can add a
folder, a Basic Article, an article based on a template, or you can import
articles from a .zip file. Selecting *Basic Article* (or the name of an
available template) brings up the New Article window. As you enter your
article's title in this window, a URL-safe version of the title is added to the
end of the article's friendly URL. The value is displayed and editable in the
*Friendly URL* field. 

You create the article's content using the WYSIWYG editor. Knowledge Base
articles are created in HTML. Click the *Source* button in the editor to view
the HTML source, or use this view to write HTML yourself. 

![Figure 14.27: You can create and modify a Knowledge Base article's content using the WYSIWYG editor.](../../images/kb-admin-new-article.png)

You can also add attachments and tags, specify related assets, and set
permissions. By default, View permission is granted to the Guest role, meaning
anyone can view your article. At any time, you can save the article as a draft
to continue working on it later, or you can submit it for publication. If
workflow is enabled for the Knowledge Base, your article must be approved before
it is published. 

The Permissions button is next to the Add selector in the Admin portlet.
Clicking this button lets you define permissions for the Knowledge Base
application: the roles that can add/delete articles, folders, and templates, the
roles that can change general knowledge base permissions, the roles that can
subscribe to articles, and the roles that can view templates and suggestions. 

+$$$

**Note:** To be able to access the Knowledge Base Admin portlet, a role must
have the permission *Knowledge Base* &rarr; *Access in Site Administration*. To
be able to add or act on articles, folders, or suggestions, the site
administrator must grant the appropriate permissions using the Permissions
window in the Knowledge Base Admin portlet.

$$$

### Adding Folders and Articles [](id=adding-folders-and-articles)

The interface for adding folders is straightforward. You can enter a name,
description, and permissions for the folder. 

As you add folders and articles to your Knowledge Base, notice the
actions you can perform on them: 

*Edit:* change the folder's name and description.

*Move:* relocate the folder under a new parent folder. 

*Delete:* remove the folder and its articles from the knowledge base. 

*Permissions:* grant or revoke the following permissions: add an article to the
folder, add a sub-folder to the folder, delete the folder, move the folder, set
permissions on the folder, edit (update) the folder, and view the folder. 

Articles can be created or moved to several places in the Knowledge Base: to the
root of the Knowledge Base, to folders, and they can become children of other
articles.

Here are the actions you can perform on an article:

*View:* display the article. 

*Edit:* change the article's title and content and manage its attachments,
categorization, and its related assets. 

*Add Child Article:* add a new child article to the article. 

*Permissions:* configure the permissions on the article. 

*Subscribe:* choose to be notified of any updates to the article. Once you've
subscribed to an article, the *Unsubscribe* action appears, letting you
unsubscribe from the article's notifications. 

*Move:* move the article to a different folder or change an article's position
in the hierarchy by choosing a new parent article for it.

*Delete:* remove the article from the Knowledge Base. 

You can also assign new *priority* values to articles. When many articles are
in the Knowledge Base Display portlet, navigation arranges them 
in ascending priority. Priority 1 is the highest priority; think "Priority 1
distress call!" The higher an article's priority, the higher it is shown in the
navigation. To assign new priorities, select the checkbox for each article you
want to re-prioritize, enter a new priority value, and select *Actions* &rarr;
*Save*.

+$$$

**Note:** In the *Articles* tab, the *Actions* drop-down is only displayed when
one or more article checkboxes are selected. The Actions drop-down lets you save
changes (i.e., priority values) to selected articles or delete selected articles
from the knowledge base. 

$$$

### Managing Templates [](id=managing-templates)

The Admin portlet's Templates tab is for managing templates. Templates are
starting points for articles. You can create templates with default headers
or other content to give your articles a starting outline users can work from.
Templates help foster consistent formatting and content organization for
articles. To create a new template, click the *Add Template* button in the 
*Templates* tab. 

![Figure 14.28: As an administrator, you can add a new template to your knowledge base from the *Knowledge Base* portlet.](../../images/kb-admin-new-template.png)

From the *Templates* tab, you can perform the following actions on a template:

*View:* display the template. From here, you can print the template, use it to
create an article, edit the template, modify the permissions on the template, or
delete it. 

*Edit:* change the template's title and content.

*Permissions:* configure the permissions on the template. You can choose whether
a role can change permissions, update, view, or delete the template. 

*Delete:* remove the template from the Knowledge Base.

### Responding to User Feedback [](id=responding-to-user-feedback)

The Admin portlet's *Suggestions* tab shows user feedback and lets you mark
progress on addressing the feedback. 

![Figure 14.29: The Suggestions tab in the Knowledge Base portlet helps you monitor article feedback and mark progress in addressing feedback.](../../images/kb-admin-suggestions.png)

Each Suggestion entry provides the link to the associated article, the user's
feedback, the user's name linked to the user's home page, the feedback's
time stamp, and the progress on addressing the suggestion. You can move the
entry between *New*, *In Progress*, and *Resolved* states. 

+$$$

**Note:** To view article suggestions, the role must be granted the *Knowledge
Base* &rarr; *Knowledge Base: View Suggestions* permission. 

To move suggestions between the *New*, *In Progress*, and *Resolved* states, the
role must be granted the *Knowledge Base* &rarr; *Knowledge Base Article:
Update* permission. Users assigned to that role can also view and update the
state of article suggestions from any of the other Knowledge Base application's
portlets. 

$$$

So far, you've learned how to create, edit, and organize articles. You've also
seen how the Suggestions feature lets you and your users collaborate on your
Knowledge Base content. The next topic for discussion is the Knowledge Base
application's portlets that display articles, their navigation, how to aggregate
articles for viewing, and finding articles. The Markdown source file importer
[section](/discover/portal/-/knowledge_base/6-2/knowledge-base#importing-articles-from-markown-source-files)
comes after that. 

## Knowledge Base Display Portlet [](id=knowledge-base-display-portlet)

The Knowledge Base Display portlet is how you publish articles. You can access
the portlet's Configuration menu by clicking the gear icon in the top right
corner of the portlet and selecting *Configuration*. In the configuration dialog
box's *Setup* tab, there's a *General* sub-tab that has an *Article Folder*
field with a *Select* button. Clicking the *Select* button lets you choose an
existing article or folder of articles to display in the portlet. If you choose
a folder of articles to display and click *Save*, the navigation in the left
side of the portlet displays links to all of the folder's articles, and the
viewing area in the right side of the portlet displays the folder's leading
article (the "priority one" article). 

![Figure 14.30: The Knowledge Base Display portlet's navigation and viewing area facilitates viewing article hierarchies.](../../images/kb-display.png)

The display portlet can display hierarchies of articles. Clicking an article's
link in the navigation displays the article in the view area. Clicking a link
to an article that has child articles expands the navigation tree to show
links to the child articles. The expanded nodes naturally collapse when you
click on a different top level article. Below the article content are links to
navigate to the next or previous article. The navigation reacts as you
would expect, highlighting the link of the article you're viewing, and the tree
nodes expand and collapse appropriately. 

Links at the top of the portlet let users subscribe to an RSS feed of the
Knowledge Base, subscribe to the current article, view its history, or print it.

If your portal administrator enables the Knowledge Base application's source URL
feature and an article has an assigned source URL, an ![*Edit on GitHub*](../../images/kb-display-edit-on-github-button.png) button appears to the
right of the article's title; it gives users access to the article's online
source location. This feature can be used to encourage users to contribute fixes
or improvements to articles. If you're interested in using this feature, you can
direct your portal administrator to follow instructions in [Advanced Configuration](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration). 

Below the article's content is the rating interface, showing thumbs up and
thumbs down icons. Users can also click the link *Do you have suggestions?* and
submit suggestions or comments for the article. Administrators can view the
suggestions and mark progress using statuses *In Progress* and *Resolved*.

Administrators can perform the following actions on articles:

*Edit:* change the article's title and content, or manage its attachments,
categorization, and its related assets. 

*Add Child Article:* add a new child article to the article. 

*Move:* relocate the article to a different folder or change the article's
position in the hierarchy by choosing a new parent article for it.

*Delete:* remove the article from the Knowledge Base. 

The display portlet provides common Liferay application [configuration](/discover/portal/-/knowledge_base/6-2/configuring-liferay-applications)
options, including ones for look and feel, export/import, permissions, scope,
and sharing.

As an administrator, say that you've used folders to aggregate similar articles,
and you want to provide an easy way for users to switch between these sets of
articles. The content folder feature lets you add a selector to the top of
the display portlet's navigation that lets users switch between sets of
articles. Here's what you do to set up content folders:

1.  In the Knowledge Base Admin portlet, add a folder to hold sub-folders. Then
    add to this folder the sub-folders that will hold articles. These sub-folders
    are the *content folders*. 
2.  Add articles to each content folders. 
3.  Click the Knowledge Base Display portlet's gear icon.  In *Setup* &rarr;
    *General*, select the top-level folder (the parent of the content folders) and
    click *Save*. 

A content selector appears at the top of the Knowledge Base Display
portlet's navigation. Its values reflect the names of your content folders.

![Figure 14.31: The Knowledge Base Display portlet's content folder feature lets users switch between different sets of articles.](../../images/kb-display-root-context-selector.png)

**Example Content Folder and Article Structure:**

- Lunar Rover (folder)
    - SE (content folder)
        - Congratulations on Owning Your New Lunar Rover! (article)
        - Driving and Operation (article)
        - Doors, Windows, and Cup Holders (article)
        - Vehicle Care (article)
    - LTE (content folder)
        - Congratulations on Owning Your New Lunar Rover! (article)
        - (more articles ...)
    - GT Extreme (content folder)
        - Congratulations on Owning Your New Lunar Rover! (article)
        - (more articles ...)

You can also add a common prefix to the content names shown in the selector. To
enter a common prefix, bring up the display portlet's Configuration dialog box,
navigate to the *Setup* &rarr; *Display Settings* tab, enter the prefix into the
*Content Root Prefix* field, and click *Save*. For the example folder and article
structure listed above, you could apply a common prefix *Lunar Rover* to produce
content selector values as shown in Figure 14.13. You can specify whatever value
you like as a root content prefix or you can leave it blank. 

This is how the Knowledge Base Display portlet publishes articles. Next you'll
learn how to use the Knowledge Base Article portlet to show articles
individually. 

## Knowledge Base Article Portlet [](id=knowledge-base-article-portlet)

The Knowledge Base Article portlet can be placed on a page to show a single
article's content. The Article portlet even shows abstracts of an 
article's children. You can add any number of article portlets to a page and
each one can display a different article. When you first place this portlet on a
page, it shows the link *Please configure this portlet to make it visible
to all users*. Click the link and click *Select Article* to choose an article to
display. Pick an article and then click *Save*. When your page refreshes, it
displays the article in the portlet.

![Figure 14.32: The Knowledge Base Article portlet is great at individually displaying articles.](../../images/kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the
article it displays. Links at the top of the portlet let users subscribe to an
RSS feed of the Knowledge Base, subscribe to the current article, view its
history, or print it.

## Knowledge Base Section Portlet [](id=knowledge-base-section-portlet)

The Knowledge Base Section portlet lets you selectively show articles associated
with a specific topic (section). For example, a news site might have a *World*
section, a *Politics* section, a *Business* section and an *Entertainment*
section. Multi-select boxes in the Add Article and Edit Article screens let you
define section(s) articles belong to. You can add any number of section portlets
to a page and each one can display articles from any number of sections. To use
sections, a portal administrator must configure the feature in the Knowledge Base
application's portlet properties. Once the feature is enabled, he or she must
specify in the properties file any section names you want to use. The 
[Advanced Configuration](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration)
section explains how to configure the sections feature in the portlet
properties file.

![Figure 14.33: Here's an image of Knowledge Base Section portlets being displayed on a page.](../../images/kb-section-portlets.png)

The Knowledge Base section portlet can be configured with different display
styles (title or abstract). Additionally you can define an article window state
(maximized or normal), how to order the articles, how many articles to display
per page, and whether or not to show pagination.

## Knowledge Base Search [](id=knowledge-base-search)

Wikis often have deeply nested articles that can be hard to find by browsing.
The Knowledge Base's ability to show the structure of articles makes it
easier to browse than a Wiki. But this often isn't enough, and that's where
users need search. Enter the Knowledge Base Search portlet. 

![Figure 14.34: The Knowledge Base Search portlet helps you search the knowledge base for keywords.](../../images/kb-search-portlet.png)

The search portlet helps you find articles that match keywords you specify. 

Next you'll learn how to create new articles and update existing articles by
importing them from Markdown source files.

## Importing Articles from Markdown Source Files [](id=importing-articles-from-markdown-source-files)

As has already been stated, the Knowledge Base application can import articles
in bulk. This lets you have an offline process where articles are prepared ahead
of time before they are published. The file format for the articles is Markdown. 

Markdown is a text-only file format that is designed to be easy to read, yet
support all the things you'd need to do to format your articles. It's also
something of a standard: there's [Github Flavored Markdown](https://help.github.com/articles/github-flavored-markdown), 
forums that support Markdown (reddit, StackExchange, and others), Markdown
editors, and an IETF draft for making it an official Internet media type
(text/markdown). Why is there so much interest in Markdown? 

1.  It's readable. Even if you don't know Markdown, you can read it without
    having to filter out the syntax. 

2.  It gets out of a writer's way. You don't have to worry about mousing to
    various icons to change text into a heading or create bulleted lists. Just start
    typing. The syntax is very intuitive. 

3.  There are tools to convert it to many other formats, though it was designed
    to convert to HTML. If your articles are in Markdown, it's straightforward to
    publish them to the web, mobile formats (Kindle, ePub), and print. 

4.  Since it's only text, you can use existing tools to collaborate on that
    text. Using services like GitHub, people can contribute to your articles, and
    you can see all the changes that have been made to them. 

The Knowledge Base supports a Markdown dialect known as
[Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/). This dialect
extends the original Markdown with features like table formatting, image
captions, and footnotes. 

For the Knowledge Base to import your Markdown articles, they must adhere to
these requirements: 

- All source files must use the `.markdown` or `.md` extensions.
- Articles must start with a top-level header (e.g., `# Some
Heading ...`). 
- Each header must have an associated, unique ID for the article's friendly URL
title and for anchor tags in the article's sub headers. Here's an example of
a top-level header that correctly specifies an ID: `# Some Heading  [](id=some-heading)`

Below is Markdown source text for a simple example article: 

    # The Moons of Mars [](id=the-moons-of-mars)

    As you look up from your chaise lounge, you're sure to see our neighboring
    planet Mars. Did you know that Mars has two moons? You might have to break out a
    pair of binoculars to see them.

    ![Mars and its moons.](./images/kb-display-mars-moons.png)

    Its two moons are aptly named after the two sons of mythical Roman God Mars.
    Their names are Phobos and Diemos.

In the first line above, notice the header's ID assignment
`id=the-moons-of-mars`. On import, the ID value becomes the URL title of the
resulting Knowledge Base article. Also note that the referenced image file
`kb-display-mars-moons.png` resides in a folder called `images`. 

When you're finished editing Markdown files that you want to import, Zip them up
with their accompanying image files. 

### Zip File Requirements [](id=zip-file-requirements)

-  Each Zip file must end in the suffix `.zip`.
-  Each Zip file must contain at least one Markdown source file, optionally
   organized in folders.
-  All referenced image files must be in a folder named `images` in the Zip
   file's root. 
-  Image files must be in a supported format and must use the appropriate file
   extensions. Supported extensions are `.bmp`,`.gif`,`.jpeg`,`.jpg`, and
   `.png`. They're specified via a portlet property. For details, see [Advanced Configuration](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration).

**Example Basic Zip File Structure:**

- `folder-1/`
    - `some-article.markdown`
    - `another-article.markdown`
- `images/`
    - `some-image.png`
    - `another-image.jpeg`

Markdown files can be specified anywhere in the Zip file's directory structure.
They can be nested in any number of folders. Image files are the only files
supported for attachments. No other kinds of references are supported--not even
anchors. 

In addition to the source files and images, you can pass to the importer a base
source URL property that specifies your source file's online repository
location. Each article's *Edit on GitHub* button (if enabled) takes the user to
the source location. The importer prefixes each file's path with the base source
URL to construct a URL to the article's repository source location; it looks
like `[base URL]/[article file path]`. Here's an example base source URL
property:

    base.source.url=https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

The source URL constructed from the above base URL and article source file
`folder-1/some-article.markdown` from the example basic Zip file would be this:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

You specify the base source URL in a file called `.METADATA` in the Zip file's
root folder. The importer treats the `.METADATA` file as a standard Java
properties file and uses the base source URL to construct the source URL for
all of the Zip file's resulting articles. To use the source URL feature, your
portal administrator must enable it via the Knowledge Base application's portlet
[properties](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration).

The importer also supports a two level hierarchy of articles and can assign
article priorities based on numerical file prefixes. Below is a more advanced
example showing a Zip file structure that leverages these features.

**Advanced Zip File Structure Example:**

- `01-winter-events/`
    - `00-winter-excursions-intro.markdown`
    - `01-star-dust-snow-shoeing.markdown`
    - `02-lunar-alpine.markdown`

- `02-summer-events/`
    - `00-summer-excursions-intro.markdown`
    - `01-lunar-rock-scrambling.markdown`
    - `02-extra-terrestrial-mountain-biking.markdown`

- `images/`
    - `some-image.png`
    - `another-image.jpeg`

- `.METADATA`

To designate an article to be the *parent* of all other articles in the same
source folder, end its file name with `-intro.markdown`. This creates a two-level
parent-child hierarchy. Using this convention, the above Zip file specifies
`00-winter-excursions-intro.markdown` as the parent of its neighboring Markdown
files: `01-star-dust-snow-shoeing.markdown` and `02-lunar-alpine.markdown`. 

To designate an article's *priority* using its source file, add a numeric prefix
to its file name. This prefix must be greater than zero. For example, the
priorities for articles imported from files named `01-file.markdown` and
`02-file.markdown` become `1.0` and `2.0`. When importing, keep the checkbox
labeled *Apply numerical prefixes of article files as priorities* selected. If a
file doesn't have a prefix, its article gets the next available priority
(i.e., the highest current priority, plus one). The Zip file's articles are
processed in file order. 

**Advanced Example's Resulting Relationships and Priorities**

Source File                                               | &nbsp;Article       | &nbsp;Relationship           | &nbsp;Priority  |
--------------------------------------------------------- | ------------------- | ---------------------------- | :-------------: |
 `01-intro/00-winter-excursions-intro.markdown`           | *Winter Excursions* | peer of *Summer Excursions*  | **1.0**         |
 `01-intro/01-star-dust-snow-shoeing.markdown`            | *Star Dust ...*     | child of *Winter Excursions* | 1.0             |
 `01-intro/02-lunar-alpine.markdown`                      | *Lunar Alpine*      | child of *Winter Excursions* | 2.0             |
 `02-intro/00-summer-excursions-intro.markdown`           | *Summer Excursions* | peer of *Winter Excursions*  | **2.0**         |
 `02-intro/01-lunar-rock-scrambling.markdown`             | *Lunar Rock ...*    | child of *Summer Excursions* | 1.0             |
 `02-intro/02-extra-terrestrial-mountain-biking.markdown` | *Extra Terrestrial* | child of *Summer Excursions* | 2.0             |
 
In the above Advanced Zip File Structure example, notice that the
`-intro.markdown` files have the prefix `00`. You can start your intro files
with `00` so that they're listed at the top of the folder's file listing on your
machine. The real trick is that the importer uses the prefix of such a file's
folder as its Knowledge Base article priority.  Here's the underlying logic: if
a file has prefix `00`, the importer assigns the resulting article's priority to
`1.0`. A top-level intro file, however, gets special treatment: if its prefix is
less than `1.0`, the importer checks the intro file's folder(s) for a prefix of
`1.0` or greater. It then sets the intro article's priority to the first folder
prefix found that is `1.0` or greater. This convention lets you specify
priorities for top-level (non-child) articles in your hierarchy. 

Once you have your article Zip file, it's time to import it. Navigate to *Site
Administration* &rarr; *Content* &rarr; *Knowledge Base* &rarr; *Articles* and
click on *Add* &rarr; *Import* to bring up the importer page. Browse to the
location of your file, and in most cases leave the checkbox for the article
priorities checked, and then click *Save*. Your file is uploaded, and the
importer converts each source file's Markdown text to HTML, applying the HTML to
the resulting article. Any image files that are included in the Zip file and
that are referenced in an article source file are imported as attachments to the
article. 

![Figure 14.35: Selecting *Add* &rarr; *Import* in the Knowledge Base portlet brings up the interface for selecting a Zip file of Markdown source files and images to produce and update articles in your Knowledge Base.](../../images/kb-admin-importer.png)

### Importer FAQs [](id=importer-faqs)

- **What happens when I import an existing article?** The importer checks 
if the source file's leading header ID (e.g., `# Some Heading  [](id=some-heading)`)
matches the URL title of any existing article in the Knowledge Base folder. If a
match is found, the importer replaces the article's content with the incoming
content converted from the source file. If no match is found, a new article is
created. 

- **Do I need to import all of a Knowledge Base folder's articles, even if I
only want to create a new article or update a subset of the folder's current
articles?** No. You can import as many or as few new articles and modified
articles as you like. 

- **Does the importer remove articles?** No. The importer only creates and
updates articles. It doesn't delete any existing articles. To delete an article,
you must manually do so via Knowledge Base portlets. 

- **Can I update an article's priority?** Yes. You can use the file/folder
prefix convention and re-import the article to update its priority. 

- **If I change an article's title, should I also change it's header ID?** It
depends on whether you've already published your article. If it hasn't been
published, then there are no public links to it, so it's fine to change the header
ID. If the article is already published, you must decide whether it's worth
breaking existing links to the article and worth having search engines
rediscover and re-rank your article based on its new friendly URL, which is
based on the new header ID. 

Congratulations on mastering the art of creating and importing Markdown files to
produce Knowledge Base articles! 

Next, portal administrators can learn how to override the Knowledge Base Portlet
application's portlet properties to enable and configure some advanced, but
optional features. 

## Advanced Configuration [](id=advanced-configuration)

The Knowledge Base application has several optional features that are disabled
by default. These include source URL, import file conventions, new article
priority increment, and sections. Here you'll learn how to configure these
features by overriding the Knowledge Base app's portlet properties. Advanced
configuration should only be performed by a portal administrator, as the person
modifying the configuration must have access to the downloaded Knowledge Base
application and be comfortable with repackaging applications and installing the
modified Knowledge Base app using the Control Panel. 

+$$$

**Important:** Advanced configuration of the Knowledge Base application should
only be performed by a portal administrator.

$$$

Next, you'll learn about the Knowledge Base app's portlet properties, how to
override the default values, and how to deploy the result to your portal. 

### Overriding Default Portlet Properties [](id=overriding-default-portlet-properties)

To start, create a file called `portlet-ext.properties` to hold the 
settings for the property values that you want to override. Keep your
`portlet-ext.properties` file open in an editor so you can add values as you go
along. 

**Source URL Properties:** Defines the source location of importable Markdown
files. This is intended to point to an online source repository where the files
are stored. GitHub is assumed as the default. Once defined, the Knowledge Base
displays a button (default label is *Edit on GitHub*) above each displayed
article. Users can click the button to navigate to an article's source location.
To enable this feature, specify the following property setting:

    knowledge.base.source.url.enabled=true

To override the button's default label *Edit on GitHub*, specify a new label as
the value for property `knowledge.base.source.url.edit.message.key`. The best
practice is to specify the value as a [language key](/develop/tutorials/-/knowledge_base/6-2/using-liferays-language-keys).
For example, if you create a language key `edit-on-bitbucket=Edit on Bitbucket`,
you could specify that language key as the button's new label:

    knowledge.base.source.url.edit.message.key=edit-on-bitbucket

You can alternatively specify that label explicitly like this:

    knowledge.base.source.url.edit.message.key=Edit on Bitbucket


**Importer File Convention Properties:** These properties define the supported
file extensions, the suffix for parent files, and the image folder's path within
the Zip file.

You can modify the supported file extensions: 

    markdown.importer.article.extensions=.markdown,.md
    markdown.importer.image.file.extensions=.bmp,.gif,.jpeg,.jpg,.png

You can change the article parent file suffix: 

    markdown.importer.article.intro=intro.markdown

You can modify the image folder path the importer looks for in the Zip file:

    markdown.importer.image.folder=images/

**New Article Priority Increment Property:** By default the priority for new
articles is incremented by `1.0`. To disable this increment so that articles get
a flat value of `1.0`, specify the following setting:

    admin.kb.article.increment.priority.enabled=false

**Section Names Property:** The section names property lets you specify 
names of arbitrary topics to attribute to articles. Using the Knowledge Base
Sections portlet, you can display one or more *sections* (groups) of articles.
To make section names available, you must specify them in a comma-separated
list. Specify them as a list of values for the property
`admin.kb.article.sections`:

    admin.kb.article.sections=Politics,Business,World

This creates the sections *Business*, *Politics*, and *World*. 

Next you'll learn how to deploy your customized properties. 

### Deploying Override Property Values [](id=deploying-override-property-values)

Your custom properties must be deployed with the Knowledge Base application. To
do this, you'll have to extract the application, place your file in the proper
location, re-package the application, and then deploy it. 

Don't worry; that's much easier than it sounds. 

1. 
[Download](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps#downloading-through-liferay) 
the Knowledge Base from Liferay Marketplace. You'll get a file with an `.lpkg`
extension. 

2. Extract the `.lpkg` file (any utility that handles Zip files works). The
extracted contents include the app's `.war` file
`knowledge-base-portlet-[version].war`. 

3. Extract the `.war` file to access the application's files. 

5. Add your `portlet-ext.properties` file to the portlet project folder
`WEB-INF/classes`. 

6. Repackage (e.g., using the `jar` executable or a Zip tool) the portlet
project's folders and files into a `.war` file. 

7. In your portal, navigate to *Control Panel* &rarr; *Apps* &rarr; *App
Manager* &rarr; *Install*. 

8. Select *File Upload*, select your `.war` file, and click *Install* to deploy
the Knowledge Base application.

Your deployed application now uses the settings you specified in your
`portlet-ext.properties` file. Congratulations on deploying your advanced
configuration settings! 

You've learned the benefits of publishing articles using the Knowledge Base
application. You've seen how easy it is to create, organize, and manage
articles, and you've learned various ways to present articles in the Knowledge
Base Display, Knowledge Base Article, and Knowledge Base Section portlets. You
can consider yourself to be truly *knowledgeable* of Liferay's Knowledge Base
application.
