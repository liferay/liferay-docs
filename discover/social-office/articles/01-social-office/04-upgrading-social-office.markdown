# Upgrading Social Office [](id=upgrading-social-office)

There are a few steps and procedures that you need to follow if you're upgrading 
to a newer version of Social Office. The sections below describe how to upgrade 
from Social Office 2.x to 3.x, and from Social Office 3.0.0 to 3.0.1.

## Upgrading From Social Office 2.x to 3.x 

Social Office 3.x only runs on Liferay Portal 6.2.x. If you'd like to upgrade
from Social Office 2.x to to 3.x, you first need to upgrade your portal from
Liferay 6.1 to Liferay 6.2. For information on upgrading Liferay, please refer
to the [Liferay User Guide](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/upgrading-liferay-liferay-portal-6-2-user-guide-18-en).
Remember to undeploy your Social Office 2.x plugins before upgrading your portal
to 6.2. If you have a Liferay Portal EE subscription, make sure to apply any
available fix packs before deploying your Social Office 3.x plugins.

+$$$

**Note:** There are a few known issues with the Social Office plugins that you 
might experience if you're running on JBoss. If the Go To menu is not initially 
visible after deploying the Social Office plugins or if you can't confirm a 
connection or site invitation after logging in as the default administrator 
user, clear the `/temp` folder and then restart the server to resolve the issue.

$$$

Once your 6.2 portal is ready, deploy your Social Office 3.x plugins. When you
first log in to your portal as an administrator after installing Social Office
3, you'll see the Social Office configurations portlet. You can use this portlet
to grant the Social Office User role to users. The Social Office User provides
access to the Dashboard home page, the public Profile page, and all of their
applications. You're now ready to use Social Office!

## Upgrading from Social Office 3.0.0 to 3.0.1

Before upgrading from Social Office 3.0.0 to 3.0.1, make sure to set
`hot.undeploy.on.redeploy=true` in your `portal-ext.properties` file. This
property makes Liferay completely undeploy a plugin before deploying a new
version of the plugin. If you don't set `hot.undeploy.on.redeploy=true` before
upgrading, the upgrade might not correctly handle activities. Setting
`hot.undeploy.on.redeploy=true` ensures that activities are handled correctly.
See the following issues for more details:

[SOS-2158](https://issues.liferay.com/browse/SOS-2158)
[SOS-2159](https://issues.liferay.com/browse/SOS-2159)
[SOS-2160](https://issues.liferay.com/browse/SOS-2160)

After installing the new version of Social Office, you're all set!
