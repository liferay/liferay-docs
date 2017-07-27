# Standards and Customizations 

Liferay's documentation is currently implemented in Markdown. We chose Markdown
for several reasons:

1. It's readable. Even if you don't know Markdown, you can read it without
   having to filter out the syntax. 

2. It gets out of a writer's way. You don't have to worry about mousing to
   various icons to change text into a heading or create bulleted lists. Just
   start typing. The syntax is so intuitive, you probably have used it all your
   life anyway.

3. It's easily convertable to other formats. Using Markdown lets us publish to
   the web, mobile, and print from the same source files.

4. It's great for collaborating. Using Github, we can easily see what various
   people have contributed, through the same diffs we'd use in programming. We
   can merge those changes pretty easily, too, because the format is simple enough
   that the changes in the diffs happen to be the actual changes made to the text,
   rather than a bunch of formatting tags.

In summary, Markdown is, by definition, a text based format that's designed to
be as readable as possible. It lets you write in a very natural format which can
then be converted to other formats for publication. We have switched to using
Markdown for the Liferay 6.1 documentation. It's allowing us to single-source
our documentation for the web, for ebooks, and for print.


## Ant Target Quick Reference

Each Ant target described in this section is for Liferay's CE docs only (unless
otherwise noted). Most targets have a DXP version that runs on all CE and DXP
docs. Append `-dxp` to the targets listed below when checking/editing DXP-only
documentation.

Each target should be executed from a document directory in the `liferay-docs`
repo. For example, `/develop/tutorials`, `/discover/portal`, etc.

<!-- compare-with-older-branch/dist-diffs process is broken. Need to fix before
documenting. -Cody -->

-   `add-article-to-temp`: Copies the specified article (e.g., 
    `-Darticle=articles/path/article_name.markdown`) and its supporting
    `intro.markdown` articles and images, to a folder called `temp` and prepares
    them for
    [importing to a Knowledge Base](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/informing-users-with-the-knowledge-base#importing-articles-from-markdown-source-files).
    Optionally use this target
    to add as many articles as you like  to the `temp` folder for putting in the
    distribution.

    Here's an example command sequence that prepares a ZIP file of two articles and their supporting files:

    1.  `ant clean-temp` &rarr; Deletes the `temp` folder.
    2.  `ant add-article-to-temp -Darticle=articles\100-tooling\02-liferay-ide\02-creating-a-liferay-workspace-with-liferay-ide.markdown` 
    3.  `ant add-article-to-temp -Darticle=articles\02-from-liferay-6-to-liferay-7\07-upgrading-plugins-to-liferay-7\04-upgrading-portlet-plugins\02-upgrading-a-servlet-based-portlet.markdown`
    4.  `ant dist-temp` &rarr; Zips up  `temp` folder's articles and images into a 
        ZIP file in the `dist` folder, for importing to a Knowledge Base portlet.
        <br><br>

-   `article-to-html`: Converts a Markdown article to HTML. This target requires
    a `-Darticle` argument, which should point to your Markdown article (e.g.,
    `ant article-to-html
    -Darticle=articles/100-tooling/05-maven/01-installing-liferay-maven-artifacts.markdown`).
    The generated HTML is located in the document directory's `/build` folder.

-   `check-headers`: Checks all Markdown headers to ensure they properly begin
  with `#` characters.

-   `check`: Runs several targets at once to make sure Markdown articles follow
    LDN standards to ensure a successful build and publishing process. This
    target should be executed before every pull request. This target includes
    the `check-headers`, `check-images`, `check-intros`, `number-headers`, and
    `number-images` tasks.

-   `check-article-images`: Checks a specific Markdown article's image paths.
    If an image path (e.g., `../../images/test-pic.png`) does not point to an
    existing image in the `/images` folder, an error is thrown. This target
    requires a `-Darticle` argument, which should point to your Markdown article
    (e.g., `ant check-article-images
    -Darticle=articles/100-tooling/05-maven/01-installing-liferay-maven-artifacts.markdown`).

-   `check-images`: Checks all Markdown articles' image paths in the folder. If
    an image path (e.g., `../../images/test-pic.png`) does not point to an
    existing image in the `/images` folder, an error is thrown.

-   `check-intros`: Checks all directories for an intro file (e.g.,
    `00-intro.markdown`). If a folder does not have an intro article, an error 
    is thrown.

-   `clean-images`: (no DXP target) Deletes images from the `images` and
    `images-dxp` folders that are not used in their corresponding Markdown
    articles.

-   `check-links`: (no DXP target) Checks LDN links (CE articles only) to ensure
    they're pointing at an existing LDN article. A list of invalid links are
    listed if any are found.

-   `dist-article-ce`: Creates a ZIP file for importing the specified article
    (``-Darticle=...``), its images, and supporting structure to a Knowledge
    Base.

-   `dist-ce`: Packages all necessary CE resources into a ZIP file deployable to
    LDN.

-   `dist-dxp`: Packages all necessary DXP resources into a ZIP file deployable 
    to Liferay's Customer Portal.

-   `number-headers`: Numbers all Markdown article headers with a unique header
    ID. If there are any duplicate IDs, an error is thrown. See the
    [Assigning Header IDs](#assigning-header-ids) and
    [Markdown Header ID FAQ](#markdown-header-id-faq) sections for more
    information.

-   `number-images`: Numbers all Markdown articles' images in the folder. Image
    numbers (e.g., *\!\[Figure x:* ) are replaced with the correct image
    numbering if they are incorrect. See the
    [Markdown Image Numbers Tool](#markdown-image-numbers-tool) section for more
    information.

## Tokens

Our documentation is deployed to two separate sites to display CE and DXP
documentation. Instead of having a completely separate folder structure for both
docs, we have all articles that are targeted for both CE and DXP residing in the
`/articles` folder and DXP-only documentation residing in the `/articles-dxp`
folder. Because so many single articles are deployed to two separate sites,
we've created tokens that use one type of string when publishing to one site and
another different string for the second site. The available tokens are listed
below:

**CE Docs**

- `@product@` = Liferay Portal
- `@product-ver@` = Liferay Portal CE 7.0
- `@ide@` = IDE
- `@app-ref@` = https://docs.liferay.com/ce/apps
- `@platform-ref@` = https://docs.liferay.com/ce/portal

**DXP Docs**

- `@product@` = Liferay DXP
- `@product-ver@` = Liferay Digital Enterprise 7.0
- `@ide@` = Developer Studio
- `@app-ref@` = https://docs.liferay.com/dxp/apps
- `@platform-ref@` = https://docs.liferay.com/dxp/digital-enterprise

## Markdown Image Numbers Tool

We have a tool that you can call with Ant that numbers the images in a Markdown
chapter file. While you're writing or editing a Markdown file, you can
just number all the images in that chapter with a lowercase x. For example, your
image tags could take the following form:

    ![Figure x: <image-description>](../../images/<image-name>)

When you are finished working on a file, you can call the `number-images` Ant
task from the parent directory (e.g., `/develop/tutorials`) of the document you
are working on:

    ant number-images

If you're working in a DXP article located in `/articles-dxp`, run the
corresponding DXP Ant task:

    ant number-images-dxp

The DXP task numbers images for articles in `/articles` and `/articles-dxp`.

## Assigning Header IDs

Header IDs help to assure that each uploaded document's web contents have a
unique URL. These IDs not only prevent documents from using the same URLs but
they also help to preserve web content URLs despite changes to header text in
revisions of the document.

Once you've finished creating headers for your article, number your headers
using the Ant task:

    ant number-headers

If you're working in a DXP article located in `/articles-dxp`, run the
corresponding DXP Ant task:

    ant number-headers-dxp

It will fail if header IDs conflict.

Example - Output from header ID conflict

    number-headers:
        [java] Numbering headers for files in ../tutorials/articles ...
        [java] Dup id:liferay-ide file:..\tutorials\articles\100-tooling\02-liferay-ide\01-installing-liferay-ide.markdown line:1 (already used by file:..\tutorials\articles\100-tooling\02-liferay-ide\00-liferay-ide-intro.markdown)
        [java] Exception in thread "main" java.lang.Exception: FAILURE - Duplicate header IDs exist
        [java]     at com.liferay.documentation.util.NumberHeadersSiteMain.main(Unknown Source)

    BUILD FAILED

To resolve a conflict, you *must* be sure to preserve the header ID that existed
first, if that header is a part of the *live* version of the document on
dev.liferay.com. Then, remove the newer header ID from the other header and run
the Ant task again to produce a new unique ID for that header.

## Markdown Tips

Below are some tips for some constructs that are unique to Liferay
documentation.

### Spaces vs. Tabs

Our standard is the opposite of Liferay's code: we use spaces instead of tabs.
Why? Because lists and code blocks in Markdown are much easier to control using
spaces instead of tabs. Please see the documentation for Markdown for further
information on this, or we provide a good example of it when we talk about lists
below.

### Figures

To do figures, you should do it this way:

    ![Figure 1: Logging into Liferay Portal is easy.](../../images/logging-into-liferay-portal.png)

If you're working in a DXP Markdown article, your image should be saved in the
`/images-dxp` folder and the figure path should reflect that folder:

    ![Figure 1: This diagram breaks down the evaluation process for the weather rule.](../../images-dxp/weather-rule-diagram.png)

Using this syntax for figure images causes Pandoc to create the following,
easily styled markup:

	<div class="figure">
	<img src="../../images/01-logging-into-liferay-portal.png" alt="Figure 1.1: Logging into Liferay Portal" />
	<p class="caption">Figure 1.1: Logging into Liferay Portal</p>
	</div>

We've duplicated this behavior in the Pegdown parser that we've implemented.

### Inline Images / Icon Images

An icon's image helps the reader identify the icon in the UI. To use an existing
icon snapshot, check a document's `images/` folder for files ending in
*-icon.png*. Follow these steps to include an icon image inline in your
article's Markdown text:

1.  Take a snapshot of the icon, if one doesn't already exist in the document's
    `images/` folder. Please save the snapshot to the `images/` folder and end
    its name with `-icon.png`.
2.  Crop the image to remove unrelated content from around the icon.
3.  Resize the image's height to no greater than 20 pixels. **Important:** Make
    sure to keep the aspect ratio.
4. In the Markdown text, include the icon image in parentheses.

Inline icon image example in Markdown:

    Click the *Add Blog Entry* icon (![Add Blog Entry](../../images/add-icon.png))
    to bring up the blog entry editor.

Result shown in a Knowledge Base article:

![Inline icon image](images/render-icon-image-inline.png)

### Right Arrows

We are in the habit of using right arrows to denote a series of things a user
can click on, such as Go To -> Control Panel -> Web Content. Open/LibreOffice
would automatically replace the dash and greater-than sign with a right arrow.

We can do the same in Markdown using the HTML code for this character, which is
`&rarr;`. I created a SuperAbbrev in jEdit which transforms `rightarrow` into
`&rarr;`.

### Tables

Because Pegdown does not support the
[Pandoc extension table syntax](http://johnmacfarlane.net/pandoc/README.html#tables),
we use a table syntax similar to
[MultiMarkdown](http://fletcher.github.com/peg-multimarkdown/mmd-manual.pdf)
that supports the following features:

* Cell content alignment (left, right, or center)
* Cells containing links, code, images, and text that is plain, bold, italicized, double-quoted, or single-quoted
* Cells containing strong text, emphasized text, double/single quotes, code, links, and images
* Left alignment (default) with `:--- `
* Right alignment with `: ---:`
* Center alignment with `:---:`

Here is MultiMarkdown-like source for an example table:

    **Table Heading (outside of table)**

	  Column1 |  &nbsp;Column2 | &nbsp;Type  | &nbsp;Example  |
	--------- | :--------------| :---------: | -------------: |
	  foo     | bar            | strong       | **powerful** |
	  foo     | bar            | italics        | *emphasized* |
	  foo     | bar            | double quotes | "Hey you!" |
	  foo     | bar            | single quotes | 'yes' |
	  foo     | bar            | code          | `System.out.println()` |
	  foo     | bar            | link          | [Liferay.com](http://liferay.com) |
	  foo     | bar            | image         | ![tip](../../images/tip-pen-paper.png)|

Here is the table rendered in Github ...

**Table Heading (outside of table)**

  Column1 |  &nbsp;Column2 | &nbsp;Type  | &nbsp;Example  |
--------- | :--------------| :---------: | -------------: |
  foo     | bar            | strong       | **powerful** |
  foo     | bar            | italics        | *emphasized* |
  foo     | bar            | double quotes | "Hey you!" |
  foo     | bar            | single quotes | 'yes' |
  foo     | bar            | code          | `System.out.println()` |
  foo     | bar            | link          | [Liferay.com](http://liferay.com) |
  foo     | bar            | image         | ![tip](./images/tip.png)|

Table Limitations:

* Grid tables (tables with grid lines) are not supported
* Table captions are not supported
* The period character ( '.') cannot be used in an alignment/divider line

Table Syntax Requirements:

* There must be at least one '|' character per line.
* The *separator* line must contain only |, -, :, or space characters.
* Cell content must be on one line only.
* Columns are separated by the '|' character.

Table Options

* You can pad out cell text using non-breaking spaces (i.e. `&nbsp;`) to the
  left and/or right of the cell text.
* You can use a horizontal rule to help separate the end of the table from
  paragraphs or tables that follow.

---

![important](./images/tip.png) **Important:** - Pandoc does not support
MultiMarkdown table syntax. If you use Pandoc to build a document for test
purposes, you'll notice that the table does not get converted as you would
expect. If you are using Pandoc to convert a document for a final product (e.g.
ePub), you'll need to temporarily change the table syntax to follow the Pandoc
extension.

![The example table converted using Pandoc](images/mmdTablePandocHTML.png)

---

Next, let's learn about creating sidebar text.


### Sidebars

Sidebars appear frequently in our documentation. We place text in sidebars if we
want to draw special attention to it or if the text contains ancillary
information that doesn't quite belong in the main text. For example, notes,
tips, and warnings are often placed in sidebars. To create a sidebar, set off
your sidebar text with a begin sidebar token (`+$$$`) and an end sidebar token
(`$$$`), like so:

    +$$$

    Your sidebar text goes here.

    $$$

Sidebars are only rendered on [dev.liferay.com](https://dev.liferay.com). They
are not rendered on Github.

Here's what a sidebar on [dev.liferay.com](https://dev.liferay.com) looks like:

![liferay-sidebar image](./images/ldn-sidebar.png)

There's no need to include images in your sidebars. Images for sidebars are
handled by Liferay's [dev.liferay.com](https://dev.liferay.com) theme.

**Important:** Make sure that your sidebar tokens have empty lines above and
below them so that they are parsed as independent paragraphs.

Let's look at ordered lists, next.

### Ordered Lists

Explicitly number your lists like so ...

    1. First step.
    2. Second step.
    3. Third step.

List items (steps) can have multiple paragraphs, images, code blocks, etc. But
all text blocks following a step's first paragraph, **must** be indented *4
spaces* from the start of the step number. Otherwise, the continuous numbering
is disrupted and the step that follows restarts at `1`.

**Good steps (Markdown source) ...**

    1. First step.

        This paragraph supports step 1.

    2. Second step.

        Another paragraph and an image.

        ![liferay-cube image](./images/liferay-cube.png)

    3. Third step.

        +$$$

        **Note:** A sidebar note. Text is placed in sidebars if it deserves
        special attention or if it contains ancillary information that doesn't
        quite belong in the main text. For example, notes, tips, and warnings
        are often placed in sidebars.

        $$$

    4. Finally! The fourth and final step. Code must be indented 4 spaces more.
    Let's see a good code block ...

            System.out.println("This code is mono-spaced");

**Resulting HTML from *Good steps***

1. First step.

    This paragraph supports step 1.

2. Second step.

    Another paragraph and an image.

    ![liferay-cube image](./images/liferay-cube.png)

3. Third step.

    ![liferay-sidebar image](./images/ldn-sidebar.png)

4. Finally! The fourth and final step. Code must be indented 4 spaces more.
Let's see a good code block ...

        System.out.println("This code is mono-spaced");

---

All is well in the *Good steps*. Let's consider what NOT to do by way of
example--the *Bad steps*.

**Bad steps (Markdown source) ...**

    1. First step.

    This paragraph is not indented the full 4 spaces from the step number.

    2. Second step.

        Another paragraph and an image.

    ![liferay-cube image](./images/liferay-cube.png)

    3. Third step.

    +$$$
    **Note:** This note disrupts continuous numbering. It also won't be rendered
    as a sidebar.
    $$$

    4. Finally! The fourth and final step. But the code is not monospace as it
    needs to be indented 4 more spaces ...

        System.out.println("Code should be mono-spaced");

**Resulting HTML from *Bad steps* ...**

1. First step.

This paragraph is not indented 4 spaces from the step number.

2. Second step.

    Another paragraph and even the image and notes below are good.

![liferay-cube image](./images/liferay-cube.png)

3. Third step.

+$$$
**Note:** This sidebar note disrupts continuous numbering because it is not
indented four spaces. It also is not rendered as a sidebar because there are no
blank lines below the begin sidebar token or above the end sidebar token.
$$$

4. Finally! The fourth and final step. But the code is not monospace as it
needs to be indented 4 more spaces ...

    System.out.println("Code should be mono-spaced");

---

Well, there you have it--the do's and don'ts of ordered lists.

**Important:** Before you send a pull request, view your Markdown file converted
to HTML, using your editor's Pegdown converter or by viewing your document
blob on Github. That way you can be sure any ordered lists you have, preserve
their consinutous numbering.

### Including Video Tutorials

You may need to include a video tutorial in a developer tutorial or User Guide 
article. To display video tutorials in the markdown, an Administrator must first 
upload the video tutorial (in MP4 and WEBM formats) to the Documents and Media 
repository on LDN. Videos are organized into two folders: `/Develop-videos` for 
developer tutorials and `/User-admin-videos` for User Guide articles. The 
video's title should be the same for both formats, and include the `.mp4` || 
`.webm` extension in the document name, as shown in the configuration below:

    getting-started-with-liferay-ide.mp4

Once the videos are uploaded, you can include them in the markdown. First 
add a thumbnail of the video to the images folder. Thumbnails should be 
250px by 141px (to roughly match the 16x9 aspect ratio). Typically, thumbnails 
are title cards as shown in the figure below:

![Liferay-video-thumbnail image](./images/liferay-video-thumbnail.png)

Add the following markup after the first paragraph to include the thumbnail:

    <div class="video-link">
    <img src="../../../images/vid-ide-thumbnail.png" alt="video-thumbnail"/>
    </div>

The `<div>` **must use the `video-link` class for the tutorial to render 
properly.** Finally, you can include the video.

We use the HTML5 video tag to include multiple sources of our video. Add this 
markup to the bottom of the article, just before the *Related Topics* section:

    <div class="video-tag" data-name="Getting Started with Liferay IDE">
      <video width="100%" height="100%" controls>
        <source src="https://dev.liferay.com/documents/10184/367132/getting-started-with-liferay-ide.mp4" type="video/mp4">
        <source src="https://dev.liferay.com/documents/10184/367132/getting-started-with-liferay-ide.webm" type="video/webm">
        Your browser does not support HTML5 video.
      </video>
    </div>

The wrapping `<div>` tag **must use the `video-tag` class and provide a 
`data-name` value**. The `data-name` attribute is used as the title for the 
video player. The `src` attributes point to the video formats. If one format 
isn't supported, the other will be delivered. If neither is supported, the user 
receives the notification text.

View the video by clicking the thumbnail on the right-side 
(or bottom on mobile) of the article.

### Markdown Metadata

Our build process supports conversion from Markdown to html, odt, and epub
formats using Pandoc. The Liferay Docs README describes the repository contents,
directory structure, and conversion process. We concatenate the individual
chapter files of a document and insert a markdown title block which pandoc
parses as bibliographic information. See
[http://johnmacfarlane.net/pandoc/README.html](http://johnmacfarlane.net/pandoc/README.html)
for details. The default title block in build.properties is empty:

	%
	%
	%

You can override the default title block by defining title, author, and date
fields in your `build.<username>.properties` file. For example, the following
`build.<username>.properties` entry

	title=Title
	author=Author(s) (separated by semicolons)
	date=Date

adds the following Markdown text to the beginning of the concatenated file:

	% Title
	% Author(s) (separated by semicolons)
	% Date

## Markdown Header ID FAQ

This FAQ is provided to help answer questions and provide information on how and
why we use IDs for the headers in the Markdown files of our official product
documentation.

### What are the header IDs and why are they important?

Header IDs were created for the purpose of preserving the URLs of our official
documentation on Liferay.com. Previously, the URLs for our web content were
determined by the heading text of our documents (e.g. the text from "#
Introduction to Liferay Portal" markdown was used to generate the URL final
string in the web content's URL
[http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/introduction-to-liferay](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/introduction-to-liferay)).
If the titles of the web content were changed, either by re-import of the
markdown or manual edits via the GUI, the URLs changed too--breaking any links
to the web content.

In response to this issue, Liferay Portal and the AssetImporter have been
improved so that web content can be referenced by static IDs. Regardless of
whether the titles of a web content change, its ID remains the same, preserving
the URL of that web content.

The header IDs are now specified in the markdown source code for our official
documents.

Example 1 - ID to an existing web content:

    # Introduction to Liferay [](id=introduction-to-liferay)

Example 2 - ID for a new section header:

    ## Adding and Updating Assets [](id=adding-and-updating-assets)

Each header, regardless of level, is to have a corresponding ID. This gives us
the flexibility to create web content for even the lowliest of subsections if we
so choose. But no need to worry, those IDs get stripped out of the web content
on import to Liferay.com.

The naming convention for new headers very closely follows the existing header
title. Uppercase letters are converted to lowercase and spaces are converted to
dashes.

### How should I specify an ID for a new header?

Execute ant target `number-headers` or `number-headers-dxp` from your document
directory (e.g., `/develop/tutorials`). You can also run `ant check` or `ant
check-dxp` to generate header IDs and to various other checks and tasks.

### What should I do with the ID for an existing header I've modified?

**IMPORTANT:** Do not change the ID of an existing header.  If the header does
not have an existing header, then run the `number-headers` target on the
document.

### If I re-order sections or chapters, what do I do with their header IDs?

**IMPORTANT:** Do not change the ID of an existing header. You can however, move
the header (along with its ID) around within an article or into a different
article.

### If I want to update existing web content and find that its source is missing header IDs, what do I do?

First, inform the document owner (e.g. Rich for the User Guide and Jim for the
Dev Guide).

You'll need to use the last portion of the web content's URL title as the ID for
header in the respective markdown source. After updating the IDs in the
markdown, be sure to run ant target `number-headers` to detect any issues with
the headers.

### How can I be sure that my header IDs will not be in conflict with other header IDs (e.g., IDs in other documents)?

Ant target `number-headers` detects and reports any issues and/or conflicts
between headers. It will fail if any issues are encountered.

Example - Header ID conflict output

	number-headers:
        [java] Numbering headers for files in ../tutorials/articles ...
        [java] Dup id:liferay-ide file:..\tutorials\articles\100-tooling\02-liferay-ide\01-installing-liferay-ide.markdown line:1 (already used by file:..\tutorials\articles\100-tooling\02-liferay-ide\00-liferay-ide-intro.markdown)
        [java] Exception in thread "main" java.lang.Exception: FAILURE - Duplicate header IDs exist
        [java]     at com.liferay.documentation.util.NumberHeadersSiteMain.main(Unknown Source)

    BUILD FAILED

To resolve the above conflict, the author *must* be sure to preserve the header
ID that existed first, if that header is a part of the *live* version of the
document on Liferay.com. Then, the author should remove the newer header ID from
the other header and run `ant number-headers` to produce a new unique ID for
that header.

### Will the header IDs show in the web content?

No, they will not show unless possibly there is a syntax error in the header ID
used in your markdown source.

### Are there safe-guards to prevent upload of documents that have missing or conflicting IDs?

Yes, the dependency targets (e.g. `number-headers`) executed by our distribution
targets, `dist-ce` and `dist-dxp`, fail and report errors if the documents are
missing IDs or have conflicting IDs.

## Appendix: A Sample Markdown Environment

For liferay.com, we use Pegdown with our own, customized parser, which is
included in this project. You can use this with our `convert.[sh|bat]` script in
the `bin` folder to preview your articles.

### Editing Markdown Files

Most programmers have a close relationship with their text editor of choice.
This is not an attempt to break that relationship in any way: Markdown is plain
text, and you should use whatever tool makes you most effective. One of the
reasons we chose it is to allow writers to use whatever tools they want.

For those who are looking for some guidance on a good tool to use, you can use
jEdit. It's a great cross-platform text editor which is highly extensible.
Though it's written in Java, it can be configured to start in the background
when your machine starts, which makes it pop up as fast as any native editor.
This makes it ideal regardless of which platform (Linux, Windows, or Mac) you
use.

For Markdown, jEdit has a Markdown plugin that can render a Markdown document
into HTML, and there's also a syntax highlighting mode file that you can
install. The Markdown plugin is available in jEdit's plugin manager, and the
mode file can be downloaded from
[https://github.com/peterlynch/jEdit-modes|Github](https://github.com/peterlynch/jEdit-modes) or [http://hasseg.org/blog/post/302/markdown-and-pod-syntax-highlighting-modes-for-jedit](http://hasseg.org/blog/post/302/markdown-and-pod-syntax-highlighting-modes-for-jedit).  

To install the mode file, copy it into your `.jedit/modes` folder, and edit the
`catalog` file which is in the same folder. Add this line to the file, between
the <MODES> tags:

    <MODE NAME="markdown" FILE="markdown.xml" FILE_NAME_GLOB="*.{markdown,md}" />

Save the file and restart jEdit. While editing, you now have syntax highlighting
and can preview Markdown files in HTML using the plugin.

If you're going to be doing diffing and merging using jEdit's jdiff plugin,
you'll also need to set the width to be narrower than the 120 character default
provided by the Markdown mode file. To do this, go to *Utilities* -> *Global
Options* -> *Editing*. Under *Change Settings for Mode*, select *Markdown*, and
then change the *Wrap Margin* to 80. Of course, make sure also that *Word Wrap*
is set to *Soft*.

Now you've got a great environment for editing Markdown files.

Next, you should look at our 
[writer's guidelines](03-writers-guidelines.markdown). 
This is how we ensure a consistent writing style throughout the documentation. 

## Contact Information

Rich Sezov (sez11a), Jim Hinkey (jhinkey)
