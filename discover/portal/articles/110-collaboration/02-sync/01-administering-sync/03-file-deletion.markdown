# Preventing Accidental File Deletion in Liferay Sync [](id=preventing-accidental-file-deletion-in-liferay-sync)

Liferay Sync's power rests in its ability to propagate between @product@ 
and connected Sync clients. When a user deletes a file from a connected 
client, Sync also deletes the file in the instance and in any other connected 
clients. Likewise, if a user deletes a file in the instance, Sync also deletes 
the file in all connected clients. In other words, anywhere a user deletes
a file, Sync deletes it *everywhere*. But don't worry: @product@'s Recycle Bin
is enabled by default and lets you recover deleted files. You can access the
Recycle Bin from each Site's *Site Administration* menu. 

+$$$

**Warning:** Liferay Sync automatically propagates file and folder deletion
through the @product@ server and in all connected clients. If an instance or
Site administrator disables the Recycle Bin, deleted files can't be recovered.

$$$

@product@ instance and Site administrators can, of course, disable the Recycle 
Bin. Disabling the Recycle Bin in a Site, however, leaves the Site vulnerable to
accidental file deletions that propagate through Sync. 
