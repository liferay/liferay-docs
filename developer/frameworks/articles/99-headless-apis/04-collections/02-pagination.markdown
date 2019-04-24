# Pagination [](id=pagination)

A small collection can be transmitted in a single response without difficulty. 
Transmitting a large collection all at once, however, can consume too much 
bandwidth, time, and memory. It can also overwhelm the user with too much data. 
It's therefore best to get and display the elements of a large collection in 
discrete chunks, or pages. 

By default, Liferay Headless REST APIs return paginated collections. The 
following attributes in the responses also contain the information needed to 
navigate between those pages: 

-   `totalCount`: The total number of this resource's items.
-   `pageSize`: The number of this resource's items to be included in this
    response. 
-   `page`: The number of the current page.
-   `lastPage`: The last page's number. 
-   `items`: The elements present in this page. The information received in the items element is the whole data of the elements, there’s no need to perform additional GET requests for each individual elements.
-   `id`: Each item has an id, to allow a request to retrieve more information.

For example, suppose that there are 123 users your portal and you want to get 
information on them. To do this, send an [authenticated request]() to the UserAccount URL: 

     curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts" \
             -u 'test@liferay.com:test'

The response contains the first 30 users and ids for navigating the rest of the 
collection. Note that most of the contents of the `items` attribute, which contains the 
users, are omitted here so you can focus on the metadata for navigating the 
collection: 

    {
          "items": [
            {
              "id": 20130,
              ...
            },
            {
              "id": 59347,
              ...
            }
          ],
          "lastPage": 5,
          "page": 1,
          "pageSize": 30,
          "totalCount": 123
        }
        
The attributes page and pageSize allow client applications to navigate through the results, to do a request for the next page you just have to do this request:

     curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/documents?page=2" \
          -u 'test@liferay.com:test'
          
In the same way, to customize the number of elements per page, use the optional parameters `pageSize`.

## Related Topics [](id=related-topics)

[Making Authenticated Requests]()