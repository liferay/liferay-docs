# Adding Metadata [](id=adding-metadata)

<div class="learn-path-step">
    <p>Writing the Guestbook Admin App<br>Step 2 of 5</p>
</div>

Now that you've generated the classes, you must turn them into OSGi components.
Remember that because components are container-managed objects, you must provide 
metadata that tells @product@'s OSGi container how to manage their lifecycles. 

Follow these steps:

1.  Add the following portlet key to the `GuestbookPortletKeys` class:

        public static final String GUESTBOOK_ADMIN =
          "com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet";

2.  Open the `GuestbookAdminPortlet` class and add the `@Component` annotation 
    immediately above the class declaration:

        @Component(
            immediate = true,
            property = {
                    "com.liferay.portlet.display-category=category.hidden",
                    "com.liferay.portlet.scopeable=true",
                    "javax.portlet.display-name=Guestbooks",
                    "javax.portlet.expiration-cache=0",
                    "javax.portlet.init-param.portlet-title-based-navigation=true",
                    "javax.portlet.init-param.template-path=/",
                    "javax.portlet.init-param.view-template=/guestbookadminportlet/view.jsp",
                    "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN,
                    "javax.portlet.resource-bundle=content.Language",
                    "javax.portlet.security-role-ref=administrator",
                    "javax.portlet.supports.mime-type=text/html",
                    "com.liferay.portlet.add-default-resource=true"
            },
            service = Portlet.class
        )

3.  Hit [CTRL]+[SHIFT]+O to add the `javax.portlet.Portlet` and other imports. 

There are only a few new things here. Note the value of the
`javax.portlet.display-name` property: `Guestbooks`. This is the name that
appears in the Site menu. Also note the value of the `javax.portlet.name`
property: `+ GuestbookPortletKeys.GUESTBOOK_ADMIN`. This specifies the portlet's
title via the `GUESTBOOK_ADMIN` portlet key that you just created. 

Pay special attention to the following metadata property:

    com.liferay.portlet.display-category=category.hidden

This is the same property you used before with the Guestbook portlet. You placed
that portlet in the Social category. The value `category.hidden` specifies a 
special category that doesn't appear anywhere. You're putting the Guestbook 
Admin portlet here because it'll be part of the Site menu, and you don't want 
users adding it to a page. This prevents them from doing that. 

Next, you can configure the Panel app class. Follow these steps:

1.  Open the `GuestbookAdminPanelApp` class and add the `@Component` annotation 
    immediately above the class declaration: 

        @Component(
            immediate = true,
            property = {
                "panel.app.order:Integer=300",
                "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
            },
            service = PanelApp.class
        )

    The `panel.category.key` metadata property determines where to place the
    Guestbook Admin portlet in the Product Menu. Remember that the Product Menu 
    is divided into three main sections: the Control Panel, the User Menu, and 
    the Site Administration area. The value of the `panel.category.key` property 
    is `PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT`, which means Guestbook 
    Admin is in *Site Administration* &rarr; *Content*. The key is provided by 
    [the `PanelCategoryKeys` class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/application-list/application-list-api/src/main/java/com/liferay/application/list/constants/PanelCategoryKeys.java). 
    The `panel.app.order` value determines the rank for the Guestbook Admin 
    portlet in the list. 

2.  Finally, update the class to use the proper name and portlet keys:

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

3.  Hit [CTRL]+[SHIFT]+O to organize imports. This time, import
    `com.liferay.portal.kernel.model.Portlet` instead of 
    `javax.portlet.Portlet`. 

Now that the configuration is out of the way, you're free to implement the app's 
functionality: adding, editing, and deleting guestbooks. That's the next step. 
