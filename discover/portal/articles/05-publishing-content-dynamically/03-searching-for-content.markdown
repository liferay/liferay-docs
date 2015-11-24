# Searching for Content

To stay organized, many people used to use a paper-based planner. It had
different sections for various areas of their lives, which included daily items
like tasks, notes, a spiritual section, and agenda pages that kept track of
things the person needed to go over with specific people. A planning section had
tabs for projects, family, future items, and reference. 

Of course, since this was paper-based, it had its limitations. It was kind of
hard to find stuff. Did you put the note about buying your daughter that special
birthday gift in the notes section or in the family section? Or maybe it was on
your *While Out* list, so you'd remember to buy it before her birthday? 

Liferay content can be like this. That important information you remember
seeing--was it in a wiki article, a message boards post, or web content? Did you
remember to tag it? If you don't have this kind of information, browsing to the
content you're looking for could be difficult. Thankfully, Liferay includes a
powerful, faceted search function. You can access this function through the
Search application, which lets you drill down through the different types of
content, tags, and categories to refine your search. Next, you'll look at the
search features Liferay provides for specific kinds of content and then examine
how to use Liferay's faceted search.

## Searching for Specific Types of Content

Liferay provides several applications that let users and administrators search
for content. First, there's the Search application, which can be placed on a
page to let users search for web content instances. The Search application only
searches for web content. However, the Blogs, Wiki, Message Boards, and
Documents and Media applications all provide search bars that let users search
among the specific types of assets with which these applications allow users to
interact. Note that all of these applications are accessible from Site
Administration in the Product Menu. They can also be placed on portal pages for
end-users to use. The Web Content application in the Product Menu's Site
Administration section also has a built-in search bar, which allows for web
content search. Because this application is only designed for administrative
use (and is not available to add to a page), the Search application is available
to allow non-adminstrative users to search for web content.

<!-- At current time, Wiki did not have search bar in Site Admin. This is most
likely due to it not being "Lexiconified" yet, but double check this before
final publishing. -Cody -->

+$$$

**Note:** The Web Content Search portlet is deprecated in Liferay 6.2 and will
be removed in Liferay 7.0. The Web Content Search portlet will be migrated to
the Search portlet, which will be configured to only search for web content. 

$$$

<!-- The Web Content Search portlet is still available in current builds of
Liferay 7.0. I've contacted Julio to find out why it's still there and if it's
still planned for removal. He indicated they did not have time to remove it, but
are planning to have it disabled by default. The plan will be to completely
remove it for version 7.1. Once plan is finalized, this section will need to be
updated. A ticket (LRDOCS-) has been filed to edit this content, when finalized.
-Cody -->

In order for content to be searchable, it must first be indexed. Liferay uses
Lucene for indexing and searching for content. Please refer to the Lucene
section of the portal properties file if you'd like to customize Liferay's
indexing and search behavior. By default, Liferay indexes blog posts, wiki
articles, message board posts, Documents and Media files' descriptions, and web
content instances. If a Documents and Media file is a text file, the file's
content is indexed as well. Comments on blog posts, wiki articles, and Documents
and Media files and text file attachments to wiki articles and message board
posts are also indexed. Liferay automatically indexes content as it's added to
the portal.

If you'd like to search among assets of a specific type and you'd like to
include comments and attachments in your search, use the search bar of the
appropriate application. For example, if you'd like to search for the term
*Lunar Resort* among wiki articles, enter the term *Lunar Resort* into the Wiki
application's search bar. 

![Figure 1: When using the Wiki application's search bar to search for *Lunar Resort*, wiki articles, comments, and attachments containing the words *Lunar* or *Resort* are returned.](../../images/wiki-search.png)

Wiki articles, comments, and text file attachments containing the word *Lunar*
or *Resort* are returned as search results. The search results clearly show
whether an individual search result is a wiki article, a comment, or an
attachment. In the next section, you'll see how the Search application can be
used to search through web content on Liferay. Its faceted search
feature is a powerful tool that allows users to include or not include specific
types of assets in a search. However, the Search portlet does not return
comments or attachments as search results.

## Searching for Localized Web Content

Liferay Portal supports localizing web content as well as searching for
localized web content. Recall that to add a translation of a web content
article, you need to edit the article, choose a language, and then add the
translation and click *Save*. Here's how searching for localized content works
in Liferay:

- Web content articles whose default language matches the portal's default
  language are searched.
- Web content articles whose default language is `en_US` (American English) are
  searched, regardless of Liferay's default language.
- If the language of a translation of an article matches Liferay's default
  language, the article is searched.
- Any article with an `en_US` translation can be searched regardless of
  Liferay's default language.
- A user's default language does not affect search results.

+$$$

**Note:** The latest approved version of a web content article is always the
version that is searched. To change the current approved version of a web
content article, edit the article, click on the *View History* button, click on
the *Actions* button next to a version of the article, then click on *Expire*.

$$$

Next you'll learn about searching content in Liferay using facets.

## Searching for Content Using Facets

To get started using faceted search, drop the Search application on a page and
search for something. You'll see a page with results on the right and a
collection of *facets* on the left. 

![Figure 2: The first set of facets is content types. You can drill down to specific types of content that contain the search terms you entered.](../../images/faceted-search.png)

Facets allow users of the Search application to select criteria with which to
filter search results. A facet is a combination of the information about a
specific indexed field, its terms, and their frequency. Facets are typically
named by the field in question. From the Search application's Configuration
window, administrators can configure the facets available to users for filtering
search results. The default facets include the following:

- Site
- Asset type
- Asset tag
- Asset category
- Folder
- User
- Modified time range

For example, after searching for a certain term in the Search application,
clicking on a specific site filters the search results to only display assets
within the specified site. Clicking on a specific user filters the search
results to only display assets added by the specified user. The frequency with
which the term was found for each facet is listed in parentheses after the
facet. It may jog your memory to see that the term you searched for appears in a
blog entry, and that may be all you need to find what you were looking for. If,
however, your memory is more foggy than that, or you're searching for something
you're not sure is actually there, then the asset tags or asset categories
facets may be more helpful to you.

![Figure 3: Asset tag facets let you see how many assets contain the terms for which you searched *and* contain certain tags. Click on a specific tag to narrow down the list of search results to those to which the tag has been applied.](../../images/faceted-search-tags.png)

For example, if you searched for a wireless phone, you might be more interested
in content that has your search terms in it *and* has also been tagged by users.
One or more tags might help you to find what you're looking for. Note that the
number of tags that appear is configurable: by default it's 10, but there could
be many more as a result of a particular search. You'll look at the configuration
options later in this section. For now, you'll learn how to drill down to narrow
search results. 

To drill down into the search, click on a facet to add it to the filter list.
The results to the right are refined by the selected facets. 

![Figure 4: Drilling down creates a list of what you selected at the top of the screen.](../../images/faceted-search-drill-down-1.png)

In the figure above, the search is refined to only show wiki pages. The
*Liferay* site is also selected, to refine the search. The facets that are
selected, *Liferay* and *Wiki Page*, appear in a list at the top, and there's a
blue "X" next to them that lets you remove it from the filter as you work to
refile your search. Suppose that the two facets selected weren't enough to
filter the search into a small enough list to sort through. In this case, you
could further refine the search by selecting another facet, as below. 

![Figure 5: Selecting another facet further refines the search.](../../images/faceted-search-drill-down-2.png)

Now the *bear* category is also selected, which reduces the list of search
hits on the right. In this way, you can interactively tweak the search results
to narrow them down, making it easier to find that proverbial needle within the
haystack. 

<!-- left off here -->

### Asset Types

Searching can only be done on assets. As has already been described in this
chapter, just about any entity in the portal is an asset and can be indexed and
searched. Under the hood, this means that these entities use Liferay's Asset API
and have an Indexer class defined. 

Developers can create custom searchable assets within the portal. This is
described in the
[Asset Framework](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/asset-framework-liferay-portal-6-2-dev-guide-06--1).
section of the Developer's Guide. For this reason, you can have additional asset
types defined in your portal beyond the ones that Liferay ships with by default.
If this is the case, you might want to tweak the `frequencyThreshold` and the
`maxTerms` settings to increase the number of asset types displayed past the
default of 10. This is covered in the section below on search options.

<!-- Change Asset Framework link above to LDN's Asset Framework link, when
available. Can follow at LRDOCS-1115. -Cody -->

Note that although users can be appear as search results in the Search portlet,
they behave differently than other assets. For example, you cannot select a user
as a related asset. Also, users cannot be displayed by the Asset Publisher
portlet. Usually, when you click on an asset from a list of results in the
Search portlet, the selected asset is displayed in an Asset Publisher portlet.
If you click on a user, however, you're taken to the user's profile page. If
public personal pages have been disabled, clicking on a user from list of search
results does nothing. To disable public personal pages, you can set the
following portal properties:

    layout.user.public.layouts.enabled=false
    layout.user.public.layouts.auto.create=false
    layout.user.public.layouts.power.user.required=true

The technical reason for this behavior is that although an `AssetEntry` object
is created for each `User` object, the `AssetEntries` objects of users are
created as non-visible assets. Instead of displaying users in the Asset
Publisher, Liferay uses the `displayURL` of each user as the link from a list of
search results.

### Asset Tags

If tags have been applied to any asset that appears in the result set, it may
be displayed in the Asset Tag facet. Tags are handled in a similar way to how
asset types are handled: not all tags may appear. There may be many more than
the 10 tags listed, but the default configuration for this facet is to show the
top 10 most frequent terms. As with asset types, this can be modified by
setting the `max terms` property.

### Asset Categories

If categories have been applied to any asset that appears in the result set,
they may be displayed in the Asset Categories facet. Asset categories work just
like asset tags. As with asset tags, you can modify the number of categories
listed in the search by setting the `max terms` property.

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

## Setting Options for Detailed Search Results

As with Liferay's other portlets, you can configure the Search portlet via the
configuration screen, which looks like the below illustration. 

<!--[Figure 6.12: Basic search configuration is pretty straightforward.](../../images/05-faceted-search-configuration.png)-->

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

+$$$

**Note:** You can identify available indexed fields by enabling the Search
portlet's *Display Results in Document Form* configuration setting and then
expanding individual results by clicking the *+* symbol to the left of their
titles. 

$$$

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
