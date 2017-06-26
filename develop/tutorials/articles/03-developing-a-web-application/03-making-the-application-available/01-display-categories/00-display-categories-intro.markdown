# Setting the Display Category for the Guestbook Portlet [](id=setting-the-display-category-for-the-guestbook-portlet)

The Guestbook portlet doesn't have a display category specified yet. Apps
without a display category will be in an "Undefined" category. This isn't
an ideal user experience if you want to add the application to a page. 

To fix this, you'll specify the display category for the Guestbook portlet next.

## Defining the Component Metadata Properties [](id=defining-the-component-metadata-properties)

The display category for a portlet is defined in its component class as a 
metadata property. Since the Guestbook portlet is a way for users to 
communicate with each other, you'll add the Guestbook portlet to the Social 
category. Only one Guestbook portlet should be added to a page, so you'll also 
define it as a non-instanceable portlet.

Open the `GuestbookPortlet` class and update the component class metadata 
properties to match the configuration below:

    @Component(
    	immediate = true,
    	property = {
    		"com.liferay.portlet.display-category=category.social",
    		"com.liferay.portlet.instanceable=false",
    		"com.liferay.portlet.scopeable=true",
    		"javax.portlet.display-name=Guestbook",
    		"javax.portlet.expiration-cache=0",
    		"javax.portlet.init-param.template-path=/",
    		"javax.portlet.init-param.view-template=/guestbookwebportlet/view.jsp",
    		"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
    		"javax.portlet.resource-bundle=content.Language",
    		"javax.portlet.security-role-ref=power-user,user",
    		"javax.portlet.supports.mime-type=text/html"
    	},
    	service = Portlet.class
    )
    
The `com.liferay.portlet.display-category=category.social` property sets the 
display category to *Social* for the Guestbook portlet. The 
`com.liferay.portlet.instanceable=false` property specifies that the Guestbook 
portlet is non-instanceable, so only one instance of the portlet can be added 
to a page. 

### Creating PortletKeys

You can see in the above code that we referenced the `GuestbookPortletKeys`,
but we haven't actually created that class yet. `PortletKeys` enable you to
manage important things like the portlet name or other repeatable, commonly used
variables in one place. This way, if you need to change the name of the portlet,
you can do it in one place, and then reference it in every other class that 
needs it.

1. In Liferay IDE, right click on the "guestbook-web" project and select *New* &rarr; 
    *Package*.
2. Set the name as `com.liferay.docs.guestbook.constants` and click *Finish*.
3. Right click on the new package and select *New* &rarr; *Class*.
4. Name the class `GuestbookPortletKeys` and click *Finish*.
5. Open `GuestbookPortletKeys` and create a public, static, final String -
    "GUESTBOOK" with a value of 
	"com_liferay_docs_guestbook_portlet_GuestbookPortlet"
 
	 public class GuestbookPortletKeys {

	 	public static final String GUESTBOOK =
	 		"com_liferay_docs_guestbook_portlet_GuestbookPortlet";
	 }
6. Save the file.

Now you'll need to go back to `GuestbookPortlet` and import the new class.

1. Press CTRL+SHIFT+O to organize imports.
2. Check that the `GuestbookPortletKeys` class was added.
3. Save your file.

### New and improved
 
Now the Guestbook portlet will display in a category, with the name you 
specified. While you were able to add it to the page before, it has now been
improved and will provide a beter user experience.

Another important feature that's being used behind the scenes is Liferay's 
Single Page Applications (SPA.) All @product@ apps are automatically SPA 
enabled by default, and require no changes to your apps or workflow, so the 
Guestbook portlet can take advantage of SPA features right away. You can read 
more about SPA in the [Automatic Single Page Applications](/develop/tutorials/-/knowledge_base/7-0/automatic-single-page-applications) tutorial.

In the next section, you'll create the Guestbook Admin portlet.
