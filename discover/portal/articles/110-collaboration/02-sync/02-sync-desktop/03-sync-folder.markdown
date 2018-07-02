# Using Your Local Liferay Sync Folder [](id=using-your-local-liferay-sync-folder)

Once you configure and run Sync, Sync automatically uploads to @product@ any
files you add or modify in your Sync folder. Sync also downloads to your Sync
folder any file changes by other users. If you delete a file in your Sync
folder, Sync also deletes it from the server and other clients. You should
therefore use **extreme caution** when deleting files in your Sync folder. If,
however, you accidentally delete a file, all is not lost! The file can still be
recovered from the instance's Recycle Bin, which is enabled by default. Note, if
the administrator has disabled the Recycle Bin, recovering deleted files is
impossible. 

+$$$

**Warning:** Deleting a file in your Sync folder also deletes it in the 
@product@ instance and in other clients. If you accidentally delete a file, it 
can be recovered from the instance's Recycle Bin. The Recycle Bin is enabled by 
default. File recovery is, however, impossible if the instance or Site 
administrator has disabled the Recycle Bin. 

$$$

You can run through the following exercise to familiarize yourself with how to
create, edit, download, and upload files with Sync. First, open the Sync folder
in your file manager and create a new file called `README.txt`. Enter the word
`test` in this file. Next, make sure you can access this file in your Site. Go
to the Site you want to sync with and navigate to its Documents and Media app.
It lists your `README.txt` file.

Download the `README.txt` file to a convenient location on your machine. Open 
the file and check that it still says `test`. Now open the `README.txt` file in 
your Sync folder and edit it so that it says `second test`. Once the changes are 
synced, go back to your browser and refresh the page with your Documents and 
Media app. Click on the `README.txt` file's name, look at the file information 
displayed, and check that the file's version number has been incremented. 

![Figure 1: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../../../images/sync-file-edit-01.png)

If you download and open `README.txt` again, it now says `second test`. Your
edit was uploaded to the Site! You can be confident that this edit was also
downloaded by all other Sync clients connected to your Site. 

Now delete the `README.txt` file from your local Sync folder. When the changes
finish syncing, go back to your browser and refresh the page containing your
Documents and Media app. The file is gone! The file is also deleted from the
local Sync folders of all other Sync clients connected to the Site. Remember
this very important rule: deleting files in your local Sync folder deletes them
*everywhere*! Next, you'll learn how to use the Sync client for your mobile
device. 
