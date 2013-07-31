
# Advanced Web Content Management   

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

## Advanced Content with Structures and Templates  

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

### Using Structures  

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

#### Editing Structures

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
detail templates and list templates, please refer to chapter 9 on [Dynamic Data
Lists](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/dynamic-data-lists-in-liferay).

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
For example, if you'd like to create an in-depth Nose-ster sports article in
addition to a regular Nose-ster sports article, you can simply inherit the
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

Another method to edit your structure is switching to *Source* mode and manually
customizing your structure by editing its XML file. You'll notice by default the
*View* mode is selected. Click the *Source* tab to switch to Source mode. This
method is for the more experienced developers.

Take a moment to add, delete, and rearrange different elements.

![Figure 3.2: The structure editor gives you many options to customize your Web Content.](../../images/04-web-content-structure-editor.png)

<!-- Needs to be replaced for 6.2. -->
<!-- Icons not appearing at the present time for WC structure editor -->

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

#### Editing Field Settings  

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

For the Nose-ster structure, type something in the *Tip* field that helps users
know what to put into the Body element (example: *This is an HTML text area for
the body of your content*). Now, when users hover over the Help icon near your
title, your tip is displayed.

##### Structure Default Values

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

#### Assigning Permissions  

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

### Using Templates  

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

#### Template Types (FTL, VM, XSL, and CSS)  

Liferay supports templates written in four different templating languages, to
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

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very
straightforward and modifications are simple (colors, fonts, layouts, etc.). If
your structure is more complex, however, you'll need to use one of the other
options.

<!-- CSS was not available during testing of 6.2 feature. Notified Juan
Fernandez of missing template language. Bug filed for fix: LPS-37934 -->

#### Adding Templates

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

This template is pretty small but it actually does quite a bit. First, a portlet
URL which maximizes the portlet is created. Once this is done, the template gets
the namespace of the portlet. This is important to avoid URL collisions with
other URLs that might be on the page.

After this, the template attempts to get a request parameter called `read_more`.
Whether or not this was successful is the key to the rest of the script:

- If the template got the `read_more` parameter, it displays the abstract and
the body below the title (which is always displayed).

- If the template didn't get the `read_more` parameter, it displays the image,
the abstract and the link created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 3.5: The initial and expanded views for the Nose-ster News Article. After Clicking *Read More*, you're able to read the full text body.](../../images/04-web-content-adv-example1.png)

New for Liferay 6.2 is the ability to create generic templates that aren't
connected to a specific structure. In previous versions of Liferay, each
template had to be associated with a structure. Now, you have options for
whether to permanently assign a template to a structure or create a generic
template and reuse its code for any structure. In other words, generic templates
can be embedded in other templates, which allows for reusable code, JS library
imports, or macros which will be imported by Velocity or FreeMarker templates in
the system.

Suppose you have three different Nosester web content articles and structures
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

### Assigning Template Permissions  

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

## Managing RSS Feeds in Liferay

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

### Managing RSS Feeds from the Control Panel

To manage a Liferay site's RSS feeds, navigate to the Site Administration page
of your site and click *Web Content*. Site administrators can use this Web
Content administration portlet to manage their site's web content, including web
content structures and templates, which we examined above. Site administrators
can also use the Web Content administration portlet to manage their site's RSS
feeds. Click *Manage* &rarr; *Feeds* if you'd like to add, edit, or delete RSS
feeds.

![Figure 3.7: Clicking *Manage* &rarr; *Feeds* from the Control Panel's Web Content administration portlet opens a popup window which displays your site's RSS feeds. You can add or edit RSS feeds, configure their permissions, or delete them.](../../images/web-content-manage-feeds.png)

<!-- Needs to be replaced for 6.2. -->

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

![Figure 3.8: To create a new RSS feed, you only need to specify a name and a target page. Of course, you can also configure other features the feed such as its permissions, web content constraints, and presentation settings.](../../images/web-content-new-feed.png)

<!-- Needs to be replaced for 6.2. -->

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

### Using the RSS Portlet

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
popup window by clicking on the wrench icon at the top right corner of the
portlet and selecting *Configuration*.

![Figure 3.9: The RSS portlet's configuration window lets you choose feeds to be displayed and allows you to customize the display settings.](../../images/rss-portlet-config.png)

<!-- Needs to be replaced for 6.2. -->

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

![Figure 3.10: By default, the RSS portlet is configured to display feeds from Yahoo News and the New York Times. Open the portlet's configuration window to add, edit, or feeds.](../../images/rss-portlet-default-view.png)

<!-- Needs to be replaced for 6.2. -->

Now that we've discussed how to create, manage, and use RSS feeds, let's examine
site templates and page templates. Site templates are a powerful tool for
managing many similar sites. Let's examine how they work and then we'll look at
page templates.

## Using Site Templates  

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
Panel and then click *Actions* &rarr; *Manage Pages*. From here, you can add or
remove pages from a site template or select themes and layout templates to apply
to the site template. Click on a specific page if you'd like to select a
different theme or layout template for that page. To edit the pages themselves,
click *Actions* &rarr; *View Pages*. You can add specific portlets to each page
of a site template and configure the preferences of each portlet. Each page can
have any theme, any layout template, and any number of portlet applications,
just like a page of a regular site. As with site pages, you can organize the
pages of a site template into hierarchies. When you create a site using a site
template, the configuration of pages and portlets is copied from the template to
the site.  By default, all changes made to the site template are automatically
copied to sites based on that template.

---

![tip](../../images/01-tip.png) **Tip:** If you want to publish a piece of web
content to many sites and ensure modifications are applied to all, don't use
site template content for that purpose. Instead, place the content in the global
scope and then reference it from a *Web Content Display* application in each
site.

---

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

The following figure displays the form shown when editing the *Community Site*
template:

![Figure 3.13: Site templates have several configurable options including the option to allow site administrators to modify pages accociated with the site template.](../../images/01-site-templates.png)

To view and manage the pages of a site template, click the *Open site template*
link. This opens the template in a new browser window (or tab) and it can be
navigated or managed like a regular site.

### Site Templates Example

Suppose we need to create the following three sites for Nose-ster's internal
use: Engineering, Marketing, and Legal. These should be private sites that are
only accessible to members of these respective departments. We could design each
site separately but can save ourselves some work if we create a site template to
use instead.

To create a site template, navigate to the Control Panel and click *Site
Templates*. Then click *Add* and enter a name for your template: we'll use
*Department* for our example. Leave the *Active* and *Allow Site Administrators
to Modify the Pages Associated with This Site Template* boxes checked. The
*Active* box must be checked for your template to be usable. If your template is
still a work in progress, you can uncheck it to ensure that no one uses it until
it's ready. Checking *Allow Site Administrators to Modify the Pages Associated
with This Site Template* allows site administrators to modify or remove the
pages and portlets that the template introduces to their sites--if you want the
templates to be completely static, you should uncheck this.

From the list of site templates, click on the *Department* site template that
you created. Then click on the *Open site template* link to begin adding pages
and portlets and configuring the layouts. When you click this link, the site
template opens in a new browser tab or window. For our example, we would like
our site template to include four pages. First, we'd like a Home page with the
Activities, Announcements, and Calendar portlets. Next, we'd like a Documents
and Media page with the Documents and Media portlet. Finally, we should create a
Wiki page with the Wiki and Tag Cloud portlets and a Message Boards page with
the Message Boards and Tag Cloud portlets. When you're done creating and
configuring the pages of your site template, just close the browser tab or
window that opened when you clicked *Open site template*. Changes to site
templates are automatically saved as you make them, so you don't need to return
to the Site Templates page of the Control Panel and select *Save*.

![Figure 3.14: You can see the name of the site template you're currently editing](../../images/editing-site-template.png)

<!-- Needs to be replaced for 6.2. -->

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
*Actions* &rarr; *Go to Private Pages* next to one of your new sites. Using site
templates streamlines the site creation process for administrators, making it
easy to create sites quickly. Now each Nose-ster department has its own
calendar, documents and media library, wiki, and message boards application.
Although the pages and portlets of each department's site are the same, each
site will quickly be filled with department-specific information as users add
and share content within the sites. Also, site administrators can add new pages,
portlets, and content to their sites, further differentiating each department's
site from the others.

### Propagating Changes from Site Templates to Sites

It's possible for site template administrators to add, update, or delete site
template pages. Changes made to a site template can be propagated to sites whose
page sets are linked to the site template. Such a link is created when you
create a site based on a site template and leave the *Enable propagation of
changes from the site template* box checked. To disable or re-enable this link
for a site, select the site in the Control Panel's *Sites*. Then click on *Site
Settings* and uncheck or recheck the *Enable propagation of changes from the
site template* checkbox. In this section, we explain the propagation of changes
from site templates to sites and discuss the options available to site
administrators and site template administrators.

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

	This page has been changed since the last update from the site template. No
	further updates from the site template will be applied. Click *Reset* to
	overwrite the changes and receive updates from the site template.

If the site administrator clicks the *Reset* button, changes are propagated from
the site template to all the pages of the site that were imported from the site
template. Clicking the *Reset* button makes two kinds of updates. First, changes
made by site administrators to pages that were imported from the site template
are undone. Second, changes made by site template administrators to site
template pages are applied to the site pages.

Site template administrators can set preferences for portlets on site template
pages. When a portal administrator creates a site from a site template, the
portlet preferences are copied from the site template's portlets, overriding any
default portlet preferences. When merging site template and site changes, e.g.,
when resetting, portlet preferences are copied from site template portlets to
site portlets. Only global portlet preferences or local portlet preferences
which don't refer to IDs are overwritten.

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

## Using Page Templates  

Click on *Page Templates* in the Control Panel to see a list of page templates.
Page templates function similarly to site templates but at the page level. Each
page template provides a pre-configured page to reuse. Within a page template,
it's possible to select a theme, a layout template, to add portlets to the page
and to configure portlet preferences. Both sites and site templates can utilize
page templates for creating new pages.

![Figure 3.15: The Blog page template is already available for use along with the Content Display Page and Wiki page templates.](../../images/server-configuration-page-templates.png)

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

![Figure 3.16: When creating a new site page, you're given options for the page template and page type.](../../images/control-panel-selecting-page-template.png)

To use your template to create a new page, just navigate to a page over which
you have site administrator privileges and select *Add* &rarr; *Page* from the
Dockbar. You'll be able to select a page template and type a name for the new
page. Alternatively, you can use the Site Administration page.
First, make sure you're on your desired site and navigate to *Admin* &rarr; *Site Administration*. Then click on *Site Pages* &rarr; *Add Page*, type a name, select your template from the drop down menu, and click *Add
Page* to finish.

![Figure 3.17: Choosing whether or not to automatically apply page template changes to live pages](../../images/automatic-application-page-template-changes.png)

<!-- Needs to be replaced for 6.2. -->
<!-- At the current time, Site Pages does not have the *Automatically apply
changes done to the page template* checkbox. I'm not sure if this still needs to
be added so I left off on the image for now. If this feature has been removed,
the text needs to be changed to reflect this. -->

Note that by default, when a site administrator creates pages based on a page
template, any future changes to the template are automatically propagated to
those pages. Site administrators can disable this behavior by unchecking the
*Automatically apply changes done to the page template* box.

<!-- | COMMENT FOR AUTHOR: IMHO, the following paragraph does not fit here
because it is of interest in the context of managing a site, not in the context
of managing a site template | -->

If staging has been enabled, changes to the page template are automatically
propagated to the staged page. These changes still need to be approved before
the page is published to live. For this reason, the automatic propagation of
page template changes to the staged page cannot be turned off and the
*Automatically apply changes done to the page template* checkbox does not
appear.

We'll discuss staging in more detail later in this chapter. For now let's look
at importing and exporting templates.

### Exporting and Importing Site Templates and Page Templates  

If you want to export a site that uses site or page templates to a different
environment (through a LAR file or remote publication), the templates must be
exported and imported manually in advance or the import will fail.

To export a Site using a Site Template, use the following process:

1. Go to *Control Panel* &rarr; *Site Templates* and click *Actions* &rarr;
*Manage Pages* for the Site Template your site is using.
2. Click *Export* to obtain a LAR file with the content of the Site Template. Be
sure to choose the applications and data you want exported.
3. In your target environment, go to *Control Panel* &rarr; *Site Templates* and
create a new Site Template.
4. Click *Actions* &rarr; *Manage Pages* for that Site Template and then click
*Import*.
5. Upload the LAR file containing your site template's content.

Now the site can be exported and imported normally to this new environment.

For page templates, the process very similar:

1.  Go to *Control Panel* &rarr; *Page Templates*.
2. Next to the page template you would like to export, click *Actions* &rarr;
*Export*. This produces a LAR file you can import later.
3. On the target environment, go to *Control Panel* &rarr; *Page Templates* and
create a new Page Template.
4.  Next to the new template, click *Actions* &rarr; *Import*.
5.  Upload the LAR file containing the exported page template from step 3.

The page template can now be imported normally to this new environment.

Next, let's examine the tools Liferay provides for handling translations.

## Localization  

Previous versions of Liferay had the ability to create and manage different
translations of your web content but with Liferay 6.2 we've added several
improvements.

When you create a new piece of Web Content, you have the ability to choose a
default language. If you click *Change*, you can select your default language
from a large number of languages Liferay supports. Before you can create a
translation, you must finish creating the content in your default language and
save it. Once you've done that, editing the content provides you with the option
to *Add Translation*.

![Figure 3.18: Adding a translation](../../images/04-web-content-content-translation.png)

<!-- Needs to be replaced for 6.2 -->
<!-- Very few translation options available at present time (6) -->

After you click *Add Translation*, you can select a language by scrolling
through the list or by entering the language you want to use in the search box.
When you select a language, a lightbox opens within your browser window enabling
you to easily compare the original with the new translation. Once you are done
with the translation, click *Save* and the translation is added to the list of
*Available Translations*.

![Figure 3.19: Adding a translation](../../images/04-web-content-content-translation-2.png)

<!-- Needs to be replaced for 6.2 -->
<!-- Unable to compare translations at this time -- window sizing issues -->

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
*Localizable* box checked appear within the translation window. Next, we'll
discuss how to let users customize their site pages.

## Allowing Users to Customize Site Pages  

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
content.

You also want your content to be found easily by your users. This is done
through tags and categories.

Additionally, you may want to create content and send it through an approval and
reviewal process weeks before you want it displayed on the web site. Liferay
gives you this flexibility with the *Schedule* and *Workflow* features.

## Scheduling Web Content  

Liferay's WCM lets you define when your content goes live. You can determine
when the content is displayed, expired and/or reviewed. This is an excellent way
to keep your site current and free from outdated (and perhaps incorrect)
information. The scheduler is built right into the form your users access to add
web content. Specifically, it can be found in the right panel listed with
several other configurable settings.

![Figure 3.20: The web content scheduler can be easily accessed from the right panel of the page.](../../images/04-web-content-schedule.png)

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

## Staging Page Publication  

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

Liferay 6.1 added a feature to staging called Page Versioning. This feature
works with both Local Live and Remote Live staging and allows site
administrators to create multiple variations of staged pages. This allows
several different versions of sites and pages to be developed at the same time.
Variations can be created, merged, and published using a Git-like versioning
system. Let's jump in to see how to use staging.

### Enabling Local Live Staging

Site administrators can enable Staging for a site via the Site Settings UI. To
reach this interface, navigate to the Site Administration page of your site,
click on *Site Settings* in the left menu, then click on *Staging* listed under
the Advanced tab. Under Staging Type, select either *Local Live* or *Remote
Live* and additional options appear. Staging allows changes to be made in a
staging environment so that work can be reviewed, possibly using a workflow,
before it's published to a live site. Enabling Local Live staging is easy. Just
select *Local Live* and decide whether you'd like to enable page versioning. You
can enable page versioning on a site's public pages, private pages, both, or
neither. Page versioning allows you to work in parallel on different versions of
pages and maintains a history of all page modifications. We discuss page
versioning in more detail below.

### Enabling Remote Live Staging

When you enable Remote Live staging, the remote site becomes the live
environment and the current site becomes the staging environment. The remote
(live) Liferay server and the local (staging) Liferay server should be
completely separate systems. They should not, for example, share the same the
database. When Remote Live staging is enabled, all the necessary information is
transferred over the network connecting the two servers. Before a site
administrator can enable Remote Live staging for a site, the remote Liferay
server must first be added to the current Liferay server's list of allowed
servers. The current Liferay server must also be added to the remote Liferay
server's list of allowed servers. You can make these configurations in your
Liferay servers' `portal-ext.properties` files. Your first step should be to add
the following lines to your current Liferay server's `portal-ext.properties`
file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Remote server IP address]
    axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP,192.168.0.16,[Remote server IP address]

Then add the following lines to your remote Liferay server's
`portal-ext.properties` file:

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Local server IP address]
    axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP,192.168.0.16,[Local server IP address]

Remember to restart both Liferay servers after making these portal properties
updates. After restarting, log back in to your local Liferay portal instance as
a site administrator. Then navigate to the Site Administration page for your
site. Then click on *Site Settings* in the left menu and then on *Staging*
listed under the Advanced tab. Select *Remote Live* under Staging Type and
additional options appear.

![Figure 3.21: After your remote Liferay server and local Liferay server have been configured to communicate with each other, you have to specify a few Remote Live connection settings.](../../images/remote-live-staging-settings.png)

<!-- Needs to be replaced for 6.2 -->
<!-- Skipping Staging images at present time. Still a lot of development work to
be merged into trunk for this topic. -->

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
Finally, check the *Use a Secure Network Connection* field to secure the
publication of pages from your local (staging) Liferay server to your remote
(live) Liferay server.

That's all you need to do to enable Remote Live Staging! However, when a user
attempts to publish changes from the local (staging) server to the remote (live)
server, Liferay passes the user's credentials to the remote server to perform a
permission check. In order for a publishing operation to succeed, the operation
must be performed by a user that has identical credentials and permissions on
both the local (staging) and the remote (live) server. This is true regardless
of whether the user attempts to publish the changes immediately or attempts to
schedule the publication for later. If only a few users should have permission
to publish changes from staging to production, it's easy enough to create a few
user accounts on the remote server that match a selected few on the local
server. However, the more user accounts that you have to create, the more
tedious this job becomes and the more likely you are to make a mistake. And you
not only have to create identical user accounts, you also have to ensure that
these users have identical permissions. For this reason, we recommend that you
use LDAP to copy selected user accounts from your local (staging) Liferay server
to your remote (live) Liferay server. Liferay's Virtual LDAP Server application
(EE-only), available on Liferay Marketplace, makes this easy.

### Example: Enabling Local Live Staging

Let's create a Local Live staging environment for Nose-ster's home page. Before
we begin, let's add a new page. Click *Add &rarr; Page* from the Dockbar in the
default site and name the new page *News and Events*. Next, click *News and
Events* to view the page. Then add the Alerts and Announcements portlets to the
News and Events page.

When you activate staging Local Live staging, Liferay creates a clone of your
site. This clone became the staging environment. Because of this, we recommend
only activating staging on new, clean sites. Having a few pages and some
portlets (like those of the example site we've created) is no big deal. However,
if you have already created a large amount of content you might not be able to
enable staging on that site. Also, if you intend to use page versioning to track
the history of updates to your site, we recommend that you enable it as early as
possible, *before* your site has many pages and lots of content. Your site's
update history won't be saved until you enable page versioning. Page versioning
requires staging (either Local Live or Remote Live) to be enabled.

Now we're ready to activate staging for this site. Go to *Admin* &rarr; *Site
Administration* &rarr; *Site Settings* and select *Staging* from under the
*Advanced* heading. We'll assume we don't have a separate staging server so
we'll select the *Local Live* staging type. If you do have a separate server to
use for staging, follow the instructions in the previous section for configuring
it and your local server for remote staging. Either way, once you make a
selection (either *Local Live* or *Remote Live*), more options become available
for page versioning and staged portlets.

### Enabling Page Versioning and Staged Portlets

Enabling page versioning for a site allows site administrators to work in
parallel on multiple versions of the site's pages. Page versioning also
maintains a history of all updates to the site from the time page versioning was
enabled. Site administrators can revert to a previous version of the site at any
time. This flexibility is very important in cases where a mistake is found and
it's important to quickly publish a fix. If you're following the Nose-ster
example, check *Enabled On Public Pages* to enable page versioning for the
Nose-ster site and then click *Save*.

![Figure 3.22: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

<!-- Needs to be replaced for 6.2 -->

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

### Using the Staging Environment  

After enabling staging (either Local Live or Remote Live) for a site, you'll
notice a colored bar with some new menus just below the Dockbar when you
navigate to the site. These new menus help us manage staged pages. You'll also
notice that most of your page management options have been removed, because now
you can't directly edit live pages. You now must use the staging environment to
make changes. Click on *Staging* to view the staged area. Your management
options are restored and you can access some new options related to staging. If
you're following along with the Nose-ster example, navigate back to the News and
Events page and click on *Staging* to get your page editing capabilities back.

![Figure 3.23: You can see the new bar staging adds to the top of your screen.](../../images/04-web-content-staging-live-page.png)

<!-- Needs to be replaced for 6.2 -->

Add the Calendar portlet and then click on *Live* from the Dockbar. Notice that
the Calendar portlet isn't there. That's because you've staged a change to the
page but haven't published that change yet to the live site. Go back to the
staged page and look at the options you have available. From here you can *Undo*
changes, view a *History* of changes, *Mark as Ready for Publication*, and
*Manage Page Variations*.

**Undo/Redo:** allows you to step back/forward through recent changes to a page,
which can save you the time of manually adding or removing portlets if you make
a mistake.

**History:** shows you the list of revisions of the page, based on publication
dates. You can go to any change in the revision history and see how the pages
looked at that point.

**Manage Page Variations:** allows you to work in parallel on multiple versions
of a staged page. We will explain this later.

After you're done making changes to the staged page, click the *Mark as Ready
for Publication* button. The status of the page changes from *Draft* to *Ready
for Publication* and any changes you've made can be published to the Live Site.
When you publish a page to live, only the version which was *Marked as Ready for
Publication* is published.

The dropdown next to the Staging link at the top gives you the option to
*Publish to Live Now* or *Schedule Publication to Live*.

**Publish to Live Now:** immediately pushes any changes to the Live Site.

**Schedule Publication to Live:** lets you set a specific date to publish or to
set up recurring publishing. You could use this, for example, to publish all
changes made during the week every Monday morning without any further
intervention.

Click on *Mark as Ready for Publication* and then *Publish to Live Now* to
publish your Calendar portlet to the live site.

Content publication can be also controlled using staging. Calendar events are
staged by default (this can be changed in Staging Configuration). If you create
an event in the staged site, it isn't visible in the live site until you publish
it to the live site following the same steps you just performed (you can select
which types of content are published when you publish to the live site). If
workflow is enabled for Calendar Events, the event needs to go through the
workflow process before it can be published to the live site.

![Figure 3.24: Ready to publish to the live site.](../../images/04-web-content-staging-publish.png)

<!-- Needs to be replaced for 6.2 -->

Web content tends to be frequently updated, often more so than other kinds of
content. For some web content articles, this can result in very high numbers of
versions, sometimes hundreds. Such high version numbers can make it very slow to
publish web content articles. Liferay 6.2 addresses this issue by allowing site
administrators to choose whether or not to publish a web content article's
version history when a staged article is ready to be published. To use this
feature, staging must be enabled. Edit a web content article that's being
displayed in a Web Content Display portlet on one of your pages and then click
*Staging* &rarr; *Publish to Live Now* from just below the Dockbar. In the
popup, expand the Applications heading and scroll down until you see the section
for Web Content.

![Figure 3.25: Uncheck the version history box to only publish the latest approved version of web content articles that have multiple versions.](../../images/web-content-version-history-box.png)

<!-- Needs to be replaced for 6.2 -->

Liferay 6.2 also added a portal property,
`journal.publish.version.history.by.default`, that sets the default behavior. By
default, this property is set to `true` so site administrators have to manually
manually uncheck the *Version History* box mentioned above if they only want to
publish the latest approved version of web content articles. If you'd like to
change the default behavior, add the following line to your
`portal-ext.properties` file:
`journal.publish.version.history.by.default=false`.

One of the most powerful features of staging is page variations. Let's see how
to use them to create multiple different variations of your site's pages for
different purposes.

### Using Site Pages Variations  

Let's say you're working on a product-oriented site where you'll have several
major changes to a page or a set of pages over a short period of time. Also you
need to be working on multiple versions of the site at the same time to ensure
everything has been properly reviewed before it goes live. With staging in
Liferay 6.1 you can do this using **Page Variations**.

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

By default, we only have one site page variation which is called **Main
Variation**. To create a new one, use the dropdown next to the *Staging* link
and click on *Manage Site Pages Variations*. This brings you to a list of the
existing site page variations for your site. Click *Add Site Pages Variation* to
create a new one. From the *Add Site Pages Variation* screen, you can set a
Name, Description and also set your new variation to copy the content from an
existing variation. There are several options to choose in this selector.

**Any existing Site Pages Variation:** creates a new site page variation that
contains only the last version of all the pages that exist in this variation.
The current variation must be marked as ready for publication.

**All Site Pages Variation:** creates a new variation that contains the last
version marked as ready for publication from any single page existing in any
other variation.

**None:** creates a new, empty variation.

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
Administration* &rarr; *Site Pages* screen. Here all the existing pages for all
the variations are shown in a tree. Pages not enabled for the current variation
are shown in a lighter color.

To publish a variation to the live site, click on *Publish to Live now* in the
dropdown next to the variation name. Publications can also be scheduled
independently for different variations. For example, you could have a variation
called *Mondays* which is published to the live site every Monday and another
one called *Day 1* which is published to the live site every first day of each
month.

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

![Figure 3.26: This is an example of a Thanksgiving Page Variation.](../../images/04-web-content-branch-thanksgiving.png)

<!-- Needs to be replaced for 6.2 -->

Another powerful feature is the possibility of *merging* Site Pages Variations.
To merge two Site Pages Variations, you need to go to the Manage Site Variations
screen. From there, click on *Merge* on the Site Pages Variation you want to use
as the base. You will be asked to choose the Site Pages Variation to merge on
top of it. Merging works in the following way:

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

### Wrapping up Staging  

You can enable staging on an individual site basis, depending on your needs.
This makes it easy to put strict controls in place for your public web site,
while opening things up for individual sites that don't need such strict
controls. Liferay's staging environment is extremely easy to use and makes
maintaining a content-rich web site a snap.

Liferay 6.0 introduced a new feature to the permissions system called teams.
Let's examine teams next.

## Creating Teams for Advanced Site Membership Management  

Teams don't appear as a link in the Control Panel because they exist *within*
sites. Teams allow site administrators a greater degree of flexibility than was
possible using just user groups and roles. They allow site administrators to
create various sets of users and permissions for site-specific functions. Teams
are the preferred method for collecting permissions within a single site.

If you create a team for one site, the permissions defined for it are not
available to any other sites. In contrast, if you assigned a custom role to a
user group, the role would be available portal-wide even though the specific
permissions defined by it would only apply within the scope of a designated
site. Furthermore, team members, unlike user group members, are guaranteed to be
members of the desired site.

To create a team within a site, first navigate to the *Control Panel &rarr;
Sites* page and then select *Actions &rarr; Manage Memberships* for the site
within which you want to create a team. Finally, click *View Teams* and
click the *Add Team* button.

![Figure 3.27: Creating teams within your site helps your users demonstrate teamwork and collaboration.](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description,
click *Save*. Your new team will appear in the list. To add members, simply
click on *Actions &rarr; Assign Members*.

Permission management for teams is handled at the individual portlet level,
using the *Options &rarr; Configuration &rarr; Permissions* tab of the portlet
itself. Remember the portlet options link is the wrench symbol at the top of a
portlet. This enables users who wouldn't have access to all of the necessary
options in the Control Panel to manage permissions through teams.

To give a team access to a particular portlet function, access the *Permissions*
tab of a portlet residing on a page, check the boxes corresponding to
permissions you want to assign to the teams, then click *Save*. That's it! Now
your team is ready to perform their functions. Next, let's look at how to
configure Liferay for mobile devices.

## Displaying Site Pages to Mobile Devices  

Mobile device rules allow you to configure sets of rules to alter the behavior
of the portal based on the device being used to access Liferay. The proportion
of mobile device users browsing the web has been steadily increasing, so it's
important to be able to handle different kinds of devices appropriately. For
instance, you can configure the look and feel of Liferay pages accessed by
smartphone or tablet users differently from those accessed by PC users.

Both sites and individual pages can be configured with any number of rule
groups. A rule group is designed to describe a group of devices; think of a rule
group as a mobile device family. It can contain one or more rules that describe
a category of devices, such as all Android devices or all iOS tablets. You can
define as many rules in a rule group as you need to classify all the devices for
which you'd like to define actions. Rule groups can be prioritized to determine
which one applies to a given page request. 

In order to configure mobile device rules, you need a way to find out the
characteristics of the device. While some of the characteristics are provided by
the device, most are not. For this reason, there are databases that contain
information about thousands of devices. These databases make it possible to
learn every detail about a device from the device type, which is included in
each request sent to the portal. Liferay's Mobile Device Rules can connect to
device databases so that you can use their device characteristics in your rules. 

Among the plugins available on Liferay Marketplace, you can find the Device
Recognition Provider plugin. This plugin provides out of the box integration
with WURFL, an open source database licensed with the AGPLv3 license. Commercial
licenses are also available. It's also possible to develop plugins that
integrate with other device databases. Even if you don't have a device database,
you can still set up mobile device rules. They won't, however, be effective
until a database is deployed, because the portal won't have enough information
about the devices being used to make page requests.

<!-- Marketplace info below needs to be updated when Marketplace is available
for 6.2 testing. -->

To configure mobile device rules, you must install the Device Recognition
Provider plugin. This plugin uses the WURFL database to enable Liferay to detect
which mobile device or operating system is being used for any given request. To
install the plugin, navigate to the Store section of the Control Panel, located
under the Marketplace heading. Click on the *Utility* section and then on *See
All*. Search for the appropriate Device Recognition Provider plugin (CE or EE)
and click on it. Finally, click on *Free* to acquire the plugin. Once you've
acquired the plugin, you need to download and install it. To do so, navigate to
the Purchased section of the Control Panel, find your Device Recognition
Provider plugin, and click on *Download* and then *Install*.

**Installation Note:** If your server doesn't have access to the outside
Internet, an error appears in your log: `SLF4J: Failed to load class
"org.slf4j.impl.StaticLoggerBinder`. This occurs because WURFL by default
downloads device information from the web. You can provide the same information
to WURFL manually by downloading the SLF4J distribution from
[http://www.slf4j.org/download.html](http://www.slf4j.org/download.html),
unzipping the resulting file, copying `slf4j-log4j12.jar` to
`[WEB_APP_HOME]/wurfl-web/WEB-INF/lib` folder, and restarting your Liferay
instance. On some application servers, you'll need to add this .jar file to the
`wurfl-web.war` file first (in the directory noted above) before deploying the
file to your server. 

You can access the Mobile Device Rules administrative page from the Content
section of the Control Panel. Select the appropriate scope using the context
menu selector so your rule groups are available where you expect them to be. The
Mobile Device Rules administrative page displays a list of defined rule groups
and lets you add more. To add rules to a rule group, select *Actions*
&rarr; *Manage Rules*, or click on a rule group to edit it, and then click
the *Manage Rules* link.

![Figure 3.28: You can manage device rules from the Mobile Device Rules administrative page.](../../images/mobile-device-rules.png)

The rules defined for a rule group, along with the priorities of the rule groups
selected for a particular site or page, determine which rule group's actions are
applied to a given request. From the Manage Rules page for a specific rule set,
you can add a rule by specifying a rule type. Remember that you can add as many
rules to a rule group as you need in order to classify the devices on which
you'd like to take actions. Note that, by default, only the Simple Rule type is
available.  The rules are designed, however, to be extensible, and additional
rule types can be added by your developers. Once added, you can edit the rule to
specify a device type and operating system.

![Figure 3.29: You need to install the Device Recognition Provider plugin to populate the OS list.](../../images/mobile-device-editing-rule.png)

<!-- Needs to be updated for 6.2 -->
<!-- Unable to deploy Device Recognition plugin at time of image updating. -->

Once you've created some mobile device rule groups and added some rules to them,
you'll be ready to set up some actions. The actions defined for a rule group
determine what happens to a particular request when the device is detected
and the rule group has been found to apply.

You can add actions to a rule group from the Site Pages page of Site
Administration. Select either the public or private pages and then look for the
*Mobile Rule Groups* link in the right-hand menu. Use the *Select Rule Group*
button to select rule groups to be applied either to a site or to a single page.
If you select the page group itself from the left-hand menu, the selected rule
group applies to all the pages of the site by default. If, however, you select
an individual page and then click the *Select Rule Group* button, the rule
groups apply only to that page. You can select multiple rule groups for a
particular site or page and order them by priority. The rule groups are checked
in decreasing order of priority: the actions defined by the first rule group
that applies are executed.

![Figure 3.30: You can select a mobile device rule group to apply for a site or page from the Site Pages section of the Control Panel.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage
Actions* button and then click *Add Action*. By default, there are four kinds of
actions that can be configured for mobile rule groups: layout template
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

1. Create a mobile device rule group to represent the family of devices for
   which to define an action or actions.

2. Define one or more rules for your rule group that describe the family of
   devices represented by your rule group.

3. Apply your rule group to an entire page set of a site (all the public pages
   of a site or all the private pages) or to a single page.

4. Define one or more actions for your rule group that describe how requests
   should be handled.

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
Administration, and click on *Mobile Device Rules*. Click on *Add Rule Group*,
enter the name *Android and Bada Mobile Phones*, and click *Save*. You'll see
the message, *No rules are configured for this rule group*.

Click the *Manage Rules* link and we'll configure our rule group to apply only
to mobile phones running Android or Bada. Click *Add Rule*, enter *Rule 1* for
the name and select *Simple Rule* for the type, then click *Save*. Then click on
the rule to edit it or click *Actions* &rarr; *Edit*. Under OS, select *Android*
and *Bada OS* (hold down Control to make multiple selections), select *False*
under Tablet since we want our rule group to apply only to mobile phones, and
click *Save*. Now we just need to define the redirect action for our rule group.
Navigate back to *Site Administration* &rarr; *Site Pages* and click on *Mobile
Device Rules* in the navigation menu to the right.

![Figure 3.31: To apply a mobile device rule group to a page set of a site, click on *Mobile Device Rules*, click *Select Rule Group*, and select the desired rule group.](../../images/site-pages-mobile-device-rules.png)

Click *Select Rule Group* and then click the *Android and Bada Mobile Phones*
rule group that you configured. Once you've selected your rule group, click 
*Mobile Rule Groups* again and click either on your rule group or *Actions*
&rarr; *Manage Actions* next to it. Then click *Add Action*, enter the name
*Android/Bada Liferay Redirect*, and select *Site Redirect* under Type.  Under
the Site dropdown menu that appears, select *Android/Bada Liferay* and under the
Page dropdown menu that appears, select the *Welcome* page that you created
earlier. Lastly, click *Save*. That's it! Now Android and Bada mobile phone
users are redirected to the Android/Bada Liferay site from the Liferay site. 

Let's look at one more example of using mobile device rules before we move on.
Suppose you'd like to create another rule so that when a site is accessed by an
Android or iOS tablet, a different layout is used. To set this up, we need to
follow the same four steps described above. First, make sure you're on the
Liferay site by checking in the My Sites tab of the Dockbar. Then navigate to
the Mobile Device Rules page of Site Administration. Add a new rule group called
*Android and iOS Tablets*. Add a simple rule called *Rule 1* to this rule group.
As with the previous example, we only need one rule to describe our device
family. Edit *Rule 1* and select *Android and iPhone OS* under the OS heading
and *True* under the Tablet heading, then click *Save*.

Next, click on *Site Pages* in Site Administration, select *Mobile Device
Rules*, and select the *Android and iOS Tablets* rule group. Notice that you've
now selected two rule groups for the Liferay site's public pages and they've
been assigned priorities. If a device making a request belongs to both of the
device families represented by the rule groups, the priority of the rule groups
determines which rule group's actions are executed. Note that in our example,
the first rule group contains only mobile phones and the second rule group
contains only tablets, so no devices can belong to both rule groups. Now we just
need to define an action for our Android and iOS Tablets rule group to use a
different layout: On the Site Pages page of Site Administration, click on
*Mobile Device rules*, and then on *Actions* &rarr; *Manage Actions* next to
Android and iOS Tablets. Click on *Add Action*, enter the name *Layout Template
Modification*, and select the *Layout Template Modification* action type.
Lastly, select the *1 Column* layout template (or whichever one you like) and
click *Save*. Good job! Now the Liferay site's pages are presented to Android
and iOS tablet users with the 1 Column layout template.

## Summary  

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
