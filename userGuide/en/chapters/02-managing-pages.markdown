# Managing Pages in Liferay Portal

With most products, you would learn what the software can do in terms of setting up your environment and security model, and then start building your system. You'd design your infrastructure and get your server environment up and running while your developers would go ahead and write the applications that would live on your web site. With Liferay Portal, however, you start farther ahead. Liferay Portal is more than just a *container* for applications with a robust security model. It already includes many of the applications you'll need, out of the box, ready to go, and integrated with all the user management and security features you've already learned about.

Perhaps the key application that ships with Liferay is Liferay's Web Content Management system (WCM). We call it the key application because it is the one that almost everybody uses, because everyone needs to populate their web sites with content. Liferay's WCM empowers you to manage all of the content on your site quickly, easily, and in the browser. Beyond managing existing content, Liferay WCM lets users easily create and manage everything from a simple article containing text and images to fully functional web sites. Web publishing works alongside Liferay Portal's larger collection of applications, which means that you can add shopping cart functionality, visitor polls, web forms, site collaboration tools, and more. Everything is done with our collection of easy-to-use tools with familiar rich-text editors and intuitive interface.

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

By the time we're done, you should be able to apply all of these concepts to your own content. To demonstrate Liferay's Content Management features, we'll create and manage content on the portal for *Nose-ster* a new social network where people are connected based on what their noses look like.

First, a little housekeeping. If we're going to be *Nose-ster*, our portal should also be called Nose-ster. To set general information about your portal like the name and mail domain, go to the control panel and select *Portal Settings* under the Portal heading. You could set up the configuration for Nose-ster as follows.

![Figure 4.1 Changing Portal Settings](../../images/04-web-content-changing-settings.png)

You can also customize the logo in the top left corner of every page by selecting *Display Settings* under the *Miscellaneous* tab on the panel to the right. Once you've made the changes, we can begin creating pages.

## Page Creation and Management

You have a few options for accessing the page creation interface, which one you use will probably depend on the specific cirumstances of what you're editing, and where you are on the portal. From the control panel, make sure that you have the correct site selected in the context menu and click the *Site Pages* link in the content section. If you're currently viewing the page you wish to manage, click *Manage* from the Dockbar and select *Page*. For more options that effect the entire site, click *Manage* and select *Site Pages*. 

![Figure 4.2: Managing Individual Pages](../../images/04-web-content-managing-single-page.png)

Alternatively, you can navigate to the Sites page under the Portal section of the control panel and click *Actions* &rarr; *Manage Pages*. To quickly add a single page while you're browsing a site, you can click *Add* from the Dockbar and select *Page*, and a page will be added immediately. You can then enter a name for the page and begin working on it immediately.

![Figure 4.3: Managing Site Pages](../../images/04-managing-site-pages.png)

Go to *Manage* &rarr; *Site Pages*, you'll see an interface to view existing pages, create new pages, view pages, and export or import pages using Liferay Archive (LAR) files. Note that you can switch between managing a set of pages and managing a single page using the left-hand side navigation menu. Click on *Public Pages* or *Private Pages* to manage the group or click on an individual page to manage just that one. Switching views like this will change this list of available tabs to the right. By default, liferay.com, which we renamed nosester.com, contains a single public page called *Welcome*..

### Understanding Public and Private Pages

Liferay's page groups are always associated with sites. Even users' personal pages are part of their personal sites. All pages must belong either to a site's set of public pages or private pages. By default, anyone, even guests, can access a site's public pages. Private pages are only accessible to users who are members of the site which owns the pages. This means that the private pages of an organization's site would only be viewable by members of the organization. 

Regardless of whether the pages are public or private, Liferay uses the same interface to manage them. Let's look at this interface more closely. 

### Managing Pages

From the Manage Site Pages dialog box, you can add a page to the site by clicking the *Add Page* button. Because *Public Pages* is selected on the left, clicking *Add Page* here will add a top level page next to the Welcome page. You can, however, nest pages as deeply as you like. If you wanted to create a sub-page under the Welcome page, all you'd have to do is select the *Welcome* page first and then create your page. And if you later decide you don't like the order of your pages, you can drag and drop them in the list to put them in whatever order you want. Let's go ahead and add another top level page and name it *Community*. We'll use this page for the Recent Bloggers and Wiki portlets.

Note that when you create a new page, you have the option to create either a blank page, or a page prepopulated with portlets from a page template. When you're entering the name of the page, you'll be able to select from a list of page templates that are currently available. For more information about page templates, see chapter 12.

![Figure 4.4: Adding Pages](../../images/04-web-content-add-page.png)

#### Backing up and Restoring Pages

You can find two buttons labeled *Export* and *Import* next to the Add Page button in the Manage Site Pages dialog box. The Export button allows you to export the pages you create into a single file, called a LAR (Liferay Archive). You can then import this file into any server running Liferay, and all of your pages will be copied. If you have a LAR that you would like to import, use the *Import* button. Exporting and Importing LARs is a great way to take content from one environment (say, a development or QA environment) and move it all in one shot to your production server.

LARs are also a good way to back up your site's content. You can export them to a specific location on your server which is backed up, and if you ever have to restore your site, all you need to do is import the latest LAR file. One limitation on LAR files, however, is that they are version dependent, so you can't use an export from an old version of Liferay and import it into a newer version.

Let's be good administrators and export a LAR file for backup purposes. Click on the *Export* button and then name the file `nosesterv1.lar`. We need to select what to export using the checkboxes. Let's select everything for this initial export. Note that if you select the *More Options* link, the list expands to include data from many of Liferay's applications, including the Documents and Media Library, Message Boards, and Web Content. You can also export the theme you're using.

Once you click *Export*, your browser prompts you to save the file. Once you have the file, you can copy it to a backup location for safekeeping or import it into another installation of Liferay Portal. If you must rebuild or wish to revert back to this version of your site, you can import this file by clicking the *Import* button from the Manage Site Pages dialog box, browsing to it, and selecting it. 

Next, we'll look at the options on the right side menu, starting with Look and Feel. 

#### Customizing the Look and Feel

When you open the Manage Site Pages dialog box it defaults to the Look and Feel tab. On this tab, you're presented with an interface which allows you to choose a theme for the site which you are currently working on. Themes can transform the entire look of the portal. They are created by developers and are easily installed using the Liferay Marketplace. Since we don't have any themes beyond the default one installed yet, we'll use the default theme for our pages.  

![Figure 4.5: Look and Feel Interface](../../images/04-look-and-feel.png)

Many themes include more than one color scheme. This allows you to keep the existing look and feel while giving your site a different flavor. Let's change the color scheme from blue to green by selecting *Green* under *Color Schemes*. If you now go back to the site (by clicking *Back to nosester.com* in the top left corner of the control panel), you'll see that some parts of the page are now tinged in a greenish hue. 

If you apply a color scheme to a set of public or private pages it is, by default, applied to each page in the set. However, if you open the Manage Pages dialog box for a particular page, you can select *Define a specific look and feel for this page* to customize the page. You can use this feature to choose a different color scheme for a particular page than the one defined for the set of public or private pages to which it belongs. 

There are a few settings that you can configure for your theme. You can switch the bullet style between dots and arrows and you can choose whether or not to show portlet borders by default.

Also, notice that you can choose themes for regular browsers or mobile devices. You could create another site for mobile users attached to the [http://m.nosester.com](http://m.nosester.com) address and serve up a page that is designed for the smaller screens on phones.

The *CSS* section allows you to enter custom CSS that will also be served up by your theme. In this way, you can tweak a theme in real time by adding new styles or overriding existing ones. 

The next option configures the logo that appears for your site. 

#### Using a custom logo

If you want to use your own logo for a specific site, you can configure an alternate logo in the Logo tab. To add a custom logo, select the Logo tab, and browse to the location of your logo. Make sure that your logo fits the space in the top left corner of the theme you are using for your web site. If you don't, you could wind up with a site that's difficult to navigate, as other page elements are pushed aside to make way for the logo. 

When you click on a specific page, some other options become available to you. 

### Changing options for individual pages

When you select a single page, some different options appear. Let's look at what these do. 

**Details:** lets you name the page for any localizations you need. You can also set the HTML title that appears in the browser window for the page. Finally, you can set an easy to remember, friendly URL for the page. 

**SEO:** provides several means of optimizing the data the page provides to an indexer that's crawling the page. You can set the various meta tags for description, keywords, and robots. There's also a separate Robots section that lets you tell indexing robots how frequently the page is updated and how it should be prioritized. If the page is localized, you can select a box to make Liferay generate canonical links by language. If you want to set some of these settings for the entire site, you can specify them from the Sitemaps and Robots tabs of the Manage Site Settings dialog box (see below). 

**Look and Feel:** lets you set a page-specific theme. 

**Layout:** lets you specify how portlets will be arranged on a page. You can only choose a template from the list but it's very easy for developers to define custom layouts and add them to the list. 

**JavaScript:** gives you the ability to paste custom Javascript code to be executed on this page. 

**Custom fields:** If custom fields have been defined for pages (which can be done from the *Custom Fields* page of the control panel), they will appear here. These are metadata about the page, and can be anything you like, such as author or creation date. 

**Advanced:** contains several optional features. You can set a query string to provide parameters to the page. You can set a target for the page so that it either pops up in a particularly named window or appears in a frameset. And you can set an icon for the page, which appears in the navigation menu. 

**Mobile Rule Groups:** allows you to apply rules for how this page should be rendered for various mobile devices. You can set these up in the *Mobile Device Rules* section of the control panel. 

**Customization Settings:** lets you mark specific sections of the page that you want users to be able to customize.

Now that you know how to configure pages, let's look at the settings for the site as a whole. 

### Configuring Site Settings

As with Site Pages, you can access Site Settings through the control panel or directly from the site using the Dockbar (*Manage* &rarr; *Site Settings*). 

![Figure 2.x: Site Settings](../../images/web-content-site-settings.png)

You'll find options to specify details and metadata about your site, set up friendly URLs and virtual hosts, configure search engine optimization settings, turn staging on or off, and specify a Google Analytics ID. Let's take a closer look. 

*Details*: The details tab allows an administrator to change the description and membership type of a site, and also to specify tags and categories for the site. The membership type can be set as open, restricted, or private based on the privacy needs of the site. Users can join and leave an open site at will. To join a restricted site, a user has to be added by the site administrator, but can request to be added through the Sites section of the control panel. A private site is like a restricted site, but doesn't appear in the Sites section of the control panel for users who aren't members.  

*Pages*: From Site Settings, click on *Pages* to manage some basic features of the pages on a site. You can view current public and private pages and manage their page templates. You can also change the application adapter.

*Site URL*: The Site URL tab is where you handle configuration for user-facing URLs, it has two sections: Friendly URL and Virtual Hosts. The *Friendly URL* option allows you to change the name of your site as it appears in the URL. This will need to be a unique name, but it doesn't need to have any relation to the actual name of your site. Having a good friendly URL will be critical to good search engine optimization.

You can use *Virtual Hosts* to make web navigation much easier for your users by connecting a domain name to a site. This tab allows you to define a domain name (i.e., www.mycompany.com) for your site. This can be a full domain or a subdomain. This enables you to host a number of web sites as separate sites on one Liferay server.

For instance, if we set this up for Nose-ster's Development Network, users in that site could use developers.noseter.com to get to their site, provided that Nose-ster's network administrators created the domain name and pointed it to the Liferay server.

To set this up, you would simply configure the DNS for developers.nosester.com at the web address for your portal, and enter http://developers.noseter.com in the Virtual Host tab for the Developers site. This can help users quickly access their site without having to recall an extended URL.

*Site Templates*: The Site Templates tab can be useful if you have yet added pages to your site. If you haven't added either public or private pages, you will be able to apply a site template from this page. If you've already created both public and private pages, than you can safely ignore this tab.

*Sitemap*: The next tab lets you generate a sitemap to help you optimize your site for search engines. The Sitemap tab publishes your site using the sitemap protocol, a protocol that helps search engines crawl your web site to index relevant information. You can publish your site to Yahoo or Google, and their web crawlers will use the sitemap to index your site.

Liferay Portal makes this very simple for administrators by generating the sitemap XML automatically for all public web sites.

By selecting one of the search engine links, the sitemap will be sent to them. It's only necessary to do this once per site. The search engine crawler will periodically crawl the sitemap once you've made the initial request.

If you're interested in seeing what is being sent to the search engines, select the *Preview* link to see the generated XML.

*Robots*: Generally, you can configure your `robots.txt` at a portal level through the control panel, or control it at an individual page level. However, once you setup virtual hosting, you will need to setup separate `robots.txt` files for each domain. The Robots page gives you the option to setup your `robots.txt` for both your public and private pages on a site if you have Virtual Hosting setup. If you don't have Virtual Hosting set up, this tab will be rather boring. 

*Staging*: Staging enables you to edit and revise a page behind the scenes, and then publish changes to your site once they have been completed and reviewed. For a full explanation of Staging, see Chapter 3: Managing Content.

*Analytics*: The Analytics tab allows you to integrate your pages with Google Analytics. Liferay provides seamless integration with Google Analytics, allowing you to place your ID in one place, and then it will get inserted automatically on every page. This enables you to focus your efforts on building the page, rather than remembering to put the code everywhere. Google Analytics is a fantastic, free service which lets you do all kinds of traffic analysis on your site, so you can see who visits, where they visit from, and what pages they most often visit. This helps you to tweak your site so that you can provide the most relevant content to your users.

### Site Content

Liferay 6.1 makes it easier to access Web Content management without using the control panel. You can now click *Manage* and then *Site Content* to access the same Web Content controls that are featured in the control panel right from your portal page. 

![Figure 3.x: Site Content](../../images/web-content-site-content.png)

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

For details about Liferay's social collaboration suite, see chapter 4. For details about using the control panel to manage web content, see chapter 12.

### Page Customizations

Page Customizations are a new feature in Liferay 6.1. With Page Customizations, any user can create personalized versions of any public page that has customizations enabled. Customizations are based on the rows and columns of a page layout, and an administrator can activate or deactivate customizations for any row or column on any page. Once a user customizes a page, they have the option to use either their version or the default version of a page. Users will not be able to see alternate versions of pages other than their own.

When an administrator activates Page Customizations for a page, any portlets that are in a *Customizable* row or column can be moved around the page or removed from the page. Users will also be able to add new portlets of their own choosing to the page. If, at any time, a user determines that they don't like their customizations, they can click *Reset My Customizations* to revert their page back to the default. 

![Figure 4.6: Setting Customizable Columns](../../images/04-web-content-personal-customization.png)

### Modifying Page Layouts

Page layouts allow you to arrange your pages so that the content appears the way you want it to. Liferay comes shipped with many layouts already defined. Developers can create more and they can be deployed to your portal for your use.

In order to prepare for the portlets we'll soon be adding, let's change the layout of the Collaboration page. To access layouts, select *Manage &rarr; Page Layout* from the dockbar.

Now, select the *2 Columns (70/30)* layout and click *Save*. Once saved, you'll return to the page and it'll seem as though nothing has happened. However, once we start adding portlets you'll notice how the page is now equally divided into two columns. You can stack portlets on top of each other in these columns. There are, of course, more complicated layouts available, and you can play around with them to get the layout that you want.

Sometimes a particular layout is *almost* what you want, but not quite. In this case, you can use the Nested Portlets portlet to embed a layout inside another layout. This portlet is simply a container for other portlets. Its configuration allows you to select from any of the layouts installed in Liferay, just like the layouts for a page. This gives you virtually unlimited options for laying out your pages.

### Editing a Page

There are a lot of other things you can do beyond placing portlets on a page. So let's take a look at to the Nose-ster site. You can do this by going up to the dockbar and clicking *Go to &rarr; Nose-ster*.

We'll use the *Community* page you created earlier in the chapter. Navigate to the *Community* page and select *Manage &rarr; Page* from the dockbar.

This screen should now be familiar to you, but we haven't yet looked at all of its options.

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

For now, let's just click *Return to full page*.

### Portlets

As we discussed earlier, Liferay Portal pages are composed of portlets. All of your site's functionality, from blogs to shopping, is composed of portlets.

Adding portlets to a page is simple. Let's add some to our Collaboration page.

1.  In the dockbar, select *Add &rarr; More*.
2.  In the menu that appears, expand the *Collaboration* category.
3.  Drag the *Blogs Aggregator* portlet off the Add Application window onto the right column of our page.
4.  Next, drag the *Wiki* portlet to the *left column*.

See how easy it is to add applications to your pages? We've gone one step further: we've got the Wiki portlet, the Blogs Aggregator portlet, and then a nested portlet with a different layout and the Alerts, Search, and Dictionary portlets.

![Figure 4.7: Yeah, we're showoffs. But as you can see, your page layout options are virtually limitless.](../../images/04-web-content-portlet-layout.png)

You'll find it's very easy to make your pages look exactly the way you want them to. If the layout options provided aren't enough, you can even develop your own. More information about that can be found in Liferay's official guide to development, *Liferay in Action*.

### Page Permissions

By default, public pages are just that: public. They can be viewed by anybody, logged in or not logged in. And private pages are really only private from non-members of the site. If someone has joined your site or is a member of your organization, that person can see all of the private pages. You can, however, modify the permissions on individual pages in either page group so that only certain users can view them.

So, let's say we wanted to create a page only for administrators to see. We can do this with the following procedure:

1.  Go to the dockbar and select *Manage &rarr; Control Panel*.
2.  Ensure that you have selected the default site in the context selector.
3.  Click the *Site Pages* link.
4.  Click the *Private Pages* tab to switch to the Private Pages. Remember, these pages by default are viewable only by members of the site.
5.  Create a page called *Admin Tips*.
6.  Click on the page in the tree on the left and then click *Permissions*.
7.  Uncheck the *View* and *Add Discussion* permissions next to the Site Member role.
8.  Click the *Save* button.

![Figure 4.8: Permissions for Admin Tips](../../images/04-web-content-page-permissions.png)

Congratulations! You've just changed the permissions for this page so that only site administrators can view it. Any users you add to this role will now be able to see the page. Other users, even members of this site, won't have the permissions to see it.

## Summary

You now understand how to manage pages in Liferay Portal. It's time to move on to adding content to those pages. Liferay's Web Content Management (WCM) is a highly powerful, yet flexible, set of tools that enables you to successfully manage your web site.

You'll soon discover that Liferay's WCM is easy to learn and highly configurable. If you already have experience with WCM, you'll see some new features and improvements to old ones. If you're new to Liferay's WCM, then you'll be surprised at how fast you will be adding, editing, and scheduling content on your site. Once you're familiar with portlets such as Web Content Display and Asset Publisher, your ability to manage an immense site with a large amount of content will simply amaze you.

We'll be using Liferay's WCM to publish simple pieces of content, develop templates to define how content is to be displayed, set up a workflow for content to be approved, schedule when content is to be published and much, much more.


