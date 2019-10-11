---
header-id: creating-and-managing-pages
---

# Adding Pages to Sites

[TOC levels=1-4]

In the previous section, you learned how to create sites. You may have gathered 
from that section that sites aren't particularly useful without pages. In
fact, sites primarily exist for the sake of organizing pages and content, so 
now you'll learn about the different types of pages in Liferay, and how to 
select the best tools based on your use cases. You'll also learn how to manage 
pages and use various configuration options.

Before diving into page creation, you should understand the two major page
types provided in @product-ver@:

[*Content Pages*](/docs/7-2/user/-/knowledge_base/u/creating-content-pages):
This new page type is flexible, especially for non-technical users. You can
build pages using content created from pre-defined fragments, which themselves
can contain widgets. 

[*Widget Pages*](/docs/7-2/user/-/knowledge_base/u/adding-applications-to-a-page):
@product@'s traditional page type is made up of one or more widgets. There are
some use cases (particularly if a page's sole purpose is to host an application)
to prefer widget pages.

You should always opt for Content Pages, unless there's a concrete reason
otherwise. Content Pages offer many key features of Widget Pages plus more. Some
key features of Content Pages include
[personalized Experiences](/docs/7-2/user/-/knowledge_base/u/segmentation-and-personalization)
and
[A/B Testing](https://help.liferay.com/hc/en-us/articles/360034856751-A-B-Testing)
Furthermore, Content Pages are easier to use and are more powerful for
non-technical users compared to Widget Pages.

Why would someone prefer Widget Pages? Widget Pages were once the only page type
available in earlier versions of @product@, so they're more familiar than
Content Pages.

Additionally, there are still a few things that Widget Pages provide that are
not possible with Content Pages:

- *Developing an advanced custom layout*: Using Content Pages, authors can
  create their own page layouts. This prevents developers from creating
  pre-selectable, custom layouts with FreeMarker like Widget Pages allowed for.
  Though Content Pages let you create a layout visually (a more user-friendly
  approach), the programmatic approach of Widget Pages allows for more advanced
  capabilities.

- *User-Customizable columns*: This was a rarely used feature of Widget Pages
  that is not provided in Content Pages. If your page requires a
  user-customizable column, you must use a Widget Page.

- *Using Staging with Page Variations*: Content Pages do not support Staging's
  Page Variations. This avoids possible confusion with the similar capability to
  create variations of a page that are used in personalization and A/B Testing.

- *Widget permissions*: You can configure widget permissions on a Widget Page.
  This is not yet possible for Content Pages; it's planned for a future release.

- *Widget Look & Feel*: On Widget Pages you can access the
  [Look and Feel](/docs/7-2/user/-/knowledge_base/u/look-and-feel-configuration)
  tool for widgets, offering fine-grained control over its CSS. This is not
  available for widgets on Content Pages, since the look and feel of your
  content is defined in the theme or by using Fragments.

Continue on to learn more about creating pages!
