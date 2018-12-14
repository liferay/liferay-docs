# Understanding the Page Layout [](id=understanding-the-page-layout)

Understanding the page layout is crucial to knowing what markup to target for 
styling, how to organize your content, and how to create your site. Your page 
layout is unique to the requirements and design for your site. The default page 
layout that the Unstyled theme provides is organized into three key sections:

- **Header**: contains the navigation, site logo and title (if shown), and 
  sign-in link when the user isn’t logged in
- **Main Content**: contains the portlets or fragments for the page
- **Footer**: contains any additional information, such as the copyright or 
  author

![Figure 1: The page layout is broken into three key sections.](../../images/portal-layout-sections.png)

## Portlets or Fragments [](id=portlets-or-fragments)

The `#content` `Section` makes up the majority of the page. The portlets or 
fragments are contained inside the `#main-content` `div`. While you can write 
all of your content manually in your theme templates if you wish, @product@ 
provides a CMS with several portlets OOTB that you can use to help build your 
site. After all, that’s one of the major benefits of using @product@. For more 
information on using @product@'s native portlets and CMS system, see the 
[User & Admin documentation](/discover/portal). 
You can also create custom portlets for your site. Portlets can be added via the 
Add Menu (referred to as widget), included in a sitemap through the 
[Resources Importer](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-a-theme), 
or they can be 
[embedded in the page's theme or layout template](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates). 
See the 
[portlet tutorials section](/develop/tutorials/-/knowledge_base/7-1/portlets) 
for more information on creating and developing portlets. 

You can target the elements and IDs shown in the table below to style the page:

| Element | ID | Description |
| --- | --- | --- |
| div | #wrapper | The container div for the page contents | 
| header | #banner | The page’s header |
| section | #content > #main-content | The main contents of the page (portlets or fragments) |
| footer | #footer | The page’s footer |

![Figure 2: Each section of the page has elements and IDs that you can target for styling.](../../images/portal-layout-elements.png)

As shown in the diagram above, you can also add 
[fragments](/discover/portal/-/knowledge_base/7-1/creating-fragments) 
to a page. You can either have a page that contains portlets or a content page 
that contains fragments, not both. Fragments are components, composed of CSS, 
JS, and HTML, that provide a key piece of functionality for the page (i.e. a 
carousel or banner). @product@ provides an editor that you can use to create 
collections of fragments that you can then add to the page. These fragments can 
be made editable so that you can edit them inline on the page to suit your 
vision. 

## Layout Templates, Page Templates, and Site Templates [](id=layout-templates-page-templates-and-site-templates)

The page layout within the #content Section, that is to say how the portlets or 
fragments are organized on the page, is determined by the 
[Layout Template](/develop/tutorials/-/knowledge_base/7-1/layout-templates-intro). 
Several layout templates are included out-of-the-box. You can also 
[create custom layout templates manually](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually) 
or with the 
[Liferay Theme Generator's layout sub-generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator).

Layout templates can be pre-configured depending on the 
[page type](/discover/portal/-/knowledge_base/7-1/page-types-and-templates) 
you choose when the page is created. Along with setting the types of portlets to 
include on the page, the page template may also define the default layout 
template for the page. Climbing further up the scope chain, you can create 
[site templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates), 
which can define the pages, page templates, layout templates, and theme(s) to 
use for a site pages.

## Product Navigation Sidebars and Panels [](id=product-navigation-sidebars-and-panels)

The main page layout also contains a few notable sidebars that the user can 
trigger through the Control Menu, if they are signed in and have the proper 
roles. These are listed below:

- **Add Menu**: lets you add portlets (widgets) and fragments (if applicable) to 
  the page
- **Control Menu**: provides the main navigation for accessing the Add Menu, 
  Product Menu, and Simulation Panel
- **Product Menu**: contains administrative apps, configuration settings, and 
  user account settings, profile, and dashboard page
- **Simulation Panel**: lets the user simulate how the page will display across 
  different devices

![Figure 3: Remember to account for the product navigation sidebars and panels when styling your site.](../../images/portal-layout-nav-control-menu.png)

![Figure 4: The Add Menu pushes the main contents to the left.](../../images/portal-layout-nav-add-menu.png)

![Figure 5: The Product Menu pushes the main contents to the right.](../../images/portal-layout-nav-product-menu.png)

![Figure 6: The Simulation Panel pushes the main contents to the left.](../../images/portal-layout-nav-simulation-panel.png)

When styling the page, you must keep the navigation menus in mind, especially 
for absolutely positioned elements, such as a fixed navbar. If the user is 
logged in and can view the Control Menu, the fixed navbar would have to have a 
top margin equal to the Control Menu's height. See the 
[Product Navigation tutorials section](/develop/tutorials/-/knowledge_base/7-1/product-navigation) 
for more information on customizing these menus.
