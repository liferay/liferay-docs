# Creating the Display Style Buttons [](id=creating-the-display-style-buttons)

The next task is to add the Display Style buttons to the Management Bar for
the Guestbook app. The Display Style buttons give you and your users control
over how your app's search container results are displayed. There are three 
display views: List, Descriptive, and Icon. Before you create the Display Style 
buttons, you must add some dependencies.

Get started by adding the required dependencies to your `build.gradle` file next.

## Adding Dependencies [](id=adding-dependencies)

Follow these steps to add the required dependencies:

1.  The `liferay-frontend` taglibs generate the markup necessary to create the
    Clay UI. You must therefore add the following dependency to the frontend 
    taglib in your `guestbook-web` module project's `build.gradle` file:

        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib",
        version: "2.0.0"

2.  Add the following imports to the Guestbook app's `init.jsp`:

        <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
        <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
        <%@ page import="java.util.Objects" %>
        
        <liferay-frontend:defineObjects />

Now that the dependencies are added, you can implement the List view next.

## Implementing the List View [](id=implementing-the-list-view)

Follow these steps to implement the List view:

1.  Open up the `guestbook-web` module project's `view.jsp` and add the 
    following variable to the java scriplet at the top of the file:

        String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

2.  Set the render URL for the portlet. Add the render URL below the closing 
    `</aui:button>` tag:

        <liferay-portlet:renderURL varImpl="viewPageURL">
        	<portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
        	<portlet:param name="guestbookId" value="<%= String.valueOf(guestbookId) %>" />
        </liferay-portlet:renderURL>

3.  Add the `liferay-frontend:management-bar` taglibs just below the render URL 
    you just added:

        <liferay-frontend:management-bar>
        	<liferay-frontend:management-bar-buttons>
        		<liferay-frontend:management-bar-display-buttons
        			displayViews='<%= new String[] {"list"} %>'
        			portletURL="<%= viewPageURL %>"
        			selectedDisplayStyle="<%= displayStyle %>"
        		/>
        	</liferay-frontend:management-bar-buttons>
        </liferay-frontend:management-bar>

The configuration above adds `list` to the set of options for the display views 
and sets the `portletURL`. Finally, the `selectedDisplayStyle` attribute sets 
the display style for the view. At the moment, there is just the one display 
style, `list`, but you'll add more to this as you create the remaining views. 

Next you can implement the Descriptive view.  

## Implementing the Descriptive View [](id=implementing-the-descriptive-view)

Follow these steps to implement the Descriptive view:

1.  Add `descriptive` to the String Array for the `displayViews` attribute of
    the `liferay-frontend:management-bar-display-buttons`:

        displayViews='<%= new String[] {"descriptive", "list"} %>'

2.  Now that you have more than one option for `displayViews`, you need to add a 
    check to display the proper view when it is selected in the Management Bar.
    Replace the contents between the `liferay-ui:search-container-row` taglibs 
    with the following code:

        <c:choose>
    				<c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
    					<liferay-ui:search-container-column-icon
    						icon="quote-left"
    					/>

    					<liferay-ui:search-container-column-text
    						colspan="<%= 2 %>"
    					>
    						<h5>
    							<%= HtmlUtil.escape(entry.getName()) %>
    						</h5>

    						<h6 class="text-default">
    							<span><%= HtmlUtil.escape(entry.getMessage()) %></span>
    						</h6>
    					</liferay-ui:search-container-column-text>

    					<liferay-ui:search-container-column-jsp
    						path="/html/guestbookmvcportlet/guestbook_actions.jsp"
    					/>
    				</c:when>
    				<c:when test='<%= Objects.equals(displayStyle, "list") %>'>
    					<liferay-ui:search-container-column-text
    						property="message"
    					/>

    					<liferay-ui:search-container-column-text
    						property="name"
    					/>

    					<liferay-ui:search-container-column-jsp
    						path="/html/guestbookmvcportlet/guestbook_actions.jsp"
    					/>
    				</c:when>
        </c:choose>

This wraps the list view in a conditional check, and adds the descriptive view
condition, which organizes the search container results
into a horizontal [card](https://liferay.github.io/clay/content/cards/), with
an icon, summarized message, and guest name.

![Figure 1: The descriptive view gives you a quick view of the guestbook message in a horizontal card.](../../../../../images/descriptive-button-added.png)

You can implement the Icon view next.

## Implementing the Icon View [](id=implementing-the-icon-view)

Follow these steps to implement the Icon view:

1.  Add `icon` to the String Array for the `displayViews` attribute of
the `liferay-frontend:management-bar-display-buttons`:

        displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'

2.  Add the icon view condition above the descriptive view condition within the  
    `liferay-ui:search-container-row` taglibs:

            <c:when test='<%= Objects.equals(displayStyle, "icon") %>'>

              <%
              row.setCssClass("entry-card lfr-asset-item");
              %>

              <liferay-ui:search-container-column-text>
                <liferay-frontend:icon-vertical-card
                  actionJsp="/html/guestbookmvcportlet/guestbook_actions.jsp"
                  actionJspServletContext="<%= application %>"
                  icon="quote-left"
                  resultRow="<%= row %>"
                  subtitle="<%= HtmlUtil.escape(entry.getMessage()) %>"
                  title="<%= HtmlUtil.escape(entry.getName()) %>"
                />
              </liferay-ui:search-container-column-text>
            </c:when>

The Icon view is similar to the Descriptive view, except it places the search
container results into a vertical card, which emphasizes the icon, and displays
a summarized message along with the guest name. You may notice that the cards 
in the Icon view are oversized. This issue is fixed in the next section and will 
look like the figure below when complete:

![Figure 2: The icon view emphasizes the icon and gives you a quick view of the message and guest name.](../../../../../images/icon-view-with-search-iterator.png)

You have one last touch to add to the search container to complete the display
style buttons. You'll make this change next.

## Styling the Search Container [](id=styling-the-search-container)

You've implemented the display style views for the Management Bar, but you
haven't styled the Search Container to reflect these changes.

Add the `displayStyle` attribute and `markupView` attribute to the
`<liferay-ui:search-iterator />` tag with the following values:

    <liferay-ui:search-iterator
        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
    />

The Search Container styles are updated and the display style buttons are fully
implemented! Your app should now look like the figure below with the List view 
selected:

![Figure 3: The display style buttons provide handy styles for your app's search container results.](../../../../../images/display-buttons-complete.png)

In the next section of the Learning Path you'll create the Management Bar 
Navigation Filters.
