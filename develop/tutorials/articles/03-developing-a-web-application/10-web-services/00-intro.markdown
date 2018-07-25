# Generating Web Services [](id=generating-web-services)

Assets opened the door to support many features of @product@'s development
framework. There's more to cover, but the Guestbook app's back-end is now mature 
enough to widen its appeal. 

Right now, the Guestbook app's back-end services can only be accessed by modules 
in the same OSGi container. If you want to read or post Guestbook entries, you 
have to write and deploy native code on the server. This is fine for some, but 
others want more: web clients on a different platform; standalone applications 
using Electron or some other framework; or mobile apps for Android or iOS. Web 
services power all of these. 

You now have an application with back-end services and a front-end web client 
running in the same container. Web services make it possible to have multiple 
front-end clients on multiple platforms that access the same back-end. This 
makes it possible to build *headless* applications on @product@, with multiple 
front-ends elsewhere. 

![Figure 1: @product@ makes it easy to write multi-client applications.](../../../images/multi-client-application.png)

Next, you'll use Service Builder to create the Guestbook app's web services. 
When you finish, authorized clients can then consume these web services. 

Ready to start? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/creating-remote-services-with-service-builder">Let's Go!<span class="icon-circle-arrow-right"></span></a>
