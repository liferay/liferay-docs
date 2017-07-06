# Creating PortletKeys

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



