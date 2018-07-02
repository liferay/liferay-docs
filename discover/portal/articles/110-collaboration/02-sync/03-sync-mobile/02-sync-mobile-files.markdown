# Managing Files and Folders in Liferay Sync Mobile [](id=managing-files-and-folders-in-liferay-sync-mobile)

Whether you're working in My Documents or My Sites, you manage files and folders
the same way. Pressing a Site or folder shows you a list of its files and
folders. It displays each file's size and modification date. You can refresh the
list by pulling down from the top of the screen. Your current location in the
navigation hierarchy also appears at the top of the screen alongside a plus
icon. Pressing the plus icon launches an upload screen for adding content in the
current location. You can add a new folder, upload a file, or launch your
device's camera app to take and upload a picture or video. Pressing the *X* icon
on the upload screen's top right corner cancels any action and returns you to
the current file list. 

![Figure 1: Sync shows files and folders in a list.](../../../../images/sync-mobile-site.png)

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

![Figure 2: Downloaded files appear in the list with their size in blue.](../../../../images/sync-mobile-file-downloaded.png)

The Sync mobile app also lets you move, rename, and delete files and folders. To 
the right of each file and folder in the list is a circle icon with three dots. 
Pressing this icon slides open a context menu on the right that lets you move, 
rename, or delete that item. The screenshots below show these options. Note that 
you should use **extreme caution** when deleting files or folders. Deleting 
files or folders in the mobile Sync app also deletes them from @product@ and
across any synced clients. Accidentally deleted files can be restored from the
Recycle Bin, which is enabled by default. If the instance or Site administrator
disables the Recycle Bin, however, recovering deleted files is impossible. 

What if you want to delete a file on your device without also deleting it in the
instance? Currently, you can only do this by signing out of your account in the
app's Settings menu. Doing so removes all downloaded files from your device, but
preserves them in the instance. If you're on Android, it may be possible to use
a system file browser app to remove downloaded files manually. 

+$$$

**Warning:** Deleting a file in the mobile Sync app deletes it in @product@ and
across any synced clients. If you accidentally delete a file, the instance or
Site administrator can restore it from the instance's Recycle Bin. The Recycle
Bin is enabled by default. If the instance or Site administrator disables the
Recycle Bin, however, recovering deleted files is impossible. 

$$$

The context menu also provides additional options for files. A small badge on
the file icon's top-right corner indicates the file's version in the @product@ 
instance. You can also use the context menu to share files you've downloaded. 
Pressing the *Share* icon opens a list of your device's apps capable of sharing 
the file. To close the context menu and return to the list of files and folders, 
swipe to the right. The following screenshot shows the options available in a 
file's context menu. 

![Figure 3: The badge on the file's icon shows the file's version in the @product@ instance. You can also share files that you've downloaded.](../../../../images/sync-mobile-file-actions.png)
