# Advanced Site Settings

Advanced Settings are settings that relate to security (like User Roles) or 
that require some kind of external configuration (like creating a Google 
Analytics account) in order to use.

### Default User Associations [](id=default-user-associations)

*Default User Associations* is the leading option when opening the Advanced 
tab. It is for configuring site roles and teams that newly assigned site 
members will have by default. If you'd like to learn more about creating roles 
and/or teams, visit the [Roles and Permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions)
and [Creating Teams for Advanced Site Membership Management](/discover/portal/-/knowledge_base/7-1/creating-teams-for-advanced-site-membership-management)
sections, respectively.

## Analytics [](id=analytics)

@product@ includes built-in support to use Google Analytics for analyzing 
traffic on your site. Google Analytics provides a snippet of code is provided 
which you add to your pages enable tracking. Adding this code to every page on 
a site would be tedious, especially if it's a large site with a lot of 
user-generated content.

There are two ways to mitigate this problem:

1.  A web developer can hardcode the tracking code into a theme, and every page 
    that uses the theme will have it embedded.

2.  An administrator can enter the tracking code in site settings.

To use options #2:

1.  Go to *Site Settings* &rarr; *Advanced*.

2.  Expand the *Analytics* section.

3.  Enter your Google Analytics ID.

4.  Click *Save*.

    All the pages in the site you selected will now have the Google Analytics 
    code in them and will be tracked. 

![Figure X: To set up Google Analytics: sign up, receive an ID, and then enter it into the Google Analytics ID field.](../../../../images/maintaining-google-analytics.png)


To enable a different analytics service:

1.  Go to *Configuration* in the Control Panel.

2.  Go to *Instance Settings* &rarr; *Miscellaneous*.

3.  Enter the name of any additional service you want to add in the *Analytics*
    field provided.

4.  Once you have entered the name, go to the *Site Settings* &rarr; *Advanced* 
    &rarr; *Analytics* page for the site where you wish to add analytics.
    
5.  Copy the JavaScript tracking code provided by your analytics platform into 
    corresponding field for your service.

    Now all pages on the selected site contain the tracking script and will 
    send analytics data to your analytics platform. 

This procedure lets you use any tool you want to visualize your site's traffic.

## Maps [](id=maps)

The *Maps* options lets you configure the maps API provider used by your Liferay
instance when displaying geolocalized assets. Geolocalized assets can be
displayed for documents, web content articles, DDL records, etc. Maps is
available under the Advanced tab. You can read more about Geolocation in [Geolocating Assets](/discover/portal/-/knowledge_base/7-1/geolocating-assets).


### Recycle Bin [](id=recycle-bin)

The *Recycle Bin* option under the Advanced tab provides the option to
enable/disable the Recycle Bin for your site. You can also regulate the age (in
minutes) for which content is able to be stored in the Recycle Bin until it is
permanently deleted. For a full explanation of the Recycle Bin, see the
[Restoring Deleted Assets](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets)
section.

### Content Sharing [](id=content-sharing)

If you select the *Content Sharing* tab from the Advanced tab, you can
configure whether sub-sites can display content from this site. Administrators
of this site's sub-sites can use all structures, templates, categories,
application display templates, etc. from this parent site. Even if you
initially allowed content sharing between the parent site and its sub-sites,
you're able to disable this option and immediately revoke content sharing from
all sub-sites.
