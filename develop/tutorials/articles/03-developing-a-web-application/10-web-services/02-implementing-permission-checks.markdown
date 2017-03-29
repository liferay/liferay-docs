# Implementing Permission Checks

Now that your guestbook and guestbook entry web services are up and running, 
it's time to implement permission checks for them. Implementing permission 
checks for a web service ensures that only users with the correct permissions 
can invoke the web service. To implement permission checks in your remote 
services, you'll use the `GuestbookModelPermission`, `GuestbookPermission`, and 
`EntryPermission` helper classes that you created in an earlier section of the 
Learning Path. These classes provide helper methods for checking permissions. 
The helper methods in `GuestbookModelPermission` are for checking top level 
model permissions. For example, if you need to check whether a user has 
permission to add a new guestbook or guestbook entry, you can use 
`GuestbookModelPermission`s helper methods. If, on the other hand, you need to 
check whether a user has permission to update or delete an existing guestbook or 
guestbook entry, you can use `GuestbookPermission` or `EntryPermission`. 

Once you've secured your remote services with permission checks, you'll update 
your portlet classes so that they call the remote services instead of the local 
services. This prevents attackers from trying to bypass your app's UI by playing 
with URL parameters to access sensitive portions of your app. 

## Implementing Permission Checks at the Service Layer

To implement permission checks for the `guestbook-service` module's remote 
services, use the following steps: 

1. In `GuestbookServiceImpl`, replace the `addGuestbook`, `deleteGuestbook`, and 
   `updateGuestbook` methods with the following: 
