# Creating a Persistence Framework for JSF Portlets

Before you begin developing the guestbook's buttons and actions, you'll generate
the persistence framework using Service Builder. By using Service Builder, you
create a persistence layer and service layer that are responsible for retrieving
your model data.

If you've read the MVC learning path, it goes specifically through creating a
persistence framework for the MVC version of the guestbook portlet; but could
you follow the same steps for a JSF portlet? The answer is *yes*. 

The functionality of the persistence framework for a JSF portlet works exactly
the same as an MVC portlet, meaning you can create the persistence layer the
exact same way you would with an MVC portlet using Service Builder. 

For your JSF portlet, you can complete the [Using Service Builder to Generate a
Persistence
Framework](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/using-service-builder-to-generate-a-persistence-fr)
learning path to successfully generate your persistence framework. Once you've
finished generating your persistence layer, return back to this point and
continue on to the next section to learn how to integrate the persistence
framework into your JSF portlet. 

## Integrating Your Persistence Framework into Your JSF Portlet

Now that you've generated your persistence framework, its time to integrate it
into your JSF guestbook so you can use it. On the persistence side of things,
Service Builder has generated all the basic functionality you'll need for the
JSF guestbook. The next step is defining the service layer, which is the buffer
between your controller and your persistence layer. 

Before you get started defining the service layer, there's one additional thing
you'll need to add to your `service.xml` file. The JSF guestbook will have a
slightly different implemenation of its service layer compared to the MVC
portlet, so we'll need to account for that slight change in the way your
services were built. 

1. Open your JSF guestbook's `service.xml` file. Verify that you're in the
Overview tab. 

2. Expand the Guestbook entity and click on *Finders*. 

3. Click the *+* button to the right of the list of finders and give it the name
   *Name* with the return type *Collection*. 

4. Click the *Build Services* button from the top right of the screen. By doing
this, you'll rebuild the services you built previously. 

The new finder you created will aid in finding a guestbook table by name,
instead of solely relying on its `GroupId`. You'll refer to this in the next
section. 

The next thing you'll need to do is implement a service layer for your
*Guestbook* entity; after that, you'll implement a service layer for your Entry
entity. 

###































