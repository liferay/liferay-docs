# Measurement Units [](id=measurement-units)

@commerce@ comes with a number of common measurement units already defined, but
if you or your shipping provider use different units, you may need to change the
default settings.

Among the measurement units defined, two---one for weight and one for
distance---must be designated as *primary units*. The primary units are the only
units used by the catalog and by the 
[variable-rate shipping method](/web/liferay-emporio/documentation/-/knowledge_base/1-0/variable-rate-shipping). 

If you use Courier Shipping to integrate your shipping cost calculations with
a shipping provider's system, you must have the provider's units defined as
well. If the provider uses units that are different from your primary, the
conversion is handled automatically.

## Adding a New Measurement Unit [](id=adding-a-new-measurement-unit)

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and select the
    *Measurement Units* tab. Then choose the appropriate sub-tab for
    *Dimensions* or *Weight*.

2.  Click ![Add](../../images/icon-add.png) and fill in the following
    fields:

    **Name:** Enter the unit's name.

    **Key:** A unique identifier for referring to the unit programmatically.
    This field auto-fills.

    **Primary:** Check this box to set the new unit as the store's primary.

    **Ratio to:** If the unit is not primary, enter its ratio to the primary in
    terms of how many of the new unit are equal to one primary. For example, if
    the primary unit is *kilogram* and the new unit is *metric ton*, the ratio
    should be 0.001. If the unit is primary, then you must adjust the ratios of
    your other units (see below).

    **Priority:** Sets the order in which units are displayed on a page. Lower
    numbers come first.

3.  Click *Save*.

## Choosing a Primary Measurement Unit [](id=choosing-a-primary-measurement-unit)

The default primary units are pound for weight and inch for distance. Follow
these steps to set another unit as primary:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click the
    *Measurement Units* tab. Select the appropriate sub-tab for either
    *Dimensions* or *Weight*.

2.  Find the measurement unit you want to set as primary. Click on the
    ![Options](../../images/icon-options.png) button next to it and click
    *Edit*.

3.  Check the *Primary* box. Click Save.

4.  Once a new primary has been set, you must reset the other unit's ratios.
    Click on the ![Options](../../images/icon-options.png) button next to each
    unit, click *Edit*, and change the *Ratio to* value to reflect the new
    primary. The ratio should indicate the number of secondary units that are
    equal to one primary. If the primary is *kilogram*, the ratio would be 1000
    for *gram* and 0.001 for *metric ton*.
