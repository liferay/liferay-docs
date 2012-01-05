# Managing Pages in Liferay Portal

With most products, you would learn what the software can do in terms of setting up your users and security model, and then start building your system. You'd design your infrastructure and get your server environment up and running while your developers write the applications that live on your web site. With Liferay Portal, however, you start farther ahead. Liferay Portal is more than just a *container* for applications with a robust security model. It already includes many of the applications you'll need, out of the box, ready to go, and integrated with all the user management and security features you've already learned about.

Perhaps the key application that ships with Liferay is Liferay's Web Content Management system (WCM). We call it the key application because it's the one that almost everybody uses, all web sites have content. Liferay's WCM empowers you to manage all the content on your site quickly, easily, and in the browser. Beyond managing existing content, Liferay WCM lets users easily create and manage everything from a simple article containing text and images to fully functional web sites. Web publishing works alongside Liferay Portal's larger collection of applications, which means that you can add shopping cart functionality, visitor polls, web forms, site collaboration tools, and more. Everything is done with our collection of easy-to-use tools with familiar rich-text editors and intuitive interface.

This chapter covers all aspects of Liferay WCM, including:

- Page types
- Layouts
- Page and content permissions
- Importing and exporting content
- Content creation and editing
- Staging
- Content publishing
- Structures and templates
- WCM Workflow
- Asset publisher

By the time we're done, you should be able to apply all these concepts to your own content. To demonstrate Liferay's Content Management features, we'll create and manage content on the portal for *Nose-ster*, a new social network where people are connected based on what their noses look like.

First, a little housekeeping. If we're going to be *Nose-ster*, our portal should also be called Nose-ster. To set general information about your portal like the name and mail domain, go to the control panel and select *Portal Settings* under the Portal heading. You could set up the configuration for Nose-ster as follows.

![Figure 2.1 Changing Portal Settings](../../images/04-web-content-changing-settings.png)

You can also customize the logo in the top left corner of every page by selecting *Display Settings* under the *Miscellaneous* tab on the panel to the right. Once you've made the changes, we can begin creating pages.

## Page Creation and Management

You have a few options for accessing the page creation interface. To unify this, it necessitates that we cover the Dockbar's *Manage* menu slightly out of order. There are two interfaces to be aware of: *Site Pages* and *Page*. You can get to these from multiple places. Depending on what you're editing and where you are on the portal, you'll use either the *Manage* menu or the control panel to work with your pages. From the control panel, make sure that you have the correct site selected in the context menu and click the *Site Pages* link in the content section. If you're currently navigated to the site you wish to manage, click *Manage* from the Dockbar and select *Site Pages*. This is the exact same interface that you see in the control panel. To modify the single page in the site that you're on, click *Manage* and select *Page*. 

![Figure 2.1: Managing Individual Pages](../../images/04-web-content-managing-single-page.png)

For convenience, you can also navigate to the Sites page under the Portal section of the control panel and click *Actions* &rarr; *Manage Pages*. To quickly add a single page while you're browsing a site, click *Add* from the Dockbar and select *Page*, and a page is added immediately. You can then enter a name for the page and work on it immediately.

![Figure 2.2: Managing Site Pages](../../images/04-managing-site-pages.png)

*Site Pages* is an interface to view existing pages, create new pages, view pages, and export or import pages using Liferay Archive (LAR) files. Note that you can switch between managing a set of pages and managing a single page using the left-hand side navigation menu. Click on *Public Pages* or *Private Pages* to manage the group or click on an individual page to manage just that one. Switching views like this changes this list of available tabs to the right. By default, liferay.com, which we renamed nosester.com, contains a single public page called *Welcome*.

Liferay's page groups are always associated with sites. Even users' personal pages are part of their personal sites. All pages must belong to one of two sets of pages: public pages or private pages. By default, anyone, even guests, can access a site's public pages. Private pages are accessible only to users who are members of the site which owns the pages. This means that the private pages of an organization's site would be viewable only by members of the organization. 

Regardless of whether the pages are public or private, Liferay uses the same interface to manage them. Let's look at this interface more closely. 

### Managing Pages

From the Manage Site Pages dialog box, you can add a page to the site by clicking the *Add Page* button. Because *Public Pages* is selected on the left, clicking *Add Page* here adds a top level page next to the Welcome page. You can, however, nest pages as deeply as you like. To create a sub-page under the Welcome page, select the *Welcome* page first and then create your page. If you later decide you don't like the order of your pages, you can drag and drop them in the list to put them in whatever order you want. Let's go ahead and add another top level page and name it *Community*. We'll use this page for the Recent Bloggers and Wiki portlets.

![Figure 2.3: Adding Pages](../../images/04-web-content-add-page.png)

When you create a new page, you can create either a blank page or a page prepopulated with portlets from a page template. When you're entering the name of the page, you can select from a list of page templates that are currently available. To view the pages once you add them, click the *View Pages* button. This is how you'd populate your pages with content and applications. This is covered in succeeding chapters.

If you're using the Manage Pages interface to create a new page, you'll have some additional options to create different types of pages. There are **Portlet Pages**, **Panel Pages**, **Embedded Pages**, **URL Pages**, and **Link to Page**. By default all pages are created as portlet pages, but in some situations you might want to use one of the other options.

**Portlet Pages** are the pages that we're usually talking about. They have a layout which you can drag and drop portlets into. The overwhelming majority of pages that you create will be portlet pages.

**Panel Pages** can have any number of portlets on them, as selected by an administrator, but only one will be displayed at a time. The users select which portlet they want to use from a menu on the left side of the page, and the selected portlet takes up the entire page. 

![Figure 2.4: A panel page](../../images/web-content-panel-page.png)

**Embedded Pages** display content from another website inside of your portal. An administrator can set a URL from in the page management interface, and that page will appear in the context and within the navigation of your Liferay portal.

**URL Pages** are just redirects to any URL specified by an administrator. You can use this to link to pages within your portal on another community, or to an external site. These should be used with caution, as blind redirects can create a poor user experience.

A **Link to Page** is a portal page which functions as an immediate redirect to another page within the same site. You can select which page to link to from a dropdown in the page management interface. This can be useful for putting a deeply nested page in the primary navigation, or just for confusing your users.

Once you've created pages and populated them with content, Liferay provides a way for you to back them up to separate files. Let's see how that works. 

#### Backing up and Restoring Pages

Next to the *Add Page* button in the Manage Site Pages screen are two buttons labeled *Export* and *Import*. The Export button exports the pages you create into a single file, called a LAR (Liferay Archive). You can then import this file into any server running Liferay to re-create the pages. If you have a LAR that you would like to import, use the *Import* button. Exporting and Importing LARs is a great way to take content from one environment (say, a development or QA environment) and move it all in one shot to your production server. Note that you should not make it a regular process to do this. If you want to regularly move pages from one server to another, you should use Liferay's staging environment, which is covered in chapter 3. 

LARs are also a good way to back up your site's content. You can export them to a specific location on your server which is backed up, and if you ever have to restore your site, all you need to do is import the latest LAR file. One limitation on LAR files, however, is that they are version dependent, so you can't use an export from an old version of Liferay and import it into a newer version.

Let's be good administrators and export a LAR file for backup purposes. Click on the *Export* button and then name the file `nosesterv1.lar`. Use the check boxes to determine what you'd like to export. For this initial export, select everything. Note that if you select the *More Options* link, the list expands to include data from many of Liferay's applications, including the Documents and Media Library, Message Boards, and Web Content. You can also export the theme you're using.

Once you click *Export*, your browser prompts you to save the file. Once you have the file, you can copy it to a backup location for safekeeping or import it into another installation of Liferay Portal. If you must rebuild or wish to revert back to this version of your site, you can import this file by clicking the *Import* button from the Manage Site Pages dialog box, browsing to it, and selecting it. 

Next, we'll look at the options on the right side menu, starting with Look and Feel. 

#### Customizing the Look and Feel

When you open the Manage Site Pages dialog box it defaults to the Look and Feel tab. On this tab, you're presented with an interface that allows you to choose a theme for the current site. Themes can transform the entire look of the portal. They are created by developers and are easily installed using the Liferay Marketplace. Since we don't have any themes beyond the default one installed yet, we'll use the default theme for our pages.  

![Figure 2.5: Look and Feel Interface](../../images/04-look-and-feel.png)

Many themes include more than one color scheme. This allows you to keep the existing look and feel while giving your site a different flavor. Change the color scheme from blue to green by selecting *Green* under *Color Schemes*. If you now go back to the site (by clicking *Back to nosester.com* in the top left corner of the control panel), you'll see that some parts of the page are now tinged in a greenish hue. 

If you apply a color scheme to a set of public or private pages it is, by default, applied to each page in the set. If, however, you open the Manage Pages dialog box for a particular page, you can select *Define a specific look and feel for this page* to make the color scheme apply to this page only. You can use this feature to choose a different color scheme for a particular page than the one defined for the set of public or private pages to which it belongs. 

There are a few more configurable settings for your theme. You can switch the bullet style between dots and arrows and you can choose whether or not to show portlet borders by default.

Also notice that themes can apply to regular browsers or mobile devices. You could create another site for mobile users attached to the [http://m.nosester.com](http://m.nosester.com) address and serve up a page designed for the smaller screens on phones.

The *CSS* section allows you to enter custom CSS that will also be served up by your theme. In this way, you can tweak a theme in real time by adding new styles or overriding existing ones. 

The next option configures the logo that appears for your site. 


#### Using a custom logo

If you want to use your own logo for a specific site, use the Logo tab. Adding a custom logo is easy: select the Logo tab and browse to the location of your logo. Make sure that your logo fits the space in the top left corner of the theme you're using for your web site. If you don't, you could wind up with a site that's difficult to navigate, as other page elements are pushed aside to make way for the logo.

In the logo tab, you can also choose whether or not to display the site name on the site. If you check the box labeled *Show Site Name* the site name will appear in the the top right corner of the page. This option is enabled by default, and cannot be disabled if the *Allow Site Administrators to set their own logo* option is disabled in *Portal Settings*. It is also not available on the default community -- only newly created communities and user pages have the option to have the name display.

When you click on a specific page, some other options become available to you. 

### Changing options for individual pages

When you select a single page, some different options appear. Let's look at what these do. 

**Details:** lets you name the page for any localizations you need. You can also set the HTML title that appears in the browser window for the page. Finally, you can set an easy to remember, friendly URL for the page. 

**SEO:** provides several means of optimizing the data the page provides to an indexer that's crawling the page. You can set the various meta tags for description, keywords, and robots. There's also a separate Robots section that lets you tell indexing robots how frequently the page is updated and how it should be prioritized. If the page is localized, you can select a box to make Liferay generate canonical links by language. If you want to set some of these settings for the entire site, you can specify them from the Sitemaps and Robots tabs of the Manage Site Settings dialog box (see below).

---
![tip](../../images/01-tip.png) In previous versions of Liferay, it was possible that a single page could be indexed multiple times. In Liferay 6.1, to improve search ratings for your pages, all URLs that direct to the same page will only create one entry in the index. So, for example while previously the simple URL *http://www.nosester.com/web/guest/blog/-/blogs/thenose* and different versions of the URL which provided additional information about the referring page and what not, like *http://www.nosester.com/web/guest/blog/-/blogs/thenose?redirect_33&...* would have different entries in the index, now there is only one. From the search engines point of view, this will make your pages rank higher, since, for example, any references to any variations of a specific URL will be considered references to the one page.] 
---

**Look and Feel:** lets you set a page-specific theme. 

**Layout:** lets you specify how portlets are arranged on a page. Choose from the available installed templates to modify the layout. It's very easy for developers to define custom layouts and add them to the list, and this is covered in both the *Liferay Developer's Guide* and in [*Liferay in Action*](http://manning.com/sezov).  

**JavaScript:** gives you the ability to paste custom Javascript code to be executed on this page. 

**Custom fields:** If custom fields have been defined for pages (which can be done from the *Custom Fields* page of the control panel), they appear here. These are metadata about the page, and can be anything you like, such as author or creation date. 

**Advanced:** contains several optional features. You can set a query string to provide parameters to the page. This can become useful to web content templates, which you'll see in the next chapter. You can set a target for the page so that it either pops up in a particularly named window or appears in a frameset. And you can set an icon for the page that appears in the navigation menu. 

**Mobile Rule Groups:** allows you to apply rules for how this page should be rendered for various mobile devices. You can set these up in the *Mobile Device Rules* section of the control panel. 

**Customization Settings:** lets you mark specific sections of the page that you want users to be able to customize.

Note that the *Manage &rarr; Page Layout* menu directs you to the same Layout tab that's in *Manage &rarr; Page*. 

### Modifying Page Layouts

Page layouts allow you to arrange your pages so that the content appears the way you want it to. Liferay comes with many layouts already defined. Developers can create more and they can be deployed to your portal for your use.

In order to prepare for the portlets we'll soon be adding, let's change the layout of the Collaboration page. To access layouts, select *Manage &rarr; Page Layout* from the Dockbar.

Now, select the *2 Columns (70/30)* layout and click *Save*. Once saved, you'll return to the page and it'll seem as though nothing has happened. Once we start adding portlets, however, you'll notice that the page is now equally divided into two columns. You can stack portlets on top of each other in these columns. There are, of course, more complicated layouts available, and you can play around with them to get the layout that you want.

Sometimes a particular layout is *almost* what you want, but not quite. In this case, use the Nested Portlets portlet to embed a layout inside another layout. This portlet is a container for other portlets. It lets you select from any of the layouts installed in Liferay, just like the layouts for a page. This gives you virtually unlimited options for laying out your pages.

The next option in the *Manage* menu is page customizations.  

### Page Customizations

Page Customizations are a new feature in Liferay 6.1. With Page Customizations, any user can create personalized versions of any public page that has customizations enabled. Customizations are based on the rows and columns of a page layout. Administrators can activate or deactivate customizations for any row or column on any page. When users customize a page, they have the option to use either their version or the default version of a page. Users can't see alternate versions of pages other than their own.

![Figure 2.6: Setting Customizable Columns](../../images/04-web-content-personal-customization.png)

When an administrator activates Page Customizations for a page, any portlets that are in a *Customizable* row or column can be moved around the page or removed from the page. Users can add new portlets of their own choosing to the page. If at any time users determine that they don't like their customizations, they can click *Reset My Customizations* to revert their pages back to the default. 

Now that you know how to configure pages, let's look at the settings for the site as a whole.

### Configuring Site Settings

As with Site Pages, you can access Site Settings through the control panel or directly from the site using the Dockbar (*Manage* &rarr; *Site Settings*). 

![Figure 2.7: Site Settings](../../images/web-content-site-settings.png)

You'll find options to specify details and metadata about your site, set up friendly URLs and virtual hosts, configure search engine optimization settings, turn staging on or off, and specify a Google Analytics ID. Let's take a closer look. 

**Details:** allows an administrator to change the description and membership type of a site, and also to specify tags and categories for the site. The membership type can be set as open, restricted, or private based on the privacy needs of the site. Users can join and leave an open site at will. To join a restricted site, a user has to be added by the site administrator, but can request to be added through the Sites section of the control panel. A private site is like a restricted site, but doesn't appear in the Sites section of the control panel for users who aren't members.  

**Pages:** From Site Settings, click on *Pages* to manage some basic features of the pages on a site. If no pages have been defined yet, you can set site templates for the public or private pages. If pages already exist, links are provided to view them. You can also change the site's application adapter, which is a special type of hook plugin that customizes out of the box functionality for specific sites. 

**Site URL:** Set the virtual host for your site and/or a friendly URL here. The *Friendly URL* option lets you manage the path to your site in the portal's URL. This needs to be a unique name, of course. Having a human-readable friendly URL assists indexing bots, and is critical to good search engine optimization. 

*Virtual Hosts* make web navigation much easier for your users by connecting a domain name to a site. This tab allows you to define a domain name (i.e., www.mycompany.com) for your site. This can be a full domain or a subdomain. This enables you to host a number of web sites as separate sites on one Liferay server.

For instance, if we set this up for Nose-ster's Development Network, users in that site could use developers.nosester.com to get to their site, provided that Nose-ster's network administrators created the domain name and pointed it to the Liferay server.

To set this up, the DNS name *developers.nosester.com* should point to your portal's IP address first. Then enter http://developers.noseter.com in the Virtual Host tab for the Developers site. This helps users quickly access their site without having to recall an extended URL.

**Site Template:** If you've applied a site template to your pages, some settings appear here. Specifically, you can see whether to site template is active or if it allows modification by site administrators. If you're not using site templates for this site, you can safely ignore this tab.

**Sitemap:** lets you send a sitemap to some search engines so they can crawl your site. It uses the sitemap protocol, which is an industry standard. You can publish your site to Yahoo or Google, and their web crawlers will use the sitemap to index your site. Liferay Portal makes this very simple for administrators by generating the sitemap XML automatically for all public web sites.

By selecting one of the search engine links, the sitemap will be sent to them. It's only necessary to do this once per site. The search engine crawler will periodically crawl the sitemap once you've made the initial request.

If you're interested in seeing what is being sent to the search engines, select the *Preview* link to see the generated XML.

**Robots:** If you're using virtual hosting for this site, you can configure `robots.txt` rules for the domain. The Robots page gives you the option to configure your `robots.txt` for both public and private pages on a site. If you don't have Virtual Hosting set up, this tab is rather boring. 

**Staging:** enables you to edit and revise a page behind the scenes, and then publish changes to your site once they have been completed and reviewed. For a full explanation of Staging, see Chapter 3: Managing Content.

**Analytics:** allows you to integrate your pages with Google Analytics. Liferay provides seamless integration with Google Analytics, allowing you to place your ID in one place, and then it will get inserted automatically on every page. This enables you to focus your efforts on building the page, rather than remembering to put the code everywhere. Google Analytics is a free service which lets you do all kinds of traffic analysis on your site, so you can see who visits, where visitors are from, and what pages they most often visit. This helps you to tweak your site so that you can provide the most relevant content to your users.

Next in the menu is Site Memberships, which is covered in chapter 12. There, you'll learn how to administer a Liferay portal and define users and permissions. 

### Page Templates and Site Templates

*Page Templates* and *Site Templates* are an invaluable tool for building out pages on larger portals. You might already know this, or you might just be discovering it, but as you add more pages to website, you'll find more repeatable patterns in the designs of those pages. Page templates enable you to create a basic design for a single page and then apply it to any new page that you create without having to do any manual configuration. Site Templates allow you to do the same thing, but on the scale of a site -- if you have multiple sites which are going to have identical layouts with different content, you can create a single site template, and apply it to all of those pages. You can also use templates to manage multiple page layouts simultaneously. For a full explanation of Page Templates, Site Templates see Chapter 12: Administering Liferay Portal.

### Site Content

Liferay 6.1 makes it easier to access Web Content management without using the control panel. You can now click *Manage* and then *Site Content* to access the same Web Content controls that are featured in the control panel right from your portal page. 

![Figure 2.10: Site Content](../../images/web-content-site-content.png)

You can manage the following kinds of content:

- Recent Content
- Web Content
- Documents and Media
- Bookmarks
- Calendar
- Message Boards
- Blogs
- Wiki
- Polls
- Software Catalog
- Tags
- Categories
- Social Equity
- Dynamic Data Lists

For details about Liferay's social collaboration suite, see chapter 4. 

## Creating the Nose-ster pages

There are a lot of other things you can do beyond placing portlets on a page. So let's start working on the Nose-ster site. You can do this by going up to the Dockbar and clicking *Go to &rarr; Nose-ster*.

We'll use the *Community* page you created earlier in the chapter. Navigate to the *Community* page and select *Manage &rarr; Page* from the Dockbar.

This screen should now be familiar to you, but let's recap. 

The Page tab allows you to:

-   Change the name of the page
-   Enter HTML code for the title
-   Choose the page type
-   Hide the page from the theme navigation
-   Define a friendly URL to the page
-   Choose an icon to be displayed
-   Choose a frame target for the page
-   Copy an existing page

You can also enter custom meta tags or JavaScript to the page if you're a web developer. Additionally, if you click the *Permissions* button, you can define which users, groups, roles, or organizations can view or edit the page.

The Children tab lets you create child pages underneath the page you've selected. You can nest pages as deep as you like, but for every page below the top level hierarchy, you must provide navigation to it via a Navigation or Breadcrumb portlet, at least with most themes (including the default). Developers can create themes which have cascading menu bars which show the full hierarchy, and some examples of that are in Liferay's plugin repositories.

For now, click *Return to full page*. You should be able to define and manage pages in Liferay at this point, so let's look at what you'd put on a page. 

### Portlets

As we discussed earlier, Liferay Portal pages are composed of portlets. All of your site's functionality, from blogs to shopping, is composed of portlets.

Adding portlets to a page is simple. Let's add some to our Collaboration page.

1.  In the Dockbar, select *Add &rarr; More*.
2.  In the menu that appears, expand the *Collaboration* category.
3.  Drag the *Blogs Aggregator* portlet off the Add Application window onto the right column of our page.
4.  Next, drag the *Wiki* portlet to the *left column*.

See how easy it is to add applications to your pages? We've gone one step further: we've got the Wiki portlet, the Blogs Aggregator portlet, and then a nested portlet with a different layout and the Alerts, Search, and Dictionary portlets in the figure below. 

![Figure 2.11: Yeah, we're showoffs. But as you can see, your page layout options are virtually limitless.](../../images/04-web-content-portlet-layout.png)

You'll find it's easy to make your pages look exactly the way you want them to. If the layout options provided aren't enough, you can even develop your own. More information about that can be found in Liferay's official guide to development, [*Liferay in Action*](http://manning.com/sezov).

### Page Permissions

By default, public pages are just that: public. They can be viewed by anybody, logged in or not logged in. And private pages are really only private from non-members of the site. If someone has joined your site or is a member of your organization, that person can see all the private pages. You can, however, modify the permissions on individual pages in either page group so that only certain users can view them.

Let's say we wanted to create a page only for administrators to see. We can do this with the following procedure:

1.  Go to the Dockbar and select *Manage &rarr; Control Panel*.
2.  Ensure that you've selected the default site in the context selector.
3.  Click the *Site Pages* link.
4.  Click the *Private Pages* tab to switch to the Private Pages. Remember, these pages by default are viewable only by members of the site.
5.  Create a page called *Admin Tips*.
6.  Click on the page in the tree on the left and then click *Permissions*.
7.  Uncheck the *View* and *Add Discussion* permissions next to the Site Member role.
8.  Click the *Save* button.

![Figure 2.12: Permissions for Admin Tips](../../images/04-web-content-page-permissions.png)

Congratulations! You've just changed the permissions for this page so that only site administrators can view it. Any users you add to this role can now see the page. Other users, even members of this site, won't have permission to see it.

## Summary

Pages in Liferay are as flexible as pages you'd create manually without a portal. Using a point and click interface, you can define your site any way you want. You can create and remove pages, export and import them, set their layouts, define how they are indexed by search engines, and more. You've also been introduced to Liferay's concept of sites. Again, using a point and click interface, you can create multiple web sites and define how users can access them, whether they are linked to a domain name, and create all of their pages. 

You now understand how to manage pages in Liferay Portal. It's time to move on to adding content to those pages. Liferay's Web Content Management (WCM) is a highly powerful, yet flexible, set of tools that enables you to successfully manage your web site.

You'll soon discover that Liferay's WCM is easy to learn and highly configurable. If you already have experience with WCM, you'll see some new features and improvements to old ones. If you're new to Liferay's WCM, then you'll be surprised at how fast you will be adding, editing, and scheduling content on your site. Once you're familiar with portlets such as Web Content Display and Asset Publisher, your ability to manage an immense site with a large amount of content will simply amaze you.

We'll be using Liferay's WCM to publish simple pieces of content, develop templates to define how content is to be displayed, set up a workflow for content to be approved, schedule when content is to be published and much, much more.


