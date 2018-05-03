# SKUs [](id=skus)

A SKU, or stock keeping unit, is an alphanumeric code representing a single
product variant. It is not the same as a product because a product can have
multiple different options with varying attributes. Rather a SKU can identify
products at the most granular level, specifying an exact combination of product
options.

Because it is SKUs (not products) that represent unique items, it is SKUs to
which prices are assigned, for which inventory is tracked, and to which shipping
adjustments can be applied.

## Creating a SKU [](id=creating-an-sku)

When you create a product, a single SKU called *default* is created. For
products that have no options which are SKU contributors, no other SKUs are
required. If a product does have options that are SKU contributors, then every
possible combination of those options represents a separate product
variant--each of which must have its own SKU in order to be sold.

<!--Is it true that an option is always required if it is an SKU contributor?-->

To create new SKUs, follow these steps:

1.  Go to the catalog, click on a product, and then go to the *SKUs* tab.

2.  Click on the ![Add](../../../images/icon-add.png) button and select either
    *Add SKU* or *Generate all SKU combinations.

If you select *Add SKU*, a single SKU will be created and the *Add SKU* form
will be displayed.

If you select *Generate all SKU combinations*, 






The default SKU applies to any product options for which you have not created
a separate SKU. To create a new SKU, go to the catalog, click on a product, and
select the *SKUs* tab. Click on the *Add* button in the lower right and select
either *Add SKU* or *Generate all SKU combinations*. If you select the second
option, you must manually enter prices for every possible combination of option
values--not optimal for a typical use case where most variations on a product
will be assigned the same price. To create a handful of SKUs to set prices for
some options while letting the default SKU handle the rest, choose *Add SKU*.

## Editing a SKU [](id=editing-an-sku)

When you create a SKU, or hit the ![Options](../../../images/icon-options.png)
button &rarr; *Edit* to the right of an existing one, a new column of tabs is
displayed. 

![Figure 1:An SKU's functions are accessed through these tabs.](../../../images/skus.png)

The following sections describe how to use these options to put SKUs to work.

### Details [](id=details)

In the details section you can change the SKU code (the default code is simply
a combination of the option values the SKU represents) and also enter a Global
Trade Item Number (such as an ISBN) and a manufacturer's part number (if
applicable). To display these numbers on the product page, see 
[Product Detail Page][/discover/portal/-knowledge_base/7_1/catalog-options].

Unchecking the *Purchasable* box will leave an item visible to customers but not
allow them to purchase it.

The *Options* section is where you select exactly what item this SKU refers to.
Choose the exact combination of option values that describe the item in
question.

The *Schedule* section allows users to display and SKU to customers for
a limited time. The default setting is to publish immediately and never expire.
Uncheck the *published* box to remove an item from the customer's view.

### Pricing [](id=pricing)

The most basic function of SKUs is to assign prices. You can't be limited to
assigning prices at the product level--after all, your widgets in blue and
yellow polka dots may command a higher price than the plain black model.

To set the price, simply click on the *Pricing* tab and enter a number in the
*Price* field.

Entering a figure in the *Costs* field can be used to track margins.

### Price Lists [](id=price-lists)

You may not want to offer all of your customers the same price. For instance,
you might want to give your employees a discount, or offer a special price to
members of a partner organization.

To do this, you must first create a price list (see 
[Price Lists](/discover/portal/-/knowledge_base/7.1/pricing#price-lists)). Once the
list exists, you can add a SKU to it from the *Price Lists* tab. To select
a price list, click on the *Add* button and check the box next to each list to
which you want to add the SKU. Then close the pop-box.

You've added your SKU to the price list, but you haven't changed the price for
list members. To do that, just click on the price list and then enter a price in
the field that displays.

+$$$

Note: the method for adding SKUs to price lists described on this page is best
suited for adding newly created products to existing lists. To populate a new
price list with existing items from the catalog, use the method described in
[Price Lists](/discover/portal/-/knowledge_base/7.1/pricing#price-lists).

$$$

### Inventory [](id=inventory)

Inventory is pretty straightforward. Just enter the number of items you have in
stock. The quantity will be automatically adjusted as orders are processed.

### Shipping Adjustment [](id=shipping-adjustment)

A product's weight or dimensions may factor into shipping costs. By default,
only weight is factored into variable shipping weights (in line with most
shipping companies' practices), but with customization other metrics could be
used. Those metrics can be entered from the *Shipping Adjustment* tab. See
[shipping methods](/discover/portal/-/knowledge_base/7.1/shipping-methods)
for more details.

+$$$

Note: Changing the weight or dimensions of a product in the *Shipping
Adjustment* tab only applies to a single SKU. To set the weight or dimensions
for all of a product's SKUs, go to the productions *Configuration* tab and click
on *Shipping*.

$$$
