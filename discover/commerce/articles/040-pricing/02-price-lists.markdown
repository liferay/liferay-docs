# Price Lists [](id=price-lists)

A price list contains a list of products with alternate prices from what's
stored by the catalog. The prices stored by the list are only available
to members of specified user segments. Other buyers must pay the base price
stored by the catalog's SKUs.

You can use a price list to give preferential pricing to some users, but the
list's prices need not be lower than the catalog's base price. A price list
could impose higher prices to reflect additional costs involved in doing
business with certain buyers, or it might allow buyers to make purchases using
a currency other than the store's default.

## Creating a Price List [](id=creating-a-price-list)

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Price Lists* and click on the
    ![Add](../../images/icon-add.png) button at the top right.

2.  Fill in the following fields and click *Publish* when finished:

**Name**: Give the list a name.

**Store Currency**: Select the price list's currency.

**Priority**: If a user has access to more than one price list and a conflict
occurs, the list with the higher priority is used. Lists are prioritized in
numerical order.

**User Segments**: Hit the *Select* button and check the box next to user
segments that should have access to this price list. Then click *Add*.

**Schedule:** Enter a date range for when the list is active. Check *Never
Expire* to make it permanent.

## Populating a Price List [](id=populating-a-price-list)

You may have published a price list, but you haven't yet had a chance to add any
products to it. Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Price Lists*. If you just
    published a list, you're already on this page. Click on the list you want to
    edit.

2.  Click the *Entries* tab and then the ![Add](../../images/icon-add.png)
    button. In the pop-up, select the SKUs you want to add to the list and click
    *Add*.

3.  Now you see the SKUs you selected on the *Entries* tab. Choose a product
    and enter its new price.

    If you want, enter a price in the *Promo Price* field as well. A promo price
    overrides the main price, but both prices can appear on the product page so
    buyers can see the markdown. <!--needs screenshot-->

4.  Repeat step 3 until a price has been set for all SKUs you added in step 2.

+$$$

There are two ways to add entries to a price list. Besides the method just
described, you can also add a product from the SKU tab in its catalog entry (see
[SKUs](web/liferay-emporio/documentation/-/knowledge_base/7-1/adding-options-to-productsl#price-lists)).
In general, the method described here is more efficient for adding a large
number of products to a new list. When creating a new product, however, it's
usually easier to add it to existing price lists from the Catalog's SKU tab.

$$$

## Tiered Pricing [](id=tiered-pricing)

Price lists make possible pricing tiers that unlock lower prices for bulk
buyers. Specify a price and a minimum quantity necessary to unlock that price.
Successive tiers can unlock even lower prices for higher-volume purchases.

Follow these steps to set up tiered pricing for a product:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Price Lists* and click on
    a list. Click the *Entries* tab to see this list's SKUs.

2.  Click on a product's name and then on the *Tier Price Entries* tab.

3.  Click the ![Add](../../images/icon-add.png) button and fill in the *Price* and
    *Minimum Quantity* fields. You can also override the price with a *Promo
    Price* (see above). Click *Save*.

4.  Repeat step 3 to add successive tiers to the same product.
