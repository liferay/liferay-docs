---
header-id: custom-facet
---

# Custom Facet

[TOC levels=1-4]

All Facets are configurable, allowing you to narrow down search results based on
a shared characteristic (all Blog Entries, for example). The Custom Facet lets
you create entirely new Facets. The first thing to do is enter the Custom
Facet's configuration screen.

![Figure 1: Custom Facets must be configured first.](../../../images/search-custom-facet-jobtitle.png)

The screenshot above shows a Custom Facet with the Job Title of each User that
matched the search. The next screenshot shows how it was configured.

![Figure 2: Configure a Custom Facet in no time.](../../../images/search-custom-facet-config.png)

**Aggregation Field**
: Specify the non-analyzed keyword field whose value is used to create the facet
terms. If the value of the search result's `jobTitle_sortable` field is _upsale
manager_, that's what appears in the Custom Facet as a term.

**Custom Heading**
: Enter a human readable heading for the Custom Facet. 

**Custom Parameter Name**
: Set the URL parameter to use for the facet. With the configuration pictured
above, searching for *jane* and clicking on _chief of security_ produces the URL

    http://localhost:8080/web/guest/search?q=jane&jobtitle=chief%20of%20security

**Max Terms**
: Set the maximum number of facet terms to display, regardless of how
many matching terms are found for the facet.

**Frequency Threshold**
: Set the minimum frequency required for terms to appear in the result list. For
example, if the frequency threshold of a facet is set to `3`, a term with two
matching results doesn't appear in the term result list.

**Display Frequencies**
: Choose whether to display the term frequencies.

## Finding Indexed Fields

To use the Custom Facet, you must know which non-analyzed keyword field to
specify. 

To browse the entire list of available fields, inspect the field mappings from
Control Panel &rarr; Configuration &rarr; Search. Alternatively, use your search
engine's API. 

For Elasticsearch, access the field mappings from your terminal using CURL to
call the [Get Mapping API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-get-mapping.html):

    curl -X GET "localhost:9200/_mapping/LiferayDocumentType"?pretty

Solr uses the [ListFields API](https://lucene.apache.org/solr/guide/6_6/schema-api.html#SchemaAPI-ListFields):

    curl http://localhost:8983/solr/liferay/schema/

Here's a snippet of output from the Elasticsearch example:

    "ddmStructureKey": {
        "store": true,
        "type": "keyword"
    },
    "ddmTemplateKey": {
        "store": true,
        "type": "keyword"
    },
    "defaultLanguageId": {
        "store": true,
        "type": "keyword"
    },
    "description": {
        "store": true,
        "term_vector": "with_positions_offsets",
        "type": "text"
    },
    "discussion": {
        "store": true,
        "type": "keyword"
    },

Use Custom Fields to aggregate facet terms by shared non-analyzed keyword
field values.

