# Searching for Content [](id=searching-for-content)

To stay organized, many people used to use a paper-based planner. It had
different sections for various areas of their lives, which included daily items
like tasks, notes, a spiritual section, and agenda pages that kept track of
things the person needed to go over with specific people. A planning section had
tabs for projects, family, future items, and reference. 

Of course, since this was paper-based, it had its limitations. It was kind of
hard to find stuff. Did you put the note about buying your daughter that special
birthday gift in the notes section or in the family section? Or maybe it was on
your *While Out* list, so you'd remember to buy it before her birthday? 

@product@ content can be like this. That important information you remember
seeing--was it in a wiki article, a message boards post, or web content? Did you
remember to tag it? If you don't have this kind of information, browsing to the
content you're looking for could be difficult. Thankfully, @product@ includes a
powerful, faceted search function. You can access this function through the
Search application, which lets you drill down through the different types of
content, tags, and categories to refine your search. Next, you'll look at the
search features @product@ provides for specific kinds of content and then examine
how to use @product@'s faceted search.

+$$$

**Note:** In previous versions of @product@, it was possible to use an [advanced
searching syntax](/discover/portal/-/knowledge_base/6-2/searching-for-content-in-liferay#advanced-searching)
to create precise search queries. This is no longer possible in @product-ver@.
However, due to customer requests for these advanced capabilities, Liferay's
engineers are investigating approaches they can take in restoring the advanced
syntax, while ensuring the adopted solution works with both of @product@'s
supported search engines, Elasticsearch and Solr.

$$$

## Searching for Specific Types of Content [](id=searching-for-specific-types-of-content)

@product@ provides several applications that let users and administrators search
for content. First, there's the Search application, which can be placed on a
page to let users search for web content articles. The Search application only
searches for web content. However, apps like Blogs, Wiki, Message Boards, and
Documents and Media all provide search bars that let users search among the
specific types of assets with which these applications allow users to interact.
Note that all of these applications are accessible from Site Administration in
the Menu. They can also be placed on site pages for end-users to use. The Web
Content application in the Menu's Site Administration section also has a
built-in search bar, which allows for web content search. Because this
application is only designed for administrative use (and is not available to add
to a page), the Search application is available to allow non-adminstrative users
to search for web content.

+$$$

**Note:** The Web Content Search application is deprecated in @product-ver@ and
will be removed in 7.1. The Web Content Search app's functionality has
been replaced by the Search app, which is configured to search for web content. 

$$$

In order for content to be searchable, it must first be indexed. @product@ uses
Lucene for indexing and searching for content. Please refer to the Lucene
section of the portal properties file if you'd like to customize @product@'s
indexing and search behavior. By default, @product@ indexes blog posts, wiki
articles, message board posts, Documents and Media files' descriptions, and web
content articles. If a Documents and Media file is a text file, the file's
content is indexed as well. Comments on blog posts, wiki articles, and Documents
and Media files and text file attachments to wiki articles and message board
posts are also indexed. @product@ automatically indexes content as it's added to
the instance.

If you'd like to search among assets of a specific type and you'd like to
include comments and attachments in your search, use the search bar of the
appropriate application. For example, if you'd like to search for the term
*Lunar Resort* among wiki articles, enter the term *Lunar Resort* into the Wiki
application's search bar. 

![Figure 1: When using the Wiki application's search bar to search for *Lunar Resort*, wiki articles, comments, and attachments containing the words *Lunar* or *Resort* are returned.](../../../images/wiki-search.png)

Wiki articles, comments, and text file attachments containing the word *Lunar*
or *Resort* are returned as search results. The search results clearly show
whether an individual search result is a wiki article, a comment, or an
attachment. In the next section, you'll see how the Search application can be
used to search through web content on @product@. Its faceted search
feature is a powerful tool that allows users to include or not include specific
types of assets in a search. However, the Search application does not return
comments or attachments as search results.

## Searching for Localized Web Content [](id=searching-for-localized-web-content)

@product@ supports localizing web content as well as searching for localized web
content. Recall that to add a translation of a web content article, you need to
edit the article, choose a language, and then add the translation and click
*Save*. Here's how searching for localized content works in @product@:

- Web content articles whose default language matches the Liferay instance's
  default language are searched.
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

Next you'll learn about searching content in @product@ using facets.

## Searching for Content Using Facets [](id=searching-for-content-using-facets)

To get started using faceted search, drop the Search application on a page and
search for something. You'll see a page with results on the right and a
collection of *facets* on the left. 

![Figure 2: The first set of facets is content types. You can drill down to specific types of content that contain the search terms you entered.](../../../images/faceted-search.png)

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

![Figure 3: Asset tag facets let you see how many assets contain the terms for which you searched *and* contain certain tags. Click on a specific tag to narrow down the list of search results to those to which the tag has been applied.](../../../images/faceted-search-tags.png)

For example, if you searched for a wireless phone, you might be more interested
in content that has your search terms in it *and* has also been tagged by users.
One or more tags might help you to find what you're looking for. Note that the
number of tags that appear is configurable: by default it's 10, but there could
be many more as a result of a particular search. You'll look at the configuration
options later in this section. For now, you'll learn how to drill down to narrow
search results. 

To drill down into the search, click on a facet to add it to the filter list.
The results to the right are refined by the selected facets. 

![Figure 4: Selecting specific options from the left menu narrows down the asset results.](../../../images/faceted-search-drill-down-1.png)

In the figure above, the search is refined to only show assets tagged with
*bear*. The *Lunar Resort* site is also selected, to refine the search. The facets
that are selected, *Lunar Resort* and *bear*, are highlighted in the left menu.
You can remove the highlighted facet by clicking another facet, or the *Any*
facet. Suppose that the two facets selected weren't enough
to filter the search into a small enough list to sort through. In this case, you
could further refine the search by selecting another facet, as below.

![Figure 5: Selecting another facet further refines the search.](../../../images/faceted-search-drill-down-2.png)

Now the *Web Content Article* asset entry is also selected, which reduces the
list of search hits on the right. In this way, you can interactively tweak the
search results to narrow them down, making it easier to find that proverbial
needle within the haystack.

### Asset Types [](id=asset-types)

Searching can only be done on assets. As has already been described in this
chapter, just about any entity in @product@ is an asset and can be indexed and
searched. Under the hood, this means that these entities use Liferay's Asset API
and have an Indexer class defined. 

Developers can create custom searchable assets within @product@. This is
described in the
[Asset Framework](/develop/tutorials/-/knowledge_base/6-2/asset-framework)
tutorials. For this reason, you can have additional asset
types defined in @product@ beyond the ones that Liferay ships with by default.
If this is the case, you might want to tweak the *Frequency Threshold* and the
*Max Terms* settings to increase the number of asset types displayed past the
default of 10. This is covered in the section below on search options.

Note that although users can be appear as search results in the Search app, they
behave differently than other assets. For example, you cannot select a user as a
related asset. Also, users cannot be displayed by the Asset Publisher app.
Usually, when you click on an asset from a list of results in the Search app,
the selected asset is displayed in an Asset Publisher app. If you click on a
user, however, you're taken to the user's profile page. If public personal pages
have been disabled, clicking on a user from list of search results does nothing.
To disable public personal pages, you can set the following portal properties:

    layout.user.public.layouts.enabled=false
    layout.user.public.layouts.auto.create=false
    layout.user.public.layouts.power.user.required=true

The technical reason for this behavior is that although an `AssetEntry` object
is created for each `User` object, the `AssetEntries` objects of users are
created as non-visible assets. Instead of displaying users in the Asset
Publisher, @product@ uses the `displayURL` of each user as the link from a list of
search results.

### Asset Tags [](id=asset-tags)

If tags have been applied to any asset that appears in the result set, it may
be displayed in the Asset Tag facet. Tags are handled in a similar way to how
asset types are handled: not all tags may appear. There may be many more than
the 10 tags listed, but the default configuration for this facet is to show the
top 10 most frequent terms. As with asset types, this can be modified by
setting the `Max Terms` property, which is described later in this section.

### Asset Categories [](id=asset-categories)

If categories have been applied to any asset that appears in the result set,
they may be displayed in the Asset Categories facet. Asset categories work just
like asset tags. As with asset tags, you can modify the number of categories
listed in the search by setting the `Max Terms` property, which is described
later in this section.

## Setting Options for Detailed Search Results [](id=setting-options-for-detailed-search-results)

As with @product@'s other applications, you can configure the Search application
via the configuration screen, which looks like the below illustration. 

![Figure 6: You have three separate tabs for choosing your search configuration: *Display Settings*, *Spell Check Settings*, and *Other Settings*.](../../../images/faceted-search-configuration.png)

Your Search configuration displays the Display Settings first, by default.
You're able to control the scope of your search and the multiple facets of that
scope. You can toggle any of the following facets:

- Sites
- Asset Type
- Tag
- Category
- Folder
- User
- Modified Date

For each facet, you can click the *Configure* button to modify the display
settings for that particular facet. The configurations you set for each facet is
used to apply advanced search configuration settings in the form of a JSON
string. If you don't know what JSON is, don't worry, your configurations are
automatically inserted into a JSON object by @product@, so you only have to worry
about the values for your facets' configuration options. You can learn more
about this in the
[Faceted Search and Customized Search Filtering](/develop/tutorials/-/knowledge_base/6-2/faceted-search-and-customized-search-filtering)
tutorial. The following options are available in the configuration menu for the
indiviualized facets:

**Display Facet:** Specifies whether the facet appears in search results. 

**Weight:** Uses a floating point (or double) value used to determine the
ordering of facets in the facet column of the search application. Facets are
positioned with the largest values at the top.

**Frequency Threshold:** Indicates the minimum frequency required for terms to
appear in the result list. For example, if the frequency threshold of a facet is
set to `3`, a term appearing twice won't appear in the term result list.

**Max Terms:** Determines the maximum number of terms included in the search
result regardless of how many matching terms are found for the facet.

** Show Asset Count:** Displays the number of terms returned for the facet in
the search results.

**Current/Available Assets (Asset Type facet only):** Configures the asset types
that are returned in the search results.

**Display Style (Tag facet only):** Determines how the tags are displayed (i.e.,
Cloud or List).

**Label (Modified Date facet only):** Represents the language key that is used
for localizing the title of the facet when it's rendered.

**Range (Modified Date facet only):** Defines an interval within all the
matching terms frequencies' are summed.

The next configuration tab you can access is *Spell Check Settings*. In this
tab, you can toggle the following checkboxes:

**Display "Did you mean: ..." if the number of search results does not meet
the threshold:** When the number of search results does not meet the threshold,
"Did you mean: ..." is displayed to suggest a query with spell checked keywords.

**Display Related Queries:** Displays related queries when the number of
search results does not meet the threshold.

**Add New Related Queries Based on Successful Queries:** Queries that meet the
threshold are indexed and suggested to users as related queries or as part of
autocomplete suggestions.

Lastly, you can select the *Other Settings* tab for the Search application,
which provides miscellaneous search options:

**Display Results in Document Form:** Never use this in production. Developers
use this feature to view search responses in their generic, Document-based
format. Part of a developer's job when writing search indexers is to convert
Documents (the objects that get indexed) to the actual object and back again.
This option allows developers to see how their objects are being indexed. 

**View in Context:** When an asset is clicked, show it in the app to which it
belongs.

**Display Main Query:** Show the exact search query that the app generated to
the search engine. Again, never use this in production; this is for development
purposes only. 

**Display Open Search Results:** Shows results from third party Open Search
plugins, if they are installed. This is for backward compatibility only:
developers are encouraged to re-design their search code, and then custom assets
are aggregated with native @product@ assets seamlessly. 

+$$$

**Note:** You can identify available indexed fields by enabling the Search app's
*Display Results in Document Form* configuration setting and then expanding
individual results by clicking the *+* symbol to the left of their titles.

$$$

<!-- All of the JSON information that was documented here was removed, since
it's no longer necessary. JSON syntax has been abstracted away from users, so
this information was no longer relevant. The removed information may be useful
for the faceted-search-and-customized-search-filtering tutorial. For reference,
the JSON info in this section was removed as part of LRDOCS-2108. -Cody -->

Search is a powerful component of @product@'s asset framework. The proclivity of
assets means that there is an extensible, robust, and configurable search
mechanism throughout the portal that allows administrators to optimize the
search experience of their users. Users also get an easy to use search interface
that makes use of the tags and categories that they themselves apply to various
pieces of content, regardless of the type of content. This makes @product@'s
search truly "for the people."

Power users can learn an extended search syntax that lets them craft very
specific searches. These searches can be used on large installations with lots
of data to find the proverbial needle in the proverbial haystack. Administrators
can tune the configuration of search apps so that they are optimized for the
contents of their communities. 

Next, you'll look at how the Asset Publisher app makes even more extensive use
of @product@'s asset framework to bring relevant content to users.
