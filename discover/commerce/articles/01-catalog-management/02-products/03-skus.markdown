# SKUs [](id=skus)

The catalog stores pricing, inventory, and other information by attaching it to
a SKU, or Stock Keeping Unit. A SKU is an alphanumeric code representing
a single product variant. A product must have at least one SKU to be purchased.

When a product has only one SKU, the SKU represents the product directly.

![Figure 1: The SKU is a holder for certain kinds of product information.](../../../images/single-SKU.png)

When a product has more than one SKU, each SKU represents a unique product
variant.

![Figure 2: SKUs organize information that is unique to each variant.](../../../images/multiple-SKUs.png)

A product must have a different SKU for each of its variants.  A product has
different variants if it has been assigned options which are SKU contributors
(see [options](/web/liferay-emporio/documentation/-/knowledge_base/7-1/options)
for details). Giving a SKU to each variant allows the inventory engine to track
each variant individually. It also lets you set a unique price for each variant.

+$$$

When should an option be a SKU contributor? If a product is available in
a variety of colors, the option to select a color must be a SKU contributor in
order for the inventory engine to track each color separately. Using a SKU
contributor in this case also allows prices to vary by color.

Alternatively, suppose a store offered free gift-wrapping for some products. The
gift-wrap option would not be a SKU contributor, as neither pricing nor
inventory are affected.

$$$

Since the product variant a SKU represents is defined by a combination of
[options](/web/liferay-emporio/documentation/-/knowledge_base/7-1/options),
all options should be finalized before creating or editing SKUs.

## Creating a SKU [](id=creating-an-sku)

When you create a product, a single SKU called *default* is created. For
products that require only one SKU, the default is all that is needed. To
configure it, see the instructions for editing a SKU below.

If a product requires multiple SKUs, then you must add them. Follow these steps:

1.  Go to the catalog, click on a product, and go to the *SKUs* tab.

2.  Click on the ![Add](../../../images/icon-add.png) button and select
    *Generate all SKU combinations*. This creates a SKU for each combination of
    option values a product could take.

    Alternatively, you could create a single SKU by selecting *Add SKU*, but
    keep in mind that any product variant without a SKU is not purchasable.

+$$$

When you create new SKUs, the default SKU is set automatically to *Inactive*.
However, if you reactivate it (click on the SKU and click *Publish*), the
default SKU's price appears on the product page when no options have been
selected.

$$$

## Editing a SKU [](id=editing-an-sku)

When you create a SKU or click ![Options](../../../images/icon-options.png)
&rarr; *Edit* to the right of an existing one, a new column of tabs appears. 

![Figure 1:An SKU's functions are accessed through these tabs.](../../../images/skus.png)

### Details [](id=details)

The *Details* tab mirrors the *Add SKU* form that appears when a single SKU is
created. It contains the following fields:

**SKU:** Enter the SKU.

**Global Trade Item Number, Manufacturer Part Number:** Enter a trade item
number (such as an ISBN) or a part number if applicable. These numbers can be
displayed on the product page (see 
[Product Detail Page](/discover/portal/-knowledge_base/7_1/catalog-options)) and used in search
results. 

**Purchasable:** Unchecking this box leaves an item visible to customers but
purchasable.

**Options** Select which product variant this SKU refers to. Choose the exact
combination of option values that describe the item in question. The field is
auto-filled for generated SKUs.

**Schedule:** Enter a display and expiration date if this SKU should be visible
to buyers only for a limited time. To publish immediately and permanently, check
both the *Published* and *Never Expire* boxes.

### Pricing [](id=pricing)

The *Pricing* tab contains three fields:

**Price:** Set the SKU's base selling price.

**Promo Price:** Enter a promotional rate that overrides the base price. See
[Pricing](/discover/portal/-/knowledge_base/7.1/pricing#price-lists) for
details.

**Cost:** Enter seller's costs to help track margins.

### Price Lists [](id=price-lists)

Price lists set special prices for certain user segments. To add a SKU to
a price list, go to the *Price Lists* tab:

1.  Click the ![Add](../../../images/icon-add.png) button and select
    a price list.

2.  Click *Add*.

3.  Click the price list you just added, and then enter price in the field that
    displays. This price is charged only to members of the price list's user
    segment.

For more information on creating and using price lists, see 
[Price Lists](/discover/portal/-/knowledge_base/7.1/pricing#price-lists). 

### Inventory [](id=inventory)

The *Inventory* tab has one field for each warehouse you have in use (if you
use more than one warehouse, see
[warehouses](/web/liferay-emporio/documentation/-/knowledge_base/7-1/inventory)).
Enter the number in stock at each warehouse. The quantity is adjusted
automatically as orders are processed.

### Shipping Adjustment [](id=shipping-adjustment)

A product's shipping rate may be adjusted based on its weight or dimensions,
which are set in *Configuration* tab &rarr; *Shipping*. To override these
dimensions for a single SKU, enter it the SKU's *Shipping Adjustment* tab. See
[shipping methods](/discover/portal/-/knowledge_base/7.1/shipping-methods) for
more details.
