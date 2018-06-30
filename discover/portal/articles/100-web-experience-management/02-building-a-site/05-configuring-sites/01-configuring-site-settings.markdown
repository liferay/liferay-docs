# General Settings [](id=configuring-site-settings)

General settings range from core configuration, like your Site's Membership
Type, to finer details like Documents and Media indexing options.

## Details [](id=details)

*Details* provides the same menu you filled out when first creating your Site.
This allows an administrator to change the description and membership type of
a Site.

### Membership Options [](id=membership-options)

The membership type can be set as open, restricted, or private based on the
privacy needs of the Site. Users can join and leave an open Site at will. To
join a restricted Site, users must be added by the Site administrator, but they
can request membership through the Sites section of the Control Panel.
A private Site works like a restricted Site but is hidden from users who aren't
members.

### Site Hierarchies [](id=site-hierarchies)

Sites can be organized into hierarchies. At the bottom of the Details
sub-section is the Parent Site section. When you select the parent Site for the
Site you're currently on, a checkbox appears for limiting membership to members
of the parent Site.

## Pages [](id=pages)

Under Pages you can view your Site's Public or Private Pates, if any exist. If
they don't exist, a *Site Templates* selector appears for creating pages with
a Site Template.

![Figure 1: Selecting a Site Template.](../../../../images/selecting-site-template.png)

## Categorization [](id=categorization)

*Categorization* helps administrators organize the Site and allows for users to 
easily find your Site and its content through search and navigation. For more 
information on using tags and categories, visit the
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories) section.

## Site URL [](id=site-url)

The *Friendly URL* option lets you set your Site's URL paths. Friendly URLs are
used for both public and private pages. The public Site base URL is
`https://localhost:8080/web`, and the private one is
`https://localhost:8080/group`. Each friendly URL must be unique.

For example, setting the friendly URL of your default Site to `/lunar-resort`
makes your Site's public home page's URL
`https://localhost:8080/web/lunar-resort/home`. The private Site's URL is thus
`https://localhost:8080/group/lunar-resort/home`.

Note that if you add a friendly URL for your instance's home page, you
should update your instance's Home URL field so that page requests to
`http://localhost:8080` redirect properly: 

1.  Go to *Configuration* &rarr; *Instance Settings* in the Control Panel.

2.  Under *Navigation*, in the Home URL field enter your home URL (i.e. */web/lunar-resort/home*).

Once you've entered this setting, page requests to `localhost:8080` redirect
to the friendly URL of your Liferay instance's new home page.

You can also configure Virtual Hosts, which connects a domain name to a Site, under *Site URL*. You can use this to define a domain name (i.e., 
`www.lunar-resort.com`) for your Site. This can be a full domain or a 
subdomain. You can use this to host a number of web sites as separate Sites on 
one Liferay server.

![Figure 2: When configuring virtual hosts, the public and private pages of a site can be configured to different domains.](../../../../images/settting-virtual-hosts.png)

For instance, if you set this up for the Lunar Resort's development network,
users in that Site would access `developers.lunar-resort.com`,
provided that the Lunar Resort instance's network administrators created the
domain name and pointed it to the Liferay server.

1.  With your provider, set the DNS name *developers.lunar-resort.com* to point 
    to your Liferay instance's IP address.

2.  In the Virtual Host tab for the Developers Site, set the URL to
    *http://developers.lunar-resort.com*

This helps users quickly access their Site without having to recall an extended 
URL. The *Site URL* option is listed under the General tab.

## Documents and Media [](id=documents-and-media)

The last option is *Documents and Media*, which lets you enable/disable
Directory Indexing. If on, Site administrators can browse your Site's
Documents and Media files and folders. For example, a Site administrator of
a Site called *Lunar Resort* can browse documents at
`http://localhost:8080/documents/lunar-resort` if this option is enabled.

## Site Template [](id=site-template)

If you created your Site using a Site Template, this section appears and
displays information about the link between the Site Template and the Site.
Specifically, you can see which Site Template was used and whether or not it
allows modifications to the pages inherited from it by Site administrators. To
learn more about Site Templates and how to create your own, see 
[Building Sites from Templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates).

## Custom Fields [](id=custom-fields)

*Custom Fields* only appears if you've created them in Control Panel &rarr;
*Configuration* &rarr; *Custom Fields*. For more information on Custom Fields,
see 
[Custom Fields](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/custom-fields).
