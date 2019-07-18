---
header-id: creating-display-pages
---

# Creating Display Page Templates

[TOC levels=1-4]

Display Page Templates are created initially in much the same way as Content
Pages. You  select any number of page fragments and add them to the page.
Display pages differ in that after you add the fragments, you can then map
editable fields in those fragments to the fields of a web content article. You
can learn more about creating Page Fragments in the 
[Creating Content Pages](/docs/7-2/user/-/knowledge_base/u/creating-content-pages)
article.

Looking at the example of a template for a long form article, we can see how
Display Page Templates utilize Page Fragments. The article can have an image, a
title (simple style text), a main body (rich text), a highlighted quote (simple
styled text), and then a standard footer. Your first step in creating the
Display Page Template is to create a Page Fragment which has all those fields
formatted the way you want them. Your fragment could have these fields:

-  Editable header
-  Editable Image
-  Editable rich text
-  Editable plain text (with block-quote styling)
-  Non-editable footer

To go along with this fragment, you could have a Web Content Structure with 
these fields:

-  Title (Text box)
-  Image (Documents and Media image)
-  Content (Web Content)
-  Quote (Text area)

![Figure 1: Connecting structure fields to fragment data.](../../../../../images/structure-to-fragment.png)

The Display Page Template maps the fields from the Web Content Structure to the
fragment. When the Display Page Template is assigned for an article with that
Structure, it appears on a display page with the formatting from the fragment.
