# Creating Tooltips with the Liferay UI Icon Help Tag [](id=creating-tooltips-with-the-liferay-ui-icon-help-tag)

An unfamiliar UI can be tricky to navigate. Your users can feel as though they 
are lost in a jungle, wandering around aimlessly, desperately trying to get 
their bearings. The `liferay-ui` tag library offers a helpful solution in the 
form of the `liferay-ui:icon-help` tag. The Icon Help tag creates a handy 
tooltip UI. Tooltips are a useful way to communicate additional information to 
your users in a unobtrusive way. The tooltips render as an easy to identify 
question icon that informs the user through a informational pop-up on mouse
over.

Now that you have a better understanding of the `liferay-ui:icon-help` tag and
all its informational powers, you can learn how to configure it to pack your 
user's heads full of information!

## Using the Liferay UI Icon Help Tag [](id=using-the-liferay-ui-icon-help-tag)

The example here shows how to add and use the `liferay-ui:icon-help` tag in the 
`view.jsp` of a portlet. First, you need to reference the `liferay-ui` taglib. 
The following steps show you how to do this:

1.  Open your portlet's `view.jsp`. 

2.  Add a directive to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

+$$$

**Note:** If you have installed a custom theme you may also need to add the
following imports to your `view.jsp` for the `liferay-ui:icon-help` tag to work
properly:

    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
    <liferay-theme:defineObjects />

$$$

You can now use the `liferay-ui` tags in your portlet! Follow these steps to add
and configure the `liferay-ui:icon-help` tag:

1.  Inside the `view.jsp` of your portlet, add the `liferay-ui:icon-help` tag at 
    the bottom:

        <liferay-ui:icon-help/>

    Now that you have your tag placed, you can add the `message` attribute. The 
    `message` attribute is required and contains the text to display to users on 
    mouse over.

2.  Add the `message` attribute to the `liferay-ui:icon-help` tag and give it
    the text you want to display for the tooltip:
    
        <liferay-ui:icon-help message="Help, I'm trapped inside this icon!"/>

The message is supplied as a string at the moment. As this is not best practice, 
you should supply the message via a language key. You'll take care of this next.

## Creating a Language Key Hook for the Help Message [](id=creating-a-language-key-hook-for-the-help-message)

In order to supply the language key for the help message you will need to create 
a hook.

Follow the steps below to create the language key hook:
    
1.  Goto *File*&rarr;*New*&rarr;*Liferay Plugin Project*.

2.  Set the *Project name* as `help-message-language-hook` and *Display name* 
    as `Help Message Language Hook`.

3. Set the proper SDK and runtime, select *Hook* for the *Plugin type*, and
   click *Finish*.

    The skeleton for the hook is complete. Now you need to configure the hook to
    modify the existing language properties. In this case you'll be modifying
    the English US language properties, but the process can be used to hook into
    any existing language properties file.

1. Right-click the language key hook you just created in the package explorer
   on the left and select *New*&rarr;*Liferay Hook Configuration*.

2. With `help-message-language-hook` set as the *Hook plugin project* select
   *Language properties* for the *hook type* and *click* *Next*.

3. Leave the default *Content folder* and click *Add*.

4. Enter *Language_en.properties* for the property file, click *OK* and 
   *Finish*.

5. Open the `docroot/WEB-INF/src` folder of the `help-message-language-hook` 
   and open the `Language_en.properties` file in the `content` package.

6. Add the following language key to the file and Save it:

        help-trapped = Help, I'm trapped inside this icon!

    Now that you have the language key defined you can update the tag to use it.     
    
7. In your `view.jsp`, update the `liferay-ui:icon-help` tag to look like the 
   following code:

        <liferay-ui:icon-help message="help-trapped"/>
    
8. Redeploy the portlet, hover over top of the help icon. The help message now 
   uses the language key you just created!

It's important to note that the question icon for the `liferay-ui:icon-help` tag 
shows wherever you put it in your `jsp`. In other words, if you want it to be 
associated with a given UI element, you have to place it next to that UI element 
in the `jsp`. For example, the figure below shows what the configuration above 
looks like when the `liferay-ui:icon-help` tag is placed next to a default user 
icon:

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
