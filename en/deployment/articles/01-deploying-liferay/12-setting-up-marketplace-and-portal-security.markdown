---
header-id: setting-up-marketplace-and-portal-security
---

# Setting Up Marketplace

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/installing-and-managing-apps/getting-started/using-marketplace.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

[Liferay Marketplace](https://www.liferay.com/marketplace) is more than just a
store for Liferay applications. Under the hood, it provides both the store and
@product@'s application deployment features. For this reason, you must ensure
that Marketplace can run and configure itself.

Here are some scenarios to work around to ensure Marketplace works successfully:

-   Server is Firewalled without Access to the Internet
-   Limited Database Access

The firewall scenario is discussed first.

## Server is Firewalled without Access to the Internet

Your server might be behind a firewall that prevents access to the Internet. Or
your security policy might not allow direct download and installation from the
Internet. In these cases, you have two options:

1.  From an Internet-enabled computer, download the [Marketplace
    plugin](https://www.liferay.com/marketplace/download). Then deploy the
    plugin (`.lpkg` file) by copying it into the `deploy` folder in [Liferay
    Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home).

2.  Alternately, once you have the downloaded `.lpkg` file, deploy it using the
    [App Manager](/docs/7-2/user/-/knowledge_base/u/managing-and-configuring-apps).

Next you'll learn how to work around database access restrictions.

## Limited Database Access

Some production environments do not have the necessary database permissions for
@product@, apps, modules, and plugins to maintain their tables. In these cases:

1.  Grant the @product@ database user temporary full rights to the database.

2.  Install @product@ and start it so that it populates its database.

3.  Once the database is created, remove the permissions for creating tables and
    dropping tables from the @product@ database user.

See the [database preparation
instructions](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#limiting-database-access)
for more information. Note that many sophisticated @product@ apps---not just the
Marketplace app---require new tables when deployed. If your environment
restricts database access, you may need to repeat the above steps whenever you
deploy a new app.

You've prepared @product@ for installing Marketplace and additional apps.
