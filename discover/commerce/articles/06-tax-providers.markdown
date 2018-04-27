# Tax Providers [](id=tax-providers)

Yeah, we know. Taxes are inevitable as death and a lot less interesting--we
wanted to skip this section too. Fortunately, applying a tax rate to your
products is a straightforward process.

It is possible to integrate with a third-party provider to manage taxes, sparing
you the trouble of keeping track of tax rates for every jurisdiction where you
do business (see [developer docs]). This section, however covers how to handle
taxes in-house.

## Tax Categories [](id=tax-categories)

The main purpose of tax categories is to allow for the sale of different
products that are to be taxed differently. To apply any tax rate, you must have
at least one tax category. Once you have a category in place, you can assign
a tax rate or rates to the category, which must then be deployed to products in
your catalog.

![Figure 1: Tax settings are reached at the  *Taxes* tab at *Commerce* &rarr; *Settings*.](../images/taxes.png)

To create a tax category, go to *Tax Categories* (see above) and click the
![Add](../images/icon-add.png). Enter a name for the category, and if
necessary a description for site administrators. Then click *Save*.

## Fixed Rate [](id=fixed-rate)

Fixed Rate taxes are simple, requiring only that you define a rate and assign it
to a tax category. Once that's done, the defined rate is applied to every
product that deploys its category.

![Figure 2: Rates are defined from *Taxes* &rarr; *Tax Methods*.](../images/tax-methods.png)

To create a fixed tax rate, click on *Fixed* In the *Detail* section, you can
change the name of the rate and enter a more informative description if desired.
Check the *Percentage* box if you want the tax to be expressed as a percentage
of the subtotal rather than as a flat dollar amount. To use the tax rate, the
*Active* box must also be checked.

Then click on the *Tax Rates* tab, and create a new rate by clicking the
![Add](../images/icon-add.png) button. Select a tax category and enter the rate. You
can create no more than one fixed tax rate for each tax category.

Now your tax rate is in place. To deploy it to a product, go to your catalog and
select a product. Then go to *configuration* &rarr; *Tax Categories* &rarr;
![Add](../images/icon-add.png) and select the appropriate tax category.

## By Address [](id=by-address)

Fixed rate taxes are easy to set up, but sellers doing business across borders
will require a more sophisticated approach. When the By Address tax method is
used, users can assign multiple tax rates to a single category, each of which
applies to a different tax jurisdiction.

The process for setting up by address taxes is much like that for fixed rate
taxes: the main differences are that you can assign more than one By Address
rate to a category, and that each rate must specify a region where it applies.
When a customer enters her shipping address, the correct tax rate will be
applied to her order.

From *Tax Methods*, click on *By Address* and fill in the detail form. Then
click on *Tax Rate Settings* and then ![Add](../images/icon-add.png).

![Figure 3: Most users will need to specify only a country and region, but the Zip field allows for rates to be fine tuned at the local
level.](../images/tax-address.png)

Select a Tax Category, set the rate, and choose the area where you want to be
applied. Simply leave the *Region* or *Zip* fields blank to apply the rate to
a larger area.

To the rate to a product, select one from your catalog, go to
*configuration* &rarr; *Tax Categories* &rarr; [Add](../icon-add.png) and
select the appropriate tax category.
