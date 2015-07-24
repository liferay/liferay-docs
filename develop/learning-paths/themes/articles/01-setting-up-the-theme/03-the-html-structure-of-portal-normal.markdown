# The HTML Structure of portal_normal.ftl

`portal_normal.ftl` is similar to the `index.html` of a website. You can think 
of It as a hub through which all of the theme templates are rendered to the 
browser.

Take a look at the figure below to see what the finshed HTML structure will look 
like:

![Figure 1: The HTML is broken up into sections.](../../images/html-diagram.png)

The HTML structure is broken up into three main sections: Banner, Content, and
Footer. The Banner contains the logo for the site, its name, the page title, and 
the main navigation. The Content `<div>` holds the breadcrumbs for the site, 
as well as the portlets for the page. Finally, the Footer holds the 
'powered-by Liferay' and social media links. The three main sections are all 
contained within a wrapper `div`, which will be used to adjust
the width of the site.

Now that you are familiar with the HTML structure, you can make some
modifications to the theme templates next.