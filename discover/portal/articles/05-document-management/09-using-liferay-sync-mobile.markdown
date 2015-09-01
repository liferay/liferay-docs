# Using Liferay Sync on Your Mobile Device [](id=using-liferay-sync-on-your-mobile-device)

Liferay Sync for Android and iOS contains most of the desktop Sync client's 
functionality. The main difference is that the mobile client can only be 
connected to a single account at a time. Also, mobile Sync doesn't automatically 
download files to your device. Storage space on mobile devices is typically 
limited, so this saves precious space on your device. The Sync mobile app 
instead lets you download only the files you want to work with. As with the Sync 
desktop clients, the latest versions of Sync on Android and iOS have been 
redesigned to provide a consistent user experience across platforms. While this 
section details using Sync on Android, the instructions also apply to Sync 
on iOS. 

Installing Sync on your Android or iOS device is done through each platform's 
app store, the same as any other mobile app. The quickest way to find the Sync 
mobile app is to search the App Store or Google Play for Liferay. You can also 
download it from the [Liferay Sync downloads page](https://www.liferay.com/downloads/liferay-sync). 
Once you've installed the Sync app on your device, follow the instructions below 
to learn how to use it. 

## Connecting to Your Portal [](id=connecting-to-your-portal)

When Liferay Sync first starts on your mobile device, press the *Get Started* 
button to begin setting up the app. The next screen asks for your login 
credentials and your portal's address. Once you enter them, press *Sign In*. 
After signing in, the app takes you to a panel that shows your name, a gear icon 
for accessing the app's settings, *My Sites*, and *My Documents*. My Sites and 
My Documents encompass the sites you're permitted to sync with in your portal. 
My Documents is your personal user site, while My Sites shows the other sites 
available. No matter how deep you are in the folder hierarchy of a site, swiping
to the right returns you to the panel. If you're in the first level of My Sites
or My Documents, pressing the location bar at the top slides the screen slightly
to the right to reveal a compact view of the panel. The following screenshots
show both views of the panel.

![Figure 5.26: This panel lets you access the app's settings, as well as your sites and documents.](../../images/sync-mobile-panel.png)

![Figure 5.27: Tapping the title bar at the top of My Sites or My Documents opens the main Sync panel's compact view.](../../images/sync-mobile-panel-compact.png)

Press the gear icon to access the app's settings. Settings shows your account 
information and gives you the option to sign out of your portal. Settings also 
lets you toggle *Security Mode*. Security Mode protects files that are stored on 
your device by encrypting them. You therefore need to set up a passcode for the 
app when using Security Mode. The passcode is then used to access the Sync app 
and decrypt your files. This protects the files on your device and portal in the 
event your device is lost or stolen. However, you should note that enabling 
Security Mode causes downloading and opening files to take slightly longer than 
usual. Your Liferay server must also use SSL; otherwise your files are 
transmitted in the open. Below the Security Mode toggle, the app's version is 
listed as well as a link to send Liferay feedback on the app.

![Figure 5.28: The Settings screen for the Sync app lets you sign out of your portal, enable Security Mode, view the app's version, and send feedback.](../../images/sync-mobile-settings.png)

Great! Now that you know how to connect to your portal and find your sites, you 
can get started managing the files and folders in those sites.

## Managing Files and Folders [](id=managing-files-and-folders)

Managing files and folders is the same whether you're working in My Documents or 
My Sites. Pressing a site or folder shows you a list of its contents. Each file 
is labeled with its size and modification date. You can refresh the list by 
pulling down from the top of the screen. Your current location in the navigation 
hierarchy also appears at the top of the screen alongside a plus icon. Pressing 
the plus icon launches an upload screen that lets you add content in the current 
location. You can add a new folder, upload a file, and launch your device's 
camera app to take and upload a picture or video. Pressing the *X* icon on the 
upload screen's top right corner returns you to the list of items for that 
location without taking any action.

![Figure 5.29: Sync shows files and folders in a list.](../../images/sync-mobile-site.png)

To download a file to your device, press the file in this list. The label 
indicating the file's size and modification date then changes to a download 
progress indicator. When the file finishes downloading, your device 
automatically opens it in the app you've configured to open files of that type. 
If you haven't configured your device to use a specific app for that file type, 
you're presented with a list of apps on your device that can open the file. If 
your device doesn't have an app that can open the file, Sync tells you to 
install one that can. Downloaded files appear in the list with the file size in 
blue instead of gray. For example, the screenshot below shows that 
`LiferayinAction.pdf` has been downloaded to the device.

![Figure 5.30: Downloaded files appear in the list with their size in blue.](../../images/sync-mobile-file-downloaded.png)

The Sync mobile app also lets you move, rename, and delete files and folders. To 
the right of each file and folder in the list is a circle icon with three dots. 
Pressing this icon slides open a context menu to the right that lets you move, 
rename, or delete that item. The screenshots below show these options. Note that 
you should use **extreme caution** when deleting files or folders. Deleting 
files or folders in the mobile Sync app also deletes them from your portal and 
across any synced clients. Accidentally deleted files can be restored from the 
portal's Recycle Bin, which is enabled by default. However, you should note that 
file recovery isn't possible if the portal or site administrator has disabled 
the Recycle Bin. 

What if you want to delete a file on your device without also deleting it 
in the portal? Currently, the only way to do this is to sign out of your account 
in the app's Settings menu. Doing so removes any downloaded files from your 
device, but preserves them in the portal. However, if you're on Android then 
it's possible to use a system file browser app to manually remove any downloaded 
files. 

+$$$

**Warning:** Deleting a file in the mobile Sync app deletes it in the portal and
across any synced clients. If a file is accidentally deleted, you can restore it
from the portal's Recycle Bin. The Recycle Bin is enabled by default.  However,
file recovery isn't possible if the portal or site administrator has disabled
the Recycle Bin. 

$$$

The context menu also provides additional options for files. The file's version 
in the portal is indicated by a small badge on the file icon's top right corner. 
You can also share files from the context menu if you've downloaded them to your 
device. Pressing the *Share* icon opens a list of apps on your device that are 
capable of sharing the file. To close the context menu and return to the list of 
files and folders, swipe to the right. The following screenshot shows the 
options available in the context menu for files. 

![Figure 5.31: The badge on the file's icon shows the file's version in the portal. You can also share files that you've downloaded to your device.](../../images/sync-mobile-file-actions.png)

Awesome! You've now seen how Liferay Sync keeps you up to date with the files on 
your Liferay sites. Any new or modified files on your sites are automatically 
propagated to your desktop and mobile environments. This lets you view and work 
with your files from the environment and location that you choose. What's more, 
other users you collaborate with can also use Sync to get your latest 
contributions. This way, entire teams can always work with the most recent 
information. 
