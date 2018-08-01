# Managing Data Definitions [](id=managing-data-definitions)

There are several ways to manage your data definitions. Of course, you can edit
a data definition, but you can also configure its permissions, manage its
templates, copy it, or delete it. 

Follow these steps to access your data definitions: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and expand your site's menu (the Site Administration menu). Then select 
    *Content* &rarr; *Dynamic Data Lists*. This opens the Dynamic Data Lists 
    screen. A table lists any existing dynamic data lists. 

2.  Click the *Options* button at the top-right 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Data Definitions*. The Data Definitions screen appears.
    A table lists the data definitions. 

![Figure 1: You can copy an existing data definition, manage its templates, and more.](../../../images/ddl-definitions-actions.png)

You can manage your data definitions via the *Actions* menu 
(![Actions](../../../images/icon-actions.png)) 
for each definition: 

**Edit:** Edit the data definition. The edit screen uses the same form 
for 
[creating data definitions](/discover/portal/-/knowledge_base/7-1/creating-data-definitions). 
Note that if you edit a data definition referenced elsewhere (e.g., by
a dynamic data list or display template), then you must update that reference. 

**Manage Templates:** The *Manage Templates* screen creates and manages
templates for the data definition. For details, see 
[Using Templates to Display Forms and Lists](/discover/portal/-/knowledge_base/7-1/using-templates-to-display-forms-and-lists). 

**Permissions:** Configure the data definition's permissions. Note that 
these permissions are for an individual definition accessed through the
Dynamic Data Lists application in *Site Administration* &rarr; *Content*
&rarr; *Dynamic Data Lists*. For example, if Site members have View
permission for a data definition, any Site member who also has a Role that
can access the Dynamic Data Lists app and its data definitions can see this
definition listed in the Manage Data Definitions screen. If you don't want
this, remove the View permission for Site Member, and Site members won't
see your data definition listed with the others. 

**Copy:** The *Copy Data Definition* form copies the definition and its
templates. In the form, give the copied definition a new name and description
and select whether to also copy the original definition's templates. Click
*Copy* when you're done. The copied definition then appears in the Data
Definitions table with existing definitions. You can create new definitions
based on existing ones, and then modify the copied one to suit your needs. You
can, of course, edit any definition in the portal, but if you copy a definition
instead, you can still access the original. 

**Delete:** Delete the definition. 
