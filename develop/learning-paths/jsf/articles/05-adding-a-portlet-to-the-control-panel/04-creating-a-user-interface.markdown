# Creating a User Interface [](id=creating-a-user-interface)

All of the Guestbook Admin portlet's back-end code is developed; the only thing
left to do is create the portlet's user interface. The default view should have
a button for adding new guestbooks, and it should also display a list of the
guestbooks that have been added to the site. 

![Figure 1: The Guestbook Admin's UI will contain buttons to add, edit, delete, and control permissions of guestbook entities.](../../images/guestbook-admin.png)

Each guestbook should also be accompanied with the action buttons Edit, Delete,
and Permissions. You'll begin by creating the buttons, then you'll check for
each button's permissions. 

## Creating JSF Views for the User Interface [](id=creating-jsf-views-for-the-user-interface)

The first thing you'll do is configure the existing
`docroot/views/guestbook-admin/view.xhtml` file to facilitate the UI's
navigation. Similar to the original `views/view.xhtml`, this view will serve as
the navigation view, checking boolean properties in the `GuestbookBacking` bean,
and displaying the appropriate view according to those property values. 

1. Open the `views/guestbook-admin/view.xhtml` file and replace the contents
   within the `<h:body>...</h:body>` tags with the following: 

        <c:choose>
            <c:when test="#{guestbookBacking.editingGuestbook}">
                <ui:include src="/views/guestbook.xhtml" />
            </c:when>
            <c:otherwise>
                <ui:include src="/views/guestbook-admin/guestbook_actions.xhtml" />
            </c:otherwise>
        </c:choose>

    This `view` checks the `GuestbookBacking` bean for the `editingGuestbook`
    property. If the property is `true`, the `guestbook` view is displayed. If
    it's `false`, the `guestbook_actions` view is displayed. Recall, the
    `editingGuestbook` property is set to `true` when the Edit button is
    clicked, meaning the `guestbook_actions` view is always displayed, unless
    the Edit button has been clicked. Also notice you didn't have to create a
    separate `guestbook` view for your new Guestbook Admin portlet. This is a
    nice convenience when using JSF views. 

    Now you'll need to create your `guestbook_actions` view, which acts
    similarly to your original Guestbook portlet's `master` view. 

2. Right-click the `views/guestbook-admin` folder and select *New* &rarr;
   *File*. Give the file the name `guestbook_actions.xhtml` and click *Finish*. 

3. The first thing you'll need to do is add the necessary XML version and
   `<f:view>` element into the `guestbook_actions.xhtml` file, similar to what
   you completed for your other views: 

        <?xml version="1.0"?>

        <f:view xmlns="http://www.w3.org/1999/xhtml"
            xmlns:c="http://java.sun.com/jsp/jstl/core"
            xmlns:f="http://java.sun.com/jsf/core"
            xmlns:h="http://java.sun.com/jsf/html"
            xmlns:ui="http://java.sun.com/jsf/facelets"
            xmlns:aui="http://liferay.com/faces/aui">

        </f:view>

4. Within the `<f:view>...</f:view>` tags, add the Add Guestbook button code
   into the `guestbook_actions` view. Make sure to surround the button with
   `<h:form>...</h:form>` tags: 

        <h:form>
            <h:commandButton action="#{guestbookBacking.add}"
                rendered="#{guestbookBacking.hasAddPermission}"
                styleClass="btn btn-default" value=" #{i18n['add-guestbook']} ">
                <f:ajax render="@all" />
            </h:commandButton>
            <br />

        </h:form>

    This is the exact same code that was already present in your
    `views/master.xhtml` file.

5. Remove the `<h:commandButton>` element you added in step 4 from the `master`
   view, so the Add Guestbook button is only accessible from you Guestbook Admin
   portlet. 

6. Directly below the `<br />` tag under the command button for adding a
   guestbook, add the following code to create your guestbook's action buttons: 

        <c:choose>
            <c:when test="#{empty guestbookBacking.guestbooks}">
                <h:outputText value="#{i18n['no-guestbooks-yet']}" />
            </c:when>
            <c:otherwise>
                <h:dataTable
                    styleClass="table table-bordered table-hover table-striped"
                    rowClasses="table-cell " value="#{guestbookBacking.guestbooks}"
                    var="guestbook">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="#{i18n['name']}" />
                        </f:facet>
                        <h:outputText value="#{guestbook.name}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value=" " />
                        </f:facet>

                        <h:commandButton action="#{guestbookBacking.edit(guestbook)}"
                            styleClass="btn btn-default" value=" #{i18n['edit']} ">
                            <f:ajax render="@all" />
                        </h:commandButton>

                        <h:commandButton action="#{guestbookBacking.delete(guestbook)}"
                            styleClass="btn btn-default" value=" #{i18n['delete']} "
                            onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}">
                            <f:ajax render="@all" />
                        </h:commandButton>

                        <h:outputLink styleClass="btn btn-default"
                            value="#{guestbook.permissionsUrl}">
                            <h:outputText value=" #{i18n['permissions']} " />
                        </h:outputLink>

                    </h:column>
                </h:dataTable>
            </c:otherwise>
        </c:choose>

    All three of your action buttons for guestbooks are now available in your
    portlet. They are formatted in a `dataTable`. Also notice that the Edit and
    Delete buttons call the `GuestbookBacking` bean action methods you created
    in the previous learning path. The Permissions button calls the
    `permissionsURL` property you created in your `Guestbook` wrapper class. 

Now that your buttons are created and your guestbooks are displayed in a table,
redeploy your `guestbook-jsf-portlet` project and visit your Guestbook Admin
portlet. It should look similar to Figure 1 in this section. Also, the Add
Guestbook button should no longer be present in your Guestbook portlet. 

Your buttons are all available, but you have not configured the buttons'
permissions. In the next section, you'll configure the permissions. 

## Checking for Permissions in JSF Views [](id=checking-for-permissions-in-jsf-views)

The last thing you'll need to do to have fully functional action buttons for
your guestbook entities is to utilize your `Guestbook` wrapper class'
permissions methods. You'll need to check each button for its corresponding
permissions property. 

For instance, the Delete button will need to call the `deleteable` property from
the `Guestbook` wrapper class. The `deleteable` property is `true` if the user
has the appropriate permissions to access the button. If the property is `false`
the button is invisible. The property is set using the `getDeleteable` method
from the `Guestbook` wrapper class, which checks for the user's permissions. To
add permissioning to your action buttons, open the `guestbook_actions` view and
find the action buttons you created previously in the data table. 

For each of your entry buttons, surround each individual
`<h:commandButton>...</h:commandButton>` or `<h:outputLink>...</h:outputLink>`
tag with the `<h:panelGroup>...</h:panelGroup>` tag. Each panel group tag should
contain the `rendered` element specifying the appropriate permission to check
for each action button. Below is how your entry buttons should look after you're
finished: 

    ...

    <h:panelGroup rendered="#{guestbook.updateable}">
        <h:commandButton action="#{guestbookBacking.edit(guestbook)}"
            styleClass="btn btn-default" value=" #{i18n['edit']} ">
            <f:ajax render="@all" />
        </h:commandButton>
    </h:panelGroup>
    <h:panelGroup rendered="#{guestbook.deleteable}">
        <h:commandButton action="#{guestbookBacking.delete(guestbook)}"
            styleClass="btn btn-default" value=" #{i18n['delete']} "
            onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}">
            <f:ajax render="@all" />
        </h:commandButton>
    </h:panelGroup>
    <h:panelGroup rendered="#{guestbook.permissible}">
        <h:outputLink styleClass="btn btn-default"
            value="#{guestbook.permissionsUrl}">
            <h:outputText value=" #{i18n['permissions']} " />
        </h:outputLink>
    </h:panelGroup>

    ...

Your action buttons are now configured with permissions, which are controllable
from your Permissions button. Awesome! Your Guestbook Admin portlet is fully
functional and allows administrators to control your Guestbook portlet's
guestbooks. 
