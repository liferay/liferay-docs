# Updating the Panel App Component Class [](id=updating-the-panel-app-component-class)

Follow these steps to update the class:

1.  Open the `GuestbookAdminPanelApp` class and replace the 
    `com.liferay.docs.guestbook.constants.GuestbookAdminMVCPanelCategoryKeys` 
    and `com.liferay.docs.guestbook.constants.GuestbookAdminPortletKeys` 
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

        public class GuestbookAdminPanelApp extends BasePanelApp {

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
necessary action methods to `GuestbookAdminPortlet`, and lastly you'll 
create a basic user interface for the Guestbook Admin portlet. 