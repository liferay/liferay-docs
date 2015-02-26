
# Advanced Web Content Management [](id=advanced-web-content-management-liferay-portal-6-2-user-guide-03-en)

In the previous chapter we looked at some basic ways you can use Liferay to
handle your web content. In this chapter we'll delve deeper into slightly more
complex web content management techniques. But don't be alarmed, it's not too
intense. We'll cover the following topics:

- Web content structures and templates
- RSS Feeds
- Site templates and page templates
- Allowing users to customize site pages
- Staging
- Creating teams to allow for flexible management of site permissions
- Mobile device rules

We'll examine how web content structures and templates provide additional power
and flexibility to the web content management system we saw in chapter 2. You'll
also learn how easy it is to set up and administer multiple sites in Liferay.
Next, we'll learn how you can empower your users to create personal
customizations of site pages. We'll also examine how you can use staging to
manage the publication of pages and content on your site. Well conclude with
sections on creating teams and rules for presenting site pages to mobile
devices. Once finished with this chapter, you'll be the envy of your peers as
they'll think you really know what you're doing.

## Advanced Content with Structures and Templates [](id=advanced-content-with-structures-and-te-liferay-portal-6-2-user-guide-03-en)

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

### Using Structures [](id=using-structures-liferay-portal-6-2-user-guide-03-en)

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

#### Editing Structures [](id=editing-structures-liferay-portal-6-2-user-guide-03-en)

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
detail templates and list templates, please refer to chapter 10 on [Using Web
Forms and Dynamic Data
Lists](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-web-forms-and-dynamic-data-lists-liferay-portal-6-2-user-guide-10-en).

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
action, visit the *Document Management* chapter's *WebDAV access* chapter.

---

 ![Note](../../images/01-tip.png) **Note:** Some operating systems require a
 WebDAV server to be class level 2 before (i.e., to support file locking) before
 allowing files to be read or written. For Liferay 6.2, the Documents and Media
 library was upgraded to class level 2 but Web Content structures and templates
 were not. This means that Liferay 6.2's Document and Media library supports
 WebDAV file locking but Web Content structures and templates do not. However,
 on operating systems which require WebDAV servers to be class level 2, it's
 possible to avoid the restriction by using third-party WebDAV clients (e.g.,
 [Cyberduck](http://cyberduck.ch).

---

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

#### Editing Field Settings [](id=editing-field-settings-liferay-portal-6-2-user-guide-03-en)

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

##### Structure Default Values [](id=structure-default-values-liferay-portal-6-2-user-guide-03-en)

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

#### Assigning Permissions [](id=assigning-permissions-liferay-portal-6-2-user-guide-03-en)

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

### Using Templates [](id=using-templates-liferay-portal-6-2-user-guide-03-en)

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

#### Template Types (FTL, VM, and XSL) [](id=template-types-ftl-vm-xsl-and-css-liferay-portal-6-2-user-guide-03-en)

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

#### Adding Templates [](id=adding-templates-liferay-portal-6-2-user-guide-03-en)

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
reference the *WebDAV access* section in the *Document Management* chapter.

Now that you've created a handsome template and know how to use the template
editor, it's time to decide who the lucky people are that get to use your new
template.

### Assigning Template Permissions [](id=assigning-template-permissions-liferay-portal-6-2-user-guide-03-en)

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

## Managing RSS Feeds in Liferay [](id=managing-rss-feeds-in-liferay-liferay-portal-6-2-user-guide-03-en)

RSS is a family of web feed formats used to publish frequently updated works
such as blog entries and news articles. RSS allows users to stay up-to-date with
your site's content without actually having to visit your site! Instead, they
can subscribe to your site's RSS feed with an RSS feed reader. Their RSS reader
reads your site's RSS feed and displays information about all the web content
that's published on your site, such as each article's title and publication
date. If one of your site's web content articles grabs their attention, then
they can follow their RSS reader's link to the article's full content on your
site. Many RSS readers are available today, including web-based readers, ones
for the Windows, Mac, and Linux platforms, and ones for mobile devices. Let's
see how to create RSS feeds in Liferay.

### Managing RSS Feeds from the Control Panel [](id=managing-rss-feeds-from-the-control-pan-liferay-portal-6-2-user-guide-03-en)

To manage a Liferay site's RSS feeds, navigate to the Site Administration &rarr;
Content page of your site and click *Web Content*. Site administrators can use
this Web Content administration portlet to manage their site's web content,
including web content structures and templates, which we examined above. Site
administrators can also use the Web Content administration portlet to manage
their site's RSS feeds. Click *Manage* &rarr; *Feeds* if you'd like to add,
edit, or delete RSS feeds.

---

 ![Note](../../images/01-tip.png) **Note:** The Web Content Feeds portlet is
 deprecated for Liferay 6.2 and will be removed in Liferay 7.0. The portlet will
 be migrated to the Asset Publisher portlet. 

---

![Figure 3.7: Clicking *Manage* &rarr; *Feeds* from the Control Panel's Web Content administration portlet opens a popup window which displays your site's RSS feeds. You can add or edit RSS feeds, configure their permissions, or delete them.](../../images/web-content-manage-feeds.png)

Click the *Add Feed* button to add a new feed. You need to enter a name and
select a target page for the feed. A feed's target page serves two purposes:

1. The site to which the target page belongs determines which web content
   articles appear in the feed. For example, if the target page belongs to the
   Marketing site, only web content articles belong to the Marketing site will
   appear in the feed. 

2. The target page is the page where "orphaned" web content articles will be
   displayed. "Orphaned" web content articles are articles that have been
   published in your site but have not been configured to be displayed in
   specific Web Content Display portlets. Liferay RSS feeds can provide links to
   any published web content articles, both "orphaned" articles and articles
   that have been configured to be displayed in specific Web Content Display
   portlets. For articles that have been configured to be displayed in a
   specific portlet, the RSS feeds' links point to the portal page of that
   portlet. For "orphaned" articles, the RSS feeds' links point to the feed's
   target page. When users click on such links for "orphaned" articles, the full
   content of the "orphaned" article is displayed on the target page.

![Figure 3.8: To create a new RSS feed, you only need to specify a name and a target page. Of course, you can also configure other features of the feed such as its permissions, web content constraints, and presentation settings.](../../images/web-content-new-feed.png)

To specify a target page, you need to enter the target page's friendly URL. Note
that friendly URLs do not include the host name. For example, the friendly URL
of a public page called *Welcome* belonging to a site called *Marketing* might
look like this: `/web/marketing/welcome`. Optionally, you can specify a target
portlet ID. This would be the portlet ID of a Web Content Display portlet on the
target page in which "orphaned" web content should be displayed. The portlet
must exist or else the content will not displayed. The URL field contains the
address of your RSS feed. It appears after you've actually created the feed by
clicking *Save*.

The final two sections of the *Add Feed* form allow you customize which web
content articles appear in your feed.

1. The Web Content Constraints section allows you to select a web content type
   and a structure with which to filter the articles that appear in your feed.
   You can select a particular type of web content such as *Announcements*,
   *News*, or *Press Release*. Only articles of the type you select will appear
   in your feed. You can also choose for only web content articles that have a
   particular structure to appear in your feed. This is useful since customized
   kinds of web content articles are often created using web content structures.

2. The Presentation Settings section allows you to customize additional details
   about your feed and how articles are displayed in your feed. Leave the
   Feed Item Content set to *Web Content Description* if you'd just like a
   description of each article to appear in your feed. Set it to *Rendered Web
   Content: Use Default Template* if you'd like the full content of each article
   to appear in the feed. Customizing the Feed Type allows you to choose which
   web feed language to use for your feed. You can choose *Atom 1.0* (the
   default), *RSS 1.0*, or *RSS 2.0*. Customize the *Maximum Items to Display*
   to choose the maximum number of articles should appear in your feed at one
   time. Leave the Order By Column set to *Modified Date* to have articles
   arranged in order from the last time they were published or modified. You can
   set the Order by Column to *Display Date* if you want to have articles
   arranged in order from the time they were configured to be displayed in a
   specific Web Content Display portlet. Lastly, you can leave the Order by Type
   set to *Ascending* to have the oldest articles at the top of the feed or you
   can set it to *Descending* to have the newest articles at the top of the
   feed.

When you're done configuring your RSS feed, you can click *Preview* to see how
your feed looks. If you're satisfied, click *Save* to create your feed.

Once one or more feeds have been created, they'll appear in a list in the Feeds
popup window when you click *Manage* &rarr; *Feeds*. You can edit existing feeds
using the same form used for creating them. The main difference is that when you
edit an existing feed, the URL field is populated. Copy this URL into a new
browser tab or window to test your feed. From the Feeds popup window, you can
also customize the permissions of feeds or delete feeds.

It's possible to completely disable RSS feeds at the portal level. You can do
this by setting the `rss.feeds.enabled` property to `false`. By default, it's
set to `true`. If you keep the default, RSS enabled, you can make several other
RSS property customizations. Please refer to the RSS section of your
`portal.properties` file for details.

### Using the RSS Portlet [](id=using-the-rss-portlet-liferay-portal-6-2-user-guide-03-en)

The RSS portlet allows you to display any number of RSS feeds and configure how
they are displayed. If you're looking for a web-based RSS reader, look no
further: just add the RSS portlet to one your personal site's private pages, and
voila! You have your own personal RSS reader. Open the portlet's Configuration
popup to select the feeds to be displayed and customize the display. The RSS
portlet can also be placed on sites' public or private pages to make feeds
available to guests or site members, respectively. In these cases, make sure
that only site administrators have permission to customize the RSS portlet and
select feeds to be displayed.

Once you've added the RSS portlet to a page, open the portlet's Configuration
popup window by clicking on the gear icon at the top right corner of the
portlet and selecting *Configuration*.

![Figure 3.9: The RSS portlet's configuration window lets you choose feeds to be displayed and allows you to customize the display settings.](../../images/rss-portlet-config.png)

By default, the RSS portlet displays two feeds. In the Feeds section, click on
the green plus sign to add a new feed or on the red minus sign to remove a feed.
Enter the URL of the RSS feed to display into the URL field. If you leave the
Title field blank, the feed's title appears in the RSS portlet. If you enter a
custom title into the Title field, the custom title appears instead of the
feed's title.

In the Display Settings section, use the following checkboxes to select the feed
details that should be displayed:

- Show Feed Title
- Show Feed Published Date
- Show Feed Description
- Show Feed Image
- Show Feed Item Author

You can also select the number of entries and expanded entries that should be
displayed per feed. Expanded entries show more of an article's actual content
than regular entries. By default, each feed shows eight entries per feed but
only one expanded entry per feed. You can set the feed image alignment to
control whether feed images appear to the right or left of the text. By default,
the feed image alignment is set to *Right*. Finally, you can select a header web
content and/or a footer web content. These are web content articles that appear
in the RSS portlet either above all of the feeds or below all of the feeds. You
can use these to provide an introduction, description, or footnotes about the
feeds that you've selected to be displayed.

![Figure 3.10: By default, the RSS portlet is configured to display feeds from Liferay Community Blogs, Yahoo News, and the New York Times. This image displays what the Liferay Community Blogs feed looks like in the RSS portlet.](../../images/rss-portlet-default-view.png)

Now that we've discussed how to create, manage, and use RSS feeds, let's examine
site templates and page templates. Site templates are a powerful tool for
managing many similar sites. Let's examine how they work and then we'll look at
page templates.

## Using Site Templates [](id=using-site-templates-liferay-portal-6-2-user-guide-03-en)

Site Templates can be administered from the Control Panel. They allow portal
administrators to create multiple sites with the same default set of pages and
content. Site templates can contain multiple pages, each with its own theme,
layout template, portlets, and portlet configurations. Site templates can also
contain content just like actual sites. This allows administrators to use site
templates to create new sites that are each created with the same default pages,
portlets, and content. After they've been created, these sites and their pages
can be modified by site administrators. Using site templates can save site
administrators a lot of work even if each site that was created from a given
site template ends up being very different.

To get started, click on *Site Templates* in the Sites section of the Control
Panel. Here, you can add, manage, or delete site templates. You can also
configure the permissions of site templates. As long as a site is linked to the
site template it was created from, changes to the site template's pages,
portlets, and portlet configurations are propagated to the site. Changes to a
site template's content, however, are not propagated to existing sites that are
linked to the site template. We discuss the propagation of changes between site
templates and sites in more detail in the section on site templates use cases
below.

To manage the pages of a site template, click on *Site Templates* in the Control
Panel and then click *Actions* &rarr; *Manage*. You're provided a left menu
which contains the *Pages*, *Content*, and *Configuration* sections for each
site. By default, the Manage Interface begins with the template's *Site Pages*.
From here, you can add or remove pages from a site template or select themes and
layout templates to apply to the site template. Click on a specific page if
you'd like to select a different theme or layout template for that page. To edit
the pages themselves, click *Actions* &rarr; *View Pages*. You can add specific
portlets to each page of a site template and configure the preferences of each
portlet. Each page can have any theme, any layout template, and any number of
portlet applications, just like a page of a regular site. As with site pages,
you can organize the pages of a site template into hierarchies. When you create
a site using a site template, the configuration of pages and portlets is copied
from the template to the site. By default, all changes made to the site template
are automatically copied to sites based on that template.

---

![tip](../../images/01-tip.png) **Tip:** If you want to publish a piece of web
content to many sites and ensure modifications are applied to all, don't use
site template content for that purpose. Instead, place the content in the global
scope and then reference it from a *Web Content Display* application in each
site.

---

The Content section offers separate repositories for content related portlets
based on your site template. For instance, by clicking *Polls* from the Content
section, you can create a poll question that is only available for that specific
site template. Assets created within your template's Content section can
only be accessed by sites using the template.

Lastly, the Configuration section includes Application Display Template and
Mobile Device configuration options for your site template. Also, nested in the
Configuration section is the *Site Template Settings*. This option allows you to
edit the template's name and description while also offering boolean options for
activating your site template and allowing site administrators to modify pages
associated with your template.

The following figure displays the form shown when editing the *Community Site*
template's settings:

![Figure 3.11: Site templates have several configurable options including the option to allow site administrators to modify pages accociated with the site template.](../../images/01-site-templates.png)

By default, the following site templates are provided:

- **Community Site:** Provides a preconfigured site for building online
  communities. The Home page of a *community site* provides message boards,
  search, a display of a poll and statistics of the activity of community
  members. The site will also be created with a page for a community calendar
  and a page for a wiki.

- **Intranet Site:** Provides a preconfigured site for an intranet. The Home
  page displays the activities of the members of the site, search, a language
  chooser and a list of the recent content created in the intranet. It also
  provides 3 additional pages for *Documents and Media*, *Calendar* and external
  *News* obtained through public feeds.

Now that you know the basics for creating and managing your site templates,
let's put your knowledge to the test by completing an example.

### Site Templates Example [](id=site-templates-example-liferay-portal-6-2-user-guide-03-en)

Suppose we need to create the following three sites for the Lunar Resort's
internal use: Engineering, Marketing, and Legal. These should be private sites
that are only accessible to members of these respective departments. We could
design each site separately but can save ourselves some work if we create a
site template to use instead.

To create a site template, navigate to the Control Panel and click *Site
Templates*. Then click *Add* and enter a name for your template: we'll use
*Department* for our example. Leave the *Active* and *Allow Site Administrators
to Modify the Pages Associated with This Site Template* boxes checked. The
*Active* box must be checked for your template to be usable. If your template is
still a work in progress, you can uncheck it to ensure that no one uses it until
it's ready. Checking *Allow Site Administrators to Modify the Pages Associated
with This Site Template* allows site administrators to modify or remove the
pages and portlets that the template introduces to their sites--if you want the
templates to be completely static, you should uncheck this. Click *Save* to
create your site template.

From the left menu, select the *Pages* section and click *Site Pages*. Then
click on the *View Pages* button to begin adding pages and portlets and
configuring the layouts. When you click this button, the site template opens in
a new browser tab or window. For our example, we would like our site template to
include four pages. First, we'd like a Home page with the Activities,
Announcements, and Calendar portlets. Next, we'd like a Documents and Media page
with the Documents and Media portlet. Finally, we should create a Wiki page with
the Wiki and Tag Cloud portlets and a Message Boards page with the Message
Boards and Tag Cloud portlets. When you're done creating and configuring the
pages of your site template, just close the browser tab or window that opened
when you clicked *View Pages*. Changes to site templates are automatically saved
as you make them, so you don't need to return to the Site Templates page of the
Control Panel and select *Save*.

![Figure 3.12: You can see the name of the site template you're currently editing.](../../images/editing-site-template.png)

Next, let's use our site template to create our Engineering, Marketing and Legal
sites. Go to the Control Panel and click on *Sites*. Then click *Add* &rarr;
*Department*. Enter *Engineering* for the site name and set the Membership Type
to *Private*. Recall that private sites don't appear in the My Sites portlet so
that regular portal users won't even know that the Engineering site exists.
Also, the only way users can be added to a private site is via an invitation
from a site administrator. Leave the *Active* box checked so that your site can
be used immediately. Select the *Copy as Private Pages* option since our
Engineering site is intended for internal use only. Leave the *Enable
propagation of changes from the site template* box checked so that the
Engineering site receives updates if the Department site template is modified.
Finally, click *Save* to create your Engineering site.

Repeat these steps to create the Marketing and Legal sites. The new sites have
all the pages and portlets you created in the site template. To view the pages
of the new sites, click on *Sites* in the Control Panel and then click on
*Actions* &rarr; *Go to Private Pages* next to one of your new sites. Using
site templates streamlines the site creation process for administrators, making
it easy to create sites quickly. Now each department of the Lunar Resort has
its own calendar, documents and media library, wiki, and message boards
application.  Although the pages and portlets of each department's site are the
same, each site will quickly be filled with department-specific information as
users add and share content within the sites. Also, site administrators can add
new pages, portlets, and content to their sites, further differentiating each
department's site from the others.

### Propagating Changes from Site Templates to Sites [](id=propagating-changes-from-site-templates-liferay-portal-6-2-user-guide-03-en)

It's possible for site template administrators to add, update, or delete site
template pages. Changes made to a site template can be propagated to sites whose
page sets are linked to the site template. Such a link is created when you
create a site based on a site template and leave the *Enable propagation of
changes from the site template* box checked. To disable or re-enable this link
for a site, select the site from *My Sites* in the Dockbar. Navigate to *Admin*
&rarr; *Configuration* to open the *Site Settings* page and uncheck or recheck
the *Enable propagation of changes from the site template* checkbox. In this
section, we explain the propagation of changes from site templates to sites and
discuss the options available to site administrators and site template
administrators.

If a site's page set has been created from a site template and the propagation
of changes from the site template is enabled, site administrators can add new
pages but cannot remove or reorder the pages imported from the site template.
If a site has both pages imported from a site template and custom site pages,
the site template pages always appear first; custom pages added by site
administrators appear after the site template pages. Only site template
administrators can remove, reorder, or add site template pages. Site
administrators can add or remove custom site pages. They can also reorder custom
site pages as long as they're all positioned after the site template pages. Site
template administrators cannot add, remove, or reorder custom site pages.

If a site administrator changes a page that was imported from a site template
and refreshes the page, the following message appears:

        This page has been changed since the last update from the site template.
        No further updates from the site template will be applied. Click *Reset*
        to overwrite the changes and receive updates from the site template.

If the site administrator clicks the *Reset* button, changes are propagated from
the site template page to the corresponding site page that was imported from the
site template. Clicking the *Reset* button makes two kinds of updates to a page.
First, changes made by site administrators to the site page are undone. Second,
changes made by site template administrators to the site template page are
applied to the site page. Note: clicking the *Reset* button only resets one
page. If multiple site pages have been modified and you'd like to re-apply the
site template pages to them, you'll need to click the *Reset* button for each
page.

Site template administrators can set preferences for portlets on site template
pages. When a portal administrator creates a site from a site template, the
portlet preferences are copied from the site template's portlets, overriding any
default portlet preferences. When merging site template and site changes, e.g.,
when resetting, portlet preferences are copied from site template portlets to
site portlets. Only global portlet preferences or local portlet preferences
which don't refer to IDs are overwritten.

In some cases, merging site template and site changes fails. For example, if
pages from a site template cannot be propagated because their friendly URLs are
in conflict, the portal could try to continuously merge the site changes.
Instead of entering into an infinite loop of merge fails, Liferay stops the
merge after several unsuccessful attempts. However, Liferay doesn't stop there:
your merge is temporarily paused, you're given an indication of the current
merge fail, and then you have the  opportunity to fix your merge conflicts.
After you've squared away your conflict, navigate to *Site Administration*
&rarr; *Configuration* &rarr; *Site Settings* and click the *Reset and
Propagate* button.

![Figure 3.13: You can reset and propagate the merge fail count by navigationg to *Site Settings*.](../../images/reset-and-propagate.png)

The *Reset and Propagate* button resets the merge fail count and attempts to
propagate your site changes again. This process gives the portal administrator
the opportunity to detect and fix a merge fail, when problems arise. This
helpful process can also be done with page template merges, which follows
similar steps.

Site administrators can also add data to site template portlets. For example,
site template administrators can add the Wiki portlet to a site template page
and use the Wiki to create lots of articles. When a portal administrator creates
a site from a site template, data is copied from the site template's portlets to
the site's portlets. The preferences of the site's portlets are updated with the
IDs of the copied data. For example, if a site is created from a site template
that has a Wiki portlet with lots of wiki articles, the wiki articles are copied
from the site template's scope to the site's scope and site's Wiki portlet is
updated with the IDs of the copied wiki articles. Portlet data is only copied
from a site template to a site when the site is first created; data is not
copied during a site reset.

Now that we've learned how site templates work, let's discuss how to use page
templates. 

## Using Page Templates [](id=using-page-templates-liferay-portal-6-2-user-guide-03-en)

Click on *Page Templates* in the Control Panel to see a list of page templates.
Page templates function similarly to site templates but at the page level. Each
page template provides a pre-configured page to reuse. Within a page template,
it's possible to select a theme, a layout template, to add portlets to the page
and to configure portlet preferences. Both sites and site templates can utilize
page templates for creating new pages.

![Figure 3.14: The Blog page template is already available for use along with the Content Display Page and Wiki page templates.](../../images/server-configuration-page-templates.png)

You can edit or delete existing page templates, configure their permissions, or
add new page templates. By default, three sample page templates are provided:

- **Blog:** provides a page with three applications related to blogging. It has
  two columns, the main left column contains the blogs portlet and the small
  right column provides two side portlets, Tag Cloud and Recent Bloggers. The
  tag cloud application will show the tags used within the site and will allow
  navigating through the blog entries shown in the main blogs portlet.

- **Content Display Page:** provides a page preconfigured to display content. It
  has three auxiliary applications (Tags Navigation, Categories Navigation, and
  Search) and an Asset Publisher. The most significant aspect of this page is
  that the Asset Publisher is preconfigured to be display any web content
  associated with this page. This means that you can select any page created
  from this page template as a *Display Page* for a web content article. You can
  choose a display page for a web content article when creating a new web
  content article or when editing an existing one. When you create a new web
  content article, a unique (canonical) URL for the web content pointing to this
  page will be assigned to it.

- **Wiki:** provides a page with three applications related to authoring a wiki.
  It also has two columns, the main left column with the wiki application and
  two right side portlets to allow navigating through pages by tags and
  categories.

To add a new page template, click the *Add* button. Then enter a name and
description for your template. Leave the *Active* button checked. Click *Save*
and then identify your page template in the list. Click its name or use the
Actions button to edit the page template. The *Open Page Template* link opens a
new browser window which you can use to configure your new page. Any changes you
make are automatically saved so you can close the new browser window once you're
done.

Note that after a new page template has been created the default permissions are
to only allow the creator to use the page template. To give other users access
to it, use the actions menu in the list of templates and choose *Permissions*.
Once you see the matrix of roles and permissions, check the *View* permission
for the role or roles needed to see the page template in the list of available
page templates when creating a new page. If you want any user who can create a
page to be able to use the page template, just check the *View* permission for
the *User* role.

![Figure 3.15: When creating a new site page, you're given options for the page template and page type.](../../images/selecting-page-template.png)

To use your template to create a new page, just navigate to a page over which
you have site administrator privileges and select *Add* &rarr; *Page* from the
left menu. You'll be able to select a page template and type a name for the new
page. Alternatively, you can use the Site Administration page. First, make sure
you're on your desired site and navigate to *Admin* &rarr; *Site
Administration*. Then click on *Site Pages* &rarr; *Add Page*, type a name,
select your template from the drop down menu, and click *Add Page* to finish.

![Figure 3.16: You can choose whether or not to automatically apply page template changes to live pages.](../../images/automatic-application-page-template-changes.png)

Note that by default, when a site administrator creates pages based on a page
template, any future changes to the template are automatically propagated to
those pages. Site administrators can disable this behavior by unchecking the
*Automatically apply changes done to the page template* box. Occasionally,
propagation for page templates fails due to unintended errors. To learn how to
manage a failed page template propagation, visit the *Propagating Changes from
Site Templates to Sites* section of this chapter.

If staging has been enabled, changes to the page template are automatically
propagated to the staged page. These changes still need to be approved before
the page is published to live. For this reason, the automatic propagation of
page template changes to the staged page cannot be turned off and the
*Automatically apply changes done to the page template* checkbox does not
appear.

We'll discuss staging in more detail later in this chapter. For now let's look
at importing and exporting templates.

### Exporting and Importing Site Templates and Page Templates [](id=exporting-and-importing-site-templates--liferay-portal-6-2-user-guide-03-en)

If you want to export a site that uses site or page templates to a different
environment (through a LAR file or remote publication), the templates must be
exported and imported manually in advance or the import will fail.

To export a Site using a Site Template, use the following process:

1. Go to *Control Panel* &rarr; *Site Templates* and click *Actions* &rarr;
*Manage* for the Site Template your site is using.
2. Click *Export* to obtain a LAR file with the content of the Site Template. Be
sure to choose the applications and data you want exported.
3. In your target environment, go to *Control Panel* &rarr; *Site Templates* and
create a new Site Template.
4. Click *Actions* &rarr; *Manage Pages* for that Site Template and then click
*Import*.
5. Upload the LAR file containing your site template's content.

Now the site can be exported and imported normally to this new environment.

For page templates, the process very similar:

1. Go to *Control Panel* &rarr; *Page Templates*.
2. Next to the page template you would like to export, click *Actions* &rarr;
*Export*. This produces a LAR file you can import later.
3. On the target environment, go to *Control Panel* &rarr; *Page Templates* and
create a new Page Template.
4.  Next to the new template, click *Actions* &rarr; *Import*.
5.  Upload the LAR file containing the exported page template from step 3.

The page template can now be imported normally to this new environment.

Next, let's examine the tools Liferay provides for handling translations.

## Localization [](id=localization-liferay-portal-6-2-user-guide-03-en)

When you create a new piece of Web Content, you have the ability to choose a
default language. If you click *Change*, you can select your default language
from a large number of languages Liferay supports. Before you can create a
translation, you must finish creating the content in your default language and
save it. Once you've done that, editing the content provides you with the option
to *Add Translation*.

![Figure 3.17: You have many translation languages to choose from for your web content.](../../images/04-web-content-translation.png)

After you click *Add Translation*, you can select a language by scrolling
through the list or by entering the language you want to use in the search box.
When you select a language, a new window opens within your browser enabling you
to translate the original web content into the selected language. Once you are
done with the translation, click *Save* and the translation is added to the list
of *Available Translations*.

![Figure 3.18: After typing your translated text, you can summarize the translation in the *Abstract* section.](../../images/04-web-content-translation-2.png)

You can modify the language translation list by inserting `locales.enabled=`
followed by your preferred languages in your `portal-ext.properties` file. For
example, `locales.enabled=ar_SA,nl_NL,hi_IN` offers *Arabic (Saudi Arabia)*,
*Dutch (Netherlands)*, and *Hindi (India)*.

The ability to completely delete a translation in one step has also been added.
Instead of simply disabling a translation or having to go through a multistep
process to remove it, you can now simply open the translation you don't want and
click *Remove Translation*.

When you create a new web content structure, each field you create has a
*Localizable* checkbox displayed next to it. This enables you to control what
can and can't be changed in the translation process. For example, if you don't
want images or content titles to be changed when the content is translated, you
can make sure those fields aren't listed as localizable. When you follow the
steps above to localize content, only fields within the structure that had the
*Localizable* box checked appear within the translation window.

---

 ![Note](../../images/01-tip.png) **Note:** The Localizable checkbox was removed
 for 6.2 CE GA1 and GA2, and 6.2 EE GA1, SP1, and SP2. However, the Localizable
 checkbox is now available for Liferay following these versions for web content
 structure fields. You can view
 [LPS-33161](https://issues.liferay.com/browse/LPS-33161) for more details.

---

Next, we'll discuss how to let users customize their site pages.

## Allowing Users to Customize Site Pages [](id=allowing-users-to-customize-site-pages-liferay-portal-6-2-user-guide-03-en)

As we discussed before, as your site becomes larger and more complex, management
of the content becomes more challenging. We've gone over Liferay management
tools that help you create content quickly and in an orderly fashion. You
created a simple announcement with Liferay's structure editor that allows you to
quickly design a structure and prepare it for the template designers. Then you
applied a template to the structure. You know how to display content using the
Web Content Display portlet. Now, you're ready to take advantage of Liferay's
advanced publishing options.

If a web site isn't properly managed, it can quickly become stale and that
drives viewers away. If people are finding your site because of search engines,
you don't want them presented with outdated (and possibly inaccurate) web
content. You also want your content to be found easily by your users. This is
done through tags and categories.

Additionally, you may want to create content and send it through an approval and
review process weeks before you want it displayed on the web site. Liferay gives
you this flexibility with the *Schedule* and *Workflow* features.

## Scheduling Web Content [](id=scheduling-web-content-liferay-portal-6-2-user-guide-03-en)

Liferay's WCM lets you define when your content goes live. You can determine
when the content is displayed, expired and/or reviewed. This is an excellent way
to keep your site current and free from outdated (and perhaps incorrect)
information. The scheduler is built right into the form your users access to add
web content. Specifically, it can be found in the right panel listed with
several other configurable settings.

![Figure 3.19: The web content scheduler can be easily accessed from the right panel of the page.](../../images/04-web-content-schedule.png)

**Display Date:** Sets (within a minute) when content will be displayed.

**Expiration Date:** Sets a date to expire the content. The default is one year.

**Never Auto Expire:** Sets your content to never expire.

**Review Date:** Sets a content review date.

**Never Review:** Sets the content to never be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control
in managing when, and for how long, your web content is displayed on your web
site. Additionally, you have the ability to determine when your content should
be reviewed for accuracy and/or relevance. This makes it possible to manage your
growing inventory of content.

Similar to scheduling, Liferay's staging feature also allows you to manipulate
time, in a manner of speaking.

## Staging Page Publication [](id=staging-page-publication-liferay-portal-6-2-user-guide-03-en)

Staging is an important feature of Liferay WCM. The concept of staging is a
simple one: you can modify your site behind the scenes and then publish all your
updates in one shot. You don't want users seeing your web site change before
their eyes as you're modifying it, do you? Liferay's staging environment allows
you to make changes to your site in a specialized *staging area*. When you're
finished, you can publish all your site changes at once.

Liferay provides site administrators with two different ways to set up staging:
Local Live and Remote Live. With Local Live staging, both your staging
environment and your live environment are hosted on the same server. When Local
Live staging is enabled for a site, a clone of the site is created containing
copies of all of the site's existing pages. Portlet data is also copied,
depending on which portlets are selected when staging is enabled. The cloned
site becomes the staging environment and the original site becomes the live
environment.

When Remote Live staging is enabled for a site, a connection is established
between the current site and another site on a remote Liferay server. The remote
site becomes the live environment and the current site becomes the staging
environment--an instance of Liferay Portal used solely for staging. Content
creators can use the staging server to make their changes while the live server
handles the incoming user traffic. When changes to the site are ready to be
published, they are pushed over the network to the remote live server. Whether
you enable Local Live or Remote Live staging, the interface for managing and
publishing staged pages is the same. 

So when should you use Local Live staging and when should you use Remote Live
Staging? Local Live staging allows you to publish site changes very quickly,
since the staged and live environments are on the same server. It's also easier
to switch between the staged and live environments using Local Live staging.
However, since the staged content is stored in the same database as the
production content, the content isn't as well protected or backed up as with
Remote Live staging. Also, you can't install new versions of portlets for
testing purposes in a Local Live staging environment since only one version of a
portlet can be installed at any given time on a single Liferay server.

With Remote Live staging, your staging and live environments are hosted on
separate servers. This allows you to deploy new versions of portlets and
content to your staging environment without worrying about interfering with your
live environment. With Remote Live staging, you can also use one Liferay
instance as the staging server for multiple production servers. However,
publishing is slower with Remote Live than with Local Live since data needs to
be transferred over a network. And, of course, you need more hardware to run a
separate staging server.

Liferay also offers the Page Versioning feature. This feature works with both
Local Live and Remote Live staging and allows site administrators to create
multiple variations of staged pages. This allows several different versions of
sites and pages to be developed at the same time. Variations can be created,
merged, and published using a Git-like versioning system. Let's jump in to see
how to use staging.

### Enabling Local Live Staging [](id=enabling-local-live-staging-liferay-portal-6-2-user-guide-03-en)

Site administrators can enable Staging for a site via the Site Settings UI. To
reach this interface, navigate to the *Site Administration* &rarr;
*Configuration* page of your site, make sure *Site Settings* is highlighted in
the left menu, and click on *Staging* listed under the Advanced tab. Under
Staging Type, select either *Local Live* or *Remote Live* and additional options
appear. Staging allows changes to be made in a staging environment so that work
can be reviewed, possibly using a workflow, before it's published to a live
site. Enabling Local Live staging is easy. Just select *Local Live* and decide
whether you'd like to enable page versioning. You can enable page versioning on
a site's public pages, private pages, both, or neither. Page versioning allows
you to work in parallel on different versions of pages and maintains a history
of all page modifications. We discuss page versioning in more detail below.

### Enabling Remote Live Staging [](id=enabling-remote-live-staging-liferay-portal-6-2-user-guide-03-en)

When you enable Remote Live staging, the remote site becomes the live
environment and the current site becomes the staging environment. The remote
(live) Liferay server and the local (staging) Liferay server should be
completely separate systems. They should not, for example, share the same the
database. When Remote Live staging is enabled, all the necessary information is
transferred over the network connecting the two servers.

Before a site administrator can enable Remote Live staging for a site, the
remote Liferay server must be added to the current Liferay server's list of
allowed servers. The current Liferay server must also be added to the remote
Liferay server's list of allowed servers. You also need to specify an
authentication key to be shared by your current and your remote server and
enable each Liferay server's tunneling servlet authentication verifier. You can
make all of these configurations in your Liferay servers'
`portal-ext.properties` files.  Your first step should be to add the following
lines to your current Liferay server's `portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Remote server IP address]
    axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP,192.168.0.16,[Remote server IP address]
    tunneling.servlet.shared.secret=[secret]
    auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=

Then add the following lines to your remote Liferay server's
`portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Local server IP address]
    axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP,192.168.0.16,[Local server IP address]
    tunneling.servlet.shared.secret=[secret]
    auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=

Liferay's use of a pre-shared key between your staging and production
environments helps secure the remote publication process. It also removes the
need to send the publishing user's password to the remote server for web service
authentication. Using a pre-shared key allows Liferay to create an authorization
context (permission checker) from the provided email address, screen name, or
user ID *without* the user's password. You can specify any value for the
`tunneling.servlet.shared.secret` property; the value for your current server
just has to match the value of your remote server. Remember to restart both
Liferay servers after making these portal properties updates. After restarting,
log back in to your local Liferay portal instance as a site administrator. Then
navigate to the *Site Administration* &rarr; *Configuration* page for your site.
Next, click on *Site Settings* in the left menu and then on *Staging* listed
under the Advanced tab. Select *Remote Live* under Staging Type and additional
options appear.

![Figure 3.20: After your remote Liferay server and local Liferay server have been configured to communicate with each other, you have to specify a few Remote Live connection settings.](../../images/remote-live-staging-settings.png)

First, enter your remote Liferay server's IP address into the Remote Host/IP
field. If the remote Liferay server is a cluster, you can set the Remote Host/IP
to the load balanced IP address of the cluster in order to increase the
availability of the publishing process. Next, enter the port on which the remote
Liferay instance is running into the Remote Port field. You only need to enter a
Remote Path Context if a non-root portal servlet context is being used on the
remote Liferay server. Finally, enter the site ID of the site on the remote
Liferay server that will be used for the Live environment. If a site hasn't
already been prepared for you on the remote Liferay server, you can log in to
the remote Liferay server and create a new blank site. After the site has been
created, note the site ID so you can enter it into the Remote Site ID field on
your local Liferay server. You can find any site's ID by selecting *Actions
&rarr; Edit* next to the site's name on the Sites page of the Control Panel.
Finally, it's best to check the *Use a Secure Network Connection* field to use
HTTPS for the publication of pages from your local (staging) Liferay server to
your remote (live) Liferay server.

---

 ![Tip](../../images/01-tip.png) **Tip:** In general, remote staging should be
 enabled for a site as early as possible. It's generally *not* a good idea to
 add gigabytes of data into Liferay's CMS and then decide to turn on remote
 staging.  There's an existing issue that limits Liferay to less than 2G of data
 for publishing data to a remote staging server:
 [http://issues.liferay.com/browse/LPS-35317](http://issues.liferay.com/browse/LPS-35317).
 You can check this issue to see if it's been resolved and to find out which
 versions of Liferay it affects.

---

That's all you need to do to enable Remote Live Staging! Note that if you fail
to set the tunneling servlet shared secret or the values of these properties on
your current and remote servers don't match, you won't be able to enable staging
and an error message appears. When a user attempts to publish changes from the
local (staging) server to the remote (live) server, Liferay passes the user's
email address, screen name, or user ID to the remote server to perform a
permission check. In order for a publishing operation to succeed, the operation
must be performed by a user that has identical credentials and permissions on
both the local (staging) and the remote (live) server. This is true regardless
of whether the user attempts to publish the changes immediately or attempts to
schedule the publication for later.

If only a few users should have permission to publish changes from staging to
production, it's easy enough to create a few user accounts on the remote server
that match a selected few on the local server. However, the more user accounts
that you have to create, the more tedious this job becomes and the more likely
you are to make a mistake. And you not only have to create identical user
accounts, you also have to ensure that these users have identical permissions.
For this reason, we recommend that you use LDAP to copy selected user accounts
from your local (staging) Liferay server to your remote (live) Liferay server.
Liferay's Virtual LDAP Server application (EE-only), available on Liferay
Marketplace, makes this easy.

Next, you'll learn how to configure your portal to use IPv6 addresses and use
them for Remote Live Staging. 

#### Validating IPv6 Addresses [](id=validating-ipv6-addresses-liferay-portal-6-2-user-guide-03-en)

If your portal is set up to validate IPv6 addresses, you'll need to configure
your Remote Live Connection Settings. Restart your Liferay instance and navigate
back to the Staging page. Select the *Remote Live* radio selector and specify
the fields for your remote site. The *Remote Host/IP* field should match the
host you specified as your `tunnel.servlet.hosts.allowed` property in the
`portal-ext.properties` file (e.g., *[0:0:0:0:0:0:0:1]*). Make sure to include
the brackets. Fill in the rest of the information relevant to your site and
click *Save*. 

To check if the remote site is running on an IPv6 address, add a new portlet to
the staged site, and then select *Staging* &rarr; *Publish to Remote Live* from
the Dockbar. The changes are published to your remote staged site. 

Your portal instance now validates the IPv6 address you specified for your
remote live site. Great job! 

Next, you'll learn how to enable local live staging.

### Example: Enabling Local Live Staging [](id=example-enabling-local-live-staging-liferay-portal-6-2-user-guide-03-en)

Let's create a Local Live staging environment for the Lunar Resort home page. Before
we begin, let's add a new page. Click *Add &rarr; Page* from the left side menu
in the default site and name the new page *News and Events*. Next, click *News
and Events* to view the page. Then add the Alerts and Announcements portlets to
the News and Events page.

When you activate Local Live staging, Liferay creates a clone of your
site. This clone became the staging environment. Because of this, we recommend
only activating staging on new, clean sites. Having a few pages and some
portlets (like those of the example site we've created) is no big deal. However,
if you have already created a large amount of content you might not be able to
enable staging on that site. Also, if you intend to use page versioning to track
the history of updates to your site, we recommend that you enable it as early as
possible, *before* your site has many pages and lots of content. Your site's
update history won't be saved until you enable page versioning. Page versioning
requires staging (either Local Live or Remote Live) to be enabled.

Now you're ready to activate staging for this site. Go to *Admin* &rarr; *Site
Administration* &rarr; *Configuration* &rarr; *Site Settings* and select 
*Staging* from under the *Advanced* heading. We'll assume we don't have a 
separate staging server so select the *Local Live* staging type. If you do 
have a separate server to use for staging, follow the instructions in the 
previous section for configuring it and your local server for remote staging. 
Either way, once you make a selection (either *Local Live* or *Remote Live*), 
more options become available for page versioning and staged portlets.

### Enabling Page Versioning and Staged Portlets [](id=enabling-page-versioning-and-staged-por-liferay-portal-6-2-user-guide-03-en)

Enabling page versioning for a site allows site administrators to work in
parallel on multiple versions of the site's pages. Page versioning also
maintains a history of all updates to the site from the time page versioning was
enabled. Site administrators can revert to a previous version of the site at any
time. This flexibility is very important in cases where a mistake is found and
it's important to quickly publish a fix. If you're following the Lunar Resort
example, check *Enabled On Public Pages* to enable page versioning for the
Lunar Resort site and then click *Save*.

![Figure 3.21: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

Before you activate staging, you can choose which portlets' data should be
copied to staging. We'll cover many of the collaboration portlets listed under
the Staged Portlets heading when we come to chapter 8. For now, you just need to
be aware that you can enable or disable staging for any of these portlets. Why
might you want to enable staging for some portlet types but not others? In the
case of collaborative portlets, you probably *don't* want to enable staging
since such portlets are designed for user interaction. If their content were
staged, you'd have to manually publish your site whenever somebody posted a
message on the message boards to make that message appear on the live site.
Generally, you'll want web content to be staged because end users aren't
creating that kind of content--web content is the stuff you publish to your
site. But portlets like the Message Boards or Wiki would likely benefit from
*not* being staged. Notice which portlets are marked for staging by default: if
you enable staging and accept the defaults, staging is *not* enabled for the
collaborative portlets.

### Using the Staging Environment [](id=using-the-staging-environment-liferay-portal-6-2-user-guide-03-en)

After enabling staging (either Local Live or Remote Live) for a site, you'll
notice a colored bar with some new menus on the Dockbar when you navigate to
the site. These new menus help us manage staged pages. You'll also notice that
most of your page management options have been removed, because now you can't
directly edit live pages. You now must use the staging environment to make
changes. Click on the *Staging* button to view the staged area. Your
management options are restored and you can access some new options related to
staging. If you're following along with the Lunar Resort example, navigate back
to the News and Events page and click on *Staging* to get your page editing
capabilities back.

![Figure 3.22: You can see the new staging options added to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Bookmarks portlet and then click on *Live* from the Dockbar. Notice that
the Bookmarks portlet isn't there. That's because you've staged a change to the
page but haven't published that change yet to the live site. Go back to the
staged page and click on the *Staging* drop-down menu to look at the options you
have available. From here you have many options to choose from to help in your
staging conquest.

**Manage Site Pages Variations:** allows you to work in parallel on multiple
versions of a staged site page. We'll explain this later.

**Manage Page Variations:** allows you to work in parallel on multiple versions
of a staged page. We'll explain this later.

**Undo/Redo:** allows you to step back/forward through recent changes to a page,
which can save you the time of manually adding or removing portlets if you make
a mistake.

**History:** shows you the list of revisions of the page, based on publication
dates. You can go to any change in the revision history and see how the pages
looked at that point.

**Mark as Ready for Publication:** After you're done making changes to the
staged page, click this button. The status of the page changes from *Draft* to
*Ready for Publication* and any changes you've made can be published to the Live
Site. When you publish a page to live, only the version which was *Marked as
Ready for Publication* is published.

The *Publish to Live* button gives you the option to publish to Live *Now* or
*Schedule* a publication to Live.

**Now:** immediately pushes any changes to the Live Site.

**Schedule:** lets you set a specific date to publish or to set up recurring
publishing. You could use this, for example, to publish all changes made during
the week every Monday morning without any further intervention.

The *Publish to Live* button also gives you options to select pages,
applications, content, and permissions.

**Pages** gives you the option to choose which pages to include when you
publish. You can see the default settings in the gray text below the header.
Click the *Change* button to configure these options to suite your needs.

**Application Configuration** allows you to select which applications you want
to publish. Select the *Choose Applications* radio button to configure
the applications and settings to be published.

**Content** allows you to configure the content to be published. Clicking the 
*select* option under All Content allows you to delete the portlet metadata
before publishing.

**Permissions** allows you to include permissions for the pages and portlets
when the changes are published.

Furthermore, you're given the Current and Previous and Scheduled tabs. 
The Current and Previous tab lets you view past publications along with their 
authors and create/completion dates. The Scheduled tab lets you view what 
publication processes are scheduled for publishing.

Click on *Mark as Ready for Publication*, then click *Publish to Live* &rarr;
*Now*, and select *Publish* to publish your Bookmarks portlet to the live site.

Content publication can be also controlled using staging. Bookmarks are staged
by default (this can be changed in Staging Configuration). If you create a
bookmark in the staged site, it isn't visible in the live site until you publish
it to the live site following the same steps you just performed (you can select
which types of content are published when you publish to the live site). If
workflow is enabled for any new resource, the resource needs to go through the
workflow process before it can be published to the live site.

![Figure 3.23: Ready to publish to the live site.](../../images/04-web-content-staging-publish.png)

Web content tends to be frequently updated, often more so than other kinds of
content. For some web content articles, this can result in very high numbers of
versions, sometimes hundreds. Such high version numbers can make it very slow to
publish web content articles. Liferay addresses this issue by allowing site
administrators to choose whether or not to publish a web content article's
version history when a staged article is ready to be published. To use this
feature, staging must be enabled. Edit a web content article that's being
displayed in a Web Content Display portlet on one of your pages and then click
*Staging* &rarr; *Publish to Live* from the Dockbar. In the popup, expand the
Content heading and find the section for Web Content. You can click the *Change*
button to select/deselect options to publish dealing with your new Web Content
instance.

![Figure 3.24: Click the *Change* button and uncheck the version history box to only publish the latest approved version of web content articles that have multiple versions.](../../images/web-content-version-history-box.png)

Liferay 6.2 also added a portal property,
`journal.publish.version.history.by.default`, that sets the default behavior. By
default, this property is set to `true` so site administrators have to manually
uncheck the *Version History* box mentioned above if they only want to publish 
the latest approved version of web content articles. If you'd like to change the 
default behavior, add the following line to your `portal-ext.properties` file:
`journal.publish.version.history.by.default=false`.

One of the most powerful features of staging is page variations. Let's see how
to use them to create multiple different variations of your site's pages for
different purposes.

### Using Site Pages Variations [](id=using-site-pages-variations-liferay-portal-6-2-user-guide-03-en)

Let's say you're working on a product-oriented site where you'll have several
major changes to a page or a set of pages over a short period of time. Also you
need to be working on multiple versions of the site at the same time to ensure
everything has been properly reviewed before it goes live. With staging in
Liferay 6.2 you can do this using *Page Variations*.

For example, you can create several page variations, enabling the marketing team
to give your site a completely different look and feel for Christmas. At the
same time, the product management team can work on a different version that will
be published the day after Christmas for the launching of a new product.
Additionally, the product management team is considering two different ideas for
the home page of the site, so they can create several page variations of the
home page inside their product launch site.

Variations only affect pages and not the content, which means all the existing
content in your staging site is shared by all your variations. In different site
page variations you can have different logos, different look and feel for your
pages, different applications on these pages, different configuration of these
applications and even different pages. One page can exist in just one site page
variation or in several of them.

By default, we only have one site page variation which is called *Main
Variation*. To create a new one, use the dropdown next to the *Staging* link
and click on *Manage Site Pages Variations*. This brings you to a list of the
existing site page variations for your site. Click *Add Site Pages Variation* to
create a new one. From the *Add Site Pages Variation* screen, you can set a
Name, Description, and also set your new variation to copy the content from an
existing variation. There are several options to choose in this selector.

**All Site Pages Variations:** creates a new variation that contains the last
version marked as ready for publication from any single page existing in any
other variation.

**None (Empty Site Pages Variation):** creates a new, empty variation.

**Main Variation:** creates a new site page variation that contains only the
last version of all the pages that exist in this variation. The current
variation must be marked as ready for publication.

You are also able to rename any variation. For example, edit the Main Variation
and change its name to something that makes more sense in your site, such as
*Basic*, *Master*, *Regular* and create a variation for Christmas.

You can switch between different variations by clicking on them from the staging
menu bar. It's also possible to set permissions on each variation, so certain
users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo,
apply a new theme, move portlets around, change the order of the pages and
configure different portlets. The other variations won't be affected. You can
even delete existing pages or add new ones (remember to *Mark as Ready for
Publication* when you are finished with your changes).

When you delete a page, it is deleted only in the current variation. The same
happens when you add a new page. If you try to access a page which was deleted
in the current variation, Liferay informs you this page is not *enabled* in this
variation and you must enable it. To enable it, navigate to the *Site
Administration* &rarr; *Pages* &rarr; *Site Pages* screen. Here all the existing
pages for all the variations are shown in a tree. Pages not enabled for the
current variation are shown in a lighter color. You can also access Staging
options from the Site Pages screen by clicking the *Staging* drop-down menu
located above the pages tree.

To publish a variation to the live site, click on *Publish to Live* &rarr;
*Publish* in the dropdown next to the variation name. Publications can also be
scheduled independently for different variations. For example, you could have a
variation called *Mondays* which is published to the live site every Monday and
another one called *Day 1* which is published to the live site every first day
of each month.

You can also have variations for a single page inside a site page variation,
which allows you to work in parallel in different versions of a page. For
example, you might work on two different proposals for the design of the home
page for the Christmas variation. These page variations only exist inside a site
Page variation.

To create a new page variation, click *Manage Page Variations* on the staging
toolbar. This brings you to a list of existing page variations for the current
page (by default, there is only one called *Main Variation*). You can create
more or rename the existing one. You can switch between different page
variations using the toolbar containing the page variations below the site pages
variations toolbar. When you decide which page variation should be published,
mark it as *Ready for Publication*. Only one page variation can be marked as
ready for publication and that is the one that gets published to the live site.

For example, we could create a page variation called Thanksgiving for the News
and Events page inside of the Christmas variation and another one called
Christmas Day to display different content on those particular days.

![Figure 3.25: This is an example of a Thanksgiving Page Variation.](../../images/04-web-content-branch-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations.
To merge two Site Pages Variations, you need to go to the Manage Site Pages
Variations screen. From there, click on *Merge* on the Site Pages Variation you
want to use as the base. You will be asked to choose the Site Pages Variation to
merge on top of it. Merging works in the following way:

* New pages that don't exist in the base Variation, will be added.
* If a page exists in both Site Pages variations, and at least one version of
the page was marked as ready for publication, then the latest version marked as
ready will be added as a new Page Variation in the target page of the base
Variation. (Note that older versions or page variations not marked as ready for
publication won't be copied. However, merge can be executed as many times as
needed and will create the needed pages variations in the appropriate page of
the base Site Pages Variation).
* Merging does not affect content nor will overwrite anything in the base
Variation, it will just add more versions, pages and page variations as needed.

Let's finish our discussion of staging by outlining a few more features.

### Wrapping up Staging [](id=wrapping-up-staging-liferay-portal-6-2-user-guide-03-en)

You can enable staging on an individual site basis, depending on your needs.
This makes it easy to put strict controls in place for your public web site,
while opening things up for individual sites that don't need such strict
controls. Liferay's staging environment is extremely easy to use and makes
maintaining a content-rich web site a snap.

Let's examine teams next.

## Creating Teams for Advanced Site Membership Management [](id=creating-teams-for-advanced-site-member-liferay-portal-6-2-user-guide-03-en)

Site Teams are ad hoc groups of a site's users, that perform the same set of
tasks in the site. Site administrators can assign these teams permissions for
various site-specific functions. Site Teams are the preferred method for
collecting permissions within a single site. Some common functions to assign
a Site Team include:

- Moderating site Wiki content, Message Boards threads, etc.
- Writing blogs
- Editing a specific page in the site

For instance, if your site has Message Boards, you might want to enable a subset
of the site's members to moderate the categories and threads, and perhaps to ban
abusive/offensive posters. To do this, you could create a Site Team named *Web
Moderators*, define the team's permissions on the Message Boards portlet, and
assign the desired site members to the team. 

The permissions assigned to a Site Team only apply to that site. Knowing that
a team's permissions don't impact other sites, the site administrator can
concentrate on defining and applying permissions to his site's teams. 

---

![Tip](../../images/01-tip.png) **Tip:** To create and apply permissions for a
group of users to use across multiple sites or organizations in your portal,
consider aggregating the users into a [User Group](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/user-groups)
and assigning the User Group permissions via
[Roles](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/roles-and-permissions). 

---

To create a team within a site, first navigate to the Site Administration page
of your site and select *Users* &rarr; *Site Teams*. It's important to note that
configuring other site membership groupings, such as *Users*, *Organizations*,
and *User Groups* can be done in the *Site Memberships* portlet, which is also
located in the Users tab. You can visit the 
[User Management](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/user-management)
chapter for more information on how these site memberships work. Finally, click the
*Add Team* button. 

![Figure 3.26: Creating teams within your site can foster teamwork and collaboration, as team permissions enable team members to access the same resources and perform the same types of tasks.](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description,
click *Save*. Your new team shows in the list. To add members, simply click on
*Actions &rarr; Assign Members*.

To manage a team's permissions, click on *Actions* &rarr; *Permissions* for that
team. Setting permissions for the team assigns all of the team's members those
permissions. Only administrators with the ability to edit/manage the team have
the ability to manage team permissions.

If created team whose task is to moderate the Message Boards, for example, you'd
want to give the team all of the permissions they'd need. So you'd Navigate to
*Site Administration* &rarr; *Content* &rarr; *Message Boards* &rarr;
*Permissions*, find the team in the Role column, and select the appropriate
permissions.

![Figure 3.27: The Troll-busters Site Team has unlimited permissions on the Message Boards portlet.](../../images/site-team-permissions-message-boards.png)

That's it! It's easy to give groups of site users appropriate permissions to
perform their tasks. Next, let's look at how to configure Liferay for mobile
devices.

## Displaying Site Pages to Mobile Devices [](id=displaying-site-pages-to-mobile-devices-liferay-portal-6-2-user-guide-03-en)

Mobile device families allow you to configure sets of rules to alter the
behavior of the portal based on the device being used to access Liferay. The
proportion of mobile device users browsing the web has been steadily increasing,
so it's important to be able to handle different kinds of devices appropriately.
For instance, you can configure the look and feel of Liferay pages accessed by
smartphone or tablet users differently from those accessed by PC users.

Both sites and individual pages can be configured with any number of mobile
device families. A family is designed to describe a group of devices. It can
contain one or more rules that describe a category of devices, such as all
Android devices or all iOS tablets. You can define as many rules in a family as
you need to classify all the devices for which you'd like to define actions.
Families can be prioritized to determine which one applies to a given page
request.

In order to configure mobile device rules, you need a way to find out the
characteristics of the device. While some of the characteristics are provided by
the device, most are not. For this reason, there are databases that contain
information about thousands of devices. These databases make it possible to
learn every detail about a device from the device type, which is included in
each request sent to the portal. Liferay's Mobile Device Rules can connect to
device databases so that you can use their device characteristics in your rules. 

Among the applications available on Liferay Marketplace, you can find the Device
Recognition Provider EE application. This app provides out-of-the-box
integration with WURFL, an open source database licensed with the AGPLv3
license. Commercial licenses are also available. It's also possible to develop
plugins that integrate with other device databases. Even if you don't have a
device database, you can still set up mobile device rules. They won't, however,
be effective until a database is deployed, because the portal won't have enough
information about the devices being used to make page requests.

To configure mobile device rules, you must install the Device Recognition
Provider EE application on your instance of Liferay Portal EE. This app uses the
WURFL database to enable Liferay to detect which mobile device or operating
system is being used for any given request. To install the app, navigate to the
Store section of the Control Panel, located under the Marketplace heading. Click
on the *Utility* section and then on *See All*. Search for the Device
Recognition Provider EE application and click on it. Finally, click on the price
tag to begin purchasing the app. Once you've acquired the app, you need to
download and install it. To do so, navigate to the Purchased section of the
Control Panel, find your Device Recognition Provider EE app, and click on
*Download* and then *Install*.

**Installation Note:** If your server doesn't have access to the outside
Internet, an error appears in your log: `SLF4J: Failed to load class
"org.slf4j.impl.StaticLoggerBinder`. This occurs because WURFL by default
downloads device information from the web. You can provide the same information
to WURFL manually by downloading the SLF4J distribution from
[http://www.slf4j.org/download.html](http://www.slf4j.org/download.html),
unzipping the resulting file, copying `slf4j-log4j12-<version>.jar` to
`[WEB_APP_HOME]/wurfl-web/WEB-INF/lib` folder, and restarting your Liferay
instance. On some application servers, you'll need to add this .jar file to the
`wurfl-web.war` file first (in the directory noted above) before deploying the
file to your server. 

You can access the Mobile Device Families administrative page from the
Configuration section of Site Administration. Make sure you're on the
appropriate site before adding mobile device families via Site Administration.
You can also add families for all sites by navigating to the Control Panel
&rarr; *Sites* &rarr; *Global*. The Mobile Device Families administrative page
displays a list of defined families and lets you add more. To add rules to a
family, select *Actions* &rarr; *Manage Classification Rules*, or click on a
family to edit it, and then click the *Manage Classification Rules* link.

![Figure 3.28: You can manage device rules from the Mobile Device Families administrative page.](../../images/mobile-device-families.png)

The rules defined for a family, along with the priorities of the families
selected for a particular site or page, determine which family's actions are
applied to a given request. From the Manage Classification Rules page for a
specific rule set, you can add a rule by specifying an operating system, rule
type, physical screen size, and screen resolution. Remember that you can add as
many rules to a family as you need in order to classify the devices on which
you'd like to take actions. You'll notice after saving the classification rule
that it's characterized as a *Simple Rule*. By default, only the Simple Rule
type is available. The rules are designed to be extensible, and additional rule
types can be added by your developers.

![Figure 3.29: You need to install the Device Recognition Provider EE application to populate the OS list.](../../images/mobile-device-editing-rule.png)

Once you've created some mobile device families and added some rules to them,
you'll be ready to set up some actions. The actions defined for a family
determine what happens to a particular request when the device is detected and
the family has been found to apply.

You can add actions to a family from the Site Pages page of Site Administration.
Select either the public or private pages and then look for the *Mobile Device
Rules* link in the right-hand menu. Use the *Select Device Family* button to
select families to be applied either to a site or to a single page. If you
select the page group itself from the left-hand menu, the selected family
applies to all the pages of the site by default. If, however, you select an
individual page and then click the *Select Device Family* button, the families
apply only to that page. You can select multiple families for a particular site
or page and order them by priority. The families are checked in decreasing order
of priority: the actions defined by the first family that applies are executed.

![Figure 3.30: You can select a mobile device family to apply for a site or page from the Site Pages section of Site Administration.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage
Actions* button and then click *Add Action*. By default, there are four kinds of
actions that can be configured for mobile families: layout template
modifications, theme modifications, URL redirects, and site redirects. Layout
template modifications let you change the way portlets are arranged on pages
delivered to mobile devices, and themes modifications let you select a specific
look and feel. If it makes more sense for you to create separate mobile versions
of certain sites or pages, you can use a redirect to make sure mobile device
users get to the right page. To define a URL redirect, you need to specify a
URL. To define a site redirect, you need only specify the site name and page
name of the page to which you're redirecting. Like mobile device rules, mobile
device actions are designed to be extensible. Your developers can define custom
actions in addition to the four actions provided by default.

To review, if you'd like to configure an action or actions that take place when
mobile device requests are received, take the following steps:

1. Create a mobile device family to represent the group of devices for which to
   define an action or actions.

2. Define one or more rules for your family that describe the group of
   devices represented by your family.

3. Apply your family to an entire page set of a site (all the public pages of a
   site or all the private pages) or to a single page.

4. Define one or more actions for your family that describe how requests should
   be handled.

To see how this might work in practice, let's discuss a few examples of how you
can use mobile device rules. First, suppose you have a separate version of a
site on your portal that's specifically designed for mobile phones running
Android or Bada. For our example, we'll make a site called Android/Bada Liferay
and we'll configure the default Liferay site to redirect incoming requests from
Android or Bada mobile phones to the Android/Bada Liferay site. Our first step
is to create the Android/Bada Liferay site: go to the Sites page of the Control
Panel and click *Add* &rarr; *Blank Site*. Enter the name *Android/Bada Liferay*
and click *Save*. Then, with Android/Bada selected in the context menu selector,
click on *Site Pages*. By default, the newly created site doesn't have any
pages, so click on *Add Page*, enter the name *Welcome*, and click the *Add
Page* button. Now our Android/Bada Liferay site has a public Welcome page just
like our default Liferay site.

Next, select *Liferay* in the *My Sites* tab of the Dockbar, navigate to Site
Administration &rarr; Configuration, and click on *Mobile Device Families*.
Click on *Add Device Family*, enter the name *Android and Bada Mobile Phones*, and
click *Save*. You'll see the message, *No rules are configured for this rule
group*.

Click the *Manage Classification Rules* link and we'll configure our rule group
to apply only to mobile phones running Android or Bada. Click *Add
Classification Rule*, enter *Rule 1* for the name. Under Operating System,
select *Android* and *Bada OS* (hold down Control to make multiple selections),
select *Other Devices* under Tablet since we want our family to apply only to
mobile phones, and click *Save*. Now we just need to define the redirect action
for our family. Navigate to *Pages* &rarr; *Site Pages* and click on *Mobile
Device Rules* in the navigation menu to the right.

![Figure 3.31: To apply a mobile device family to a page set of a site, click on *Mobile Device Rules*, click *Select Device Family*, and select the desired rule group.](../../images/site-pages-mobile-device-rules.png)

Click *Select Device Family* and then click the *Android and Bada Mobile Phones*
device family that you configured. Once you've selected your device family,
click on your device family or *Actions* &rarr; *Manage Actions* next to it.
Then click *Add Action*, enter the name *Android/Bada Liferay Redirect*, and
select *Redirect to Site* under Type. Under the Site dropdown menu that appears,
select *Android/Bada Liferay* and under the Page dropdown menu that appears,
select the *Welcome* page that you created earlier. Lastly, click *Save*. That's
it! Now Android and Bada mobile phone users are redirected to the Android/Bada
Liferay site from the Liferay site. 

Let's look at one more example of using mobile device rules before we move on.
Suppose you'd like to create another rule so that when a site is accessed by an
Android or iOS tablet, a different layout is used. To set this up, we need to
follow the same four steps described above. First, make sure you're on the
Liferay site by checking in the My Sites tab of the Dockbar. Then navigate to
the Mobile Device Families page of Site Administration. Add a new device family
called *Android and iOS Tablets*. Add a classification rule called *Rule 1*,
select *Android and iPhone OS* under the *Operating System heading*, select
*Tablets* under the *Device Type* heading, then click *Save*. As with the
previous example, we only need one rule to describe our device family.

Next, click on *Site Pages* in Site Administration, select *Mobile Device
Rules*, and select the *Android and iOS Tablets* device family. Notice that
you've now selected two rule groups for the Liferay site's public pages and
they've been assigned priorities. If a device making a request belongs to both
of the device families represented by the rule groups, the priority of the rule
groups determines which rule group's actions are executed. Note that in our
example, the first rule group contains only mobile phones and the second rule
group contains only tablets, so no devices can belong to both rule groups. Now
we just need to define an action for our Android and iOS Tablets rule group to
use a different layout: On the Site Pages page of Site Administration, click on
*Mobile Device Rules*, and then on *Actions* &rarr; *Manage Actions* next to
Android and iOS Tablets. Click on *Add Action*, enter the name *Layout Template
Modification*, and select the *Layout Template Modification* action type.
Lastly, select the *1 Column* layout template (or whichever one you like) and
click *Save*. Good job! Now the Liferay site's pages are presented to Android
and iOS tablet users with the 1 Column layout template.

## Summary [](id=summary-liferay-portal-6-2-user-guide-03-en)

This chapter has been your guide to advanced Liferay site management and web
content management. We've seen how you can use Liferay to manage both simple
content and advanced content with structures and templates. We've also learned
how to use page and site templates to simplify the site creation process.

Liferay WCM also includes a powerful staging environment, allowing you to stage
content locally on the same server or remotely to another server. You can
publish your site when you want it, on the schedule you choose. You can even
create different variations of your site that can be worked on simultaneously.

Site administrators can allow users to create personal customizations of site
pages. We discussed how site administrators can create teams as a flexible means
of delegating site permissions. We also saw how to configure mobile device rules
so that site pages are presented differently depending on the device making a
page request.

Whether your site is small and static or large and dynamic, Liferay's WCM
enables you to plan and manage it. With tools such as the WYSIWYG editor,
structures and templates, you can quickly add and edit content. With the Web
Content Display and Asset Publisher, you can rapidly select and configure what
content to display and how to display it. By using Liferay's integrated
workflow, you can set up custom publishing rules to fit your organization. And
by using Liferay's staging and scheduling mechanisms, you can manage various
branches of pages and content and control when they are published to your live
portal instance. You will find that managing your site becomes far easier when
using Liferay's Web Content Management system.
