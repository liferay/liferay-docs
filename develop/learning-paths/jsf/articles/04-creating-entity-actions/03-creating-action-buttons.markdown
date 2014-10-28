# Creating Action Buttons

Now that you have the power to edit and delete entities, and also control their
permissions, it's time to expose those capabilities to your users through your
JSF views. This will be a similar process to when you created the Add Guestbook
and Add Entry buttons. All of your action buttons will display in the `master`
view, so you'll only edit that particular view. You'll begin with adding the
guestbook's action buttons. 

1. Open the `master.xhtml` file. Directly below the first `<br />` tag under the
   command buttons for adding a guestbook and entry, add the following code to
   create your guestbook's action buttons: 

        <div id="guestbook_buttons" style="display: inline-block">
            <h:commandButton action="#{guestbookBacking.edit(guestbookBacking.selectedGuestbook)}" styleClass="btn btn-default" value=" #{i18n['edit']} ">
                <f:ajax render="@all" />
            </h:commandButton>
            <h:commandButton action="#{guestbookBacking.delete(guestbookBacking.selectedGuestbook)}" styleClass="btn btn-default" value=" #{i18n['delete']} "
                onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}" >
                <f:ajax render="@all" />
            </h:commandButton>
            <h:outputLink styleClass="btn btn-default" value="#{guestbookBacking.selectedGuestbook.permissionsUrl}">
                <h:outputText value=" #{i18n['permissions']} " />
            </h:outputLink>
		</div>

    All three of your action buttons for guestbooks are now available in your
    portlet. They are formatted as an `inline-block`. Also notice that each
    button calls the `GuestbookBacking` bean's `selectedGuestbook` property,
    which represents the current guestbook entity you've selected to perform an
    action on. 

    +$$$
    
    **Note:** Currently, the drop-down *Actions* button used in many MVC
    portlets is not supported in Liferay Faces. If your unfamiliar with MVC
    portlets' Actions button, visit the
    [Creating Action Buttons](/develop/learning-paths/-/knowledge_base/6-2/creating-action-buttons)
    learning path to learn more about its functionality. 

    $$$

2. Find the following `<h:column>...</h:column>` tag: 

        <h:column>
            <f:facet name="header"><h:outputText value="#{i18n['name']}" /></f:facet>
            <h:panelGroup rendered="#{entry.viewable}">
                <h:outputText value="#{entry.name}" />
            </h:panelGroup>
        </h:column>

    Add the entry entity's action buttons directly below the above
    `<h:column>...</h:column>` tag: 

        <h:column>
            <f:facet name="header"><h:outputText value=" " /></f:facet>
            <h:commandButton action="#{entryBacking.edit(entry)}" styleClass="btn btn-default" value=" #{i18n['edit']} ">
                <f:ajax render="@all" />
            </h:commandButton>
            <h:commandButton action="#{entryBacking.delete(entry)}" styleClass="btn btn-default" value=" #{i18n['delete']} "
                onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}" >
                <f:ajax render="@all" />
            </h:commandButton>
            <h:outputLink styleClass="btn btn-default" value="#{entry.permissionsUrl}">
                <h:outputText value=" #{i18n['permissions']} " />
            </h:outputLink>
        </h:column>

    These buttons work in the exact same way as the guestbook entity action
    buttons, but are formatted a bit differently to fit with entries listed in a
    table. 

Your buttons are now ready for use! Redeploy your Guestbook portlet and give
them a shot! The Edit and Delete buttons should function as expected. The
Permissions button for your entities should display the Permissions menu, but
none of the permissions work yet. 

You'll fix this by adding the remainder of the permissions methods to your
wrapper classes. 
