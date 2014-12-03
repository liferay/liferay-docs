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
can add specialized, Liferay-specific application fields such as Image Uploader
and Documents and Media right onto the structure. Furthermore, you can move the
elements around by dragging them where you want them. This makes it easy for you
to prototype different orders for your input fields. Additionally, elements can
be grouped together into blocks which can then be repeatable. Template writers
can then write a template which loops through these blocks and presents your
content in innovative ways, such as in sliding navigation bars, content which
scrolls with the user and more.

Let's look at how we can create and edit structures through the Manage
Structures interface.

### Editing a Structure [](id=editing-a-structure)

Go back to the Control Panel and select *Web Content* from the content section.
The first way to access the Manage Structures interface is simply by clicking
*Manage* &rarr; *Structures*. This opens a popup showing all the web content
structures that exist in your currently selected scope. Here, you can add new
web content structures, edit existing ones, manage the templates associated with
a structure, edit the permissions of a structure, and copy or delete structures.
Copying web content structures can be useful if you'd like to create a new web
content structure that's similar to an existing one, but you don't want to start
from scratch. Liferay generates a unique portal ID for the copied structure, but
every other attribute of the copied structure, including the name, is the same
as that of the original. Once you've copied a web content structure, you should
enter a new name for it to avoid confusing it with the original. When you copy a
web content structure, you'll be prompted to choose whether to copy any detail
templates or list templates associated with the structure. For information on
detail templates and list templates, please refer to chapter 9 on Dynamic Data
Lists.

![Figure 3.1: You can access the Manage Structures interface by clicking
*Manage* &rarr; *Structures* from the Web Content page of the Control Panel.](../../images/manage-structures.png)

The second way to access the Manage Structures interface is directly from the
web content article WYSIWYG editor. Click *Add* &rarr; *Basic Web Content* from
the Web Content page to add another piece of content to your portal. Instead of
going right for the content, this time we'll first create a structure. To access
the Manage Structures interface, simply click on *Select* next to the
*Structure* heading near the top of the page. To create a new structure in your
chosen scope, simply click on the *Add* button in the Manage Structures popup.

It's very easy to create and edit structures: all you have to do is drag
elements into the structure and then give them names. For instance, select the
*Checkbox* element under the *Form Controls* tab and drag it onto the structure.
You can do the same with any of the elements. To remove it from the structure,
simply select the *Delete* icon (black circle with X) in the upper right corner
of the element. Take a moment to add, delete and rearrange different elements.

![Figure 3.2: Structure Elements](../../images/04-web-content-structure-editor.png)

Liferay supports the following elements in structures:

**FORM FIELDS**

**Text Field:** Used for items such a titles and headings.

**Text Box:** Used for the body of your content or long descriptions.

**Text Area (HTML):** An area that uses a WYSIWYG editor to enhance the content.

**Checkbox:** Allows you to add a checkbox onto your structure. Template
developers can use this as a display rule.

**Selection List:** Allows you to add a select box onto your structure.

**Multi-selection List:** Allows you to add a multi-selection list onto your
structure.

**APPLICATION FIELDS**

**Image Uploader:** Allows you to add the upload image application into your
structure.

**Documents and Media:** Allows you to add the Documents and Media folder
hierarchy to your structure.

**MISCELLANEOUS**

**Link to Page:** Inserts a link to another page in the same site.

**Selection Break:** Inserts a break in the content.

These form elements provide all you need to model any information type you would
want to use as web content. Liferay customers have used structures to model
everything from articles, to video metadata, to databases of wildlife. You're
limited only by your imagination. To fire that imagination, let's look more
closely at the form elements.

### Editing Form Elements [](id=editing-form-elements)

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
element *Text Area (HTML)* which has the Field Label *Instructions*. If we
wanted to give it the variable name `Steps`, we can do it very easily: at the
bottom of every form element is a **Variable Name** field. Replace the generated
name with the name you want to use. Now your template writer has a variable by
which he or she can refer to this field.

Below each field is a button labeled *Edit Options*. This contains several other
ways to configure your fields:

**Field Type:** changes the field type, in case you dragged the wrong field type
to this location in the form. **Field Label:** changes the displayed label for
the field.

**Index Type:** Choose how you want Liferay to index your field for search. You
can have it indexed by keyword, which filters out common words such as *and*,
*but*, *the*, and so on, or you can have it index the full text of the field. By
default, indexing is turned off.

**Predefined Value:** Specifying predefined values for structure forms is a way
to specify defaults. When a user creates a new web content article based on a
structure that has predefined values for various fields, the predefined values
appear in the form as defaults for those fields.

**Instructions for the User:** Check this box and type a description of what the
field is for to display it as a tooltip for the user. 

**Repeatable:** If you want this field to be a repeatable element, check this
box. Your users can then add as many copies of this field as they like. For
example, if you're creating a structure for articles, you might want a
repeatable Author field in case you have multiple authors for a particular
article.

**Required:** Check the box to mark the field required. If a field is required,
users must enter a value for it in order to submit content using this structure. 

For the Nose-ster structure, type something in the *Instructions for the User*
field that helps users know what to put into the Body element (example: *this is
an HTML Text area for the body of your content*). Also enable the *Display as
Tooltip* box. Now, when users hover over the Help icon near your title, your
instructions are displayed.

#### Structure Default Values [](id=structure-default-values)

Structure Default Values allow you to create one structure that uses common data
from multiple articles.

Returning to our newspaper scenario again, let's say you want all sports
articles to have the same display page (sports page), the same categories, or the
same set of tags. Instead of adding them for each article or wondering if your
users are adding them to every web content, you can add these characteristics once
for every sports article by creating default values for the structure. There are
two ways to edit structure default values: creating a new structure or editing
an existing structure.

For a new structure, you must first create the structure before editing its
default values. Navigate to *Web Content* in the Control Panel and click the
*Structures* tab, then select the *Add Structure* button. Under the *XML Schema
Definition* section of the new structure form, use the *Add Row* button to
create different types of fields for the structure. Or you can use the editor to
create the structure manually: the Launch Editor button allows you to edit the
XML for the structure if you wish to do it via code. When you are done, click
*Save and Continue* to go to the Structure Default Values form.

![Figure 3.3: You can create fields for structure default values via the XML
Schema Definition section of the new structure
form.](../../images/xml-schema-definitions.png)

To edit an existing structure, go to *Web Content* in the Control Panel and
click the *Structures* tab to see the structures list. Find the *Actions* button
for the desired structure and select *Edit Default Values* from the menu to view
a window like the one below. This form allows you to manage the structure
settings.

![Figure 3.4: You can edit default values via the Actions button of the structure  form.](../../images/structure-default-values-sports.png)

Every new web content you create with this structure is preloaded with the
data you inserted.

### Assigning Permissions [](id=assigning-permissions)

Setting permissions on structures is done using the same procedure as
permissions everywhere else in Liferay. Most users should not have the ability
to edit structures. Structures are coupled with templates, which require some
web development knowledge to create. This is why only trusted developers should
be able to create structures and templates. Users, of course, should be able to
view structures. The View permission enables them to make use of the structures
to create content.

![Figure 3.5: View Permission for a Structure](../../images/04-web-content-structure-permissions.png)

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
enter content into a structure, it *must* have a matching template. Without a
template, the portal has no idea how to display content which has been created
using a custom structure.

Let's look more closely at the types of templates Liferay supports.

### Template Types (VM, XSL, FTL and CSS) [](id=template-types-vm-xsl-ftl-and-css)

Liferay supports templates written in four different templating languages, to
support the skill sets of the largest number of developers. This increases the
chances you can jump right in and use whichever one you've already used before.
If you haven't yet been exposed to any of them, your best bet is Velocity or
Freemarker, as they are less "chatty" than XSL and extremely simple to
understand.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix
logic with HTML. This is similar to other scripting languages, such as PHP,
though Velocity is much simpler. Because it's been in the product the longest,
it is probably the most widely used language for templates in Liferay WCM. If
you haven't used any of the template languages before, we recommend using
Velocity: you'll get up to speed the fastest.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to
transform the underlying XML of a structure into markup suitable for the
browser. While it may not be as clean and compact as Velocity or FTL, it's
widely used for transforming XML into other formats and it's very likely your
developers have already been exposed to it.

**FTL** (FreeMarker Template Language): Freemarker is a templating language
which could be considered a successor to Velocity, though it is not yet as
popular. It has some advantages over Velocity for which it sacrifices some
simplicity, yet it is still easy to use.

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very
straightforward and modifications are simple (colors, fonts, layouts, etc.). If
your structure is more complex, however, you'll need to use one of the other
options.

### Adding Templates [](id=adding-templates)

Liferay WCM makes it easy to create structures, templates and content from the
same interface. Let's go through the entire flow of how you'd create a
structure, link it to a template and then create content using them both. We'll
use Velocity for our template and we'll lay out the structure fields
systematically to go along with the format we've defined for our content.

![Figure 3.6: Adding Template Interface](../../images/04-web-content-templates-create.png)

1. Go back to the Web Content section of the Control Panel and click *Add*
   &rarr; *Basic Web Content*.
2. Click *Select* next to the Structures heading to access the Manage Structures
   interface.
3. Click on the *Add* button.
4. Add the following fields:

	| Field Type | &nbsp;Variable Name |
	--------- | ---------- |
	Text  | &nbsp;`title` |
	Text Box | &nbsp;`abstract` |
	Documents and Media | &nbsp;`image` |
	Text Area | &nbsp;`body` |

5.  Name the structure *News Article* and click *Save*.
6.  Back on the Manage Structures interface, click *Actions* &rarr; *Manage
    Templates* next to the News Article structure that you created.
7.  Click *Add*.
8.  Enter the name *News Article* and add a description.
9.  Uncheck the *Cacheable* checkbox.
10.  Make sure Velocity is selected as the script language (it's the default).
11. If you've written the script beforehand, you can select *Browse* to upload
    it from your machine. Otherwise, you can click *Launch Editor* to type the
    script directly into the small editor window that appears.
12. Click *Save*.
13. Click *Back*.
14. Select the News Article structure.
15. On the New Web Content form, you'll see the Title, Abstract, Image, and Body
    fields that you defined for the News Article structure. The News Article
    template should also be selected.
16. Populate the fields and click *Publish* to publish your News Article.

Below is the template script for this structure. It is written in Velocity:

    #set ($renderUrlMax = $request.get("render-url-maximized"))
    #set ($namespace = $request.get("portlet-namespace"))
    #set($readmore = $request.get("parameters").get("read_more"))
	<h1>$title.getData()</h1>
    #if ($readmore)
	<p>$abstract.getData()</p>
	<p>$body.getData()</p>
    #else
	<p>
	<img src="${image.getData()}" border="0" align="right">
	$abstract.getData()</p>
	<a href="${renderUrlMax}&${namespace}read_more=true">Read More</a>
    #end

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

![Figure 3.7: Initial View](../../images/04-web-content-adv-example1.png)

![Figure 3.8: After Clicking "Read More"](../../images/04-web-content-adv-example2.png)

Now that you've created a handsome template, it's time to decide who the lucky
people are that get to use it.

## Assigning Template Permissions [](id=assigning-template-permissions)

Permissions for templates are similar to permissions for structures. As with
structures, you only want specific developers editing and creating templates.
You may, however, want to make the templates viewable to some content creators
who understand the template scripting language but are not directly writing the
scripts. You can determine who views the template by selecting from the
*Viewable By* select box beneath the *Permissions* tab. By default the *Anyone
(Guest Role)* is selected.

You'll also want to determine how users can interact with the template. You can
do this by selecting the *More* link.

From the *More* link, you can grant or deny permissions based on Roles. For
instance, you may create a role with the ability to update the template and
create a second role that can both update and delete. Liferay Portal makes it
possible to assign permissions based on the roles and responsibilities within
your organization.

Now that you understand the role structures and templates play in creating web
content, let's look at how you can use Liferay to manage multiple sites.
