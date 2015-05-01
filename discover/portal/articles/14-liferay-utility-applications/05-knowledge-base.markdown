# Knowledge Base [](id=knowledge-base)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay's Knowledge Base application provides a means for creating and
organizing articles within a site. Itss perfect for creating and organizing
information more formally than in a wiki. For example, it can be used to
hierarchically organize articles to display professional product documentation
or to form complete books or guides. It even lets you import article source
files written in Markdown, automatically converting them into full-fledged
knowledge base articles. It's easy to set up the Knowledge Base application with
a workflow that requires articles to be approved before they are published.
Additionally, it lets administrators create article templates, to ensure that
articles using the template follow a common structure and include certain kinds
of information. 

Here are the Knowledge Base application's new features:

- Navigation built into the Knowledge Base Display 
- Suggestions interface for soliciting and managing article feedback
- Article folders
- Article fields for friendly URL, source URL, categorization, and related assets
- Button ![*Edit on GitHub*](../../images/kb-display-edit-on-github-button.png)
can be enabled to take readers to an article's online source repository location
- Import article Markdown source files to create and update articles

The Knowledge Base application is available for purchase on the [Liferay Marketplace](http://www.liferay.com/marketplace).
Once you've [installed](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps)
the app, the Knowledge Base page is available in the *Site Administration*
&rarr; *Content* section and the following portlets are available at
*Applications* &rarr; *Knowledge Base*: Knowledge Base Display, Knowledge Base
Article, Knowledge Base Search, and Knowledge Base Section. 

## Knowledge Base Page in Site Administration [](id=knowledge-base-page-of-site-administration)

The Knowledge Base page in *Site Administration* &rarr; *Content* has everything
administrators and authors need to create, update, and manage articles. It has
three tabs: one for managing articles, one for managing templates, and the last
one for managing aritcle suggestions. The *Articles* tab shows all the articles
and article folders in the knowledge base and it lets you perform actions on
them. The *Templates* tab shows all the templates defined in the knowledge base
and lets you perform actions on them. The *Suggestions* tab shows all the
suggestions users have submitted for the articles and enables you to handle them
in workflow fashion.

![Figure 14.26: You can manage knowledge base articles, folders, templates, and suggestions from the *Knowledge Base* page in *Site Administration*.](../../images/kb-admin-page.png)

The *Add* drop-down lets you add a Basic Article, add an article based on an
available template, add a folder, or import articles from a Zip file. Selecting
*Basic Article* or the name of an available template brings up the *New Article*
window. As you enter your article's title in this window, a URL-safe version of
the title is added to the tail end of the article's friendly URL--see the value
in the *Friendly URL* field. You can, of course, edit the friendly URL.  

You create the article's content using the WYSIWYG editor. Knowledge Base
articles are created in HTML. Click the *Source* button in the editor to view
the HTML source based on what you've written using the WYSIWYG editor or your
can write some HTML yourself. 

![Figure 14.27: You can create and modify a knowledge base article's content using the WYSIWYG editor.](../../images/kb-admin-new-article.png)

In addition to entering an article's title and creating its content, you can
use the interface to add attachments, add tags, specify related assets, and set
permissions. By default, view permission is granted to the guest role, meaning
anyone can view your article. After you're done using the editor, you can save
the article as a draft and continue working on it later, or you can submit it
for publication. Your article may need to be approved before being published,
depending on the workflow defined for your portal. 

The *Permissions* button is next to the *Add* drop-down in the Knowledge Base
page. Clicking this button brings up a window that lets you define permissions
for applying to the Knowledge Base application generally, not to particular
articles. Here, you can define which roles can add/delete articles, folders and
templates, which roles can change general knowledge base permissions, which
roles can subscribe to articles, and which roles can view templates and
suggestions. 

+$$$

**Note:** In order for a role to be able to access the Knowledge Base page in
Site Administration, the role must have the permission *Knowledge Base* &rarr;
*Access in Site Administration*. And to add or act on articles, folders, or
suggestions, the site administrator must grant the role such permissions (e.g.,
via the *Permissions* window in the Knowledge Base page in Site Administration).

$$$

The Add drop-down in the Knowledge Base page in Site Administration also has an
option for adding a folder. The interface for adding folders is straightforward,
letting you enter a name, description, and permissions for the folder. The last
add option lets you import articles using a Zip file. The article import feature
is covered in a later [section](/discover/portal/-/knowledge_base/6-2/knowledge-base#importing-articles-from-markown-source-files)
of this documentation. 

As you add folders and articles to your knowledge base, notice the
actions you can perform on them. Administrators can perform the following
actions on a folder: 

*Edit:* change the folder's name and description.

*Move:* relocate the folder under a new parent folder. 

*Delete:* remove from the knowledge base the folder and its articles. 

*Permissions:* grant or revoke a role's ability to add an article to the folder,
add a sub-folder to the folder, delete the folder, move the folder, set
permissions on the folder, edit (update) the folder, and view the folder. 

Articles can be created or moved into in several places in the knowledge base.
They can reside in the root of the knowledge base, they can reside in folders,
and they can be children of other articles (parent articles).

Here are the actions you can perform on an article:

*View:* display the article. 

*Edit:* change the article's title and content and manage its attachments,
categorization, and its related assets. 

*Add Child Article:* add a new child article to the article. 

*Permissions:* configure the permissions of roles with respect to the
article. 

*Subscribe:* choose to be notified of any updates to the article. Once you've
subscribed to an article, the *Unsubscribe* action is made available giving you
the option of unsubscribing from the article's notifications. 

*Move:* move the article to a different folder or change an article's position
in the hierarchy by choosing a new parent article for it.

*Delete:* remove the article from the knowledge base. 

You can also assign new *priority* values to articles. When multiple articles
are configured to be displayed in the Knowledge Base Display portlet, peer
articles are listed in the portlet's navigation are arranged top to bottom in
ascending priority. The lower an article's priority value the higher up it's
listed in the Knowledge Base Display portlet's navigation. To assign new
priority values, enter a new priority value for each article you want to
reprioritize, select the checkbox of each modified article, and select *Actions*
&rarr; *Save*. 

+$$$

**Note:** In the *Articles* tab, the *Actions* drop-down is only displayed when
one or more article checkboxes are selected. The Actions drop-down lets you save
changes (i.e., priority values) to the selected articles or delete selected
articles from the knowledge base. 

$$$

The *Templates* tab of the Knowledge Base page in Site Administration lets you
create article templates. A template basically functions like a starting point
for the creation of certain types of articles. You can create templates that
have default headers or content to pre-populate articles created from the
template. Using templates fosters consistency in format and content across new
articles. To create a new template, click on the *Add Template* button in
*Templates* tab. 

![Figure 14.28: As an administrator, you can add a new template to your knowledge base from the *Knowledge Base* page in *Site Administration*.](../../images/kb-admin-new-template.png)

From the *Templates* tab, you can perform the following actions on a template:

*View:* display the template. From here, you can print the template, use it to
create an article, edit the template, modify the permissions on the template, or
delete it. 

*Edit:* change the template's title and content.

*Permissions:* configure the permissions on the template. You can choose whether
a role is permitted to update, view, or delete the template, or change the
permissions on the template.

*Delete:* remove the template from the knowledge base.

The *Suggestions* tab of the Knowledge Base page in Site Administration lets
you view user feedback on articles and lets you mark progress on addressing the
feedback. 

![Figure 14.29: The Suggestions tab in the Knowledge Base page in Site Administration helps you monitor article feedback and mark progress in addressing feedback.](../../images/kb-admin-suggestions.png)

Each Suggestion entry provides the link to the associated article, the user's
feedback, the user's name linked to the user's home page, the feedback's
timestamp, and a status value indicating progress on addressing the suggestion.
You can move the entry between *New*, *In Progress*, and *Resolved* states. 

+$$$

**Note:** For a role to be able to view suggestions to articles in the
*Knowledge Base* page in *Site Adminiistration*, the role must be granted the
*Knowledge Base* &rarr; *Knowledge Base: View Suggestions* permission. 

For a role to be able to move suggestions between the *New*, *In Progress*,
and *Resolved* states, the role must be granted the *Knowledge Base* &rarr;
*Knowledge Base Article: Update* permission. Users assigned to that role can
also view article suggestions and update the state of article suggestions from
within any one of the other Knowledge Base application's portlets. 

$$$

Next you'll learn how to create new articles and update existing articles by
importing them from Markdown source files bundled in a Zip file. 

## Importing Articles from Markown Source Files [](id=importing-articles-from-markown-source-files)

If you navigate to *Site Administration* &rarr; *Content* &rarr; *Knowledge
Base* &rarr; *Articles* and click on *Add* &rarr; *Import* to bring up the
window that lets you import into your knowledge base, a Zip file full of
articles and their images. All you need to do is write your articles in a text
format called Markdown, Zip up your Markdown article source files and their
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

Below is Markdown source text for a simple example article: 

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

**Zip File Requirements:**

- Each Zip file must end in the suffix `.zip`.
- Each Zip file must contain at least one Markdown source file.
- All referenced image files must be in a folder named `images`, in the Zip
file's root. 
- Image files must use one of the portlet's designated suffixes (the default
suffixes supported are `.bmp`,`.gif`,`.jpeg`,`.jpg`, and `.png`).

**Example Basic Zip File Structure:**

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

You specify the base source URL in a file called `.METADATA` in the Zip file's
root folder and the importer applies that base source URL to all of the Zip
file's resulting articles. To use the source URL feature, your portal
administrator must enable it via the Knowledge Base application's portlet [properties](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration).

The Zip file importer also supports importing a two level hierarchy of articles
and assigning articles a priority based on numerical file prefixes. Below is a
more advanced listing that shows a Zip file structure that leverages these
features.

**Example Advanced Zip File Structure:**

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

To designate an article to be the *parent* of all other articles in the same
source folder, end the article source file's name with `intro.markdown`. This
creates a two-level parent-child hierarchy. Using this convention, the above Zip
file specifies `00-winter-excursions-intro.markdown` as the parent of its
neighboring Markdown files: `01-star-dust-snow-shoeing.markdown` and
`02-lunar-alpine.markdown`. 

To designate an article's *priority* via it's source file, add a non-zero prefix
to its file name. Example, the priorities for articles created from files named
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
special treatment: if its prefix is less than `1.0`, the importer checks to see
if the intro file's folder(s) have a prefix of `1.0` or greater. It assigns, as
the intro article's priority, the first folder prefix it finds that is `1.0` or
greater. This folder prefix convention enables you to specify priorities for
top-level (non-child) articles in your hierarchy. 

It's important that you know the importer's limitations and best practices for
importing articles. A good way to address these is in a Frequently Asked
Questions (FAQ) format.

**Importer FAQs:**

- **What happens when I import an existing article?** The importer looks to see
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
you must manually do so in the Knowledge Base application. 

That wraps it up for working with the Knowledge Base from within Site
Administration. The next sections introduce Knowledge Base portlets for
displaying articles and article hierarchies, aggregating articles for viewing,
and finding articles. 

## Knowledge Base Display Portlet [](id=knowledge-base-display-portlet)

The Knowledge Base Display portlet (display portlet) is ideal for displaying
sets of articles. You can access the portlet's *Configuration* menu by clicking
on the gear icon in the top right corner of the portlet and selecting
*Configuration*. In the configuration dialog box's *Setup* tab, there's a
*General* sub-tab that has an *Article Folder* field with a *Select* button.
Clicking the *Select* button brings up a dialog box for navigating to and
choosing an existing article or folder of articles to display in the portlet. If
you choose a folder of articles to display and click *Save*, the navigation in
the left side of the portlet displays links to all of the folder's articles, and
the viewing area in the right side of the portlet displays the folder's leading
article (the article that has the lowest Priority value). 

The display portlet is terrific for displaying hierarchies of articles. Clicking
an article's link in the navigation displays the article in the view area.
Clicking on a link to an article that has child articles expands the navigation
tree to display links to the child articles. The expanded nodes naturally
collapse when you click on a different top level article. Inside the viewing
area you can use the links below the article content to navigate to the next
article or previous article. The navigation reacts as you would expect,
highlighting the link of the article you're viewing and expanding and collapsing
the tree nodes appropriately. 

![Figure 14.30: The Knowledge Base Display portlet's navigation and viewing area facilitates viewing article hierarchies.](../../images/kb-display.png)

There are links at the top of the portlet that users can use to subscribe to an RSS
feed of the knowledge base, subscribe to the article, view the history of the
article, or print the article. 

If your portal administrator enables the Knowledge Base application's source URL
feature and an article has an assigned source URL, a button ![*Edit on GitHub*](../../images/kb-display-edit-on-github-button.png) displays to the
right of the article's title, giving readers access to the article's online
source location. This feature can be used to encourage users to contribute fixes
or improvements to articles. If you're interested in using this feature, you can
direct your portal administrator to follow instructions in [Advanced Configuration](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration). 

Below the article's content, the thumbs up and thumbs down icon rating interface
is displayed, by default. In addition to users being able to give thumb ratings,
they can also click the link *Do you have suggestions?* and submit suggestions
or comments for the article. Administrators can view the suggestions and mark
progress (*In Progress* or *Resolved* statuses) addressing them.

Administrators can perform the following actions on articles:

*Edit:* change the article's title and content. It also lets you manage
its attachments, categorization, and its related assets. 

*Add Child Article:* add a new child article to the article. 

*Move:* relocate the article to a different folder or change the article's
position in the hierarchy by choosing a new parent article for it.

*Delete:* remove the article from the knowledge base. 

The display portlet provides common Liferay application [configuration](/discover/portal/-/knowledge_base/6-2/configuring-liferay-applications)
options, including ones for look and feel, export/import, permissions, scope,
and sharing.

As an administrator, let's say that you've diligently used knowledge base
folders to aggregate similar articles and you want to provide an easy way for
users to switch between these sets of articles. The display portlet's *Root
Context* feature lets you configure a drop-down selector at the top of the
display portlet's navigation, to enable users to switch between the sets of
articles. Here's what you do to set up Root Contexts and the Root Context
selector:

1. In the Knowledge Base page of Site Administration, add a folder to hold
sub-folders of articles. Then add a sub-folder for each set of articles and add
your articles to them. 
2. Click on the Knowledge Base Display portlet's gear icon to bring up the
*Configuration* dialog box. In *Setup* &rarr; *General*, select the top-level
folder (the parent folder of the sub-folders) that you created in step 1 and
click *Save*. 

A Root Context selector displays at the top of the Knowledge Base Display
portlet's navigation. Its values reflect the names of your top-level folder's
sub-folders. Each sub-folder is considered to be the *Root Context* for the
articles it contains. 

![Figure 14.x: The Knowledge Base Display portlet's Root Context feature lets users switch between different sets of articles.](../../images/kb-display-root-context-selector.png)

You can also add a common prefix to the Root Context names shown in the
selector. For example, if you wanted to display user manuals (comprised of sets
of articles) for different versions of a product, you could create a top-level
folder and then create a sub-folder named after each product version. You'd
configure the display portlet by selecting your top-level folder and clicking
*Save*. In the *Root Context Prefix* field, you enter your product's name and
click *Save*. In the display portlet's navigation, the Root Context selection
for each of the product's user manuals would show the product's name followed by
the product version, which is the name of each article set's folder. You can, of
course, name your folders and Root Context Prefix whatever you like.  

In this section, you've learned the features that the Knowledge Base Display
portlet provides for displaying sets of articles. Next you'll learn how to use
the Knowledge Base Article portlet to individually display articles. 

## Knowledge Base Article Portlet [](id=knowledge-base-article-portlet)

The Knowledge Base Article portlet (article portlet) can be placed on a page to
display a single article's entire content, followed by abstracts of all its
child articles. You can add any number of article portlets to a page and you can
configure each article portlet to display a different article. When you first
place this portlet on a page, it displays the message *Please configure this
portlet to make it visible to all users*. This message is a link to the
configuration dialog box for the portlet. Click *Select Article* to choose an
article to display. Pick an article and then click *Save*. When your page
refreshes it displays the article in the portlet.

![Figure 14.31: The Knowledge Base Article portlet is great at individually displaying articles.](../../images/kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the
article it displays. There are also links at the top of the portlet users can
use to subscribe to an RSS feed of the knowledge base, subscribe to the article,
view the history of the article, or print the article.

## Knowledge Base Section Portlet [](id=knowledge-base-section-portlet)

The Knowledge Base Section portlet (section portlet) allows administrators to
selectively show articles associated with a specific section. For example, a
news site might have a *World* section, a *Politics* section, a *Business*
section and an *Entertainment* section. Your users will see a multi-select box
in the Add Article and Edit Article screens that allows them to select which
section an article belongs to. You can add any number of section portlets to a
page and you can configure each section portlet to display articles from any
number of sections. To use sections, a portal administrator must enable the
feature the Knowledge Base application's portlet properties; and a portal
administrator must specify any section names you want to use via the Knowledge
Base application's portlet properties. The [Advanced Configuration](/discover/portal/-/knowledge_base/6-2/knowledge-base#advanced-configuration)
section explains how to configure the sections feature in the portlet
properties. 

![Figure 14.32: Here's an image of Knowledge Base Section portlets being displayed on a page.](../../images/kb-section-portlets.png)

The Knowledge Base section portlet has some additional configurations that allow
an administrator to select a display style (title or abstract), an article
window state (maximized or normal), how to order the articles, how many articles
to display per page, and whether or not to show pagination.

## Knowledge Base Search [](id=knowledge-base-search)

Wikis often have deeply nested articles that can be hard to find by browsing.
Liferay's knowledge base's ability to selectively display articles makes it
easier to browse than a Wiki. The Knowledge Base Search portlet allows you to
search for articles in the knowledge base. This portlet presents the search
results to you in order from most relevant to least relevant.

![Figure 14.33: The Knowledge Base Search portlet helps you search the knowledge base for keywords.](../../images/kb-search-portlet.png)

The search portlet helps you find articles in your knowledge base that match
keywords you specify. 

## Advanced Configuration [](id=advanced-configuration)

The Knowledge Base application provides several features that you can fine-tune
to meet your needs. Here are some features that you might be interested in
enabling or configuring differently: source URL, import file conventions, new
article priority increment, and sections. In this section, you'll learn how to
configure these features by overriding the Knowledge Base app's portlet
properties. Advanced configuration should be done by a portal administrator, as
person modifying the configuration must have access access to the downloaded
Knowledge Base application, must be comfortable repackaging applications, and
must install the modified Knowledge Base app via the Control Panel. 

+$$$

**Important:** Advanced configurations to the Knowledge Base application should
only be performed by a portal administrator.

$$$

In this section, you'll learn about the Knowledge Base app's portlet properties,
how to specify values to override the default values, and how to deploy the
Knowledge Base app and these new values to your portal. 

### Overriding Default Portlet Properties

To start, create a file called `portlet-ext.properties` to hold the property
settings for the property values that you want to override. Keep your
`portlet-ext.properties` open in an editor so you can add new property values.
The sections that follow describe the most common properties that Knowledge Base
administrators consider overriding. 

**Source URL Properties:** Enabling the source URL feature lets you specify the
URL of the article's source file and it displays a button (default label is
*Edit on GitHub*) above each displayed article. Users can click the button to
navigate to the article's source location. To enable this feature, specify the
following property setting:

    knowledge.base.source.url.enabled=true

To override the button's default label *Edit on GitHub*, specify a new label as
the value for property `knowledge.base.source.url.edit.message.key`. The best
practice is to specify the value as a [language key](/develop/tutorials/-/knowledge_base/6-2/using-liferays-language-keys).
For example, if you created a language key `edit-on-bitbucket=Edit on
Bitbucket`, you could specify that language key as the button's new label:

    knowledge.base.source.url.edit.message.key=edit-on-bitbucket

You could alternatively, specify that same example label explicitly like this:

    knowledge.base.source.url.edit.message.key=Edit on Bitbucket


**Importer File Convention Properties:** The importer file convention properties
let you specify supported file name extensions for imported source article files
and source image files, a file suffix for designating parent article source
files, and the folder path within the Zip file, in which the importer looks for
source image files.

You can override the following property settings with values to specify
supported file extensions and supported source image file extensions
respectively. Here are the default settings that you can override:

    markdown.importer.article.extensions=.markdown,.md
    markdown.importer.image.file.extensions=.bmp,.gif,.jpeg,.jpg,.png

You can specify the expected article parent file suffix by specifying a
different property setting. Here's the default setting that you can override:

    markdown.importer.article.intro=intro.markdown

You can specify the folder path within which the Zip file importer looks for
source image files. Here's the default setting that you can override:

    markdown.importer.image.folder=images/

**New Article Priority Increment Property:** By default new articles receive a
priority value that is `1.0` higher than the highest priority of all other
articles in the folder. To disable this increment so that articles get a flat
value of `1.0` by default, specify the following setting:

    admin.kb.article.increment.priority.enabled=false

**Section Names Property:** The section names property lets you specify the
names of categories/topics to attribute to articles. Using the Knowledge Base
Sections portlet, you can display one or more *sections* (groups) of articles.
In order to make section names available for article authors to choose, you must
specify them in a comma-separated list. To create section names, specify them as
a list of values for the property `admin.kb.article.sections`. Here's an example
of setting them to section values *Politics*, *Business*, and *World*:

    admin.kb.article.sections=Politics,Business,World

Next you'll learn how to deploy any new portlet property settings you've
specified. 

### Deploying Override Property Values

For the Knowledge Base application to be able to use new portlet property
settings, the properties must be made availabe in the application's class path.
You can do this by putting the `portlet-ext.properties` file you've created into
the application's class path via the Knowledge Base portlet's `.war` file. The
`.war` file is available in the Knowledge Base application's `.lpkg` file--it's
an archive file that contains the app's web archives and metadata files. The
following steps show you how to put your `portlet-ext.properties` file into the
app's `.lpkg` file and how to deploy the app using the `.lpkg` file. 

1. Navigate to your Knowledge Base app by going to your *Account Home* in
[http://www.liferay.com](http://www.liferay.com) (e.g.,
`https://www.liferay.com/web/[username]`). Click on *Apps*, and then select
the Knowledge Base app to display its description. 

2. Click the *App* button to download the app as a `.lpkg` file. 

3. Extract the contents of the `.lpkg` file. The extracted contents include the
app's portlet `.war` file `knowledge-base-portlet-[version].war`. 

4. Extract the contents of the portlet `.war` file. The extracted contents are
comprised of the portlet project's files. 

5. Add your `portlet-ext.properties` file to the portlet project folder
`WEB-INF/classes`. 

6. Repackage (e.g., using the `jar` executable or a Zip tool) the portlet
project's folders and files into a `.war` file. 

7. Repackage that newly packaged `.war` file and all other files originally
extracted from the `.lpkg` file. **Important:** don't include the *original*
portlet `.war` file. 

8. In your portal, navigate to *Control Panel* &rarr; *Apps* &rarr; *App
Manager* &rarr; *Install*. 

9. Select *File Upload*, select your `.lpkg` file, and click *Install* to deploy
the Knowledge Base application.

Your deployed application now uses the property settings that you specified in
your `portlet-ext.properties` file. Congratulations on deploying your advanced
configuration settings! 

In this section, you've learned the benefits of publishing articles using the
Knowledge Base application. You've seen how easy it is to create, organize, and
manage articles, and you've learned various ways to present articles in
the Knowledge Base Display, Knowledge Base Article, and Knowledge Base Section
portlets. You can consider yourself to be truly *knowledgeable* of Liferay's
Knowledge Base application. 
