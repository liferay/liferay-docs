# Organizing Content with Tags and Categories [](id=organizing-content-with-tags-and-categories)

Tags and categories are two important tools you can use to help organize
information in @product@. These tools help users to easily find the content
they're looking for through search or navigation. Tagging and categorizing
assets is easy. You can tag or categorize an asset at creation time or when
editing an existing asset. If you click on the *Metadata* section of the
form when creating or editing an asset, you'll find an interface for adding tags
and categories. If no categories are available to be added to the asset (e.g.,
if no categories have been created), the *Select* option won't appear.

![Figure 1: Here, the Web Content application's form for categorizing a new web content article includes the categories *Color* and *Type* with *Select* options, since categories exist.](../../../images/web-content-categorization.png)

+$$$

**Note:** You'll notice in figure 1 above that there is also a *Priority* field
for web content. This field is not related to categories and tags, but rather,
specifies the order in which the web content article is listed when displayed in
the Asset Publisher. To learn more about the Asset Publisher, see the
[Publishing Assets](/discover/portal/-/knowledge_base/7-0/publishing-assets)
section.

$$$

The Menu (![Menu](../../../images/icon-menu.png)) contains interfaces for
managing tags and categories for each site in @product@. Navigate to the Site
Administration menu &rarr; *Content*, and you'll find the *Tags* and
*Categories* options. These options can be used to manage all your site's tags
and categories. It is important that you both tag and categorize your content
when you enter it. You'll take a closer look at tags and categories next.

## Tagging Content [](id=tagging-content)

Tags are an important tool that can help organize information in @product@ and
make it easier for users to find the content that they're interested in. Tags
are words or phrases that you can attach to any content on the website. Tagging
content makes your search results more accurate and enables you to use tools
like the Asset Publisher to display content in an organized fashion on a web
page. There are two ways to create tags: you can do it through the
administrative console in the Site Administration section of the Menu or on the
fly as content is created. By default, tags can be created by regular users and
users can apply them to any assets which they have permission to create or edit.

While regular users can, by default, create new tags by applying them to any
assets that they have permission to create or edit, only site administrators can
access the *Tags* application in the Content section of the Site Administration
area of the Menu. Here, site administrators can create new tags and edit any
existing site tags. To create tags in Site Administration, visit the site for
which you want to create tags and then click *Content *&rarr; *Tags*. From this
screen, you can view existing tags and create new ones. To create a new tag,
click the *Add Tag* icon (![Add Tag](../../../images/icon-add.png)) and enter a
name for the tag.

![Figure 2: The Add Tag interface is very simple, only requiring the name of your tag.](../../../images/new-tag-interface.png)

The process for adding tags during content creation is very similar. For
example, to create tags for a new web content article, navigate to the
*Metadata* dropdown in a New Web Content menu, and add tags *Lunar*, *Moon*, and
*Spectacular*. Once you've created the web content with these tags, the web
content will be associated with those tag words when they are searched or
referenced anywhere in @product@.

Tags are not the only instance-wide mechanism for describing content: you can
also use categories.

## Defining Categories for Content [](id=defining-categories-for-content)

Categories are similar in concept to tags, but are designed for use by
administrators, not regular users. Hierarchies of categories can be created, and
categories can be grouped together in *vocabularies*. While tags represent an ad
hoc method for users to group content together, categories exist to allow
administrators to organize content in a more official, hierarchical structure.
You can think of tags like the index of a book and categories like its table of
contents. Both serve the same purpose: to help users find the information they
seek.

+$$$

**Note:** In previous versions of Liferay, you could specify web content types
via `portal.properties`. In @product@ 7.0, web content types are no longer used
and have been replaced by vocabularies. Vocabularies allow users to filter their
web content articles by category instead, which lets you filter your content
using the Asset Publisher and faceted search.

$$$

Adding vocabularies and categories is similar to adding tags. Visit the site for
which you want to create categories and then click *Content* &rarr; *Categories*
to view the Categories application.

![Figure 3: After adding new vocabularies, you'll notice your vocabularies indicate the amount of categories existing beneath them.](../../../images/vocabulary-list.png)

Clicking on a vocabulary displays any categories that have been created under
that vocabulary. To create a new vocabulary, click on the *Add Vocabulary*
button (![Add Vocabulary](../../../images/icon-add.png)). Enter a name and,
optionally, a description. By default, the *Allow Multiple Categories* option is
enabled. This allows multiple categories from the vocabulary to be applied to an
asset. If the box is disabled, only one category from the vocabulary can be
applied to an asset. The *Associated Asset Types* lets you choose which asset
types the categories of the vocabulary can be applied to and which asset types
are *required* to have an associated asset from the vocabulary. Lastly, you can
configure the permissions of the vocabulary. Should the vocabulary be viewable
by guests? Only site members? Only owners? Which of these roles should be able
to delete the vocabulary, update it, or edit its permissions? By default, guests
can view the vocabulary but only the owner can delete it, update it, or
configure its permissions.

Creating new categories is similar to creating new tags except that categories
must be added to an existing vocabulary and they can only be created by site
administrators. Once created, however, regular users can apply categories to any
assets they have permission to create or edit. To create a new category, click
the *Add Category* icon (![Add Category](../../../images/icon-add.png)) if you're
already viewing a vocabulary, or you can select the *Actions* button
(![Actions](../../../images/icon-actions.png)) next to an existing vocabulary and
select *Add Category*. Enter a name for the new category and, optionally, a
description. Just as with tags, you can configure the permissions
of the category, choosing which roles (guest, site member, owner) can view the
category, apply it to an asset, delete it, update it, or configure its
permissions. By default, categories are viewable by guests and site members can
apply categories to assets. Also, you can add properties to categories. Category
properties are a way to add information to specific categories. You can think of
category properties as tags for your categories. Structurally, category
properties are just like tag properties: they are key-value pairs associated
with specific categories that provide information about the categories.

Once you have created some vocabularies and categories, you can take advantage
of the full capabilities of categories by creating a nested hierarchy of
categories. To nest categories, select the *Actions* button for the category
you'd like to be the parent category. Then select *Add Subcategory*, which adds
a child category to the selected parent.

After you've created a hierarchy of categories, your content creators will have
them available to apply to content that they create. Click on *Web Content* in
the Content section of Site Administration and click *Add* &rarr; *Basic Web
Content*. Click on *Categorization* from the right-side menu and click *Select*
on the vocabulary you'd like to apply. A dialog box appears with your
categories. Select any relevant categories by checking the box next to them, and
they'll be applied to the content.

Suppose you're running a Lunar Resort shop called Lunar Fireworks and you have
many web content articles describing the colors and types of fireworks you
offer. The abundance of your articles is overwhelming, and as your shop grows,
so too does the web content articles you're required to manage. You've decided
to categorize your web content based on the color and type of firework, so the
articles are easier to manage. Navigate to Site Administration &rarr; *Content*
&rarr; *Categories* and create vocabularies *Type* and *Color*. Make sure both
vocabularies are only used for web content articles by clicking the *Associated
Asset Types* dropdown and selecting *Web Content Article*. Create categories
*Fire* and *Smoke* for the Type vocabulary and *Red*, *Yellow*, and *Blue*
categories for the Color vocabulary.

Now navigate to *Content* &rarr; *Web Content* in Site Administration and create
an article called *Red Rocket*. This is your best selling product, so make sure
to give it a detailed explanation and awesome picture. Select the *Metadata*
dropdown for your web content article and select the Type &rarr; Fire and Color
&rarr; Red categories. When you publish your new web content article for your
best selling product, it'll be organized by its type and color. Once you've
organized all your articles, you'll always be able to reference the type and
color of a firework, just in case you forget.

There are a few other cool features for vocabularies and categories. A few of
them were mentioned already when the *Allow Multiple Categories* and *Required*
selectors for vocabularies and categories were discussed. The three new
features are targeted vocabularies, single/multi-valued vocabularies, and
separated widgets for every vocabulary.

### Targeted Vocabularies [](id=targeted-vocabularies)

Targeted Vocabularies allow you to decide which vocabularies can be applied to
an asset type and which vocabularies are required for an asset type. To
configure these settings, go to the Categories application in Site
Administration and select a vocabulary's *Actions* icon. Select the *Associated
Asset Types* tab to reveal a dialog box like the one below.

![Figure 4: You can target vocabularies by checking the *Allow Multiple Categories* selector and then selecting the Asset Types.](../../../images/targeted-vocabularies.png)

The default value for *Associated Asset Types* is *All Asset Types*. You can
fine tune your choices by using the *+* and *-* buttons, which narrows the scope
of the vocabulary to specific assets. In the screenshot above, notice that the
vocabulary is configured to be available for Web Content articles and Blog
entries, but it is not required. It is mandatory, however, for Bookmark entries.

### Single and Multi-valued Vocabularies [](id=single-and-multi-valued-vocabularies)

You can also decide if users can choose one or more categories from the same
vocabulary to apply to an asset. If a vocabulary is single-valued you can only
choose one. If it allows more, you can choose several categories from the
vocabulary to apply to an asset.

![Figure 5: Multi-valued vocabularies allow multiple categories from the vocabulary to be applied to an asset. Single-valued vocabularies only allow one category from the vocabulary to be applied. Here, the *Dining* and *Nightlife* categories are selected to be applied but the *Scenic Adventures* category is not.](../../../images/multi-valued-vocabularies.png)

You can configure the single-valued or multi-valued status of a vocabulary
through the Categories application. Edit a vocabulary and deselect the *Allow
Multiple Categories* selector to create a single-valued vocabulary. Use the
default option to create a multi-valued vocabulary.

### Separated Widgets [](id=separated-widgets)

A third feature of vocabularies and categories is that every vocabulary has its
own separated widget. These widgets appear in the Categorization section of the
form for editing an asset and they allow users to easily select appropriate
categories for that asset.

![Figure 6: Vocabularies have their own widgets, making it easy to select available categories.](../../../images/separated-widgets.png)

It's important to use tags and categories with all your content, so that content
is easier for users to find. Now that your content is categorized and tagged,
you'll learn how to define content relationships next.
