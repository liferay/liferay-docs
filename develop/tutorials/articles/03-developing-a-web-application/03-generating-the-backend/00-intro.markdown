# Generating the Back-end [](id=generating-the-back-end)

So far, you have a prototype application that uses Liferay's 
Model-View-Controller (MVC) portlet framework. MVC is a great design pattern for 
web applications because it splits your application into three parts (the model, 
the view, and the controller). This lets you swap out those parts if necessary. 

A *persistence* layer and a *service* layer are added to these three parts of 
your application. To get the prototype working, you used Portlet Properties to 
create a rudimentary persistence layer. Since this isn't a long-term solution, 
you'll now replace that layer by persisting your guestbooks and their entries to 
a database. 

![Figure 1: Service Builder generates the shaded layers of your application.](../../../images/application-layers.png)

*Service Builder* is Liferay's code generation tool for defining object models
and mapping those models to SQL databases. By defining your model in a single
XML file, you can generate your object model (the M in MVC), your service layer,
and your persistence layer all in one shot. At the same time, you can generate
web services (more on that later) and support every database @product@ supports. 

Ready to begin? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder-0">Let's Go!<span class="icon-circle-arrow-right"></span></a>

