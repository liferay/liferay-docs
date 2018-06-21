# General Settings

General settings provides a number of basic settings for your Site. These range from core configuration, like your Site's Membership Type, to finer details like Documents and Media indexing options.

## Details [](id=details)

*Details* is the leading option under the General tab, which provides
the same menu you filled out when first creating your Lunar Resort site. This
allows an administrator to change the description and membership type of a site.

### Membership Options

The membership type can be set as open, restricted, or private based on the
privacy needs of the site. Users can join and leave an open site at will. To
join a restricted site, a user has to be added by the site administrator. A user
can also request to be added through the Sites section of the Control Panel. A
private site is like a restricted site but doesn't appear in the Sites section
of the Control Panel for users who aren't members.

### Site Hierarchies

You also have the ability to organize sites into hierarchies. At the bottom of
the Details sub-section is the Parent Site section. This feature allows you to
select the parent site for the site you're currently on. After selecting a
parent site, you have a checkbox option to limit membership to members of the
parent site.


## Pages

Under Pages you can view the Public Pages or Private Pates of your site, if any 
exist. If they don't exist, you are provided with a *Site Templates* selector 
that you can use to quickly build out pages with a Site Template.

![Figure X: Selecting a Site Template.](../../../../images/selecting-site-template.png)

## Categorization [](id=categorization)

*Categorization* helps administrators organize the site and allows for users to 
easily find your site and its content through search and navigation. For more 
information on using tags and categories, visit the
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories) section.

## Site URL [](id=site-url)

The *Friendly URL* option lets you manage the path to your site in the 
instance's URL. Friendly URLs are used for both public and private pages. For 
public pages, the friendly URL is appended to http://localhost:8080/web. For 
private pages, it is appended  to http://localhost:8080/group. Each friendly 
URL needs to be unique.

For example, if you set the friendly URL of your instance's default site to 
/lunar-resort, the URL of your default site's public home page would change to 
http://localhost:8080/web/lunar-resort/home. If your instance's default site had
private pages, the URL of the default private home page would change to
http://localhost:8080/group/lunar-resort/home.

Note that if you're adding a friendly URL for your instance's home page, you
should update your instance's Home URL field so that page requests to
http://localhost:8080 redirect properly. To do this:

1.  Go to *Configuration* &rarr; *Instance Settings* in the Control Panel.

2.  Under *Navigation*, in the Home URL field enter your home URL (i.e. */web/lunar-resort/home*).

    Once you've entered this setting, page requests to localhost:8080 will 
    redirect to the friendly URL of your Liferay instance's new homepage.

The other setting you can configure under the Site URL tab is *Virtual Hosts* 
which connects a domain name to a site. You can use this to define a domain 
name (i.e., www.lunar-resort.com) for your site. This can be a full domain or a 
subdomain. You can use this to host a number of web sites as separate sites on 
one Liferay server.

For instance, if you set this up for the Lunar Resort's development network,
users in that site would access *developers.lunar-resort.com*,
provided that the Lunar Resort instance's network administrators created the
domain name and pointed it to the Liferay server.

To set this up:

1.  With your provider, set the DNS name *developers.lunar-resort.com* to point 
    to your Liferay instance's IP address.

2.  In the Virtual Host tab for the Developers site, set the URL to
    *http://developers.lunar-resort.com*

This helps users quickly access their site without having to recall an extended 
URL. The *Site URL* option is listed under the General tab.

## Documents and Media [](id=documents-and-media)

The last option that relates to organizing your site's content is *Documents and
Media*, which is found under General. This lets you enable/disable Directory
Indexing, which allows site administrators to browse your site's documents and
media files and folders. For example, a site administrator of a site called
*Lunar Resort* can browse documents at
http://localhost:8080/documents/lunar-resort if this option is enabled.


## Site Template [](id=site-template)

If you created your site using a Site Template, this section will appear and 
display information about the link between the site template and the site. 
Specifically, you can see which site template was used and whether or not it 
allows modifications to the pages inherited from it by site administrators. To 
learn more about site templates and how to create your own, see the [Building Sites from Templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates)
section.


## Custom Fields [](id=custom-fields)

*Custom Fields* lets you edit the custom fields you already have configured
for the Site resource. If you don't have any custom fields configured for the
Site resource, you can navigate to the Control Panel &rarr; *Custom Fields*
located under the *Configuration* tab. The Custom Fields tab does not display in
Site Settings unless you have existing custom fields. For more information on
Custom Fields, see the
[Custom Fields](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/custom-fields)
section.
