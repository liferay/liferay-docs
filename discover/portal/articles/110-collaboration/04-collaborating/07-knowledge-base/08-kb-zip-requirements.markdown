# Knowledge Base ZIP File Requirements [](id=knowledge-base-zip-file-requirements)

The Knowledge Base importer supports article hierarchies, so Markdown files can 
be specified anywhere in the ZIP file's directory structure. They can be nested 
in any number of folders. Image files are the only files supported for 
attachments. 

+$$$

**Note:** Imported articles are independent of the workflow settings. This 
means that **imported articles are automatically approved.**

Only users with the *Import Articles* permission assigned to their Role are 
able to import articles. This permission can be assigned manually through 
*Control Panel* &rarr; *Users* &rarr; *Roles*. If you've upgraded from Liferay 
Portal 6.2, you can also assign this Role to every Role that was already able to 
add articles with a command from the Gogo shell. 

Open the
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
Type `knowledgeBase:addImportArticlePermissions` and hit enter. 

$$$

The ZIP file's articles are imported in file order (alphanumerically). To 
designate an article's priority, add a numeric prefix to its file name. For 
example, the priorities for articles named `01-file.markdown` and 
`02-file.markdown` become `1.0` and `2.0`.

To designate an article to be the parent of all other articles in the same 
source folder, end its file name with `-intro.markdown`. This creates a 
parent-child hierarchy. You can use the prefix `00` for parent articles to place 
them at the top of the folder's file order. The importer uses the numeric prefix 
of an intro file's folder as its article priority. 

Here's the underlying logic for the `00` prefix: 

-   A file prefix of `00` for a non-intro file assigns the resulting article's 
    priority to `1.0`.
-   A file prefix of `00` for a top-level intro file sets the article's priority 
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
    - `03-lunar-olympics/`
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

-  Each ZIP file must end in the suffix `.zip`.
-  Each ZIP file must contain at least one Markdown source file, optionally 
   organized in folders.
-  All referenced image files must be in a folder named `images` in the ZIP 
   file's root. 
-  Image files must be in a supported format and must use the appropriate file
   extensions. Supported extensions are `.bmp`,`.gif`,`.jpeg`,`.jpg`, and 
    `.png`. They're specified via an app system setting. For details, see 
   [Knowledge Base System Settings](/discover/portal/-/knowledge_base/7-1/knowledge-base-system-settings).

Once you have your article ZIP file, it's time to import it.

Follow these steps to import your ZIP file:

1.  In the Menu 
    (![Menu](../../../../images/icon-menu.png)), 
    navigate to *Site Administration* (the menu for your site) &rarr; *Content* 
    &rarr; *Knowledge Base* &rarr; *Articles*. 

2.  Click *Add* 
    (![Add](../../../../images/icon-add.png)) 
    &rarr; *Import* to bring up the importer page. 

3.  Browse to the location of your file, and in most cases leave the checkbox 
    for the article priorities checked, and then click *Save*. 
 
Your file is uploaded, and the importer converts each source file's Markdown 
text to HTML, applying the HTML to the resulting article. Any image files that 
are referenced in an article and included in the ZIP file are imported as 
attachments to the article. 

![Figure 1: Selecting *Add* &rarr; *Import* in Knowledge Base brings up the interface for selecting a ZIP file of Markdown source files and images to produce and update articles in your Knowledge Base.](../../../../images/kb-admin-import.png)

In addition to source files and images, you can configure a base source URL 
system setting for the importer that specifies your source file's repository 
location. Each article's *Edit on GitHub* button (if enabled) takes the user to 
the source location. The importer prefixes each file's path with the base source 
URL. This constructs a URL to the article's repository source location; it looks 
like `[base URL]/[article file path]`. Here's an example base source URL: 

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

The source URL constructed from this base URL and article source file 
`folder-1/some-article.markdown` would be:

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

You specify the base source URL in a file called `.METADATA` in the ZIP file's
root folder. The importer treats the `.METADATA` file as a standard Java
properties file and uses the base source URL to construct the source URL for
all of the ZIP file's resulting articles. 

To use the source URL feature, your administrator must enable it via the 
[Knowledge Base System Settings](/discover/portal/-/knowledge_base/7-1/knowledge-base-system-settings). 
