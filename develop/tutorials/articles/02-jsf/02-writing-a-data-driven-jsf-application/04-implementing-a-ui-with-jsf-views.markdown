# Implementing a UI with JSF Views [](id=implementing-a-ui-with-jsf-views)

Your services and managed beans are all set for action, but how will your users
interact with the portlet? You'll need to implement a user interface for your
users to interact with using JSF views. 

You've briefly read about the views you'll need to incorporate. When you first
created your JSF guestbook portlet, a `view.xhtml` file was automatically
created with some default code. For your guestbook, you'll edit this file and
create the `guestbook`, `entry`, and `master` XHTML files. You'll learn about
each view's purpose, and then add the appropriate code to get your guestbook up
and running. 

Each view represents a page in your guestbook. For instance, if you were to
click an *Add Guestbook* button, the rendered page to add a guestbook would be
the `guestbook` view. The same concept applies for the `entry` view. The
`master` view represents the default page a user views that is not related to
creating a new guestbook or entry entity. Lastly, the `view` communicates with
your guestbook bean, and handles the navigation between views. 

Now that you have a fundamental understanding of the views and what they do,
it's time to dive in and create them! 

## Editing the Default View [](id=editing-the-default-view)

Currently, your guestbook only has the default view, which has some filler text.
The `view.xhtml` file located in your portlet's `docroot/views` directory is
going to serve as the default navigation view. This means that every time a
button is clicked or a page is rendered, it will be processed through your
`view.xhtml` file. Therefore, this view is very bare bones, and only points to
the view that should be rendered. 

+$$$

**Note:** You can change your default view by editing your portlet's
`docroot/WEB-INF/portlet.xml` file. Find the `<init-param>` element with the
name `javax.portlet.faces.defaultViewId.view`, and change the `<value>` element
to your new default view's path. For the guestbook portlet, you'll keep your
default view set to the `view.xhtml` file. 

$$$

As was mentioned earlier, there are three additional views, so you'll use a
choose-when conditional statement to select the appropriate view to be rendered.
Replace the code between the `<f:view>...</f:view>` tags with the following: 

    <h:head />
    <h:body>
        <c:choose>
            <c:when test="#{guestbookBacking.editingGuestbook}">
                <ui:include src="/views/guestbook.xhtml" />
            </c:when>
            <c:when test="#{guestbookBacking.editingEntry}">
                <ui:include src="/views/entry.xhtml" />
            </c:when>
            <c:otherwise>
                <ui:include src="/views/master.xhtml" />
            </c:otherwise>
        </c:choose>
    </h:body>

If you look back at your `GuestbookBacking` class, you'll recall that you had
boolean variables `editingGuestbook` and `editingEntry`. The `view.xhtml` calls
those variables to check their value. For instance, if the `editingGuestbook`
variable is `true`, the `view.xhtml` renders the `guestbook` view. A similar
process is completed for the `editingEntry` variable and `entry` view, if the
`editingGuestbook` variable was `false`. If both are `false`, the `view.xhtml`
renders the `master` view. 

Awesome! Your default navigation view is complete. It's almost time to create
the views to navigate to, but first, you'll create your language keys. 

## Specifying Your Language Keys [](id=specifying-your-language-keys)

Before you begin creating your UI, it's wise to specify your language keys. By
specifying language keys, you can reuse phrases in any of your views. You can
also create multiple language properties files, or resource bundles, to offer
translations. If you'd like to learn more information about language keys and
localization, visit the
[Localization](/develop/tutorials/-/knowledge_base/6-2/localization) module,
which offers several useful tutorials on this subject. 

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

4. Give the new file the name `i18nFaces.properties` and click *Finish*. If your
curious, *i18n* stands for
[Internationalization](http://en.wikipedia.org/wiki/Internationalization_and_localization),
which is used for adapting to multiple languages. As was stated earlier, you'll
focus on English in the learning path, but it's a best practice to provide this
file in JSF portlets. 

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

## Creating Your Guestbook's Master View [](id=creating-your-guestbooks-master-view)

Now that you have your navigation view and language keys configured, it's time
to create the actual pages your portlet's users will see.

As was mentioned earlier, when the boolean variables `editingGuestbook` and
`editingEntry` in your guestbook bean are `false`, the `master` view is
rendered. When these two variables are false, it means that the user has not yet
selected to edit a guestbook or guestbook entry, so the master page should be
displayed. Therefore, the `master` view must offer a way for the user to
navigate to your `guestbook` and `entry` views. It should also display your
created guestbooks and their entries. 

The final `master` view will appear in your guestbook portlet similar to the
figure below: 

![Figure 1: The `master` view provides the *Add Guestbook* and *Add Entry* buttons, allowing a user to navigate to the `guestbook` and `entry` views.](../../images/jsf-master-view.png)

Follow the steps below to create the `master` view for your guestbook portlet. 

1. Right-click on your guestbook's `docroot/views` folder and select *New*
   &rarr; *File*. Name the file `master.xhtml` and click *Finish*. 

    ![Figure 2: Make sure to specify the full name and extension of the view and click *Finish*.](../../images/master-view-wizard.png)

2. Add the following XML version and `<f:view>...</f:view>` tags: 

        <?xml version="1.0"?>

        <f:view xmlns="http://www.w3.org/1999/xhtml" xmlns:c="http://java.sun.com/jsp/jstl/core"
            xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" 
            xmlns:ui="http://java.sun.com/jsf/facelets">
	
        </f:view>

    The
    [`<f:view>`](https://javaserverfaces.java.net/nonav/docs/2.2/vdldocs/facelets/f/view.html)
    tag is the container for all JSF component tags used on your guestbook page.
    You've specified the libraries you'll use in this container. 

3. Next add the following code within the `<f:view>...</f:view>` tags, which
   creates the guestbook tabs: 

        <h:form>
            <h:messages globalOnly="true" layout="table" />
            <br />
                <span class="guestbook_tab">
                    <h:outputText value=" " />
                </span>
                <ui:repeat value="#{guestbookBacking.guestbooks}" var="guestbook">
                    <span class="#{(guestbook.guestbookId == guestbookBacking.selectedGuestbook.guestbookId) ? 'guestbook_tab_active' : 'guestbook_tab'}">
                        <h:outputText rendered="#{guestbook.guestbookId == guestbookBacking.selectedGuestbook.guestbookId}" value="#{guestbook.name}" />
                        <h:commandLink action="#{guestbookBacking.select(guestbook)}" immediate="true" rendered="#{guestbook.guestbookId != guestbookBacking.selectedGuestbook.guestbookId}" value="#{guestbook.name}">
                            <f:ajax render="@all" />
                        </h:commandLink>
                    </span>
                </ui:repeat>
                <span class="guestbook_tab">
                    <h:outputText value=" " />
                </span>
            <br />
            <br />

    This code snippet creates the guestbook tabs by grabbing the list of
    guestbooks stored on the view scoped guestbook bean. Then each guestbook is
    assigned a tab and rendered using its unique `guestbookId`. 
    
    You'll also notice another call to the guestbook bean within the
    `<h:commandLink>...</h:commandLink>` tags. This is used when a new guestbook
    has been submitted from the `guestbook` view, and a new tab for the
    guestbook must be created. Here, the bean's `select(guestbook)` method is
    called, which sets the new selected guestbook on the guestbook bean for
    storage, and forces a reload of all the guestbooks stored on the bean. Once
    the list of guestbooks on the bean are reloaded, all the guestbooks you've
    created can be accurately displayed as guestbook tabs. 
    
    Lastly, notice that the `<h:commandLink>` tag uses AJAX. If you're
    unfamiliar with AJAX, you can read an AJAX general overview
    [here](http://en.wikipedia.org/wiki/Ajax_\(programming\)). In summary, it
    provides asynchronous sending and receiving of data to/from a server. This
    allows for much quicker response time in your portlet. You can also learn
    more about the `<f:ajax>` tag specifically by visiting its
    [VDL docs](https://javaserverfaces.java.net/nonav/docs/2.2/vdldocs/facelets/f/ajax.html). 

4. Now you'll add the buttons that provide access to your `guestbook` and
   `entry` views, which will be created in later sections. Add the following
   code after the last `<br />` tag: 

        <h:commandButton action="#{guestbookBacking.add}" styleClass="btn btn-default" value=" #{i18n['add-guestbook']} ">
            <f:ajax render="@all" />
        </h:commandButton>
        <h:commandButton action="#{entryBacking.add}" styleClass="btn btn-default" value=" #{i18n['add-entry']} ">
            <f:ajax render="@all" />
        </h:commandButton>
        <br />		
        <br />

    Each button calls the guestbook or entry entity's bean, which opens
    a request to add a new entity. The instance of the entity is stored on the
    guestbook bean, and when the bean is called, it navigates the portlet to the
    appropriate view to display. 

5. Lastly, add the following choose-when statement after the last `<br />` tag,
   which displays your guestbook entries: 

            <c:choose>
                <c:when test="#{empty guestbookBacking.entries}">
                    <h:outputText value="#{i18n['no-entries-yet']}" />
                </c:when>
                <c:otherwise>
                    <h:dataTable styleClass="table table-bordered table-hover table-striped" rowClasses="table-cell "
                        value="#{guestbookBacking.entries}" var="entry">
                        <h:column>
                            <f:facet name="header"><h:outputText value="#{i18n['message']}" /></f:facet>
                            <h:outputText value="#{entry.message}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="#{i18n['name']}" /></f:facet>
                            <h:outputText value="#{entry.name}" />
                        </h:column>
                   </h:dataTable>
               </c:otherwise>
           </c:choose>
        </h:form>

    When a guestbook has no entries, the `no-entries-yet` language key that you
    specified in `Language_en_US.properties` is displayed. Otherwise, a data
    table is created displaying inputted entries for the currently selected
    guestbook. 

Your `master` view is complete! There is one last thing you'll need to do to
make your view stylistically pleasing: styling. 

The guestbook portlet should look professional, as many users will be looking at
it for various events. Luckily, JSF portlets allow for CSS files, which help
style the portlet. You'll need to add some styling to the guestbook tabs, as
they are not very good looking, by default. 

When you first created the guestbook portlet, a blank `docroot/css/main.css`
file was created, by default. Add the following code to `main.css` file to
improve your guestbook tabs visually in your UI: 

    .guestbook_tab,.guestbook_tab_active {
        line-height: 20px;
        padding: 8px 12px 3px 12px;
        }

    .guestbook_tab {
        border-bottom: 1px solid #ddd;
    }

    .guestbook_tab_active {
        border-left: 1px solid #ddd;
        border-top: 1px solid #ddd;
        border-right: 1px solid #ddd;
        border-bottom-left-radius: 0px;
        border-bottom-right-radius: 0px;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        font-weight: bold;
    }

That's it! Your guestbook portlet's `master` view is complete. It displays your
created guestbooks in interactive tabs, lists your guestbook entries in a data
table, and gives you two buttons that navigate you to the `guestbook` and
`entry` views. 

Next, you'll create the remaining views so you can create new guestbooks and
entries. 

## Creating Your Guestbook View [](id=creating-your-guestbook-view)

The `guestbook` view will serve as the view that is displayed when a user clicks
the *Add Guestbook* button. The final `guestbook` view will appear in your
Guestbook portlet like the figure below: 

![Figure 3: The `guestbook` view displays a Name field, a button to save the guestbook, and a button to cancel out of the view.](../../images/jsf-guestbook-view.png)

1. Right-click on your guestbook portlet's `docroot/views` folder and select
   *New* &rarr; *File*. Give it the name `guestbook.xhtml` and click *Finish*. 

2. Add the following XML version and `<f:view>` element to your `guestbook`
   view:

        <?xml version="1.0"?>

        <f:view xmlns="http://www.w3.org/1999/xhtml" xmlns:aui="http://liferay.com/faces/aui"
            xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:f="http://java.sun.com/jsf/core"
            xmlns:h="http://java.sun.com/jsf/html" xmlns:ui="http://java.sun.com/jsf/facelets">

        </f:view>

    This `<f:view>` tag is identical to your `master` view's `<f:view>` tag. 

3. Next, add the following choose-when statement within the
   `<f:view>...</f:view>` tags: 

        <h:form>
            <c:choose>
                <c:when test="#{empty guestbookBacking.selectedGuestbook.name}">
                    <h3>#{i18n['new-guestbook']}</h3>
                </c:when>
                <c:otherwise>
                    <h3>#{i18n['editing']} #{guestbookBacking.selectedGuestbook.name}</h3>
                </c:otherwise>
            </c:choose>

    When there's no guestbook name stored on your guestbook bean, the
    `new-guestbook` language key is displayed on the page. In your portlet, your
    selected guestbook is always empty when you've first arrived to the
    guestbook view, right after selecting the *Add Guestbook* button. 
    
    Otherwise, if the selected guestbook has a name, the `editing` language key
    displays, which means you've already created the selected entry and are now
    editing it. This scenario is not possible at the moment, but will be
    developed in a later JSF learning path on creating action buttons.

    You'll add the necessary fields and buttons in the next step. 

4. Now you'll need to create the fields and buttons that should be displayed for
   this view. Add the code snippet right after the `</c:choose>` tag. 

            <aui:fieldset>
                <aui:field id="guestbookNameField" label="#{i18n['name']}">
                    <h:message for="guestbookName" />
                    <h:inputText id="guestbookName" required="true" value="#{guestbookBacking.selectedGuestbook.name}">
                        <f:ajax render="guestbookNameField" />
                    </h:inputText>
                </aui:field>
            </aui:fieldset>
            <h:commandButton action="#{guestbookBacking.save}" styleClass="btn btn-default" value="#{i18n['save']}">
                <f:ajax execute="@form" render="@all" />
            </h:commandButton>
            <h:commandButton action="#{guestbookBacking.cancel}" styleClass="btn btn-default" value="#{i18n['cancel']}">
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

    Lastly, notice that the AUI field and both buttons use AJAX. As we discussed
    earlier, this provides a much quicker response time for these portlet
    actions because of asynchronous communication with the server. 

Terrific! Your `guestbook` view is complete! 

Now it's time to create the `entry` view for when a user would like to add a
guestbook entry. 

## Creating Your Guestbook Entry View [](id=creating-your-guestbook-entry-view)

Now that you can add guestbooks, it's time to create the view that allows users
to create guestbook entries. The `entry` view will display when clicking on the
*Add Entry* button. 

![Figure 4: The `entry` view displays three text fields, a button to save the entry, and a button to cancel out of the view.](../../images/entry-view.png)

1. Right-click on your guestbook portlet's `docroot/views` folder and select
   *New* &rarr; *File*. Give it the name `entry.xhtml` and click *Finish*. 

2. Add the following XML version and `<f:view>...</f:view>` tags: 

        <?xml version="1.0"?>

        <f:view xmlns="http://www.w3.org/1999/xhtml" xmlns:aui="http://liferay.com/faces/aui"
            xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:f="http://java.sun.com/jsf/core"
            xmlns:h="http://java.sun.com/jsf/html" xmlns:ui="http://java.sun.com/jsf/facelets">

        </f:view>

    This `<f:view>` tag is the same as your other views' `<f:view>` tags. 

3. Next, add the following choose-when statement within the
   `<f:view>...</f:view>` tags: 

        <h:form>
            <c:choose>
                <c:when test="#{empty guestbookBacking.selectedEntry.name}">
                    <h3>#{i18n['new-entry']}</h3>
                </c:when>
                <c:otherwise>
                    <h3>#{i18n['editing']} #{guestbookBacking.selectedEntry.name}</h3>
                </c:otherwise>
            </c:choose>

    This choose-when statement is very similar to the one used previously in
    `guestbook.xhtml`. When the selected entry stored on the guestbook bean is
    empty, the `new-entry` language key is displayed on the page. If the
    selected entry is empty, this means you've clicked the *Add Entry* button
    and have just arrived at the `entry` view. 
    
    If the selected entry is not empty, you've already created the selected
    entry and are now editing it. If this is the case, the `editing` language
    key is displayed. This is not possible at the moment since you haven't
    created a way to edit an entry yet. You'll create a way to do this when
    creating action buttons for your Guestbook portlet. 

4. Create your `entry` view's text fields by adding the following code right
   after the `</c:choose>` tag: 

        <aui:fieldset>
            <aui:field id="entryNameField" label="#{i18n['name']}">
                <h:message for="entryName" />
                <h:inputText id="entryName" required="true" value="#{guestbookBacking.selectedEntry.name}">
                    <f:ajax render="entryNameField" />
                </h:inputText>
            </aui:field>
            <aui:field id="entryEmailField" label="#{i18n['email']}">
                <h:message for="entryEmail" />
                <h:inputText id="entryEmail" required="true" validatorMessage="#{i18n['please-enter-a-valid-email-address']}"
                    value="#{guestbookBacking.selectedEntry.email}">
                    <f:validateRegex pattern=".+[@].+[.].+" />
                    <f:ajax render="entryEmailField" />
                </h:inputText>
            </aui:field>
            <aui:field id="entryMessageField" label="#{i18n['message']}">
                <h:message for="entryMessage" />
                <h:inputTextarea id="entryMessage" required="true" value="#{guestbookBacking.selectedEntry.message}">
                    <f:ajax render="entryMessageField" />
                </h:inputTextarea>
            </aui:field>
        </aui:fieldset>

    Like the `guestbook` view, your `entry` view uses AUI to create its text
    fields. You've created three text fields: *Name*, *Email*, and *Message*. 

    A cool feature about the JSF guestbook portlet is how quickly it can
    validate your inputs. For instance, you'll notice the following tag for the
    `entryEmail`: 

        <f:validateRegex pattern=".+[@].+[.].+" />

    The inputted email address is validated to ensure that it follows standard
    email syntax protocol. The use of AJAX quickly checks the inputted email
    once the user removes the cursor from the `entryEmail` field. This quick
    validation prevents incorrect syntax from even reaching your setter methods.

    In turn, when using JSF, you have an extra layer of protection from your
    database, safeguarding against malicious content, or even syntactically
    incorrect text like the guestbook's Email field. 

    ![Figure 5: With the insertion of an angle bracket, rather than a period, the guestbook portlet immediately displays an error message.](../../images/email-validation-jsf.png)

5. Create your `entry` view's buttons by adding the following code below the
   `</aui:fieldset>` tag: 

            <h:commandButton action="#{entryBacking.save}" styleClass="btn btn-default" value="#{i18n['save']}">
                <f:ajax execute="@form" render="@all" />
            </h:commandButton>
            <h:commandButton action="#{entryBacking.cancel}" immediate="true" styleClass="btn btn-default" value="#{i18n['cancel']}">
                <f:ajax render="@all" />
            </h:commandButton>
        </h:form>

    These buttons are almost identical to your `guestbook` view's Save and
    Cancel button. The only differences are, of course, you're calling the
    `save()` and `cancel()` methods from the `EntryBacking` bean. 

Your `entry` view is now complete! 

Re-deploy the Guestbook portlet and test out your new portlet by adding
guestbooks and guestbook entries. You now have a fully functional JSF guestbook,
which can be used to add guestbooks and guestbook entries. However, what if you
want to restrict access to adding or viewing certain entries? You can learn how
to implement permissions for your JSF guestbook portlet in the next learning
path. 
