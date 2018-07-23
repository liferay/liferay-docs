# Form Permissions

By default, you can grant the following permissions for a form:

-  Delete: Permission to delete the form.
-  Permissions: This gives any role with the permission the ability to configure
   the form's permissions.
-  Add Record: Permission to submit form entries.
-  Update: Permission to update form entries.
-  View: All the other permissions you can configure for the form depend on this
   one. Think about it: if you can't see the form, how can you add a record?

![Figure 14: You can configure the permissions for a form.](../../images/forms-form-permissions.png)

By default, unauthenticated visitors to your site (Guests) can view and fill out
forms, because the *Guest* role has *View* and *Add Record* permissions.

Navigate to the Forms application (*Site Administration* &rarr; *Content* &rarr;
*Forms*). Click the form's  *Actions* button
(![Actions](../../images/icon-actions.png)), and select *Permissions*.  Give the
Guest role the View and Add permissions, then click *Save*.

+$$$

**Note:** By default, all users inherit the Guest role's permissions. The Guest
role represents unauthenticated visitors of your site, and it makes sense that
if you want to allow Guest users to submit forms, you're fine with your site
members and portal users submitting forms, as well. If you want to disable the
automatic inheritance of the Guest role permissions, there's a
[property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Permissions) 
you can set in your `portal-ext.properties` file:

    permissions.check.guest.enabled=false

$$$

Configure appropriate form permissions for each form.
