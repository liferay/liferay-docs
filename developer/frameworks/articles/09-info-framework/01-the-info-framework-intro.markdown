# The Info Framework

@product-ver@ introduces the Info Framework to provide a greater degree of 
extensibility for the most common needs of retrieving, processing and 
displaying any type of information. A key aspect of the Info Framework is that 
it makes no assumptions about the source of the data or how it is represented 
in memory (like which Java class the information is from). It can work with 
information stored in the database, created through some process in memory or 
retrieved from an external source. 

In @product-ver@, the Info Framework still has limited functionality, but it 
sets the foundation for obtaining and displaying information from external 
systems or custom data models in Liferay. It also provides more flexibility in 
customizing how any piece of information is displayed.

The Info Framework is lightweight. By design, it does not require all the 
information to implement any specific interface. This means that you can use it 
with any existing java class, even if you don’t have access to modify it. It 
has also been designed as a collection of loosely coupled micro-frameworks, so 
that developers can choose which features to use and ignore the others. This 
lowers the learning curve and minimizes work for developers.


| Note: Liferay veterans may notice some similarities between the [Asset Framework](asset-framework-link) and Info 
| Frameworks. The Info Framework can be considered a generalization of the | 
| Asset Framework and its design has considered lots of lessons learned with 
| the asset framework. In particular, the Info Framework provides many similar 
| characteristics (such as rendering of information) but with less requirements 
| (such as having an AssetEntry in the database). We have also made sure the 
| two frameworks play well together so that when the Asset Framework is used, 
| for example for rendering an asset, the renderer is also available through 
| the Info framework. The Info framework is not meant to be a full replacement 
| for the Asset Framework. However if the Asset Framework seems too complex for 
| your needs, the Info Framework might be a better fit for you.

## Using the Info Framework

The Info Framework facilitates generalizing information handling. Custom 
applications can use it to make them more generic and extensible. 

Some of the out of the box Liferay features us it to achieve that same goal. In particular, Liferay 7.2 uses it in two ways:

*  The Asset Publisher can display Assets from  Information Lists defined by 
    the Info Framework.
    
*  Display Pages, which previously only worked for an AssetEntry, can now 
    leverage the InfoFramework, to create display pages for any type of 
    information that can be represented by a Java class. This allows 3rd party 
    developers to add support for display pages to various like entities 
    Orders, Categories, and Events that are not Assets.

There are currently two tools provided by the Info Framework: Information Item 
Renders and Information List Providers. You can create an Information List 
Provider to obtain a list of information items from a source, or create an 
Information Item Renderer to provide a custom renderer for any type of 
information. These two features can be used together or separately.

### List Providers

Information List Providers are used to obtain a list of information items from 
a source. To do this, a developer must implement the `InfoListProvider` 
interface, and provide the necessary logic for retrieving the information from 
its source. By providing an implementation of the `InfoListProvider` interface, 
developers can provide programmatic retrieval of information of any type, as 
long as it can be represented through a Java class. 

`InfoListProvider` has four methods to implement:

**`getLabel()`** provides the label that is displayed for this provider in the UI of applications like the Asset Publisher.

**`getInfoList()`** provides the information list. This method has two variants: a plain list or a list with pagination and sorting.

**`getInfoListCount()`** provides total number of items. This is needed for the paginated variant of `getInfoList`.

For an example of how to create Information List providers, see [Creating Information List Providers](new-link).

###Item Renderers

Developers can create custom renderers for any type of information. To do this, 
a developer must provide an implementation of the `InfoItemRenderer` interface 
to provide programmatic rendering of information. It can be any kind of 
information as long as it can be represented through a Java class. You can 
create multiple renderers for a single type of information as well.

Internally, Liferay's Display Pages use this from the  Content component. When 
it is added to a display page template, this component will render whatever 
piece of information is shown through that template (whether it is Content in 
the strict sense or some other entity type), is rendered by the first 
`InfoItemRenderer` class registered that entity. Information Item Renders will 
be leveraged further in future Liferay versions.

To create an Information Item Renderer you must create a class that implements 
`InfoItemRenderer` and registers it as a component. Inside that class, all you 
need is the `render()` method and any additional logic the `render()` method 
will need. To learn to Information Item Renderers, see [Creating Information Item Renderers](new-link).

