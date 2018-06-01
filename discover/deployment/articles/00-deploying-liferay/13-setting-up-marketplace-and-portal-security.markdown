# Setting Up Marketplace [](id=setting-up-marketplace-and-portal-security)

[Liferay Marketplace](https://www.liferay.com/marketplace)
is more than just a store for Liferay applications. Under the hood, it provides
both the store and @product@'s application deployment features. For this
reason, you must ensure that Marketplace can run and configure itself. 

Here are some scenarios to work around to ensure Marketplace works successfully:

-   [Server is Firewalled without Access to the Internet](#server-is-firewalled-without-access-to-the-internet)
-   [Limited Database Access](#limited-database-access)

The firewall scenario is discussed first. 

## Server is Firewalled without Access to the Internet [](id=server-is-firewalled-without-access-to-the-internet)

Your server might be behind a firewall that prevents access to the Internet. Or
your security policy might not allow direct download and installation from the
Internet. In these cases, you have two options:

1.  From an Internet-enabled computer, download the 
    [Marketplace plugin](https://www.liferay.com/marketplace/download).
    Then allow @product@ to auto deploy it by dropping the downloaded `.lpkg`
    file into the `deploy` folder in
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home).

2.  Alternately, once you have the downloaded `.lpkg` file, deploy it using the
    [App Manager](/discover/portal/-/knowledge_base/7-1/managing-and-configuring-apps).

Next you'll learn how to work around database access restrictions. 

## Limited Database Access [](id=limited-database-access)

Some production environments do not have the necessary database permissions for
@product@, apps, modules, and plugins to maintain their tables. In these cases:

1.  Grant the @product@ database user temporary full rights to the database.

2.  Install @product@ and start it so that it populates its database.

3.  Once the database is created, remove the permissions for creating tables and
    dropping tables from the @product@ database user.

See the
[database server and new database instructions](/discover/deployment/-/knowledge_base/7-1/preparing-for-install#step-1-choose-a-database-server-and-create-a-new-database)
for more information. Note that many sophisticated
@product@ apps---not just the Marketplace app---require new tables when
deployed. If your environment restricts database access, you may need to repeat
the above steps whenever you deploy a new app.

You've prepared @product@ for installing Marketplace and additional apps. 
