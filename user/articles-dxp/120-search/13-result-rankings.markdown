# Customizing Search Result Rankings

**Availability/Compatibility:**

Starting with @product-ver@ Service Pack 1, new search tuning features are
available for administrative Users: Result Rankings is one of them.

Search Tuning features like Result Rankings are only supported when using
Elasticsearch as the search engine.

**Introduce and Describe:**
Result Rankings a brute force for intervening into the relevance scoring of the
search engine, by doing these things:


1.  Designate that certain results should appear at the top of the results if
    they are matched with a certain keyword. This is the idea of _pinning_
    results to the top of the results list.

2.  By contrast, hide results that shouldn't appear in certain searches at all. 

3.  Add results that aren't returned by a certain search.

4.  Configure a search page to see the results from the Result Rankings
    customization and the un-manipulated results. LPS-96212

Pin, hide, and add search results with Result Rankings.

**Use Case:**

At the Lunar Resort website, interested site visitors often search for specific
activities, entering keywords like "rover races", "atv rentals", and "lunar
golf". For all of these, the Lunar Resort wants a certain 
[Content Page](/docs/7-2/user/-/knowledge_base/u/creating-content-pages)
to always appear at the top of the search results. This is the go-to Activities
page in the Lunar Resort where guests can go to find all of the resort's
adventurous offerings, including lunar rover races, ATV rentals, and information
about golfing packages. This is a prime use case for Result Rankings. By
contrast, the Lunar Resort does not want the legal liability waiver form to
appear during a search for fun activities: that's a bridge to be crossed once
guests are already signing up for the activity. It shouldn't pollute a search
for fun activities, even though it contains many of the keywords Users would
search for. Result Rankings lets you, the portal administrator, _pin_ the
Activities Content Page to the top of the results and _hide_ the liability
waiver Web Content Article. In addition, a community member wrote a blog
favorably reviewing the Lunar Resort, and you want that content added to
searches for activities at the resort. 

![Figure x: The Lunar Results wants to tweak these results: pin the Activities page to the top, and hide the legal content entirely.](../../images/search-result-rankings-todo.png) 

**How To:**

To manipulate result rankings, create a new _Alias_ containing the keywords you
want to intercept. A search is performed, and the matching results are shown
(you can also do a separate search if you want to grab results that haven't even
been returned during a natural search for the alias keywords). Once you have the
results, choose to pin them or hide them as you please.

**How To Details:**

1.  Navigate to Control Panel &rarr; Search Tuning &rarr; Result Rankings.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  Enter one of the keywords you want to intercept (it can be a phrase, instead
    of just one word; and don't worry, you can add more later) in the _Search
    Term_ field. 

    To limit the rankings intervention to just one index's results,
    enter the index name in the Index Name field.

    Click _Customize Results_.

4.  You're taken to a page with the keyword(s) displayed as an _Alias_, and the
    search results that resulted.

5.  From here the world (or at least the search index) is your oyster:

    To add more keywords (or search terms, if you prefer), click _Add an Alias_
    and enter additional search keywords or phrases to which this rankings
    intervention will apply. 

    ![Figure x: ](../../images/search-result-rankings-aliases.png)

    To alter the rankings, hover over the result of interest: two icons appear,
    one for pinning and one for hiding. Click the one that applies. Otherwise
    click the Actions button (![Actions](../../images/icon-actions.png)), and
    select _Pin Result_ or _Hide Result_. Once you select either option, it's
    applied immediately. A pinned result moves to the top of the list, and a
    hidden result disappears. Repeat the action as many times as necessary.

    ![Figure x: ](../../images/search-result-rankings-pinned-result.png)

    To add a result that was not returned by searching for the first keyword or
    phrase, click the _Add Result_ button and search for whichever asset it is
    that you'd like to pin. 

    ![Figure x: ](../../images/search-result-rankings-add-result.png)


**Trying it out:**

**More Details and Warnings:**

- Result Rankings Scope

    - Search User: Pinned results will be returned disregarding the scope of the
    search executed (i.e. Pinned results from Site A will show up in searches in
    Site B despite what the search scope is)

    - Search Admin: When searching for results in Result Rankings admin, relevant
    results from all sites will show up

- Pinned results will be permission checked normally (i.e. if a user doesn't
    have permissions to see an asset, the user won't see the asset even if it's
    pinned)

- Make the distinction between Results Ranking aliases and Synonyms

    Synonyms expand the search to include addition (synonymous) keywords, so
    more results will be returned.

    Result Rankings Aliases are just all the keywords that will also have the
    particular ranking interventions applied to them.

- Quoted aliases are not searchable (i.e. if you have an alias named "example"
    and you search for it, you will actually perform a search for the exact
    match)

Actions that can be taken

Pin one or multiple results (and unpin it)
Hide a result (and unhide it)
Reorder results
Unpublish a ranking
Ranked versus unranked

**Questions For Search Team:**

*[Result Rankings Questions]*: This thread contains questions I encountered while writing the In-Progress Result Rankings Docs at https://issues.liferay.com/browse/LRDOCS-5910: 

Some answers may be contained in the LPS story tickets or other sources. If so, feel free to write "Check the stories" in response instead of duplicating the information.

1.  It looks like, when you add an "alias", it's not adding keywords to the
    search query (keyword1 AND keyword2,...), and thus expanding the results.
    It's also not identical to synonyms.

2.  Some assets look much different (e.g., Form Records, Users) when searched in
    Result Rankings UI and in the Search Results widget.

    See the screenshots for an example

    Actually a DDM Structure is also returned in Result Rankings, and I'm not
    sure why. I would think this results list should perfectly match my Search
    Page Results widget's list of results.

    Is this intended?

3.  This is an opinion. I think empty search should be supported in the _Add a
    Result_ view, for cases when the Admin wants to browse all the assets.

4.  To delete rankings, I have to perform the action twice. The first time it
    doesn't work, the second time it does. Probably the same as LPS-99865

