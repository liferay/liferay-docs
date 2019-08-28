---
header-id: creating-portlet-keys
---

# Creating Portlet Keys

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 3 of 11</p>
</div>

`PortletKeys` manage important things like the portlet name or other repeatable,
commonly used variables in one place. This way, if you must change the portlet's
name, you can do it in one place and then reference it in every class that
needs it. Keys are created in a `PortletKeys` class and then referenced in
a component property. 

Follow these steps to create your application's `PortletKeys`:

1.  Open the `com.liferay.docs.guestbook.constants` package. 

2.  Open `GuestbookPortletKeys` and make sure there's a public, static, final
    String called `GUESTBOOK` with a value of 
    `com_liferay_docs_guestbook_portlet_GuestbookPortlet`: 
 
    ```java
    public static final String GUESTBOOK =
             "com_liferay_docs_guestbook_portlet_GuestbookPortlet";
    ```

3. Save the file.

4.  In your `guestbook-web` module, open the `GuestbookPortlet` class and 
    update the component class metadata properties by adding one new property: 

    ```java
    "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
    ```

    Note that you need the trailing comma if you've added the property to the
    middle of the list. If you've added it to the end of the last, leave it off
    (but add a trailing comma to the prior property!). 

5.  Save `GuestbookPortlet`. 

Nice job! 

Next, you'll integrate your application with the back-end you generated with 
Service Builder. 
