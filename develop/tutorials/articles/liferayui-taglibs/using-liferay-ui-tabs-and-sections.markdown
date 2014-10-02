# Using Liferay UI Tabs and Sections [](id=using-liferay-uitabs-and-liferay-uisections)

Tabs are a good way to keep your portlet's UI clean and organized. Liferay's 
liferay-ui tag library provides a simple and easy to use solution making this 
possible. Using the `liferay-ui:tabs` tag in conjunction with the 
`liferay-ui:section` tag, you can create a clean and well organized UI in a 
short amount of time.

![Figure 1: Using tabs can streamline your portlet's interface.](../../images/liferay-ui-tabs-03.png)

This tutorial shows you how to use the `liferay-ui:tabs` tag to implement a 
tabbed interface in your portlet. When you're done you'll be able to keep tabs 
on anything!

## Setting Up the liferay-ui:tabs Tag

Using the `liferay-ui:tabs` tag is a fairly simple process. The examples below
show how to add and use the `liferay-ui:tabs` tag with the `liferay-ui:section`
tag in the `view.jsp` of a portlet. There are three basic steps to accomplish 
this:

- **Step 1:** Reference the Liferay UI Taglib.
- **Step 2:** Insert and Configure the Liferay UI Tabs Tag in the View JSP.
- **Step 3:** Insert and Configure the Liferay UI Section Tags in the View JSP. 

Follow these steps and you'll have a well organized UI for your portlet in no 
time. Now, onwards to the first step!

### Step 1: Reference the Liferay UI Taglib

1. Open the `view.jsp` of your portlet. Create one if it does not already exist.

2. Add a directive at the top of the file to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

With the first step out of the way you are now able to use `liferay-ui` tags in 
your portlet. Next up, step two!

### Step 2: Insert and Configure the Liferay UI Tabs Tag in the View JSP

This step is where you implement the tabs in your portlet. Everything you do in 
this step determines how your UI looks and functions. 

Add the following code to your `view.jsp`:

    <liferay-ui:tabs names="tab1,tab2,tab3">
    
    </liferay-ui:tabs>

This adds three tabs to your portlet, named `tab1`, `tab2`, and `tab3`. Of 
course, you can use more or fewer tabs if you wish. You can also name them 
anything you like.
    
That's all there is to it--you now have fully functional tabs! At the moment 
though your tabs are not very interesting. While they work, they're not 
associated with any content or functionality. You'll address this next. 

### Available Attributes for the Liferay UI Tabs

There are several attributes that you can use with the `liferay-ui:tabs` tag 
that add functionality to your tabs. The list here shows these attributes.

**backLabel:** Sets a label for the back URL.

**backURL:** Sets a URL for back.

**formName:** Sets the component's form name.

**names:** Sets the names of the tabs. Each name must be separated by a comma.

**onClick:** Sets a function to be called when users click the tabs.

**param:** Sets the variable to refer to the tabs component.

**portletURL:** Sets a URL to reference the portlet itself.

**refresh:** Sets whether the page refreshes when a tab is clicked. The default 
value is `"true"`.

**tabsValues:** Sets the tab value names. Values must follow the same order as
the tabs definied in the `names` attribute. Each value must be separated by a 
comma with no spaces.

**type:** Sets the type of tabs. Possible values are `"tabs"` and `"pills"`.

**url:** Sets the URL all the tabs are linked to when clicked. You can also set 
the URL of each tab individually by using the `url[tab-number]` attribute.

**url0-9:** Sets the URL the given tab number links to.

**value:** Sets the tab that is active when users first visit the page your 
portlet is on. By default, this is the first tab.

Now that you know what attributes are available, you can make use of them to 
spice up your tabs!

### Step 3: Insert and Configure the Liferay UI Section Tags in the View JSP

Before you start adding attributes, you need to create sections inside the 
`liferay-ui:tabs` tag that correspond to each of your tabs. This will let you 
associate content with specific tabs. The `liferay-ui:section` tag is used to 
accomplish this. In the `view.jsp`, replace your previous `liferay-ui:tabs` tag 
with the following:

    <liferay-ui:tabs names="tab1,tab2,tab3" refresh="false" tabsValues="tab1,tab2,tab3">
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

Note that the text for each tab is bounded by a `liferay-ui:section` tag. Your 
portlet should look similar to the screenshot here:

![Figure 2: Placing content inside sections allows you to associate it with individual tabs.](../../images/tabs-01.png)

There you have it! The `liferay-ui:tabs` and `liferay-ui:section` tags work 
together to produce a clean and well organized UI for your apps. However, the 
placeholder text for each tab still isn't very exciting. Now you can replace the 
placeholder text with the content you want to appear in each tab. While it's 
possible to add content directly inside of the `liferay-ui:section` tags, often 
times it's best to place content in its own JSP and then call that file using 
the `<%@ include file="filepath"%>` directive. The code below demonstrates how 
to configure your JSPs in this manner.

First, create the JSP that you'll use to add content to a tab. The JSP in this 
example is called `sample.jsp`. After adding the necessary taglib references, go 
ahead and add the content you want to use. This example uses the 
`liferay-ui:input-time-zone` tag to provide a dropdown menu that lets the user 
select their time zone. Here is the code for the `sample.jsp`:
    
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    Select Time Zone: 

    <liferay-ui:input-time-zone
	    name="timezone"
		value="Europe/Paris"
        displayStyle="0"
	/>

Now you just need to add the `include` directive with your new JSP in the 
appropriate section of your `view.jsp`. In this example, the third tab contains 
the reference to the new JSP:
    
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
	
With this configuration your portlet should look similar to the screenshot here:

![Figure 3: Here's what the newly configured tabs look like.](../../images/tabs-02.png)

Great! In this tutorial you learned how to implement a tabbed interface in your 
portlets. Now you can keep tabs on all the things you wish!

## Related Topics

[Using Liferay UI Icon](/tutorials/-/knowledge_base/using-liferay-ui-icon-in-a-portlet)
