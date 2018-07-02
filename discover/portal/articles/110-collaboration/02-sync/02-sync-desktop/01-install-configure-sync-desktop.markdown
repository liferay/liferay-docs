# Installing and Configuring the Desktop Liferay Sync Client [](id=installing-and-configuring-the-desktop-liferay-sync-client)

You can download the desktop client from the
[Liferay Sync downloads page](https://www.liferay.com/downloads/liferay-sync). 
Note that you'll need a Liferay account for this. Once you've downloaded the 
appropriate desktop client for your operating system, installing Liferay Sync on 
Windows or Mac OS is straightforward. 

## Installing the Liferay Sync Desktop Client [](id=installing-the-liferay-sync-desktop-client)

To install the Liferay Sync client on Windows, you must have administrator
privileges. Upon launching the Windows application installer, you're prompted
to choose an install location. Select an appropriate location and click
*Install*. Sync automatically starts after the installation finishes. The first
time Sync runs, you must configure it to connect and sync with @product@. The
configuration steps are shown below. 

+$$$

**Note:** You can upgrade previous versions of the desktop Liferay Sync client
to version 3.0. When doing so, however, you must set up your account again in
the new version of the client. Prior to upgrading, it's typically best to shut
down Liferay Sync, backup files from your local Sync folder, and delete that
folder. 

$$$

The Liferay Sync client for Mac is packaged in a DMG file. Double-clicking on
the DMG file mounts it as a disk image and opens a window showing the image's
contents. To install Sync, drag the Liferay Sync icon to your Applications
folder. Once it's installed, run it from the Applications folder. If you're
running Mac OS X 10.9 or lower, you're prompted for your machine's administrator
credentials to install the Finder icon/context menu tool. This prompt only
appears when installing or upgrading the tool. 

![Figure 1: Drag the Liferay Sync icon to the Applications folder.](../../../../images/sync-mac-install.png)

Next, you'll configure the Sync client. 

## Configuring the Liferay Sync Desktop Client [](id=configuring-the-liferay-sync-desktop-client)

Now that you've installed Sync, you're ready to configure it! The configuration 
steps for Sync on Windows and Mac are identical. 

1.  Open Sync and enter your server's address along with your account
    credentials. Click *Sign In* when you're finished. 

    ![Figure 2: The first time you run Liferay Sync, you must tell it how to communicate with your @product@ server.](../../../../images/sync-setup-01.png)

    When connecting to a server via HTTPS, an error appears if the certificate 
    can't be verified. Choosing *Proceed Anyway* bypasses verification and 
    leaves the connection open to compromise. Liferay Sync attempts to read the 
    certificates specified in the Java Control Panel 
    ([see section 20.4.5](https://docs.oracle.com/javase/8/docs/technotes/guides/deploy/jcp.html#A1152831)). 
    If Java isn't installed, you can also put your certificates in 
    `[user.home]/.liferay-sync-3/certificates`. Liferay Sync trusts all 
    certificates in this folder. 

    ![Figure 3: When connecting over HTTPS, Liferay Sync produces an error if it can't verify the security certificate. Choosing *Proceed Anyway* bypasses verification and leaves the connection open to compromise.](../../../../images/sync-certificate-error.png)

2.  Select the Sites you want to sync with. You can search for a Site in the 
    *Search* bar above the Site list. If you want to sync all the subfolders of 
    your selected Sites, click *Proceed* and move on to the next step. 

    ![Figure 4: Select the Sites you want to sync with. Clicking a Site's gear icon opens another window where you can choose to sync with only specific subfolders in that Site.](../../../../images/sync-setup-02.png) 

    To sync only specific folders in a Site, first click the Site's gear icon. 
    In the window that appears, all folders are selected by default. Unselect 
    the folders you don't want to sync with. Unselecting a subfolder causes the 
    parent folder's checkbox to show a minus sign, indicating that you haven't 
    selected all of the parent folder's subfolders. To sync only the documents 
    at the top of a folder's hierarchy (no subfolders), unselect all of that 
    folder's subfolders. You can also do this by clicking the folder's checkbox 
    until the minus sign appears. Click *Select* when you're finished with your 
    selections, and then click *Proceed* to move on to the next step. 

    ![Figure 5: Choose the Site's subfolders that you want to sync with. The checkbox with the minus sign indicates that not all of the *registration* folder's subfolders are selected.](../../../../images/sync-select-folders.png)

3.  Specify the local folder to sync. This folder is used exclusively for
    Sync: Sync creates it and it must not conflict with any existing local
    folder. The Sync folder's default name is the instance's host name, and its
    default location is the user's documents folder. For example, since the
    instance in the following screenshots runs locally at the address
    `http://localhost:8080/`, Sync creates a Sync folder named *localhost* in
    the user's documents folder. You can, of course, specify any unique name and
    location for the Sync folder. Click *Start Syncing* to begin syncing files. 

    ![Figure 6: Specify your local Sync folder's name and location.](../../../../images/sync-setup-03.png)

4.  Celebrate! You've successfully set up Liferay Sync! Sync congratulates you 
    on setting it up and begins to sync files from the Sites you selected to 
    your local Sync folder. Note, completing the initial synchronization may 
    take a significant amount of time, depending on the amount of data being 
    transferred. You can safely close the window as syncing continues in the 
    background. To view the local Sync folder, click *Open Folder*. To open 
    Sync's preferences, click the small gray text *advanced setup* near the 
    top-right. 

    ![Figure 7: Congratulations, you've successfully set up Liferay Sync!](../../../../images/sync-setup-04.png)
