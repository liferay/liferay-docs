# Informing Users with the Knowledge Base [](id=informing-users-with-the-knowledge-base)

Liferay's Knowledge Base app provides a way to create and organize articles in a 
site. For example, it can be used to display professional product documentation 
or form complete books or guides. It even lets you import article source files 
written in Markdown. It's [workflow-enabled](/discover/portal/-/knowledge_base/7-0/using-worklfow), 
so you can require articles to be approved before they are published. 
Additionally, the Knowledge Base app lets administrators create article 
templates that help users follow a common structure and include consistent kinds 
of information. These features let you create and manage your own knowledge 
base, full of information relevant to your users.

Here's what the Knowledge Base can do:

- Navigation is built into the Knowledge Base Display 
- Has a suggestions interface for article feedback
- Stores articles in folders
- Contains metadata fields for friendly URL, source URL, categorization, and related assets
- Button (![*Edit on GitHub*](../../../images/icon-edit-on-github.png))
can be enabled to take readers to an article's online source repository location
- Imports article Markdown source files to create and update articles

The Knowledge Base app is available on the 
[Liferay Marketplace](https://www.liferay.com/marketplace). This app also 
contains other apps that let you manage different aspects of your knowledge 
base. Once you've installed the app, it's accessible by opening Site
Administration and navigating to *Content* &rarr; *Knowledge Base*. The Knowledge 
Base has several apps you can add to site pages. The *Add* &rarr; *Applications* 
&rarr; *Content Management* menu lists its apps: Knowledge Base Article, 
Knowledge Base Display, Knowledge Base Search, and Knowledge Base Section. 
You'll learn what each does as you learn how to manage your knowledge base. 
First you'll learn how to create and manage articles. 

## Creating and Managing Articles [](id=creating-and-managing-articles)

In Site Administration, the Knowledge Base has everything administrators and 
authors need to create, update, and manage articles. To navigate to the 
Knowledge Base, go to Site Administration &rarr; *Content* &rarr; 
*Knowledge Base*. It has two tabs: one for creating and managing articles 
(Articles) and one for managing article suggestions (Suggestions). The Articles 
tab is for managing all the articles and article folders in the Knowledge Base. 
The Suggestions tab is for managing user-submitted feedback. 

![Figure 1: You can manage Knowledge Base articles, folders, and suggestions from the Knowledge Base in Site Administration.](../../../images/kb-admin-articles.png)

The Articles tab lets you create and manage Knowledge Base articles and folders.
It also displays any existing articles and folders. Folders appear in a table 
near the top of the Articles tab, while articles appear in a separate table at 
the bottom. When you click the Add button (![Add](../../../images/icon-portlet-add-control.png))
a menu pops up with all the different options for adding articles. You can add a 
folder, a Basic Article, an article based on a template, or you can import 
articles from a ZIP file. When you click the Options button (![Options](../../../images/icon-app-options.png)), 
a menu pops up allowing you to configure common features for the Knowledge Base 
app. You can manage templates, subscribe, edit permissions, import/export, and 
configure email options for the app. The *permissions* button allows you to 
define basic permissions for the app, such as who can view the app and who can 
add the app to a page. Clicking the *Home Folder Permissions* button lets you 
define more detailed permissions for the Knowledge Base app. You can choose the 
roles that can perform the following tasks: 

- Add/delete articles, folders, and templates 
- Change the Knowledge Base app's permissions 
- Subscribe to articles
- View templates and suggestions

+$$$

**Note:** To access Knowledge Base in Site Administration, a role must have the 
permission *Knowledge Base* &rarr; *Access in Site Administration*. To add or 
act on articles, folders, or suggestions, the site administrator must grant the 
appropriate permissions using the Permissions window in Knowledge Base.

$$$

To create an article, select *Basic Article* or the name of an available 
template in the Add button menu. This brings up the New Article form. As you 
enter your article's title in this window, a URL-safe version of the title is 
added to the end of the article's friendly URL. The value is displayed and 
editable in the *Friendly URL* field, underneath the *Configuration* section 
heading. You create the article's content using the WYSIWYG editor. Knowledge 
Base articles are created in HTML. Click the *Source* button in the editor to 
view the HTML source, or use this view to write HTML yourself. 

![Figure 2: You can create and modify a Knowledge Base article's content using the WYSIWYG editor.](../../../images/kb-admin-new-article.png)

You can also add attachments and tags, specify related assets, and set 
permissions. By default, View permission is granted to the Guest role, meaning
anyone can view your article. At any time, you can submit the article for 
publication or save it as a draft to continue working on it later. If you've 
enabled workflow for the Knowledge Base, your article must be approved before 
publication. 

You can also create folders for organizing your articles. To create a folder, 
select *Folder* from the Add button menu. This opens the New Folder form. The 
UI for adding folders is very straightforward. You can enter a name, description, 
and permissions for the folder. 

Articles and folders can exist in several places in Knowledge Base. You can 
create articles and folders in the root of Knowledge Base, or within other 
folders. Navigate through folders by clicking them in the UI. The text 
immediately below the filters and *Order by* selector at the top of the app 
shows your position in a folder hierarchy. Click any of the preceding folders in 
this text to navigate back up the hierarchy. You can also move articles and 
folders into other folders, and create child articles of other articles.

![Figure 3: This screenshot uses a red box to highlight the text that indicates the current position in the folder hierarchy.](../../../images/kb-admin-folder-hierarchy.png)

Many of these functions are available in each item's *Actions* button. You can 
perform the following actions on folders: 

- **Edit:** change the folder's name and description.

- **Move:** relocate the folder under a new parent folder. 

- **Delete:** remove the folder and its articles from the knowledge base. 

- **Permissions:** grant or revoke the following permissions: add an article to 
  the folder, add a sub-folder to the folder, delete the folder, move the 
  folder, set permissions on the folder, edit (update) the folder, and view the 
  folder. 

The following actions are available for articles:

- **Edit:** change the article's title and content and manage its attachments,
  categorization, and its related assets. 

- **Add Child Article:** add a new child article to the article. 

- **Permissions:** configure the permissions on the article. 

- **Subscribe:** choose to be notified of any updates to the article. Once 
  you've subscribed to an article, the *Unsubscribe* action appears, letting you
  unsubscribe from the article's notifications. 

- **Move:** move the article to a different folder or change an article's 
  position in the hierarchy by choosing a new parent article for it. 

- **Delete:** remove the article from the Knowledge Base.

You can also assign new *priority* values to articles. The priority value of 
each article is shown underneath the article's title. When many articles are in 
the Knowledge Base Display app (more on this app in a moment), navigation 
arranges them in ascending priority. Priority 1 is the highest priority; think 
"Priority 1 distress call!" The higher an article's priority, the higher it 
appears in the navigation. To assign new priorities, select *Move* from the 
Actions menu that appears next to the article, enter a new priority value for 
the article, and click *Move*. You can also delete multiple articles at once by 
selecting the checkbox for each article that you want to delete and clicking the 
*X* button that appears at the top of the app. Note that this Actions menu only 
appears when one or more article checkboxes are checked. This Actions menu lets 
you check the info for selected articles or delete selected articles. 

Next, you'll learn how to manage article templates. 

### Managing Templates [](id=managing-templates)

Templates are starting points for articles. For example, you can create 
templates that contain default headers or other content to give your users a 
starting point to work from. Templates help foster consistent formatting and 
content organization for articles. To create a new template, click the 
*Templates* button in the Options menu (![Options](../../../images/icon-app-options.png)) 
at the top of the app. This brings up the Templates page, where you can manage 
existing templates. Click the Add button at the bottom of the screen to add a 
template. Fill in the New Template form and click *Publish*.

![Figure 4: As an administrator, you can add a new template to your knowledge base from Knowledge Base in Site Administration.](../../../images/kb-admin-new-template.png)

From the Templates page, the following actions are available in each 
template's Actions button:

- **View:** display the template. From here, you can print the template, use it 
  to create an article, edit it, modify its permissions, or delete it. 

- **Edit:** change the template's title and content. 

- **Permissions:** configure the permissions on the template. You can choose 
  whether a role can change permissions, update, view, or delete the template. 

- **Delete:** remove the template from the Knowledge Base. 

Knowledge Base in Site Administration also lets you handle user feedback on 
articles. You'll learn about this next.

### Responding to User Feedback [](id=responding-to-user-feedback)

Knowledge Base's Suggestions tab shows user feedback on articles and lets 
you mark progress on addressing the feedback. 

![Figure 5: The Suggestions tab in Knowledge Base helps you monitor article feedback and mark progress in addressing feedback.](../../../images/kb-admin-suggestions.png)

Each suggestion provides the link to the associated article, the user's 
feedback, the user's name, the feedback's time stamp, and the status on 
addressing the suggestion. You can move the entry between *New*, *In Progress*, 
and *Resolve* states. 

+$$$

**Note:** To view article suggestions, your role must be granted the *Knowledge
Base* &rarr; *Knowledge Base: View Suggestions* permission. 

To move suggestions between the *New*, *In Progress*, and *Resolve* states, 
your role must be granted the *Knowledge Base* &rarr; *Knowledge Base Article:
Update* permission. Roles assigned this permission can also view and update the
state of article suggestions from any of the other Knowledge Base apps. 

$$$

So far, you've learned how to create, edit, and organize articles. You've also
seen how the suggestions feature lets you and your users collaborate on your
knowledge base content. Next, you'll learn how the other Knowledge Base apps let 
you display, navigate, aggregate, and view articles. 

## Knowledge Base Display [](id=knowledge-base-display)

Once you have some articles, you can use the Knowledge Base Display app to 
display them. Begin by adding Knowledge Base Display to the page you want your 
articles to appear on. Knowledge Base Display is very flexible. You can 
customize how it displays articles, as well as which ones it displays. To access 
the app's Configuration menu, mouse over the app, click the *Options* icon 
(![Options](../../../images/icon-app-options.png)) 
in the top right corner, and select *Configuration*. In the Configuration 
dialog's *Setup* tab, the *General* sub-tab has an *Article or Folder* field 
with a *Select* button. Click *Select* to choose an existing article or folder 
of articles to display in the app. Knowledge Base Display also provides common 
Liferay application configuration options, including ones for look and feel, 
export/import, permissions, scope, and sharing. For more information on this, 
see the section on configuring Liferay applications in 
[Web Experience Management](/discover/portal/-/knowledge_base/7-0/web-experience-management). 

The Knowledge Base Display's navigation menu and display options make it the 
perfect candidate for a full page app. Simply add the app to a single column row
of a layout and your page is ready to go.

If you display a folder of articles, the navigation on the left side of the app 
displays links to all of the folder's articles. The viewing area on the right 
side of the app displays the folder's leading article (the "priority one" 
article). Click any article in the navigation to display it in the viewing area. 
The currently displayed article's link appears in bold in the navigation. You 
can also move between articles by clicking the links at the bottom of the app. 

![Figure 6: Knowledge Base Display's navigation and viewing area facilitates viewing article hierarchies.](../../../images/kb-display.png)

Knowledge Base Display can also display article hierarchies. Viewing an article 
that has child articles expands the navigation tree to show links to the child 
articles. Any expanded nodes collapse when you view a different top level 
article.

+$$$

**Note:** The Knowledge Base Display flattens the article hierarchy after three 
levels. Anything three levels or higher in the article hierarchy is listed at 
the same level. Look at the article structure below for example:

- `articles-section-01/`
    - `some-article`
    - `another-article`
        - `child-article`
            - `grandchild-article`
                - `great-grandchild-article`

The article hierarchy above would display *grandchild-article* and 
*great-grandchild-article* on the same level in the Knowledge Base Display:

- `articles-section-01/`
    - `some-article`
    - `another-article`
        - `child-article`
            - `grandchild-article`
            - `great-grandchild-article`

$$$

Users can also take additional actions on an article in Knowledge Base Display. 
Links at the top of the app let users subscribe to an RSS feed of the Knowledge 
Base, subscribe to the current article, view its history, or print it. Below the 
article's content is the rating interface, showing thumbs up and thumbs down 
icons. Users can also submit suggestions or comments for the article in the 
textbox underneath *Do you have any suggestions?*, at the bottom of the app. 
Administrators can view the suggestions and mark progress. Administrators can 
also edit, add a child article to, assign permissions to, move, or delete the 
article, using the corresponding buttons at the top of the app. 

If your Liferay administrator enables the Knowledge Base app's source 
URL feature (more on this in a moment) and an article has an assigned source 
URL, an *Edit on GitHub* button (![GitHub](../../../images/icon-edit-on-github.png)) 
appears to the right of the article's title. This button lets users access the 
article's online source location. You can use this feature to encourage users to 
contribute fixes or improvements to articles. If you're interested in this 
feature, you can direct your administrator to follow the instructions in 
[Advanced Configuration](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features). 

Great! Now you know the basics of how Knowledge Base Display works. Next, you'll 
learn how to let users choose between different article sets in different 
folders, without giving them access to the app's Configuration menu. 

### Displaying Different Article Sets [](id=displaying-different-article-sets)

As an administrator, say that you've used folders to aggregate similar articles,
and you want to provide an easy way for users to switch between these sets of
articles. The content folder feature lets you add a selector to the top of 
Knowledge Base Display's navigation that lets users switch between article sets. 
Here's what you do to set up content folders:

1. Add a folder in Knowledge Base. Then create sub-folders in this folder. 
   These sub-folders are the *content folders*. 

2. Add articles to each content folder.

3. Select *Configuration* from Knowledge Base Display's *Options* menu. In the 
   *Setup* &rarr; *General* tab, select the content folders' parent folder and 
   click *Save*. 

A content selector then appears at the top of Knowledge Base Display's 
navigation area. Its values reflect the names of your content folders. Select 
one to view its articles. 

![Figure 7: Knowledge Base Display's content folder feature lets users switch between different sets of articles.](../../../images/kb-display-content-selector.png)

You can also add a common prefix to the names shown in the selector. To do this, 
first navigate to Knowledge Base Display's *Configuration* dialog and select the 
*Setup* &rarr; *Display Settings* tab. Then enter the prefix into the *Content 
Root Prefix* field and click *Save*. 

Awesome! Now you know how to use Knowledge Base Display to display several 
articles that users can choose from. Next, you'll learn how to use the Knowledge 
Base Article app to display only individual articles. 

## Knowledge Base Article [](id=knowledge-base-article)

You can place the Knowledge Base Article app on a page to show a single 
article's content. This app even shows abstracts of children articles. You can 
add any number of Knowledge Base Article apps to a page and each one can display 
a different article. When you first place this app on a page, it shows the link 
*Please configure this portlet to make it visible to all users*. Click this link 
and pick an article and then click *Save*. When your page refreshes, the app 
displays the article. You can also use the *Setup* &rarr; *General* tab of the 
app's *Configuration* menu to choose an article to display. 

![Figure 8: The Knowledge Base Article app is great at displaying individual articles.](../../../images/kb-article.png)

Knowledge Base Article displays its article using the same UI as Knowledge Base 
Display's viewing area. This includes the links at the top and bottom of the app 
that let the user interact with the article, and the buttons at the top of the 
app that lets administrators manage it.

## Knowledge Base Section [](id=knowledge-base-section)

The Knowledge Base Section app lets you selectively show articles associated 
with a specific topic (section). For example, a news site might have the 
sections *World*, *Politics*, *Business*, and *Entertainment*. Multi-select 
boxes in the Add Article and Edit Article screens let you define the section(s) 
the articles belong to. You can add any number of Knowledge Base Section apps to 
a page and each one can display articles from any number of sections. To use 
sections, your Liferay administrator must configure the feature in the Knowledge 
Base app's properties within Portal's system settings. Once the feature is 
enabled, he or she must specify in the system settings any section names you 
want to use. The [Advanced Configuration](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features)
section explains how to configure the sections feature in System Settings. 

![Figure 9: Here's an image of Knowledge Base Section apps on a page.](../../../images/kb-section.png)

Knowledge Base Section can be configured with different display styles (title or 
abstract). You can also define whether to show pagination or section titles.

## Knowledge Base Search [](id=knowledge-base-search)

Even though the Knowledge Base can show the structure of its articles, it may be 
difficult to find exactly what you're looking for by browsing. That's where the 
Knowledge Base Search app comes in. Using Knowledge Base Search is very 
straightforward. Just enter your search term and press the *Search* button. Your 
results are shown in a table that has columns for each matching article's title, 
author, create date, modified date, and number of views. You can select which 
columns to display in the app's Configuration dialog. 

![Figure 10: The Knowledge Base Search app helps you search the knowledge base for keywords.](../../../images/kb-search.png)

Next, you'll learn how to create new articles and update existing articles by 
importing them from Markdown source files. 

## Importing Articles from Markdown Source Files [](id=importing-articles-from-markdown-source-files)

As mentioned earlier, the Knowledge Base app can import articles in bulk. This 
lets you have an offline process where articles are prepared ahead of time 
before they're published. The file format for the articles is Markdown. Use 
Knowledge Base to import articles.

Markdown is a text-only file format that is designed to be easy to read, yet
support all the things you'd need to do to format your articles. It's also
something of a standard: there's 
[Github Flavored Markdown](https://help.github.com/articles/github-flavored-markdown), 
forums that support Markdown (reddit, StackExchange, and others), Markdown
editors, and an IETF draft for making it an official Internet media type
(text/markdown). Why is there so much interest in Markdown? 

1.  It's readable. Even if you don't know Markdown, you can read it without
    having to filter out the syntax. 

2.  It gets out of a writer's way. You don't have to worry about mousing to
    various icons to change text into a heading or create bulleted lists. Just 
    start typing. The syntax is very intuitive. 

3.  There are tools to convert it to many other formats, though it was designed
    to convert to HTML. If your articles are in Markdown, it's straightforward 
    to publish them to the web, mobile formats (Kindle, ePub), and print. 

4.  Since it's only text, you can use existing tools to collaborate on that
    text. Using services like GitHub, people can contribute to your articles, 
    and you can see all the changes that have been made to them. 

The Knowledge Base supports a Markdown dialect known as
[Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/). This dialect
extends the original Markdown with features like table formatting, image
captions, and footnotes.

For the Knowledge Base to import your Markdown articles, they must adhere to
these requirements: 

- All source files must use the `.markdown` or `.md` extensions.
- Articles must start with a top-level header (e.g., `# Some Heading ...`). 
- Each header must have an associated, unique ID for the article's friendly URL
  title and for anchor tags in the article's sub headers. Here's an example of
  a top-level header that correctly specifies an ID: 
  `# Some Heading  [](id=some-heading)`

Below is Markdown source text for a simple example article: 

    # The Moons of Mars [](id=the-moons-of-mars)

    As you look up from your chaise lounge, you're sure to see our neighboring
    planet Mars. Did you know that Mars has two moons? You might have to break 
    out a pair of binoculars to see them.

    Its two moons are aptly named after the two sons of mythical Roman God Mars.
    Their names are Phobos and Deimos.

In the first line above, notice the header's ID assignment 
`id=the-moons-of-mars`. On import, the ID value becomes the URL title of the
resulting Knowledge Base article. 

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
   `.png`. They're specified via an app system setting. For details, see 
   [Advanced Configuration](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features).

**Example Basic Zip File Structure:**

- `folder-1/`
    - `some-article.markdown`
    - `another-article.markdown`
- `images/`
    - `some-image.png`
    - `another-image.jpeg`

Markdown files can be specified anywhere in the ZIP file's directory structure.
They can be nested in any number of folders. Image files are the only files
supported for attachments. No other kinds of references are supported--not even
anchors. 

In addition to source files and images, you can configure a base source 
URL system setting for the importer that specifies your source file's online 
repository location. Each article's *Edit on GitHub* button (if enabled) takes 
the user to the source location. The importer prefixes each file's path with the 
base source URL. This constructs a URL to the article's repository source 
location; it looks like `[base URL]/[article file path]`. Here's an example base 
source URL:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

The source URL constructed from this base URL and article source file 
`folder-1/some-article.markdown` would be:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

You specify the base source URL in a file called `.METADATA` in the ZIP file's
root folder. The importer treats the `.METADATA` file as a standard Java
properties file and uses the base source URL to construct the source URL for
all of the ZIP file's resulting articles. To use the source URL feature, your 
Liferay administrator must enable it via the Knowledge Base app's 
[system settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#advanced-configuration). 

The importer also supports a two level hierarchy of articles and can assign
article priorities based on numerical file prefixes. Below is a more advanced
example showing a ZIP file structure that leverages these features.

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
source folder, end its file name with `-intro.markdown`. This creates a 
two-level parent-child hierarchy. Using this convention, the above ZIP file 
specifies `00-winter-excursions-intro.markdown` as the parent of its neighboring 
Markdown files: `01-star-dust-snow-shoeing.markdown` and 
`02-lunar-alpine.markdown`. 

To designate an article's *priority* using its source file, add a numeric prefix
to its file name. This prefix must be greater than zero. For example, the
priorities for articles imported from files named `01-file.markdown` and
`02-file.markdown` become `1.0` and `2.0`. When importing, keep the checkbox
labeled *Apply numerical prefixes of article files as priorities* selected. If a
file doesn't have a prefix, its article gets the next available priority
(the highest current priority, plus one). The ZIP file's articles are processed 
in file order. 

**Advanced Example's Resulting Relationships and Priorities**

- `01-intro/00-winter-excursions-intro.markdown`
    - **Article:** Winter Excursions
    - **Relationship:** Peer of *Summer Excursions*
    - **Priority:** **1.0**
- `01-intro/01-star-dust-snow-shoeing.markdown`
    - **Article:** Star Dust Snow Shoeing
    - **Relationship:** Child of *Winter Excursions*
    - **Priority:** 1.0
- `01-intro/02-lunar-alpine.markdown` 
    - **Article:** Lunar Alpine
    - **Relationship:** Child of *Winter Excursions*
    - **Priority:** 2.0
- `02-intro/00-summer-excursions-intro.markdown`
    - **Article:** Summer Excursions
    - **Relationship:** Peer of *Winter Excursions*
    - **Priority:** **2.0**
- `02-intro/01-lunar-rock-scrambling.markdown`
    - **Article:** Lunar Rock Scrambling
    - **Relationship:** Child of *Summer Excursions*
    - **Priority:** 1.0
- `02-intro/02-extra-terrestrial-mountain-biking.markdown`
    - **Article:** Extra Terrestrial Mountain Biking
    - **Relationship:** Child of *Summer Excursions*
    - **Priority:** 2.0

In this example, note that the `-intro.markdown` files have the prefix `00`. You 
can start your intro files with `00` so that they're listed at the top of the 
folder's file listing on your machine. The real trick is that the importer uses 
the prefix of such a file's folder as its Knowledge Base article priority. 
Here's the underlying logic: if a file has prefix `00`, the importer assigns the 
resulting article's priority to `1.0`. A top-level intro file, however, gets 
special treatment: if its prefix is less than `1.0`, the importer checks the 
intro file's folder(s) for a prefix of `1.0` or greater. It then sets the intro 
article's priority to the first folder prefix found that is `1.0` or greater. 
This convention lets you specify priorities for top-level (non-child) articles 
in your hierarchy. 

Once you have your article ZIP file, it's time to import it. Navigate to *Site
Administration* &rarr; *Content* &rarr; *Knowledge Base* &rarr; *Articles* and
click on *Add* &rarr; *Import* to bring up the importer page. Browse to the
location of your file, and in most cases leave the checkbox for the article
priorities checked, and then click *Save*. Your file is uploaded, and the
importer converts each source file's Markdown text to HTML, applying the HTML to
the resulting article. Any image files that are included in the ZIP file and
referenced in an article source file are imported as attachments to the article. 

![Figure 11: Selecting *Add* &rarr; *Import* in Knowledge Base brings up the interface for selecting a ZIP file of Markdown source files and images to produce and update articles in your Knowledge Base.](../../../images/kb-admin-importer.png)

### Importer FAQs [](id=importer-faqs)

- **What happens when I import an existing article?** The importer checks 
if the source file's leading header ID (e.g., 
`# Some Heading  [](id=some-heading)`) matches the URL title of any existing 
article in the Knowledge Base folder. If a match is found, the importer replaces 
the article's content with the incoming content converted from the source file. 
If no match is found, a new article is created. 

- **Do I need to import all of a Knowledge Base folder's articles, even if I
only want to create a new article or update a subset of the folder's current
articles?** No. You can import as many or as few new or modified articles as you 
like. 

- **Does the importer remove articles?** No. The importer only creates and
updates articles. It doesn't delete any existing articles. To delete an article,
you must manually do so via the Knowledge Base apps. 

- **Can I update an article's priority?** Yes. You can use the file/folder
prefix convention and re-import the article to update its priority. 

- **If I change an article's title, should I also change it's header ID?** It
depends on whether you've already published your article. If it hasn't been
published, then there are no public links to it, so it's fine to change the 
header ID. If the article is already published, you must decide whether it's 
worth breaking existing links to the article, and worth having search engines
rediscover and re-rank your article based on its new friendly URL. The new 
friendly URL is based on the new header ID. 

Congratulations on mastering the art of creating and importing Markdown files to
produce Knowledge Base articles! 

Next, Liferay administrators can learn how to override the Knowledge Base app's 
properties to enable and configure features. 

## Advanced Configuration [](id=advanced-configuration)

In previous versions of Liferay, the Knowledge Base's configuration was defined
in its `portlet.properties` file and could be overwritten with a 
`portlet-ext.properties` file. Since Liferay 7, a new Configuration API is 
implemented that allows you to configure the Knowledge Base via system settings. 
This allows an Administrator to make changes to the Knowledge Base's 
configuration and see them immediately. The System Settings are organized into 
five sub sections, one for the default Knowledge Base configuration for all 
sites(*Knowledge Base Service*), and one for each of the other Knowledge Base 
apps(*Knowledge Base Article*, *Knowledge Base Display*, *Knowledge Base Search*, 
and *Knowledge Base Section*).

+$$$

**Important:** Advanced configuration of the Knowledge Base application should
only be performed by a Liferay administrator. 

$$$

To start, open the Control Menu and navigate to *Control Panel* &rarr; 
*Configuration* &rarr; *System Settings*. Click the *Collaboration* link at the
top and scroll down to the *Knowledge Base* options.

The next few sections show the old properties and the new corresponding system 
settings to use, as well as a brief description of what the setting does along
with possible values.

The configuration settings that are shared across the Knowledge Base apps are 
covered in the [Shared Configuration Menu Settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#shared-configuration-menu-settings)
section.

The optional configuration settings are covered in the 
[Optional Configuration Features](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features)
section.

### Knowledge Base Service Configuration Menu [](id=knowledge-base-service-configuration-menu)

Here is a list of the properties previously found in Knowledge Base's 
`portlet.properties` and the new properties to use in the Knowledge Base Service
System Settings configuration menu:

<style>
.max-960 {
	margin: 0 auto;
	max-width: 960px;
}
.no-max
.max-960 {
	max-width: none;
}
.system-settings-table td {
	border-bottom: 1px solid;
	border-top: 1px solid;
	padding: 10px;
}
.table-header {
	font-weight: bold;
}
.table-header.second {
	width: 70%;
}
.left-header {
	border-right: 1px solid;
}
</style>

<div class="system-settings-table">
<div class="max-960">
<div class="aui-helper-clearfix section-1" >
<div class="aui-w100 block-1 content-column" >
<div class="content-column-content">
<table>
	<thead>
		<td class="table-header left-header">
			portlet.properties
		</td>
		<td class="table-header second">
			System Setting
		</td>
	</thead>
	<tbody>
		<tr>
			<td>
		            admin.email.from.name
			</td>
			<td>
	                    emailFromName
			</td>			
		</tr>
		<tr>
			<td>
			    admin.email.from.address
			</td>
			<td>
		            emailFromAddress
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.added.body<br>
					admin.email.kb.article.added.enabled<br>
					admin.email.kb.article.added.subject<br>
			</td>
			<td>
					emailKBArticleAddedBody<br>
					emailKBArticleAddedEnabled<br>
					emailKBArticleAddedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.suggestion.in.progress.body<br>
					admin.email.kb.article.suggestion.in.progress.enabled<br>
					admin.email.kb.article.suggestion.in.progress.subject<br>
			</td>
			<td>
					emailKBSuggestionInProgressBody<br>
					emailKBSuggestionInProgressEnabled<br>
					emailKBSuggestionInProgressSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.suggestion.received.body<br>
					admin.email.kb.article.suggestion.received.enabled<br>
					admin.email.kb.article.suggestion.received.subject<br>
			</td>
			<td>
					emailKBSuggestionReceivedBody<br>
					emailKBSuggestionReceivedEnabled<br>
					emailKBSuggestionReceivedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.suggestion.resolved.body<br>
					admin.email.kb.article.suggestion.resolved.enabled<br>
					admin.email.kb.article.suggestion.resolved.subject<br>
			</td>
			<td>
					emailKBSuggestionResolvedBody<br>
					emailKBSuggestionResolvedEnabled<br>
					emailKBSuggestionResolvedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.email.kb.article.updated.body<br>
					admin.email.kb.article.updated.enabled<br>
					admin.email.kb.article.updated.resolved.subject<br>
			</td>
			<td>
					emailKBSuggestionUpdatedBody<br>
					emailKBSuggestionUpdatedEnabled<br>
					emailKBSuggestionUpdatedSubject<br>
			</td>
		</tr>
		<tr>
			<td>
				        admin.kb.article.increment.priority.enabled
			</td>
			<td>
					articleIncrementPriorityEnabled
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.article.extensions
			</td>
			<td>
					markdownImporterArticleExtensions
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.article.intro
			</td>
			<td>
					markdownImporterArticleIntro
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.image.file.extensions
			</td>
			<td>
					markdownImporterImageFileExtensions
			</td>
		</tr>
		<tr>
			<td>
				        markdown.importer.image.folder
			</td>
			<td>
					markdownImporterImageFolder
			</td>
		</tr>
		<tr>
			<td>
				        knowledge.base.source.url.enabled
			</td>
			<td>
					sourceURLEnabled
			</td>
		</tr>
		<tr>
			<td>
				        knowledge.base.source.url.edit.message.key
			</td>
			<td>
					sourceURLEditMessageKey
			</td>
		</tr>		
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

+$$$

**Note:** The `knowledge.base.ratings.number.of.stars` property has been removed 
since Knowledge Base now has a Ratings Configuration Framework. You can change 
the ratings type in the Instance Settings configuration menu under the *Social* 
tab. You can also configure the ratings type for an individual site under the 
*Social* tab of the Site Settings configuration menu.

The `knowledge.base.social.bookmarks.display.style` property can now be found
under the *Social bookmarks display style* setting of each Knowledge Base 
app's configuration menu in System Settings.

$$$

The list below describes what each configuration option does in the System
Settings UI.

-  **Get editor name:**  Sets the default editor. Possible values are: 
   `alloyeditor`, `ckeditor`, `simple`, `tinymce`, and `tinymce_simple`.

-  **Article increment piority enabled:** Covered in the 
   [Optional Configuration Features](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features)
   section.
   
-  **Markdown settings:** Covered in the [Optional Configuration Features](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features)
   section.
   
-  **Source URL settings:** Covered in the [Optional Configuration Features](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#optional-configuration-features)
   section.
   
+$$$

**Note:** If you view the settings in each of these configuration menus you'll 
see that some settings have a value that is set to a variable. For example, the 
Knowledge Base Article configuration menu has the value shown below for the 
*Social bookmarks types* setting:

    ${server-property://com.liferay.portal/social.bookmark.types}
    
the `server-property` prefix specifies that this value is set through a server
property. You can view the default value by going to *Configuration* &rarr; 
*Server Administration* and selecting the *Properties* tab and 
*Portal Properties* sub-tab. From here you can Search for the property you 
want(e.g., `social.bookmark.types`) to see the default value. To overwrite the 
property's default value, add the updated property values to the 
`portal-ext.properties` file in your app server's root directory.

The second kind of variable prefix is `resource`. For example, the
*Email KBArticle suggestion in progress body* setting in the Knowledge Base 
Service configuration menu has the following value:

    ${resource:com/liferay/knowledge/base/dependencies/email_kb_suggestion_in_progress_body.tmpl}

This points to the resource that provides the value. To overwrite these 
resources, you need to either change the configuration in the settings UI to 
point to your new file, or using a configuration file in your app server's 
`osgi/config` directory, you need to overwrite the property to point to the 
absolute file path of the new file. You can view the current configuration
files and settings by exporting the configuration file. This is covered more in
the [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations) 
User Guide doc.

$$$
   
-  **Email from name:** Sets the Sender's name for automated emails.
   
-  **Email from address:** Sets the Sender's email address for automated emails.
   
-  **Email KBArticle [name] enabled:** These properties set whether to enable
   automated emails for the named actions 
   (e.g., *Email KBArticle added enabled*, if enabled, sends an automated email 
   when an article is added).
   
-  **Email KBArticle [action] [section]:** These properties set the email
   templates for the named action and corresponding section
   (e.g., *Email KBArticle added body* sets the body template for the kb article 
   added email.)
   
-  **Rss delta:** Sets the pagination for RSS entries.

-  **Rss display style:** Sets the display style of the RSS. Possible values are 
   `abstract`, `full-content`, or `title`.
   
-  **Rss format:** Sets the web feed language to use for your RSS feed. Possible 
   values are `Atom10` (Atom 1.0 the default), `RSS10`(RSS 1.0), or 
   `RSS20`(RSS 2.0).
   
-  **Rss feed type:** Sets the default feed type from the feed types defined in 
   the `rss.feed.types` portal property.

### Knowledge Base Article Configuration Menu [](id=knowledge-base-article-configuration-menu)

-  **Show/Enable KBArticle [element]:** Sets whether to enable or show the named
   kb article element.

### Knowledge Base Display Configuration Menu [](id=knowledge-base-display-configuration-menu)

The list below describes what each configuration option does in the System
Settings UI.

-  **Show/Enable KBArticle [element]:** Sets whether to enable or show the named
   kb article element.

-  **Content root prefix:** Sets the default value for the content root prefix.

### Knowledge Base Search Configuration Menu [](id=knowledge-base-search-configuration-menu)

-  **Show/Enable KBArticle [element]:** Sets whether to enable or show the named
   kb article element in the search results.
   
### Knowledge Base Section Configuration Menu [](id=knowledge-base-section-configuration-menu)

<div class="system-settings-table">
<div class="max-960">
<div class="aui-helper-clearfix section-1" >
<div class="aui-w100 block-1 content-column" >
<div class="content-column-content">
<table>
	<thead>
		<td class="table-header left-header">
			portlet property
		</td>
		<td class="table-header second">
			System Setting
		</td>
	</thead>
	<tbody>
		<tr>
			<td>
				        admin.kb.article.sections
			</td>
			<td>
					adminKBArticleSections
			</td>
		</tr>
		<tr>
			<td>
				        admin.kb.article.sections.default
			</td>
			<td>
					adminKBArticleSectionsDefault
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

The list below describes what each configuration option does in the System
Settings UI.

-  **kb articles sections:** Sets the default available sections with a 
   comma-separated list.
-  **kb article display style:** Sets the default kb article display style. 
   Possible values are `title` or `abstract`.
   
-  **show/enable KBArticle [element]:** Sets whether to enable or show the named
   kb article element.
   
-  **Admin KBArticle sections:** Sets the kb article sections available in the
   admin portlet.
   
-  **Admin KBArticle sections default:** Sets the default selected section in 
   the admin portlet.

### Shared Configuration Menu Settings [](id=shared-configuration-menu-settings)

Here is a listing of the shared configuration settings that you can set for
each knowledge base app and the possible values:

-  **Resource Prim key:** Sets the primary key that identifies a particular 
   kb(Knowledge Base) article or kb folder 
   (depending on the resource class name ID).
   
-  **Resource class name id:** Sets the class name of either the kb article or 
   kb folder. This value needs to match the value of the `Resource Prim key`. If 
   the classname is a kb article, the resource prim key needs to be the prim key 
   of a kb article. If the class name belongs to a kb folder the resource prim 
   key needs to belong to a kb folder.
   
-  **Enable KBArticle description:** Sets whether to allow users to add a 
   description to the kb articles.
   
-  **Enable KBArticle view count increment:** sets whether to add a view counter 
   to the kb article.
   
-  **Enable social bookmarks:** Sets whether to display social bookmarks 
   when viewing a kb article.
   
-  **Social bookmarks display style:** Sets the display style of the social
   bookmarks. Possible values are `menu`, `simple`, `vertical`, or `horizontal`.
   
-  **Social bookmarks display position:** Sets the position to display social 
   bookmarks. Possible values are `top` or `bottom`.
   
-  **Social bookmarks types:** A comma-separated list of the possible bookmarks 
   types (twitter,facebook,plusone for example). Overwrite with a 
   `portal-ext.properties` file.
    
Next, Liferay administrators can learn how to override the Knowledge Base app's 
properties to enable and configure some advanced, but optional features.
   
### Optional Configuration Features [](id=optional-configuration-features)

The Knowledge Base app has several optional features that are disabled by 
default. These include source URL, import file conventions, new article priority 
increment, and sections. Now you'll learn how to configure these features by 
editing the Knowledge Base app's system settings. Advanced configuration should 
only be performed by a Liferay administrator, as the person modifying the 
configuration must be comfortable with using the Control Panel to configure
system settings.

**Source URL Settings:** Defines the source location of importable Markdown
files. This is intended to point to an online source repository where the files
are stored. GitHub is assumed as the default. Once defined, the Knowledge Base
displays a button (default label is *Edit on GitHub*) above each displayed
article. Users can click the button to navigate to an article's source location.
To enable this feature, click the *Knowledge Base Service* configuration menu 
link in *System Settings* &rarr; *Collaboration* and click the checkbox next to 
the *Source URLEnabled* label.

To change the button's default label *Edit on GitHub*, specify a new label as
the value for the *Source URLEdit message key* setting. Best practice is to 
specify the value as a language key. For example, if you create a language key 
`edit-on-bitbucket=Edit on Bitbucket`, you can specify that language key as the 
button's new label:

    edit-on-bitbucket

Alternatively, you can specify the label explicitly:

    Edit on Bitbucket

**Importer File Convention Settings:** These settings define the supported file 
extensions, the suffix for parent files, and the image folder's path within the 
ZIP file. To modify these settings, click the *Knowledge Base Service* link in 
*System Settings* &rarr; *Collaboration*.

You can modify the supported file extensions by adding a new field(with the plus 
button on the side of the setting) for the settings below and entering a new 
value:

*Markdown importer article extensions*(.markdown or .md for example)
*Markdown importer image file extensions*(.bmp, .gif, .jpeg, .jpg, or .png for
example)

You can change the article parent file suffix: 

*Markdown importer article intro*

You can modify the image folder path the importer looks for in the ZIP file:

*Markdown importer image folder*

**New Article Priority Increment Setting:** By default the priority for new
articles is incremented by `1.0`. To disable this increment so that articles get
a flat value of `1.0`, click the *Knowledge Base Service* link in 
*System Settings* &rarr; *Collaboration* and uncheck the checkbox for this 
setting:

*Article increment priority enabled*

Alternatively, you can enable or disable this feature for each app in the 
corresponding Knowledge Base app configuration menu in System Settings.

**Section Names Setting:** The section names setting lets you specify names of 
arbitrary topics to attribute to articles. Using the Knowledge Base Section app, 
you can display one or more *sections* (groups) of articles. Navigate to the 
*Knowledge Base Section* configuration menu from *System Settings* &rarr; 
*Collaboration*, click the plus button next to the *Admin KBArticle sections* 
setting to add a new field for each section you want, and enter a name for each 
section:

*Admin KBArticle sections*(Business, Politics or World for example)

This creates the sections *Business*, *Politics*, and *World*. 

Click the *Update* button to save your settings.

Your app now uses the settings you specified!

You've learned the benefits of publishing articles using the Knowledge Base
app. You've seen how easy it is to create, organize, and manage articles, and 
you've learned various ways to present articles in the Knowledge Base Display, 
Knowledge Base Article, and Knowledge Base Section apps. You can now consider 
yourself truly *knowledgeable* of Liferay's Knowledge Base app. 
