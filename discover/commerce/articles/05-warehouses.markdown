# Warehouses [](id=warehouses)

Initially, inventory levels for all products must be entered manually. From then
on, inventory levels are tracked automatically as orders are filled. To set
a product's inventory level, see
[SKUs](/web/liferay-emporio/documentation/-/knowledge_base/1-0/skus#inventory).
First, however, you must have your warehouses in order.

## Using a Single Warehouse [](id=using-a-single-warehouse)

If you have only one warehouse, there is no need to geocode its location.
However, if you integrate your system with a shipping company's (see 
[Shipping Methods](/web/liferay-emporio/documentation/-/knowledge_base/1-0/shipping-methods)
for details), you must enter your warehouse's address to calculate shipping
costs.

+$$$

Before you can enter the address for a warehouse, you may need to enter and
activate the applicable country and region (state or province) at the
*Countries* tab at *Site Menu* &rarr; *Commerce* &rarr; *Settings*. For more
detail see
[Settings](/web/liferay-emporio/documentation/-knowledge_base/1-0/countries).

$$$

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click the
    *Shipping* tab.

2.  Make sure the *Use Single Address* radio button is selected.

3.  Fill in the form, giving your warehouse a name and entering its address.
    Click *Save*.

## Using Multiple Warehouses [](id=using-multiple-warehouses)

If you have more than one warehouse, then you not only must track inventories at
different locations separately, but also must determine from which warehouse
a product should be shipped.

First, you must enter the location of each warehouse. Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click the
    *Shipping* tab.

2.  Make sure the *Use Closest Warehouse* radio button is selected and click
    *Save*. A new tab, *Warehouses*, appears at the top of the screen.

3.  Click the ![Add](../images/icon-add.png) button on the *Warehouses* tab.
    Enter a name, description, and address for the warehouse. The *Active* box
    must be checked before the warehouse can be used, but the
    latitude and longitude fields can be left blank for now (see *Geocoding*,
    below). Click *Save*.

4.  Repeat steps 2 and 3 until all of your warehouses have been added.

## Geocoding Warehouses [](id=geocoding-warehouses)

@commerce@ automatically processes orders so products are shipped from the
closest warehouse. On its own, however, the software cannot calculate which
warehouse is closest to the delivery point solely on the basis of street
address---maintaining a record of the coordinates of every address on the planet
isn't in the mission profile.

The default solution is integration with Microsoft's Bing Maps, which Microsoft
makes available free of charge---though this depends on use case and volume. At
the time of this writing, instructions on obtaining a Bing API key are published
at [Microsoft Developer Network](https://msdn.microsoft.com/en-us/library/ff428642.aspx).

Once you have an API key, follow these steps to set products to ship from the
closest available warehouse:

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* and
    click on *Commerce* &rarr; *Bing Geocoder*.

2.  Paste your key from Microsoft into the *API Key* field and click *Save*.

3.  Return to the *Warehouses* tab in *Site Menu* &rarr; *Commerce* &rarr;
    *Settings* and click ![options](../images/icon-options.png) &rarr;
    *Geolocate* for each facility.
