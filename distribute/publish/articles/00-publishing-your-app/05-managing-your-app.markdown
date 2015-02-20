# Managing Your Published App [](id=managing-your-published-app)

You've launched your new app on the Marketplace. Congratulations! As you settle
down from your launch celebration, you start to wonder how your app's doing in
the Marketplace. Is there a way to monitor how many people are viewing it and
downloading it? And what about those features that didn't make this release? How
difficult will it be to upload your app's next version with those features?
Don't sweat it. The Liferay Marketplace and app wizard are here to help. 

The Liferay Marketplace lets you track app performance. You can see its trends
with respect to views, downloads, and purchases. And the app wizard lets you
upload new versions of your app and it lets you make changes to some of its
details. In this article you'll learn how all of this works. 

## Tracking App Performance [](id=tracking-app-performance)

One of the main reasons for developing and publishing apps on the Marketplace
is to drive downloads and adoption of your app. The Marketplace enables you, as
the developer of your app, to get detailed reports about the number of views,
downloads, and purchases of your app(s). To access these metrics, navigate
to *Home* &rarr; *Metrics* (under *Development*). 

![Figure 1: The App Performance view in Marketplace lets you see how many times your apps has been viewed, downloaded, and purchased over a time interval.](../../images/marketplace-app-metrics-over-time.png) 

The view shown above is the default metrics view for a single app. Across the
top is a list of data series options (*Views*, *Downloads*, or *Purchases*).
Below that, a date range can be chosen. In the middle, a graph is shown for the
data within the date range. Finally, the same data that is graphed is also shown
in tabular format, in case you want to know the exact values making up the
graph. The different types of data available to view are described below.

### Views [](id=views)

When someone searches or browses the Marketplace, they click on apps to see
detailed views of the apps they're interested in. When this occurs for your app,
a *View* is recorded for the app, and this data is what is shown on the App
Metrics screen when *Views* is selected at the top. *Views* is also the default
view, as shown above. The number of recorded views per day per user is
unlimited.

### Downloads [](id=downloads)

A download is recorded for your app when someone downloads a specific package of
a specific version of your app. The number of recorded downloads per day per
user is unlimited.

### Purchases [](id=purchases)

The Marketplace makes an effort to count the total number of purchases for your
app. 

It's great that the Liferay Marketplace provides these app monitoring features.
As you see areas to improve your app or when necessary changes come up, you'll
want to know how to make changes to your published app. The next section
explains what changes you can make. 

## Making Changes to Published Apps [](id=making-changes-to-published-apps)

After your app is published and approved, you will undoubtedly need to make one
or more of these kinds of changes during the life of the app:

- Editing your app details (e.g., description, icon, etc.)
- Editing app prices
- Adding support for a new version of Liferay Portal
- Releasing a new version of your app to fix bugs or offer new functionality
- Disabling your apps

Liferay Marketplace supports all of the above operations as described below.

### Editing Your App Details [](id=editing-your-app-details)

App details include the name, description, icon, screenshots, and other
information that you supplied on the first screen during the app creation
process. To make changes to this content for your app, navigate to *Home* &rarr;
*Apps* and select the app you wish to edit. Click the *Edit* button to edit that
app. 

This screen shows you what the app looks like on the Marketplace. To edit the
detail information, click the *Edit* button at the bottom of the preview. This
allows you to edit details (as well as add new files to your existing version).
Note that the current values as they appear in your app are used to pre-fill the
form. Make any changes as needed on this screen and click *Next*. If you do not
need to edit any more variations, you can continue clicking *Next* until you
reach the final preview screen. Click *Submit for Review* to submit your detail
changes for review. Once approved, the changes you request appear on the
Marketplace.

### Editing App Prices [](id=editing-app-prices)

You can change your app's prices, instance bundles, and regional availability if
you wish. This can be for a variety of reasons, whether it's to run a
promotional offer, or to adjust your pricing model to better account for app
demand. To make changes to your app, navigate to *Company Profile Home* &rarr;
*Apps*, then click the app you wish to edit, and then at the bottom, click
*Edit* &rarr; *Pricing*. When you've finished editing your app's prices, click
*Next*. 

These changes do not require Liferay verification process to approve. Simply
make the changes you wish and save your app. These new prices will be reflected
immediately. 

### Adding Support for New Versions of Liferay Portal [](id=adding-support-for-new-versions-of-liferay-portal)

If you need to add files in support of another Liferay release, the process is
similar. Navigate to navigate to *Home* &rarr; *Apps* and select the app you
wish to edit. Click the *Edit* button to edit that app. Click *Next* to advance
past the details screen (making any changes as needed), and click *Next* to
advance past the version edit screen (you can't actually edit the version number
of an already-approved version, but you can edit the "What's New" information if
needed).

Once you advance past the version edit screen, you'll be at the File Upload
screen. This screen should look familiar--it's the same workflow used when you
initially created your app! The difference is that you can't edit pre-approved
files for specific Liferay releases. You can only add *new* files for a
different Liferay release (if you actually need to update existing files, you
must create a new version of the app--see the later section on adding versions
for details on how to do this).

Upload your new files (ensuring that your new plugins have updated compatibility
information: see the section on *Specify App Compatibility* for details on
versions), click *Next*, and observe the newly-added files listed at the
bottom of the preview screen. Click *Submit for Review* to submit your requested
change (adding of files). The files will be reviewed by Liferay, and once
approved, the new package becomes available for download in the Marketplace.

### Releasing a New Version of your App [](id=releasing-a-new-version-of-your-app)

As time passes, you may wish to add new functionality to your app or fix a
batch of bugs. This can be accomplished by releasing a new version of your app.
New versions offer your users new functionality and bug fixes, and users are
generally encouraged to always use the latest version. In addition, when a new
version of your app becomes available, existing users are notified automatically
through Liferay's notification system.

New versions of your apps are created in a similar fashion to creating new apps.
To add a new version, navigate to *Home* &rarr; *Apps* and select the app you
wish to edit. Click the *Edit* button to edit that app. You are taken to the
Details screen. At the bottom of the Details screen, click the *Add New Version*
button. This button begins the process of adding a new version, starting with
the App Details screen. In this case, the screen is pre-filled with data from
the current version of the app. 

You can make any changes to the pre-filled data on this screen. Since this is a
new version of an existing app, making major changes (such as completely
changing the name or description) might be unsettling to your existing users. It
is common that you'll want to upload new screenshots and refresh the icon. Note
that you cannot change the app owner (such as moving from a personally-developed
app to a company-developed app).

Clicking *Next* takes you through the same screens you've already seen. On the
*Add App Version* screen, you can specify a new version name for this version of
your app. Also, note that when adding new versions to an existing app, you have
the option to add *What's New* text. This typically lists changes for this
version, such as significant new features or bug fix information. Clicking *Next*
from here allows you to upload the files associated with the new version of the
app. For a new version of the app, you must upload all files for all supported
Liferay versions again, even if they have not changed since the last version.

### Deactivating Your App [](id=deactivating-your-app)

When the time comes to retire your app, you can *Deactivate* it. Deactivating an
app causes the app to become unavailable from the Marketplace for new
customers, and it doesn't appear in any public Marketplace listings. Existing
customers that have already downloaded your app can continue downloading the
legacy versions of the app they have already acquired, but they can't download
any versions they've not already received. The app remains in your inventory,
with all of its history, in case you need to re-activate or reference it in the
future.

To deactivate your app, navigate to *Home* &rarr; *Apps* and select the app you
wish to deactivate. Click the *Deactivate* button.

In this set of articles, we looked at how to create, publish, maintain, and
track Liferay Marketplace apps. You can do this through the App Manager that's
available on your profile [liferay.com](http://liferay.com) page
([liferay.com](http://liferay.com) account required!). Then, we covered the
requirements for publishing apps, which did not differ significantly from
requirements for general Liferay development. Next, we showed how you can
publish a sample app on the Marketplace and how you can modify it as the app
evolves. Finally, we looked at how to track the adoption of apps using view,
download, and install metrics.

## Related Topics [](id=related-topics)

[Beginning Liferay Development](/develop/learning-paths/-/knowledge_base/6-2/beginning-liferay-development)

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)

[Plugin Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
