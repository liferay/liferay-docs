# Getting Started with @commerce@ [](id=getting-started)

@commerce@ is a digital commerce platform built from the ground up to work with
@product@, making it easy to leverage a fully integrated content management
system and best-in-breed portal capabilities in commerce websites. Any website
built with @product-ver@ can be integrated seamlessly with a store built with
@commerce@.

## Overview [](id=overview)

@commerce@ contains a 
[catalog management](/web/liferay-emporio/documentation/-/knowledge_base/1-0/catalog-management)
system that supports both physical and virtual products. The catalog stores
a wide range of product data---product variations, pricing, images,
specifications---and makes it readily available to the store's customer-facing
pages.

![Figure 1: The catalog stores inventory levels, category assignments, and other product information.](../../images/catalog.png)

@commerce@ includes a wide range of tools for setting up a 
[storefront](/web/liferay-emporio/documentation/-/knowledge_base/1-0/setting-up-a-storefront).
New search tools, for instance, are designed specifically for product catalogs,
while customizable widgets make it easy to add common UI elements---such as
*Checkout* and *View Cart*---to a page.

![Figure 2: Product pages are ready to go out-of-the-box, but can be customized extensively.](../../images/product-detail.png)

@commerce@ is optimized for business-to-business (B2B) transactions, combining
modern site experiences with features that address B2B needs. 
[Customer Portal](/web/liferay-emporio/documentation/-/knowledge_base/1-0/customer-portal)
serves the needs of buyers with approval processes to observe before
making purchases. When buyers places an order, @commerce@ can send an automated
email to their managers, prompting them to approve the order so it can be
processed. The customer portal enables customer organizations to manage their
own accounts, so they can designate their own buyers, order managers, and
account managers.

In addition, @commerce@ features a user control system integrated with
@product@'s Roles and permissions. 
[User segmentation](/web/liferay-emporio/documentation/-/knowledge_base/1-0/user-segmentation)
allows pricing, catalog visibility, and other features to be precisely targeted
to certain customers. 

[Pricing](/web/liferay-emporio/documentation/-/knowledge_base/1-0/pricing) and
[Tax](/web/liferay-emporio/documentation/-/knowledge_base/1-0/taxes) management features
allow almost unlimited flexibility, with support for almost any kind of discount
and multi-currency transaction. Meanwhile, 
[payments](/web/liferay-emporio/documentation/-/knowledge_base/1-0/payment-methods)
can be handled through integration with a third-party payment processor such as PayPal.

![Figure 3: Any number of different payment methods can be added.](../../images/payment-methods.png)

Products can be shipped by a number of different
[methods](/web/liferay-emporio/documentation/-/knowledge_base/1-0/shipping-methods):
sellers can impose a flat rate, calculate costs on the basis of weight, or
integrate their systems closely with the rates of a shipping service, allowing
buyers to choose directly from the services offered by, say, FedEx or UPS.
The
[Warehouses](/web/liferay-emporio/documentation/-/knowledge_base/1-0/warehouses)
feature allows @commerce@ to track inventories at different warehouses
separately and automatically. When an order is placed, @commerce@ determines
which warehouse is closest to the delivery point and processes the order
accordingly.

## Installation [](id=installation)

Enterprise subscribers with a Liferay Commerce order can install from the 
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/commerce).
You need an order number that includes @commerce@ or an `.xml` activation key.
You must also have @product-ver@; see installation instructions
[here](/discover/deployment/-/knowledge_base/7-1/deploying-product).

Once you've downloaded the @commerce@ `.lpkg` from Customer Portal, follow these
steps if you have an `.xml` key:

1.  Start @product-ver@. 

2.  Drop your activation key into the `deploy` folder in Liferay Home. Wait for
    the log to show that the license has been registered.

3.  Drop the @commerce@ `.lpkg` into the `deploy` folder as well. Wait for the
    log to show that your instance must be restarted.

4.  Restart your instance.

5.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Search* and reindex all
    search indexes.

Follow these steps if you have an order number that includes @commerce@:

1.  Start @product-ver@.

2.  Drop the `.lpkg` into the `deploy` folder in Liferay Home.

3.  Go to *Control Panel* &rarr; *Apps* &rarr; *License Manager* and enter your
    Order ID into the provided field. Click *Query*.

4.  Select Liferay Commerce from the drop-down menu that appears and click
    *Register*.

5.  Wait for a success message and restart @product@.

6.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Search* and reindex all
    search indexes.

## Site Initializers [](id=site-initializers)

The quickest way to get going with @commerce@ is to use a Site Initializer.
A site initializer is a type of 
[Site Template]( /discover/portal/-/knowledge_base/7-1/building-sites-from-templates)
that does much of the initial work of setting up a store. Creating a Site with
a Site Initializer is an easy way to see what @commerce@ can do, and can be used
to get a head start on a live store: modify the theme to suit your purposes,
replace the dummy catalog with your own data, and you're well on your way.

At the time of this writing, @commerce@ ships with one site initializer, called
Beryl. It includes a theme, a complete user interface oriented toward B2B
selling, and a catalog populated with sample products. See 
[Customer Portal](/web/liferay-emporio/documentation/-/knowledge_base/1-0/customer-portal)
for for information.
