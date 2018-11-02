# Getting Started with @commerce@ [](id=getting-started)

@commerce@ is a digital commerce platform built from the ground up to work with
@product@, making it easy to leverage a fully integrated content management
system and best-in-breed portal capabilities in commerce websites. Any website
built with @product-ver@ can be integrated seamlessly with a store built with
@commerce@.

## Overview [](id=overview)

@commerce@ contains a 
[catalog management](/web/emporio/documentation/-/knowledge_base/1-0/catalog-management)
system that supports both physical and virtual products. The catalog stores
a wide range of product data---product variations, pricing, images,
specifications---and makes it readily available to the store's customer-facing
pages.

![Figure 1: The catalog stores inventory levels, category assignments, and other product information.](../../images/catalog.png)

@commerce@ includes a wide range of tools for setting up a 
[storefront](/web/emporio/documentation/-/knowledge_base/1-0/setting-up-a-storefront).
New search tools, for instance, are designed specifically for product catalogs,
while customizable widgets make it easy to add common UI elements---such as
*Checkout* and *View Cart*---to a page.

![Figure 2: Product pages are ready to go out-of-the-box, but can be customized extensively.](../../images/breccia-product-detail.png)

@commerce@ is optimized for business-to-business (B2B) transactions, combining
modern site experiences with features that address B2B needs. For buyers with
approval processes to observe before making purchases, @commerce@ can provide
[workflows](/discover/portal/-/knowledge_base/7-1/workflow) so that the
appropriate manager must approve each order before it can be placed.

In addition, @commerce@ features a user control system integrated with
@product@'s Roles and permissions. [User
segmentation](/web/emporio/documentation/-/knowledge_base/1-0/user-segmentation)
allows pricing, catalog visibility, and other features to be precisely targeted
to certain customers. 

[Pricing](/web/emporio/documentation/-/knowledge_base/1-0/pricing) and
[Tax](/web/emporio/documentation/-/knowledge_base/1-0/taxes) management features
allow almost unlimited flexibility, with support for almost any kind of discount
and multi-currency transaction. Meanwhile, 
[payments](/web/emporio/documentation/-/knowledge_base/1-0/payment-methods)
can be handled through integration with a third-party payment processor such as PayPal.

![Figure 3: Any number of different payment methods can be added.](../../images/payment-methods.png)

Products can be shipped by a number of different
[methods](/web/emporio/documentation/-/knowledge_base/1-0/shipping-methods):
sellers can impose a flat rate, calculate costs on the basis of weight, or
integrate their systems closely with the rates of a shipping service, allowing
buyers to choose directly from the services offered by, say, FedEx or UPS.
The
[Warehouses](/web/emporio/documentation/-/knowledge_base/1-0/warehouses)
feature allows @commerce@ to track inventories at different warehouses
separately and automatically. When an order is placed, @commerce@ determines
which warehouse is closest to the delivery point and processes the order
accordingly.

## Installation [](id=installation)

@commerce@ can be installed from an `.lpkg` file you can download
[here](https://dev.liferay.com/en/web/emporio/downloads). You will also need
@product-ver@; see installation instructions
[here](/discover/deployment/-/knowledge_base/7-1/deploying-product).

Once you have the `.lpkg`, follow these steps:

1.  Start @product-ver@. 

2.  Drop the @commerce@ `.lpkg` into the `deploy` folder in Liferay Home. Wait for the
    log to show that your instance must be restarted.

3.  Restart your instance.

4.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Search* and reindex all
    search indexes.

## Accelerators [](id=accelerators)

The quickest way to get going with commerce is to use a site Accelerator. An 
Accelerator is a type of [Site Template]( /discover/portal/-/knowledge_base/7-1/building-sites-from-templates)
that does much of the initial work of setting up a store. Creating a Site with
an Accelerator is an easy way to see what @commerce@ can do, and can be used
to get a head start on a live store: modify the theme to suit your purposes,
replace the dummy catalog with your own data, and you're well on your way.

At the time of this writing, @commerce@ ships with two Accelerators.
Beryl---available to subscribers only---is geared toward B2B selling, while
Breccia models a UI similar to that used by most major retailers. Both
Accelerators include a theme, a complete user interface, and a catalog populated
with sample products. See 
[Accelerators](/web/emporio/documentation/-/knowledge_base/1-0/accelerators)
for more information.
