# Configuration [](id=configuration)

A product's configuration governs how it is displayed and transacted. You can
configure how the product's inventory is handled, the conditions under which it
can be sold, and any special circumstances involving shipping or taxes.

To get to a product's configuration page, go to the catalog, select a product,
and click on the *Configuration* tab. This page has four more tabs of its own:
Inventory, Shipping, Display Page, and Tax Category.

## Inventory [](id=inventory)

The Inventory tab controls how a product's inventory data is tracked and
communicated to buyers. It contains these fields:

**Inventory Engine:** Select an inventory engine for this product by making
a selection from the drop-down menu. The default inventory engine, which is the
only one packaged with @commerce@, tracks the catalog's inventory data and keeps
it up to date as orders are placed.

You can make changes to this behavior---say, for the sake of integrating your
system more closely with a supplier---by developing a custom inventory engine
and activating for individual products using this drop-down.

**Low Stock Activity:** Select an action to be taken automatically when
a product's inventory falls below a certain level. You can set the level that
triggers this action below, in the *Minimum Stock Quantity* field.

**Availability Estimate:** Select an estimate from the drop-down to indicate how
long it should take to resupply the product if inventory is depleted. This
estimate is displayed on the product's page whenever it is out of stock. The
drop-down menu is populated from *Settings* &rarr; *Availability Estimates*. See
[Settings](/web/liferay-emporio/documentation/-/knowledge_base/1-0/settings) for
details.

**Display Availability:** Check this box to display an in stock/out of stock
notice on the product page. 

**Display Stock Quantity:** Check this box to display an inventory level notice
on the product's page. 

**Minimum Stock Quantity:** Enter a number to trigger the *Low Stock Activity*
threshold. 

**Allow Back Orders:** Check this box to permit orders even when the product is
out of stock.

**Minimum/Maximum Order Quantity:** Set the least and greatest quantities that
can be purchased in a single transaction.

**Allowed Order Quantities:** Order quantities are limited to any numbers
entered. An entry of "2, 4, 6" allows buyers to purchase only those quantities.

**Multiple Order Quantity:** Only permit order quantities that are multiples of
the number entered.

## Shipping [](id=shipping)

The *Shipping* tab governs how shipping costs for a product are calculated. It
contains these fields:

**Shippable:** If disabled, no shipping costs are calculated at all. Disable
this for virtual products.

**Free Shipping:** Enable to override 
[Shipping Methods](/web/liferay-emporio/documentation/-/knowledge_base/1-0/shipping-methods)
and ship for free.

**Ship Separately:** Enable to require this product be shipped in its own
package.

**Shipping Extra Price:** Enter a shipping surcharge for this product.

**Width, Height, Depth and Weight:** If you are calculating shipping charges by
weight (see 
[Variable Rate Shipping](/web/liferay-emporio/documentation/-/knowledge_base/1-0/variable-rate-shipping))
enter the product's weight. Other dimensions can be entered for reference
purposes, or---with the development of custom code---to calculate shipping
costs.

## Display Page [](id=display-page)

Designating a unique product display page creates a relationship between the
product and the specified page. For a less labor intensive way to create
a generic product page, see 
[Displaying Product Pages](/web/liferay-emporio/documentation/-/knowledge_base/1-0/displaying-product-pages).
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

The *Tax Category* tab governs how a product's sales tax is calculated. It
contains these fields:

**Tax Category:** Select from available tax categories in the drop-down menu.
This determines the amount of tax that's charged when this product is purchased.
See [Taxes](/web/liferay-emporio/documentation/-/knowledge_base/1-0/taxes) for
details on creating and using tax categories.

**Tax Exempt:** If this box is checked, no tax is charged on this product.
