# Creating the Guestbook Admin Portlet [](id=creating-the-guestbook-admin-portlet)

Now you'll create the Guestbook Admin portlet and add it to the 
Control Menu. To create the Guestbook Admin portlet, you'll use the New Liferay 
Module Project wizard. You used this wizard in an earlier Learning Path section when you created the `guestbook-web` module. The New Liferay Module Project wizard creates various required metadata properties for the component class such as the `display-category`, `display-name`, and `security-role-ref`. It also determines the names and locations of other required files such as the portlet class and the location of the portlet's JSP files. 

![Figure 1: The Guestbook Admin portlet allows administrators to add new guestbooks or to edit existing guestbooks, configure their permissions, or delete them.](../../../images/admin-app-start.png)

Get started by creating the Guestbook Admin portlet next.

## Using the New Liferay Module Project Wizard [](id=using-the-new-liferay-module-project-wizard)

To create the Guestbook Admin portlet, follow these steps:

1.  In Eclipse, right-click on the *com-liferay-docs-guestbook* workspace and select *New* &rarr; *Liferay Module Project*.
   
2.  Enter *guestbook-admin* for the *Project name*.

3.  Uncheck *Use default location* and navigate to the `guestbook-web` module's
    root folder.

4.  Leave the default settings for Build type, select *panel-app* as 
    the Project Template Name, and click *Next*.

5.  Leave the default Component Class Name, enter 
    `com.liferay.docs.guestbook` for the Package Name and click 
    Finish.

The New Liferay Module Project wizard created a `GuestbookAdminPortlet` 
file for you in the `com.liferay.docs.guestbook.portlet` package of the 
`guestbook-web/src/main/java` folder, as you specified. It also created 
`GuestbookAdminPanelApp` and `GuestbookAdminPanelCategory` classes for 
you in the `com.liferay.docs.guestbook.application.list` package. The 
`GuestbookAdminPanelApp` class sets where in the Control Menu the Guestbook 
Admin portlet is displayed and the `GuestbookAdminPanelCategory` class is 
responsible for creating the Control Menu category that the portlet is 
displayed in. For the purposes of this Learning Path, you'll add the Guestbook 
Admin portlet to an existing Control Menu category, so this class is not 
needed. Likewise, the `GuestbookAdminMVCPanelCategoryKeys` class is not needed. 
You'll add the portlet key for the `GuestbookAdminPortlet` class to the 
existing `GuestbookPortletKeys` class, so you can delete the entire 
`/constants` folder.

The `GuestbookAdminPortlet` class extends @product@'s `MVCPortlet` class, so 
it works out-of-the-box. The `javax.portlet.init-param.template-path=/` 
metadata property of the Guestbook Admin portlet's component class points to 
the `GuestbookAdminPortlet.java` file's location.

The `GuestbookAdminPanelApp` class extends @product@'s `BasePanelApp` class 
and also works out-of-the-box. The New Liferay Module Project wizard also 
created a `src/main/resources/META-INF/resources` folder with sample `view.jsp` 
and `init.jsp` files. You'll use the existing `init.jsp` you created for the 
Guestbook portlet, so you can delete the `init.jsp` that was generated.

At the moment, the Guestbook Admin portlet and associated files are still 
contained within their own project. You'll need to manually move these files 
into their proper locations.

1.  Move the `GuestbookAdminPortlet` file over to the 
    `com.liferay.docs.guestbook.portlet` package of the `guestbook-mvc` module 
    and delete the empty `com.liferay.docs.guestbook.portlet` package left 
    behind.
    
2.  Create a new folder in the `guestbook-web` module's 
    `src/main/resources/META-INF/resources/` directory and call it 
    *guestbookadminportlet*. Move the `guestbook-admin` module project's 
    `view.jsp` to the folder you just created. 
    
3.   Move the `GuestbookAdmin` module project's `/application` folder and 
     contents over to the `com.liferay.docs.guestbook` package of the  
     `guestbook-mvc` module.
     
4.  Delete the `GuestbookAdmin` module project folder and empty directories.

5.  Add the following line to the `guestbook-mvc` `build.gradle` file:

    compileOnly group: "com.liferay", name: "com.liferay.application.list.api", version: "2.0.0"
    
6. Run *Refresh Gradle Project* to fix any dependency issues.

You have set the foundation for the Guestbook Admin portlet. There are still 
errors in your project, but they'll be resolved as you continue. Next you'll 
update the Guestbook Admin portlet's metadata.
