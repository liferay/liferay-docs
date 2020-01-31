---
header-id: graphql-filter-sort-and-search
---

# Filter, Sort, and Search

[TOC levels=1-4]

You can use @product@'s headless GraphQL APIs to search for the content you
want. You can also sort and filter content. 

## Filter

It's often useful to filter large collections for the exact data that you need.
Not all collections, however, allow filtering. The ones that support it contain
the optional parameter `filter`. To filter a collection based on the value of
one or more fields, use the `filter` parameter following a subset of the
[OData](https://www.odata.org) standard. 

Filtering mainly applies to fields indexed as keywords in @product@'s search. To
find content by terms contained in fields indexed as text, you should instead
use [search](#search). 

### Comparison Operators

| Operator     | Description           | Example                               |
|------------- |---------------------- | ------------------------------------- |
| `eq`         | Equal                 | `addressLocality eq 'Redmond'`        |
|              | Equal null            | `addressLocality eq null`             |
| `ne`         | Not equal             | `addressLocality ne 'London'`         |
|              | Not null              | `addressLocality ne null`             |
| `gt`         | Greater than          | `price gt 20`                         |
| `ge`         | Greater than or equal | `price ge 10`                         |
| `lt`         | Less than             | `dateCreated lt 2018-02-13T12:33:12Z` |
| `le`         | Less than or equal    | `dateCreated le 2012-05-29T09:13:28Z` |
| `startswith` | Starts with           | `startswith(addressLocality, 'Lond')`   |

### Logical Operators

| Operator     | Description  | Example                          |
| ------------ | ------------ | -------------------------------- |
| `and`        | Logical and  | `price le 200 and price gt 3.5`  |
| `or`         | Logical or   | `price le 3.5 or price gt 200`   |
| `not`        | Logical not  | `not (price le 3.5)`             |

Note that the `not` operator requires a trailing space. 

### Grouping Operators

| Operator  | Description         | Example                                         |
|---------- | ------------------- | ----------------------------------------------- |
| `( )`     | Precedence grouping | `(price eq 5) or (addressLocality eq 'London')` |

### String Functions

| Function    | Description | Example                   |
| ----------- | ----------- | ------------------------- |
| `contains`  | Contains    | `contains(title,'edmon')` |

### Lambda Operators

Lambda operators evaluate a boolean expression on a collection. They must be
prepended with a navigation path that identifies a collection. 

| Lambda Operator | Description | Example                                    |
| --------------- | ----------- | ------------------------------------------ |
| `any`           | Any         | `keywords/any(k:contains(k,'substring1'))` |

The `any` operator applies a boolean expression to each collection element and
evaluates to `true` if the expression is true for any element. 

### Escaping in Queries

You can escape a single quote in a value by escaping it with a backslash. For
example, to filter for a blog posting whose headline is `New Headless APIs`,
send this filter string to the filter parameter.

```
filter: \\"headline eq \'Title\'\\"
```

Here's an example of the full request: 

```bash
curl -X "POST" "http://localhost:8080/o/graphql" \
     -H 'Content-Type: text/plain; charset=utf-8' \
     -H 'Cookie: COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US; JSESSIONID=EFEEC1617529C7C85E8CCCE510B0F6CF' \
     -u 'test@liferay.com:test' \
     -d $'{
  "query": "query { blogPostings(siteKey: \\"guest\\", filter: \\"headline eq \'Title\'\\") { items {headline} page pageSize totalCount } }"
}'

```

And here's a possible response:

```json
{
  "items": [
    {
      "alternativeHeadline": "The power of OpenAPI & Liferay",
      "articleBody": "<p>We are happy to announce...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "dateModified": "2019-04-22T07:04:51Z",
      "datePublished": "2019-04-22T07:02:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "new-headless-apis",
      "headline": "New Headless APIs",
      "id": 59301,
      "numberOfComments": 0,
      "siteId": 20124
    }
  ],
  "page": 1,
  "pageSize": 20,
  "totalCount": 1
}
```

### Filtering in Structured Content Fields (ContentField)

To filter for a `ContentField` value (dynamic values created by the end user),
you must use the paths that are scoped to an individual `ContentStructure`. 

Find the ID of the `ContentStructure` and use it in place of
`{contentStructureId}` in this query: 

```
"contentStructureStructuredContents"
```

## Search

You can search large collections with keywords. Use search when you want results
from any field, rather than specific ones. To perform a search, use the optional
parameter `search` followed by the search terms. For example, this request
searches for all the `BlogEntry` fields containing Title: 

```bash
curl -X "POST" "http://localhost:8080/o/graphql" \
     -H 'Content-Type: text/plain; charset=utf-8' \
     -u 'test@liferay.com:test' \
     -d $'{
  "query": "query { blogPostings(siteKey: \\"guest\\", search: \\"Title\\") { items {headline} page pageSize totalCount } }"
}'
```

```json
{
  "items": [
    {
      "alternativeHeadline": "How to work with OAuth",
      "articleBody": "<p>To configure OAuth...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T09:35:09Z",
      "dateModified": "2019-04-22T09:35:09Z",
      "datePublished": "2019-04-22T09:34:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "authenticated-requests",
      "headline": "Authenticated requests",
      "id": 59309,
      "numberOfComments": 0,
      "siteId": 20124
    }
  ],
  "page": 1,
  "pageSize": 20,
  "totalCount": 1
}
```

## Sorting

Collection results can be sorted. Note, however, that not all collections allow
sorting. The ones that support it contain the optional parameter `{lb}?sort{rb}`
in their GraphQL definition.

To get sorted collection results, append `sort:\"<param-name>\"` to the request
URL. For example, appending `sort:\"title\"` to the request URL sorts the
results by title. 

The default sort order is ascending (0-1, A-Z). To perform a descending sort,
append `:desc` to the parameter name. For example, to perform a descending sort
by title, append `sort:\"title:desc\"` to the request URL. 

To sort by more than one parameter, separate the parameter names by commas and
put them in order of priority. For example, to sort first by title and then by
creation date, append `sort:\"title,dateCreated\"` to the request URL. 

To specify a descending sort for only one parameter, you must explicitly specify
ascending sort order (`:asc`) for the other parameters: 

```
sort:\"headline:desc,dateCreated:asc\"
```

## Flatten

The `flatten` query parameter returns all resources and disregards folders or other
hierarchical classifications. Collection GraphQL specifications define if
`flatten` is available. Its default value is `false`, so a document query to the
root folder returns only the documents in that folder. 

With `flatten` set to `true`, the same query returns documents in any
subfolders, regardless of how deeply those folders are nested. Setting `flatten`
set to `true` and querying for documents in a Site's root folder returns all the
documents in the Site. 

