# The HTML Structure of portal_normal.ftl [](id=the-html-structure-of-portalnormal-ftl)

The `portal_normal.ftl` file is a template through which all the portlets on a
Liferay site are rendered. 

Look at the figure below to see what the finished HTML structure will look 
like:

![Figure 1: The HTML is broken up into sections.](../../images/html-diagram.png)

The HTML structure is broken up into three main sections: Banner, Content, and
Footer. The Banner contains the logo for the site, its name, the page title, and 
the main navigation. The Content `<div>` holds the breadcrumbs for the site and
the portlets on the page. Finally, the Footer holds the 'powered-by Liferay' and
social media links. The three main sections are all contained within a wrapper
`<div>` that's used to adjust the width of the site.

Now that you are familiar with the HTML structure, you can make some
modifications to the theme templates next.
