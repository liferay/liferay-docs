# Configuration [](id=configuration)

A product's configuration governs how it is displayed and transacted. You can
configure how the product's inventory is handled, the conditions under which it
can be sold, and any special circumstances involving shipping or taxes.

To get to a product's configuration page, go to the catalog, select a product,
and click on the *Configuration* tab. This page has four more tabs of its own:
*Inventory*, *Shipping*, *Display Page,* and *Tax Category*.

## Inventory [](id=inventory)

The *Inventory* tab contains the following fields:

**Inventory Engine:** The inventory engine tracks the catalog's inventory data
and keeps it up to date as orders are placed. If you develop a custom inventory
engine---say, for the sake of integrating closely with a supplier---you can
activated it for individual products with this drop-down menu.

**Low Stock Activity:** Select an action to be taken automatically when
a product's inventory falls below a certain level. You can set the level that
triggers this action below, in the *Minimum Stock Quantity* field.

**Availability Range:** This is an estimate for how long it will take for the
product to be restocked. This drop-down menu is populated from *Settings* &rarr;
*Availability Ranges*. See
[Settings](/web/liferay-emporio/documentation/-/knowledge_base/7-1/settings)
for details.

**Display Availability:** Display a notice on the product page indicating
whether it is in stock.

**Display Stock Quantity:** Display a notice on the product page indicating how
many are in stock.

**Minimum Stock Quantity:** When the product's inventory level falls to the
number entered in this field, the *Low Stock Activity* is triggered.

**Allow Back Orders:** Check this box to permit orders to be placed when the
product is out of stock.

**Minimum/Maximum Order Quantity:** Set the Set the least and greatest
quantities that can be purchased in a single transaction.

**Allowed Order Quantities:** Order quantities will be limited to any numbers
entered. An entry of "2, 4, 6" allows buyers to only purchase those quantities.

**Multiple Order Quantity:** Only permit order quantities that are multiples of
the number entered.

## Shipping [](id=shipping)

The *Shipping* tab contains the following fields:

**Shippable:** If disabled, then this product must be picked up in-store. This
option should be disabled for virtual products.

**Free Shipping:** Enable to override 
[Shipping Methods](/web/liferay-emporio/documentation/-/knowledge_base/7-1/shipping-methods-intro)
and ship for free.

**Ship Separately:** Enable to require that this product be shipped in its own
package.

**Shipping Extra Price:** Enter a shipping surcharge for this product.

**Width, Height, Depth and Weight:** If you are calculating shipping charges by
weight (see 
[Shipping Methods](/web/liferay-emporio/documentation/-/knowledge_base/7-1/)
) then enter the products weight. Other dimensions can be entered for reference
purposes, or---with the development of custom code---to calculate shipping
costs.

## Display Page [](id=display-page)

Designating a unique product display page creates a relationship between the
product and the specified page. For a less labor intensive way to create
a generic product page, see 
[Product Detail Page](/web/liferay-emporio/documentation/-/knowledge_base/7-1/product-detail-page).
To create a unique product page for this product, follow these steps:

1.  Create a page.

2.  Go to the catalog and select a product. Click on *Configuration* tab and
    then the *Display Page* tab.

3.  Click *Choose* and then select the page you just created. Click *Done* to
    close the pop-up and then click *Save*.

This process creates a relationship between the product and a blank page. At any
point, you can populate the page with whatever content or applications suit your
purposes.

## Tax Category [](id=tax-category)

The *Tax Category* tab contains the following fields:

**Tax Category:** Select from available tax categories in the drop-down menu.
This determines the amount that will be charged when this product is purchased
to cover tax. See 
[Tax Providers](/web/liferay-emporio/documentation/-/knowledge_base/7-1/tax-providers)
for details on creating and using tax categories.

**Tax Exempt:** If this box is checked, no tax will be charged on this product.
