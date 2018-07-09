# Search Results Behavior [](id=search-results-behavior)

The previous article covered ways to display search results. This article
covers these additional Search Results concepts and configurations: 

- [Filtering search results with facets](#filtering-results-with-facets)
- [Understanding search results relevance](#search-results-relevance)
- [The effect of permissions on search results](#permissions-and-search-results)
- [Search results in the staging environment](#search-and-staging)
- [Search results summaries](#result-summaries)
- [Search results term highlighting](#highlighting)

## Filtering Results with Facets [](id=filtering-results-with-facets)

Results are filtered using *facets*. Most users have encountered similar
filtering capabilities in other applications, particularly during commerce
activities. Users enter a search term, are presented with a list of results and
search facets, which you can think of as buckets that group results together if
they share a common characteristic.

Administrators can configure facets. Read about 
[configuring facets](/discover/portal/-/knowledge_base/7-1/facets) 
to learn more.

## Search Results Relevance [](id=search-results-relevance)

The search engine decides which results appear at the top of the list using the
concept of *relevance*. Relevance is a score calculated by the search engine.
There are numerous factors contributing to the total score of a returned
document, and all of the implementation details of how relevance scoring works
are algorithms provided by the 
[search engine](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

## Permissions and Search Results [](id=permissions-and-search-results)

Users lacking
[VIEW permission](/discover/portal/-/knowledge_base/7-1/roles-and-permissions)
on an asset don't see it in the search results. A logged in User with the Site
Administrator role likely sees more search results than a guest User to the
site. 

In the background, there are two rounds of permissions checks. The first
permissions check, _pre-filtering_, happens in the search engine's index. It's
faster than checking database permissions information, but occasionally the
search index can have stale permissions information. To ensure the search
engine's index has correct, up-to-date permissions information, a second,
last-second permissions check, _post-filtering_, is performed on the results
prior to their display.

### Initial Permissions Checking [](id=initial-permissions-checking)

The first round of search results permissions filtering adds filter clauses to
the search query. This ensures that results return from the search engine
pre-filtered, containing results the current User can view.

This initial permission checking is configurable at *Control Panel* &rarr;
*Configuration* &rarr; *System Settings* &rarr; *Search* &rarr; *Permission
Checker*. It includes two system level settings to configure how search
processes User permissions.

**Include Inherited Permissions**
: Ignore this setting. It's deprecated, no longer used anywhere, and will be
removed in a future release.

**Permissions Term Limit**
: Limits the number of permission search clauses added to the search query
before this level of permission checking is aborted. Permission checking then
relies solely on the final permission filtering described below.

The only reason to limit permissions terms is performance. Users with
administrative access to lots of sites and organizations generate many
permissions terms added to the query. Too many terms in a query can make the
search engine time out.

### Final Permissions Checking [](id=final-permissions-checking)

A final round of permission checking happens prior to presenting results in the
UI. For example, the User searches for *liferay*, and the search engine returns
all relevant forum posts. As the Search Results iterates through the list of
relevant forum posts, it performs one last permission check of the post to
ensure the User can view the post and its categories. If a matching forum post
exists in a category the User doesn't have permission to view, it isn't
displayed in the list of search results.

This final round of permission checking is configurable at *Control Panel*
&rarr; *Configuration* &rarr; *System Settings* &rarr; *Search* &rarr; *Default
Search Result Permission Filter*. It includes two settings:

1.  The first setting, Permission Filtered Search Result Accurate Count
    Threshold, specifies the maximum number of search results to
    permissions-filter before results are counted. A higher threshold increases
    count accuracy, but decreases performance. Since results in the currently
    displayed page are always checked, any value below the search results
    pagination delta effectively disables this behavior.

2.  The second setting, Search Query Result Window Limit, sets the maximum batch
    size for each permission checking request. <!-- OR limits the number of
    results to include in each permission checked request/response cycle to and
    from the search engine-->. This is again impacted by pagination. For
    example, if there are 100 results per page, and a User wants to jump all the
    way to page 200 of the search results, all results between page one and 200
    must be checked to ensure the User has permission. That's 20,000 results to
    permissions check. Doing this in one trip to and form the search engine can
    result in performance issues. Set the maximum batch size for each permission
    checking request. 

## Search and Staging [](id=search-and-staging)

With
[staging](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication),
content is placed first in a preview and testing environment before being
published for consumption by end Users (on the live site). Content added to the
search index is marked so that the search API can decipher whether an item is
live or not. In the live version of the site, only content that's marked for the
live site is searchable. 

In the staged version of the site, all content---live or staged---is searchable.

## Result Summaries [](id=result-summaries)

A result summary includes the information from a document that the asset's
developer felt is most useful to end Users searching for the asset. Each asset
can have different fields included in the summary. For assets with text content,
a common summary format includes the *title* and some of the *content*, with
title displayed first. The asset type always appears on the second line, and
a snippet of the content that matches the search term is on the last line.
Assets without content fields, like Documents and Media documents, display the
description instead.

Users are different. Only the User's full name and the asset type (User) appear
in User result summaries.

![Figure 1: User summaries contain only the User's full name.](../../../images/search-results-user.png)

For assets that contain other assets (Web Content, Documents & Media, and
Bookmarks folders) or whose content is not amenable to display (Dynamic Data
List Records and Calendar Events), it makes more sense to display the title,
asset type, and description in results summaries. There'd never be anything in a
content field for these assets.

![Figure 2: Documents and Media, Web Content, and Bookmarks folders include
titles and descriptions in their summaries.](../../../images/search-results-folder.png)

Bookmarks entries show the title and the URL.

![Figure 3: Bookmarks Entries summaries show the title and the URL.](../../../images/search-results-bookmark.png)

The asset developer determines which fields are summary-enabled, but there's
logic invoked at search time that determines precisely the part of the summary
fields to display. For example, a `content` field can have a lot of text, but
the summary doesn't show it all. Instead, it shows a relevant snippet of the
field's text. If the keyword searched for is present in the summary field, that
portion of the field is used in the summary. In addition, the matching keyword
is highlighted in the summary.

## Highlighting [](id=highlighting)

By now you've probably noticed that search terms appearing in the summary are
<mark>highlighted</mark> by default. If this is undesirable, disable it in the
widget configuration screen. 

![Figure 4: Some document summaries have lots of highlights if the search term matches text that appears in the summary.](../../../images/search-results-highlight.png)

Highlighting is a helpful visual cue that hints at why the result is returned,
but beware. A hit can score well and thus be returned near the top of the
results, without having any highlights in the summary. That's because not all
indexed fields appear in the summary. Consider a User named Arthur C. Clarke. He
has an email address of *acc@authors.org*, which is searchable. Because results
summaries for Users only contain the full name of the User, searching for Mr.
Clarke by his email address returns the User, but no term is highlighted. 

![Figure 5: Results that match the search term won't always have highlights.](../../../images/search-results-no-highlight.png)

There are additional cases where search results don't have highlighting.
