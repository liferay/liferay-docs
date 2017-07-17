# Creating Portlet Keys

`PortletKeys` enable you to manage important things like the portlet name or
other repeatable, commonly used variables in one place. This way, if you need to
change the name of the portlet, you can do it in one place, and then reference
it in every other class that needs it. Keys must be referenced first as a
component property, and then as a class. 

1.  Open the `GuestbookPortlet` class and update the component class metadata
    properties by adding one new property: 

        "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,

    Note that you need the trailing comma if you've added the property to the
    middle of the list. If you've added it to the end of the last, leave it off. 

2.  Save `GuestbookPortlet`. It now shows an error because you haven't created
    the class yet. 

3.  In Liferay IDE, right click on the "guestbook-web" project and select *New* &rarr; 
    *Package*.

4.  Set the name as `com.liferay.docs.guestbook.constants` and click *Finish*.

5.  Right click on the new package and select *New* &rarr; *Class*.

6.  Name the class `GuestbookPortletKeys` and click *Finish*.

7.  Open `GuestbookPortletKeys` and create a public, static, final String called
    `GUESTBOOK` with a value of 
    `com_liferay_docs_guestbook_portlet_GuestbookPortlet`:
 
        public class GuestbookPortletKeys {

             public static final String GUESTBOOK =
                 "com_liferay_docs_guestbook_portlet_GuestbookPortlet";
         }

6. Save the file.

Now you must go back to `GuestbookPortlet` and import the new class.

1.  Open `GuestbookPortlet` again. 

2.  Press CTRL+SHIFT+O to organize imports.

3.  Check that the `GuestbookPortletKeys` class was added.

4.  Save your file.

The error is now gone, and your application can be deployed again. Nice job! 

Your next step is to integrate your application with the new back-end you
generated with Service Builder. 

