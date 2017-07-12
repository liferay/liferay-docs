# Configuring the Search Application [](id=configuring-the-search-application)

If you're working with a freshly unzipped @product@ bundle, you'll notice that
all site pages contain a Search portlet, deployed to the top right corner of the
page, next to the Navigation portlet. Each of those portlets provides your users
with the ability to search for content quickly and easily. The out of the box
behavior will serve you well, but the portlet can be configured to achieve just
the right functionality for your use case.

The configuration ability covered in this article includes

- Configuring the display of search results
- Configuring spell checking on user queries

For information on configuring the Search application's facets, see the [next
article](/discover/portal/-/knowledge_base/7-0/facet-settings).

Start by configuring the display settings for the Search application.

## Displaying Search Results [](id=displaying-search-results)

The search application has a nice format for displaying search results. It also
allows users to click on a specific result so they can look at it in more
detail. You can configure the application's display options by clicking its
options menu
(![Options](../../images/icon-options.png)) and selecting
*Configuration*. The tab displayed by default is *Display Settings*.

The Scope setting is really important here. By default, searching is done on
*This Site*, which means only the assets associated with the site where the
search is executed. To expand the scope of the search to the entire @product@
instance, select *Everything*, To let the user choose which scope they want
to search, select *Let the User Choose*.

![Figure 1: The *Let the User Choose* scope option enables a drop-down menu in the search bar where users can set the scope of their search.](../../images/search-scope.png)

The list of facet settings on this page is also quite important. To learn more
about facets and their configuration options read
[here](/discover/portal/-/knowledge_base/7-0/facet-settings). 

For more display options, click the *Other Settings* tab. There are several
options here:

**Display Results in Document Form**
: Display results as [search
documents](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search).
Never use this in production. Developers use this feature to view search
responses in their generic, Document-based format. Part of a developer's job
when writing search indexers is to convert Documents (the objects that get
indexed) to the actual object and back again. This option allows developers to
see how their objects are being indexed.

+$$$

**Note:** You can identify available indexed fields by enabling the Search
application's
*Display Results in Document Form* configuration setting and then expanding
individual results by clicking the *+* symbol to the left of their titles.

$$$

**View in Context**
: When an asset is clicked, show it in the app to which it belongs. For example,
if you click on a blog post in the search results, you'll be taken to the page
where the blog is posted in the Blogs application. Note that you will no longer
be in the search context after clicking on a search result. When this option is
unchecked, the asset displays in an Asset Publisher window while still in the
search context. If you have the right permissions, you can even edit the content
directly from the Search context. Click the back arrow to return to the search
results.

**Display Main Query**
: Show the exact search query that the app generated to the search engine. Never
use this in production; this is for development purposes only.

**Display Open Search Results**
: Show results from third party Open Search plugins, if they are installed. This
is for backward compatibility only: developers are encouraged to re-design their
search code, and then custom assets are aggregated with native @product@ assets
seamlessly.

Besides the display options, you can also configure the spell check settings, so
users get alternate search queries suggested to them when the initial search
returns few results.

## Spell Checking User Queries [](id=spell-checking-user-queries)

Next to the Other Settings tab lives the Spell Check Settings tab. Here you can
configure the Search application to help users when their initial query doesn't
yield many results.

![Figure 2: Configure the spell check settings to allow for user input mistakes and help lead users to results.](../../images/search-spell-check-settings.png)

There's really three main settings here:

**Display "Did you mean..." if the number of search results does not meet the
threshold.**
: Present users alternate, spell checked search queries if their search did not
return a minimum number of results (50 by default).

**Display Related Queries**
: Display up to a maximum number of alternative queries (5 by default) if the
user's query doesn't return a minimum number of results (0 by default).

**Add New Related Queries Based on Successful Queries**
: Index a user's search query if it produces a minimum number of results (50 by
default). If the Display Related Queries setting is enabled, it can be used as a
related query for similar search queries that don't produce enough results.

+$$$

**Note:** To enable the spell checking behavior described above, you must first
trigger a reindex of the spell check indexes. Navigate to the *Control Panel*
&rarr; *Configuration* &rarr; *Server Administration*. Find the Index Actions
entry for *Reindex all spell check indexes.* Click *Execute*, and look for a
message in your log your indicating that the spell checking indexes were created
successfully:

    16:19:35,793 INFO  [liferay/search_writer/SYSTEM_ENGINE-24][BaseSpellCheckIndexWriter:257] 
        Start indexing dictionary for com/liferay/portal/search/dependencies/spellchecker/en_US.txt
    16:19:41,932 INFO  [liferay/search_writer/SYSTEM_ENGINE-24][BaseSpellCheckIndexWriter:2910] 
        Finished indexing dictionary for com/liferay/portal/search/dependencies/spellchecker/en_US.txt

Once the spell checking indexes are created, your spell check settings become
effective.

$$$

In addition to enabling or disabling any of these settings, the thresholds for
all of the spell check settings can also be changed to produce the desired
behavior.
<!--Any notes about performance or other warnings with these numbers? -->

Search is a powerful component of @product@'s asset framework. Administrators
can customize the display options for content, and configure the spell check
settings to ensure users don't go away feeling frustrated after an unsuccessful
search query. Facets are Another important component of @product@'s search
functionality for the opposite situation, where users might be overwhelmed by
the number of search results, and unable to find the proverbial needle in the
haystack. To learn more, read the next article on how facets let users narrow
down search results to find just what they were looking for.

