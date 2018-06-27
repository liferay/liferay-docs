# Taxes [](id=taxes)

Collecting taxes is accomplished through the use of *Tax Categories* and *Tax
Methods*.

A **Tax Method** defines the process by which tax is calculated. There are two
kinds of tax methods: *fixed* and *by address*.

A **Tax Category** is a container for one or more tax methods. Different
products can be taxed differently by assigning different tax categories to them.

## Tax Categories [](id=tax-categories)

To apply any tax rate, you must first have at least one tax category. Follow
these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click the *Taxes* tab
    and then the *Tax Categories* sub-tab.

2.  Click on the ![Add](../images/icon-add.png) button. Give your tax category
    a name and a description and then click *Save*.

See
[Configuration](/web/liferay-emporio/documentation/-/knowledge_base/1-0/configuration#tax-category)
for more on how to assign a tax category to a product.

## Tax Methods [](id=tax-methods)

There are two kinds of tax methods: fixed and by address. The fixed method
applies the same rate to a product each time it is purchased, while the by
address method only applies to buyers within a specified geographical region.

### Creating a Fixed Tax Method [](id=creating-a-fixed-tax-method)

Creating a fixed tax method sets rates for each tax category independently. The
tax collected then depends on which tax category a product is assigned. Follow
these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click the *Taxes* tab
    and then the *Tax Methods* sub-tab. Click on *Fixed Tax Rate*.

2.  Enter a name and description for the method in the appropriate fields.

3.  Check the *Percentage* box if you want the tax to be defined as a percentage
    of the purchase price. Uncheck the box to define the tax as a fixed amount.

4.  Check the *Active* box to enable the method and click *Save*.

5.  Click on the *Tax Rates* sub-tab. This displays a *Rate* field for each tax
    category. Fill in each field and hit *Save*.

### Creating a By Address Tax Method [](id=creating-a-by-address-tax-method)

Taxing by address sets rates for geographical areas independently. Several by
address rates can be applied to a single tax category. When a product to which
that category is assigned is purchased, the tax rate appropriate to the buyer's
location is used. Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click the *Taxes* tab
    and then the *Tax Methods* sub-tab. Click on *By Address*.

2.  Enter a name and description for the method in the appropriate fields.

3.  Check the *Percentage* box if you want the tax to be defined as a percentage
    of the purchase price. Uncheck the box to define the tax as a fixed amount.

4.  Check the *Active* box to enable the method and click *Save*.

5.  Click on the *Tax Rate Settings* sub-tab and then on the
    [Add](../images/icon-add) button.

6.  Fill out the following fields:

    **Tax Category:** This tax rate will only apply to products that are
    assigned the tax category you select.

    **Rate:** Set the tax rate, either as a percentage of the purchase or as an
    absolute amount (depending on what selection you made in step 3).

    **Country, Region, Zip:** This tax rate will apply only to buyers located in
    the area you designate. To designate an entire country, leave *Region* and
    *Zip* blank; to designate an entire region, leave *Zip* blank.

7.  Click *Save*. Repeat steps 5 and 6 to add additional settings.

If you assign multiple settings to a single tax category (but specifying
different geographical areas), then the rate appropriate to the buyer's location
will be used.
