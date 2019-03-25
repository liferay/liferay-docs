---
header-id: content-publication-management
---

# Content Publication Management

[TOC levels=1-4]

Managing content publication is primarily controlled by two frameworks:

<!-- - Change Lists -->
- [Export/Import](#export-import)
- [Staging](#staging)

These features give you the power to plan page publication and manage content
in @product@. You can leverage the APIs offered by these frameworks to manage
your app's publication process.

The Export/Import and Staging frameworks are closely tied together, both
implementing the same interfaces and sharing the same extension points. This
means that by implementing one of these frameworks in your app, you can leverage
the other with a few simple configurations.

Generally, Export/Import can be viewed as the base feature and Staging is built
on top of it. This documentation is positioned with this in mind. You can
visit the
[Export/Import](/developer/frameworks/-/knowledge_base/7-2/export-import)
framework's articles for the base APIs that both it and the Staging frameworks
share. Reference the
[Staging](/developer/frameworks/-/knowledge_base/7-2/staging) framework's
articles for additional APIs/configuration pertaining only to it.

Here are a few of the things you can do with the Export/Import and Staging APIs. 

## Export/Import

The Export/Import feature adds another dimension to your application by letting
you produce reusable content and import content from other places. You can even
leverage this feature to backup your content. @product@ packages its content in
LAR files (Liferay ARchive).

![Figure 1: Leveraging the Export/Import feature in your app is useful for sharing content.](../../images/export-import-preview.png)

Export/Import is a default feature for many of @product@'s out-of-the-box apps.
It offers an intuitive GUI that lets you manage current export/import processes
and track the history of previous export/imports. You can also easily pick
specific parts of data to export based on content type, date range, and
configurations. Importing content into your app can be accomplished using the
modern drag-and-drop interface or selecting the LAR file from your file system.

## Staging

Staging lets you differentiate between a live environment viewable by users at
real time and a temporary environment where you can create and test your site's
setup. Implementing Staging in your app lets its content be tracked by the
temporary staged environment, allowing you to properly plan your app's page
configuration and content before it's released to the world.

Here's an example of some functionality you can add to your app with Staging's
APIs:

- Local Staging environment tracking
- Remote Staging environment tracking
- Single asset publishing
- Content tracking on page variations
