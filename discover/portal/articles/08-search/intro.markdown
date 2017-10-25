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
content, tags, and categories to refine your search. 

In this introductory article you'll cover these basic concepts:

- Searching for specific types of content
- Searching for assets
- Searching for localized content

In the following articles you'll Learn how to [configure the Search
application's display settings](/discover/portal/-/knowledge_base/7-0/configuring-the-search-application) and
about [using facets](/discover/portal/-/knowledge_base/7-0/faceted-search) to narrow down the
search results.

For content to be searchable, it must first be indexed. By default, @product@
uses uses Elasticsearch, a search engine built on the Lucene search engine
library, for indexing and searching for content. Refer to the Lucene section of
the [portal
properties](https://docs.liferay.com/portal/7.0-latest/propertiesdoc/portal.properties.html#Lucene%20Search)
file to customize @product@'s indexing and search behavior. By default,
@product@ indexes blog posts, wiki articles, message board posts, Documents and
Media files' descriptions, and web content articles. If a Documents and Media
file is a text file, the file's content is indexed as well. Comments on blog
posts, wiki articles, and Documents and Media files and text file attachments to
wiki articles and message board posts are also indexed. @product@ automatically
indexes content as it's added.

Besides the Search application, which will return any indexed assets, many
@product@ applications contain a native search functionality for their specific
assets.

## Searching for Specific Types of Content [](id=searching-for-specific-types-of-content)

@product@ provides several applications that let users and administrators search
for content. First, there's the Search application, which is deployed to a
page so users can search for any content. The Search application searches for
any indexed assets in @product@, but apps like Blogs, Wiki, Message Boards, and
Documents and Media all provide search bars that let users search only the
assets they display. Note that all of these applications are accessible from
Site Administration in the Menu. They can also be placed on site pages for
end users to view and interact with.

+$$$

**Note:** The Web Content Search application is deprecated in @product-ver@ and
will be removed in 7.1. The Web Content Search application's functionality has
been replaced by the Search application. 

$$$

To search assets of a specific type, including their comments and attachments in
your search, use the search bar of the appropriate application. For example, if
you'd like to search for the term *Lunar Resort* among wiki articles, enter the
term *Lunar Resort* into the Wiki application's search bar. 

![Figure 1: When using the Wiki application's search bar to search for *Lunar Resort*, wiki articles, comments, and attachments containing the words *Lunar* or *Resort* are returned.](../../images/wiki-search.png)

Wiki articles, comments, and text file attachments containing the word *Lunar*
or *Resort* are returned as search results. The search results clearly show
whether an individual search result is a wiki article, a comment, or an
attachment. 

## Searching for Assets [](id=searching-for-assets)

Searching can only be done on *assets*. Most of the content types in @product@
(for example, blog entries) are assets that can be indexed and searched. Under
the hood, assets use the [Asset
API](/develop/tutorials/-/knowledge_base/7-0/asset-framework) and have an
[Indexer
class](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search#indexersdefined).

All indexed assets can be returned as search results. Since developers can
create assets, your @product@ instance might have additional asset types beyond
the ones that @product@ ships with by default. If this is the case, you can
tweak the *Frequency Threshold* and the *Max Terms* settings to increase the
number of asset types displayed past the default of 10.

+$$$

**User Display:** When you click an asset in the search results, it's displayed
in an Asset Publisher (unless you use the *View in Context* option for the
asset). Users are different, though. Think of them as invisible assets, not
intended for display in the Asset Publisher application. While users appear as
search results with other indexed assets, when you click one you're taken to the
user's profile page. If [public personal
pages](/discover/portal/-/knowledge_base/7-0/creating-sites#customizing-personal-sites)
have been disabled, clicking on a user from the list of search results shows you a
blank page.

$$$

+$$$

**Versioning Note:** If there are multiple versions of a piece of content, the
latest approved version is the only one searched.

$$$

## Searching for Localized Content [](id=searching-for-localized-content)

@product@ supports localizing some of its content types. For example, to add a
translation of a web content article, edit the article, choose a language,
translate the relevant fields, and click *Save*. So what happens when users
search for that content? The short answer is, they can! The details get
complicated because each asset in @product@ is indexed in the search engine
differently. Searching for localized Web Content Articles will work one way, and
Documents and Media Documents will be indexed differently, in the way seen most
suitable by the developers of the application. The same applies to any custom
assets added by the developers of your organization. Basically, if a developer
makes a field localizable (for example, the title of the asset is often able to
be localized), then a user can search for it and find the content by searching
for a translated term.

+$$$

**Note on Eastern Languages:** Because the process called *tokenization* works
differently for Eastern languages (for example, Japanese and Chinese), searching
for their content works differently. The process used to tokenize these
languages makes it difficult to search for them in @product@. There are several
known problems, which are being improved in @product@, and will be made
available by [fix
pack](/documentation/7.0/deploy/-/official_documentation/deployment/keeping-up-with-fix-packs-and-service-packs)
(Digital Enterprise customers only) or made available in a future GA release
(for Community Edition users).

$$$

The search application allows users to search for content in your @product@.
Keep reading to learn how to configure the Search application so users can
easily find what they're looking for. 
