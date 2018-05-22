# Managing Permissions [](id=managing-permissions)

With the staging environment, there are many different options to use for
building and managing a site and its pages. Sometimes limiting the access to
a subset of the powerful features of staging is desired by some administrators.
You can manage access to the staging environment by creating or modifying a role
to possess certain permissions. To create/modify a role, complete the following
steps:

1.  Navigate to the *Control Panel* &rarr; *Users* &rarr; *Roles*.

2.  To create a new role, select the *Add* button
    (![Add](../../../images/icon-add.png)) and complete the New Role menu. Once
    you have a new role created, or you've decided on the role you'd like to
    modify, select the role's *Actions* icon
    (![Actions](../../../images/icon-actions.png)) and select *Edit*.

3. From the top menu, select *Define Permissions*.

The most obvious permissions for staging are the general permissions that look
similar to the permissions for most Liferay apps. These permissions can be found
in the *Site Administration* &rarr; *Publishing* &rarr; *Staging* section of the
Define Permissions menu. This includes

- *Access in Site Administration*
- *Add to Page*
- *Configuration*
- *Permissions*
- *Preferences*
- *View*

Also, there are some site resource permissions that deal directly with staging.
These permissions are located in the *Control Panel* &rarr; *Sites* &rarr;
*Sites* section in the Define Permissions menu. The relevant site resource
permissions related to staging are listed below:

- *Add Page Variation:* hides/shows the *Add* button on the Staging Bar &rarr;
  Manage Page Variations screen.
- *Add Site Pages Variation:* hides/shows the *Add* button on the Staging Bar
  &rarr; Manage Site Page Variations screen.
- *Export/Import Application Info:* if the Publish Staging permission is not
  granted, hides/shows the application level Export/Import menu. The
  Configuration permission for the Export/Import app is also required.
- *Export/Import Pages:* if the Publish Staging permission is not granted,
  hides/shows the Export/Import app in the Site Administration menu.
- *Manage Staging:* hides/shows the staging configuration menu in the Staging
  app in the Site Administration menu.
- *Publish Application Info:* hides/shows the application level Staging menu.
- *Publish Staging:* hides/shows the *Publish to Live* button on the Staging Bar
  and hides/shows the *Add* button in the Staging app in the Site Administration
  menu.
- *View Staging:* if Publish Staging, Manage Pages, Manage Staging, or Update
  permissions are not granted, hides/shows the Staging app in the Site
  Administration menu.

Notice that some of the permissions listed above are related to the
export/import functionality. Since these permissions are directly affected by
the Publish Staging permission, they are important to note. Visit the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content)
section for more details on importing/exporting site and page content.