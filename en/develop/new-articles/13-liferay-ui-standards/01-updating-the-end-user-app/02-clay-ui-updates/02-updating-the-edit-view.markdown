# Updating the Edit View [](id=updating-the-edit-view)

Next, update the Guestbook's edit view. You'll make the
following changes to the UI:

- Improve the readability and visibility of the form
- Minimalize the navigation

Go ahead and get started by updating the form next.

## Updating the Form [](id=updating-the-form)

Follow these steps to update the edit Entry form:

1.  Open the `guestbook-web` module's `edit_entry.jsp` and add the 
    `container-fluid-1280` CSS class to the `<aui:form>` tag:
    
        <aui:form action="<%= addEntryURL %>" cssClass="container-fluid-1280" 
        name="fm">

2.  Following the Clay guidelines, increase the click area and text size of 
    the form buttons by adding the `btn-lg` CSS class:
    
        <aui:button-row>
            <aui:button cssClass="btn-lg" type="submit" />
            <aui:button cssClass="btn-lg" onClick="<%= viewURL.toString() %>" 
            type="cancel" />
        </aui:button-row>
        
3.  Wrap the form fields `<aui:fieldset>` tags with a `<aui:fieldset-group>` tag 
    with Clay Markup:
    
        <aui:fieldset-group markupView="lexicon">
            <aui:fieldset>
                ...
            </aui:fieldset>
        </aui:fieldset-group>

Next you'll update the Edit Entry form's navigation.

## Updating the Navigation [](id=updating-the-navigation)

Follow these steps to update the Edit Entry form's navigation. To keep the form 
as clear and uncluttered as possible, you'll move the title and associated back 
button to the portlet title. Follow these steps to make these changes:

1.  Open the `guestbook-web` module's `init.jsp` and add the following import 
    for the portlet URL:

        <%@ page import="javax.portlet.PortletURL" %>
        
2.  Open the `edit_entry.jsp` and find the `if (entryId > 0){...}` check and 
    replace it with the following code:

        if (entryId > 0) {
        	entry = EntryLocalServiceUtil.getEntry(entryId);

        	guestbookId = entry.getGuestbookId();
        }

3.  Add the following code below the `if (entryId > 0){...}` check:

        PortletURL backURL = renderResponse.createRenderURL();

        backURL.setParameter("mvcPath", "/html/guestbookmvcportlet/view.jsp");
        backURL.setParameter("guestbookId", String.valueOf(guestbookId));

        portletDisplay.setShowBackIcon(true);
        portletDisplay.setURLBack(backURL.toString());

        renderResponse.setTitle((entry == null) ? "Add Entry" : entry.getName());
        
4.  Remove the following blocks of code since this is now handled by the back 
    URL logic you just added:
    
        <portlet:renderURL var="viewURL">
            <portlet:param 
                name="mvcPath" 
                value="/html/guestbookmvcportlet/view.jsp" 
            />
        </portlet:renderURL>
        
        <liferay-ui:header
            backURL="<%= viewURL.toString() %>"
            title="<%= entry == null ? "Add Entry" : entry.getName() %>"
        />
        
5.  Update the `value` attribute for the hidden guestbookId `<aui:input>` to 
    match the configuration below:
    
        <aui:input name="guestbookId" type="hidden" value="<%= guestbookId %>" />
        
6.  Finally, update the cancel button's `onClick` attribute to use the new 
    `backURL`:
    
        <aui:button cssClass="btn-lg" onClick="<%= backURL.toString() %>" 
        type="cancel" />
        
The Edit Entry view is updated! The updated view is shown in the figure below:

![Figure 1: The updated Edit Entry view keeps the user in mind, providing a clear and concise form.](../../../../../images/updated-edit-entry-form.png)

The End user Guestbook app's design is completed! In the next section you'll 
update the Guestbook Admin's design to reflect these same changes, along with a 
few additional updates.
