# Price Lists

A price list contains a list of products with prices that are alternates to the
prices stored by the catalog. The prices stored by the list are only available
to members of specified user segments. Other buyers must pay the base price
stored by the catalog's SKUs.

A price list can be used to give preferential pricing to some users, but the
list's prices do not need to be lower than the catalog's base price. A price
list could impose higher prices to reflect additional costs involved in doing
business with certain buyers, or it might allow buyers to make purchases using
a currency other than the store's default.

## Creating a Price List

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Price Lists* and click on the
    ![Add](../../images/icon-add.png) button at the top right.

2.  Fill in the follow fields and click *Publish* when finished:

**Name**: Give the list a name.

**Store Currency**: Select the currency the price list will use.

**Priority**: If a user has access to more than one price list and a conflict
occurs, the list with the higher priority is used. Lists are prioritized in
numerical order.

**User Segments**: Hit the *Select* button and check the box next to user
segments that should have access to this price list. Then click *Add*.

**Schedule:** Enter dates between which the list will be active. Check *Never
Expire* to make it permanent.

## Populating a Price List

You may have published a price list, but you haven't yet had a chance to add any
products to it. Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Price Lists*. If you just
    published a list, you are already on this page. Click on the list you want
    to add products to.

2.  Click on the *Entries* tab and then the ![Add](../../images/icon-add.png)
    button. In the pop-up, check the box next to each SKU you want to add to the
    list. Then click *Add*.

3.  This returns you to the *Entries* tab, where the SKUs you just selected
    are now listed. Click on a product and enter its new price.

    If you want, enter a price in the *Promo Price* field as well. A promo price
    will override the main price, but both prices can be displayed on the
    product page so that buyers can see the markdown. <!--needs screenshot-->

4.  Repeat step 3 until a price has been set for all of the SKUs you added in
    step 2.

+$$$

There are two ways to add entries to a price list. Besides using the
method just described, you can also add a product from the SKU tab in its
catalog entry (see
[SKUs](web/liferay-emporio/documentation/-/knowledge_base/7-1/adding-options-to-productsl#price-lists)).
In general, the method described on this page is more efficient for adding
a large number of products to a new list. When creating a new product, however,
it is usually easier to add it to existing price lists from the Catalog's SKU
tab.

$$$

## Tiered Pricing

Price lists allow you set up pricing tiers which unlock lower prices for bulk
buyers. Simply specify a price and a minimum quantity necessary to unlock that
price. Successive tiers can unlock even lower prices for still higher-volume
purchases.

Follow these steps to set up tiered pricing for a product:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Price Lists* and click on
    a list. Then click on the *Entries* tab to see this lists SKUs.

2.  Click on a product's name and then on the *Tier Price Entries* tab.

3.  Click the ![Add](../../images/icon-add.png) button and fill in the *Price* and
    *Minimum Quantity* fields. You can also override the price with a *Promo
    Price* (see above) if you want. Click *Save*.

4.  Repeat step three to add successive tiers to the same product.
