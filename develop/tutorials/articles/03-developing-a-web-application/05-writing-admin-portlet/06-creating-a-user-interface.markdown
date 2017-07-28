# Creating a User Interface [](id=creating-a-user-interface)

It's time to create the Guestbook Admin portlet's user interface. The default
view of the portlet has a button for adding new guestbooks. It must also
display the guestbooks that have already been added.

Each guestbook's name is displayed along with an Actions button. The Actions
button reveals options for editing the guestbook, configuring its permissions,
or deleting it. 

## Creating JSPs for the Guestbook Admin Portlet's User Interface [](id=creating-jsps-for-the-guestbook-admin-portlets-user-interface)

The Guestbook Admin portlet's user interface is made up of three JSPs: the
default view, the Actions button, and the form for adding or editing a
guestbook.

The first one to create is the default view: 

1.  Create a folder to store the Guestbook Admin portlet's JSPs. In
    `src/main/resources/META-INF/resources`, create a folder called
    `guestbookadminportlet`. 
 
2.  Create a file in this folder called `view.jsp` and fill it with this code: 

        <%@include file="../init.jsp"%>

                <liferay-ui:search-container
                              total="<%= GuestbookLocalServiceUtil.getGuestbooksCount(scopeGroupId) %>"
        >
                        <liferay-ui:search-container-results
                    results="<%= GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId, 
                    searchContainer.getStart(), searchContainer.getEnd()) %>"
                />

                        <liferay-ui:search-container-row
                                className="com.liferay.docs.guestbook.model.Guestbook" modelVar="guestbook">

                                <liferay-ui:search-container-column-text property="name" />
                        
                                <liferay-ui:search-container-column-jsp
                                    align="right" 
                                    path="/guestbookadminportlet/guestbook_actions.jsp"
                                />
                
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
    attribute allows you to specify a custom CSS class that can be used for
    additional styling. The `<portlet:renderURL>` tag constructs a URL that
    points to the `edit_guestbook.jsp`. You haven't created this JSP yet, but
    you'll use it for both adding a new guestbook and editing an existing one.

    Finally, a Liferay search container is used to display the list of guestbooks. 
    Three sub-tags define the search container: 

    - `<liferay-ui:search-container-results>`
    - `<liferay-ui:search-container-row>` 
    - `<liferay-ui:search-iterator>` 

    The `<liferay-ui:search-container-results>` tag's `results` attribute uses a
    service call to retrieve all the guestbooks for the scope.  The `total`
    attribute uses another service call to get a count of guestbooks.

    The `<liferay-ui:search-container-row>` tag defines what rows contain. In
    this case, the `className` attribute defines `com.liferay.docs.guestbook.model.Guestbook"`, and
    the `modelVar` attribute defines `guestbook` as the variable for the
    currently iterated guestbook. In the search container row, two columns are
    defined. The `<liferay-ui:search-container-column-text property="name" />`
    tag specifies the first column. This tag displays text. Its
    `property="name"` attribute specifies that the text to be displayed is the
    `name` attribute of the current guestbook object. The
    `<liferay-ui:search-container-column-jsp`
    `path="/guestbookadminportlet/guestbook_actions.jsp" align="right" />` tag
    specifies the second, and last, column. This tag includes another JSP file
    within a search container column. Its `path` attribute specifies the path to
    the JSP file that should be displayed: `guestbook_actions.jsp`.
 
    Finally, the `<liferay-ui:search-iterator />` tag iterates through and
    displays the list of guestbooks. Using Liferay's search container makes
    the Guestbook Admin portlet look like a native Liferay portlet. It also
    provides built-in pagination so that your portlet can automatically display
    large numbers of guestbooks on one site.

    Your next step is to add the `guestbook_actions.jsp` file that's responsible
    for displaying the list of possible actions for each guestbook.

2.  Create a new file called `guestbook_actions.jsp` in your project's
    `/guestbookadminportlet` folder. Paste in its code: 

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

                                <liferay-security:permissionsURL
                                        modelResource="<%= Guestbook.class.getName() %>"
                                        modelResourceDescription="<%= guestbook.getName() %>"
                                        resourcePrimKey="<%= String.valueOf(guestbook.getGuestbookId()) %>"
                                        var="permissionsURL" />

                                <portlet:actionURL name="deleteGuestbook" var="deleteURL">
                                        <portlet:param name="guestbookId"
                                                value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
                                </portlet:actionURL>

                                <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

                </liferay-ui:icon-menu>

    In `guestbook_actions.jsp`, you are displaying a list of possible actions
    that can be performed on a guestbook: editing it, configuring its
    permsisions, or deleting it. As with all of your portlet's JSPs, you need to
    include `init.jsp`, since you're following the pattern of adding all JSP
    imports to one file. In order to get the particular guestbook selected by
    the user, you have to use the search container result row selected by the
    user. This is what you're doing in the initial scriptlet at the top of the
    file. You also need the `mvcPath` URL parameter for the *edit guestbook*
    action so the portlet knows the path to the form to display for editing a
    guestbook: `edit_guestbook.jsp`. Creating this JSP will be your last step.

    Most of `edit_guestbook.jsp` is taken up by the `<liferay-ui:icon-menu>` tag
    and its contents. The `<liferay-ui:icon-menu>` tag itself is a container for
    menu items. The Edit menu item displays the Edit icon and the message 
    *Edit*:

        <liferay-ui:icon image="edit" message="Edit"
                url="<%=editURL.toString() %>" />

    The `editURL` variable is created via the `<portlet:renderURL
    var="editURL">` tag with two parameters: `guestbookId` and `mvcPath`. The
    `guestbookId` parameter specifies the guestbook to edit (it's the one from
    the selected search container result row), and the `mvcPath` parameter
    specifies the path the Edit Guestbook form.

    The Permissions menu item displays the Permissions icon and the default
    message *Permissions*:

        <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

    The `permissionsURL` variable is created via the
    `<liferay-security:permissionsURL>` tag. You supply the model resource class
    name and the primary key of the specific resource for which you'd like to
    display the permissions configuration menu, and the tag does the rest.
    Another benefit of using Service Builder and defining your guestbook
    permissions in `src/main/resources/resource-actions/default.xml` is that
    your application seamlessly integrates with Liferay's permissions system.

    The Delete menu item displays a default delete icon and the default message
    *Delete*:
    
        <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

    Unlike the `editURL`, which is just a render URL that displays the
    form for editing a guestbook, the `deleteURL` is an action URL. It invokes
    the portlet's `deleteGuestbook` action and you create it via the
    `<portlet:actionURL name="deleteGuestbook" var="deleteURL">` tag. This
    action URL only takes one parameter, the `guestbookId` of the guestbook to
    be deleted. Now there's just one more JSP file left to create: the
    `edit_guestbook.jsp` that contains the form for both adding a new guestbook
    and editing an existing one.

3. Create a new file called `edit_guestbook.jsp` in your project's
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

    You begin, of course, by importing `init.jsp`. Then you declare a `null`
    guestbook variable. If there's a `guestbookId` parameter in the request,
    then you know that you're editing an existing guestbook, and you use the
    `guestbookId` to retrieve the corresponding guestbook via a service call.
    Otherwise, you know that you're adding a new guestbook.

    Next, you create a view URL that points to the default view of the Guestbook
    Admin portlet. This URL is invoked if the user clicks *Cancel* on the Add
    Guestbook or Edit Guestbook form. After that, you create an action URL that
    invokes either the Guestbook Admin portlet's `addGuestbook` method or its
    `updateGuestbook` method. If no guestbook could be retrieved from the
    request, `addGuestbook` is invoked. Otherwise, `updateGuestbook` is invoked
    with the required parameter: the guestbook's `guestbookId`.

    If a guestbook is being edited, the current guestbook's name should appear
    in the name field of the form. You use the following tag to define a model
    of the guestbook that can be used in the AlloyUI form:

        <aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

    The form itself is created with the following tag:

        <aui:form action="<%= editGuestbookURL %>" name="<portlet:namespace />fm">

    When the form is submitted, the `editGuestbookURL` is invoked, which calls
    the Guestbook Admin portlet's `addGuestbook` or `updateGuestbook` method, as
    discussed above.
    
    The `guestbookId` needs to appear on the form so that it can be submitted.
    The user, however, doesn't need to see it. Thus, you specify `type="hidden"`:

        <aui:input type="hidden" name="guestbookId"
                value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />

    The name, of course, should be editable by the user so it's not hidden.

    The last item on the form is a button row with two buttons. The *Submit*
    button submits the form, invoking the `editGuestbookURL` which, in turn,
    invokes either the `addGuestbook` or `updateGuestbook` method of the
    Guestbook Admin portlet. The *Cancel* button invokes the `viewURL` which
    displays the default view of the Guestbook Admin portlet.

Excellent! You've now finished creating a basic UI for the Guestbook Admin 
portlet. The Guestbook Admin portlet should now match the figure below:

![Figure 1: The Guestbook Admin portlet allows administrators to add new guestbooks or to edit existing guestbooks, configure their permissions, or delete them.](../../../images/admin-app-start.png)

Test out the Guestbook Admin portlet! Try adding new guestbooks, editing 
guestbooks, and deleting guestbooks. In a later section of the Learning Path, 
you'll improve the UI by applying Liferay's Lexicon Design patterns. 

<!-- Outro to go here -->
