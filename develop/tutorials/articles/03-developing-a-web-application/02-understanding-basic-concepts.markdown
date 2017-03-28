# Modules 
Modules are the core building blocks of Liferay development. Every application 
is made from one or more modules. The goal of a module is to have each 
functional piece of an application encapsulated in a separate module. All of 
the code for each part of the application is contained within each discrete 
module, and you could swap out implementations of any module, more or less at 
will. Each module is bundled up and package in a JAR file. The JAR can then be 
deployed through your application server. 

As one example, if you use the Model View Controller design pattern, you could 
create one module to encapsulate the Model, one for the View, and one for the 
Controller. A new implementation of the View (or Model or Controller) could be 
swapped in at any point, without needing to make any changes to the Model or 
Controller, and without needing to repackage or redeploy them. 

A module can contain any combination of JSPs, Java classes, properties, or any 
other kind of binary or text data. The module will also need a manifest file to 
let everyone know exactly what is inside of it. In fact, technically speaking 
your module would be perfectly valid if it contained nothing but a manifest. 


# Components 
If the module is what surrounds and binds the pieces of your application, the 
component is the object which contains the actual functionality. Component is a 
big broad word which can define many different things from a Java and OSGi 
perspective, but the idea that ties all of those things together is that a 
component is some object which provides a specific functionality. Components 
are deployed inside of Modules, and they provide a well defined way to build 
features for your application. 

For the application that you will build, you'll use the Declarative Services 
(DS) component implementation. With Declarative Services, you "declare" that an 
object is a component, and then the services of that component are registered. 
If you want to then use that component, you simply invoke the service as 
defined in the Service Registry. 

# Services 
In OSGi, a service defines the specific functionality provided by a component 
and registered with the Service Registry. So from the top down: 

*A Module contains a Component. 
*The Component provides some functionality. 
*That functionality is defined by the Service. 
*The Service is declared in the Service Registry. 

Using Services, we can access the methods of other components contained in 
other modules. Let's say that our you have a Component named "light-years" 
which takes the name of a star as an input and returns the number of light 
years that star is from Earth as an integer. When the module containing that 
component is deployed to the OSGi container its services are registered with 
the Service Registry. Now any other modules can use those services. If you then 
deployed a portlet component named "star-map" with a clickable image map of our 
galaxy, that portlet could reference the service provided by the "light-years" 
component to display how far the clicked star is from earth. 

The beauty here is that I could create a new implementation of "light-years" 
and "star-map" wouldn't be any the wiser, just like I could make a totally 
different portlet with a totally different way to get the name of the star, but 
all "light-years" needs to know is how to send the final data. 