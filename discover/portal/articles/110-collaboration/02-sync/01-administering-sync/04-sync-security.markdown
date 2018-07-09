# Ensuring Liferay Sync Security [](id=ensuring-liferay-sync-security)

As an administrator, you have a stake in the security of all connections to and
from your servers. As long as @product@ is configured to use HTTPS, Sync clients
use user-supplied credentials to communicate securely. Users can only access the
documents and Sites they're permitted to access. To support Security Mode in the
Sync mobile client and securely transmit files, your @product@ server must also
use SSL. The next section demonstrates how Sync's permissions work with your
@product@ instance's permissions. 

## Liferay Sync Permissions Demonstration [](id=liferay-sync-permissions-demonstration)

Sync uses @product@'s default permissions to determine files and folders to sync
with the user's devices. It can only sync files a user can access. After
installing the desktop Sync client, follow the steps below to test this
functionality. 

First, enter `classified information` into a new text file and save it on your
desktop as `secret.txt`. Then use your browser to sign into @product@
and create a new user with the user name *secretagent* and the email address
*secretagent\@liferay.com*. Give this user a password and then create a new
private Site called *Secret Site*. Create a page on the Site and add the
Documents and Media app to it. Then add the secretagent user to the Secret Site
and grant the *Site Administrator* Role to the user. Log in as secretagent and
navigate to the Secret Site. Then upload the `secret.txt` document to the
Documents and Media app. Make sure you also have a user that isn't a member of
the Secret Site and therefore doesn't have access to any of its documents
through Sync. If you don't have such a user, create one now. 

Next, configure your Liferay Sync client to sign in with the secretagent user's
credentials and sync with the Secret Site. Open the Liferay Sync menu from the
system tray and select *Preferences*. In the *Accounts* tab, click the plus icon
at the window's bottom left to add an account. Provide the secretagent user's
credentials and uncheck all Sites except the Secret Site. Now confirm that Sync
downloaded the `secret.txt` file to your new Sync folder. Open it and check that
it contains the text `classified information`. Next, use Sync to connect to your
@product@ instance with the user that doesn't belong to the Secret Site. The
file doesn't sync because this user isn't a Site member. 

Now go to Sync Connector Admin and set the Secret Site's default file
permissions to View Only. Create a new user, add it to the Secret Site, and add
its account in your Liferay Sync client. As with the secretagent user, Sync
downloads the `secret.txt` file to this user's local Sync folder because the
user is a member of the Secret Site. Now edit and save this file. Even though
you can edit and save it locally, the edits aren't synced because the Site's
default file permissions are View Only. After attempting the sync, a red *x*
appears next to the file in the local Sync folder. Right click the file to see
the error. It confirms the user doesn't have the required permissions. 

![Figure 1: The upload error occurs because the user only has permission to view files.](../../../../images/sync-file-permissions-error.png)

To confirm that the error didn't propagate through Sync, open the file in the 
secretagent user's local Sync folder. It still contains the original text. 
Likewise, the original file remains in the Site's Documents and Media portlet. 
To get rid of the error in the other user's local Sync folder, return there and 
then right click the file and select *Download From Server*. This replaces the 
file with the latest file in the @product@ instance. 

Now edit `secret.txt` in the secretagent user's local Sync folder. When you
check the file in the other user's local Sync folder and in the @product@ 
instance, notice that Sync propagated the edits. The changes were propagated 
because the secretagent user owns the file in the instance. Owners can do 
anything with their files, even when the Site's default file permissions are set 
to View Only. 
