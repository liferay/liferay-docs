---
header-id: search-tuning-synonym-sets
---

# Search Tuning: Synonym Sets

[TOC levels=1-4]

Starting with @product-ver@ Service Pack 1, new search tuning features are
available for administrative Users: Synonym Sets is one of them.

Search Tuning features like Synonym Sets are only supported when using
Elasticsearch as the search engine.

Synonym Sets are mappings that you (the admin) create, so that if a User
searches for a certain keyword, the synonyms in your mapping are also searched.
Matches to synonymous keywords are scored equally to matches with the exact
keyword by the search engine.

Read the [multi-language search documentation](LINK TO UPDATED DOCS) to see
which native @product@ assets/fields are supported for synonym searches. What
does localized search have to do with synonyms? All asset types that index their
data into localized fields will be analyzed with a synonyms-aware analyzer, and
able to be found during a synonym search.

| **Lunar Resort Use Case:** Multiple content creators at the Lunar Resort write
| blogs about a variety of topics. Consistent terminology is a problem for some
| concepts. One writer might use the term "rover" for the vehicle that travels
| across the moonscape, while another uses "lunar cart" or "moon atv". As the
| portal administrator, you must ensure that the search experience is such that
| searching for any of those keywords returns all relevant results. Synonym Sets
| are a key ally in this pursuit.

## Creating and Managing Synonym Sets

Create a synonym set by adding as many synonymous keywords to a set as you'd
like. Once the synonym set is saved, any searches in the same company scope
(that's any site from the Virtual Instance where the synonyms were configured)
will take effect.

![Figure 1: Add as many synonymous keywords to a set as you'd like.](../../images/search-synonym-set.png)

To create a synonym set,

1. Navigate to Control Panel &rarr; Configuration &rarr; Search Tuning.

2.  In the Synonym Sets tab, click the Add button
    (![Add](../../images/icon-add.png)).

3.  Enter the list of synonyms in the set. The input of a synonym is
    accomplished either by clicking _Enter_ or by entering a comma.

4.  When the list is finished, click _Publish_.

The available synonym sets are listed, and can be managed in bulk or
individually. The management options are to update a synonym set, or delete one
or more synonym sets.

![Figure 2: Synonym sets can be managed in bulk.](../../images/search-synonym-sets.png) 

To edit or delete a single synonym set, click the Actions button
(![Actions](../../images/icon-actions.png)) for the synonym set and choose Edit
or Delete.

## Using Synonym Sets

When you have a synonym set defined, the synonyms are ready for use. To test
them, find a Search Bar anywhere in the virtual instance and enter a keyword
from one of your synonym sets. Results matching the keyword and any synonym are
returned in the Search Results widget.

![Figure 3: The Blogs Entry does not contain the word "rover" but it can be matched because of a synonym set mapping "cart" as its synonym. The synonym is even highlighted.](../../images/search-synonomous-result.png)

## Which Fields are Supported with Synonym Sets?

To leverage synonym sets, a field being searched must use an analyzer that can
recognize your synonyms. The search infrastructure's backend has been adapted to
do this for most fields that are commonly involved in full text search. However,
if the indexing code for an asset doesn't index it's data into the right fields,
it won't work with synonyms. So how can you identify what content types and
which fields will work with the synonyms feature? Read the [multi-language
search documentation](LINK TO UPDATED DOCS) to see which native @product@
assets/fields are supported. All asset types that index their data into
localized fields will be analyzed with a synonyms-aware analyzer, and able to be
found during a synonym search.

