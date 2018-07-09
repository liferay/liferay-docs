# Discounts [](id=discounts)

A discount applies a modifier to a product's base price. It can be an absolute
number or a percentage, and can be applied to a limited number of products or to
the entire catalog. It can be available to all buyers or to a defined 
[user segment](/web/liferay-emporio/documentation/-/knowledge_base/1-0/user-segmentation),
or to a more narrowly defined group of customers that meet certain
qualifications. Unlike a price list, a discount always *reduces* the base price.

## Creating a Discount [](id=creating-a-discount)

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Discounts* and click on the
    ![Add](../../images/icon-add.png) button at the top right.

2.  Fill out the *Add Discount* Form (see below) then click *Publish*.

    Discounts have targets: 

3.  If you chose *Apply to Products* (see the *Add Discount* form below),
    a *Products* tab appears. Click it and then click the
    ![Add](../../images/icon-add.png). Check
    the box next to the products you want and click *Add*.

4.  If you chose *Apply to Categories*, a *Categories* tab appears. Click it and
    then click *Select*. Choose any number of categories and click *Done*. Then
    click *Save*.

## Adding Rules to a Discount [](id=adding-rules-to-a-discount)

You can create rules that limit the buyer's discount conditions. There are six
types of rules:

**cart-total**: Buyers can benefit from the discount if their orders contain
products worth a specified amount.

**purchased-all**: Buyers whose past purchases include specified products can
benefit from the discount.

**had-purchased-one-of-these-products**: Buyers whose past purchases include at
least one from a list of specified products can benefit.

**had-spent-amount**: Buyers who have already spent a specified minimum amount
can benefit.

**has-all-of-these-products**: Buyers can benefit if their orders include all
from a list of specified products.

**has-one-of-these-products**: Buyers can benefit if their orders contain at
least one from a list of specified products.

Follow these steps:

1.  Create a discount or choose an existing one at *Site Menu* &rarr; *Commerce*
    &rarr; *Discounts*.

2.  Click the *Rules* tab and select one of the above types from the
    drop-down menu.

3.  If you chose the *cart-total* or the *had-spent-amount* type, enter an
    amount in the field that appears.

    If you chose any other type, click the *Select* button and check the box
    next to each appropriate product. Click *Add*.

5.  Click *Save*.

## Add Discount Form [](id=add-discount-form)

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
[user segmentation](/web/liferay-emporio/documentation/-/knowledge_base/7-1/user-segmentation)
for details.

**Coupon Code**: Set the toggle to *Yes* and enter text in the field that
appears. Customers must enter that text to take advantage of the discount.

**Use Percentage**: Set the toggle to *Yes* to express the discount amount as
a percentage off the list price. Set it to *No* to enter a set amount.

**Maximum Discount Amount**: Set the maximum amount by which a product or order
can be discounted. If this field is left at zero, no discount is applied. This
field only appears when the *Use Percentage* toggle is enabled.

**Level 1**: Set the order discount percentage. Hit the *Add* button next to the
field to create a discount series up to three levels deep. This field only
appears when the *Use Percentage* toggle is enabled.

**Discount Amount**: Set the currency amount reduced from each discounted
product or order. This field only appears when the *Use Percentage* toggle is
disabled.

**Limitation Type**: Choose the number of times buyers can use the discount.
Choose *Limited* to set a hard limit on how many times the discount can be
applied. Choose *Limited For Users** to limit the number of times an individual
buyer can benefit.

**Limitation Times**: Set the limit specified in the previous field.

**Cumulative**: Check to allow this discount to be combined with other
discounts.

**Active**: This box must be checked for the discount to be applied.

**Schedule**: Enter a display and expiration date to limit the time in which the
discount applies. Alternately, check *Never Expire* to ignore the expiration
date.
