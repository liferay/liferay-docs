# Installation and Uninstallation [](id=installation-and-uninstallation)

The easiest way to install/uninstall the Audience Targeting app is via
[Liferay Marketplace](http://www.liferay.com/marketplace). Since this is a
DXP-only app, there will only be one version to download for your @product@
instance.

If you can't uninstall the Audience Targeting app via Marketplace or you want to
manually uninstall it, follow these steps:

1. Delete your `[Liferay Home]/data/osgi` folder.

2. Delete your app server's `work` and `temp` directories. For example, if
   you're running Liferay on Tomcat, delete your `[Liferay
   Home]/tomcat-[version]/work` and `[Liferay Home]/tomcat-[version]/temp`
   folders.

3. Restart your app server.
