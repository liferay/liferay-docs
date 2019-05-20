---
header-id: introduction-to-content-sets
---

# Introduction to Content Sets

[TOC levels=1-4]

In previous versions of the Liferay, the Asset Publisher was your most powerful tool for delivering content to users. With the Asset Publisher, administrators define either static lists of assets to display to a user or dynamic lists based on criteria like tags, categories, or asset type. In @product-ver@ Content Sets take the core idea of defining different types of asset lists and expands it. Content Lists are created outside of the context of a specific application or widget and can be used and re-used across different channels and applications.

The core idea of a Content Set is simple: an administrator defines a list of content, and then that list of content can be displayed somewhere. The way that the Content Set is displayed is determined by the method that is used to display it. For example, if the Content Set is being used by a smartwatch app, it could be displayed as a simple list of titles, and selecting a title would cause the full article to display on a connected mobile device. The same Content Set could be displayed in a web browser through the Asset Publisher with the full content of each article available to scroll through.

## Creating and Displaying Content Sets

Content Sets are created through the Site Administration interface. All the features for creating and managing Content Sets are contained here, and they are displayed using Liferay's widgets or [your own custom applications](dev-guide-link). Read our guides for information on [Creating Content Sets](user-guide-link) and [Displaying Content Sets](user-guide-link)

## Content Set Personalization

Content Sets can have variations driven by @product@'s Personalization engine. After you create a Content Set, if you have at least one User Segment created, you can create a personalized experience of the Content Set for that Segment. To learn to harness the power of experience personalization for Content Sets, see [Content Set Personalization](user-guide-link).

## Converting Asset Publisher Configurations to Content Sets

You may have already gone through a great deal of work to create a perfect, curated list of content through the Asset Publisher, but now you want to display that list elsewhere without duplicating your work. You can do that with Content Sets. Read the [Converting Asset Publisher Configuration to Content Sets guide](user-guide-link) to learn more.
