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

## Creating Your Guestbook View

The `guestbook` view will serve as the view that is displayed when a user clicks
the *Add Guestbook* button. 

1. Right-click on your guestbook portlet's `docroot/views` folder and select
*New* &rarr; *File*. Give it the name `guestbook.xhtml` and click *Finish*. 

    ![Figure 1: Make sure to specify the full name and extension of the view and click *Finish*.](../../images/guestbook-view-wizard.png)

2. Add the following `<f:view>` element to your `guestbook` view:

        <?xml version="1.0"?>

        <f:view xmlns="http://www.w3.org/1999/xhtml" xmlns:aui="http://liferay.com/faces/aui"
            xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:f="http://java.sun.com/jsf/core"
            xmlns:h="http://java.sun.com/jsf/html" xmlns:ui="http://java.sun.com/jsf/facelets">

        </f:view>

    The
    [`<f:view>`](http://www.jsftoolbox.com/documentation/help/12-TagReference/core/f_view.html)
    tag is the container for all JSF component tags used on your guestbook page.
    You've specified the libraries you'll use in this container. 

3. Next, add the following choose-when statement within the
   `<f:view>...</f:view>` tags: 

        <h:form>
            <c:choose>
                <c:when test="#{empty guestbookModelBean.selectedGuestbook.name}">
                    <h3>#{i18n['new-guestbook']}</h3>
                </c:when>
                <c:otherwise>
                    <h3>#{i18n['editing']} #{guestbookModelBean.selectedGuestbook.name}</h3>
                </c:otherwise>
            </c:choose>

    When there's no guestbook name stored on your model bean, the
    `new-guestbook` language key is displayed on the page. In your portlet, your
    selected guestbook is always empty when you've first arrived to the
    guestbook view, right after selecting the *Add Guestbook* button. 
    
    Otherwise, if the selected guestbook has a name, the `editing` language key
    displays, which means you've given the new guestbook a name and you're
    viewing the transition page between clicking *Save* and your guestbook
    appearing on the main default page. You'll add the necessary fields and
    buttons in the next step. 
    
    You can examine Figure 2 at the end of this section to visualize what has
    been explained above. 

4. Now you'll need to create the fields and buttons that should be displayed for
   this view. Add the code snippet below right after the `</c:choose>` tag. 

            <aui:fieldset>
                <aui:field id="guestbookNameField" label="#{i18n['name']}">
                    <h:message for="guestbookName" />
                    <h:inputText id="guestbookName" required="true" value="#{guestbookModelBean.selectedGuestbook.name}">
                        <f:ajax render="guestbookNameField" />
                    </h:inputText>
                </aui:field>
            </aui:fieldset>
            <h:commandButton action="#{guestbookBackingBean.save}" styleClass="btn btn-default" value="#{i18n['save']}">
                <f:ajax execute="@form" render="@all" />
            </h:commandButton>
            <h:commandButton action="#{guestbookBackingBean.cancel}" styleClass="btn btn-default" value="#{i18n['cancel']}">
                <f:ajax render="@all" />
            </h:commandButton>
        </h:form>

    First, you used AUI tags to create the guestbook name field. Remember,
    you're able to do this because you specified your portlet's JSF component
    suite as being Liferay Faces Alloy. Therefore, your portlet has access to
    the AUI library from the `liferay-faces-alloy.jar` file, located in your
    guestbook portlet's `docroot/WEB-INF/lib` directory.

    Next, you created two buttons named *Save* and *Cancel*. These buttons'
    actions are available by calling the guestbook backing bean's `save()` and
    `cancel()` methods. 

    Lastly, notice that the AUI field and both buttons use AJAX. If you're
    unfamiliar with AJAX, you can read an AJAX general overview
    [here](http://en.wikipedia.org/wiki/Ajax_\(programming\)). In summary, it
    provides asynchronous sending and receiving of data to/from a server. This
    allows for much quicker response time in your portlet. You can also learn
    more about the `<f:ajax>` tag specifically, by visiting
    [this](http://www.jsftoolbox.com/documentation/help/12-TagReference/core/f_ajax.html)
    site. 

5. Add the following `<h:outputScript>` declaration right after the `</h:form>`
   tag: 

        <h:outputScript>AUI().one('input[id$=:guestbookName]').focus();</h:outputScript>

    This tag places your cursor in the guestbook name field when the `guestbook`
    view is rendered. 

Terrific! You `guestbook` view is complete! Now it's time to create the `entry`
view for when a user would like to add a guestbook entry. 

## Creating Your Guestbook Entry View
