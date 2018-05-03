# SKUs [](id=skus)

A SKU, or stock keeping unit, is an alphanumeric code representing a single
product variant. It is not the same as a product because a product can have
multiple different options with varying attributes. Rather a SKU identifies
products at the most granular level, specifying an exact combination of product
options.

Because it is SKUs (not products) that represent unique items, it is SKUs that
are tracked in inventory and to which prices are assigned.

## Creating a SKU [](id=creating-an-sku)

When you create a product, a single SKU called *default* is created. For
products that have no
[options](/web/liferay-emporio/documentation/-/knowledge_base/7-1/options)
which are SKU contributors, no other SKUs are required. If a product does have
options that are SKU contributors, then every possible combination of those
options represents a separate product variant---each of which must have its own
SKU in order to be sold.

+$$$

When you create new SKUs, the default SKU is automatically set to *Inactive*.
However, if you reactivate it (click on the SKU and hit *Publish*), the default
SKU's price is displayed on the product page when no options have been selected.

$$$

To create new SKUs, follow these steps:

1.  Go to the catalog, click on a product, and go to the *SKUs* tab.

2.  Click on the ![Add](../../../images/icon-add.png) button and select either
    *Add SKU* or *Generate all SKU combinations.

If you select *Add SKU*, a single SKU is created and the *Add SKU* form is
displayed (See *Details*, below).

If you select *Generate all SKU combinations*, then an SKU will be created for
every product variant. To configure these SKUs, you must edit each individually.

## Editing a SKU [](id=editing-an-sku)

When you create a SKU, or hit the ![Options](../../../images/icon-options.png)
button &rarr; *Edit* to the right of an existing one, a new column of tabs is
displayed. 

![Figure 1:An SKU's functions are accessed through these tabs.](../../../images/skus.png)

### Details [](id=details)

The *Details* tab mirrors the *Add SKU* form that is displayed when a single SKU
is created. It contains the following fields:

**SKU:** If the SKU was created singly, you must create an SKU code. This field
is automatically filled when SKUs are generated.

**Global Trade Item Number, Manufacturer Part Number:** Enter a trade item
number (such as an ISBN) or a part number if applicable. These numbers can be
displayed on the product page (see [Product Detail
Page](/discover/portal/-knowledge_base/7_1/catalog-options) and used in search
results. <!--will appear in search results?-->

**Purchasable:** Unchecking this box will leave an item visible to customers but
not allow them to purchase it.

**Options** Select which product variant this SKU refers to. Choose the exact
combination of option values that describe the item in question. The field is
auto-filled for generated SKUs.

**Schedule:** Enter a display and expiration date if this SKU should only be
visible to buyers for a limited time. To publish immediately and permanently,
check both the *Published* and *Never Expire* boxes.

### Pricing [](id=pricing)

The *Pricing* tab contains just three fields:

**Cost:** Enter seller's costs to help track margins.

**Price:** Set the SKU's selling price.

**Promo Price:** Enter a promotional rate. See
[Pricing](/discover/portal/-/knowledge_base/7.1/pricing#price-lists)
for details.

### Price Lists [](id=price-lists)

Price lists set special prices for certain user segments. To add an SKU to
a price list, go to the *Price Lists* tab:

1.  Click on the ![Add](../../../images/icon-add.png) button, and select
    a price list.

2.  Click *Add*.

3.  Click on the price list you just added, and then enter price in the field
    that displays. This price will be charged only to members of the price
    list's user segment.

For more information on creating and using price lists, see 
[Price Lists](/discover/portal/-/knowledge_base/7.1/pricing#price-lists)). 

### Inventory [](id=inventory)

The *Inventory* tab has one field for each warehouse you have in use. (If you
use more than one warehouse, see
[warehouses](/web/liferay-emporio/documentation/-/knowledge_base/7-1/inventory).
Enter the number in stock at each warehouse. The quantity will be automatically
adjusted as orders are processed.

### Shipping Adjustment [](id=shipping-adjustment)

A product's shipping adjustment is set in *Configuration* tab &rarr; *Shipping*.
To override this adjustment for a single SKU, enter it the SKU's *Shipping
Adjustment* tab. See 
[shipping methods](/discover/portal/-/knowledge_base/7.1/shipping-methods)
for more details.
