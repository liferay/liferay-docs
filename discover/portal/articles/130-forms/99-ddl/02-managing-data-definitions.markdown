# Managing Data Definitions

There are several things you can do to manage your data definitions. Of course, 
you can edit a data definition, but you can also configure its permissions, 
manage its templates, copy it, or delete it. 

Follow these steps to access your portal's data definitions: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and expand your site's menu (the Site Administration menu). Then select 
    *Content* &rarr; *Dynamic Data Lists*. This opens the Dynamic Data Lists 
    screen. A table lists any existing dynamic data lists. 

2.  Click the *Options* button at the top-right 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Data Definitions*. The Data Definitions screen appears. A 
    table lists the data definitions. 

![Figure 1: You can copy an existing data definition, manage its templates, and more.](../../../images/ddl-definitions-actions.png)

You can manage your data definitions via the *Actions* menu 
(![Actions](../../../images/icon-actions.png)) 
for each definition. The following options are available: 

-   **Edit:** Edit the data definition. The edit screen uses the same form 
    that's used to 
    [create data definitions](liferay.com). 
    Note that if you edit a data definition that is referenced elsewhere (e.g., 
    by a dynamic data list or display template), then you'll need to update that 
    reference. 

-   **Manage Templates:** Opens the *Manage Templates* screen, which lets you 
    create and manage templates for the data definition. For details, see the 
    [documentation on templates](liferay.com). 

-   **Permissions:** Configure the data definition's permissions. Note that 
    these permissions are for an individual definition accessed through the 
    Dynamic Data Lists app in *Site Administration* &rarr; *Content* &rarr; 
    *Dynamic Data Lists*. For example, if site members have View permission for 
    a data definition, any site member who also has a role that can access the 
    Dynamic Data Lists app and its data definitions can see this definition 
    listed in the Manage Data Definitions screen. If you don't want this, 
    deselect the View permission for Site Member, and site members won't see 
    your data definition listed with the others. 

-   **Copy:** Opens the *Copy Data Definition* form, which lets you copy the 
    definition and its templates. In the form, give the copied definition a new 
    name and description, and select whether to also copy the original 
    definition's templates. Click *Copy* when you're done. The copied definition 
    then appears in the Data Definitions table alongside any other existing 
    definitions. This lets you create new definitions based on existing ones, 
    and then modify the copied one to suit your needs. You can, of course, edit 
    any definition in the portal, but if you copy a definition instead, you can 
    still access the original. 

-   **Delete:** Delete the definition. 
