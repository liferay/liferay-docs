# Using liferay-ui:tabs and liferay-ui:sections [](id=using-liferay-uitabs-and-liferay-uisections)

Tabs are a good way to keep your portlet's UI clean and organized. Liferay 
provides a simple and easy to use solution to make this possible in their 
liferay-ui tag library. Using the liferay-ui:tabs tag in conjunction with the 
liferay-ui:section tag you can create a well organized and clean UI in a short 
amount of time.

This tutorial will cover how to use the liferay-ui:tabs tag in your portlet. 
When you're done you'll be able to keep tabs on anything!

## Setting Up the Liferay-UI:Tabs Tag

Using the liferay-ui:tabs tag is a fairly simple process. The examples below
show how to add and use the `liferay-ui:tabs` tag with the `liferay-ui:section`
tag in the `view.jsp` of a portlet:

- **Step 1:** Reference the liferay UI Taglib.
- **Step 2:** Insert and Configure the Liferay UI Tabs Tag in the View JSP.
- **Step 3:** Insert and Configure the Liferay UI section Tags in the View JSP. 
 	
Follow these steps and you'll have a well organized UI for your portlet in no 
time!

## Step 1: Reference the Liferay UI Taglib

1. Open the `view.jsp` of your portlet. Create one if it does not already exist.

2. Add a directive to reference the liferay-ui taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    
With the first step out of the way you are now able to use liferay-ui
tags in your portlet. Next up step 2!
    
## Step 2: Insert and Configure the Liferay UI Tabs Tag in the View JSP

This step is where the UI comes into play. Everything you do in this step will
determine how your UI functions and looks. 

Inside your `view.jsp` add the following code:

    <liferay-ui:tabs names="tab1, tab2, tab3">
    
    </liferay-ui:tabs>

That's all there is to it. You have fully functional tabs! At the moment though 
your tabs are not all that interesting. There are several attributes to 
configure for the Liferay-UI Tabs tag that can add some more functionality to 
your tabs. The list below shows the available attributes to configure.

### Available Attributes for the Liferay UI Tabs

**backLabel:** Sets a label for the back URL.

**backURL:** Sets a URL for back.

**formName:** Sets the component's form name.

**names:** Sets the names of the tabs. Each name must be separated by a comma.

**onClick:** Sets a function to be called on users clicking the tabs.

**param:** Sets the variable to refer to the tabs component.

**portletURL:** Sets a URL to reference the portlet itself.

**refresh:** Whether the page refreshes when a tab is clicked. (Default: true)

**tabValues:** Sets the tab value names. Values must follow the same order as
the names attribute. Each value must be separated by a comma with no spaces.

**type:** Sets the type of tabs. Possible values are tabs and pills.

**url:** Sets the URL to which all the tabs link to when clicked. You can also
set each tab's URL individually using the url[tab-number] attribute.

**url0-9:** Sets the URL to which the given tab number links.

**value:** Sets the active tab to the value given from the tabsValues attribute.
No matter which tab is selected, the tab given for the value will remain active.

Now that you know what attributes you have available to you, You can move on to
step 3!

## Step 3: Insert and Configure the Liferay UI Section Tags in the View JSP

Still inside the `view.jsp` replace your previous liferay-ui:tabs tag with the
following:

    <liferay-ui:tabs names="tab1, tab2, tab3" refresh="false" tabsValues="tab1,tab2,tab3">
        <liferay-ui:section>
		    Text for Tab 1.
	    </liferay-ui:section>
	    <liferay-ui:section>
		    Text for Tab 2.
	    </liferay-ui:section>
	    <liferay-ui:section>
		    Text for Tab 3.
	    </liferay-ui:section>
    </liferay-ui:tabs>
    
With the addition of the liferay-ui:section tags, you can now see unique content
for each tab. Your portlet should look similar to the figure below:

![Figure 1: Here's what the fully configured tabs look like.](../../images/tabs-01.png)

There you have it. The Liferay UI Tabs and Sections tags work together to make a
powerful combination that effectively produces a clean and well organized UI for
your apps. There are several use cases for tabs. One such case is to display
JSPs in each of the tabs. Using the `<%@ include file="filepath"%>` directive 
you can easily add a JSP to a `<liferay-ui:section>` tag for your tabs. The code 
below demonstrates how to configure your JSPs to create the case above:

Here is the code for the View JSP:
    
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    <liferay-ui:tabs names="tab1, tab2, tab3" refresh="false">
  		<liferay-ui:section>
		    Text for Tab 1.
	    </liferay-ui:section>
	    <liferay-ui:section>
		    Text for Tab 2.
	    </liferay-ui:section>
	    <liferay-ui:section>
		    <%@ include file="html/sample.jsp" %>
		</liferay-ui:section>
    </liferay-ui:tabs>
    
Here is the code for the Sample JSP:
    
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    Select Time Zone: 

    <liferay-ui:input-time-zone
	    name="timezone"
		value="Europe/Paris"
        displayStyle="0"
	/>
	
With the configuration above your portlet should look like the figure below:

![Figure 2: Here's what the newly configured tabs look like.](../../images/tabs-02.png)

Now you can keep tabs on all the things you wish!

## Related Topics

 [Using Liferay UI Icon](http://dev.liferay.com/tutorials/-/knowledge_base/using-liferay-ui-icon-in-a-portlet)
