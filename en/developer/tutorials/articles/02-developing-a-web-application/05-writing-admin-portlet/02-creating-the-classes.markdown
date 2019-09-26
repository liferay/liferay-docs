---
header-id: creating-the-classes
---

# Creating the Classes

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Writing the Guestbook Admin App</p><p>Step 1 of 6</p>
</div>

Because the Guestbook and Guestbook Admin applications should be bundled
together, you'll create the new application inside the `guestbook-web` project.
If you disagree with this design decision, you can create a separate project for
Guestbook Admin; the project template you'd use is *panel-app*. For now,
however, it's better to go through the process manually to learn how it all
works: 

1.  Right-click the `com.liferay.docs.guestbook.portlet` package in the
    `guestbook-web` project and select *New* &rarr; *Class*. 

2.  Name the class `GuestbookAdminPortlet`. 

3.  Click *Browse* next to the Superclass and search for `MVCPortlet`. Click it
    and select *OK*. 

4.  Click *Finish*. 

You now have your Guestbook Admin application's portlet class. For an 
administrative application, however, you need at least one more component. 

## Panels and Categories

As described in the 
[product menu tutorial](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu), 
there are three sections of the product menu as illustrated below. 

![Figure 1: The product menu is split into three sections: the Control Panel, the User menu, and the Sites menu.](../../../images/product-menu-parts.png)

Each section is called a *panel category*. A panel category can hold various 
menu items called *panel apps*. In the illustration above, the Sites menu is 
open to reveal its panel apps and categories (yes, you can nest them). 

The most natural place for the Guestbook Admin portlet is in the *Content
& Data* panel category with @product@'s other content-based apps. This
integrates it nicely in the spot where Site administrators expect it to be. This
also means you don't have to create a new category for it: you can just create
the panel entry, which is what you'll do next. If you'd like to learn more about
panel categories and apps after this, see the 
[product menu tutorial](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu)
and the 
[control menu tutorial](/docs/7-2/customization/-/knowledge_base/c/customizing-the-control-menu).

Follow these steps to create the panel entry for the Guestbook Admin portlet: 

1.  Add the dependency you need to extend @product@'s panel categories and apps. 
    To do this, open `guestbook-web`'s `build.gradle` file and add these 
    dependencies: 

    ```groovy
    compileOnly group: "com.liferay", name: "com.liferay.application.list.api"
	compileOnly group: "com.liferay", name: "com.liferay.petra.string"
    ```

2.  After saving the file, right-click `guestbook-web` and select *Gradle*
    &rarr; *Refresh Gradle Project*. 

3.  Right-click `src/main/java` in the `guestbook-web` project and select *New* 
    &rarr; *Package*. Name the package 
    `com.liferay.docs.guestbook.application.list` and click *Finish*. 

4.  Right-click your new package and select *New* &rarr; *Class*. Name the class 
    `GuestbookAdminPanelApp`.
 
5.  Click *Browse* next to Superclass, search for 
    `BasePanelApp`, select it, and click *OK*. Then click *Finish*. 

Great! You've created the classes you need, and you're ready to begin working on
them. 
