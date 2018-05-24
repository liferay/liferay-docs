# Flat Rate Shipping [](id=fixed-shipping-method)

When you create a flat rate shipping method, you create a method that any buyer
can choose to use to ship any product. By creating multiple flat rate methods,
you can give customers various choices in terms of cost, speed, and security.

Flat rate shipping imposes the same cost on every product in the catalog.
However, you can create exceptions for individual products from the *Shipping
Extra Price* field in the product's
[Configuration](/web/liferay-emporio/documentation/-/knowlede_base/7-1/configuration#shipping)
tab.

## Creating a Flat Rate Shipping Method

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click on the
    *Shipping Method* tab. Click on the *Flat Rate* method and then on the
    *Shipping Options* tab.

2.  Click on the ![Add](../../images/icon-add.png) button and fill in the
    following fields:

    **Name:** This is the name that buyers will see when they are prompted to
    select a shipping option.

    **Description:** Information about the method---delivery time, guarantees,
    insurance and the like---should go in this field.

    **Amount:** Set the method's cost.

    **Priority:** Sets the order in which methods will be displayed. Lower
    numbers come first.

3.  Click *Save*. To create more than one method, repeat steps 2 and 3 as
    needed.

4.  Click on the *Details* tab and check the *Active* box. Click *Save*.

The *Details* tab also contains fields for changing the name and description of
the flat rate shipping method type, which may be useful for reference, but the
text is not automatically displayed to customers. You can also set a priority,
which orders flat rate shipping methods relative to other types.

## Restrictions

You may not want a shipping method to be available to customers all over the
world. Follow these steps to limit flat rate shipping methods to specified
countries:

1.  From the *Flat Rate* page (*Site Menu* &rarr; *Commerce* &rarr; *Settings*
    &rarr; *Shipping Methods* &rarr; *Flat Rate*), click on the *Restrictions*
    tab.

2.  Click on the ![Add](../../images/icon-add.png) button and check the box next
    to each country for which you want to permit flat rate shipping. Click
    *Add*.

These restrictions apply to all flat rate shipping methods you create,
but not to methods of any other type.

To permit shipping to all countries, it is not necessary to list each one
separately. Simply leave the restriction list empty.
