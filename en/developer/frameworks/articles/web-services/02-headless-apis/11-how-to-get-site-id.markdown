---
header-id: how-to-get-site-id
---

# How to get siteId

[TOC levels=1-4]

Several APIs (generally all collection APIs) need the `siteId` parameter to be able to execute the requests. The `siteId` is the internal identifier of the `Group` where that content was created.

## Using siteId or siteKey

In all the APIs available from 7.2 GA2 onwards, the `siteKey` is also accepted as a valid parameter. The `siteKey` is the external name of the site (for example `Guest`). 

In all REST APIs is allowed in all the places that expect a `siteId`, in GraphQL APIs there are 2 different parameters: `siteId` and `siteKey`. 

Using the `siteKey` is recommended over `siteId` in all situations because it's more recognizable, doesn't expose an internal parameter and doesn't change in import/export processes, just to mention a few reasons.

## Obtain siteId

There are several ways of retrieving it:

 * Using the site API to query it by name, friendly URL or in the list of sites of a specific user.
 * It's displayed in the @product@'s UI via the Site Administration menu.
 
 ![Figure 1: GraphQL BlogPostings definition](../../../images/rest-site-id.png)
 
 * It can be retrieved from the database, under the table `Group` or as the `groupId` column in all the contents.
 * It's available in the Javascript/Java context of each site under the code 
 
        themeDisplay.getSiteGroupId()