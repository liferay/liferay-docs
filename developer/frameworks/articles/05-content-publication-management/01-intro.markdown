---
header-id: content-publication-management
---

# Content Publication Management

[TOC levels=1-4]

Managing content publication is primarily controlled by two frameworks:

<!-- - Change Lists -->
- [Export/Import](#export-import)
- [Staging](#staging)

These features give you the power to plan page publication and manage content.
You can leverage the APIs offered by these frameworks to manage your app's
publication process.

The Export/Import and Staging frameworks are closely tied together. They both
implement the same interfaces and share the same extension points. By
implementing one of these frameworks in your app, you automatically leverage the
other. There are a few simple configurations that can be set to customize them
separately. You'll learn about this later.

Export/Import can be viewed as the base feature with Staging built on top of it
(although they're implemented together). You can visit the
[Export/Import](/docs/7-2/frameworks/-/knowledge_base/frameworks/export-import)
framework's articles for the base APIs that both it and the Staging frameworks
share. You must implement these to implement Staging. Reference the
[Staging](/docs/7-2/frameworks/-/knowledge_base/frameworks/staging) framework's
articles for additional configuration pertaining only to it.

Here are a few of the things you can do with the Export/Import and Staging APIs. 

## Export/Import

The Export/Import feature adds another dimension to your application by
providing a framework for producing reusable content and importing content from
other places. By creating [LAR files (Liferay ARchive)](/docs/7-2/reference/-/knowledge_base/reference/liferay-archive-lar-file), 
you can export your data, import it to another system, or even use this feature
to back up your content.

![Figure 1: Leveraging the Export/Import feature in your app is useful for sharing content.](../../images/export-import-preview.png)

Export/Import is a default feature for many of @product@'s out-of-the-box apps.
It offers an intuitive GUI for managing export/import processes and tracking the
history of previous export/imports. You can also easily pick subsets of data to
export based on content type, date range, and configurations. Importing content
is done using a modern drag-and-drop interface or by selecting the LAR file from
your file system.

## Staging

Staging gives you a test environment where you can modify your site and
test different configurations without changing your live site. When you implement
Staging in your app, its content can be tracked by the staged environment,
allowing users to stage your app's data before releasing it to the world.

Here's an example of some functionality you can add to your app with Staging's
APIs:

- Local Staging environment tracking
- Remote Staging environment tracking
- Single asset publishing
- Content tracking on page variations

Continue on to learn more about the frameworks that bring content publication
management to life!
