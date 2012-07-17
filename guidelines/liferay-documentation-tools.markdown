# Liferay Documentation Tools

Liferay's documentation is currently implemented in Markdown. We chose Markdown for several reasons: 

1. It's readable. Even if you don't know Markdown, you can read it without having to filter out the syntax.  

2. It gets out of a writer's way. You don't have to worry about mousing to various icons to change text into a heading or create bulleted lists. Just start typing. The syntax is so intuitive, you probably have used it all your life anyway. 

3. It's easily convertable to other formats. Using Markdown lets us publish to the web, mobile, and print from the same source files. 

4. It's great for collaborating. Using Github, we can easily see what various people have contributed, through the same diffs we'd use in programming. We can merge those changes pretty easily, too, because the format is simple enough that the changes in the diffs happen to be the actual changes made to the text, rather than a bunch of formatting tags. 

In summary, Markdown is, by definition, a text based format that's designed to be as readable as possible. It lets you write in a very natural format which can then be converted to other formats for publication. We have switched to using Markdown for the Liferay 6.1 documentation. It's allowing us to single-source our documentation for the web, for ebooks, and for print. 

## Markdown Environment

At the moment, we're using Pandoc, because it supports the Markdown extensions we need. Pandoc is also cross-platform, so it can be run on all three environments (Linux, Mac, and Windows) that we use.

For liferay.com, we use Pegdown with our own, customized parser. We may introduce that parser to this project in the future, and then you won't have to go through the Pandoc install process described below. 

### Installing Pandoc 

Pandoc is fairly easy to install, provided you can get the Haskell environment installed on your machine. You can generally get this from [here](http://hackage.haskell.org/platform). 


On (K)ubuntu 11.10 and up, the Pandoc package works fine. On other operating systems, an installer is provided for Haskell, and from there, the procedure is the same for all operating systems. 


Once Haskell is installed, you can use the `cabal` tool according to the Pandoc [install instructions](http://johnmacfarlane.net/pandoc/installing.html). Once Pandoc is installed, add it to your path. This can be done by adding the directory `$HOME/.cabal/bin` to your `.profile` or `.bashrc` file.

Next, you need an editor for Markdown files. 

### Editing Markdown Files

Most programmers have a close relationship with their text editor of choice. This is not an attempt to break that relationship in any way: Markdown is plain text, and you should use whatever tool makes you most effective. One of the reasons we chose it is to allow writers to use whatever tools they want. 


For those who are looking for some guidance on a tool to use, I (RS) use jEdit. It's a great cross-platform text editor which is highly extensible. Though it's written in Java, it can be configured to start in the background when your machine starts, which makes it pop up as fast as any native editor. This makes it ideal regardless of which platform (Linux, Windows, or Mac) you use. 


For Markdown, jEdit has a Markdown plugin that can render a Markdown document into HTML, and there's also a syntax highlighting mode file that you can install. The Markdown plugin is available in jEdit's plugin manager, and the mode file can be downloaded from [https://github.com/peterlynch/jEdit-modes|Github](https://github.com/peterlynch/jEdit-modes). 


To install the mode file, copy it into your `.jedit/modes` folder, and edit the `catalog` file which is in the same folder. Add this line to the file, between the <MODES> tags: 

	<MODE NAME="markdown" FILE="markdown.xml" FILE_NAME_GLOB="*.markdown" />


Save the file and restart jEdit. While editing, you now have syntax highlighting and can preview Markdown files in HTML using the plugin.


If you're going to be doing diffing and merging using jEdit's jdiff plugin, you'll also need to set the width to be narrower than the 120 character default provided by the Markdown mode file. To do this, go to *Utilities* -> *Global Options* -> *Editing*. Under *Change Settings for Mode*, select *Markdown*, and then change the *Wrap Margin* to 80. Of course, make sure also that *Word Wrap* is set to *Soft*. 


Now you've got a great environment for editing Markdown files. 

## Markdown Image Numbers Tool 

We have a tool that you can call with Ant that numbers the images in a Markdown chapter file. While you're writing or editing a Markdown chapter file, you can just number all the images in that chapter with a lowercase x. For example, if you were editing chapter 2 of Using Liferay Portal, your image tags could take the following form:

	![Figure 2.x: <image-description>](../../images/<image-name>)

When you are finished working on a chapter file, you can call the `number-images` Ant task from the directory of the document you are working on and supply the chapter as an argument:

	ant number-images -Dchapter=<chapter-name>

For example, to number the images of chapter 2 of Using Liferay Portal, you would use the following command from liferay-docs/userGuide:

	ant number-images -Dchapter=02-liferay-marketplace

Our numbering tool will also renumber the images of incorrectly numbered chapters.

## Assigning Header IDs 

Header IDs help to assure that each uploaded document's web contents have a unique URL. These IDs not only prevent documents from using the same URLs but they also help to preserve web content URLs despite changes to header text in revisions of the document.

1. Edit your document's `liferay-docs/<document>/doc.properties` to specify the product abbreviation, product version, and an abbreviation for your document. These property values help namespace the header IDs.

Example – `devGuide/doc.properties`: 


    product.abbrev=lp
    product.version=6.1
    doc.abbrev=dg

2. Number the section headers of your document

	ant number-headers

It will fail if header IDs conflict.

Example - Output from header ID conflict


	...
	number-headers:
	[numberheaders] Numbering headers for files in ..\devGuide\en\chapters ...
	[numberheaders] Dup id:lp-6-1-dgen10-summary-0 file:11-marketplace.markdown line:305 (already used by file:10-plugin-security.markdown)
	...

To resolve a conflict, you *must* be sure to preserve the header ID that existed first, if that header is a part of the *live* version of the document on Liferay.com. Then, remove the newer header ID from the other header and run ...


	ant number-headers


... again to produce a new unique ID for that header.

## Contact Information 

* Jim Hinkey (jhinkey) - Dev Guide
* Rich Sezov (sez11a) - All other guides
