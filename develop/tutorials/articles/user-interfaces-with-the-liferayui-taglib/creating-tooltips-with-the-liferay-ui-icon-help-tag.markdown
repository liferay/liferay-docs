# Creating Tooltips with the Liferay UI Icon Help Tag [](id=creating-tooltips-with-the-liferay-ui-icon-help-tag)

An unfamiliar UI can be tricky to navigate. Your users can feel as though they 
are lost in a jungle, wandering around aimlessly, desperately trying to get 
their bearings. The `liferay-ui` tag library offers a helpful solution in the 
form of the `liferay-ui:icon-help` tag. The Icon Help tag creates a handy 
tooltip UI. Tooltips are a useful way to communicate additional information to 
your users in a unobtrusive way. The tooltips render as a easy to identify 
question icon, which informs the user through a informational pop-up dialogue on 
mouse over.

Now that you have a better understanding of the `liferay-ui:icon-help` tag and
all its informational powers, you can learn how to configure it to pack your 
user's heads full of information!

## Using the Liferay UI Icon Help Tag [](id=using-the-liferay-ui-icon-help-tag)

The example here shows how to add and use the `liferay-ui:icon-help` tag in the 
`view.jsp` of a portlet. First, you need to reference the `liferay-ui` Taglib. 
The following steps show you how to do this:

1.  Open the `view.jsp` of your portlet. Create one if it doesn't already exist.

2.  Add a directive to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

You can now use the `liferay-ui` tags in your portlet! Use the following steps 
to add and configure the `liferay-ui:icon-help` tag:

1.  Inside the `view.jsp` of your portlet, add the `liferay-ui:icon-help` tag at 
    the bottom:

        <liferay-ui:icon-help/>

    Now that you have your tag placed, you can add the `message` attribute. The 
    `message` attribute is required and contains the text to display to users on 
    mouseover.
    
2.  Add the `message` attribute to the `liferay-ui:icon-help` tag and give it
    the value you want to display for the text of the tooltip dialogue:
    
        <liferay-ui:icon-help message="Help, I'm trapped inside of this icon!"/>

It's important to note that the question icon for the `liferay-ui:icon-help` tag 
displays wherever you put it in your `jsp`. In other words, if you want it to be 
associated with a given UI element, you have to place it next to that UI 
element in the `jsp`. For example, the figure below shows what the configuration 
above looks like when the `liferay-ui:icon-help` tag placed next to a default 
user icon:

![Figure 1: With the configuration above your tooltip should look like this.](../../images/icon-help-01.png)

Liferay Portal uses the `liferay-ui:icon-help` tag to display tooltips for 
Control Panel items:

![Figure 2: Here's an example of how Liferay Portal uses tooltips.](../../images/icon-help-02.png)

There you go! You can now arm your users with the information they need to 
better interact with your UI.

## Related Topics [](id=related-topics)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)

[Using the AlloyUI Form Validator in A Portlet](/develop/tutorials/-/knowledge_base/6-2/using-the-alloyui-form-validator-in-a-portlet)
 
[Using Liferay UI Tabs and Sections](/develop/tutorials/-/knowledge_base/6-2/using-liferay-ui-tabs-and-sections)
