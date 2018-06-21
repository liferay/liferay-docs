# Configuring the Recycle Bin [](id=configuring-the-recycle-bin)

The Recycle Bin supports instance-wide scope or site-specific scope. The
instance-wide scope of the Recycle Bin is set by adding the `trash.enabled`
property to your `portal-ext.properties` file. By default, the Recycle Bin is
enabled instance-wide. You'll go into more detail for adding this property and
several others to your properties file later in the section. First, you'll
explore the UI and see what the Recycle Bin can do.

First, you'll configure the Recycle Bin for site-specific scoping.

1.  Choose the Site you'd like configure for the Recycle Bin from the Site 
    Administration menu.

2.  Click *Configuration* &rarr; *Site Settings*.

3.  Next, select the top *Advanced* tab and click *Recycle Bin*. You'll notice 
    a few configurable options.

    **Enable Recycle Bin:** enable and disable settings for the Recycle Bin's
    site-specific scope.

    **Trash Entries Max Age:** customize the number of minutes a file is kept 
    in the Recycle Bin until its permanent deletion (default is 43200 minutes, 
    or 30 days).

    ![Figure 1: The Recycle Bin offers several configurable options for your site.](../../../images/recycle-bin-site-settings.png)

4.  When you've finished configuring your Recycle Bin settings, click *Save*.

+$$$

**Note:** If you disable the Recycle Bin while it's still holding recycled
items, the recycled items remain stored and reappear in the Recycle Bin if it is
re-enabled.

$$$

You can also configure the Recycle Bin via properties in the `portal.properties`
file. Remember that it's a best practice not to edit the `portal.properties`
directly, but to create a separate `portal-ext.properties` file containing the
properties to override. There are some additional options not available in the
GUI that you can set:

`trash.search.limit=500`: set the limit for results used when performing
searches in the Recycle Bin (default is 500).

`trash.entry.check.interval=60`: set the interval in minutes for how often the
trash handler runs to delete trash entries that have been in the Recycle Bin
longer than the maximum age (default is 60).

Also, as was mentioned earlier, there are properties to enable the Recycle bin
instance-wide and set trash entries' maximum age.

`trash.enabled=true`: set this property to *false* to disable the Recycle Bin
for all sites in the portal (default is *true*).

`trash.entries.max.age=43200`: set the number of minutes trash entries should be
held before being permanently deleted.

Visit the
[portal.properties](https://docs.liferay.com/portal/7.1/propertiesdoc/portal.properties.html#Trash)
file to view all of the configurable properties for the Recycle Bin.

Next, you should make sure permissions are set properly for users who can
handle/view the assets in the Recycle Bin. Users who had *View* permissions on
a document when it was recycled can also view that document in the Recycle
Bin. Users who had *Update* or *Delete* permissions on a document when it was
recycled can restore the document. 

Now that you've successfully configured the Recycle Bin, you'll look at how to
use it.
