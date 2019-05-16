---
header-id: pagination
---

# Pagination

[TOC levels=1-4]

Collection resources are returned in pages of information. 
[Working with Collections of Data](/develop/tutorials/-/knowledge_base/7-2/working-with-collections-of-data) 
explains this in more detail. Here, you'll learn how to work with collection 
pages. 

For example, suppose that there are 123 users your portal and you want to get 
information on them. To do this, send an 
[authenticated request](/develop/tutorials/-/knowledge_base/7-2/making-authenticated-requests) 
to the UserAccount URL: 

    curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts" \
             -u 'test@liferay.com:test'

The response contains the first 30 users and IDs for navigating the rest of the 
collection. Note that most of the contents of the `items` attribute, which 
contains the users, are omitted here so you can focus on the metadata for 
navigating the collection: 

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

The attributes `page` and `pageSize` allow client applications to navigate 
through the results. For example, such a client could send a request for a 
specific page. This example gets the second page (`?page=2`) of documents that 
exist on the site with the ID `20124`: 

    curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/documents?page=2" \
          -u 'test@liferay.com:test'

Similarly, you can customize the number of elements per page via the optional 
parameter `pageSize` (e.g., `?pageSize=20`). 

## Related Topics

[Working with Collections of Data](/develop/tutorials/-/knowledge_base/7-2/working-with-collections-of-data)

[Making Authenticated Requests](/develop/tutorials/-/knowledge_base/7-2/making-authenticated-requests)
