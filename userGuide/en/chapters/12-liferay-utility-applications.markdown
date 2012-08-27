# Liferay Utility Applications [](id=lp-6-1-ugen12-liferay-utility-applications-0)

In this chapter we'll look at some Liferay utility applications that might be
useful for you. The Software Catalog is currently packaged with Liferay but will
soon be replaced by Liferay Marketplace. Please see chapter 13 for information
about Liferay Marketplace and managing Liferay plugins. The Knowledge Base
application is an EE-only plugin. In this chapter we'll discuss both of these
applications:

- Software catalog
- Shopping
- Weather
<!--
## Bookmarks  [](id=lp-6-1-ugen12-bookmarks--0) Where did they go?
-->
- Knowledge Base

The Software Catalog allows you to define a set of software items to display to
visitors. The Knowledge Base applicaton allows you to create articles and
organize them into full books or guides that be published on your portal.

## Creating Your Own Plugin Repository [](id=lp-6-1-ugen15-creating-your-own-plugin-repository-0)

As your enterprise builds its own library of portlets for internal use, you can
create your own plugin repository to make it easy to install and upgrade
portlets. This will allow different departments who may be running different
instances of Liferay to share portlets and install them as needed. If you are a
software development house, you may wish to create a plugin repository for your
own products. Liferay makes it easy for you to create your own plugin repository
and make it available to others.

You can create your plugin repository in two ways:

1. Use the Software Catalog in the Control Panel to create the repository by
using its graphical interface and an HTTP server.

2. Create an XML file using the Liferay Plugin Repository DTD
(`http://www.liferay.com/dtd/liferay-plugin-repository_6_0_0.dtd`) and an HTTP
server.

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

![Figure 12.1: The Software Catalog with Nothing Installed](../../images/marketplace-software-catalog.png)

Each site in your portal can have an instance of the Software Catalog. The
Control Panel presents you with the software catalog for whichever site you are
working on. This means different sites can have different software repositories,
so you can host several software repositories on the same instance of Liferay if
you wish, they just have to be in different sites. Choose the site that will
host the plugin repository and go to the Control Panel. You will see at the top
of the screen a message that says “Content for [Site], where [Site] is the
site you were on when you selected the Control Panel from the dockbar. If you
want to administer the software catalog for a different site, you can select it
from the selection box.

![Figure 12.2: Populated Software Catalog from liferay.com](../../images/marketplace-populated-software-catalog.png)

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

![Figure 12.3: Adding a Product to the Software Catalog](../../images/marketplace-adding-product-to-software-catalog.png)

**Name:** The name of your software product.

**Type:** Select whether this is a portlet, theme, layout template, hook or web
plugin.

**Licenses:** Select the license(s) under which you are releasing this software.

**Author:** Enter the name of the author of the software.

**Page URL:** If the software has a home page, enter its url here.

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

![Figure 12.4: Adding a Product Version to the Software Catalog](../../images/marketplace-adding-product-version-software-catalog.png)

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

[http://<server name\>:<port number\>/software\_catalog?<Friendly URL name or Group ID\>](http://<server name\>:<port number\>/software\_catalog?<Friendly URL name or Group ID\>)

For example, if you are on the same machine as your Liferay instance, and that
instance is running on port 8080, and your group ID from the database is 10148,
you would use the following URL:

[http://localhost:8080/software\_catalog?10148](http://localhost:8080/software\_catalog?10148)

If you have also created a friendly URL called *old-computers* for this site,
you would use the following URL:

[http://localhost:8080/software\_catalog?old-computers](http://localhost:8080/software\_catalog?old-computers)

If you have configured everything properly, an XML document should be returned:

	<?xml version="1.0" encoding="UTF-8"?\>

	<plugin-repository\>

	<settings/\>

	<plugin-package\>

	<name\>My Summary</name\>

	<module-id\>old-computers/my-summary-portlet/1.0/war</module-id\>

	<modified-date\>Thu, 23 Apr 2009 20:40:16 +0000</modified-date\>

	<types\>

	<type\>portlet</type\>

	</types\>

	<tags\>

	<tag\>social</tag\>

	<tag\>profile</tag\>

	</tags\>

	<short-description\>My Summary</short-description\>

	<long-description\>My Summary</long-description\>

	<change-log\>Initial Version</change-log\>

	<download-url\>[http://www.liferay.com/portlets/my-summary-portlet-6.0.4.war](http://www.liferay.com/portlets/my-summary-portlet-5.2.2.war)

	</download-url\>

	<author\>Rich Sezov</author\>

	<screenshots/\>

	<licenses\>

	<license osi-approved="true"\>MIT License</license\>

	</licenses\>

	<liferay-versions/\>

	</plugin-package\>

	</plugin-repository\>

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
Let's go down that aisle that next.

## Shopping [](id=lp-6-1-ugen12-shopping-0)

Would your organization like to make some money selling promotional items? Are
you an artist looking to share your work with the world? Perhaps your company
produces a publication that customers want to purchase? If there is something of
value the visitors of your site want or need, then Lifeary's Shopping
application can help them complete a secure transaction with ease.

The Shopping portlet utilizes PayPal and allows you to choose which credit cards
your store will accept. You can organize your inventory with categories and
subcategories. There is also a search function that lets users find items
quickly. Users place items in a shopping cart, allowing them to purchase
multiple items at once. There is also an email notification system to alert
users when their transaction is being processed.

So before we start printing money, let's create an online store first.

### Setting up shop

To begin setting up a store, simply place the Shopping application on a page in
your site, although it might be preferable to dedicate an entire page to the
application depending on how many items you have to sell. The Shopping portlet
is available from the *Add* -> *More* menu in the task bar under Shopping.

![Figure 12.5: Start setting up the store by entering items and categories in the shopping portlet.](../../images/shopping-add-portlet.png)

The shopping portlet has the following four tabs across the top:

**Categories:** allows you to Search Categories, Add Category & manage
Permissions. The portlet defaults to this view.

**Cart:** is the shopping cart view. It displays the order subtotal, the
shipping cost, and a field for entering a coupon code. There are buttons for
Updating the Cart, Emptying the Cart, and Checking out.

**Orders:** displays a list of all orders placed with options to Search for
orders by the order Number, Status, First Name, Last Name and/or Email Address.

Below the search fields is the orders list. Orders can be Deleted or Edited
using the Actions button. Editing an order displays the order details with these
options: Invoice, Resend Confirmation Email, Send Shipping Email, Delete,
Cancel. There is also the option to enter comments on the form.

**Coupons:** allows you to enter data related to a discount: the coupon Code,
Discount Type, and whether it is Active or not. Search looks for a particular
coupon offer while Add Coupon opens a new form to enter the coupon data. Delete
removes a coupon.

To add a coupon, enter the coupon code in the *Code* field. If no code is
specified, you can create one automatically by selecting the *Autogenerate Code*
checkbox. After entering the coupon's *Name* and *Description*, you can set the
coupon's start and expiration dates. Additional options allow you to set a
coupon to Never Expire and make a coupon Active.

Under the Discount section, you can set the minimum amount required for the
discount to occur, the discount amount and the discount type. Types can be based
on a percentage, an actual amount, free shipping, or a tax free sale. The Limits
section allows you to set coupon restrictions based on a list of categories
and/or SKU numbers.

Below the tabs are breadcrumbs for navigating between the categories and
subcategories you create. In fact, this would be a good time to start creating
some categories.

#### Creating Categories

It's not difficult to create categories. Simply click the *Add Category* button
to display the Category form. In this form enter the *Name*, *Description*, and
set the *Permissions* for the category. That's all there is to it.

![Figure 12.6: In this figure there are three subcategories for the `Aromatherapy` category. The first subcategory has three items, the second has two, and the thrid is empty.](../../images/shopping-categories.png)

When you select *Save*, you will see your new category listed in the portlet,
along with the *# of Categories* and the *# of Items* that are in the category.
You can edit the category, set permissions for it or delete it using the
*Action* button.

![Figure 12.7: Breadcrumbs are an important navigational tool in the shopping portlet.](../../images/shopping-category-breadcrumbs.png)

Each category can have unlimited subcategories and you can add subcategories to
any category. Notice as you add categories and subcategories, navigational
breadcrumbs appear in the portlet. Use these to move through the store
inventory.

#### Creating Items

Below Categories is the section for Items. You create items the same way you
create categories. Use the *Add Item* button to open the new item form. Enter
data for the SKU number, name, description, and item properties. You can select
checkboxes to specify whether the item *Requires Shipping* and whether it is a
*Featured* item. Enter the *Stock Quantity* to record how many items are
available and set the appropriate permissions.

The Fields area is where you add additional fields to set specific
characteristics for the item. These can include things like sizes and colors.
The additional fields appear in the item form as pull-down menus, as in the
figure below.

![Figure 12.8: The additional fields you create for an item appear in the item description form as options.](../../images/shopping-item-options.png)

The Prices area of the new item form is where you enter all data pertaining to
the item's cost, minimum and maximium quantities, quantity discounts, taxes, and
shipping costs.

The Images area of the form allows you to add photos to the item form. You can
add a link to the photo or upload the file locally. Choose from three sizes of
images. You must select the appropriate checkbox for the image you want to
display. When you are finished creating a new item, click *Save*.

![Figure 12.9: The image in this figure is the medium sized option.](../../images/shopping-item-image.png)

As items are added, they will be listed in the Items section of the portlet. If
the item you just created needs to go into one of your new cateogries or
subcategories, you can assign it to the category through editing form. Choose
the *Select* button, next to the *Remove* button, and this displays a dialog box
listing all the shop categories.

![Figure 12.10: To put an item in a category, open *Edit* and choose *Select* to see the available options.](../../images/shopping-select-categories.png)

*Choose* the desired category from the list to relocate the item to it's new
location. Notice how the breadcrumbs reflect this change in the item form.

![Figure 12.11: When an item moves into a category, the breadcrumb navigation will update accordingly.](../../images/shopping-item-breadcrumb-change.png)

Any changes that need to be made to the item can be executed through *Actions*
-> *Edit*. Also, you can locate an item anywhere from within the application by
using the *Search* function.

That's how you create an item for the store. Now let's examine some of the
shopping portlet's configuration options.

### Configuration

By selecting the *wrench* icon in the top right of the portlet, you can
configure the shopping application. In the Setup view, there are tabs for
Payment Settings, Shipping Calculation, Insurance Calculation, and Emails.

#### Payment Settings

The payment settings section is where you configure all the functions related to
transactions for your store.

**PayPal Email Address:** is the address of your store's PayPal account where
all sales will flow.

PayPal can be disabled by entering a blank PayPal address in the field. Credit
cards can likewise be disabled. Payments to the store are not required when
these settings are disabled.

**Credit Cards:** sets the type of credit cards your store accepts.

The Current column holds the cards your store takes. The Available column holds
cards not accepted by your shop. These can be moved easily from one column to
another by selecting a card and clicking one of the arrow buttons. The arrows
below the Current window allow you to choose the order credit cards are
displayed on the form.

**Currency:** sets the appropriate currency your shop accepts.

**Tax State:** sets the applicable state where your business is based for tax
purposes.

**Tax Rate:** sets the percentage of taxes your store is responsible for paying.

**Minimum Order:** sets the amount for the minimum order required for a sale.

#### Shipping and Insurance Calculation

The Shipping and the Insurance forms have identical options.

**Formula:** sets the equation for determining shipping and insurance costs.
They're calculated on either a flat rate based on the total purchase amount or
on a percentage of the total amount spent.

**Values:** sets the shipping and insurance fees based on a range of figures
that the order amount falls under.

#### Emails

This form sets the addresses for customer email notifications.

![Figure 12.12: Shopping emails can be configured in a myriad of ways to suit your needs.](../../images/shopping-confirmation-email-form.png)

**Emails From:** sets the customers name and email address for basic
correspondence.

**Confirmation Email:** is the from used by the store to communicate with
customers about their orders. It could be used, for example, to let customers
know their transaction has been received and is being processed.

**Shipping Email:** is the form the store uses to allert customers that their
orders have been shipped.

Now that we have your store set up the way you want it, let's walk through the
buying process.

### Buying

Logged in users are given a shopping cart to store the items they wish to buy,
allowing them to purchase multiple items at once.

When buyers select an item from a category they see the item's description
displaying all of its relevant information.

![Figure 12.13: You can include images of each item in your store. (Medium sized images display on the item's description form).](../../images/shopping-item.png)

Below the description is a field indicating the item's availability plus other
options, if applicable. There is the

**Add to Shopping Cart:** places the item in your cart for checkout.

**Next:** lets you to scroll through all the items in the category, giving you
the option to add to the cart as you go.

After adding an item to the cart, click *<< Back* to return to the item
description and continue shopping by navigating the category breadcrumbs.

![Figure 12.14: This shopping cart has two items in it so far.](../../images/shopping-cart-order.png)

Each time you add an item to the cart, you will see a running talley of the
cart's contents. Quantities for each item are controlled using drop-down menus.
The order subtotal and shippinig costs are displayed above a field where coupon
codes are entered. When you have finished adding to the cart, you have three
options to choose from.

**Update Cart:** allows you to change the quantity of an item being purchased.
If a minimum number of items has been set in the item description, the field
will display that number in the cart by default. You can overide that with the
drop-down menus.

**Empty Cart:** allows you to clear the contents of the cart to either start
shopping again or to quit.

**Checkout:** sends you to a form to verify the billing address, shipping
address, and the credit card information. You can also add comments about the
order if necessary.

![Figure 12.15: All the information on an order can be seen in the order summary view.](../../images/shopping-order-summary.png)

When all the data has been entered correctly, click *Continue* to see the order
summary. After reviewing the order, click *Finished* and you will be given the
order number.

#### Customizing the shopping cart with a hook

If you think the shopping cart looks a little basic for your purposes, you can
customize it by using a hook. To learn more about changing the appearance of the
shopping cart, consult the *Liferay Developer's Guide* or see section 8.3 in
[*Liferay in Action*](http://manning.com/sezov) called, Hooks in action:
Inkwell’s shopping cart.

Your online store is set up, you have inventory, you have a payment system, and
you have sales rolling in. All is good. Some day there will be customers with
questions about their orders. Let's go over the orders next.

### Handling Orders

When you select an order from the Orders tab, you see a summary of the order
details along with some options along the bottom.

**Invoice:** creates a printer-friendly copy of the order that can be sent to a
customer.

**Resend Confirmation Email:** allows you to notify the customer that the order
has been received and is being processed.

**Send Shipping Email:** notifies the customer that the order is en route.

You can also add comments about the order and subscribe to the comments to get
any updates on the order.

![Figure 12.16: Say something nice about the invoice, please.](../../images/shopping-invoice.png)

Search for orders using these criteria: Number, Status, First Name, Last Name
and/or Email Address

<!--

#### Integrating the Amazon Rankings portlet

Portlet - Accepts ISBN numbers and displays corresponding Amazon Rankings.

Does the Amazon Rankings portlet only rank books (with certain sized ISBNs)?

Amazon Web Services#

Before using Amazon Rankings Portlet, you must setup your Amazon Web Services
key.

In portal.properties, here's the location to add your Amazon license key:

 ##
 ## Amazon License Keys
 ##
    #
    # Enter a list of valid Amazon license keys. Configure additional keys by
    # incrementing the last number. The keys are used following a Round-Robin
    # algorithm. This is made available only for personal use. Please see the
    # Amazons license at http://www.amazon.com for more information.
    #
    #amazon.license.0=
    #amazon.license.1=
    #amazon.license.2=
    #amazon.license.3=

Place the above inside portal-ext.properties.

You can get an Amazon License Key here:
https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/

Configuration#

You can specify which items to display by clicking on the configuration tab on
the upper right corner of the portlet.

Separate your ISBN numbers with a space.

Troubleshooting#

1. If you load Amazon Rankings Portlet, you might get a message that looks
similar to:

ERROR [WebCachePool:68] 0066620996 java.util.NoSuchElementException
 
Check if you've setup your Amazon Web Services key correctly in
portal-ext.properties.

Check to see if the ISBN you input actually exists.

2. As of Liferay 4.2.1, Amazon Rankings Portlet currently ranks books only. The
configuration will only take ISBN numbers in the 10-digit format.

3. If your Amazon Web Services key is not setup properly in Liferay, it will
also affect the Shopping Portlet when you try to 'Add Books'.
-->

Now that you know all about Liferay's Shopping application, why don't we check
the weather?

## Weather [](id=lp-6-1-ugen12-weather-0)

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

Whether you're going from Los Angeles to La Paz, Mumbai to Moscow, Harare to
Hong Kong or Dehli to Detroit you will always know the conditions on the ground
when you use Liferay's Weather application. For EE cutomers only, it is
available as a free download from Liferay's Marketplace.

To get the application, go to the Marketplace Store in the Control Panel. Under
the Apps menu select *Utility*. Select *See All* in the upper right corner of
the Utility window and view a summary of the application. Follow the download
instructions and the portlet will appear in your Application menu ready to
deploy.

![Figure 12.17: Is it raining at Grandma's house? You will know when you use the Weather portlet.](../../images/weather-portlet.png)

You can configure the portlet to display locations using the Preferences option.
The Guest Preferences option allows you to create a default set of locations any
user to your site can view. Locations can be entered as text or as a postal code
and are linked to weather.com for the latest conditions. The search feature
allows users to find cities not listed in the portlet. Temperature readings can
be displayed as Celsius or Fahrenheit.

Next, let's see what the Knowledge Base application has to offer.

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

![Figure 12.18: Knowledge Base Display Portlet](../../images/liferay-collaboration-kb-display-portlet.png)

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

![Figure 12.19: New Knowledge Base Article](../../images/liferay-collaboration-kb-new-article.png)

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

![Figure 12.20: Knowledge Base Permissions](../../images/liferay-collaboration-kb-permissions.png)

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

![Figure 12.21: Knowledge Base Control Panel Page](../../images/liferay-collaboration-kb-control-panel.png)

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

![Figure 12.22: Adding a New Template From the Control Panel](../../images/liferay-collaboration-kb-new-template.png)

Navigate back to the templates tab of the Knowledge Base page of the control
panel. You can perform the following actions on a template:

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

### Knowledge Base Article Portlet [](id=lp-6-1-ugen04-knowledge-base-article-portlet-0)

The Knowledge Base Article portlet can be placed on a page to display an entire
article. When you first place this portlet on a page it displays the message
*Please configure this portlet to make it visible to all users*. This message is
a link to the configuration dialog box for the portlet. Click *Select Article*
to choose an article to display. Pick an article and then click *Save*. When
your page refreshes it will display the article in the portlet.

![Figure 12.23: Knowledge Base Article Portlet](../../images/liferay-collaboration-kb-article-portlet.png)

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

![Figure 12.24: Knowledge Base Section Portlets](../../images/liferay-collaboration-kb-section-portlet.png)

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

![Figure 12.25: Knowledge Base Search Portlet](../../images/liferay-collaboration-kb-search-portlet.png)

You can also use the Categories Navigation portlet in conjunction with the
Knowledge Base display portlet. When both of these portlets are placed on a page
you can select a topic in the Categories Navigation portlet and the Knowledge
Base display portlet will show all of the articles that match the topic. You can
create topics from the Categories page of the Control Panel.

![Figure 12.26: Knowledge Base Category Navigation Portlet](../../images/liferay-collaboration-kb-catnavandkbdisplay-portlets.png)

You can select topics for articles when you are creating or editing them.

## Summary [](id=lp-6-1-ugen12-summary-0)

In this chapter, we examined two Liferay utility applications: the Software
Catalog and the Knowledge Base. The Software Catalog allows you to define a set
of software items to make available to visitors to your portal. Remember that
the Software Catalog will be replaced by Liferay Marketplace and will soon be
deprecated. The Knowledge Base applicaton is an EE-only application that allows
you to create articles and organize them into full books or guides that be
published on your portal. Next, let's take a tour of the Liferay Marketplace and
learn how to manage Liferay plugins.
