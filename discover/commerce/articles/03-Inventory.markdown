# Inventory [](id=inventory)

Initially, inventory levels for all of your products have to be entered
manually. From then on, inventory levels will be tracked automatically as orders
are filled.

To enter an inventory level, navigate to the SKU in question (*Site Menu* &rarr;
*Commerce* &rarr; *Products* &rarr; *SKUs* tab), click the options
(![options](../images/icon-options.png)) button and select *Edit*. Then
select the inventory tab on the left and enter the correct quantity. Hold on,
though--this just for a basic use case where all you're inventory is in one
place. If your products are being warehoused in more than one location, read on
to learn about best practices for your situation.

![Figure 1: Don't forget to click *Save*.](../images/inventory.png)

<!--Is there anything that needs to be done to ensure that inventory is tracked
automatically? I can't get the inventory count in catalog/skus to count change
unless I change it manually.-->

## Warehouses [](id=warehouses)

For some use cases, it's not necessary to bother with the warehouse feature--for
instance, if you plan to charge a flat rate for all shipping, or if all you're
selling is, say, digital music files.

Some users, however, may want to calculate shipping costs on the basis of
distance. To use this feature, go to *Site Menu* &rarr; *Commerce* &rarr;
*Settings*, and click on the *Shipping* tab. Make sure the *Single Address*
radio button is selected and enter the address of the warehouse.

+$$$

Before you can enter the address for a warehouse, you'll need to enter and
activate the applicable country and region (state or province) at the
*Countries* tab at *Site Menu* &rarr; *Commerce* &rarr; *Settings*. For more
detail see (link to settings/countries).

$$$

### Using Multiple Warehouses [](id=using-multiple-warehouses)

Sometimes, however, inventory tracking needs to be a little more sophisticated.
What if, for example, a retailer operates out of more than one warehouse? Your
inventory needs to record changes accurately when products are shipped from the
warehouse nearest their destination.

To enter multiple warehouse locations, go to *Site Menu* &rarr; *Commerce*
&rarr; *Settings*, click on the *Shipping* tab and select the radio button
labeled *Use Closest Warehouse. A new tab, *Warehouses* will appear at the top
right. Go to it and use the *Add* (![Add](../images/icon-add.png)) button
to add as many warehouses as you want.

### Geocoding Warehouses [](id=geocoding-warehouses)

Here's the catch. All by itself, @commerce@ isn't able to tell solely on the
basis of a street address which warehouse is closest to the delivery
point--maintaining a database with the GPS coordinates of every address on the
planet isn't in the mission profile.

The default solution is is integration with Microsoft's Bing Maps. Once you have
obtained a maps API key from Microsoft go to *Control Panel* &rarr; *System
Settings* and click on the *Commerce* tab. Then click on *Bing Geocoder* and
paste your Bing Maps key into the field provided.

+$$$

As of the time of this writing, instructions on obtaining a Bing API key were
published at 
[Microsoft Developer Network][https://msdn.microsoft.com/en-us/library/ff428642.aspx].
Microsoft makes Bing maps integration available free of charge to some users
depending on use case and volume.

$$$

Once your key has been set, return to *Site Menu* &rarr; *Commerce* &rarr;
*Settings* to set up your warehouses. When using a single warehouse (in the
*Shipping* tab), the warehouse will be automatically geocoded when you enter the
address. When multiple warehouses are in use, go to the *Warehouse* tab and
click ![options](../images/icon-options.png) &rarr; Geolocate for each
facility.

## Measurement Units [](id=measurement-units)

Once you have your bearings--literally--

Once your warehouses are geocoded and your measurement units are set, you're
ready to define shipping options. That's covered in the next section.
