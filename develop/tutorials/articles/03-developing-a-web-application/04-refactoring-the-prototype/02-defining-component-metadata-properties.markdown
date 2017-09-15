# Defining the Component Metadata Properties [](id=defining-the-component-metadata-properties)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 2 of 6</p>
</div>

When users add applications to a page, they pick them from a list of *display
categories*. 

![Figure 1: Users choose applications from a list of display categories.](../../../images/display-categories.png)

A portlet's display category is defined in its component class as a metadata 
property. Since the Guestbook portlet lets users communicate with each other, 
you'll add it to the Social category. Only one Guestbook portlet should be added 
to a page, so you'll also define it as a *non-instanceable* portlet. Such a 
portlet can appear only once on a page or site, depending on its scope. 

Open the `GuestbookPortlet` class and update the component class metadata 
properties to match this configuration: 

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
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user",
            "javax.portlet.supports.mime-type=text/html"
        },
        service = Portlet.class
    )

The `com.liferay.portlet.display-category=category.social` property sets the 
Guestbook portlet's display category to *Social*. The 
`com.liferay.portlet.instanceable=false` property specifies that the Guestbook 
portlet is non-instanceable, so only one instance of the portlet can be added 
to a page. In the property `javax.portlet.init-param.view-template`, you also 
update the location of the main `view.jsp` to its new location in 
`/guestbookwebportlet`.

Since you edited the portlet's metadata, you must remove and re-add the portlet 
to the page before continuing: 

1.  Go to `localhost:8080` in your web browser.

2.  Sign in to your administrative account.

3.  The Guestbook portlet now shows an error on the page. Click its portlet menu 
    (at the top-right of the portlet), then select *Remove* and click *OK* to 
    confirm.

4.  Open the *Add* menu and select *Applications*.

5.  Open the *Social* category and drag and drop the *Guestbook* application
    onto the page.

Great! Now the Guestbook portlet appears in an appropriate category. Though you 
were able to add it to the page before, the user experience is better. 
