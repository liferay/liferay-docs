# Using the Asset Publisher [](id=using-the-asset-publisher)

As we create web content, it's important to keep in mind that to Liferay, the
pieces of content are assets, just like message board entries and blog posts.
This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of
assets such as images, documents, blogs, and of course, web content. This helps
in creating a more dynamic web site: you can place user-created wiki entries,
blog posts or message board messages in context with your content. Let's look at
some of its features.

## Querying for Content [](id=querying-for-content)

The Asset Publisher portlet is a highly configurable application that lets you
query for mixed types of content on the fly. By giving you the ability to
control what and how content is displayed from one location, the Asset Publisher
helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Configuration* link in the
portlet's menu (the wrench icon).

The ability to configure how content is displayed and selected by your users
further demonstrates the flexibility of the Asset Publisher. You get to choose
how content is displayed. You can select it manually for display in a similar
way to the Web Content Display portlet or you can set up predefined queries and
filters and let the portal select the content for you, based on its type or its
tags and categories.

Let's first look at how we might select content manually. You'll see that it's
very similar to the Web Content Display portlet.

### Selecting Assets Manually [](id=selecting-assets-manually)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell
the Asset Publisher that you want to select content manually. You can select
what you want to be published within the portlet, or you can create new content
from within the Asset Publisher.

![Figure 5.12: Selecting assets manually is very similar to the Web Content
Display portlet, except you have many other content types to choose
from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the
content right where you are. You can create blogs, bookmarks, calendar entries,
documents, images, and of course, web content. Anything you create here is added
to the list below of assets that are displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can
pick from existing content in the portal that either you or your users have
created. Has someone written an excellent wiki page that you want to highlight?
Select it here, and it will be displayed.

The Asset Publisher enables you to mix and match different content types in the
same interface. Once you have your content selected, you can move on to the
display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select
content dynamically.

### Selecting Assets Dynamically [](id=selecting-assets-dynamically)

The Asset Publisher's default behavior is to select assets dynamically according
to rules that you give it. These rules can be stacked on top of each other so
that they compliment each other to create a nice, refined query for your
content. You have the following options for creating these rules:

**Scope:** Choose the scopes from which the content should be selected. This can
be either the scope of current site, the global scope, or both.

**Asset Type:** Choose whether you'll display any asset or only assets of a
specific type, such as only web content, only wiki entries, or any combinations
of multiple types.

![Figure 5.13: You can filter by tags and categories, and you can set up as many
filter rules as you
need.](../../images/04-web-content-asset-publisher-filter.png)

**Filter Rules:** Add as many filters on tags or categories as you like. You can
choose whether the content contains or does not contain any or all categories or
tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you
can then decide how the content will be displayed.

The Display Settings section gives you precise control over the display of your
assets. There are a multitude of options available to configure how you want
your content to appear. You can configure the style, length of abstracts,
behavior of the asset link, maximum items to display, pagination type and file
conversions. Additionally, you can enable printing, flags, ratings, comments and
comment ratings, and these work the same way they do in the Web Content Display
portlet.

You can display the content returned by the filters above in order by title,
create date, modified date, view count and more in ascending or descending
order. For instance, you may have a series of "How To" articles that you want
displayed in descending order based on whether the article was tagged with the
*hammer* tag. Or, you may want a series of video captures to display in
ascending order based on a category called *birds*. You can also group by
*Asset*, *Type* or *Vocabularies*. Vocabularies are groups of categories defined
by administrators in the *Categories* section of the Control Panel.

In the *Ordering and Grouping* section of the Asset Publisher, you have great
control over how content is ordered and grouped in the list, but this is only
one aspect of how your content will be displayed. You can refine the display
through many other display settings.
