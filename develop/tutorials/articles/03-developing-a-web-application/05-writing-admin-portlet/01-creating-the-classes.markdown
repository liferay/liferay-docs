# Creating the Classes  

Because the Guestbook and Guestbook Admin applications should be bundled
together, you'll create the new application manually inside the guestbook-web
project, rather than by using a wizard. If you disagree with this design
decision, you absolutely can create a separate project for Guestbook Admin; the
project template you'd use is *panel-app*. For now, however, it's better to go
through the process manually to learn how it all works. 

1.  Right-click on the `com.liferay.docs.guestbook.portlet` package in the
    `guestbook-web` project and select *New* &rarr; *Class*. 

2.  Name the class `GuestbookAdminPortlet`. 

3.  Click *Browse* next to the Superclass and search for `MVCPortlet`. Click it
    and select *OK*. 

4.  Click *Finish*. 

You now have a portlet class for your Guestbook Admin application. For an
administrative application, however, you need at least one more component. 

## Panels and Categories 

As described in the [product menu tutorial](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
tutorial, there are three sections of the product menu as illustrated below. 

![Figure x: The product menu is split into three sections: the Control Panel, the User menu, and the Sites menu.](../../images/product-menu-parts.png)

Each of these three sections is called a *Panel Category*. A panel category can
hold various menu items, called *Panel Apps*. In the illustration above, the
Sites menu is open reveal its panel apps and categories (yes, you can nest
them). 

The most natural place for the Guestbook Admin portlet to live is in the Content
panel category with the other content-based apps that ship with @product@. This
integrates it nicely in the spot where site administrators expect it to be. And
this is great for you, because you don't have to create a new category for it:
you can just create the panel entry, which is what you'll do next. If you'd like
to learn more about panel categories and apps after this, please visit the 
[product menu tutorial](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
and the [control menu tutorial](/develop/tutorials/-/knowledge_base/7-0/customizing-the-control-menu).

1.  Add the dependency you need to extend @product@'s panel categories and apps
    by opening `guestbook-web`'s `build.gradle` file and adding this dependency: 

        compileOnly group: "com.liferay", name: "com.liferay.application.list.api", version: "2.0.0"

2.  Right-click on `guestbook-web` and select *Gradle* &rarr; *Refresh Gradle
    Project*. 

3.  Right-click on `src/main/java` in the `guestbook-web` project and select
    *New* &rarr; *Package*. 

4.  Name the package `com.liferay.docs.guestbook.application.list` and click
    *Finish*. 

5.  Right-click on your new package and select *New* &rarr; *Class*. 

6.  Name the class `GuestbookAdminPanelApp`. 

7.  Click *Browse* next to Superclass, search for `BasePanelApp`, select it, and
    click *OK*. 

8.  Click *Finish*. 

Great! You've created the classes you need, and you're ready to begin working on
them. 
