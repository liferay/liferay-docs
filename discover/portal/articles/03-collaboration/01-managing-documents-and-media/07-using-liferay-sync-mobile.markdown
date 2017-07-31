# Using Liferay Sync on Your Mobile Device [](id=using-liferay-sync-on-your-mobile-device)

Liferay Sync for Android and iOS contains most of the
[desktop Sync client](/discover/portal/-/knowledge_base/6-2/using-liferay-sync-on-your-desktop)'s
functionality. The mobile client can, however, only be connected to one 
@product@ instance account at a time. Also, mobile Sync doesn't automatically 
download files to your device. To save precious storage space on your device, 
the Sync mobile app lets you choose the files you want to work with. As with the 
Sync desktop clients, the latest versions of Sync on Android and iOS provide a 
consistent user experience across platforms. While this article details using 
Sync on Android, the instructions also apply to Sync on iOS. 

You need to download and install Sync on your Android or iOS device through its 
respective app store, the same as you do any other mobile app. To find the app, 
search Google Play or the App Store for *Liferay*. You can also download Sync 
from the 
[Liferay Sync downloads page](https://www.liferay.com/downloads/liferay-sync). 
Once you've installed the Sync app on your device, follow the instructions below 
to learn how to use it. 

## Connecting to Your @product@ Instance [](id=connecting-to-your-portal)

When Liferay Sync first starts on your mobile device, press the *Get Started*
button to begin setup. The setup screen asks for your login credentials and your
@product@ instance's address. Once you enter them, press *Sign In*. After 
signing in, the app takes you to a panel that shows your name, a gear icon for 
accessing the app's settings, and navigation options *My Sites* and *My 
Documents*. My Sites and My Documents encompass the sites in your @product@ 
instance that you can sync with. My Documents is your personal user site, while 
My Sites shows the other sites with which you can sync. No matter how deep you 
are in the folder hierarchy of a site, swiping to the right returns you to this 
panel. If you're in the first level of My Sites or My Documents, pressing the 
location bar at the top slides the screen slightly to the right to reveal a 
compact view of the panel. The following screenshots show both views of the 
panel. 

![Figure 1: This panel lets you access the app's settings, as well as your sites and documents.](../../../images/sync-mobile-panel.png)

![Figure 2: Tapping the title bar at the top of My Sites or My Documents opens the main Sync panel's compact view.](../../../images/sync-mobile-panel-compact.png)

Press the gear icon to access Sync's settings. Settings shows your account
information and an option to sign out of your @product@ instance. Settings also 
lets you toggle *Security Mode*. Security Mode protects files stored on your 
device by encrypting them. Using Security Mode requires you to set up a passcode 
to use when accessing the Sync app. Security Mode protects the files on your 
device and @product@ instance in the event your device is lost or stolen. You 
should note, however, downloading and opening files in Security Mode takes 
slightly longer than usual because the @product@ server must use SSL--if it 
didn't, your files would be transmitting in the open. Below the Security Mode 
toggle are the app's version and a link to send app feedback to Liferay. 

![Figure 3: The Settings screen for the Sync app lets you sign out of your @product@ instance, enable Security Mode, view the app's version, and send feedback.](../../../images/sync-mobile-settings.png)

Great! Now that you know how to connect to your @product@ instance and find your 
sites, you can get started managing the sites' files and folders. 

## Managing Files and Folders [](id=managing-files-and-folders)

Whether you're working in My Documents or My Sites, you manage files and folders
the same way. Pressing a site or folder shows you a list of its files and
folders. It displays each file's size and modification date. You can refresh the
list by pulling down from the top of the screen. Your current location in the
navigation hierarchy also appears at the top of the screen alongside a plus
icon. Pressing the plus icon launches an upload screen that lets you add content
in the current location. You can add a new folder, upload a file, or launch your
device's camera app to take and upload a picture or video. Pressing the *X* icon
on the upload screen's top right corner cancels any action and returns you to
the current file list. 

![Figure 4: Sync shows files and folders in a list.](../../../images/sync-mobile-site.png)

To download a file to your device, press the file's name in the list. The label
that previously showed the file's size and modification date is replaced by a
download progress indicator. When the file finishes downloading, your device
automatically opens it in the app you've configured to open files of that type.
If you haven't configured your device to use a specific app for that file type,
you're presented with a list of apps on your device that can open the file. If
your device doesn't have an app that can open the file, Sync tells you to
install one that can. Downloaded files appear in the list with the file size in
blue instead of gray. For example, the screenshot below shows that
`LiferayinAction.pdf` is on the device. 

![Figure 5: Downloaded files appear in the list with their size in blue.](../../../images/sync-mobile-file-downloaded.png)

The Sync mobile app also lets you move, rename, and delete files and folders. To 
the right of each file and folder in the list is a circle icon with three dots. 
Pressing this icon slides open a context menu on the right that lets you move, 
rename, or delete that item. The screenshots below show these options. Note that 
you should use **extreme caution** when deleting files or folders. Deleting 
files or folders in the mobile Sync app also deletes them from your @product@ 
instance and across any synced clients. Accidentally deleted files can be 
restored from the instance's Recycle Bin, which is enabled by default. If the 
instance or site administrator disables the Recycle Bin, however, recovering 
deleted files is impossible. 

What if you want to delete a file on your device without also deleting it in the
instance? Currently, you can only do this by signing out of your account in the
app's Settings menu. Doing so removes all downloaded files from your device, but
preserves them in the instance. If you're on Android, it may be possible to use 
a system file browser app to manually remove downloaded files. 

+$$$

**Warning:** Deleting a file in the mobile Sync app deletes it in the @product@ 
instance and across any synced clients. If you accidentally delete a file, the 
instance or site administrator can restore it from the instance's Recycle Bin. 
The Recycle Bin is enabled by default. If the instance or site administrator 
disables the Recycle Bin, however, recovering deleted files is impossible. 

$$$

The context menu also provides additional options for files. A small badge on
the file icon's top-right corner indicates the file's version in the @product@ 
instance. You can also use the context menu to share files you've downloaded. 
Pressing the *Share* icon opens a list of your device's apps capable of sharing 
the file. To close the context menu and return to the list of files and folders, 
swipe to the right. The following screenshot shows the options available in a 
file's context menu. 

![Figure 6: The badge on the file's icon shows the file's version in the @product@ instance. You can also share files that you've downloaded.](../../../images/sync-mobile-file-actions.png)

Awesome! You've seen how Liferay Sync keeps you up to date with the files on
your @product@ sites. Any new or modified files on your sites are automatically
propagated to your desktop and mobile environments. This lets you view and work
with your files from within those environments. What's more, other users you
collaborate with can also use Sync to get your latest contributions. This way,
entire teams can always work with the latest version of every document. 
