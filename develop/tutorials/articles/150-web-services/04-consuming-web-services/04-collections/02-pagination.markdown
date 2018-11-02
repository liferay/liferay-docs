# Pagination [](id=pagination)

A small collection can be transmitted in a single response without difficulty. 
Transmitting a large collection all at once, however, can consume too much 
bandwidth, time, and memory. It can also overwhelm the user with too much data. 
It's therefore best to get and display the elements of a large collection in 
discrete chunks, or pages. 

By default, Liferay hypermedia REST APIs return paginated collections. The 
following attributes in the responses also contain the information needed to 
navigate between those pages: 

-   `totalItems`: The total number of elements in the collection.
-   `numberOfItems`: The number of elements in this response (e.g., in this 
    page). 
-   `view`: Links for navigating these collection pages: 
    -   `@id`: The current page's URL. 
    -   `first`: The first page's URL. 
    -   `last`: The last page's URL. 
    -   `previous`: The previous page's URL (if applicable). 
    -   `next`: The next page's URL (if applicable). 

For example, suppose that there are 123 users your portal and you want to get 
information on them. To do this, send an 
[authenticated request](/develop/tutorials/-/knowledge_base/7-1/making-authenticated-requests) 
to the person URL: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person

The response contains the first 30 users and URLs for navigating the rest of the 
collection. Note that the contents of the `member` attribute, which contains the 
users, are omitted here so you can focus on the metadata for navigating the 
collection: 

    {
        "totalItems": 123,
        "numberOfItems": 30,
        "view": {
            "@id": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "first": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "last": "http://localhost:8080/o/api/p/person?page=5&per_page=30",
            "next": "http://localhost:8080/o/api/p/person?page=2&per_page=30",
            "@type": ["PartialCollectionView"],
        },
        "@id": "http://localhost:8080/o/api/p/person",
        "member": [
            // ... array of users returned in the collection  ...
        ],
        // ... 
        "@type": ["Collection"],
    }

You can use this information in a client app to build a UI component for
navigating the pages. For example, you could link the *First* button to the URL
in the `first` attribute, the *1* button to the URL in the `@id` attribute, the
*Next* button to the URL in the `next` attribute, and the *Last* button to the
URL in the `last` attribute. 

![Figure 1: This simple UI lets users navigate the pages of data that the server returns.](../../../../images/apio-pagination-01.png)

If the user clicks the *Next* button, the client app could make this request: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person?page=2&per_page=30

The response includes updated page navigation info: 

    {
        "totalItems": 123,
        "numberOfItems": 30,
        "view": {
            "@id": "http://localhost:8080/o/api/p/person?page=2&per_page=30",
            "first": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "last": "http://localhost:8080/o/api/p/person?page=5&per_page=30",
            "next": "http://localhost:8080/o/api/p/person?page=2&per_page=30",
            "previous": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "@type": ["PartialCollectionView"],
        },
        "@id": "http://localhost:8080/o/api/p/person",
        "member": [
            // ... array of users returned in the collection ...
        ],
        // ...
        "@type": ["Collection"],
    }

This response includes a navigation link to go back a page (`previous`). The 
client app's navigation bar could then show a button for this action: 

![Figure 2: You can update the pagination UI as the user traverses the pages.](../../../../images/apio-pagination-02.png)

When the user reaches the last page, the client should send this request: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person?page=5&per_page=30

Since this request is for the last page of data, the response no longer includes 
a `next` link: 

    {
        "totalItems": 3,
        "numberOfItems": 30,
        "view": {
            "@id": "http://localhost:8080/o/api/p/person?page=5&per_page=30",
            "first": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "last": "http://localhost:8080/o/api/p/person?page=5&per_page=30",
            "previous": "http://localhost:8080/o/api/p/person?page=4&per_page=30",
            "@type": ["PartialCollectionView"],
        },
        "@id": "http://localhost:8080/o/api/p/person",
        "member": [
            // ... array of users returned in the collection ...
        ],
        // ...
        "@type": ["Collection"],
    }

Also note that the last page in this example includes only three elements. You
can reflect this (and remove the *Next* button) in the client's page navigation
UI: 

![Figure 3: The last page lists the number of items it displays, and no longer includes a *Next* button.](../../../../images/apio-pagination-03.png)

## Related Topics [](id=related-topics)

[Making Authenticated Requests](/develop/tutorials/-/knowledge_base/7-1/making-authenticated-requests)
