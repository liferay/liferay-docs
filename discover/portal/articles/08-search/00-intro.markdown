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

## Searching for Specific Types of Content [](id=searching-for-specific-types-of-content)

@product@ provides several applications that let users and administrators search
for content. First, there's the Search application, which can be placed on a
page so users can search for web content articles. The Search application only
searches for web content, but apps like Blogs, Wiki, Message Boards, and
Documents and Media all provide search bars that let users search among the assets they display.
Note that all of these applications are accessible from Site Administration in
the Menu. They can also be placed on site pages for end-users to use. The Web
Content application in the Menu's Site Administration section also has a
built-in search bar, which allows for web content search. Because this
application is only designed for administrative use (and is not available to add
to a page), the Search application is available to allow non-administrative users
to search for web content.

+$$$

**Note:** The Web Content Search application is deprecated in @product-ver@ and
will be removed in 7.1. The Web Content Search app's functionality has
been replaced by the Search app, which is configured to search for web content. 

$$$

In order for content to be searchable, it must first be indexed. By default,
@product@ uses uses Elasticsearch, as searh engine built on the Lucene search
engine library, for indexing and searching for content. Refer to the Lucene
section of the portal properties file to customize @product@'s
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


<!-- Not applicable now, removed in published docs
### Advanced Searching [](id=advanced-searching)

The Search application's search box is deceptively simple. Though you have only
a single field for search, there's a search syntax inherited from
[Lucene](http://lucene.apache.org/core/old_versioned_docs/versions/3_0_3/queryparsersyntax.html)
that lets you create very powerful search queries. You'll take a look at some
ways you can use search queries.

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

**Boolean operators:** You can use logic operators, such as `AND`, `OR`, `NOT`,
`+`, and `-` in your searches. The `AND` operator matches assets in which the
terms between the `AND` operator exist. For example, to search for both Liferay
and Kaleo Workflow, use this query: 

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

Next, you'll look at how the Search application can be configured. 

-->

