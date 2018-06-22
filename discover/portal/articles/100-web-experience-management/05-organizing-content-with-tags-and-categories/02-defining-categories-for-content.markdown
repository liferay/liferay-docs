# Defining Categories for Content [](id=defining-categories-for-content)

Categories are similar in concept to tags, but are designed for use by
administrators, not regular users. Hierarchies of categories can be created, and
categories can be grouped together in *vocabularies*. While tags represent an
*ad hoc* method for grouping content, categories exist to allow administrators
to organize content in a more official, hierarchical structure. Think of tags
like the index of a book and categories like its table of contents. Both serve
the same purpose: to help users find the information they seek.

You can add properties to categories. Category properties are a way to add
information to specific categories. You can think of category properties as tags
for your categories. Structurally, category properties are just like tag
properties: they are key-value pairs associated with specific categories that
provide information about the categories.

Adding vocabularies and categories is similar to adding tags:

1.  Go to the site where you want to create categories.

2.  Click *Categorization* &rarr; *Categories* to view the Categories 
    application.

![Figure 1: After adding new vocabularies, you'll notice your vocabularies indicate the amount of categories existing beneath them.](../../../images/vocabulary-list.png)

Clicking on a vocabulary displays categories that have been created under that
vocabulary. To create a new vocabulary,

1.  Click on the *Add Vocabulary* button (![Add Vocabulary](../../../images/icon-add.png)).

2.  Enter a name and, optionally, a description.

3.  Click *Save*.

By default, the *Allow Multiple Categories* option is enabled. This allows 
multiple categories from the vocabulary to be applied to an asset. If the box 
is disabled, only one category from the vocabulary can be applied to an asset. 
The *Associated Asset Types* lets you choose which asset types the categories 
of the vocabulary can be applied to and which asset types are *required* to have
an associated asset from the vocabulary. Finally, you can configure the
permissions of the vocabulary. By default, guests can view the
vocabulary but only the owner can delete it, update it, or configure its
permissions.

Creating new categories is similar to creating new tags except that categories
must be added to an existing vocabulary and they can only be created by site
administrators. Once created, however, regular users can apply categories to any
assets they have permission to create or edit. To create a new category:

1.  Click the *Add Category* icon (![Add Category](../../../images/icon-add.png)).

If you're already viewing a vocabulary:

1.  Select the *Actions* button (![Actions](../../../images/icon-actions.png)) next to an existing 
    vocabulary and select *Add Category*.

2.  Enter a name for the new category and, optionally, a description.

3.  Click *Save*.

Just as with tags, you can configure the category's permissions, choosing which
roles (guest, site member, owner) can view the category, apply it to an asset,
delete it, update it, or configure its permissions. By default, categories are
viewable by guests, and site members can apply categories to assets. 

Once you have created some vocabularies and categories, you can take advantage
of the full capabilities of categories by creating a nested hierarchy of
categories. To nest categories, select the *Actions* button for the category you
want to be the parent category. Then select *Add Subcategory*, which adds
a child category to the selected parent.

After you've created a hierarchy of categories, they're available to apply to
content: 

1.  Click on *Web Content* in the Content section of Site Administration and
    click *Add* &rarr; *Basic Web Content*. 
 
2.  Click on *Categorization* from the right-side menu and click *Select* on the
    vocabulary you'd like to apply. A dialog box appears with your categories.
 
3.  Select relevant categories by checking the box next to them, and they'll
    be applied to the content.

Suppose you're running a Lunar Resort shop called Lunar Fireworks and you have
many web content articles describing the colors and types of fireworks you
offer. The abundance of your articles is overwhelming, and as your shop grows,
so too does the web content articles you're required to manage. You've decided
to categorize your web content based on the color and type of firework, so the
articles are easier to manage.

1.  Go to Site Administration &rarr; *Content* &rarr; *Categories* and create 
    vocabularies *Type* and *Color*.

2.  Make sure both vocabularies are only used for web content articles by 
    clicking the *Associated Asset Types* dropdown and selecting *Web Content 
    Article*.

3.  Create categories *Fire* and *Smoke* for the Type vocabulary and *Red*, 
    *Yellow*, and *Blue* categories for the Color vocabulary.

4.  Now navigate to *Content* &rarr; *Web Content* in Site Administration and 
    create an article called *Red Rocket*. This is your best selling product, so
    make sure to give it a detailed explanation and an awesome picture.

5.  Select the *Metadata* dropdown for your web content article and select the 
    Type &rarr; Fire and Color &rarr; Red categories.

When you publish your new web content article for your best selling product,
it's organized by its type and color. Once you've organized all your articles,
you'll always be able to reference the type and color of a firework, just in
case you forget.

There are a few other cool features for vocabularies and categories. A few of
them were mentioned already when the *Allow Multiple Categories* and *Required*
selectors for vocabularies and categories were discussed. The three new
features are targeted vocabularies, single/multi-valued vocabularies, and
separated widgets for every vocabulary. They're in the next tutorial.
