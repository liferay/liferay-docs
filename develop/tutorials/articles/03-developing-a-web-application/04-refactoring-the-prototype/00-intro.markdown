# Refactoring the Prototype [](id=refactoring-the-prototype)

In an earlier section of this Learning Path, you created a Guestbook portlet 
prototype. Then you wrote a `service.xml` file to define your application's data 
model, and used Service Builder to generate the back-end code (the model, 
service, and persistence layers). You also added service methods using the 
appropriate extension points: your entities' `*LocalServiceImpl` classes. Now 
you need to integrate the original prototype with the new back-end to create a 
fully functional application. 

There are many differences between the prototype and the application you'll 
create. In the back-end, you've already accounted for one big difference: users 
can create multiple Guestbooks that each have their own entries. In the 
front-end, however, only site administrators should be able to create 
guestbooks. Therefore, you'll create another portlet called Guestbook Admin and 
place it in the Content menu for sites. 

To turn this application from a prototype into a full-fledged Liferay web 
application, you'll make these changes: 

-   Modify your view layer's folder structure to account for the administrative 
    portlet
-   Set the Display Category so users can find the application more easily
-   Create a file to store the application's text keys
-   Change the controller to call your new Service Builder-based back-end
-   Update the view so it can display multiple Guestbooks in tabs

Ready to begin? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/organizing-folders-for-larger-applications">Let's Go!<span class="icon-circle-arrow-right"></span></a>
