# Using Liferay UI Tabs and Sections [](id=using-liferay-ui-tabs-and-sections)

Using tabs is a good way to keep your portlet's UI clean and organized.
Liferay's `liferay-ui` tag library provides simple, easy-to-use tags to
implement tabs in your UI. By using the `liferay-ui:tabs` tag in conjunction
with the `liferay-ui:section` tag, you can quickly create a tidy UI that's easy
to navigate. 

![Figure 1: Using tabs can streamline your portlet's interface.](../../images/liferay-ui-tabs-03.png)

This tutorial shows you how to use the `liferay-ui:tabs` tag and
`liferay-ui:section` tag to implement a tabbed interface in your portlet. When
you're done you'll be able to ["keep tabs on"](http://idioms.thefreedictionary.com/keep+tabs+on) anything!

## Setting Up the liferay-ui:tabs Tag [](id=setting-up-the-liferay-uitabs-tag)

The `liferay-ui:tabs` tag is easy to use. The examples below show how to add and
use the `liferay-ui:tabs` tag with the `liferay-ui:section` tag in your
portlet's view. There are three basic steps to accomplish this:

- **Step 1:** Reference the Liferay UI tag library.
- **Step 2:** Insert and configure the Liferay UI tabs tag in your view JSP.
- **Step 3:** Insert and configure the Liferay UI section tags in your view JSP. 

Follow these steps and you'll have a well-organized UI for your portlet in no 
time. Now, onward to building a tabbed interface!

### Step 1: Reference the Liferay UI Taglib [](id=step-1-reference-the-liferay-ui-taglib)

1. Open one of your portlet's view JSPs. Create one if it doesn't already exist.

2. Add a directive at the top of the file to reference the `liferay-ui` tag
library:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

With the first step out of the way, you can now use `liferay-ui` tags in your
portlet. Next up, you'll add tabs! 

### Step 2: Insert and Configure the Liferay UI Tabs Tag in Your View JSP [](id=step-2-insert-and-configure-the-liferay-ui-tabs-tag-in-your-view-jsp)

Specify a `<liferay-ui:tabs>` tag and add a `names` attribute to it, with the
names of the tabs you want to create. You can specify as many tabs as you like.

For example, if you wanted to add three tabs named `tab1`, `tab2`, and `tab3`,
you could add this code to your JSP:

    <liferay-ui:tabs names="tab1,tab2,tab3">

    </liferay-ui:tabs>

You can deploy your portlet's changes and visit this view JSP that has the tabs,
to make sure they're shown just as you specified. Did you notice, however, that
the first tab is always selected? While your tabs resemble the structure you
want, they're not associated with any content or functionality. You can use the
`liferay-ui:tabs` tag's attributes and the `liferay-ui:section` tag to add
functionality and content. 

There are several attributes that you can use with the `liferay-ui:tabs` tag 
that add functionality to your tabs. Below is a listing of some of these
attributes.

**backLabel:** Sets a label for the back URL.

**backURL:** Sets a URL for back.

**formName:** Sets the component's form name.

**names:** Sets the names of the tabs. Each name must be separated by a comma.

**onClick:** Sets a function to be called when users click the tabs.

**param:** Sets the variable to refer to the tabs component.

**portletURL:** Sets a URL to reference the portlet itself.

**refresh:** Sets whether the page refreshes when a tab is clicked. The default 
value is `true`.

**tabsValues:** Sets the tab value names. Values must follow the same order as
the tabs defined in the `names` attribute. Each value must be separated by a 
comma with no spaces.

**type:** Sets the type of tabs. Possible values are `tabs` and `pills`.

**url:** Sets the URL all the tabs are linked to when clicked. You can also set 
the URL of each tab individually by using the `url[tab-number]` attribute.

**url\[0-9\]:** Sets the URL the given tab number links to.

**value:** Sets the tab that is active when users first visit the page your 
portlet is on. By default, the first tab is active.

Now that you know what attributes are available, you can use them to spice up
your tabs! 

### Step 3: Insert and Configure the Liferay UI Section Tags in Your View JSP [](id=step-3-insert-and-configure-the-liferay-ui-section-tags-in-your-view-jsp)

Before you start adding attributes, you should create a *section* for each of
your tabs. The sections allow you to associate content with each tab. Inside 
your `liferay-ui:tabs` tag you can add a `liferay-ui:section` tag for each of
the tabs you named in the `names` attribute of your `liferay-ui:tabs` tag.
Within each section, you can add HTML content or add content indirectly by
including content from another JSP. 

Here's example code that demonstrates adding sections for three tabs named
*tab1*, *tab2*, and *tab3*:

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

Note that the text for each tab in this example is bounded by a
`liferay-ui:section` tag. The figure below shows what this sample code looks
like. Your portlet's tabs and sections may look similar to it.

![Figure 2: Placing content inside sections allows you to associate it with individual tabs.](../../images/tabs-01.png)

There you have it! The `liferay-ui:tabs` and `liferay-ui:section` tags work
together to produce a clean, well-organized UI for your app. If you're only
using text, however, your tabs may not seem very exciting. But you can add any
HTML content you want. While it's possible to add content directly inside the
`liferay-ui:section` tags, it may be better to place content in separate JSPs
and reference them from the sections by adding an `<%@ include
file="filepath"%>` directive as the `liferay-ui:section` tag's content. The code
below demonstrates how to configure JSPs in this manner. 

For example, if you wanted to provide a means for users to select a time zone,
you could add a `liferay-ui:input-time-zone` tag to a separate JSP and then
include it in a tab section of your main view JSP. Here's example code for a JSP
file named `sample.jsp` that contains this time zone component:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    Select Time Zone: 

    <liferay-ui:input-time-zone
        name="timezone"
        value="Europe/Paris"
        displayStyle="0"
    />

Once you've prepared a separate content JSP, you can add an `include` directive
to reference it in an appropriate `liferay-ui:section` tag in your view JSP. 

In the example below, the third tab refers to a separate content JSP file named
`sample.jsp`: 
    
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    <liferay-ui:tabs names="tab1,tab2,tab3" refresh="false" tabsValues="tab1,tab2,tab3">
        <liferay-ui:section>
            Text for Tab 1.
        </liferay-ui:section>
        <liferay-ui:section>
            Text for Tab 2.
        </liferay-ui:section>
        <liferay-ui:section>
             <%@ include file="sample.jsp" %>
        </liferay-ui:section>
    </liferay-ui:tabs>

If you configured a portlet that implemented the code from these examples, the
portlet would look like the screenshot in the figure below. 

![Figure 3: Here's an example of what a tab could look like referencing useful content like this time zone selector.](../../images/tabs-02.png)

Great! In this tutorial you learned how to implement a tabbed interface for your 
portlet. Now you can "keep tabs on" all kinds of portlets!

## Related Topics [](id=related-topics)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

<!--
[Using Liferay UI Icon](/develop/tutorials/-/knowledge_base/6-2/using-icons-with-the-liferay-ui-taglib)
-->
