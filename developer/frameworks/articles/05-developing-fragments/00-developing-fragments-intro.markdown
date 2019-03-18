# Page Fragments [](id=developing-fragments)

Page Fragments take your design vision and accurately realize it on a web page. 
To do this, developers are given a space where they have a "blank slate."  You 
have three tools at your disposal to accomplish this:

**HTML**: The markup of the fragment. Fragments use standard HTML with 
   special tags to add dynamic behavior.

**CSS**: CSS styles and positions the fragment's markup. 

**JavaScript**: JavaScript provides dynamic behavior to the fragment.

The HTML, CSS, and JavaScript are all completely standard, just like anywhere
else on the web, but are also enhanced with Liferay-specific features. You can
specify text and images as editable, as well as providing for "rich text": that
is, text with additional formatting. Liferay portlets can also be embedded in
Fragments as "widgets," making pages with Fragments more dynamic than regular
web content.

## Creating Page Fragments

There are two types of Page Fragments, *Sections* and *Components*. A Section
is intended to ... A Component is intended to ... 

Fragments are created inside of Collections. Collections provide an easy way to 
manage and share groups of related Fragments. They are also used when a user is 
selecting Fragments to add to a page. @product@ provides a robust interface for 
creating and managing Fragments and Collections without using any external 
tools, but you can also use your preferred tools for Web Development. For an 
explanation of basic Fragment creation using Liferay's built in tools, see 
[Creating a Fragment](link). For more information on creating and managing 
Fragments, see [Managing Page Fragments with Desktop Tools](link).

### Fragment CLI

To streamline fragment development, @product-ver@ provides command line tools 
for generating, importing, and exporting fragments and fragment collections. 
For instructions on setting up the CLI, see the [Setting up the Fragment Generator](link) reference. Using this CLI is also covered in the [Managing Page Fragments with Desktop Tools](link) section.

## Contributed Collections

In most cases, Page Fragments are created by web developers and imported 
through the Page Fragments interface, or created directly using the embedded 
tools. Page Fragments created like this can be updated and edited by any user 
with the necessary privileges and used however they want. This works for many 
cases, but you may have certain situations where you want to add fragments that 
will be 100% static, and that cannot be modified. In this case you can create a 
Contributed Fragment Collection.

Contributed Fragment Collections are deployable modules which contain Page 
Fragments. Those fragments can be used just like regular fragments, but are not 
contained in the database, and cannot be modified except by updating the module 
that they are deployed in. Use our [Creating Contributed Collections guide] to 
learn to create your own Contributed Collections.

## Fragment Specific Tags

In addition to the standard HTML, CSS, and JavaScript used for creating 
Fragments, there are a number of Liferay specific tags that you will need to 
use to make editable sections, or embed widgets in your Fragment.

Making text editable allows the marketer or web admin to modify the text before
publishing it. This way, you can reuse a single fragment with different headings
or different text for different pages. Fragments make creating content easy and
this feature can save you the work of duplicating work just to change the text. 

Like text, you can set images as editable. An editable image can be
selected from the user's local files or from the Documents and Media Library.
You must be careful with defining styles for editable images since an image that
without the proper dimensions or that is forced into a poorly sized space can
have major negative effects on your layout. 

You can add more dynamic behavior to a Fragment by including a widget. 
Currently, you can only embed a portlet as a widget, but other types of widgets
will be available in the future.

You can find a complete list of these in the [Page Fragments Reference](link).
