---
header-id: embedding-portlets-in-themes
---

# Embedding Portlets in Themes

[TOC levels=1-4]

You may occasionally want to embed a portlet in a theme, making the portlet 
visible on all pages where the theme is used. Since there are numerous drawbacks 
to hard-coding a specific portlet into place, the *Portlet Providers* framework 
offers an alternative that displays the appropriate portlet based on a given 
entity type and action. 

The first thing you should do is open the template file for which you want to 
declare an embedded portlet. For example, the `portal_normal.ftl` template file 
is a popular place to declare embedded portlets. To avoid problems, it's usually 
best to embed portlets with an entity type and action, but you may encounter 
circumstances where you'll want to hard code it by portlet name. Both methods 
are covered in this section. These topics are covered:

- Embedding a portlet by entity type and action
- Embedding a portlet by instance name and ID
