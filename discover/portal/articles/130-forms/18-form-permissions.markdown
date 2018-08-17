# Form Permissions [](id=form-permissions)

To access a form's permissions, first navigate to the Forms app in 
*Site Administration* (your site's menu) &rarr; *Content* &rarr; *Forms*. Then 
click the form's *Actions* button 
(![Actions](../../images/icon-actions.png)), 
and select *Permissions*. 

By default, you can grant these permissions for a form: 

**Delete:** Delete the form. 

**Permissions:** Access and configure the form's permissions. 

**Add Form Instance Record:** Submit form entries. 

**Update:** Update form entries. 

**View:** View the form. 

![Figure 1: You can configure a form's permissions.](../../images/forms-form-permissions.png)

Note that guest users can view and fill out forms by default. The *Guest* Role 
has *View* and *Add Form Instance Record* permissions. 

+$$$

**Note:** By default, all users inherit the Guest Role's permissions. The Guest
Role represents unauthenticated visitors of your Site. If you want to let
Guest users submit forms (the default setting), it makes sense that
authenticated users can also.  To disable automatic inheritance of the Guest
Role's permissions, set 
[this property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Permissions)
in your `portal-ext.properties` file:

    permissions.check.guest.enabled=false

$$$
