# Filter, sort and search [](id=filter-sort-and-search)

Filter, sort and search can be used together at the same time, in order to find the content you are interested in.

## Filter

It's often useful to filter large collections for the exact data that you need. Not all collections, however, allow filtering. The ones that support it contain the optional parameter `filter` in their OpenAPI profile.

To filter a collection based on the value of one or more fields, use the optional parameter `filter` following a subset of the oData standard.

Filtering mainly applies to fields indexed as keywords in Liferay Search. If the user is interested in finding content by terms contained in fields indexed as text, it is recommended to use Search param instead.

### Comparison Operators

| Operator  | Description          | Example                             |
|---------- |--------------------- |-------------------------------------|
| eq        | Equal                | addressLocality eq 'Redmond'        |
|           | Equal null           | addressLocality eq null             |
| ne        | Not equal            | addressLocality ne 'London'         |
|           | Not null             | addressLocality ne null             |
| gt        | Greater than         | price gt 20                         |
| ge        | Greater than or equal| price ge 10                         |
| lt        | Less than            | dateCreated lt 2018-02-13T12:33:12Z |
| le        | Less than or equal   | dateCreated le 2012-05-29T09:13:28Z |
| startsWith| Starts with          | addressLocality startsWith 'Lond'   |

### Logical Operators

| Operator  | Description | Example                      |
|---------- |------------ |------------------------------|
|and|Logical and |price le 200 and price gt 3.5          |
|or |Logical or |price le 3.5 or price gt 200            |
|not' |Logical not |not (price le 3.5)|

The `not` operator needs a space character after it.

### Grouping Operators

| Operator  | Description | Example                      |
|---------- |------------ |------------------------------|
|( ) |Precedence grouping |(price eq 5) or (addressLocality eq 'London')  |

### String functions

| Function  | Description | Example                      |
|---------- |------------ |------------------------------|
| contains  | Contains    |contains(title,'edmon')|

### Lambda Operators

Lambda operators evaluate a boolean expression on a collection. They must be prepended with a navigation path that identifies a collection.

| Lambda Operator  | Description | Example                                |
|----------------- |------------ |----------------------------------------|
| any              | Any         |keywords/any(k: contains(k,'substring1')|

The `any` operator applies a boolean expression to each collection member and evaluates to `true` if the expression is true for any member. 

### Escaping in Queries

You can escape a single quote in a value by adding another single quote. 

For example, to filter for a BlogsEntry whose headline is `New Headless APIs`:
```
?filter=headline eq 'New Headless APIs'
```

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
   
### Filtering in StructuredContent fields (ContentField)

To filter for a contentField value (that are dinamic, created by the end user), you have to use the endpoints that are scoped to an individual ContentStructure, specifically those with this URL:

    "/content-structures/{contentStructureId}/structured-contents"

## Search

It's often useful to search large collections with keywords. When searching, we are not interested in the specific field that contains our search terms but in getting results instead.

To filter a collection based on the value of one or more fields, use the optional parameter `search` following the terms you want to use. For example to search for all the BlogEntry fields for a value containing Oauth we can do:

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

Sorting collection results is another common task. Note, however, that not all collections allow sorting. The ones that support it contain the optional parameter `{lb}?sort{rb}` in their OpenAPI profile.

To get sorted collection results, append `?sort=<param-name>` to the request URL. For example, appending `?sort=title` to the request URL sorts the results by title. 

The default sort order is ascending (0-1, A-Z). To perform a descending sort, append `:desc` to the parameter name. For example, to perform a descending sort by title, append `?sort=title:desc` to the request URL. 

To sort by more than one parameter, separate the parameter names by commas and put them in order of priority. For example, to sort first by title and then by creation date, append `?sort=title,dateCreated` to the request URL. 

To specify a descending sort for only one parameter, you must explicitly specify ascending sort order (`:asc`) for the other parameters. For example: 

`?sort=headline:desc,dateCreated:asc`