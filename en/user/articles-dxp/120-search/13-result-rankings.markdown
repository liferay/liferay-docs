---
header-id: search-tuning-customizing-search-results
---

# Search Tuning: Customizing Search Results

[TOC levels=1-4]

Starting with @product-ver@ Service Pack 1, new search tuning features are
available for administrative Users: Custom Result Rankings is one of them.

Result Rankings provides a brute force method for intervening into the relevance
scoring of the search engine, by doing these things:

1.  Designate that certain results should appear at the top of the results if
    they are matched with a certain keyword. This is the idea of _pinning_
    results to the top of the results list.

2.  By contrast, hide results that shouldn't appear in certain searches at all. 

3.  Add results that aren't normally returned by searching a certain keyword.

4.  Re-order pinned results with a drag-and-drop interface.

Result Rankings let you pin, hide, and add search results for a given set of
keywords. These rankings apply only to searches using the newer search widgets
(Search Bar, Search Results, etc.). The rankings you customize do not apply to
the legacy Search portlet results or to the individual application search bars.

| **Use Case:** At the Lunar Resort website, visitors often search for activities,
| entering keywords like "rover races", "atv rentals", and "lunar golf". For all
| of these, the Lunar Resort always wants a certain [Content
| Page](/docs/7-2/user/-/knowledge_base/u/creating-content-pages) to appear  at
| the top of the search results. This is the Activities page in the Lunar Resort
| where guests can find all of the resort's adventurous offerings, including lunar
| rover races, ATV rentals, and information about golfing packages. By contrast,
| the Lunar  Resort does not want the legal liability waiver form to appear during
| a search for fun activities: that's a bridge to be crossed when guests sign up
| for the activity. It shouldn't pollute a search for fun activities, even though
| it contains many of the keywords Users would search for. Result Rankings lets
| you _pin_ the Activities Content Page to the top of the results and _hide_ the
| liability waiver Web Content Article.  In addition, a community member wrote a
| blog favorably reviewing the Lunar Resort, and you want that content added to
| searches for activities at the resort. This is a prime use case for Result
| Rankings. 

![Figure 1: The Lunar Resort wants to tweak these results: pin the Activities page to the top, and hide the legal content entirely.](../../images/search-result-rankings-todo.png) 

## Availability

Search tuning features like Result Rankings are only supported when using
Elasticsearch as the search engine. If you're using Solr, make sure you disable
the 
[search tuning features](/docs/7-2/deploy/-/knowledge_base/d/installing-solr#blacklisting-elasticsearch-only-features)
(Synonym Sets and Result Rankings) when you upgrade your installation to
@product@ Service Pack 1 (Fix Pack 2).

Results Rankings was added in @product-ver@ Service Pack 1.

## Requirements and Limitations

Result Ranking entries are configured in a Virtual Instance, but are not applied
only to that Virtual Instance. Instead, custom rankings made in one virtual
Instance are shared across all Virtual Instances in the deployment, and even
across separate deployments sharing an Elasticsearch cluster (in a multi-tenant
scenario). Therefore, Result rankings shouldn't be used when connecting
multiple @product@ deployments to the same Elasticsearch cluster unless you
intend for the same Result rankings to apply to every @product@ deployment. See 
[LPS-101291](https://issues.liferay.com/browse/LPS-101291)
for more information.

An existing Result Ranking cannot be renamed. Renaming requires recreating the
ranking under a different name. See
[LPS-96357](https://issues.liferay.com/browse/LPS-96357)
for more information.

## Creating and Managing Result Rankings

To manipulate result rankings, create a new _Alias_ containing the
keywords/search terms you want to intercept. Perform a search to get results
(you can also do a separate search if you want to grab results that haven't even
been returned during a natural search for the alias keywords). Once you have the
results, choose to pin, hide, re-order, or add results as you please.

To create a new Result Rankings Alias:

1.  Navigate to Control Panel &rarr; Search Tuning &rarr; Result Rankings.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  On the New Ranking screen, enter one of the keywords or search phrases you
    want to intercept (it can be a phrase, instead of just one word; and don't
    worry, you can add more later) in the _Search Query_ field. 

    Click _Customize Results_.

A search query is executed. The results are displayed and the tools for pinning,
hiding, and adding results are made available. Re-ordering becomes possible
after at least one result is pinned. First, consider whether to add one or more
Aliases.

### Adding Aliases

The Customize Rankings screen is ready to use, but any intervention only
applies to the search query you initially entered in the New Ranking screen. To
apply the customized rankings to additional search terms, add them as _Aliases_. 

1.  In the Aliases field, enter the search term to add as an Alias.

    **Warning:** Do not use quotes in your alias terms.

2.  To submit the search term as an alias, click Enter or a comma in the Aliases
    field. You can Add multiple aliases here. 

    ![Figure 2: Apply your custom rankings to matched results of additional search terms.](../../images/search-result-rankings-aliases.png)

Note that results not manipulated manually here are returned as usual when the
alias term is queried for in the Search Bar. 

Now you can customize the rankings.

### Activating and Deactivating Aliases

> Available as of Liferay DXP 7.2, SP2

You can activate or deactivate existing aliases as you have need for them to take effect:

1.  Find the Active alias and open its editor screen.

2. Click the toggle switch currently labeled Active.

Duplicate active aliases are not allowed, but you can deactivate an alias and
then create a duplicate. After deactivating an alias, you can only reactivate
it after deactivating any active duplicates.

### Pinning and Hiding Results

To pin or hide rankings, hover over the result of interest: two icons appear,
one for pinning and one for hiding. Click the one that applies. Otherwise click
the Actions button (![Actions](../../images/icon-actions.png)), and select _Pin
Result_ or _Hide Result_. Once you select either option, it's applied
immediately. A pinned result moves to the top of the list, and a hidden result
disappears. Repeat the action as many times as necessary.

If you're done customizing the results, click _Save_, and it's applied
immediately.

![Figure 3: Pin results to the top of the Search Results list.](../../images/search-result-rankings-pinned-result.png)

### Adding Results

To add a result that was not returned by searching for the first keyword or
phrase, click the _Add Result_ button and search for whichever asset you want to
pin. 

![Figure 4: Add results that aren't normally returned.](../../images/search-result-rankings-add-result.png)

Click _Save_ if you're done customizing results.

### Re-Ordering Pinned Results

To re-order pinned results (results that are not pinned cannot be re-ordered),
click the handle icon, drag the result, and drop it in the preferred location in
the list. 

![Figure 5: Re-order the pinned rankings if you want to emphasize or de-emphasize certain results.](../../images/search-result-rankings-reorder.png)

Once finished customizing result rankings, click _Save_.

## Result Rankings Scope and Permissions

Because configuration of Result rankings happens at the virtual instance scope,
there are scoping and permissions behaviors to be aware of.

Scope is disregarded for pinned results: Pinned results existing in Site
A always appear in searches from Site B, even if the Search Bar Scope is set to
_This Site_. 

Search from Result Rankings is global: When searching for results in Result
Rankings admin, relevant results from all sites are returned.

Permissions are applied as usual: If a User doesn't have permission to see an
asset, pinning it does not make it appear in the search results for that
User.

## Result Rankings Aliases versus Synonyms

Since both are new features without precedent in @product@, there can be
confusion over Result Rankings Aliases and Synonyms.
[Synonyms](/docs/7-2/user/-/knowledge_base/u/search-tuning-synonym-sets) 
expand the search to include results matched by additional (synonymous)
keywords, so more results are returned if there are matches to the synonyms.

Result Rankings Aliases are just keywords that also have the particular
ranking interventions applied to them. Only the searched keyword is matched
to results, and then, the pins, hides, re-ordering, and additional results take
effect after that. Of course, if you add a Synonym Set that includes an
identical set of search terms as the Result Ranking you configure, the features
can be used together. All the synonymous terms return the same result set, with
the customized rankings applied on top.

## Known Issues

There are several known  issues and planned improvements for Result Rankings.
See [LPS-99540](https://issues.liferay.com/browse/LPS-99540) for the complete
list.

