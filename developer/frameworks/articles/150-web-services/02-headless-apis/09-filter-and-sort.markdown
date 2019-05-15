---
header-id: filter-sort-and-search
---

# Filter, Sort, and Search

[TOC levels=1-4]

You can use @product@'s headless REST APIs to search for content you're 
interested in. You can also sort and filter content. Here, you'll learn how. 

## Filter

It's often useful to filter large collections for the exact data that you need. 
Not all collections, however, allow filtering. The ones that support it contain 
the optional parameter `filter` in their OpenAPI profile. To filter a collection 
based on the value of one or more fields, use the `filter` parameter following a 
subset of the oData standard. 

Filtering mainly applies to fields indexed as keywords in @product@'s search. To 
find content by terms contained in fields indexed as text, you should instead 
use 
[search](#search). 

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
| `startsWith` | Starts with           | `addressLocality startsWith 'Lond'`   |

### Logical Operators

| Operator     | Description  | Example                          |
| ------------ | ------------ | -------------------------------- |
| `and`        | Logical and  | `price le 200 and price gt 3.5`  |
| `or`         | Logical or   | `price le 3.5 or price gt 200`   |
| `not`        | Logical not  | `not (price le 3.5)`             |

Note that the `not` operator needs a space character after it. 

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
| `any`           | Any         | `keywords/any(k: contains(k,'substring1')` |

The `any` operator applies a boolean expression to each collection element and
evaluates to `true` if the expression is true for any element. 

### Escaping in Queries

You can escape a single quote in a value by adding another single quote. For 
example, to filter for a blog posting whose headline is `New Headless APIs`, 
append this filter string to the request URL: 

    ?filter=headline eq 'New Headless APIs'

Here's an example of the full request: 

    curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/?filter=headline%20eq%20%27New%20Headless%20APIs%27" \
         -u 'test@liferay.com:test'

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
      "lastPage": 1,
      "page": 1,
      "pageSize": 20,
      "totalCount": 1
    }

### Filtering in Structured Content Fields (ContentField)

To filter for a `ContentField` value (dynamic values created by the end user), 
you must use the endpoints that are scoped to an individual `ContentStructure`. 
To do so, find the ID of the `ContentStructure` and use it in place of 
`{contentStructureId}` in this URL: 

    "/content-structures/{contentStructureId}/structured-contents"

## Search

It's often useful to search large collections with keywords. Use search when you 
want results from any field, rather than specific ones. To perform a search, use 
the optional parameter `search` followed by the search terms. For example, this 
request searches for all the `BlogEntry` fields containing OAuth: 

    curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/?search=OAuth" \
         -u 'test@liferay.com:test'

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
      "lastPage": 1,
      "page": 1,
      "pageSize": 20,
      "totalCount": 1
    }

## Sorting

Sorting collection results is another common task. Note, however, that not all
collections allow sorting. The ones that support it contain the optional
parameter `{lb}?sort{rb}` in their OpenAPI profile.

To get sorted collection results, append `?sort=<param-name>` to the request
URL. For example, appending `?sort=title` to the request URL sorts the results
by title. 

The default sort order is ascending (0-1, A-Z). To perform a descending sort,
append `:desc` to the parameter name. For example, to perform a descending sort
by title, append `?sort=title:desc` to the request URL. 

To sort by more than one parameter, separate the parameter names by commas and
put them in order of priority. For example, to sort first by title and then by
creation date, append `?sort=title,dateCreated` to the request URL. 

To specify a descending sort for only one parameter, you must explicitly specify
ascending sort order (`:asc`) for the other parameters. For example: 

    ?sort=headline:desc,dateCreated:asc

## Flatten

Some collections (as defined in their OpenAPI profile) allow the query parameter 
`flatten`, which returns all resources and disregards folders or other 
hierarchical classifications. This parameter's default value is `false`, so a 
document query to the root folder returns only the documents in that folder. 
With `flatten` set to `true`, the same query also returns documents in any 
subfolders, regardless of how deeply those folders are nested. In other words, 
setting `flatten` set to `true` and querying for documents in a Site's root 
folder returns all the documents in the Site. 
