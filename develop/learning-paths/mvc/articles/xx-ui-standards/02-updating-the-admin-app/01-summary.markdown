# Guestbook Admin App UI Summary [](id=guestbook-admin-app-ui-summary)

This section summarizes the UI updates that are the same across the Guestbook 
Admin app and Guestbook end-user app. These steps are covered in detail for the 
end-user Guestbook app in the previous sections. The completed code for these 
updates can be found [here](https://github.com/liferay/liferay-docs/tree/master/develop/learning-paths/mvc/code/guestbook-ui-complete/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookadminmvcportlet).

The following updates must be made to the Guestbook Admin App:

- Update the Main View's Navigation and Search Container
- Add the Management Bar with Sort Filters and Navigation Filters
- Update the Edit View's Form and Navigation

The steps required for these updates are summarized next.

## Guestbook Admin Main View Updates [](id=guestbook-admin-main-view-updates)

1.  Open the Guestbook Admin's `view.jsp` and add a navigation bar with the 
    label `Guestbooks`:
    
        <aui:nav-bar markupView="lexicon">
        	<aui:nav cssClass="navbar-nav">
        		<aui:nav-item label="Guestbooks" selected="<%= true %>" />
        	</aui:nav>
        </aui:nav-bar> 

2.  Remove the icon images from the Actions Menu in the `guestbook_actions.jsp` 
    and update the `icon-menu` to match the Lexicon guidelines:

        <liferay-ui:icon-menu
            direction="left-side"
            icon="<%= StringPool.BLANK %>"
            markupView="lexicon"
            message="<%= StringPool.BLANK %>"
            showWhenSingleIcon="<%= true %>"
        >

        <liferay-ui:icon
    			message="Edit"
    			url="<%= editURL.toString() %>"
    		/>
        ...
        <liferay-ui:icon
    			message="permissions"
    			url="<%= permissionsURL %>"
    		/>

3.  Update the Search Container to expand the width of the container using a 
    fluid `div`:
   
       <div class="container-fluid-1280">

4.  Add the `table-cell-content` CSS class to the Search Container `name` column 
    to indicate it as the principal column:
    
        <liferay-ui:search-container-column-text
        	cssClass="table-cell-content"
        	property="name"
        />
    
The Management Bar updates are covered next.

## Management Bar Updates [](id=management-bar-updates)

1.  Set the default display style to `list` in the `view.jsp`:
    
        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle", 
        "list");
        %>

2.  Add the renderURL and `<liferay-frontend:management-bar-buttons>` tags below 
    the `aui:nav-bar` tags:

        <liferay-portlet:renderURL varImpl="viewPageURL">
            <portlet:param name="mvcPath" value="/html/guestbookadminmvcportlet/view.jsp" />
            <portlet:param name="displayStyle" value="<%= displayStyle %>" />
        </liferay-portlet:renderURL>

        <liferay-frontend:management-bar>
          <liferay-frontend:management-bar-buttons>
            <liferay-frontend:management-bar-display-buttons
              displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
              portletURL="<%= viewPageURL %>"
              selectedDisplayStyle="<%= displayStyle %>"
            />
          </liferay-frontend:management-bar-buttons>
        </liferay-frontend:management-bar>

3.  Implement the Icon, Descriptive, and List display views in between the 
    `liferay-ui:search-container-row` tags:

        <c:choose>
            <c:when test='<%= Objects.equals(displayStyle, "icon") %>'>

              <%
              row.setCssClass("entry-card lfr-asset-item");
              %>

              <liferay-ui:search-container-column-text>
                <liferay-frontend:icon-vertical-card
                  actionJsp="/html/guestbookadminmvcportlet/guestbook_actions.jsp"
                  actionJspServletContext="<%= application %>"
                  icon="list"
                  resultRow="<%= row %>"
                  title="<%= HtmlUtil.escape(guestbook.getName()) %>"
                />
              </liferay-ui:search-container-column-text>
            </c:when>
            <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
              <liferay-ui:search-container-column-icon
                icon="list"
              />

              <liferay-ui:search-container-column-text
                colspan="<%= 2 %>"
              >
                <h5>
                  <%= HtmlUtil.escape(guestbook.getName()) %>
                </h5>
              </liferay-ui:search-container-column-text>

              <liferay-ui:search-container-column-jsp
                path="/html/guestbookadminmvcportlet/guestbook_actions.jsp"
              />
            </c:when>
            <c:when test='<%= Objects.equals(displayStyle, "list") %>'>
              <liferay-ui:search-container-column-text
                cssClass="table-cell-content"
                property="name"
              />

              <liferay-ui:search-container-column-jsp
                align="right"
                path="/html/guestbookadminmvcportlet/guestbook_actions.jsp"
              />
            </c:when>
        </c:choose>

3.  Update the Search Iterator to use the `displayStyle` and `lexicon` markup 
    view:

        <liferay-ui:search-iterator 
            displayStyle="<%= displayStyle %>" 
            markupView="lexicon" 
        />

4.  Add the Navigation Filters below the display buttons, using the
    `<liferay-frontend:management-bar-navigation />` taglib:
    
        <liferay-frontend:management-bar-filters>
        	<liferay-frontend:management-bar-navigation
        		navigationKeys='<%= new String[] {"all"} %>'
        		portletURL="<%= viewPageURL %>"
        	/>
        </liferay-frontend:management-bar-filters>

5.  Create the `GuestbookNameComparator` class, update the 
    `GuestbookLocalServiceImpl`, and rebuild services.

6.  Implement the Sort Filters in the `view.jsp`, using the 
    `<liferay-frontend:management-bar-sort />` taglib:
    
        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

        String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

        boolean orderByAsc = false;

        String orderByType = ParamUtil.getString(request, "orderByType", "asc");

        if (orderByType.equals("asc")) {
        	orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equals("name")) {
        	orderByComparator = new GuestbookNameComparator(orderByAsc);
        }
        %>
        ...
        <liferay-frontend:management-bar-sort
        	orderByCol="<%= orderByCol %>"
        	orderByType="<%= orderByType %>"
        	orderColumns='<%= new String[] {"name"} %>'
        	portletURL="<%= viewPageURL %>"
        />
        ...
        <liferay-ui:search-container-results
        	results="<%= GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId, 
          searchContainer.getStart(), searchContainer.getEnd(), 
          orderByComparator) %>"
        />
    
7.  Export the `GuestbookNameComparator` class in the Guestbook's `init.jsp`.

That sums up the steps for the Management Bar. The remaining updates are covered 
next.

## Guestbook Admin Edit View Updates [](id=guestbook-admin-edit-view-updates)

1.  Open the `edit_guestbook.jsp`, move the title and associated back button 
    to the portlet title:

        <%
        ...
        String redirect = ParamUtil.getString(request, "redirect");
        String headerTitle = (guestbook == null) ? "Add Guestbook" : guestbook.getName();

        portletDisplay.setShowBackIcon(true);
        portletDisplay.setURLBack(redirect);

        renderResponse.setTitle(headerTitle);
        %>
        
2.  Open the `guestbook_actions.jsp` and add the `redirect` portlet parameter to 
    the `editURL` renderURL variable:
    
        <portlet:renderURL var="editURL">
    			<portlet:param name="guestbookId" value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
    			<portlet:param name="mvcPath" value="/html/guestbookadminmvcportlet/edit_guestbook.jsp" />
    			<portlet:param name="redirect" value="${currentURL}" />
    		</portlet:renderURL>

3.  Open the `edit_guestbook.jsp` and update the form to use a fluid container:

        <aui:form 
            action="<%= editGuestbookURL %>" 
            cssClass="container-fluid-1280" 
            name="fm"
        >

4.  Update the form's buttons to use the `btn-lg` CSS class:

        <aui:button-row>
        	<aui:button cssClass="btn-lg" type="submit" />

        	<aui:button cssClass="btn-lg" onClick="<%= viewURL %>" type="cancel" />
        </aui:button-row>

5.  Wrap the form field's `<aui:fieldset>` tags with an `<aui:fieldset-group>` 
    with the Lexicon markup view:
    
        <aui:fieldset-group markupView="lexicon">
        	<aui:fieldset>
        		<aui:input name="name" />
        	</aui:fieldset>
        </aui:fieldset-group>

After following the steps above, the Guestbook Admin app's UI now has all the 
updates that the end user Guestbook app has. In the next section, you'll make an 
additional update to the Guestbook Admin's UI, by including an Add Menu.
