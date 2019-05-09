# The Info Framework

The Info Framework provides enhances flexibility for retrieving, processing, 
and displaying any information, regardless of where the information comes from. 
The Info Framework can process data from the database, created 
through process in memory, or retrieved from an external source.

The Info Framework is a new feature @product-ver@ and currently has limited 
functionality. The current version is the first set for obtaining and 
displaying information from external systems or custom data models. The Info 
framework is also very lightweight, and  does not require you to implement any  
specific interface to use it. That means you can use it for an object even if 
you don't have access to the source. 

Rather than create a monolithic framework which requires all of the features to 
be implemented for the sake of using any, it is a collection of loosely coupled 
micro-frameworks. This lowers the learning curve and minimizes work for 
developers.

| Note: Liferay veterans may notice some similarities between the [Asset Framework](asset-framework-link) and Info 
| Frameworks. The Info Framework is similar in some ways to the Asset 
| Framework -- both provide for rendering information -- but the Info Framework 
| is more generalized. Integrating the Asset Framework in your application 
| requires more effort, but also provides a greater number of features. The two 
| frameworks should not be viewed as being in competition with each other, but 
| as complementary. As an example of this, Assets rendered through the Asset
| Framework are also available through the Info Framework.

## Using the Info Framework

The Info Framework simplifies information handling across the board. You can 
use it to customize your own applications, and it provides new ways to use 
existing Liferay features. In previous versions of Liferay, features like 
Display Pages and the Asset Publisher could only be used if you create an 
`AssetEntry`. With the Info Framework you can define lists to display through 
the Asset Publisher, and any entity that can be represented as a Java class can have a Display Page without creating an `AssetEntry` for that entity.

To use the Info Framework, you create a `InfoItemRenderer` and an Information 
List Provider by implementing `InfoListProvider`. You can use renderers and 
providers together or independently.

### Information List Providers

Information List Providers are used to obtain a list of information items from 
a source. A development must implement the `InfoListProvider` interface, and 
provide the necessary logic for retrieving the information from its source. By 
providing an implementation of the `InfoListProvider` interface, developers can 
provide programmatic retrieval of information of any type, as long as it can be 
represented through a Java class. 

To implement `InfoListProvider` you must implement its four methods:

**`getLabel()`** provides the label that is displayed for this provider in the UI of applications like the Asset Publisher.

**`getInfoList`** provides the information list. This method has two variants: a plain list or a list with pagination and sorting.

**`getInfoListCount`** provides total number of items. This is needed for the 
paginated variant of `getInfoList`.

For an example of how to create Information List providers, see [Creating Information List Providers](new-link).

### Information Item Renderers

Developers can create custom renderers for any type of information. A developer 
must provide an implementation of the `InfoItemRenderer` interface to provide 
programmatic rendering of information. It can be any kind of information as 
long as it can be represented through a Java class. You can create multiple 
renderers for a single type of information as well.

Internally, Liferay's Display Pages use this with *Content* component. Whatever 
content you define for the *Content* field (whether it is **Content** in the 
strict sense or some other entity type), is rendered by the first 
`InfoItemRenderer` class registered that entity.

To create an Information Item Renderer you must create a class that implements 
`InfoItemRenderer` and registers it as a component. Inside that class, all you 
need is the `render()` method and whatever other logic the `render()` method 
will need. To learn to Information Item Renderers, see [Creating Information Item Renderers](new-link).

### Custom Applications

You can also leverage the Info Framework to improve the way you retrieve and 
display information in your Custom Applications. For more information on this, see [Using Providers with Custom Application](new-link).


