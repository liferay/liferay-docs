# Creating Tooltips with the Liferay UI Icon Help Tag

Unfamiliar UI can be tricky to navigate. Your users can feel as though they are 
lost in a jungle, wandering around aimlessly, desperately trying to get their 
bearings. The liferay-ui tag library offers a helpful solution in the form of 
the `liferay-ui:icon-help` tag. The Icon Help tag creates a handy tooltip ui. 
Tooltips are a useful way to communicate additional information to your users in 
a unintrusive way. The tooltips render as a easy to identify question icon, 
which informs the user through a informational pop-up dialogue on mouse over.

Now that you have a better understanding of the `liferay-ui:icon-help` tag and
all of its informational powers, you can learn how to configure it next. 

## Setting Up the Liferay UI Icon Help Tag

The example below shows how to add and use the `liferay-ui:icon-help` tag in the 
`view.jsp` of a portlet:

- **Step 1:** Reference the liferay-ui Taglib.
- **Step 2:** Configure the icon-Help Tag in the View JSP.
 	
Go through each of these steps to pack your user's heads full of information.

### Step 1: Reference the liferay-ui Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add a directive to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

You can now use the liferay-ui tags in your portlet! Continue onward to step 2.

### Step 2: Configure the icon-Help Tag in the View JSP

1.  Inside the `view.jsp` of your portlet add the `liferay-ui:icon-help` tag at 
    the bottom:

        <liferay-ui:icon-help/>

    Now that you have your tag placed, you can configure the `message` attribute
    next.
    
2.  Add the `message` attribute to the `liferay-ui:icon-help` tag and give it
    the value you want to display for the text of the tooltip dialogue:
    
        <liferay-ui:icon-help message="Help, I'm trapped inside of this icon!"/>

The figure below shows what the configuration above looks like when placed in
context:

![Figure 1: With the configuration above your tooltip should look like this.](../../images/icon-help-01.png)

Here is an example of how Liferay Portal uses a tooltip in the Control Panel:

![Figure 2: Here's an example of how Liferay Portal uses tooltips.](../../images/icon-help-02.png)

There you go! You can now arm your users with the information they need to 
better interact with your UI.

## Related Topics

 [Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)

 [Using the AlloyUI Form Validator in A Portlet](/develop/tutorials/-/knowledge_base/6-2/using-the-alloyui-form-validator-in-a-portlet)
 
 [Using Liferay UI Tabs and Sections](/develop/tutorials/-/knowledge_base/6-2/using-liferay-ui-tabs-and-sections)
