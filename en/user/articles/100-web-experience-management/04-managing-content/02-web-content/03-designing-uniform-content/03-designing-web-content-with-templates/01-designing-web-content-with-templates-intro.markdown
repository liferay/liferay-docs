---
header-id: designing-web-content-with-templates
---

# Designing Web Content with Templates

[TOC levels=1-4]

While templates aren't required to display web content (i.e. you can use a 
[Display Page Template](https://portal.liferay.dev/docs/7-2/user/-/knowledge_base/u/display-pages-for-web-content) 
to map the structure fields), developers can create templates to display the 
elements of the structure in the markup they want. In essence, templates are 
scripts that tell @product@ how to display content in the structure. Changes to 
the structure require corresponding changes to any templates that use it, 
because new or deleted fields produce errors on the page. Unless a template is 
generic and meant to be embedded in other templates, a template must have a 
matching structure. 

@product@ only supports creating web content templates in FreeMarker (FTL) by
default. FreeMarker is the preferred, recommended language, and what you'll use
in the next example.
