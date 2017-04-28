# Creating the Guestbook Admin Portlet [](id=creating-the-guestbook-admin-portlet)

Now you'll create the Guestbook Admin portlet and add it to the 
Control Menu. To create the Guestbook Admin portlet, you'll use the New Liferay 
Module Project wizard. You used this wizard in an earlier Learning Path section when you created the `guestbook-web` module. The New Liferay Module Project wizard creates various required metadata properties for the component class such as the `display-category`, `display-name`, and `security-role-ref`. It also determines the names and locations of other required files such as the portlet class and the location of the portlet's JSP files. 

![Figure 1: The Guestbook Admin portlet allows administrators to add new guestbooks or to edit existing guestbooks, configure their permissions, or delete them.](../../../images/admin-app-start.png)

Get started by creating the Guestbook Admin portlet next.

## Using the New Liferay Module Project Wizard [](id=using-the-new-liferay-module-project-wizard)

To create the Guestbook Admin portlet, follow these steps:

1.  In Eclipse, right-click on the *guestbook-web* module and select *New*
    &rarr; *Liferay Module Project*.
   
2.  Enter *GuestbookAdmin* for the *Project name*.

3.  Uncheck *Use default location* and navigate to the `guestbook-web` module's  
    root folder.

4.  Leave the default settings for Build type, select *panel-app* as 
    the Project Template Name, and click *Next*.

5.  Leave the default Component Class Name, enter 
    `com.liferay.docs.guestbook` for the Package Name and click 
    Finish.

The New Liferay Module Project wizard created a `GuestbookAdminMVCPortlet` 
file for you in the `com.liferay.docs.guestbook.portlet` package of the 
`guestbook-web/src/main/java` folder, as you specified. It also created 
`GuestbookAdminMVCPanelApp` and `GuestbookAdminMVCPanelCategory` classes for 
you in the `com.liferay.docs.guestbook.application.list` package. The 
`GuestbookAdminMVCPanelApp` class sets where in the Control Menu the Guestbook 
Admin portlet is displayed and the `GuestbookAdminMVCPanelCategory` class is 
responsible for creating the Control Menu category that the portlet is 
displayed in. For the purposes of this Learning Path, you'll add the Guestbook 
Admin portlet to an existing Control Menu category, so this class is not 
needed. Likewise, the `GuestbookAdminMVCPanelCategoryKeys` class is not needed. 
You'll add the portlet key for the `GuestbookAdminMVCPortlet` class to the 
existing `GuestbookPortletKeys` class, so you can delete the entire 
`/constants` folder.

The `GuestbookAdminMVCPortlet` class extends @product@'s `MVCPortlet` class, so 
it works out-of-the-box. The `javax.portlet.init-param.template-path=/` 
metadata property of the Guestbook Admin portlet's component class points to 
the `GuestbookAdminPortlet.java` file's location.

The `GuestbookAdminMVCPanelApp` class extends @product@'s `BasePanelApp` class 
and also works out-of-the-box. The New Liferay Module Project wizard also 
created a `src/main/resources/META-INF/resources` folder with sample `view.jsp` 
and `init.jsp` files. You'll use the existing `init.jsp` you created for the 
Guestbook portlet, so you can delete the `init.jsp` that was generated.

At the moment, the Guestbook Admin portlet and associated files are still 
contained within their own project. You'll need to manually move these files 
into their proper locations.

1.  Move the `GuestbookAdminMVCPortlet` file over to the 
    `com.liferay.docs.guestbook.portlet` package of the `guestbook-web` module 
    and delete the empty `com.liferay.docs.guestbook.portlet` package left 
    behind.
    
2.  Create a new folder in the `guestbook-web` module's 
    `src/main/resources/META-INF/resources/html` directory and call it 
    *guestbookadminmvcportlet*. Move the `GuestbookAdmin` module project's 
    `view.jsp` to the folder you just created.
    
3.   Move the `GuestbookAdmin` module project's `/application` folder and 
     contents over to the `com.liferay.docs.guestbook` package of the  
     `guestbook-web` module.
     
4.  Delete the `GuestbookAdmin` module project folder and empty directories.

5.  Fix the portlet keys class import 
    `com.liferay.docs.guestbook.constants.GuestbookPortletKeys`:

        com.liferay.docs.guestbook.constants.GuestbookPortletKeys;

You have set the foundation for the Guestbook Admin portlet. Next you'll update 
the Guestbook Admin portlet's metadata.

## Updating the MVC Portlet Component Class Metadata

The New Liferay Module Project Wizard created the Guestbook Admin portlet's 
component class for you and added some metadata properties with default settings. 
You'll need to update these properties with the proper values, as well as add 
some more metadata information.

Follow these steps to update the class:

1.  Add the following portlet key to the `GuestbookPortletKeys` class:

        public static final String GUESTBOOK_ADMIN =
          "com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet";

2.  Open the `guestbookadminmvcportlet` class, and replace the metadata 
    properties in the `@Component` annotation with the properties shown below:

        "com.liferay.portlet.display-category=category.hidden",
        "com.liferay.portlet.scopeable=true",
        "javax.portlet.display-name=Guestbooks",
        "javax.portlet.expiration-cache=0",
        "javax.portlet.init-param.portlet-title-based-navigation=true",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/html/guestbookadminmvcportlet/view.jsp",
        "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=administrator",
        "javax.portlet.supports.mime-type=text/html"

Note the value of the `javax.portlet.display-name` property: `Guestbooks`. This 
is the name that will appear in the Control Menu. Also note the value of the 
`javax.portlet.name` property: `+ GuestbookPortletKeys.GUESTBOOK_ADMIN`. This 
specifies the portlet's title, and uses the `GUESTBOOK_ADMIN` portlet key that 
you just created.

Pay special attention to the following metadata property:

    com.liferay.portlet.display-category=category.hidden

The category specified here determines where (or if) your portlet appears in the
list of portlets that are available to be added to a portal page. When you click
on the *Add* button at the left side of the Control Menu (the plus symbol) and 
select *Applications*, you'll see various expandable categories of portlets 
that you can add to a page. For example, the *Highlighted* category contains 
commonly used portlets such as Web Content Display, Asset Publisher, and 
Navigation. By specifying *category.hidden* as the category of the Guestbook 
Admin portlet, you ensure that it can't be added to a portal page via the 
Control Menu. This is fine since you added it to the Control Menu . If you 
didn't set the Guestbook Admin portlet's category to *category.hidden* in the 
component class, a site administrator could add the portlet to a portal page. 
If you want a portlet to be accessible via the Control Menu and *only* via the 
Control Menu, remember to specify its category as *category.hidden* in the 
component class's metadata properties.

Next you can configure the Panel app class.

## Updating the Panel App Component Class

Follow these steps to update the class:

1.  Open the `GuestbookAdminMVCPanelApp` class and replace the 
    `com.liferay.docs.guestbook.constants.GuestbookAdminMVCPanelCategoryKeys` 
    and `com.liferay.docs.guestbook.constants.GuestbookAdminMVCPortletKeys` 
    imports with the ones shown below:

        import com.liferay.application.list.constants.PanelCategoryKeys;
        import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;

2.  Update the component metadata properties to match the configuration below:

        @Component(
        	immediate = true,
        	property = {
        		"panel.app.order:Integer=300",
        		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
        	},
        	service = PanelApp.class
        )

3.  Finally, update the class to use the proper name and portlet keys:

        public class GuestbookPanelApp extends BasePanelApp {

        	@Override
        	public String getPortletId() {
        		return GuestbookPortletKeys.GUESTBOOK_ADMIN;
        	}

        	@Override
        	@Reference(
        		target = "(javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN + ")",
        		unbind = "-"
        	)
        	public void setPortlet(Portlet portlet) {
        		super.setPortlet(portlet);
        	}

        }

The `panel.category.key` metadata property determines the section of the
Control Menu to which the Guestbook Admin portlet is added. Remember that
@product@'s' Product Menu is divided into three main sections: the Control 
Panel, the User Menu, and the Site Administration area. Notice that the value 
of the `panel.category.key` property is 
*PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT*, which means that you have to 
navigate to *Site Administration* &rarr; *Content* from the Control Menu to 
view your portlet. The key is provided by the [`PanelCategoryKeys` 
class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/application-list/application-list-api/src/main/java/com/liferay/application/list/constants/PanelCategoryKeys.java) within Portal.

The `panel.app.order` value determines where the Guestbook Admin portlet 
appears in the list of portlets in the Site Administration area's Content 
section of the Control Menu. The higher the value, the lower it appears in the 
Control menu.

Now that you've created the Guestbook Admin portlet and added it to the Control 
Menu, you need to add your desired functionality to it: the abilities to add, 
edit, and delete guestbooks and to configure their permissions. You'll start by 
adding some custom services to `GuestbookLocalServiceImpl`, then you'll add the 
necessary action methods to `GuestbookAdminMVCPortlet`, and lastly you'll 
create a basic user interface for the Guestbook Admin portlet. 