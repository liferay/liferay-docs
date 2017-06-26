# Service Builder
Service Builder is Liferay's code generation tool for defining custom object
models called entities. Service Builder generates your application's service
layer and provides a clean separation between your object model and code for
the underlying database.

Now you will use Service Builder to generate create, read, update, delete, and
find operations for your application. You'll also use Service Builder to
generate the necessary model, persistence, and service layers for your
application all using one XML file. Then you can add the necessary business
logic for your application.

## Service Layer
This application is a data-driven applications, uses services for storing and 
retrieving data. The application asks for data, and the service fetches it. The 
application can then display this data to the user, who reads it or modifies 
it. If the data is modified, the application passes it back to the service, and 
the service stores it. The application doesn’t need to know anything about how 
the service does what it does.

You'll create a Service Builder project and use the `service.xml` file with all 
of the necessary entities to generate this code.

1. In Eclipse, right-click on your package and select *New* &rarr; *Liferay Module Project*.
2. Name the project `guestbook`.
3. Select `service-builder` for the Project Template Name.
4. Click *Next*.
5. Enter `com.liferay.docs.guestbook` for the *Package Name*.
6. Click Finish.

This will create two modules that contain the basic workings and structure of 
your Service Layer, but you still need to generate the persistence code, create 
the correct business logic, and connect it back to the portlet application.