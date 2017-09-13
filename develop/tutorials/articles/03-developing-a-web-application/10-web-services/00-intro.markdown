# Creating Your App's Web Services [](id=creating-your-apps-web-services)

Assets opened the door to support many features of Liferay's development
framework. There's more there to cover, but the Guestbook app is now mature
enough in its back-end to start widening its appeal. 

Right now, the Guestbook application's back-end services can only be accessed by
modules in the same OSGi container. If you want to read or post Guestbook
entries, you have to write native code to be deployed on the server. Some people
want only this. Others want more: web clients on a different platform;
standalone applications using Electron or some other framework; or
mobile applications for Android or iOS. What's the one thing that powers all
this? Web services. 

You now have an application with back-end services and a front-end web client
running in the same container. Web services make it possible to have multiple
front-end clients on multiple platforms that access the same back-end through
web services. This makes it possible to build not only what you've built, but
also to build "headless" applications on @product@ with multiple front-ends
everywhere. 

![Figure x: @product@ makes it easy to write multi-client applications.](../../../images/multi-client-applications.png)

Next, you'll use Service Builder to create remote web services for the 
Guestbook app. When you finish, authorized clients can then consume your app's 
web services. 

Ready to start? 
