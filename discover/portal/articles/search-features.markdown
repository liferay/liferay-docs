# Searching for Assets

Deploy the search facets, and the search results, to a page.

Change the facets to use a barebones application decorator in Look and Feel
Configuration. Then you won't see the empty facets cluttering the page unless
search hits for those facets are returned.

## Search Results


### Configuration

Enable highlights

View Selected Results in Context (when available)

Display Results in Document Form (fields with values): Not selected by default

Parameter Name for Pagination Start: *start*

Pagination Delta: *20*

Parameter Name for Pagination Delta: *delta*

## Bookmarkability

Anything that changes the search results becomes a URL parameter. So if a user
searches for something and a set of results is returned, the user can bookmark
the URL in their browser and expect to see the same list of results (assuming no
content relevant to their search terms were added, deleted, or changed in such a
way that they are added or removed to the search results).

## Search Bar

One of the more basic elements to your search page is a Search Bar. Simply put,
the search bar accepts user input (i.e., keywords, or search terms) to use in
the search query. 

Add the Search Bar application to a page. It has sensible default configuration
options, but you're free to customize anything you need. 

### Configuring the Search Bar

Click the application's Options button
(![Options](../../../images-dxp/icon-options.png)) and select Configuration.

The Setup tab is opened by default. There are several configuration options:

Keywords Parameter Name
: Set the term used for the URL parameter that holds the keywords. By default,
or if the option is set as blank, a *q* is used here. If a user enters the
keyword *johan* into the search bar using the default setting, here's the URL
that's generated:

    http://my.domain.com/web/guest/home?q=johan

If you set the Keywords Parameter Name to *keywords* and search for *wolfgang*,
here's the generated URL:

    http://my.domain.com/web/guest/home?keywords=wolfgang

Scope
: Set the scope of the search results that are returned. By default, the scope
is *Everything*, which means the result set for the search query willr etyurn
everything applicable to the [virtual
instance](/discover/portal/-/knowledge_base/7-0/virtual-instances).
Alternatively, set the scope to the current site (*This Site*), or to *Let the
User Choose*. 

When you let the user choose the scope of the results, the search bar includes a
selector so the user can search using the Everything scope or they can limit the
search to This Site.

Destination Page
: 

Auto Complete URL
: 

In the Sharing tab

