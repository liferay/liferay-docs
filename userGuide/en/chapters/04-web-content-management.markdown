# Web Content Management

With most products, you would learn what the software can do in terms of setting up your environment and security model, and then start building your system. You'd design your infrastructure and get your server environment up and running while your developers would go ahead and write the applications that would live on your web site. With Liferay Portal, however, you start farther ahead. Liferay Portal is more than just a *container* for applications with a robust security model. It already includes many of the applications you'll need, out of the box, ready to go, and integrated with all the user management and security features you've already learned about.

Perhaps the key application that ships with Liferay is Liferay's Web Content Management system (CMS). We call it the key application because it is the one that almost everybody uses, because everyone needs to populate their web sites with content. Liferay's CMS empowers you to manage all of the content on your site quickly, easily, and in the browser. Beyond managing existing content, Liferay CMS lets users easily create and manage everything from a simple article containing text and images to fully functional web sites. Web publishing works alongside Liferay Portal's larger collection of applications, which means that you can add in shopping cart functionality, visitor polls, web forms, site collaboration tools, and more. Everything is done with our collection of easy-to-use tools with familiar rich-text editors and intuitive interface.

This chapter covers all aspects of Liferay CMS, including:

- Page types

- Layouts

- Page and content permissions

- Importing and exporting content

- Content creation and editing

- Staging

- Content publishing

- Structures and templates

- CMS Workflow

- Asset publisher

By the time we're done, you should be able to apply all of these concepts to your own content. To demonstrate Liferay's Content Management features, we'll create and manage content on the portal for *Nose-ster* a new social network where people are connected based on what their nose looks like.

First, a little housekeeping. If we're going to be *Nose-ster*, our portal should also be called Nose-ster. To set general information about your portal like the name and mail domain, go to the control panel and select *Portal Settings* under the Portal heading. You could set up the configuration for Nose-ster as follows.

![Figure 4.1 Changing Portal Settings](../../images/04-web-content-changing-settings.png)

You can also customize the logo in the top left corner of every page by selecting *Display Settings* under the *Miscellaneous* tab on the panel to the right. Once you've made the changes, we can begin creating pages.

## Page Creation and Management

Your pages must contain something, right? The reason web pages exist at all is to display content. Use can use the web to share project information, advertise to potential customers, or demonstrate a golf swing. In each case you're displaying content. And with Liferay's CMS, you'll find that the process of loading your pages up with content is extremely easy and straightforward, and you don't ever need to leave your browser.

Essentially, CMS is a suite of various portlets that include functionality for creating and displaying various types of content. Don't be concerned by the sheer number of applications you see in this category in the *Add &rarr; More* menu: we'll go over everything in a step-by-step fashion. Let's start by taking a look at page creation and management.

### Managing Pages

If you are currently viewing the page you wish to manage, simply click *Manage* from the dockbar and select *Page*. If you would like to manage your site's whole set of pages, clink *Manage* and select *Site Pages*. 

![Figure 4.2: Managing Individual Pages](../../images/04-web-content-managing-single-page.png)

You can also access the Site Pages dialog box two other ways in the control panel. The first way is to simply select the site that you want to work with the context selector of the Content section, and click on *Site Pages* in the left column. The second way is to navigate to the Sites page under the Portal section of the control panel and to click *Actions* and select *Manage Pages*. To quickly add a single page, you can click *Add* from the dockbar and select *Page*, and a page will be added immediately. You can then enter a name for the page and begin working on it immediately.

![Figure 4.3: Managing Site Pages](../../images/04-managing-site-pages.png)

Once you're in the Manage Pages dialog box, you'll see an interface to view existing pages, create new pages, view pages, and export or import pages using Liferay Archive (LAR) files. Note that you can switch between managing a set of pages and managing a single page using the left-hand side navigation menu. Click on *Public Pages* or *Private Pages* to manage the group or click on an individual page to manage just that one. Switching views like this will change this list of available tabs to the right. By default, liferay.com, which we renamed nosester.com, contains a single public page called *Welcome* and we'll leave it that way.

### Understanding Public and Private Pages

Liferay's page groups are always associated with sites. Even users' personal pages are part of their personal sites. All pages must belong either to a site's set of public pages or private pages. By default, anyone, even guests, can access a site's public pages. Private pages are only accessible to users who are members of the site which owns the pages. This means that the private pages of an organization's site would only be viewable by members of the organization. The same interface applies to both the public and private pages tabs.

### Manage Pages Interface

From the Manage Site Pages dialog box, you can add a page to the site by clicking the *Add Page* button. Because *Public Pages* is selected on the left, clicking *Add Page* here will add a top level page next to the Welcome page. However, you can nest pages as deeply as you like. If you wanted to create a sub-page under the Welcome page, all you'd have to do is select the *Welcome* page first and then create your page. And if you later decide you don't like the order of your pages, you can drag and drop them in the list to put them in whatever order you want. Let's go ahead and add another top level page and name it *Community*. We'll use this page for the Recent Bloggers and Wiki portlets.

Note that when you create a new page, you have the option to either create a blank page, or to have it prepopulated with portlets from a page template. When you're entering the name of the page, you'll be able to select from a list of page templates that are currently available. For more information about page templates, see chapter 12.

![Figure 4.4: Adding Pages](../../images/04-web-content-add-page.png)

### Export/Import

You can find two buttons labeled *Export* and *Import* next to the Add Page button in the Manage Site Pages dialog box. The Export button allows you to export the pages you create into a single file, called a LAR (Liferay Archive). You can then import this file into any server running Liferay, and all of your pages will be copied. If you have a LAR that you would like to import, use the Import button. Exporting and Importing LARs is a great way to take content from one environment (say, a development or QA environment) and move it all in one shot to your production server.

LARs are also a good way to back up your site's content. You can export them to a specific location on your server which is backed up, and if you ever have to restore your site, all you need to do is import the latest LAR file. One limitation on LAR files, however, is that they are version dependent, so you can't use an export from an old version of Liferay and import it into a newer version.

Let's be good administrators and export a LAR file for backup purposes. Click on the *Export* button and then name the file `nosesterv1.lar`. We need to select what to export using the checkboxes. Let's select everything for this initial export. Note that if you select the *More Options* link, the list expands to include data from many of Liferay's applications, including the Documents and Media Library, Message Boards, and Web Content. You can also export the theme you're using.

Once you click *Export*, your browser will prompt you to save the file. Once you have the file, you can copy it to a backup location for safekeeping or import it into another installation of Liferay Portal. If you must rebuild or wish to revert back to this version of your site, you can import this file by clicking the *Import* button from the Manage Site Pages dialog box, browsing to it, and selecting it.

### Look and Feel

When you open the Manage Site Pages dialog box it defaults to the Look and Feel tab. On this tab, you'll be presented with an interface which allows you to choose a theme for the site which you are currently working on. Themes can transform the entire look of the portal. They can be created by developers and can be easily installed using the plugins installer from the control panel. We covered the plugins installer in chapter 2.

![Figure 4.5: Look and Feel Interface](../../images/04-look-and-feel.png)

Since we don't have any themes beyond the default one installed yet, we'll use the default theme for our pages. Many themes include more than one color scheme. This allows you to keep the existing look and feel while giving your site a different flavor. Let's change the color scheme from blue to green by selecting *Green* under *Color Schemes*. If you now go back to the site (by clicking *Back to nosester.com* in the top left corner of the control panel), you'll see that some parts of the page are now tinged in a greenish hue. 

If you apply a color scheme to a set of public or private pages it will, by default, be applied to each page in the set. However, if you open the Manage Page dialog box for a particular page, you can select *Define a specific look and feel for this page* to customize the page. You can use this feature to choose a different color scheme for a particular page than the one defined for the set of public or private pages it belongs to.

There are a few settings that you can configure for your theme. You can switch the bullet style between dots and arrows and you can choose whether or not to show portlet borders by default.

Also, notice that you can choose themes for regular browsers or mobile devices. You could create another site for mobile users attached to the [http://m.nosester.com](http://m.nosester.com) address and serve up a page that is designed for the smaller screens on phones.

The *CSS* section allows you to enter custom CSS that will also be served up by your theme. In this way, you can tweak a theme in real time by adding new styles or overriding existing ones.

### Logo

If you want to use your own logo for a specific site, you can configure an alternate logo in the Logo tab. To add a custom logo, select the Logo tab, and browse to the location of your logo. Make sure that your logo fits the space in the top left corner of the theme you are using for your web site.

In addition to *Look and Feel* and *Logo*, there are also *Javascript* and *Advanced* tabs. You can you these to add javascript code to be executed at the bottom of every page in the site or change any advanced settings that apply to all of your pages.

### Additional Tabs of the Manage Page Window

The settings in the tabs of the Manage Page dialog box apply only to individual pages. This dialog box has some different tabs from those of the Manage Site Pages dialog box. These include SEO (Search Engine Optimization), Layout, Custom Fields, and Customization Settings. The SEO tab of the Manage Page dialog box is for customizing the search engine properties of an *individual* page. For example, you could change the priority of a page or remove it from the sitemap altogether. The SEO settings for your *site* can can be specified from the Sitemaps and Robots tabs of the Manage Site Settings dialog box. 

The layout templates tab lets you specify how portlets will be arranged on a page. You can only choose a template from the list but it's very easy for developers to define custom layouts and add them to the list. If custom fields have been defined, which can be done from the *Custom Fields* page of the control panel, you can add a custom field to a page from the custom fields tab. The contents of the custom field serves as metadata about the page so it will not be visible on the page. The customization tab lets you mark specific sections of the page that you want users to be able to customize.

### Site Settings

The *Manage &rarr; Site Settings* dialog box gives us several powerful tools. You'll find options to specify details and metadata about your site, set up friendly URLs and virtual hosts, configure search engine optimization settings, turn staging on or off, and specify a Google Analytics ID. Let's take a closer look.

![Figure 3.x: Site Settings](../../images/web-content-site-settings.png)

#### Details

The details tab allows an administrator to change the description and membership type of a site, and also to add tags. The membership type can be set as open, restricted, or private based on the privacy needs of the site. Adding tags can make it easier to search for a site on the portal. Tags can also, together with the site description, help users decide if they should join a site.

#### Pages

From Site Settings, click on *Pages* to manage some basic features of the pages on a site. You can view current public and private pages and manage their page templates. You can also change the application adapter.

#### Site URL

The Site URL tab is where you handle configuration for user-facing URLs, it has two sections: Friendly URL and Virtual Hosts. 

##### Friendly URL

The Friendly URL option allows you to change the name of your site as it appears in the URL. This will need to be a unique name, but it doesn't need to have any relation to the actual name of your site. Having a good friendly URL will be critical to good search engine optimization.

##### Virtual Hosts

You can make web navigation much easier for your users by connecting a domain name to a site. This tab allows you to define a domain name (i.e., www.mycompany.com) for your site. This can be a full domain or a subdomain. This enables you to host a number of web sites as separate sites on one Liferay server.

For instance, if we set this up for Nose-ster's Development Network, users in that site could use developers.noseter.com to get to their site, provided that Nose-ster's network administrators created the domain name and pointed it to the Liferay server.

To set this up, you would simply configure the DNS for developers.nosester.com at the web address for your portal, and enter http://developers.noseter.com in the Virtual Host tab for the admissions site.

This can help users quickly access their site without having to recall an extended URL.

#### Site Templates

The Site Templates tab can be useful if you have yet added pages to your site. If you haven't added either public or private pages, you will be able to apply a site template from this page. If you've already created both public and private pages, than you can safely ignore this tab.

#### Sitemap

The next tab lets you generate a sitemap to help you optimize your site for search engines. The Sitemap tab publishes your site using the sitemap protocol, a protocol that helps search engines crawl your web site to index relevant information. You can publish your site to Yahoo or Google, and their web crawlers will use the sitemap to index your site.

Liferay Portal makes this very simple for administrators by generating the sitemap XML automatically for all public web sites.

By selecting one of the search engine links, the sitemap will be sent to them. It's only necessary to do this once per site. The search engine crawler will periodically crawl the sitemap once you've made the initial request.

If you're interested in seeing what is being sent to the search engines, select the *Preview* link to see the generated XML.

#### Robots

Generally, you can configure your `robots.txt` at a portal level through the control panel, or control it at an individual page level. However, once you setup virtual hosting, you will need to setup separate `robots.txt` files for each domain. The Robots page gives you the option to setup your `robots.txt` for both your public and private pages on a site if you have Virtual Hosting setup. If you don't have Virtual Hosting set up, this tab will be rather boring. 

#### Analytics

The Analytics tab allows you to integrate your pages with Google Analytics. Liferay provides seamless integration with Google Analytics, allowing you to place your ID in one place, and then it will get inserted automatically on every page. This enables you to focus your efforts on building the page, rather than remembering to put the code everywhere. Google Analytics is a fantastic, free service which lets you do all kinds of traffic analysis on your site, so you can see who visits, where they visit from, and what pages they most often visit. This helps you to tweak your site so that you can provide the most relevant content to your users.

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

You now understand how to manage pages in Liferay Portal. It's time to move on to adding content to those pages. Liferay's Web Content Management (WCM) is a highly powerful, yet flexible, set of tools that enables you to successfully manage your web site.

You'll soon discover that Liferay's WCM is easy to learn and highly configurable. If you already have experience with WCM, you'll see some new features and improvements to old ones. If you're new to Liferay's WCM, then you'll be surprised at how fast you will be adding, editing, and scheduling content on your site. Once you're familiar with portlets such as Web Content Display and Asset Publisher, your ability to manage an immense site with a large amount of content will simply amaze you.

We'll be using Liferay's WCM to publish simple pieces of content, develop templates to define how content is to be displayed, set up a workflow for content to be approved, schedule when content is to be published and much, much more.

### What is Web Content Management?

Web Content Management is a system which allows non-technical users to publish content to the web without having advanced knowledge of web technology or programming of any sort. Liferay CMS empowers you to publish your content with a simple point and click interface, and it helps you to keep your site fresh. You'll find yourself easily creating, editing, and publishing content within just a few minutes of being exposed to its features. But Liferay CMS doesn't sacrifice power for simplicity. If need be, you can use your developer skills to create complex presentation layer templates that make your content “pop” with dynamic elements. Once these templates have been deployed into the portal, your non-technical users can manage content using these templates as easily as they would manage static content. All of this makes Liferay CMS an appropriate choice for sites with only a few pages or sites with gigabytes of content.

### How Can Liferay's WCM Help You?

With Liferay's WCM you have the ability to create, edit, stage, publish, and approve content with easy to learn yet powerful tools. Liferay's WCM streamlines site changes for the end user versus doing a site in HTML. Some ways Liferay WCM makes this possible include:

-   Once set up, non-technical users can manage the site.
-   Liferay's fine-grained permissions system ensures your content gets to the right users.
-   To manage the site, no programming is required.
-   Content can be staged.
-   Content can be passed through a workflow.
-   Content can be published on a schedule.
-   WCM is integrated with Liferay's services, so advanced template developers can use them to query for data stored elsewhere in Liferay.

### What Features Does Liferay WCM Have?

Liferay's WCM has a host of features the makes managing the content of your site easier.

-   **WYSIWYG Editor:** A complete HTML editor that allow you to modify fonts, add color, insert images and much more.

-   **Structure Editor:** Easily add and remove fields you want available to content creators and then dynamically move them around. This editor includes an entire suite of form controls you can drag and drop onto your structure.

-   **Template Editor:** Import template script files that inform the system how to display the content within the fields determined by the structure.

-   **Web Content Display:** A portlet that allows you place web content on a page in your portal.

-   **Asset Publisher:** A portlet which can aggregate different types of content together in one view.

-   **Scheduler:** Lets you schedule when content is reviewed, displayed, and removed.

-   **Workflow Integration:** Run your content through an approval or review process.

Liferay's Web Content Management is a powerful and robust tool for creating and organizing content on your web site. Now that you've seen the basics of what you can do with Liferay's WCM, let's apply some of these concepts and create some content.

## Building a Site with Liferay's WCM

You've just been assigned the task to build a web site for a an innovative new social networking site called Nose-ster. You've decided to take advantage of Liferay Portal and its rapid deployment features as well as its ability to get a fully functional, content-rich web site with integrated social features up and running in little time.

We'll walk through the creation of Nose-ster's web site, starting by creating some simple content using Liferay's built-in WYSIWYG editor and then publishing it. We'll then take advantage of Liferay's robust structure editor. We'll use templates to display the content and the explore some of the advanced publishing features such as the built-in workflow and Asset Publisher.

### Simple Content Creation

As we've stated above, content is the reason web sites exist. Liferay Portal has made it easier than ever to get content published to your site. Because Liferay Portal is so flexible, you can use basic authoring tools right away or take advantage of the more advanced features. It's adaptable to your needs.

We'll begin by creating simple content using Liferay's WYSIWYG Editor and then we'll publish it to the home page of Nose-ster's web site. This a fast and straightforward process that demonstrates how easy it is to create and publish content onto your Liferay Portal. So let's get familiar with the Web Content section of the control panel so we can create and publish our first pieces of content.

#### Web Content Section of the Control Panel

![Figure 4.8: Choosing a Site in the Content Section](../../images/04-web-content-context-dropdown.png)

When you manage web content from the control panel, you have the ability to select the location where you will be adding or editing the content. For instance, you can add content that will be available to a specific site, or globally across the portal. The Content section of the control panel displays the name of the site where you are currently working as its heading. You can change where you're working using the drop down attached to the heading. We will add our first piece of content to the *Nose-ster* site, which we defined earlier in the chapter as the default site.

##### Rich, WYSIWYG Editing

Once you have the Nose-ster site selected, click on the *Web Content* link in the control panel. Next, click the *Add* button under the *Web Content* tab. This is a highly customizable form that is asking for a title and it contains a powerful WYSIWYG editor. From here we can add images and links, change fonts, italicize, increase font size and more. We will cover the other features such as structures, templates, and content scheduling later in this chapter.

For now, type the words *Welcome to Nose-ster* in the *Name* field. Notice that content can be localized in whatever language you want. If you click the *localize* check box, two select boxes appear which allow you to pick the language you're working in and the language that is the default. You can enter translations of your content for any language in the list. The screenshot below shows this interface but for now, we won't be using it, so you can leave it unchecked. In the content field, add a short sentence announcing that the web site is up and running.

![Figure 4.9: The Web Content Editor](../../images/04-web-content-wysiwyg.png)

##### Images, Fonts, Links, and More

Getting a new web site up and running is an exciting step for anyone, whether it is a large corporation or a small non-profit charity. To celebrate this momentous achievement at Nose-ster, let's give our announcement some of the pomp and circumstance we think it deserves!

Using the editor, select all of the current text, and then change the style to Heading 1 and the color to dark blue.

You could insert an image here as well as more text with a different style, as demonstrated in the screenshot below. You can also bullets, numbering, links to another site, or custom images. You can even add an emoticon. Let's add a smiley face at the end of our announcement.

![Figure 4.10: Customizing Content](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text, images, tables, links, and more. Additionally, you can modify the display to match the purpose of the content. Plus it's integrated with the rest of Liferay Portal: for example, when you upload an image to be added to a page, that image can be viewed and manipulated in the Image Gallery portlet.

If you're HTML savvy, Liferay CMS doesn't leave you out in the cold. You can click the *Source* button and write your own HTML if you wish.

##### Assigning Permissions

You can determine who will and who won't see your content. By default, the content is viewable by Anyone (Guest Role). You can limit viewable permissions by selecting any Role from the drop-down or in the list. Additionally, Liferay Portal provides the ability to customize permissions in more detail. Select the *More Options* link next to the drop down button, and you'll find the different activities you can grant or deny to your web content.

![Figure 4.11: Permissions for Web Content](../../images/04-web-content-content-permissions.png)

For this piece of web content, we don't need to change anything. After you're finished with permissions, click *Save*. This will save the content in draft form. Once you're satisfied with the your changes, select *Publish*. This makes the content available for display, but we still have some work to do to enable users to see it. In Liferay CMS, all content resides in a container, which is one of two portlets: Web Content Display or Web Content List. By far the most frequently used is the *Web Content Display* portlet. So let's go back to the page where we want the content displayed and add the Web Content Display portlet (in this case, the Welcome page).

#### Publishing Content with the Web Content Display Portlet

Now that we've created and published our first piece of web content for Nose-ster, it's time to display it. First, we'll need to add the *Web Content Display* portlet to our Welcome page. Do this by selecting *Add &rarr; Web Content Display* from the dockbar.

![Figure 4.11: Adding the Web Content Display Portlet](../../images/portal-admin-ch4_html_m5a656d59.jpg)

Once the portlet appears, drag it to the position on the page where you want your content to appear. You can use the Web Content Display portlet to lay out the content on your pages any way you like. You can have as many Web Content Display portlets on a page as you need.

##### Publishing Existing Content

To add existing web content, select the *gear* icon on the lower left of the portlet. You will see the message *Please select a web content from the list below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If there were lots of published content available, you could search for the content by name, ID, type, version, content, and site (click the *Advanced* link to see all the options). You can also show the available locales for your content. If you're working on the page for a particular language, you can select the translation of your content that goes with your locale.

![Figure 4.12: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also enable the document conversion. This gives your users the ability to download your content in their format of choice. This is especially handy if you are running a research or academically oriented site; users can very quickly download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling ratings so that users can rate the content, enabling comments, and enabling ratings on comments.

The Print button pops the content up in a separate browser window that contains just the content, without any of the web site navigation. This is handy for printing the content. Enabling ratings shows one of two ratings interfaces Liferay has: five stars or thumbs up and thumbs down. This can be set globally in the `portal-ext.properties` file. See chapter 12 for further information about this.

Enabling comments creates a discussion forum attached to your content which users can use to discuss your content. Enabling ratings on comments gives your users the ability to rate the comments. You may decide you want one, some, or none of these features, which is why they're all implemented as simple check boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see that you can choose the type of client to which you want to expose content. This lets you target the large screens of users' computers for expansive graphics and lots of special effects, as well as target the small screens of mobile devices with pertinent information and a lightweight page. For now, leave both checked and click the *Save* button. You can now close the configuration window.

##### Publishing New Content

To publish new content, select the *page and green plus icon* on the lower left of the portlet. This launches the same full-featured editor you've already seen in the control panel, which lets you add and edit content in place as you are working on your page.

This is another example of the flexibility that Liferay Portal offers. At times, you may want to add content directly into the Web Content Display portlet of the page you're managing, especially if you are in the process of building the page. At other times, you may want to use the control panel to create content, because at that moment you're more concerned with the creation of the content and not where the content will later be displayed. Liferay CMS supports both processes.

##### Editing Content

Once the content is displayed - whether you've selected content or created it in the Web Content Display portlet - you can edit the content directly from the Web Content Display portlet or from the control panel. To edit it from the Web Content Display portlet, select the *pencil* icon to the lower left of the portlet. This will launch the WYSIWYG editor and from there you can make any necessary changes.

![Figure 4.12: Edit, Select, and Add Icons of Web Content Display Portlet](../../images/web-content-display-icons.png)

When you publish your content this way, it will become immediately available on the site (unless, of course, you have a workflow enabled, which we'll see below). It's nice to be able to edit the content where it is when you need to, as well as edit it in the control panel.

Note: if you want to view your page the way your users will see it (i.e., without all those portlet controls and icons), go up to the dockbar and select *Toggle Edit Controls*. This makes all those extra controls you see as a portal administrator disappear. If you need to use those controls again, just select *Toggle Edit Controls* again.

### Advanced Content Creation

You will find that as your web site grows, managing it becomes more challenging. Without preset limitations, users can display content in any order and in any manner they desire (think huge, flashing letters in a font nobody can read). Also, without a scheduling feature, manually removing or replacing old content becomes cumbersome. Furthermore, without a workflow for approving content, some content might get published that should not have been, or content could get published that contains errors or omissions.

Thankfully, with Liferay Portal's Advanced Content Creation features, managing a complex and rapidly evolving web site is easy. You can use *Structures* to define which fields are available to users when they create content and use *Templates* to define how to display that content. Additionally, you can configure Liferay's built-in *Workflow* system to set up a review and publishing process and then take advantage of the *Scheduling* feature to determine when content is displayed and when it's removed. Liferay Portal gives you the management tools you need to run everything from a simple, one-page web site to an enormous, content-rich site.

#### Structures

Structures are the foundation for web content. They determine which fields are available to users as they create a new item for display. Structures not only improve manageability for you the administrator, but also make it much easier for the users to quickly add content.

For example, say you're managing an online news magazine. All of your articles need to contain the same types of information: a title, a subtitle, an author, and one or more pages of text and images that comprise the body of the article. If Liferay only supported simple content as has been described above, you would have no way to make sure that your users entered a title, subtitle, and author. You might also get articles that don't match the look and feel of your site. If titles are supposed to be navy blue and they come in from your writers as light blue, you need to spend time reformatting them before they are published.

Structures give you the ability to provide a format for your content so that your users know what needs to be entered to have a complete article. Using structures, you can provide a form for your users which spells out exactly what is required and can be automatically formatted using a template.

To create a structure, you simply add form controls such as text fields, text boxes, text areas (HTML), check boxes, select boxes, and multi-selection lists, as well as specialized, Liferay-specific *Application Fields* such as Media Gallery and Documents and Media Library right onto the structure. Furthermore, you can move the elements around by dragging them where you want them. This allows you to quickly brainstorm different orders for your input fields. Additionally, elements can be grouped together into blocks which can then be repeatable. Template writers can then write a template which loops through these blocks and presents your content in innovative ways, such as in sliding navigation bars, content which scrolls with the user, and more.

Let's take a look at how we edit a structure.

##### Editing a Structure

Go back to the control panel and select the *Web Content*. Click *Add* from the Web Content page to add another piece of content to your portal. Instead of going right for the content this time, however, we're going to create a structure. To edit a structure, simply click on the *Edit* icon next to the *Structure* heading near the top of the page.

It is very easy to edit structures: all you have to do is drag elements into the structure and then give them names. For instance, select the *Checkbox* element under the *Form Controls* tab and drag it onto the structure. You can do the same with any of the elements. To remove it from the structure, simply select the *Delete* icon (black circle with X) in the upper right corner of the element. Take a moment to add, delete, and rearrange different elements.


Liferay supports the following elements ![Figure 4.13: Structure Elements](../../images/04-web-content-structure-editor.png)
in structures:

**FORM FIELDS**

**Text Field:** Used for items such a titles and headings.
**Text Box:** Used for the body of your content or long descriptions.
**Text Area (HTML):** An area that uses a WYSIWYG editor to enhance the content.
**Checkbox:** Allows you to add a checkbox onto your structure. Template developers can use this as a display rule.
**Selectbox:** Allows you to add a select box onto your structure.
**Multi-selection Lists:** Allows you to add a multi-selection list onto your structure.

**APPLICATION FIELDS**

**Image Gallery:** Allows you to add the Image Gallery application into your structure.
**Upload Image:** Allows you to add the upload image application into your structure.
**Document Library:** Allows you to add the Document Library application to your structure.

**MISCELLANEOUS**

**Link to Page:** Inserts a link to another page in the same site.
**Selection Break:** Inserts a break in the content.

##### Editing Elements

When creating a new structure it is essential that you set the variable names for the elements for the template writers. Otherwise, the generated variable names will be very difficult for a template writer to follow. In your structure, you can add the element *Text Area* (HTML) which has the Field Label *Instructions*. However, we want to give it the variable name `Steps`. This can be done very easily: at the bottom of every form element is a **Variable Name** field. Replace the generated name with the name you want to use. There are many other options for fields, including setting tooltips for users. To set these options, select the *Edit Options* button in the lower right corner of the element.

You can type instructions in the **Instructions for the User** field and even display them as a tooltip. For the Nose-ster structure, type in something that will help users know what to put into the Body element (example: this is an HTML Text area for the body of your content). Also, enable the *Display as Tooltip* box. Now, when users hover over the Help icon near your title, they will see the instructions you entered.

##### Assigning Permissions

Permissions to the structure are straightforward. Generally, you don't want most users editing structures as this often requires a developer to modify the template assigned to the structure. However, you do want to make your structure viewable to everyone that will be using it to add web content.

![Figure 4.14: View Permission for a Structure](../../images/04-web-content-structure-permissions.png)

You have the ability to grant or deny permissions based on Roles. For instance, you can give the Guest role the ability to *Add Discussion* or you may want to give a *Site Member* the ability to *Delete*. Liferay Portal makes it easy to configure the permissions based on your specific needs for the site.

#### Templates

Developers create templates to display the elements of the structure in the markup that they want. Content can then be styled properly using CSS, because markup is generated consistently when users enter content. In essence, templates are scripts that tell Liferay how to display the content within the fields determined by the structure. Any changes to the structure require corresponding changes to the template. Otherwise, new or deleted fields will produce errors on the page. Without a template, the portal has no idea how to display content which has been created using a custom structure.

##### Template Types (VM, XSL, FTL, and CSS)

Liferay supports templates written in four different templating languages. This is so that you can get started right away. If you have experience with one over another, you can use whichever one you've already used before. If you are just starting with any one of them, we recommend Velocity, as it is less “chatty” than XSL and extremely simple to understand.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix logic with HTML. This is similar to other scripting languages, such as PHP, that you may have seen before, though Velocity is much simpler. It's been in the product the longest, so it is probably the most widely used language for templates in Liferay CMS. If you haven't used any of the template languages before, we recommend using Velocity: you'll get up to speed the fastest.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to transform the underlying XML of a structure into markup suitable for the browser. While it may not be as clean and compact as Velocity or FTL, it is widely used for transforming XML into other formats.

**FTL** (FreeMarker Template Language): Freemarker is a templating language which could be considered a successor to Velocity, though it is not yet as popular. It has some advantages over Velocity for which it sacrifices some simplicity, yet it is still easy to use and less “chatty” than XSL.

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very straightforward and modifications are simple (colors, fonts, layouts, etc). If your structure is more complex, however, you'll need to use one of the other options.

##### Adding a Template

Liferay CMS makes it easy to create structures, templates, and content from the same interface. Let's go through the entire flow of how we'd create a structure, link it to a template, and then create content based on what we've defined. We'll use Velocity for our template, and we'll lay out the structure fields systematically to go along with the format we've defined for our content.

![Figure 4.14: Adding Template Interface](../../images/04-web-content-templates-create.png)

1.  Go back to the Web Content section of the control panel and click *Add* under *Web Content*.
2.  Click the *Edit* icon for Structures.
3.  Remove the Content field and add the following fields:

**Field Type**, **Variable Name**

* Text, *title*

* Text Box, *abstract*

* Image, *image*

* Text Area, *body*

4.  Select *Save* and give the structure a name.
5.  Go back to the main web content page and select the *Templates* tab.
6.  Select *Add Template.*
7.  Type in a name and description.
8.  De-select the box labeled *Cacheable.*
9.  Select VM as the language.
10. Click *Select* and choose a Structure that goes with the Templates.
11. If you've written the script beforehand, you can select *Browse* to upload it from your machine. Otherwise, you can click *Launch Editor* to type the script directly into the small editor window that appears.
12. Select *Save.*
13. Return to the Web Content tab and open the Company News content. You'll see the new element labeled Abstract just below the Title.

Below is the template script for this structure. It is written in
Velocity:

    #set ($renderUrlMax = $request.get("render-url-maximized"))
    #set ($namespace = $request.get("portlet-namespace"))
    #set($readmore = $request.get("parameters").get("read_more"))
	<h1>$title.getData()</h1>
    #if ($readmore)
	<p>$abstract.getData()</p>
	<p>$body.getData()</p>
    #else
	<p>
	<img src="${image.getData()}" border="0" align="right">
	$abstract.getData()</p>
	<a href="${renderUrlMax}&${namespace}read_more=true">Read More</a>
    #end

This template is pretty small, but it actually does quite a bit. First, a portlet URL which maximizes the portlet is created. Once this is done, we get the namespace of the portlet. This is important because we don't want our URL to collide with another URL that might be on the page.

After this, we attempt to get a request parameter called `read_more`. Whether or not we were successful in getting this parameter is the key to the rest of the script:

-   If we were successful in getting the `read_more` parameter, we display the abstract and the body below the title (which is always displayed).

-   If we were not successful in getting the `read_more` parameter, we display the image, the abstract, and the link we created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 4.x: Initial View](../../images/04-web-content-adv-example1.png)

![Figure 4.x: After Clicking "Read More"](../../images/04-web-content-adv-example2.png)

Of course, there is much, much more you can do with structures and templates. Check out the Liferay Wiki ([http://wiki.liferay.com](http://wiki.liferay.com/)) for further information and examples.

#### Assigning Template Permission

Permissions for templates are very similar to permissions for structures. Generally, you only want specific developers accessing the template. However, you may want to make the templates viewable to some content creators who understand the template scripting language, but are not directly writing the scripts. You can determine who views the template by selecting from the *Viewable By* select box beneath the *Permissions* tab. By default the *Anyone (Guest Role)* is selected.

You'll also want to determine how users can interact with the template. You can do this by selecting the *More* link.

From the *More* link, you have the ability to grant or deny permissions based on Roles. For instance, you may create a role with the ability to update the template and create a second role that can both update and delete. Liferay Portal makes it possible to assign permissions based on the roles and responsibilities within your organization.

### Advanced Publishing Options

As we discussed above, as your site becomes larger and more complex, management of the content becomes more challenging. We've discussed Liferay management tools that help you create content quickly and in an orderly fashion. We created a simple announcement and then covered Liferay's new structure editor that allows you to quickly design a structure and prepare it for the template designers. Then, we went through applying a template to the structure. We demonstrated how to display our content using the Web Content Display portlet. Now, we're ready to take advantage of Liferay's advanced publishing options.

If a web site isn't properly managed, it can quickly become out of date, and that drives viewers away. If people are finding your site because of search engines, you don't want them presented with outdated (and now inaccurate) web content.

Additionally, you may want the ability to create content and send it through a review and approve process weeks before you want it displayed on the web site. Liferay gives you this flexibility with the *Schedule* feature in the Web Content portlet.

#### Scheduling Web Content

You can publish your content on a schedule. You can determine when the content will be displayed, expired, and/or reviewed. This is an excellent to way to keep your site current and free from outdated (and perhaps incorrect) information. The scheduler is built right into the form that your users make use of to add web content, in the same column as the structure and template selectors.

![Figure 4.16: Schedule for Publishing Content](../../images/04-web-content-schedule.png)

**Display Date:** Allows you to determine (within a minute) when content will be displayed.
**Expiration Date:** Allows you to set the date when the content will expire. The default is one year.
**Never Auto Expire:** Allows you set your content to never expire.
**Review Date:** Allows you to set a date when you want the content reviewed.
**Never Review:** Allows you to determine that your content will not be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control in managing when, and for how long, your web content is displayed on your web site. Additionally, you have the ability to determine when your content should be reviewed for accuracy and/or relevance. This makes it possible to manage your growing inventory of content.

#### Tags and Categories

Tags are keywords that can be attached to web content in order to help users find content. Categories are a hierarchical organization of content whose structure can be defined by administrators. With tags and categories, you can make it easier for your users to find your content through search or navigation.

##### Why Tag?

Tags are keywords attached to a piece of web content in the portal. By assigning a tag to web content, you define metadata about that content. This can be used by Liferay's search engine to score the results of a search, enabling users to find content that is most relevant to their search. Tags can be created on the fly by the creator of the content, and it is important to tag your content whenever it is created. If you don't tag your content, all the search engine has to go on is the full text of the content when a user does a search, and that might not produce the most optimal results.

Tagging also helps with navigation. Liferay Portal has two portlets specifically designed for navigating content using tags: Tag Cloud and Tag Navigation. If you add either of these to a page, you can use them to show the topics contained in your content.

##### Who Tags?

Tags in web content are added by the creator of the content. They can be added on the fly or they can be selected from the existing library of tags. For most of the portal, users tag content, but for web content, only the content creator tags the content, because there is no user interface for regular users to tag web content.

It is important that you both tag and categorize your content when you enter it.

##### What is the Difference Between a Tag and a Category?

Categories are defined by someone with administrative access to the content. They are hierarchical, tree-like structures that users can use to find content. Categories are different from tags in that they are never created by end users. Instead, categories define how your content is organized from the point of view of the owner of the content. A good example of categories might be the table of contents of a book: it shows the hierarchical structure and organization for all of the content within that book. This shows that the structure of the book has been planned ahead of time by the creator of the book. Categories do the same thing. By contrast, tags are like the index of a book: they show where many different topics are mentioned within the book in alphabetical order. When a search is done throughout the book, even the author might be surprised at how many times he or she mentions a particular topic outside of its category. So both ways of organizing your content are important, especially if your users will be using search to find content.

Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 4.17: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used administratively to manage your tags and categories. We'll take a look at this interface in the next chapter.

### Using the Asset Publisher Portlet

As we create web content, it's important to keep in mind that to Liferay, the pieces of content are assets, just like message board entries and blog posts. This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of assets such as images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts, or message board messages in context with your content. Let's take a look at some of its features.

#### Querying for Content

The Asset Publisher portlet is a highly configurable application that lets you query for mixed types of content on the fly. By giving you the ability to control what and how content is displayed from one location, the Asset Publisher helps you to “bubble up” the most relevant content to your users.

To get to all of the portlet's options, click the *Configuration* link in the portlet's menu (the wrench icon).

##### Selecting Assets

The ability to configure how content is displayed and selected by your users further demonstrates the flexibility of the Asset Publisher. You get to choose how content is displayed. You can select it manually for display in a similar way to the Web Content Display portlet, or you can set up predefined queries and filters and let the portal select the content for you, based on its type or its tags and categories.

Let's first take a look at how we might select content manually. You'll see that it's very similar to the Web Content Display portlet.

##### Selecting Assets Manually

By selecting *Manual* from the select box beneath *Asset Selection*, tell the Asset Publisher that you want to select your content manually. You can select what you want to be published within the portlet, or you can create new content right from within the Asset Publisher.

![Figure 4.18: Selecting assets manually is very similar to the Web Content Display portlet, except you have many other content types to choose from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the content right where you are. You can create blogs, bookmarks, calendar entries, documents, images, and of course, web content. Anything you create here will be added to the list below of assets that will be displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can pick from existing content in the portal that either you or your users have created. Has someone written an excellent wiki page that you want to highlight? Select it here, and it will be displayed.

The Asset Publisher gives you the ability to mix and match different content types in the same interface. Once you have your content selected, you can move on to the display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select content dynamically.

##### Selecting Assets Dynamically

The default behavior for the Asset Publisher is to select assets dynamically according to rules that you give it. These rules can be stacked on top of each other so that they compliment each other to create a nice, refined query for your content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be selected.

**Asset Type:** Choose whether you will display any asset or only assets of a specific type, such as only web content, only wiki entries, or any combinations of multiple types.

![Figure 4.19: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)


**Filter Rules:** Add as many filters on tags or categories as you like. You can choose whether the content contains or does not contain any or all categories or tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you can then decide how the content will be displayed.

#### Ordering and Grouping

You can display the content returned by the filters above in order by title, create date, modified date, view count, and more in ascending or descending order. For instance, you may have a series of “How To” articles that you want displayed in descending order based on whether the article was tagged with the *hammer* tag. Or, you may want a series of video captures to display in ascending order based on a category called *birds*. You can also group by *Asset*, *Type*, or *Vocabularies*. Vocabularies are groups of categories defined by administrators in the *Categories* section of the control panel. Again, we'll see more about categories in chapter 4.

In the *Ordering and Grouping* section of the Asset Publisher, you have great control over how content is ordered and grouped in the list, but this is only one aspect of how your content will be displayed. You can refine the display through many other display settings.

#### Display Settings

The Display Settings section gives you precise control over the display of your assets. There are a multitude of options available to configure how you want your content to appear. You can configure the style, length of abstracts, behavior of the asset link, maximum items to display, pagination type, and file conversions. Additionally, you can enable printing, flags, ratings, comments, and comment ratings, and these work the same way they do in the Web Content Display portlet.

##### Display Style

**Abstracts:** Shows the first 200-500 characters of the content, defined by the **Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

##### Other Settings

**Asset Link Behavior:** When the link to the asset is clicked, it can be displayed in the Asset Publisher or in the portlet to which the asset belongs, such as the Blogs or Message Boards.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next navigation; regular includes a way of selecting the page to which you'd like to navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it from the list.

**Show Available Locales:** Since content can be localized, you can have different versions of it based on locale. This will show the locales available, enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org integration, you can allow your users to convert the content to one of several formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see below).

![Figure 4.20: Show Metadata](../../images/portal-admin-ch4_html_m409b2939.jpg)

**Enable RSS Subscription:** This gives users the ability to subscribe to the content via RSS Feeds.

The Display Settings section of the Asset Publisher has numerous options to help you configure how your content selections are displayed to your users. Even though there are many choices, its easy to go through the options and quickly adjust the ones that apply to you. You'll want to use the Asset Publisher to query for mixed assets in the portal that have relevant information for your users.

#### Staging and Workflow

Staging is a major feature of Liferay CMS. The concept of staging is a simple one: you don't want your users seeing your web site change before their eyes as you are modifying it, do you? Liferay's staging environment allows you to make changes to your site in a specialized *staging area*, and then when you are finished, publish the whole site to your users.

You can use staging in multiple ways. Larger organizations may consider having a staging server—a separate instance of Liferay Portal which is used just for staging. Content creators can then use this server to make their changes while the live server handles the incoming user traffic. When changes to the site are ready to be published, they can be pushed remotely to the live server.

Alternatively, you may want to host both your staging environment and your live environment on the same server—particularly if you are part of a smaller organization with less resources to purchase servers. Either way, once set up, the interface is the same; the only difference comes when it's actually time to publish your content.

In addition, Liferay 6.1 adds the capability to create multiple branches of staged content, so that you can manage several future versions of a page simultaneously. Branches can be merged and published through an intuitive UI.

##### Enabling the Staging Environment

The Staging tab allows us to make changes in a staging environment and preview our work before publishing it to the live site. Let's create a staging environment for Nose-ster's homepage.

First, let's add a new page. Click *Add &rarr; Page* from the somewhere in the default community and name the new page *News and Events.* Next, click the *View Pages* button and add the Alerts and Announcements portlets to it.

Now we're ready to activate the staging feature for this site. Go to the control panel then to *Site Settings* and select *Staging* from under the *Advanced* heading.

![Figure 4.21: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

We'll assume we don't have a separate staging server, and so we'll select a staging type of *Local Live*. If you were to select *Remote Live*, you would also have needed to supply the name or IP of the remote server where staged content should be published, the port (80 if Liferay is sitting behind a web server, or the port your application server is listening on if not), and the remote site or organization ID. You can find this ID by selecting *Actions &rarr; Edit* on any site in the control panel. Either way, once we make a selection (*Local Live* or *Remote Live*), more options will become available to us.

We'll cover many of the collaboration portlets listed here when we come to chapter 4. For now you just need to be aware that the option is available to enable or disable staging for any of them, and you need to decide if you would like to stage content for these portlets. In the case of the collaborative portlets, the answer would usually be “no.” Why? Because portlets such as the Message Boards are designed for user interaction. If their content were staged, you'd have to manually publish your site whenever somebody posted a message on the message boards in order to make that message appear on the live site.

Generally, you'll want web content to be staged, because end users aren't creating that kind of content—web content is the stuff you publish to your site. But portlets like the message boards or the wiki would likely benefit from *not* being staged.

Enabling *Page Versioning* gives you the ability to work in parallel on multiple versions of the same page, as well as the flexibility to easily revert to a previous version if you encounter any issues. Check *Enabled On Public Pages* so that we take a look at versioning.  

##### Using the Staging Environment

If we navigate back to the News and Events page we'll now notice some new items along the top of the screen which will help us manage staged pages, and you'll also notice that most of your page management options have been removed. Click on *Staging* to view the staged area. You will now have your management options restored, and you'll have some new options related to staging.

![Figure 4.22: You can see the new bar that staging adds to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Calendar portlet and then click on *Live* from the dockbar. Notice that the Calendar portlet isn't there. As you can see, only the page changes are staged; web content itself can go through a whole different workflow process (which we'll get to later). Go back to the staged page, and take a look at the options you have available. From here you can *Undo* changes, view a *History* of changes, *Mark as Ready for Publication*, and *Manage Page Variations*.

**Undo:** allows you to step back through recent changes to a page, which can save you the time of manually adding or removing portlets if you make a mistake.

**History:** will show you the list of revisions of the page, based on the times when it was published.

**Manage Page Variations:** allows you to create multiple versions of a staged page that you can either merge together, or use to work on several major changes at the same time.

After you're done making changes to the staged page, click on *Mark as Ready for Publication*. Nothing will happen immediately, but now the page, with any changes that you've made, can be published to the Live Site. If you click on the dropdown next to the Staging link at the top, you will have the option to *Publish to Live Now* or *Schedule Publication to Live*.

**Publish to Live Now:** will allow you to immedatiately push any changes to the Live Site.

**Schedule Publication:** to Live will give you the option to set a specific date to publish, or to setup recurring publishing. You could use this, for example, to publish all changes made during the week every Monday morning without any further intervention.

![Figure 4.22: Ready to publish to Live ](../../images/04-web-content-staging-publish.png)

You may have noticed that something appears to be missing from this process, in particular, you might be asking the question, "How do I reject changes?" Starting with Liferay 6.1, Staging is integrated with Liferay's Workflow engine. In order to have a review process for staged pages, you will need to make sure that you have a workflow engine configured, and that you have Staging set up in the workflow. When using a worfklow, clicking *Mark as Ready for Publication* will submit the staged pages into the workflow. Once all necessary approvals have been completed, the *Publish to Live Now* and *Schedule for Publication* options will become available.

#### Site Pages Variations

Let's say that you're working in an environment where you'll have several major changes to a page or a set of pages over a short period of time, and need to be working on multiple variations on the same time to ensure that everything has been properly reviewed before it goes live. With staging in Liferay 6.1 you can do this with **Page Variations**.

To create a new page variation, click on *Manage Page Variations* on the staging menu bar. This will bring you to a list of existing versions of your page. Click *Add Page Variation* to create a new one. From the *Add Page Variation* screen, you can set a Name, Description, and set your new variation to copy the content from an existing variation.

![Figure 4.23: Creating a new variation](../../images/04-web-content-add-site-pages-variation.png)

Site Page Variations are the top level variations, and each one will become it's own **Main Variation**, and each Main Variation can in turn have different variations for each page under it. You can switch between different variations by simply clicking on them from the staging menu bar. It is also possible to set permissions on each variation, so that certain users wouldn't have access to manage some, but not all page variations. Page variations also have different branches, so you could, for example, have three main variations, and then have have three varitions of each of those, resulting in nine different versions of a page. While can be confusing at first, it can all come together when you add **Merging**.

Merging occurs automatically once you click *Merge*, and it will do it's best to preserve all changes made in different branches. For example, if you add two different portlets to a page in two different variations, and then merge them with the main variation, both portlets would be added to the page after the merge. You could then do some tweaking on the final merged product before publishing to live.

![Figure 4.24: Our branching example](../../images/04-web-content-staging-forking.png)

For example, suppose we want to manage a couple of variations of a page for Thanksgiving and Christmas. Initially we could create a Site Page Variation for general changes called *Current Design* and make any maintenance changes there. Whenever we have anything that needs to be published, we would marge those changes with the Main Variation and publish it.

In the mean time, we could duplicate the pages from the Current Design variation in a *Thanksgiving Page* variation, and make additional changes to the page. Once it gets close to Thanksgiving, we could merge Thanksgiving Break with the Current Design, and subsequently merge up to the Main Variation again. Once this is complete, we could make any necessary tweaks after the merge, and Publish the pages. We would then repeat the same steps at Christmas time.

![Figure 4.25: The Thanksgiving branch.](../../images/04-web-content-branch-thanksgiving.png)

#### Wrapping up Staging

You can enable staging on an individual site basis, depending on your needs. This makes it really easy to put strict controls in place for your public web site, but to open things up for individual sites that don't need such strict controls. Liferay's staging environment is extremely easy to use and makes maintaining a content-rich web site a snap.

### Using Liferay's Integrated Workflow with Content Management

Workflow is essentially a predetermined sequence of connected steps. In Liferay CMS, workflow is designed to manage the creation, modification, and publication of web content. You can set up a workflow so that content cannot be published without going through an approval process that you design. In this way, content goes up on your site only after it has been reviewed and approved.

Liferay's workflow engine is called Kaleo workflow, and it ships with Liferay CE. If you have uninstalled it or are using EE, it needs to be installed and configured separately. This was covered in chapter 2. For now, we'll assume it's installed and look at how you can take advantage of workflow in getting your content through any approval steps between creation and publication.

To enable workflow for Web Content, navigate to the control panel and select *Workflow Configuration*. From there, select a workflow that has been deployed to Liferay.

![Figure 4.26: Enabling Workflow for Content Management](../../images/04-web-content-workflow-config.png)

As you will discover in chapter 6, you can design workflows to suit your organization's approval process. For Nose-ster's implementation we will use the *Single Approver* workflow which ships with the product.

#### Defining Workflows for Web Content

Let's go ahead and set up Liferay's Workflow for the Nose-ster web site.

1.  Go to the control panel and select *Workflow Configuration* from the left panel.

2.  From the select box, choose *Single Approver* for Web Content. Click *Save.* Note that you can add workflow to many of Liferay's portlets.

That's all it takes to set up workflow for web content. Now publishing content works a little bit differently. Let's go through the process of publishing details for new class offerings at Nose-ster. Return to the home page and click on the *Add Web Content *icon on the Web Content Display portlet. Call it *Course Offerings* and enter some content. Notice that the Publish button is now gone. In its place is a *Submit for Publication* button. Go ahead and click it.

![Figure 4.27: Pending Workflow](../../images/04-web-content-workflow-config.png)

Next, go to the *Workflow Tasks* in control panel and then select *My Workflow Tasks*. You will see the option to Review Content for Sales Goals. It shows because you are logged in as an Administrator. There is also a Content Approvers role which is defined by this workflow, and anyone in this role can approve content as well.

To approve the content, you must first take ownership of it. Click on the task. You should see the screen below.

Taking ownership of, reviewing, and approving content is very easy:

1.  Click the *Assign to Me* button. Alternatively, you could assign it to someone else in the Content Approvers role or create / update a due date for the content's approval.
2.  Once you've assigned it to yourself, buttons allowing you to approve or reject the content appear. Click *Approve*.
3.  You're asked to submit a comment. You'd have to do this for either *Approve* or *Reject*. Add a comment and click *Save*.
4.  The content is now approved.

In a real world situation, you obviously wouldn't want the person who created the content to be the one who approves it. Instead, you would have one or more roles designed for users who will be creating content, and you will have specific users assigned to one or more roles for approving content. Our example was of a very straightforward workflow, as it has only a single approver. Kaleo workflow allows you to design workflows that go through as many steps as you need to conform to your business processes. We look at Kaleo workflow in more detail in chapter 6.

## Summary

This chapter has been your guide to Liferay Web Content Management. We've seen how pages are created and managed in Liferay sites and organizations. It is easy to create whole page hierarchies without ever leaving your browser. You can import and export pages using LAR archives, and these can also be used to transfer a site from one Liferay Portal server to another.

Liferay CMS also includes a powerful staging environment, allowing you to stage content locally on the same server or remotely to another server. And when working on pages, you have the ability to use layouts and nested portlets to design every page to look exactly the way you want it to look.

Whether your site is small and static or large and dynamic, Liferay's WCM enables you to easily plan and manage it. With tools such as the WYSIWYG Editor, Structures, and Templates, you can quickly add and edit content. With the Web Content Display and Asset Publisher, you can rapidly select and configure what and how your content is displayed. And by using Liferay's integrated workflow, you can set up custom publishing rules to fit your organization. You will find that managing your site becomes far easier when using Liferay's Web Content Management system.
