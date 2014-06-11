# Integrating Your Persistence Framework into Your Application

Once you've generated your persistence framework with Service Builder, your next
task is to bring all that functionality into your application. On the
persistence side of things, Service Builder has generated all the basic
functionality you'll need for the Guestbook application. All you need to do is
define the service layer, which is the buffer between your controller and your
persistence layer. It is this layer that gives you the flexibility in your
application to swap components in and out, should you decide that Service
Builder is not for you. 

1. Open the `com.liferay.docs.guestbook.service.impl` package. Service Builder
   has generated several stub classes for you here, two for each entity. The
   `-LocalServiceImpl.java` class is your implementation for local services, and
   the `-ServiceImpl.java` class is your implementation for remote services. 

2. Open the `GuestbookLocalServiceImpl.java` class. 
