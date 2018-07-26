# Navigating Between Related Resources [](id=navigating-between-related-resources)

A main benefit of hypermedia REST APIs is the ability to discover and navigate 
all the services from a single starting point, the API's home URL. This is 
possible because each endpoint contains links to its related resources or 
endpoints. 

You can therefore navigate from: 

-   The home URL to any of the root resources. The 
    [getting started tutorial on discovering the API](liferay.com) 
    contains an example of this. 
-   A collection to any of its elements. 
-   A resource to a related resource. 

In each of these cases, navigation is a matter of locating and following the 
links included in the server's response. Therefore, besides the home URL, you 
don't need advance knowledge of any URL or strategy for constructing URLs. The 
URLs you need are already in the response. The tutorials in this section show 
you how to traverse APIs via these URLs. 

