# Creating Portlet Keys [](id=creating-portlet-keys)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 3 of 6</p>
</div>

`PortletKeys` manage important things like the portlet name or other repeatable,
commonly used variables in one place. This way, if you need to change the
portlet's name, you can do it in one place, and then reference it in every class
that needs it. Keys must be referenced first as a component property, and then
as a class. 

Follow these steps to create your application's `PortletKeys`:

1.  In your `guestbook-web` module, open the `GuestbookPortlet` class and 
    update the component class metadata properties by adding one new property: 

        "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,

    Note that you need the trailing comma if you've added the property to the
    middle of the list. If you've added it to the end of the last, leave it off
    (but add a trailing comma to the prior property!). 

2.  Save `GuestbookPortlet`. It now shows an error because you haven't added 
    the key to the class. 

3.  Open the `com.liferay.docs.guestbook.constants` package. 

4.  Open `GuestbookPortletKeys` and create a public, static, final String called
    `GUESTBOOK` with a value of 
    `com_liferay_docs_guestbook_portlet_GuestbookPortlet`: 
 
        public static final String GUESTBOOK =
                 "com_liferay_docs_guestbook_portlet_GuestbookPortlet";

5. Save the file.

Now `GuestbookPortlet`'s error has disappeared, and your application can be
deployed again. Nice job! 

Next, you'll integrate your application with the new back-end you generated with 
Service Builder. 
