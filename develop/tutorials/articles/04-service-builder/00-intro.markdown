# Generating Your Service Layer [](id=generating-your-service-layer)

The word *service* can mean many specific things, but the general dictionary
definition states that it's "an act of helpful activity." Everyone has
experienced this in some way. Whether it's an act of kindness from a
friend or stranger or a service you pay for, in all instances, you have a need,
and the service provides for that need. 

Data-driven applications by their nature need access to a service for storing
and retrieving their data. In a well-designed application, the application asks
for data, and the service fetches it. The application can then display this data
to the user, who reads it or modifies it. If the data is modified, the
application passes it back to the service, and the service stores it. The
application doesn't need to know anything about *how* the service does what it
does. It trusts the service to handle storing and retrieving the data, freeing
the application to be as robust as it needs to be. 

This is what is called *loose coupling*, and it's a hallmark of good application
design. If your application's service layer is self-contained, then you can
swap it out for a better service layer when something more robust comes along,
and you won't have to modify the application to take advantage of it. 

Well, something more robust has come along, and it's called Service Builder.
Using the Object-Relational Mapping engine provided by Hibernate along with a
sophisticated code generator, Service Builder can help you implement your
service layer in a fraction of the time it would normally take. But this isn't
just any ordinary service layer: Service Builder also optionally helps you with
remote services in multiple protocols, such as JSON and SOAP. And if you need to
do something really funky with the database, it gets out of your way and lets
you use whatever SQL queries you want. 

Intrigued? We hope so. We'll cover the following topics: 

- What is Service Builder? 
- Defining Your Object-Relational Map
- Writing the Local Service Class
- Calling Local Services
- Using Model Hints
- Enabling Remote Services
- Developing Custom SQL Queries
- Configuring `service.properties`

As you can see, there is a lot to cover, so let's start by describing Service
Builder in more detail. 
