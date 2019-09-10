---
header-id: updating-the-resources-importer
---

# Updating the Resources Importer

[TOC levels=1-4]

The Resources Importer is now an OSGi module bundled with @product@, so you 
don't have to download the Resources Importer separately. The following 
components have been updated and are the focus of this section:

- Plugin properties
- Web content article files and folder structure
- Sitemap

| **Note:** Due to the page and article import order, articles that link to pages
| in the Site's layout cause a null pointer exception
| [issue](https://issues.liferay.com/browse/LPS-64859).
| These links have been removed from the Lunar Resort theme's web content
| articles to avoid this issue.

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/updating-6-2-liferay-plugin-package-properties">Let's Go<span class="icon-circle-arrow-right"></span></a>
