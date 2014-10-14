# Making changes to published apps [](id=making-changes-to-published-apps)

After your app is published and approved, you will undoubtedly need to make one
or more of these kinds of changes during the life of the app:

- Editing your app details (e.g. description, icon, etc)
- Adding support for a new version of Liferay Portal
- Releasing a new version of your app to fix bugs or offer new functionality
- Disabling your apps

Liferay Marketplace supports all of the above operations as described below.

## Editing your app details [](id=editing-your-app-details)

App details include the name, description, icon, screenshots, and other
information that you supplied on the first screen during the app creation
process. To make changes to this content for your app, navigate to *Home* &rarr;
*App Manager* &rarr; *Apps*, then click the *Action* button next to the app you
wish to edit, and select *Edit*.

![Figure 15.9: Editing an app is as simple as navigating to it and clicking *Edit*.](../../images/marketplace-edit-app-details.png) 

This screen shows you what the app looks like on the Marketplace. To edit the
detail information, click the *Edit* button at the bottom of the preview. This
allows you to edit details (as well as add new files to your existing version).
Note that the current values as they appear in your app are used to pre-fill the
form. Make any changes as needed on this screen, and click *Next*. If you do not
need to edit any more variations, you can continue clicking *Next* until you
reach the final preview screen. Click *Submit for Review* to submit your detail
changes for review. Once approved, the changes you request appear on the
Marketplace.

## Adding support for new versions of Liferay Portal [](id=adding-support-for-new-versions-of-liferay-portal)

If you need to add files in support of another Liferay release, the process is
similar. Navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the
*Action* button next to the app for which you wish to add new files, and click
*Edit*. Click *Next* to advance past the details screen (making any changes as
needed), and click *Next* to advance past the version edit screen (you can't
actually edit the version number of an already-approved version, but you can
edit the "What's New" information if needed).

Once you advance past the version edit screen, you'll be at the File Upload
screen. This screen should look familiar--it's the same workflow used when you
initially created your app! The difference is that you can't edit pre-approved
files for specific Liferay releases. You can only add *new* files for a
different Liferay release (if you actually need to update existing files, you
must create a new version of the app--see the later section on adding versions
for details on how to do this).

Upload your new files (ensuring that your new plugins have updated compatibility
information, see the section on *Specify App Compatibility* for details on
versioning), click *Next*, and observe the newly-added files listed at the
bottom of the preview screen. Click *Submit for Review* to submit your requested
change (adding of files). The files will be reviewed by Liferay, and once
approved, the new package is available for download in the Marketplace.

## Releasing a new version of your app [](id=releasing-a-new-version-of-your-app)

After time passes, you may wish to add new functionality to your app or fix a
batch of bugs. This can be accomplished by releasing a new version of your app.
New versions offer your users new functionality and bugfixes, and users are
generally encouraged to always use the latest version. In addition, when a new
version of your app becomes available, existing users are notified automatically
through Liferay's notification system.

New versions of your apps are created similarly to the way the initial version
was. To add a new version, navigate to *Home* &rarr; *App Manager* &rarr;
*Apps*. Click the *Action* button next to the app for which you wish to add a
new version, and then click *Edit*. You will be taken to the Details screen. At
the bottom of the Details screen, click the *Add New Version* button. This
button begins the process of adding a new version, starting with the App Details
screen. In this case, the screen is pre-filled with data from the current
version of the app, as shown below.

![Figure 15.10: Adding a version is similar to creating a new app, except that the fields are filled in for you.](../../images/marketplace-add-version-details.png) 

You can make any changes to the pre-filled data on this screen. Since this is a
new version of an existing app making major changes (such as completely changing
the name or description) might be unsettling to your existing users. It is
common that you'll want to upload new screenshots and refresh the icon. Note
that you cannot change the app owner (such as moving from a personally-developed
app to a company-developed app).

Clicking *Next* takes you through the same screens you've already seen. On the
*Add App Version* screen, you can specify a new version name for this version of
your app. Also, note that when adding new versions to an existing app, you have
the option to add *What's New* text. This is typically filled in with a list of
changes for this version, such as significant new features or bugfix
information. Clicking *Next* from here allows you to upload the files associated
with the new version of the app. For a new version of the app, you must upload
all files for all supported Liferay versions again, even if they have not
changed since the last version.

## Deactivating your app [](id=deactivating-your-app)

When the time comes to retire your app, you can *Deactivate* it. Deactivating an
app causes the app to no longer be downloadable from the Marketplace for new
customers and it won't appear in any public Marketplace listings. Existing
customers that have already downloaded your app can continue downloading the
legacy versions of the app they have already acquired, but they can't download
any versions they've not already received. The app remains in your inventory,
with all of its history, in case you need to re-activate or reference it in the
future.

To deactivate your app, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*,
click on the *Actions* button next to the app for which you want deactivate, and
select the *Deactivate* action.
