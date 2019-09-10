---
header-id: clay-css-and-themes
---

# Clay CSS and Themes

[TOC levels=1-4]

[Lexicon](https://liferay.design/lexicon/) is a design language that provides a 
common framework for building consistent UIs. [Clay](https://clayui.com/docs/css-framework/scss.html), 
the web implementation of Lexicon, is an extension of Bootstrap's open source 
CSS Framework. Bootstrap is by far the most popular CSS framework on the web. 
Built with Sass, Clay CSS fills the front-end gaps between Bootstrap and the 
specific needs of @product@. 

Bootstrap features have been extended to cover more use cases. Here are some of 
the new components added by Clay CSS: 

- Aspect Ratio
- Cards
- Dropdown Wide and Dropdown Full
- Figures
- Nameplates
- Sidebar / Sidenav
- Stickers
- SVG Icons
- Timelines
- Toggles

Several reusable CSS patterns have also been added to help accomplish time 
consuming tasks such as these:

- truncating text
- content filling the remaining container width
- truncating text inside table cells
- table cells filling remaining container width and table cells only being as 
  wide as their content
- open and close icons inside collapsible panels
- nested vertical navigations
- slide out panels
- notification icons/messages
- vertical alignment of content

[Clay CSS](https://claycss.com/) is bundled with two sub-themes: [Clay Base](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay) 
and [Atlas](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay/atlas). 
Clay Base is @product@'s Bootstrap API extension. It adds all the features and 
components you need and inherits Bootstrap's styles. As a result, Clay Base is 
fully compatible with [third party themes](/docs/7-2/frameworks/-/knowledge_base/f/integrating-third-party-themes-with-clay) 
that leverage Bootstrap's Sass variable API. 

Atlas is @product@'s custom Bootstrap theme that is used in the Classic Theme. 
Its purpose is to overwrite and manipulate Bootstrap and Clay Base to create its 
classic look and feel. Atlas is equivalent to installing a Bootstrap third party 
theme.

| **Note:** It is not recommended to integrate third party themes with Atlas, as
| it adds variables and styles that are outside the scope of Bootstrap's API.

This section covers these topics:

- Customizing the Atlas and Clay base themes
- Integrating third party themes with Clay
