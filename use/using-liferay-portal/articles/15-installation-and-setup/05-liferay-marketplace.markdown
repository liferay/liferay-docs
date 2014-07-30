# Liferay Marketplace [](id=liferay-marketplace-lp-6-2-use-useportal)

The Liferay Marketplace is an integral part of the Liferay Portal experience.
Starting with Liferay Portal 6.2, the Marketplace plugin is required to be
installed alongside Liferay Portal. The Marketplace plugin enables a host of
features that extend beyond just access to the online Liferay Marketplace. Some
of the key features the Marketplace plugin enables are:

- Liferay Marketplace:  direct access to our online Marketplace
- App Manager: ability to install, uninstall, and update apps
- Bundled Apps: easily manage apps that may come bundled with your Liferay
  Portal 
- Developer Apps: ability to manage apps that you’re developing
- License Manager: streamlined license management for your Liferay Portal and
  apps

The portal installation process attempts to deploy and register the Marketplace
plugin automatically. If your environment supports/allows 1) hot deploy and 2)
full database rights, the automatic deploy process takes care of itself.
Many companies (especially in a production environment), however, limit
automated processes and/or database access. Additionally, certain application
servers (eg., WebSphere) do not support hot deploy, so you may need to 
deploy the Marketplace plugin manually. Depending on your environment’s
restrictions, you may need to follow one or more of the steps below to 
install the Marketplace plugin properly. 

## Server is Firewalled without Access to the Internet

Your server may be behind a firewall that prevents access to the Internet, or
your security policy may not allow direct download and installation from the
Internet. In these cases, you have 2 options:

1. From an Internet-enabled computer, download the Marketplace plugin from
[here](https://www.liferay.com/marketplace/download).
Then allow Liferay to auto deploy it by dropping the downloaded .lpkg file into
the Liferay deploy folder. 

2. From an Internet-enabled computer, download the Marketplace plugin. Then use
the Liferay App Manager to deploy the plugin.

Detailed instructions can be found under [Installing Plugins Manually](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en). 

## Application Server Does Not Support Hot Deploy

If your application server does not support hot deploy, you can't leverage
Liferay’s auto deploy feature. You can, however, manually deploy the
plugin in two steps:

1. Use Liferay’s tools to “pre-deploy�? the file.

2. Then use your app server's tools to do the actual deployment.

This is because Liferay makes deployment-time modifications to the plugins right
before they are actually deployed to the application server. Detailed
instructions can be found under [Deploy Issues for Specific Containers](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en). 

## Limited Database Access

Some production environments do not have the necessary database permissions for
Liferay and its plugins to maintain their tables. In these cases:

1. Grant the ID Liferay uses to access the database temporary full rights to the
   database. 

2. Install Liferay and have it create the database. 

3. Once the database is created, remove the permissions for creating tables and
   dropping tables from the user ID.

Detailed instructions are available
[here](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/liferays-database-liferay-portal-6-2-user-guide-15-en).
It should be noted that most sophisticated Liferay apps--not just the
Marketplace plugin--require new tables when deployed. If your environment
restricts database access, you may need to repeat the above steps whenever you
deploy a new app to the Liferay Portal.
