---
header-id: using-resources-and-permissions
---

# Using Resources and Permissions

[TOC levels=1-4]

Your application is a great foundation to build on. What comes next? What if
users want a Guestbook that's limited to certain trusted people? What if you
don't want just any old user to go around editing or deleting people's Guestbook
entries? To do that, you have to implement permissions. 

Thankfully, with @product@ you don't have to write an entire permissions system 
from scratch: the framework provides a robust and well-tested permissions system 
that you can implement quickly. You'll follow Liferay's well-defined process for 
implementing permissions, called *DRAC*: 

- **Define** all resources and permissions
- **Register** all defined resources in the permissions system
- **Associate** permissions with resources
- **Check** for permission before returning resources

If you get stuck, [source code](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/guestbook/07-permissions/com-liferay-docs-guestbook) 
for this step is provided. 

Ready to start? 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/defining-permissions">Let's Go!<span class="icon-circle-arrow-right"></span></a>
