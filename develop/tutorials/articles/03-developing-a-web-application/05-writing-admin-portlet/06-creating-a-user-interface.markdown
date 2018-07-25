# Creating a User Interface [](id=creating-a-user-interface)

<div class="learn-path-step">
    <p>Writing the Guestbook Admin App<br>Step 5 of 5</p>
</div>

It's time to create the Guestbook Admin portlet's user interface. The portlet's 
default view has a button for adding new guestbooks. It must also display the 
guestbooks that already exist. 

Each guestbook's name is displayed along with an Actions button. The Actions
button reveals options for editing the guestbook, configuring its permissions,
or deleting it. 

## Creating JSPs for the Guestbook Admin Portlet's User Interface [](id=creating-jsps-for-the-guestbook-admin-portlets-user-interface)

The Guestbook Admin portlet's user interface is made up of three JSPs: the
default view, the Actions button, and the form for adding or editing a
guestbook. 

Create the default view first: 

1.  Create a folder for the Guestbook Admin portlet's JSPs. In
    `src/main/resources/META-INF/resources`, create a folder called
    `guestbookadminportlet`. 
 
2.  Create a file in this folder called `view.jsp` and fill it with this code: 

        <%@include file="../init.jsp"%>

        <liferay-ui:search-container
            total="<%= GuestbookLocalServiceUtil.getGuestbooksCount(scopeGroupId) %>">
            <liferay-ui:search-container-results
                results="<%= GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId, 
                    searchContainer.getStart(), searchContainer.getEnd()) %>" />

            <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Guestbook" modelVar="guestbook">

                <liferay-ui:search-container-column-text property="name" />
                        
                <liferay-ui:search-container-column-jsp
                    align="right" 
                    path="/guestbookadminportlet/guestbook_actions.jsp" />
                
            </liferay-ui:search-container-row>

            <liferay-ui:search-iterator />
        </liferay-ui:search-container>

        <aui:button-row cssClass="guestbook-admin-buttons">
            <portlet:renderURL var="addGuestbookURL">
                <portlet:param name="mvcPath"
                    value="/guestbookadminportlet/edit_guestbook.jsp" />
                <portlet:param name="redirect" value="<%= "currentURL" %>" />
            </portlet:renderURL>
                
            <aui:button onClick="<%= addGuestbookURL.toString() %>"
                value="Add Guestbook" />
        </aui:button-row>
 
    First is the standard `init.jsp` include to gain access to the imports. 

    Next is a button row with a single button for adding new guestbooks:
    `<aui:button-row cssClass="guestbook-admin-buttons">`. The `cssClass` 
    attribute lets you specify a custom CSS class for additional styling. The 
    `<portlet:renderURL>` tag constructs a URL that points to the 
    `edit_guestbook.jsp`. You haven't created this JSP yet, but you'll use it 
    for adding a new guestbook and editing an existing one. 

    Finally, a Liferay search container is used to display the list of 
    guestbooks. Three sub-tags define the search container: 

    - `<liferay-ui:search-container-results>`
    - `<liferay-ui:search-container-row>` 
    - `<liferay-ui:search-iterator>` 

    The `<liferay-ui:search-container-results>` tag's `results` attribute uses a 
    service call to retrieve the guestbooks in the scope. The `total` attribute 
    uses another service call to get a count of guestbooks. 

    The `<liferay-ui:search-container-row>` tag defines what rows contain. In 
    this case, the `className` attribute defines 
    `com.liferay.docs.guestbook.model.Guestbook"`. The `modelVar` attribute 
    defines `guestbook` as the variable for the currently iterated guestbook. In 
    the search container row, two columns are defined. The 
    `<liferay-ui:search-container-column-text property="name" />` tag specifies 
    the first column. This tag displays text. Its `property="name"` attribute 
    specifies that the text to be displayed is the current guestbook object's 
    `name` attribute. The tag `<liferay-ui:search-container-column-jsp` 
    `path="/guestbookadminportlet/guestbook_actions.jsp" align="right" />` 
    specifies the second (and last) column. This tag includes another JSP file 
    within a search container column. Its `path` attribute specifies the path to 
    the JSP file that should be displayed: `guestbook_actions.jsp`. 
 
    Finally, the `<liferay-ui:search-iterator />` tag iterates through and 
    displays the list of guestbooks. Using Liferay's search container makes the 
    Guestbook Admin portlet look like a native @product@ portlet. It also 
    provides built-in pagination so that your portlet can automatically display 
    large numbers of guestbooks on one site. 

    Your next step is to add the `guestbook_actions.jsp` file that's responsible 
    for displaying the list of possible actions for each guestbook. 

3.  Create a new file called `guestbook_actions.jsp` in your project's 
    `/guestbookadminportlet` folder. Paste in this code: 

        <%@include file="../init.jsp"%>

        <%
            String mvcPath = ParamUtil.getString(request, "mvcPath");

            ResultRow row = (ResultRow) request
                            .getAttribute("SEARCH_CONTAINER_RESULT_ROW");

            Guestbook guestbook = (Guestbook) row.getObject();
        %>

        <liferay-ui:icon-menu>

            <portlet:renderURL var="editURL">
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(guestbook.getGuestbookId()) %>" />
                <portlet:param name="mvcPath"
                    value="/guestbookadminportlet/edit_guestbook.jsp" />
            </portlet:renderURL>

            <liferay-ui:icon image="edit" message="Edit"
                    url="<%=editURL.toString() %>" />

            <portlet:actionURL name="deleteGuestbook" var="deleteURL">
                    <portlet:param name="guestbookId"
                        value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
            </portlet:actionURL>

            <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

        </liferay-ui:icon-menu>

    This JSP comprises the pop-up actions menu that shows the possible actions 
    users can perform on a guestbook: editing it or deleting it. First, 
    `init.jsp` is included because it contains all the JSP imports. Because 
    `guestbook_actions.jsp` is included for every Search Container row, it 
    retrieves the guestbook in the current iteration. The scriptlet grabs that 
    guestbook so its ID can be supplied to the menu tags. 

    The `<liferay-ui:icon-menu` tag dominates `guestbook_actions.jsp`. It's a 
    container for menu items, of which there are currently only two (you'll add 
    more later). The Edit menu item displays the Edit icon and the message 
    *Edit*: 

        <liferay-ui:icon image="edit" message="Edit"
                url="<%=editURL.toString() %>" />

    The `editURL` variable comes from the `<portlet:renderURL var="editURL">` 
    tag with two parameters: `guestbookId` and `mvcPath`. The `guestbookId` 
    parameter specifies the guestbook to edit (it's the one from the selected 
    search container result row), and the `mvcPath` parameter specifies the Edit 
    Guestbook form's path. 

    The Delete menu item displays a delete icon and the default message
    *Delete*:
    
        <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

    Unlike the `editURL`, which is a render URL that links to the 
    `edit_guestbook.jsp`, the `deleteURL` is an action URL that invokes the 
    portlet's `deleteGuestbook` action. The tag 
    `<portlet:actionURL name="deleteGuestbook" var="deleteURL">` creates this 
    action URL, which only takes one parameter: the `guestbookId` of the 
    guestbook to be deleted. 
    
    Now there's just one more JSP file left to create: the `edit_guestbook.jsp` 
    that contains the form for adding a new guestbook and editing an existing 
    one. 

4.  Create a new file called `edit_guestbook.jsp` in your project's
    `/guestbookadminportlet` directory. Then add the following code to it:

        <%@include file = "../init.jsp" %>

        <%
                long guestbookId = ParamUtil.getLong(request, "guestbookId");
                
                Guestbook guestbook = null;

                if (guestbookId > 0) {
                        guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
                }
        %>

        <portlet:renderURL var="viewURL">
                <portlet:param name="mvcPath" value="/guestbookadminportlet/view.jsp" />
        </portlet:renderURL>

        <portlet:actionURL name='<%= guestbook == null ? "addGuestbook" : "updateGuestbook" %>' var="editGuestbookURL" />

        <aui:form action="<%= editGuestbookURL %>" name="fm">
        
                <aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

                <aui:input type="hidden" name="guestbookId"
                    value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />

                <aui:fieldset>
                     <aui:input name="name" />
                </aui:fieldset>

                <aui:button-row>
                     <aui:button type="submit" />
                     <aui:button onClick="<%= viewURL %>" type="cancel"  />
                </aui:button-row>
        </aui:form>

    After the `init.jsp` import, you declare a `null` guestbook variable. If
    there's a `guestbookId` parameter in the request, then you know that you're
    editing an existing guestbook, and you use the `guestbookId` to retrieve the
    corresponding guestbook via a service call.  Otherwise, you know that you're
    adding a new guestbook.

    Next is a view URL that points to the Guestbook Admin portlet's default 
    view. This URL is invoked if the user clicks *Cancel* on the Add Guestbook
    or Edit Guestbook form. After that, you create an action URL that invokes
    either the Guestbook Admin portlet's `addGuestbook` method or its 
    `updateGuestbook` method, depending on whether the `guestbook` variable is 
    null. 

    If a guestbook is being edited, the current guestbook's name should appear 
    in the form's name field. You use the following tag to define a model of the 
    guestbook that can be used in the AlloyUI form: 

        <aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

    The form itself is created with the following tag:

        <aui:form action="<%= editGuestbookURL %>" name="<portlet:namespace />fm">

    When the form is submitted, the `editGuestbookURL` is invoked, which calls
    the Guestbook Admin portlet's `addGuestbook` or `updateGuestbook` method, as
    discussed above.
    
    The `guestbookId` must appear on the form so that it can be submitted.
    The user, however, doesn't need to see it. Thus, you specify 
    `type="hidden"`:

        <aui:input type="hidden" name="guestbookId"
                value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />

    The name, of course, should be editable by the user so it's not hidden.

    The last item on the form is a button row with two buttons. The *Submit*
    button submits the form, invoking the `editGuestbookURL` which, in turn,
    invokes either the `addGuestbook` or `updateGuestbook` method. The *Cancel*
    button invokes the `viewURL` which displays the default view.

Excellent! You've now finished creating the UI for the Guestbook Admin portlet. 
It should now match the figure below: 

![Figure 1: The Guestbook Admin portlet lets administrators add or edit guestbooks, configure their permissions, or delete them.](../../../images/admin-app-start.png)

Test out the Guestbook Admin portlet! Try adding, editing, and deleting 
guestbooks. 

Now all the Guestbook application's primary functions work. There are still many
missing features, however. For example, if there's ever an error, users never
see it: all the code written so far just prints messages in the logs. Next,
you'll learn how to display those errors to the user. 
