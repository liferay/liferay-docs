---
header-id: updating-the-resources-importer
---

# Updating the Resources Importer

[TOC levels=1-4]

The Resources Importer is now an OSGi module in Liferay's Web Experience
application suite. Since the suite is bundled with @product@, you don't have to
download the Resources Importer separately. The following components have been
updated and are the focus of this tutorial:

- Plugin properties
- Web content article files and folder structure
- Sitemap

| **Note:** Due to the page and article import order, articles that link to pages
| in the Site's layout cause a null pointer exception
| [issue](https://issues.liferay.com/browse/LPS-64859).
| These links have been removed from the example Lunar Resort theme's web content
| articles to avoid this issue.

Start updating the plugin properties for the Resources Importer. 