# Refactoring the Prototype

First you created a Guestbook portlet prototype. Then you wrote a 
`service.xml` file to define your application's data model and used Service 
Builder to generate back-end code including model, service, and persistence 
layers. You added custom service methods using the appropriate extension 
points: your entities' `*LocalServiceImpl` classes. Now you need to take the
original prototype and integrate it with the new backend to create a fully
functional application.

Before you get to do all that, you have some housekeeping to take care of to
conform your application to best practices.
