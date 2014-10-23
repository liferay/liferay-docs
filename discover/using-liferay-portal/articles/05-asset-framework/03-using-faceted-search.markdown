# Using Faceted Search [](id=using-faceted-search)

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

## Searching for Portal Content [](id=searching-for-portal-content)

To get started, drop the Search portlet on a page and search for something.
You'll see a page with results on the right and a collection of *facets* on the
left. 

![Figure 5.7: The first set of facets is content types. You can drill down to
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

![Figure 5.8: Asset tag facets provide you with more information about content
that contains the terms for which you searched.](../../images/faceted-search-2.png)

In this case, if you searched for a wireless phone, you may be more interested
in content that has your search terms in it and has also been tagged by users.
One or more of the tags may help you to find what you're looking for. Note that
the number of tags that appear is configurable: by default it's 10, but there
could be many more as a result of a particular search. We'll look at the
configuration options later in the chapter. For now, let's see how drilling down
works. 

## Drilling Down to the Data You Want [](id=drilling-down-to-the-data-you-want)

To drill down into the search, click a facet to add it to the filter list, and
the results to the right are refined by the facet you selected. 

![Figure 5.9: Drilling down creates a list of what you selected at the top of
the screen.](../../images/05-faceted-search-drill-down-1.png)

Here we can see that we've selected one of the tags, *liferay*, to further
refine the search. The tag appears in a list at the top, and there's a red X
next to it that lets us remove it from our filter as we work to increase the
relevancy of our search. But maybe selecting only the tag isn't enough to filter
our search into something small enough to sort through. In this case, we can
further refine the search by selecting another facet, as below. 

![Figure 5.10: Selecting another facet further refines the
search.](../../images/05-faceted-search-drill-down-2.png)

Now we've selected web content, which is one particular content type within
Liferay, and the list of potential hits on our search terms has been
dramatically reduced. In this way, you can interactively tweak the search
results to narrow them down, making it easier to find that proverbial needle
within the haystack. 

### Asset Types [](id=asset-types)

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

### Asset Tags [](id=asset-tags)

If tags have been applied to any asset that appears in the result set, it may
be displayed in the Asset Tag facet. Tags are handled in a similar way to how
asset types are handled: not all tags may appear. There may be many more than
the 10 tags listed, but the default configuration for this facet is to show the
top 10 most frequent terms. As with asset types, this can be modified by
setting `max terms` property.

### Asset Categories [](id=asset-categories)

If categories have been applied to any asset that appears in the result set,
they may be displayed in the Asset Categories facet. Yadda, yadda, yadda, same
thing as the two sections above. That last sentence was written to check if
you're still reading. 

Let's move on to advanced searching. 

## Advanced Searching [](id=advanced-searching)

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

## Setting Search Options [](id=setting-search-options)

As with Liferay's other portlets, you can configure the Search portlet via the
configuration screen, which looks like the below illustration. 

![Figure 5.11: Basic search configuration is pretty straightforward.
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
expanding individual results by clicking the [+] to the left of their titles. 

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

## Summary [](id=summary)

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
