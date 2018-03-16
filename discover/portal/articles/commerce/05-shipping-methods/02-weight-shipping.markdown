# By Weight Shipping Method

Shipping by weight is a slightly different animal, requiring you to do a little
math. Wait, come back! It's really quite simple. Dynamic shipping allows you to
implement a formula that will automatically set shipping rates on the basis of
weight and price.

## Measurement Units

There's a little bit of housekeeping to take care of before you can ship by
weight. Of course, every product in your catalog will need to specify a weight
[link to catalog/SKUs]. Before you do that, however, you'll need to create
measurement units. Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and
click on the *Measurement Units* tab. Then select either the *Dimension* or
*Weight* tab and click [add](../../images/icon-add.png).

![Figure 2: This is where you choose between using a traditional system of
measurement or the much easier (quicker, more seductive) metric system.](../../images/measurement-units.png).

The key field <!--what is this for, anyway?--> will auto-fill when the unit's
name is entered.

If you intend for the unit to be the primary measurement used by your catalog,
check the *Primary* box and the *Ratio to Primary* field will disappear.
Otherwise, enter the number of new units that are equal to a single primary
unit. For example, if the primary unit is *ounces*, then ratio to primary for
the unit *pounds* would be 0.0625. If the primary unit is *kilograms*, then the
ratio for the unit *grams* would be an even 1000 (seductive, isn't it?).

Once your measurement units are in place and you've entered the weight of your
products, you can activate the By Weight shipping method just as you activated
the fixed rate method [link to details, above]. But the shipping options for By
Weight are a little different.

## Shipping Options

To define a shipping option for By Weight Shipping, go to *Site Menu* &rarr;
*Commerce* &rarr; *Settings* &rarr; *Shipping Methods* tab &rarr; *By Weight*
and select the *Shipping Options* tab. Name and describe your option as above
[link to fixed/shipping options] and move on to the *Shipping Option Settings*
tab.

![Figure 3: this is where it can get a little math-y. Relax, the computer will
handle the actual arithmetic.](../../images/shipping-formula.png)


