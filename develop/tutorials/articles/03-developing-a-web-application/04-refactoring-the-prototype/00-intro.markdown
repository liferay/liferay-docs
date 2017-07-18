# Refactoring the Prototype

First you created a Guestbook portlet prototype. Then you wrote a 
`service.xml` file to define your application's data model and used Service 
Builder to generate back-end code including model, service, and persistence 
layers. You added service methods using the appropriate extension 
points: your entities' `*LocalServiceImpl` classes. Now you need to take the
original prototype and integrate it with the new back-end to create a fully
functional application.

But there are differences between the prototype and the real application. One
big difference has been accounted for in the back-end: users can create multiple
Guestbooks that each have their own entries. On the front-end, however,
Guestbook creation should be relegated only to site administrators. For that
reason, you'll create another portlet called Guestbook Admin and place it in the
Content menu. 

To turn this application from a prototype into a full-fledged Liferay web
application, then, you'll make these changes: 

- Modify the folder structure of your view layer to account for the
  administrative portlet
- Set the Display Category for so users can find the application more easily
- Create a file to store text keys for the application
- Change the controller to call your new Service Builder-based back-end
- Update the view so it can display multiple Guestbooks in tabs

Ready to begin? 

