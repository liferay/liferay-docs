# Implementing a UI with JSF Views

Your services and managed beans are all set for action, but how will your users
interact with the portlet? You'll need to implement a user interface for your
users to interact with using JSF views. 

You've briefly read about the views you'll need to incorporate. When you first
created your JSF guestbook portlet, a `view.xhtml` file was automatically
created with some default code. For the guestbook, you'll edit this file and
create the `guestbook`, `entry`, and `master` XHTML files. You'll learn about
each view's purpose, and then add the appropriate code to get your guestbook up
and running. 

Each view represents a page in your guestbook. For instance, if you were to
click an *Add Guestbook* button, the rendered page to add a guestbook would be
the `guestbook` view. The same concept applies for the `entry` view. The
`master` view represents all the pages a user views that is not related to
creating a new guestbook or entry entity. Lastly, the default `view`
communicates with your view bean, and handles the navigation between views. 

Now that you have a fundamental understanding of the views and what they do,
it's time to dive in and create them! 

## Editing the Default View

Currently, your guestbook only has the default view, which has some filler text.
The `view.xhtml` file located in your portlet's `docroot/views` directory is
going to serve as the default navigation view. This means that everytime a
button is clicked or a page is rendered, it will be processed through your
`view.xhtml` file. Therefore, this view is very bare bones, and only points to
the view that should be rendered. 

---

![note](../../images/01-tip.png) **Note:** You can change your default
view by editing your portlet's `docroot/WEB-INF/portlet.xml` file. Find the
`<init-param>` element with the name `javax.portlet.faces.defaultViewId.view`,
and change the `<value>` element to your new default view's path. For the
guestbook portlet, you'll keep your default view set to the `view.xhtml` file. 

---

As was mentioned earlier, there are three additional views, so you'll use a
choose-when conditional statement to select the appropriate view to be rendered.
Replace the code between the `<f:view>...</f:view>` tags with the follwoing: 

    <h:head />
    <h:body>
        <c:choose>
            <c:when test="#{viewBean.editingGuestbook}">
                <ui:include src="/views/guestbook.xhtml" />
            </c:when>
            <c:when test="#{viewBean.editingEntry}">
                <ui:include src="/views/entry.xhtml" />
            </c:when>
            <c:otherwise>
                <ui:include src="/views/master.xhtml" />
            </c:otherwise>
        </c:choose>
    </h:body>

If you look back at your `ViewBean` class, you'll recall that you had boolean
varaibles `editingGuestbook` and `editingEntry`. The `view.xhtml` calls those
variables in the view bean to check if they're `true`. For instance, if the
`editingGuestbook` variable is `true`, the `view.xhtml` renders the `guestbook`
view. A similar process is completed for the `editingEntry` variable and `entry`
view, if the `editingGuestbook` variable was `false`. If both are `false`, the
`view.xhtml` renders the `master` view. 

Awesome! Your default navigation view is complete. It's almost time to create the
views to navigate to, but first, you'll create your language keys. 

## Specifying Your Language Keys

Before you begin creating your UI, it's wise to specify your language keys. By
specifying language keys, you can reuse phrases in any of your views. You can
also create multiple language properties files, or resource bundles, to offer
translations. If you'd like to learn more information about language keys and
localization, visit the
[Localization](/develop/tutorials/-/knowledge_base/localization) module, which
offers several useful tutorials on this subject. 

For the JSF guestbook, you'll stick to providing language keys for English. 

1. Open the JSF guestbook portlet's
`docroot/WEB-INF/src/Language_en_US.properties` file and delete any existing
language keys. 

2. Add the following language keys: 

        add-guestbook=Add Guestbook
        new-entry=New Entry
        new-guestbook=New Guestbook
        no-entries-yet=No entries yet ...

    These language keys will be used throughout your views. If you're wondering
    if that's the only place the guestbook can look for language keys, the
    answer is no. When the portlet is deployed to Liferay Portal, it will have
    access to Liferay's default language keys, which are available to all
    portlets in the portal. 

3. Next, you'll need to specify custom JSF validation messages. It's a best
   practice to provide these in a separate resource bundle. Right-click the
   `docroot/WEB-INF/src` directory and select *New* &rarr; *File*. 

4. Give the new file the name `i18nFaces.properties` and click *Finish*. 

5. Add the following JSF-specific validation messages into the
   `i18nFaces.properties` file: 

        javax.faces.component.UIInput.REQUIRED=Value is required
        javax.faces.converter.DateTimeConverter.DATE=Invalid date format

6. For your portlet to recognize this new properties file as a language resource
bundle, you'll need to specify it in your `faces-config.xml` file. Open your
guestbook portlet's `docroot/WEB-INF/faces-config.xml` file and add the
following code between the `<faces-config>...</faces-config>` tags: 

        <application>
            <message-bundle>i18nFaces</message-bundle>
        </application>

Your guestbook portlet is now equipped with all of its language keys. Now it's
time to begin creating your portlet's views. 


