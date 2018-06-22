# Liferay UI Icon Help [](id=liferay-ui-icon-help)

The icon help tag lets you communicate additional information to your users in 
an unobtrusive way. It renders as an iconic question mark that provides more 
information through a pop-up tooltip on mouse over. You can see an example of 
this in the Control Panel:

![Figure 1: Here's an example of the icon help tag.](../../../images/liferay-ui-taglib-tooltip.png)

+$$$

**Note:** If you have installed a custom theme you may also need to add the
following imports to your `view.jsp` to make `liferay-ui:icon-help` tag work:

    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
    <liferay-theme:defineObjects />

$$$

Add the `<liferay-ui:icon-help/>` tag next to the UI that needs tooltip
information. Define the informational text with the required `message`
attribute. Below is an example snippet for one of the Server Administration's
clean up actions:

    <h5>
        <liferay-ui:message key="clean-up-permissions" />
        <liferay-ui:icon-help message="clean-up-permissions-help" />
    </h5>

![Figure 2: help icons are used throughout the Control Panel.](../../../images/liferay-ui-taglib-tooltip-02.png)

Note that the message is supplied via a 
[language key](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#where-do-i-put-language-files). 
While you can use a string for the tooltip's message for testing purposes, a 
language key is considered best practice and should be used in production.

## Related Topics [](id=related-topics)

[Clay Badges](/develop/tutorials/-/knowledge_base/7-1/clay-badges)

[Clay Stickers](/develop/tutorials/-/knowledge_base/7-1/clay-stickers)

[Liferay UI Icon Menus](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-menus)
