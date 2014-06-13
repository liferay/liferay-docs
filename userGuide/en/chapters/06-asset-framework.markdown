# Leveraging the Asset Framework [](id=leveraging-the-asset-framework-liferay-portal-6-2-user-guide-06-en)

Any type of content in Liferay is considered an asset. In chapters 2 and 3, we
examined Liferay's most common type of asset: web content. Other types of assets
include blog posts, wiki articles, message board posts, bookmarks, and
documents. It's possible for developers to define custom asset types that
utilize Liferay's asset framework. Originally, the asset framework was created
to provide a mechanism for adding tags to blog entries, wiki articles, and web
content without reimplementing the same functionality multiple times. The asset
framework has been greatly extended since then and it now supports tags,
categories, vocabularies, comments, ratings, and asset relationships.

This chapter covers the following topics:

- Tagging and categorizing content
- Using targeted, single value, and multi-value vocabularies
- Using faceted search
- Using the Asset Publisher
- Setting up display pages
- Adding relationships between assets

The Asset Publisher portlet is designed to display multiple assets. It has quite
a few configuration options which we'll cover in this chapter. By default,
abstracts (previews) of recently published assets are displayed by the Asset
Publisher portlet and links to their full views are provided. You can configure
the Asset Publisher portlet to display a table of assets, a list of asset
titles, or the full content of assets. You can also configure the Asset
Publisher to display only certain kinds of assets and you choose how many items
to display in a list. The Asset Publisher portlet is very useful for displaying
chosen types of content, for displaying recent content, and for allowing users
to browse content by tags and categories. The Asset Publisher is designed to
integrate with the Tags Navigation and Categories Navigation portlets to allow
this.

## Tagging and Categorizing Content [](id=tagging-and-categorizing-content-liferay-portal-6-2-user-guide-06-en)

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

### Tags [](id=tags-liferay-portal-6-2-user-guide-06-en)

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

### Categories [](id=categories-liferay-portal-6-2-user-guide-06-en)

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

#### Targeted Vocabularies [](id=targeted-vocabularies-liferay-portal-6-2-user-guide-06-en)

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

#### Single and Multi-valued Vocabularies [](id=single-and-multi-valued-vocabularies-liferay-portal-6-2-user-guide-06-en)

You can also decide if users can choose one or more categories from the same
vocabulary to apply to an asset. If a vocabulary is single-valued you can only
choose one. If it allows more, you can choose several categories from the
vocabulary to apply to an asset.

![Figure 6.5: Multi-valued vocabularies allow multiple categories from the vocabulary to be applied to an asset. Single-valued vocabularies only allow one category from the vocabulary to be applied. Here, the *Cinema* and *Music* categories are selected to be applied but the *Sports* category is not.](../../images/multi-valued-vocabularies.png)

You can configure the single-valued or multi-valued status of a vocabulary
through the categories administration portlet. Edit a vocabulary and deselect
the *Allow Multiple Categories* checkbox to create a single-valued vocabulary.
Use the default option to create a multi-valued vocabulary.

#### Separated Widgets [](id=separated-widgets-liferay-portal-6-2-user-guide-06-en)

A third feature of vocabularies and categories is that every vocabulary has its
own separated widget. These widgets appear in the Categorization section of the
form for editing an asset and they allow users to easily select appropriate
categories for that asset.

![Figure 6.6: Vocabularies have their own widgets, making it easy to select available categories.](../../images/separated-widgets.png)

It's important to use tags and categories with all your content, so that content
is easier for users to find. Let's look at one of the ways users will make use
of tags and categories: searching for content. 

## Searching for Content in Liferay [](id=searching-for-content-in-liferay-liferay-portal-6-2-user-guide-06-en)

To stay organized, I (RS) used to use a paper-based planner. It had different
sections for various areas of my life. Its initial incarnation came from a
commercial company, but over the years I tweaked it into something that worked
for me. This final version (before I went digital) had different tabs for
different areas of my life that I wanted to keep track of: daily items like
tasks, notes, a spiritual section, and agenda pages that kept track of things I
needed to go over with specific people. A planning section had tabs for
projects, family, future items, and reference. 

Of course, since this was paper-based, it had its limitations. It was kind of
hard to find stuff. Did I put the note I'd written about that new toy my
daughter wanted in the notes section or in the family section? Or maybe it was
on my *While Out* list, so I would remember to buy it before her birthday? 

Liferay content can be like this. That important information you remember
seeing--was it in a wiki article, a message boards post, or web content? Did you
remember to tag it? If you don't have this kind of information, browsing to the
content you're looking for could be difficult. Thankfully, Liferay includes a
powerful, faceted search function. You can access this function through the
Search portlet, which lets you drill down through the different types of
content, tags, and categories to refine your search. Let's look at the search
features Liferay provides for specific kinds of content and then examine how to
use Liferay's faceted search.

### Searching for Specific Types of Content [](id=searching-for-specific-types-of-content-liferay-portal-6-2-user-guide-06-en)

Liferay provides several applications that allow users and administrators to
search for content. First, there's the Search portlet. The Search portlet can be
placed a page to allow users to search for assets of any type. We'll learn how
to use the Search portlet when we discuss Liferay's faceted search feature in
the next section. There's also a Web Content Search portlet. This portlet can be
placed on a page to allow users to search for web content instances. Users can't
use the Web Content Search portlet to search for other kinds of content.
However, the Blogs, Wiki, Message Boards, Documents and Media, and Web Content
portlets all provide search bars that allow users to search among the specific
types of assets with which these portlets allow users to interact. Note that all
of these portlets are accessible from the Control Panel. They can also, except
for the Web Content portlet, be placed on portal pages for end-users to use. The
Web Content portlet is not designed for end users; it's intended for
administrative use only.

In order for content to be searchable, it must first be indexed. Liferay uses
Lucene for indexing and searching for content. Please refer to the Lucene
section of the portal properties file if you'd like to customize Liferay's
indexing and search behavior. By default, Liferay indexes blog posts, wiki
articles, and message board posts, Documents and Media files' descriptions, and
web content instances. If a Documents and Media file is a text file, the file's
content is indexed as well. Comments on blog posts, wiki articles, and Documents
and Media files and text file attachments to wiki articles and message board
posts are also indexed. Liferay automatically indexes content as it's added to
the portal.

If you'd like to search among assets of a specific type and you'd like to
include comments and attachments in your search, use the search bar of the
appropriate portlet. For example, if you'd like to search for the term *Liferay*
among wiki articles, enter the term *Liferay* into the Wiki portlet's search
bar. 

![Figure 6.7: When using the Wiki portlet's search bar to search for *Liferay*, wiki articles, comments, and attachments containing the word *Liferay* are returned.](../../images/wiki-search.png)

Wiki articles, comments, and text file attachments containing the word *Liferay*
are returned as search results. The search results clearly show whether an
individual search result is a wiki article, a comment, or an attachment. In the
next section, we'll see how the Search portlet can be used to search through
multiple types of content on Liferay. Its faceted search feature is a powerful
tool that allows users to include or not include specific types of assets in a
search. However, the Search portlet does not return comments or attachments as
search results.

### Searching for Portal Content Using Facets [](id=searching-for-portal-content-using-face-liferay-portal-6-2-user-guide-06-en)

To get started using faceted search, drop the Search portlet on a page and
search for something. You'll see a page with results on the right and a
collection of *facets* on the left. 

![Figure 6.8: The first set of facets is content types. You can drill down to specific types of content that contain the search terms you entered.](../../images/faceted-search-1.png)

Facets allow users of the Search portlet to select criteria with which to filter
search results. A facet is a combination of the information about a specific
indexed field, its terms, and their frequency. Facets are typically named by the
field in question. From the Search portlet's Configuration window,
administrators can configure the facets available to users for filtering search
results. The default facets include the following:

- Site
- Asset type
- Asset tag
- Asset category
- Folder
- User
- Modified time range

For example, after searching for a certain term in the Search portlet, clicking
on a specific site filters the search results to only display assets within the
specified site. Clicking on a specific user filters the search results to only
display assets added by the specified user. The frequency with which the term
was found for each facet is listed in parentheses after the facet. It may jog
your memory to see that the term you searched for appears in a blog entry, and
that may be all you need to find what you were looking for. If, however, your
memory is more foggy than that, or you're searching for something you're not
sure is actually there, then the asset tags or asset categories facets may be
more helpful to you. 

![Figure 6.9: Asset tag facets let you see how many assets contain the terms for which you searched *and* contain certain tags. Click on a specific tag to narrow down the list of search results to those to which the tag has been applied.](../../images/faceted-search-2.png)

For example, if you searched for a wireless phone, you might be more interested
in content that has your search terms in it *and* has also been tagged by users.
One or more tags might help you to find what you're looking for. Note that the
number of tags that appear is configurable: by default it's 10, but there could
be many more as a result of a particular search. We'll look at the configuration
options later in the chapter. For now, let's learn how to drill down to narrow
search results. 

To drill down into the search, click on a facet to add it to the filter list.
The results to the right are refined by the selected facets. 

![Figure 6.10: Drilling down creates a list of what you selected at the top of the screen.](../../images/05-faceted-search-drill-down-1.png)

Here, we've refined the search to only show documents. We've also selected one
of the tags, *cool*, to refine the search. The facets we've selected, *Document*
and *cool*, appear in a list at the top, and there's a red "X" next to it that
lets us remove it from our filter as we work to refile our search.  Suppose that
the two facets we selected weren't enough to filter our search into a small
enough list to sort through. In this case, we could further refine the search by
selecting another facet, as below. 

![Figure 6.11: Selecting another facet further refines the search.](../../images/05-faceted-search-drill-down-2.png)

Now we've also selected the *Music* category, which reduces the list of search
hits on the right. In this way, you can interactively tweak the search results
to narrow them down, making it easier to find that proverbial needle within the
haystack. 

#### Asset Types [](id=asset-types-liferay-portal-6-2-user-guide-06-en)

Searching can only be done on assets. As has already been described in this
chapter, just about any entity in the portal is an asset and can be indexed and
searched. Under the hood, this means that these entities use Liferay's Asset API
and have an Indexer class defined. 

Developers can create custom searchable assets within the portal. This is
described in the [Asset
Framework](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/asset-framework-liferay-portal-6-2-dev-guide-06-en).
section of the Developer's Guide. For this reason, you can have additional asset
types defined in your portal beyond the ones that Liferay ships with by default.
If this is the case, you might want to tweak the `frequencyThreshold` and the
`maxTerms` settings to increase the number of asset types displayed past the
default of 10. This is covered in the section below on search options.

#### Asset Tags [](id=asset-tags-liferay-portal-6-2-user-guide-06-en)

If tags have been applied to any asset that appears in the result set, it may
be displayed in the Asset Tag facet. Tags are handled in a similar way to how
asset types are handled: not all tags may appear. There may be many more than
the 10 tags listed, but the default configuration for this facet is to show the
top 10 most frequent terms. As with asset types, this can be modified by
setting the `max terms` property.

#### Asset Categories [](id=asset-categories-liferay-portal-6-2-user-guide-06-en)

If categories have been applied to any asset that appears in the result set,
they may be displayed in the Asset Categories facet. Asset categories work just
like asset tags. As with asset tags, you can modify the number of categories
listed in the search by setting the `max terms` property.

Let's move on to advanced searching. 

### Advanced Searching [](id=advanced-searching-liferay-portal-6-2-user-guide-06-en)

The Search portlet's search box is deceptively simple. Though you have only a
single field for search, there's a search syntax inherited from
[Lucene](http://lucene.apache.org/core/old_versioned_docs/versions/3_0_3/queryparsersyntax.html)
that lets you create very powerful search queries. Let's look at some ways you
can use search queries. 

**Searching for specific fields:** By default, searches are performed against a
long list of fields. Sometimes you want results for a term within a particular
field. This can be achieved using the field search syntax `[field]:[term]`. For
example, to search in the *Title* field for *Liferay*, use the following
syntax:

    title:liferay

If you search for a phrase within a field, surround the term with double
quotation marks: 

    title:"Liferay Portal"

**Wildcards:** You can use wildcards in exactly the way you use them with your
operating system: for a single character wildcard, use `?`; for
the multiple character wildcard, use `*`. 

**Boolean operators:** You can use logic operators, such as AND, OR, NOT, `+`,
and `-` in your searches. The `AND` operator matches assets in which the terms
between the `AND` operator exist. For example, to search for both Liferay and
Kaleo Workflow, use this query: 

    "liferay" AND "kaleo workflow"

The `OR` operator is the default; if there's no operator between two terms, the
`OR` operator takes effect. `OR` finds matches if any term exists in an asset. 

The `+` operator requires that the term exists somewhere in some field in the
asset. If you wanted to search for something that *must* contain *liferay* and
*may* contain *portal*, use this query: 

    +liferay portal

The `NOT` operator excludes assets that contain the term after the `NOT`
operator. It requires that at least two terms be present: 

    "Liferay Portal" NOT "Liferay Social Office" 

The `-` operator is similar: it excludes assets that contain the term after the
`-` symbol: 

    "Liferay Portal" - "Liferay Social Office" 

**Grouping:** You can use parentheses within your queries to form sub-queries,
in a similar fashion to an SQL statement. For example, to search for *liferay*
or *social office* and *website*, use this query: 

    (liferay OR "social office") AND website

As you can see, the search syntax is very powerful. There's more you can do with
it than what is listed here; to view the full syntax, visit the Lucene URL
above. 

Next, we'll look at how the Search portlet can be configured. 

### Setting Search Options [](id=setting-search-options-liferay-portal-6-2-user-guide-06-en)

As with Liferay's other portlets, you can configure the Search portlet via the
configuration screen, which looks like the below illustration. 

![Figure 6.12: Basic search configuration is pretty straightforward.](../../images/05-faceted-search-configuration.png)

**Display Asset Type Facet:** Toggles whether the Asset Type facet appears. 

**Display Asset Tags Facet:** Toggles whether the Asset Tags facet appears. 

**Display Asset Categories Facet:** Toggles whether the Asset Categories facet
appears. 

**Display Modified Range Facet:** Toggles whether the date modified range facet
appears. 

**Display Results in Document Form:** Never use this in production. Developers
use this feature to view search responses in their generic, Document-based
format. Part of a developer's job when writing search indexers is to convert
Documents (the objects that get indexed) to the actual object and back again.
This option allows developers to see how their objects are being indexed. 

**View in Context:** When an asset is clicked, show it in the portlet to which
it belongs. 

**Display Main Query:** Show the exact search query that the portlet generated
to the search engine. Again, never use this in production; this is for
development purposes only. 

**Display Open Search Results:** Shows results from third party Open Search
plugins, if they are installed. This is for backward compatibility only:
developers are encouraged to re-design their search code as described in
*Liferay in Action*, and then custom assets are aggregated with native portal
assets seamlessly. 

These are the basic options. But you didn't miss the fact that there are also
advanced options, did you?

Configuring advanced search requires a bit more technical acumen than you might
expect, because there are so many properties to tweak. Thankfully, in most
instances, you shouldn't need to change a thing. If you do, however, the
configuration is done through a JSON object. 

If you don't know what a JSON object is, don't worry: it's not a difficult
concept. JSON stands for **J**ava**S**cript **O**bject **N**otation. An Object
is a software development term for anything that can be represented in code.
Objects have *attributes*, or sometimes these are called *fields*, and they are
very similar to fields you'd find on a form that you're filling out. Software
developers use the word *object* to refer generically to anything like this that
they can describe in the software; for all intents and purposes, objects
could just as easily have been called Things. For example, one type of object
used in Liferay is a User. A User can be represented in code, and it has many
*fields*, such as a name, an email address, and more. JSON is one way of
describing objects like this. 

The object we're concerned with is called `facets`. Here's what it looks like,
in all its glory, in JSON. Explanation of the settings follows the object below. 

	{"facets": [
	    {
		"displayStyle": "asset_entries",
		"weight": 1.5,
		"static": false,
		"order": "OrderHitsDesc",
		"data": {
		    "values": [
			"com.liferay.portlet.bookmarks.model.BookmarksEntry",
			"com.liferay.portlet.blogs.model.BlogsEntry",
			"com.liferay.portlet.calendar.model.CalEvent",
			"com.liferay.portlet.documentlibrary.model.DLFileEntry",
			"com.liferay.portlet.journal.model.JournalArticle",
			"com.liferay.portlet.messageboards.model.MBMessage",
			"com.liferay.portlet.wiki.model.WikiPage",
			"com.liferay.portal.model.User"
		    ],
		    "frequencyThreshold": 1
		},
		"label": "asset-type",
		"className": "com.liferay.portal.kernel.search.facet.AssetEntriesFacet",
		"fieldName": "entryClassName"
	    },
	    {
		"displayStyle": "asset_tags",
		"weight": 1.4,
		"static": false,
		"order": "OrderHitsDesc",
		"data": {
		    "maxTerms": 10,
		    "displayStyle": "list",
		    "frequencyThreshold": 1,
		    "showAssetCount": true
		},
		"label": "tag",
		"className": "com.liferay.portal.kernel.search.facet.MultiValueFacet",
		"fieldName": "assetTagNames"
	    },
	    {
		"displayStyle": "asset_tags",
		"weight": 1.3,
		"static": false,
		"order": "OrderHitsDesc",
		"data": {
		    "maxTerms": 10,
		    "displayStyle": "list",
		    "frequencyThreshold": 1,
		    "showAssetCount": true
		},
		"label": "category",
		"className": "com.liferay.portal.kernel.search.facet.MultiValueFacet",
		"fieldName": "assetCategoryTitles"
	    },
	    {
		"displayStyle": "modified",
		"weight": 1.1,
		"static": false,
		"order": "OrderHitsDesc",
		"data": {
		    "ranges": [
			{
			    "range": "[past-hour TO *]",
			    "label": "past-hour"
			},
			{
			    "range": "[past-24-hours TO *]",
			    "label": "past-24-hours"
			},
			{
			    "range": "[past-week TO *]",
			    "label": "past-week"
			},
			{
			    "range": "[past-month TO *]",
			    "label": "past-month"
			},
			{
			    "range": "[past-year TO *]",
			    "label": "past-year"
			}
		    ],
		    "frequencyThreshold": 0
		},
		"label": "modified",
		"className": "com.liferay.portal.kernel.search.facet.ModifiedFacet",
		"fieldName": "modified"
	    }
	]}

Now that you've seen the object, don't be daunted by it. Here are all the
settings within the object that you can tweak. 

**"className":** This field must contain a string value which is the FQCN (fully
qualified class name) of a java implementation class implementing the Facet
interface. Liferay provides the following implementations by default:

	com.liferay.portal.kernel.search.facet.AssetEntriesFacet
	com.liferay.portal.kernel.search.facet.ModifiedFacet
	com.liferay.portal.kernel.search.facet.MultiValueFacet
	com.liferay.portal.kernel.search.facet.RangeFacet
	com.liferay.portal.kernel.search.facet.ScopeFacet
	com.liferay.portal.kernel.search.facet.SimpleFacet

**"data":** This field takes an arbitrary JSON object (a.k.a. {}) for use by a
specific facet implementation. As such, there is no fixed definition of the data
field. Each implementation is free to structure it as needed. The value defined
here matches the implementation that's selected in the `className` attribute
above. 

**"displayStyle":** This field takes a string value and represents a
particular template implementation which is used to render the facet. These
templates are normally JSP pages (but can also be implemented as Velocity or
Freemarker templates provided by a theme if the portal property
`theme.jsp.override.enabled` is set to `true`). The method of matching the string to
a JSP is simply done by prefixing the string with /html/portlet/search/facets/
and appending the .jsp extension.

For example, `"displayStyle": "asset_tags"` maps to the JSP 

	/html/portlet/search/facets/asset_tags.jsp

Armed with this knowledge a crafty developer could create custom display styles
by deploying custom (new or overriding) JSPs using a JSP hook. See the
*Developer's Guide* or *Liferay in Action* for more information on hook plugins. 

**"fieldName":** This field takes a string value and defines the indexed field on
which the facet operates.

For example, `"fieldName": "entryClassName"` indicates that the specified facet
implementation operates on the `entryClassName` indexed field.

**Note:** You can identify available indexed fields by enabling the Search
portlet's *Display Results in Document Form* configuration setting and then
expanding individual results by clicking the *+* symbol to the left of their
titles. 

**"label":** This field takes a string value and represents the language key that
is used for localizing the title of the facet when it's rendered.

**"order":** This field takes a string value. There are two possible values:

   `OrderValueAsc`: This tells the facet to sort it's results by the term values,
   in ascending order.

   `OrderHitsDesc`: This tells the facet to sort it's results by the term
   frequency, in descending order.

**"static":** This field takes a boolean value (`true` or `false`). The default
value is false. A value of `true` means that the facet should not actually be
rendered in the UI. It also means that it should use pre-set values (stored in
its `data` field) rather than inputs dynamically applied by the end user. This
allows for the creation of pre-configured search results.

Imagine you would like to create a pre-configured search that returns only
images (i.e. the asset type is
`com.liferay.portlet.documentlibrary.model.DLFileEntry` and the indexed field
extension should contain the values bmp, gif, jpeg, jpg, odg, png, or svg). We
would need two static facets, one with "fieldName": "entryClassName" and another
with "fieldName": "extension". This could be represented using the following
facet configuration:

	{
	    "displayStyle": "asset_entries",
	    "static": true,
	    "weight": 1.5,
	    "order": "OrderHitsDesc",
	    "data": {
		"values": [
		    "com.liferay.portlet.documentlibrary.model.DLFileEntry"
		],
		"frequencyThreshold": 0
	    },
	    "className": "com.liferay.portal.kernel.search.facet.AssetEntriesFacet",
	    "label": "asset-type",
	    "fieldName": "entryClassName"
	},
	{
	    "displayStyle": "asset_entries",
	    "static": true,
	    "weight": 1.5,
	    "order": "OrderHitsDesc",
	    "data": {
		"values": ["bmp", "gif", "jpeg", "jpg", "odg", "png", "svg"],
		"frequencyThreshold": 0
	    },
	    "className": "com.liferay.portal.kernel.search.facet.MultiValueFacet",
	    "label": "images",
	    "fieldName": "extension"
	}

**"weight":** This field takes a floating point (or double) value and is used to
determine the ordering of the facets in the facet column of the search portlet.
Facets are positioned with the largest values at the top. (yes, the current
implementation is counter-intuitive and perhaps could be reversed in future
versions).

Configuring search using a JSON object is a bit unusual, but as you can see,
it's not as hard as it looks initially.

### Summary [](id=summary-liferay-portal-6-2-user-guide-06-en)

Search is a powerful component of Liferay Portal's asset framework. The
proclivity of assets means that there is an extensible, robust, and configurable
search mechanism throughout the portal that allows administrators to optimize
the search experience of their users. Users also get an easy to use search
interface that makes use of the tags and categories that they themselves apply
to various pieces of content, regardless of the type of content. This makes
Liferay's search truly "for the people."

Power users can learn an extended search syntax that lets them craft very
specific searches. These searches can be used on large installations with lots
of data to find the proverbial needle in the proverbial haystack. Administrators
can tune the configuration of search portlets so that they are optimized for the
contents of their communities. 

Next, we'll look at how the Asset Publisher portlet makes even more extensive
use of Liferay's asset framework to bring relevant content to users.

## Using the Asset Publisher [](id=using-the-asset-publisher-liferay-portal-6-2-user-guide-06-en)

As we create web content, it's important to keep in mind that to Liferay, the
pieces of content are assets, just like message board entries and blog posts.
This allows you to publish different kinds of content using Liferay's Asset
Publisher. You can use the Asset Publisher to publish a mixed group of various
kinds of assets such as images, documents, blogs, and of course, web content.
This helps in creating a more dynamic web site: you can place user-created wiki
entries, blog posts, or message board messages in context with your content.
Let's examine some of its features.

### Querying for Content [](id=querying-for-content-liferay-portal-6-2-user-guide-06-en)

The Asset Publisher portlet is a highly configurable application that lets you
query for mixed types of content on the fly. By giving you the ability to
control what and how content is displayed from one location, the Asset Publisher
helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Options* button in the portlet's
menu (the gear icon). On the Setup tab, you can configure the Asset Publisher's
settings from the following three areas:

- Asset Selection
- Display Settings
- Subscriptions

Asset Selection allows you to configure which assets are displayed. You can set
asset selection to either *dynamic* or *manual*. With dynamic asset selection,
assets are automatically displayed based on certain rules or filters. For
example, you can set the Asset Publisher to display only assets of a certain
type or assets to which certain tags or categories have been applied. With
manual asset selection, the Asset Publisher only displays assets that have been
explicitly selected by an administrator.

The Asset Publisher supports a scope that restricts both manual and dynamic
asset selection. The Asset Publisher can only display assets from its configured
scope. By default, the Asset Publisher portlet is scoped to the site of the page
to which it was added. However, you can customize the scope from the Asset
Selection section of the Asset Publisher configuration window. To extend your
Asset Publisher's scope, click *Select* under Scope and choose either *Global*
to add the global scope or *Other Site...* to add the scope of anther site.

The Display Settings section of the Asset Publisher configuration window lets
administrators customize many details that determine how content is displayed.
The Subscription section allows administrators to enable, disable, or configure
email subscriptions and RSS subscriptions. In the following sections, we'll
explore the available configurations for the Asset Selection, Display Settings,
and Subscriptions sections of the Asset Publisher's configuration window. Let's
start by learning how select content manually. You'll see that it's very similar
to using the Web Content Display portlet except that you can select assets of
any type, not just web content instances.

#### Selecting Assets Manually [](id=selecting-assets-manually-liferay-portal-6-2-user-guide-06-en)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell
the Asset Publisher that you want to select content manually. You can configure
multiple scopes, including the global scope, from which to select assets.

![Figure 6.13: Selecting assets in the Asset Publisher manually is similar to selecting assets in the Web Content Display portlet except that you can select assets of any type, not just web content. You can also add scopes to expand the list of assets that available to be displayed in the Asset Publisher.](../../images/04-web-content-asset-publisher-manual.png)

When selecting assets manually, you'll see a list of configured scopes under the
Scope heading. Click the red "X" button at the right to remove a scope from the
list. Click the *Select* button to add additional scopes to the Asset
Publisher's configuration. After you've added a scope, a new Select button
appears under the Asset Entries heading. A list of assets selected for display
appears in the Asset Entries section. You can select assets to be displayed by
clicking on the appropriate *Select* button. One button appears for each
configured scope. By default, the available asset types include the following:

- Documents Folder
- Bookmarks Folder
- Blogs Entry
- Message Boards Message
- Web Content Instance
- Bookmarks Entry
- Wiki Page
- Document
- Web Content Folder

You can select any number of assets to be displayed. Note, however, that there's
a display setting called *Number of Items to Display* that determines the
maximum number of items to display (or, if pagination is enabled, the maximum
number of items to display per page). The Asset Publisher enables you to mix and
match different asset types in the same interface. When you're done selecting
items to display, click *Save*. Any selected assets are added to the list of
assets that are displayed by the portlet. Once you have your content selected,
you can configure the display types to configure how the content appears. We'll
discuss the display settings in more detail after we finish discussing how to
select assets for display. 

While manual Asset selection allows you to select assets of various types from
different scopes, it can be time-consuming to periodically update the assets
that should be displayed. It's often more convenient to use the Asset Publisher
to select content dynamically.

#### Selecting Assets Dynamically [](id=selecting-assets-dynamically-liferay-portal-6-2-user-guide-06-en)

The Asset Publisher's default behavior is to select assets dynamically according
a set of customizable rules. These rules can be stacked on top of each other so
that they compliment each other to create a nice, refined query for your
content. You can define complicated rules for selecting assets for display and
Liferay automatically takes permissions into account. Liferay's Asset Publisher
performs well in these situations since it queries by search index instead of
querying the database directly. You have the following options for creating
rules for selecting content:

**Scope:** Choose the sites from which the content should be selected. This
works the same way as with manual asset selection: assets can only be displayed
if they belong to a configured scope.

**Asset Type:** Choose whether you'll display any assets or only assets of a
specific type, such as only web content, only wiki entries, or any combination
of multiple types.

**Filter Rules:** Add as many filters on tags or categories as you like. You can
choose whether the content must contain or must not contain any or all of the
tags or categories that you enter.

![Figure 6.14: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)

Once you've set up your filter rules for dynamically selecting content, you can
decide how the content will be displayed.

<!-- TODO: Discuss how to use the CUSTOM USER ATTRIBUTES section of the dynamic
asset selection configuration window of the Asset Publisher. -->

You can order the content returned by the filters by title, create date,
modified date, publication date, etc. in ascending or descending order. For
instance, suppose you have a series of "How To" articles that you want displayed
in descending order based on whether the article was tagged with the *hammer*
tag. Or, suppose you want a series of video captures to display in ascending
order based on a category called *birds*. For these use cases, you can configure
the ordering and grouping settings. You can also group by *Asset*, *Type* or
*Vocabularies*. Vocabularies are groups of categories defined by administrators
in the *Categories* section of the Control Panel.

The *Ordering and Grouping* section of the Asset Publisher allows you to
precisely control how content is ordered and grouped when displayed. You can
order the assets displayed by Asset Publisher in ascending or descending order
by the following attributes:

- Title
- Create Date
- Modified Date
- Publish Date
- Expiration Date
- Priority

You can also configure a second ordering. The second ordering would be applied
to any assets for which the first ordering wasn't sufficient. For example,
suppose you chose to order assets by title and there are multiple assets with
the same title. Then the second ordering would take effect. For example, you
could order all the assets that had the same title by their publication dates.

You can establish grouping rules as well as ordering rules. You can group assets
by type or by vocabulary. For example, suppose there's a vocabulary called
*Membership Type* that belongs to your site. Suppose this vocabulary has two
categories: *Premium* and *Regular*. If you group assets by Membership Type, all
assets with the Premium category will be displayed in one group and all assets
with the Regular category will be displayed in another group. Grouping rules are
applied before any ordering rules: they're a way to divide up the displayed
assets into separate lists. The ordering rules are applied separately to each
group of assets.

Note that grouping and ordering rules are only one mechanism to control how your
content will be displayed. You can refine the display through many other display
settings which we'll examine next.

### Configuring Display Settings [](id=configuring-display-settings-liferay-portal-6-2-user-guide-06-en)

Open the *Display Settings* subtab of the Setup tab of the Asset Publisher's
Configuration window. Here, you can configure many more settings that control
the Asset Publisher's behavior and that determine how the Asset Publisher
displays content. The Display Settings section gives you precise control over
the display of your assets. There are many options available to configure how
you want your content to appear. Many of these, such as printing, flags,
ratings, comments, comment ratings, and social bookmarks work the same way they
do in the Web Content Display portlet.

**Show Add Content Button**: When checked, this checkbox adds an *Add New*
button that allows users to add new assets directly from the Asset Publisher
portlet. This is checked by default.

**Display Template**: This selector lets you choose an application display
template to customize how the Asset Publisher displays assets. Liferay creates
the following display templates for each newly created site, including the
default site:

- Abstracts: This display template shows the first 200-500 characters of the
  content, defined by the **Abstract Length** field. This is the default display
  template of the Asset Publisher.
- Table: This display template displays the content in an HTML table which can
  be styled by a theme developer.
- Title List: This display template displays the content's title as defined by
  the user who entered it.
- Full Content: This display template displays the entire content of the entry.

    There's also a Rich Summary display template that belongs to the global
    scope. This template provides a summary view of each asset along with a
    *Read More* link to the article's full content.

**Abstract Length**: Here, you can select the number of characters to display
for abstracts. The default is `200`.

**Asset Link Behavior:** The default value is *Show Full Content*. With this
value selected, when the link to an asset is clicked, the full asset is
displayed in the current Asset Publisher. (There's also a *View in Context* link
that shows the article in the Wiki page's Wiki portlet.) If the value *View in a
Context* is selected, clicking on an asset causes that asset to be displayed in
the portlet to which the asset belongs. For example, a blog entry would be
displayed in the Blogs portlet where it was created. Likewise, a forum post
would be displayed in the Message Boards portlet where it was created.
Similarly, a generic web content instance would be displayed in the Asset
Publisher of its configured display page. See the section below on display pages
for more information.

---

 ![Tip](../../images/01-tip.png) **Tip:** When the Asset Publisher displays web
 content instances that have an associated small image, the small image becomes a
 link to the full instance.  To use this feature, add or edit a web content
 instance that the Asset Publisher should display. Before clicking *Publish*,
 click on *Abstracts*, flag *Small Image*, and upload an image.  Then click
 *Publish*. Once your web content instance appears in the Asset Publisher's list,
 clicking the small image takes you to the full instance.

---

**Number of Items to Display**: Here, you can select the maximum number of
assets that can be displayed by the Asset Publisher. However, if pagination is
enabled, there's no limit to the number of of assets that the Asset Publisher
can display. So with pagination enabled, this number represents the maximum
number of assets that can be displayed per page.

**Pagination Type**: This can be set to *None*, *Simple*, or *Regular*. With
pagination set to *None*, the Asset Publisher displays at most the number of
assets specified in the **Number of Items to Display** property. Setting the
pagination type to *Simple* adds *Previous* and *Next* buttons that enable the
user to browse through "pages" of assets in the Asset Publisher. Setting the
pagination type to *Regular* adds more options and information including *First*
and *Last* buttons, a dropdown selector for pages, the number of items per page,
and the total number of results (assets being displayed).

<!-- TODO 
**Show Metadata Descriptions**:
-->

**Show Available Locales:** Since content can be localized, you can have
different versions of it based on locale. Enabling this option shows the locales
available, enabling users to view the content in their language of choice. 

**Set as the Default Asset Publisher for This Page**: The Asset Publisher
portlet is an instanceable portlet; multiple Asset Publishers can be added to a
page and each has an independent configuration. The default Asset Publisher for
a page is the one used to display any web content associated with the page.

**Enable Conversion To:** If you have enabled Liferay Portal's
OpenOffice/LibreOffice integration, you can allow your users to convert the
content to one of several formats:

- DOC
- ODT
- PDF
- RTF
- SXW
- TXT

    Please refer to the User Guide's section on [Liferay Server
    Administration](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/server-administration-liferay-portal-6-2-user-guide-17-en)
    for information on setting up Liferay's OpenOffice/LibreOffice document
    conversion functionality.

**Enable ...**: The Asset Publisher's Display Settings allow you to
enable/disable the following options for displayed assets:

- Print
- Flags
- Related assets
- Ratings
- Comments
- Comment ratings
- Social bookmarks

    Enabling the Print option adds a *Print* link to the full view of an asset
    displayed in the Asset Publisher. Clicking *Print* opens a new browser
    window with a print view of the asset. Enabling flags, related assets,
    ratings, comments, comment ratings, or social bookmarks add links to the
    corresponding social features to the view full of the asset in the Asset
    Publisher.

    ---

     ![Tip](../../images/01-tip.png) **Tip:** An alternate way to add comments
     and ratings to a page is through the *Page Comments* and *Page Ratings*
     portlets.  Just add the portlets in the appropriate location near the asset
     you'd like to have feedback for. Note that starting in Liferay 6.2, these
     portlets can no longer be exported.
     
    ---

<!-- TODO
**Display Style**:

**Display Position**:
-->

**Show Metadata:** Allows you to select various metadata types to be displayed
(see below). For example, you can select tags and categories for display. Upon
saving your configuration, the Asset Publisher displays tags and categories for
each displayed asset. Then users can click on the tags and categories to
manually filter the displayed assets.

![Figure 6.15: You can configure the Asset Publisher to display various kinds of metadata about the displayed assets.](../../images/available-metadata-fields.png)

The Display Settings section of the Asset Publisher has numerous options to help
you configure how your content selections are displayed to your users. Even
though there are many choices, it's easy to go through the options and quickly
adjust the ones that apply to your situation. You'll want to use the Asset
Publisher to query for different kinds of assets in the portal that contain
relevant information for your users.

### Configuring Asset Publisher Subscriptions [](id=configuring-asset-publisher-subscriptio-liferay-portal-6-2-user-guide-06-en)

The Asset Publisher portlet supports two kinds of subscriptions: RSS
subscriptions and email subscriptions. To enable subscriptions, open the Asset
Publisher's configuration window (click on the gear icon and select
*Configuration*). In the configuration window, open the Subscriptions tab of the
Setup tab. There are two options:

**Enable RSS Subscription**

**Enable Email Subscription**

Enabling RSS subscription creates an RSS feed containing links to all of the
assets that the Asset Publisher is configured to display. A link to this RSS
feed appears in at the bottom of the Asset Publisher portlet.

![Figure 6.16: When RSS subscriptions have been enabled for an Asset Publisher portlet, a link to the Asset Publisher's RSS feed appears. Users can subscribe to the Asset Publisher's RSS feed using their preferred RSS reader.](../../images/asset-publisher-rss.png)

Enabling email subscription adds a *Subscribe* link to the Asset Publisher.
Users wishing to be notified of newly published assets can click on this link to
be added to the subscription list. Liferay periodically checks for new assets
and sends emails to subscribed users informing them about the new assets. By
default, Liferay performs this check every twenty-four hours but this can be
customized by adding the following property to your `portal-ext.properties` file
and changing the number:

    asset.publisher.check.interval=24

Also by default, Liferay limits the number of assets that are retrieved from the
database during this check to twenty. If you're interested in optimizing
Liferay's performance, you can lower this limit. If you're not concerned about
performance, you can remove this limit entirely. To customize the number of
assets about which Liferay notifies subscribed users, add the following property
to your `portal-ext.properties` file and change the number:

    asset.publisher.dynamic.subscription.limit=20

Next, we'll look at Display Pages, an addition to the asset framework introduced
by Liferay 6.1.

### Content Display Pages [](id=content-display-pages-liferay-portal-6-2-user-guide-06-en)

If you've been using Liferay for a while, you might have noticed something about
how Liferay handles web content--content is never tied directly to a page. While
this can be useful (because it means that you don't have to recreate content if
you want to display the same thing on multiple pages), it also means that you
don't have a static URL for any web content, which is bad for search engine
optimization.

As an improvement, Liferay introduced the concept of *display pages* and
*canonical URLs*. Each web content entry on the portal has a canonical URL,
which is the official location of the content that is referenced any time the
content is displayed. A display page can be any page with an asset publisher
configured to display any content associated with the page. When adding or
editing web content, you can select a display page, but only pages with
a configured asset publisher are available for selection.

To create a display page, you can create a page yourself, add an Asset Publisher
portlet and configure it yourself. Alternatively, you can use the *Content
Display Page* page template included with Liferay. If you're creating a Display
Page manually, once you've added an Asset Publisher portlet to the page, open
its configuration window. Then check the *Set as the Default Asset Publisher for
This Page* box. Also, for its display settings, set the Display Style to
*Abstracts* and the Asset Link Behavior to *View in Context*.

---

 ![Note](../../images/01-tip.png) **Note:** Web content linked in the Asset
 Publisher can be viewed by clicking their asset links. With the *View in
Context* behavior checked, the link displays the Web Content in its
configured display page. If the web content does not have a configured display
page, it is displayed in the web content display portlet to which the
asset belongs.

---

You may now be thinking, "Wait, you just told me that each Web Content item has
its own URL, and that this is somehow related to pages where we display a whole
bunch of content on the same page?" That's right. Just watch--create a display
page called *My Web Content Display Page* somewhere on your portal, using the
*Content Display Page* template. Now, on a different page, add a Web Content
Display portlet. Click the *Add Web Content* button, enter a title and some
content, click on *Display Page* at the right, and select the Display Page you
just created. Then click *Publish*.

![Figure 6.17: You can select a display page for a web content instance when creating or editing one.](../../images/04-web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, click the *Read
More* link to display the full content. Notice that the canonical URL for
content appears in your browser's address bar. If you create your own custom
display page, any additional portlets that you place on the page are displayed
along with the content when you access it via the canonical URL. If you used the
*Content Display Page* page template for your display page, it not only features
a configured Asset Publisher portlet but also a Tags Navigation, a Categories
Navigation, and a Search portlet. These tools help users to quickly identify
relevant content.

![Figure 6.18: The Canonical URL](../../images/04-web-content-canonical-url.png)

Next, let's learn about another new feature introduced by Liferay 6.1.

## Defining Content Relationships [](id=defining-content-relationships-liferay-portal-6-2-user-guide-06-en)

Related Assets was a feature introduced in Liferay 6.1 that enables you to
connect an asset to other assets within the same site or to global assets, even
if they don't share any tags and aren't in the same category. We've already seen
that you can show related assets within the display for a specific asset, and
with the Related Assets portlet you can show links to any assets which are
related to content displayed on that page.

The Related Assets portlet is based on the Asset Publisher and possesses
essentially the same interface with one key difference. The Asset publisher
displays any content that meets the criteria selected in the portlet
configuration. The Related Assets portlet only displays content that meets the
criteria, and also is listed as a related asset for a piece of content that is
currently published on the page where it is placed. Let's take a look at the
Related Assets portlet.

As a prerequisite for the Related Assets portlet to display related assets, you
have to configure it to show the content you want displayed. To do this, go to
the Asset Publisher portlet and select the *gear* icon in the upper right
corner of the portlet. Under the *Setup* tab, set type of asset(s) to display
using the *Asset Type* menu. The default value is set to *Any*. You can narrow
the scope of the portlet to display any single category of asset type or select
multiple assets from the menu.

Filter options let you set minimum requirements for displaying assets by their
categories, tags, and custom fields. Ordering and Grouping allows you to
organize assets using the same criteria. Display settings allow you to customize
how assets are shown in the portlet. They can be listed by title, in a table,
by abstract or full content. You can convert assets to different document types
like ODT, PDF, and RTF. You can choose to show various metadata fields such as
author, modification date, tags, and view count. You can even enable RSS
subscriptions and customize their display settings.

When you are finished setting the Source and Filter options, click *Save*. But
hold on a minute. You saw the message that says, `You have successfully updated
the setup`, but there still aren't any assets displayed in the related assets
portlet. Why? You cannot see any related assets until you select an
asset in the Asset Publisher.

![Figure 6.19: Select an asset in the Asset Publisher to see its related assets displayed in the Related Assets portlet. In the screenshot, we renamed the three Related Assets portlets on the left and updated their configurations.](../../images/related-assets-portlet-after.png)

Once you select an asset, its related assets are displayed in the Related Assets
portlet, as in the image above.

## Summary [](id=summary-liferay-portal-6-2-user-guide-06-en-0)

In this chapter, we explored Liferay's asset framework. Any type of content in
Liferay is considered an asset and can utilize the features provided by the
asset framework: tags, categories, comments, ratings, and relationships. We
examined the Asset Publisher portlet and looked at the many configuration
options for choosing what kinds of assets to display and how to display them. We
saw that the Asset Publisher portlet is designed to integrate with the Tags
Navigation and Categories navigation portlets to allow users to browse content
more easily. We also learned about the Display Page attribute of web content,
the Content Display Page page template, and canonical URLs for assets. Assets
can have display page associated with them so that the full view of the asset is
displayed on the display page. The display page of an asset is used in the
asset's canonical URL.
