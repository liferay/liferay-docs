---
header-id: defining-relationships-between-service-entities
---

# Defining Relationships Between Service Entities

[TOC levels=1-4]

Relationships between database entities or Java objects are necessary for most
applications. The Guestbook application, therefore, defines a relationship
between a Guestbook and its entries.

As mentioned earlier, each entry must belong to a particular Guestbook.
Therefore, each `GuestbookEntry` entity must relate to a `Guestbook`
entity. 

Create the `GuestbookEntry` entity's fields: 

```xml
<entity name="GuestbookEntry" local-service="true" uuid="true" remote-service="true">

	<column name="entryId" primary="true" type="long" />
	<column name="name" type="String" />
	<column name="email" type="String" />
	<column name="message" type="String" />
	<column name="guestbookId" type="long" />
```

Note the last field in the list is the `guestbookId` field. Since it's the same
name as the `Guestbook` object's primary key, a relationship is created between
the two objects. If you're using Liferay @ide@, you can see this relationship in
its diagram mode. 

![Figure 1: Relating entities is a snap in Liferay @ide@'s *Diagram* mode for `service.xml`.](../../../images/service-builder-relate-entities.png)

Congratulations! You've related two entities. 

Next, add the instance, audit, and status fields mentioned from the previous
step to enable Liferay's multi-tenancy, audit, and workflow features. 

Now that your entity columns are in place and entity relationships are
established, you can specify the default order in which the entity instances are
retrieved from the database. 
