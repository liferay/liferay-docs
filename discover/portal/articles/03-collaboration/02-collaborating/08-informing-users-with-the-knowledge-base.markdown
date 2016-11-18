# Informing Users with the Knowledge Base [](id=informing-users-with-the-knowledge-base)

Liferay's Knowledge Base app lets you create and organize articles in a 
site. For example, it can be used to display professional product documentation 
or form complete books or guides. It even lets you import article source files 
written in Markdown. It's [workflow-enabled](/discover/portal/-/knowledge_base/7-0/using-workflow), 
so you can require articles to be approved before they are published. 
Additionally, the Knowledge Base app lets administrators create article 
templates that help users follow a common outline. With this application, you
can create and manage your own knowledge base, full of information relevant to
your users.

Here are the key features of the Knowledge Base:

- Its navigation is built into the Knowledge Base Display. 
- It has a suggestions interface for article feedback.
- It stores articles in folders.
- It contains metadata fields for friendly URL, source URL, categorization, and related assets.
- The button (![*Edit on GitHub*](../../../images/icon-edit-on-github.png))
  can be enabled to take readers to an article's source repository location (if
  you choose to use it that way).
- It imports article Markdown source files to create and update articles.

The Knowledge Base has several portlets you can add to site pages:

- Knowledge Base Article 
- Knowledge Base Display 
- Knowledge Base Search 
- Knowledge Base Section 

Each of these is covered below. First you'll learn how to create and manage 
articles. 

## Creating and Managing Articles [](id=creating-and-managing-articles)

Site Administration contains everything that administrators and authors need to
create, update, and manage articles in the Knowledge Base.

To navigate to the Knowledge Base, go to Site Administration &rarr; *Content* 
&rarr; *Knowledge Base*. It has two tabs: one for creating and managing articles 
and article folders (Articles) and one for managing user-submitted feedback for 
articles (Suggestions).

![Figure 1: You can manage Knowledge Base articles, folders, and suggestions from the Knowledge Base in Site Administration.](../../../images/kb-admin-articles.png)

+$$$

**Note:** To access Knowledge Base in Site Administration, a role must have the 
permission *Knowledge Base* &rarr; *Access in Site Administration*. To add or 
act on articles, folders, or suggestions, the site administrator must grant the 
appropriate permissions using the Permissions window in Knowledge Base.

$$$

To create an article follow these steps:

1.  Select the *Articles* tab and choose *Basic Article* or the name of an 
    available template in the Add button (![Add](../../../images/icon-portlet-add-control.png)) 
    menu.
    
    This brings up the New Article form.
 
2.  Enter a title for the article.
 
    A URL-safe version of the title you provide is added to the end of the 
    article's friendly URL. You can manage the friendly URL in the 
    *Friendly URL* field underneath the *Configuration* section heading.
 
3.  Use the WYSIWYG editor to create the article's content.

    ![Figure 2: You can create and modify a Knowledge Base article's content using the WYSIWYG editor.](../../../images/kb-admin-new-article.png)

    Click the *Source* button in the editor to view the article's HTML source 
    or write in HTML yourself. You can also add attachments and tags, specify 
    related assets, and set permissions for the article in the sections below
    the editor. By default, View permission is granted to the Guest role, 
    meaning anyone can view your article.

4.  Click *Publish* to submit the article for publication or click 
    *Save as Draft* to continue working on it later.
    
    **Note: If you've enabled workflow for the Knowledge Base, your article 
    must be approved before publication.**
 
Once the article is saved, it is converted automatically to HTML for the 
Knowledge Base. Articles are listed in a table at the bottom of the Articles tab.

Each article also has a *priority* value that determines its position in the 
Knowledge Base Display app's (more on this app in a moment) navigation. Each
article's priority value is listed underneath the article's title. The Knowledge
Base Display app's navigation arranges articles in ascending priority.  Priority
1 is the highest priority; think "Priority 1 distress call!" The higher an
article's priority, the higher it appears in the navigation. Articles are
assigned the next lowest priority by default. This behavior can be changed via
[System Settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#importer-file-convention-settings).

To assign articles a new priority value, follow these steps:

1.  Select *Move* from the Actions menu next to the article.

2.  Enter a new priority value for the article.

3.  Click *Move* to apply the new priority.

You can also organize articles with folders. Follow these steps to create a 
folder:

1.  Open the Add button menu and select *Folder*.
 
2.  Enter a name and an optional description.

    By default, anyone can view the folder. You can manage this setting along 
    with the other permissions through the *Permissions* heading.
 
3.  Click *Save*.

    Folders are listed in a table near the top of the Articles tab.

The text immediately below the filters and *Order by* selector at 
the top of the app shows your position in the folder hierarchy. Click on a 
folder's name in the hierarchy to navigate to it. You can also move articles 
into folders and create child articles. Knowledge Base also supports nested
folders.

![Figure 3: This screenshot uses a red box to highlight the text that indicates the current position in the folder hierarchy.](../../../images/kb-admin-folder-hierarchy.png)
 
You can perform the following actions on folders: 

- **Edit:** change the folder's name and description.

- **Move:** relocate the folder under a new parent folder or update its
  priority. 

- **Delete:** remove the folder and its articles from the knowledge base. 

- **Permissions:** grant or revoke the following permissions: add an article to 
  the folder, add a sub-folder to the folder, delete the folder, move the 
  folder, set permissions on the folder, edit (update) the folder, and view the 
  folder.

You can also delete multiple articles or folders at once:

Select the checkbox for each item that you want to delete and click the *X* 
button that appears at the top of the app to delete it.

This Actions menu also lets you check the info for selected items. Note that 
this menu only appears when one or more article or folder checkboxes are checked.

The (![Add](../../../images/icon-portlet-add-control.png)) menu provides the 
following options for articles:

- Add a Basic Article
- Add an article based on a template
- Add a folder
- Import articles from a ZIP file

+$$$

**Note:** To import articles, your role must be granted the *Knowledge Base* 
&rarr; *Resource Permissions: Import Articles* permission.

$$$

The Options menu (![Options](../../../images/icon-app-options.png)) has these
functions:

- Manage templates
- subscribe
- edit permissions
- import/export configuration settings
- configure email options

The *Home Folder Permissions* option lets you define detailed permissions for 
the Knowledge Base app. You can choose the roles that can perform the following 
tasks: 

- Add/delete articles, folders, and templates 
- Change the Knowledge Base app's permissions 
- Subscribe to articles
- View templates and suggestions

Next, you'll learn how to manage article templates. 

### Creating and Managing Templates [](id=managing-templates)

Templates give users a starting point to work from. For example, you can create 
templates that contain default headers or other content for articles. Templates 
help foster consistent formatting and content organization for articles.

To create a new template follow these steps:

1.  Click the *Templates* button in the Options menu (![Options](../../../images/icon-app-options.png)) 
    at the top of the app.
    
    This brings up the Templates page, where you can manage existing templates.
 
2.  Click the Add button at the bottom of the screen to add a template.

3.  Fill in the New Template form and click *Publish*.

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

When you move the suggestion to a different state, an email is sent notifying
the user of the change. To view and configure the templates for the automated
emails, click the *Configuration* button in the Options menu
(![Options](../../../images/icon-app-options.png)).

So far, you've learned how to create, edit, and organize articles. You've also
seen how the suggestions feature lets you and your users collaborate on your
content. Next, you'll learn how the other Knowledge Base apps let you display,
navigate, aggregate, and view articles. 

## Knowledge Base Display [](id=knowledge-base-display)

You can use the Knowledge Base Display app to display your published articles.
Knowledge Base Display is flexible. You can customize how it displays articles,
as well as which ones it displays. Add the Knowledge Base Display to the page
where you're publishing your articles. 

To display an article or folder of articles, follow these steps:

1.  Mouse over the app, click the *Options* icon (![Options](../../../images/icon-app-options.png)) 
    in the top right corner, and select *Configuration*.

2.  Click *Select* in the *Article or Folder* field to bring up the available
    folders and articles.

3.  Click *Choose* next to the article or folder of articles you want to
    display, and *Save*.
    
    You can also click the *Choose This Folder* button at the top of the Select 
    Entry dialog to select the current folder.
 
Knowledge Base Display also provides these common configuration options:

- look and feel
- export/import
- permissions
- scope
- sharing

For more information on this, see the section on configuring Liferay 
applications in [Web Experience Management](/discover/portal/-/knowledge_base/7-0/web-experience-management).

The Knowledge Base Display's navigation menu and display options make it the 
perfect candidate for a full page app. If you display a folder of articles, the 
navigation on the left side of the app displays links to all the folder's 
articles. The viewing area on the right side of the app displays the folder's 
leading article (the "priority one" article). Click an article in the navigation 
to display it in the viewing area. The currently displayed article's link 
appears in bold in the navigation. You can also move between articles by 
clicking the links at the bottom of the app.

![Figure 6: Knowledge Base Display's navigation and viewing area facilitates viewing article hierarchies.](../../../images/kb-display.png)

Knowledge Base Display can also show article hierarchies. Viewing an article 
that has child articles expands the navigation tree to show links to the child 
articles. Any expanded nodes collapse when you view a different top level 
article.

The links at the top of the app allow users to perform the following actions on 
an article:

- Subscribe to an RSS feed of the Knowledge Base
- subscribe to the current article
- view the current article's history
- Print the current article 

Administrators have access to an additional set of links at the top of the app
that allows them to perform the following actions:

- Edit the article
- Add a child article
- Assign permissions to the article
- Move the article
- Delete the article

Below the article's content is the rating interface, showing thumbs up and 
thumbs down icons. Users can also submit suggestions or comments below the 
article in the *Do you have any suggestions?* text box. Administrators can view 
the suggestions and mark progress.

If your Liferay administrator enables the Knowledge Base app's source 
URL feature (more on this in a moment) and an article has an assigned source 
URL, an *Edit on GitHub* button (![GitHub](../../../images/icon-edit-on-github.png)) 
appears to the right of the article's title. This button lets users access the 
article's source location. You can use this feature to encourage users to 
contribute fixes or improvements to articles. If you're interested in this 
feature, you can direct your administrator to follow the instructions in 
[System Settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#system-settings). 

Great! Now you know the basics of how Knowledge Base Display works. Next, you'll 
learn how to let users choose between different article sets in different 
folders, without giving them access to the app's Configuration menu. 

### Displaying Different Article Sets [](id=displaying-different-article-sets)

As an administrator, say that you've used folders to aggregate similar articles,
and you want to provide an easy way for users to switch between these sets of
articles. The Knowledge Base Display's content folder feature adds a selector to 
the top of the navigation that lets users switch between article sets.
 
To set up content folders follow these steps:

1. Add a folder in Knowledge Base. Then create sub-folders in this folder. These 
   sub-folders are the *content folders*. 

2. Add articles to each content folder.

3. Select *Configuration* from Knowledge Base Display's *Options* menu. In the 
   *Setup* &rarr; *General* tab, select the content folders' parent folder and 
   click *Save*. 

The content selector's values reflect the names of your content folders. Select 
one to view its articles.

![Figure 7: Knowledge Base Display's content folder feature lets users switch between different sets of articles.](../../../images/kb-display-content-selector.png)

You can also add a common prefix to the names shown in the selector:

1. Navigate to Knowledge Base Display's *Configuration* dialog and select the 
   *Setup* &rarr; *Display Settings* tab.
 
2. Enter the prefix into the *Content Root Prefix* field and click *Save*. 

Awesome! Now you know how to use Knowledge Base Display to display several 
sets of articles. Next, you'll learn how to use the Knowledge Base Article app
to display only individual articles. 

## Knowledge Base Article [](id=knowledge-base-article)

The Knowledge Base Article app lets you show a single article's content. It even 
shows abstracts of child articles. You can add multiple instances of the 
Knowledge Base Article app to a page, and each one can show a different 
article.

To display an article follow these steps:

1.  Click the *Please configure this portlet to make it visible to all users* 
    link, choose an article, and click *Save*.
 
2.  Refresh the page to display the article.

    You can also use the *Setup* &rarr; *General* tab of the app's 
    *Configuration* menu to choose an article to display. 

![Figure 8: The Knowledge Base Article app is great at displaying individual articles.](../../../images/kb-article.png)

Knowledge Base Article shares the same UI as the Knowledge Base Display to 
display and manage its articles. Please refer to the [Knowledge Base Display](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#knowledge-base-display) 
section for a detailed description of the app's UI.

Now that you know how to publish individual articles with the Knowledge Base
Article app, you can learn how to selectively display articles with the 
Knowledge Base Section app next.

## Knowledge Base Section [](id=knowledge-base-section)

+$$$

**Note:** as of Knowledge Base 3.0.0, the Knowledge Base Sections app is 
deprecated and replaced by Liferay Categories.

$$$

The Knowledge Base Section app lets you publish articles associated with a 
specific topic (section). For example, a news site might have the sections 
*World*, *Politics*, *Business*, and *Entertainment*.

![Figure 9: Here's an image of Knowledge Base Section apps on a page.](../../../images/kb-section.png)

To use sections, your Liferay administrator must configure the feature in the 
Knowledge Base app's properties within @product@'s System Settings. Once the 
feature is enabled, he or she must specify in the System Settings any section 
names you want to use. This process is covered in more detail in the 
[System Settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#section-names-setting)
section.

Multi-select boxes in the Knowledge Base app's Add Article and Edit Article
screens let you define the section(s) the articles belong to. You can add
multiple instances of the Knowledge Base Section app to a page and each one can
display articles from any number of sections. You can choose to display article
titles or abstracts. You can also define whether to show pagination or section
titles.

Follow these steps to configure an instance of the Knowledge Base Section app:

1.  Select *Configuration* from the Knowledge Base Section's *Options* menu.

2.  Select the section or sections (by holding down the shift key) that you want to
    use and click *Save*.
 
3.  Close the Configuration window to see the updates.

The matching articles are displayed in the app beneath their section heading.

Now that you know how to use the Knowledge Base Section app to organize your
articles, you can learn how to use the Knowledge Base Search app to quickly
locate your articles next.

## Knowledge Base Search [](id=knowledge-base-search)

+$$$

**Note:** as of Knowledge Base 3.0.0, the Knowledge Base Search app is 
deprecated and replaced by Liferay Search.

$$$

Even though the Knowledge Base can show the structure of its articles, it may be 
difficult to find exactly what you're looking for by browsing. That's where the 
Knowledge Base Search app comes in.

Enter your search term and press the *Search* button. The results are displayed
in a table with the following criteria for each matching article:

- title
- author
- create date
- modified date
- number of views

You can select the criteria to display in the app's Configuration dialog. 

![Figure 10: The Knowledge Base Search app helps you search the knowledge base for keywords.](../../../images/kb-search.png)

Next, you'll learn how to create new articles and update existing articles by 
importing them from Markdown source files. 

## Importing Articles from Markdown Source Files [](id=importing-articles-from-markdown-source-files)

As mentioned earlier, the Knowledge Base app can import articles in bulk. This 
lets you have an offline process where articles are prepared ahead of time 
before they're published. Articles are imported into the Knowledge Base as 
[Markdown](http://commonmark.org) files. Markdown is a text-only file format
that is easy to read, yet supports all the things you'd need to do to format
your articles. 

+$$$

**Note:** To import articles, your role must be granted the *Knowledge Base* 
&rarr; *Resource Permissions: Import Articles* permission.

$$$

The Knowledge Base supports a Markdown dialect known as [Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/). 
This dialect extends the original Markdown with features like table formatting, 
image captions, and footnotes.

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

    Its two moons are aptly named after the two sons of mythical Roman god Mars.
    Their names are Phobos and Deimos.

In the first line above, notice the header's ID assignment
`id=the-moons-of-mars`. On import, the ID value becomes the Knowledge Base
article's URL title.

Markdown is something of a standard: there's 
[Github Flavored Markdown](https://help.github.com/articles/github-flavored-markdown), 
a proposed [common Markdown syntax](http://www.commonmark.org), 
forums that support Markdown (reddit, StackExchange, and others), Markdown
editors, and an [IETF draft](https://tools.ietf.org/html/rfc7763) for making it
an official Internet media type (text/markdown). Why is there so much interest
in Markdown? 

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

Once your markdown files are ready to import, you must zip them up with their
accompanying image files. This is covered next.

### Zip File Requirements [](id=zip-file-requirements)

The importer supports article hierarchies, so Markdown files can be specified 
anywhere in the ZIP file's directory structure. They can be nested in any number 
of folders. Image files are the only files supported for attachments. 

+$$$

**Note:** Imported articles are independent of the workflow settings. This 
means that **imported articles are automatically approved.**

Only users with the *Import Articles* permission assigned to their role are 
able to import articles. This permission can be assigned manually through 
*Control Panel* &rarr; *Users* &rarr; *Roles*. If you've upgraded from 6.2, you
can also assign this role to every role that was already able to add
articles with a command from the Gogo shell.

Open the Gogo shell: `telnet localhost 11311`. Type 
`knowledgeBase:addImportArticlePermissions` and hit enter.

$$$

The ZIP file's articles are imported in file order (alphanumerically). To 
designate an article's priority, add a numeric prefix to its file name. For 
example, the priorities for articles named `01-file.markdown` and 
`02-file.markdown` become `1.0` and `2.0`.

To designate an article to be the *parent* of all other articles in the same 
source folder, end its file name with `-intro.markdown`. This creates a 
parent-child hierarchy. You can use the prefix `00` for parent articles to place 
them at the top of the folder's file order. The importer uses the numeric prefix 
of an intro file's folder as its article priority.

Here's the underlying logic for the `00` prefix:

- A file prefix of `00` for a non intro file assigns the resulting article's 
  priority to `1.0`.

- A file prefix of `00` for a top-level intro file sets the article's priority 
  to the first folder numeric prefix found that is `1.0` or greater.

This convention lets you specify priorities for top-level (non-child) articles 
in your hierarchy.

When importing, keep the checkbox labeled *Apply numerical prefixes of article
files as priorities* selected. If a file doesn't have a prefix, its article gets
the next available priority (the highest current priority, plus one).

Below is an example ZIP file structure that demonstrates the features mentioned 
so far:

**ZIP File Structure Example:**

- `01-winter-events/`
    - `00-winter-excursions-intro.markdown`
    - `01-star-dust-snow-shoeing.markdown`
    - `02-lunar-alpine.markdown`

- `02-summer-events/`
    - `00-summer-excursions-intro.markdown`
    - `01-lunar-rock-scrambling.markdown`
    - `02-extra-terrestrial-mountain-biking.markdown`
    - `03-summer-olympics/`
        - `00-lunar-olympics-intro.markdown`
        - `01-zero-gravity-diving.markdown`

- `images/`
    - `some-image.png`
    - `another-image.jpeg`

The above ZIP file specifies `00-winter-excursions-intro.markdown` as the parent 
of its neighboring Markdown files: `01-star-dust-snow-shoeing.markdown` and 
`02-lunar-alpine.markdown`. Likewise, `00-lunar-olympics-intro.markdown` is the
parent of `01-zero-gravity-diving.markdown`. `00-lunar-olympics-intro.markdown` 
is also the peer of `01-lunar-rock-scrambling.markdown` and 
`02-extra-terrestrial-mountain-biking.markdown`, and the child of 
`00-summer-excursions-intro.markdown`.

**ZIP Example's Resulting Relationships and Priorities**

- `01-winter-events/00-winter-excursions-intro.markdown`
    - **Article:** Winter Excursions
    - **Relationship:** Peer of *Summer Excursions*
    - **Priority:** **1.0**
- `01-winter-events/01-star-dust-snow-shoeing.markdown`
    - **Article:** Star Dust Snow Shoeing
    - **Relationship:** Child of *Winter Excursions*
    - **Priority:** 1.0
- `01-winter-events/02-lunar-alpine.markdown` 
    - **Article:** Lunar Alpine
    - **Relationship:** Child of *Winter Excursions*
    - **Priority:** 2.0
- `02-summer-events/00-summer-excursions-intro.markdown`
    - **Article:** Summer Excursions
    - **Relationship:** Peer of *Winter Excursions*
    - **Priority:** **2.0**
- `02-summer-events/01-lunar-rock-scrambling.markdown`
    - **Article:** Lunar Rock Scrambling
    - **Relationship:** Child of *Summer Excursions*
    - **Priority:** 1.0
- `02-summer-events/02-extra-terrestrial-mountain-biking.markdown`
    - **Article:** Extra Terrestrial Mountain Biking
    - **Relationship:** Child of *Summer Excursions*
    - **Priority:** 2.0
- `02-summer-events/03-summer-olympics/00-lunar-olympics-intro.markdown`
    - **Article:** Lunar Olympics
    - **Relationship:** Child of *Summer Excursions*
    - **Priority:** 3.0
- `02-summer-events/03-summer-olympics/01-zero-gravity-diving.markdown`
    - **Article:** Zero Gravity Diving
    - **Relationship:** Grandchild of *Summer Excursions*
    - **Relationship:** Child of *Opening Ceremonies*
    - **Priority:** 1.0

ZIP files must meet the following requirements:

-  Each Zip file must end in the suffix `.zip`.
-  Each Zip file must contain at least one Markdown source file, optionally
   organized in folders.
-  All referenced image files must be in a folder named `images` in the Zip
   file's root. 
-  Image files must be in a supported format and must use the appropriate file
   extensions. Supported extensions are `.bmp`,`.gif`,`.jpeg`,`.jpg`, and
   `.png`. They're specified via an app system setting. For details, see 
   [System Settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#system-settings).

Once you have your article ZIP file, it's time to import it.

Follow these steps to import your ZIP file:

1.  Navigate to *Site Administration* &rarr; *Content* &rarr; *Knowledge Base* 
    &rarr; *Articles*.
 
2.  Click on *Add* &rarr; *Import* to bring up the importer page.

3.  Browse to the location of your file, and in most cases leave the checkbox 
    for the article priorities checked, and then click *Save*.
 
Your file is uploaded, and the importer converts each source file's Markdown 
text to HTML, applying the HTML to the resulting article. Any image files that 
are referenced in an article and included in the ZIP file are imported as 
attachments to the article.

![Figure 11: Selecting *Add* &rarr; *Import* in Knowledge Base brings up the interface for selecting a ZIP file of Markdown source files and images to produce and update articles in your Knowledge Base.](../../../images/kb-admin-importer.png)

In addition to source files and images, you can configure a base source 
URL system setting for the importer that specifies your source file's repository
location. Each article's *Edit on GitHub* button (if enabled) takes the user to
the source location. The importer prefixes each file's path with the base source
URL. This constructs a URL to the article's repository source location; it looks
like `[base URL]/[article file path]`. Here's an example base source URL:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

The source URL constructed from this base URL and article source file 
`folder-1/some-article.markdown` would be:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

You specify the base source URL in a file called `.METADATA` in the Zip file's
root folder. The importer treats the `.METADATA` file as a standard Java
properties file and uses the base source URL to construct the source URL for
all of the Zip file's resulting articles.

To use the source URL feature, your Liferay administrator must enable it via the 
Knowledge Base app's [System Settings](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#system-settings).

Now that you know how to import articles into the Knowledge Base, you may have
some questions about the importer. Some of the common questions and answers are 
covered in the next section.

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

- **If I change an article's title, should I also change its header ID?** It
depends on whether you've already published your article. If it hasn't been
published, then there are no public links to it, so it's fine to change the 
header ID. If the article is already published, you must decide whether it's 
worth breaking existing links to the article, and worth having search engines
rediscover and re-rank your article based on its new friendly URL. The new 
friendly URL is based on the new header ID. 

Congratulations on mastering the art of creating and importing Markdown files to
produce Knowledge Base articles! 

Next, Liferay administrators can learn how to override the Knowledge Base app's 
System Settings to enable and configure features. 

## System Settings [](id=system-settings)

Liferay administrators can configure the Knowledge Base across sites using
System Settings. To access its settings, open the Control Panel, navigate to
*Configuration* &rarr; *System Settings*, and click on the *Collaboration* link.
There are five sections of Knowledge Base configuration settings: 
 
- *Knowledge Base Article*
- *Knowledge Base Display*
- *Knowledge Base Search*
- *Knowledge Base Section*
- *Knowledge Base Service*

The *Knowledge Base Service* section's settings apply defaults to all the
Knowledge Base apps and to the Knowledge Base in Site Administration. The other
sections apply to specific Knowledge Base apps and override the *Knowledge Base
Service* defaults. 

+$$$

**Important:** Advanced configuration of the Knowledge Base application's system
settings should only be performed by a Liferay administrator. 

$$$

The Knowledge Base has several optional features that are disabled by default,
but can be enabled and configured from System Settings. These include source
URL, import file conventions, new article priority increment, and sections. Now
you'll learn how to configure these features by editing the Knowledge Base
System Settings. 

### Source URL Settings [](id=source-url-settings)

The source URL settings define the source location of importable Markdown
files. This is intended to point to a source repository where the files are
stored. GitHub is assumed as the default. Once defined, the Knowledge Base
displays a button (default label is *Edit on GitHub*) above each displayed
article. Users can click the button to navigate to an article's source location.

The source URL settings are accessible in the *Knowledge Base Service*
System Settings section. To enable using the source URL, check the 
*Source URLEnabled* checkbox.

To change the source URL button's label, specify a new value for the 
*Source URLEditmessage key* setting. Best practice is to specify the value as a 
language key. For example, if you create a language key 
`edit-on-bitbucket=Edit on Bitbucket`, you can specify that language key as the 
button's new label:

    edit-on-bitbucket

Alternatively, you can specify the label explicitly:

    Edit on Bitbucket

Next, you'll learn how to modify the file conventions for the importer.

### Importer File Convention Settings [](id=importer-file-convention-settings)

These settings define the supported file extensions, the suffix for parent
files, and the image folder's path within the import Zip files. These
settings are accessible in the *Knowledge Base Service* System Settings section. 

The following settings specify the supported file extensions for the importer:

- *Markdown importer article extensions* sets the supported article extensions. 
  The default values are `.markdown` and `.md`. 

- *Markdown importer image file extensions* sets the supported image file 
  extensions. The default values are `.bmp`, `.gif`, `.jpeg`, `.jpg`, and `.png`.

To modify the supported file extensions follow these steps:

1.  Click the *+* or *-* button next to the setting to add or remove a supported 
    file extension respectively.
 
2.  Enter a new value, if adding an extension.

3.  Click *Save*.

These settings define additional article configuration options for the importer:
 
- *Markdown importer article intro* sets the article parent file suffix. The
  default value is `intro.markdown`.

- *Markdown importer image folder* sets the image folder path the importer looks
  for in the article Zip file. The default path is `/images`.

- *New Article Priority Increment Setting* sets the floating point value by
  which priorities of new articles are incremented. By default they're 
  incremented by `1.0`. To disable this increment so that articles get a flat 
  value of `1.0`, deselect the checkbox for the setting 
  *Article increment priority enabled*.

  Alternatively, you can enable or disable the article increment priority 
  feature for each app in the corresponding Knowledge Base app configuration 
  menu in System Settings. 

If you're using the Knowledge Base Section app, you'll want to set some section
names. Let's explore that next.

### Section Names Setting [](id=section-names-setting)

The section names setting lets you specify names of arbitrary topics to
attribute to articles. Using the Knowledge Base Section app, you can display one
or more *sections* (groups) of articles. To use sections, you must first define
them in the System Settings for the *Knowledge Base Section* app.

Follow these steps to make new sections available:

1.  Navigate to the *Knowledge Base Section* configuration menu from 
    *System Settings* &rarr; *Collaboration*.
 
2.  Click the plus button next to the *Admin KBArticle sections* setting to add 
    a new field for each section you want.
 
3.  Enter a name for each new section and click *Save*. 

![Figure 12: Adding a new field for the *Admin KBArticle sections* setting creates a new section for the Knowledge Base Section app. These fields create sections *Business*, *Politics*, and *World*.](../../../images/kb-section-setting.png)

Once your sections are added, you can follow the steps in the 
[Knowledge Base Section](/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#knowledge-base-section)
app section to learn how to use them.

This concludes the short tour of a sampling of Knowledge Base configuration
settings.

For a full list of the available System Settings for Knowledge Base see 
[Knowledge Base Configuration](/discover/reference/-/knowledge_base/7-0/knowledge-base-configuration).

You've learned the benefits of publishing articles using the Knowledge Base app.
You've seen how easy it is to create, organize, and manage articles, and you've
learned various ways to present articles in the Knowledge Base Display,
Knowledge Base Article, and Knowledge Base Section apps. Knowledge Base system
settings are also at your service. You can now consider yourself truly
*knowledgeable* of Liferay's Knowledge Base app.
