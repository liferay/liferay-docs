# Variable Rate Shipping [](id=variable-rate-shipping)

Variable rate shipping calculates shipping costs using three factors: the
order's weight, its subtotal (cost before shipping and taxes), and any fixed
price you impose. You choose the priority of each factor. 

You can create multiple different shipping options with variable rates. For
example, you might create a "Standard Ground" option with a relatively low cost
per unit of weight, as well as a "Two-Day Air" option that calculates costs in the
same way but results in a higher price.

## Creating a Variable Rate Shipping Option [](id=creating-a-variable-rate-shipping-option)

Variable-rate shipping costs are determined by the following formula: `shipping
costs = [fixed price] + ([order total weight] x [rate unit weight price])
+ ([order subtotal] x [rate percentage])`.

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click the
    *Shipping Method* tab. Choose the *Variable Rate* method and then the
    *Shipping Options* tab.

2.  Click ![Add](../../images/icon-add.png) and fill in the following fields:

    **Name:** Buyers see this name when selecting a shipping option.

    **Description:** Information about the option---delivery time, guarantees,
    insurance and the like---should go in this field.

    **Priority:** Sets the option display order. Lower numbers come first.

3.  Click *Save*. Then click the *Shipping Option Settings* tab.

4.  Click ![Add](../../images/icon-add.png) and fill in the following
    fields:

    **Shipping Option:** Select the shipping option for this setting. The first
    time through, select the option you named in step 2.

    **Warehouse:** Select a warehouse if this method should apply only to
    shipments from one location. Leave blank to use this method for all
    warehouses.

    **Country, Region, Zip:** Make an entry if this shipping method should be
    restricted to destinations within the area you define.

    **Weight From, Weight To:** Enter a weight range for orders that can use
    this option.

    **Fixed Price:** An entry in this field sets an effective minimum price and
    contributes the fixed component of the shipping cost formula. It can be left
    blank.

    **Rate Unit Weight Price:** An entry in this field imposes a cost per unit
    of weight. It can be left blank.

    **Rate Percentage:** An entry in this field imposes a shipping cost based on
    a percentage of the order subtotal. It can be left blank.

5.  Click *Save*. To create more than one option, return to the *Shipping
    Options* tab and repeat steps 2 through 4.

6.  Click the *Details* tab and check the *Active* box. Click *Save*.

The *Details* tab also contains fields for changing the name and description of
the flat rate shipping method type. They may be useful for reference, but the
text is not automatically displayed to buyers. You can also set a priority,
which orders variable rate shipping methods relative to other types.
