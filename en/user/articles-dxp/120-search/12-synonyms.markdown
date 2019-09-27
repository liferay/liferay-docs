---
header-id: search-tuning-synonym-sets
---

# Search Tuning: Synonym Sets

[TOC levels=1-4]

Starting with @product-ver@ Service Pack 1, new search tuning features are
available for administrative Users: Synonym Sets is one of them.

Synonym Sets are mappings that you (the admin) create, so that if a User
searches for a certain keyword or phrase, the synonymous terms in your mapping
are also searched. Matches to synonyms keywords are scored equally to matches
with the exact keyword by the search engine.

**Lunar Resort Use Case:** Multiple content creators at the Lunar Resort write
blogs about a variety of topics. Consistent terminology is a problem for some
concepts. One writer might use the term "rover" for the vehicle that travels
across the moonscape, while another uses "lunar cart" or "moon ATV". As the
portal administrator, you must ensure that the search experience is such that
searching for any of those keywords returns all relevant results. Synonym Sets
are a key ally in this pursuit.

## Requirements and Limitations

Search tuning features like Synonym Sets are only supported when using
Elasticsearch as the search engine. If you're using Solr, make sure you disable
the 
[search tuning features](/docs/7-2/deploy/-/knowledge_base/d/installing-solr#blacklisting-elasticsearch-only-features)
(Synonym Sets and Result Rankings) when you upgrade your installation to
@product@ Service Pack 1 (Fix Pack 2).

As of the initial release (@product-ver@ SP-1), Synonym Sets work with fields
indexed in two locales: English and Spanish. Thus, the assets supporting
localization out-of-the-box work with Synonym Sets. Technically, this means that
synonym searches operate on fields indexed with the `en_*` and `es_*` suffixes.
Read the 
[multi-language search documentation](/docs/7-2/user/-/knowledge_base/u/searching-for-localized-content) 
to learn which native @product@ assets/fields support localization in the search
index. All asset types that index their data into English and Spanish are
analyzed with a synonyms-aware analyzer and can be found during a synonym
search.

The `=>`
[format](https://www.elastic.co/guide/en/elasticsearch/guide/current/synonym-formats.html)
supported in Elasticsearch is not supported through the Synonyms Set UI.

## Creating and Managing Synonym Sets

Create a synonym set by adding as many synonymous keywords to a set as you
want. Once the synonym set is saved, any searches in the same company scope
(that's any site from the Virtual Instance where the synonyms were configured)
take effect.

![Figure 1: Add as many synonymous keywords to a set as you'd like.](../../images/search-synonym-set.png)

To create a synonym set,

1. Navigate to Control Panel &rarr; Search Tuning &rarr; Synonyms.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  Enter the list of synonyms in the set. The input of a synonym is
    accomplished by clicking _Enter_ or by entering a comma.

4.  When the list is finished, click _Publish_.

The available synonym sets appear and can be managed in bulk or individually.
The management options are to update a synonym set or delete one or more
synonym sets.

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

## Known Issues

There are several [known issues](https://issues.liferay.com/browse/LPS-99658)
for Synonym Sets. These are some of the most important ones:

[LPS-100272](https://issues.liferay.com/browse/LPS-100272): 
Reindexing permanently deletes all Synonym Sets. Please refer to the ticket for
a way to backup and preserve (restore) Synonym Sets across reindex operations. 

[LPS-98126](https://issues.liferay.com/browse/LPS-98126)
Users can create duplicate Synonym Set entries and update other Synonym Sets unintentionally.

## Related Resources

* <https://www.elastic.co/guide/en/elasticsearch/guide/current/synonyms.html>
* <https://www.elastic.co/guide/en/elasticsearch/reference/6.8/analyzer-anatomy.html>
* <https://www.elastic.co/guide/en/elasticsearch/reference/6.8/analysis-synonym-graph-tokenfilter.html>
* <http://lucene.apache.org/core/7_7_0/analyzers-common/org/apache/lucene/analysis/en/EnglishPossessiveFilter.html>
