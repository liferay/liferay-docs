---
header-id: defining-ordering-of-service-entity-instances
---

# Defining Ordering of Service Entity Instances

[TOC levels=1-4]

Often, you want to retrieve multiple instances of a given entity and list them
in a particular order. The `service.xml` file lets you specify the default order
of your entities. 

Suppose you want to return `GuestbookEntry` entities by their creation date.
It's easy to specify these default orderings:

```xml
<order>
    <order-column name="createDate" order-by="desc" />
</order>
```

You can enter `asc` or `desc` for ascending or descending order. 

Now that you know how to order your service entities, the last thing to do is to
define the finder methods for retrieving entity instances from the database. 
