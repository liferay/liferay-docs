# Discounts

A discount applies a modifier to a product's base price. It can be expressed as
either an absolute number or as a percentage, and can be applied to either
a limited number of products or to the entire catalog. It can be available
either to all buyers or to a defined 
[user segment](web/liferay-emporio/documentation/-/knowledge_base/1-0/user-segmentation),
or to an even more narrowly defined group of customers that meet certain
qualifications. Unlike a price list, a discount always reduces the base price
and never increases it.

## Creating a Discount

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Discounts* and click on the
    ![Add](../../images/icon-add.png) button at the top right. This displays the
    *Add Discount* form.

2.  Fill out the *Add Discount* Form (see below) then click *Publish*.

3.  If you chose *Apply to Products* as the discount's target (see the *Add
    Discount* form below), an additional tab, *Products* is displayed at the top
    of the page. Click on it and then click the ![Add](../../images/icon-add.png)
    at the top right. Check the box next to the products you want and click
    *Add*.

4.  If you chose *Apply to Categories* as the discount's target, a *Categories*
    tab is displayed at the top of the page. Click on it and then click
    *Select*. Click on any number of categories and click *Done*. Then click
    *Save*.

## Adding Rules to a Discount

You can create rules that limit the conditions under which a buyer can take
advantage of the discount. There are six types of rules:

**cart-total**: A buyer will only be able to benefit from the discount if his
order contains products worth a specified amount.

**purchased-all**: Only buyers who have already purchased specified products
will be able to benefit from the discount.

**had-purchased-one-of-these-products**: Only buyers who have already purchased
at least one of a number of specified products will be able to benefit.

**had-spent-amount**: Only buyers who have already spent a specified minimum
amount will be able to benefit.

**has-all-of-these-products**: A buyer will only be able to benefit if his order
includes all of a number of specified products.

**has-one-of-these-products**: A buyer will only benefit if his order contains
at least one of a number of specified products.

Follow these steps:

1.  Create a discount or click on an existing one at *Site Menu* &rarr;
    *Commerce* &rarr; *Discounts.

2.  Click on the *Rules* tab and select one of the above types from the
    drop-down menu.

3.  If you chose either the *cart-total* or the *had-spent-amount* type, enter
    an amount in the field that appears.

    If you chose any other type, click the *Select* button and check the box
    next to each appropriate product. Click *Add*.

5.  Click *Save*.

## Add Discount Form

The *Add Discount* form contains the following fields:

**Title**: Give the discount a name.

**Target**: Select the discounts target from the drop-down box. You have five
options:

- *Apply to Categories*: Apply the discount to selected product categories.

- *Apply to Products*: Apply the discount to individually selected products.

- *Apply to Shipment*: Apply the discount to shipping costs.

- *Apply to Subtotal*: Apply the discount to orders before shipping and taxes.

- *Apply to Total*: Apply the discount to orders after shipping and taxes are
added.

**User Segment**: To allow only certain customers to take advantage of the
discount, click *Select* and choose a segment or segments. See 
[user segmentation](web/liferay-emporio/documentation/-/knowledge_base/7-1/user-segmentation)
for details.

**Coupon Code**: Set the toggle to *Yes* and enter text in the field that
appears. Customers will be required to enter that text to take advantage of the
discount.

**Use Percentage**: Set the toggle to *Yes* to express the discount amount as
a percentage off the list price. Set it to *No* to enter a set amount.

**Maximum Discount Amount**: Set the maximum amount by which a product or order
will be discounted. If this field is left a zero, no discount will be applied.
This field only appears when the *Use Percentage* toggle is enabled.

**Level 1**: Set the percentage that will be discounted from the product or
order. Hit the *Add* button next to the field to create a discount series up to
three levels deep. This field also only appears when the *Use Percentage* toggle
is enabled.

**Discount Amount**: Set the amount that will be taken off of each discounted
product or order. This field only appears when the *Use Percentage* toggle is
disabled.

**Limitation Type**: Choose whether to limit the number of times buyers can take
advantage of the discount. Choose *Limited* to set a hard limit on how many
times the discount will be applied. Choose *Limited For Users** to limit the
number of times an individual buyer can benefit.

**Limitation Times**: Set the limit specified in the previous field.

**Cumulative**: Check to allow this discount to be combined with other
discounts.

**Active**: This box must be checked for the discount to be applied.

**Schedule**: Enter a display and expiration date to limit the time in which the
discount will apply. Alternately, check *Never Expire* to ignore the expiration
date.
