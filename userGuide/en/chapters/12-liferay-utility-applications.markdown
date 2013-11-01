# Liferay Utility Applications [](id=lp-6-1-ugen12-liferay-utility-applications-0)

In this chapter we'll look at some Liferay utility applications that might be
useful for you. The Software Catalog is currently packaged with Liferay but will
soon be replaced by Liferay Marketplace. Please see chapter 13 for information
about Liferay Marketplace and managing Liferay plugins. The Knowledge Base
application is an EE-only plugin. In this chapter we'll discuss several of these
applications:

- Bookmarks
- Weather
- Software catalog
- Shopping
- Knowledge Base

Liferay's Bookmarks application is a simple way for users to keep track of URLs
in the portal that can also be used by an administrator to publish relevant
links to groups of users. The Software Catalog allows you to define a set of
software items to display to visitors. The Knowledge Base application allows you
to create articles and organize them into full books or guides that be published
on your portal.

## Capturing Web Sites with the Bookmarks Portlet [](id=lp-6-1-ugen12-capturing-web-sites-with-the-bookmarks-portlet-idlp-6-1-uge-0)

Many of us enjoy collecting things we value. They may be stamps, comic books,
sea shells, or fabulous shoes. The list goes on and on. But have you considered
URLs collectible? Having a thorough collection of links can be a great way to
add value to your portal's usability.

With Liferay's Bookmarks application, users collect and manage URLs in the
portal. They can add, edit, delete, export and import bookmarks. Users can use
links to access regularly visited web sites. Administrators can publish links
tailored to specific groups of users. Both internal pages as well as external
sites can be bookmarked.

### Organizing Bookmarks by Folder [](id=lp-6-1-ugen12-organizing-bookmarks-by-folder-0)

You can store all your important links in one place and you can manage this data
easily using folders. You can create, edit, and delete bookmark folders. You get
to decide how many bookmarks or folders are displayed on a page. Bookmark
folders can have any number of subfolders.

Here's an example of what one bookmarks portlet might look like. Bookmark
Folders are displayed above individual bookmarks. 

![Figure 12.1: Individual bookmarks, not associated with a folder, are listed separately.](../../images/bookmarks-folder-view-wide.png)

In this example, there are eight bookmark folders, four of which have
subfolders. The columns showing the number of folders and the number of entries
show the subfolders and the entries contained within each top level folder. Note
that total number of bookmarks includes those in the subfolders.

Using the Actions button on the right, you can edit the folder, manage
folder permissions, delete the folder, add a subfolder, or add a bookmark to
the folder. 

As your collection of links grows, you may need to add more subfolders to keep
things in order. Should you decide a link needs to move from one folder to
another, you can manage this using the Edit option for that link. 

#### Moving a Link [](id=lp-6-1-ugen12-moving-a-link-0)

Just for fun, let's move a link from the main bookmarks folder into a subfolder
one level down. We'll move the Liferay link into the Nonfiction subfolder in the
Literature folder. The Nonfiction subfolder is a child of the Literature folder.
We need to move the link to the Literature folder first before moving it into
the Nonfiction subfolder. 

![Figure 12.2: You can move a link one level at a time by selecting the Select button.](../../images/bookmarks-select-remove-buttons.png)

To achieve this, we select *Edit* from the Actions button for the link. In the
Edit view, find the section called *Folder*. When applicable, this section 
contains breadcrumb links to allow you to navigate freely among the other folder
levels. Currently, there are two buttons here labeled Select and Remove. Click
the *Select* button and a new window appears, like the one below. Clicking the
*Remove* button here doesn't affect the link. It is used solely for moving links
out of folders and subfolders back into the main Bookmarks view.

![Figure 12.3: Select the Choose button next to the desired folder.](../../images/bookmarks-choosing-subfolder.png)

Now choose the *Literature* folder. When you do this, notice how the link's
Folder section changes to reflect the new location of the link.

![Figure 12.4: When you choose a folder, the folder navigation changes to reflect the new location of the link.](../../images/bookmarks-choosing-subfolder2.png)

When you choose *Save*, you return to the Bookmarks portlet. Notice that the
Liferay link is no longer under the Bookmarks section. Select *Literature*
to reveal its contents. In the example below, you can see the Liferay link is
now in the bookmarks section of the Literature folder.

![Figure 12.5: When you move a link to a folder, it remains in the bookmarks section until it's moved into a subfolder.](../../images/bookmarks-link-move.png)

Select *Edit* from the Actions button next to the Liferay link, then click
*Select*. Choose the *Nonfiction* subfolder and again notice the change in the
folder-level breadcrumbs. Click *Save* and the Literature folder view appears.
Select *Nonfiction* to see your link in the subfolder's bookmarks list. Piece of
cake, right?

To move a link out of a subfolder and into a higher-level folder, Edit the
link in the subfolder and choose *Select*.

![Figure 12.6: To move a link up to a higher-level folder, choose the appropriate folder from the breadcrumbs in the Select view.](../../images/bookmarks-link-move2.png)

In the resulting window, select the appropriate folder from the breadcrumbs at
the top. In this example, we selected *Literature* for consistency. This opens a
new window, like the one below, showing the other folder options.

![Figure 12.7: In this view, you can move the link into several different locations.](../../images/bookmarks-link-move3.png)

You can choose one of the other subfolders from the list, or you can choose
*Home* from the breadcrumbs at the top. When you verify that the desired folder
is the one currently displayed in the breadcrumbs, click *Choose This Folder*.
Then click *Save* and you're done. If you don't like any of the subfolders
listed, you can place the link into a new subfolder by using the *Add Subfolder*
button.

If you choose *Remove* instead of Select in the above example, you take the link
out of both subfolders and return it to the main bookmarks view.

Now that you have an understanding of how bookmark folders are used, let's
create some new bookmarks.

### Adding and Using Bookmarks [](id=lp-6-1-ugen12-adding-and-using-bookmarks-0)

Navigate to your portal and add the Bookmarks application to your page by
selecting *Add* &rarr; *More*. The portlet looks like this by default:

![Figure 12.8: Initially, no bookmarks are listed in this form until they're created.](../../images/bookmarks-add-portlet.png)

Across the top of the portlet are links labeled Home, Recent, and Mine. There is
also a Search field and button.

**Home:** returns you to the top level of the portlet.

**Recent:** displays a list of the latest bookmarks that have been added.

**Mine:** displays a list of the bookmarks you added to the portlet.

**Search:** lets you search for bookmarks by name, category, or tags.

When you select the *Permissions* button on the right, a list of Roles and their
associated permissions appears. The options are Add Entry, Add Folder,
Permissions, and View. When you are finished selecting the permissions click
*Save*.

Clicking the *Add Folder* button in the Bookmarks application reveals this form:

![Figure 12.9: It's not necessary to enter a description for a Bookmarks folder.](../../images/bookmarks-add-new-folder-form.png)

Here you can choose the folder's name, a description of its contents, and who
can view it. Under More Options, you can set portlet permissions for various
Roles to the folder. Click *Save* when you are finished.

To create a bookmark, click the *Add Bookmark* button. This form is similar to
the New Folder form but has a few more options.

![Figure 12.10: When you use the Add Bookmark form, you must enter a valid URL in the required field.](../../images/bookmarks-add-new-form.png)

Click *Select* to choose the folder for the new bookmark. Click *Remove* to
delete a bookmark from the selected folder. As stated above, a removed link goes
into the list of general bookmarks that aren't associated with a folder. These
are listed in the bookmarks section, below the folders, in the portlet.

Below the Permissions there are additional options for Categorization and
Related Assets, just like in other Liferay applications. Please see chapter 5 on
the Asset Framework for further information about this.

Once you have added a new bookmark, it appears in the portlet. From here, you
can manage your bookmark using familiar Liferay editing features. Collecting and
organizing your links is a snap when you use Liferay's Bookmarks application.

## Weather [](id=lp-6-1-ugen12-weather-0)

Liferay's Weather portlet displays basic weather-related information
(temperature, conditions) for multiple configurable locations. It's available as
an app from Liferay Marketplace. For each configured location, a link to [Open Weather Map](http://openweathermap.org/)
is provided that points to more detailed information. The Weather portlet also
provides a search bar that allows users to find information about locations that
aren't listed by the portlet. Searching via the search bar forwards the user to
[Open Weather Map](http://openweathermap.org/), where the search is executed.

![Liferay's Weather portlet displays basic weather-related information (temperature, conditions) for multiple configurable locations.](../../images/weather-portlet.png)

To configure the displayed locations, open the Weather portlet's configuration
window. In the first text area, enter the names of the cities about which you'd
like weather information to be displayed. Alternatively, you can use zip codes.
Enter one city or zip code per line. For the temperature format, you can choose
between Celsius and Fahrenheit.

Sometimes you have a team of developers creating lots of plugins. Next, you'll 
see how to create your own repository for those plugins. 

## Creating Your Own Plugin Repository [](id=lp-6-1-ugen15-creating-your-own-plugin-repository-0)

As your enterprise builds its own library of portlets for internal use, you can
create your own plugin repository to make it easy to install and upgrade
portlets. This will allow different departments who may be running different
instances of Liferay to share portlets and install them as needed. If you are a
software development house, you may wish to create a plugin repository for your
own products. Liferay makes it easy for you to create your own plugin repository
and make it available to others.

You can create your plugin repository in two ways:

1.  Use the Software Catalog in the Control Panel to create the repository by
    using its graphical interface and an HTTP server.

2.  Create an XML file using the Liferay Plugin Repository DTD
    (`http://www.liferay.com/dtd/liferay-plugin-repository_6_0_0.dtd`) and an
    HTTP server.

Both methods have their benefits. The first method allows users to upload their
plugins to an HTTP server to which they have access. They can then register
their plugins with the repository by adding a link to it via the Control Panel's
graphical user interface. Liferay will then generate the XML necessary to
connect the repository to a Control Panel running on another instance of
Liferay. This XML file can then be placed on an HTTP server and its URL can be
added to the Plugin Installer, making the portlets in this repository available
to the server running Liferay.

The second method does not require an instance of Liferay to be running. You can
upload plugins to an HTTP server of your choice, then create an XML file called
`liferay-plugin-repository.xml` manually. If you make this file available on an
HTTP server (it can be the same one which is storing the plugins or a different
one), you can connect the repository to a Plugin Installer in the Control Panel
running on an instance of Liferay.

We will first look at creating a plugin repository using the Software Catalog in
the Control Panel.

### Software Catalog [](id=lp-6-1-ugen15-the-software-catalog-0)

You will want to use the Software Catalog if you will have multiple users
submitting portlets into the repository and if you don't want to worry about
creating the `liferay-plugin-repository.xml` file yourself.

![Figure 12.11: The Software Catalog with Nothing Installed](../../images/marketplace-software-catalog.png)

Each site in your portal can have an instance of the Software Catalog. The
Control Panel presents you with the software catalog for whichever site you are
working on. This means different sites can have different software repositories,
so you can host several software repositories on the same instance of Liferay if
you wish, they just have to be in different sites. Choose the site that will
host the plugin repository and go to the Control Panel. You will see at the top
of the screen a message that says "Content for [Site]," where [Site] is the
site you were on when you selected the Control Panel from the dockbar. If you
want to administer the software catalog for a different site, you can select it
from the selection box.

![Figure 12.12: Populated Software Catalog from liferay.com](../../images/marketplace-populated-software-catalog.png)

The Software Catalog has several tabs. The first tab is labeled Products. The
default view of the portlet, when populated with software, displays what plugins
are available for install or download. This can be seen in the version on
Liferay's home page.

We will use an example site in order to better illustrate how to use the
Software Catalog portlet. Assume you, as the portal administrator, have created
a site called *Old Computers*. This site will be for users to collaborate on
setting up and using old computers with obsolete hardware and operating systems.
Users who participate in the site will eventually get upgraded to a more
privileged status and get their own blog page. To implement this, you have
created a My Summary portlet which displays the user's name, picture and
description from his or her user profile. Because this portlet is generic enough
that it could be useful to anyone using Liferay, you have decided to make it
available in your own software catalog.

The first step in adding a plugin to your software repository is to add a
license for your product. A license communicates to users the terms upon which
you are allowing them to download and use your software. Click the *Licenses*
tab and then click the *Add License* button that appears. You will then see a
form which allows you to enter the title of your license, a URL pointing to the
actual license document and check boxes denoting whether the license is open
source, active or recommended.

When you have finished filling out the form, click the *Save* button. Your
license will be saved. Once you have at least one license in the system, you can
begin adding software products to your software catalog. Click the *Products*
tab, then click the *Add Product* button.

Your next step will be to create the product record in the software catalog.
This will register the product in the software catalog and allow you to start
adding versions of your software for users to download and/or install directly
from their instances of Liferay. You will first need to put the `.war` file
containing your software on a web server that is accessible without
authentication to the users who will be installing your software. In the example
above, the *Old Computers* site is on the Internet so you would place the file
on a web server that is accessible to anyone on the Internet. If you are
creating a software catalog for an internal Intranet, you would place the file
on a web server that is available to anyone inside of your organization's
firewall.

To create the product record in the Software Catalog portlet, click the
*Products* tab, then click the *Add Product* button. Fill out the form with
information about your product.

![Figure 12.13: Adding a Product to the Software Catalog](../../images/marketplace-adding-product-to-software-catalog.png)

**Name:** The name of your software product.

**Type:** Select whether this is a portlet, theme, layout template, hook or web
plugin.

**Licenses:** Select the license(s) under which you are releasing this software.

**Author:** Enter the name of the author of the software.

**Page URL:** If the software has a home page, enter its URL here.

**Tags:** Enter any tags you would like added to this software.

**Short Description:** Enter a short description. This will be displayed in the
summary table of your software catalog.

**Long Description:** Enter a longer description. This will be displayed on the
details page for this software product.

**Permissions:** Click the *Configure* link to set permissions for this software
product.

**Group ID:** Enter a group ID. A group ID is a name space which usually
identifies the company or organization that made the software. For our example,
we will use *old-computers*.

**Artifact ID:** Enter an Artifact ID. The artifact ID is a unique name within
the name space for your product. For our example, we will use
*my-summary-portlet*.

**Screenshot:** Click the *Add Screenshot* button to add a screen shot of your
product for users to view.

When you have finished filling out the form, click the *Save* button. You will
be brought back to the product summary page and you will see your product has
been added to the repository.

Notice in the version column, *N/A* is being displayed. This is because there
are not yet any released *versions* of your product. To make your product
downloadable, you need to create a version of your product and point it to the
file you uploaded to your HTTP server earlier.

Before you do that, however, you need to add a *Framework Version* to your
software catalog. A Framework version denotes what version of Liferay your
plugin is designed for and works on. You cannot add a version of your product
without linking it to a version of the framework for which it is designed.

Why is this so important? Because as Liferay gains more and more features, you
may wish to take advantage of those features in future versions of your product,
while still keeping older versions of your product available for those who are
using older versions of Liferay. This is perfectly illustrated in the example My
Summary portlet we are using. Liferay had a My Summary portlet of its own, which
does exactly what we have described here. This portlet was added to the suite of
portlets which Liferay provides in the Social Networking plugin. This plugin
makes use of the many social networking features which have been added to
Liferay. So rather than just displaying a summary of your information, the
Social Networking portlet adds features such as status updates, a "wall" for
each user in his or her profile that other users can *write* on, the ability to
become *friends* with other users—thereby granting them access to their
profiles—and more.

None of this would work in older versions of Liferay, because the core engine
that enables developers to create features like this is not there. So in this
case, you would want to keep the older My Summary portlet available for users
who have not yet upgraded and make the newer social portlets available to those
using latest version of Liferay. This is what *Framework Versions* does for you.
If you connect to Liferay's software repositories with an old version of Liferay
Portal, you will see the My Summary portlet. If you connect to Liferay's
software repositories with new version of Liferay, you will see the social
portlets.

So click the *Framework Versions* tab and then click the *Add Framework Version*
button.

Give the framework a name, a URL and leave the *Active* check box checked. For
our example, we have entered 6.0.3 for the name, because our portlet should work
on that version and higher, and [http://www.liferay.com](http://www.liferay.com)
for the URL. Click *Save*.

![Figure 12.14: Adding a Product Version to the Software Catalog](../../images/marketplace-adding-product-version-software-catalog.png)

Now go back to the *Products* tab and click on your product. You will notice a
message is displayed stating the product does not have any released versions.
Click the *Add Product Version* button.

**Version Name:** Enter the version of your product.

**Change Log:** Enter some comments regarding what changed between this version
and any previous versions.

**Supported Framework Versions:** Select the framework version for which your
software product is intended. Enter a `+` at the end of the version number if
you want to specify a version plus any future versions.

**Download Page URL:** If your product has a descriptive web page, enter its URL
here.

**Direct Download URL (Recommended):** Enter a direct download link to your
software product here. The Plugin Installer portlet will follow this link in
order to download your software product.

**Include Artifact in Repository:** To enable others to use the Plugin Installer
portlet to connect to your repository and download your plugin, select *yes*
here.

When you are finished filling out the form, click the *Save* button. Your
product version will be saved and your product will now be available in the
software repository.

#### Generating The Software Catalog [](id=lp-6-1-ugen15-generating-the-software-catalog-0)

The Software Catalog works by generating an XML document which the Plugin
Installer reads. Using the data from this XML document, the Plugin Installer
knows where it can download the plugins from, what version of Liferay the
plugins are designed for and all other data about the plugins that have been
entered into the Software Catalog portlet.

In order to get your Software Catalog to generate this XML data, you will need
to access a particular URL. If you have created a friendly URL for your site
(for example, the default site, which is called *guest*, has a friendly URL of
`/guest` already configured for it), you can use the friendly URL. If not, you
will first need to know the Group ID of the site in which your Software Catalog
portlet resides. You can do this by accessing the Manage Pages interface and
looking at the URLs for any of the pages. The URL will look something like this:
`http://localhost:8080/web/10148/1`.

Obviously, it is much easier if you are using Friendly URLs, which we highly
recommend.

Next, go to your browser and go to the following URL:

[http://<server name\>:<port number\>/software\_catalog?<Friendly URL name or
Group ID\>](http://<server name\>:<port number\>/software\_catalog?<Friendly URL
name or Group ID\>)

For example, if you are on the same machine as your Liferay instance, and that
instance is running on port 8080, and your group ID from the database is 10148,
you would use the following URL:

[http://localhost:8080/software\_catalog?10148](http://localhost:8080/software\_catalog?10148)

If you have also created a friendly URL called *old-computers* for this site,
you would use the following URL:

[http://localhost:8080/software\_catalog?old-computers](http://localhost:8080/software\_catalog?old-computers)

If you have configured everything properly, an XML document should be returned:

<?xml version="1.0" encoding="UTF-8"?>
	
<plugin-repository>
	
	   <settings/>
	
   	   <plugin-package>
	
		   <name>My Summary</name>
	
   		   <module-id>old-computers/my-summary-portlet/1.0/war</module-id>
	
		   <modified-date>Thu, 23 Apr 2009 20:40:16 +0000</modified-date>
	
		<types>
	
			<type>portlet</type>
	
		</types>
	
		<tags>
	
			<tag>social</tag>
	
			<tag>profile</tag>
	
		</tags>
	
		<short-description>My Summary</short-description>
	
		<long-description>My Summary</long-description>
	
		<change-log>Initial Version</change-log>
	
		<download-url>
            http://www.liferay.com/portlets/my-summary-portlet-6.0.4.war

	    </download-url>
	
	    <author>Rich Sezov</author>
	
	    <screenshots/>
	
	    <licenses>
	
		    <license osi-approved="true">MIT License</license>
	
	    </licenses>
	
	    <liferay-versions/>
	
	    </plugin-package>
	
    </plugin-repository>



You can now give the URL to your software repository out on your web site and
other administrators of Liferay can enter it into the Plugins Installation
module of their Liferay Control Panels to connect to your repository.

If you want to serve your repository off of a static web server, you can save
this document to a file called `liferay-plugin-package.xml` and put this file on
your HTTP server. You can then give out the URL to the directory which holds
this file on your web site and anyone with an instance of Liferay will be able
to point their Plugin Installer portlets to it.

#### Benefits of the Software Catalog [](id=lp-6-1-ugen15-benefits-of-the-software-catalog-0)

As you can see, the Software Catalog makes it easy for you to create a
repository of your software. Users of Liferay can configure their Plugin
Installers to attach to your repository and the proper versions of your software
will be automatically made available to them by a single click. This is by far
the easiest way for you to keep track of your software and for your users to
obtain your software.

Another benefit of the Software Catalog is that you can make available to your
users a standard interface for manually downloading your software. For those who
prefer to manually download plugins, your Software Catalog gives them an
interface to go in, find your software either by browsing or by searching,
preview screen shots and download your software—and you don't have to build
any of those pages yourself. Simply configure your software in the portlet and
all of that is done for you.

How can you do this? The Software Catalog is also available as a portlet. You
can add it to any page on your web site through the *Add Application* menu. You
can find the portlet in the *Tools* category.

### Manually Creating A Software Catalog [](id=lp-6-1-ugen15-manually-creating-a-software-catalog-0)

If you do not wish to use the Control Panel to create your software catalog, you
can create it manually by manually typing out the XML file that the Software
Catalog section of the Control Panel would normally generate. Note that if you
do this, you will not be able to use the Software Catalog portlet as a graphical
user interface to your software that end users can use to download your software
manually: you will have to build this yourself. Keep in mind many instances of
Liferay Portal sit behind a firewall without access to the Internet. Because of
this, if you are making your software available to Internet users, some of them
will have to download it manually anyway, because their installations are
firewalled. In this case, the Software Catalog portlet is the easiest way to
provide a user interface for downloading your software.

If you still wish to use a text editor to create your software catalog, you can.
To manually create a software catalog, obtain the DTD for the XML file from
Liferay's source code. You will find this DTD in the *definitions* folder in the
Liferay source. It is a file called `liferay-plugin-package_6_0_0.dtd`. Use this
DTD with a validating XML editor (a good, free choice is jEdit with all the XML
plugins) to create your software catalog manually.

### Connecting to a Software Catalog [](id=lp-6-1-ugen15-connecting-to-a-software-catalog-0)

If there is a software catalog of plugins you would like to point your instance
of Liferay to, all you need is the URL to the catalog. Once you have the URL, go
to the Plugin Installer in your Control Panel and click the *Configuration* tab.
You will see there are two fields in which you can enter URLs to plugin
repositories: *Trusted Plugin Repositories* and *Untrusted Plugin Repositories*.
Currently, the only difference between the two is to provide a visual cue for
administrators as to which repositories are trusted and untrusted.

Enter the URL to the repository to which you wish to connect in one of the
fields and click *Save*. The portlet will connect to the repository and items
from this repository will be shown in the list.

If all this talk of catalogs has put you in the mood to do some shopping, then
it's probably a good time to get acquainted with Liferay's Shopping application.
Let's go down that aisle next.

## Shopping [](id=lp-6-1-ugen12-shopping-0)

Would your organization like to make some money selling promotional items? Are
you an artist looking to share your work with the world? Perhaps your company
produces a publication that customers want to purchase? If you have something of
value the visitors of your site want or need, then Lifeary's Shopping
application can help you get these items to your customers with a secure
transaction.

The Shopping portlet uses PayPal and allows you to choose the credit cards
your store accepts. You can organize your inventory with categories and
subcategories. A search function helps users find items quickly. Users
place items in a shopping cart, allowing them to purchase multiple items at
once. There is also an email notification system to alert customers when their
transactions are processed.

Before we start printing money, let's first create an online store.

### Setting up shop [](id=lp-6-1-ugen12-setting-up-shop-0)

To begin setting up a store, place the Shopping application on a page in your
site. Like the Message Boards portlet, the Shopping portlet takes up a lot of
space. It's best, therefore, to dedicate an entire page to the application. The
Shopping portlet is available from the *Add* &rarr; *More* menu in the Dockbar
under Shopping.

![Figure 12.15: Start setting up the store by entering items and categories in
the shopping portlet.](../../images/shopping-add-portlet.png)

The shopping portlet has four tabs across the top:

**Categories:** shows the categories of items in your store. For example, if
you're selling music, you might have categories for various genres, such as pop,
rock, metal, hip hop, and the like. The portlet defaults to this view.

**Cart:** shows the items the user has selected to purchase from your store. It
displays the order subtotal, the shipping cost, and a field for entering a
coupon code. There are buttons for updating the cart, emptying the cart, and
checking out.

**Orders:** displays a list of all previous orders placed, containing options to
search for orders by the order number, status, first name, last name and/or
email address.

**Coupons:** lets you define coupon codes to offer discounts to your customers.
You can enter the coupon code, discount type, and whether it is active or not.
Search looks for a particular coupon offer while Add Coupon opens a new form to
key in the coupon data. Delete removes a coupon.

Below the tabs are breadcrumbs for navigating between the categories and
subcategories you create. In fact, this would be a good time to start creating
some categories.

#### Creating Categories [](id=lp-6-1-ugen12-creating-categories-0)

It's not difficult to create categories. Simply click the *Add Category* button
to display the Category form. In this form enter the *Name*, *Description*, and
set the *Permissions* for the category. That's all there is to it.

![Figure 12.16: In this figure there are three subcategories for the
`Aromatherapy` category. The first subcategory has three items, the second has
two, and the third is empty.](../../images/shopping-categories.png)

When you click *Save*, your new category is listed in the portlet, along with
the number of subcategories and the number of items that are in the category.
You can edit the category, set permissions for it or delete it using the
*Actions* button.

![Figure 12.17: Breadcrumbs are an important navigational tool in the shopping 
portlet.](../../images/shopping-category-breadcrumbs.png)

Each category can have unlimited subcategories and you can add subcategories to
any category. Notice as you add categories and subcategories, navigational
breadcrumbs appear in the portlet. Use these to move through the store
inventory.

#### Creating Items [](id=lp-6-1-ugen12-creating-items-0)

When you select a category, you'll see its items appear. You create items the
same way you create categories. Use the *Add Item* button to open the new item
form. Enter data for the SKU number, name, description, and item properties. You
can select checkboxes to specify whether the item requires shipping and
whether it is a featured item. Enter the stock quantity to record how many
items are available and set the appropriate permissions.

The Fields area is where you add additional fields to set specific
characteristics for the item. These can include things like sizes and colors.
The additional fields appear in the item form as pull-down menus, as in the
figure below.

![Figure 12.18: The additional fields you create for an item appear in the item
description form as menu options.](../../images/shopping-item-options.png)

The Prices area is for all data pertaining to the item's cost, minimum and
maximum quantities, quantity discounts, taxes, and shipping costs.

The Images area lets you add photos to the item form. You can add a link to the
photo or upload the file locally. Choose from three sizes of images. You must
select the appropriate check box for the image you want to display. When you're
finished creating a new item, click *Save*.

![Figure 12.19: The image in this figure is the medium sized option.](../../images/shopping-item-image2.png)

As products are added, they are listed in the Items section of the portlet.
If the item you just created needs to go into one of your new categories or
subcategories, you can assign it to the category by editing the item. Choose
the *Select* button (next to the *Remove* button), and this displays a dialog box
listing all the shop categories.

![Figure 12.20: To put an item in a category, edit the item and choose *Select*
to see the available categories.](../../images/shopping-select-categories.png)

Choose the desired category from the list to relocate the item to its new
location. Notice how the breadcrumbs reflect this change in the item form.

![Figure 12.21: When an item moves into a category, the breadcrumb navigation
updates accordingly.](../../images/shopping-item-breadcrumb-change.png)

You can make changes to any item through *Actions* &rarr; *Edit*. Finding an
item is easy, using the *Search* function.

That's how you create an item for the store. Now let's examine some of the
shopping portlet's configuration options.

### Configuration [](id=lp-6-1-ugen12-configuration-0)

By selecting the *wrench* icon in the top right of the portlet, you can manage
the configuration options of the shopping application. In the Setup view, there
are tabs for Payment Settings, Shipping Calculation, Insurance Calculation, and
Emails.

#### Payment Settings [](id=lp-6-1-ugen12-payment-settings-0)

The payment settings section is where you configure all the functions related to
transactions for your store.

**PayPal Email Address:** is the address of your store's PayPal account which is
used for payment processing.

Note that PayPal can be disabled by entering a blank PayPal address in the
field. Credit cards can likewise be disabled. Payments to the store are not
required when these settings are disabled. The credit card function does not
process payments; it instead stores the card information with the order so you
can process the transaction separately. 

**Credit Cards:** sets the type of credit cards your store accepts.

The Current column holds the cards your store takes. The Available column holds
cards not accepted by your shop. These can be moved easily from one column to
another by selecting a card and clicking one of the arrow buttons. The arrows
below the Current window allow you to choose the order credit cards are
displayed on the form.

**Currency:** sets the appropriate currency your shop accepts.

**Tax State:** sets the applicable state where your business is responsible for
paying taxes.

**Tax Rate:** sets the percentage of taxes your store is responsible for paying.
This rate is added as a sales tax charge to orders.

**Minimum Order:** sets the minimum amount required for a sale.

#### Shipping and Insurance Calculation [](id=lp-6-1-ugen12-shipping-and-insurance-calculation-0)

Both the Shipping and Insurance forms have identical options.

**Formula:** sets the equation for determining shipping and insurance costs.
They're calculated on either a flat rate based on the total purchase amount or
on a percentage of the total amount spent.

**Values:** sets the shipping and insurance fees based on a range of figures
that the total order amount falls under.

#### Emails [](id=lp-6-1-ugen12-emails-0)

This form sets the addresses for customer email notifications. Use the list of
term definitions below to customize the correspondence with your customers.

![Figure 12.22: Shopping emails can be configured in a myriad of ways to suit
your needs.](../../images/shopping-confirmation-email-form.png)

**Emails From:** sets the email address from which order and shipping
notifications are sent.  

**Confirmation Email:** Use this form to customize the email message customers
receive when an order is received. 

**Shipping Email:** Use this form to customize the email message customers
receive when an order has been shipped.

So far we have added the shopping portlet to your site, created categories and
items for your store, set up payment options, and configured customer
communication options. These are the basics required to get your store up and
running. Now let's review the buying process.

### Using the shopping cart [](id=lp-6-1-ugen12-using-the-shopping-cart-0)

Logged in users are given a shopping cart to store the items they wish to buy.
Customers can manage items and their quantities directly from the cart, allowing
them to purchase a single product or multiple products at once. Customers can
also key in coupon codes to take advantage of any discounts your store has to
offer. Products can be placed in the cart from any category or subcategory. The
cart's appearance can be customized to reflect the overall design of your store.

When buyers select an item, they see the item's description displaying all of
its relevant information. The figure below is typical of what an item's
description might look like.

![Figure 12.23: You can include images of each item in your store. (Medium sized
images display on the item's description form).](../../images/shopping-item.png)

Below the product description is the Availability field indicating whether the
item is in stock. There are also two buttons for managing the shopping
experience:

**Add to Shopping Cart:** places the item in your cart for checkout.

**Next:** lets you to scroll through all the items in the category, giving you
the option to add to the cart as you go.

After adding an item to the cart, click *<< Back* to return to the product
description and continue shopping by navigating the category breadcrumbs at the
top of the form. You can also continue shopping by scrolling through a 
category, item by item, using the *Previous* and *Next* buttons at the bottom of
the product description.

![Figure 12.24: This shopping cart has two items in it so far.](../../images/shopping-cart-order.png)

Each time you add an item to the cart, a running tally of the cart's contents is
kept. Quantities for each item are controlled using drop-down menus.  The order
subtotal and shipping costs appear above a field where coupon codes can be 
entered. When you have finished adding products to the cart, you have three
options:

**Update Cart:** lets you change the quantity of an item being purchased.
If a minimum number of items has been set in the item description, the field
under the Quantity column shows that number by default. You can adjust
the exact number of items you want with the drop-down menus in the cart.

**Empty Cart:** lets you clear the contents of the cart to either start
shopping again or to stop shopping.

**Checkout:** sends you to a new form to verify the billing address, shipping
address, and the credit card information. You can also add comments about the
order if necessary.

![Figure 12.25: All the information pertaining an order can be seen in the order 
summary view.](../../images/shopping-order-summary.png)

When all the data has been entered correctly, click *Continue* to see the order
summary. After reviewing the summary, click *Finished* and you are given
confirmation the order has been placed, along with the order number. Use this
number to search for the order history and keep track of its status.

#### Customizing the shopping cart with a hook [](id=lp-6-1-ugen12-customizing-the-shopping-cart-with-a-hook-0)

If you think the shopping cart looks a little basic for your purposes, you can
customize it by using a hook. To learn more about changing the appearance of the
shopping cart, consult the [*Liferay Developer's
Guide*](http://www.liferay.com/documentation/liferay-portal/6.1/development) or
see section 8.3 in [*Liferay in Action*](http://manning.com/sezov). 

Now your online store is set up, you have inventory, you have a payment system,
and you have sales rolling in. All is good. Some day there will be customers
with questions about their orders. Let's go over the orders next.

### Managing Orders [](id=lp-6-1-ugen12-managing-orders-0)

Under the Orders tab there are fields for finding specific orders. Search 
for orders using the order number, order status, first or last name on the order
or by the email address associated with the account. For more information on 
searching in Liferay Portal, see the Faceted Search section in chapter 5. 

![Figure 12.26: Search for orders in the Orders view or select one from the list.](../../images/shopping-orders.png)

Below the search fields is the orders list. Orders can be deleted or edited
using the *Actions* button. When you select an order from the Orders tab, or if 
you edit an order, you see a summary of the order details along with some 
options across the bottom.

![Figure 12.27: Review order specifics in the Edit 
view.](../../images/shopping-order-detail.png)

**Invoice:** creates a printer-friendly copy of the order that can be sent to a
customer.

**Resend Confirmation Email:** lets you notify the customer that the order
has been received and is being processed.

**Send Shipping Email:** notifies the customer that the order is *en route*. You
can also include a tracking number with this email to allow the customer to
follow the delivery process.

**Delete:** removes the order from the system.

**Cancel:** closes the Edit view and returns the user to the main orders view. 

You can also add comments about the order and subscribe to the comments to get
any updates on the order.

### Managing Coupons [](id=lp-6-1-ugen12-managing-coupons-0)

The Coupons view of the Shopping application lets you provide coupon codes for
special sale events or other discounts. You can determine the type of discount
to apply and whether it is currently active. You can search for coupons and
create new coupons from this form.

![Figure 12.28: Create a coupon code automatically when you select the Autogenerate Code box.](../../images/shopping-coupon.png)

To add a coupon, enter the coupon code in the Code field. If no code is
specified, you can create one automatically by selecting the *Autogenerate Code*
checkbox. After entering the coupon's name and description, you can set the
coupon's start and expiration dates. Additional options let you activate the
coupon and set it to never expire.

![Figure 12.29: Customize your coupon parameters under Discounts and Limits.](../../images/shopping-coupon-discount-limits.png)

Under the Discount section, you can set the minimum order amount required for
the discount, the discount amount, and the discount type. Types can be based on
a percentage, a fixed amount, free shipping, or a tax free sale. The Limits
section lets you set coupon restrictions based on a list of categories and/or
SKU numbers.

#### Integrating the Amazon Rankings portlet [](id=lp-6-1-ugen12-integrating-the-amazon-rankings-portlet-0)

If your store sells books, you can use Liferay's Amazon Rankings application to
display them alongside the main shopping portlet. Both of these are found in the
Shopping category under *Add* &rarr; *More* in the Control Panel. The Amazon
Rankings application lets you highlight the books in your store's inventory
outside of the typical category structure. Books are arranged in ascending order
according to Amazon's Best Sellers Rank. Book cover images displayed in the
portlet come from the images in the product's description.

##### Setting up your Amazon Web Services account [](id=lp-6-1-ugen12-setting-up-your-amazon-web-services-account-0)

To use Amazon rankings, you must first setup an Amazon Associates Program
account. This gives you the *associate ID tag* you need to enter in your
`portal-ext.properties` file. Then you need to join the Amazon Product Advertising
API group. This yields the *access key id* and the *secret access key* that
also must go into your `portal-ext.properties` file.

Amazon License Keys are available here:

[https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/](https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/)

Add the following lines to your `portal-ext.properties` file and populate the
values for the associate ID tag, access key id, and secret access key. Ensure
there are no spaces between the `=` sign and the property values.

	amazon.access.key.id=
	amazon.associate.tag=
	amazon.secret.access.key=

Note that these keys are provided by Amazon for personal use only. Please
consult Amazon at [http://www.amazon.com](http://www.amazon.com) for more
information.

If your Amazon Web Services key is set improperly, you can't add books to your
Shopping portlet.

##### Setting up the Amazon Rankings portlet [](id=lp-6-1-ugen12-setting-up-the-amazon-rankings-portlet-0)

After setting up your Amazon Web Services account, choose the books to display
in your store. Select *Configuration* from the Amazon Rankings portlet in the
upper right corner. Go to the *Setup* tab and enter the International Standard
Book Numbers (ISBNs) in the textbox, separated by spaces. The portlet accepts
10-digit ISBNs rejecting ISBNs that letters.

![Figure 12.30: Separate ISBNs with single spaces.](../../images/shopping-amazon-rankings-config.png)

When you are finished setting up the rankings, books appear in the portlet
similar to the example below. Clicking on the book's cover image opens the
book's Amazon page.

![Figure 12.31: Using the Amazon Rankings application can be a nice addition to your store.](../../images/shopping-and-amazon-rankings.png)

Now that you have a good grasp on Liferay's Shopping and Amazon Rankings
applications, let's see what the Knowledge Base application has to offer.

## Knowledge Base [](id=knowledge-base)

Liferay's Knowledge Base portlet provides a means for creating and organizing
articles within a site. The knowledge base is perfect for creating and
organizing information more formally than in a wiki. For example, it can be used
to organize and display professional product documentation. It's easy to set up
the knowledge base with a workflow that requires articles to be approved before
they are published. Additionally, it allows administrators to create article
templates. Templates can be used to insure certain kinds of articles possess a
common structure and include certain kinds of information. Knowledge base
articles can be categorized to make them easy to find. They can also be
organized hierarchically to form complete books or guides. The Knowledge Base
portlet is available as an app from Liferay Marketplace. Please see chapter 2
for installation instructions.

### Knowledge Base Display Portlet [](id=lp-6-1-ugen04-knowledge-base-display-portlet-0)

The Knowledge Base app actually consists of four portlets that can be placed on
site pages as well as one that adds a page to the Control Panel. The four
portlets that can be placed on a page are Knowledge Base (Display), Knowledge
Base Search, Knowledge Base Article and Knowledge Base Section. When placed on a
page, the Knowledge Base display portlet presents many of the same options to an
administrator that are available from the Knowledge Base page of the control
panel.

![Figure 12.32: Knowledge Base Display Portlet](../../images/liferay-collaboration-kb-display-portlet.png)

You can use the four links at the top of the Knowledge Base display portlet to
control what it displays.

*Knowledge Base Home:* shows you a list of all top level articles.

*Recent Articles:* shows you a list of articles in order from most recent
activity to least recent activity.

*Administrator:* shows you a list of all articles, regardless of which ones are
parents or children of the others.

*My Subscriptions:* shows you a list of articles you are subscribed to.

The *Add Article* button is available from the Knowledge Base Home or
Administrator view of the Knowledge Base display portlet or from the Articles
tab of the Knowledge Base page of the Control Panel. Use this button to create
an article for the knowledge base. When creating an article, you can use the
same WYSIWYG editor you used to create wiki pages. Articles, however, are not
the same as wiki pages: they must be created in HTML, not MediaWiki or Creole.
Click the *Source* button in the editor to view the HTML source of what you've
written or write some HTML yourself.

![Figure 12.33: New Knowledge Base
Article](../../images/liferay-collaboration-kb-new-article.png)

In addition to entering a title and creating content for your article, you can
use the editor to add attachments, add tags and set permissions. By default,
view permission is granted to the guest role, meaning anyone can view your
article. After you're done using the editor, you can save it as draft and
continue working on it later or you can submit it for publication. Your article
may need to be approved before being published, depending on the workflow
defined for your portal.

You can find the *Permissions* button next to the Add Article button in the
Knowledge Base display portlet or on the Knowledge Base page of the control
panel. Click this button to define permissions that apply to the Knowledge Base
display portlet generally, not to particular articles. Here, you can define
which roles can add articles and templates, which are granted knowledge base
administrator privileges, which can change permissions on articles, which can
subscribe to articles and which can view templates.

![Figure 12.34: Knowledge Base
Permissions](../../images/liferay-collaboration-kb-permissions.png)

Users may need to be granted access to the knowledge base page of the control
panel in order to exercise some of the above permissions. For example, suppose
the user role has been granted the Add Article and the View Templates
permissions. A user will be able to add articles from the knowledge base display
portlet but will need access to the knowledge base page of the Control Panel in
order to view templates. Note that the Knowledge Base (Display) permissions are
distinct from the Knowledge Base (Admin) portlet. The display permissions define
what a user can do with the Knowledge Base display portlet on a page while the
admin permissions define what a user can do on the Knowledge Base page of the
Control Panel.

### Knowledge Base Page of the Control Panel [](id=lp-6-1-ugen04-knowledge-base-page-of-the-control-panel-0)

The Knowledge Base page of the Control Panel has two tabs: one for articles and
one for templates. The articles tab shows all the articles in the knowledge base
and lets you perform actions on them. The templates tab shows all the templates
defined in the knowledge base and lets you perform actions on them.

![Figure 12.35: Knowledge Base Control Panel Page](../../images/liferay-collaboration-kb-control-panel.png)

Administrators can perform the following actions on an article:

*View:* displays an article. From here, you can add a child article, edit the
article, change its permissions, move it or delete it.

*Edit:* allows you to change the title and content of an article as well as add
attachments, select topics and add tags.

*Permissions:* lets you configure the permissions on a specific article.

*Subscribe:* lets you to choose to be notified of any updates to a particular
article.

*Move:* lets you change an article's position in the hierarchy by choosing a new
parent article for it.

*Delete:* lets you remove an article from the knowledge base.

These actions are similar to the ones that can be performed from the
Administrator view of the Knowledge Base display portlet. However, the Knowledge
Base display portlet is intended to be placed on a page for the end user so an
additional action is available: *RSS* is a link to an RSS feed of an article.
Also, the *View* action is only available from the Control Panel since the
Knowledge Base Article portlet can be used to display an article on a page.

The templates tab of the Knowledge Base page of the Control Panel allows
administrators to create templates to facilitate the creation of articles. A
template basically functions like a starting point for the creation of certain
types of articles. Click the *Add Template* button on the Templates tab of the
Knowledge Base page of the Control Panel to create a new template.

![Figure 12.36: Adding a New Template From the Control Panel](../../images/liferay-collaboration-kb-new-template.png)

Navigate back to the templates tab of the Knowledge Base page of the control
panel. You can perform the following actions on a template:

*View:* displays a template. From here, you can use the template to create an
article, edit the template, modify the permissions on the template or delete it.

*Edit:* allows you to change the title and content of a template.

*Permissions:* allows you to configure the permissions on a template. You can
choose roles to have permission to update, view, delete or change the
permissions on templates.

*Delete:* lets you remove a template from the knowledge base.

![Figure 12.37: Knowledge Base Section Portlets](../../images/liferay-collaboration-kb-section-portlet.png)

To use a template to create a new article, you have to view the template and
then click *Use this Template*. This brings you to the New Article editor with
the contents of the template copied for you.

### Knowledge Base Article Portlet [](id=lp-6-1-ugen04-knowledge-base-article-portlet-0)

The Knowledge Base Article portlet can be placed on a page to display an entire
article. When you first place this portlet on a page it displays the message
*Please configure this portlet to make it visible to all users*. This message is
a link to the configuration dialog box for the portlet. Click *Select Article*
to choose an article to display. Pick an article and then click *Save*. When
your page refreshes it will display the article in the portlet.

![Figure 12.38: Knowledge Base Article Portlet](../../images/liferay-collaboration-kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the
article it displays. There are also links at the top of the portlet users can
use to subscribe to an RSS feed of the knowledge base, subscribe to the article,
view the history of the article or print the article.

### Knowledge Base Section Portlet [](id=lp-6-1-ugen04-knowledge-base-section-portlet-0)

The Knowledge Base Section portlet allows administrators to selectively show
articles associated with a specific section. For example, a news site might have
a *World* section, a *Politics* section, a *Business* section and an
*Entertainment* section. In order to use sections, you need to set the
`admin.kb.article.sections` property in your knowledge base portlet's
`portlet.properties` file and redeploy the portlet. You can find the
`portlet.properties` file in the knowledge base portlet's source directory.
Updating the one in your server's directory won't work. Use comma delimited
section names to set the property, like
`admin.kb.article.sections=World,Politics,Business,Entertainment`, for example.

Once you have defined some sections in your knowledge base's
`portlet.properties` file, your users will see a multi-select box in the Add
Article and Edit Article screens that allows them to select which section an
article belongs to. You can add any number of Knowledge Base section portlets to
a page and you can configure each portlet to display articles from any number of
sections.

![Figure 12.39: Knowledge Base Section Portlets](../../images/liferay-collaboration-kb-section-portlet.png)

The Knowledge Base section portlet has some additional configurations that allow
an administrator to select a display style (title or abstract), an article
window state (maximized or normal), how to order the articles, how many articles
to display per page and whether or not to show pagination.

### Knowledge Base Navigation [](id=lp-6-1-ugen04-knowledge-base-navigation-0)

Wikis often have deeply nested articles that can be hard to find by browsing.
Liferay's knowledge base's ability to selectively display articles makes it
easier to browse than a Wiki. The knowledge base also features some other aids
to navigation. The Knowledge Base Search portlet allows you to search for
articles in the knowledge base. This portlet presents the search results to you
in order from most relevant to least relevant.

![Figure 12.40: Knowledge Base Search Portlet](../../images/liferay-collaboration-kb-search-portlet.png)

You can also use the Categories Navigation portlet in conjunction with the
Knowledge Base display portlet. When both of these portlets are placed on a page
you can select a topic in the Categories Navigation portlet and the Knowledge
Base display portlet will show all of the articles that match the topic. You can
create topics from the Categories page of the Control Panel.

![Figure 12.41: Knowledge Base Category Navigation Portlet](../../images/liferay-collaboration-kb-catnavandkbdisplay-portlets.png)

You can select topics for articles when you are creating or editing them.

## Summary [](id=lp-6-1-ugen12-summary-0)

In this chapter, we examined two Liferay utility applications: the Software
Catalog and the Knowledge Base. The Software Catalog allows you to define a set
of software items to make available to visitors to your portal. Remember that
the Software Catalog will be replaced by Liferay Marketplace and will soon be
deprecated. The Knowledge Base application is an EE-only application that
allows you to create articles and organize them into full books or guides that
be published on your portal. Liferay's Weather portlet displays basic
weather-related information for multiple configurable locations. Next, let's
take a tour of the Liferay Marketplace and learn how to manage Liferay plugins.
