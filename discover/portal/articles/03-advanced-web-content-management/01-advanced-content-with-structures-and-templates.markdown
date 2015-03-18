# Advanced Content with Structures and Templates [](id=advanced-content-with-structures-and-templates)

If you've ever launched a web site, you know that as it grows, you can
experience growing pains. This is the case especially if you've given lots of
people access to the site to make whatever changes they need to make. Without
preset limitations, users can display content in any order and in any manner
they desire (think huge, flashing letters in a font nobody can read). Content
can get stale, especially if those responsible for it don't maintain it like
they should. And sometimes, content is published that should never have seen the
light of day.

Thankfully, Liferay WCM helps you handle all of those situations. You can use
*Structures* to define which fields are available to users when they create
content. These can be coupled with *Templates* that define how to display that
content. Content won't get stale, because you can take advantage of the
*Scheduling* feature to determine when content is displayed and when it's
removed. Additionally, you can configure Liferay's built-in *Workflow* system to
set up a review and publishing process so only what you want winds up on the
live site. Liferay Portal gives you the management tools you need to run
everything from a simple, one-page web site to an enormous, content-rich site.

All of this starts with structures.

## Using Structures [](id=using-structures)

Structures are the foundation for web content. They determine which fields are
available to users as they create new items for display. Structures not only
improve manageability for the administrator, they also make it much easier for
users to quickly add content.

For example, say you're managing an online news magazine. All your articles need
to contain the same types of information: a title, a subtitle, an author and one
or more pages of text and images that comprise the body of the article. If
Liferay only supported simple content as has been described above, you'd have no
way to make sure your users entered a title, subtitle, and author. You might
also get articles that don't match the look and feel of your site. If titles are
supposed to be navy blue but they come in from your writers manually set to
light blue, you need to spend time reformatting them before they are published.

Structures give you the ability to provide a format for your content so your
users know what needs to be entered to have a complete article. Using
structures, you can provide a form for your users which spells out exactly what
is required and can be formatted automatically using a template.

You create a structure by adding form controls such as text fields, text boxes,
text areas (HTML), check boxes, select boxes and multi-selection lists. Also you
can add specialized, Liferay-specific application fields such as an Image
Uploader and Documents and Media right onto the structure. Furthermore, you can
move the elements around by dragging them where you want them. This makes it
easy for you to prototype different orders for your input fields. Additionally,
elements can be grouped together into blocks which can then be repeatable.
Template writers can then write a template which loops through these blocks and
presents your content in innovative ways, such as in sliding navigation bars,
content which scrolls with the user and more.

Let's look at how we can create and edit structures through the Manage
Structures interface.

### Editing Structures [](id=editing-structures)

Go back to the Site Administration page and select *Web Content* from the
Content section. The first way to access the Manage Structures interface is
simply by clicking *Manage* &rarr; *Structures*. This opens a popup showing all
the web content structures that exist in your currently selected scope. Here,
you can add new web content structures, edit existing ones, manage the templates
associated with a structure, edit the permissions of a structure, and copy or
delete structures.

Copying web content structures can be useful if you'd like to create a new web
content structure that's similar to an existing one, but you don't want to start
from scratch. Liferay generates a unique portal ID for the copied structure, but
every other attribute of the copied structure, including the name, is the same
as that of the original. Once you've copied a web content structure, you should
enter a new name for it to avoid confusing it with the original. When you copy a
web content structure, you'll be prompted to choose whether to copy any detail
templates or list templates associated with the structure. For information on
detail templates and list templates, please refer to the
[Using Web Forms and Dynamic Data Lists](/discover/portal/-/knowledge_base/6-2/using-web-forms-and-dynamic-data-lists)
section.

![Figure 3.1: You can access the Manage Structures interface by clicking *Manage* &rarr; *Structures* from the Web Content page.](../../images/manage-structures.png)

The second way to access the Manage Structures interface is directly from the
web content article WYSIWYG editor. Click *Add* &rarr; *Basic Web Content* from
the Web Content page to add another piece of content to your portal. Instead of
going right for the content, this time we'll first create a structure. To access
the Manage Structures interface, simply click on *Select* next to the
*Structure* heading near the top of the page. To create a new structure in your
chosen scope, simply click on the *Add* button in the Manage Structures popup.

It's very easy to create and edit structures: all you have to do is drag
elements into the structure and then give them names. For instance, select the
*Text* element and drag it onto the structure. You can do the same with any of
the elements. To remove it from the structure, simply select the *Delete* icon
(trash can) in the upper right corner of the element. You also have the ability
to duplicate the element, which can be done by selecting the *Duplicate*
(addition sign) button. We'll explain the *Configuration* button later.

Web content structures also have the capability of inheriting characteristics
from other structures. When a parent structure is configured, the child
structure inherits the parent's fields and settings. Using this feature is
helpful when you want to make a similar structure to one that already exists.
For example, if you'd like to create an in-depth Lunar Resort sports article in
addition to a regular Lunar Resort sports article, you can simply inherit the
characteristics of the regular article and only add additional fields to the
more in-depth article. When the in-depth article is configured, it will display
its parent's fields in addition to its own fields.

For Liferay 6.2, the WebDAV URL feature was introduced for web content
structures and templates so users could upload and organize resources from both
a web interface and the file explorer of their desktop operating system. With
the WebDAV URL, site administrators are capable of adding, browsing, editing,
and deleting structures and templates on a remote server. After you complete
your structure, you can access the WebDAV URL by re-opening the structure or
template and clicking the *Details* section. If you'd like the see WebDAV in
action, visit the
[WebDAV Access](/discover/portal/-/knowledge_base/6-2/automatic-previews-and-metadata#webdav-access)
section.

+$$$

**Note:** Some operating systems require a
 WebDAV server to be class level 2 before (i.e., to support file locking) before
 allowing files to be read or written. For Liferay 6.2, the Documents and Media
 library was upgraded to class level 2 but Web Content structures and templates
 were not. This means that Liferay 6.2's Document and Media library supports
 WebDAV file locking but Web Content structures and templates do not. However,
 on operating systems which require WebDAV servers to be class level 2, it's
 possible to avoid the restriction by using third-party WebDAV clients (e.g.,
 [Cyberduck](http://cyberduck.ch)).

$$$

Another method to edit your structure is switching to *Source* mode and manually
customizing your structure by editing its XML file. You'll notice by default the
*View* mode is selected. Click the *Source* tab to switch to Source mode. This
method is for the more experienced developers.

Take a moment to add, delete, and rearrange different elements.

![Figure 3.2: The structure editor gives you many options to customize your Web Content.](../../images/04-web-content-structure-editor.png)

Liferay supports the following fields in structures:

**Boolean:** Adds a checkbox onto your structure, which stores either `true`
(checked) or `false` (unchecked). Template developers can use this as a display
rule.

**Date:** Adds a preformatted text field that displays a convenient date picker
to assist in selecting the desired data. The format for the date is governed by
the current locale.

**Decimal:** Similar to *Number*, except that it required a decimal point (.) be
present.

**Documents and Media:** Adds an existing uploaded document to attach to the
structure. Also has the ability to upload documents into the Document Library.

**HTML:** An area that uses a WYSIWYG editor to enhance the content.

**Image:** Adds the browse image application into your structure.

**Integer:** Similar to *Number*, except that it constrains user input to
non-fractional numbers.

**Link to Page:** Inserts a link to another page in the same site.

**Number:** Presents a text box that only accepts numbers as inputs, but puts no
constraints on the kind of number entered.

**Radio:** Presents the user with a list of options to choose from using radio
button inputs.

**Select:** Presents a selection of options for the user to choose from using a
combo box. Can be configured to allow multiple selections, unlike *Radio*.

**Text:** Used for items such as titles and headings.

**Text Box:** Used for the body of your content or long descriptions.

These fields provide all you need to model any information type you would
want to use as web content. Liferay customers have used structures to model
everything from articles, to video metadata, to databases of wildlife. You're
limited only by your imagination. To fire that imagination, let's look more
closely at field settings.

### Editing Field Settings [](id=editing-field-settings)

When creating a new structure, it is essential that you set variable names.
Template writers can use these variables to refer to elements on your form. If
you don't set variable names, Liferay generates random variable names and these
can be difficult for a template writer to follow. For example, consider a field
called *Author*. You might create this field in your form but the underlying
variable name in the structure might look something like `TextField4882`. The
template writer needs to create markup for your structure and place the Author
field in a certain spot in the markup. How will he or she know which field is
Author when they're all named randomly?

To solve this problem, all you need to do is set a variable name for each field
as you add it to your structure. Let's do this now. In your structure, add an
element *HTML*. To change its field label and variable name, you'll need to
access the field's settings. Hover over the field and select the wrench icon
that appears in the upper right corner. Change the *Field Label* value to
*Instructions* and the *Name* value (variable name) to `Steps`. Now your
template writer has a variable by which he or she can refer to this field.

Here's a list of all the configurable settings available for a structure's
fields:

**Type:** Lists the type of field placed in the definition. This is not editable
but is available to reference from a template.

**Field Label:** Sets the text that can be displayed with the field. This is the
human-readable text that the user sees.

**Show Label:** Select *Yes* to display the Field Label.

**Required:** Select *Yes* to mark the field required. If a field is required,
users must enter a value for it in order to submit content using this structure.

**Name:** The name of the field internally, automatically generated. Since this
is the variable name that you can read the data from in a template, you should
give a more memorable name here.

**Predefined Value:** Specifying predefined values for structure forms is a way
to specify defaults. When a user creates a new web content article based on a
structure that has predefined values for various fields, the predefined values
appear in the form as defaults for those fields.

**Tip:** Each field can have a small help icon, with a tooltip attached that
displays helpful information. If you would like to provide text for the tooltip
you may enter it here.

**Indexable:** Select *Yes* to enable Liferay to index your field for search.

**Repeatable:** Select *Yes* to make your field repeatable. Your users can then
add as many copies of this field as they like. For example, if you're creating a
structure for articles, you might want a repeatable Author field in case you
have multiple authors for a particular article.

**Width:** Changes the width of the field. The field width can be *small*,
*medium*, or *large* (not available for Boolean, Documents and Media, Image,
Radio, and Select.

**Multiple:** Select *Yes* to enable a multi-selection list (only available for
Select).

**Options:** Changes the options available for selection. You're able to add and
remove options as well as edit each individual option's display name and value
(only available for Radio and Select).

For the Lunar Resort structure, type something in the *Tip* field that helps users
know what to put into the Body element (example: *This is an HTML text area for
the body of your content*). Now, when users hover over the Help icon near your
title, your tip is displayed.

#### Structure Default Values [](id=structure-default-values)

Structure Default Values allow you to create one structure that uses common data
from multiple articles.

Returning to our newspaper scenario again, let's say you want all sports
articles to have the same display page (sports page), the same categories, or
the same set of tags. Instead of adding them for each article or wondering if
your users are adding them to every web content, you can add these
characteristics once for every sports article by creating default values for the
structure. Creating default values is not part of creating a new structure, so
make sure you have an existing structure.

To edit a structure's default values, go to *Web Content* in the Content section
of the Site Administration page and click *Manage* &rarr; *Structures* to see
the structures list. Find the *Actions* button for the desired structure and
select *Edit Default Values* from the menu to view a window like the one below.
This form allows you to manage the structure settings.

![Figure 3.3: You can edit default values via the *Actions* button of the Manage Structures interface.](../../images/structure-actions.png)

Every new web content you create with this structure is preloaded with the
data you inserted. Next, let's demonstrate assigning permissions.

### Assigning Permissions [](id=assigning-permissions)

Setting permissions on structures is done using the same procedure as
permissions everywhere else in Liferay. Most users should not have the ability
to edit structures. Structures are coupled with templates, which require some
web development knowledge to create. This is why only trusted developers should
be able to create structures and templates. Users, of course, should be able to
view structures. The *View* permission enables them to make use of the
structures to create content.

![Figure 3.4: You're able to assign structure permissions via the *Actions* button.](../../images/04-web-content-structure-permissions.png)

You can grant or deny permissions based on Roles and this is the recommended way
to handle permissions for structures.

Now that you understand what structures are used for, you need to understand the
other half of Liferay's web content management system: templates.

## Using Templates [](id=using-templates)

Developers create templates to display the elements of the structure in the
markup they want. Content can then be styled properly using CSS, because markup
is generated consistently by the template when structured content is displayed.
In essence, templates are scripts that tell Liferay how to display content in
the structure. Any changes to the structure require corresponding changes to the
template, because new or deleted fields produce errors on the page. If users
enter content into a structure, it *must* have a matching template. However, you
have options for whether you want your template to be permanently linked to your
structure. Generic templates are templates that are not tied to a structure,
which allows for reusable code that can be imported into other templates.
Without a template, the portal has no idea how to display content which has been
created using a custom structure.

Let's look more closely at the types of templates Liferay supports.

### Template Types (FTL, VM, and XSL) [](id=template-types-ftl-vm-and-xsl)

Liferay supports templates written in three different templating languages, to
support the skill sets of the largest number of developers. This increases the
chances you can jump right in and use whichever one you've already used before.
If you haven't yet been exposed to any of them, your best bet is FreeMarker or
Velocity, as they are less "chatty" than XSL and extremely simple to
understand.

**FTL** (FreeMarker Template Language): Freemarker is a templating language
which could be considered a successor to Velocity. It has some advantages over
Velocity for which it sacrifices some simplicity, yet it is still easy to use.
If you haven't used any of the template languages before, we recommend using
FreeMarker: you'll get up to speed the fastest.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix
logic with HTML. This is similar to other scripting languages, such as PHP,
though Velocity is much simpler.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to
transform the underlying XML of a structure into markup suitable for the
browser. While it may not be as clean and compact as Velocity or FTL, it's
widely used for transforming XML into other formats and it's very likely your
developers have already been exposed to it.

### Adding Templates [](id=adding-templates)

Liferay WCM makes it easy to create structures, templates, and content from the
same interface. Let's go through the entire flow of how you'd create a
structure, link it to a template and then create content using them both. We'll
use FreeMarker for our template and we'll lay out the structure fields
systematically to go along with the format we've defined for our content.

1. Go back to the Web Content section of the Site Administration page and click
*Add* &rarr; *Basic Web Content*.
2. Click *Select* next to the Structures heading to access the Manage Structures
interface.
3. Click on the *Add* button.
4. Name the structure *News Article* and add the following fields:

	| Field Type | &nbsp;Field Label | &nbsp;Name |
	--------- | ---------- | ---------- |
	Text  | &nbsp;Title | &nbsp;`title` |
	Text Box | &nbsp;Abstract | &nbsp;`abstract` |
	Image | &nbsp;Image | &nbsp;`image` |
	HTML | &nbsp;Body | &nbsp;`body` |

5. Click *Save*.
6. In the Manage Structures interface, click *Choose* next to the News Article
structure that you created.
7. In the New Web Content form, click *Select* next to the Template heading to
access the Manage Templates interface.
8. Click *Add*, enter the name *News Article*, and add a description.
9. Make sure FreeMarker is selected as the script language (it's the default).
10. If you've written the script beforehand, you can select *Browse* to upload
it from your machine. Otherwise, you can type the script directly into the
script editor window.
11. Click *Save*.
12. Exit the Manage Templates interface and click *Select* next to the Template
heading again.
13. Click *Choose* next to the News Article template you created.
14. On the New Web Content form, you'll see the Title, Abstract, Image, and Body
fields that you defined for the News Article structure. The News Article
template should also be selected.
15. Populate the fields and click *Publish* to publish your News Article.

Below is the template script for this structure. It is written in FreeMarker:

    <#assign renderUrlMax = request["render-url-maximized"]>
    <#assign namespace = request["portlet-namespace"]>
    <#assign readmore = request.parameters?is_hash && getterUtil.getBoolean(request.parameters.read_more, false)>
    <h1>${title.getData()}</h1>
    <#if readmore>
    <p>${abstract.getData()}</p>
    <p>${body.getData()}</p>
    <#else>
    <p>
    <img src="${image.getData()}" border="0" align="right">
    ${abstract.getData()}</p>
    <a href="${renderUrlMax}&${namespace}read_more=true">Read More</a>
    </#if>

This template is small but accomplishes a lot. First, a portlet URL which
maximizes the portlet is created. Once this is done, the template gets the
namespace of the portlet. This is important to avoid URL collisions with other
URLs that might be on the page.

After this, the template attempts to get a request parameter called `read_more`.
Whether or not this was successful is the key to the rest of the script:

- If the template got the `read_more` parameter, it displays the abstract and
the body below the title (which is always displayed).

- If the template didn't get the `read_more` parameter, it displays the image,
the abstract and the link created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 3.5: The initial and expanded views for the Lunar Resort News Article. After Clicking *Read More*, you're able to read the full text body.](../../images/03-adv-web-content-structures-templates-completed.png)

<!--Figure needs to be reworked-->

New for Liferay 6.2 is the ability to create generic templates that aren't
connected to a specific structure. In previous versions of Liferay, each
template had to be associated with a structure. Now, you have options for
whether to permanently assign a template to a structure or create a generic
template and reuse its code for any structure. In other words, generic templates
can be embedded in other templates, which allows for reusable code, JS library
imports, or macros which will be imported by Velocity or FreeMarker templates in
the system.

Suppose you have three different Lunar Resort web content articles and structures
with similar aesthetics. Instead of creating three different templates from
scratch, you can use the same generic template for all three and build off of
it. This creates a smarter and more efficient process when creating a multitude
of similar web content articles. Generic templates are created the same way as
regular, structure-based templates. The only setting that differs is the
*Structure* option, which you'll need to leave blank to create a generic
template.

For cases where you're creating your template within Liferay, you can use the
template editor. On the left side of the template editor, you'll notice a
palette of common variables used for making web content templates. This is a
great reference when creating your template. To place one of the variables onto
the template editor, simply position your cursor where you want the variable
placed, and click the variable name. If the variable name doesn't give you
sufficient information on the variable's functionality, you can hover your
pointer over it for a more detailed description.

![Figure 3.6: You can hover your pointer over a variable for a more detailed description.](../../images/04-web-content-templates-create.png)

The interactive template editor is available for the FreeMarker and Velocity
languages. Depending on which language you select, the variable content changes
so you're always adding content in the language you've chosen. Another cool
feature for the template editor is the autocomplete feature. It can be invoked
by typing *${* which opens a drop-down menu of available variables. By clicking
one of the variables, the editor inserts the variable into the template editor.

After you've saved your template, Liferay provides a WebDAV URL and static URL.
These values access the XML source of your structure. You can find these URLs by
returning to your template after it's been saved and expanding the *Details*
section. For more information on WebDAV and the uses of the WebDAV URL,
reference the
[WebDAV Access](/discover/portal/-/knowledge_base/6-2/automatic-previews-and-metadata#webdav-access)
section.

Now that you've created a handsome template and know how to use the template
editor, it's time to decide who the lucky people are that get to use your new
template.

## Assigning Template Permissions [](id=assigning-template-permissions)

Permissions for templates are similar to permissions for structures. As with
structures, you only want specific developers editing and creating templates.
You may, however, want to make the templates viewable to some content creators
who understand the template scripting language but are not directly writing the
scripts. You can determine who views and interacts with the template by
navigating to *Manage* &rarr; *Templates* and selecting *Permissions* from the
*Actions* button.

You can grant or deny permissions based on Roles. For instance, you may create a
role with the ability to update the template and create a second role that can
both update and delete. Liferay Portal makes it possible to assign permissions
based on the roles and responsibilities within your organization.

Now that you understand the role structures and templates play in creating web
content, let's look at how to create RSS feeds in Liferay.
