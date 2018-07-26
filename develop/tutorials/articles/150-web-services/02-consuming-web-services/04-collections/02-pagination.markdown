# Pagination [](id=pagination)

A small collection can be transmitted in a single response without difficulty. 
Transmitting a large collection all at once, however, can consume too much 
bandwidth, time, and memory. And even if it were efficient, you probably 
wouldn't want to overwhelm the end user by showing them all the data at once. 
It's therefore best to get and display the elements of a large collection in 
discrete chunks, or pages. 

By default, the collections returned by Liferay hypermedia APIs are paginated. 
The responses also contain the information needed to navigate between those 
pages, in the following attributes: 

-   `totalItems`: The total number of elements in collection.
-   `numberOfItems`: The number of elements returned in this response (e.g., in 
    this page). 
-   `view`: Links for navigating these pages:
    -   `@id`: The current page's URL. 
    -   `first`: The first page's URL. 
    -   `last`: The last page's URL. 
    -   `previous`: The previous page's URL (if applicable). 
    -   `next`: The next page's URL (if applicable).

For example, suppose that there are 123 users your portal and you want to get 
information on them. To do this, send an 
[authenticated request](liferay.com) 
to the person URL: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person

You receive the first 30 users, and URLs for navigating the rest of the 
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

You can use this information in your client app to build a UI component that 
lets the user navigate the pages: 

![Figure 1: This simple UI lets users navigate the pages of data that the server returns.](../../../../images/apio-pagination-01.png)

For example, you could link the *First* button to the URL in the `first` 
attribute, the *1* button to the URL in the `@id` attribute; the *Next* button 
to the URL in the `next` attribute; and the *Last* button to the URL in the 
`last` attribute. If the user clicks the *Next* button, your client app could 
make the following request: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person?page=2&per_page=30

This updates the navigation info in the response: 

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

Note that the response includes a navigation link to go back a page 
(`previous`). You could then change your navigation bar to show a button for 
this action: 

![Figure 2: You can update the pagination UI as the user traverses the pages.](../../../../images/apio-pagination-02.png)

When the user reaches the last page, your client should send this request: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person?page=5&per_page=30

Since the request was for the last page of data, the response no longer includes 
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

Also note that in this example, the last page includes only 3 elements. You can 
reflect this (and remove the *Next* button) in your client's pagination UI: 

![Figure 3: The last page lists the number of items it displays, and no longer includes a *Next* button.](../../../../images/apio-pagination-03.png)
