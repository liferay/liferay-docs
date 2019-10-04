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

Before diving into page creation, you should be informed on the two major page
types provided in @product-ver@:

[*Content Pages*](/docs/7-2/user/-/knowledge_base/u/creating-content-pages): the
new page type providing more flexibility, especially for non-technical users.

[*Widget Pages*](/docs/7-2/user/-/knowledge_base/u/adding-applications-to-a-page):
the traditional page type that is more rigid. There may still be some use cases
where it's preferred.

You should always opt for Content Pages, unless there's a concrete reason
otherwise. Content Pages are being actively developed and offer many key
features of Widget Pages plus more. Some key features of Content Pages include
[personalized Experiences](/docs/7-2/user/-/knowledge_base/u/segmentation-and-personalization)
and
[A/B Testing](/docs/7-2/user/-/knowledge_base/u/a-b-testing). Furthermore,
Content Pages are easier to use and are more powerful for non-technical users
compared to Widget Pages.

Why would someone prefer Widget Pages? Usually Widget Pages are preferred
because they were the only page type available in earlier versions of @product@,
so they're more familiar than Content Pages.

In addition, there are several other things that Widget Pages provide that are
not possible with Content Pages:

- *Developing an advanced custom layout*: Content Pages let page authors create
  their own page layout. As a result, developers can't create their own custom
  layouts with FreeMarker like Widget Pages allowed for.

- *User-Customizable columns*: This was a rarely used feature of Widget Pages
  that is not provided in Content Pages. If your page requires a
  user-customizable column, you must leverage a Widget Page.

- *Using Staging with Page Variations*: Content Pages do not support Staging's
  Page Variations. This avoids possible confusion with the similar capability to
  create variations of a page that are used in personalization and A/B Testing.

- *Widget permissions*: You can configure widget permissions on a Widget Page.
  This is not possible for Content Pages.

- *Widget Look & Feel*: Widget Pages let you access the
  [Look and Feel](/docs/7-2/user/-/knowledge_base/u/look-and-feel-configuration)
  tool for widgets, offering fine-grained control over its CSS. This is not
  available for widgets on Content Pages, since the look and feel of your
  content should be defined in the theme or by using Fragments.

Continue on to learn more about creating pages!
