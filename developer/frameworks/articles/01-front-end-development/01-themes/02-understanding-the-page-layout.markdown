---
header-id: understanding-the-page-layout
---

# Understanding the Page Layout

Understanding the layout's structure is crucial to targeting the correct markup 
for styling, organizing your content, and creating your site. Your page layout 
is unique to the requirements and design for your site. The Unstyled theme's 
default page layout is organized into three key sections:

- **Header:** contains the navigation, site logo and title (if shown), and 
  sign-in link when the user isn't logged in
- **Main Content:** contains the portlets or fragments for the page
- **Footer:** contains additional information, such as the copyright or 
  author

![Figure 1: The page layout is broken into three key sections.](../../../images/portal-layout-sections.png)

## Portlets or Fragments

The `#content` `Section` makes up the majority of the page. Portlets or 
fragments are contained inside the `#main-content` `div`. @product@ ships with 
a default set of applications that provide common functionality, such as forums 
and Wikis, documents and media, blogs, and more. For more information on using 
@product@ and its native portlets, see the 
[User & Admin documentation](/discover/portal). 
You can also create custom portlets for your site. Portlets can be added via the 
Add Menu (referred to as widget), included in a sitemap through the 
[Resources Importer](/develop/tutorials/-/knowledge_base/7-2/importing-resources-with-a-theme), 
or they can be 
[embedded in the page's theme](/develop/tutorials/-/knowledge_base/7-2/embedding-portlets-in-themes). 
See the 
[portlet tutorials section](/develop/tutorials/-/knowledge_base/7-2/portlets) for more information 
on creating and developing portlets. 

You can target the elements and IDs shown in the table below to style the page:

| Element | ID | Description |
| --- | --- | --- |
| div | #wrapper | The container div for the page contents | 
| header | #banner | The page's header |
| section | #content > #main-content | The main contents of the page (portlets or fragments) |
| footer | #footer | The page's footer |

![Figure 2: Each section of the page has elements and IDs that you can target for styling.](../../../images/portal-layout-elements.png)

As shown in the diagram above, you can also add 
[fragments](/discover/portal/-/knowledge_base/7-2/creating-fragments) 
to a page. You can have a page that contains portlets or a content page that 
contains fragments, not both. Fragments are components---composed of CSS, 
JavaScript, and HTML---that provide key pieces of functionality for the page 
(i.e. a carousel or banner). @product@ provides an editor for creating 
collections of fragments that you can then add to the page. These fragments can 
be edited on the page to suit your vision. 

## Layout Templates, Page Templates, and Site Templates

The page layout within the `#content` Section is determined by the 
[Layout Template](/develop/tutorials/-/knowledge_base/7-2/layout-templates-intro). 
Several layout templates are included out-of-the-box. You can also 
[create custom layout templates manually](/develop/tutorials/-/knowledge_base/7-2/creating-layout-templates-manually) 
or with the 
[Liferay Theme Generator's layout sub-generator](/develop/tutorials/-/knowledge_base/7-2/creating-layout-templates-with-the-themes-generator). 

Layout templates can be pre-configured depending on the 
[page type](/discover/portal/-/knowledge_base/7-2/page-types-and-templates) 
you choose when the page is created. Along with setting the types of portlets to 
include on the page, the page template may also define the default layout 
template for the page. Climbing further up the scope chain, you can create 
[site templates](/discover/portal/-/knowledge_base/7-2/building-sites-from-templates), 
which can define the pages, page templates, layout templates, and theme(s) to 
use for site pages. 

## Product Navigation Sidebars and Panels

The main page layout also contains a few notable sidebars an administrative user 
can trigger through the Control Menu. These are listed below:

- **Add Menu:** lets you add portlets (widgets) and fragments (if applicable) to 
  the page
- **Control Menu:** provides the main navigation for accessing the Add Menu, 
  Product Menu, and Simulation Panel
- **Product Menu:** contains administrative apps, configuration settings, and 
  user account settings, profile, and dashboard page
- **Simulation Panel:** simulates how the page appears on different devices

![Figure 3: Remember to account for the product navigation sidebars and panels when styling your site.](../../../images/portal-layout-nav-control-menu.png)

![Figure 4: The Add Menu pushes the main contents to the left.](../../../images/portal-layout-nav-add-menu.png)

![Figure 5: The Product Menu pushes the main contents to the right.](../../../images/portal-layout-nav-product-menu.png)

![Figure 6: The Simulation Panel pushes the main contents to the left.](../../../images/portal-layout-nav-simulation-panel.png)

When styling the page, you must keep the navigation menus in mind, especially 
for absolutely positioned elements, such as a fixed navbar. If the user is 
logged in and can view the Control Menu, the fixed navbar must have a top margin 
equal to the Control Menu's height. See the 
[Product Navigation tutorials section](/develop/tutorials/-/knowledge_base/7-2/product-navigation) 
for more information on customizing these menus. 

## Related Topics

- [Creating Layout Templates with the Layouts Sub-generator](/develop/tutorials/-/knowledge_base/7-2/creating-layout-templates-with-the-themes-generator)
- [Bundling Layout Templates with a Theme](/develop/tutorials/-/knowledge_base/7-2/including-layout-templates-with-a-theme)
- [Creating Themes](/develop/tutorials/-/knowledge_base/7-2/creating-themes)
