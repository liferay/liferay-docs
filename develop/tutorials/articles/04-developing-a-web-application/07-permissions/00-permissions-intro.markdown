# Using Resources and Permissions [](id=using-resources-and-permissions)

You now have an application that uses the database for data storage. This is a
great foundation to build on. What comes next? What if users want a Guestbook 
that's limited to certain trusted people? To do that, you have to implement 
permissions. 

Thankfully, with @product@ you don't have to write an entire permissions system 
from scratch: the framework provides a robust and well-tested permissions system 
that you can implement quickly. You'll follow Liferay's well-defined process for 
implementing permissions, called *DRAC*: 

- **Define** all resources and permissions
- **Register** all defined resources in the permissions system
- **Associate** permissions with resources
- **Check** for permission before returning resources

Ready to start? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-1/defining-permissions">Let's Go!<span class="icon-circle-arrow-right"></span></a>
