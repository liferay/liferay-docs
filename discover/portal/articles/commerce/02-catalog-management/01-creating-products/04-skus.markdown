# SKUs

An SKU, or stock keeping unit, is an alphanumeric code representing a distinct
item for sale. It is not the same as a product because a product can have
multiple different options with varying attributes. Rather, an SKU can identify
products at the most granular level, specifying an exact combination of product
options.

## Creating an SKU

When you create a product, a single SKU called *default* is created. The default
SKU applies to any product options for which you have not created a separate
SKU. To create a new SKU, go to the catalog, click on a product, and select the
*SKUs* tab. Click on the *Add* button in the lower right and select either *Add
SKU* or *Generate all SKU combinations*. If you select the second option, you
must manually enter prices for every possible combination of option values--not
optimal for a typical use case where most variations on a product will be
assigned the same price. To create a handful of SKUs to set prices for some
options while letting the default SKU handle the rest, choose *Add SKU*.

+$$$

Below the Search & Filter bar, there is a toggle switch to *Ignore SKU
Combinations*. If at any point you want give every version of the product 
the same price regardless of what options it takes, enable this switch and
you won't have to assign a price for each option individually. <!--When I try to
enable the Ignore SKU Combinations toggle, I get an error message that says
"Only one SKU can be approved." How do I change the approval status of an
SKU?-->

$$$

## Editing an SKU

To edit an existing SKU, hit the ![Options](../../../images/icon-options.png)
button to the right of its entry. Creating a new SKU takes you to this page
automatically. The following sections describe how to use these options to put
SKUs to work.

### Details

In the details section you can change the SKU code (the default code is simply
a combination of the option values the SKU represents) and also enter a Global
Trade Item Number (such as an ISBN) and a manufacturer's part number (if
applicable). <!--Are these numbers solely for inventory tracking? They don't
appear on the product page.-->

<!--I'm not sure what the *Purchasable* box means, as I can uncheck it and still
add the item to my cart and proceed to checkout.-->

The *Options* section is where you select exactly what item this SKU refers to.
Choose the exact combination of option values that describe the item in
question. <!--This section is not working properly at present-->

The *Schedule* section allows users to display and SKU to customers for
a limited time. The default setting is to publish immediately and never expire.

<!--unchecking the *published* box makes an item unavailable for purchase but
doesn't seem to impact whether this SKUs option value combination is available
on the product page. Is that a problem?-->

### Pricing

The most basic function of SKUs is to assign prices. You can't be limited to
assigning prices at the product level--after all, your widgets in blue and
yellow polka dots may command a higher price than the plain black model.

To set the price, simply click on the *Pricing* tab and enter a number in the
*Price* field. Select the currency to the right of the field.

<!--What purpose does the *Cost* field have, other than for
reference in setting prices?-->

### Price Lists

You may not want to offer all of your customers the same price. For instance,
you might want to give your employees a discount, or offer a special price to
members of a partner organization.

To do this, you must first create a price list (see [link]). Once the list
exists, add an SKU to it by navigating to *Site Menu* &rarr; *Commerce* &rarr;
*Catalog*. Select a product, click on the *SKUs* tab, select the desired SKU and
then click on the *Price Lists* tab. To select a price list, click on the *Add*
button and check the box next to each list you want to add the SKU to. Then
close the pop-box.

You've added you SKU to the price list, but you haven't changed the price for
list members. To do that, click on the
![Options](../../../images/icon-options.png) next to a price list and select
*Edit*. In the details tab, you can set the price for this list.

Alternatively, to set a price that kicks in for bulk buyers, go to the *Tier
Price Entries* tab and click on the *Add* button. You can create a single
discount for buyers of certain quantities, or add multiple tiers with larger
orders unlocking deeper discounts.

<!--This is how I think price lists are supposed to work. At present, however,
none of this seems to affect the price listed on the product page.-->

<!--It looks like you can't set up tiered pricing unless you have a price list
in place. Is that the way it should be? Won't some users want enable tiered
pricing for all customers without bothering with price lists?-->

### Inventory

Inventory is pretty straightforward. Just enter the number of items you have in
stock. <!--Will this number be updated automatically when orders are shipped or
when new supplies are received? How does that work?-->

### Shipping Adjustment

<!--I don't know how this works yet.-->
