---
header-id: how-to-get-site-id
---

# How to get siteId

[TOC levels=1-4]

Several APIs (generally all collection APIs) need the `siteId` parameter to to
execute requests. The `siteId` is the internal identifier of the Site where
that content was created.

## Using siteId or siteKey

In all the APIs available from 7.2 GA2+, the `siteKey` is also accepted as
a valid parameter. The `siteKey` is the external name of the Site (for example
`Guest`). 

In the REST APIs, you can use `siteKey` in all the places that expect a `siteId`;
in GraphQL APIs, there are two different parameters: `siteId` and `siteKey`. 

Using the `siteKey` is recommended over `siteId` in all situations because it's
more recognizable, doesn't expose an internal parameter, and doesn't change in
import/export processes. 

## Obtain siteId

There are several ways to retrieve the `siteId`:

* Use the Site API to query it by name, friendly URL or in the list of a User's
  Sites.
* From @product@'s UI in the Site Administration menu (not recommended).
* From the `Group` table in the database (not recommended). 
* From the `ThemeDisplay` object in JavaScript or Java: 
 
        themeDisplay.getSiteGroupId()

![Figure 1: GraphQL BlogPostings definition](../../../images/rest-site-id.png)
