# Creating Your Own Plugin Repository [](id=creating-your-own-plugin-repository)

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

## Software Catalog

You will want to use the Software Catalog if you will have multiple users
submitting portlets into the repository and if you don't want to worry about
creating the `liferay-plugin-repository.xml` file yourself.

+$$$

**Tip:** The Software Catalog plugin was
deprecated in Liferay 6.2 and is disabled by default. To activate it, navigate
to the Control Panel &rarr; *Plugins Configuration* and scroll through the list
of portlets until you find *Software Catalog*. Select *Software Catalog* and
click the *Active* checkbox. Then click *Save*.

$$$

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

### Generating the Software Catalog

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

### Benefits of the Software Catalog

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

## Manually Creating A Software Catalog

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

## Connecting to a Software Catalog

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
