<!-- Review the writing guidelines for proper italics, etc for UI items -->


<!-- Add something about search results and unauthenticated vs. authenticated
users, vis a vis User results and DDL Record results (and maybe others?) -->

# Searching for Content

If you're working with a freshly unzipped @product@ bundle, you'll notice that
all site pages contain a Search portlet, deployed to the top right corner of the
page, next to the Navigation portlet. Each of those portlets provides your users
with the ability to search for content quickly and easily. The out of the box
behavior will serve you well, but the portlet can be configured so you can
obtain just the right functionality for your use case.

Familiarizing yourself with these topics will get you well on your way to
leveraging @product@'s built-in search functionality:

- Searching is for Assets
- Searching for Content Using Facets
- Facet Settings
- Searching for Localized Content

Facets can take the search experience to the next level.

<!-- This might be where the real content for this article starts -->

## Search is for Assets

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
number of asset types displayed past the default of 10.  See more about these
settings in the section below on search options.

<!-- Move to defining-content-relationships article and other asset publisher
docs? Users wouldn't expect to find instructions on disabling user personl sites
in the searching for content article, either. PErhaps this should go in the
Creating Sites article -->
+$$$

**User Display:** When you click an asset in the search results, it's displayed
in an Asset Publisher (unless you chose the *View in Context* option for the
asset). Users are different, though. Think of them as "invisible assets", not
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

## Displaying Search Results

The search application has a nice format for displaying search results. It also
allows users to click on a specific result so they can look at it in more
detail. You can configure the application's display options by clicking its
configuration menu
(![Configuration](../../../images-dxp/icon-configuration.png)) and selecting
*Configuration*. The tab displayed by default is *Display Settings*.

The Scope setting is really important here. By default, searching is done on
*This Site*, which means only the assets associated with the site where the
search is executed. To expand the scope of the search to the entire @product@
instance, select *Everything*, and to let the user choose which scope they want
to search, select *Let the User Choose*.

The list of facet settings are also quite important. To learn more about facets
read [LINK to the facet settigns article](LINK). 

There are more options worth pointing out in the Other Settings tab.

Display Results in Document Form
: Display results as [search
documents](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search).
Never use this in production. Developers use this feature to view search
responses in their generic, Document-based format. Part of a developer's job
when writing search indexers is to convert Documents (the objects that get
indexed) to the actual object and back again. This option allows developers to
see how their objects are being indexed.

+$$$

**Note:** You can identify available indexed fields by enabling the Search app's
*Display Results in Document Form* configuration setting and then expanding
individual results by clicking the *+* symbol to the left of their titles.

$$$

View in Context
: When an asset is clicked, show it in the app to which it belongs. For example,
if you click on a Blogs Entry in the search results, you'll be taken to the page
where the blog is posted. Note that users will no longer be in the search context
after clicking on a search result. When this option is unchecked, the asset
displays in an Asset Publisher window while still in the search context.  The
user can click a back arrow to return to the search results.

Display Main Query
: Show the exact search query that the app generated to
the search engine. Never use this in production; this is for development
purposes only.

Display Open Search Results
: Show results from third party Open Search plugins, if they are installed. This
is for backward compatibility only: developers are encouraged to re-design their
search code, and then custom assets are aggregated with native @product@ assets
seamlessly.

## Spell Checking User Queries

Next to the Other Settings tab lives the Spell Check Settings tab.

![Figure x: Configure the spell check settings to allow for user input mistakes and help lead users to results.](../../../images/search-spell-check-settings.png)

There's really three main settings here:

*Display "Did you mean..." if the number of search results does not mee the
threshold.*
: Present users alternate search queries if their search did not
return a minimum number of results (50 by default).

*Display Related Queries*
: Up to a maximum number of alternative queries (5 by default) are displayed if
the user's query doesn't return a minimum number of results (0 by default).

*Add New Related Queries Based on Successful Queries* If a user's search query
produces a minimum number of results (50 by default), the query is indexed. If
the Display Related Queries setting is enabled, it's used as a related query for
similar search queries that don't produce enough results.

The numbers involved in all of those settings can also be set in Spell Check
Settings.





### Asset Tag

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

## Searching for Localized Content

@product@ supports localizing some of its content types. For example, to add a
translation of a web content article, edit the article, choose a language, add
the translation, and click *Save*. Here's how searching for localized content
works in @product@:

- Content whose default language matches the @product@ instance's
  default language are searched.
- Content whose default language is `en_US` (American English) are
  searched, regardless of Liferay's default language.
- If the language of a translation matches Liferay's default
  language, the article is searched.
- Content with an `en_US` translation can be searched regardless of
  Liferay's default language.
- A user's default language does not affect search results.

Search is a powerful component of @product@'s asset framework. Users can search
for content, and enhance their search experience with facets and localized
content. Administrators can configure the application to best suit their users'
needs, changing the scope of the search, the way results are displayed, and
more.



