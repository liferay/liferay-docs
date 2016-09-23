# Liferay Documentation Tools

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

## Markdown Environment

At the moment, we're using Pandoc, because it supports the Markdown extensions
we need. Pandoc is also cross-platform, so it can be run on all three
environments (Linux, Mac, and Windows) that we use.

For liferay.com, we use Pegdown with our own, customized parser. We may
introduce that parser to this project in the future, and then you won't have to
go through the Pandoc install process described below. 

### Installing Pandoc 

Pandoc is fairly easy to install, provided you can get the Haskell environment
installed on your machine. You can generally get this from
[here](http://hackage.haskell.org/platform). 


On (K)ubuntu 11.10 and up, the Pandoc package works fine. On other operating
systems, an installer is provided for Haskell, and from there, the procedure is
the same for all operating systems. 


Once Haskell is installed, you can use the `cabal` tool according to the Pandoc
[install instructions](http://johnmacfarlane.net/pandoc/installing.html). Once
Pandoc is installed, add it to your path. This can be done by adding the
directory `$HOME/.cabal/bin` to your `.profile` or `.bashrc` file.

Next, you need an editor for Markdown files. 

### Editing Markdown Files

Most programmers have a close relationship with their text editor of choice.
This is not an attempt to break that relationship in any way: Markdown is plain
text, and you should use whatever tool makes you most effective. One of the
reasons we chose it is to allow writers to use whatever tools they want. 


For those who are looking for some guidance on a tool to use, I (RS) use jEdit.
It's a great cross-platform text editor which is highly extensible. Though it's
written in Java, it can be configured to start in the background when your
machine starts, which makes it pop up as fast as any native editor. This makes
it ideal regardless of which platform (Linux, Windows, or Mac) you use. 


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

## Markdown Image Numbers Tool 

We have a tool that you can call with Ant that numbers the images in a Markdown
chapter file. While you're writing or editing a Markdown chapter file, you can
just number all the images in that chapter with a lowercase x. For example, if
you were editing chapter 2 of Using Liferay Portal, your image tags could take
the following form:

	![Figure 2.x: <image-description>](../../images/<image-name>)

When you are finished working on a chapter file, you can call the
`number-images` Ant task from the directory of the document you are working on
and supply the chapter as an argument:

	ant number-images -Dchapter=<chapter-name>

For example, to number the images of chapter 2 of Using Liferay Portal, you
would use the following command from liferay-docs/userGuide:

	ant number-images -Dchapter=02-liferay-marketplace

Our numbering tool will also renumber the images of incorrectly numbered
chapters.

## Assigning Header IDs 

Header IDs help to assure that each uploaded document's web contents have a
unique URL. These IDs not only prevent documents from using the same URLs but
they also help to preserve web content URLs despite changes to header text in
revisions of the document.

1.  Edit your document's `liferay-docs/<document>/doc.properties` to specify the
    product abbreviation, product version, and an abbreviation for your
    document. These property values help namespace the header IDs.

    Example - `devGuide/doc.properties`: 


        product.abbrev=lp
        product.name=Liferay Portal
        product.version=6.2
        doc.name=Dev Guide

2.  Number the section headers of your document.

        ant number-headers

It will fail if header IDs conflict.

Example - Output from header ID conflict


	...  number-headers:
	[numberheaders] Numbering headers for files in ..\devGuide\en\chapters ...
	[numberheaders] Dup id:summary-liferay-portal-6-2-dev-guide-06-en file:06-hooks.markdown line:305 (already used by file:06-hooks.markdown)
	...

To resolve a conflict, you *must* be sure to preserve the header ID that existed
first, if that header is a part of the *live* version of the document on
Liferay.com. Then, remove the newer header ID from the other header and run ...


	ant number-headers


... again to produce a new unique ID for that header.

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

Previously, Open/LibreOffice added the words: *Illustration #* to captions when
they were entered. This was nice, but is unfortunately something we'll need to
abandon. To do figures, you should do it this way: 


	![Figure 1.1: Logging into Liferay Portal](../../images/01-logging-into-liferay-portal.png)

This causes Pandoc to create the following, easily styled markup: 


	<div class="figure">
	<img src="../../images/01-logging-into-liferay-portal.png" alt="Figure 1.1: Logging into Liferay Portal" />
	<p class="caption">Figure 1.1: Logging into Liferay Portal</p>
	</div>
	
We've duplicated this behavior in the Pegdown parser that we've implemented. 

### Inline Images / Icon Images

An icon's image helps the reader identify the icon in the UI. To use an existing
icon snapshot, check a document's `images/` folder for files ending in *-icon.png*.
Follow these steps
to include an icon image inline in your article's Markdown text:

1. Take a snapshot of the icon, if one doesn't already exist in the document's
`images/` folder. Please save the snapshot to the `images/` folder and end its name with
`-icon.png`. 
2. Crop the image to remove unrelated content from around the icon.
3. Resize the image's height to no greater than 20 pixels. **Important:** Make sure to keep the
aspect ratio. 
4. In the Markdown text, include the icon image in parentheses.

Inline icon image example Markdown:

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

Because Pegdown does not support the [Pandoc extension table
syntax](http://johnmacfarlane.net/pandoc/README.html#tables), we use a table
syntax similar to
[MultiMarkdown](http://fletcher.github.com/peg-multimarkdown/mmd-manual.pdf),
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

 ![important](./images/tip.png) **Important:** - Pandoc does not 
support MultiMarkdown table syntax. If you use Pandoc to build a document for
test purposes, you'll notice that the table does not get converted as you would
expect. If you are using Pandoc to convert a document for a final product
(e.g. ePub), you'll need to temporarily change the table syntax to follow the
Pandoc extension.

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

### Markdown Metadata 

Our build process supports conversion from Markdown to html, odt, and epub
formats using Pandoc. The Liferay Docs README describes the repository contents,
directory structure, and conversion process. We concatenate the individual
chapter files of a document and insert a markdown title block which pandoc
parses as bibliographic information. See
[http://johnmacfarlane.net/pandoc/README.html|http://johnmacfarlane.net/pandoc/README.html](http://johnmacfarlane.net/pandoc/README.html|http://johnmacfarlane.net/pandoc/README.html)
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
[http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/introduction-to-liferay](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/introduction-to-liferay).
If the titles of the web content were changed, either by re-import of the
markdown or manual edits via the GUI, the URLs changed too--breaking any links
to the web content.

In response to this issue, Liferay portal and the AssetImporter have been
improved so that web content can be referenced by static IDs. Regardless of
whether the titles of a web content change, its ID remains the same, preserving
the URL of that web content.

The header IDs are now specified in the markdown source code for our official
documents.

Example 1 - ID to an existing web content:

    # Introduction to Liferay [](id=introduction-to-liferay)

Example 2 - ID for a new section header:

    ### Using the Dockbar [](id=lp-6-1-ugen01-using-the-dockbar-0)

Each header, regardless of level, is to have a corresponding ID. This gives us
the flexibility to create web content for even the lowliest of subsections if we
so choose. But no need to worry, those IDs get stripped out of the web content
on import to Liferay.com.


The naming convention for new headers is as follows:

*product.abbrev-product.version-(doc.abbrev)(lang)(chapterNum)-(headerText)-increment*


Example, `id=lp-6-1-ugen01-using-the-dockbar-0` can be broken down into:

* **product.abbrev:** lp	(for Liferay Portal)
* **product.version:** 6-1	(for version 6.1)
* **doc.abbrev:** ug	(for User Guide)
* **lang:** en	(for English)
* **chapterNum:** 01	(derived from the file's prefix - e.g.,
  `01-introduction-to-liferay-ui.markdown`)
* **headerText:** using-the-dockbar	(derived from "### Using the Dockbar")
* **increment:** 0	(indicating this is the first such header having the
  attributes mentioned above. This increment becomes necessary to distinguish
between web content with header text, like "Summary", found within the same
chapter.)

### How should I specify an ID for a new header? 

Execute ant target `number-headers` from your document directory. Note, unless
you specify otherwise, your default language (e.g., `en`) is used in the
document ID.

### What should I do with the ID for an existing header I've modified? 

**IMPORTANT:** Do not change the ID of an existing header.  If the header does
not have an existing header, then run the `number-headers` target on the
document.

### If I re-order sections or chapters, what do I do with their header IDs? 

**IMPORTANT:** Do not change the ID of an existing header.
You can however, move the header (along with its ID) around within a chapter
document or into a different chapter document.

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

	...
	number-headers:
	[numberheaders] Numbering headers for files in ..\devGuide\en\chapters ...
	[numberheaders] Dup id:lp-6-1-dgen10-summary-0 file:11-marketplace.markdown line:305 (already used by file:10-plugin-security.markdown)
	...

To resolve the above conflict, the author *must* be sure to preserve the header
ID that existed first, if that header is a part of the *live* version of the
document on Liferay.com. Then, the author should remove the newer header ID from
the other header and run `ant number-headers` to produce a new unique ID for
that header.

**Important**, each document directory (e.g., `liferay-docs/userGuide/`) has a
file `doc.properties` that specifies the product abbreviation, product version,
and documentation abbreviation to assure that IDs are name-spaced properly.


Example - devGuide/doc.properties:


    product.abbrev=lp
    product.version=6.1
    doc.abbrev=dg

### Will the header IDs show in the web content? 

No, they will not show unless possibly there is a syntax error in the header ID
used in your markdown source.

### Are there safe-guards to prevent upload of documents that have missing or conflicting IDs? 

Yes, the dependency targets (e.g. `number-headers`) executed by our distribution
targets, `dist` and `dist-win`, fail and report errors if the documents are
missing IDs or have conflicting IDs.

## Contact Information 

* Jim Hinkey (jhinkey) - Dev Guide
* Rich Sezov (sez11a) - All other guides
