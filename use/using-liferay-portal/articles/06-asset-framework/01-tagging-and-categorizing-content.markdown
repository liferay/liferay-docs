# Tagging and Categorizing Content [](id=tagging-and-categorizing-content)

Tags and categories are two important tools you can use to help organize
information on your portal. These tools help users to easily find the content
they're looking for through search or navigation. Tagging and categorizing
assets is easy. You can tag or categorize an asset at creation time or when
editing an existing asset. If you click on the *Categorization* section of the
form for creating or editing an asset, you'll find an interface for adding tags
and categories. If no categories are available to be added to the asset (e.g.,
if no categories have been created), the Categories heading won't appear.

![Figure 6.1: Here, the Web Content Display portlet's form for categorizing a new web content instance doesn't include a Categories heading since no categories have been created.](../../images/04-web-content-categorization.png)

The Control Panel contains an interface for managing tags and categories for
each site in the portal. This interface can be used to manage all your tags and
categories in one place. It is important that you both tag and categorize your
content when you enter it. Let's take a closer look at tags and categories.

## Tags 

Tags are an important tool that can help organize information on your portal and
make it easier for users to find the content that they're interested in. Tags
are words or phrases that you can attach to any content on the website. Tagging
content makes your search results more accurate and enables you to use tools
like the Asset Publisher to display content in an organized fashion on a web
page. There are two ways to create tags: you can do it through the
administrative console in the Control Panel or on the fly as content is created.
By default, tags can be created by regular users and users can apply them to any
assets which they have permission to create or edit.

While regular users can, by default, create new tags by applying them to any
assets that they have permission to create or edit, only site administrators can
access the *Tags* portlet in the Content section of the Site Administration area
of the Control Panel. Here, site administrators can create new tags and edit any
existing site tags. To create tags in the Control Panel, visit the site for 
which you want to create tags and then click on *Admin* &rarr; *Content*. Then 
click on *Tags* in the Content section on the left. From this screen, you can 
view existing tags and create new ones. To create a new tag, click *Add Tag* and
enter a name for the tag.

You can also customize a tag's permissions and properties. This configuration,
however, must be done by a server administrator. To implement this
configuration, add a `portal-ext.properties` file to your Liferay Home directory
with the following contents and then restart the server:

	asset.tag.permissions.enabled=true
	asset.tag.properties.enabled=true

Once this is done, you can change the permissions on a tag to make it viewable by 
guests, site members, or owner. You can also assign other permissions for 
managing tags, including permission to delete the tag, edit the tag, or edit the 
tag's permissions. You can also add properties to a tag. Properties are a way to 
add information to specific tags. You can think of tag properties as tags for 
your tags. Structurally, tag properties are key-value pairs associated with 
specific tags that provide information about the tags.

![Figure 6.2: The Add Tag interface with editing of tag properties and permissions enabled. When managing a site's content, click on *Tags* and then *Add Tag* to create a new tag. The Add Tag interface allows you to enter a name for the tag, define permissions for the tag, and add properties to the tag.](../../images/05-add-tag.png)

Tags are not the only portal-wide mechanism for describing content: you can also
use categories.

## Categories 

Categories are similar in concept to tags, but are designed for use by
administrators, not regular users. Hierarchies of categories can be created, and
categories can be grouped together in *vocabularies*. While tags represent an ad
hoc method for users to group content together, categories exist to allow
administrators to organize content in a more official, hierarchical structure.
You can think of tags like the index of a book and categories like its table of
contents. Both serve the same purpose: to help users find the information they
seek.

Adding vocabularies and categories is similar to adding tags. Visit the site for
which you want to create categories and then click on *Admin* &rarr; *Content*.
Then click on *Categories* in the Content section on the left to view the
categories administration portlet.

![Figure 6.3: When managing a site's content, click on *Categories* and then on *Add Vocabulary* to create a new vocabulary. By default, a vocabulary called *Topic* already exists. When adding new categories, make sure you're adding them to the correct vocabulary.](../../images/add-category.png)

Clicking on a vocabulary on the left displays any categories that have been
created under that vocabulary. To create a new vocabulary, click on the *Add
Vocabulary* button. Enter a name and, optionally, a description. By default, the
*Allow Multiple Categories* box is checked. This allows multiple categories from
the vocabulary to be applied to an asset. If the box is unchecked, only one
category from the vocabulary can be applied to add asset. The *Associated Asset
Types* lets you choose which asset types the categories of the vocabulary can be
applied to and which asset types are *required* to have an associated asset from
the vocabulary. Lastly, you can configure the permissions of the vocabulary.
Should the vocabulary be viewable by guests? Only site members? Only owners?
Which of these roles should be able to delete the vocabulary, update it, or edit
its permissions? By default, guests can view the vocabulary but only the owner
can delete it, update it, or configure its permissions.

Creating new categories is similar to creating new tags except that categories
must be added to an existing vocabulary and they can only be create by site
administrators. However, once created, regular users can apply categories to any
assets they have permission to create or edit. To create a new category, click
the *Add Category* button in the categories administration portlet. Enter a name
for the new category and, optionally, a description. Use the *To Vocabulary*
dropdown list to select a vocabulary to which to add the category. Just as with
tags, you can configure the permissions of the category, choosing which roles
(guest, site member, owner) can view the category, apply it to an asset, delete
it, update it, or configure its permissions. By default, categories are viewable
by guests and site members can apply categories to assets. Also, you can add
properties to categories, just as with tags. Category properties are a way to
add information to specific categories. You can think of category properties as
tags for your categories. Structurally, category properties are just like tag
properties: they are key-value pairs associated with specific categories that
provide information about the categories.

Once you have created some vocabularies and categories, you can take advantage
of the full capabilities of categories by creating a nested hierarchy of
categories. To nest categories, select the category that you'd like to be the
parent category. Then drag any category that should be a child category onto it.
You will see a plus sign appear next to the name of the category you are
dragging if you can add it to the selected parent category; if you see a red *x*
that means that you cannot add that category as a subcategory of parent category
that you have selected.

After you have created a hierarchy of categories, your content creators will have
them available to apply to content that they create. Click on *Web Content* in
the Content section of the Site Administration area the Control Panel and click
*Add* &rrar; *Basic Web Content*. Click on *Categorization* from the right-side
menu and click *Select* on the vocabulary you'd like to apply. A dialog box
appears with your categories. Select any relevant categories by checking the box
next to them, and they'll be applied to the content.

Liferay 6.1 added several new features to vocabularies and categories. We
mentioned a few of these already when we were discussing the *Allow Multiple
Categories* and *Required* checkboxes for vocabularies and categories. The three
new features are targeted vocabularies, single/multi-valued vocabularies, and
separated widgets for every vocabulary.

### Targeted Vocabularies 

Targeted Vocabularies allow you to decide which vocabularies can be applied to
an asset type and which vocabularies are required for an asset type. To
configure these settings, go to the categories administration portlet in the
Control Panel and mouse over the vocabulary in the list until you see the edit
icon to the right. Select the icon to reveal a dialog box like the one below.

![Figure 6.4: You can target vocabularies by checking the *Allow Multiple Categories* checkbox and then selecting the Asset Types.](../../images/targeted-vocabularies.png)

The default value for *Associated Asset Types* is *All Asset Types*. You can
fine tune your choices by using the *+* and *-* buttons, which narrows the scope
of the vocabulary to specific assets. In the screenshot above, notice that the
vocabulary is configured to be available for Web Content instances and Blog
entries, but it is not required. It is mandatory, however, for Documents and
Media files.

### Single and Multi-valued Vocabularies 

You can also decide if users can choose one or more categories from the same
vocabulary to apply to an asset. If a vocabulary is single-valued you can only
choose one. If it allows more, you can choose several categories from the
vocabulary to apply to an asset.

![Figure 6.5: Multi-valued vocabularies allow multiple categories from the vocabulary to be applied to an asset. Single-valued vocabularies only allow one category from the vocabulary to be applied. Here, the *Cinema* and *Music* categories are selected to be applied but the *Sports* category is not.](../../images/multi-valued-vocabularies.png)

You can configure the single-valued or multi-valued status of a vocabulary
through the categories administration portlet. Edit a vocabulary and deselect
the *Allow Multiple Categories* checkbox to create a single-valued vocabulary.
Use the default option to create a multi-valued vocabulary.

### Separated Widgets 

A third feature of vocabularies and categories is that every vocabulary has its
own separated widget. These widgets appear in the Categorization section of the
form for editing an asset and they allow users to easily select appropriate
categories for that asset.

![Figure 6.6: Vocabularies have their own widgets, making it easy to select available categories.](../../images/separated-widgets.png)

It's important to use tags and categories with all your content, so that content
is easier for users to find. Let's look at one of the ways users will make use
of tags and categories: searching for content. 
