# Liferay Utility Applications [](id=liferay-utility-applications-liferay-portal-6-2-user-guide-13-en)

In this chapter we'll look at some Liferay utility applications that might be
useful for you. The Software Catalog has been replaced by Liferay Marketplace
but can still be installed as a plugin. Please see chapter 13 for information
about Liferay Marketplace and managing Liferay plugins. The Reports,
JasperReports, and Knowledge Base applications are only available to EE
customers. In this chapter we'll discuss following applications:

- Bookmarks
- Software Catalog
- Shopping
- Reports and JasperReports
- Knowledge Base
- Akismet
- Weather
- OAuth

Liferay's Bookmarks application is a simple way for users to keep track of URLs
in the portal that can also be used by an administrator to publish relevant
links to groups of users. The Software Catalog allows you to define a set of
software items to display to visitors. The Knowledge Base application allows you
to create articles and organize them into full books or guides that be published
on your portal.

## Capturing Web Sites with the Bookmarks Portlet [](id=capturing-web-sites-with-the-bookmarks--liferay-portal-6-2-user-guide-13-en)

Many of us enjoy collecting things we value. They may be stamps, comic books,
sea shells, or fabulous shoes. The list goes on and on. But have you considered
URLs collectible? Having a thorough collection of links can be a great way to
add value to your portal's usability.

With Liferay's Bookmarks application, users collect and manage URLs in the
portal. They can add, edit, delete, export and import bookmarks. Users can use
links to access regularly visited web sites. Administrators can publish links
tailored to specific groups of users. Both internal pages as well as external
sites can be bookmarked.

### Adding and Using Bookmarks [](id=adding-and-using-bookmarks-liferay-portal-6-2-user-guide-13-en)

Navigate to your portal and add the Bookmarks application to your page by
selecting *Add* &rarr; *Applications*. The portlet looks like this by default:

![Figure 13.1: Initially, no bookmarks are listed in this form until they're created.](../../images/bookmarks-add-portlet.png)

Across the top of the portlet are links labeled Home, Recent, and Mine. There is
also a Search field and button.

**Home:** returns you to the top level of the portlet.

**Recent:** displays a list of the latest bookmarks that have been added.

**Mine:** displays a list of the bookmarks you added to the portlet.

**Search:** lets you search for bookmarks by name, category, or tags.

When you select the *Permissions* button on the right, a list of Roles and their
associated permissions appears. The options are Add Entry, Add Folder,
Permissions, and View. When you are finished selecting the permissions, click
*Save*.

Clicking the *Add Folder* button in the Bookmarks application reveals the *New
Folder* form. Here you can choose the folder's name, a description of its
contents, and who can view it. Under More Options, you can set portlet
permissions for various Roles to the folder. Click *Save* when you are finished.

To create a bookmark, click the *Add Bookmark* button. This form is similar to
the New Folder form but has a few more options.

![Figure 13.2: When you use the Add Bookmark form, you must enter a valid URL in the required field.](../../images/bookmarks-add-new-form.png)

Click *Select* to choose the folder for the new bookmark. Click *Remove* to
delete a bookmark from the selected folder. As stated above, a removed link goes
into the list of general bookmarks that aren't associated with a folder. These
are listed in the bookmarks section, below the folders, in the portlet.

Below the Permissions there are additional options for Categorization and
Related Assets, just like in other Liferay applications. Please see chapter 6 on
the Asset Framework for further information about this.

Once you have added a new bookmark, it appears in the portlet. From here, you
can manage your bookmark using familiar Liferay editing features. Collecting and
organizing your links is a snap when you use Liferay's Bookmarks application.

### Organizing Bookmarks by Folder [](id=organizing-bookmarks-by-folder-liferay-portal-6-2-user-guide-13-en)

You can store all your important links in one place and you can manage this data
easily using folders. You can create, edit, and delete bookmark folders. You get
to decide how many bookmarks or folders are displayed on a page. Bookmark
folders can have any number of subfolders.

Here's an example of what one bookmarks portlet might look like. Bookmark
Folders are displayed above individual bookmarks. 

![Figure 13.3: Individual bookmarks, not associated with a folder, are listed separately.](../../images/bookmarks-folder-view-wide.png)

In this example, there are six bookmark folders, three of which have
subfolders. The columns showing the number of folders and the number of entries
show the subfolders and the entries contained within each top level folder. Note
that total number of bookmarks includes those in the subfolders.

Using the Actions button on the right, you can edit the folder, manage
folder permissions, delete the folder, add a subfolder, or add a bookmark to
the folder. 

As your collection of links grows, you may need to add more subfolders to keep
things in order. Should you decide a link needs to move from one folder to
another, you can manage this using the Edit option for that link. 

#### Moving a Link [](id=moving-a-link-liferay-portal-6-2-user-guide-13-en)

Just for fun, let's move a link from the main bookmarks folder into a subfolder
one level down. We'll move the Liferay link into the Trivia subfolder in the
Sports & Games folder. The Trivia subfolder is a child of the Sports & Games
folder. We'll move the link to the Sports & Games folder first before moving it
into the Trivia subfolder.

To achieve this, we select *Edit* from the Actions button for the link. In the
Edit view, find the section called *Folder*. When applicable, this section 
contains breadcrumb links to allow you to navigate freely among the other folder
levels. Currently, there are two buttons here labeled Select and Remove. Click
the *Select* button and a new window appears, like the one below. Clicking the
*Remove* button here doesn't affect the link. It is used solely for moving links
out of folders and subfolders back into the main Bookmarks view.

![Figure 13.4: Select the Choose button next to the desired folder.](../../images/bookmarks-choosing-subfolder.png)

Now choose the *Sports & Games* folder. When you do this, notice how the link's
Folder section changes to reflect the new location of the link. When you choose
*Save*, you return to the Bookmarks portlet. Notice that the Liferay link is no
longer under the Bookmarks section. Select *Sports & Games* to reveal its
contents. In the example below, you can see the Liferay link is now in the
bookmarks section of the Sports & Games folder.

![Figure 13.5: When you move a link to a folder, it remains in the bookmarks section until it's moved into a subfolder.](../../images/bookmarks-link-move.png)

Select *Edit* from the Actions button next to the Liferay link, then click
*Select*. Navigate to the *# of Folders* number next to the Sports & Games
folder and choose the *Trivia* subfolder. Again, notice the change in the
folder-level breadcrumbs. Click *Save* and the Sports & Games folder view
appears. change in the link's folder section, which now displays *Trivia*. Click
*Save* and the Sports & Games folder view appears. Select *Trivia* to see your
link in the subfolder's bookmarks list. Piece of cake, right?

To move a link out of a subfolder and into a higher-level folder, edit the link
in the subfolder and choose *Select*. In the resulting window, select the
appropriate folder from the breadcrumbs at the top. In this example, we selected
*Sports & Games* for consistency. This opens a new window, like the one below,
showing the other folder options.

![Figure 13.6: In this view, you can move the link into several different locations.](../../images/bookmarks-link-move3.png)

You can choose one of the other subfolders from the list, or you can choose
*Home* from the breadcrumbs at the top. When you verify that the desired folder
is the one currently displayed in the breadcrumbs, click *Choose This Folder*.
Then click *Save* and you're done. If you don't like any of the subfolders
listed, you can place the link into a new subfolder by using the *Add Subfolder*
button.

If you choose *Remove* instead of Select in the above example, you take the link
out of both subfolders and return it to the main bookmarks view.

Now that you have an understanding of how bookmark folders are used, let's
create our own plugin repository.

## Creating Your Own Plugin Repository [](id=creating-your-own-plugin-repository-liferay-portal-6-2-user-guide-13-en)

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
    (`http://www.liferay.com/dtd/liferay-plugin-repository_6_2_0.dtd`) and an
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

### Software Catalog [](id=software-catalog-liferay-portal-6-2-user-guide-13-en)

You will want to use the Software Catalog if you will have multiple users
submitting portlets into the repository and if you don't want to worry about
creating the `liferay-plugin-repository.xml` file yourself.

---

![Tip](../../images/01-tip.png) **Tip:** The Software Catalog plugin was
deprecated in Liferay 6.2 and is disabled by default. To activate it, navigate
to the Control Panel &rarr; *Plugins Configuration* and scroll through the list
of portlets until you find *Software Catalog*. Select *Software Catalog* and
click the *Active* checkbox. Then click *Save*.

---

You can add the Software Catalog portlet to your page by navigating to *Add*
&rarr; *Applications* and selecting it from the *Tools* category.

![Figure 13.7: The Software Catalog portlet can be added to any of your site pages.](../../images/marketplace-software-catalog.png)

Each site in your portal can have an instance of the Software Catalog. The
Software Catalog can be managed from your site's Site Administration page. This
means different sites can have different software repositories, so you can host
several software repositories on the same instance of Liferay if you wish, they
just have to be in different sites. Choose the site that will host the plugin
repository and go to the Site Administration page. You can change the site that
you're on by navigating to the Dockbar's *My Sites* drop-down menu and selecting
your desired site.

![Figure 13.8: This is an example of a populated Software Catalog from liferay.com](../../images/marketplace-populated-software-catalog.png)

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
created a My Summary portlet which displays the user's name, picture, and
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

![Figure 13.9: The *New Product* screen provides a recommended licenses setting for your product.](../../images/marketplace-adding-product-to-software-catalog.png)

**Name:** The name of your software product.

**Type:** Select whether this is a portlet, theme, layout template, hook or web
plugin.

**Licenses:** Select the license(s) under which you are releasing this software.
You must first add a license using the *License* tab before you can select it in
the *New Product* form.

**Author:** Enter the name of the author of the software.

**Page URL:** If the software has a home page, enter its URL here.

**Tags:** Enter any tags you would like added to this software.

**Short Description:** Enter a short description. This will be displayed in the
summary table of your software catalog.

**Long Description:** Enter a longer description. This will be displayed on the
details page for this software product.

**Permissions:** Click the *Configure* link to set permissions for this software
product.

**Site ID:** Enter a site ID. A site ID is a name space which 
identifies the site that released the software. For our example,
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
Social Networking portlet adds features such as status updates, a *wall* for
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
our example, we have entered 6.2.0 for the name, because our portlet should work
on that version and higher, and [http://www.liferay.com](http://www.liferay.com)
for the URL. Click *Save*.

Now go back to the *Products* tab and click on your product. You will notice a
message is displayed stating the product does not have any released versions.
Click the *Add Product Version* button.

![Figure 13.10: Versions usually increment by *.1* for every new release.](../../images/marketplace-adding-product-version-software-catalog.png)

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

**Test Direct Download URL:** Select *Yes* if you'd like Liferay to test the
download URL for its validity.

**Include Artifact in Repository:** To enable others to use the Plugin Installer
portlet to connect to your repository and download your plugin, select *Yes*
here.

When you are finished filling out the form, click the *Save* button. Your
product version will be saved and your product will now be available in the
software repository.

#### Generating the Software Catalog [](id=generating-the-software-catalog-liferay-portal-6-2-user-guide-13-en)

The Software Catalog works by generating an XML document which the Plugin
Installer reads. Using the data from this XML document, the Plugin Installer
knows where it can download the plugins from, what version of Liferay the
plugins are designed for, and all other data about the plugins that have been
entered into the Software Catalog portlet.

In order to get your Software Catalog to generate this XML data, you will need
to access a particular URL. If you have created a friendly URL for your site
(for example, the default site, which is called *guest*, has a friendly URL of
`/guest` already configured for it), you can use the friendly URL. If not, you
will first need to know the Group ID of the site in which your Software Catalog
portlet resides. Obviously, it is much easier if you are using Friendly URLs,
which we highly recommend.

Next, go to your browser and go to the following URL:

	http://<server name\>:<port number\>/software_catalog?<Friendly URL name or Group ID\>

For example, if you are on the same machine as your Liferay instance, and that
instance is running on port 8080, and your group ID from the database is 10148,
you would use the following URL:

[http://localhost:8080/software\_catalog?10148](http://localhost:8080/software\_catalog?10148)

If you have also created a friendly URL called *old-computers* for this site,
you would use the following URL:

[http://localhost:8080/software\_catalog?old-computers](http://localhost:8080/software\_catalog?old-computers)

If you have configured everything properly, an XML document should be returned:

	<?xml version="1.0" encoding="UTF-8"?>
	
	<plugin-repository\>
		<settings/>
		<plugin-package>
			<name>My Summary</name>
			<module-id>old-computers/my-summary-portlet/1.0/war</module-id>
			<modified-date>Thu, 20 Sep 2013 18:28:14 +0000</modified-date>
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
				http://www.liferay.com/portlets/my-summary-portlet-6.2.0.war
			</download-url>
			<author>Cody Hoag</author>
			<screenshots/>
			<licenses>
				<license osi-approved="true">LGPL</license>
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

#### Benefits of the Software Catalog [](id=benefits-of-the-software-catalog-liferay-portal-6-2-user-guide-13-en)

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
can add it to any page on your web site through the *Add* &rarr; *Applications*
menu. You can find the portlet in the *Tools* category.

### Manually Creating A Software Catalog [](id=manually-creating-a-software-catalog-liferay-portal-6-2-user-guide-13-en)

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
Liferay source. It is a file called `liferay-plugin-package_6_2_0.dtd`. Use this
DTD with a validating XML editor (a good, free choice is jEdit with all the XML
plugins) to create your software catalog manually.

### Connecting to a Software Catalog [](id=connecting-to-a-software-catalog-liferay-portal-6-2-user-guide-13-en)

If there is a software catalog of plugins you would like to point your instance
of Liferay to, all you need is the URL to the catalog. Once you have the URL, go
to the App Manager in the Control Panel and click the *Install* tab. You will
see there is a field in which you can enter a URL to a plugin repository.

Enter the URL to the repository to which you wish to connect and click *Save*.
The portlet will connect to the repository and items from this repository will
be shown in the list.

If all this talk of catalogs has put you in the mood to do some shopping, then
it's probably a good time to get acquainted with Liferay's Shopping application.
Let's go down that aisle next.

## Shopping [](id=shopping-liferay-portal-6-2-user-guide-13-en)

Would your organization like to make some money selling promotional items? Are
you an artist looking to share your work with the world? Perhaps your company
produces a publication that customers want to purchase? If you have something of
value the visitors of your site want or need, then Liferay's Shopping
application can help you get these items to your customers with a secure
transaction.

The Shopping portlet uses PayPal and allows you to choose the credit cards
your store accepts. You can organize your inventory with categories and
subcategories. A search function helps users find items quickly. Users
place items in a shopping cart, allowing them to purchase multiple items at
once. There is also an email notification system to alert customers when their
transactions are processed.

Before we start printing money, let's first create an online store.

### Setting up shop [](id=setting-up-shop-liferay-portal-6-2-user-guide-13-en)

To begin setting up a store, place the Shopping application on a page in your
site. Like the Message Boards portlet, the Shopping portlet takes up a lot of
space. It's best, therefore, to dedicate an entire page to the application. The
Shopping portlet is available from the *Add* &rarr; *Applications* menu on the
left side panel under Shopping.

![Figure 13.11: Start setting up the store by entering items and categories in the shopping portlet.](../../images/shopping-add-portlet.png)

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

#### Creating Categories [](id=creating-categories-liferay-portal-6-2-user-guide-13-en)

It's not difficult to create categories. Simply click the *Add Category* button
to display the Category form. In this form enter the *Name*, *Description*, and
set the *Permissions* for the category. That's all there is to it.

![Figure 13.12: In this figure there are three subcategories for the `Aromatherapy` category. The first subcategory has three items, the second has two, and the third is empty.](../../images/shopping-categories.png)

When you click *Save*, your new category is listed in the portlet, along with
the number of subcategories and the number of items that are in the category.
You can edit the category, set permissions for it or delete it using the
*Actions* button.

![Figure 13.13: Breadcrumbs are an important navigational tool in the shopping portlet.](../../images/shopping-category-breadcrumbs.png)

Each category can have unlimited subcategories and you can add subcategories to
any category. Notice as you add categories and subcategories, navigational
breadcrumbs appear in the portlet. Use these to move through the store
inventory.

#### Creating Items [](id=creating-items-liferay-portal-6-2-user-guide-13-en)

When you select a category, you'll see its items appear. You create items the
same way you create categories. Use the *Add Item* button to open the new item
form. Enter data for the SKU number, name, description, and item properties. You
can select checkboxes to specify whether the item requires shipping and
whether it is a featured item. Enter the stock quantity to record how many
items are available and set the appropriate permissions.

The Fields area is where you add additional fields to set specific
characteristics for the item. These can include things like sizes and colors.
The additional fields appear in the item form as pull-down menus.

The Prices area is for all data pertaining to the item's cost, minimum, and
maximum quantities, quantity discounts, taxes, and shipping costs.

The Images area lets you add photos to the item form. You can add a link to the
photo or upload the file locally. Choose from three sizes of images. You must
select the appropriate check box for the image you want to display. When you're
finished creating a new item, click *Save*.

![Figure 13.14: The image in this figure is the medium sized option.](../../images/shopping-item-image2.png)

As products are added, they are listed in the Items section of the portlet. If
the item you just created needs to go into one of your new categories or
subcategories, you can assign it to the category by editing the item. Choose the
*Select* button (next to the *Remove* button), and this displays a dialog box
listing all the shop categories. Choose the desired category from the list to
relocate the item to its new location. Notice how the breadcrumbs reflect this
change in the item form. For a tutorial on categories, breadcrumbs, and item
relocation, visit the *Moving a Link* section in this chapter.

You can make changes to any item through *Actions* &rarr; *Edit*. Finding an
item is easy, using the *Search* function.

That's how you create an item for the store. Now let's examine some of the
shopping portlet's configuration options.

### Configuration [](id=configuration-liferay-portal-6-2-user-guide-13-en)

By selecting the *gear* icon in the top right of the portlet, you can manage
the configuration options of the shopping application. In the Setup view, there
are tabs for Payment Settings, Shipping Calculation, Insurance Calculation, and
Emails.

#### Payment Settings [](id=payment-settings-liferay-portal-6-2-user-guide-13-en)

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

#### Shipping and Insurance Calculation [](id=shipping-and-insurance-calculation-liferay-portal-6-2-user-guide-13-en)

Both the Shipping and Insurance forms have identical options.

**Formula:** sets the equation for determining shipping and insurance costs.
They're calculated on either a flat rate based on the total purchase amount or
on a percentage of the total amount spent.

**Values:** sets the shipping and insurance fees based on a range of figures
that the total order amount falls under.

#### Emails [](id=emails-liferay-portal-6-2-user-guide-13-en)

This form sets the addresses for customer email notifications. Use the list of
term definitions below to customize the correspondence with your customers.

![Figure 13.15: Shopping emails can be configured in a myriad of ways to suit your needs.](../../images/shopping-confirmation-email-form.png)

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

### Using the shopping cart [](id=using-the-shopping-cart-liferay-portal-6-2-user-guide-13-en)

Logged in users are given a shopping cart to store the items they wish to buy.
Customers can manage items and their quantities directly from the cart, allowing
them to purchase a single product or multiple products at once. Customers can
also key in coupon codes to take advantage of any discounts your store has to
offer. Products can be placed in the cart from any category or subcategory. The
cart's appearance can be customized to reflect the overall design of your store.

When buyers select an item, they see the item's description displaying all of
its relevant information. The figure below is typical of what an item's
description might look like.

![Figure 13.16: Your product's SKU number is listed above the image.](../../images/shopping-item.png)

Below the product description is the Availability field indicating whether the
item is in stock. There are also two buttons for managing the shopping
experience:

**Add to Shopping Cart:** places the item in your cart for checkout.

**Next:** lets you to scroll through all the items in the category, giving you
the option to add to the cart as you go.

After adding an item to the cart, click Back (blue arrow) to return to the
product description and continue shopping by navigating the category breadcrumbs
at the top of the form. You can also continue shopping by scrolling through a
category, item by item, using the *Previous* and *Next* buttons at the bottom of
the product description.

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

![Figure 13.17: The shopping cart gives a preview of the items you'd like to buy.](../../images/shopping-cart-order.png)

When you're ready to checkout, click the *Checkout* button at the bottom of the
screen. When all the data has been entered correctly, click *Continue* to see
the order summary. After reviewing the summary, click *Finished* and you are
given confirmation the order has been placed, along with the order number. Use
this number to search for the order history and keep track of its status.

#### Customizing the shopping cart with a hook [](id=customizing-the-shopping-cart-with-a-ho-liferay-portal-6-2-user-guide-13-en)

If you think the shopping cart looks a little basic for your purposes, you can
customize it by using a hook. To learn more about changing the appearance of the
shopping cart, consult the [*Liferay Developer's
Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) or
see section 8.3 in [*Liferay in Action*](http://manning.com/sezov). 

Now your online store is set up, you have inventory, you have a payment system,
and you have sales rolling in. All is good. Some day there will be customers
with questions about their orders. Let's go over the orders next.

### Managing Orders [](id=managing-orders-liferay-portal-6-2-user-guide-13-en)

On the Orders tab, there are fields for finding specific orders. Search for
orders using the order number, order status, first or last name on the order, or
by the email address associated with the account. For more information on
searching in Liferay Portal, see the Faceted Search section in chapter 6 of this
guide.

Below the search fields is the orders list. Orders can be deleted or edited
using the *Actions* button. When you select an order from the Orders tab, or if 
you edit an order, you see a summary of the order details along with some 
options across the bottom.

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

### Managing Coupons [](id=managing-coupons-liferay-portal-6-2-user-guide-13-en)

The Coupons view of the Shopping application lets you provide coupon codes for
special sale events or other discounts. You can determine the type of discount
to apply and whether it is currently active. You can search for coupons and
create new coupons from this form.

![Figure 13.18: Create a coupon code automatically when you select the Autogenerate Code box.](../../images/shopping-coupon.png)

To add a coupon, enter the coupon code in the Code field. If no code is
specified, you can create one automatically by selecting the *Autogenerate Code*
checkbox. After entering the coupon's name and description, you can set the
coupon's start and expiration dates. Additional options let you activate the
coupon and set it to never expire.

![Figure 13.19: Customize your coupon parameters under Discounts and Limits.](../../images/shopping-coupon-discount-limits.png)

Under the Discount section, you can set the minimum order amount required for
the discount, the discount amount, and the discount type. Types can be based on
a percentage, a fixed amount, free shipping, or a tax free sale. The Limits
section lets you set coupon restrictions based on a list of categories and/or
SKU numbers.

#### Integrating the Amazon Rankings portlet [](id=integrating-the-amazon-rankings-portlet-liferay-portal-6-2-user-guide-13-en)

If your store sells books, you can use Liferay's Amazon Rankings application to
display them alongside the main shopping portlet. Both of these are found in the
Shopping category under *Add* &rarr; *Applications* in the left menu. The Amazon
Rankings application lets you highlight the books in your store's inventory
outside of the typical category structure. Books are arranged in ascending order
according to Amazon's Best Sellers Rank. Book cover images displayed in the
portlet come from the images in the product's description.

##### Setting up your Amazon Web Services account [](id=setting-up-your-amazon-web-services-acc-liferay-portal-6-2-user-guide-13-en)

To use Amazon rankings, you must first setup an Amazon Associates Program
account. This gives you the *associate ID tag* you need to enter in your
`portal-ext.properties` file. Then you need to join the Amazon Product Advertising
API group. This yields the *access key id* and the *secret access key* that
also must go into your `portal-ext.properties` file.

Amazon License Keys are available here:

[https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/](https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/)

Add the following lines to your `portal-ext.properties` file and populate the
values for the associate ID tag, access key ID, and secret access key. Ensure
there are no spaces between the `=` sign and the property values.

	amazon.access.key.id=
	amazon.associate.tag=
	amazon.secret.access.key=

Note that these keys are provided by Amazon for personal use only. Please
consult Amazon at [http://www.amazon.com](http://www.amazon.com) for more
information.

To obtain the `amazon.associate.tag`, visit
[https://affiliate-program.amazon.com/gp/associates/apply/main.html](https://affiliate-program.amazon.com/gp/associates/apply/main.html)
and apply, if necessary. Your associate tag is the *Tracking ID* listed in the
upper left corner of the [Associates
Central](https://affiliate-program.amazon.com/gp/associates/network/main.html)
page.

---

![Tip](../../images/01-tip.png) **Tip:** Make sure to create your affiliate
program before generating an access key; otherwise, the access key will not be
linked to your affiliate program. 

---

If your Amazon Web Services key is set improperly, you can't add books to your
Shopping portlet.

##### Setting up the Amazon Rankings portlet [](id=setting-up-the-amazon-rankings-portlet-liferay-portal-6-2-user-guide-13-en)

After setting up your Amazon Web Services account, choose the books to display
in your store. Select *Configuration* from the Amazon Rankings portlet in the
upper right corner. Go to the *Setup* tab and enter the International Standard
Book Numbers (ISBNs) in the textbox, separated by spaces. The portlet accepts
10-digit ISBNs rejecting ISBNs that letters.

![Figure 13.20: Separate ISBNs with single spaces.](../../images/shopping-amazon-rankings-config.png)

When you are finished setting up the rankings, books appear in the portlet
similar to the example below. Clicking on the book's cover image opens the
book's Amazon page.

![Figure 13.21: Using the Amazon Rankings application can be a nice addition to your store.](../../images/shopping-and-amazon-rankings.png)

Now that you have a good grasp on Liferay's Shopping and Amazon Rankings
applications, let's learn how to generate reports in Liferay.

## Generating Reports in Liferay [](id=generating-reports-in-liferay-liferay-portal-6-2-user-guide-13-en)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay's Reports portlet allows administrators to create reports and schedule
report generation runs. In order to work, the Reports portlet needs to be
combined with an appropriate implementation such as the JasperReports web
plugin. You can use Liferay's Reports portlet to create professional reports
containing charts, images, subreports, etc. When a report is generated, data is
dynamically pulled from Liferay's database into a template. Data can be pulled
into Jasper reports via JDBC and Jasper reports can be exported to many
different file formats including PDF, HTML, XLS, RTF, CSV, or XML. The Reports
and JasperReports plugins are available as apps on Liferay Marketplace. You can
purchase, download, and install the Reports and JasperReports apps directly from
your Liferay instance's Control Panel interface or you can navigate to
[http://www.liferay.com/marketplace](http://www.liferay.com/marketplace) in your
browser, purchase and download the apps, and copy the `.lpkg` files to your
Liferay instance's `/deploy` folder.

### Using the Reports Portlet [](id=using-the-reports-portlet-liferay-portal-6-2-user-guide-13-en)

Once you've installed the Reports and Jasper Reports EE applications, log in to
your portal as an administrator and navigate to *Site Administration*. If your
applications have been successfully deployed, you'll find a *Reports Admin*
entry in the *Configuration* section on the left menu.

![Figure 13.22: To check that the Reports EE and Jasper Reports EE applications have been successfully deployed to your Liferay server, look for the *Reports Admin* entry in the Configuration section of your Site Administration page.](../../images/reports-admin-site-admin.png)

The Reports Admin portlet has three tabs:

- *Reports*
- *Definitions*
- *Sources*

The Reports tab shows a list of all generated reports. The Definitions tab shows
a list of report definitions. The most important features of a report definition
are the data source, which determines where to find the data to be displayed in
a report, and the template, which determines which information to display and
how to display it. The Sources tab allows you to add new data sources which can
be selected by report definitions. Note: your portal's database is automatically
set up as a default data source called *Portal*. The *Portal* data source does
not appear on the Sources tab but is selectable on the form for adding/editing a
report definition.

![Figure 13.23: Use the *Sources* tab of the Reports Admin portlet to define data sources for report definitions. Use the *Definitions* tab to define report definitions, generate reports, and schedule reports for generation. Use the *Reports* tab to browse through and download generated reports.](../../images/reports-admin-portlet.png)

In order to generate a report, you need to a have one or more report definitions
configured. To create a report definition, you need a data source and a report
template. If you'd like to use your portal's database as your report
definition's data source, use the default data source called *Portal*. If you'd
like to use a different data source, navigate to the *Sources* tab of the
Reports Admin portlet and click on the *Add Source* button. Enter a name for the
new data source and enter the JDBC connection information:

- Driver Class Name
- URL
- User Name
- Password

For example, to connect to a MySQL database called *data_source* installed on
the Liferay server, you could use the following credentials:

- Driver Class Name: *com.mysql.jdbc.Driver*
- URL: *jdbc:mysql://localhost/data_source?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false*
- User Name: *[MySQL User Name]*
- Password: *[MySQL Password]*

Click on the *Test Database Connection* button to make sure that you've entered
the connection information correctly. Liferay will respond with a success or
error message depending on whether or not it was able to connect to the data
source. From the Reports Admin portlet's Sources tab, you can see a list of all
the data sources that have been added. You can configure data sources'
permissions to customize who can edit them and you can delete data sources that
should no longer be used.

Once your data source has been saved, make sure that your report template is
ready for use. If you're using Jasper and need to create a Jasper template,
consider using a tool like iReport Designer or Jaspersoft Studio to create your
template. iReport is built on top of Netbeans IDE while Jaspersoft Studio is
Eclipse-based. 

Once you've created a Jasper template (`.jrxml` file), you're
ready to create a report definition. Liferay handles compiling the template,
populating the template with data, and exporting the report. For more
information on using iReport Designer and on the Jasper Report lifecycle, please
refer to Jaspersoft's [wiki](http://community.jaspersoft.com/wiki/ireport-designer-getting-started) and
[documentation](http://community.jaspersoft.com/documentation?version=7114).

To add a report definition, navigate to the *Definitions* tab of the Reports
Admin portlet and click *Add Report Definition*. Enter a definition name and,
optionally, a description. Select a data source for your report definition.
Remember that you can use your portal's database as your database by selecting
the default *Portal* data source. Then select a Jasper template (`.jrxml` file)
for your report template. Optionally, you can add report parameters and values
to your report definition and they'll be injected into the template at runtime
when the report is generated. Lastly, you can configure the permissions of your
new report definition. By default, new reports, report definitions, and data
sources are site-scoped. For this reason, new report definitions are set to be
viewable by site members. When you've completed your new report definition,
click *Save*.

Now you're ready to use your report definition to generate reports manually or
schedule them to be generated. From the Report Admin portlet's Definitions tab,
click *Actions* &rarr; *Add Report* next to your report definition. You can
choose any of the following reports formats:

- CSV
- HTML
- PDF
- RTF
- TXT
- XLS
- XML

You can configure email notifications or specific email recipients. Email
notifications just inform users that a report has been generated and provide a
link to the report's location in the portal. Email recipients actually receive
copies of the report as email attachments. You can customize the account from
which report notifications and deliveries are sent as well as the messages
themselves from the configuration window of the Reports Admin portlet. When
generating a report, you can also configure the permissions of the report to be
generated. By default, generated reports are site-scoped and are viewable by
site members.

To configure reports to be generated on a schedule, click *Actions* &rarr; *Add
Schedule* next to your report definition from the Report Admin portlet's
Definitions tab. You can select a start date and, optionally, an end date. You
can also select how often to repeat the report generation event:

- *Never*
- *Daily*
- *Weekly*
- *Monthly*
- *Yearly*

As on the *Add Report* form, you can select a report format, configure email
notifications and email recipients, and configure the permissions of the reports
to be generated. When you're done setting up your report generation schedule,
click *Save*. Great! Now your reports will automatically be generated on the
dates you've configured. You can create multiple reports from a single report
definition. You can also edit a report definition, configure a report
definition's permissions, or delete a report definition from the Report Admin
portlet's Definitions tab.

Once one or more reports have been generated, they'll appear in the Reports
Admin portlet's Reports tab. To edit a report's permissions or to delete a
report, use the *Actions* button next to the report's name. To view details
about a report, click on its name. From the details view of a report, you can
download the report by clicking *Actions* &rarr; *Download* next to the report
file. You can also deliver the report by clicking *Actions* &rarr; *Deliver
Report*, choosing an email recipient, and clicking *Deliver*. To delete the
report file, click *Actions* &rarr; *Delete*.

### Configuring the Reports Admin Portlet [](id=configuring-the-reports-admin-portlet-liferay-portal-6-2-user-guide-13-en)

To configure the Reports Admin portlet, navigate to the Reports Admin portlet in
the Control Panel, click on the *wrench* icon at the top right corner of the
portlet, and select *Configuration*. There are three tabs:

- *Email From*
- *Delivery Email*
- *Notifications Email*

The Email From tab allows you to customize the name and email address of the
account that sends report notifications within your portal. For example, you
could set the name to *Reports Admin* and the email address to
*reports@liferay.com*. The Delivery Email tab allows you to customize the email
message that's sent when a report is delivered to a portal user. When a report
is delivered to a portal user, it's included as an attachment to this email
message. Finally, the Notifications Email tab allows you to customize the
message that's sent when a report notification is sent to a portal user. In this
case, the report is not included as an attachment; the email message just
provides a link to the report's location in the portal.

### Using the Reports Display Portlet [](id=using-the-reports-display-portlet-liferay-portal-6-2-user-guide-13-en)

Installing the Reports EE and JasperReports EE apps not only adds the Reports
Admin portlet to the Control Panel but also makes the Reports Display portlet
available. The Reports Display portlet provides the same functionality as the
Reports tab of the Reports Admin portlet. The Reports Display portlet allows
users to download or deliver reports but not to add, edit, or delete report
definitions or data sources.

![Figure 13.24: Here, the Reports Display portlet shows only a single report.](../../images/reports-display-portlet.png)

The Reports Display portlet is intended to be placed within a site's private
pages to allow site members to access site-scoped reports. It can also be placed
within a site's public pages. In this case, reports will only be displayed to
users that have permission to view the reports. For example, if a guest views a
site's public page to which the Reports Display portlet has been added,
site-scoped reports will not be visible; only reports for which the View
permission has been added to the Guest role will appear in the Reports Display
portlet.

### Creating a Sample Jasper Report in Liferay [](id=creating-a-sample-jasper-report-in-life-liferay-portal-6-2-user-guide-13-en)

To create a sample Jasper report in Liferay, follow this section's instructions.
We'll use your portal's database as our report definition's data source and
we'll use a simple Jasper template that was created using iReport Designer.

1. Save the following Jasper template to a file named `sample-report.jrxml`
   somewhere on your machine's file system.

        <?xml version="1.0" encoding="UTF-8"?>
        <jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="sample-report2" language="groovy" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20">
                <property name="ireport.zoom" value="1.0"/>
                <property name="ireport.x" value="0"/>
                <property name="ireport.y" value="0"/>
                <queryString language="SQL">
                        <![CDATA[select * from User_]]>
                </queryString>
                <field name="userId" class="java.lang.Long">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="screenName" class="java.lang.String">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="emailAddress" class="java.lang.String">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="createDate" class="java.sql.Timestamp">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="modifiedDate" class="java.sql.Timestamp">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <group name="userId">
                        <groupExpression><![CDATA[$F{userId}]]></groupExpression>
                </group>
                <group name="screenName">
                        <groupExpression><![CDATA[$F{screenName}]]></groupExpression>
                </group>
                <group name="emailAddress">
                        <groupExpression><![CDATA[$F{emailAddress}]]></groupExpression>
                </group>
                <group name="createDate">
                        <groupExpression><![CDATA[$F{createDate}]]></groupExpression>
                </group>
                <background>
                        <band splitType="Stretch"/>
                </background>
                <title>
                        <band height="79" splitType="Stretch">
                                <staticText>
                                        <reportElement x="0" y="0" width="555" height="51"/>
                                        <textElement>
                                                <font size="24" isBold="true"/>
                                        </textElement>
                                        <text><![CDATA[Sample Report: Users]]></text>
                                </staticText>
                        </band>
                </title>
                <pageHeader>
                        <band height="35" splitType="Stretch"/>
                </pageHeader>
                <columnHeader>
                        <band height="61" splitType="Stretch">
                                <staticText>
                                        <reportElement x="0" y="0" width="63" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[userId]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="63" y="0" width="90" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[screenName]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="153" y="0" width="128" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[emailAddress]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="281" y="0" width="141" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[createDate]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="422" y="0" width="133" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[modifiedDate]]></text>
                                </staticText>
                        </band>
                </columnHeader>
                <detail>
                        <band height="125" splitType="Stretch">
                                <textField>
                                        <reportElement x="0" y="0" width="63" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{userId}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="63" y="0" width="90" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{screenName}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="153" y="0" width="128" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{emailAddress}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="281" y="0" width="141" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{createDate}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="422" y="0" width="133" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{modifiedDate}]]></textFieldExpression>
                                </textField>
                        </band>
                </detail>
                <columnFooter>
                        <band height="45" splitType="Stretch"/>
                </columnFooter>
                <pageFooter>
                        <band height="54" splitType="Stretch"/>
                </pageFooter>
                <summary>
                        <band height="42" splitType="Stretch"/>
                </summary>
        </jasperReport>

2. Log in to your portal as an administrator, navigate to Site Administration,
   and navigate to the Reports Admin portlet.

3. Navigate to the Definitions tab and click *Add Report Definition*.

4. For the definition name, enter *Jasper Test*.

5. Leave the data source selection as the default: *Portal*.

6. Browse to and select the `sample-report.jrxml` template that you created in
   step 1, then click *Save* to create your report definition.

7. Click *Actions* &rarr; *Add Report* next to your Jasper Test report
   definition, choose the PDF report format, and click *Generate*.

8. Navigate to the Reports Admin portlet's Reports tab and click on the report
   you generated.

9. Click *Actions* &rrar; *Download* next to the `sample-report.pdf` file.

This report should list all of your portal's users, displaying the userId,
screenName, emailAddress, createDate, and modifiedDate of each user.

![Figure 13.25: This reports lists all portal users by userId, screenName, emailAddress, createDate, and modifiedDate.](../../images/sample-report-users.png)

Now that we understand how to generate Jasper reports in Liferay, let's see what
the Knowledge base application has to offer. 

## Knowledge Base [](id=knowledge-base-liferay-portal-6-2-user-guide-13-en)

![EE Only Feature](../../images/ee-feature-web.png)

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
portlet is available as an app from Liferay Marketplace. You can purchase,
download, and install the Knowledge Base EE app directly from your Liferay
instance's Control Panel interface or you can navigate to
[http://www.liferay.com/marketplace](http://www.liferay.com/marketplace) in your
browser, purchase and download the app, and copy the `.lpkg` file to your
Liferay instance's `/deploy` folder.

### Knowledge Base Display Portlet [](id=knowledge-base-display-portlet-liferay-portal-6-2-user-guide-13-en)

The Knowledge Base app actually consists of four portlets that can be placed on
site pages as well as one that adds a page to Site Administration. The four
portlets that can be placed on a page are Knowledge Base (Display), Knowledge
Base Article, Knowledge Base Search, and Knowledge Base Section. When placed on
a page, the Knowledge Base display portlet presents many of the same options to
an administrator that are available from the Knowledge Base page of Site
Administration.

![Figure 13.26: The Knowledge Base Display portlet displays your recently added articles in a list.](../../images/liferay-collaboration-kb-display-portlet.png)

You can use the four links at the top of the Knowledge Base Display portlet to
control what it displays.

*Knowledge Base Home:* shows you a list of all top level articles.

*Recent Articles:* shows you a list of articles in order from most recent
activity to least recent activity.

*Administrator:* shows you a list of all articles, regardless of which ones are
parents or children of the others.

*My Subscriptions:* shows you a list of articles you are subscribed to.

The *Add Article* button is available from the Knowledge Base Home or
Administrator view of the Knowledge Base Display portlet or from the Articles
tab of the Knowledge Base page of Site Administration. Use this button to create
an article for the knowledge base. When creating an article, you can use the
same WYSIWYG editor you used to create wiki pages. Articles, however, are not
the same as wiki pages; they must be created in HTML, not MediaWiki or Creole.
Click the *Source* button in the editor to view the HTML source of what you've
written or write some HTML yourself.

![Figure 13.27: You can create a new Knowledge Base Article using the WYSIWYG editor.](../../images/liferay-collaboration-kb-new-article.png)

In addition to entering a title and creating content for your article, you can
use the editor to add attachments, add tags, and set permissions. By default,
view permission is granted to the guest role, meaning anyone can view your
article. After you're done using the editor, you can save it as draft and
continue working on it later or you can submit it for publication. Your article
may need to be approved before being published, depending on the workflow
defined for your portal.

You can find the *Permissions* button next to the Add Article button in the
Knowledge Base display portlet or on the Knowledge Base page of Site
Administration. Click this button to define permissions that apply to the
Knowledge Base Display portlet generally, not to particular articles. Here, you
can define which roles can add articles and templates, which are granted
knowledge base administrator privileges, which can change permissions on
articles, which can subscribe to articles, and which can view templates.

![Figure 13.28: Set various permissions for each of your portal's roles in the Knowledge Base Permissions interface.](../../images/liferay-collaboration-kb-permissions.png)

Users may need to be granted access to the knowledge base page of Site
Administration in order to exercise some of the above permissions. For example,
suppose the user role has been granted the *Add Article* and the *View
Templates* permissions. A user will be able to add articles from the Knowledge
Base Display portlet but will need access to the knowledge base page of Site
Administration in order to view templates. Note that the Knowledge Base
Display permissions are distinct from the Knowledge Base Admin portlet. The
display permissions define what a user can do with the Knowledge Base Display
portlet on a page while the admin permissions define what a user can do on the
Knowledge Base page of Site Administration.

### Knowledge Base Page of Site Administration [](id=knowledge-base-page-of-the-control-pane-liferay-portal-6-2-user-guide-13-en)

The Knowledge Base page of the Site Administration interface has two tabs: one
for articles and one for templates. The *Articles* tab shows all the articles in
the knowledge base and lets you perform actions on them. The *Templates* tab
shows all the templates defined in the knowledge base and lets you perform
actions on them.

![Figure 13.29: Administrators can control knowledge base articles and templates from the *Site Administration* &rarr; *Content* page.](../../images/liferay-collaboration-kb-control-panel.png)

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
Administrator view of the Knowledge Base Display portlet. However, the Knowledge
Base Display portlet is intended to be placed on a page for the end user so an
additional action is available: *RSS* is a link to an RSS feed of an article.
Also, the *View* action is only available from the Site Administration page
since the Knowledge Base Article portlet can be used to display an article on a
page.

The Templates tab of the Knowledge Base page of Site Administration allows
administrators to create templates to facilitate the creation of articles. A
template basically functions like a starting point for the creation of certain
types of articles. Click the *Add Template* button on the Templates tab of the
Knowledge Base page of Site Administration to create a new template.

![Figure 13.30: As an administrator, you can add a new template to your knowledge base by navigating to *Site Administration* &rarr; *Content* &rarr; *Knowledge Base (Admin)*.](../../images/liferay-collaboration-kb-new-template.png)

Navigate back to the Templates tab of the Knowledge Base page of Site
Administration. You can perform the following actions on a template:

*View:* displays a template. From here, you can use the template to create an
article, edit the template, modify the permissions on the template or delete it.

*Edit:* allows you to change the title and content of a template.

*Permissions:* allows you to configure the permissions on a template. You can
choose roles to have permission to update, view, delete or change the
permissions on templates.

*Delete:* lets you remove a template from the knowledge base.

To use a template to create a new article, you have to view the template and
then click *Use this Template*. This brings you to the New Article editor with
the contents of the template copied for you.

### Knowledge Base Article Portlet [](id=knowledge-base-article-portlet-liferay-portal-6-2-user-guide-13-en)

The Knowledge Base Article portlet can be placed on a page to display an entire
article. When you first place this portlet on a page, it displays the message
*Please configure this portlet to make it visible to all users*. This message is
a link to the configuration dialog box for the portlet. Click *Select Article*
to choose an article to display. Pick an article and then click *Save*. When
your page refreshes it will display the article in the portlet.

![Figure 13.31: Display articles using the Knowledge Base Article portlet.](../../images/liferay-collaboration-kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the
article it displays. There are also links at the top of the portlet users can
use to subscribe to an RSS feed of the knowledge base, subscribe to the article,
view the history of the article, or print the article.

### Knowledge Base Section Portlet [](id=knowledge-base-section-portlet-liferay-portal-6-2-user-guide-13-en)

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

![Figure 13.32: Here's an image of Knowledge Base Section portlets being displayed on a page.](../../images/liferay-collaboration-kb-section-portlet.png)

The Knowledge Base section portlet has some additional configurations that allow
an administrator to select a display style (title or abstract), an article
window state (maximized or normal), how to order the articles, how many articles
to display per page, and whether or not to show pagination.

### Knowledge Base Navigation [](id=knowledge-base-navigation-liferay-portal-6-2-user-guide-13-en)

Wikis often have deeply nested articles that can be hard to find by browsing.
Liferay's knowledge base's ability to selectively display articles makes it
easier to browse than a Wiki. The knowledge base also features some other aids
to navigation. The Knowledge Base Search portlet allows you to search for
articles in the knowledge base. This portlet presents the search results to you
in order from most relevant to least relevant.

![Figure 13.33: The Knowledge Base Search portlet helps you search the knowledge base for keywords.](../../images/liferay-collaboration-kb-search-portlet.png)

You can also use the Categories Navigation portlet in conjunction with the
Knowledge Base Display portlet. When both of these portlets are placed on a page
you can select a topic in the Categories Navigation portlet and the Knowledge
Base Display portlet will show all of the articles that match the topic. You can
create topics from the Categories page of *Site Administration* &rarr;
*Content*.

![Figure 13.34: Use the Categories Navigation portlet to search articles that match a specific topic.](../../images/liferay-collaboration-kb-catnavandkbdisplay-portlets.png)

You can select topics for articles when you are creating or editing them.

## Akismet [](id=akismet-liferay-portal-6-2-user-guide-13-en)

Akismet is a web-based spam detection service. The Akismet app, available from
Liferay Marketplace, integrates Liferay with Akismet to provide spam detection
for Liferay's message boards, blogs, wikis, and comments. When you install the
Akismet app from Liferay Marketplace, two portlets are added to the Control
Panel: the *Akismet* portlet is added to the Configuration section and the *Spam
Moderation* portlet is added to the Content section of Site Administration.

In order to enable Liferay to use Akismet, you need to visit
[http://akismet.com](http://akismet.com), create an account, and generate an API
key. Once you've generated an API key, navigate to the Configuration section of
the Control Panel and click on *Akismet*. Enter your API key into the provided
field and check the *Enabled for Message Boards* and *Enabled for Discussion*
boxes. The term "discussions" is another way to refer to comment threads in
Liferay. Optionally, you can customize the *Reportable Time* and the *Check
Threshold*. The Reportable Time indicates the time in days after a post is
created or updated that it can be marked as spam or not spam. The Check
Threshold indicates the number of posts after which Akismet will no longer
check posts. After you've finished configuring Akismet, click *Save*.

To see Akismet in action, add some message board posts or comment on some
existing Liferay assets. See if you can get Akismet to mark one as spam. If
Akismet doesn't mark a post as spam, you can always manually do as an
administrator. Once one or more posts have been marked as spam, navigate to the
Content section of the Site Administration section of the Control Panel, then
click on *Spam Moderation*. Here, you can find all the message board posts,
discussions (comments), and wiki pages that have been marked as spam. You can
select all the posts that you agree are spam and delete them. You can also
select any posts that you don't agree are spam and mark them as *Not Spam*. If
you're not sure about a post, click the *Actions* button and select *View in
Context* to view the post in its original setting.

## Weather [](id=weather-liferay-portal-6-2-user-guide-13-en)

Liferay's Weather portlet displays basic weather-related information
(temperature, conditions) for multiple configurable locations. It's available as
an app from Liferay Marketplace. For each configured location, a link to [Open Weather Map](http://openweathermap.org/)
is provided that points to more detailed information. The Weather portlet also
provides a search bar that allows users to find information about locations that
aren't listed by the portlet. Searching via the search bar forwards the user to
[Open Weather Map](http://openweathermap.org/), where the search is executed.

![Figure 13.35: Liferay's Weather portlet displays basic weather-related information (temperature, conditions) for multiple configurable locations.](../../images/weather-portlet.png)

To configure the displayed locations, open the Weather portlet's configuration
window. In the first text area, enter the names of the cities about which you'd
like weather information to be displayed. Alternatively, you can use zip codes.
Enter one city or zip code per line. For the temperature format, you can choose
between Celsius and Fahrenheit.

## OAuth [](id=oauth-liferay-portal-6-2-user-guide-14-en)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay's OAuth utility authorizes third-party applications to interact with a
user's resources. It's available as an app from Liferay Marketplace. Let's say
you're hosting Liferay Portal and have users and customers coming to your web
site. You want them to have access to a third party resource, like Twitter, and
be able to access their accounts from your site. In the past, they would have to
provide their Twitter user names and passwords, but not if you use OAuth.
For this reason, a popular characterization for the OAuth client is the "valet key for
your web services."

OAuth is a handshake mechanism where, instead of asking for personal
information, Liferay redirects users to a service provider like Twitter, where
they can tell Twitter to allow Liferay limited access to their accounts. This
example is similar to our earlier "valet key" characterization. You wouldn't
want a valet driver opening your glove box, storage spaces, hood, and other
personal compartments in your vehicle. You would only want the valet to
access what is necessary to park your car. OAuth is based on this same
idea: it gives a site just enough information to do what it needs and nothing
more. This assures users that their personal information is safe, but gives
them freedom to take advantage of valuable resources they typically use from the
service provider's site.

### Registering OAuth Applications [](id=registering-oauth-applications-liferay-portal-6-2-user-guide-14-en)

The first thing you'll need to do is register an application for OAuth's
services. To access the OAuth Admin page, navigate to the Control Panel and,
under the *Users* heading, select *OAuth Admin*. Then select *Add* to create a
new OAuth application in the OAuth registry. You'll be given the following
options:

**Application Name:** the display name for your application

**Description:** the short description that is attached to your application

**Website URL:** your application's URL

**Callback URI:** the URI where users are redirected after authentication is
complete

**Access Level:** select the *Read* or *Write* access level. For the *Read*
access level, the user can only view the application's contents, but not modify
them. The *Write* access level gives the user permission to access and modify
the application's contents.

After you're finished registering the OAuth app, click *Actions* &rarr; *View*.
You'll notice Liferay generated two Application Credentials: the *Consumer Key*
and *Consumer Secret*. The consumer key is a value used by the application to
identify itself to the service provider. Likewise, the consumer secret is a
value the application uses to establish ownership of the consumer key.

![Figure 13.36: You'll need the application credentials to implement OAuth in your application.](../../images/oauth-app-credentials.png)

Take note of your application credentials; you'll need them when configuring
your application with OAuth. To learn how to configure an application with
OAuth, visit the [Liferay Developer's Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development).
Once you have your application configured to use OAuth, visit the next section to
begin authorizing requests via OAuth. 

<!-- Add link to Dev Guide OAuth section when available -->

### Authorizing Requests via OAuth [](id=authorizing-requests-via-oauth-liferay-portal-6-2-user-guide-14-en)

Once you have your application configured to use OAuth, you can place your
application on a page and test out the process. Here is a basic synopsis of
what's happening during the authorization process.

The app you registered in the previous section and configured with the consumer
key and secret in the Developer's Guide is characterized as a service provider.
The service provider uses OAuth to allow users access to its protected
resources. These protected resources are data controlled by the service
provider, which can only be accessed by the application through authentication.
By configuring an application in Liferay to use OAuth, you're keeping all
private information you have between a third-party service provider and the
portal separate. Essentially, OAuth bridges the connection between the portal
and third-party services without the user sharing any protected resources
between them. 

Once your OAuth application is placed on a portal page, you'll be asked to
grant or deny the third-party service provider limited access to your portal.

![Figure 13.37: You can grant or deny the service provider access to your.](../../images/oauth-authorize.png)

Congratulations! You've successfully installed your OAuth app and authorized
access between a service provider and Liferay Portal!

## Summary [](id=summary-liferay-portal-6-2-user-guide-13-en)

In this chapter, we examined several Liferay utility applications: the Bookmarks
portlet, the Shopping portlet, the Software Catalog, the Reports and
JasperReports applications, the Knowledge Base, the Akismet application, the
Weather portlet, and the OAuth application.

The Bookmarks portlet allows users and administrators to collect and share
useful link to pages either within or outside of the portal. The Shopping
portlet allows you to use PayPal to set up your portal for E-commerce activity.
The Software Catalog allows you to define a set of software items to make
available to visitors to your portal. Remember that the Software Catalog has
been replaced by Liferay Marketplace. If you want to use it, you'll have to
manually install it as a plugin. The Reports application is an EE-only
application that provides a Reports Admin portlet in the Control Panel for
creating reports and scheduling report generation. When combined with a suitable
implementation, such as the JasperReports application (also EE-only), you can
upload report definitions in the Reports Admin portlet, generate reports, and
export reports to a wide variety of formats.

The Knowledge Base application is an EE-only application that allows you to
create articles and organize them into full books or guides that be published on
your portal. The Akismet application offers a web-based spam detection service
for your portal's message boards, blogs, wikis, and comments. The Weather
portlet provides users with a summary of weather-related information for
multiple locations and provides a links to [Open Weather
Map](http://openweathermap.org/) for more detailed information for each
location. Lastly, the EE-only OAuth application manages third-party
applications' access to your portal's resources and vice versa. Next, let's take
a tour of the Liferay Marketplace and learn how to manage Liferay plugins.
