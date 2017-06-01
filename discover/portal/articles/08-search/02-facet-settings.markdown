# Facet Settings

As with @product@'s other applications, you can configure the Search application
via the configuration screen, which looks like the below illustration. 

![Figure 6: You have three separate tabs for choosing your search configuration: *Display Settings*, *Spell Check Settings*, and *Other Settings*.](../../../images/faceted-search-configuration.png)

Your Search configuration displays the Display Settings first, by default.
You're able to control the scope of your search and the multiple facets of that
scope. You can configure any of the following facets by clicking the *Configure*
button:

- Sites
- Asset Type
- Tag
- Category
- Folder
- User
- Modified Date

<!-- Why bother with the JSON bit? -->
The configuration you set for each facet is used to apply advanced search
configuration settings in the form of a JSON string. If you don't know what JSON
is, don't worry, your configurations are automatically inserted into a JSON
object by @product@, so you only have to worry about the values for your facets'
configuration options. You can learn more about this in the
[Faceted Search and Customized Search Filtering](/develop/tutorials/-/knowledge_base/6-2/faceted-search-and-customized-search-filtering)
tutorial. 

<!-- Should this be a reference article? -->
The following configuration options are available for facets:

**Display Facet:** Specifies whether the facet appears in search results. 

**Weight:** Uses a floating point (or double) value used to determine the order
facets appear in the search application. Facets with the largest values are
positioned at the top.

<!-- Display Frequencies is what they're calling this now -->
**Frequency Threshold:** Indicates the minimum frequency required for terms to
appear in the result list. For example, if the frequency threshold of a facet is
set to `3`, a term appearing twice won't appear in the term result list.

**Max Terms:** Determines the maximum number of terms included in the search
result regardless of how many matching terms are found for the facet.

**Show Asset Count:** Displays the number of terms returned for the facet in
the search results.

**Current/Available Assets (Asset Type facet only):** Configures the asset types
that are returned in the search results.

**Display Style (Tag facet only):** Display tags as a Cloud or List (default).

**Label (Modified Date facet only):** The language keyused for localizing the
title of the facet when it's rendered.

**Range (Modified Date facet only):** Define an interval within all the
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

**Display Results in Document Form:** Display results as search documents. Never use this in production. Developers
use this feature to view search responses in their generic, Document-based
format. Part of a developer's job when writing search indexers is to convert
Documents (the objects that get indexed) to the actual object and back again.
This option allows developers to see how their objects are being indexed. 

<!--Different term used in new search results-->
**View in Context:** When an asset is clicked, show it in the app to which it
belongs. For example, if you click on a Blogs Entry in the search results,
you'll be taken to the page where the blog is posted. Note that you will no
longer be in the search context when you click on a search result. 

The alternative behavior is to be show a snapshot of the content without leaving
the Search portlet context. To go back to the list of search results, just click
the back arrow. If you have the proper permissions you can even edit the content
directly from the Search context.
<!-- Not working right now 5/17/17. Clicking back clears the search results. -->

<!-- Not available in Search Bar or Search Results as of 5/17/17 -->
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
