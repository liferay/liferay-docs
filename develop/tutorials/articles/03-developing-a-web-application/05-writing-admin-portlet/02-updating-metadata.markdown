# Updating the Web Portlet Component Class Metadata [](id=updating-the-mvc-portlet-component-class-metadata)

The New Liferay Module Project Wizard created the Guestbook Admin portlet's 
component class for you and added some metadata properties with default settings. 
You'll need to update these properties with the proper values, as well as add 
some more metadata information.

Follow these steps to update the class:

1.  Add the following portlet key to the `GuestbookPortletKeys` class:

        public static final String GUESTBOOK_ADMIN =
          "com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet";

2.  Open the `guestbookadminportlet` class, and replace the metadata 
    properties in the `@Component` annotation with the properties shown below:

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
        "javax.portlet.supports.mime-type=text/html"

3. Reorganize imports to add the `GuestbookPortletKeys` import.

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
