# Service Builder Persistence [](id=service-builder-persistence)

Liferay's Service Builder can generate your project's persistence layer by
automating the creation of interfaces and classes. Your application's
persistence layer persists data represented by your configured entities to a
database. In fact, your local service implementation classes are responsible for
calling the persistence layer to retrieve and store your application's data. So
instead of taking the time-consuming route of writing your own persistence
layer, you can use Liferay's Service Builder to quickly define your entities and
generate the layer instantaneously.

In this section of tutorials, you'll learn how to define an object-relational
map and generate your persistence layer from that map. You'll also learn about
the local and remote services Service Builder generates, and how you can use
them for your own application. You'll also discuss how to use the
`ServiceContext` class, model hints, SQL queries, and Hibernate's criteria API. 
