# Implementing Permission Checks [](id=implementing-permission-checks)

Now that your guestbook and guestbook entry web services exist, you must 
implement permission checks for them. Implementing permission checks for a web 
service ensures that only users with the correct permissions can invoke the web 
service. To implement permission checks in your remote services, you'll use the 
`GuestbookModelPermission`, `GuestbookPermission`, and `EntryPermission` helper 
classes that you created earlier. These classes provide helper methods for 
checking permissions. The helper methods in `GuestbookModelPermission` check 
top-level model permissions. For example, you can use 
`GuestbookModelPermission`'s helper methods to check if a user can add a new 
guestbook or guestbook entry. If, on the other hand, you must check if a user 
can update or delete an existing guestbook or guestbook entry, you'll use 
`GuestbookPermission` or `EntryPermission`. 

Once you've secured your remote services with permission checks, you'll update
your portlet classes to call remote services instead of local services. This 
prevents attackers from trying to bypass your app's UI by playing with URL 
parameters to access sensitive portions of your app. 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/implementing-permission-checks-at-the-service-layer">Let's Go!<span class="icon-circle-arrow-right"></span></a>
