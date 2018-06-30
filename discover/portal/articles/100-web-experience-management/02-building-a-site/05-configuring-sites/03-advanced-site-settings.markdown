# Advanced Site Settings [](id=advanced-site-settings)

Advanced Settings relate to security (like User Roles) or require external
configuration (like creating a Google Analytics account) to use.

### Default User Associations [](id=default-user-associations)

*Default User Associations* configures Site roles and teams that newly assigned Site 
members have by default. If you'd like to learn more about creating roles 
and/or teams, visit the [Roles and Permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions)
and [Creating Teams for Advanced Site Membership Management](/discover/portal/-/knowledge_base/7-1/creating-teams-for-advanced-site-membership-management).

## Analytics [](id=analytics)

@product@ includes built-in support for Google Analytics for analyzing 
traffic on your Site. Google Analytics provides a snippet of code which you add
to your pages enable tracking. Adding this code to every page on a Site would
be tedious, especially if it's a large Site with a lot of user-generated
content.

There are two ways to mitigate this problem:

1.  A web developer can hard-code the tracking code into a theme, which embeds
    it on every page.

2.  An administrator can enter the tracking code in Site settings.

To use option #2:

1.  Go to *Site Settings* &rarr; *Advanced*.

2.  Expand the *Analytics* section.

3.  Enter your Google Analytics ID.

4.  Click *Save*.

    All the pages in the Site you selected now have the Google Analytics code
    and can be tracked. 

![Figure 1: To set up Google Analytics: sign up, receive an ID, and then enter it into the Google Analytics ID field.](../../../../images/maintaining-google-analytics.png)

To enable a different analytics service:

1.  Go to *Configuration* in the Control Panel.

2.  Go to *Instance Settings* &rarr; *Miscellaneous*.

3.  Enter the name of any additional service you want to add in the *Analytics*
    field provided.

4.  Once you have entered the name, go to the *Site Settings* &rarr; *Advanced* 
    &rarr; *Analytics* page for the Site where you wish to add analytics.
 
5.  Copy the JavaScript tracking code provided by your analytics platform into 
    the corresponding field for your service.

Now all pages on the selected Site contain the tracking script and send
analytics data to your analytics platform. 

## Maps [](id=maps)

The *Maps* option configures the maps API provider used by your Liferay
instance when displaying geolocalized assets. Geolocalized assets can be
displayed for documents, web content articles, DDL records, etc. Maps is
available under the Advanced tab. You can read more about Geolocation in [Geolocating Assets](/discover/portal/-/knowledge_base/7-1/geolocating-assets).

### Recycle Bin [](id=recycle-bin)

The *Recycle Bin* option enables or disables the Recycle Bin for your Site. You
can also regulate the age (in minutes) for which content is able to be stored
in the Recycle Bin until it is permanently deleted. For a full explanation of
the Recycle Bin, see [Restoring Deleted Assets](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets).

### Content Sharing [](id=content-sharing)

If you select the *Content Sharing* tab from the Advanced tab, you can
configure whether sub-Sites can display content from this Site. Administrators
of this Site's sub-Sites can use all structures, templates, categories,
application display templates, and more from this parent Site. Even if you
initially allowed content sharing between the parent Site and its sub-Sites,
you can disable this option and immediately revoke content sharing from
all sub-Sites.
