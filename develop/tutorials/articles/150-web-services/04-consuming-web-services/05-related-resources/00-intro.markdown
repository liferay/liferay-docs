---
header-id: navigating-between-related-resources
---

# Navigating Between Related Resources

A key benefit of Liferay's hypermedia REST APIs is discovering and navigating 
all the services from a single starting point, the API's home URL. This is 
possible because each endpoint contains links to its related resources or 
endpoints. 

You can therefore navigate from 

-   The home URL to any of the root resources. The 
    [getting started tutorial on discovering the API](/docs/7-1/tutorials/-/knowledge_base/t/get-started-discover-the-api) 
    contains an example of this. 
-   A collection to any of its elements. 
-   A resource to a related resource. 

Navigation is a matter of locating and following the links in the server's 
response. Besides the home URL, you therefore don't need advance knowledge of
specific URLs or strategies for constructing them, because they're already in
the response. The tutorials in this section show you how to navigate APIs via
these URLs. 

