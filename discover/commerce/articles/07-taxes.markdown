# Taxes [](id=taxes)

Collecting taxes is accomplished by *Tax Categories* and *Tax Methods*.

A **Tax Method** defines how tax is calculated. There are two kinds of tax
methods: *fixed* and *by address*.

A **Tax Category** is a container for one or more tax methods. Products can be
taxed differently by assigning different tax categories to them.

## Tax Categories [](id=tax-categories)

To apply any tax rate, you must first have at least one tax category. Follow
these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click the *Taxes* tab
    and then the *Tax Categories* sub-tab.

2.  Click ![Add](../images/icon-add.png). Give your tax category
    a name and a description and then click *Save*.

See
[Configuration](/web/liferay-emporio/documentation/-/knowledge_base/1-0/configuration#tax-category)
for more on how to assign a tax category to a product.

## Tax Methods [](id=tax-methods)

There are two kinds of tax methods: fixed and by address. The fixed method
applies the same rate to a product each time it is purchased, while the by
address method applies only to buyers within a specified geographical region.

### Creating a Fixed Tax Method [](id=creating-a-fixed-tax-method)

Creating a fixed tax method sets rates for each tax category independently. The
tax collected then depends on the tax category assigned to a product. Follow
these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click the *Taxes* tab
    and then the *Tax Methods* sub-tab. Click on *Fixed Tax Rate*.

2.  Enter a name and description for the method in the appropriate fields.

3.  Check the *Percentage* box if the tax should be defined as a percentage of
    the purchase price. Uncheck the box to define the tax as a fixed amount.

4.  Check the *Active* box to enable the method and click *Save*.

5.  Click the *Tax Rates* sub-tab. This displays a *Rate* field for each tax
    category. Fill in each field and hit *Save*.

### Creating a By Address Tax Method [](id=creating-a-by-address-tax-method)

Taxing by address sets rates for geographical areas independently. You can apply
several by address rates to a single tax category. When a product from that
category is purchased, the tax rate appropriate to the buyer's location is used.
Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click the *Taxes* tab
    and then the *Tax Methods* sub-tab. Click on *By Address*.

2.  Enter a name and description for the method in the appropriate fields.

3.  Check the *Percentage* box if you want the tax to be defined as a percentage
    of the purchase price. Uncheck the box to define the tax as a fixed amount.

4.  Check the *Active* box to enable the method and click *Save*.

5.  Click the *Tax Rate Settings* sub-tab and then on the
    [Add](../images/icon-add) button.

6.  Fill out the following fields:

    **Tax Category:** This tax rate only applies to products assigned to the tax
    category you select.

    **Rate:** Set the tax rate, either as a percentage of the purchase or as an
    absolute amount (depending on what selection you made in step 3).

    **Country, Region, Zip:** This tax rate applies only to buyers located in
    the area you designate. To designate an entire country, leave *Region* and
    *Zip* blank; to designate an entire region, leave *Zip* blank.

7.  Click *Save*. Repeat steps 5 and 6 to add additional settings.

If you assign multiple settings to a single tax category (but specify different
geographical areas), the rate appropriate to the buyer's location is used.
