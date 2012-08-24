# Leveraging the Asset Framework [](id=lp-6-1-ugen05-leveraging-the-asset-framework-0)

Any type of content in Liferay is considered an asset. In chapters 2 and 3, we
already examined Liferay's most common type of asset: web content. Other types
of assets include blog posts, wiki articles, message board posts, bookmarks, and
documents. It's possible for developers to define custom asset types that
utilize Liferay's asset framework. Originally, the asset framework was created
to provide a mechanism for adding tags to blog entries, wiki articles, and web
content without reimplement the same functionality multiple times. The asset
framework has been greatly extended since then and it now supports tags,
categories, comments, ratings, and asset relationships.

This chapter covers the following topics:

- Tagging and categorizing content
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

## Tagging and Categorizing Content [](id=lp-6-1-ugen03-tags-and-categories-0)

Tags and categories are two important tools you can use to help organize
information on your portal and make it easier for your users to find the content
they're looking for through search or navigation. Tagging and categorizing web
content is easy. You can do it at the bottom of the same form you use to add
content. If you open the *Categorization* section of the form, you'll be
presented with an interface for adding tags and categories.

![Figure 5.1: Tagging and categorizing content can be done at the same time you
create it.](../../images/04-web-content-categorization.png)

The Control Panel contains an interface for managing tags and categories for
each site in the portal. This interface can be used to manage all your tags and
categories in one place. It is important that you both tag and categorize your
content when you enter it. Let's take a closer look at tags and categories.

### Tags [](id=ta-3)

Tags are an important tool that you can use to help organize information on your
portal and make it easier for your users to find content that they're looking
for. Tags are words or phrases that you can attach to any content on the
website. Tagging content will make your search results more accurate, and enable
you to use tools like the Asset Publisher to display content in an organized
fashion on a web page. There are two ways to create tags: you can do it through
the administrative console in the Control Panel, or on the fly as content is
created.

![Figure 5.2: The Add Tag Dialog](../../images/05-add-tag.png)

To create tags in the Control Panel, select the site that you want to create
tags for, and select *Tags*. From this screen, you will be able to view any
existing tags and make new ones. To create a new tag, simply click *Add Tag*.
You'll then be asked for the name of the tag, and you'll have the ability to set
permissions for viewing or managing the tag. You can also add properties to a
tag. Properties basically act like tags for your tags. Structurally, properties
are key-value pairs associated with specific tags that provide information about
your tags. You can edit existing tags from the *Tags* window of on the Control
Panel. You can change the tag name, change the tag's permissions, delete the
tag, or add properties.

Tags are not the only portal-wide mechanism for describing content: you can also
use categories.

### Categories [](id=categori-3)

Categories are similar in concept to tags, but are designed for use by
administrators, not regular users. Hierarchies of categories can be created, and
categories can be grouped together in *vocabularies*. While tags represent an ad
hoc method for users to group content together, categories exist to allow
administrators to organize content in a more official, hierarchical structure.
You can think of tags like the index of a book and categories like its table of
contents. Both serve the same purpose: to help the user find the information he
or she seeks.

Adding vocabularies and categories is similar to adding tags. Once you've
selected the site you want to work on, select *Categories* from the content
section of the Control Panel, and you will be presented with the categories
administration page.

![Figure 5.3: Categories Administration Page](../../images/05-categories.png)

Clicking on a vocabulary on the left displays any categories that have been
created under that vocabulary. You can create new vocabularies simply by
clicking *Add Vocabulary* and providing a name for it. You can create categories
in a similar fashion by choosing a vocabulary on the left, and then selecting
*Add Category*. Like tags, you can also provide properties for categories. Once
you have created some vocabularies and categories, you can take advantage of the
full capabilities of categories by creating a nested hierarchy of categories. To
nest categories, select what you want to be the parent category, then drag any
category that you want to become a child category onto it. You will see a plus
sign appear next to the name of the category you are dragging if you can add it
to the selected parent category; if you see a red *x* that means that you cannot
add that category as a subcategory of parent category that you have selected.

Once you have created a hierarchy of categories, your content creators will have
them available to apply to content that they create. Navigate to the Web Content
page of the Control Panel and click *Add Content*. Click the Categorization link
from the right-side menu and click *Select* on the vocabulary use would like to
use. A dialog box will appear with your categories. Select any relevant
categories by checking the box next to them, and they will be applied to the
content.

It's important to use tags and categories with all your content, so that content
is easier for users to find. Let's look at one of the ways users will make use
of tags and categories: searching for content. 

## Using Faceted Search

To stay organized, I (RS) used to use a paper-based planner. It had various
sections for various areas of my life. Its initial incarnation came from a
commercial company, but over the years I tweaked it into something that worked
for me. This final version (before I went digital) had different tabs for
different areas of my life that I wanted to keep track of: daily items like
tasks, notes, a spiritual section, and agenda pages that kept track of things I
needed to go over with specific people. A Planning section had tabs for
projects, family, future items, and reference. 

Of course, since this was paper-based, it had its limitations. It was kind of
hard to find stuff. Did I put the note I'd written about that new toy my
daughter wanted in the Notes section or in the Family section? Or maybe it was
on my *While Out* list, so I would remember to buy it before her birthday? 

Liferay content can be like this. That important information you remember
seeing--was it in a wiki article, a message boards post, or web content? Did you
remember to tag it? If you don't have this kind of information, browsing to the
content you're looking for could be difficult. Thankfully, Liferay includes a
powerful, faceted search function, which means you can drill down through the
different types of content, tags, and categories to refine your search and find
what you want. Let's see how to use it. 

### Searching for Portal Content

To get started, drop the Search portlet on a page and search for something.
You'll see a page with results on the right and a collection of *facets* on the
left. 

![Figure 5.x: The first set of facets is content types. You can drill down to
specific types of content that contain the search terms you
entered.](../../images/faceted-search-1.png)

A facet is a combination of the information about a specific indexed field, its
terms, and their frequency. Facets are typically named by the field in question.
The default facets are asset types (pictured above), asset tags, asset
categories, and modified time range. 

The frequency in which the term was found for each facet is listed in
parentheses after the facet. It may jog your memory to see that the term you
searched for appears in a blog entry, and that may be all you need to find what
you were looking for. If, however, your memory is more foggy than that, or
you're searching for something you're not sure is actually there, then the asset
tags or asset categories facets may be more helpful to you. 

![Figure 5.x: Asset tag facets provide you with more information about content
that contains the terms for which you searched.](../../images/faceted-search-2.png)

In this case, if you searched for a wireless phone, you may be more interested
in content that has your search terms in it and has also been tagged by users.
One or more of the tags may help you to find what you're looking for. Note that
the number of tags that appear is configurable: by default it's 10, but there
could be many more as a result of a particular search. We'll look at the
configuration options later in the chapter. For now, let's see how drilling down
works. 

### Drilling Down to the Data You Want

To drill down into the search, click a facet to add it to the filter list, and
the results to the right are refined by the facet you selected. 

![Figure 5.x: Drilling down creates a list of what you selected at the top of
the screen.](../../images/05-faceted-search-drill-down-1.png)

Here we can see that we've selected one of the tags, *liferay*, to further
refine the search. The tag appears in a list at the top, and there's a red X
next to it that lets us remove it from our filter as we work to increase the
relevancy of our search. But maybe selecting only the tag isn't enough to filter
our search into something small enough to sort through. In this case, we can
further refine the search by selecting another facet, as below. 

![Figure 5.x: Selecting another facet further refines the
search.](../../images/05-faceted-search-drill-down-2.png)

Now we've selected web content, which is one particular content type within
Liferay, and the list of potential hits on our search terms has been
dramatically reduced. In this way, you can interactively tweak the search
results to narrow them down, making it easier to find that proverbial needle
within the haystack. 

#### Asset Types

Searching can only be done on assets. As has already been described in this
chapter, just about any entity in the portal is an asset and can be indexed and
searched. Under the hood, this means that these entities use Liferay's Asset API
and have an Indexer defined. 

Developers can create custom searchable assets within the portal. This is
described in the [Developer's
Guide](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/-asset-framewo-1).
For this reason, you may have additional asset types defined in your portal
beyond the ones that Liferay ships with by default. If this is the case, you may
wish to tweak the `frequency threshold` and the `max terms` settings to increase
the number of asset types displayed past the default of 10. This is covered in
the section below on search options. 

#### Asset Tags

If tags have been applied to any asset that appears in the result set, it may
be displayed in the Asset Tag facet. Tags are handled in a similar way to how
asset types are handled: not all tags may appear. There may be many more than
the 10 tags listed, but the default configuration for this facet is to show the
top 10 most frequent terms. As with asset types, this can be modified by
setting `max terms` property.

#### Asset Categories

If categories have been applied to any asset that appears in the result set,
they may be displayed in the Asset Categories facet. Yadda, yadda, yadda, same
thing as the two sections above. That last sentence was written to check if
you're still reading. 

Let's move on to advanced searching. 

### Advanced Searching

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

### Setting Search Options

As with Liferay's other portlets, you can configure the Search portlet via the
configuration screen, which looks like the below illustration. 

![Figure 5.x: Basic search configuration is pretty straightforward.
](../../images/05-faceted-search-configuration.png)

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

These are the basic options, but surely you didn't miss the fact that there are
also advanced options. 

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

**“className”:** This field must contain a string value which is the FQCN (fully
qualified class name) of a java implementation class implementing the Facet
interface. Liferay provides the following implementations by default:

	com.liferay.portal.kernel.search.facet.AssetEntriesFacet
	com.liferay.portal.kernel.search.facet.ModifiedFacet
	com.liferay.portal.kernel.search.facet.MultiValueFacet
	com.liferay.portal.kernel.search.facet.RangeFacet
	com.liferay.portal.kernel.search.facet.ScopeFacet
	com.liferay.portal.kernel.search.facet.SimpleFacet

**“data”:** This field takes an arbitrary JSON object (a.k.a. {}) for use by a
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

For example, `"displayStyle": "asset_tags"`maps to the JSP 

	/html/portlet/search/facets/asset_tags.jsp

Armed with this knowledge a crafty developer could create custom display styles
by deploying custom (new or overriding) JSPs using a JSP hook. See the
*Developer's Guide* or *Liferay in Action* for more information on hook plugins. 

**"fieldName":** This field takes a string value and defines the indexed field on
which the facet operates.

For example, `"fieldName": "entryClassName"` indicates that the specified facet
implementation operates on the `entryClassName` indexed field.

**Note:** You can identify available indexed fields by enabling the Search
portlet’s *Display Results in Document Form* configuration setting and then
expanding individual results by clicking the [+] to the left of their titles. 

**"label":** This field takes a string value and represents the language key that
is used for localizing the title of the facet when it's rendered.

**"order":** This field takes a string value. There are two possible values:

   `OrderValueAsc`: This tells the facet to sort it’s results by the term values,
   in ascending order.

   `OrderHitsDesc`: This tells the facet to sort it’s results by the term
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

Configuring search using a JSON object is a bit unusual, but as you can see, it's
not as hard as it looks initially. 

### Summary

Search is a powerful component of Liferay Portal's asset framework. The
proclivity of assets means that there is an extensible, robust, and configurable
search mechanism throughout the portal that allows administrators to optimize the search
experience of their users. Users also get an easy to use search interface that
makes use of the tags and categories that they themselves apply to various
pieces of content, regardless of the type of content. This makes Liferay's
search truly "for the people." 

Power users can learn an extended search syntax that lets them craft very
specific searches. These searches can be used on large installations with lots
of data to find the proverbial needle in the proverbial haystack. Administrators
can tune the configuration of search portlets so that they are optimized for the
contents of their communities. 

Next, we'll look at how the Asset Publisher portlet makes even more extensive
use of Liferay's asset framework to bring relevant content to users.

## Using the Asset Publisher [](id=lp-6-1-ugen03-using-the-asset-publisher-portlet-0)

As we create web content, it's important to keep in mind that to Liferay, the
pieces of content are assets, just like message board entries and blog posts.
This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of
assets such as images, documents, blogs, and of course, web content. This helps
in creating a more dynamic web site: you can place user-created wiki entries,
blog posts or message board messages in context with your content. Let's look at
some of its features.

#### Querying for Content [](id=lp-6-1-ugen03-querying-for-content-0)

The Asset Publisher portlet is a highly configurable application that lets you
query for mixed types of content on the fly. By giving you the ability to
control what and how content is displayed from one location, the Asset Publisher
helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Configuration* link in the
portlet's menu (the wrench icon).

The ability to configure how content is displayed and selected by your users
further demonstrates the flexibility of the Asset Publisher. You get to choose
how content is displayed. You can select it manually for display in a similar
way to the Web Content Display portlet or you can set up predefined queries and
filters and let the portal select the content for you, based on its type or its
tags and categories.

Let's first look at how we might select content manually. You'll see that it's
very similar to the Web Content Display portlet.

##### Selecting assets manually [](id=lp-6-1-ugen03-selecting-assets-manually-0)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell
the Asset Publisher that you want to select content manually. You can select
what you want to be published within the portlet, or you can create new content
from within the Asset Publisher.

![Figure 5.4: Selecting assets manually is very similar to the Web Content
Display portlet, except you have many other content types to choose
from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the
content right where you are. You can create blogs, bookmarks, calendar entries,
documents, images, and of course, web content. Anything you create here is added
to the list below of assets that are displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can
pick from existing content in the portal that either you or your users have
created. Has someone written an excellent wiki page that you want to highlight?
Select it here, and it will be displayed.

The Asset Publisher enables you to mix and match different content types in the
same interface. Once you have your content selected, you can move on to the
display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select
content dynamically.

##### Selecting assets dynamically
[](id=lp-6-1-ugen03-selecting-assets-dynamically-0)

The Asset Publisher's default behavior is to select assets dynamically according
to rules that you give it. These rules can be stacked on top of each other so
that they compliment each other to create a nice, refined query for your
content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be
selected.

**Asset Type:** Choose whether you'll display any asset or only assets of a
specific type, such as only web content, only wiki entries, or any combinations
of multiple types.

![Figure 5.5: You can filter by tags and categories, and you can set up as many
filter rules as you
need.](../../images/04-web-content-asset-publisher-filter.png)

**Filter Rules:** Add as many filters on tags or categories as you like. You can
choose whether the content contains or does not contain any or all categories or
tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you
can then decide how the content will be displayed.

#### Ordering and Grouping [](id=lp-6-1-ugen03-ordering-and-grouping-0)

You can display the content returned by the filters above in order by title,
create date, modified date, view count and more in ascending or descending
order. For instance, you may have a series of "How To" articles that you want
displayed in descending order based on whether the article was tagged with the
*hammer* tag. Or, you may want a series of video captures to display in
ascending order based on a category called *birds*. You can also group by
*Asset*, *Type* or *Vocabularies*. Vocabularies are groups of categories defined
by administrators in the *Categories* section of the Control Panel. 

In the *Ordering and Grouping* section of the Asset Publisher, you have great
control over how content is ordered and grouped in the list, but this is only
one aspect of how your content will be displayed. You can refine the display
through many other display settings.

#### Display Settings [](id=lp-6-1-ugen03-display-settings-0)

The Display Settings section gives you precise control over the display of your
assets. There are a multitude of options available to configure how you want
your content to appear. You can configure the style, length of abstracts,
behavior of the asset link, maximum items to display, pagination type and file
conversions. Additionally, you can enable printing, flags, ratings, comments and
comment ratings, and these work the same way they do in the Web Content Display
portlet.

##### Display Style [](id=lp-6-1-ugen03-display-style-0)

**Abstracts:** Shows the first 200-500 characters of the content, defined by the
**Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme
developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

##### Other Settings [](id=lp-6-1-ugen03-other-settings-0)

**Asset Link Behavior:** The default value is *Show Full Content*. With this
value selected, when the link to an asset is clicked, the full asset is
displayed in the current Asset Publisher. If the value *View in a Specific
Portlet* is selected, clicking on an asset causes that asset to be displayed in
the portlet to which the asset belongs. For example, a blog entry would be
displayed in the Blogs portlet where it was created. Likewise, a forum post
would be displayed in the Message Boards porlet where it was created. Similarly,
a generic Web Content article would be displayed in the Asset Publisher of its
configurated Display Page. See the secton below on Display Pages for more
information.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next
navigation; regular includes a way of selecting the page to which you'd like to
navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it
from the list.

**Show Available Locales:** Since content can be localized, you can have
different versions of it based on locale. This will show the locales available,
enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org
integration, you can allow your users to convert the content to one of several
formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable
print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see
below).

![Figure 5.6: Available metadata
types](../../images/available-metadata-fields.png)

**Enable RSS Subscription:** This lets users subscribe to the content via RSS
Feeds.

The Display Settings section of the Asset Publisher has numerous options to help
you configure how your content selections are displayed to your users. Even
though there are many choices, it's easy to go through the options and quickly
adjust the ones that apply to you. You'll want to use the Asset Publisher to
query for mixed assets in the portal that have relevant information for your
users.

Next, we'll look at Display Pages, an addition to the asset framework introduced
by Liferay 6.1. 

#### Display Page [](id=lp-6-1-ugen03-display-page-0)

If you've been using Liferay for a while, or you've just spent a little bit of
time with this guide, you might have noticed something about how Liferay handles
web content--content is never tied directly to a page. While this can be useful
(because it means that you don't have to recreate content if you want to display
the same thing on multiple pages), it also means that you don't have a static
URL for any web content, which is bad for search engine optimization.

As an improvement, Liferay has introduced the concept of Display Pages and
Canonical URLs. Each web content entry on the portal has a canonical URL, which
is the official location of the content that is referenced any time the content
is displayed. A Display Page can be any page with an asset publisher configured
to display any content associated with the page. When adding or editing web
content articles, you can select a Display Page, but only pages with a
configured asset publisher are available for selection. 

To create a Display Page, you can create a page yourself, add an Asset Publisher
portlet and configure it yourself. Alternatively, you can use the *Content
Display Page* page template included with Liferay. If you're creating a Display
Page manually, once you've added an Asset Publisher portlet to the page, open
its configuration window. Then check the *Set as the Default Asset Publisher for
This Page* box.

You may now be thinking, "Wait, you just told me that each Web Content item has
its own URL, and that this is somehow related to pages where we display a whole
bunch of content on the same page?" Yes. That's exactly what I said. Just
watch--create a display page called *My Web Content Display Page* somewhere on
your portal, using the *Content Display Page* template. Now, on a different
page, add a Web Content Display portlet. Click the *Add Web Content* button,
enter a title and some content, click on *Display Page* at the right, and select
the Display Page you just created. Then click *Publish*.

![Figure 5.7: Selecting a Display
Page](../../images/04-web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, you can now click
the *Read More* link to display the content. Notice that the canonical URL for
content appears in your browser's address bar. If you create your own custom
display page, any additional portlets that you place on the page are displayed
along with the content when you access it via the canonical URL. If you used the
*Content Display Page* page template for your Display page, it not only features
a configured Asset Publisher portlet but also a Tags Navigation, a Categories
Navigation, and a Search portlet. These tools help users to quickly identify
relevant content.

![Figure 5.8: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to another new featured introduced by Liferay 6.1. 

## Defining content relationships [](id=related-assets)

Related Assets is a new feature in Liferay 6.1 that enables you to connect any
number of assets within a site or across the portal, even if they don't share
any tags and aren't in the same category. We've already seen that you can show
related assets within the display for a specific asset, and with the Related
Assets portlet you can show links to any assets which are related to content
displayed on that page.

The Related Assets portlet is based on the Asset Publisher and possseses
essentially the same interface with one key difference. The Asset publisher
displays any content that meets the criteria selected in the portlet
configuration. The Related Assets portlet only displays content that meets the
criteria, and also is listed as a related asset for a piece of content that is
currently published on the page where it is placed.

## Summary [](id=lp-6-1-ugen05-summary-0)

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
