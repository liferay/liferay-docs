# Customizing Search Result Rankings

| **Known Issues:** There are several [known
| issues](https://issues.liferay.com/browse/LPS-99540) for Result Rankings. These
| are some of the biggest:
| 
| LPS-XXXXX: Don't use quotes in your alias definitions.
<!-- Might not be an issue really, just a warning we need to make -->

Starting with @product-ver@ Service Pack 1, new search tuning features are
available for administrative Users: Custom Result Rankings is one of them.

Search Tuning features like Result Rankings are only supported when using
Elasticsearch as the search engine.

Result Rankings provides a brute force method for intervening into the relevance
scoring of the search engine, by doing these things:

1.  Designate that certain results should appear at the top of the results if
    they are matched with a certain keyword. This is the idea of _pinning_
    results to the top of the results list.

2.  By contrast, hide results that shouldn't appear in certain searches at all. 

3.  Add results that aren't normally returned by searching a certain keyword.

4.  Re-order results with a drag-and-drop interface.

<!-- Not sure if we need to show this 5.  Configure a search page to see the results from the Result Rankings
    customization and the un-manipulated results. LPS-96212
-->

Result Rankings lets you pin, hide, and add search results for a given set of
keywords.

| **Use Case:** At the Lunar Resort website, interested site visitors often search
| for specific activities, entering keywords like "rover races", "atv rentals",
| and "lunar golf". For all of these, the Lunar Resort wants a certain [Content
| Page](/docs/7-2/user/-/knowledge_base/u/creating-content-pages) to always appear
| at the top of the search results. This is the go-to Activities page in the Lunar
| Resort where guests can go to find all of the resort's adventurous offerings,
| including lunar rover races, ATV rentals, and information about golfing
| packages. This is a prime use case for Result Rankings. By contrast, the Lunar
| Resort does not want the legal liability waiver form to appear during a search
| for fun activities: that's a bridge to be crossed once guests are already
| signing up for the activity. It shouldn't pollute a search for fun activities,
| even though it contains many of the keywords Users would search for. Result
| Rankings lets you, the portal administrator, _pin_ the Activities Content Page
| to the top of the results and _hide_ the liability waiver Web Content Article.
| In addition, a community member wrote a blog favorably reviewing the Lunar
| Resort, and you want that content added to searches for activities at the
| resort. 

![Figure x: The Lunar Resort wants to tweak these results: pin the Activities page to the top, and hide the legal content entirely.](../../images/search-result-rankings-todo.png) 

## Creating and Managing Result Rankings

To manipulate result rankings, create a new _Alias_ containing the keywords you
want to intercept. A search is performed, and the matching results are shown
(you can also do a separate search if you want to grab results that haven't even
been returned during a natural search for the alias keywords). Once you have the
results, choose to pin them or hide them as you please.

To create a new Result Rankings Alias:

1.  Navigate to Control Panel &rarr; Search Tuning &rarr; Result Rankings.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  On the New Ranking screen, enter one of the keywords or search phrases you
    want to intercept (it can be a phrase, instead of just one word; and don't
    worry, you can add more later) in the _Search Query_ field. 

    To limit the rankings intervention to just one index's results, enter the
    index name in the Index Name field.

    Click _Customize Results_.

A search query is executed. The results are displayed and the tools for pinning,
hiding, re-ordering, and adding results are made available. First, consider
whether to add one or more Aliases.

### Adding Aliases

The Customize Rankings screen is ready to use, but any intervention will only
apply to the search query you initially entered in the New Ranking screen. To
apply the customized rankings to additional search queries, add them as
_Aliases_. 

1.  In the Aliases field, enter the keyword or phrase to add as an Alias.

2.  To submit the keyword or phrase as an alias, click Enter or a comma in the
    Aliases filed. You can Add multiple aliases here. 

    ![Figure x: ](../../images/search-result-rankings-aliases.png)

Note that results not manipulated manually here will be returned as usual when
the alias term is queried for in the Search Bar. 

Now customize the rankings.

### Pinning and Hiding Results

To pin or hide rankings, hover over the result of interest: two icons appear,
one for pinning and one for hiding. Click the one that applies. Otherwise click
the Actions button (![Actions](../../images/icon-actions.png)), and select _Pin
Result_ or _Hide Result_. Once you select either option, it's applied
immediately. A pinned result moves to the top of the list, and a hidden result
disappears. Repeat the action as many times as necessary.

If you're done customizing the results, click _Save_ and it's applied
immediately.

![Figure x: ](../../images/search-result-rankings-pinned-result.png)

### Adding Results

To add a result that was not returned by searching for the first keyword or
phrase, click the _Add Result_ button and search for whichever asset it is that
you'd like to pin. 

![Figure x: ](../../images/search-result-rankings-add-result.png)

Click _Save_ if you're done customizing results.


### Re-Ordering Results

To re-order results, click the drag handle icon, drag the result, and drop it in the preferred location in the list. 

![Figure x: ](../../images/search-result-rankings-reorder.png)

Once finished customizing result rankings, click _Save_.

## Result Rankings Scope and Permissions

Because configuration of Result rankings happens at the virtual instance scope,
there are scoping and permissions behaviors to be aware of.

Scope is disregarded for pinned results: Pinned results existing in Site A will
always appear in searches from Site B, even if the Search . <!-- The View in
context URL brings the User to Site A?-->

Search from Result Rankings is global: When searching for results in Result
Rankings admin, relevant results from all sites are returned.

Permissions are applied as usual: If a User doesn't have permission to see an
asset, pinning it will not make it appear in the search results for that
User.

## Result Rankings Aliases versus Synonyms

[Synonyms](/docs/7-2/user/-/knowledge_base/u/synonyms) expand the search to
include additional (synonymous) keywords, so more results will be returned if
there are matches to the synonyms.

Result Rankings Aliases are just keywords that will also have the particular
ranking interventions applied to them. They won't cause an expansion of the
original query's results list.

Quoted aliases are not searchable (i.e. if you have an alias named "example" and
you search for it, you will actually perform a search for the exact match)

Actions that can be taken

Pin one or multiple results (and unpin it)
Hide a result (and unhide it)
Reorder results
Unpublish a ranking
Ranked versus unranked

