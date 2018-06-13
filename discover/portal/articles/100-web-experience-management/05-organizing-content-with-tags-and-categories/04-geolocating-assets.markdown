# Geolocating Assets [](id=geolocating-assets)

Geolocation is a feature that enables you to add the geographic coordinates 
where an asset was created, as metadata to your assets. You can add geolocation 
metadata to your web content, data lists, and documents & media. This feature 
is provided for you out-of-the-box by @product-ver@. However, you must first 
enable it in your assets in order to use it.

Having the ability to know where your user data is coming from, allows you to 
visualize patterns quickly and efficiently, so you can create a optimized 
experience that caters to the needs of the end user.

Let's examine how you can enable geolocation in your web content.

## Geolocating Web Content [](id=geolocating-web-content)

To use geolocation in your web content, you'll need to create a [structure and template](/discover/portal/-/knowledge_base/7-1/designing-uniform-content)  
that include a Geolocation field.  first need to a that includes a Geolocation 
field.

![Figure 1: Add a geolocation field to your structure to enable geolocation in your web content.](../../../images/geo-structure.png)

1.  Create a structure which includes a Geolocation field like in the image 
    above.

2.  Create a new template and select the structure you just created 
    with the geolocation field.

3.  Scroll down to the *Script* heading and locate the *Fields* section. Here 
    you will see *Content* and *Geolocation* snippets.

4.  Click on the snippets to add them to the template, and *Save*.

![Figure 2: Add the Content and Geolocation snippets to quickly create your web content template.](../../../images/web-content-geolocation-template.png)

To set your location for the web content, you can share your location with the 
browser, type a specific address into the address bar on the map, or even drag 
the indicator and drop it in any point in the map and the address will be 
automatically updated to reflect the new point. Once the web content is saved, 
the location is added as metadata to the web content.

![Figure 3: You can enter your location in the address bar, move the indicator to a location, or share your location with the browser.](../../../images/web-content-geo-create.png)

+$$$

Note: Depending on your browser settings, you may need to configure it, so the
geolocation can obtain your location.

![Figure 4: Make sure your browser is configured to share your location.](../../../images/share-location-dialog.png)

$$$

## Geolocating Data Lists [](id=geolocating-data-lists)

To use geolocation in your dynamic data lists, you first have to create a data
definition that includes a geolocation field.

1.  Open the *Menu* and navigate to *Content* &rarr; *Dynamic Data Lists*. 

2.  Click the *Options* menu and select *Manage Data Definitions*.

3.  Click the *Add* button to create a new data definition.

4.  Enter a name, optional description, and parent data definition if you have 
    one.
    
5.  Scroll down and add a *Geolocation* field to the data definition, along 
    with any other fields you wish to add and *Save*.

6.  Go back to the Dynamic Data Lists screen and click the *Add* button () to 
    create a new list.

7.  Enter a name and optional description.

8.  Finally, click the *Select* button and  choose the newly created data 
    definition.

Now that your data list is complete you can use the [Data List Display portlet](/discover/portal/-/knowledge_base/7-1/creating-data-lists) 
to display it.

## Geolocating Documents and Media [](id=geolocating-documents-and-media)

To enable geolocation in your documents & media, you must first create a custom
document type that includes geolocation metadata. You can add geolocation
metadata as part of a Metadata Set or as part of the new document type. To add 
geolocation metadata as part of a Metadata Set:

1.  Open the *Menu* () and navigate to *Content* &rarr; *Documents and Media*. Open the *Options* () menu, and select *Metadata Sets*.

2.  Click the *Add* () button and enter a name, optional description, and 
    Parent Metadata Set if you have one.

3.  Scroll down and add a *Geolocation* field, along with any additional fields 
    you wish to have, and *Save*.

To create the new document type with geolocation:

1.  Navigate to *Documents and Media*, open the *Options* () menu and select 
    *Document Types*. 

2.  Click the *Add* button () and enter a name and optional description.

3.  Scroll down to the Main Metadata Fields heading and add a *Geolocation* 
    field along with any  other fields you wish to have for the document type.
    
4.  If you are using a Metadata Set, scroll down to the Additional Metadata 
    Fields heading, click the *Select Metadata Set* button.

5.  Choose your Metadata Set with the geolocation metadata and *Save*.

6.  Navigate back to the *Documents and Media* screen and click the *Add*
    button () and select your newly created document type.

7.  Fill out the information for the document, and just as with the web 
    content, your location is automatically obtained from the browser and added 
    to your document.

Once your assets are geolocation enabled, you can use the [Asset Publisher](/discover/portal/-/knowledge_base/7-1/publishing-assets) 
to display the location of the assets on a map, using the map display template.
Check out the [Configuring Display Settings](/discover/portal/-/knowledge_base/7-1/using-the-asset-publisher#configuring-display-settings)
section to learn more.

![Figure 5: The Asset Publisher can display your geolocated assets on a map.](../../../images/geo-map.png)

Next, you'll learn about @product@'s Search application.
