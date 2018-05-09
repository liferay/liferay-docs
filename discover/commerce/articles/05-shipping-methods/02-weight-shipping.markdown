# By Weight Shipping Method [](id=by-weight-shipping-method)

Shipping by weight is a slightly different animal, requiring you to do a little
math. Wait, come back! It's really quite simple-and it allows you to set
appropriate shipping costs for every product in your catalog without the
laborious process of manually creating a shipping exception for every item you
sell. Dynamic shipping allows you to implement a formula that will automatically
set shipping rates on the basis of weight and price.

## Measurement Units [](id=measurement-units)

There's a little bit of housekeeping to take care of before you can
ship by weight. Of course, every product in your catalog will need to specify
a weight [link to catalog/SKUs]. Before you do that, however, you may need to
create measurement units.

If you built you store from a site template, measurement units will already be
in place. But if you built your site from scratch or prefer archaic units of
measure, go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click on the
*Measurement Units* tab. Then select either the *Dimension* or *Weight* tab and
click ![add](../../images/icon-add.png).

![Figure 1: This is where you choose between using a traditional system of measurement or the much easier (quicker, more seductive) metric system.](../../images/measurement-units.png).

The *key* field  will auto-fill when the unit's
name is entered.

If you intend for the unit to be use to compute shipping costs, check the
*Primary* box and the *Ratio to Primary* field will disappear. Otherwise, enter
the number of new units that are equal to a single primary unit. For example, if
the primary unit is *ounces*, then ratio to primary for the unit *pounds* would
be 0.0625. If the primary unit is *kilograms*, then the ratio for the unit
*grams* would be a nice, even 1000 (seductive, isn't it?).

Once your measurement units are in place and you've entered the weight of your
products, you can activate the By Weight shipping method just as you activated
the fixed rate method [link to details, above]. But the shipping options for By
Weight are a little different.

## Shipping Options [](id=shipping-options)

To define a shipping option for By Weight Shipping, go to *Site Menu* &rarr;
*Commerce* &rarr; *Settings* &rarr; *Shipping Methods* tab &rarr; *By Weight*
and select the *Shipping Options* tab. Name and describe your option as above
[link to fixed/shipping options] and move on to the *Shipping Option Settings*
tab.

<!--[Figure 3: This is where it can get a little math-y. Relax, the computer
will handle the actual arithmetic.](../../images/shipping-formula.png)-->

By Weight Shipping calculates a rate by the formula displayed at the top of the
page. This allows to base shipping costs on three factors: a fixed (or minimum)
cost, a cost expressed as set price per unit of weight, and a cost expressed as
a percentage of the order subtotal. To use these factors, fill in the following
fields.

**Shipping Option**: Select the option to which this option setting applies.

**Warehouse**: Make a selection here to apply this setting only to shipments
from a particular warehouse. Leave blank to apply to all warehouses.

**Country, Region, Zip**: Entries to these fields limit the use of this setting
to deliveries to the selected locations.

**Weight From, Weight To**: Entries to these fields limit the use of this
setting to products that fall within the weight range.

**Fixed Price**: A price in this field sets an effective minimum price and
contributes the fixed component of the shipping cost formula.

**Rate Unit Weight Price**: Sets the cost to ship per unit of weight.

**Rate percentage**: A number in this field imposes a shipping cost
as a percentage of the total purchase price of the items in an order.

The total shipping cost will be the sum of the fixed price, the rate unit weight
price (times the weight of order, or course) and the rate percentage (times the
order subtotal). If you don't want to use one or more of these components, leave
the corresponding field blank.

The next section covers FedEx shipping.
