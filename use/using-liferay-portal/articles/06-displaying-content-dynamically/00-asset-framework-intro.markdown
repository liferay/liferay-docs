# Displaying Content Dynamically [](id=displaying-content-dynamically)

Any type of content in Liferay is considered an asset. In chapters 2 and 3, we
examined Liferay's most common type of asset: web content. Other types of assets
include blog posts, wiki articles, message board posts, bookmarks, and
documents. It's possible for developers to define custom asset types that
utilize Liferay's asset framework. Originally, the asset framework was created
to provide a mechanism for adding tags to blog entries, wiki articles, and web
content without reimplementing the same functionality multiple times. The asset
framework has been greatly extended since then and it now supports tags,
categories, vocabularies, comments, ratings, and asset relationships.

This chapter covers the following topics:

- Tagging and categorizing content
- Using targeted, single value, and multi-value vocabularies
- Using faceted search
- Using the Asset Publisher
- Setting up display pages
- Adding relationships between assets

The Asset Publisher portlet is designed to display multiple assets. It has quite
a few configuration options which we'll cover in this chapter. By default,
abstracts (previews) of recently published assets are displayed by the Asset
Publisher portlet and links to their full views are provided. You can configure
the Asset Publisher portlet to display a table of assets, a list of asset
titles, or the full content of assets. You can also configure the Asset
Publisher to display only certain kinds of assets and you choose how many items
to display in a list. The Asset Publisher portlet is very useful for displaying
chosen types of content, for displaying recent content, and for allowing users
to browse content by tags and categories. The Asset Publisher is designed to
integrate with the Tags Navigation and Categories Navigation portlets to allow
this.
