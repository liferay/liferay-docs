---
header-id: supporting-custom-content-types-in-content-and-display-pages
---

# Supporting Custom Content Types in Content and Display Pages

[TOC levels=1-4]

Content Pages and Display Page Templates can display several types of content
out-of-the-box:

- Web Content Article
- Document
- Blogs Entry

You can publish these content types in highly customizable ways using 
[Page Fragments](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments). 
You can use these page types to map fields of certain content (e.g., Web
Content) to fields defined in a Page Fragment. Then you can publish the content
on a page using the Page Fragment as a template. To see an example of how
Display Page Templates work, see the
[Display Page Template Example](/docs/7-2/user/-/knowledge_base/u/display-page-template-example). For more info on creating Content Pages, see the
[Building Content Pages](/docs/7-2/user/-/knowledge_base/u/building-content-pages)
article.

If you want to extend the Content Page or Display Page Template framework to
support other content types, you must use the
[Info framework](/docs/7-2/frameworks/-/knowledge_base/f/the-info-framework).

You must complete the following steps:

1.  Provide basic information about your custom content type.

2.  Provide your content type's fields so they're configurable in the Page
    Editor.

3.  Provide friendly URLs for your page type.

4.  Handle the information that the user is requesting.

As an example, you'll step through how to provide this information to the
Content Page and Display Page Template frameworks.

| **Note:** This section assumes you're customizing Display Page Templates'
| available content types. The same process outlined in these articles also
| applies to Content Pages, although it's not explicitly stated.

Continue on to begin!
